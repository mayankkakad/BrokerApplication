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
        }
        catch(Exception e){}
    }
    public boolean new_account(String fname, String lname, String username, String password, String security_question, String security_answer) {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
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
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return result;
    }
    public int verify_credentials(String username, String password) {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
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
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return result;
    }
    public String getSecurityQuestion(String username) {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        String retval="";
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select `security_question` from `userdata` where `username`=\""+username+"\";");
            rs.next();
            retval=rs.getString("security_question");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return retval;
    }
    public String getSecurityAnswer(String username) {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        String retval="";
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select `security_answer` from `userdata` where `username`=\""+username+"\";");
            rs.next();
            retval=rs.getString("security_answer");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return retval;
    }
    public void updatePassword(String username, String password) {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
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
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public boolean check_username(String username) {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `userdata` where `username`=\""+username+"\";");
            if(rs.next())
            {
                try{conn.close();}catch(Exception e){e.printStackTrace();}
                return true;
            }
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return false;
    }
    public String getName(String username) {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        String name="";
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select `first_name`,`last_name` from `userdata` where `username`=\""+username+"\";");
            rs.next();
            name=rs.getString("first_name")+" "+rs.getString("last_name");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return name;
    }
    public boolean addSeller(Vector<Seller> sellerlist,Vector<String> sellerplacelist)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        boolean success=false;
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select count(*) as `total` from `sellerdata`;");
            rs.next();
            int count=rs.getInt("total")+1;
            for(int i=0;i<sellerlist.size();i++)
            {
                st=conn.createStatement();
                rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_name`=\""+sellerlist.get(i).name+"\";");
                if(rs.next())
                    continue;
                st=conn.createStatement();
                String query="insert into `sellerdata` values("+(count++)+",\""+LoginPage.loggedInUser+"\",\""+sellerlist.get(i).code+"\",\""+sellerlist.get(i).name+"\","+sellerlist.get(i).quantity+",\""+sellerplacelist.get(i)+"\");";
                st.executeUpdate(query);
            }
            success=true;
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return success;
    }
    public boolean addBuyer(Vector<Buyer> buyerlist,Vector<String> buyerplacelist)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
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
                st=conn.createStatement();
                rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_name`=\""+buyerlist.get(i).name+"\";");
                if(rs.next())
                    continue;
                Statement st1=conn.createStatement();
                st1.executeUpdate("insert into `buyerdata` values("+count+",\""+LoginPage.loggedInUser+"\",\""+buyerlist.get(i).code+"\",\""+buyerlist.get(i).name+"\","+buyerlist.get(i).quantity+",\""+buyerplacelist.get(i)+"\");");
            }
            success=true;
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return success;
    }
    public Vector<Seller> getSellerList()
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<Seller> sellerlist=new Vector<Seller>();
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" order by `seller_name` asc;");
            while(rs.next())
                sellerlist.add(new Seller(rs.getString("seller_code"),rs.getString("seller_name"),rs.getInt("quantity")));
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return sellerlist;
    }
    public Vector<Buyer> getBuyerList()
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<Buyer> buyerlist=new Vector<Buyer>();
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" order by `buyer_name` asc;");
            while(rs.next())
                buyerlist.add(new Buyer(rs.getString("buyer_code"),rs.getString("buyer_name"),rs.getInt("quantity")));
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return buyerlist;
    }
    public void deleteCustomer(String username,String code,String name,String type)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            if(type.equals("seller"))
                st.executeUpdate("delete from `sellerdata` where `username`=\""+username+"\" and `seller_code`=\""+code+"\" and `seller_name`=\""+name+"\";");
            else
                st.executeUpdate("delete from `buyerdata` where `username`=\""+username+"\" and `buyer_code`=\""+code+"\" and `buyer_name`=\""+name+"\";");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public void updateCustomer(String username,String ogcode,String ogname,String newcode,String newname,String newquantity,String newplace,String type,String newtype)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            if(newtype.equals(type))
            {
                if(type.equals("seller"))
                    st.executeUpdate("update `sellerdata` set `seller_code`=\""+newcode+"\",`seller_name`=\""+newname+"\",`quantity`="+Integer.parseInt(newquantity)+",`place`=\""+newplace+"\" where `username`=\""+username+"\" and `seller_code`=\""+ogcode+"\" and `seller_name`=\""+ogname+"\";");
                else
                    st.executeUpdate("update `buyerdata` set `buyer_code`=\""+newcode+"\",`buyer_name`=\""+newname+"\",`quantity`="+Integer.parseInt(newquantity)+",`place`=\""+newplace+"\" where `username`=\""+username+"\" and `buyer_code`=\""+ogcode+"\" and `buyer_name`=\""+ogname+"\";");
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
                    st.executeUpdate("insert into `buyerdata` values("+count+",\""+username+"\",\""+newcode+"\",\""+newname+"\","+newquantity+",\""+newplace+"\");");
                }
                else
                {
                    st.executeUpdate("delete from `buyerdata` where `username`=\""+username+"\" and `buyer_code`=\""+ogcode+"\" and `buyer_name`=\""+ogname+"\";");
                    st=conn.createStatement();
                    rs=st.executeQuery("select count(*) as `total` from `sellerdata`;");
                    rs.next();
                    int count=rs.getInt("total")+1;
                    st=conn.createStatement();
                    st.executeUpdate("insert into `sellerdata` values("+count+",\""+username+"\",\""+newcode+"\",\""+newname+"\","+newquantity+",\""+newplace+"\");");
                }
            }
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public Vector<Seller> getSpecificSellerList(String text)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<Seller> sellerlist=new Vector<Seller>();
        try
        {
            text="%"+text+"%";
            st=conn.createStatement();
            rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_code` like \'"+text+"\' order by `seller_name` asc;");
            while(rs.next())
                sellerlist.add(new Seller(rs.getString("seller_code"),rs.getString("seller_name"),rs.getInt("quantity")));
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return sellerlist;
    }
    public Vector<Buyer> getSpecificBuyerList(String text)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<Buyer> buyerlist=new Vector<Buyer>();
        try
        {
            text="%"+text+"%";
            st=conn.createStatement();
            rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_code` like \'"+text+"\' order by `buyer_name` asc;");
            while(rs.next())
                buyerlist.add(new Buyer(rs.getString("buyer_code"),rs.getString("buyer_name"),rs.getInt("quantity")));
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return buyerlist;
    }
    public void addSellerWiseTransaction(Seller mainseller,Vector<Buyer> finalbuyerlist,Vector<String> items,Vector<String> rate,String date)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        String scode,bcode,time;
        int total=0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        time=dtf.format(now).substring(0,5);
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
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public void addBuyerWiseTransaction(Buyer mainbuyer,Vector<Seller> finalsellerlist,Vector<String> items,Vector<String> rate,String date)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        String scode,bcode,time;
        int total=0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        time=dtf.format(now).substring(0,5);
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
                st.executeUpdate("update `sellerdata` set `quantity`=`quantity`+"+finalsellerlist.get(i).quantity+" where `username`=\""+LoginPage.loggedInUser+"\" and `seller_code`=\""+scode+"\" and `seller_name`=\""+finalsellerlist.get(i).name+"\";");
                total=total+finalsellerlist.get(i).quantity;
            }
            st=conn.createStatement();
            st.executeUpdate("update `buyerdata` set `quantity`=`quantity`+"+total+" where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_code`=\""+bcode+"\" and `buyer_name`=\""+mainbuyer.name+"\";");
        }
        catch(Exception e){
        e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public Vector<String> getDates()
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<String> dates=new Vector<String>();
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select distinct `date` from `transactions` where `username`=\""+LoginPage.loggedInUser+"\";");
            while(rs.next())
                dates.add(rs.getString("date"));
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return dates;
    }
    public Vector<Transaction> getTransactions(String date)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<Transaction> transactions=new Vector<Transaction>();
        try
        {
            st=conn.createStatement();
            if(date.equals("all"))
            {
                rs=st.executeQuery("select * from `transactions` where `username`=\""+LoginPage.loggedInUser+"\";");
                while(rs.next())
                    transactions.add(new Transaction(rs.getString("date"),rs.getString("time"),rs.getString("seller_code"),rs.getString("seller_name"),rs.getString("buyer_code"),rs.getString("buyer_name"),rs.getString("item"),rs.getInt("quantity"),rs.getInt("rate")));
            }
            else
            {
                rs=st.executeQuery("select * from `transactions` where `username`=\""+LoginPage.loggedInUser+"\" and `date`=\""+date+"\";");
                while(rs.next())
                    transactions.add(new Transaction(rs.getString("date"),rs.getString("time"),rs.getString("seller_code"),rs.getString("seller_name"),rs.getString("buyer_code"),rs.getString("buyer_name"),rs.getString("item"),rs.getInt("quantity"),rs.getInt("rate")));
            }
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        Vector<Transaction> toReturn=new Vector<Transaction>();
        for(int i=transactions.size()-1;i>=0;i--)
            toReturn.add(transactions.get(i));
        return toReturn;
    }
    public void deleteTransactions()
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            st.executeUpdate("delete from `transactions` where `username`=\""+LoginPage.loggedInUser+"\";");
            st=conn.createStatement();
            st.executeUpdate("update `buyerdata` set `quantity`=0 where `username`=\""+LoginPage.loggedInUser+"\";");
            st=conn.createStatement();
            st.executeUpdate("update `sellerdata` set `quantity`=0 where `username`=\""+LoginPage.loggedInUser+"\";");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public void deleteParticularTransaction(Transaction t)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            st.executeUpdate("delete from `transactions` where `username`=\""+LoginPage.loggedInUser+"\" and `date`=\""+t.date+"\" and `time`=\""+t.time+"\" and `seller_code`=\""+t.seller_code+"\" and `seller_name`=\""+t.seller_name+"\" and `buyer_code`=\""+t.buyer_code+"\" and `buyer_name`=\""+t.buyer_name+"\" and `item`=\""+t.item+"\" and `quantity`="+t.quantity+" and `rate`="+t.rate+";");
            st=conn.createStatement();
            st.executeUpdate("update `buyerdata` set `quantity`=`quantity`-"+t.quantity+" where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_code`=\""+t.buyer_code+"\" and `buyer_name`=\""+t.buyer_name+"\";");
            st=conn.createStatement();
            st.executeUpdate("update `sellerdata` set `quantity`=`quantity`-"+t.quantity+" where `username`=\""+LoginPage.loggedInUser+"\" and `seller_code`=\""+t.seller_code+"\" and `seller_name`=\""+t.seller_name+"\";");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public void updateTransaction(Transaction t,String date,String time,String item,int quantity,int rate)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            st.executeUpdate("update `transactions` set `date`=\""+date+"\",`time`=\""+time+"\",`item`=\""+item+"\",`quantity`="+quantity+",`rate`="+rate+" where `username`=\""+LoginPage.loggedInUser+"\" and `date`=\""+t.date+"\" and `time`=\""+t.time+"\" and `seller_code`=\""+t.seller_code+"\" and `seller_name`=\""+t.seller_name+"\" and `buyer_code`=\""+t.buyer_code+"\" and `buyer_name`=\""+t.buyer_name+"\" and `item`=\""+t.item+"\" and `quantity`="+t.quantity+" and `rate`="+t.rate+";");
            int change=quantity-t.quantity;
            st=conn.createStatement();
            st.executeUpdate("update `buyerdata` set `quantity`=`quantity`+"+change+" where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_code`=\""+t.buyer_code+"\" and `buyer_name`=\""+t.buyer_name+"\";");
            st=conn.createStatement();
            st.executeUpdate("update `sellerdata` set `quantity`=`quantity`+"+change+" where `username`=\""+LoginPage.loggedInUser+"\" and `seller_code`=\""+t.seller_code+"\" and `seller_name`=\""+t.seller_name+"\";");
        }
        catch(Exception e){
        e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public Vector<Transaction> getSellerWiseTransactions(String seller_code,String seller_name)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<Transaction> transactions=new Vector<Transaction>();
        String scode;
        try
        {
            if(seller_code.equals(""))
            {
                st=conn.createStatement();
                rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_name`=\""+seller_name+"\";");
                rs.next();
                scode=rs.getString("seller_code");
            }
            else
            {
                String text="%"+seller_code+"%";
                st=conn.createStatement();
                rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_name`=\""+seller_name+"\" and `seller_code` like \'"+text+"\';");
                rs.next();
                scode=rs.getString("seller_code");
            }
            st=conn.createStatement();
            rs=st.executeQuery("select * from `transactions` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_code`=\""+scode+"\" and `seller_name`=\""+seller_name+"\";");
            while(rs.next())
                transactions.add(new Transaction(rs.getString("date"),rs.getString("time"),rs.getString("seller_code"),rs.getString("seller_name"),rs.getString("buyer_code"),rs.getString("buyer_name"),rs.getString("item"),rs.getInt("quantity"),rs.getInt("rate")));
        }
        catch(Exception e){
        e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return transactions;
    }
    public Vector<Transaction> getBuyerWiseTransactions(String buyer_code,String buyer_name)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<Transaction> transactions=new Vector<Transaction>();
        String bcode;
        try
        {
            if(buyer_code.equals(""))
            {
                st=conn.createStatement();
                rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_name`=\""+buyer_name+"\";");
                rs.next();
                bcode=rs.getString("buyer_code");
            }
            else
            {
                String text="%"+buyer_code+"%";
                st=conn.createStatement();
                rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_name`=\""+buyer_name+"\" and `buyer_code` like \'"+text+"\';");
                rs.next();
                bcode=rs.getString("buyer_code");
            }
            st=conn.createStatement();
            rs=st.executeQuery("select * from `transactions` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_code`=\""+bcode+"\" and `buyer_name`=\""+buyer_name+"\";");
            while(rs.next())
                transactions.add(new Transaction(rs.getString("date"),rs.getString("time"),rs.getString("seller_code"),rs.getString("seller_name"),rs.getString("buyer_code"),rs.getString("buyer_name"),rs.getString("item"),rs.getInt("quantity"),rs.getInt("rate")));
        }
        catch(Exception e){
        e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return transactions;
    }
    public void billFormat(String left,String right,String broker_name,String broker_address,String bill_title,String bill_period)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `billformat` where `username`=\""+LoginPage.loggedInUser+"\";");
            if(rs.next())
            {
                st=conn.createStatement();
                st.executeUpdate("update `billformat` set `left`=\""+left+"\",`right`=\""+right+"\",`broker_name`=\""+broker_name+"\",`broker_address`=\""+broker_address+"\",`bill_title`=\""+bill_title+"\",`bill_period`=\""+bill_period+"\" where `username`=\""+LoginPage.loggedInUser+"\";");
            }
            else
            {
                st=conn.createStatement();
                st.executeUpdate("insert into `billformat` values(\""+LoginPage.loggedInUser+"\",\""+left+"\",\""+right+"\",\""+broker_name+"\",\""+broker_address+"\",\""+bill_title+"\",\""+bill_period+"\");");
            }
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public Bill getBillFormat()
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Bill b=null;
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `billformat` where `username`=\""+LoginPage.loggedInUser+"\";");
            if(rs.next())
                b=new Bill(rs.getString("left"),rs.getString("right"),rs.getString("broker_name"),rs.getString("broker_address"),rs.getString("bill_title"),rs.getString("bill_period"));
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return b;
    }
    public Vector<String> getSellerPlaceList()
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<String> sellerplacelist=new Vector<String>();
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\";");
            while(rs.next())
                sellerplacelist.add(rs.getString("place"));
        }
        catch(Exception e){}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return sellerplacelist;
    }
    public Vector<String> getBuyerPlaceList()
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<String> buyerplacelist=new Vector<String>();
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\";");
            while(rs.next())
                buyerplacelist.add(rs.getString("place"));
        }
        catch(Exception e){}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return buyerplacelist;
    }
    public String getSellerPlace(String seller_code,String seller_name)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        String place="";
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select place from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_code`=\""+seller_code+"\" and `seller_name`=\""+seller_name+"\"");
            place=rs.getString("place");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return place;
    }
    public String getBuyerPlace(String buyer_code,String buyer_name)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        String place="";
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select place from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_code`=\""+buyer_code+"\" and `buyer_name`=\""+buyer_name+"\"");
            place=rs.getString("place");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return place;
    }
    public void addItems(Vector<Item> itemlist)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            for(int i=0;i<itemlist.size();i++)
            {
                st=conn.createStatement();
                rs=st.executeQuery("select * from `itemdata` where `username`=\""+LoginPage.loggedInUser+"\" and `item_name`=\""+itemlist.get(i).name+"\";");
                if(rs.next())
                    continue;
                st=conn.createStatement();
                st.executeUpdate("insert into `itemdata` values(\""+LoginPage.loggedInUser+"\",\""+itemlist.get(i).code+"\",\""+itemlist.get(i).name+"\");");
            }
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public Vector<Item> getItems()
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<Item> itemlist=new Vector<Item>();
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `itemdata` where `username`=\""+LoginPage.loggedInUser+"\" order by `item_name` asc;");
            while(rs.next())
                itemlist.add(new Item(rs.getString("item_code"),rs.getString("item_name")));
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return itemlist;
    }
    public void deleteItem(Item item)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            st.executeUpdate("delete from `itemdata` where `username`=\""+LoginPage.loggedInUser+"\" and `item_code`=\""+item.code+"\" and `item_name`=\""+item.name+"\";");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public void updateItem(String oldcode,String oldname,String newcode,String newname)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            st.executeUpdate("update `itemdata` set `item_code`=\""+newcode+"\",`item_name`=\""+newname+"\" where `username`=\""+LoginPage.loggedInUser+"\" and `item_code`=\""+oldcode+"\" and `item_name`=\""+oldname+"\";");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public Vector<Item> getSpecificItemList(String text)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<Item> itemlist=new Vector<Item>();
        try
        {
            text="%"+text+"%";
            st=conn.createStatement();
            rs=st.executeQuery("select * from `itemdata` where `username`=\""+LoginPage.loggedInUser+"\" and `item_code` like \'"+text+"\' order by `item_name` asc;");
            while(rs.next())
                itemlist.add(new Item(rs.getString("item_code"),rs.getString("item_name")));
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return itemlist;
    }
    public int checkSellerDatabase(Vector<Seller> sellerlist)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            for(int i=0;i<sellerlist.size();i++)
            {
                st=conn.createStatement();
                rs=st.executeQuery("select * from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_name`=\""+sellerlist.get(i).name+"\";");
                if(rs.next())
                {
                    try{conn.close();}catch(Exception e){e.printStackTrace();}
                    return i;
                }
            }
        }
        catch(Exception e){}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return -1;
    }
    public int checkBuyerDatabase(Vector<Buyer> buyerlist)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            for(int i=0;i<buyerlist.size();i++)
            {
                st=conn.createStatement();
                rs=st.executeQuery("select * from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_name`=\""+buyerlist.get(i).name+"\";");
                if(rs.next())
                {
                    try{conn.close();}catch(Exception e){e.printStackTrace();}
                    return i;
                }
            }
        }
        catch(Exception e){}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return -1;
    }
    public int checkItems(Vector<Item> itemlist)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            for(int i=0;i<itemlist.size();i++)
            {
                st=conn.createStatement();
                rs=st.executeQuery("select * from `itemdata` where `username`=\""+LoginPage.loggedInUser+"\" and `item_name`=\""+itemlist.get(i).name+"\";");
                if(rs.next())
                {
                    try{conn.close();}catch(Exception e){e.printStackTrace();}
                    return i;
                }
            }
        }
        catch(Exception e){}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return -1;
    }
    public void deleteCustomers(String s)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            if(s.equals("All"))
            {
                st=conn.createStatement();
                st.executeUpdate("delete from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\";");
                st=conn.createStatement();
                st.executeUpdate("delete from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\";");
            }
            else if(s.equals("Sellers"))
            {
                st=conn.createStatement();
                st.executeUpdate("delete from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\";");
            }
            else
            {
                st=conn.createStatement();
                st.executeUpdate("delete from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\";");
            }
        }
        catch(Exception e){}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public void deleteItems()
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            st.executeUpdate("delete from `itemdata` where `username`=\""+LoginPage.loggedInUser+"\";");
        }
        catch(Exception e){}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
    }
    public int getTotalQuantity(String date)
    {
        int x=-1;
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select sum(`quantity`) as `quansum` from `transactions` where `username`=\""+LoginPage.loggedInUser+"\" and `date`=\""+date+"\";");
            if(rs.next())
                x=rs.getInt("quansum");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return x;
    }
    public int getSellerQuantity(String seller_name)
    {
        int x=-1;
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select `quantity` from `sellerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_name`=\""+seller_name+"\";");
            if(rs.next())
                x=rs.getInt("quantity");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return x;
    }
    public int getBuyerQuantity(String buyer_name)
    {
        int x=-1;
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select `quantity` from `buyerdata` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_name`=\""+buyer_name+"\";");
            if(rs.next())
                x=rs.getInt("quantity");
        }
        catch(Exception e){e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return x;
    }
    
    public Vector<Transaction> getBuyerTransactions(String buyer_code,String buyer_name)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<Transaction> transactions=new Vector<Transaction>();
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `transactions` where `username`=\""+LoginPage.loggedInUser+"\" and `buyer_code`=\""+buyer_code+"\" and `buyer_name`=\""+buyer_name+"\";");
            while(rs.next())
                transactions.add(new Transaction(rs.getString("date"),rs.getString("time"),rs.getString("seller_code"),rs.getString("seller_name"),rs.getString("buyer_code"),rs.getString("buyer_name"),rs.getString("item"),rs.getInt("quantity"),rs.getInt("rate")));
        }
        catch(Exception e){
        e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return transactions;
    }
    
    public Vector<Transaction> getSellerTransactions(String seller_code,String seller_name)
    {
        try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
        Vector<Transaction> transactions=new Vector<Transaction>();
        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("select * from `transactions` where `username`=\""+LoginPage.loggedInUser+"\" and `seller_code`=\""+seller_code+"\" and `seller_name`=\""+seller_name+"\";");
            while(rs.next())
                transactions.add(new Transaction(rs.getString("date"),rs.getString("time"),rs.getString("seller_code"),rs.getString("seller_name"),rs.getString("buyer_code"),rs.getString("buyer_name"),rs.getString("item"),rs.getInt("quantity"),rs.getInt("rate")));
        }
        catch(Exception e){
        e.printStackTrace();}
        try{conn.close();}catch(Exception e){e.printStackTrace();}
        return transactions;
    }
    
    public void filterDatabase() {
        try
        {
            try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
            st=conn.createStatement();
            rs=st.executeQuery("select * from `buyerdata`;");
            Vector<Buyer> blist=new Vector<>();
            while(rs.next()) {
                blist.add(new Buyer(rs.getString("buyer_code"),rs.getString("buyer_name"),rs.getInt("quantity")));
            }
            try{conn.close();}catch(Exception e){e.printStackTrace();}
            for(int j=0;j<blist.size();j++) {
                Vector<Transaction> buyertrans=getBuyerTransactions(blist.get(j).code,blist.get(j).name);
                int total=0;
                for(int i=0;i<buyertrans.size();i++) {
                    total+=buyertrans.get(i).quantity;
                }
                if(blist.get(j).quantity!=total) {
                    try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
                    st=conn.createStatement();
                    st.executeUpdate("update `buyerdata` set `quantity`="+total+" where `buyer_code`=\""+blist.get(j).code+"\" and `buyer_name`=\""+blist.get(j).name+"\";");
                    try{conn.close();}catch(Exception e){e.printStackTrace();}
                }
            }
            
            try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
            st=conn.createStatement();
            rs=st.executeQuery("select * from `sellerdata`;");
            Vector<Seller> slist=new Vector<>();
            while(rs.next()) {
                slist.add(new Seller(rs.getString("seller_code"),rs.getString("seller_name"),rs.getInt("quantity")));
            }
            try{conn.close();}catch(Exception e){e.printStackTrace();}
            for(int j=0;j<slist.size();j++) {
                Vector<Transaction> sellertrans=getSellerTransactions(slist.get(j).code,slist.get(j).name);
                int total=0;
                for(int i=0;i<sellertrans.size();i++) {
                    total+=sellertrans.get(i).quantity;
                }
                if(slist.get(j).quantity!=total) {
                    try{conn=DriverManager.getConnection("jdbc:sqlite:brokerdatabase.db");}catch(Exception e){e.printStackTrace();}
                    st=conn.createStatement();
                    st.executeUpdate("update `sellerdata` set `quantity`="+total+" where `seller_code`=\""+slist.get(j).code+"\" and `seller_name`=\""+slist.get(j).name+"\";");
                    try{conn.close();}catch(Exception e){e.printStackTrace();}
                }
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
}
