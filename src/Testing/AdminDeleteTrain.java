/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;
import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author HP
 */
public class AdminDeleteTrain {
    JFrame DeleteFrame = new JFrame("Delete Train Route");
    JPanel pnlDelete = new JPanel();
    JLabel lblTrainNumber = new JLabel("Train Number");
    JTextField txtTrainNumber = new JTextField();
    JButton btnDelete = new JButton("Delete Train Route");
    
    AdminDeleteTrain(){
        setLayoutManager();
        setLocationAndSize();
        addComponentsToPanel();
        DeleteFrame.setVisible(true);
        DeleteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        DeleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DeleteFrame.setResizable(true); 
        DeleteFrame.add(pnlDelete);
        addEvent();
    }
    
    public void setLayoutManager(){
        pnlDelete.setLayout(null);
    }
    
    public void setLocationAndSize(){
        lblTrainNumber.setBounds(800,300,100,30);
        txtTrainNumber.setBounds(1000,300,150,30);
        btnDelete.setBounds(900,350,150,30);
    }
    
    public void addComponentsToPanel(){
        pnlDelete.add(lblTrainNumber);
        pnlDelete.add(txtTrainNumber);
        pnlDelete.add(btnDelete);
        
    }
    
    public void addEvent(){
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int TrainNumber = Integer.parseInt(txtTrainNumber.getText());
                try {
                    DatabaseConnection db = new DatabaseConnection();
                    String query = "delete from Train where TrainNumber =?";
                    
                    PreparedStatement pstmt = db.con.prepareStatement(query);
                    pstmt.setInt(1, TrainNumber);
                    
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Train Route Deleted");
                    db.con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.toString());
                }
                txtTrainNumber.setText("");
            }
        });
    }
    
    public static void main(String[] args) {
        new AdminDeleteTrain();
    }
    
}
