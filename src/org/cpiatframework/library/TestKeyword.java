package org.cpiatframework.library;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.cpiatframework.config.Constants;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

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
	
	
	public static String takeScreenShot(String fileName, String project, String folderDate, String browser){
		String result;
		String msg = "";
		try {
			Thread.sleep(2000);
			System.out.println("take screenshot");
			Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).withName(fileName + " -- " + folderDate).save(Constants.PATH_DOWNLOAD + File.separator + project + "-" + folderDate + "\\" + browser + " Screenshots" + "\\");
//			FileUtils.copyFile(scrFile, new File(Constants.PATH_DOWNLOAD + File.separator + project + "-" + folderDate + "\\" + browser[2] + " - SCREENSHOTS" + "\\" + fileName + ".png"));
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
			msg = "Unable to locate element. Please check your locator/s or identifier/s.";
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
			msg = "Unable to locate element.Please check your locator/s or identifier/s.";
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
			msg = "Unable to locate element.Please check your locator/s or identifier/s.";
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
			msg = "Unable to dismiss alert.";
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
			msg = "Unable to accept alert.";
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
			msg = "Unable to locate element.Please check your locator/s or identifier/s.";
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
			msg = "Unable to locate element.Please check your locator/s or identifier/s.";
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
			msg = "Unable to locate element.Please check your locator/s or identifier/s.";
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	

	public static String uploadFile(String elementKey, String property, String value) throws IOException{
		String result ;
		String msg = "";
		try {
			By byElement= getPropBy(elementKey, property);
//			
//		    driver.findElement(byElement).sendKeys(value);
		    
		    ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		    WebElement upload = driver.findElement(byElement);
		    upload.sendKeys(value);
			
			msg = " ";
			result = "PASSED";
				
		} catch (Exception e) {
			msg = "No file uploaded.";
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String selectFromDropdown(String elementKey, String property, String value) throws IOException{
		String result;
		String msg = "";
		try {
			
			By byElement= getPropBy(elementKey, property);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
			Select drpdown = new Select(driver.findElement(byElement));
			for (WebElement options : drpdown.getOptions()) {		
				System.out.println(options.getText().equalsIgnoreCase("Java Advanced Topics") + " " + value + " " + options.getText());
				if (options.getText().equalsIgnoreCase(value)) {
					System.out.println("selected");
					drpdown.selectByIndex(drpdown.getOptions().indexOf(options));
					wait.until(ExpectedConditions.elementSelectionStateToBe(options, true));
					break;
				}
			}
			msg = " ";
			result = "PASSED";
			
		} catch (Exception e) {
			msg = "Unable to locate element.Please check your locator/s or identifier/s.";
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String selectDateGeniisys(String value) throws IOException{
		String result;
		String msg = "";
		
		String[] values = value.split("-");
		try {
			//month
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='scwMonths']")));
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='scwMonths']")));
			Select drpdown = new Select(driver.findElement(By.xpath(".//*[@id='scwMonths']")));
			for (WebElement options : drpdown.getOptions()) {		
				//System.out.println(options.getText().equalsIgnoreCase("Java Advanced Topics") + " " + value + " " + options.getText());
				if (options.getText().equalsIgnoreCase(values[0])) {
					System.out.println("selected " + options.getText());
					drpdown.selectByIndex(drpdown.getOptions().indexOf(options));
					wait.until(ExpectedConditions.elementSelectionStateToBe(options, true));
					break;
				}
			}
			
			//year
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='scwYears']")));
			Select drpdown2 = new Select(driver.findElement(By.xpath(".//*[@id='scwYears']")));
			for (WebElement options : drpdown2.getOptions()) {		
				//System.out.println(options.getText().equalsIgnoreCase("Java Advanced Topics") + " " + value + " " + options.getText());
				if (options.getText().equalsIgnoreCase(values[2])) {
					System.out.println("selected");
					drpdown2.selectByIndex(drpdown2.getOptions().indexOf(options));
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementSelectionStateToBe(options, true));
					break;
				}
			}
			
			//day
			WebElement datewidget = driver.findElement(By.xpath(".//*[@id='scw']"));
			//List<WebElement> dates = driver.findElements(By.xpath(".//*[@id='mainPageBody']/div[4]/div[1]/table/tbody/tr/td")); //calendar data
			List<WebElement> dates = datewidget.findElements(By.tagName("td"));
			
			for(int i=0; i<dates.size(); i++){
				String date = dates.get(i).getText();
				if(date.equalsIgnoreCase(values[1])){
					dates.get(i).click();
					break;
				}
			}
			
			msg = " ";
			result = "PASSED";
			
		} catch (Exception e) {
			msg = "Unable to locate element.Please check the value you entered.";
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	
	
	public static String selectDateTP(String value) throws IOException{
		String result;
		String msg = "";
		
		String[] values = value.split("-");
		try {
			//year
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@id='mainPageBody']/div[4]/div[1]/table/thead/tr[2]/th[2]")));
			driver.findElement(By.xpath(".//*[@id='mainPageBody']/div[4]/div[1]/table/thead/tr[2]/th[2]")).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@id='mainPageBody']/div[4]/div[2]/table/thead/tr[2]/th[2]")));
			driver.findElement(By.xpath(".//*[@id='mainPageBody']/div[4]/div[2]/table/thead/tr[2]/th[2]")).click();
			List<WebElement> years = driver.findElements(By.tagName("span"));
			for(int i=0; i<years.size(); i++){
				String year = years.get(i).getText();
				if(year.equalsIgnoreCase(values[2])){
					years.get(i).click();
					break;
				}
			}
			
			//month
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@id='mainPageBody']/div[4]/div[2]/table/tbody/tr/td")));
			WebElement monthWidget =  driver.findElement(By.xpath(".//*[@id='mainPageBody']/div[4]/div[2]/table/tbody/tr/td"));
			List<WebElement> months = monthWidget.findElements(By.tagName("span"));
			for(int i=0; i<months.size(); i++){
				String month = months.get(i).getText();
				if(month.equalsIgnoreCase(values[0])){
					months.get(i).click();
					break;
				}
			}
			
			//day
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='mainPageBody']/div[4]")));
			WebElement datewidget = driver.findElement(By.xpath(".//*[@id='mainPageBody']/div[4]"));
			List<WebElement> dates = datewidget.findElements(By.tagName("td"));
			
			for(int i=0; i<dates.size(); i++){
				String date = dates.get(i).getText();
				if(date.equalsIgnoreCase(values[1])){
					dates.get(i).click();
					break;
				}
			}
			
			msg = " ";
			result = "PASSED";
			
		} catch (Exception e) {
			msg = "Unable to locate element.Please check the value you entered.";
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	
	public static String closeBrowser() {
		String result;	
		String msg = "";
		
		try {
			Thread.sleep(5000);
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
		} else if (elementKey.equalsIgnoreCase("tag name")){
			by = By.tagName(property);
		} else if (elementKey.equalsIgnoreCase("link Text")){
			by = By.linkText(property);
		} else if (elementKey.equalsIgnoreCase("partial Link Text")){
			by = By.partialLinkText(property);
		} else if (elementKey.equalsIgnoreCase("css Selector")){
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
