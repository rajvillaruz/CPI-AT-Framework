package org.cpiatframework.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpiatframework.config.Constants;
import org.cpiatframework.util.Zip;

/**
 * Servlet implementation class ResultControllerNew
 */
@WebServlet("/ResultControllerNew")
public class ResultControllerNew extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String download = request.getParameter("download");
		String folderName = request.getParameter("folderName");
		String name = folderName + ".zip";
		String path = Constants.PATH_DOWNLOAD + File.separator + folderName;
		File allFiles = new File(path);
		
		if (download.equalsIgnoreCase("all")) {
//			String zipFile = path + File.separator + name;
//			File downloadName = new File(zipFile);
			
			
			String output_zip_file = path+File.separator+name;
			String source_folder = path;
			Zip zipFile = new Zip(output_zip_file, source_folder);
			zipFile.generateFileList(new File(source_folder));
			System.out.println(output_zip_file);
			zipFile.zipIt(output_zip_file);
			
			File downloadAll = new File(output_zip_file);
			
//			FileOutputStream fout = new FileOutputStream(zipFile);
//			ZipOutputStream zout = new ZipOutputStream(fout);
//			List<String> fileList;
//			
//				try {
//					 for (int i = 0; i < files.length; i++) {
//						 byte[] buffer = new byte[1024];
//						 FileInputStream fin = new FileInputStream( zipFile + File.separator + files[i]);
//						 zout.putNextEntry(new ZipEntry(files[i]));
//						 int length;
//						 
//	                     while((length = fin.read(buffer)) > 0){
//	                        zout.write(buffer, 0, length);
//	                     }
//	                     zout.closeEntry();
//	                     fin.close();
//					 }
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					zout.close();
//				}
//				
			
			ServletContext ctx = getServletContext();
			FileInputStream fis = new FileInputStream(downloadAll);
			String mimeType = ctx.getMimeType(downloadAll.getAbsolutePath());
			response.setContentType(mimeType != null? mimeType:"application/octet-stream");
			response.setContentLength((int) downloadAll.length());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");

			PrintWriter os = response.getWriter();
			int read;
			
			try {
				while((read = fis.read()) != -1){
					os.write(read);
				}
				os.flush();
				os.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} 		
	
	}

}
