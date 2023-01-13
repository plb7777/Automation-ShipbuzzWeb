package com.w2a.base;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class MyDemoTest {
	
	@Test
	public void myDemoTest() {
		System.out.println("inside my demo test method");
	}

	@BeforeMethod
	public void myBeforeMethod() {
		System.out.println("inside my before method");
	}

	@AfterMethod
	public void myAfterMethod() {
		System.out.println("inside my after method");
	}

	@BeforeClass
	public void myBeforeClass() {
		System.out.println("inside my before class method");
	}

	@AfterClass
	public void myAfterClass() {
		System.out.println("inside my after class method");
	}

	@BeforeTest
	public void myBeforeTest() {
		System.out.println("inside my before test method");
	}

	@AfterTest
	public void myAfterTest() {
		System.out.println("inside my after test method");
	}

	@BeforeSuite
	public void myBeforeSuite() {
		System.out.println("inside my before suite method");
	}

	@AfterSuite
	public void myAfterSuite() {
		System.out.println("inside my after suite method");
	}

}
