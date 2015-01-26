package com.maaryan.filedup.vo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class FileIndex {
	private String hashAlgorithm;
	private String baseFolderPath;
	private Map<String, Set<FileMeta>> fileIndexMap = new HashMap<>();

	public String getHashAlgorithm() {
		return hashAlgorithm;
	}

	public void setHashAlgorithm(String hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}

	public String getBaseFolderPath() {
		return baseFolderPath;
	}

	public void setBaseFolderPath(String baseFolderPath) {
		this.baseFolderPath = baseFolderPath;
	}

	public synchronized boolean addFile(FileMeta fileMeta) {
		if (!fileMeta.isAcceptable()) {
			return false;
		}
		Set<FileMeta> fileSet = fileIndexMap.get(fileMeta.getFileHash());
		if (fileSet == null) {
			fileSet = new TreeSet<FileMeta>(new Comparator<FileMeta>() {
				public int compare(FileMeta o1, FileMeta o2) {
					if (o1 == null && o2 == null)
						return 0;
					if (o1 == null || o2 == null)
						return -1;
					return o1.getFilePath().compareTo(o2.getFilePath());
				}
			});
			fileIndexMap.put(fileMeta.getFileHash(), fileSet);
		}
		return fileSet.add(fileMeta);
	}

	@Override
	public String toString() {
		return fileIndexMap.toString();
	}

	public List<Set<FileMeta>> findDuplicates() {
		List<Set<FileMeta>> duplicateFilesList = new ArrayList<Set<FileMeta>>();
		for (Map.Entry<String, Set<FileMeta>> entry : fileIndexMap.entrySet()) {
			if (entry.getValue().size() > 1) {
				duplicateFilesList.add(entry.getValue());
			}
		}
		return duplicateFilesList;
	}
}
