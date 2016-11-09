package org.cpiatframework.library;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.cpiatframework.config.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExpectedKeyword {
	
	public static WebDriver driver;
	private static By by;
	public static String browser = "";
	public static String project = "";
	public static String folderDate = "";

	public static WebDriver getDriver() {
		return driver;
	}
	
	public static String getFolderDate() {
		return folderDate;
	}

	public static void setFolderDate(String folderDate) {
		ExpectedKeyword.folderDate = folderDate;
	}

	public static String getProject() {
		return project;
	}

	public void setProject(String project) {
		ExpectedKeyword.project = project;
	}
	
	public static String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		ExpectedKeyword.browser = browser;
	}

	public static void setDriver(WebDriver driver) {
		ExpectedKeyword.driver = driver;
	}
	
	public static String elementIsVisible(String keyword, String elementKey, String property) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
		try{
			if(driver.findElement(getPropBy(elementKey, property)).isDisplayed()){
				msg = property + " is visible.";
				remarks = "PASSED";
				result = remarks + ":" + " " + msg;
			}/* else {
				msg = property + " is not visible.";
				result = "FAILED:" + " " + msg;
			}*/
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = property + " is not visible.";
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
			takeScreenShot(keyword, property, remarks);
		}
		
		return result;
	}
	
	public static String elementIsNotVisible(String keyword, String elementKey, String property) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
		try{
			if(!driver.findElement(getPropBy(elementKey, property)).isDisplayed()){
				msg = property + " is not visible.";
				remarks = "PASSED";
				result = remarks + ":" + " " + msg;
			} /*else {
				msg = property + " is visible.";
				result = "FAILED:" + " " + msg;
			}*/
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = property + " is visible.";
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
			takeScreenShot(keyword, property, remarks);
		}
		
		return result;
	}
	
	public static String elementIsSelected(String keyword, String elementKey, String property) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
		try{
			if(driver.findElement(getPropBy(elementKey, property)).isSelected()){
				msg = property + " is selected.";
				remarks = "PASSED";
				result = remarks + ":" + " " + msg;
			} /*else {
				msg = property + " is visible.";
				result = "FAILED:" + " " + msg;
			}*/
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = property + " is visible.";
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
			takeScreenShot(keyword, property, remarks);
		}
		
		return result;
	}
	
	public static String elementIsNotSelected(String keyword, String elementKey, String property) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
		try{
			if(!driver.findElement(getPropBy(elementKey, property)).isSelected()){
				msg = property + " is not selected.";
				remarks = "PASSED";
				result = remarks + ":" + " " + msg;
			} /*else {
				msg = property + " is visible.";
				result = "FAILED:" + " " + msg;
			}*/
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = property + " is visible.";
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
			takeScreenShot(keyword, property, remarks);
		}
		
		
		return result;
	}
	
	public static String elementIsEnabled(String keyword, String elementKey, String property) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
		try{
			if(driver.findElement(getPropBy(elementKey, property)).isEnabled()){
				msg = property + " is enabled.";
				remarks = "PASSED";
				result = remarks + ":" + " " + msg;
			} /*else {
				msg = property + " is visible.";
				result = "FAILED:" + " " + msg;
			}*/
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = property + " is visible.";
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
			takeScreenShot(keyword, property, remarks);
		}
		
		return result;
	}
	
	public static String elementIsDisabled(String keyword, String elementKey, String property) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
		try{
			if(!driver.findElement(getPropBy(elementKey, property)).isEnabled()){
				msg = property + " is disabled.";
				remarks = "PASSED";
				result = remarks + ":" + " " + msg;
			} /*else {
				msg = property + " is visible.";
				result = "FAILED:" + " " + msg;
			}*/
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = property + " is visible.";
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
			takeScreenShot(keyword, property, remarks);
		}
		
		return result;
	}
	
