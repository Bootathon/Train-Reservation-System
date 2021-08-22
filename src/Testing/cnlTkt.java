package Testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

class ticket extends JFrame {
    JTable tb_cncl;
    JScrollPane sp_tb;
    JLabel lb_tit,lb_tno,lb_name,lb_age,lb_src,img,lb_dest;
    JButton bt_cnl,bt_skip;
    Container c=getContentPane();
    ImageIcon im = new ImageIcon("D:\\Java_ij\\BootathonP1\\src\\Testing\\rhtarrow _2.png");
    int sel;
    ticket() throws Exception{
        setWindow();
        setComponenets();
        setSize();
        addCmp();
    }
    void setWindow(){
        setBounds(300,150,815,270);
        setVisible(true);
        setLayout(null);
        setTitle("CANCEL");
        //c.setBackground(Color.red);
        setDefaultCloseOperation(ticket.EXIT_ON_CLOSE);
    }
    void setComponenets() throws Exception{
        String uname = "Ram";
        ArrayList<String[]> al = new ArrayList<>();
        Connection db = sql.conn();
        String pQuery = "select * from Passengers where UserName=?";  //Query
        String tQuery = "select * from Train where TrainNumber=?";  //Query
        PreparedStatement ps = db.prepareStatement(pQuery);
        PreparedStatement ts = db.prepareStatement(tQuery);
        ps.setString(1,uname);
        ResultSet res = ps.executeQuery();
        while(res.next()){ //appending into arraylist
            ts.setInt(1,Integer.parseInt(res.getString("TrainNumber")));
            ResultSet tes = ts.executeQuery();
            while(tes.next()) {
                al.add(new String[]{res.getString("TicketNumber"), res.getString("Name"),res.getString("Age"),res.getString("Gender"),tes.getString("TrainNumber"),tes.getString("Source"),tes.getString("Destination"),tes.getString("ArrivalTime"),tes.getString("Departure"),tes.getString("Date"),tes.getString("Amount")});
            }
        }
        String[][] val = new String[al.size()][11]; //Array List to 2D array
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
            val[i][10]=al.get(i)[10];
        }
        Object[] clm_name = {"Ticket No.","Name","Age","Gender","Train No","Source","Destination","Arrives at","Departs at","Date","Amount"};

        tb_cncl = new JTable(val,clm_name);
        tb_cncl.setFocusable(false);
        tb_cncl.setRowHeight(30);
        tb_cncl.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tb_cncl.setSelectionBackground(Color.GREEN);
        tb_cncl.setShowVerticalLines(false);
        tb_cncl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setBounds(300,150,815,500);
                sel = tb_cncl.getSelectedRow();
                System.out.println(tb_cncl.getSelectedRow());
                lb_tno.setText("Ticket No. : "+al.get(tb_cncl.getSelectedRow())[0]);
                lb_name.setText("Name        : "+al.get(tb_cncl.getSelectedRow())[1]);
                lb_age.setText("Age            : "+al.get(tb_cncl.getSelectedRow())[2]);
                lb_src.setText(al.get(tb_cncl.getSelectedRow())[5]);
                lb_dest.setText(al.get(tb_cncl.getSelectedRow())[6]);
            }
        });
        sp_tb= new JScrollPane(tb_cncl);

        lb_tit = new JLabel("CANCEL TICKET");
        lb_tit.setForeground(Color.red);
        lb_tno  = new JLabel("Ticket No. : ",SwingConstants.LEFT);
        lb_name = new JLabel("Name        : ",SwingConstants.LEFT);
        lb_age  = new JLabel("Age            : ",SwingConstants.LEFT);
        lb_src = new JLabel();
        lb_dest = new JLabel();

        img = new JLabel();
        img.setIcon(im);

        bt_cnl = new JButton("CANCEL");
        bt_cnl.setBackground(Color.RED);
        bt_cnl.setForeground(Color.white);
        bt_cnl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int v  = JOptionPane.showConfirmDialog(null,"Are you Sure want to Cancel Ticker No: "+val[sel][0]);
                if(v==0) {
                    try {
                        String qry = "delete from Passengers where TicketNumber='"+val[sel][0]+"'";
                        Statement st = db.createStatement();
                        st.execute(qry);
                        JOptionPane.showMessageDialog(null,"Ticket no: "+val[sel][0]+" has been CANCELLED!");
                    } catch (Exception t) {
                        System.out.println(t);
                    }
                }
            }
        });
        bt_skip = new JButton("SKIP");
        bt_skip.setBackground(Color.gray);
    }
    void setSize(){
        sp_tb.setBounds(0,50,800,220);
        lb_tit.setBounds(300,10,200,30);
        lb_tno.setBounds(200,300,150,20);
        lb_name.setBounds(200,330,200,20);;
        lb_age.setBounds(200,360,100,20);
        lb_src.setBounds(400,315,100,30);
        img.setBounds(500,320,30,20);
        lb_dest.setBounds(550,315,100,30);
        bt_cnl.setBounds(250,400,80,30);
        bt_skip.setBounds(380,400,80,30);
    }
    void addCmp(){
        add(sp_tb);
        add(lb_tit);
        add(lb_tno);
        add(lb_name);
        add(lb_age);
        add(lb_src);
        add(lb_dest);
        add(img);
        add(bt_cnl);
        add(bt_skip);
    }
}
class booking {
    public static void main(String ss[]) throws Exception {
        new ticket();
    }
}