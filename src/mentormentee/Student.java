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
public class Student {
    
     private String name;
    private String dob;
    private long prn;
    private String department;
    private String gender;
    private String parentName;
    private long mobileNumber;
    private String email;
    public static int studentCount=0;
    public String mentorName;

    public static void setStudentCount() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
            Statement stmt=con.createStatement();
            String cmd="SELECT count(prn) as countprn from student";
            ResultSet rs1 = stmt.executeQuery(cmd);
            rs1.next();
           // System.out.println(""+"as  "+rs1.getInt("countprn"));
            Student.studentCount = rs1.getInt("countprn");
        
    }

    public static void count(){
        studentCount++;

    }



    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public static int getStudentCount() {
        return studentCount;
    }

    public Student(String name, String dob, long prn, String department, String gender, String parentName, long mobileNumber, String email) {
        this.name = name;
        this.dob = dob;
        this.prn = prn;
        this.department = department;
        this.gender = gender;
        this.parentName = parentName;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public Student() {
    }

    public Student(Student s){
        this.name = s.name;
        this.dob = s.dob;
        this.prn = s.prn;
        this.gender=s.gender;
        this.department = s.department;
        this.parentName = s.parentName;
        this.mobileNumber = s.mobileNumber;
        this.email = s.email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public long getPrn() {
        return prn;
    }

    public void setPrn(long prn) {
        this.prn = prn;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", prn=" + prn +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                ", parentName='" + parentName + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", email='" + email + '\'' +
                ", mentorName='" + mentorName + '\'' +
                '}';
    }

    public static Student getData(long prn)
    {
        Student s=new Student();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
            Statement stmt=con.createStatement();
            String cmd="SELECT * from student where prn="+prn+";";
            ResultSet rs=stmt.executeQuery(cmd);
            if(rs.next())
            {
                s.setName(rs.getString("name"));
                s.setDob(rs.getString("dob"));
                s.setPrn(rs.getLong("prn"));
                s.setDepartment(rs.getString("department"));
                s.setParentName(rs.getString("parentname"));
                s.setMobileNumber(rs.getLong("mobilenumber"));
                s.setEmail(rs.getString("email"));
                s.setMentorName(rs.getString("mentorname"));
                s.setGender(rs.getString("gender"));
            }
            else
            {

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        //  System.out.println(id1);
        return s;
    }

    public static void setData(Student s){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
            Statement stmt = con.createStatement();
            s.setMentorName("null");
            //String cmd="insert into student values('"+s.getName()+"','"+s.getDob()+"',"+s.getPrn()+",'"+s.getDepartment()+"','"+s.getParentName()+"',"+s.getMobileNumber()+",'"+s.getEmail()+"');";
            String cmd="insert into student values('"+s.getName()+"','"+s.getDob()+"',"+s.getPrn()+",'"+s.getDepartment()+"','"+s.getParentName()+"',"+s.getMobileNumber()+",'"+s.getEmail()+"','"+s.getGender()+"','"+s.getMentorName()+"');";
            stmt.executeUpdate(cmd);
            Student.count();
            JOptionPane.showMessageDialog(null, "Record inserted");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void updateData(Student s){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
            Statement stmt = con.createStatement();
            String cmd="update student set name = '"+s.getName()+"', dob = '"+s.getDob()+"', prn = "+s.getPrn()+", department = '"+s.getDepartment()+"', parentname = '"+s.getParentName()+"', mobilenumber = "+s.getMobileNumber()+", email = '"+s.getEmail()+"', gender = '"+s.getGender()+"', mentorname = '"+s.getMentorName()+"';";
            stmt.executeUpdate(cmd);
            JOptionPane.showMessageDialog(null, "Record Updated");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
   public static void setReport(long prn){
         try{
         Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
            Statement stmt=con.createStatement();
            //String cmd="SELECT count(prn) as countprn from student";
            stmt.executeUpdate("insert into report values("+prn+", ' ', '');" );
        }
        catch(Exception e)
        {
            
        }
   }




}

    