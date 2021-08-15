package pack;

import java.sql.*;

public class sql{
    static Connection con;
    static Connection conn() throws  Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con =DriverManager.getConnection("jdbc:mysql://localhost:3306/TrainTicketReservationSystem", "root", "jana@123");
        System.out.println("Connected");
        return con;
    }
    public static void main(String args[]) throws Exception{
        sql.conn();

    }
}
