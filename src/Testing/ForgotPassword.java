/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainreservation;
import java.awt.event.*;
import javax.swing.*;
import javax.mail.*;
import java.sql.*;
import java.util.Properties;
import javax.mail.internet.*;

/**
 *
 * @author menag
 */
public class ForgotPassword implements ActionListener{
    JFrame frame;
    JPanel pnlForgotPass=new JPanel();
    JLabel header=new JLabel("RECOVER USERNAME / PASSWORD");
    JLabel subheading=new JLabel("Don't worry,happens to the best of us");
    JLabel  lblEmail=new JLabel("Your Email");
    JTextField txtEmail=new JTextField();
    JButton btnSubmit =new JButton("Email me my Credentials");

    public ForgotPassword(JFrame frame) 
    {
        this.frame=frame;
        frame.add(pnlForgotPass);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToPanel();
        addActionEvent();
        
        frame.setTitle("Registration Form");
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public void setLayoutManager()
    {
        pnlForgotPass.setLayout(null);
    }
    public void setLocationAndSize()
    {
        header.setBounds(900,300,200,40);
        subheading.setBounds(890,350,300,40);
        lblEmail.setBounds(850,500,200,40);
        txtEmail.setBounds(850,550,300,40);
        btnSubmit.setBounds(900,600,200,30);
    }
    public void addComponentsToPanel()
    {
       pnlForgotPass.add(header);  
       pnlForgotPass.add(subheading);
       pnlForgotPass.add(lblEmail);
       pnlForgotPass.add(txtEmail);
       pnlForgotPass.add(btnSubmit);
    }
    public void addActionEvent()
    {
        btnSubmit.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==btnSubmit)
        {
            String email=txtEmail.getText();
            if(Validate.validateEmail(email))
            {
                try {
                    DatabaseConnection db=new DatabaseConnection();
                    String query="Select Username,Password,Name from client where Email=?";
                    PreparedStatement pStmt=db.con.prepareStatement(query);
                    pStmt.setString(1,email);
                    
                    ResultSet rs=pStmt.executeQuery();
                    if(rs.next())
                    {
                        final String USERNAME=rs.getString("username");
                        final String PASSWORD=rs.getString("password");
                        final String NAME=rs.getString("name");
                        System.out.println(USERNAME+" "+PASSWORD+" "+NAME);
                        sendMail(email,USERNAME,PASSWORD,NAME);
                        
                        JOptionPane.showMessageDialog(null,"Email sent Successfully");
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"User Not Found");
                    }
                
                } 
                catch (Exception exception) {
                    System.out.println(exception.toString());
                }     
            }
        }
    }
    public void sendMail(String recepient,String Username,String Password,String Name)
    {
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
           try 
           {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(adminMail));
                message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
                message.setSubject("Railway Reservation System Email Verification");
               String content = "<h3>Hello "+ Name +",</h3>"
                    + "<p> Your Credentials are attached below</p>"
                    + "<div style=\"background-color:coral;padding:50;margin: 100;margin-right: 70%;\">"
                    + "<h2 style=\"background-color: azure;\">User Name : "+Username+"</h2>"
                    +"<h2 style=\"background-color: azure;\">Password : "+Password+"</h2>"
                    +"</div>";
                message.setContent(content,"text/html");
                
                Transport.send(message);
                System.out.println("Sent Successfully");
                pnlForgotPass.setVisible(false);
                new Login(frame);
            }
           catch (Exception e) {
               e.printStackTrace();
           }
    }
    public static void main(String[] args) {
        new ForgotPassword((new JFrame()));
    }
    
}
