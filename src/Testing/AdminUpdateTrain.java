/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.sql.*;
/**
 *
 * @author HP
 */
public class AdminUpdateTrain{
    JFrame UpdateFrame = new JFrame();
    JPanel pnlUpdate = new JPanel();
    
    JLabel lblSearchTrainNumber = new JLabel("TRAIN NUMBER");
    JTextField txtSearchTrainNumber = new JTextField();
    JButton btnSearch = new JButton("Search");
    
    JLabel lblTname = new JLabel("TRAIN NAME");
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
    
    JButton btnSubmit = new JButton("Update Route");

    AdminUpdateTrain() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        UpdateFrame.setTitle("Update Details");
        UpdateFrame.setVisible(true);
        UpdateFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        UpdateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UpdateFrame.setResizable(true); 
        UpdateFrame.add(pnlUpdate);
        addActionEvent();
    }
    public void setLayoutManager()
   {
       pnlUpdate.setLayout(null);
   }
   public void setLocationAndSize()
   {
       
       lblSearchTrainNumber.setBounds(750,50,100,30);
       txtSearchTrainNumber.setBounds(1050,50,150,30);
       btnSearch.setBounds(950,100,100,30);
       
       
       lblTname.setBounds(750,250,100,30);
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
      
       
       
       txtTname.setBounds(1050,250,150,30);
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
       
       btnSubmit.setBounds(950,750,150,30);
       
       ArrivalMeridian.setBounds(1130, 400,50,30);
       DepatureMeridian.setBounds(1130, 450,50,30);
   }
   public void addComponentsToContainer()
   {
      pnlUpdate.add(lblSearchTrainNumber);
      pnlUpdate.add(txtSearchTrainNumber);
      pnlUpdate.add(btnSearch);
       
      pnlUpdate.add(lblTname);
      pnlUpdate.add(txtTname);
      pnlUpdate.add(lblSorce);
      pnlUpdate.add(lblDestination);
      pnlUpdate.add(lblArrival);
      pnlUpdate.add(lblDepature);
      pnlUpdate.add(lblAmount);
      pnlUpdate.add(lblDate);
      pnlUpdate.add(lblDistance);
      pnlUpdate.add(lblDuration);
      pnlUpdate.add(lblSeats);
      pnlUpdate.add(lblArrivalColon);
      pnlUpdate.add(lblDepatureColon);
      
      
      
      pnlUpdate.add(txtSource);
      pnlUpdate.add(txtDestination);
      pnlUpdate.add(txtArrivalHour);
      pnlUpdate.add(txtArrivalMin);
      pnlUpdate.add(txtDepatureHour);
      pnlUpdate.add(txtDepatureMin);
      pnlUpdate.add(txtAmount);
      pnlUpdate.add(txtDate);
      pnlUpdate.add(txtDistance);
      pnlUpdate.add(txtDuration);
      pnlUpdate.add(txtSeats);
      
      pnlUpdate.add(ArrivalMeridian);
      pnlUpdate.add(DepatureMeridian);
      
      pnlUpdate.add(btnSubmit);
   }
   
   public void addActionEvent(){
       btnSearch.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int TrainNumber = Integer.parseInt(txtSearchTrainNumber.getText());
               
               try {
                   DatabaseConnection db = new DatabaseConnection();
                   String query = "select * from Train where TrainNumber = ?";
                   PreparedStatement pstmt = db.con.prepareStatement(query);
                   
                   pstmt.setInt(1, TrainNumber);
                   
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       txtTname.setText(rs.getString("TrainName"));
                       txtSource.setText(rs.getString("Source"));
                       txtDestination.setText(rs.getString("Destination"));
                       txtDate.setText(rs.getString("Date"));
                       txtAmount.setText(Integer.toString(rs.getInt("Amount")));
                       txtSeats.setText(Integer.toString(rs.getInt("Seats")));
                       txtDistance.setText(Integer.toString(rs.getInt("Distance")));
                       txtDuration.setText(Integer.toString(rs.getInt("Duration")));
                       txtArrivalHour.setText(rs.getString("ArrivalTime").substring(0,2));
                       txtArrivalMin.setText(rs.getString("ArrivalTime").substring(3,5));
                       txtDepatureHour.setText(rs.getString("Departure").substring(0, 2));
                       txtDepatureMin.setText(rs.getString("Departure").substring(3,5));
                       if(rs.getString("ArrivalTime").charAt(6) == 'A'){
                           ArrivalMeridian.setSelectedIndex(0);
                       }else{
                           ArrivalMeridian.setSelectedIndex(1);
                       }
                       if(rs.getString("Departure").charAt(6) == 'A'){
                           DepatureMeridian.setSelectedIndex(0);
                       }else{
                           DepatureMeridian.setSelectedIndex(1);
                       }
                   }
                   db.con.close();
                   
               } catch (Exception ex) {
                   System.out.println(ex.toString());
               }
           }
       });
       
       btnSubmit.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               final int TrainNumber = Integer.parseInt(txtSearchTrainNumber.getText());
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
               try {
                   DatabaseConnection db = new DatabaseConnection();
                   String query = "update Train set Source = ?,Destination =?,ArrivalTime=?,Departure=?,Duration=?,Date=?,Distance=?,Amount=?,Seats=?,TrainName =? where TrainNumber = ?";
            
                    PreparedStatement p = db.con.prepareStatement(query);

                    
                    p.setString(1,Source);
                    p.setString(2,Destination);
                    p.setString(3,Arrival);
                    p.setString(4,Depature);
                    p.setInt(5,Duration);
                    p.setString(6,Date);
                    p.setInt(7,Distance);
                    p.setInt(8,Amount);
                    p.setInt(9,Seats);
                    p.setInt(11, TrainNumber);
                    p.setString(10, TrainName);

                    p.executeUpdate();
                    db.con.close();
            
                     JOptionPane.showMessageDialog(null,"Train Route Updated Successfully");
               } catch (Exception ex) {
                   System.out.println(ex.toString());
               }
               setToDefault();
           }
       });
   }
   
   public void setToDefault(){
       
       txtSearchTrainNumber.setText("");
       txtTname.setText("");
       txtSource.setText("");
       txtDestination.setText("");
       txtSeats.setText("");
       txtArrivalHour.setText("");
       txtArrivalMin.setText("");
       txtDepatureHour.setText("");
       txtDepatureMin.setText("");
       ArrivalMeridian.setSelectedIndex(0);
       DepatureMeridian.setSelectedIndex(0);
       txtDuration.setText("");
       txtDistance.setText("");
       txtAmount.setText("");
       txtDate.setText("");
   }
   
   
    public static void main(String[] args) {
        new AdminUpdateTrain();
    }
    
    
    
    
}
