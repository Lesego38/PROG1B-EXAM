/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productsalesapplication;

/**
 *
 * @author Student
 */


public class Product implements IProduct {

    private String[] productNames;
    private int[][] productSales;

      // these are the constractors
    public Product(String[] productNames,
                   int[][] productSales) {

        this.productNames = productNames;
        this.productSales = productSales;
    }

      // calculation of total sales
    @Override
    public int GetTotalProductsSold() {

        int total = 0;

        for (int i = 0; i < productSales.length; i++) {

            for (int j = 0; j < productSales[i].length; j++) {

                total += productSales[i][j];
            }
        }

        return total;
    }

      // calculation of product sold
    @Override
    public double GetAverageProductSold() {

        int total = GetTotalProductsSold();

        int numberOfSales = 0;

        for (int i = 0; i < productSales.length; i++) {
            numberOfSales += productSales[i].length;
        }

        if (numberOfSales == 0) {
            return 0;
        }

        return (double) total / numberOfSales;
    }

        // most popular product
    @Override
    public String GetMostPopularProduct() {

        int highestSales = -1;
        String popularProduct = "";

        for (int i = 0; i < productSales.length; i++) {

            int productTotal = 0;

            for (int j = 0; j < productSales[i].length; j++) {

                productTotal += productSales[i][j];
            }

            if (productTotal > highestSales) {

                highestSales = productTotal;

                popularProduct = productNames[i];
            }
        }

        return popularProduct;
    }

      // return sales for each product
    public int GetProductTotal(int index) {

        int total = 0;

        for (int j = 0; j < productSales[index].length; j++) {

            total += productSales[index][j];
        }

        return total;
    }
}

