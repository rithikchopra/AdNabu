package com.AdNabu.qa.Constants;

import com.AdNabu.qa.util.GeneralUtils;

public class Constants {
	//public static final String PROJECTNAME = "My Diabetes";
	public static final String BASEDIR = System.getProperty("user.dir");
	
//	public static final String RUNMANGERFOLDERPATH = BASEDIR+"//Resource";
//	public static final String DATASHEETFOLDERPATH = BASEDIR+"//Resource";
	
	public static final String DATA_TC_COL_NAME = "TC_Name";
	public static final String DATA_STATUS_COL_NAME = "Status";
	
	
	
	
	public static final String TCSHEET_WORKSHEETNAME = "TestCase";
	public static final String TCSHEET_STEPCNOCOLNAME = "Step_No";
	public static final String TCSHEET_CLASSNAME = "Class_Name";
	public static final String TCSHEET_KEYWORDCOLNAME = "Action";
	public static final String TCSHEET_DATACOLNAME = "Data";
//	public static final String TCSHEET_FOLDER_PATH = BASEDIR+"\\src\\test\\resources";
	
//	public static final String RUNMANGERFILEPATH = BASEDIR+"//Resource//"+PROJECTNAME+".xlsx";
	public static final String RESULTSPATH = BASEDIR+System.getProperty("file.separator")+"Results";
    public static final String DATASHEETPATH = BASEDIR+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"test"+System.getProperty("file.separator")+"resources"+System.getProperty("file.separator");
	public static final String DATASHEETWORKSHEETNAME = "Data";
	
	//public static final String UFTDriverPath = "C:\\NCAL_Automation_Projects\\Epic\\Source_code\\RPM\\Scripts\\TestCase_Driver_Suite";
	public static final String UFTDriverPath = BASEDIR+System.getProperty("file.separator")+"UFT_Driver"+System.getProperty("file.separator")+"DriverSuite";
//	public static String TC_SourceCode_Package_Name = "\\src\\main\\java\\com\\kpmobility\\clinicallibrary\\intake\\pages";
	public static int runMangerCurrentRow = 0;
	public static String currentTCName = null;

	public static boolean gridStarted = false;
	public static boolean gridinitiatedandUp = false;
	
	public static String reportFolderDateAndTime = "TestSummaryReport_"+ GeneralUtils.getCurrentDateAndTime();
	public static String reportPath;
	public static String deviceOS;
	
}
