package org.servlets.garits.Accounts;

import org.servlets.garits.Job.Job;

public class Receptionist extends User {

    public Receptionist(int sno, String un, String n, String ad, String t, String em, String pc) {
        super(sno, un, n, ad, t, em, pc);
    }

    public Receptionist() {}

	/**
	 * 
	 * @param job
	 */
	public void takeInNewJob(Job job) {
		// TODO - implement Receptionist.takeInNewJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param job
	 */
	public void addToPendingJobsList(Job job) {
		// TODO - implement Receptionist.addToPendingJobsList
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param job
	 */
	public Job getJob(Job job) {
		// TODO - implement Receptionist.getJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param regNo
	 */
	public Job getJob(String regNo) {
		// TODO - implement Receptionist.getJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param job
	 */
	public Job alterJob(Job job) {
		// TODO - implement Receptionist.alterJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param staffNo
	 */
	public void allocateMechanic(int staffNo) {
		// TODO - implement Receptionist.allocateMechanic
		throw new UnsupportedOperationException();
	}

	public static Receptionist Receptionist() {
		// TODO - implement Receptionist.Receptionist
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sno
	 * @param un
	 * @param n
	 * @param ad
	 * @param t
	 * @param em
	 * @param pc
	 */
	public static Receptionist Receptionist(int sno, String un, String n, String ad, String t, String em, String pc) {
		// TODO - implement Receptionist.Receptionist
		throw new UnsupportedOperationException();
	}

}