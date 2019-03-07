package CucumberRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/*
 * @this is Cucumber Runner class
 * @this is responsible to run the project with help of TestNG
 * 
 */
@CucumberOptions(
				plugin={"pretty","html:target/html/","json:target/cucumber.json"},
				glue={"com.Core","Common"},
				features="./src/test/resources/features/",
				dryRun= false
		)
public class Runner extends AbstractTestNGCucumberTests {

}
