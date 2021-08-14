/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;
import java.util.regex.*;
import javax.swing.*;
/**
 *
 * @author menag
 */
public class Validate {
    public static  boolean validateEmail(String email)
    {
        String regExp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        if(email.matches(regExp))
        {
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Invalid Email Address","Invalid Email Address",0);
            return false;
        }
    }
    
    public static boolean validatePassword(String password)
    {
        String regExp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.@#$%^&-+=()])(?=\\S+$).{6,10}$";
        if(password.matches(regExp))
        {
            return true;
        }
        else
        {
            String message="1.Password must contain atleast one Uppercase letter\n"
                    + "2.Password must contain atleast one Lowercase letter\n"
                    + "3.Password must contain atleast one Digit\n"
                    + "4.Password must contain atleast one Special character\n"
                    + "5.Password must be less than 10 and more than 6 characters in length.";
            JOptionPane.showMessageDialog(null,message,"Invalid Password",0);
            return false;
        }
    }
    public static boolean checkConformPassword(String password,String conformPassword)
    {
        if(password.equalsIgnoreCase(conformPassword))
        {
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Passwords dont match");
            return false;
        }
    }
}
