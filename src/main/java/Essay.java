public class Essay extends Question implements java.io.Serializable{
    private static final long serialVersionUID = 35053285L;
    protected boolean multipleAns=true;
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

    public void take(){
        Output.display(getPrompt());
        String input;
        if(multipleAns) {
            Output.display("You must answer the prompt, " + numAns + "Times.");
            for(int i =0; i<numAns; i++){
                Output.display("Enter a response");
                String resp = Input.getStringResp();
                setResponse(resp);
            }
        }
        else{
            Output.display("Answer the prompt");
            input = Input.getStringResp();
            setResponse(input);
        }
    }

    protected void setResponse(String response){
        this.userAnswer.setResponse(response);
    }

    public ResponseCorrectAnswer getResponse(){
        return this.userAnswer;
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

