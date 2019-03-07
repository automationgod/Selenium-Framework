package com.Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/*
 * @this is logger class
 * @this is responsible to generate test report in Logs folder
 */

public class Logger {
	public static String strLogPath;
	public static boolean isInitialized = false;
	public static List<String> logList = null;
	public static boolean isTestPassOrFail = true;
	private WebDriver driver;
	
	public Logger(WebDriver driver){
		this.driver = driver;
	}
	
	
	public void Initialize(String strFeatureName, String strScenarioName) throws IOException{
			
			if(!isInitialized){
				DateFormat format= new SimpleDateFormat("dd_HH_mm_ss");
				Date dateobj = new Date();
				String strFileName= "TestResult"+"_"+ format.format(dateobj)+".html";
				strLogPath=System.getProperty("user.dir")+"\\Logs\\"+strFileName;
				File file = new File( System.getProperty("user.dir")+"\\Resource\\html_template.txt" );
				FileInputStream fis = new FileInputStream(file);
				byte[] data = new byte[(int) file.length()];
				fis.read(data);
				fis.close();
				String sTemplateText = new String(data);
				FileWriter logger= new FileWriter(strLogPath , false);
				sTemplateText = sTemplateText.replace("TEMPLATE_SCENARIO_NAME", strScenarioName);
				sTemplateText = sTemplateText.replace("TEMPLATE_FEATURE_NAME", strFeatureName);
				logger.write(sTemplateText);
				logger.close();
				logList = new ArrayList<String>();
				isInitialized = true;
			}
			else{
				FileWriter logger= new FileWriter(strLogPath, true);
				String completeLog = "<li class=\"level suite failed open\"><span>Scenario: TEMPLATE_SCENARIO_NAME   <!--RESULT_FINAL-->  </span><ul>";
				completeLog = completeLog.replace("TEMPLATE_SCENARIO_NAME", strScenarioName);
				logger.write(completeLog);
				logger.close();
			}
	}
	public void WriteLog(String strLogContent, boolean isPassorFail, boolean isSnapshot) throws Exception{
		FileWriter logger = null;
		try {
			logger = new FileWriter(strLogPath, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(isSnapshot){
			strLogContent = strLogContent + CaptureScreenShot();
		}
		if(isPassorFail){
			
			String strTempLog = ("<font color='green'> [PASS] STEP_RESULT </font><br>").replace("STEP_RESULT", strLogContent);
			try {
				logger.write(strTempLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("[PASS]: " +strLogContent);
		}else{
			String strTempLog = ("<font color='red'> [FAIL] STEP_RESULT </font><br>").replace("STEP_RESULT", strLogContent);
			try {
				logger.write(strTempLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("[FAIL]: " +strLogContent);
		}
		try {
			logger.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( !isPassorFail){
			isTestPassOrFail = false;
			throw new Exception("Scenario is failed. Please check the logs");
		}
	}

	public String CaptureScreenShot() {
		
		DateFormat format = new SimpleDateFormat("dd_MMM_YY");
		Date dateobj = new Date();
		String strDirName = format.format(dateobj);
		File file = new File(System.getProperty("user.dir")+"\\Log\\"+ strDirName);
		if( ! file.exists()){
			file.mkdir();
		}
		format = new SimpleDateFormat("HH_mm_ss");
		dateobj = new Date();
		String strFileName = format.format(dateobj)+".png";
		String strCompleteFileName = System.getProperty("user.dir")+"\\Logs\\"+ strDirName +"\\"+ strFileName;
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			
		FileUtils.copyFile(scrFile, new File(strCompleteFileName));
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			strCompleteFileName = "";
		}
		
		String strWebLink = " <a href= \""+strCompleteFileName +"\" target=\"_blank\"> Click Here For Snapshot </a>";
		return strWebLink;
	}
}
