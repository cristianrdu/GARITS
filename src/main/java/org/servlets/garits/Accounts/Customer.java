package org.servlets.garits.Accounts;

import java.util.ArrayList;

public class Customer {

    public Customer(String email, String name, String address, String tel, String post_code, String fax) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.post_code = post_code;
        this.fax = fax;
    }
    
	private String email;
	private String name;
	private String address;
	private String tel;
	private String post_code;
	private String fax;
        private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" + "email=" + email + ", name=" + name + ", address=" + address + ", tel=" + tel + ", post_code=" + post_code + ", fax=" + fax + ", vehicles=" + vehicles + '}';
    }
    

	public void makeRepairBooking() {
		// TODO - implement Customer.makeRepairBooking
	}

	public void makeMoTBooking() {
		// TODO - implement Customer.makeMoTBooking
	}

	public void confirmService() {
		// TODO - implement Customer.confirmService
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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

	public String getTel() {
		return this.tel;
	}

	/**
	 * 
	 * @param tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPostCode() {
		return this.post_code;
	}

	/**
	 * 
	 * @param postCode
	 */
	public void setPostCode(String postCode) {
		this.post_code = postCode;
	}

	public String getFax() {
		return this.fax;
	}

	/**
	 * 
	 * @param fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

    public void AddVehicle(Vehicle v) {
        this.vehicles.add(v);
    }
    public Vehicle GetVehicle(String regNo){
        for(Vehicle v: vehicles){
            if(v.getRegNo().equals(regNo)){
               return v; 
            }
        }
        return null;
    }

	/**
	 * 
	 * @param name
	 * @param ID
	 */
	

}