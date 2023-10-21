package com.crm.qa.testcases;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.myDiabetes.qa.base.TestBase;
import com.myDiabetes.qa.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends TestBase {

	private WebDriver driver;
	LoginPage loginPage;

	String TCName = this.getClass().getSimpleName();

	String TCDesc = "TC001_Validate creating new patient details";

	public LoginPageTest() {
		super();
	}

	@Test(description = "TC001_Validate creating new patient details", priority = 1)
	public void LoginPageTest() throws AWTException {
		extentLogger = reports.startTest(TCName);
		driver = initBrowser("Chrome");
//		initialization();
		extentLogger.log(LogStatus.FAIL, "");

		driver.get(prop.getProperty("url"));
//		loginPage = new LoginPage();

		new LoginPage(driver, extentLogger)
		.validateLoginPageTitle()
		.login(prop.getProperty("username"), prop.getProperty("password"))
//		.closePopups()
		.addNewPatient();

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