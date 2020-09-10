/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.servlets.garits.Documents.StockLevelReport;
import org.servlets.garits.Job.Job;
import org.servlets.garits.Job.Task;

/**
 *
 * @author balac
 */
public class DocumentsDAO {

    private Connection connection;

    public DocumentsDAO() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/GARITS", "postgres", "password");
        connection.setAutoCommit(false);
    }

    
    
    public ArrayList<StockLevelReport> getStockLevelReportOnDemand() throws SQLException {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        ArrayList<StockLevelReport> ss = new ArrayList<StockLevelReport>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT STOCK.* , "
                + "(STOCK.initial_item_quantity*STOCK.unit_cost) AS initial_cost, "
                + "(STOCK.actual_item_quantity*STOCK.unit_cost) AS actual_cost , "
                + "manufacturer_name "
                + "FROM public.STOCK INNER JOIN public.MANUFACTURER ON "
                + "STOCK.Manufacturermanufacturer_name=Manufacturer.manufacturer_name "
                //+"WHERE STOCK.stock_date between "+date.toLocalDate().minusMonths(1)+" AND "+date+";"
        );

        
        while (rs.next()) {
            if(date.toLocalDate().getMonthValue()-rs.getDate("stock_date").toLocalDate().getMonthValue()<=1&&
                    date.toLocalDate().getYear()-rs.getDate("stock_date").toLocalDate().getYear()==0){
                
            StockLevelReport stockLevelReport = new StockLevelReport();
            stockLevelReport.setPartName(rs.getString("item_description"));
            stockLevelReport.setCode(rs.getInt("code"));
            stockLevelReport.setStockDate(rs.getDate("stock_date"));
            stockLevelReport.setManufacturer(rs.getString("manufacturermanufacturer_name"));
            stockLevelReport.setVehicleType(rs.getString("vehicle_type"));
            stockLevelReport.setYear(rs.getInt("years"));
            stockLevelReport.setPrice(rs.getInt("unit_cost"));
            stockLevelReport.setInitialStockLevel(rs.getInt("initial_item_quantity"));
            stockLevelReport.setInitialCost(rs.getInt("initial_cost"));
            stockLevelReport.setUsed(rs.getInt("used_item_quantity"));
            stockLevelReport.setDelivery(rs.getInt("delivery"));
            stockLevelReport.setNewStockLevel(rs.getInt("actual_item_quantity"));
            stockLevelReport.setStockCost(rs.getInt("actual_cost"));
            stockLevelReport.setThreshold(rs.getInt("low_quantity_threshold"));
            ss.add(stockLevelReport);}
        }
        return ss;
    }
    
    /**
     *
     * @param job
     * @return 
     * 
     */
    //function used to query the DB to get the amount of hours spent on each task of a particular job
    public ArrayList<String> getHoursWorked(Job job){
        Connection c = null;
        Statement stmt = null;
        ArrayList<String> workTimes = new ArrayList();
      try {
         
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/GARITS",
            "postgres", "password"); //connect to the DB
         c.setAutoCommit(false);
         
         //create a new statement
         stmt = c.createStatement();
         
         
         for(Task task: job.getTasks()){
             //execute an SQL statement on the DB, which returns the task duration for every task with the given taskID
             try (ResultSet rs = stmt.executeQuery( "SELECT task_duration FROM task WHERE task_id = " + Integer.toString(task.getTaskID()) + ";" )) {
                while ( rs.next() ) {
                    //add the result to the array list
                    workTimes.add(rs.getString(1));
                }  }
         }
            
         stmt.close();//close the statement
         c.close();//close the connection
      } catch ( Exception e ) {
          //catch and handle the exception in case the connection fails
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      
      //return the work times
      return workTimes;
    }
    
    
    
}
