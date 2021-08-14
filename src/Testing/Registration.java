/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.*;
import java.util.Random;
import java.sql.*;
/**
 *
 * @author menag
 */



class Registration extends JFrame implements ActionListener {

    Container container=getContentPane();
    JLabel lblName = new JLabel("NAME");
    JLabel lblEmail=new JLabel("EMAIL ID");
    JLabel lblPass=new JLabel("CREATE PASSWORD");
    JLabel lblConformPass=new JLabel("CONFORM PASSWORD");
    JTextField txtName = new JTextField();
    JTextField txtEmail=new JTextField();
    JPasswordField txtPass=new JPasswordField();
    JPasswordField txtConformPass=new JPasswordField();
    JButton btnRegister=new JButton("REGISTER");



    Registration()
    {
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Registration Form");
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        addActionEvent();
    }
   public void setLayoutManager()
   {
       container.setLayout(null);
   }
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
       lblName.setBounds(50*15,100*2,200,30);
       lblEmail.setBounds(50*15,150*2,200,30);
       lblPass.setBounds(50*15,200*2,200,30);
       lblConformPass.setBounds(50*15,250*2,200,30);
       txtName.setBounds(150*7,100*2,200,30);
       txtEmail.setBounds(150*7,150*2,200,30);
       txtPass.setBounds(150*7,200*2,200,30);
       txtConformPass.setBounds(150*7,250*2,200,30);
       btnRegister.setBounds(60*15,300*2,100,30);

   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
       container.add(lblName);
       container.add(lblEmail);
       container.add(lblPass);
       container.add(lblConformPass);
       container.add(txtName);
       container.add(txtEmail);
       container.add(txtPass);
       container.add(txtConformPass);
       container.add(btnRegister);

   }
   
   public void addActionEvent()
   {
       btnRegister.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnRegister)
       {
           String name = txtName.getText();
           String email=txtEmail.getText();
           String password=String.valueOf(txtPass.getPassword());
           String conformPassword=String.valueOf(txtConformPass.getPassword());
           
           if(Validate.validateEmail(email) && Validate.validatePassword(password) && Validate.checkConformPassword(password, conformPassword))
           {
               final String OTP=otpGenerator();
               final String userName=GenerateUserName();
               sendOtp(email,OTP,name);
               String otpEntered=JOptionPane.showInputDialog(null,"Enter OTP","Verification",-1);
               if(otpEntered.equals(OTP))
               {
                   
                   JOptionPane.showMessageDialog(null,"Registered Successfully.Your User Name will be recieved to your Email shortly","",-1);
                   try
                   {
                       
                       Class.forName("com.mysql.cj.jdbc.Driver");
                       Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/TrainTicketReservationSystem", "root", "Saju#123");
                       //add name column in the client database
                       //cmd:alter table Client add Name varchar(20);
                       String query="insert into client values(?,?,?,?);";
                       PreparedStatement pStmt=con.prepareStatement(query);
                       pStmt.setString(1,userName);
                       pStmt.setString(2,password);
                       pStmt.setString(3,email);
                       pStmt.setString(4, name);
                       pStmt.executeUpdate();
                       con.close();
                       SendDetails(email, userName, name, password);
                       
                   }
                   catch(Exception exception)
                   {
                       exception.printStackTrace();
                   }
                   
               }
                else
               {
                   JOptionPane.showMessageDialog(null,"OTP Incorrect");
               }
           }
       }
   }
    public void sendOtp(String recepient,String otp,String Name)
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
                    + "<p>The One-Time-Password (OTP) is given Below<p> "
                    + "<h2 style=\"background-color: coral; padding: 2%; margin-right: 85%; padding-left: 80;\">"+ otp +"</h2>"
                    + "<p>Enjoy Our Serives.Have a good Day</p>";
                message.setContent(content,"text/html");
                
                Transport.send(message);
                System.out.println("Sent Successfully");
            }
           catch (Exception e) {
               e.printStackTrace();
           }
    }
    public void SendDetails(String recepient,String Username,String Name,String Password)
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
                message.setSubject("Railway Reservation System Credentials");
                String content = "<h3>Hello "+ Name +",</h3>"
                    + "<p> Your Credentials are attached below</p>"
                    + "<div style=\"background-color:coral;padding:50;margin: 100;margin-right: 70%;\">"
                    + "<h2 style=\"background-color: azure;\">User Name : "+Username+"</h2>"
                    +"<h2 style=\"background-color: azure;\">Password : "+Password+"</h2>"
                    +"</div>";
                message.setContent(content,"text/html");
                
                Transport.send(message);
                System.out.println("Sent Successfully");
            }
           catch (Exception e) {
               e.printStackTrace();
           }
    }
    
    
    public String otpGenerator(){
        return String.valueOf((int)((Math.random()*(999999-100000)+100000)));
    }
    public String GenerateUserName(){
       Random r = new Random();
        String UserName ="";
        for(int i=0;i<2;i++){
            UserName+= (char)(65 + (r.nextInt(25)));
        }
        for(int i=0;i<4;i++){
            UserName+= r.nextInt(9);
        }
        return UserName;
    }
    public static void main(String[] args) {
        new Registration();
          
    }
}
