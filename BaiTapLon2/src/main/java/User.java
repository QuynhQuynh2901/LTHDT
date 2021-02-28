
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class User {
    private String username;
    private String password;
    private String fullName;
    private String country;
    private String gender;
    private Date dob;
    private Date accessDate;
    private List<Double> score = new ArrayList<>();
    private int countTest = 0;

    public User() {
    }

    /**
     * phuong thuc khoi tao co tham so dung de gan gia tri
     * @param username
     * @param password
     * @param fullName
     * @param country
     * @param gender
     * @param dob
     * @param accessDate 
     */
    public User(String username, String password, String fullName, String country, String gender, Date dob, Date accessDate) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.country = country;
        this.gender = gender;
        this.dob = dob;
        this.accessDate = accessDate;
    }
    
    /**
     * phuong thuc khoi tạo dung cho dang nhap
     * @param un
     * @param pw
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     * @throws Exception 
     */
    public User(String un, String pw) throws ClassNotFoundException, SQLException, ParseException, Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/englishdb", "root", "30092018tu");
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM user");
        while(rs.next()){
            if(un.toUpperCase().equals(rs.getString("username").toUpperCase())
               && pw.toUpperCase().equals(rs.getString("password").toUpperCase())){
                
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String dob = rs.getString("dob");
                String dos = rs.getString("dos");
                this.username = rs.getString("username");
                this.password = rs.getString("password");
                this.fullName = rs.getString("fullname");
                this.country = rs.getString("country");
                this.gender = rs.getString("gender");
                this.dob = f.parse(dob);
                this.accessDate = f.parse(dos);
                break;
            }
        }
        if(this.username == null)
            throw new Exception("Incorrect username or password.");
        
        stm.close();
        conn.close();
        
    }
    
    /**
     * Phuong thuc dang ky, cho nguoi dung nhap thong tin sau do kiem tra su trung lap duoi CSDL 
     * dùng phuong thuc tinh de khong can tạo moi doi tuong van dung dc
     * @param scanner
     * @return 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws Exception 
     */
    public static User register(Scanner scanner) throws ClassNotFoundException, SQLException, Exception{
        System.out.println("Full name: ");
        String fn = scanner.nextLine();
        System.out.println("Username: "); //Tai len tu csdl xem co trung hay khong?
        String us = scanner.nextLine();
        System.out.println("Password: ");
        String pw = scanner.nextLine();
        System.out.println("Gender: ");
        String g = scanner.nextLine();
        System.out.println("Date of birth(dd-mm-yyyy): ");
        String dob = scanner.nextLine();
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Country: ");
        String c = scanner.nextLine();
        System.out.println();
        
        Connection conn = Connectionn.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM user");
        
        while(rs.next()){
            if(us.toLowerCase().equals(rs.getString("username").toLowerCase()))
                throw new Exception("Username " + us + " is not available.");      
        }
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "INSERT INTO user(fullname, username,"
                    + " password, gender, dob, country, dos)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, fn);
        pre.setString(2, us);
        pre.setString(3, pw);
        pre.setString(4, g);
        pre.setString(5, f1.format(f.parse(dob)));
        pre.setString(6, c);
        pre.setString(7, f1.format(new Date()));
        pre.executeUpdate();

        pre.close();
        rs.close();
        
        conn.close();
        stm.close();
        return new User(us, pw, fn, c, g, f.parse(dob), new Date());
    }
    
    @Override
    public String toString() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("\nFull name: %s\nUsername: %s\n"
                + "Gender: %s\nDate of birth: %s\nCountry: %s\nAccess date: %s\n", this.getFullName(), this.getUsername(), this.getGender(), f.format(this.getDob()), this.getCountry(), f.format(this.getAccessDate()));
    }
    
    /**
     * phuong thuc getNumberOfTests dung de lay so lan choi cua user thong qua username
     * 
     */
    public int getNumberOfTests(Connection conn) throws SQLException, ClassNotFoundException{
        int kq = 0;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("select max(count_test) as max from practice where practice_username = '" + this.getUsername() + "'");
        while(rs.next()){
            kq = rs.getInt("max");
        }
        rs.close();
        
        stm.close();
        conn.close();
        return kq;
    }
    
    /**phuong thuc lay diem cua moi lan ra bang cach lay so lân choi cao nhât tu csdl
     * 
     * @param conn
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws ParseException 
     */
    public void pointsForEachTest(Connection conn) throws SQLException, ClassNotFoundException, ParseException{
        String sql = "select practice.*, user.username from practice inner join user on user.username = practice.practice_username where count_test = ? and practice_username = ?";
        int numOfTest = this.getNumberOfTests(Connectionn.getConnection());
        for(int i = 1; i <= numOfTest; i++){
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, i);
            pre.setString(2, this.getUsername());
            ResultSet rs = pre.executeQuery();
            int dem = 0;
            int scoreTrue = 0;
            String date = null;
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat f1 = new SimpleDateFormat("dd-MM-yyyy");
            while(rs.next()){
                dem++;
                if(rs.getDouble("score") == 1)
                    scoreTrue++;
                date = rs.getString("practice_date");
            }
            double score = 10.0 / dem * (double)scoreTrue;
            System.out.printf("Test %d - %.2f - %s\n", i, score, f1.format(f.parse(date)));
            rs.close();
            pre.close();
        }
        conn.close();
    }
    
     public void scoreByMonth(Connection conn, int m) throws SQLException, ParseException{
     
        String sql1 = "select p.* "
                + "from practice p inner join user u on u.username = p.practice_username "
                + "where month(practice_date) = ? and username = ?";
        PreparedStatement pre1 = conn.prepareStatement(sql1);
        pre1.setInt(1, m);
        pre1.setString(2, this.getUsername());
        
        ResultSet rs1 = pre1.executeQuery();
        int count = 0;
        double s = 0;
        String date = null;
        
        System.out.printf("Count test -  Score - Practice date\n");
        while(rs1.next()){
            date = rs1.getString("practice_date");
            if(rs1.getInt("score") == 1)
                s += 10;
            
            System.out.printf("Test %d - %.2f - %s\n", rs1.getInt("count_test")
                    , rs1.getDouble("score") * 10, date);
            count++;
        }
        rs1.close();
        
        System.out.printf("AVG of month %d: %.2f\n\n", m, s / count);
        
        conn.close();
         
     }
    
     /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the accessDate
     */
    public Date getAccessDate() {
        return accessDate;
    }

    /**
     * @param accessDate the accessDate to set
     */
    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    /**
     * @return the score
     */
    public List<Double> getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(List<Double> score) {
        this.score = score;
    }

    /**
     * @return the countTest
     */
    public int getCountTest() {
        return countTest;
    }

    /**
     * @param aCountTest the countTest to set
     */
    public void setCountTest(int aCountTest) {
        countTest = aCountTest;
    }
    
}
