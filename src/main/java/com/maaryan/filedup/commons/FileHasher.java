package com.maaryan.filedup.commons;

import java.io.File;
import java.io.IOException;

public abstract class FileHasher {
	
	public static FileHasher getFileHasher(String hashAlgorithm){
		if("md2".equalsIgnoreCase(hashAlgorithm)){
			return new Md2FileHasher();
		}else if("md5".equalsIgnoreCase(hashAlgorithm)){
			return new Md5FileHasher();
		}else if("sha-1".equalsIgnoreCase(hashAlgorithm)){
			return new Sha1FileHasher();
		} else if("sha-256".equalsIgnoreCase(hashAlgorithm)){
			return new Sha256FileHasher();
		} else if("sha-384".equalsIgnoreCase(hashAlgorithm)){
			return new Sha384FileHasher();
		} else if("sha-512".equalsIgnoreCase(hashAlgorithm)){
			return new Sha512FileHasher();
		} else{
			return new Sha512FileHasher();
		}
	}
	
	protected abstract String getHash(File file) throws IOException;
	public String getHashKey(File file) throws IOException{
		return getHash(file)+"rn"+file.length();
	}
}
