package com.maaryan.filedup.thread;

import com.maaryan.filedup.FileDupException;

public class SleepUtil {
	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new FileDupException();
		}
	}
}
