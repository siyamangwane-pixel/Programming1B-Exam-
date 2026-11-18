/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.JavaBasedGUIApplication.java;

/**
 *
 * @author mangw
 */
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProductSalesGUI extends JFrame {
    private final JTextField txtCustomer;
    private final JComboBox<String> cmbProduct;
    private final JTextField txtPrice;
    private final JButton btnProcess;
    private final JTextArea txtArea;
    private final JLabel lblRecords;

    private final ArrayList<String> customerNames = new ArrayList<>();
    private final ArrayList<String> productNames = new ArrayList<>();
    private final ArrayList<Double> basePrices = new ArrayList<>();

    public ProductSalesGUI() {
        setTitle("Mattress Sales Reporting Application");
        setSize(550, 510);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);
        add(panel);

        // Customer Name Label & Text Box
        JLabel lblCustomer = new JLabel("Customer Name:");
        lblCustomer.setBounds(30, 20, 130, 25);
        panel.add(lblCustomer);

        txtCustomer = new JTextField();
        txtCustomer.setBounds(170, 20, 320, 25);
        panel.add(txtCustomer);

        // Product Name Label & Combo Box
        JLabel lblProduct = new JLabel("Product Name:");
        lblProduct.setBounds(30, 60, 130, 25);
        panel.add(lblProduct);

        String[] availableProducts = {
            "Cozy Nights monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };
        cmbProduct = new JComboBox<>(availableProducts);
        cmbProduct.setBounds(170, 60, 320, 25);
        panel.add(cmbProduct);

        // Product Price Label & Text Box
        JLabel lblPrice = new JLabel("Product Price (R):");
        lblPrice.setBounds(30, 100, 130, 25);
        panel.add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(170, 100, 320, 25);
        panel.add(txtPrice);

        // Process Button
        btnProcess = new JButton("Process");
        btnProcess.setBounds(30, 145, 460, 35);
        panel.add(btnProcess);

        // Text Area for Report Display
        txtArea = new JTextArea();
        txtArea.setEditable(false);
        txtArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(txtArea);
        scrollPane.setBounds(30, 195, 460, 205);
        panel.add(scrollPane);

        // Records Processed Label
        lblRecords = new JLabel("Records Processed: 0");
        lblRecords.setBounds(30, 415, 300, 25);
        panel.add(lblRecords);

        // Button Event Listener
        btnProcess.addActionListener(e -> processRecord());
    }

    // Capture inputs, calculate, update display, and save to data.txt
    private void processRecord() {
        String customer = txtCustomer.getText().trim();
        String product = (String) cmbProduct.getSelectedItem();
        String priceStr = txtPrice.getText().trim();

        if (customer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a customer name.");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            if (price < 0) {
                JOptionPane.showMessageDialog(this, "Price cannot be negative.");
                return;
            }

            // Store data dynamically
            customerNames.add(customer);
            productNames.add(product);
            basePrices.add(price);

            updateReportDisplay();
            saveDataToFile();

            // Clear inputs for the next entry
            txtCustomer.setText("");
            txtPrice.setText("");
            txtCustomer.requestFocus();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.");
        }
    }

    // Calculate VAT, Commission, Totals and refresh the text area
    private void updateReportDisplay() {
        if (customerNames.isEmpty()) {
            txtArea.setText("");
            lblRecords.setText("Records Processed: 0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-14s %-20s %-10s %-10s %-12s%n", "Customer", "Product", "VAT(15%)", "Comm(5%)", "Total(R)"));
        sb.append("----------------------------------------------------------------------------\n");

        double totalSalesAll = 0;

        for (int i = 0; i < customerNames.size(); i++) {
            double price = basePrices.get(i);
            double vat = price * 0.15;
            double commission = price * 0.05;
            double totalSale = price + vat + commission;
            totalSalesAll += totalSale;

            sb.append(String.format("%-14s %-20s R%-9.2f R%-9.2f R%-11.2f%n",
                    customerNames.get(i),
                    productNames.get(i),
                    vat,
                    commission,
                    totalSale));
        }

        sb.append("----------------------------------------------------------------------------\n");
        sb.append(String.format("Overall Total Sales Amount : R%.2f%n", totalSalesAll));
        sb.append(String.format("Overall Average Sale Amount: R%.2f", totalSalesAll / customerNames.size()));

        txtArea.setText(sb.toString());
        lblRecords.setText("Records Processed: " + customerNames.size());
    }

    // Automatically save report to data.txt when processed
    private void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt"))) {
            writer.println("MATTRESS SALES REPORT LOG");
            writer.println("****************************************************************************");
            writer.println(txtArea.getText());
            writer.println("****************************************************************************");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductSalesGUI().setVisible(true);
        });
    }
}