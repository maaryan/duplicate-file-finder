package com.maaryan.filedup.thread;

import java.io.File;

import org.slf4j.LoggerFactory;

import com.maaryan.filedup.FileIndexer;
import com.maaryan.filedup.commons.FileDupFileUtil;

public class FileMetaCanonicalTask extends FileMetaTask {
	{
		this.logger = LoggerFactory.getLogger(FileMetaCanonicalTask.class);
	}

	public FileMetaCanonicalTask(FileIndexer fileIndexer, File file) {
		super(fileIndexer, file);
	}

	protected String getPath(File file) {
		return FileDupFileUtil.getCanonicalPath(file);
	}
}
