/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public abstract class Question {
    private String content;
    private Level level;
    private Category category;
    

    
    
    public Question(String content, Level level, Category category) {
        this.content = content;
        this.level = level;
        this.category = category;
    }
    
    @Override
    public String toString() {
        return String.format("\n%s\n", this.getContent());
    }
    
    public void addChoice(Choice c){}
    public boolean checkAnswer(String kw){return true;}
    public void addQuestion(Question q){}
    
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }
    
    
}
