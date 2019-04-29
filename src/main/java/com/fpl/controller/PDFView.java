package com.fpl.controller;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.fpl.dao.PlayerDao;
import com.fpl.dao.UserDao;
import com.fpl.dao.WeekDao;
import com.fpl.pojo.Player;
import com.fpl.pojo.Week;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;  

public class PDFView extends AbstractPdfView {
	
	@Autowired
	PlayerDao playerDao;
	
	@Autowired
	WeekDao weekDao;
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter pdfwriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Font  helvetica_18_blue = new Font(Font.HELVETICA, 18, Font.BOLDITALIC, Color.BLUE);
		
		
		Paragraph title = new Paragraph("Points Charts", helvetica_18_blue);
		
		Phrase phraseTitle = new Phrase("Find out the points below, Top 30 Players in PL");
		
		
		PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);
		
		PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(5);
		
        cell.setPhrase(new Phrase("Player", helvetica_18_blue));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Points", helvetica_18_blue));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Price", helvetica_18_blue));
        table.addCell(cell);
        
        Week w=(Week) model.get("week");
        List<Player> players= (List<Player>) model.get("playerList");
        System.out.println(w);
        System.out.println(players.get(0).getPoints());
        for (Player player : players){
            table.addCell(String.valueOf(player.getName()));
            for(Week week: player.getPoints().keySet()) {
            	if(week.getWeekId()==w.getWeekId()) {
                    table.addCell(String.valueOf(player.getPoints().get(week)));
                    break;
            	}
            }
            table.addCell(String.valueOf(player.getPrice()));
        }
        
		pdfdoc.add(title);
		pdfdoc.add(phraseTitle);
		pdfdoc.add(table);
	}

	
}
