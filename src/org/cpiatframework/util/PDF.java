package org.cpiatframework.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.common.io.Files;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PDF {
	private File pdfFile;
	private String project;
	private String qa;
	private List<String> crossBrowserResult;
	
	public PDF(File pdfPath, String project, String qa, List<String> crossBrowserResult) {
		this.pdfFile = pdfPath;
		this.project = project;
		this.qa = qa;
		this.crossBrowserResult = crossBrowserResult;
	}
	
	public void writePDF() throws DocumentException, IOException {
		Date date = new Date();		
		Document document = new Document(PageSize.A4, 36, 36, 120, 54);
		System.out.println("writing");
		Files.createParentDirs(pdfFile);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile)); 
		document.open();
		//header
		Rectangle headerBox = new Rectangle(36, 54, 559, 788);
		HeaderFooter event = new HeaderFooter();
		writer.setBoxSize("headerBox", headerBox);
		writer.setPageEvent(event);

		Paragraph preface = new Paragraph();
		preface.add(new Paragraph("Project: " + project.toUpperCase(), FontFactory.getFont(FontFactory.TIMES,18, Font.BOLDITALIC, BaseColor.BLACK)));
		
		SimpleDateFormat ft2 = new SimpleDateFormat ("E MM/dd/yyyy 'at' hh:mm:ss a");

		preface.add(new Paragraph( "Report generated by: " + qa + ", " + ft2.format(date), FontFactory.getFont(FontFactory.TIMES,14, Font.BOLD, BaseColor.BLACK)));
		addEmptyLine(preface, 1);
		Paragraph someSectionText = new Paragraph("Results:");
		preface.add(someSectionText);
		document.add(preface);
	    try{
	    	for(String finalResult : crossBrowserResult) { 				
	    		PdfPTable t = new PdfPTable(3);
				t.setSpacingBefore(15);
				t.setSpacingAfter(25);
				
			    PdfPCell c1 = new PdfPCell(new Phrase("STEPS"));
			    c1.setBackgroundColor(new CMYKColor(0, 1, 0, 24));
			    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    t.addCell(c1);
			    PdfPCell c2 = new PdfPCell(new Phrase("RESULT"));
			    c2.setBackgroundColor(new CMYKColor(0, 1, 0, 24));
			    c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    t.addCell(c2);
			    PdfPCell c3 = new PdfPCell(new Phrase("REMARKS"));
			    c3.setBackgroundColor(new CMYKColor(0, 1, 0, 24));
			    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			    t.addCell(c3);
				String[] lines = finalResult.split(",");
				for(String line: lines){
					String[] cells = line.split("~");
					
					if (cells.length == 3) {
						for(int i=0; i < 3; i++){
							if (cells[i].equals("PASSED")){
								//Paragraph cPassed = new Paragraph(cells[i],FontFactory.getFont(FontFactory.TIMES,14, Font.ITALIC, BaseColor.GREEN));
								PdfPCell cPassed = new PdfPCell(new Paragraph(cells[i],FontFactory.getFont(FontFactory.TIMES,12, Font.NORMAL, BaseColor.BLACK)));
								cPassed.setBackgroundColor(new CMYKColor(19, 1, 18, 17));
								t.addCell(cPassed);
							}else if (cells[i].contains("FAILED")){
								PdfPCell cFailed = new PdfPCell(new Paragraph(cells[i],FontFactory.getFont(FontFactory.TIMES,12, Font.NORMAL, BaseColor.BLACK)));
								cFailed.setBackgroundColor(new CMYKColor(0, 41, 33, 0));
								t.addCell(cFailed);
							} else {
								Paragraph c = new Paragraph(cells[i] ,FontFactory.getFont(FontFactory.TIMES,12, Font.NORMAL, BaseColor.BLACK));
								t.addCell(c);
							}   
						}
					} else {
						for(int i=0; i < cells.length; i++){
							if (cells[i].equals("PASSED")){
								//Paragraph cPassed = new Paragraph(cells[i],FontFactory.getFont(FontFactory.TIMES,12, Font.ITALIC, BaseColor.GREEN));
								//t.addCell(cPassed);
								PdfPCell cPassed = new PdfPCell(new Paragraph(cells[i],FontFactory.getFont(FontFactory.TIMES,12, Font.NORMAL, BaseColor.BLACK)));
								cPassed.setBackgroundColor(new CMYKColor(19, 1, 18, 17));
								t.addCell(cPassed);
							}else if (cells[i].contains("FAILED")){
								PdfPCell cFailed = new PdfPCell(new Paragraph(cells[i],FontFactory.getFont(FontFactory.TIMES,12, Font.NORMAL, BaseColor.BLACK)));
								cFailed.setBackgroundColor(new CMYKColor(0, 41, 33, 0));
								t.addCell(cFailed);
							} else {
								Paragraph c = new Paragraph(cells[i],FontFactory.getFont(FontFactory.TIMES,12, Font.NORMAL, BaseColor.BLACK));
								t.addCell(c);
							}     
						}
						for(int i=0; i < 3 - cells.length; i++){
							t.addCell(" ");   
						}
					}
						
				}
				document.add(t);
				t.flushContent();
			}
			
	    } catch (Exception e){
	    	e.printStackTrace();
	    } finally {
			document.close();
			writer.close();
	    }
	}
	
	static class HeaderFooter extends PdfPageEventHelper {

		  public void onEndPage(PdfWriter writer, Document document) {
		    Rectangle rect = writer.getBoxSize("headerBox");
		    try {
		      Image img = Image.getInstance("C:\\Users\\cpi\\git\\CPI-AT-Framework\\WebContent\\imgs\\cpi_logo.png");
		      img.scaleToFit(100,100);
		      img.setAbsolutePosition(35,742); 
		      writer.getDirectContent().addImage(img);
		    } catch (Exception x) {
		      x.printStackTrace();
		    }

		  }

		}
	private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
                paragraph.add(new Paragraph(" "));
        }
	 }
}