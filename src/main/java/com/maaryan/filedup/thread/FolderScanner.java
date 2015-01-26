package com.maaryan.filedup.thread;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FolderScanner implements Runnable{
	protected Logger logger = LoggerFactory.getLogger(FolderScanner.class);
	protected File folder;
	protected FilenameFilter fileNameFilter;
	protected final BlockingQueue<File> fileQueue;
	public FolderScanner(File folder, FilenameFilter fileNameFilter, BlockingQueue<File> fileQueue){
		this.folder = folder;
		this.fileQueue = fileQueue;
	}
	@Override
	public void run() {
		logger.info("FolderScanner Started");
		scanFolder(folder);
		logger.info("FolderScanner Done");
	}
	
	private void scanFolder(File folder){
		logger.info("Scanning folder "+folder.getAbsolutePath());
		if(!folder.exists()){
			logger.warn("Folder not found "+folder.getAbsolutePath());
		}
		List<File> folders = new ArrayList<>();
		for(File f: folder.listFiles(fileNameFilter)){
			if(f.isDirectory()){
				folders.add(f);
			}else{
				logger.debug("Producing file to fileQueue: "+f.getAbsolutePath());
				fileQueue.add(f);
			}
		}
		for(File f: folders){
			scanFolder(f);
		}
		logger.info("Scanning done for folder "+folder.getAbsolutePath());
	}
}
