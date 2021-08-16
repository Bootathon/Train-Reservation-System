/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train.ticket.system;
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
import javax.swing.border.LineBorder;
/**
 *
 * @author HP
 */
public class Register extends JFrame {
    JPanel pnlRegister;
    
    
   
    JLabel lblConformPass=new JLabel("CONFORM PASSWORD");
    JTextField txtName = new HintTextField("NAME");
    JTextField txtEmail=new HintTextField("E-MAIL");
    JPasswordField txtPass=new JPasswordField();
    JPasswordField txtConformPass=new JPasswordField();
    JButton btnRegister=new JButton("REGISTER");

    Register() {
        
        pnlRegister = new JPanel();
        
        JLabel Title = new JLabel("REGISTER");
        Title.setFont(new Font("Verdana", Font.BOLD, 25));
        Title.setForeground(Color.BLACK);
        Title.setBounds(225, 50,150,30);
        pnlRegister.add(Title);
        
        pnlRegister.setBounds(220, 100, 550, 550);
        pnlRegister.setBackground(new Color(220,222,220,210));
        pnlRegister.setBorder(new LineBorder(Color.BLACK,1,true));
        pnlRegister.setLayout(null);
        add(pnlRegister);
        
        NameLabel();
        EmailLabel();
        PasswordLabel();
        PasswordConfirmLabel();
        Registerbutton();
        
        ImageIcon image1=new ImageIcon(this.getClass().getResource("BGIMAGE.jpg"));
        Image image2=image1.getImage();
        Image image=image2.getScaledInstance(1000, 870, java.awt.Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
        JLabel background=new JLabel();
        background.setIcon(new ImageIcon(image));
        background.setBounds(0, 0, 1000, 800);
        getContentPane().add(background);
        
        setBounds(500,150,1000,800);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void NameLabel(){
        ImageIcon user = new ImageIcon(this.getClass().getResource("NameIcon.png"));
        Image user1 = user.getImage();
        Image user2 = user1.getScaledInstance(40, 40,Image.SCALE_SMOOTH);
    
        JLabel lblName=new JLabel(new ImageIcon(user2));
        lblName.setBounds(125,150,50,50);       
        pnlRegister.add(lblName);
        
        txtName.setBounds(200,160,200,30);
        txtName.setFont(new Font("Verdana", Font.CENTER_BASELINE, 12));
        txtName.setBackground(new Color(220,222,220,210));
        txtName.setBorder(null);
        pnlRegister.add(txtName);
    }
    
    public void EmailLabel(){
        ImageIcon email = new ImageIcon(this.getClass().getResource("Email.png"));
        Image email1 = email.getImage();
        Image email2 = email1.getScaledInstance(40, 40,Image.SCALE_SMOOTH);
        
        JLabel lblEmail=new JLabel(new ImageIcon(email2));
        lblEmail.setBounds(125,220,50,50);       
        pnlRegister.add(lblEmail);
        
        txtEmail.setBounds(200,230,200,30);
        txtEmail.setFont(new Font("Verdana", Font.CENTER_BASELINE, 12));
        txtEmail.setBackground(new Color(220,222,220,210));
        txtEmail.setBorder(null);
        pnlRegister.add(txtEmail);
        
    }
    
    public void PasswordLabel(){
        ImageIcon pass = new ImageIcon(this.getClass().getResource("passwordIcon.jpg"));
        Image pass1 = pass.getImage();
        Image pass2 = pass1.getScaledInstance(40, 40,Image.SCALE_SMOOTH);
        
        JLabel lblPassword = new JLabel(new ImageIcon(pass2));
        lblPassword.setBounds(125,290,50,50);       
        pnlRegister.add(lblPassword);
        
        txtPass.setBounds(200,300,200,30);
        txtPass.setFont(new Font("Verdana", Font.CENTER_BASELINE, 12));
        txtPass.setBackground(new Color(220,222,220,210));
        txtPass.setBorder(null);
        pnlRegister.add(txtPass);
    }
    public void PasswordConfirmLabel(){
        ImageIcon passc = new ImageIcon(this.getClass().getResource("confirmpassword.png"));
        Image passc1 = passc.getImage();
        Image passc2 = passc1.getScaledInstance(40, 40,Image.SCALE_SMOOTH);
        
        JLabel lblPassword = new JLabel(new ImageIcon(passc2));
        lblPassword.setBounds(125,360,50,50);       
        pnlRegister.add(lblPassword);
        
        txtConformPass.setBounds(200,370,200,30);
        txtConformPass.setFont(new Font("Verdana", Font.CENTER_BASELINE, 12));
        txtConformPass.setBackground(new Color(220,222,220,210));
        txtConformPass.setBorder(null);
        pnlRegister.add(txtConformPass);
    }
    
    public void Registerbutton(){
        btnRegister.setBounds(220,440,150,40);
        btnRegister.setFont(new Font("Verdana", Font.CENTER_BASELINE, 12));
        btnRegister.setBackground(new Color(220,222,220,210));
        pnlRegister.add(btnRegister);
        
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
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
        });
    }
    //--Send OTP MAIL--//
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
    
    //--Send Details to mail--//
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
        new Register();
    }
    
    
}
