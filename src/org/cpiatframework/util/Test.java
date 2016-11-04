package org.cpiatframework.util;

import java.io.IOException;

import org.cpiatframework.library.TestKeyword;

public class Test {
	public String keyword = "";
	public String elementKey = "";
	public String property = "";
	public String value = "";
	public String expected = "";
	public String ipAddress = "";
	public String project = "";
	public String folderDate = "";
	public String[] browser;
	
	public Test(String keyword) {
		this.keyword = keyword;
	}

	public Test(String keyword, String property) {
		this.keyword = keyword;
		this.property = property;
	}
	
	public Test(String keyword, String elementKey, String property) {
		this.keyword = keyword;
		this.property = property;
		this.elementKey = elementKey;
	}
	
	public Test(String keyword, String elementKey, String property, String value) {
		this.keyword = keyword;
		this.property = property;
		this.elementKey = elementKey;
		this.value = value;
	}
	
	public Test(String keyword, String elementKey, String property, String value, String expected) {
		this.keyword = keyword;
		this.elementKey = elementKey;
		this.property = property;
		this.value = value;
		this.expected = expected;
	}
	
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	public String getFolderDate() {
		return folderDate;
	}

	public void setFolderDate(String folderDate) {
		this.folderDate = folderDate;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String[] getBrowser() {
		return browser;
	}

	public void setBrowser(String[] browser) {
		this.browser = browser;
	}
	
	public String callMethod() throws IOException {
		String result = "";
		switch (keyword.toUpperCase()) {
		case "OPEN BROWSER":
			result = TestKeyword.openBrowser(browser, ipAddress);
			break;
		case "LAUNCH APP":
			result = TestKeyword.launchApp(property);
			break;
		case "INPUT TEXT":
			result = TestKeyword.inputText(elementKey, property, value);
			break;
		case "CLICK BUTTON":
			result = TestKeyword.clickButton(elementKey, property);
			break;
		case "CLOSE BROWSER":
			result = TestKeyword.closeBrowser();
			break;
		default:
			break;
		}
		
		if (expected != null){
			String[] exp = expected.split(",");
			Expected eu;
			int size = exp.length;
			switch (size){
			case 2:
				eu = new Expected(exp[0], exp[1]);
				eu.setFolderDate(folderDate);
				eu.setProject(project);
				eu.setBrowser(browser[0]);
				result = result + "-" + eu.callMethod();
				break;
			case 3:
				eu = new Expected(exp[0], exp[1], exp[2]);
				eu.setFolderDate(folderDate);
				eu.setProject(project);
				eu.setBrowser(browser[0]);
				result = result + "-" + eu.callMethod();
				break;
			default:
				break;
			}	
		} else{
			result = result + "- ";
		}
		return keyword + "-" + result;
	}
}
