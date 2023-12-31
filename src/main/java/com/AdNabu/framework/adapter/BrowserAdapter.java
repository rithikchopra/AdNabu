package com.AdNabu.framework.adapter;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.AdNabu.qa.Constants.Constants;

public class BrowserAdapter {
	private WebDriver driver;
	
	public WebDriver initializeBrowserAdapter(String browser){
		
		DesiredCapabilities caps = null;
		if(browser.equalsIgnoreCase("Chrome")){	
		System.setProperty("webdriver.chrome.driver", Constants.BASEDIR+System.getProperty("file.separator")+"Drivers"+System.getProperty("file.separator")+("ChromeDriver"));
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--enable-extensions");
	    options.addArguments("disable-infobars");
//	    options.addArguments("--headless");
	    options.addArguments("--allow-running-insecure-content");
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	    driver = new ChromeDriver(capabilities);
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	}
		return driver;
	
	}
}
	
