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

class Login implements ActionListener {
    JFrame frame;
    
    public static String LogInUserName="";
    JPanel pnlLogin=new JPanel();
    JLabel lblUser=new JLabel("USERNAME");
    JLabel lblPass=new JLabel("PASSWORD");
    JTextField txtUser=new JTextField();
    JPasswordField txtPass=new JPasswordField();
    JButton btnLogin=new JButton("LOGIN");
//    JButton resetButton=new JButton("RESET");
    JCheckBox showPassword=new JCheckBox("Show Password");
    JButton btnForgotPassword=new JButton("Forgot Password?");
    JLabel lblDiv=new JLabel("/");
    JButton btnRegister =new JButton("New User? Register");

    Login(JFrame frame)
    {
       //Calling methods inside constructor.
        this.frame=frame;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addActionEvent();
        setColor();
        frame.add(pnlLogin);
    }
   public void setLayoutManager()
   {
       pnlLogin.setLayout(null);
   }
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
       lblUser.setBounds(50*15,150*2,100,30);
       lblPass.setBounds(50*15,200*2,100,30);
       txtUser.setBounds(150*7,150*2,150,30);
       txtPass.setBounds(150*7,200*2,150,30);
       showPassword.setBounds(150*7,220*2,150,30);
       btnLogin.setBounds(57*15,280*2,200,30);
//       resetButton.setBounds(200,300,100,30);
       btnForgotPassword.setBounds(50*15,320*2,180,30);
       lblDiv.setBounds(53*18,320*2,10,30);
       btnRegister.setBounds(52*19,320*2,200,30);

   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
       pnlLogin.add(lblUser);
       pnlLogin.add(lblPass);
       pnlLogin.add(txtUser);
       pnlLogin.add(txtPass);
       pnlLogin.add(showPassword);
       pnlLogin.add(btnLogin);
//       container.add(resetButton);
        pnlLogin.add(btnForgotPassword);
        pnlLogin.add(lblDiv);
        pnlLogin.add(btnRegister);
   }
   public void setColor()
   {
       btnForgotPassword.setBorderPainted(false);
       btnForgotPassword.setBackground(Color.WHITE);
       btnForgotPassword.setForeground(Color.BLUE);
       btnRegister.setBorderPainted(false);
       btnRegister.setBackground(Color.WHITE);
       btnRegister.setForeground(Color.BLUE);
   }
   public void addActionEvent()
   {
       btnLogin.addActionListener(this);
       showPassword.addActionListener(this);
       btnForgotPassword.addActionListener(this);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
               txtPass.setEchoChar((char) 0);
            } else {
                txtPass.setEchoChar('*');
            }
        }
        if(e.getSource()==btnLogin)
        {
                final String username=txtUser.getText();
                final String password=new String(txtPass.getPassword());
                String user;
                if(username.equals("Admin"))
                {
                    user="Admin";
              
                }
                else
                {
                    user="Client";
                }
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/TrainTicketReservationSystem", "root", "Saju#123");
                    System.out.println("Connection Success");
                    String query="select Username,Password from $user where Username=? and Password=?";
                    query=query.replace("$user",user);
                    PreparedStatement pStmt = con.prepareStatement(query);
                    pStmt.setString(1,username);
                    pStmt.setString(2,password);
                   
                    ResultSet rs=pStmt.executeQuery();
                    if(rs.next())
                    {
                        LogInUserName = rs.getString("Username");
                        //below line shld direct to mainpage (need to be added)
                        JOptionPane.showMessageDialog(null,"Logged in Successfully!!"); 
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null,"User not found");
                    }
                    con.close();
                    System.out.println("User: " + LogInUserName);
                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(null,exception.toString());
            }
        }
        if(e.getSource() == btnForgotPassword)
        {
            pnlLogin.setVisible(false);
            new ForgotPassword(frame);
        }
    }

    
    public static void main(String args[])
    {
           new Login(new JFrame());
    }
}
