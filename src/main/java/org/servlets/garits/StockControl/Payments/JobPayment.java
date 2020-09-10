/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.StockControl.Payments;

import org.servlets.garits.Job.Job;

/**
 *
 * @author glaxo
 */
public class JobPayment implements Payment {
    
    private Job job;
    private String paymentMethod;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public JobPayment() {
    }

    public JobPayment(Job job, String paymentMethod) {
        this.job = job;
        this.paymentMethod = paymentMethod;
    }
    
    
    
    
    
    
    
    
    @Override
    public double calculateTotal() {
        double totalCost = 0;
        
        
        
        return totalCost;
    }
    
}
