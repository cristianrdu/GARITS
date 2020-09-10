package org.servlets.garits.Accounts;

public class FixedDiscount extends DiscountPlan {

	private int percentage;

	/**
	 * 
	 * @param price
	 */
	public void CalculateDiscount(double price) {
		// TODO - implement FixedDiscount.CalculateDiscount
		throw new UnsupportedOperationException();
	}

	public int getPercentage() {
		return this.percentage;
	}

	/**
	 * 
	 * @param percentage
	 */
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	/**
	 * 
	 * @param percentage
	 */
	public static FixedDiscount FixedDiscount(int percentage) {
		// TODO - implement FixedDiscount.FixedDiscount
		throw new UnsupportedOperationException();
	}

}