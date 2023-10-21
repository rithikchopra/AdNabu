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

public class verifySearchPaginationResults extends TestBase {

	private WebDriver driver;
	HomePage homepage;

	String TCName = this.getClass().getSimpleName();

	String TCDesc = "TC004_Automating Pagination Search Results";

	public verifySearchPaginationResults() {
		super();
	}

	@Test(description = "TC004_Automating Pagination Search Results", priority = 1)
	public void LoginPageTest() throws AWTException {
		extentLogger = reports.startTest(TCName);
		driver = initBrowser("Chrome");

		driver.get(prop.getProperty("url"));

		new HomePage(driver, extentLogger)
		.searchForText("a")
		.navigateToNextSearchPage()
		.verifySearchKeywordInProductsTitle("a");
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