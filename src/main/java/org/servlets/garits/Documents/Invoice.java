package org.servlets.garits.Documents;


import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.UnitValue;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.servlets.garits.DAO.DocumentsDAO;
import org.servlets.garits.Job.*;
import org.servlets.garits.StockControl.SparePart;

public class Invoice extends Document {

	private int labourRate; //labour rate for the mechanic
	private double tradePrice; 
	private double VAT;
	private boolean status;
        
        private Job job; //the Job whose invoice is currently being generated
        
        private DocumentsDAO ddao; //Documents data access object used for database queries where necessary



	/**
	 * 
     * @param storeDest
	 * @param labourRate
	 * @param tradePrice
	 * @param VAT
	 * @param status 
     * @throws java.io.FileNotFoundException 
	 */
	public void generateInvoice(String storeDest, int labourRate, double tradePrice, double VAT, boolean status) throws FileNotFoundException {
		String dest = job.getTasks().get(0).getTaskID() + ".pdf";       
        PdfWriter writer = null; 
        try {
            writer = new PdfWriter(dest);
        } catch (FileNotFoundException ex) {
            throw ex;
        }

        // Create a PdfDocument       
        PdfDocument pdfDoc = new PdfDocument(writer); 
        
        //Create a bold variable to be used in cases where we want the text in bold
        PdfFont bold = null;
        
        }
	public void storeInvoice() {
		// TODO - implement Invoice.storeInvoice
		throw new UnsupportedOperationException();
	}
        
        //creates a new cell with the given text to be used later in the pdf generation
    public static Cell createCell(String text) {
        return new Cell().setPadding(0.8f)
            .add(new Paragraph(text)
                .setMultipliedLeading(1));
    }
    
    //a modification of the previous method. This one creates a cell, but uses the provided font for styling.
    public static Cell createCell(String text, PdfFont font) {
        return new Cell().setPadding(0.8f)
            .add(new Paragraph(text)
                .setFont(font).setMultipliedLeading(1));
    }
    
    //creates a new table for with the customer's address
    public static Table getAddressTable(String name, String address, String 
            postcode, PdfFont bold) {
        Table table = new Table(new UnitValue[]{
            new UnitValue(UnitValue.PERCENT, 50),
            new UnitValue(UnitValue.PERCENT, 50)})
                .setWidthPercent(100);
        table.addCell(getPartyAddress("Client:", //add a new cell with the client's information in it
            name, address, postcode, bold));
        table.addCell(getPartyAddress("Seller:", //as well as a cell with the return address for the purposes of mail
                "Quick Fix Fitters", "19 High St. Ashford,", "Kent CT16 8YY",
            bold));
        return table;
    }
    
    //create the cell with the customer's data
    public static Cell getPartyAddress(String who, String name, String line1, String line2, PdfFont bold) {
    	Paragraph p;
            p = new Paragraph()
                    .setMultipliedLeading(1.0f)
                    .add(new Text(who).setFont(bold)).add("\n")
                    .add(name).add("\n")
                    .add(line1).add("\n")
                    .add(line2).add("\n");
        Cell cell = new Cell()
        		.setBorder(Border.NO_BORDER) //delete the border, as we want to use the table only for formatting purposes
        		.add(p);
        return cell;
    }
    
    

