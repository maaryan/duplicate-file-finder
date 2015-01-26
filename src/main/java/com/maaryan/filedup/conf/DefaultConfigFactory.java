package com.maaryan.filedup.conf;

public class DefaultConfigFactory {
	
	public static FileDupConfig getFileDupCanonicalModeConfig(){
		FileDupConfig fileIndexerConfig = new FileDupConfig();
		//TODO: Configure based on system resources
		return fileIndexerConfig;
	}
	
	public static FileDupConfig getFileDupRelativeModeConfig(){
		FileDupConfig fileIndexerConfig = new FileDupConfig();
		//TODO: Configure based on system resources
		return fileIndexerConfig;
	}
	
}
