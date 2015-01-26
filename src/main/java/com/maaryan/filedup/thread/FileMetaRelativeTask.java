package com.maaryan.filedup.thread;

import java.io.File;

import org.slf4j.LoggerFactory;

import com.maaryan.filedup.FileIndexer;
import com.maaryan.filedup.commons.FileDupFileUtil;

public class FileMetaRelativeTask extends FileMetaTask {
	{
		this.logger = LoggerFactory.getLogger(FileMetaRelativeTask.class);
	}

	public FileMetaRelativeTask(FileIndexer fileIndexer, File file) {
		super(fileIndexer, file);
	}

	protected String getPath(File file) {
		return FileDupFileUtil.getRelativePath(fileIndexer.getFileDupConfig().getBaseFolder(),
				file);
	}

}
