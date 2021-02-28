
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class test {
    public static void main(String[] args) throws SQLException{
        MySQl.getConnection();
        Connection con = MySQl.getConnection();
        
        try{
            Statement stm = MySQl.getConnection().createStatement();
            String sql = "SELECT * FROM CATEGORY";
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            System.out.print("enter paragrath: "+ rs.getInt("2") + rs.getString("2"));
            
        } catch(SQLException e){
        
        }
        
        
        
        
    }
}
