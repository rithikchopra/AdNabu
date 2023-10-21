package com.AdNabu.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.AdNabu.qa.Constants.Constants;

public class SeleniumScreenshot {
	WebDriver driver;
	
	public static  void saveScreenshot(WebDriver driver){
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			File destFile = new File("datetime.png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String saveSeleniumScreenshots(WebDriver driver, String screenshotName){
		String destination = "";
		String ssName="";
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			ssName = screenshotName+"_"+ GeneralUtils.getCurrentDateAndTime()+".png";
			destination =GeneralUtils.createResultFolderForCurrentDay()+System.getProperty("file.separator")+Constants.reportFolderDateAndTime+System.getProperty("file.separator")+"Screenshots"+System.getProperty("file.separator")+ssName;
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			//Reporter.log("<a href='" + finalDestination.getAbsolutePath() + "'> <img src='" + finalDestination.getAbsolutePath() + "' height='100' width='100'/> </a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//return destination;
		return Constants.reportFolderDateAndTime+System.getProperty("file.separator")+"Screenshots"+System.getProperty("file.separator")+ssName;
		



	}
	
}
