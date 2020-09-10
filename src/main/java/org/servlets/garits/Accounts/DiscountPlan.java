package org.servlets.garits.Accounts;

public abstract class DiscountPlan {

	/**
	 * 
	 * @param price
	 */
	public abstract void CalculateDiscount(double price);

	public  DiscountPlan() {	
		
	}

}