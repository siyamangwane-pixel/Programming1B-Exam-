/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mangw
 */
import static java.lang.IO.print;
import java.util.Scanner;

// The interface
interface ProductsSold {
    int GetTotalProductSold();
    double GetAverageProductSold();
    String GetMostPopularProduct();   
}

// Class implementing the ProductsSold interface
class Productsold implements ProductsSold {
    private final String[] productNames;
    private final double[][] salesData; 
    // Column 0: Quantity Sold
    // Column 1: Unit Price
    // Column 2: Total Sales (Quantity * Unit Price)

    public Productsold(String[] productNames, double[][] salesData) {
        this.productNames = productNames;
        this.salesData = salesData;
    }

    @Override
    public int GetTotalProductSold() {
        int total = 0;
        for (double[] salesData1 : salesData) {
            total += (int) salesData1[0];
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        if (productNames.length == 0) {
            return 0.0;
        }
        return (double) GetTotalProductSold() / productNames.length;
    }

    @Override
    public String GetMostPopularProduct() {
        if (salesData.length == 0) {
            return "None";
        }

        double maxSales = salesData[0][2];
        int topIndex = 0;

        for (int i = 1; i < salesData.length; i++) {
            if (salesData[i][2] > maxSales) {
                maxSales = salesData[i][2];
                topIndex = i;
            }
        }
        return productNames[topIndex];
    }
}

// New PrintProduct class responsible for output formatting and display
class PrintProduct {
    
    public void printTotalSales(int total) {
        System.out.printf("Total number of products sold : %d%n", total);
    }

    public void printAverageSales(double average) {
        System.out.printf("Average sold per product      : %.2f%n", average);
    }

    public void printMostPopularProduct(String product) {
        System.out.printf("Most popular product          : %s%n", product);
    }

    // Method to coordinate the full report display
    public void displayReport(String[] names, double[][] data, ProductsSold report) {
        System.out.println("\n========================================================");
        System.out.println("                 MATTRESS SALES REPORT                  ");
        System.out.println("========================================================");
        System.out.printf("%-18s %-10s %-12s %-12s%n", "Product", "Qty Sold", "Unit Price", "Total Sales");
        System.out.println("--------------------------------------------------------");

        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-18s %-10.0f R%-11.2f R%-11.2f%n",
                    names[i], data[i][0], data[i][1], data[i][2]);
        }

        System.out.println("--------------------------------------------------------");
        printTotalSales(report.GetTotalProductSold());
        printAverageSales(report.GetAverageProductSold());
        printMostPopularProduct(report.GetMostPopularProduct());
        System.out.println("========================================================");
    }
}

public class JavaApplicatinReport.java {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Mattress Sales Reporting System ===");
            
            
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Enter a valid whole number.");
                scanner.next();
            }
            int numProducts = scanner.nextInt();
            scanner.nextLine(); // clear newline buffer
            
            if (numProducts <= 0) {
                System.out.println("You must enter at least one product. Exiting.");
                scanner.close();
                return;
            }
            
            // 1D array for names
            String[] names = new String[numProducts];
            
            // 2D array for metrics
            double[][] data = new double[numProducts][3];
            
            for (int i = 0; i < numProducts; i++) {
                System.out.println("\n--- Entering details for Product " + (i + 1) + " ---");
                
                System.out.print("Enter mattress name: ");
                names[i] = scanner.nextLine().trim();
                
                while (true) {
                    System.out.print("Enter quantity sold: ");
                    if (scanner.hasNextInt()) {
                        int qty = scanner.nextInt();
                        scanner.nextLine();
                        if (qty >= 0) {
                            data[i][0] = qty;
                            break;
                        }
                        System.out.println("Error: Quantity cannot be negative.");
                    } else {
                        System.out.println("Error: Enter a valid whole number.");
                        scanner.next();
                    }
                }
                
                while (true) {
                    System.out.print("Enter unit price (R): ");
                    if (scanner.hasNextDouble()) {
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        if (price >= 0) {
                            data[i][1] = price;
                            break;
                        }
                        System.out.println("Error: Price cannot be negative.");
                    } else {
                        System.out.println("Error: Enter a valid number.");
                        scanner.next();
                    }
                }
                
                data[i][2] = data[i][0] * data[i][1];
            }
            
            // Instantiate logic and printer classes
            ProductsSold reportData = new Productsold(names, data);
            PrintProduct printer = new PrintProduct();
            
            // Print final report
            printer.displayReport(names, data, reportData);
        }
    }
}
   

