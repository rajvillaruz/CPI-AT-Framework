package org.cpiatframework.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpiatframework.config.Constants;
import org.cpiatframework.util.Zip;

@WebServlet("/ResultController")
public class ResultController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String download = request.getParameter("download");
		String folderName = request.getParameter("folderName");
		String name = folderName + ".zip";
		String path = Constants.PATH_DOWNLOAD + File.separator + folderName;
		
//		File file = new File(path+File.separator+name);
		File allFiles = new File(path);
		
		
		if(download.equalsIgnoreCase("all")){
			try{
				String output_zip_file = path+File.separator+name;
				String source_folder = path;
				Zip zipFile = new Zip(output_zip_file, source_folder);
				zipFile.generateFileList(new File(source_folder));
				System.out.println(output_zip_file);
				zipFile.zipIt(output_zip_file);				
				File downloadAll = new File(output_zip_file);
				
				ServletContext ctx = getServletContext();
				InputStream fis = new FileInputStream(downloadAll);
				String mimeType = ctx.getMimeType(downloadAll.getAbsolutePath());
				response.setContentType(mimeType != null? mimeType:"application/octet-stream");
				response.setContentLength((int) downloadAll.length());
				response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
				response.setHeader("Content-Length", String.valueOf(downloadAll.length()));
				ServletOutputStream os = response.getOutputStream();
				
//				byte[] bufferData = new byte[10 * 65536];
				byte[] bufferData = new byte[2048];
				int read=0;
				while((read = fis.read(bufferData))!= -1){
					os.write(bufferData, 0, read);
					os.flush();
					response.flushBuffer();
				}
				//os.flush();
				os.close();
				fis.close();
			}catch (Exception ex){
				ex.printStackTrace();
			} finally {
				deleteDir(allFiles);
				System.out.println("Deleted");
			}
		}
		
	 }

	 public void deleteDir(File file) {
		 Boolean isDeleted = false;
		    File[] contents = file.listFiles();
		    if (contents != null) {
		        for (File f : contents) {
		        	isDeleted = f.delete();
		        	System.out.println(f.toString() + isDeleted);
		            deleteDir(f);
		        }
		    }
		    file.delete();
	}

}
