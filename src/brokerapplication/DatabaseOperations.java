/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokerapplication;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
/**
 *
 * @author Mayank Kakad
 */
public class DatabaseOperations {
    Connection conn;
    Statement st;
    ResultSet rs;
    public DatabaseOperations() {
        try
        {
            Class.forName("org.sqlite.JDBC");
            conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");
            st=conn.createStatement();
        }
        catch(Exception e){}
    }
    public boolean new_account(String fname, String lname, String username, String password, String security_question, String security_answer) {
        boolean result=false;
        try
        {
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            byte[] passhash=md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger number=new BigInteger(1,passhash);
            StringBuilder hashPass=new StringBuilder(number.toString(16));
            while(hashPass.length()<32)
                hashPass.insert(0,'0');
            String passwordHash=hashPass.toString();
            st=conn.createStatement();
            rs=st.executeQuery("select `username`,`password_hash` from `userdata` where `username`=\""+username+"\";");
            if(rs.next())
                result=false;
            else
            {
                result=true;
                st=conn.createStatement();
                st.executeUpdate("insert into `userdata` values(\""+fname+"\",\""+lname+"\",\""+username+"\",\""+passwordHash+"\",\""+security_question+"\",\""+security_answer+"\");");
            }
        }
        catch(Exception e){}
        return result;
    }
    public int verify_credentials(String username, String password) {
        int result=0;
        try
        {
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            byte[] passhash=md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger number=new BigInteger(1,passhash);
            StringBuilder hashPass=new StringBuilder(number.toString(16));
            while(hashPass.length()<32)
                hashPass.insert(0,'0');
            String passwordHash=hashPass.toString();
            st=conn.createStatement();
            rs=st.executeQuery("select `username`,`password_hash` from `userdata` where `username`=\""+username+"\";");
            if(rs.next())
            {
                if(rs.getString("password_hash").equals(passwordHash))
                    result=2;
                else
                    result=1;
            }
        }
        catch(Exception e){}
        return result;
    }
    public String getSecurityQuestion(String username) {
        String retval="";
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select `security_question` from `userdata` where `username`=\""+username+"\";");
            rs.next();
            retval=rs.getString("security_question");
        }
        catch(Exception e){}
        return retval;
    }
    public String getSecurityAnswer(String username) {
        String retval="";
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select `security_answer` from `userdata` where `username`=\""+username+"\";");
            rs.next();
            retval=rs.getString("security_answer");
        }
        catch(Exception e){}
        return retval;
    }
    public void updatePassword(String username, String password) {
        try
        {
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            byte[] passhash=md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger number=new BigInteger(1,passhash);
            StringBuilder hashPass=new StringBuilder(number.toString(16));
            while(hashPass.length()<32)
                hashPass.insert(0,'0');
            String passwordHash=hashPass.toString();
            st=conn.createStatement();
            st.executeUpdate("update `userdata` set `password_hash`=\""+passwordHash+"\" where `username`=\""+username+"\";");
        }
        catch(Exception e){}
    }
    public boolean check_username(String username) {
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `userdata` where `username`=\""+username+"\";");
            if(rs.next())
                return true;
        }
        catch(Exception e){}
        return false;
    }
    public String getName(String username) {
        String name="";
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select `first_name`,`last_name` from `userdata` where `username`=\""+username+"\";");
            rs.next();
            name=rs.getString("first_name")+" "+rs.getString("last_name");
        }
        catch(Exception e){}
        return name;
    }
    public boolean addSeller(Vector<Seller> sellerlist)
    {
        boolean success=false;
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select count(*) as `total` from `sellerdata`;");
            rs.next();
            int count=rs.getInt("total")+1;
            rs.close();
            st.close();
            for(int i=0;i<sellerlist.size();i++)
            {
                st=conn.createStatement();
                String query="insert into `sellerdata` values("+(count++)+",\""+LoginPage.loggedInUser+"\",\""+sellerlist.get(i).code+"\",\""+sellerlist.get(i).name+"\","+sellerlist.get(i).quantity+");";
                st.executeUpdate(query);
            }
            success=true;
        }
        catch(Exception e){}
        return success;
    }
    public boolean addBuyer(Vector<Buyer> buyerlist)
    {
        boolean success=false;
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select count(*) as `total` from `buyerdata`;");
            rs.next();
            int count=rs.getInt("total")+1;
            rs.close();
            for(int i=0;i<buyerlist.size();i++,count++)
            {
                Statement st1=conn.createStatement();
                st1.executeUpdate("insert into `buyerdata` values("+count+",\""+LoginPage.loggedInUser+"\",\""+buyerlist.get(i).code+"\",\""+buyerlist.get(i).name+"\","+buyerlist.get(i).quantity+");");
            }
            success=true;
        }
        catch(Exception e){}
        return success;
    }
    public Vector<Seller> getSellerList()
    {
        Vector<Seller> sellerlist=new Vector<Seller>();
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\";");
            while(rs.next())
                sellerlist.add(new Seller(rs.getString("seller_code"),rs.getString("seller_name"),rs.getInt("quantity")));
        }
        catch(Exception e){}
        return sellerlist;
    }
    public Vector<Buyer> getBuyerList()
    {
        Vector<Buyer> buyerlist=new Vector<Buyer>();
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\";");
            while(rs.next())
                buyerlist.add(new Buyer(rs.getString("buyer_code"),rs.getString("buyer_name"),rs.getInt("quantity")));
        }
        catch(Exception e){}
        return buyerlist;
    }
    public void deleteCustomer(String username,String code,String name,String type)
    {
        try
        {
            st=conn.createStatement();
            if(type.equals("seller"))
                st.executeUpdate("delete from `sellerdata` where `username`=\""+username+"\" and `seller_code`=\""+code+"\" and `seller_name`=\""+name+"\";");
            else
                st.executeUpdate("delete from `buyerdata` where `username`=\""+username+"\" and `buyer_code`=\""+code+"\" and `buyer_name`=\""+name+"\";");
        }
        catch(Exception e){}
    }
    public void updateCustomer(String username,String ogcode,String ogname,String newcode,String newname,String newquantity,String type,String newtype)
    {
        try
        {
            st=conn.createStatement();
            if(newtype.equals(type))
            {
                if(type.equals("seller"))
                    st.executeUpdate("update `sellerdata` set `seller_code`=\""+newcode+"\",`seller_name`=\""+newname+"\",`quantity`="+Integer.parseInt(newquantity)+" where `username`=\""+username+"\" and `seller_code`=\""+ogcode+"\" and `seller_name`=\""+ogname+"\";");
                else
                    st.executeUpdate("update `buyerdata` set `buyer_code`=\""+newcode+"\",`buyer_name`=\""+newname+"\",`quantity`="+Integer.parseInt(newquantity)+" where `username`=\""+username+"\" and `buyer_code`=\""+ogcode+"\" and `buyer_name`=\""+ogname+"\";");
            }
            else
            {
                if(type.equals("seller"))
                {
                    st.executeUpdate("delete from `sellerdata` where `username`=\""+username+"\" and `seller_code`=\""+ogcode+"\" and `seller_name`=\""+ogname+"\";");
                    st=conn.createStatement();
                    rs=st.executeQuery("select count(*) as `total` from `buyerdata`;");
                    rs.next();
                    int count=rs.getInt("total")+1;
                    st=conn.createStatement();
                    st.executeUpdate("insert into `buyerdata` values("+count+",\""+username+"\",\""+newcode+"\",\""+newname+"\","+newquantity+");");
                }
                else
                {
                    st.executeUpdate("delete from `buyerdata` where `username`=\""+username+"\" and `buyer_code`=\""+ogcode+"\" and `buyer_name`=\""+ogname+"\";");
                    st=conn.createStatement();
                    rs=st.executeQuery("select count(*) as `total` from `sellerdata`;");
                    rs.next();
                    int count=rs.getInt("total")+1;
                    st=conn.createStatement();
                    st.executeUpdate("insert into `sellerdata` values("+count+",\""+username+"\",\""+newcode+"\",\""+newname+"\","+newquantity+");");
                }
            }
        }
        catch(Exception e){}
    }
    public Vector<Seller> getSpecificSellerList(String text)
    {
        Vector<Seller> sellerlist=new Vector<Seller>();
        try
        {
            text="%"+text+"%";
            st=conn.createStatement();
            rs=st.executeQuery("select * from `sellerdata` where `seller_code` like \'"+text+"\';");
            while(rs.next())
                sellerlist.add(new Seller(rs.getString("seller_code"),rs.getString("seller_name"),rs.getInt("quantity")));
        }
        catch(Exception e){e.printStackTrace();}
        return sellerlist;
    }
    public Vector<Buyer> getSpecificBuyerList(String text)
    {
        Vector<Buyer> buyerlist=new Vector<Buyer>();
        try
        {
            text="%"+text+"%";
            st=conn.createStatement();
            rs=st.executeQuery("select * from `buyerdata` where `buyer_code` like \'"+text+"\';");
            while(rs.next())
                buyerlist.add(new Buyer(rs.getString("buyer_code"),rs.getString("buyer_name"),rs.getInt("quantity")));
        }
        catch(Exception e){}
        return buyerlist;
    }
    public void addSellerWiseTransaction(Seller mainseller,Vector<Buyer> finalbuyerlist,Vector<String> items,Vector<String> rate,String date)
    {
        String scode,bcode,time;
        int total=0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        time=dtf.format(now);
        try
        {
            if(mainseller.code.equals(""))
            {
                st=conn.createStatement();
                rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_name`=\""+mainseller.name+"\";");
                rs.next();
                scode=rs.getString("seller_code");
            }
            else
            {
                String text="%"+mainseller.code+"%";
                st=conn.createStatement();
                rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_name`=\""+mainseller.name+"\" and `seller_code` like \'"+text+"\';");
                rs.next();
                scode=rs.getString("seller_code");
            }
            for(int i=0;i<finalbuyerlist.size();i++)
            {
                if(finalbuyerlist.get(i).code.equals(""))
                {
                    st=conn.createStatement();
                    rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_name`=\""+finalbuyerlist.get(i).name+"\";");
                    rs.next();
                    bcode=rs.getString("buyer_code");
                }
                else
                {
                    String text="%"+finalbuyerlist.get(i).code+"%";
                    st=conn.createStatement();
                    rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_name`=\""+finalbuyerlist.get(i).name+"\" and `buyer_code` like \'"+text+"\';");
                    rs.next();
                    bcode=rs.getString("buyer_code");
                }
                st=conn.createStatement();
                st.executeUpdate("insert into `transactions` values(\""+date+"\",\""+time+"\",\""+LoginPage.loggedInUser+"\",\""+scode+"\",\""+mainseller.name+"\",\""+bcode+"\",\""+finalbuyerlist.get(i).name+"\",\""+items.get(i)+"\",\""+finalbuyerlist.get(i).quantity+"\","+Integer.parseInt(rate.get(i))+");");
                st=conn.createStatement();
                st.executeUpdate("update `buyerdata` set `quantity`=`quantity`+"+finalbuyerlist.get(i).quantity+" where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_code`=\""+bcode+"\" and `buyer_name`=\""+finalbuyerlist.get(i).name+"\";");
                total=total+finalbuyerlist.get(i).quantity;
            }
            st=conn.createStatement();
            st.executeUpdate("update `sellerdata` set `quantity`=`quantity`+"+total+" where `username`=\""+LoginPage.loggedInUser+"\" and `seller_code`=\""+scode+"\" and `seller_name`=\""+mainseller.name+"\";");
        }
        catch(Exception e){
        e.printStackTrace();}
    }
    public void addBuyerWiseTransaction(Buyer mainbuyer,Vector<Seller> finalsellerlist,Vector<String> items,Vector<String> rate,String date)
    {
        String scode,bcode,time;
        int total=0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        time=dtf.format(now);
        try
        {
            if(mainbuyer.code.equals(""))
            {
                st=conn.createStatement();
                rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_name`=\""+mainbuyer.name+"\";");
                rs.next();
                bcode=rs.getString("buyer_code");
            }
            else
            {
                String text="%"+mainbuyer.code+"%";
                st=conn.createStatement();
                rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_name`=\""+mainbuyer.name+"\" and `buyer_code` like \'"+text+"\';");
                rs.next();
                bcode=rs.getString("buyer_code");
            }
            for(int i=0;i<finalsellerlist.size();i++)
            {
                if(finalsellerlist.get(i).code.equals(""))
                {
                    st=conn.createStatement();
                    rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_name`=\""+finalsellerlist.get(i).name+"\";");
                    rs.next();
                    scode=rs.getString("seller_code");
                }
                else
                {
                    String text="%"+finalsellerlist.get(i).code+"%";
                    st=conn.createStatement();
                    rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_name`=\""+finalsellerlist.get(i).name+"\" and `seller_code` like \'"+text+"\';");
                    rs.next();
                    scode=rs.getString("seller_code");
                }
                st=conn.createStatement();
                st.executeUpdate("insert into `transactions` values(\""+date+"\",\""+time+"\",\""+LoginPage.loggedInUser+"\",\""+scode+"\",\""+finalsellerlist.get(i).name+"\",\""+bcode+"\",\""+mainbuyer.name+"\",\""+items.get(i)+"\",\""+finalsellerlist.get(i).quantity+"\","+Integer.parseInt(rate.get(i))+");");
                st=conn.createStatement();
                st.executeUpdate("update `sellerdata` set `quantity`=`quantity`+"+finalsellerlist.get(i).quantity+" where `username`=\""+LoginPage.loggedInUser+"\" and `seller_code`=\""+bcode+"\" and `seller_name`=\""+finalsellerlist.get(i).name+"\";");
                total=total+finalsellerlist.get(i).quantity;
            }
            st=conn.createStatement();
            st.executeUpdate("update `buyerdata` set `quantity`=`quantity`+"+total+" where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_code`=\""+bcode+"\" and `buyer_name`=\""+mainbuyer.name+"\";");
        }
        catch(Exception e){
        e.printStackTrace();}
    }
}
