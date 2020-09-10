package org.servlets.garits.Accounts;

import java.util.Date;

public class AccountHolder extends Customer {

    public AccountHolder(String accountId, Date pld, int plo, boolean paid, 
            Customer customer, DiscountPlan discountPlan, String email, 
            String name, String address, String tel, String postCode, String fax)
    /*
         Wouldn't it be easier if for each class, if it extends a superclass,
            
         we could have the Key attribute(as a string or something) from that 
            
        class be stored in this class instead of having a whole object stored?
            
    */
    {
        super(email, name, address, tel, postCode, fax);
        this.accountId = accountId;
        this.pld = pld;
        this.plo = plo;
        this.paid = paid;
        this.customer = customer;
        this.discountPlan = discountPlan;
    }

	private String accountId;
	private Date pld;
	private int plo;
	private boolean paid;
	private Customer customer;
	private DiscountPlan discountPlan;
        

	public String getAccountId() {
		return this.accountId;
	}

	/**
	 * 
	 * @param accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getPld() {
		return this.pld;
	}

	/**
	 * 
	 * @param pld
	 */
	public void setPld(Date pld) {
		this.pld = pld;
	}

	public int getPlo() {
		return this.plo;
	}

	/**
	 * 
	 * @param plo
	 */
	public void setPlo(int plo) {
		this.plo = plo;
	}

	public boolean getPaid() {
		return this.paid;
	}

	/**
	 * 
	 * @param paid
	 */
	public void setPaid(boolean paid) {
		this.paid = paid;
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

	public DiscountPlan getDiscountPlan() {
		return this.discountPlan;
	}

	/**
	 * 
	 * @param discountPlan
	 */
	public void setDiscountPlan(DiscountPlan discountPlan) {
		this.discountPlan = discountPlan;
	}

	

}