package org.cpiatframework.util;

import java.io.IOException;

import org.cpiatframework.library.ExpectedKeyword;

public class Expected {
	private String keyword = "";
	private String elementKey = "";
	private String property = "";
	private String value = "";
	private String browser = "";
	private String project = "";
	private String folderDate = "";
	
	public Expected(String keyword) {
		this.keyword = keyword;
	}
	
	public Expected(String keyword, String elementKey) {
		this.keyword = keyword;
		this.elementKey = elementKey;
	}
	
	public Expected(String keyword, String elementKey, String property) {
		this.keyword = keyword;
		this.elementKey = elementKey;
		this.property = property;
	}
	
	public Expected(String keyword, String elementKey, String property, String value) {
		this.keyword = keyword;
		this.elementKey = elementKey;
		this.property = property;
		this.value = value;
	}

	public String getFolderDate() {
		return folderDate;
	}

	public void setFolderDate(String folderDate) {
		this.folderDate = folderDate;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	public String callMethod() throws IOException {
		String result = "";
		ExpectedKeyword.browser = browser;
		ExpectedKeyword.project = project;
		ExpectedKeyword.folderDate = folderDate;
		System.out.println(ExpectedKeyword.browser);
		switch (keyword.toUpperCase()) {
		case "ELEMENT SHOULD BE VISIBLE":
			result = ExpectedKeyword.elementIsVisible(keyword, elementKey, property);
			break;
		case "ELEMENT SHOULD NOT BE VISIBLE":
			result = ExpectedKeyword.elementIsNotVisible(keyword, elementKey, property);
			break;
		case "ELEMENT SHOULD BE SELECTED":
			result = ExpectedKeyword.elementIsSelected(keyword, elementKey, property);
			break;
		case "ELEMENT SHOULD NOT BE SELECTED":
			result = ExpectedKeyword.elementIsNotSelected(keyword, elementKey, property);
			break;
		case "ELEMENT SHOULD BE ENABLED":
			result = ExpectedKeyword.elementIsEnabled(keyword, elementKey, property);
			break;
		case "ELEMENT SHOULD BE DISABLED":
			result = ExpectedKeyword.elementIsDisabled(keyword, elementKey, property);
			break;
		default:
			break;
		}
		
		
		return keyword + " \n" + result;
	}
}
