
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
public class MultipleChoice extends Question{
    private final String[] LABELS = {"A", "B", "C", "D"};
    private List<Choice> choices = new ArrayList<>();

    public MultipleChoice( String content, Level level, Category category) {
        super(content, level, category);
        this.choices = choices;
    }
    
    public void addChoice(Choice c){
        this.choices.add(c);
    }
    
    public boolean checkAnswer(String kw){
        for(int i = 0; i < choices.size(); i++){
            if(this.choices.get(i).isCorrect() == 1 && LABELS[i].equals(kw.toUpperCase()))
                return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        String s = super.toString();
        int n = this.choices.size() - LABELS.length;
        if(n < 0)
            for(int i = 0; i < this.choices.size(); i++)
                s = s + LABELS[i] + ". " + this.choices.get(i).getContent() + "\n";
        else
            for(int i = 0; i < LABELS.length; i++)
                s = s + LABELS[i] + ". " + this.choices.get(i).getContent() + "\n";
        return s;
    }

    public String[] getLABELS() {
        return LABELS;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public List<Choice> getChoices() {
        return choices;
    }
    
    
}
