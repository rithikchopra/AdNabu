package com.AdNabu.qa.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.AdNabu.qa.util.SeleniumScreenshot;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class BasePage {

	public WebDriver driver;
	public ExtentTest extentLogger;

	public BasePage(WebDriver driver, ExtentTest extentLogger) {
		this.driver = driver;
		this.extentLogger = extentLogger;
	}
	
	public void click(WebElement webElement, String fieldName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
//			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			MoveToElement(webElement);	
			webElement.click();
			extentLogger.log(LogStatus.PASS,
					extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, fieldName)));
			extentLogger.log(LogStatus.INFO, "Clicked on " + fieldName);
		} catch (TimeoutException e) {
			reportFailure("Timeout Exception in " + Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " Method while working on " + fieldName + " " + e.getMessage());
		} catch (Exception e) {
			reportFailure("Exception in " + Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " Method while working on " + fieldName + " " + e.getMessage());
		}
	}
	
	public void MoveToElement(WebElement webElement) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(webElement).build().perform();
		} catch (Exception e) {
			extentLogger.log(LogStatus.INFO,
					extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "Element not found")));

		}
	}

	public void reportFailure(String message) {
		extentLogger.log(LogStatus.FAIL, message);
		extentLogger.log(LogStatus.FAIL,
				extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "exception")));
	}
	
	public void setValue(WebElement webElement, String value, String fieldName) {
		try {
			highlightElement(webElement);
//			WebDriverWait wait = new WebDriverWait(driver, 20);
//			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			webElement.clear();
			waitForSomeTime(1000);
		//	MoveToElement(webElement);
			webElement.sendKeys(value);
			extentLogger.log(LogStatus.PASS,
			extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, fieldName)));
			Actions action = new Actions(driver);
			action.sendKeys(Keys.TAB).build().perform();
			extentLogger.log(LogStatus.INFO, "Value " + value + " is set in the text field " + fieldName);
		} catch (TimeoutException e) {
			
				extentLogger.log(LogStatus.INFO,"Timeout Exception in " + Thread.currentThread().getStackTrace()[1].getMethodName()
						+ " Method while working on " + fieldName + " " + e.getMessage());

		} catch (Exception e) {
			
				extentLogger.log(LogStatus.INFO,"Exception in " + Thread.currentThread().getStackTrace()[1].getMethodName()
						+ " Method while working on " + fieldName + " " + e.getMessage());
			

		}
	}
	
	public void highlightElement(WebElement element) {
			// System.out.println("Highlighting element");
			JavascriptExecutor js = ((JavascriptExecutor) driver);

			for (int i = 0; i < 6; i++) {
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: red; border:3px solid red;");
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
			}
			js = null;
		
	}
	
	public void waitForSomeTime(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			reportFailure("InterruptedException Exception in " + BasePage.class.getDeclaredMethods()[0].getName()
					+ e.getMessage());
		}
	}
	
	public boolean verifyTitle(String title) {
		boolean bReturn = false;
		try {
			if (driver.getTitle().equalsIgnoreCase(title)) {
				extentLogger.log(LogStatus.PASS, "Title Matched");
				bReturn = true;
				extentLogger.log(LogStatus.PASS,
						extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, title)));
			} else
				extentLogger.log(LogStatus.FAIL, "Title Mismatch");
			extentLogger.log(LogStatus.FAIL,
					extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, title)));

		} catch (Exception e) {
			reportFailure("Exception in " + Thread.currentThread().getStackTrace()[1].getMethodName()
					+ " Method while working on " + title + " " + e.getMessage());
		}

		return bReturn;
	}
	
}
