package com.Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

/*
 * @this is Framework base class.
 * @this class is responsible to launch the web browser based on the properties file
 *  
 */


public class Framework {
	
	public static Properties prop;
	public static WebDriver driver = null;
	public static Logger logger = null;
	public static WebDriverWait wait;
	
	@Before
	public void Initialize( Scenario scenario) throws Exception{
		
		if( ! LoadConfig()){
			throw new Exception("Unable to load Configaration from config file");
		}
		getBrowser();
		logger = new Logger(driver);
		logger.Initialize(" AUTOMATION RESULT ",  scenario.getName());	
		wait = new WebDriverWait(driver, Integer.parseInt(prop.getProperty("selenium.wait")));
	}
	
	public void getBrowser(){
		
		String nameBrowser = prop.getProperty("execution.browser");
		if(nameBrowser.toUpperCase().equalsIgnoreCase("IE")){
			String strIEDriverPath=System.getProperty("user.dir")+"\\Resource\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", strIEDriverPath);
			driver = new InternetExplorerDriver();		
			
		}else if(nameBrowser.toUpperCase().equalsIgnoreCase("CHROME")){
			String strChromeDriverPath=System.getProperty("user.dir")+"\\Resource\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", strChromeDriverPath);
			driver = new ChromeDriver();
		}else if(nameBrowser.toUpperCase().equalsIgnoreCase("FIREFOX")){
			File strFirefoxbinaryPath= new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary ffbinary= new FirefoxBinary(strFirefoxbinaryPath);
			File strFirefoxProfilePath= new File("C:\\Users\\soura\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\pty7r1i6.default");
			FirefoxProfile ffProfile= new FirefoxProfile(strFirefoxProfilePath);
			driver = new FirefoxDriver(ffbinary,ffProfile);
		}else if(nameBrowser.toUpperCase().equalsIgnoreCase("EDGE")){
			String strEDGEdriverpath = "C:\\Users\\soura\\workspace\\Template\\Resource\\MicrosoftWebDriver.exe";
			System.setProperty("webdriver.edge.driver", strEDGEdriverpath);
			driver = new EdgeDriver();
		}else{
			try {
				throw new Exception("Browser name is not match as per mentioned in config.properties");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public boolean LoadConfig(){
			
			prop = new Properties();
			String strConfigPath= System.getProperty("user.dir")+"\\Config\\config.properties";
			System.out.println("Picked up Configuration path as " + strConfigPath);
			
			try {
				
				FileInputStream oFis= new FileInputStream(strConfigPath);
				prop.load(oFis);
				
			} catch (IOException e) {
				
				System.out.println(e.getMessage());
				return false;
			}
			return true;
		}
	
	}
