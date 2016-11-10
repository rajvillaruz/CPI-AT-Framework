package org.cpiatframework.library;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.cpiatframework.config.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestKeyword {
	WebElement element;
	static DesiredCapabilities capabilities;
	public static WebDriver driver;
	static By by;
	
	public static String openBrowser(String[] browser, String ipAddress) throws MalformedURLException {
		String result;	
		String msg = "";
		
		try {
			System.out.println(ipAddress);
			System.out.println(browser[2]);
			if(browser[2].equals(Constants.BROWSER_PROPERTY_CHROME.toString())){
				capabilities = DesiredCapabilities.chrome();
				driver = new RemoteWebDriver(new URL("http://" + ipAddress + "/wd/hub"), capabilities);
				ExpectedKeyword.driver = driver;
			} else if (browser[2].equals(Constants.BROWSER_PROPERTY_FIREFOX)){
				capabilities = DesiredCapabilities.firefox();
				driver = new RemoteWebDriver(new URL("http://" + ipAddress + "/wd/hub"), capabilities);
				ExpectedKeyword.driver = driver;
			}	
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String launchApp(String url){
		String result;
		String msg = "";
		try {
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String inputText(String elementKey, String property, String value) throws IOException {
		String result;
		String msg = "";
		try {
			By byInputProp = getPropBy(elementKey, property);
			driver.findElement(byInputProp).sendKeys(value);
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
		
	}
	
	public static String clickButton(String elementKey, String property) throws IOException{
		String result ;
		String msg = "";
		try {
			By byButtonProp = getPropBy(elementKey, property);
			
			driver.findElement(byButtonProp).click();
			msg = " ";
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String closeBrowser() {
		String result;	
		String msg = "";
		
		try {
			driver.quit();
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static By getPropBy(String elementKey, String property) throws IOException {
		if(elementKey.endsWith("id")){
			by = By.id(property);
		} else if (property.contains("xpath")){
			by = By.xpath(property);
		} else if (property.contains("class")){
			by = By.className(property);
		} else if (property.contains("name")){
			by = By.name(property);
		} else if (property.contains("tagname")){
			by = By.tagName(property);
		} else if (property.contains("linkText")){
			by = By.linkText(property);
		} else if (property.contains("partialLinkText")){
			by = By.partialLinkText(property);
		} else if (property.contains("cssSelector")){
			by = By.cssSelector(property);
		} else {
			by = By.id(property);
		}
		
		return by;
	}
}
