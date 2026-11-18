/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productsalesapplication;

/**
 *
 * @author Student
 */


public class PrintProduct {

      // printing of monthly sales and products
    public void PrintProductSales(
            String[] productNames,
            int[][] productSales) {

        Product product = new Product(
            productNames, productSales
        );

        System.out.println("\nPRODUCT MONTHLY SALES");
        System.out.println("----------------------------------------");

        for (int i = 0; i < productNames.length; i++) {

            System.out.println(productNames[i]);

            for (int j = 0; j < productSales[i].length; j++) {

                System.out.println(
                    "Month " + (j + 1) + ": "
                    + productSales[i][j]
                );
            }

            System.out.println(
                "Product Total: " + product.GetProductTotal(i)
            );

            System.out.println();
        }
    }

     // printing of total sales
    public void PrintTotalProductSales(Product product) {

        System.out.println(
            "TOTAL PRODUCT SALES: "
            + product.GetTotalProductsSold()
        );
    }

      //printing of average product sales
    public void PrintAverageProductSales(Product product) {

        System.out.printf(
            "AVERAGE PRODUCT SALES: %.0f%n",
            product.GetAverageProductSold()
        );
    }

     // printing of most popular product
    public void PrintMostPopularProduct(Product product) {

        System.out.println(
            "MOST POPULAR PRODUCT: "
            + product.GetMostPopularProduct()
        );
    }
}
