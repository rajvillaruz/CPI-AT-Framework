package org.cpiatframework.util;

import java.io.IOException;
import java.util.Arrays;

import org.cpiatframework.library.TestKeyword;

public class Test {
	public String keyword;
	public String elementKey;
	public String property;
	public String value;
	public String expected;
	public String ipAddress;
	public String project;
	public String folderDate;
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
		
		System.out.println("ACTION: " + keyword + "result: " + result);
		
		if (expected != null){
			Expected eu;			
			String[] expectedLine = null;
			if (expected.contains("\n")) {
				System.out.println("new line here");
				expectedLine = expected.split("\n");
				System.out.println(expectedLine.length);
			}
			
			if (expectedLine == null) {
				if (expected.contains("(")) {
					result = result + multiplePropVal(expected);
				} else {
					String[] exp = expected.split(",");
					int size = exp.length;
					switch (size){
						case 1:
							eu = new Expected(expected);
							eu.setBrowser(browser[0]);
							eu.setProject(project);
							eu.setFolderDate(folderDate);
							result = result + "~" + eu.callMethod();
							break;
						case 3:
							eu = new Expected(exp[0], exp[1], exp[2]);		
							eu.setBrowser(browser[0]);
							eu.setProject(project);
							eu.setFolderDate(folderDate);
							result = result + "~" + eu.callMethod();
							break;
						case 4:
							eu = new Expected(exp[0], exp[1], exp[2], exp[3]);
							eu.setBrowser(browser[0]);
							eu.setProject(project);
							eu.setFolderDate(folderDate);
							result = result + "~" + eu.callMethod();
							break;
						default:
							break;
					}
				}
			} else {
				result = result + "~";
				for (String line : expectedLine) {
					if (line.contains("(")) {
						result = multiplePropVal(line);
						System.out.println(line);
					} else {
						System.out.println(line);
						String[] exp = line.split(",");
						int size = exp.length;
						switch (size){
							case 1:
								eu = new Expected(line);
								eu.setBrowser(browser[0]);
								eu.setProject(project);
								eu.setFolderDate(folderDate);
								result = result + "\n" + eu.callMethod();
								break;
							case 3:
								eu = new Expected(exp[0], exp[1], exp[2]);		
								eu.setBrowser(browser[0]);
								eu.setProject(project);
								eu.setFolderDate(folderDate);
								result = result + "\n" + eu.callMethod();
								break;
							case 4:
								eu = new Expected(exp[0], exp[1], exp[2], exp[3]);
								eu.setBrowser(browser[0]);
								eu.setProject(project);
								eu.setFolderDate(folderDate);
								result = result + "\n" + eu.callMethod();
								break;
							default:
								break;
						}
						System.out.println("expected per line:" + result);
					}
				}
			}
			
		} else {
			result = result + "~ ";
		}
		
		if(property == null){
			property = " ";
		}
		
		return keyword + " " + property + "~" + result;
	}

	private String multiplePropVal(String expected) throws IOException {
		Expected eu;
		String result = "";
		result = result + "~";
		int idxOfOpen = expected.indexOf("(") + 1;
		int idxOfClose = expected.indexOf(")");
		String[] propsValue = null;
		String[] action = expected.substring(0, idxOfOpen - 2).concat(expected.substring(idxOfClose + 1)).split(",");
		if (expected.contains("{")) {
			String[] pair = expected.substring(idxOfOpen, idxOfClose).split("[{]");
			for (int i=0; i < pair.length; i++) {			
				if (!pair[i].isEmpty()) {
					propsValue = pair[i].split(",");
					System.out.println("inside if " + propsValue[0]);
					System.out.println("prop[1] " + propsValue[1].substring(0,propsValue[1].length() - 1));
					eu = new Expected(action[0], action[1], propsValue[0], propsValue[1].substring(0,propsValue[1].length() - 1));		
					result = result + expectedMethodCaller(eu);
					System.out.println("expected" + result);
				}
			}
		} else {
			System.out.println("prior result: " + result);
			propsValue = expected.substring(idxOfOpen, idxOfClose).split(",");
			for (String prop : propsValue) {
				eu = new Expected(action[0], action[1], prop);		
				result = result + expectedMethodCaller(eu);
				System.out.println("expected" + result);
			}
		}
		
		return result;
	}
	
	private String expectedMethodCaller(Expected eu) throws IOException {
		String result = "";
		eu.setBrowser(browser[0]);
		eu.setProject(project);
		eu.setFolderDate(folderDate);
		result = "\n" + eu.callMethod() + "\n";
		return result;
	}
}