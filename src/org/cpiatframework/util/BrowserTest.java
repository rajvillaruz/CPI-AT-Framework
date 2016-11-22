package org.cpiatframework.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class BrowserTest {
	private String[][] browsers;
	private File file;
	private String ipAddress;
	private String project;
	private String folderDate;
	
	public BrowserTest(String[][] browsers, File file, String ipAddress, String project, String folderDate) {
		this.browsers = browsers;
		this.file = file;
		this.ipAddress = ipAddress;
		this.project = project;
		this.folderDate = folderDate;
	}
	
	public List<String> testcase(String filePath) throws EncryptedDocumentException, FileNotFoundException, InvalidFormatException, IOException {
		System.out.println(file.getName());
		List<String> crossBrowserResult = new ArrayList<String>();
		System.out.println(browsers.length);
		for (int i = 0; i < browsers.length; i++) {
			String[] browser = browsers[i];
			Excel newFile = new Excel(filePath, file);
			List<List<List<String>>> testSuite = newFile.read();
			for (List<List<String>> testCase : testSuite){
				String testCaseResult = testActions(testCase, browser);
				if (testCaseResult == null) {
					return null;
				} else {
					crossBrowserResult.add(testCaseResult);
				}
				
			}
		}
		return crossBrowserResult;
	}
	
	private String testActions(List<List<String>> testCase, String[] browser) throws IOException {
		String testCaseResult = "";
		for (List<String> steps : testCase) {
			String stepResult = "";
			int stepSize = steps.size();
			Test test;
			switch (stepSize) {
			case 1:
				test = new Test(steps.get(0));
				test.setBrowser(browser);
				test.setIpAddress(ipAddress);
				stepResult = test.callMethod();
				break;
			case 2:
				test = new Test(steps.get(0), steps.get(1));
				test.setProject(project);
				test.setFolderDate(folderDate);
				stepResult = test.callMethod();
				break;
			case 3:
				test = new Test(steps.get(0), steps.get(1), steps.get(2));
				test.setProject(project);
				test.setFolderDate(folderDate);
				stepResult = test.callMethod();
				break;
			case 4:
				test = new Test(steps.get(0), steps.get(1), steps.get(2), steps.get(3));
				test.setProject(project);
				test.setFolderDate(folderDate);
				stepResult = test.callMethod();
				break;
			case 5:
				test = new Test(steps.get(0), steps.get(1), steps.get(2), steps.get(3), steps.get(4));
				test.setBrowser(browser);
				test.setProject(project);
				test.setFolderDate(folderDate);
				stepResult = test.callMethod();
				break;
			default:
				break;
			}
			
			if (stepResult == null) {
				return null;
			}
			
			if (stepResult.equalsIgnoreCase("failed session")) {
				return "failed session";
			}
			testCaseResult = testCaseResult.concat(stepResult + ",");
		}
		
		return "BROWSER: " + browser[0] + "\n" + testCaseResult;
	}
}
