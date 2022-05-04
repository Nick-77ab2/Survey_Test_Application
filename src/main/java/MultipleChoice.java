import java.util.ArrayList;

public class MultipleChoice extends Question implements java.io.Serializable{
    private static final long serialVersionUID = 44053285L;
    protected String[] choices;
    protected ArrayList multAnswers;
    protected boolean multipleAns;

   protected void setChoices(){
       int choicesSize=4;
       //ask user for choices and multiple ans
       choices = new String[choicesSize];
   }

   public String[] getChoices(){
       return this.choices;
   }
   protected void setResponse(String[] response){
       for(int i=0;i<response.length;i++)
       {
           multAnswers.add(response[i]);
       }

   }

    public String getResponse(){
       return this.userAnswer;
   }

   public void take(){

   }

   public void modify(){

   }

   public void display(){

   }

}