package com.maaryan.filedup.thread;

import java.io.File;

import org.slf4j.Logger;

import com.maaryan.filedup.FileIndexer;
import com.maaryan.filedup.vo.FileMeta;

public abstract class FileMetaTask implements Runnable {
	protected Logger logger;
	protected FileIndexer fileIndexer;
	protected File file;

	public FileMetaTask(FileIndexer fileIndexer, File file) {
		this.fileIndexer = fileIndexer;
		this.file = file;
	}

	@Override
	public void run() {
		logger.debug("Fetching fileMetaTask for file: "
				+ file.getAbsolutePath());
		try {
			FileMeta fileMeta = new FileMeta();
			fileMeta.setFilePath(getPath(file));
			fileMeta.setFileSize(file.length());
			logger.debug("Fetching hash key for file: "
					+ file.getAbsolutePath());
			fileMeta.setFileHash(fileIndexer.getFileDupConfig().getFileHasher()
					.getHashKey(file));
			fileIndexer.getFileIndex().addFile(fileMeta);
			logger.debug("Added file to fileIndex: " + file.getAbsolutePath());
		} catch (Exception e) {
			logger.error(
					"Error while getting meta for file:"
							+ file.getAbsolutePath(), e);
		}
	}

	protected abstract String getPath(File file);
}
