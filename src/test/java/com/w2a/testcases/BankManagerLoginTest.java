package com.w2a.testcases;

import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class BankManagerLoginTest extends TestBase {

	@Test
	public void bankManagerLoginTest() {		
		click("bankManagerLoginButton_XPATH");
		//Assert.assertTrue(isElementPresent(By.xpath(or.getProperty("addCustomerButton_XPATH"))));
		//Assert.fail("Bank manager login failed");
	}
	
}
