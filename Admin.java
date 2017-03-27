/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileFilter;
//import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author sefira karina
 */
public class Admin extends javax.swing.JFrame implements Item {
    
    Connection con ;
    Statement stmt ;
    ResultSet rs ;
    int counter = -1;
    int currentRow  = 1;
    int all =0 ;
    double add = 0;
    String image;
    String imgpath;
    int scb;
    
    DefaultListModel dlm = new DefaultListModel(); 

    public Admin() {
        initComponents();
        connectMe();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    public void connectMe()
    {
        try
        {
           String url = "jdbc:derby://localhost:1527/final" ;//pick data from this database
           String user = "sefirakarina" ;
           String password = "qwerty" ;
       
           con = DriverManager.getConnection(url, user, password) ;
           stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE) ; 
           /*ResultSet.TYPE_SCROLL_INSENSITIVE -> The cursor can scroll forward and backward, and the result set is 
           sensitive to changes made by others to the database that occur after the result set was created.*/
           
           /*ResultSet.CONCUR_UPADABLE -> updateable result set.*/
           String query = "select * from UNTITLED" ;
            
           imgpath = null;
           
           rs = stmt.executeQuery(query) ;
           rs.next() ;
           itemName();
           code();
           stock();
           price();
           listBox();
           picture();
           
        }//try
        catch(SQLException err) 
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }//catch
        
    } //public void connectMe
    //////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void itemName() {
        try 
        {
            String name = rs.getString("name");
            txtName.setText(name);
        } 
        catch(SQLException err) 
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void code() {
        try 
        {
            int code = rs.getInt("barcode");
            txtCode.setText(Integer.toString(code));
        } 
        catch(SQLException err) 
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void stock() {
        try 
        {
            double stock = rs.getDouble("quantity");
            txtStock.setText(Double.toString(stock));
        } 
        catch(SQLException err) 
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void price() {
        try 
        {
            double price = rs.getDouble("price");
            txtPrice.setText(Double.toString(price));
        } 
        catch(SQLException err) 
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////
    //@Override
    public void picture() throws SQLException{
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
         image = rs.getString("image");
         
         System.out.println(image);
         
         if(image != null){
             BufferedImage i = null;
             try{
                 i = ImageIO.read(new File(image));
                 Image img = i.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                 imageLabel.setIcon(new ImageIcon(img));
                 imageLabel.revalidate();
                 imageLabel.repaint();
             }catch(IOException e1){
                 System.out.println("Blank image");
                 
                 try{
                     image = "/placeholder.png";
                     i = ImageIO.read(new File(image));
                     Image img = i.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                     imageLabel.setIcon(new ImageIcon(img));
                     imageLabel.revalidate();
                     imageLabel.repaint();
                 }catch(IOException e2){
                     
                 }
             }
         }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }
    
    //////////////////////////////////////////////////////////////////////////////////////
    
     public void listBox() throws SQLException
    {
        //so if you click name from jlist, the data appear
        try
        {
           rs.absolute(0);
           dlm.clear();
           
            while(rs.next())
            {
                
                String name = rs.getString("name");
                dlm.addElement(name);
                all++;
                counter++;
            }
            jList1.setModel(dlm);
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }//catch
    }
    ///////////////////////////////////////////////////////////////////////////////////
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser2 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        bttPrevious = new javax.swing.JButton();
        bttNext = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        txtCode = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        buttUpdate = new javax.swing.JButton();
        buttAdd = new javax.swing.JButton();
        buttDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        imageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );

        bttPrevious.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bttPrevious.setText("Previous");
        bttPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttPreviousActionPerformed(evt);
            }
        });

        bttNext.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bttNext.setText("Next");
        bttNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttNextActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Item Name :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Stock :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Barcode :");

        txtStock.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });

        txtCode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodeActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Price :");

        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Rp.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttUpdate.setText("Update");
        buttUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttUpdateActionPerformed(evt);
            }
        });

        buttAdd.setText("Add");
        buttAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttAddActionPerformed(evt);
            }
        });

        buttDelete.setText("Delete");
        buttDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttUpdate)
                .addGap(18, 18, 18)
                .addComponent(buttAdd)
                .addGap(18, 18, 18)
                .addComponent(buttDelete)
                .addGap(138, 138, 138))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttUpdate)
                    .addComponent(buttAdd)
                    .addComponent(buttDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(bttPrevious)
                        .addGap(18, 18, 18)
                        .addComponent(bttNext, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttNext)
                    .addComponent(bttPrevious))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        try
        {//so if you click name from the database, the information appear

            currentRow =  jList1.getSelectedIndex() +1 ;
            rs.absolute(currentRow);
            itemName();
            code();
            stock();
            price();

        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }//catch
    }//GEN-LAST:event_jList1MouseClicked

    private void bttPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttPreviousActionPerformed
        try
        {
            if(rs.previous())
            {//display previous data
                itemName();
                code();
                stock();
                price();
            }
            else
            {
                rs.next();
                JOptionPane.showMessageDialog(this,"end of line");//if this is the first data, show this message
            }
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }//catch
    }//GEN-LAST:event_bttPreviousActionPerformed

    private void bttNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttNextActionPerformed
        try
        {   //display next data
            rs.absolute(currentRow);

            if(rs.next())
            {
                itemName();
                code();
                stock();
                price();
            }
            else
            {
                rs.previous();
                JOptionPane.showMessageDialog(this,"end of line"); // if the last data is reached, show this message
            }

            if(currentRow<=all)
            currentRow++;
            else
            {}
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }//catch
    }//GEN-LAST:event_bttNextActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockActionPerformed

    private void txtCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodeActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void buttUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttUpdateActionPerformed
   
        String newItemName = txtName.getText();
        String stringCode = txtCode.getText();
        String stringStock = txtStock.getText();
        String stringPrice = txtPrice.getText();
        
        int newCode = Integer.parseInt(stringCode) ;
        Double newStock = Double.parseDouble(stringStock);
        Double newPrice = Double.parseDouble(stringPrice) ;
       
        try
        {
            rs.absolute(currentRow);
            rs.updateInt("barcode", newCode); 
            rs.updateString("name", newItemName);
            rs.updateDouble("quantity", newStock);
            rs.updateDouble("price", newPrice);
            rs.updateRow();
            jList1.setModel(dlm);
            listBox();//update the data in jlist

            JOptionPane.showMessageDialog(this,"record updated");

        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }//catch
        
        BufferedImage i = null;
        try{
            image = "/placeholder.png";
            i = ImageIO.read(new File(image));
            Image img = i.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            imageLabel.revalidate();
            imageLabel.repaint();
        }catch(IOException e){
             
        }
    }//GEN-LAST:event_buttUpdateActionPerformed

    private void buttAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttAddActionPerformed
        String newItemName = txtName.getText();
        String stringCode = txtCode.getText();
        String stringStock = txtStock.getText();
        String stringPrice = txtPrice.getText();
        
        int newCode = Integer.parseInt(stringCode) ;
        Double newStock = Double.parseDouble(stringStock);
        Double newPrice = Double.parseDouble(stringPrice) ;

        try
        {

            rs.moveToInsertRow();

            rs.updateInt("barcode", newCode); 
            rs.updateString("name", newItemName);
            rs.updateDouble("quantity", newStock);
            rs.updateDouble("price", newPrice);

            rs.insertRow(); // insert data
            stmt.close();
            connectMe();
            rs.next();

            listBox(); // add data to jlist

            JOptionPane.showMessageDialog(this,"record added");

        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }//catch
        
        BufferedImage i = null;
        try{
            image = "/placeholder.png";
            i = ImageIO.read(new File(image));
            Image img = i.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            imageLabel.revalidate();
            imageLabel.repaint();
        }catch(IOException e){
             
        }
    }//GEN-LAST:event_buttAddActionPerformed

    private void buttDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttDeleteActionPerformed
        //delete data from database

      /*  String sid = txtid.getText();
        String sname = txtname.getText();
        String sgpa = txtgpa.getText() ;

        int newID = Integer.parseInt(sid);
        double newgpa = Double.parseDouble(sgpa);*/ 

        try
        {
            rs.deleteRow();//delete data from datavase
            JOptionPane.showMessageDialog(this,"record deleted");
            dlm.removeElement(jList1.getSelectedValue()) ;
            jList1.setModel(dlm);
            listBox(); // delete data from jlist

        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(this,err.getMessage());
        }//catch

    }//GEN-LAST:event_buttDeleteActionPerformed

    private void imageLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageLabelMouseClicked
        // TODO add your handling code here:
        try{
            filechoose = new JFileChooser();
            scb = filechoose.showOpenDialog(Admin.this);
            if(scb == 0){
                File newFile = filechoose.getSelectedFile();
                imgpath = newFile.getAbsolutePath();
                imgpath = imgpath.replace("\\", "/");
                BufferedImage i = null;
                i = ImageIO.read(new File(imgpath));
                Image img = i.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
                imageLabel.revalidate();
                imageLabel.repaint();
            } else {
                //throw new Exception();
            }
        }catch(IOException e){
            System.out.println("Image upload cancelled by user");
        }
    }//GEN-LAST:event_imageLabelMouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttNext;
    private javax.swing.JButton bttPrevious;
    private javax.swing.JButton buttAdd;
    private javax.swing.JButton buttDelete;
    private javax.swing.JButton buttUpdate;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
    private JFileChooser filechoose;
}

