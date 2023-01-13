package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream in;
	public static WebDriverWait wait;
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	
	String path = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx";
	public ExcelReader excel = new ExcelReader(path);
	
	@BeforeSuite
	public void setUp() {
		if (driver == null) {
			try {
				in = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
				config.load(in);
				in = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
				or.load(in);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
			
			driver.get(config.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}
	}
	
	public void click(String locator) {
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).click();
		}		
		test.log(LogStatus.INFO, "Clicked on " + locator);
	}
	
	public void type(String locator, String value) {
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typed on " + locator + " and entered the value " + value);
	}
	
	static WebElement dropdown;
	
	public void select(String locator, String value) {
		if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(or.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, " Selecting from dropdown " + locator + " value as " + value);
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public static void verifyEquals(String expected, String actual) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			TestUtil.captureScreenshot();
			// report ng
			Reporter.log("<br>" + "Verification failure " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.fileName + "><img src=" 
							+ TestUtil.fileName + " height=200 width=200></img></a>");
			
			// extent report
			test.log(LogStatus.FAIL, "Verification failed " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.fileName));
			
		}
	}
	
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	
}
