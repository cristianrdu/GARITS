/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.Documents;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.servlets.garits.DAO.DocumentsDAO;

/**
 *
 * @author balac
 */
public class CreateStockReport{
    
    
    private File file;
//
    public static Cell createCell(String text) {
        return new Cell().setPadding(0.8f)
            .add(new Paragraph(text)
                .setMultipliedLeading(1));
    }
    public static Cell createCell(String text, PdfFont font) {
        return new Cell().setPadding(0.8f)
            .add(new Paragraph(text)
                .setFont(font).setMultipliedLeading(1));
    }

   public static Table getLineItemTable( ArrayList<StockLevelReport> ss, PdfFont bold) {
            Table table = new Table(
                new UnitValue[]{
                    new UnitValue(UnitValue.PERCENT, 10f),
                    new UnitValue(UnitValue.PERCENT, 10f),
                    new UnitValue(UnitValue.PERCENT, 0.15f),
                    new UnitValue(UnitValue.PERCENT, 0.15f),
                    new UnitValue(UnitValue.PERCENT, 0.15f),
                    new UnitValue(UnitValue.PERCENT, 0.15f),
                    new UnitValue(UnitValue.PERCENT, 0.15f),
                    new UnitValue(UnitValue.PERCENT, 0.15f),
                    new UnitValue(UnitValue.PERCENT, 0.15f),
                    new UnitValue(UnitValue.PERCENT, 0.15f),
                    new UnitValue(UnitValue.PERCENT, 0.15f),
                    new UnitValue(UnitValue.PERCENT, 0.15f),
                    new UnitValue(UnitValue.PERCENT, 0.15f),
                    new UnitValue(UnitValue.PERCENT, 0.15f)})
                .setWidthPercent(100)
            .setMarginTop(2).setMarginBottom(2).setMarginRight(10).setMarginLeft(5);
                table.addHeaderCell(createCell("Part Name", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("Code", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("Manufacturer", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("Vehicle Type", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("Year(s)", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("Price", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("Initial Stock level", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("Initial cost, £", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("Used", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("Delivery", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("New Stock level", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("Stock cost, £", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("Low level", bold).setRotationAngle(1.57));
                table.addHeaderCell(createCell("threshold", bold).setRotationAngle(1.57));
                for(StockLevelReport s : ss) {
                    table.addCell(createCell(s.getPartName()));
                    table.addCell(createCell(s.getCode()+""));
                    table.addCell(createCell(s.getManufacturer()+""));
                    table.addCell(createCell(s.getVehicleType()+""));
                    table.addCell(createCell(s.getYear()+""));
                    table.addCell(createCell(s.getPrice()+""));
                    table.addCell(createCell(s.getInitialStockLevel()+""));
                    table.addCell(createCell(s.getInitialCost()+""));
                    table.addCell(createCell(s.getUsed()+""));
                    table.addCell(createCell(s.getDelivery()+""));
                    table.addCell(createCell(s.getNewStockLevel()+""));
                    table.addCell(createCell(s.getStockCost()+""));
                    table.addCell(createCell(s.getLowLevel()+""));
                    table.addCell(createCell(s.getThreshold()+""));
    //                float markUpSpares = part.getPrice()*part.getQty();
    //                table.addCell(createCell(invoice.calcMarkUpSpares(markUpSpares)+""));
    //                totalDue += invoice.calcMarkUpSpares(markUpSpares);
                }
                table.setFontSize(10);
                return table;


    }

        public void CreatePdf() throws SQLException {
        System.out.println("test");

        DocumentsDAO dao= new DocumentsDAO();
        String dest = "C:/Users/balac/OneDrive/Documents/GitHub/GARITS-2.0/GARITS/src/main/resources/name.pdf";
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(new FileOutputStream(dest));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateStockReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);

        PdfFont bold = null;

      try {
            bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD,true);
        } catch (IOException ex) {
            Logger.getLogger(CreateStockReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Adding a new page
        pdfDoc.addNewPage();

        // Creating a Document
        Document document = new Document(pdfDoc);
        //Adding Report number
        document.add(
                new Paragraph()
                        .setTextAlignment(TextAlignment.RIGHT)
                        .setMultipliedLeading(1)
                        .add(new Text(String.format("REPORT NO.: %s\n", 1))
                                .setFontSize(14))
                        .add(""));

        try {


            document.add(getLineItemTable(dao.getStockLevelReportOnDemand(), bold));
        } catch (SQLException ex) {
            Logger.getLogger(CreateStockReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Ending
        document.add(new Paragraph()
                .add(new Text("Thank you for your valued custom."
                        + "We look forward to receiving your payment"
                        + "in due course.\n"))
                .add(new Text("\n"))
                .add(new Text("Yours sincerely,\n"))
                .add(new Text("\n"))
                .add(new Text("G. Lancaster"))
        );
                document.close();
}

    public CreateStockReport() {
        
    }


}
