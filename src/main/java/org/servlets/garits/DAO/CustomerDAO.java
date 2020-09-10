/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.DAO;

import org.servlets.garits.Accounts.Customer;
import org.servlets.garits.Accounts.Vehicle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author balac
 */
public class CustomerDAO {

    private Connection connection;

    public CustomerDAO() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/GARITS", "postgres", "password");

        connection.setAutoCommit(false);

    }

    public ArrayList<Customer> getAllCustomers() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<Customer>();

        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("Select * FROM public.customer;");
        while (results.next()) {
            Customer customer = new Customer();
            customer.setEmail(results.getString("email"));
            customer.setAddress(results.getString("address"));
            customer.setName(results.getString("name"));
            customer.setPostCode(results.getString("post_code"));
            customer.setTel(results.getString("tel"));
            customer.setFax(results.getString("fax"));

            customers.add(customer);

        }
        connection.commit();

        connection.close();

        return customers;
    }

    public ArrayList<Vehicle> getVehicles(String email) throws SQLException {
        ArrayList<Vehicle> v = new ArrayList<Vehicle>();

        Statement statement;
        statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM public.vehicle WHERE customeremail='" + email + "'");
        while (results.next()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setChassieNumber(results.getString("chassie_number").trim());
            vehicle.setColour(results.getString("colour").trim());
            vehicle.setCustomerEmail(email);
            vehicle.setEn_serial(results.getString("eng_serial").trim());
            vehicle.setMake(results.getString("make").trim());
            vehicle.setModel(results.getString("model").trim());
            vehicle.setMotDueDate(results.getTimestamp("mot_due_date"));
            vehicle.setVehiclePickupAddress(results.getString("pickup_address").trim());
            vehicle.setYear(results.getString("year").trim());
            vehicle.setRegNo(results.getString("reg_number"));
            v.add(vehicle);
        }

        connection.commit();
        connection.close();

        return v;
    }

    public void addCustomer(Customer newCustomer) throws SQLException {
        System.out.println("addCustomer");
        PreparedStatement statement;
        statement = connection.prepareStatement("INSERT INTO public.customer(\n"
                + "email, name, address, tel, post_code, fax)\n"
                + "VALUES (?, ?, ?, ?, ?, ?);");
        statement.setString(1, newCustomer.getEmail());

        statement.setString(2, newCustomer.getName());

        statement.setString(3, newCustomer.getAddress());
        statement.setString(4, newCustomer.getTel());

        statement.setString(5, newCustomer.getPostCode());

        statement.setString(6, newCustomer.getFax());

        statement.executeUpdate();

        connection.commit();
        connection.close();
    }

    public void EditCustomerDetails(Customer customer, String email) throws SQLException {
        System.out.println("editCustomer");

        PreparedStatement statement;

        statement = connection.prepareStatement("UPDATE public.customer"
                + "	SET email=?, name=?, address=?, tel=?, post_code=?, fax=?"
                + "	WHERE email=?;");
        statement.setString(1, customer.getEmail());
        statement.setString(2, customer.getName());
        statement.setString(3, customer.getAddress());
        statement.setString(4, customer.getTel());
        statement.setString(5, customer.getPostCode());
        statement.setString(6, customer.getFax());
        statement.setString(7, email);
        statement.executeUpdate();
        statement.close();

        connection.commit();
        connection.close();
    }

    public void DeleteCustomer(Customer customer) throws SQLException {
        System.out.println("deleteCustomer");
        PreparedStatement statement;

        statement = connection.prepareStatement("DELETE FROM public.customer\n"
                + "WHERE email=?;");
        statement.setString(1, customer.getEmail());
        statement.executeUpdate();
        statement.close();

        connection.commit();

        connection.close();
    }
}
