/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.DAO;

import org.servlets.garits.StockControl.Manufacturer;
import org.servlets.garits.StockControl.Stock;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.servlets.garits.StockControl.Order;
import org.servlets.garits.StockControl.Payments.SparePartPayment;
import org.servlets.garits.StockControl.SparePart;

/**
 *
 * @author glaxo
 */
public class StockDAO {

    private Connection connection;

    public StockDAO() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/GARITS", "postgres", "password");

        connection.setAutoCommit(false);
    }

    public ArrayList<Stock> getAllStock() throws SQLException {
        ArrayList<Stock> stock = new ArrayList<Stock>();

        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery("SELECT * FROM public.stock");
        while (results.next()) {
            Stock s = new Stock();
            s.setActualItemQuantity(results.getInt("actual_item_quantity"));
            s.setInitialItemQuantity(results.getInt("initial_item_quantity"));
            s.setCode(results.getInt("code"));
            s.setUnitCost(results.getDouble("unit_cost"));
            s.setItemDescription(results.getString("item_description"));
            Manufacturer m = new Manufacturer();
            m.setManufacturerName(results.getString("manufacturermanufacturer_name"));
            s.setManufacturer(m);
            s.setThreshold(results.getInt("low_quantity_threshold"));
            s.setYears(results.getString("years"));
            s.setUseditemQuantity(results.getInt("used_item_quantity"));
            s.setStockDate(results.getDate("stock_date"));
            s.setVehicleType(results.getString("vehicle_type"));
            stock.add(s);
        }

        for (Stock s : stock) {
            Manufacturer m = s.getManufacturer();
            ResultSet r = statement.executeQuery("SELECT * FROM public.manufacturer WHERE manufacturer_name='" + m.getManufacturerName() + "'");
            r.next();
            m.setAddress(r.getString("address"));
            m.setEmailAddress(r.getString("email_address"));
            s.setManufacturer(m);
        }

        connection.commit();
        connection.close();
        return stock;
    }

    public ArrayList<Manufacturer> getManufacturers() throws SQLException {
        ArrayList<Manufacturer> m = new ArrayList<Manufacturer>();
        Statement s = connection.createStatement();
        ResultSet results = s.executeQuery("SELECT * FROM public.manufacturer");
        while (results.next()) {
            Manufacturer man = new Manufacturer();
            man.setManufacturerName(results.getString("manufacturer_name"));
            man.setEmailAddress(results.getString("email_address"));
            man.setAddress(results.getString("address"));
            m.add(man);
        }
        connection.commit();
        connection.close();
        return m;
    }

    public void AddSparePart(Stock s) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO public.stock("
                + " code, item_description, unit_cost, years, initial_item_quantity, actual_item_quantity, low_quantity_threshold, used_item_quantity, vehicle_type, stock_date, delivery, manufacturermanufacturer_name) "
                + " VALUES ((SELECT 1 + MAX(code) FROM public.stock ), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        statement.setString(1, s.getItemDescription());
        statement.setDouble(2, s.getUnitCost());
        statement.setString(3, s.getYears());
        statement.setInt(4, s.getInitialItemQuantity());
        statement.setInt(5, s.getActualItemQuantity());
        statement.setInt(6, s.getThreshold());
        statement.setInt(7, 0);
        statement.setString(8, s.getVehicleType());
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        statement.setDate(9, date);
        statement.setInt(10, 0);
        statement.setString(11, s.getManufacturer().getManufacturerName());
        statement.execute();
        connection.commit();
        connection.close();
    }

    public void AddManufacturer(Manufacturer m) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO public.manufacturer("
                + " manufacturer_name, address, email_address)"
                + " VALUES (?, ?, ?);");
        statement.setString(1, m.getManufacturerName());
        statement.setString(2, m.getAddress());
        statement.setString(3, m.getEmailAddress());
        statement.execute();
        connection.commit();
        connection.close();
    }

    public void UpdateThreshold(Stock s, int newThreshold) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE public.stock"
                + " SET  low_quantity_threshold=?"
                + " WHERE code=?;");
        statement.setInt(1, newThreshold);
        statement.setInt(2, s.getCode());
        statement.executeUpdate();
        connection.commit();
        connection.close();
    }

    public void OrderSparePart(Stock s, int quantity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO public.\"Order\"("
                + " company_name, order_date, order_cost, order_quantity, stock_code, order_no)"
                + " VALUES (?, ?, ?, ?, ?, (SELECT 1 + MAX(order_no) FROM public.\"Order\" ) );");
        statement.setString(1, s.getManufacturer().getManufacturerName());
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        statement.setDate(2, date);
        statement.setDouble(3, (s.getUnitCost() * quantity));
        statement.setInt(4, quantity);
        statement.setInt(5, s.getCode());
        statement.executeUpdate();
        connection.commit();
        connection.close();

    }

    public ArrayList<Order> getOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<Order>();

        Statement statement = connection.createStatement();
        ResultSet r = statement.executeQuery("SELECT * FROM public.\"Order\"");
        while (r.next()) {
            Order o = new Order();
            o.setCompanyName(r.getString("company_name"));
            o.setCost(r.getDouble("order_cost"));
            o.setDate(r.getDate("order_date"));
            o.setQuantity(r.getInt("order_quantity"));
            o.setStockCode(r.getInt("stock_code"));
            o.setOrderNumber(r.getInt("order_no"));
            orders.add(o);
        }

        connection.commit();
        connection.close();

        return orders;
    }

    public void Restock(SparePart s) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE public.stock"
                + " SET  actual_item_quantity=?"
                + " WHERE code=?;");
        int q = s.getStock().getActualItemQuantity() + s.getQuantity();
        statement.setInt(1, q);
        statement.setInt(2, s.getStock().getCode());
        statement.executeUpdate();
        connection.commit();
        connection.close();
    }

    public void ProcessPayment(SparePartPayment payment) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO public.payment( "
                + " payment_id, invoiceinvoice_no, amount, due_date, sparepartinvoice_no, method) "
                + " VALUES ((SELECT 1 + MAX(payment_id) FROM public.payment ), null, ?, ?,(SELECT 1 + MAX(sparepartinvoice_no) FROM public.payment ),?);");
        statement.setDouble(1, payment.calculateTotal());
        statement.setDate(2, payment.getPaymentDate());
        statement.setString(3, payment.getPaymentMethod());
        statement.execute();
        for (SparePart s : payment.getSp()) {
            PreparedStatement st = connection.prepareStatement("UPDATE public.stock"
                    + " SET  actual_item_quantity=?,used_item_quantity=?"
                    + " WHERE code=?;");
            st.setInt(1, s.getStock().getActualItemQuantity() - s.getQuantity());
            st.setInt(2, s.getStock().getUseditemQuantity() + s.getQuantity());
            st.setInt(3, s.getStock().getCode());
            st.execute();
        }

        connection.commit();
        connection.close();
    }

}
