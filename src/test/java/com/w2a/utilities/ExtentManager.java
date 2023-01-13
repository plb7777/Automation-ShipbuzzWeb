package com.w2a.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static ExtentReports extent;
	
	private static String fileWithPath = System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\extent.html";
	private static String configFile = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\extentconfig\\ReportsConfig.xml";
	
	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports(fileWithPath, true, DisplayOrder.OLDEST_FIRST);
			
			// load config file
			File f = new File(configFile);
			if (f.exists()) {
				extent.loadConfig(f);
			}
		}
		return extent;
	}
	
}
