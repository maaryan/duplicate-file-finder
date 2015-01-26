package com.maaryan.filedup;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maaryan.filedup.conf.FileDupConfig;
import com.maaryan.filedup.thread.FileMetaFeeder;
import com.maaryan.filedup.thread.FolderScanner;
import com.maaryan.filedup.thread.FolderScannerRejectionHandler;
import com.maaryan.filedup.thread.SleepUtil;
import com.maaryan.filedup.vo.FileIndex;
import com.maaryan.filedup.vo.FileMeta;

public class FileIndexer implements Runnable{
	protected Logger logger = LoggerFactory
			.getLogger(FileIndexer.class);
	protected BlockingQueue<File> fileQueue;
	protected FileIndex fileIndex = new FileIndex();
	protected FileMetaFeeder fileMetaFeeder;
	protected FileDupConfig fileDupConfig;
	protected ThreadPoolExecutor folderScannerExector;
	protected Thread fileMetaFeederTask;
	protected Comparator<File> fileSizeComparator = new Comparator<File>() {
		@Override
		public int compare(File o1, File o2) {
			return Long.compare(o1.length(), o2.length());
		}
	};
	public FileIndexer(FileDupConfig fileDupConfig){
		validate(fileDupConfig);
		this.fileDupConfig = fileDupConfig;
		this.folderScannerExector = new ThreadPoolExecutor(
				fileDupConfig.getFolderScannerCorePoolSize(),
				fileDupConfig.getFolderScannerMaxPoolSize(),
				fileDupConfig.getFolderScannerKeepAliveTimeMsecs(),
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(
						fileDupConfig.getFolderScannerQueueSize()));
		folderScannerExector
				.setRejectedExecutionHandler(new FolderScannerRejectionHandler());
		fileQueue = new PriorityBlockingQueue<File>(
				fileDupConfig.getFileQueueSize(), fileSizeComparator);
		fileMetaFeeder = new FileMetaFeeder(this);
	}
	
	private void validate(FileDupConfig fileDupConfig) {
		//TODO: Add proper validation
		if(fileDupConfig == null)
			throw new FileDupException("FileDupConfig cannot be null.");
		
	}

	public FileIndex indexFiles() {
		logger.info("Indexing started..");
		fileMetaFeederTask = new Thread(fileMetaFeeder);
		fileMetaFeederTask.start();
		for (File f : fileDupConfig.getFoldersToScan()) {
			folderScannerExector.execute(new FolderScanner(f, fileDupConfig.getFileNameFilter() ,fileQueue));
		}
		 while (folderScannerExector.getActiveCount() != 0 || fileMetaFeeder.getFileMetaTaskExecutor().getActiveCount() != 0 || fileQueue.size()!=0) {
			logger.debug("folderScannerExector.getActiveCount():"+folderScannerExector.getActiveCount()+" , fileMetaFeeder.getFileMetaTaskExecutor().getActiveCount():"+fileMetaFeeder.getFileMetaTaskExecutor().getActiveCount());
			SleepUtil.sleep(1000);
		}
		folderScannerExector.shutdown();
		try {
			folderScannerExector.awaitTermination(60, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(),e);
		}
		fileMetaFeeder.getFileMetaTaskExecutor().shutdown();
		try {
			fileMetaFeeder.getFileMetaTaskExecutor().awaitTermination(60, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(),e);
		}
		logger.info("Indexing done..");
		return fileIndex;
	}

	public FileIndex getFileIndex() {
		return fileIndex;
	}

	public void setFileIndex(FileIndex fileIndex) {
		this.fileIndex = fileIndex;
	}

	public FileDupConfig getFileDupConfig() {
		return fileDupConfig;
	}

	public void setFileDupConfig(FileDupConfig fileDupConfig) {
		this.fileDupConfig = fileDupConfig;
	}

	public BlockingQueue<File> getFileQueue() {
		return fileQueue;
	}

	@Override
	public void run() {
		indexFiles();
	}

	public List<Set<FileMeta>> findDuplicates() {
		return fileIndex.findDuplicates();
	}
	
}