public static String elementTextShouldMatchExpected(String keyword, String elementKey, String property, String value) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
	
		String actual = driver.findElement(getPropBy(elementKey, property)).getText();
		System.out.println(actual);
		try{
			if(actual.equalsIgnoreCase(value)){
//				element = driver.findElement(getPropBy(elementKey, property));
				msg = property + " content text matches the expected text.";
				remarks = "PASSED";
				result = remarks + ":" + " " + msg;
			} /*else {
				msg = property + " is visible.";
				result = "FAILED:" + " " + msg;
			}*/
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = property + " content text did not match the expected text.";
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
//			if(remarks == "PASSED"){
//				takeScreenShotPassed(keyword, elementKey, property, remarks);
//			} else {
//				takeScreenShotFailed(keyword, elementKey, property, remarks);
//			}
		}
		
		
		return result;
	}
	
	public static String getAlertMessage(String keyword, String elementKey, String property) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
		try{
			
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
//			Alert alert = driver.switchTo().alert();
//			String alertText = alert.getText();
			
//			msg = "Alert message: " + alertText;
			remarks = "PASSED";
			result = remarks + ":" + " " + msg;
			
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = e.getMessage();
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
//			if(remarks == "PASSED"){
//				takeScreenShotPassed(keyword, elementKey, property, remarks);
//			} else {
//				takeScreenShotFailed(keyword, elementKey, property, remarks);
//			}
		}
		
		return result;
	}
	
	public static String checkboxShouldBeSelected(String keyword, String elementKey, String property) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
		
		try{
			if(driver.findElement(getPropBy(elementKey, property)).isSelected()){
//				element = driver.findElement(getPropBy(elementKey, property));
				msg = property + " is selected.";
				remarks = "PASSED";
				result = remarks + ":" + " " + msg;
			}/* else {
				msg = property + " is not visible.";
				result = "FAILED:" + " " + msg;
			}*/
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = property + " is not selected.";
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
//			if(remarks == "PASSED"){
//				takeScreenShotPassed(keyword, elementKey, property, remarks);
//			} else {
//				takeScreenShotFailed(keyword, elementKey, property, remarks);
//			}
		}
		
		return result;
	}
	
	public static String getNumberOfLinks(String keyword) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
		try{
			Thread.sleep(3000);
//			List<WebElement> links = driver.findElements(By.tagName("a"));
			
//			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(links));
			Thread.sleep(3000);
			int noOfLinks = driver.findElements(By.tagName("a")).size();
			
			
			System.out.println("size: " + noOfLinks);
			
			for (int i = 0; i<driver.findElements(By.tagName("a")).size(); i++){
	 
				System.out.println(driver.findElements(By.tagName("a")).get(i).getText());
	 
			}
	 
			msg = "Number of Links: " + noOfLinks;
			remarks = "PASSED";
			result = remarks + ":" + " " + msg;
			/* else {
				msg = property + " is not visible.";
				result = "FAILED:" + " " + msg;
			}*/
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = e.getMessage();
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
//			if(remarks == "PASSED"){
//				takeScreenShotPassed(keyword, remarks);
//			} else {
//				takeScreenShotFailed(keyword, remarks);
//			}
		}
		
		return result;
	}
	
	public static String getAllLinks(String keyword) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
		try{
			Thread.sleep(3000);
			//List<WebElement> links = driver.findElements(By.tagName("a"));
			Thread.sleep(3000);
			msg = "Links: ";
			for (int i = 0; i<driver.findElements(By.tagName("a")).size(); i++){ 
				msg += driver.findElements(By.tagName("a")).get(i).getText() + "\n";
			}
			
			remarks = "PASSED";
			result = remarks + ":" + " " + msg;
			/* else {
				msg = property + " is not visible.";
				result = "FAILED:" + " " + msg;
			}*/
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = e.getMessage();
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
//			if(remarks == "PASSED"){
//				takeScreenShotPassed(keyword, remarks);
//			} else {
//				takeScreenShotFailed(keyword, remarks);
//			}
			
			//takeScreenShot(keyword, remarks);
		}
		
		return result;
	}
	
	
	public static String modalIsVisible(String keyword, String elementKey, String property) throws IOException{
		
		String result = "";	
		String msg = "";
		String remarks ="";
		
		
		try{
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(getPropBy(elementKey, property)));
			
			if(driver.findElement(getPropBy(elementKey, property)).isDisplayed()){
				//element = driver.findElement(getPropBy(elementKey, property));
				msg = property + " is visible.";
				remarks = "PASSED";
				result = remarks + ":" + " " + msg;
			} /*else {
				msg = property + " is visible.";
				result = "FAILED:" + " " + msg;
			}*/
		} catch (Exception e){
			/*msg = e.getMessage();
			result = "FAILED:" + " " + msg;*/
			msg = property + " is not visible.";
			remarks = "FAILED";
			result = remarks + ":" + " " + msg;
		} finally {
//			if(remarks == "PASSED"){
//				takeScreenShotPassed(keyword, elementKey, property, remarks);
//			} else {
//				takeScreenShotFailed(keyword, elementKey, property, remarks);
//			}
			//takeScreenShot(keyword, property, remarks);
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
	
	private static void takeScreenShot(String keyword, String property, String remarks) throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(scrFile, new File(Constants.PATH_DOWNLOAD + File.separator + project + "-" + folderDate + "\\" + browser + "\\" + keyword + "-" + property + "-" + remarks + ".png"));
	}
}
