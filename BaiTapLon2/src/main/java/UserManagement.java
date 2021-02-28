
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class UserManagement {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public void addUser(User u){
        this.getUsers().add(u);
    }
    
    public void removeUser(User u){
        this.getUsers().remove(u);
    }    
    public void removeUser(User u, Connection conn) throws SQLException{
        Statement stm = conn.createStatement();
        Statement stm1 = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM user WHERE username = '" + u.getUsername() + "'");
        while(rs.next()){
            stm1.executeUpdate("DELETE FROM practice WHERE practice_username = '" + rs.getString("username") + "'");
            stm1.executeUpdate("DELETE FROM user WHERE username = '" + u.getUsername() + "'");
        }
        rs.close();
        
        stm.close();
        stm1.close();
        conn.close();
    }
    
    public void updateUser(User u, Scanner scanner, Connection conn) throws ParseException, SQLException{
        Statement stm = conn.createStatement();
        
        System.out.println("Please enter infomation need to be updated!!");
        System.out.println("1.Full name");
        System.out.println("2.Password");
        System.out.println("3.Gender");
        System.out.println("4.Country");
        System.out.println("Your choice:");
        String c = scanner.nextLine();
        
        switch(c){
            case "1":
                System.out.println("Enter the fullname you want to change");
                String fn = scanner.nextLine();
                String query = "update user set fullname = ? where username = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, fn);
                preparedStmt.setString(2, u.getUsername());
                // execute the java preparedstatement
                preparedStmt.executeUpdate();
                System.out.println("successfully!");
                u.setFullName(fn);
                
            case "2":
                System.out.println("Enter the password you want to change");
                String ps = scanner.nextLine();
                String query1 = "update user set password = ? where username = ?";
                PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
                preparedStmt1.setString(1, ps);
                preparedStmt1.setString(2, u.getUsername());
                // execute the java preparedstatement
                preparedStmt1.executeUpdate();
                System.out.println("successfully!");
                u.setPassword(ps);
            case "3":
                System.out.println("Enter the gender you want to change");
                String gd = scanner.nextLine();
                String query2 = "update user set gender = ? where username = ?";
                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setString(1, gd);
                preparedStmt2.setString(2, u.getUsername());
                // execute the java preparedstatement
                preparedStmt2.executeUpdate();
                System.out.println("successfully!");
                u.setGender(gd);
            case "4":
                System.out.println("Enter the country you want to change");
                String cn = scanner.nextLine();
                String query3 = "update user set country = ? where username = ?";
                PreparedStatement preparedStmt3 = conn.prepareStatement(query3);
                preparedStmt3.setString(1, cn);
                preparedStmt3.setString(2, u.getUsername());
                // execute the java preparedstatement
                preparedStmt3.executeUpdate();
                System.out.println("successfully!");
                u.setCountry(cn);
                System.out.println(u);
  
        }
        
        conn.close();
    }
    
    public void viewList(){
        for(User u : this.getUsers()){
            System.out.println(u);
        }
    }
    public void viewList(Connection conn) throws SQLException, ParseException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM user");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        this.users = new ArrayList<>();
        while(rs.next()){
            User u = new User();
            u.setFullName(rs.getString("fullname"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setGender(rs.getString("gender"));
            u.setDob(f.parse(rs.getString("dob")));
            u.setCountry(rs.getString("country"));
            u.setAccessDate(f.parse(rs.getString("dos")));
            this.users.add(u);
        }
        this.viewList();
        rs.close();
        stm.close();
        conn.close();
    }
    
    public List<User> lookUpByFullName(String fn){
        List<User> r = new ArrayList<>();
        for(User u : this.getUsers()){
            if(u.getFullName().toUpperCase().equals(fn.toUpperCase()))
                r.add(u);
        }
        return r;
    }    
    public List<User> lookUpByFullName(String fn, Connection conn) throws SQLException, ParseException{
        List<User> r = new ArrayList<>();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM user");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        this.users = new ArrayList<>();
        while(rs.next()){
            User u = new User();
            u.setFullName(rs.getString("fullname"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setGender(rs.getString("gender"));
            u.setDob(f.parse(rs.getString("dob")));
            u.setCountry(rs.getString("country"));
            u.setAccessDate(f.parse(rs.getString("dos")));
            this.users.add(u);
        }
        r = this.lookUpByFullName(fn);
        rs.close();
        stm.close();
        conn.close();
        return r;
    }
    
    public List<User> lookUpByGender(String g){
        List<User> r = new ArrayList<>();
        for(User u : this.getUsers()){
            if(u.getGender().toUpperCase().equals(g.toUpperCase()))
                r.add(u);
        }
        return r;
    }    
    public List<User> lookUpByGender(String g, Connection conn) throws SQLException, ParseException{
        List<User> r = new ArrayList<>();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM user");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        this.users = new ArrayList<>();
        while(rs.next()){
            User u = new User();
            u.setFullName(rs.getString("fullname"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setGender(rs.getString("gender"));
            u.setDob(f.parse(rs.getString("dob")));
            u.setCountry(rs.getString("country"));
            u.setAccessDate(f.parse(rs.getString("dos")));
            this.users.add(u);
        }
        r = this.lookUpByGender(g);
        rs.close();
        stm.close();
        conn.close();
        return r;
    }
    
    public List<User> lookUpByDOB(String dob) throws ParseException{
        List<User> r = new ArrayList<>();
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        for(User u : this.getUsers()){
            if(u.getDob().equals(f.parse(dob)))
                r.add(u);
        }
        return r;
    }    
    public List<User> lookUpByDOB(String dob, Connection conn) throws SQLException, ParseException{
        List<User> r = new ArrayList<>();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM user");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        this.users = new ArrayList<>();
        while(rs.next()){
            User u = new User();
            u.setFullName(rs.getString("fullname"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setGender(rs.getString("gender"));
            u.setDob(f.parse(rs.getString("dob")));
            u.setCountry(rs.getString("country"));
            u.setAccessDate(f.parse(rs.getString("dos")));
            this.users.add(u);
        }
        r = this.lookUpByDOB(dob);
        rs.close();
        stm.close();
        conn.close();
        return r;
    }
    
    public List<User> lookUpByCountry(String c){
        List<User> r = new ArrayList<>();
        for(User u : this.getUsers()){
            if(u.getCountry().toUpperCase().equals(c.toUpperCase()))
                r.add(u);
        }
        return r;
    }    
    public List<User> lookUpByCountry(String c, Connection conn) throws SQLException, ParseException{
        List<User> r = new ArrayList<>();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM user");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        this.users = new ArrayList<>();
        while(rs.next()){
            User u = new User();
            u.setFullName(rs.getString("fullname"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setGender(rs.getString("gender"));
            u.setDob(f.parse(rs.getString("dob")));
            u.setCountry(rs.getString("country"));
            u.setAccessDate(f.parse(rs.getString("dos")));
            this.users.add(u);
        }
        r = this.lookUpByCountry(c);
        rs.close();
        stm.close();
        conn.close();
        return r;
    }
}
