package com.maaryan.filedup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class DupFileFinderTest{

	@Test
	public void testIndexFiles() {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		List<File> foldersToScan = new ArrayList<File>();
		foldersToScan.add(new File("src/test/resources/test-input"));
		DupFileFinder dupFileFinder = new DupFileFinder(foldersToScan);
		Assert.assertTrue(dupFileFinder.findDuplicates().get(0).size()==6);
	}

}
