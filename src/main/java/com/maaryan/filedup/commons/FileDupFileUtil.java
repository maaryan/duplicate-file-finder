package com.maaryan.filedup.commons;

import java.io.File;
import java.io.IOException;

public class FileDupFileUtil {
	public static String getCanonicalPath(File file){
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			return null;
		}
	}
	
	public static String getRelativePath(File baseFolder, File file){
		String baseFolderPath = getCanonicalPath(baseFolder);
		String fileCanonicalPath = getCanonicalPath(file);
		if(baseFolderPath == null || fileCanonicalPath == null)
			return null;
		if(fileCanonicalPath.indexOf(baseFolderPath)==0){
			return fileCanonicalPath.substring(baseFolderPath.length());
		}else{
			return null;
		}
	}

}
