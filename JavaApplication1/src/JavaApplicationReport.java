/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mangw
 */
import java.util.Scanner;

/**
 *
 * @author mangw
 */
public class JavaApplicationReport.java{
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Mattress Sales Reporting System ===");
            System.out.print("How many mattress products do you want to enter? ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Please enter a valid whole number.");
                scanner.next();
            }
            int numProducts = scanner.nextInt();
            scanner.nextLine(); // clear newline buffer
            
            if (numProducts <= 0) {
                System.out.println("You must enter at least one product. Exiting program.");
                scanner.close();
                return;
            }
            
            // 1D array to store product names
            String[] productNames = new String[numProducts];
            
            // 2D array to store numeric data for each product:
            // Column 0: Quantity Sold
            // Column 1: Unit Price
            // Column 2: Total Sales (Quantity * Unit Price)
            double[][] salesData = new double[numProducts][3];
            
            // Collect inputs dynamically
            for (int i = 0; i < numProducts; i++) {
                System.out.println("\n--- Entering details for Product " + (i + 1) + " ---");
                
                System.out.print("Enter product name (e.g., Queen Memory Foam): ");
                productNames[i] = scanner.nextLine().trim();
                
                // Input validation for quantity
                while (true) {
                    System.out.print("Enter quantity sold: ");
                    if (scanner.hasNextInt()) {
                        int qty = scanner.nextInt();
                        scanner.nextLine();
                        if (qty >= 0) {
                            salesData[i][0] = qty;
                            break;
                        }
                        System.out.println("Error: Quantity cannot be negative.");
                    } else {
                        System.out.println("Error: Enter a valid whole number.");
                        scanner.next();
                    }
                }
                
                // Input validation for unit price
                while (true) {
                    System.out.print("Enter unit price (R): ");
                    if (scanner.hasNextDouble()) {
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        if (price >= 0) {
                            salesData[i][1] = price;
                            break;
                        }
                        System.out.println("Error: Price cannot be negative.");
                    } else {
                        System.out.println("Error: Enter a valid number.");
                        scanner.next();
                    }
                }
                
                // Calculate total sales for this specific row and store it in column 2
                salesData[i][2] = salesData[i][0] * salesData[i][1];
            }
            
            // Calculations
            double totalProductsSold = 0;
            double maxSales = -1;
            int topProductIndex = 0;
            
            for (int i = 0; i < numProducts; i++) {
                totalProductsSold += salesData[i][0]; // accumulate quantity
                
                // Check for highest total sales to find the most popular product
                if (salesData[i][2] > maxSales) {
                    maxSales = salesData[i][2];
                    topProductIndex = i;
                }
            }
            
            double averageSoldPerProduct = totalProductsSold / numProducts;
            
            // Display the Final Report
            System.out.println("\n========================================================");
            System.out.println("                 MATTRESS SALES REPORT                  ");
            System.out.println("========================================================");
            System.out.printf("%-18s %-10s %-12s %-12s%n", "Product", "Qty Sold", "Unit Price", "Total Sales");
            System.out.println("--------------------------------------------------------");
            
            for (int i = 0; i < numProducts; i++) {
                System.out.printf("%-18s %-10.0f R%-11.2f R%-11.2f%n",
                        productNames[i],
                        salesData[i][0],
                        salesData[i][1],
                        salesData[i][2]);
            }
            
            System.out.println("--------------------------------------------------------");
            System.out.printf("Total number of products sold : %.0f%n", totalProductsSold);
            System.out.printf("Average sold per product      : %.2f%n", averageSoldPerProduct);
            System.out.printf("Most popular product (sales)  : %s (R%.2f)%n",
                    productNames[topProductIndex], salesData[topProductIndex][2]);
            System.out.println("========================================================");
        }
    }
}