
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.util.List;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
class Parent {
        protected static int x;
        public Parent(){
            x = 0;
        }
        public void show(){
            System.out.println(x);
        }
        protected void inc1(){
            x++;
        }
        public void inc2(){
            this.inc1();
            x +=2;
        }
        
    }
    class Child extends Parent{
        public void inc1(){
            x += 3;
        }
        public void inc2(){
            super.inc2();
            x += 4;
        }
    }
/**
 *
 * @author Admin
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
        
         Parent o1 = new Parent();
        o1.inc2();
        o1.show();
        Child o2 = new Child();
        o2.inc1();
        o2.show();
        Parent o3 = new Child();
        o3.inc2();
        o3.show();
        
        
//        Scanner scanner = new Scanner(System.in);
//        boolean loop = true; // all program
//        boolean loop1 = true; // login
//        boolean loop2 = true; //practice
//        boolean loop3 = true; // login/user
//        while(loop){
//            System.out.println("\n1. Login");
//            System.out.println("2. Register");
//            System.out.println("3. Exit");
//            System.out.println("Your chocie: ");
//            String choice = scanner.nextLine();
//            switch(choice){
//                case "1":
//                    loop1 = true;
//                    while(loop1){
//                        System.out.println("\nusername: ");
//                        String user = scanner.nextLine();
//                        System.out.println("password: ");
//                        String pass = scanner.nextLine();
//                        try{
//                            User u1 = new User(user, pass);
//                            UserManagement us = new UserManagement();
//                            QuestionManagement qs = new QuestionManagement();
//                            System.out.println("\nHi, " + u1.getFullName());
//                            System.out.println("What do you want to do?");
//                            do{
//                                System.out.println("1. See the user list");
//                                System.out.println("2. Look up the user");
//                                System.out.println("3. Delete account");
//                                System.out.println("4. Update information of user");
//                                System.out.println("5. See the question list");
//                                System.out.println("6. Look up the questions");
//                                System.out.println("7. Practice");
//                                System.out.println("8. Score statistics by month");
//                                System.out.println("9. Logout");
//                                System.out.println("Your choice: ");
//                                String c = scanner.nextLine();
//                                switch(c){
//                                    case "1":
//                                        us.viewList(Connectionn.getConnection());
//                                        loop3 = true;
//                                        break;
//                                    case "2":
//                                        System.out.println("1. Look up by fullname");
//                                        System.out.println("2. Look up by gender");
//                                        System.out.println("3. Look up by date of birth");
//                                        System.out.println("4. Look up by country");
//                                        System.out.println("Your choice: ");
//                                        String ce = scanner.nextLine();
//                                        switch(ce){
//                                            case "1":
//                                                System.out.println("Enter the fullname you want to look up: ");
//                                                String fn = scanner.nextLine();
//                                                us.lookUpByFullName(fn, Connectionn.getConnection()).forEach(p -> {
//                                                    System.out.println(p);
//                                                });
//                                                break;
//                                            case "2":
//                                                System.out.println("Enter the gender you want to look up: ");
//                                                String g = scanner.nextLine();
//                                                us.lookUpByGender(g, Connectionn.getConnection()).forEach(p -> {
//                                                    System.out.println(p);
//                                                });
//                                                break;
//                                            case "3":
//                                                System.out.println("Enter the dob you want to look up: ");
//                                                String dob = scanner.nextLine();
//                                                us.lookUpByDOB(dob, Connectionn.getConnection()).forEach(p -> {
//                                                    System.out.println(p);
//                                                });
//                                                break;
//                                            case "4":
//                                                System.out.println("Enter the country you want to look up: ");
//                                                String country = scanner.nextLine();                                                        
//                                                us.lookUpByCountry(country, Connectionn.getConnection()).forEach(p -> {
//                                                    System.out.println(p);
//                                                });
//                                                break;
//                                        }
//                                        loop3 = true;
//                                        break;
//                                    case "3":
//                                        us.removeUser(u1, Connectionn.getConnection());
//                                        us.removeUser(u1);
//                                        u1 = null;
//                                        loop3 = false;
//                                        break;
//                                    case "4":
//                                        us.updateUser(u1, scanner, Connectionn.getConnection());
//                                        break;
//                                    case "5":
//                                        qs.viewListQ(Connectionn.getConnection());
//                                        loop3 = true;
//                                        break;
//                                    case "6":
//                                        System.out.println("1. Look up by content");
//                                        System.out.println("2. Look up by category");
//                                        System.out.println("3. Look up by level");
//                                        System.out.println("Your choice: ");
//                                        String choice6 = scanner.nextLine();
//                                        switch(choice6){
//                                            case "1":
//                                                System.out.println("Enter the content you want to look up: ");
//                                                String ct = scanner.nextLine();
//                                                qs.lookUpByContent(ct, Connectionn.getConnection()).forEach(q -> System.out.print(q));
//                                                break;
//                                            case "2":
//                                                System.out.println("Enter the category you want to look up:"
//                                                        + "\n\tMultipleChoice"
//                                                        + "\n\tConversation"
//                                                        + "\n\tInComplete"
//                                                        + "\nPlease choose a category:");
//                                                String cate = scanner.nextLine();
//                                                qs.lookUpByCate(cate, Connectionn.getConnection()).forEach(q -> System.out.print(q));
//                                                break;
//                                            case "3":
//                                                System.out.println("Enter the level you want to look up:"
//                                                        + "\n\tEasy"
//                                                        + "\n\tMedium"
//                                                        + "\n\tDifficult"
//                                                        + "\nPlease choose a level:");
//                                                String lv = scanner.nextLine();
//                                                qs.lookUpByLevel(lv, Connectionn.getConnection()).forEach(q -> System.out.print(q));
//                                                break;
//                                        }
//                                        loop3 = true;
//                                        break;
//                                    case "7":
//                                        do{
//                                            System.out.println("\n1. Practice MultipleChoice Question");
//                                            System.out.println("2. Practice InComplete Question");
//                                            System.out.println("3. Practice Conversation Question");
//
//                                            System.out.println("Your choice: ");
//                                            String ch = scanner.nextLine();
//                                            switch(ch){
//                                                case "1":
//                                                    System.out.println("Number of question you want to practice: ");
//                                                    String n = scanner.nextLine();
//                                                    qs.practiceMultipleC(scanner, Integer.parseInt(n), u1, Connectionn.getConnection());
//                                                    break;
//                                                case "2":
//                                                    System.out.println("Level you want to practice: ");
//                                                    String lv = scanner.nextLine();                
//                                                    qs.practiceInComplete(scanner, lv, u1, Connectionn.getConnection());
//                                                    break;
//                                                case "3":
//                                                    System.out.println("Level you want to practice: ");
//                                                    String lv1 = scanner.nextLine();
//                                                    qs.practiceConversation(scanner, lv1, u1, Connectionn.getConnection());
//                                                    break;
//                                            }
//                                            System.out.println("Do you want to countinue practicing? (Y/N): ");
//                                            String kw = scanner.nextLine().toUpperCase();
//                                            switch(kw){
//                                                case "Y":
//                                                    loop2 = true;
//                                                    break;
//                                                case "N":
//                                                    loop2 = false;
//                                                    loop3 = true;
//                                                    break;
//                                            }
//                                        }while(loop2);
//                                        break;
//                                    case "8":
//                                        System.out.println("1. Number of tests and points for each test");
//                                        System.out.println("2. Average score of the month");
//                                        System.out.println("Your choice: ");
//                                        String coi = scanner.nextLine();
//                                        switch(coi){
//                                            case "1":
//                                                int n = u1.getNumberOfTests(Connectionn.getConnection());
//                                                if(n > 0){
//                                                    System.out.println();
//                                                    u1.pointsForEachTest(Connectionn.getConnection());
//                                                    System.out.println();
//                                                }
//                                                break;
//                                            case "2":
//                                                System.out.println("\nEnter the month you want to get average score: ");
//                                                int m = scanner.nextInt();
//                                                scanner.nextLine();
//                                                u1.scoreByMonth(Connectionn.getConnection(), m);
//                                                break;
//                                        }
//                                        loop3 = true;
//                                        break;
//                                    case "9":
//                                        loop3 = false;
//                                        break;
//                                }
//
//                            }while(loop3);
//                            loop1 = false;
//                        }
//                        catch(Exception e){
//                            System.err.println(e.getMessage());
//                        }
//                    }
//                    break;
//                case "2":
//                    boolean l = true;
//                    do{
//                        try {
//                            User u2 = User.register(scanner);
//                            l = false;
//                        } catch (Exception e) {
//                            System.err.println(e.getMessage());
//                            l = true;
//                        }
//
//
//
//                    }while(l);
//                    break;
//                case "3":
//                    loop = false;
//                    break;
//            }
//        }
    }
}
