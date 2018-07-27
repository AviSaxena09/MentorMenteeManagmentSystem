/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mentormentee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author avisx
 */
public class TestFunction {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       //Mentor.setMentorCount();
       new ViewWardReport(4057).setVisible(true);
            //rs1.next();
           // System.out.println(""+"as  "+rs1.getInt("countprn"));
            //Student.studentCount = rs1.getInt("countprn");
    }
    
}
