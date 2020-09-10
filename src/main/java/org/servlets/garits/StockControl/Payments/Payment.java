/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.StockControl.Payments;

/**
 *
 * @author glaxo
 */
public interface Payment {

    final double VAT = 0.2;

    public abstract double calculateTotal();

}
