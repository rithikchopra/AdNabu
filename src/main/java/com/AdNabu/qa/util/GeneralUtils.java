package com.AdNabu.qa.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.AdNabu.qa.Constants.Constants;

public class GeneralUtils {
	
	public static String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		return (String)dateFormat.format(new Date());
	}
	
	
	public static String getCurrentDateAndTime(){
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		String strDate =  (String)dateFormat.format(new Date());
		return strDate.replace("-", "_").replace(":", "_");
	}
	
	
	
	public static void createFolderInsideDirectory(String path,String folderName) {
		try {
			File dir = new File(path+System.getProperty("file.separator")+folderName);
			if(!dir.exists() && !dir.isDirectory())
				dir.mkdir();
		} catch (Exception e) {
			System.out.println("Somthing Wrong in Creating Folder...!");
			e.printStackTrace();
		}
		
	}
	
	
	public static String createResultFolderForCurrentDay(){
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		createFolderInsideDirectory(Constants.RESULTSPATH,dateFormat.format(new Date()).toString());
		createFolderInsideDirectory(Constants.RESULTSPATH+System.getProperty("file.separator")+dateFormat.format(new Date()).toString(),Constants.reportFolderDateAndTime);
		createFolderInsideDirectory(Constants.RESULTSPATH+System.getProperty("file.separator")+dateFormat.format(new Date()).toString()+System.getProperty("file.separator")+Constants.reportFolderDateAndTime,"Screenshots");
		return Constants.RESULTSPATH+System.getProperty("file.separator")+dateFormat.format(new Date()).toString();
	}
	
	public static String getRandomAlphaNumericString(){
		String alpha_numeric_string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int cnt =10;
		StringBuilder builder = new StringBuilder();
		while (cnt-- != 0) {
			int character = (int)(Math.random()*alpha_numeric_string.length());
			builder.append(alpha_numeric_string.charAt(character));
		}
		return builder.toString();
	}
}
