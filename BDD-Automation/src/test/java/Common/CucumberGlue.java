package Common;

import gherkin.lexer.Fr;

import org.openqa.selenium.By;
import com.Core.Framework;
import com.Core.UIOperator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/*
 * @this is the place holder for all cucumber steps definitions
 */

public class CucumberGlue {
	
	UIOperator operator = new UIOperator(Framework.driver);
	
	@Given("^user open github application$")
	public void user_open_github_application() throws Exception {
		String url = Framework.prop.getProperty("application.url");
		Framework.driver.get(url);
		Framework.logger.WriteLog("User Open github application", true, false);
	}

	
	@Then("^github home page loaded successfully$")
	public void github_home_page_loaded_successfully() throws Exception {
		Framework.logger.WriteLog("github home page loaded successfully", true, false);
		By selector = By.xpath("//a//img[contains(@alt,'Git')]");
		if(operator.VerifyControlExist(selector)){
			System.out.println("Home page is displayed");
			Framework.logger.WriteLog("Home page is displayed", true, true);
		}
	}

	
	@When("^user click on \"([^\"]*)\" - link$")
	public void user_click_on_link(String arg1) throws Exception {
		Framework.logger.WriteLog("user click on "+arg1.trim() + "- link", true, false);
		By selector = By.xpath("//h3[text()='"+arg1.trim()+"']");
		operator.ClickElement(selector);
	}

	
	@Then("^\"([^\"]*)\" page loaded successfully$")
	public void github_page_loaded_successfully(String arg1) throws Exception  {
		Framework.logger.WriteLog(arg1.trim()+" page loaded successfully", true, false);
		By selector = By.xpath("//h1[contains(text(),'"+arg1.trim()+"')]");
		if(operator.VerifyControlExist(selector)){
			Framework.logger.WriteLog(arg1.trim()+" -page is displayed", true, true);//
		}
	}

	 
	@Then("^user go back to github home$")
	public void user_go_back_to_github_home() throws Exception  {
		Framework.logger.WriteLog("user go back to github home", true, false);
		By selector = By.xpath("//a//img[contains(@alt,'Git')]");
		operator.ClickElement(selector);
	}
	
	@Then("^user close github application$")
	public void user_close_github_application() throws Throwable {
		Framework.logger.WriteLog("user close github application", true, false);
		Framework.logger.WriteLog("close github application", true, false);
		Framework.driver.quit();
	}
	
}
