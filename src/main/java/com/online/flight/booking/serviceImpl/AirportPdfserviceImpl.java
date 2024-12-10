package com.online.flight.booking.serviceImpl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.EncoderUtil;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.online.flight.booking.entity.Airport;
import com.online.flight.booking.service.AirportPdfService;

@Service
//public class AirportPdfserviceImpl extends PdfPageEventHelper implements AirportPdfService {
public class AirportPdfserviceImpl implements AirportPdfService {

	
//	private final Phrase watermarkPhrase;
	
	
	

	
//public AirportPdfserviceImpl(Phrase watermarkPhrase) {
//		this.watermarkPhrase = watermarkPhrase;
//	}
//
//public void onEndPage(PdfWriter writer, Document document) {
//    PdfContentByte canvas = writer.getDirectContentUnder();
//    ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
//            watermarkPhrase,
//            document.getPageSize().getWidth() / 2,
//            document.getPageSize().getHeight() / 2,
//            45); // Rotate 45 degrees
//}

//}

public byte[] generateInvoiceReport(List<Airport> airport) {
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		try
		{
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			
			
			   //Add Watermark as Background
			
			  PdfContentByte canvas = writer.getDirectContentUnder();
			  Font waterMarkFont = new Font(Font.HELVETICA,50,Font.BOLD,Color.LIGHT_GRAY);
			  Phrase waterMarkPhrase = new Phrase("AirPort Data",waterMarkFont);
			  
			  
			  ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, waterMarkPhrase,
					  document.getPageSize().getWidth()/2, 
					  document.getPageSize().getHeight()/2, 45);
			
			   // Create watermark phrase
//	        Font watermarkFont = new Font(Font.HELVETICA, 50, Font.BOLD, Color.LIGHT_GRAY);
//	        Phrase watermarkPhrase = new Phrase("Airport Data", watermarkFont);
//	        
//	        // Set watermark as a page event
//	        writer.setPageEvent(new AirportPdfserviceImpl(watermarkPhrase));
			  
			
			
			PdfPTable table2 = new PdfPTable(3);
			table2.setWidthPercentage(100);
			table2.setSpacingBefore(10);
			table2.setSpacingAfter(10);
			
			
			table2.setWidths(new float[] {1,4,2});
			
			
			//Logo cell
			
			Image logo = Image.getInstance("src/main/resources/Image/Logo.png");
			logo.scaleToFit(50, 50);
			PdfPCell logoCell = new PdfPCell(logo,true);
			logoCell.setBorder(Rectangle.NO_BORDER);
			logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table2.addCell(logoCell);
			
			
			//Title
			Font titleFont = new Font(Font.HELVETICA,14,Font.BOLD);
			PdfPCell titleCell = new PdfPCell();
			titleCell.setBorder(Rectangle.NO_BORDER);
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			titleCell.setPhrase(new Phrase("Airport Data", titleFont));
			table2.addCell(titleCell);
			
			//Date Cell
			
			Font dateFont = new Font(Font.HELVETICA,10,Font.NORMAL);
			PdfPCell dateCell = new PdfPCell();
			dateCell.setBorder(Rectangle.NO_BORDER);
			dateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			dateCell.setPhrase(new Phrase(new Date().toString(),dateFont));
			table2.addCell(dateCell);
			
			document.add(table2);
			
			
			
			//Table
			
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10);
			table.setSpacingAfter(10);
			
			
			
			Font headerFont = new Font(Font.HELVETICA,12,Font.BOLD,Color.white);
			PdfPCell headerCell; 
			
			
			String[] headers = {"ID","Name","Country","State","City","Address"};
			
			for(String header : headers)
			{
				headerCell = new PdfPCell(new Phrase(header,headerFont));
				headerCell.setBackgroundColor(new Color(0,102,204));
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setPadding(8);
				table.addCell(headerCell);
			}
			
			
			
			// Table Data with alternating row colors
			Font dataFont = new Font(Font.HELVETICA,10,Font.NORMAL,Color.BLACK);
			boolean alternateRow = false;
			
			for(Airport port : airport)
			{
				String[] rowData = {
						port.getId().toString(),
						port.getName(),
						port.getCountry(),
						port.getState(),
						port.getCity(),
						port.getAddress()
				};
				
				
				for(String data : rowData)
				{
					PdfPCell datacell = new PdfPCell(new Phrase(data,dataFont));
					datacell.setBackgroundColor(alternateRow ? new Color(230,230,230) : Color.WHITE);
					datacell.setPadding(6);
					datacell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(datacell);
				}
				
				alternateRow = !alternateRow;
				
			}
			
			document.add(table);
			document.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return outputStream.toByteArray();
	}




public static void generateAirportreport(ByteArrayOutputStream outputStream, List<Airport> airport) throws Exception{
	Document document = new Document();
	PdfWriter.getInstance(document, outputStream);
	document.open();
	
	document.add(new Paragraph("Airport Data"));
	
	JFreeChart chart = GraphUtil.generateGraph(airport);
	BufferedImage bufferedImage = chart.createBufferedImage(500, 400);
	
	ByteArrayOutputStream chartOutputStream = new ByteArrayOutputStream();
	EncoderUtil.writeBufferedImage(bufferedImage, "png", chartOutputStream);
	
	Image chartImage = Image.getInstance(chartOutputStream.toByteArray());
	document.add(chartImage);
	
	
	document.close();
	
}



	
}	

