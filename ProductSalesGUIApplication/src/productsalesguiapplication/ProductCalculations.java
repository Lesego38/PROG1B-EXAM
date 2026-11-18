/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productsalesguiapplication;

/**
 *
 * @author Student
 */


public class ProductCalculations implements IProductCalculations {

    private double productPrice;

    //  the constructor
    public ProductCalculations(double productPrice) {
        this.productPrice = productPrice;
    }

     // calculating VAT 15%
    @Override
    public double GetVat() {
        return productPrice * 0.15;
    }

    // calculating commission 5%
    @Override
    public double GetCommission() {
        return productPrice * 0.05;
    }

     // calculating total sales
    @Override
    public double GetTotal() {
        return productPrice + GetVat() + GetCommission();
    }
}

