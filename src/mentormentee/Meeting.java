/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mentormentee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author avisx
 */
public class Meeting extends Allotment {
    private String date;
    private int present;
    private  int absent;
    private String feedback;

    public Meeting() {
    }


    public Meeting(String date, int present, int absent, String feedback) {
        this.date = date;
        this.present = present;
        this.absent = absent;
        this.feedback = feedback;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Meeting{" +
                "date=" + date +
                ", present=" + present +
                ", absent=" + absent +
                ", feedback='" + feedback + '\'' +
                '}';
    }
    
    public static void setData(Meeting mt){
        try {
            mt.getPrn();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
            Statement stmt = con.createStatement();
            String cmd=" insert into meeting values( '"+mt.getDate() +"' , "+mt.getPresent() +" , "+mt.getAbsent()+" ,'"+mt.getFeedback() +"' , "+mt.getFacultyId()+" ); " ;
            stmt.executeUpdate(cmd);
          
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    
    
    public static void updateData(Meeting mt){
        try {
            mt.getPrn();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
            Statement stmt = con.createStatement();
            String cmd=" update meeting set date = '"+mt.getDate() +"' , present = "+mt.getPresent() +" , absent = "+mt.getAbsent()+" , feedback = '"+mt.getFeedback() +"' where facultyid = "+mt.getFacultyId()+" ; " ;
            stmt.executeUpdate(cmd);
          
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    
    
    public static Meeting getData(long fid)
    {
        Meeting mt=new Meeting();


        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
            Statement stmt=con.createStatement();
            String cmd="SELECT * from meeting where facultyid = "+fid+" ;";
            ResultSet rs=stmt.executeQuery(cmd);
            if(rs.next())
            {
                mt.setDate(rs.getString("date"));
                mt.setPresent(rs.getInt("present"));
                mt.setAbsent(rs.getInt("absent"));
                mt.setFeedback(rs.getString("feedback"));
                mt.setFacultyId(rs.getLong("facultyid"));
                
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        //  System.out.println(id1);
        return mt;
    }
    
}
