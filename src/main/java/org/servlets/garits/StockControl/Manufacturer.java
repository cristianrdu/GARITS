package org.servlets.garits.StockControl;

public class Manufacturer {

    public Manufacturer(String manufacturerName, String address, String emailAddress) {
        this.manufacturerName = manufacturerName;
        this.address = address;
        this.emailAddress = emailAddress;
    }

	private String manufacturerName;
	private String address;
	private String emailAddress;

    public Manufacturer() {
    }

	public String getManufacturerName() {
		return this.manufacturerName;
	}

	/**
	 * 
	 * @param manufacturerName
	 */
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getAddress() {
		return this.address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * 
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String toString(){
            return manufacturerName;
        }
        

}