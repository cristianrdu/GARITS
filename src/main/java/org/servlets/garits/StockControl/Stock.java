package org.servlets.garits.StockControl;

import org.servlets.garits.Accounts.Vehicle;
import java.sql.Date;

public class Stock {

    public Stock(int code, String itemDescription, double unitCost, 
            String years, int initialItemQuantity, int actualItemQuantity,
            int threshold, int useditemQuantity, String vehicle, 
            Date stockDate, int delivery, Manufacturer manufacturer) {
        this.code = code;
        this.itemDescription = itemDescription;
        this.unitCost = unitCost;
        this.years = years;
        this.initialItemQuantity = initialItemQuantity;
        this.actualItemQuantity = actualItemQuantity;
        this.threshold = threshold;
        this.useditemQuantity = useditemQuantity;
        this.vehicleType = vehicle;
        this.stockDate = stockDate;
        this.delivery = delivery;
        this.manufacturer = manufacturer;
    }

	private int code;
	private String itemDescription;
	private double unitCost;
	private String years;
	private int initialItemQuantity;
	private int actualItemQuantity;
	private int threshold;
	private int useditemQuantity;
	private String vehicleType;
	private Date stockDate;
	private int delivery;
	private Manufacturer manufacturer;

    public Stock() {
    }

	public int getCode() {
		return this.code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	public String getItemDescription() {
		return this.itemDescription;
	}

	/**
	 * 
	 * @param itemDescription
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public double getUnitCost() {
		return this.unitCost;
	}

	/**
	 * 
	 * @param unitCost
	 */
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public String getYears() {
		return this.years;
	}

	/**
	 * 
	 * @param years
	 */
	public void setYears(String years) {
		this.years = years;
	}

	public int getInitialItemQuantity() {
		return this.initialItemQuantity;
	}

	/**
	 * 
	 * @param initialItemQuantity
	 */
	public void setInitialItemQuantity(int initialItemQuantity) {
		this.initialItemQuantity = initialItemQuantity;
	}

	public int getActualItemQuantity() {
		return this.actualItemQuantity;
	}

	/**
	 * 
	 * @param actualItemQuantity
	 */
	public void setActualItemQuantity(int actualItemQuantity) {
		this.actualItemQuantity = actualItemQuantity;
	}

	public int getThreshold() {
		return this.threshold;
	}

	/**
	 * 
	 * @param threshold
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public int getUseditemQuantity() {
		return this.useditemQuantity;
	}

	/**
	 * 
	 * @param useditemQuantity
	 */
	public void setUseditemQuantity(int useditemQuantity) {
		this.useditemQuantity = useditemQuantity;
	}

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

	

	public Date getStockDate() {
		return this.stockDate;
	}

	/**
	 * 
	 * @param stockDate
	 */
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public int getDelivery() {
		return this.delivery;
	}

	/**
	 * 
	 * @param delivery
	 */
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}

	public Manufacturer getManufacturer() {
		return this.manufacturer;
	}

	/**
	 * 
	 * @param manufacturer
	 */
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String toString(){
            return this.getItemDescription();
        }

}