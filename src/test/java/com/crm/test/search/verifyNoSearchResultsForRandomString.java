package com.crm.test.search;

import org.testng.annotations.Test;
import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.AdNabu.qa.base.TestBase;
import com.AdNabu.qa.pages.HomePage;
import com.relevantcodes.extentreports.LogStatus;

public class verifyNoSearchResultsForRandomString extends TestBase {

	private WebDriver driver;
	HomePage homepage;

	String TCName = this.getClass().getSimpleName();

	String TCDesc = "TC003_Automating No results random string Search";

	public verifyNoSearchResultsForRandomString() {
		super();
	}

	@Test(description = "TC003_Automating No results random string Search", priority = 1)
	public void LoginPageTest() throws AWTException {
		extentLogger = reports.startTest(TCName);
		driver = initBrowser("Chrome");

		driver.get(prop.getProperty("url"));

		new HomePage(driver, extentLogger)
		.searchForText("fcdsfs")
		.verifyNoSearchResultsForRandomString("fcdsfs");
		driver.quit();
	}

	@AfterMethod
	public void clearAll(ITestResult result) {
		if (result.equals("FAIL")) {
			extentLogger.log(LogStatus.FAIL, "");

		}
		reports.endTest(extentLogger);
		if (driver != null) {
			driver.quit();
		}

	}

}