package stepDefinitions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import driverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.plugin.event.Node.Examples;
import pageObjectRepository.PageObjectRepo;
import utilities.CommonUtils;
import utilities.LoggerUtils;
import utilities.ReportUtils;

/**
 * <h1>Assessment Test</h1>
 * Step Definitions for the BDD features
 * <p>
 * 
 *
 * @author  Ronald Nickson Pasteen Baskar
 * @version 1.0
 * @since   2021-01-18
 */

public class StepDefs {

	public WebDriver driver;
	public Scenario scenario;
	public Examples examples;
	public String defaultEnv="remote";
	public String env="";
	@Before
	public void beforeMethod(Scenario scenario) {
		LoggerUtils.info("-----------------Start of Scenario-----------------");
		this.scenario=scenario;
		if(System.getProperty("env.USER")!=null){
			if(System.getProperty("env.USER").isEmpty()) {
				env=defaultEnv;
			}
			else {
				env=System.getProperty("env.USER").toString();
			}
		}

	}

	@Given("I want to open {string} browser")
	public void i_want_to_open_browser(String browser) {
		try {
			this.driver=DriverFactory.getWebDriver(env,browser,driver);
			LoggerUtils.info("Brower opened successfully on "+env+". Brower Name:"+browser);
		}
		catch(Exception e) {
			LoggerUtils.info("Failed to open brower on "+env+". Brower Name:"+browser);
			Assert.fail();
		}
	}

	@Given("I want to scan the website")
	public void i_want_to_scan_the_website() {
		LoggerUtils.info("The browser opened successfully");
	}

	@When("I enter the url-{string}")
	public void i_enter_the_url(String url) {
		driver.get(url);
	}

	@Then("I should be on the home page with the welcome message as {string}")
	public void i_should_be_on_the_home_page(String expWelcomeMsg) {

		LoggerUtils.info("Site succesfully lauched with the url: "+driver.getCurrentUrl());
		String actualWelcomeMessage=driver.findElement(By.xpath(PageObjectRepo.getPageObjets("pageWelcomeMessage"))).getText();
		if(actualWelcomeMessage.equalsIgnoreCase(expWelcomeMsg)) {
			LoggerUtils.info("Home page is verified succesfully with the welcome Message '"+actualWelcomeMessage+"'");
		}
	}

	@Then("I should be redirected to the corressponding webpage")
	public void i_should_be_on_redirected_to_the_corressponding_webpage() {
		LoggerUtils.info("User is succesfully redirected to the webpage with the url: "+driver.getCurrentUrl());
	}

	@Then("I validate the response code from the page url {string} is {int}")
	public void i_validate_the_response_code_from_the_page(String url, Integer expRespCode) {
		int actualResponseCode=CommonUtils.getPageResponseCode(url);
		if(actualResponseCode==expRespCode.intValue()) {
			LoggerUtils.info("Succesfully validated the page response code as "+expRespCode);
			scenario.log("Succesfully validated the page response code as "+expRespCode);
		}
		else {
			LoggerUtils.info("Failed to validate the page response code as "+expRespCode);
			scenario.log("Failed to validate the page response code as "+expRespCode);
		}
	}

	@When("I maximize the browser")
	public void i_maximize_the_browser() {
		driver.manage().window().maximize();
	}

	@Then("the {string} browser should be maximized")
	public void the_browser_should_be_maximized(String browser) {
		LoggerUtils.info("The browser maximized succesfully");

	}

	@Then("I verify the page title as {string}")
	public void i_verify_the_page_title_as(String expPageTitle) {
		String actualPageTitle=driver.getTitle();
		if(expPageTitle.equals(actualPageTitle)) {
			LoggerUtils.info("Page title verified succesfully");
		}
	}

