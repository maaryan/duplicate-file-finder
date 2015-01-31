package com.maaryan.filedup;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import com.maaryan.fhi.FileHashIndexer;
import com.maaryan.fhi.FileHashIndexerFullPath;
import com.maaryan.fhi.conf.FileHashIndexerConfig;
import com.maaryan.fhi.vo.FileHashIndex;
import com.maaryan.fhi.vo.FileMeta;

public class DupFileFinder implements Runnable{
	private List<Set<FileMeta>> duplicateFiles;
	private FileHashIndexer fileHashIndexer;
	public DupFileFinder(FileHashIndexerConfig indexerConfig,Set<Path> foldersToScan) {
		fileHashIndexer = new FileHashIndexerFullPath(foldersToScan,indexerConfig);
	}
	public DupFileFinder(Set<Path> foldersToScan) {
		fileHashIndexer = new FileHashIndexerFullPath(foldersToScan);
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
			FileHashIndex fileHashIndex = fileHashIndexer.indexFiles();
			duplicateFiles = fileHashIndex.findDuplicates();
		}
		return duplicateFiles;
	}
}
