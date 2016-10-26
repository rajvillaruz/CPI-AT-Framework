package org.cpiatframework.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.cpiatframework.config.Constants;
import org.cpiatframework.util.BrowserTest;
import org.cpiatframework.util.Excel;
import org.cpiatframework.util.Upload;

@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;
	private String page;
	private String ipAddress = "";
	private List<String> crossBrowserResult = new ArrayList<String>();
	private String[][] browsers = {
				{"Firefox", Constants.FIREFOX_DRIVER, Constants.BROWSER_PROPERTY_FIREFOX},
				{"Chrome", Constants.CHROME_DRIVER, Constants.BROWSER_PROPERTY_CHROME}
			};
	private String folderDate = "";
	
	public void init( ){
		filePath = Constants.PATH_UPLOAD;
	}
         
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		page = "view/error.jsp";
		String action = request.getParameter("action");
		RequestDispatcher dispatcher = null;
		if(action.equals("execute")){
			boolean isMultipart;
			isMultipart = ServletFileUpload.isMultipartContent(request);
			if( !isMultipart ){
				page = "view/error.jsp";
			} else {
				page = "view/result.jsp";
				String project = request.getParameter("projectName");
				String qa = request.getParameter("qaName");
				ipAddress = request.getParameter("ipaddress");
				SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy hh.mm.ss a");
				folderDate = ft.format(new Date());
				Upload fileUpload = new Upload(request, filePath);
				BrowserTest browserTest = new BrowserTest(browsers, fileUpload.uploadFile(), ipAddress, project, folderDate);
				try {
					crossBrowserResult = browserTest.testcase(filePath);
				} catch (EncryptedDocumentException e) {
					e.printStackTrace();
				} catch (InvalidFormatException e) {
					e.printStackTrace();
				}
//				try {
//					savePDF();
//				} catch (DocumentException e) {
//					e.printStackTrace();
//				}
			}
		}
		dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
