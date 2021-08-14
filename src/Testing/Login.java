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

class Login extends JFrame implements ActionListener {
    public static String LogInUserName="";
    Container container=getContentPane();
    JLabel lblUser=new JLabel("USERNAME");
    JLabel lblPass=new JLabel("PASSWORD");
    JTextField txtUser=new JTextField();
    JPasswordField txtPass=new JPasswordField();
    JButton btnLogin=new JButton("LOGIN");
//    JButton resetButton=new JButton("RESET");
    JCheckBox showPassword=new JCheckBox("Show P"
            + "assword");


    Login()
    {
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Login Form");
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
       lblUser.setBounds(50*15,150*2,100,30);
       lblPass.setBounds(50*15,220*2,100,30);
       txtUser.setBounds(150*7,150*2,150,30);
       txtPass.setBounds(150*7,220*2,150,30);
       showPassword.setBounds(150*7,250*2,150,30);
       btnLogin.setBounds(60*15,300*2,100,30);
//       resetButton.setBounds(200,300,100,30);


   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
       container.add(lblUser);
       container.add(lblPass);
       container.add(txtUser);
       container.add(txtPass);
       container.add(showPassword);
       container.add(btnLogin);
//       container.add(resetButton);
   }
   
   public void addActionEvent()
   {
       btnLogin.addActionListener(this);
       showPassword.addActionListener(this);
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
    }

    
    public static void main(String args[])
    {
           new Login();
    }
}

