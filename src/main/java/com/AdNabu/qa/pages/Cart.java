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

public class Cart extends BasePage{
	BasePage basePage = new BasePage(driver, extentLogger);
	
	public Cart(WebDriver driver, ExtentTest extentLogger){
		super(driver,extentLogger);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//div[@id='CartCount']//span)[1]")
	WebElement cartCount;
	
	public Cart verifyCartCount(String value){
		String count = cartCount.getText();
		if (count.equals(value))
        	basePage.extentLogger.log(LogStatus.PASS,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "Cart count has increased and has successfully changed")));
        else
        	basePage.extentLogger.log(LogStatus.FAIL,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "Cart count has not increased")));
	
		return this;
	}
	
	public Cart verifyProductAddedDisplayedInCartPage(String value){
		waitForSomeTime(3000);
		String text = driver.findElement(By.xpath("//a[@class='cart__product-title']")).getText();
		if (text.contains(value))
        	basePage.extentLogger.log(LogStatus.PASS,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "Product displayed in cart successfully")));
        else
        	basePage.extentLogger.log(LogStatus.FAIL,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "Product failed to display in cart")));
	
		return this;
	}
	
	public Cart removeItemFromCart(String value){
		waitForSomeTime(3000);
		
		click(driver.findElement(By.xpath("//a[@class='cart__product-title']//parent::div//following-sibling::p//a")), "Remove");
		waitForSomeTime(2000);
		
		if (driver.findElement(By.xpath("//*[contains(text(),'Your cart is currently empty.')]")).isDisplayed())
        	basePage.extentLogger.log(LogStatus.PASS,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "Cart Empty page successfully displayed")));
        else
        	basePage.extentLogger.log(LogStatus.FAIL,extentLogger.addScreenCapture(SeleniumScreenshot.saveSeleniumScreenshots(driver, "Cart Empty page not displayed")));
	
		return this;
	}
}