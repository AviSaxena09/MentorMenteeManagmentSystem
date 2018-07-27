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


public class Allotment {
    public String relationId;
    /*
    relationId= facultyName+ last 2DigitOfPrn
     */
    private long prn;
    private long facultyId;

    static int mentorCount;
    static int studentCount;

    public Allotment(String relationId, long prn, long facultyId) {
        this.relationId = relationId;
        this.prn = prn;
        this.facultyId = facultyId;
        setMentorCount();
        setStudentCount();
    }

    public Allotment() {
        setMentorCount();
        setStudentCount();
    }


    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public void setRelationId(Mentor m, Student s)
    {
        this.relationId = m.getName()+""+ s.getPrn() ;
    }

    public long getPrn() {
        return prn;
    }

    public void setPrn(long prn) {
        this.prn = prn;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public static int getMentorCount() {
        return mentorCount;
    }

    public static void setMentorCount() {
        Allotment.mentorCount = Mentor.getMentorCount();
    }

    public static int getStudentCount() {
        return studentCount;
    }

    public static void setStudentCount() {
        Allotment.studentCount = Student.getStudentCount();
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Allotment{" +
                "relationId='" + relationId + '\'' +
                ", prn=" + prn +
                ", facultyId=" + facultyId +
                '}';
    }

    /*public static void allotMentor() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=null;
        con= DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
        int lim=2,l;
        long sprn,fid;
        Student.setStudentCount(4);
        int nostud=0;// Student.getStudentCount();
        Mentor.setLimit(lim);
        Mentor m=new Mentor();
        Student s=new Student();
        
         ////////////Delete all old records//////////////////////////////
       
                Statement stmt4 = con.createStatement();
                String cmd4 = "truncate table allotment;";
                stmt4.executeUpdate(cmd4);
            
        //////////////////////////////////////////
       Statement stmt=con.createStatement();
       String ab="Select * from faculty ;";
       ResultSet rs=stmt.executeQuery(ab);
       
      
           
       while(rs.next()){
           
           m= Mentor.getData(rs.getLong("facultyid"));
                    for(int i=1;i<=lim;i++){
                        
                    ResultSet rs2=stmt.executeQuery("select * from student;");
                    while(rs2.next()){
                    s=Student.getData(rs2.getLong("prn"));
                    System.out.println(""+m.toString());
                        System.out.println(""+s.toString());
                        
                    }
       }
       }

    }

    */
   public static void allotMentor(Student s) throws SQLException, ClassNotFoundException
    {
        Mentor.setLimit();
        //System.out.println("limit is"+Mentor.getLimit());
        long id, lim=Mentor.getLimit();
        
        Mentor m=new  Mentor();
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
        Statement stmt=con.createStatement();
        ResultSet rs=null;
        rs = stmt.executeQuery("select * from faculty where noofstudent < "+lim+";");
        
        if(rs.next()){
           id= rs.getLong("facultyid");
            m=Mentor.getData(id);
           Allotment.setData(m, s);
           Mentor.inc(m);
           
        }
        else
        {
            
        }
        
       
        
        

        ///////////////////////////////////////////
        con.close();
    }

    public static void setData(Mentor m, Student s)
    {
        Allotment a=new Allotment();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
            Statement stmt = con.createStatement();
            //String cmd="insert into student values('"+s.getName()+"','"+s.getDob()+"',"+s.getPrn()+",'"+s.getDepartment()+"','"+s.getParentName()+"',"+s.getMobileNumber()+",'"+s.getEmail()+"');";
            a.setRelationId(m, s);
            a.setPrn(s.getPrn());
            a.setFacultyId(m.getFacultyId());
            s.setMentorName(m.getName());
            stmt.executeUpdate("update student set mentorname= '"+m.getName()+"' where prn = "+s.getPrn()+";");
            //Student.updateData(s);
            String cmd="insert into allotment values('"+a.getRelationId()+"',"+a.getFacultyId()+","+a.getPrn() +");";
            stmt.executeUpdate(cmd);
            Student.count();
            Message msg=new Message();
            stmt.executeUpdate("insert into message values("+msg.getSid()+" , "+msg.getRid()+" ,'"+msg.getSmsg() +"', '"+msg.getRmsg()+"', '"+a.getRelationId()+"' );");
            JOptionPane.showMessageDialog(null, "Allotment done !!!");
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static Allotment getData(long prn)
    {
        Allotment a=new Allotment();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
            Statement stmt=con.createStatement();
            String cmd="SELECT * from allotment where prn="+prn+";";
            ResultSet rs=stmt.executeQuery(cmd);
            if(rs.next())
            {
                a.setFacultyId(rs.getLong("facultyid"));
                a.setPrn(prn);
                a.setRelationId(rs.getString("relationid"));
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
        return a;
    }
}


