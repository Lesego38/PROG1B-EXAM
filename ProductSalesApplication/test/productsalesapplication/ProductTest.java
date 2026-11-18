/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package productsalesapplication;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */


public class ProductTest {

      // unit test 1 - total product sales
    @Test
    public void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {

        String[] productNames = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phukhet"
        };

        int[][] productSales = {
            {25, 18, 20},
            {15, 10, 12},
            {18, 15, 20}
        };

        Product product = new Product(
            productNames, productSales
        );

        assertEquals(
            153,
            product.GetTotalProductsSold()
        );
    }

     // unit test 2 - average product sales 
    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {

        String[] productNames = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phukhet"
        };

        int[][] productSales = {
            {25, 18, 20},
            {15, 10, 12},
            {18, 15, 20}
        };

        Product product = new Product(
            productNames, productSales
        );

        assertEquals(
            17.0,
            product.GetAverageProductSold(),
            0.01
        );
    }
}
