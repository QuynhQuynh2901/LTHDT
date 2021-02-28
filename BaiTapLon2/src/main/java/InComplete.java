
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class InComplete extends Question{
    private List<MultipleChoice> questions;

    public InComplete(String c, Level l, Category ca) {
        super(c, l, ca);
        questions = new ArrayList<>();
    }

    public void addQuestion(Question q){
        MultipleChoice c = (MultipleChoice) q;
        this.questions.add(c);
    }
    
    @Override
    public String toString() {
        String s = super.toString();
        return s;
    }
    
     /**
     * @return the questions
     */
    public List<MultipleChoice> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<MultipleChoice> questions) {
        this.questions = questions;
    }
}
