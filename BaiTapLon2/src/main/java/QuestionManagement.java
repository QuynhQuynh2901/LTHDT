
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
public class QuestionManagement {
    private List<Question> questions = new ArrayList<>();

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public List<Question> getQuestions() {
        return questions;
    }
    
    public void addQuestion(Question q){
        this.getQuestions().add(q);
    }
    
    public List<Question> getMultipleChoiceQ(){
        List<Question> k = new ArrayList<>();
        for(Question q : questions){
            if(q instanceof MultipleChoice)
                k.add(q);
        }
        return k;
    }
    public List<Question> getInCompleteQ(){
        List<Question> k = new ArrayList<>();
        for(Question q : questions){
            if(q instanceof InComplete)
                k.add(q);
        }
        return k;
    }
    public List<Question> getConversationQ(){
        List<Question> k = new ArrayList<>();
        for(Question q : questions){
            if(q instanceof Conversation)
                k.add(q);
        }
        return k;
    }
    
    
    public void practiceMultipleC(Scanner scanner, int n, User u) throws ClassNotFoundException, SQLException{
        Collections.shuffle(this.getMultipleChoiceQ());
        String[] notes = new String[100];
        String[] kws = new String[100];
        
        List<Double> score = new ArrayList<>();
            
        for(int i = 0; i < n; i++){
            
            System.out.println(this.getMultipleChoiceQ().get(i));
            System.out.println("Answer: ");
            kws[i] = scanner.nextLine();
            
            MultipleChoice c = (MultipleChoice) this.getMultipleChoiceQ().get(i);
            for(int j = 0; j < c.getChoices().size(); j++){
                if(c.getChoices().get(j).isCorrect()== 1)
                    notes[i] =  String.format("The correct answer is: %s\nNote: %s\n", c.getLABELS()[j], c.getChoices().get(j).getNote());
            }
            
            if(this.getMultipleChoiceQ().get(i).checkAnswer(kws[i])){
                score.add(1.0);
            }
            else{
                score.add(0.0);
            }
            
        }
        //Neu so luong lam bai kiem tra = 0 thi tang len 1, nguoc lai tiep tuc tang tiep so luong lam bai kiem tra 1 don vi
        if(u.getNumberOfTests(Connectionn.getConnection()) != 0)
            u.setCountTest(u.getNumberOfTests(Connectionn.getConnection()) + 1);
        else
            u.setCountTest(1);
        u.setScore(score);
        
        System.out.println("\n========== RESULT ==========");
        for(int i = 0; i < n ;i++){
            System.out.println(this.getMultipleChoiceQ().get(i));
            System.out.println("The answer of user: " + kws[i].toUpperCase());
            System.out.println(notes[i]);
        }
    }
    public void practiceMultipleC(Scanner scanner, int n, User u, Connection conn) throws ClassNotFoundException, SQLException, ParseException{
        Statement stm = conn.createStatement();
        Statement stm1 = conn.createStatement();
        
        ResultSet rs = stm.executeQuery("SELECT q.id_question, q.content as question,"
                    + " c.content as category, l.content as level"
                    + " FROM multiplechoice m"
                    + " INNER JOIN question q"
                    + " INNER JOIN level l ON q.id_level = l.id_level"
                    + " INNER JOIN category c ON q.id_category = c.id_category"
                    + " WHERE q.id_question = m.id_Qmultiple"
                    + " AND m.id_incomplete IS NULL AND m.id_conversation IS NULL"
                    + " AND q.id_question NOT IN(SELECT id_question FROM practice"
                    + " INNER JOIN user ON user.username = practice.practice_username WHERE username = '" + u.getUsername() + "')" 
                    + " ORDER BY RAND() LIMIT " + n);
        
        QuestionManagement questions = new QuestionManagement();
        int multipleChoiceId = 0;
        int[] questionId = new int[n]; // Mang luu tru cac id cau hoi
        int j = 0;
        while(rs.next()){ // Duyet tung cau hoi lay tu csdl
            Level level = new Level(rs.getString("level"));
            Category category = new Category(rs.getString("category"));
            Question q = new MultipleChoice(rs.getString("question"), level, category);
            multipleChoiceId = rs.getInt("id_question");
            questionId[j] = rs.getInt("id_question");
            j++;
            ResultSet rs1 = stm1.executeQuery("SELECT * FROM choice"
                        + " WHERE choice.id_multi = " + multipleChoiceId);
            while(rs1.next()){ // Duyet de lay cac cau tra loi add vao cau hoi
                Choice ch = new Choice(rs1.getString("content"), 
                            rs1.getInt("iscorrect"), rs1.getString("note"));
                q.addChoice(ch);
            }
            questions.addQuestion(q);
            rs1.close();
        }
        rs.close();
        //thực hiện chơi
        questions.practiceMultipleC(scanner, n, u);
                     
        String query = "INSERT INTO practice(practice_username, count_test, score, practice_date, id_question)"
                + "VALUES(?, ?, ?, ?, ?)"; // Chen vao bang du lieu nhung cau hoi nguoi dung da luyen tap
        PreparedStatement stm3 = conn.prepareStatement(query);
        for(int i = 0; i < questionId.length; i++){
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String date = f.format(new Date());
            java.sql.Date sqlDate = new java.sql.Date(f.parse(date).getTime()); 
            stm3.setString(1, u.getUsername());
            stm3.setInt(2, u.getCountTest());
            stm3.setDouble(3, u.getScore().get(i));
            stm3.setDate(4, sqlDate);
            stm3.setInt(5, questionId[i]);
            stm3.execute(); 
        }
        
        stm.close();
        stm1.close();
        conn.close();
    }
    
    
    public void practiceConversation(Scanner scanner, Level lv, User u, List<Integer> qId) throws ClassNotFoundException, SQLException{
        Statement stm = Connectionn.getConnection().createStatement();
        Collections.shuffle(this.getConversationQ());
        String[] notes = new String[100];
        String[] kws = new String[100];
        List<Question> questionsByLevel = new ArrayList<>();
        List<Double> score = new ArrayList<>();
        
        for(Question q : this.getConversationQ()){
            if(q.getLevel().getContent().toLowerCase().equals(lv.getContent().toLowerCase()))
                questionsByLevel.add(q); 
        }
        
        ResultSet rs = stm.executeQuery("SELECT c_general "
                + "FROM conversation "
                + "WHERE id_question = " + qId.get(0)); 

        for(int i = 0; i < 1; i++){
            while(rs.next())
                System.out.println(rs.getString("c_general"));
            Conversation cvs = (Conversation) questionsByLevel.get(i);
            int j = 0;
            for(MultipleChoice c : cvs.getQuestions()){      
                System.out.println("(" + (j + 1) + "): " + c + "Answer: ");
                kws[j] = scanner.nextLine();
                for(int k = 0; k < c.getChoices().size(); k++){
                    if(c.getChoices().get(k).isCorrect() == 1)
                        notes[j] =  String.format("The correct answer is: %s\nNote: %s\n", c.getLABELS()[k], c.getChoices().get(k).getNote());
                }
                if(c.checkAnswer(kws[j])){
                    score.add(1.0);
                }
                else{
                    score.add(0.0);
                }
                j++;
            }
        }
        
        //Neu so luong lam bai kiem tra = 0 thi tang len 1, nguoc lai tiep tuc tang tiep so luong lam bai kiem tra 1 don vi
        if(u.getNumberOfTests(Connectionn.getConnection()) != 0)
            u.setCountTest(u.getNumberOfTests(Connectionn.getConnection()) + 1);
        else
            u.setCountTest(1);
        
        u.setScore(score);
        
        for(int i = 0; i < 1; i++){
            System.out.println("\n========== RESULT ==========");
            //System.out.println(questionsByLevel.get(i));
            while(rs.next())
                System.out.println(rs.getString("c_general"));
            Conversation cvs = (Conversation) questionsByLevel.get(i);
            int j = 0;
            for(MultipleChoice c : cvs.getQuestions()){
                System.out.printf("The correct answer for (%d):%sThe answer of user: %s\n%s\n",
                        (j+1), c, kws[j].toUpperCase(), notes[j]);
                j++;
            }
        }             
        rs.close();
    }
    public static void practiceConversation(Scanner scanner, String lv, User u, Connection conn) throws ClassNotFoundException, SQLException, ParseException{
        Statement stm = conn.createStatement();
        Statement stm1 = conn.createStatement();
        Statement stm2 = conn.createStatement();
        Statement stm3 = conn.createStatement();
        List<Integer> questionId = new ArrayList<>();
        
        /* Nap cau hoi tu csdl */
        ResultSet rs = stm.executeQuery("SELECT c.c_general "
                + "FROM conversation c " 
                + "INNER JOIN question q ON c.id_question = q.id_question " 
                + "INNER JOIN level l ON q.id_level = l.id_level " 
                + "WHERE l.content = \"" + lv.toLowerCase() + "\" ORDER BY RAND() LIMIT 1");
        while (rs.next())
        {
            ResultSet rs1 = stm1.executeQuery("select q.id_question, q.content, "
                     + "c.id_question as conversation " 
                     + "from question q inner join conversation c " 
                     + "where q.id_question = c.id_question and "
                     + "c.c_general = \"" + rs.getString("c.c_general") + "\"");

            Level level = new Level(lv.toLowerCase());
            int multipleID = 0;
            QuestionManagement qs = new QuestionManagement();
            while(rs1.next()){
                Category cateI = new Category("General");
                Question qC = new Conversation(rs1.getString("content"), level, cateI);
                ResultSet rs2 = stm2.executeQuery("SELECT q.id_question, q.content as question, "
                            + "l.content as level, c.content as category "
                            + "FROM question q "
                            + "INNER JOIN multiplechoice m ON m.id_Qmultiple = q.id_question "
                            + "INNER JOIN level l ON l.id_level = q.id_level "
                            + "INNER JOIN category c ON q.id_category = c.id_category "
                            + "WHERE m.id_conversation = " + rs1.getInt("conversation"));
                while(rs2.next()){
                    questionId.add(rs2.getInt("id_question"));
                    Level lvM = new Level(rs2.getString("level"));
                    Category cateM = new Category(rs2.getString("category"));
                    Question qM = new MultipleChoice(rs2.getString("question"), lvM, cateM);
                    multipleID = rs2.getInt("id_question");
                    ResultSet rs3 = stm3.executeQuery("SELECT * FROM choice WHERE id_multi = " + multipleID);
                    while(rs3.next()){
                        Choice ch = new Choice(rs3.getString("content"),
                                rs3.getInt("iscorrect"), rs3.getString("note"));
                        qM.addChoice(ch);
                    }
                    qC.addQuestion(qM);
                    qs.addQuestion(qC);
                    rs3.close();
                }
                rs2.close();
            }

            /* thuc hien choi */
            qs.practiceConversation(scanner, level, u, questionId);
            rs1.close();

            String query = "INSERT INTO practice(practice_username, count_test, score, practice_date, id_question)"
                    + "VALUES(?, ?, ?, ?, ?)"; // Chen vao bang du lieu nhung cau hoi nguoi dung da luyen tap
            PreparedStatement stm4 = conn.prepareStatement(query);
            for(int i = 0; i < questionId.size(); i++){
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String date = f.format(new Date());
                java.sql.Date sqlDate = new java.sql.Date(f.parse(date).getTime()); 
                stm4.setString(1, u.getUsername());
                stm4.setInt(2, u.getCountTest());
                stm4.setDouble(3, u.getScore().get(i));
                stm4.setDate(4, sqlDate);
                stm4.setInt(5, questionId.get(i));
                stm4.execute(); 
            }
        }
        stm.close();                 
        stm1.close();
        stm2.close();
        stm3.close();
        conn.close();
    }
    
    
    public void practiceInComplete(Scanner scanner, Level lv, User u, List<Integer> qId) throws ClassNotFoundException, SQLException{
        Statement stm = Connectionn.getConnection().createStatement();
        Collections.shuffle(this.getInCompleteQ());
        String[] notes = new String[100];
        String[] kws = new String[100];
        List<Question> questionsByLevel = new ArrayList<>();
        List<Double> score = new ArrayList<>();
        
        for(Question q : this.getInCompleteQ()){
            if(q.getLevel().getContent().toLowerCase().equals(lv.getContent().toLowerCase()))
                questionsByLevel.add(q); //Xu ly ngoai le else
        }
        
        ResultSet rs = stm.executeQuery("SELECT in_general "
                + "FROM incomplete "
                + "WHERE id_question = " + qId.get(0));   

        for(int i = 0; i < 1; i++){
            while(rs.next())
                System.out.println(rs.getString("in_general"));
            InComplete icq = (InComplete) questionsByLevel.get(i);            
            int j = 0;
            for(MultipleChoice c : icq.getQuestions()){
                System.out.println("(" + (j + 1) + "): " + c + "Answer: ");
                kws[j] = scanner.nextLine();
                for(int k = 0; k < c.getChoices().size(); k++){
                    if(c.getChoices().get(k).isCorrect() == 1)
                        notes[j] =  String.format("The correct answer is: %s\nNote: %s\n", c.getLABELS()[k], c.getChoices().get(k).getNote());
                }
                if(c.checkAnswer(kws[j])){
                    score.add(1.0);
                }
                else{
                    score.add(0.0);
                }
                j++;
            }
        }
        
        //Neu so luong lam bai kiem tra = 0 thi tang len 1, nguoc lai tiep tuc tang tiep so luong lam bai kiem tra 1 don vi
        if(u.getNumberOfTests(Connectionn.getConnection()) != 0)
            u.setCountTest(u.getNumberOfTests(Connectionn.getConnection()) + 1);
        else
            u.setCountTest(1);
        
        u.setScore(score);
        
        for(int i = 0; i < 1; i++){
            System.out.println("\n========== RESULT ==========");
            //System.out.println(questionsByLevel.get(i));
            while(rs.next())
                System.out.println(rs.getString("in_general"));
            InComplete icq = (InComplete) questionsByLevel.get(i);
            int j = 0;
            for(MultipleChoice c : icq.getQuestions()){
                System.out.printf("The correct answer for (%d):%sThe answer of user: %s\n%s\n",
                        (j+1), c, kws[j].toUpperCase(), notes[j]);
                j++;
            }
        }             
        rs.close();
    }
    public static void practiceInComplete(Scanner scanner, String lv, User u, Connection conn) throws ClassNotFoundException, SQLException, ParseException{
        Statement stm = conn.createStatement();
        Statement stm1 = conn.createStatement();
        Statement stm2 = conn.createStatement();
        Statement stm3 = conn.createStatement();
        List<Integer> questionId = new ArrayList<>();
        
        /* Nap cau hoi tu csdl */
        ResultSet rs = stm.executeQuery("SELECT i.in_general "
                + "FROM incomplete i " 
                + "INNER JOIN question q ON i.id_question = q.id_question " 
                + "INNER JOIN level l ON q.id_level = l.id_level " 
                + "WHERE l.content = \"" + lv.toLowerCase() + "\" ORDER BY RAND() LIMIT 1");
        while (rs.next())
        {
            ResultSet rs1 = stm1.executeQuery("select q.id_question, q.content, "
                     + "i.id_question as incomplete " 
                     + "from question q inner join incomplete i " 
                     + "where q.id_question = i.id_question and "
                     + "i.in_general = \"" + rs.getString("i.in_general") + "\"");

            Level level = new Level(lv.toLowerCase());
            int multipleID = 0;
            QuestionManagement qs = new QuestionManagement();
            while(rs1.next()){
                Category cateI = new Category("General");
                Question qC = new InComplete(rs1.getString("content"), level, cateI);
                ResultSet rs2 = stm2.executeQuery("SELECT q.id_question, q.content as question, "
                            + "l.content as level, c.content as category "
                            + "FROM question q "
                            + "INNER JOIN multiplechoice m ON m.id_Qmultiple = q.id_question "
                            + "INNER JOIN level l ON l.id_level = q.id_level "
                            + "INNER JOIN category c ON q.id_category = c.id_category "
                            + "WHERE m.id_incomplete = " + rs1.getInt("incomplete"));
                while(rs2.next()){
                    questionId.add(rs2.getInt("id_question"));
                    Level lvM = new Level(rs2.getString("level"));
                    Category cateM = new Category(rs2.getString("category"));
                    Question qM = new MultipleChoice(rs2.getString("question"), lvM, cateM);
                    multipleID = rs2.getInt("id_question");
                    ResultSet rs3 = stm3.executeQuery("SELECT * FROM choice WHERE id_multi = " + multipleID);
                    while(rs3.next()){
                        Choice ch = new Choice(rs3.getString("content"),
                                rs3.getInt("iscorrect"), rs3.getString("note"));
                        qM.addChoice(ch);
                    }
                    qC.addQuestion(qM);
                    qs.addQuestion(qC);
                    rs3.close();
                }
                rs2.close();
            }

            /* thuc hien choi */
            qs.practiceInComplete(scanner, level, u, questionId);
            rs1.close();

            String query = "INSERT INTO practice(practice_username, count_test, score, practice_date, id_question)"
                    + "VALUES(?, ?, ?, ?, ?)"; // Chen vao bang du lieu nhung cau hoi nguoi dung da luyen tap
            PreparedStatement stm4 = conn.prepareStatement(query);
            for(int i = 0; i < questionId.size(); i++){
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String date = f.format(new Date());
                java.sql.Date sqlDate = new java.sql.Date(f.parse(date).getTime()); 
                stm4.setString(1, u.getUsername());
                stm4.setInt(2, u.getCountTest());
                stm4.setDouble(3, u.getScore().get(i));
                stm4.setDate(4, sqlDate);
                stm4.setInt(5, questionId.get(i));
                stm4.execute(); 
            }
        }
        stm.close();                 
        stm1.close();
        stm2.close();
        stm3.close();
        conn.close();
    }
  
//    public void practiceInComplete(Scanner scanner, Level lv, User u) throws ClassNotFoundException, SQLException{
//        Statement stm = Connectionn.getConnection().createStatement();
//        Collections.shuffle(this.getInCompleteQ());
//        String[] notes = new String[100];
//        String[] kws = new String[100];
//        List<Question> questionsByLevel = new ArrayList<>();
//        List<Double> score = new ArrayList<>();
//        
//        for(Question q : this.getInCompleteQ()){
//            if(q.getLevel().getContent().toLowerCase().equals(lv.getContent().toLowerCase())){
//                questionsByLevel.add(q); //Xu ly ngoai le else
//            }
//        }
//        
//        ResultSet rs = stm.executeQuery("SELECT in_general FROM incomplete");
//        this.getInCompleteQ().get(0).getCategory();
//        
//        for(int i = 0; i < 1; i++){
//            while(rs.next())
//                System.out.println(rs.getString("in_general"));
//            InComplete icp = (InComplete) questionsByLevel.get(i);
//            int j = 0;
//            for(MultipleChoice c : icp.getQuestions()){
//                System.out.print("(" + (j + 1) + "): " + c + "Answer: ");
//                kws[j] = scanner.nextLine();
//                for(int k = 0; k < c.getChoices().size(); k++){
//                    if(c.getChoices().get(k).isCorrect() == 1)
//                        notes[j] =  String.format("The correct answer is: %s\nNote: %s\n", c.getLABELS()[k], c.getChoices().get(k).getNote());
//                }
//                if(c.checkAnswer(kws[j])){
//                    score.add(1.0);
//                }
//                else{
//                    score.add(0.0);
//                }
//                j++;
//            }
//        }
//        //Neu so luong lam bai kiem tra = 0 thi tang len 1, nguoc lai tiep tuc tang tiep so luong lam bai kiem tra 1 don vi
//        if(u.getNumberOfTests(Connectionn.getConnection()) != 0)
//            u.setCountTest(u.getNumberOfTests(Connectionn.getConnection()) + 1);
//        else
//            u.setCountTest(1);
//        
//        u.setScore(score);
//        
//        for(int i = 0; i < 1; i++){
//            System.out.println("\n========== RESULT ==========");
//            System.out.println(questionsByLevel.get(i));
//            InComplete icp = (InComplete) questionsByLevel.get(i);
//            int j = 0;
//            for(MultipleChoice c : icp.getQuestions()){
//                System.out.printf("The correct answer for (%d):%sThe answer of user: %s\n%s\n",
//                        (j+1), c, kws[j].toUpperCase(), notes[j]);
//                j++;
//            }
//        }
//    }
//    public static void practiceInComplete(Scanner scanner, String lv, User u, Connection conn) throws ClassNotFoundException, SQLException, ParseException, UnsupportedOperationException{
//        Statement stm = conn.createStatement();
//        Statement stm1 = conn.createStatement();
//        Statement stm2 = conn.createStatement();
//        
//        /* Nap cau hoi tu csdl */
//        ResultSet rs = stm.executeQuery("SELECT q.id_question, q.content, "
//                + "i.id_question as incomplete, i.in_general "
//                + "FROM incomplete i "
//                + "INNER JOIN question q ON i.id_question = q.id_question "
//                + "INNER JOIN level l ON q.id_level = l.id_level "
//                + "WHERE l.content = \"" + lv.toLowerCase()+ "\" ORDER BY RAND() LIMIT 1");
//        
//        Level level = new Level(lv.toLowerCase());
//        int multipleID = 0;
//        List<Integer> questionId = new ArrayList<>();
//        QuestionManagement qs = new QuestionManagement();
//        while(rs.next()){
//            Category cateI = new Category("General");
//            Question qI = new InComplete(rs.getString("content"), level, cateI);
//            ResultSet rs1 = stm1.executeQuery("SELECT q.id_question, q.content as question, "
//                        + "l.content as level, c.content as category "
//                        + "FROM question q "
//                        + "INNER JOIN multiplechoice m ON m.id_Qmultiple = q.id_question "
//                        + "INNER JOIN level l ON l.id_level = q.id_level "
//                        + "INNER JOIN category c ON q.id_category = c.id_category "
//                        + "WHERE m.id_incomplete = " + rs.getInt("incomplete"));
//            while(rs1.next()){
//                questionId.add(rs1.getInt("q.id_question"));
//                Level lvM = new Level(rs1.getString("level"));
//                Category cateM = new Category(rs1.getString("category"));
//                Question qM = new MultipleChoice(rs1.getString("question"), lvM, cateM);
//                multipleID = rs1.getInt("id_question");
//                ResultSet rs2 = stm2.executeQuery("SELECT * FROM choice WHERE id_multi = " + multipleID);
//                while(rs2.next()){
//                    Choice ch = new Choice(rs2.getString("content"),
//                            rs2.getInt("iscorrect"), rs2.getString("note"));
//                    qM.addChoice(ch);
//                }
//                qI.addQuestion(qM);
//                qs.addQuestion(qI);
//                rs2.close();
//            }
//            rs1.close();
//        }
//        
//        /* thuc hien choi */
//        qs.practiceInComplete(scanner, level, u);
//        rs.close();
//        
//        String query = "INSERT INTO practice(practice_username, count_test, score, practice_date, id_question)"
//                + "VALUES(?, ?, ?, ?, ?)"; // Chen vao bang du lieu nhung cau hoi nguoi dung da luyen tap
//        PreparedStatement stm3 = conn.prepareStatement(query);
//        for(int i = 0; i < questionId.size(); i++){
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//            String date = f.format(new Date());
//            java.sql.Date sqlDate = new java.sql.Date(f.parse(date).getTime()); 
//            stm3.setString(1, u.getUsername());
//            stm3.setInt(2, u.getCountTest());
//            stm3.setDouble(3, u.getScore().get(i));
//            stm3.setDate(4, sqlDate);
//            stm3.setInt(5, questionId.get(i));
//            stm3.execute(); 
//        }
//                                        
//        stm.close();
//        stm1.close();
//        stm2.close();
//        conn.close();
//    
//    }
    
    
    public List<Question> lookUpByContent(String c){
        List<Question> r = new ArrayList<>();
        for(Question q : this.questions){
            if(q.getContent().toUpperCase().contains(c.toUpperCase()))
                r.add(q);
        }
        return r;
    }
    public List<Question> lookUpByContent(String c, Connection conn) throws SQLException{
        List<Question> r = new ArrayList<>();
        Statement stm = conn.createStatement();
        
        ResultSet rs = stm.executeQuery("SELECT q.content as question, "
                        + "l.content as level, c.content as category "
                        + "FROM question q "
                        + "INNER JOIN multiplechoice m ON m.id_Qmultiple = q.id_question "
                        + "INNER JOIN level l ON l.id_level = q.id_level "
                        + "INNER JOIN category c ON q.id_category = c.id_category ");
        
        this.questions = new ArrayList<>();
        
        while(rs.next()){
            Level l = new Level(rs.getString("level"));
            Category cate = new Category(rs.getString("category"));
            Question q = new MultipleChoice(rs.getString("question"), l, cate);
            this.questions.add(q);
        }
        r = this.lookUpByContent(c);
        rs.close();
        
        stm.close();
        conn.close();
        return r;
    }    
    
