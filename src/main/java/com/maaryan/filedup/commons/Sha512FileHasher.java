package com.maaryan.filedup.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

public class Sha512FileHasher extends FileHasher {

	@Override
	public String getHash(File file) throws IOException {
		try(FileInputStream fis = new FileInputStream(file)){
			return DigestUtils.sha512Hex(fis);
		}
	}
}
