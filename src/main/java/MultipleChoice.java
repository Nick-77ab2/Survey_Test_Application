
public class MultipleChoice extends Question implements java.io.Serializable{
    private static final long serialVersionUID = 44053285L;
    protected String[] choices;
    protected int numChoices;
    public int numAns=1;
    protected boolean multipleAns;

   protected void setChoices(){

       Output.display("Enter the number of choices for the question");
       numChoices=Input.getIntRespFloor(0);
       //ask user for choices and multiple ans
       choices = new String[numChoices];

       Output.display("Does this question have multiple answers (Yes/no)?");
       String resp = Input.getYesNo();
       if (resp.equals("yes")) {
           multipleAns=true;
           Output.display("How many answers are there?");
           numAns = Input.getIntRespCeiling(numChoices);
       }
       else{
           multipleAns=false;
       }

       for(int i=0; i<choices.length; i++){
            Output.display("Please enter choice #" + (i+1) +": ");
            choices[i] = Input.getStringResp();
       }
   }

   public String[] getChoices(){
       return this.choices;
   }
    protected void setResponse(String response){
        this.userAnswer.setResponse(response);
    }

    public ResponseCorrectAnswer getResponse(){
        return this.userAnswer;
    }

   public void take(){
        Output.display(getPrompt());
        Output.display(getChoices());
        if(multipleAns) {
            Output.display("Enter choice when asked or 0 when done");
            for (int i = 0; i < choices.length; i++) {
                Output.display("Enter choice #");
                int input = Input.getIntRespCeiling(choices.length + 1);
                if(input==0){
                    break;
                }
                setResponse(choices[input-1]);
            }
        }
        else{
            Output.display("Enter choice #");
            int input = Input.getIntRespCeiling(choices.length + 1);
            setResponse(choices[input-1]);
        }

   }

   public void modify(){
       int choice;
       Output.display("would you like to edit the prompt?");
       String resp = Input.getYesNo();
       if(resp.equals("yes")) {
           Output.display("enter the new prompt name");
           setPrompt(Input.getStringResp());
       }
       Output.display("would you like to edit the choices?");
       String resp1 = Input.getYesNo();
       if(resp1.equals("yes")) {
           Output.display("Which choice would you like to edit?");
           Output.display(getChoices());
           choice = Input.getIntRespCeiling(numChoices);
           Output.display("Enter new text");
           choices[choice - 1] = Input.getStringResp();
       }

   }

   public void display(){
       Output.display(getPrompt());
       Output.display(getChoices());
   }

}