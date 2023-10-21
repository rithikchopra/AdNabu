package com.AdNabu.qa.util;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.AdNabu.qa.Constants.Constants;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	
	public static ExtentReports getInstance(){
		if(extent==null){
			
			Constants.reportPath = GeneralUtils.createResultFolderForCurrentDay()+System.getProperty("file.separator")+Constants.reportFolderDateAndTime+".html";
			extent = new ExtentReports(Constants.reportPath);
			try {
				extent.addSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
				extent.addSystemInfo("User Name", System.getProperty("user.name"));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			extent.loadConfig(ExtentManager.class.getClassLoader().getResource("extent-config.xml"));
		}
		return extent;
	}
	
	
	
}
