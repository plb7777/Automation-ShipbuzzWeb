package com.w2a.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener {
	
	public void onTestStart(ITestResult result) {
		test = report.startTest(result.getName());
	}
	
	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " is passed");
		report.endTest(test);
		report.flush();
	}
	
	public void onTestFailure(ITestResult result) {
		// generate html code
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		TestUtil.captureScreenshot();
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " is failed with " + result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.fileName));
		
		// adding report ng and screenshot with link and image
		Reporter.log("Click to see sreenshot");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.fileName + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.fileName + "><img src=" 
						+ TestUtil.fileName + " height=200 width=200></img></a>");
		report.endTest(test);
		report.flush();
	}
	
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "Skipped the test " + result.getName().toUpperCase());
		report.endTest(test);
		report.flush();
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}
	
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}
	
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
