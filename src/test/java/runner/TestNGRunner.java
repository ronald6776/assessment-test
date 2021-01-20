package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * <h1>Assessment Test</h1>
 * TestNG Runner class for BDD features
 * <p>
 * 
 *
 * @author  Ronald Nickson Pasteen Baskar
 * @version 1.0
 * @since   2021-01-18
 */

@CucumberOptions(features= {"src/test/resources/features"},
plugin= {"pretty","html:target/cucumberHtmlReports/webPageScanReport.html","json:target/jsonRpt/cucumber.json"},
monochrome=true,dryRun=false,
glue= {"stepDefinitions"})
//,tags = "@Regression")
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
