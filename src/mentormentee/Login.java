/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mentormentee;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author avisx
 */
public class Login {
    public String userName;
    public String password;
    
    Student s=new Student();

    public Login(long prn) {
        s=Student.getData(prn);
    }
    
    public Login(){
        
    }

    public Login(String userName, String password, Student s) {
        this.userName = userName;
        this.password = password;
        this.s = s;
    }

    public static void setData(Login l) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
            Statement stmt = con.createStatement();
            
            String cmd = "insert into login values('"+l.getUserName()+"','"+l.getPassword()+"',"+l.getS().getPrn()+");";;
            stmt.executeUpdate(cmd);
            
            JOptionPane.showMessageDialog(null, "Id Created");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    
        public static Login getData(String user) {
            Login l=new Login();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
            Statement stmt = con.createStatement();
            
            String cmd = "select * from login where username= '"+user+"';";
            ResultSet rs=stmt.executeQuery(cmd);
            l.setUserName(rs.getString("username"));
            l.setPassword(rs.getString("password"));
            l.s.setPrn(Long.parseLong(rs.getString("prn")));
            //System.out.println(""+l.s.getPrn());
        }
        catch(Exception e){
            System.out.println(e);
        }
        return l;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student getS() {
        return s;
    }

    public void setS(Student s) {
        this.s = s;
    }


}
