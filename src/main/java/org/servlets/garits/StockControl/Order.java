package org.servlets.garits.StockControl;

import java.sql.Date;

public class Order {

    public Order(int orderNumber, String companyName, Date date, double cost, int quantity) {
        this.orderNumber = orderNumber;
        this.companyName = companyName;
        this.date = date;
        this.cost = cost;
        this.quantity = quantity;
    }

    private int orderNumber;
    private String companyName;
    private Date date;
    private double cost;
    private int quantity;
    private int stockCode;

    public Order() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getStockCode() {
        return stockCode;
    }

    public void setStockCode(int stockCode) {
        this.stockCode = stockCode;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    /**
     *
     * @param orderNumber
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    

    /**
     *
     * @param manufacturer
     */
    public void setManufacturer(Manufacturer manufacturer) {
        // TODO - implement Order.setManufacturer
    }

    public Date getDate() {
        return this.date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public double getCost() {
        return this.cost;
    }

    /**
     *
     * @param cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return this.quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
