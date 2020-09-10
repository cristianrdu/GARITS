    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.DAO;

import org.servlets.garits.Accounts.Customer;
import org.servlets.garits.Accounts.Mechanic;
import org.servlets.garits.Accounts.Vehicle;
import org.servlets.garits.Job.Job;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import org.servlets.garits.Job.Task;

/**
 *
 * @author glaxo
 */
public class JobDAO {

    private Connection connection;
    private UserDAO userDAO;

    public JobDAO() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/GARITS", "postgres", "password");

        connection.setAutoCommit(false);

    }

    public ArrayList<Job> getAllJobs() throws SQLException, ClassNotFoundException {
        ArrayList<Job> jobs = new ArrayList<Job>();
        Hashtable<Job, Vehicle> jobRegno = new Hashtable<Job, Vehicle>();

        Statement statement = connection.createStatement();
        //Get All jobs from the jobs table
        ResultSet results = statement.executeQuery("Select * From public.job");
        while (results.next()) {
            Job job = new Job();
            job.setJobType(results.getString("job_type"));
            job.setStatus(results.getString("job_status"));
            job.setWorkRequired(results.getString("work_required"));
            job.setBookingDate(results.getDate("appointment_date"));
            job.setCompletionDate(results.getDate("date_completed"));
            job.setPaid(results.getBoolean("paid"));
            job.setJobNo(results.getInt("job_no"));
            Mechanic m = new Mechanic();
            m.setStaffNo(results.getInt("mechanicstaffstaff_no"));
            job.setAssignedMechanic(m);
            Vehicle v = new Vehicle();
            v.setRegNo(results.getString("Vehiclereg_number"));
            jobRegno.put(job, v);
            Customer c = new Customer();
            c.AddVehicle(v);
            job.setCustomer(c);

            //System.out.println(job.toString());
            int tmp = job.getAssignedMechanic().getStaffNo();
            userDAO = new UserDAO();
            Mechanic mechanic = userDAO.getMechanic(tmp);
            job.setAssignedMechanic(mechanic);
            jobs.add(job);
            System.out.println(job.toString());
        }
        for (Job job : jobs) {
            Customer c = job.getCustomer();
            Vehicle v = c.GetVehicle(jobRegno.get(job).getRegNo());
            ResultSet vehResults = statement.executeQuery("SELECT * FROM public.vehicle WHERE reg_number='" + v.getRegNo() + "'");
            vehResults.next();
            String e = vehResults.getString("customeremail");
            ResultSet custResults = statement.executeQuery("SELECT * FROM public.customer WHERE email='" + e + "'");
            custResults.next();
            c.setAddress(custResults.getString("address"));
            c.setEmail(custResults.getString("email"));
            c.setFax(custResults.getString("fax"));
            c.setName(custResults.getString("name"));
            c.setPostCode(custResults.getString("post_code"));
            c.setTel(custResults.getString("tel"));
            job.setCustomer(c);
            // System.out.println(job.toString());
        }
        connection.commit();
        connection.close();
        return jobs;
    }

    public void addJob(Job job, String regno) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO public.job("
                + " job_no, date_booked_in, work_required, job_status, job_type, mechanicstaffstaff_no, date_completed, appointment_date, \"Vehiclereg_number\", paid)"
                + " VALUES ( (SELECT 1 + MAX(job_no) FROM public.job ) , ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        statement.setDate(1, job.getBookingDate());
        statement.setString(2, job.getWorkRequired());
        statement.setString(3, "Pending");
        statement.setString(4, job.getJobType());
        statement.setInt(5, job.getAssignedMechanic().getStaffNo());
        statement.setDate(6, (Date) job.getCompletionDate());
        statement.setDate(7, (Date) job.getBookingDate());
        statement.setString(8, regno);
        statement.setBoolean(9, false);
        statement.execute();
        statement.close();
        connection.commit();
        connection.close();

    }

    public ArrayList<Job> getJobsByMechanic(Mechanic m) throws SQLException {
        ArrayList<Job> jobs = new ArrayList<Job>();

        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery("SELECT * FROM public.job WHERE mechanicstaffstaff_no=" + m.getStaffNo() + " AND NOT job_status='Finished'");
        while (results.next()) {
            Job job = new Job();
            job.setJobType(results.getString("job_type"));
            job.setStatus(results.getString("job_status"));
            job.setWorkRequired(results.getString("work_required"));
            job.setBookingDate(results.getDate("appointment_date"));
            job.setCompletionDate(results.getDate("date_completed"));
            job.setPaid(results.getBoolean("paid"));
            job.setJobNo(results.getInt("job_no"));
            job.setAssignedMechanic(m);
            Vehicle v = new Vehicle();
            v.setRegNo(results.getString("Vehiclereg_number"));
            Customer c = new Customer();
            c.AddVehicle(v);
            job.setCustomer(c);
            jobs.add(job);
        }
        for (Job j : jobs) {
            ResultSet r = statement.executeQuery("SELECT * FROM public.task WHERE jobjob_no=" + j.getJobNo());
            while (r.next()) {
                Task t = new Task();
                t.setDescription(r.getString("work_carried_out"));
                t.setTaskID(r.getInt("task_id"));
                t.setEstimatedTime(r.getTimestamp("estimated_time"));
                t.setTaskDuration(r.getTimestamp("task_duration"));
                ResultSet r2 = statement.executeQuery("SELECT Stock.* FROM public.stock, public.Job_spare_part "
                        + "where job_spare_part.tasktask_id=1 AND public.job_spare_part.stock_code=public.stock.code;");
                while (r2.next()) {
                    
                }
                j.addTask(t);
            }
        }

        return jobs;
    }

}
