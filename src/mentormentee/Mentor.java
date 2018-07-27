/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mentormentee;

/**
 *
 * @author avisx
 */
   import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

 

public class Mentor {
    private String name;
    private long facultyId;
    private String department;
    private String gender;
    private long mobileNumber;
    private String email;
    private String userName;
    private String password;
    public static int mentorCount=0;
    public static int limit;
    private int noOfStudent=0;

    public static void count(){
        mentorCount++;

    }

    public int getNoOfStudent() {
        return noOfStudent;
    }

    public void setNoOfStudent(int noOfStudent) {
        
        this.noOfStudent = noOfStudent;
    }

    public static int getLimit() {
        return limit;
    }

    public static void setLimit() throws ClassNotFoundException, SQLException {
        Student.setStudentCount();
        Mentor.setMentorCount();
        int s=Student.getStudentCount();
        int m=Mentor.getMentorCount();
        int l= s/m;
        l++;
        Mentor.limit = l;
    }

    public static void setMentorCount() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
            Statement stmt=con.createStatement();
            String cmd="SELECT count(facultyid) as countid from faculty";
            ResultSet rs1 = stmt.executeQuery(cmd);
            rs1.next();
           // System.out.println(""+"as  "+rs1.getInt("countprn"));
            Mentor.mentorCount = rs1.getInt("countid");
    }
    
    public static int getMentorCount() {
        return mentorCount;
    }

    public Mentor(String name, long facultyId, String department, String gender, long mobileNumber, String email, String userName, String password) {
        this.name = name;
        this.facultyId = facultyId;
        this.department = department;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public Mentor(Mentor m) {
        this.name = m.name;
        this.facultyId = m.facultyId;
        this.department = m.department;
        this.gender = m.gender;
        this.mobileNumber = m.mobileNumber;
        this.email = m.email;
        this.userName = m.userName;
        this.password = m.password;
    }

    public Mentor() {
        noOfStudent=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "Mentor{" +
                "name='" + name + '\'' +
                ", facultyId=" + facultyId +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", noOfStudent=" + noOfStudent +
                '}';
    }

    public static void inc(Mentor m) throws ClassNotFoundException, SQLException{
           long no=m.getNoOfStudent();
            long id=m.getFacultyId();
            no+=1;
            System.out.println(""+m.toString());
            m.setNoOfStudent((int) no);
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
           Statement stmt5=con.createStatement();
           String u="update faculty set noofstudent = "+no+" where facultyid = "+id+";";
           stmt5.executeUpdate(u);

            //Mentor.updateData(m);
            m=Mentor.getData(id);
            System.out.println(""+m.toString());
            System.out.println("no "+m.getNoOfStudent() +"   "+ no );    
        
        
        
    }
    
    public static Mentor getData(long id)
    {
        Mentor m=new Mentor();


        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
            Statement stmt=con.createStatement();
            String cmd="SELECT * from faculty where facultyid="+id+";";
            ResultSet rs=stmt.executeQuery(cmd);
            if(rs.next())
            {
                m.setName(rs.getString("name"));
                m.setFacultyId(rs.getLong("facultyid"));
                m.setDepartment(rs.getString("department"));
                m.setGender(rs.getString("gender"));
                m.setMobileNumber(rs.getLong("mobilenumber"));
                m.setEmail(rs.getString("email"));
                m.setUserName(rs.getString("username"));
                m.setPassword(rs.getString("password"));
                m.setNoOfStudent((int) rs.getLong("noofstudent"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        //  System.out.println(id1);
        return m;
    }

    public static void setData(Mentor m){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
            Statement stmt = con.createStatement();
            String cmd="insert into faculty values('"+m.getName()+"',"+m.getFacultyId()+",'"+m.getDepartment()+"','"+m.getGender()+"',"+m.getMobileNumber()+",'"+m.getEmail()+"','"+m.getUserName()+"','"+m.getPassword()+"', "+ m.getNoOfStudent() +");";
            stmt.executeUpdate(cmd);
            Mentor.count();
        }
        catch(Exception e){
            System.out.println(e);
        }
       
    }
/////////////////////////////////////////////////////////////////////////////////////
    public static void updateData(Mentor m){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
            Statement stmt = con.createStatement();
            //String faculty=new
            String cmd=" update faculty set name= '"+m.getName()+"', facultyid = "+m.getFacultyId()+", department = '"+m.getDepartment()+"', gender = '"+m.getGender()+"', mobilenumber = "+m.getMobileNumber()+", email = '"+m.getEmail()+"', username = '"+m.getUserName()+"', password = '"+m.getPassword()+"', noofstudent = "+m.getNoOfStudent() + " where facultyid = "+m.getFacultyId() +";";
            stmt.executeUpdate(cmd);
           /* PreparedStatement ps= con.prepareStatement("update faculty set (?,?,?,?,?,?,?,?,?)");
            ps.setString(1,m.getName());
            ps.setLong(2, m.getFacultyId());
            ps.setString(3, m.getDepartment());
            ps.setString( 4 , m.getGender());
            ps.setLong(5, m.getMobileNumber());
            ps.setString( 6, m.getEmail());
            ps.setString(7, m.getUserName() );
            ps.setString(8 , m.getPassword());
            ps.setLong(9, m.getNoOfStudent());*/
            JOptionPane.showMessageDialog(null,"Record Updated");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void updateData(Mentor m, int stud){
        
                                         try{
                                     Class.forName("com.mysql.jdbc.Driver");
                                     Connection con5=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
                                     Statement stmt5=con5.createStatement();
                                   String u="update faculty set noofstudent= "+stud+" where facultyid = "+m.getFacultyId()+" ;";
                                   stmt5.executeUpdate(u);
                                }catch(Exception e)
                                 {
                                     System.out.println(e);
                                 }
    }
    
}

    
