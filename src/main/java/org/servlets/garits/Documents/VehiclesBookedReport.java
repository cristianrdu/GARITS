package org.servlets.garits.Documents;

public class VehiclesBookedReport extends Document {

	private int noOfVehicles;
	private int MoT;
	private int annualService;
	private int repair;
	private String casualCustomer;
	private String accountHolder;

	/**
	 * 
	 * @param noOfVehicles
	 * @param MoT
	 * @param annualService
	 * @param repair
	 * @param casualCustomer
	 * @param accountHolder
	 */
	public String generateMonthlyReport(int noOfVehicles, int MoT, int annualService, int repair, String casualCustomer, String accountHolder) {
		// TODO - implement VehiclesBookedReport.generateMonthlyReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param noOfVehicles
	 * @param MoT
	 * @param annualService
	 * @param repair
	 * @param casualCustomer
	 * @param accountHolder
	 */
	public String generateOverallReport(int noOfVehicles, int MoT, int annualService, int repair, String casualCustomer, String accountHolder) {
		// TODO - implement VehiclesBookedReport.generateOverallReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param noOfVehicles
	 * @param MoT
	 * @param annualService
	 * @param repair
	 * @param casualCustomer
	 * @param accountHolder
	 */
	public String generatePerJobTypeReport(int noOfVehicles, int MoT, int annualService, int repair, String casualCustomer, String accountHolder) {
		// TODO - implement VehiclesBookedReport.generatePerJobTypeReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param noOfVehicles
	 * @param MoT
	 * @param annualService
	 * @param repair
	 * @param casualCustomer
	 * @param accountHolder
	 */
	public String generateCustomerTypeReport(int noOfVehicles, int MoT, int annualService, int repair, String casualCustomer, String accountHolder) {
		// TODO - implement VehiclesBookedReport.generateCustomerTypeReport
		throw new UnsupportedOperationException();
	}

	public static VehiclesBookedReport VehiclesBookedReport() {
		// TODO - implement VehiclesBookedReport.VehiclesBookedReport
		throw new UnsupportedOperationException();
	}

}