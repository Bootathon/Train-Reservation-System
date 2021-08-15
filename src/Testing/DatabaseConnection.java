/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HP
 */
//Connecting with Sql Database
public class DatabaseConnection {
    
    static Connection con;

    DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con =DriverManager.getConnection("jdbc:mysql://localhost:3306/TrainTicketReservationSystem", "root", "Saju#123");           
        } catch (Exception e) {
            System.out.print(e);
        }
    }
        
        
}
