/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package productsalesapplication;

/**
 *
 * @author Student
 */

import java.util.Scanner;

public class ProductSalesApplication {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // one-dimensional array with product names
        String[] productNames = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phukhet"
        };

            // two-dimensional array with product sales
        int[][] productSales = new int[3][3];

               // sales for 3 months
        for (int i = 0; i < productNames.length; i++) {

            for (int j = 0; j < 3; j++) {

                System.out.print(
                    "Enter number of sales for month "
                    + (j + 1) + " for "
                    + productNames[i] + " mattress: "
                );

                productSales[i][j] = input.nextInt();

                while (productSales[i][j] < 0) {
                    System.out.print(
                        "Sales cannot be negative. Enter again: "
                    );
                    productSales[i][j] = input.nextInt();
                }
            }

            System.out.println();
        }

            // product objects
        Product product = new Product(
            productNames, productSales
        );

            // printproduct objects
        PrintProduct printer = new PrintProduct();

            // display the product sales report
        System.out.println("\nPRODUCT SALES REPORT - 2026");
        System.out.println("****************************************");

        printer.PrintProductSales(productNames, productSales);

        printer.PrintTotalProductSales(product);

        printer.PrintAverageProductSales(product);

        printer.PrintMostPopularProduct(product);

        input.close();
    }
}
