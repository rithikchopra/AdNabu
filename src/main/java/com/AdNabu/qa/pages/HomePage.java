package com.AdNabu.qa.pages;

import java.awt.Desktop.Action;
import java.awt.RenderingHints.Key;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.AdNabu.qa.base.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.AdNabu.qa.base.TestBase;
import com.AdNabu.qa.util.SeleniumScreenshot;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends BasePage{
	BasePage basePage = new BasePage(driver, extentLogger);
	
	public HomePage(WebDriver driver, ExtentTest extentLogger){
		super(driver,extentLogger);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="((//form[@action='/search']//button)[2]")
	WebElement searchBtn;
	
	@FindBy(xpath="(//form[@action='/search']//input)[2]")
	WebElement searchInput;
	
	@FindBy(xpath="//span[contains(text(),'Next page')]//parent::a")
	WebElement nextPage;
	
	@FindBy(xpath="//span[contains(text(),'Add to cart')]//parent::button")
	WebElement addToCart;
	
	public HomePage searchForText(String value){
		Actions action = new Actions(driver);
		waitForSomeTime(4000);
//		click(searchBtn, "Search");
//		waitForSomeTime(2000);
		setValue(searchInput, value, "Search");
		waitForSomeTime(2000);
		action.sendKeys(Keys.ENTER).build().perform();
		waitForSomeTime(2000);
		return this;
	}
	
	public HomePage verifySearchKeywordInProductsTitle(String value){
		if (value.equals(" ")) {
			if (driver.findElement(By.xpath("//*[contains(text(),'Search field cannot be empty')]")).isDisplayed())
            	basePage.extentLogger.log(LogStatus.PASS,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "Empty search successfully doesnt display any results")));
            else
            	basePage.extentLogger.log(LogStatus.FAIL,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "Empty search failed to show error message")));
		}
		else {
			List<WebElement> elements = driver.findElements(By.xpath("//li [@class='list-view-item']//span[@class='visually-hidden']"));
			for (WebElement element : elements) {
	            String text = element.getText();
	            if (text.contains(value))
	            	basePage.extentLogger.log(LogStatus.PASS,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, text + " contains the expected search keyword")));
	            else
	            	basePage.extentLogger.log(LogStatus.FAIL,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, text + " does not contain the expected search keyword")));
	        }
		}
		return this;
	}
	
	public HomePage verifyNoSearchResultsForRandomString(String value){
		waitForSomeTime(3000);
		if (driver.findElement(By.xpath("//*[contains(text(),'Please try a different search term or go back to the')]")).isDisplayed())
        	basePage.extentLogger.log(LogStatus.PASS,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "0 Results message displayed successfully")));
        else
        	basePage.extentLogger.log(LogStatus.FAIL,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "0 Results message failed to display")));
		return this;
	}
	
	public HomePage navigateToNextSearchPage(){
		click(nextPage, "Next Page");
		waitForSomeTime(2000);
		return this;
	}
	
	public HomePage navigateToProductAndAddToCart(String productName) {
		waitForSomeTime(2000);
		click(driver.findElement(By.xpath("//span[contains(text(),'"+ productName +"')]//parent::a")), productName);
		waitForSomeTime(2000);
		click(addToCart, "Add to cart");
		waitForSomeTime(2000);
		return this;
	}
}