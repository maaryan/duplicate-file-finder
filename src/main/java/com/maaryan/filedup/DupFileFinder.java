package com.maaryan.filedup;

import java.io.File;
import java.util.List;
import java.util.Set;

import com.maaryan.filedup.conf.DefaultConfigFactory;
import com.maaryan.filedup.conf.FileDupConfig;
import com.maaryan.filedup.vo.FileMeta;

public class DupFileFinder implements Runnable{
	private List<Set<FileMeta>> duplicateFiles;
	private FileDupConfig fileDupConfig;
	private FileIndexer fileIndexer;
	public DupFileFinder(FileDupConfig fileDupConfig) {
		this.fileDupConfig = fileDupConfig;
		this.fileIndexer = new FileIndexer(fileDupConfig);
	}
	public DupFileFinder(List<File> foldersToScan) {
		fileDupConfig = DefaultConfigFactory.getFileDupCanonicalModeConfig();
		fileDupConfig.setFoldersToScan(foldersToScan);
		this.fileIndexer = new FileIndexer(fileDupConfig);
	}
	
	@Override
	public void run() {
		this.findDuplicates();
	}
	public List<Set<FileMeta>> findDuplicates(){
		return findDuplicates(true);
	}
	public List<Set<FileMeta>> findDuplicates(boolean indexFiles){
		if(indexFiles){
			fileIndexer.indexFiles();
			System.out.println(fileIndexer.getFileIndex());
		}
		duplicateFiles = fileIndexer.findDuplicates();
		return duplicateFiles;
	}
}
