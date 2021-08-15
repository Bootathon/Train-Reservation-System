/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;
import com.sun.org.apache.xerces.internal.impl.dtd.models.CMBinOp;
import java.util.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.layout.Border;
import javax.mail.*;
import javax.swing.*;
import java.awt.event.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author HP
 */
public class AdminSendNotification {
    JFrame frame1 = new JFrame("Send Notification");
    JPanel pnlNotification = new JPanel();
    JLabel lblTo = new JLabel("TO");
    JLabel lblSubject = new JLabel("SUBJECT");
    JLabel lblContent = new JLabel("CONTENT");
    
    JComboBox cmbTo = new JComboBox();
    JTextField txtSubject = new JTextField();
    JTextArea txtContent = new JTextArea(30,25);
    
    JButton btnSend = new JButton("SEND MAIL");
    
    AdminSendNotification() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToPanel();
        frame1.setVisible(true);
        frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(true); 
        frame1.add(pnlNotification);
        cmbTo.addItem("All");
        for(String s : getTrainNames()){
            cmbTo.addItem(s);
        }
        addActionEvent();
        
       
        
    }
    
    
    
    
    public void setLayoutManager(){
        pnlNotification.setLayout(null);
    }
    
    public void setLocationAndSize(){
        lblTo.setBounds(600,300,100,30);
        cmbTo.setBounds(800,300,350,30);
        lblSubject.setBounds(600,350,100,30);
        txtSubject.setBounds(800,350,350,30);
        lblContent.setBounds(600,400,100,30);
        txtContent.setBounds(800, 400,350,200);
        txtContent.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnSend.setBounds(800,650,100,30);
        
    }
    
    public void addComponentsToPanel(){
        pnlNotification.add(lblTo);
        pnlNotification.add(cmbTo);
        pnlNotification.add(lblSubject);
        pnlNotification.add(txtSubject);
        pnlNotification.add(txtContent);
        pnlNotification.add(lblContent);
        pnlNotification.add(btnSend);
        
    }
    
    public void addActionEvent(){
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            System.out.print("button");
            String TrainName = cmbTo.getSelectedItem().toString();
            System.out.println(TrainName);
            String Subject = txtSubject.getText();
            String Content = txtContent.getText();
            
            ArrayList<String> EmailIds = getEmail(TrainName);
            
            final String adminMail="railwayrservation@gmail.com";
            final String adminPassword="Bootathon#2021";
            
            Properties property = new Properties();
            property.put("mail.smtp.auth", "true");
            property.put("mail.smtp.starttls.enable", "true");
            property.put("mail.smtp.host", "smtp.gmail.com");
            property.put("mail.smtp.port", "587");
            
            Session session = Session.getDefaultInstance(property, new Authenticator() {
                    @Override         
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(adminMail,adminPassword);
                    }
            });
            
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(adminMail));
                
                InternetAddress[] bcc = new InternetAddress[EmailIds.size()];
                
                for(int i=0;i<EmailIds.size();i++){
                    bcc[i] = new InternetAddress(EmailIds.get(i));
                }
                
                for( int i = 0; i < bcc.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, bcc[i]);
                }
                
                
//                for(String recepient : EmailIds){
//                    System.out.print(recepient+" ");
//                    message.addRecipient(Message.RecipientType.BCC,new InternetAddress(recepient));
//                }
                
                message.setSubject(Subject);
                message.setText(Content);
                
                Transport.send(message);
                System.out.println("Sent Successfully");
                JOptionPane.showMessageDialog(null,"Email Sent Successfully");
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex.toString());
            }
            
            
            
        } 
        });
    }
    
    public static ArrayList<String> getTrainNames(){
        ArrayList<String> TrainNames = new ArrayList<>();
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/TrainTicketReservationSystem", "root", "Saju#123");
            DatabaseConnection db = new DatabaseConnection();
            System.out.println("Connection Success");
            String query = "select TrainName from Train";
            
            PreparedStatement p = db.con.prepareStatement(query);
            
            ResultSet rs = p.executeQuery();
            
            while(rs.next()){
                TrainNames.add(rs.getString("TrainName"));
            }
            
            db.con.close();
            
//            System.out.print(TrainNames);
            
            return TrainNames;
            
        } catch (Exception e) {
            return TrainNames;
        }
    }
    
    public static ArrayList<String> getEmail(String TrainName){
        ArrayList<String> Emails = new ArrayList<>();
        if(TrainName.equals("All")){
            try {
                DatabaseConnection db = new DatabaseConnection();
                System.out.println("Connection Success");
                
                String query = "select Email from Client where UserName in(select UserName from Passengers where TrainNumber in(select TrainNumber from Train));";
                Statement stmt = db.con.prepareStatement(query);
                
                ResultSet rs = stmt.executeQuery(query);
                
                while(rs.next()){
                    Emails.add(rs.getString("Email"));
                }
                System.out.println(Emails);
                
            }
                
                
            catch (Exception e) {
                System.out.println(e);
                
            }
            return Emails;
        }else{
            try {
                DatabaseConnection db = new DatabaseConnection();
                System.out.println("Connection Success");
                
                String query = "select Email from Client where UserName in(select UserName from Passengers where TrainNumber in(select TrainNumber from Train Where TrainName = ?));";
                PreparedStatement p = db.con.prepareStatement(query);
                
                p.setString(1, TrainName);
                
                ResultSet rs = p.executeQuery(query);
                
                while(rs.next()){
                    Emails.add(rs.getString("Email"));
                }
                System.out.println(Emails);
                
            }
                
                
            catch (Exception e) {
                System.out.println(e);
                
            }
            return Emails;
        }
        
    }

   
    
    
    public static void main(String[] args) {
        new AdminSendNotification();
          
          
          
    }
    
    
    
}