	@Then("I wait for {long} seconds to load the page")
	public void i_wait_for_seconds_to_load_the_page(Long sleep) {
		JavascriptExecutor jse=((JavascriptExecutor)driver);
		String readyState="loading";
		while(!readyState.equalsIgnoreCase("complete")) {
			try {
				Thread.sleep(sleep*1000l);
				readyState=(String) jse.executeScript("return document.readyState");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Then("I validate for no console errors for the url-{string}")
	public void i_validate_for_no_console_errors(String url) {
		try {
			LogEntries logEntries=driver.manage().logs().get(LogType.BROWSER);
			LoggerUtils.info("Total console log entries found: "+logEntries.getAll().size());
			int errorCnt=0;
			boolean errorsFound=false;
			String errorsList="";
			for(LogEntry logEntry:logEntries) {
				if((logEntry.getLevel().toString()).equalsIgnoreCase("SEVERE")) {
					LoggerUtils.info("Log Type: "+logEntry.getLevel()+" Log Message: "+logEntry.getMessage() );
					errorsFound=true;
					++errorCnt;
					errorsList=errorsList+errorCnt+":: Log Type: "+logEntry.getLevel()+" Log Message: "+logEntry.getMessage()+"\n";
				}
			}

			LoggerUtils.info("Total Errors found in console: "+errorCnt);
			//Assert.assertEquals(errorsFound, false, "Verify console errors on page loads");
			//assertFalse(errorsFound);
			if(errorsFound) {
				LoggerUtils.info("Errors found in the console on page load");
				//Assert.fail();
				scenario.log("<b>Valdiation Failed for URL:</b> <i><u>"+url+"</u></i>\n Console errors found on page loads. Errors Found as below,\n"+errorsList);
				Assert.assertEquals(errorsFound, false, "Verify console errors on page loads."); //Errors Found as below,\n"+errorsList);
			}else {
				LoggerUtils.info("No console Errors found");
				Assert.assertEquals(errorsFound, false, "Verify console errors on page loads");
			}
		}
		catch(Exception e) {
			LoggerUtils.info("Error :"+e.getMessage());
		}
	}

	@When("I close the {string} browser")
	public void i_close_the_browser(String browser) {
		driver.close();
	}

	@Then("the current browser should be closed")
	public void the_current_browser_should_be_closed() {
		LoggerUtils.info("Browser closed succesfully");
		driver.quit();
	}

	@Then("I validate all the links on the page-{string}")
	public void i_validate_all_the_links_on_the_page(String url) {
		String href="";
		String homePageUrl=url;
		Set<String> uniqueLinks=new HashSet<String>();
		List<WebElement> links=driver.findElements(By.tagName("a"));
		Iterator<WebElement> it = links.iterator();
		while(it.hasNext()){
			href = it.next().getAttribute("href");
			if(href != null) {
				if(href.isEmpty()){
					LoggerUtils.warn("URL is either not configured for anchor tag or it is empty");
					continue;
				}else if(!href.startsWith(homePageUrl)){
					LoggerUtils.warn("URL belongs to another domain, skipping it.");
					continue;
				}else {
					LoggerUtils.info("URL: "+href);
					uniqueLinks.add(href);
				}
			}else
			{
				LoggerUtils.error("URL is null, skipping it.");
			}

		}
		LoggerUtils.info("Links of the page:"+uniqueLinks);
		String fileName="pageLinks";

		//Iterator<String> linkIterator=uniqueLinks.iterator();
		Map<String,Integer> linkResponseMap=new HashMap<String,Integer>();
		for(String link:uniqueLinks) {
			linkResponseMap.put(link, CommonUtils.getPageResponseCode(link));
			CommonUtils.verifyLinks(link);
		}


		ReportUtils.generateHtmlReport(fileName, "Scan and validation of all links on the Page["+url+"]", linkResponseMap);

		scenario.log("<a href=reports/"+fileName+".html target=\"_blank\">click here for the list of links scanned & validated for response code</a>");

	}

	@Then("I validate all the images on the page-{string}")
	public void i_validate_all_the_images_on_the_page(String url) {
		List<WebElement> images = driver.findElements(By.tagName("img"));
		LoggerUtils.info("Total number of Images on the Page are " + images.size());
		Map<String,Integer> imageLinkResponseMap=new HashMap<String,Integer>();
		for(int index=0;index<images.size();index++)
		{
			WebElement image=images.get(index);
			String imageURL= image.getAttribute("src");
			LoggerUtils.info("URL of Image " + (index+1) + " is: " + imageURL);
			imageLinkResponseMap.put(imageURL, CommonUtils.getPageResponseCode(imageURL));
			CommonUtils.verifyLinks(imageURL);
			//linkResponseMap=CommonUtils.verifyLinksResponseMap(imageURL);
			CommonUtils.verifyImages(driver, image);
		}

		if(imageLinkResponseMap.size()!=0) {
			String fileName="imageLinks";
			ReportUtils.generateHtmlReport(fileName, "Scan and validation of all images on the Page["+url+"]", imageLinkResponseMap);
			scenario.log("<a href=reports/"+fileName+".html target=\"_blank\">click here for the image scan & validation report</a>");
		}

		List<WebElement> pictures = driver.findElements(By.tagName("picture"));
		LoggerUtils.info("Total number of pictures on the Page are " + pictures.size());
		Map<String,Integer> pictureLinkResponseMap=new HashMap<String,Integer>();

		List<WebElement> pictureSources = driver.findElements(By.xpath(PageObjectRepo.getPageObjets("pictureSources")));
		int picCnt=0;
		for(int index=0;index<pictureSources.size();index++)
		{
			WebElement source=pictureSources.get(index);
			String sourceSet= source.getAttribute("srcset");
			String[] splitSrc=sourceSet.split(",");

			for(String src:splitSrc) {
				String srcUrl=src.split(" ")[0].trim();
				if(!srcUrl.equalsIgnoreCase("")) {
					srcUrl=url+srcUrl;
					LoggerUtils.info("URL of Image " + (++picCnt) + " is: " + srcUrl);
					pictureLinkResponseMap.put(srcUrl, CommonUtils.getPageResponseCode(srcUrl));
					CommonUtils.verifyLinks(srcUrl);
				}


			}
			//linkResponseMap=CommonUtils.verifyLinksResponseMap(imageURL);
			CommonUtils.verifyImages(driver, source);
		}

		if(pictureLinkResponseMap.size()!=0) {
			String fileName="pictureLinks";
			ReportUtils.generateHtmlReport(fileName, "Scan and validation of all pictures on the Page["+url+"]", pictureLinkResponseMap);

			scenario.log("<a href=reports/"+fileName+".html target=\"_blank\">click here for the pictures scan & validation report</a>");
		}
	}

	@After
	public void afterMethod() {
		DriverFactory.closeDriver(driver);
		LoggerUtils.info("Browser closed successfully in after method");
		LoggerUtils.info("-----------------End of Scenario-----------------");

	}

}
