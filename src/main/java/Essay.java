public class Essay extends Question implements java.io.Serializable{
    private static final long serialVersionUID = 35053285L;
    protected boolean multipleAns=true;
    @Override
    protected void setChoices() {
        Output.display("Does this question have multiple answers (Yes/no)?");
        String resp = Input.getYesNo();
        multipleAns= resp.equals("yes");
    }

    public void take(){
        Output.display(getPrompt());
        String input;
        if(multipleAns) {
            Output.display("Answer with Essay when asked or 0 when done");
            while(true) {
                Output.display("Answer the prompt");
                input = Input.getStringResp();
                if(input.equals("0")){
                    break;
                }
                setResponse(input);
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

    public Response getResponse(){
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
