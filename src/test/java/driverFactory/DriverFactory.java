package driverFactory;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import utilities.CommonUtils;
import utilities.LoggerUtils;

public class DriverFactory {

	public static WebDriver getWebDriver(String env,String browser, WebDriver driver) {
		if(browser.equalsIgnoreCase("chrome")) {
			if(env.equalsIgnoreCase("local")) {
				try {
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/driverExecutables/chromedriver.exe");
					driver=new ChromeDriver();
					LoggerUtils.info("Chrome driver has been successfully created and chrome browser opened successfully");
				}
				catch(Exception e) {
					LoggerUtils.error("Failted to create session on chrome driver. Refer the stack trace below \n"+e.getMessage());
					Assert.fail();
				}
			}
			else if(env.equalsIgnoreCase("remote") || env.equalsIgnoreCase("")){
				try {
					LoggerUtils.info("Inside remote driver creation");
					String remoteUrl=CommonUtils.getAppConfig("remoteChromeDriverURL");
					LoggerUtils.info("Remote url from app config file:"+remoteUrl);
					ChromeOptions op=new ChromeOptions();
					driver=new RemoteWebDriver(new URL(remoteUrl),op);
					LoggerUtils.info("Remote session on chrome driver has been successfully created");
				}
				catch(Exception e) {
					LoggerUtils.error("Failted to create remote session on chrome driver. Refer the stack trace below \n"+e.getMessage());
					Assert.fail();
				}
			}
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			if(env.equalsIgnoreCase("local")) {
				try {
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/test/resources/driverExecutables/geckodriver.exe");
					driver=new FirefoxDriver();
				}
				catch(Exception e) {
					LoggerUtils.error("Failted to create session on firefox driver. Refer the stack trace below \n"+e.getMessage());
					Assert.fail();
				}
			}
			else if(env.equalsIgnoreCase("remote") || env.equalsIgnoreCase("")){
				try {
					LoggerUtils.info("Inside remote driver creation");
					String remoteUrl=CommonUtils.getAppConfig("remoteFirefoxDriverURL");
					LoggerUtils.info("Remote url from app config file:"+remoteUrl);
					DesiredCapabilities ds=DesiredCapabilities.firefox();
					driver=new RemoteWebDriver(new URL(remoteUrl),ds);
					LoggerUtils.info("Remote session on firefox driver has been successfully created");
				}
				catch(Exception e) {
					LoggerUtils.error("Failted to create remote session on firefox driver. Refer the stack trace below \n"+e.getMessage());
					Assert.fail();
				}
			}
		}
		return driver;
	}

	public static void closeDriver(WebDriver driver) {
		try {
			if(((RemoteWebDriver)driver).getSessionId()!=null) {
				driver.close();
				driver.quit();
				LoggerUtils.info("Driver closed succesfully");
			}
		}
		catch(Exception e) {
			LoggerUtils.error("Failed to close the driver."+e.getMessage());
			Assert.fail();
		}
	}

}