    public List<Question> lookUpByCate(String c, Connection conn) throws SQLException{
        List<Question> r = new ArrayList<>();
        Statement stm = conn.createStatement();
        
        ResultSet rs = stm.executeQuery("SELECT q.content as question, "
                + "l.content as level, c.content as category "
                + "FROM question q "
                + "INNER JOIN level l ON l.id_level = q.id_level "
                + "INNER JOIN category c ON c.id_category = q.id_category "
                + "WHERE c.content = \"" + c + "\"");
        
        this.questions = new ArrayList<>();
        while(rs.next()){
            Level l = new Level(rs.getString("level"));
            Category cate = new Category(rs.getString("category"));
            Question q = new MultipleChoice(rs.getString("question"), l, cate);
            r.add(q);
        }
        
        rs.close();
        
        stm.close();
        conn.close();
        return r;
    }    
    
    public List<Question> lookUpByLevel(String l, Connection conn) throws SQLException{
        List<Question> r = new ArrayList<>();
        Statement stm = conn.createStatement();
        
        ResultSet rs = stm.executeQuery("SELECT q.content as question, "
                + "l.content as level, c.content as category "
                + "FROM question q "
                + "INNER JOIN level l ON l.id_level = q.id_level "
                + "INNER JOIN category c ON c.id_category = q.id_category "
                + "WHERE l.content = \"" + l + "\"");
        
        this.questions = new ArrayList<>();
        while(rs.next()){
            Level lv = new Level(rs.getString("level"));
            Category cate = new Category(rs.getString("category"));
            Question q = new MultipleChoice(rs.getString("question"), lv, cate);
            r.add(q);
        }
        
        rs.close();
        
        stm.close();
        conn.close();
        return r;
    }
    
    public void viewListQ(){
        for(Question q : this.questions){
            System.out.print(q);
        }
    }    
    public void viewListQ(Connection conn) throws SQLException, ParseException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("select q.id_question, q.content, l.content as level, c.content as category "
                + "from question q inner join  level l on l.id_level = q.id_level "
                + "inner join  category c on c.id_category = q.id_category "
                + "where q.id_category = c.id_category and q.id_level = l.id_level");
        this.questions = new ArrayList<>();
        while(rs.next()){
            String nd = rs.getString("q.content");
            Level lv = new Level(rs.getString("level"));
            Category cate = new Category(rs.getString("category"));
            Question q = new MultipleChoice(nd, lv, cate);           
            this.questions.add(q);
        }
        this.viewListQ();
        rs.close();
        stm.close();
        conn.close();
    }
}
