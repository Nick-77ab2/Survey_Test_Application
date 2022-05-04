import java.util.ArrayList;

public class Survey implements java.io.Serializable {
    ArrayList<Question> questions=null;
    private static final long serialVersionUID = 1L;
    public String basePath;
    Survey(){
        questions = new ArrayList<Question>();
    }
    public ArrayList<Question> getQuestions(){
        return questions;
    }
    public void deleteQuestion(int question){
        questions.remove(question);
    }

    protected void addMult(){

    }

    protected void addTF(){

    }

    protected void addEssay(){

    }

    protected void addShortAns(){

    }

    protected void addMatching(){

    }

    protected void addValidDate(){

    }

    public void take(){

    }
    public void modify(){

    }
    public void display(){

    }
    public void store(){

    }
    public void load(){

    }


}
