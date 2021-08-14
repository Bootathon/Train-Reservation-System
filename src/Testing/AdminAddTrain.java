/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author HP
 */
public class AdminAddTrain extends JFrame implements ActionListener{
    Container container=getContentPane(); 
    
    JLabel lblTname = new JLabel("TRAIN NAME");
    JLabel lblTnum = new JLabel("TRAIN NUMBER");
    JLabel lblSorce = new JLabel("SOURCE");
    JLabel lblDestination = new JLabel("DESTINATION");
    JLabel lblArrival = new JLabel("Arrival TIME");
    JLabel lblArrivalColon = new JLabel(":");
    JLabel lblDepature = new JLabel("DEPATURE TIME");
    JLabel lblDepatureColon = new JLabel(":");
    JLabel lblDuration = new JLabel("DURATION");
    JLabel lblDistance = new JLabel("DISTANCE");
    JLabel lblDate = new JLabel("DATE");
    JLabel lblAmount = new JLabel("AMOUNT");
    JLabel lblSeats = new JLabel("SEATS");
    
    JTextField txtTname=new JTextField();
    JTextField txtTnum=new JTextField();
    JTextField txtSource=new JTextField();
    JTextField txtDestination=new JTextField();
    JTextField txtArrivalHour=new JTextField();
    JTextField txtArrivalMin=new JTextField();
    JTextField txtDepatureHour=new JTextField();
    JTextField txtDepatureMin=new JTextField();
    JTextField txtDuration=new JTextField();
    JTextField txtDistance=new JTextField();
    JTextField txtDate=new JTextField();
    JTextField txtAmount=new JTextField();
    JTextField txtSeats=new JTextField();
    
    String[] merideians ={"A.M","P.M"};
    JComboBox ArrivalMeridian = new JComboBox(merideians);
    JComboBox DepatureMeridian = new JComboBox(merideians);
    
    JButton btnSubmit = new JButton("Add Route");
    
    
    AdminAddTrain(){
        
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Add Train");
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true); 
        addActionEvent();
        
    }
    public void setLayoutManager()
   {
       container.setLayout(null);
   }
   public void setLocationAndSize()
   {
       lblTname.setBounds(750,200,100,30);
       lblTnum.setBounds(750,250,100,30);
       lblSorce.setBounds(750,300,100,30);
       lblDestination.setBounds(750,350,100,30);
       lblArrival.setBounds(750,400,100,30);
       lblArrivalColon.setBounds(1080,400,25,30);
       lblDepature.setBounds(750,450,100,30);
       lblDepatureColon.setBounds(1080,450,25,30);
       lblDuration.setBounds(750,500,100,30);
       lblDistance.setBounds(750,550,100,30);
       lblDate.setBounds(750,600,100,30);
       lblAmount.setBounds(750,650,100,30);
       lblSeats.setBounds(750,700,100,30);
      
       
       txtTname.setBounds(1050,200,150,30);
       txtTnum.setBounds(1050,250,150,30);
       txtSource.setBounds(1050,300,150,30);
       txtDestination.setBounds(1050,350,150,30);
       txtArrivalHour.setBounds(1050,400,25,30);
       txtArrivalMin.setBounds(1090,400,25,30);
       txtDepatureHour.setBounds(1050,450,25,30);
       txtDepatureMin.setBounds(1090,450,25,30);
       txtDuration.setBounds(1050,500,150,30);
       txtDistance.setBounds(1050,550,150,30);
       txtDate.setBounds(1050,600,150,30);
       txtAmount.setBounds(1050,650,150,30);
       txtSeats.setBounds(1050,700,150,30);
       
       btnSubmit.setBounds(950,750,100,30);
       
       txtArrivalHour.setText("HH");
       txtArrivalMin.setText("MM");
       txtDepatureHour.setText("HH");
       txtDepatureMin.setText("MM");
       
       txtDuration.setText("(in Hours)");
       txtDistance.setText("(in Kms)");
       txtAmount.setText("(in Rs)");
       txtDate.setText("DD/MM/YYYY");
       
       ArrivalMeridian.setBounds(1130, 400,50,30);
       DepatureMeridian.setBounds(1130, 450,50,30);
   }
   public void addComponentsToContainer()
   {
      container.add(lblTname);
      container.add(lblTnum);
      container.add(lblSorce);
      container.add(lblDestination);
      container.add(lblArrival);
      container.add(lblDepature);
      container.add(lblAmount);
      container.add(lblDate);
      container.add(lblDistance);
      container.add(lblDuration);
      container.add(lblSeats);
      container.add(lblArrivalColon);
      container.add(lblDepatureColon);
      
      
      container.add(txtTname);
      container.add(txtTnum);
      container.add(txtSource);
      container.add(txtDestination);
      container.add(txtArrivalHour);
      container.add(txtArrivalMin);
      container.add(txtDepatureHour);
      container.add(txtDepatureMin);
      container.add(txtAmount);
      container.add(txtDate);
      container.add(txtDistance);
      container.add(txtDuration);
      container.add(txtSeats);
      
      container.add(ArrivalMeridian);
      container.add(DepatureMeridian);
      
      container.add(btnSubmit);
   }
   public void addActionEvent()
   {
       btnSubmit.addActionListener(this);
    
   }
   
   @Override
   public void actionPerformed(ActionEvent e){
       //Collecting Data from Given Information
       final int TrainNumber = Integer.parseInt(txtTnum.getText());
       final String TrainName = txtTname.getText();
       final String Source = txtSource.getText();
       final String Destination = txtDestination.getText();
       final String Date = txtDate.getText();
       final String Arrival = txtArrivalHour.getText() + ":" + txtArrivalMin.getText()+" "+ ArrivalMeridian.getSelectedItem();
       final String Depature = txtDepatureHour.getText() + ":" + txtDepatureMin.getText()+" "+ DepatureMeridian.getSelectedItem();
       final int Distance = Integer.parseInt(txtDistance.getText());
       final int Amount = Integer.parseInt(txtAmount.getText());
       final int  Seats = Integer.parseInt(txtSeats.getText());
       final int Duration = Integer.parseInt(txtDuration.getText());
       
       //Storeing in database
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/TrainTicketReservationSystem", "root", "Saju#123");
            System.out.println("Connection Success");
            String query = "insert into Train values(?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement p = con.prepareStatement(query);
            
            p.setInt(1, TrainNumber);
            p.setString(2,Source);
            p.setString(3,Destination);
            p.setString(4,Arrival);
            p.setString(5,Depature);
            p.setInt(6,Duration);
            p.setString(7,Date);
            p.setInt(8,Distance);
            p.setInt(9,Amount);
            p.setInt(10,Seats);
            p.setString(11, TrainName);
            
            p.executeUpdate();
            con.close();
            
            JOptionPane.showMessageDialog(null,"Train Route Added Successfully");
            
       } catch (Exception ex) {
           JOptionPane.showMessageDialog(null,ex.toString());
       }
       txtTname.setText("");
       txtTnum.setText("");
       txtSource.setText("");
       txtDestination.setText("");
       txtSeats.setText("");
       txtArrivalHour.setText("HH");
       txtArrivalMin.setText("MM");
       txtDepatureHour.setText("HH");
       txtDepatureMin.setText("MM");
       ArrivalMeridian.setSelectedIndex(0);
       DepatureMeridian.setSelectedIndex(0);
       txtDuration.setText("(in Hours)");
       txtDistance.setText("(in Kms)");
       txtAmount.setText("(in Rs)");
       txtDate.setText("DD/MM/YYYY");
   }
   
    
    public static void main(String[] args) {
        new AdminAddTrain();
    }
    
}

