package org.servlets.garits.Accounts;

import java.util.Date;

public class Vehicle {

    public Vehicle(String regNo, String make, String model, String colour,
            String en_serial, String year, String customerEmail,
            String vehiclePickupAddress, Date motDueDate, String chassieNumber) {
        this.regNo = regNo;
        this.make = make;
        this.model = model;
        this.colour = colour;
        this.en_serial = en_serial;
        this.year = year;
        this.customerEmail = customerEmail;
        this.vehiclePickupAddress = vehiclePickupAddress;
        this.motDueDate = motDueDate;
        this.chassieNumber = chassieNumber;
    }

    private String regNo;
    private String make;
    private String model;
    private String colour;
    private String en_serial;
    private String year;
    private String customerEmail;
    private String vehiclePickupAddress;
    private Date motDueDate;
    private String chassieNumber;

    public Vehicle() {
    }

    public String getRegNo() {
        return this.regNo;
    }

    /**
     *
     * @param regNo
     */
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getMake() {
        return this.make;
    }

    /**
     *
     * @param make
     */
    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    /**
     *
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return this.colour;
    }

    /**
     *
     * @param colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getEn_serial() {
        return this.en_serial;
    }

    /**
     *
     * @param en_serial
     */
    public void setEn_serial(String en_serial) {
        this.en_serial = en_serial;
    }

    public String getYear() {
        return this.year;
    }

    /**
     *
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    /**
     *
     * @param customerEmail
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getVehiclePickupAddress() {
        return this.vehiclePickupAddress;
    }

    /**
     *
     * @param vehiclePickupAddress
     */
    public void setVehiclePickupAddress(String vehiclePickupAddress) {
        this.vehiclePickupAddress = vehiclePickupAddress;
    }

    public Date getMotDueDate() {
        return this.motDueDate;
    }

    /**
     *
     * @param motDueDate
     */
    public void setMotDueDate(Date motDueDate) {
        this.motDueDate = motDueDate;
    }

    public String getChassieNumber() {
        return this.chassieNumber;
    }

    /**
     *
     * @param chassieNumber
     */
    public void setChassieNumber(String chassieNumber) {
        this.chassieNumber = chassieNumber;
    }

    /**
     *
     * @param regNo
     * @param make
     * @param model
     */
    public static Vehicle Vehicle(String regNo, String make, String model) {
        // TODO - implement Vehicle.Vehicle
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Vehicle{" + "regNo=" + regNo + ", make=" + make + ", model=" + model + ", colour=" + colour + ", en_serial=" + en_serial + ", year=" + year + ", customerEmail=" + customerEmail + ", vehiclePickupAddress=" + vehiclePickupAddress + ", motDueDate=" + motDueDate + ", chassieNumber=" + chassieNumber + '}';
    }

}
