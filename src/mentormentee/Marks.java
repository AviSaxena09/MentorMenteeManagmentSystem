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

/**
 *
 * @author Moin Bhavnagri
 */
public class Marks {

    private int subject1;
    private int subject2;
    private int subject3;
    private int subject4;
    private int subject5;
    private int subject6;
    private double percen;
    private String grade;
    static long prn;
    static int value;

    public double getPercen() {
        return percen;
    }

    public void setPercen(double percen) {
        this.percen = percen;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Marks(int subject1, int subject2, int subject3, int subject4, int subject5, int subject6, long prn) {
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.subject4 = subject4;
        this.subject5 = subject5;
        this.subject6 = subject6;
        this.prn = prn;
    }
    
    public static Marks calculate(Marks m){
        double per;
        String g;
        per= m.getSubject1() + m.getSubject2() + m.getSubject3() + m.getSubject4()+ m.getSubject5() + m.getSubject6() ;
        per/=6;
   
        m.setPercen(per);
        if(per==100)
            {
            g="O";
            }
        else if(per>=90)
            {
            g="A+";
            }
        else if(per>=80)
            {
                g="A";
            }   
        else if(per>=70)
            {
            g="B+";
            }
        else if(per>=60){
            g="B";
            }
        else if(per>=50)
        {
            g="C";
        }
        else if(per>=40)
        {
            g="D";
        }
        else{
            g="F";
        }
        
        m.setGrade(g);
        return m;
    }
    
    public int getSubject1() {
        return subject1;
    }

    public void setSubject1(int subject1) {
        this.subject1 = subject1;
    }

    public int getSubject2() {
        return subject2;
    }

    public void setSubject2(int subject2) {
        this.subject2 = subject2;
    }

    public int getSubject3() {
        return subject3;
    }

    public void setSubject3(int subject3) {
        this.subject3 = subject3;
    }

    public int getSubject4() {
        return subject4;
    }

    public void setSubject4(int subject4) {
        this.subject4 = subject4;
    }

    public int getSubject5() {
        return subject5;
    }

    public void setSubject5(int subject5) {
        this.subject5 = subject5;
    }

    public int getSubject6() {
        return subject6;
    }

    public void setSubject6(int subject6) {
        this.subject6 = subject6;
    }

    public static long getPrn() {
        return prn;
    }


    public Marks(long id) {
        prn=id;
        Marks.value=0;
    }
    
    public Marks()
    {
        
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Marks{" +
                "subject1=" + subject1 +
                ", subject2=" + subject2 +
                ", subject3=" + subject3 +
                ", subject4=" + subject4 +
                ", subject5=" + subject5 +
                ", subject6=" + subject6 +
                ", prn=" + prn +
                '}';
    }
    
    public static void updateMarks(Marks m) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
              Statement stmt=con.createStatement();
              
              String cmd="insert into marks values("+Marks.getPrn()+","+m.getSubject1()+","+m.getSubject2()+","+m.getSubject3()+","+m.getSubject4()+","+m.getSubject5()+","+m.getSubject6()+");";
              String cmd1="update marks set subject1="+m.getSubject1()+",subject2="+m.getSubject2()+",subject3="+m.getSubject3()+",subject4="+m.getSubject4()+",subject5="+m.getSubject5()+",subject6="+m.getSubject6()+" where prn="+Marks.getPrn()+";";
             
           
              if(Marks.value==0)
              {
  //                System.out.println(cmd);
                  stmt.executeUpdate(cmd);
              }
              else
              {
//                   System.out.println(""+cmd1);
                  stmt.executeUpdate(cmd1);
              }
    }
    
    public static Marks getData(long prn){
        Marks m=new Marks();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
            Statement stmt=con.createStatement();
            String cmd="SELECT * from marks where prn="+prn+";";
            ResultSet rs=stmt.executeQuery(cmd);
            if(rs.next())
            {
                m.setSubject1(rs.getInt("subject1"));
                m.setSubject2(rs.getInt("subject2"));
                m.setSubject3(rs.getInt("subject3"));
                m.setSubject4(rs.getInt("subject4"));
                m.setSubject5(rs.getInt("subject5"));
                m.setSubject6(rs.getInt("subject6"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        
        return m;
    }
}