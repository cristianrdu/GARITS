/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.StockControl.Payments;

import java.sql.Date;
import java.util.ArrayList;
import org.servlets.garits.StockControl.SparePart;

/**
 *
 * @author glaxo
 */
public class SparePartPayment implements Payment {

    private ArrayList<SparePart> sp;
    private String paymentMethod;
    private Date paymentDate;

    public SparePartPayment(ArrayList<SparePart> sp, String paymentMethod) {
        this.sp = sp;
        this.paymentMethod = paymentMethod;
    }

    public SparePartPayment() {
    }

    public ArrayList<SparePart> getSp() {
        return sp;
    }

    public void setSp(ArrayList<SparePart> sp) {
        this.sp = sp;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public double calculateTotal() {
        double total = 0;

        for (SparePart s : sp) {
            total = total + s.getTotalCost();
        }

        total = total + (total * VAT) + (total * 0.3);
        return total;
    }

    public double TotalExcludingVat() {
        double total = 0;
        for (SparePart s : sp) {
            total = total + s.getTotalCost() + (total * 0.3);
        }
        return total;
    }
    
    public double VAT() {
        double total = 0;
        for (SparePart s : sp) {
            total = total + s.getTotalCost();
        }
        total = total * VAT;
        return total;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

}
