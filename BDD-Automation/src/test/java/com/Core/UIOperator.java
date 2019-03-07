package com.Core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*
 * @this is basically WebDriver common action methods
 * @this accelerator helps to develop quick automation test development
 */

public class UIOperator {

private WebDriver driver;
	
	public UIOperator(WebDriver driver){
		this.driver = driver;
	}
	
	public  boolean JClickElement(By locator) throws Exception {
        try {            

            WebElement element = Framework.driver.findElement(locator);
            Framework.wait.until(ExpectedConditions.elementToBeClickable(element));
            JavascriptExecutor executor = (JavascriptExecutor)Framework.driver;
            executor.executeScript("arguments[0].click();", element);

            Framework.logger.WriteLog("Clicked on Element : " + locator, true, false);            
            return  true;
        }catch (Exception e) {
            e.printStackTrace();
            Framework.logger.WriteLog(   "Unable to Find Element : " +   locator , false,true );
            return false;
        }
    }
	
	public  boolean ClickElement(By locator) throws Exception {
        try {
            WebElement element = Framework.driver.findElement(locator);
            Framework.wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            Framework.logger.WriteLog("Clicked on Element : " + locator, true, false);            
            return  true;
        }catch (Exception e) {
            e.printStackTrace();
            Framework.logger.WriteLog(   "Unable to Find Element : " +   locator , false,true );
            return false;
        }
    }
	
	public  boolean EnterText(String strValue, By locator) throws Exception {
        try {
        	Framework.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            WebElement element = Framework.driver.findElement(locator);
            element.clear();
            element.sendKeys(strValue);
            Framework.logger.WriteLog(   "Entered value "  +  strValue + "  in Text box : " +   locator ,  true , false);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            Framework.logger.WriteLog(   "Unable to Find Element : " +   locator , false,true );
            return false;
        }
    }
	
	public  boolean VerifyControlExist(By locator) throws Exception {
    	boolean isControlExists = false;
        try {
        	
        	Framework.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            WebElement element = Framework.driver.findElement(locator);
	            if (   element.isDisplayed()  )
	            {
	            	Framework.logger.WriteLog(   "Verified that control exists. Control locator : " +   locator ,  true , false);
		            isControlExists = true;
	            }else
	            {
	            	Framework.logger.WriteLog( "The control not exist. Control locator : " +   locator ,  false , false);
	            
	            }            
	        }catch (Exception e) {
	        	Framework.logger.WriteLog( "The control not exist. Control locator : " +   locator ,  false , false);
	            
	        }
        return isControlExists;
    }
}
