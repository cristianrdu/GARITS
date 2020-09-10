/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.StockControl;

/**
 *
 * @author glaxo
 */
public class SparePart {
    
    private Stock stock;
    private int quantity;

    public SparePart(Stock stock, int quantity) {
        this.stock = stock;
        this.quantity = quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return  stock + ", " + quantity;
    }

    public double getTotalCost() {
        return quantity * stock.getUnitCost();
    }
    
    
    
    
}
