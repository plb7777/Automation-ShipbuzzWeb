package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.google.common.io.Files;
import com.w2a.base.TestBase;

public class TestUtil extends TestBase {

	public static String path;
	public static String fileName;
	
	public static void captureScreenshot() {
		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		fileName = new Date().toString().replaceAll(" ", "_").replaceAll(":", "_") + ".png";
		path = System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\";
		File file = new File(path, fileName);
		try {
			Files.copy(f, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider (name="dp")
	public Object[][] getData(Method m) {
		String sheetName = m.getName();
		
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];

		Hashtable<String, String> table = null;
		
		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2			
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				//data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
				data[rowNum - 2][0] = table;
			}
		}		
		return data;
	}
	
	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);
		for (int row = 2; row <= rows; row++) {
			String testCase = excel.getCellData(sheetName, "TCID", row);
			if (testCase.equalsIgnoreCase(testName)) {
				String runmode = excel.getCellData(sheetName, "runmode", row);
				if (runmode.equalsIgnoreCase("Y")) {
					return true;
				}
			}
		}
		return false;
	}
	
}
