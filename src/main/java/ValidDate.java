public class ValidDate extends Question implements java.io.Serializable{
    private static final long serialVersionUID = 57053285L;
    protected boolean multipleAns;
    @Override
    protected void setChoices() {
        Output.display("Does this question have multiple answers (Yes/no)?");
        String resp = Input.getYesNo();
        if (resp.equals("yes")) {
            multipleAns=true;
            Output.display("How many answers are there?");
            numAns = Input.getIntRespFloor(0);
        }
        else{
            multipleAns=false;
        }

    }

    protected void setResponse(String response){
        this.userAnswer.setResponse(response);
    }

    public ResponseCorrectAnswer getResponse(){
        return this.userAnswer;
    }

    public void take(){
        Output.display(getPrompt());
        if(multipleAns){
            Output.display("You can answer the prompt, " + numAns + "Times.");
            for(int i =0; i<numAns; i++){
                Output.display("Enter a response in the format yyyy-mm-dd");
                String resp = Input.getValidDateFormat();
                setResponse(resp);
            }
        }
        else{
            Output.display("Enter a response");
            String resp = Input.getStringResp();
            setResponse(resp);
        }

    }

    public void modify(){
        Output.display("would you like to edit the prompt?");
        String resp = Input.getYesNo();
        if(resp.equals("yes")){
            Output.display("enter the new prompt name");
            setPrompt(Input.getStringResp());
        }
    }

    public void display(){
        Output.display(getPrompt());
    }

}
