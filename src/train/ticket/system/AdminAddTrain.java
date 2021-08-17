/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train.ticket.system;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

/**
 *
 * @author HP
 */
public class AdminAddTrain {

    static JButton btnSubmit;

    public static JPanel pnlAdminAdd() {
        JPanel pnlAdmin = new JPanel();
        pnlAdmin.setBounds(0, 0, 1300, 900);
        pnlAdmin.setBackground(Color.PINK);
        pnlAdmin.setLayout(null);

        JLabel lblTitle = new JLabel("ADD TRAIN ROUTE");
        lblTitle.setBounds(470, 50, 350, 70);
        lblTitle.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 35));
        pnlAdmin.add(lblTitle);

        JLabel lblTname = new JLabel("TRAIN NAME");
        JLabel lblTnum = new JLabel("TRAIN NUMBER");
        JLabel lblSorce = new JLabel("SOURCE");
        JLabel lblDestination = new JLabel("DESTINATION");
        JLabel lblArrival = new JLabel("ARRIVAL TIME");
        JLabel lblArrivalColon = new JLabel(":");
        JLabel lblDepature = new JLabel("DEPATURE TIME");
        JLabel lblDepatureColon = new JLabel(":");
        JLabel lblDuration = new JLabel("DURATION");
        JLabel lblDistance = new JLabel("DISTANCE");
        JLabel lblDate = new JLabel("DATE");
        JLabel lblAmount = new JLabel("AMOUNT");
        JLabel lblSeats = new JLabel("SEATS");

        JTextField txtTname = new JTextField();
        JTextField txtTnum = new JTextField();
        JTextField txtSource = new JTextField();
        JTextField txtDestination = new JTextField();
        JTextField txtArrivalHour = new HintTextField("HH");
        JTextField txtArrivalMin = new HintTextField("MM");
        JTextField txtDepatureHour = new HintTextField("HH");
        JTextField txtDepatureMin = new HintTextField("MM");
        JTextField txtDuration = new HintTextField("(in Hours)");
        JTextField txtDistance = new HintTextField("(in Kms)");
        JTextField txtDate = new HintTextField("DD/MM/YYYY");
        JTextField txtAmount = new HintTextField("(in Rs)");
        JTextField txtSeats = new JTextField();

        String[] merideians = {"A.M", "P.M"};
        JComboBox ArrivalMeridian = new JComboBox(merideians);
        JComboBox DepatureMeridian = new JComboBox(merideians);

        btnSubmit = new JButton("ADD ROUTE");

        lblTname.setBounds(120, 200, 150, 30);
        lblTname.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblTnum.setBounds(600, 200, 200, 30);
        lblTnum.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblSorce.setBounds(120, 300, 150, 30);
        lblSorce.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblDestination.setBounds(600, 300, 150, 30);
        lblDestination.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblArrival.setBounds(120, 400, 150, 30);
        lblArrival.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblArrivalColon.setBounds(335, 400, 25, 30);
        lblArrivalColon.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblDepature.setBounds(600, 400, 200, 30);
        lblDepature.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblDepatureColon.setBounds(835, 400, 25, 30);
        lblDepatureColon.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblDuration.setBounds(120, 500, 150, 30);
        lblDuration.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblDistance.setBounds(600, 500, 150, 30);
        lblDistance.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblDate.setBounds(120, 600, 150, 30);
        lblDate.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblAmount.setBounds(600, 600, 150, 30);
        lblAmount.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));
        lblSeats.setBounds(120, 700, 150, 30);
        lblSeats.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 22));

        txtTname.setBounds(300, 200, 200, 30);
        txtTname.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtTname.setBackground(Color.WHITE);
        txtTname.setBorder(null);

        txtTnum.setBounds(800, 200, 200, 30);
        txtTnum.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtTnum.setBackground(Color.WHITE);
        txtTnum.setBorder(null);

        txtSource.setBounds(300, 300, 200, 30);
        txtSource.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtSource.setBackground(Color.WHITE);
        txtSource.setBorder(null);

        txtDestination.setBounds(800, 300, 200, 30);
        txtDestination.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtDestination.setBackground(Color.WHITE);
        txtDestination.setBorder(null);

        txtArrivalHour.setBounds(300, 400, 30, 30);
        txtArrivalHour.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtArrivalHour.setBackground(Color.WHITE);
        txtArrivalHour.setBorder(null);

        txtArrivalMin.setBounds(350, 400, 30, 30);
        txtArrivalMin.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtArrivalMin.setBackground(Color.WHITE);
        txtArrivalMin.setBorder(null);

        txtDepatureHour.setBounds(800, 400, 30, 30);
        txtDepatureHour.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtDepatureHour.setBackground(Color.WHITE);
        txtDepatureHour.setBorder(null);

        txtDepatureMin.setBounds(850, 400, 30, 30);
        txtDepatureMin.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtDepatureMin.setBackground(Color.WHITE);
        txtDepatureMin.setBorder(null);

        txtDuration.setBounds(300, 500, 200, 30);
        txtDuration.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtDuration.setBackground(Color.WHITE);
        txtDuration.setBorder(null);

        txtDistance.setBounds(800, 500, 200, 30);
        txtDistance.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtDistance.setBackground(Color.WHITE);
        txtDistance.setBorder(null);

        txtDate.setBounds(300, 600, 200, 30);
        txtDate.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtDate.setBackground(Color.WHITE);
        txtDate.setBorder(null);

        txtAmount.setBounds(800, 600, 200, 30);
        txtAmount.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtAmount.setBackground(Color.WHITE);
        txtAmount.setBorder(null);

        txtSeats.setBounds(300, 700, 200, 30);
        txtSeats.setFont(new Font("Ebrima", Font.PLAIN, 15));
        txtSeats.setBackground(Color.WHITE);
        txtSeats.setBorder(null);

        btnSubmit.setBounds(850, 700, 150, 50);
        btnSubmit.setFont(new Font("Ebrima", Font.CENTER_BASELINE, 15));
        btnSubmit.setBackground(new Color(220, 222, 220, 140));

        txtArrivalHour.setText("HH");
        txtArrivalMin.setText("MM");
        txtDepatureHour.setText("HH");
        txtDepatureMin.setText("MM");

        txtDuration.setText("(in Hours)");
        txtDistance.setText("(in Kms)");
        txtAmount.setText("(in Rs)");
        txtDate.setText("DD/MM/YYYY");

        ArrivalMeridian.setBounds(390, 400, 70, 30);
        ArrivalMeridian.setFont(new Font("Ebrima", Font.PLAIN, 15));
        ArrivalMeridian.setBackground(Color.WHITE);
        ArrivalMeridian.setBorder(null);

        DepatureMeridian.setBounds(890, 400, 70, 30);
        DepatureMeridian.setFont(new Font("Ebrima", Font.PLAIN, 15));
        DepatureMeridian.setBackground(Color.WHITE);
        DepatureMeridian.setBorder(null);

        pnlAdmin.add(lblTname);
        pnlAdmin.add(lblTnum);
        pnlAdmin.add(lblSorce);
        pnlAdmin.add(lblDestination);
        pnlAdmin.add(lblArrival);
        pnlAdmin.add(lblDepature);
        pnlAdmin.add(lblAmount);
        pnlAdmin.add(lblDate);
        pnlAdmin.add(lblDistance);
        pnlAdmin.add(lblDuration);
        pnlAdmin.add(lblSeats);
        pnlAdmin.add(lblArrivalColon);
        pnlAdmin.add(lblDepatureColon);

        pnlAdmin.add(txtTname);
        pnlAdmin.add(txtTnum);
        pnlAdmin.add(txtSource);
        pnlAdmin.add(txtDestination);
        pnlAdmin.add(txtArrivalHour);
        pnlAdmin.add(txtArrivalMin);
        pnlAdmin.add(txtDepatureHour);
        pnlAdmin.add(txtDepatureMin);
        pnlAdmin.add(txtAmount);
        pnlAdmin.add(txtDate);
        pnlAdmin.add(txtDistance);
        pnlAdmin.add(txtDuration);
        pnlAdmin.add(txtSeats);

        pnlAdmin.add(ArrivalMeridian);
        pnlAdmin.add(DepatureMeridian);

        pnlAdmin.add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Collecting Data from Given Information
                final int TrainNumber = Integer.parseInt(txtTnum.getText());
                final String TrainName = txtTname.getText();
                final String Source = txtSource.getText();
                final String Destination = txtDestination.getText();
                final String Date = txtDate.getText();
                final String Arrival = txtArrivalHour.getText() + ":" + txtArrivalMin.getText() + " " + ArrivalMeridian.getSelectedItem();
                final String Depature = txtDepatureHour.getText() + ":" + txtDepatureMin.getText() + " " + DepatureMeridian.getSelectedItem();
                final int Distance = Integer.parseInt(txtDistance.getText());
                final int Amount = Integer.parseInt(txtAmount.getText());
                final int Seats = Integer.parseInt(txtSeats.getText());
                final int Duration = Integer.parseInt(txtDuration.getText());

                //Storeing in database
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TrainTicketReservationSystem", "root", "Saju#123");
                    System.out.println("Connection Success");
                    String query = "insert into Train values(?,?,?,?,?,?,?,?,?,?,?)";

                    PreparedStatement p = con.prepareStatement(query);

                    p.setInt(1, TrainNumber);
                    p.setString(2, Source);
                    p.setString(3, Destination);
                    p.setString(4, Arrival);
                    p.setString(5, Depature);
                    p.setInt(6, Duration);
                    p.setString(7, Date);
                    p.setInt(8, Distance);
                    p.setInt(9, Amount);
                    p.setInt(10, Seats);
                    p.setString(11, TrainName);

                    p.executeUpdate();
                    con.close();

                    JOptionPane.showMessageDialog(null, "Train Route Added Successfully");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
                txtTname.setText("");
                txtTnum.setText("");
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
        });

        return pnlAdmin;
    }

}
