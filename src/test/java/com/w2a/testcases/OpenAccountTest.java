package com.w2a.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void openAccountTest(Hashtable<String, String> data) {

		// run mode
		if (!(TestUtil.isTestRunnable("openAccountTest", excel))) {
			throw new SkipException("Skipping " + "openAccountTest".toUpperCase() + " as run mode is false");
		}

		click("openAccountButton_XPATH");
		select("customerName_XPATH", data.get("customer"));
		select("currency_XPATH", data.get("currency"));
		click("processButton_XPATH");

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

}
