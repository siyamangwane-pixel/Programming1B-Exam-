/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.JavaBasedGUIApplication;

/**
 *
 * @author mangw
 */
public class ProductCalculations implements IProductCalculations {
    private final double price;

    // Constructor to receive the product base price dynamically
    public ProductCalculations(double price) {
        this.price = price;
    }

    @Override
    public double GetVat() {
        // VAT is 15% of the product price
        return price * 0.15;
    }

    @Override
    public double GetCommission() {
        // Commission is 5% of the product price
        return price * 0.05;
    }

    @Override
    public double GetTotal() {
        // Total sale amount = Price + VAT + Commission
        return price + GetVat() + GetCommission();
    }
}
