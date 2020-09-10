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
import static org.servlets.garits.Documents.CreateStockReport.createCell;
import static org.servlets.garits.Documents.CreateStockReport.getLineItemTable;
import org.servlets.garits.StockControl.Payments.SparePartPayment;
import org.servlets.garits.StockControl.SparePart;

/**
 *
 * @author glaxo
 */
public class StockInvoice {

    private SparePartPayment payment;
    private File file;

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

    public static Table generateInvoice(ArrayList<SparePart> sp, PdfFont bold) {
        Table table = new Table(
                new UnitValue[]{
                    new UnitValue(UnitValue.PERCENT, 40f),
                    new UnitValue(UnitValue.PERCENT, 20f),
                    new UnitValue(UnitValue.PERCENT, 40f),})
                .setWidthPercent(100)
                .setMarginTop(10).setMarginBottom(10).setMarginRight(10);
        table.addHeaderCell(createCell("Part Description", bold));
        table.addHeaderCell(createCell("Quantity", bold));
        table.addHeaderCell(createCell("UnitCost", bold));

        //float vatValue = 0;
        for (SparePart s : sp) {
            table.addCell(createCell(s.getStock().getItemDescription()));
            table.addCell(createCell(s.getQuantity() + ""));
            table.addCell(createCell(s.getStock().getUnitCost() + " £"));
        }

        return table;

    }

    public void CreatePdf() throws SQLException {

        DocumentsDAO dao = new DocumentsDAO();
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(new FileOutputStream(file));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateStockReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Creating a PdfDocument       
        PdfDocument pdfDoc = new PdfDocument(writer);

        PdfFont bold = null;

        try {
            bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD, true);
        } catch (IOException ex) {
            Logger.getLogger(CreateStockReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Adding a new page 
        pdfDoc.addNewPage();

        // Creating a Document
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDoc);
        //Adding Report number
        document.add(
                new Paragraph()
                .setTextAlignment(TextAlignment.CENTER)
                .setMultipliedLeading(1)
                .add(new Text(String.format("Spare Part Invoice No.: %s\n", 1))
                        .setFontSize(14).setFont(bold))
                .add(""));
        document.add(new Paragraph().setTextAlignment(TextAlignment.LEFT).setMultipliedLeading(1).add(new Text("Quick Fix Fitters,\n").setFontSize(14))
        .add(new Text("19 High St.,\n")).setFontSize(14).add(new Text("Ashford,\n")).add(new Text("Kent CT16 8YY\n")).add(new Text("\n")));
        
        document.add(new Paragraph().setTextAlignment(TextAlignment.LEFT).add(new Text(payment.getPaymentDate().toString() +"\n")));

        document.add(generateInvoice(payment.getSp(), bold));

        //Ending
        document.add(new Paragraph()
                .add(new Text("Total Excluding VAT: " + payment.TotalExcludingVat()+ " £\n"))
                .add(new Text(" VAT: " + payment.VAT()+ " £\n"))
                .add(new Text("Total Including VAT: " + payment.calculateTotal()+ " £\n"))
        );
        document.close();
    }

    public StockInvoice(SparePartPayment payment, File file) {
        this.payment = payment;
        this.file = file;
    }

}
