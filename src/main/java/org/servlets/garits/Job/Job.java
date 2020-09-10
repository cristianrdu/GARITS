package org.servlets.garits.Job;

import org.servlets.garits.Accounts.Mechanic;
import org.servlets.garits.Accounts.Customer;
import java.util.ArrayList;
import java.sql.Date;

public class Job {

    public Job(Mechanic assignedMechanic, String status, int estimatedTime,
            String jobType, Customer customer, String workRequired,
            ArrayList<Task> tasks) {
        this.assignedMechanic = assignedMechanic;
        this.status = status;
        this.estimatedTime = estimatedTime;
        this.jobType = jobType;
        this.customer = customer;
        this.workRequired = workRequired;
        this.tasks = tasks;
    }

    public Job() {
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    private Mechanic assignedMechanic;
    private String status;
    private int estimatedTime;
    private int jobNo;
    private String jobType;
    private Customer customer;
    private String workRequired ="";
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Date bookingDate;
    private Date completionDate;
    private String description = "";
    private boolean paid = false;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Mechanic getAssignedMechanic() {
        return this.assignedMechanic;
    }

    /**
     *
     * @param assignedMechanic
     */
    public void setAssignedMechanic(Mechanic assignedMechanic) {
        this.assignedMechanic = assignedMechanic;
    }

    public String getStatus() {
        return this.status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public int getEstimatedTime() {
        return this.estimatedTime;
    }

    /**
     *
     * @param estimatedTime
     */
    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getJobType() {
        return this.jobType;
    }

    public int getJobNo() {
        return jobNo;
    }

    public void setJobNo(int jobNo) {
        this.jobNo = jobNo;
    }
    

    /**
     *
     * @param jobType
     */
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    /**
     *
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getWorkRequired() {
        return this.workRequired;
    }

    /**
     *
     * @param workRequired
     */
    public void setWorkRequired(String workRequired) {
        this.workRequired = workRequired;
    }
    
    public void addTask(Task t){
        tasks.add(t);
    }

    @Override
    public String toString() {
        return "Job{" + "assignedMechanic=" + assignedMechanic.getName() + ", status=" + status + ", estimatedTime=" + estimatedTime + ", jobType=" + jobType + ", customer=" + customer + ", workRequired=" + workRequired + ", tasks=" + tasks + ", bookingDate=" + bookingDate + ", completionDate=" + completionDate + ", description=" + description + ", paid=" + paid + '}';
    }
    
    

}
