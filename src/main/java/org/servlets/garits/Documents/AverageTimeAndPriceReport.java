package org.servlets.garits.Documents;


public class AverageTimeAndPriceReport extends Document {

	private int time;
	private double price;
	private String MoT;
	private boolean annualService;
	private String repair;
	private String mechanicName;

	/**
	 * 
	 * @param time
	 * @param price
	 * @param MoT
	 * @param annualService
	 * @param repair
	 * @param mechanicName
	 */
	public String generateOverallReport(int time, double price, boolean MoT, boolean annualService, String repair, String mechanicName) {
		// TODO - implement AverageTimeAndPriceReportP.generateOverallReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param time
	 * @param price
	 * @param MoT
	 * @param annualService
	 * @param repair
	 * @param mechanicName
	 */
	public String generatePerJobReport(int time, double price, boolean MoT, boolean annualService, String repair, String mechanicName) {
		// TODO - implement AverageTimeAndPriceReportP.generatePerJobReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param time
	 * @param price
	 * @param MoT
	 * @param annualService
	 * @param repair
	 * @param mechanicName
	 */
	public String generateGivenMechanicReport(int time, double price, boolean MoT, boolean annualService, String repair, String mechanicName) {
		// TODO - implement AverageTimeAndPriceReportP.generateGivenMechanicReport
		throw new UnsupportedOperationException();
	}

	public static AverageTimeAndPriceReport AverageTimeAndPriceReport() {
		// TODO - implement AverageTimeAndPriceReportP.AverageTimeAndPriceReport
		throw new UnsupportedOperationException();
	}

}