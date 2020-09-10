package org.servlets.garits.Accounts;

import org.servlets.garits.Job.Task;
import org.servlets.garits.Job.Job;

public class Mechanic extends User {

    public Mechanic(double hourlyRate, int sno, String un, String n,
            String ad, String t, String em, String pc) {
        super(sno, un, n, ad, t, em, pc);
        this.hourlyRate = hourlyRate;
    }

	private double hourlyRate;

	public double getHourlyRate() {
		return this.hourlyRate;
	}

	/**
	 * 
	 * @param hourlyRate
	 */
	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	/**
	 * 
	 * @param job
	 */
	public void alterJob(Job job) {
		// TODO - implement Mechanic.alterJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param job
	 */
	public void PickJob(Job job) {
		// TODO - implement Mechanic.PickJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param job
	 * @param task
	 */
	public void addTask(Job job, Task task) {
		// TODO - implement Mechanic.addTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param job
	 */
	public void finishJob(Job job) {
		// TODO - implement Mechanic.finishJob
		throw new UnsupportedOperationException();
	}

	public static Mechanic Mechanic() {
		// TODO - implement Mechanic.Mechanic
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
	 * @param hourlyRate
	 */
	public Mechanic(int sno, String un, String n, String ad, String t, String em, String pc, double hourlyRate) {
		// TODO - implement Mechanic.Mechanic
	}
        
        public Mechanic(){
            
        }

}