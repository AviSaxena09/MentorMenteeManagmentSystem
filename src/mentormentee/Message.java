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
public class Message {
    private int sid;
    private int rid;
    private String smsg;
    private String rmsg;
    public Allotment a;
    public Message(int sid, int rid, String smsg, String rmsg) {
        this.sid = sid;
        this.rid = rid;
        this.smsg = smsg;
        this.rmsg = rmsg;
    }

    public Message() {
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getSmsg() {
        return smsg;
    }

    public void setSmsg(String smsg) {
        this.smsg = smsg;
    }

    public String getRmsg() {
        return rmsg;
    }

    public void setRmsg(String rmsg) {
        this.rmsg = rmsg;
    }

    public Allotment getA() {
        return a;
    }

    public void setA(Allotment a) {
        this.a = a;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Message{" +
                "sid=" + sid +
                ", rid=" + rid +
                ", smsg='" + smsg + '\'' +
                ", rmsg='" + rmsg + '\'' +
                ", a=" + a +
                '}';
    }

    static void updateData(Message m){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
            Statement stmt = con.createStatement();
          
            String cmd="update message set senderid = "+m.getSid()+" , recieverid = "+m.getRid()+" , smsg='"+m.getSmsg() +"', rmsg = '"+m.getRmsg()+"' where relationid = '"+m.a.getRelationId()+"' ;";
            stmt.executeUpdate(cmd);
            //Student.count();
            JOptionPane.showMessageDialog(null, "Message Sent");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static Message getData(String rid)
    {
        Message m=new Message();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
            Statement stmt=con.createStatement();
            String cmd="SELECT * from message where relationid = '"+rid+"';";
            ResultSet rs=stmt.executeQuery(cmd);
            if(rs.next())
            {
                m.setRid((int) rs.getLong("recieverid"));
                m.setSid((int) rs.getLong("senderid"));
                m.setRmsg(rs.getString("rmsg"));
                m.setSmsg(rs.getString("smsg"));
                m.a.setRelationId(rs.getString("relationid"));
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
        return m;
    }
    
    static void setData(Message m , Allotment a){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "root");
            Statement stmt = con.createStatement();
          
       //     String cmd="insert into message values("+m.getSid()+" , "+m.getRid()+" ,'"+m.getSmsg() +"', '"+m.getRmsg()+"', '"+a.getRelationId()+"' );";
            stmt.executeUpdate("insert into message values("+m.getSid()+" , "+m.getRid()+" ,'"+m.getSmsg() +"', '"+m.getRmsg()+"', '"+a.getRelationId()+"' );");
            //String s="insert into message values("+m.getSid()+" , "+m.getRid()+" ,'"+m.getSmsg() +"', '"+m.getRmsg()+"', '"+a.getRelationId()+"' );";
            //System.out.println("\n"+s);
            
            Student.count();
            JOptionPane.showMessageDialog(null, "Message Sent");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
