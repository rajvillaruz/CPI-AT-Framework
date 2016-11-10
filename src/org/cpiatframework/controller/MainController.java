package org.cpiatframework.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.cpiatframework.config.Constants;
import org.cpiatframework.util.BrowserTest;
import org.cpiatframework.util.PDF;
import org.cpiatframework.util.Upload;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfName;

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
			System.out.println(!isMultipart);
			if( !isMultipart ){
				System.out.println("isnotmultipart");
				page = "view/error.jsp";
				request.setAttribute("message", "No file uploaded" );
			} else {
				
				String project = request.getParameter("projectName");
				String qa = request.getParameter("qaName");
				ipAddress = request.getParameter("ipaddress");
				SimpleDateFormat ft = new SimpleDateFormat ("MM-dd-yyyy hh.mm.ss a");
				folderDate = ft.format(new Date());
				String testName = project + "-" + folderDate;
				String resultPath = Constants.PATH_DOWNLOAD + File.separator + testName;
				Upload fileUpload = new Upload(request, filePath);
				File excelFile = fileUpload.uploadFile();
				
				request.setAttribute("folderName", testName );
				
				if (excelFile != null) {
					page = "view/result.jsp";
					try {
						BrowserTest browserTest = new BrowserTest(browsers, excelFile, ipAddress, project, folderDate);
						crossBrowserResult = browserTest.testcase(filePath);
						if (crossBrowserResult == null) {
							page = "view/error.jsp";
							request.setAttribute("message", "Error in Test Case" );
						} else {
							String PdfPath = resultPath + File.separator + testName + ".pdf";
							File pdfFile = new File(PdfPath);
							PDF pdf = new PDF(pdfFile, project, qa, crossBrowserResult);
							pdf.writePDF();
							List<File> resultFiles = listf(resultPath);
							for (File file : resultFiles) {
								System.out.println(file.getName());
							}
							request.setAttribute("resultFiles", resultFiles);
						}
					} catch (EncryptedDocumentException e) {
						e.printStackTrace();
					} catch (InvalidFormatException e) {
						e.printStackTrace();
					} catch (DocumentException e) {
						e.printStackTrace();
					}
				} else {
					request.setAttribute("message", "No file uploaded." );
				}
			}
		}
		
		dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public static List<File> listf(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath()));
            }
        }
        //System.out.println(fList);
        return resultList;
    } 
	
}
