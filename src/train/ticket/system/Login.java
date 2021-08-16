/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train.ticket.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.LineBorder;
/**
 *
 * @author HP
 */
public class Login extends JFrame{
    
    JPanel pnlLogin;
    public static String LogInUserName="";
    JTextField txtUser=new HintTextField("USERNAME");
    JPasswordField txtPass=new JPasswordField();
    JButton btnLogin=new JButton("LOGIN");
   
    JButton btnRegister=new JButton("REGISTER");
    
    JCheckBox showPassword=new JCheckBox("Show Password");
    
    Login(){
        
        pnlLogin = new JPanel();
        
        JLabel Title = new JLabel("LOGIN");
        Title.setFont(new Font("Verdana", Font.BOLD, 25));
        Title.setForeground(Color.BLACK);
        Title.setBounds(225, 50,100,30);
        pnlLogin.add(Title);
        
        //---UserName Icon---//
        ImageIcon user = new ImageIcon(this.getClass().getResource("usernameIcon.png"));
        Image user1 = user.getImage();
        Image user2 = user1.getScaledInstance(50, 50,Image.SCALE_SMOOTH);
    
        JLabel lblUser=new JLabel(new ImageIcon(user2));
        lblUser.setBounds(125,150,50,50);       
        pnlLogin.add(lblUser);
        //--Username Icon--//
        
        //--Username Text--//
        txtUser.setBounds(200,160,200,30);
        txtUser.setFont(new Font("Verdana", Font.CENTER_BASELINE, 12));
        txtUser.setBackground(new Color(220,222,220,210));
        txtUser.setBorder(null);
        pnlLogin.add(txtUser);
        //--Username Text--//
        
        //--Password Icon--//
        ImageIcon pass = new ImageIcon(this.getClass().getResource("passwordIcon.jpg"));
        Image pass1 = pass.getImage();
        Image pass2 = pass1.getScaledInstance(40, 40,Image.SCALE_SMOOTH);
        
        JLabel lblPass=new JLabel(new ImageIcon(pass2));
        lblPass.setBounds(125,220,50,50);       
        pnlLogin.add(lblPass);
        //--Password Icon--//
        
        //--Password Text--//
        txtPass.setBounds(200,230,200,30);
        txtPass.setFont(new Font("Verdana", Font.CENTER_BASELINE, 12));
        txtPass.setBackground(new Color(220,222,220,210));
        txtPass.setBorder(null);
        pnlLogin.add(txtPass);
        
        //--Password Text--//
        
        showPassword.setBounds(200,260,200,30);        
        showPassword.setBackground(null);
        showPassword.setFont(new Font("Verdana", Font.CENTER_BASELINE, 10));
        pnlLogin.add(showPassword);
        
        showPassword.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
               txtPass.setEchoChar((char) 0);
            } else {
                txtPass.setEchoChar('*');
            }
            }
        });
        //Show Password//
        
        //--REgister Button--//
        btnRegister.setBounds(125,320,150,40);
        btnRegister.setFont(new Font("Verdana", Font.CENTER_BASELINE, 12));
        btnRegister.setBackground(new Color(220,222,220,210));
        pnlLogin.add(btnRegister);
        
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        //--REgister Button--//
        
        //--Login Button--//
        btnLogin.setBounds(295,320,150,40);
        btnLogin.setFont(new Font("Verdana", Font.CENTER_BASELINE, 12));
        btnLogin.setBackground(new Color(220,222,220,210));
        pnlLogin.add(btnLogin);
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    DatabaseConnection db = new DatabaseConnection();
                    System.out.println("Connection Success");
                    String query="select Username,Password from $user where Username=? and Password=?";
                    query=query.replace("$user",user);
                    PreparedStatement pStmt = db.con.prepareStatement(query);
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
                    db.con.close();
                    System.out.println("User: " + LogInUserName);
                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(null,exception.toString());
            }
            }
        });
        
        
        
        pnlLogin.setBounds(220, 100, 550, 550);
        pnlLogin.setBackground(new Color(220,222,220,210));
        pnlLogin.setBorder(new LineBorder(Color.BLACK,1,true));
        pnlLogin.setLayout(null);
        add(pnlLogin);
        
        
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
        
        addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                
            }
            public void mouseMoved(MouseEvent e) {
            	 int x=e.getX();
                 int y=e.getY();

                 String msg=x+"  "+y;
                 setTitle(msg);
            }
		});    
    }
    
    
    public static void main(String[] args) {
        new Login();
    }
}