    /**
     *
     * @param partsUsed
     * @param bold
     * @return Table
     */
    //creates the table which shows the parts used, their respective cost, as well as the price
    public Table getLineItemTable(ArrayList<SparePart>
            partsUsed, PdfFont bold) {
        Table table = new Table(
            new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 34f),
                new UnitValue(UnitValue.PERCENT, 30f),
                new UnitValue(UnitValue.PERCENT, 12f),
                new UnitValue(UnitValue.PERCENT, 12f),
                new UnitValue(UnitValue.PERCENT, 12f)})
            .setWidthPercent(100)
        .setMarginTop(10).setMarginBottom(10);
            table.addHeaderCell(createCell("Item:", bold)); //create the table's column descriptions
            table.addHeaderCell(createCell("Part No:", bold));
            table.addHeaderCell(createCell("Unit Cost:", bold));
            table.addHeaderCell(createCell("Qty:", bold));
            table.addHeaderCell(createCell("Cost:", bold));
            float totalDue = calculateLabourCost();
            float vatValue = 0;
            for(SparePart part : partsUsed) { //populate the table
                table.addCell(createCell(part.getStock().getItemDescription()));
                table.addCell(createCell(part.getStock().getCode()+""));
                table.addCell(createCell(part.getStock().getUnitCost()+""));
                table.addCell(createCell(part.getQuantity()+""));
                float markUpSpares = (float) (part.getTotalCost()*part.getQuantity());
                /*table.addCell(createCell(invoice.calcMarkUpSpares(markUpSpares)+""));*/
                totalDue += markUpSpares;
            }
            //WHITESPACE
            table.addCell(createCell("\n")); //add newlines for formatting purposes
            table.addCell(createCell("\n"));
            table.addCell(createCell("\n"));
            table.addCell(createCell("\n"));
            table.addCell(createCell("\n"));

            table.addCell(createCell("Labour")); //describe amount of labour done on the job
            table.addCell(createCell(""));
            table.addCell(createCell(job.getAssignedMechanic().getHourlyRate()+"")); //get the mechanic's hourly rate
            table.addCell(createCell(getJobDuration()+"")); //get the amount of time that was needed to complete the job
            table.addCell(createCell(calculateLabourCost()+"")); //calculate the labour cost
            
            table.addCell(createCell(""));
            table.addCell(createCell(""));
            table.addCell(createCell("Total")); //display the total outstanding balance
            table.addCell(createCell(""));
            table.addCell(createCell(totalDue+""));
            
            vatValue = (float) (0.2 * totalDue);
            table.addCell(createCell(""));
            table.addCell(createCell(""));
            table.addCell(createCell("VAT"));
            table.addCell(createCell(""));
            table.addCell(createCell(vatValue+"")); //display the VAT

            table.addCell(createCell(""));
            table.addCell(createCell(""));
            table.addCell(createCell("Grand Total"));
            table.addCell(createCell(""));
            table.addCell(createCell(totalDue +"")); //display the grand total, which is the total due plus VAT
        return table;
    }
    
    //helper function used to calculate the labour cost for the current job
    public float calculateLabourCost(){
        float timeSpent = 0f;
        double mechanicWage = job.getAssignedMechanic().getHourlyRate();
        ArrayList<String> hoursWorked = new ArrayList(); //an array list is required as there might be more than one task for a particular job
        hoursWorked = ddao.getHoursWorked(job);//do a DB query to get the amount of time spent on each task
        for(String str: hoursWorked){ //for each task, add the amount spent to the timeSpent variable
            String hours = str.split(" ")[1];
            int minutesWorked = Integer.parseInt(hours.split(":")[0]) * 60 + Integer.parseInt(hours.split(":")[1]);
            timeSpent += minutesWorked / 60; //use the amount of time spent in hours, as mechanics are paid hourly
        }
        
        return (float) (timeSpent * mechanicWage); //return the labour cost
    }
    
    //get the duration of a particular job
    public float getJobDuration(){
        float timeSpent = 0f;
        
        ArrayList<String> hoursWorked = new ArrayList();
        hoursWorked = ddao.getHoursWorked(job); //similar to the method calculateLabourCost()
        for(String str: hoursWorked){
            String hours = str.split(" ")[1];
            //convert the timestamp to hours and minutes and add them to the amount of minutes worked.
            int minutesWorked = Integer.parseInt(hours.split(":")[0]) * 60 + Integer.parseInt(hours.split(":")[1]);
            timeSpent += minutesWorked / 60;
        }
        
        return timeSpent; //return the amount of time spent on the current job
    }
    
   
	/**
	 * 
	 * @param job 
         * @throws java.sql.SQLException 
	 */
	public Invoice(Job job) throws SQLException {
		this.job = job;
                ddao = new DocumentsDAO();
	}

}