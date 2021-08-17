/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train.ticket.system;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.sql.*;
import javafx.scene.layout.BackgroundImage;
import javax.swing.border.LineBorder;
/**
 *
 * @author HP
 */
public class Admin extends JFrame {
 
    JPanel pnlAdmin, pnlAdd;
    Admin(){

        
        JPanel menu = MenuOptions();
        menu.setVisible(false);
        MenuButton(menu);
        
        pnlAdmin = pnlAdminDefault();
        add(pnlAdmin);
        
        
        
        
        ImageIcon image1=new ImageIcon(this.getClass().getResource("BGIMAGE1.jpg"));
        Image image2=image1.getImage();
        Image image=image2.getScaledInstance(1300, 900, java.awt.Image.SCALE_SMOOTH);
        JLabel background=new JLabel();
        background.setIcon(new ImageIcon(image));
        background.setLayout(new BorderLayout());
        background.setBounds(0, 0, 1300, 900);
          
        getContentPane().add(background);
        
        setBounds(320,120,1300,900);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void MenuButton(JPanel pnlMenu){
        ImageIcon image1=new ImageIcon(this.getClass().getResource("sidebarIcon.png"));
        Image image2=image1.getImage();
        Image image=image2.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        JButton menu = new JButton(new ImageIcon(image));
        
        menu.setBounds(20,10,50,50);
        menu.setBorderPainted(false);
        menu.setBackground(Color.WHITE);
        add(menu);
        
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pnlMenu.isVisible()){
                    pnlMenu.setVisible(false);
                    pnlMenu.setEnabled(false);
//                    pnlAdmin.setEnabled(true);
                }else{
                    pnlMenu.setVisible(true);
                    pnlMenu.setEnabled(true);
//                    pnlAdmin.setEnabled(false);
                }
            }
        });
        
    }
    
    public JPanel MenuOptions(){
        JPanel pnlMenu = new JPanel();
        pnlMenu.setBounds(0,0,400,900);
        pnlMenu.setBackground(Color.WHITE);
        pnlMenu.setLayout(null);
        
        add(pnlMenu);        
     
        
        ImageIcon user = new ImageIcon(this.getClass().getResource("usernameIcon.png"));
        Image user1 = user.getImage();
        Image user2 = user1.getScaledInstance(60, 60,Image.SCALE_SMOOTH);
    
        JLabel lblUser=new JLabel(new ImageIcon(user2));
        lblUser.setBounds(50,200,60,60);       
        pnlMenu.add(lblUser);
        
        JLabel lblName = new JLabel("ADMIN");
        lblName.setBounds(130,210,130,30);
        lblName.setFont(new Font("Verdana", Font.CENTER_BASELINE, 30));
        pnlMenu.add(lblName);
        
        JButton btnAdd = new JButton("ADD TRAIN");
        btnAdd.setBounds(50,270,300,60);
        btnAdd.setFont(new Font("Verdana", Font.CENTER_BASELINE, 21));
        btnAdd.setBackground(Color.WHITE);
        btnAdd.setBorderPainted(false);
        pnlMenu.add(btnAdd);
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlMenu.setVisible(false);
                pnlAdmin.setVisible(false);
                pnlAdd = AdminAddTrain.pnlAdminAdd();
                add(pnlAdd);
               
            }
        });
        
        JButton btnUpdate = new JButton("UPDATE TRAIN");
        btnUpdate.setBounds(50,350,300,60);
        btnUpdate.setFont(new Font("Verdana", Font.CENTER_BASELINE, 21));
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setBorderPainted(false);
        pnlMenu.add(btnUpdate);
        
        JButton btnDelete = new JButton("DELETE TRAIN");
        btnDelete.setBounds(50,420,300,60);
        btnDelete.setFont(new Font("Verdana", Font.CENTER_BASELINE, 21));
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setBorderPainted(false);
        pnlMenu.add(btnDelete);
        
        JButton btnView = new JButton("VIEW TRAIN");
        btnView.setBounds(50,500,300,60);
        btnView.setFont(new Font("Verdana", Font.CENTER_BASELINE, 21));
        btnView.setBackground(Color.WHITE);
        btnView.setBorderPainted(false);
        pnlMenu.add(btnView);
        
        JButton btnSend = new JButton("SEND NOTIFICATIONS");
        btnSend.setBounds(50,570,300,60);
        btnSend.setFont(new Font("Verdana", Font.CENTER_BASELINE, 21));
        btnSend.setBackground(Color.WHITE);
        btnSend.setBorderPainted(false);
        pnlMenu.add(btnSend);
        
        return pnlMenu;
        
    }
    
    public JPanel pnlAdminDefault(){
        JPanel pnlAdmin = new JPanel();
        pnlAdmin.setBounds(0,0,1300,900);
        pnlAdmin.setLayout(null);
        
        JLabel lblDefault = new JLabel("Hey Admin!!!");
        lblDefault.setBounds(470,350,350,70);
        lblDefault.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 50));
        pnlAdmin.add(lblDefault);
        
        JLabel lblDefault1 = new JLabel("Good to See You Again...");
        lblDefault1.setBounds(510,390,350,70);
        lblDefault1.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 20));
        pnlAdmin.add(lblDefault1);
                
        return pnlAdmin;
        
    }
    
   
    
    
    public static void main(String[] args) {
        new Admin();
    }
}


