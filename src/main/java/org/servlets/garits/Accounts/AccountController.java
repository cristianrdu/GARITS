package org.servlets.garits.Accounts;

import org.servlets.garits.DAO.CustomerDAO;
import org.servlets.garits.DAO.JobDAO;
import org.servlets.garits.DAO.StockDAO;
import org.servlets.garits.DAO.UserDAO;
import org.servlets.garits.Job.Job;
import org.servlets.garits.StockControl.Manufacturer;
import org.servlets.garits.StockControl.Stock;
import java.sql.SQLException;
import java.util.ArrayList;
import org.servlets.garits.StockControl.Order;
import org.servlets.garits.StockControl.Payments.SparePartPayment;
import org.servlets.garits.StockControl.SparePart;

public class AccountController {

    private User user;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    private UserDAO dao;
    private CustomerDAO customerDAO;
    private JobDAO jobs;
    private CustomerDAO customers;
    private StockDAO stock;

    /**
     *
     * @param job
     */
    public void takeInJob(Job job) {
        // TODO - implement AccountController.takeInJob
    }

    /**
     *
     * @param job
     */
    public void MonitorJob(Job job) {
        // TODO - implement AccountController.MonitorJob
    }

    public void addToPendingJobsList() {
        // TODO - implement AccountController.addToPendingJobsList
    }

    public User getUser() {
        return this.user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Customer> getCustomers() throws SQLException {

        customers = new CustomerDAO();
        return customers.getAllCustomers();
    }

    public ArrayList<Vehicle> getCustomerVehicles(String email) throws SQLException {
        customers = new CustomerDAO();
        return customers.getVehicles(email);
    }

    public void EditCustomer(Customer customer, String email) throws SQLException, ClassNotFoundException {
        customerDAO = new CustomerDAO();
        customerDAO.EditCustomerDetails(customer, email);
    }

    public void DeleteCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        customerDAO = new CustomerDAO();
        customerDAO.DeleteCustomer(customer);
    }

    public void EditUser(User user) throws SQLException, ClassNotFoundException {
        dao = new UserDAO();
        dao.EditUserDetails(user);
    }

    public void RemoveUser(User user) throws SQLException, ClassNotFoundException {
        dao = new UserDAO();
        dao.DeleteUser(user);
    }

    public ArrayList<Mechanic> findAllMechanics() throws SQLException, ClassNotFoundException {
        dao = new UserDAO();
        return dao.findAllMechanics();
    }

    public ArrayList<Job> getJobs() throws SQLException, ClassNotFoundException {
        jobs = new JobDAO();
        ArrayList<Job> j = jobs.getAllJobs();
        System.out.println(j.size());
        return j;
    }

    public void AddJob(Job job, String regno) throws SQLException {
        jobs = new JobDAO();
        jobs.addJob(job, regno);
    }

    public void logIn(String username, String password) throws ClassNotFoundException, SQLException {

        dao = new UserDAO();
        this.user = dao.logIn(username, password);
        //System.out.println(user.toString());
    }

    public ArrayList<Stock> getStock() throws SQLException {
        stock = new StockDAO();
        return stock.getAllStock();
    }

    public void addSparePart(Stock sp) throws SQLException {
        stock = new StockDAO();
        stock.AddSparePart(sp);
    }

    public ArrayList<Manufacturer> getManufacturers() throws SQLException {
        stock = new StockDAO();
        return stock.getManufacturers();
    }

    public void AddManufacturer(Manufacturer m) throws SQLException {
        stock = new StockDAO();
        stock.AddManufacturer(m);
    }

    public void UpdateThreshold(Stock s, int nt) throws SQLException {
        stock = new StockDAO();
        stock.UpdateThreshold(s, nt);
    }

    public void OrderSparePart(Stock s, int q) throws SQLException {
        stock = new StockDAO();
        stock.OrderSparePart(s, q);
    }

    public ArrayList<Order> getOrders() throws SQLException {
        stock = new StockDAO();
        return stock.getOrders();
    }

    public void Restock(SparePart s) throws SQLException {
        stock = new StockDAO();
        stock.Restock(s);
    }

    /**
     *
     * @param staffNo
     */
    public void allocateMechanic(int staffNo) {
        // TODO - implement AccountController.allocateMechanic
    }

    public AccountController() {
        // TODO - implement AccountController.AccountController
    }

    public void ProcessPayment(SparePartPayment payment) throws SQLException {
        stock = new StockDAO();
        stock.ProcessPayment(payment);
    }

}
