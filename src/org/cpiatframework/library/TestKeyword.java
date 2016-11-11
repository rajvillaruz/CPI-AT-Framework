package org.cpiatframework.library;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.cpiatframework.config.Constants;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestKeyword {
	static WebElement element;
	static DesiredCapabilities capabilities;
	public static WebDriver driver;
	static By by;
	static Actions action = null;
	
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
			Thread.sleep(1000);
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
	
	public static String clearTextElement(String elementKey, String property) throws IOException{
		String result ;
		String msg = "";
		try {
			By byTextElement = getPropBy(elementKey, property);
			driver.findElement(byTextElement).clear();
			msg = " ";
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String dismissAlert() throws IOException{
		String result ;
		String msg = "";
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			msg = " ";
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String acceptAlert() throws IOException{
		String result ;
		String msg = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
			msg = " ";
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String goBack() throws IOException{
		String result ;
		String msg = "";
		try {
			driver.navigate().back();
			msg = " ";
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String refreshPage() throws IOException{
		String result ;
		String msg = "";
		try {
			driver.navigate().refresh();
			msg = " ";
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String selectRadioButton(String elementKey, String property) throws IOException{
		String result ;
		String msg = "";
		try {
			By byRadioButton = getPropBy(elementKey, property);
			
			driver.findElement(byRadioButton).click();
			
			if(driver.findElement(byRadioButton).isSelected()){
				msg = " ";
				result = "PASSED";
			} else {
				msg = "Failed to select " + elementKey;
				result = "FAILED";
			}
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String doubleClickElement(String elementKey, String property) throws IOException{
		String result = null ;
		String msg = "";
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(getPropBy(elementKey, property)));
			
			if(driver.findElement(getPropBy(elementKey, property)).isDisplayed()){
				msg = " ";
				result = "PASSED";
			} 

		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String waitUntilElementIsVisible(String elementKey, String property) throws IOException{
		String result = null ;
		String msg = "";
		try {
			By byElement= getPropBy(elementKey, property);
			
			
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(getPropBy(elementKey, property)));
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable((getPropBy(elementKey, property))));
		    /*element = driver.findElement(byElement);
			
			action = new Actions(driver);
			action.doubleClick(element).build().perform();*/
			if(driver.findElement(getPropBy(elementKey, property)).isDisplayed()){
				msg = " ";
				result = "PASSED";
			}
				
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	

	public static String uploadFile(String elementKey, String property, String value) throws IOException{
		String result ;
		String msg = "";
		try {
			By byElement= getPropBy(elementKey, property);
			
		    driver.findElement(byElement).sendKeys(value);
			
			msg = " ";
			result = "PASSED";
				
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String selectFromDropdown(String elementKey, String property, String value) throws IOException{
		String result;
		String msg = "";
		try {
			By byElement= getPropBy(elementKey, property);
			
			driver.findElement(byElement).click();
			driver.findElement(byElement).sendKeys(value);
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
		//ObjectLibrary obj = new ObjectLibrary();
		if(elementKey.equalsIgnoreCase("id")){
			by = By.id(property);
		} else if (elementKey.equalsIgnoreCase("xpath")){
			by = By.xpath(property);
		} else if (elementKey.equalsIgnoreCase("class")){
			by = By.className(property);
		} else if (elementKey.equalsIgnoreCase("name")){
			by = By.name(property);
		} else if (elementKey.equalsIgnoreCase("tagname")){
			by = By.tagName(property);
		} else if (elementKey.equalsIgnoreCase("linkText")){
			by = By.linkText(property);
		} else if (elementKey.equalsIgnoreCase("partialLinkText")){
			by = By.partialLinkText(property);
		} else if (elementKey.equalsIgnoreCase("cssSelector")){
			by = By.cssSelector(property);
		} else {
			by = By.id(property);
		}
		
		return by;
	}
	
//	public static By getPropBy(String elementKey, String property) throws IOException {
//		if(elementKey.endsWith("id")){
//			by = By.id(property);
//		} else if (property.contains("xpath")){
//			by = By.xpath(property);
//		} else if (property.contains("class")){
//			by = By.className(property);
//		} else if (property.contains("name")){
//			by = By.name(property);
//		} else if (property.contains("tagname")){
//			by = By.tagName(property);
//		} else if (property.contains("linkText")){
//			by = By.linkText(property);
//		} else if (property.contains("partialLinkText")){
//			by = By.partialLinkText(property);
//		} else if (property.contains("cssSelector")){
//			by = By.cssSelector(property);
//		} else {
//			by = By.id(property);
//		}
//		
//		return by;
//	}
}
