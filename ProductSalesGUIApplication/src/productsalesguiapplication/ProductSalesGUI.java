/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package productsalesguiapplication;

/**
 *
 * @author Student
 */


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;

public class ProductSalesGUI extends JFrame {

    // GUI components
    private JTextField txtCustomerName;
    private JTextField txtPrice;
    private JComboBox<String> cmbProduct;
    private JButton btnProcess;

    private JMenuItem mnuExit;
    private JMenuItem mnuProcess;
    private JMenuItem mnuClear;

    private final DecimalFormat moneyFormat =
            new DecimalFormat("0.0");

    // Constructor
    public ProductSalesGUI() {

        setTitle("Product App");
        setSize(450, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createMenu();
        createInterface();
    }

    // Create the menu system
    private void createMenu() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");

        mnuExit = new JMenuItem("Exit");
        mnuProcess = new JMenuItem("Process");
        mnuClear = new JMenuItem("Clear");

        // Exit menu action
        mnuExit.addActionListener(e -> System.exit(0));

        // Process menu action
        mnuProcess.addActionListener(e -> processSale());

        // Clear menu action
        mnuClear.addActionListener(e -> clearForm());

        fileMenu.add(mnuExit);

        toolsMenu.add(mnuProcess);
        toolsMenu.add(mnuClear);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);
    }

    // Create the graphical interface
    private void createInterface() {

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 8, 10, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Customer name label
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("Customer Name:"), gbc);

        // Customer name text field
        txtCustomerName = new JTextField(18);

        gbc.gridx = 1;
        gbc.gridy = 0;

        panel.add(txtCustomerName, gbc);

        // Product label
        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(new JLabel("Select Product:"), gbc);

        // Product combo box
        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phukhet"
        };

        cmbProduct = new JComboBox<>(products);

        gbc.gridx = 1;
        gbc.gridy = 1;

        panel.add(cmbProduct, gbc);

        // Price label
        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(new JLabel("Enter Price:"), gbc);

        // Price text field
        txtPrice = new JTextField(18);

        gbc.gridx = 1;
        gbc.gridy = 2;

        panel.add(txtPrice, gbc);

        // Process button
        btnProcess = new JButton("PROCESS");

        btnProcess.addActionListener(e -> processSale());

        gbc.gridx = 1;
        gbc.gridy = 3;

        panel.add(btnProcess, gbc);

        add(panel);
    }

    // Process the product sale
    private void processSale() {

        try {

            // Get customer name
            String customerName =
                    txtCustomerName.getText().trim();

            // Get selected product
            String productName =
                    (String) cmbProduct.getSelectedItem();

            // Get product price
            String priceText = txtPrice.getText().trim();

            // Validate customer name
            if (customerName.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the customer name."
                );

                return;
            }

            // Validate price
            if (priceText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the product price."
                );

                return;
            }

            double price = Double.parseDouble(priceText);

            if (!Double.isFinite(price) || price <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid price greater than zero."
                );

                return;
            }

            // Create calculation object
            ProductCalculations calculation =
                    new ProductCalculations(price);

            // Calculate VAT, commission and total
            double vat = calculation.GetVat();

            double commission =
                    calculation.GetCommission();

            double total = calculation.GetTotal();

            // Display the results
            String message =
                    "Customer Name: " + customerName
                    + "\nProduct: " + productName
                    + "\nVAT: R " + moneyFormat.format(vat)
                    + "\nCOMMISSION: R "
                    + moneyFormat.format(commission)
                    + "\nTOTAL: R "
                    + moneyFormat.format(total);

            JOptionPane.showMessageDialog(
                    this,
                    message,
                    "Product Sales",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Save information to data.txt
            saveToFile(
                    customerName,
                    productName,
                    vat,
                    commission,
                    total
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter numbers only for the product price."
            );

        }
    }

    // Save sales information to data.txt
    private void saveToFile(
            String customerName,
            String productName,
            double vat,
            double commission,
            double total) {

        try (PrintWriter writer = new PrintWriter(
                new FileWriter("data.txt", true))) {

            writer.println("********************");
            writer.println("DATA LOG");
            writer.println("Customer Name: " + customerName);
            writer.println("Product: " + productName);
            writer.println("VAT: R " + vat);
            writer.println("COMMISSION: R " + commission);
            writer.println("TOTAL: R " + total);
            writer.println("********************");
            writer.println();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to save data.txt: " + e.getMessage()
            );
        }
    }

    // Clear all fields
    private void clearForm() {

        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new ProductSalesGUI().setVisible(true);

        });
    }
}



   


        
                
              
           



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Ib1CustomerName = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        Ib1Poduct = new javax.swing.JLabel();
        cmbProduct = new javax.swing.JComboBox<>();
        Ib1Price = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        btnProcess = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mnuFile = new javax.swing.JMenu();
        mnuExist = new javax.swing.JMenuItem();
        mnuTools = new javax.swing.JMenu();
        mnuProcess = new javax.swing.JMenuItem();
        mnuClear = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PRODUCT APP");

        Ib1CustomerName.setText("CUSTOMER NAME: ");

        txtCustomerName.setText("jTextField1");

        Ib1Poduct.setText("PRODUCT NAME: ");

        cmbProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 .Cozy Nights Monarch", "2 .Restonic Barbados", "3. Sealy Phukhet" }));

        Ib1Price.setText("PRICE: ");

        txtPrice.setText("jTextField1");

        btnProcess.setText("PROCESS");
        btnProcess.addActionListener(this::btnProcessActionPerformed);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem1.setText("jMenuItem1");
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        mnuFile.setText("File");

        mnuExist.setText("Exit");
        mnuExist.addActionListener(this::mnuExistActionPerformed);
        mnuFile.add(mnuExist);

        jMenuBar1.add(mnuFile);

        mnuTools.setText("Tools");

        mnuProcess.setText("Process");
        mnuProcess.addActionListener(this::mnuProcessActionPerformed);
        mnuTools.add(mnuProcess);

        mnuClear.setText("Clear");
        mnuClear.addActionListener(this::mnuClearActionPerformed);
        mnuTools.add(mnuClear);

        jMenuBar1.add(mnuTools);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Ib1CustomerName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Ib1Poduct)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(Ib1Price)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnProcess)
                        .addGap(90, 90, 90)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ib1CustomerName)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ib1Poduct)
                    .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ib1Price)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProcess)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_btnProcessActionPerformed

    private void mnuExistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuExistActionPerformed

    private void mnuProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProcessActionPerformed
        // TODO add your handling code here:
   
    }//GEN-LAST:event_mnuProcessActionPerformed

    private void mnuClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClearActionPerformed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_mnuClearActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ProductSalesGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ib1CustomerName;
    private javax.swing.JLabel Ib1Poduct;
    private javax.swing.JLabel Ib1Price;
    private javax.swing.JButton btnProcess;
    private javax.swing.JComboBox<String> cmbProduct;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem mnuClear;
    private javax.swing.JMenuItem mnuExist;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuProcess;
    private javax.swing.JMenu mnuTools;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables


