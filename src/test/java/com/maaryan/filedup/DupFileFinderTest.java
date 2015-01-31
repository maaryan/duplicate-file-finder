package com.maaryan.filedup;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class DupFileFinderTest{

	@Test
	public void testIndexFiles() {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		Set<Path> foldersToScan = new HashSet<Path>();
		foldersToScan.add(Paths.get("src/test/resources/test-input"));
		DupFileFinder dupFileFinder = new DupFileFinder(foldersToScan);
		Assert.assertTrue(dupFileFinder.findDuplicates().get(0).size()==6);
	}

}
