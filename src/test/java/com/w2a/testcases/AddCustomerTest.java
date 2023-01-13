package com.w2a.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test (dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void addCustomerTest(Hashtable<String, String> data) {
		if (!data.get("runmode").equalsIgnoreCase("Y")) {
			throw new SkipException("Skipped addCustomerTest as runmode is no");
		}
		
		click("addCustomerButton_XPATH");
		type("firstName_XPATH", data.get("firstname"));
		type("lastName_XPATH", data.get("lastname"));
		type("postCode_XPATH", data.get("postcode"));
		click("addingCustomerButton_XPATH");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
				
		alert.accept();
	}
		
}
