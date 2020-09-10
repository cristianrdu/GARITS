package org.servlets.garits.Documents;

import java.sql.Date;

public class StockLevelReport extends Document {

	private String partName;
        private Date stockDate;
        private int code;
        private String manufacturer;
        private String vehicleType;
        private int year;
        private int price;
        private int initialStockLevel;
        private int initialCost;
        private int used;
        private int delivery;
        private int newStockLevel;
        private int stockCost;
        private int lowLevel;
        private int threshold;
        
	public void generateWeeklyReport() {
		// TODO - implement StockLevelReport.generateWeeklyReport
		throw new UnsupportedOperationException();
	}

	public void generateOnDemandReport() {
		// TODO - implement StockLevelReport.generateOnDemandReport
		throw new UnsupportedOperationException();
	}

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInitialStockLevel() {
        return initialStockLevel;
    }

    public void setInitialStockLevel(int initialStockLevel) {
        this.initialStockLevel = initialStockLevel;
    }

    public int getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(int initialCost) {
        this.initialCost = initialCost;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public int getNewStockLevel() {
        return newStockLevel;
    }

    public void setNewStockLevel(int newStockLevel) {
        this.newStockLevel = newStockLevel;
    }

    public int getStockCost() {
        return stockCost;
    }

    public void setStockCost(int stockCost) {
        this.stockCost = stockCost;
    }

    public int getLowLevel() {
        return lowLevel;
    }

    public void setLowLevel(int lowLevel) {
        this.lowLevel = lowLevel;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
        
}