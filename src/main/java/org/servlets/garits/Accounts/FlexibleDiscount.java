package org.servlets.garits.Accounts;

public class FlexibleDiscount extends DiscountPlan {

	private double accummulatedPrice;

	/**
	 * 
	 * @param accumulatedPrice
	 */
	public void CalculateDiscount(double accumulatedPrice) {
		// TODO - implement FlexibleDiscount.CalculateDiscount
		throw new UnsupportedOperationException();
	}

	public double getAccummulatedPrice() {
		return this.accummulatedPrice;
	}

	/**
	 * 
	 * @param accummulatedPrice
	 */
	public void setAccummulatedPrice(double accummulatedPrice) {
		this.accummulatedPrice = accummulatedPrice;
	}

	public static FlexibleDiscount FlexibleDiscount() {
		// TODO - implement FlexibleDiscount.FlexibleDiscount
		throw new UnsupportedOperationException();
	}

}