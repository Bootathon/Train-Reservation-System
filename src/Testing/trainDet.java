package pack;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class trainDet extends JFrame{
    JLabel lb_frm,lb_to,lb_date;
    JTable tb_trdet;
    JTextField tx_frm,tx_to,tx_date;
    JScrollPane sp_tb;
    Container cnt = getContentPane();
    trainDet()throws Exception{
        setWind();
        setComponents();
        addCont();
        setsize();
    }
    void setWind(){
        cnt.setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(trainDet.EXIT_ON_CLOSE);
        setBounds(300,150,815,400);
        setTitle("Train Details");
    }
    void setComponents() throws Exception{
        //------------Fetching Data from Database---------------//
        ArrayList<String[]> al = new ArrayList<>();
        Connection db = sql.conn();
        String Query = "select * from Train";  //Query
        PreparedStatement ps = db.prepareStatement(Query);
        ResultSet res = ps.executeQuery();
        while(res.next()){ //appending into arraylist
            al.add(new String[]{res.getString("TrainNumber"),res.getString("Source"),res.getString("Destination"),res.getString("ArrivalTime"),res.getString("Departure"),res.getString("Duration")+"hrs",res.getString("Date"),res.getString("Distance")+"Km","Rs. "+res.getString("Amount"),res.getString("Seats")});
        }
        String[][] val = new String[al.size()][10]; //Array List to 2D array
        for(int i=0;i<al.size();i++){
            val[i][0]=al.get(i)[0];
            val[i][1]=al.get(i)[1];
            val[i][2]=al.get(i)[2];
            val[i][3]=al.get(i)[3];
            val[i][4]=al.get(i)[4];
            val[i][5]=al.get(i)[5];
            val[i][6]=al.get(i)[6];
            val[i][7]=al.get(i)[7];
            val[i][8]=al.get(i)[8];
            val[i][9]=al.get(i)[9];
        }
        Object[] clm_name = {"Train No","Source","Destination","Arrival at","Departure at","Duration","Date","Distance","Amount","No of Seats"};

        lb_frm = new JLabel("From: ");
        lb_to = new JLabel("   To: ");
        lb_date = new JLabel("Date: ");

        tx_frm = new JTextField();
        tx_to = new JTextField();
        tx_date = new JTextField();

        tx_frm.setEditable(false);
        tx_to.setEditable(false);
        tx_date.setEditable(false);

        tb_trdet = new JTable(val,clm_name);
        tb_trdet.setFocusable(false);
        tb_trdet.setRowHeight(25);
        tb_trdet.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tb_trdet.setSelectionBackground(Color.GREEN);
        tb_trdet.setShowVerticalLines(false);
        sp_tb= new JScrollPane(tb_trdet);

    }
    void setsize(){
        lb_frm.setBounds(30,20,100,20);
        lb_to.setBounds(30,50,100,20);
        lb_date.setBounds(240,50,100,20);

        tx_frm.setBounds(80,20,100,25);
        tx_to.setBounds(80,50,100,25);
        tx_date.setBounds(300,50,100,25);

        sp_tb.setBounds(0,100,800,250);
    }
    void addCont(){
        add(lb_frm);
        add(lb_to);
        add(lb_date);
        add(tx_frm);
        add(tx_to);
        add(tx_date);
        add(sp_tb);
    }
    public static void main(String args[])throws Exception{
        new trainDet();

    }
}

