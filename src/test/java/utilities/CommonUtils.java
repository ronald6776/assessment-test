package utilities;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * <h1>Assessment Test</h1>
 * Common Utilities for the BDD framework
 * <p>
 * 
 *
 * @author  Ronald Nickson Pasteen Baskar
 * @version 1.0
 * @since   2021-01-18
 */

public class CommonUtils {
	public static HttpURLConnection httpURLConnect;

	public static int getPageResponseCode(String url) {
		int respCode=0;
		try {
			httpURLConnect = (HttpURLConnection)(new URL(url).openConnection());
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();

			respCode = httpURLConnect.getResponseCode();
			LoggerUtils.info("HTTP Status Code of url-" + url +" is "+respCode);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return respCode;
	}

	public static void verifyLinks(String linkUrl)
	{
		try
		{
			URL url = new URL(linkUrl);

			httpURLConnect=(HttpURLConnection)url.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			if(httpURLConnect.getResponseCode()>=400)
			{
				LoggerUtils.error("HTTP STATUS - " + httpURLConnect.getResponseMessage() + "is a broken link");
			}    

			else{
				LoggerUtils.info("HTTP STATUS - " + httpURLConnect.getResponseMessage());
			}
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String,Integer> verifyLinksResponseMap(String linkUrl)
	{
		Map<String,Integer> linkMap=new HashMap<String,Integer>();
		try
		{
			URL url = new URL(linkUrl);

			httpURLConnect=(HttpURLConnection)url.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			if(httpURLConnect.getResponseCode()>=400)
			{
				LoggerUtils.error("HTTP STATUS - " + httpURLConnect.getResponseMessage() + "is a broken link");
				linkMap.put(linkUrl, httpURLConnect.getResponseCode());
			}    

			else{
				LoggerUtils.info("HTTP STATUS - " + httpURLConnect.getResponseMessage());
				linkMap.put(linkUrl, httpURLConnect.getResponseCode());
			}
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linkMap;
	}

	public static void verifyImages(WebDriver driver,WebElement image) {
		try {
			boolean imageDisplayed = (Boolean) ((JavascriptExecutor) driver).executeScript("return (typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth > 0);", image);
			if (imageDisplayed) {
				LoggerUtils.info("Image Displayed");
			}else {
				LoggerUtils.error("Image Display - Broken");
			}
		} 
		catch (Exception e) {
			LoggerUtils.error("Error Occured"+e.getMessage());
		}
	}

	public static String getCurrentDateTime() {
		DateFormat df=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date dt=new Date();
		return df.format(dt);
	}

	public static String getAppConfig(String configKey) {
		File appConfigFile=null;
		String configValue="";
		try {
			appConfigFile=new File(System.getProperty("user.dir")+"/src/test/resources/config/appConfig.json");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		configValue=JsonUtils.parseJsonToMap(appConfigFile).get(configKey);
		return configValue;

	}

}
