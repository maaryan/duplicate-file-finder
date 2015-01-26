package com.maaryan.filedup.thread;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maaryan.filedup.FileIndexer;

public class FileMetaFeeder implements Runnable{
	private Logger logger = LoggerFactory.getLogger(FileMetaFeeder.class);
	private final ThreadPoolExecutor fileMetaTaskExecutor;

	private final FileIndexer fileIndexer;
	public FileMetaFeeder(FileIndexer fileIndexer) {
		this.fileIndexer = fileIndexer;
		fileMetaTaskExecutor = new ThreadPoolExecutor(100,
	            1000, 5000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(100000));
		fileMetaTaskExecutor
		.setRejectedExecutionHandler(new FileMetaTaskRejectionHandler());
	}
	
	@Override
	public void run() {
		logger.info("FileMetaFeeder started..");
		while(true){
			try {
				if(fileIndexer.getFileQueue().isEmpty()){
					logger.info("FileQueue empty..");
					SleepUtil.sleep(10);
				}
				File file = fileIndexer.getFileQueue().take();
				logger.debug("Consuming file on queue: "+file.getAbsolutePath());
				if(fileIndexer.getFileDupConfig().isRelativeMode()){
					fileMetaTaskExecutor.execute(new FileMetaRelativeTask(fileIndexer,file));
				}else{
					fileMetaTaskExecutor.execute(new FileMetaCanonicalTask(fileIndexer,file));
				}
			} catch (InterruptedException e) {
				logger.warn(e.getMessage(),e);
			}
		}
	}

	public ThreadPoolExecutor getFileMetaTaskExecutor() {
		return fileMetaTaskExecutor;
	}
}
