package com.maaryan.filedup.conf;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import com.maaryan.filedup.commons.FileHasher;
import com.maaryan.filedup.commons.Sha512FileHasher;


public class FileDupConfig {
	
	private int fileQueueSize = 10000;
	
	private int fileMetaTaskCorePoolSize = 100;
	private int fileMetaTaskMaxPoolSize = 500;
	private int fileMetaTaskQueueSize = 10000;
	private long fileMetaTaskKeepAliveTimeMsecs = 60 * 1000;
	
	private int folderScannerCorePoolSize = 10;
	private int folderScannerMaxPoolSize = 20;
	private int folderScannerQueueSize = 20;
	private long folderScannerKeepAliveTimeMsecs = 10 * 60 * 1000;
	
	private FileHasher fileHasher = new Sha512FileHasher();
	private FilenameFilter fileNameFilter;
	
	private boolean relativeMode = false;
	private File baseFolder;
	private List<File> foldersToScan;
	
	public FileDupConfig() {
	}

	public int getFileQueueSize() {
		return fileQueueSize;
	}

	public void setFileQueueSize(int fileQueueSize) {
		this.fileQueueSize = fileQueueSize;
	}

	public int getFileMetaTaskCorePoolSize() {
		return fileMetaTaskCorePoolSize;
	}

	public void setFileMetaTaskCorePoolSize(int fileMetaTaskCorePoolSize) {
		this.fileMetaTaskCorePoolSize = fileMetaTaskCorePoolSize;
	}

	public int getFileMetaTaskMaxPoolSize() {
		return fileMetaTaskMaxPoolSize;
	}

	public void setFileMetaTaskMaxPoolSize(int fileMetaTaskMaxPoolSize) {
		this.fileMetaTaskMaxPoolSize = fileMetaTaskMaxPoolSize;
	}

	public int getFileMetaTaskQueueSize() {
		return fileMetaTaskQueueSize;
	}

	public void setFileMetaTaskQueueSize(int fileMetaTaskQueueSize) {
		this.fileMetaTaskQueueSize = fileMetaTaskQueueSize;
	}

	public long getFileMetaTaskKeepAliveTimeMsecs() {
		return fileMetaTaskKeepAliveTimeMsecs;
	}

	public void setFileMetaTaskKeepAliveTimeMsecs(
			long fileMetaTaskKeepAliveTimeMsecs) {
		this.fileMetaTaskKeepAliveTimeMsecs = fileMetaTaskKeepAliveTimeMsecs;
	}

	public int getFolderScannerCorePoolSize() {
		return folderScannerCorePoolSize;
	}

	public void setFolderScannerCorePoolSize(int folderScannerCorePoolSize) {
		this.folderScannerCorePoolSize = folderScannerCorePoolSize;
	}

	public int getFolderScannerMaxPoolSize() {
		return folderScannerMaxPoolSize;
	}

	public void setFolderScannerMaxPoolSize(int folderScannerMaxPoolSize) {
		this.folderScannerMaxPoolSize = folderScannerMaxPoolSize;
	}

	public int getFolderScannerQueueSize() {
		return folderScannerQueueSize;
	}

	public void setFolderScannerQueueSize(int folderScannerQueueSize) {
		this.folderScannerQueueSize = folderScannerQueueSize;
	}

	public long getFolderScannerKeepAliveTimeMsecs() {
		return folderScannerKeepAliveTimeMsecs;
	}

	public void setFolderScannerKeepAliveTimeMsecs(
			long folderScannerKeepAliveTimeMsecs) {
		this.folderScannerKeepAliveTimeMsecs = folderScannerKeepAliveTimeMsecs;
	}

	public FileHasher getFileHasher() {
		return fileHasher;
	}

	public void setFileHasher(FileHasher fileHasher) {
		this.fileHasher = fileHasher;
	}

	public FilenameFilter getFileNameFilter() {
		return fileNameFilter;
	}

	public void setFileNameFilter(FilenameFilter fileNameFilter) {
		this.fileNameFilter = fileNameFilter;
	}

	public boolean isRelativeMode() {
		return relativeMode;
	}

	public void setRelativeMode(boolean relativeMode) {
		this.relativeMode = relativeMode;
	}

	public File getBaseFolder() {
		return baseFolder;
	}

	public void setBaseFolder(File baseFolder) {
		this.baseFolder = baseFolder;
	}

	public List<File> getFoldersToScan() {
		return foldersToScan;
	}

	public void setFoldersToScan(List<File> foldersToScan) {
		this.foldersToScan = foldersToScan;
	}

}
