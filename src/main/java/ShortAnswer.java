public class ShortAnswer extends Essay implements java.io.Serializable{
    private static final long serialVersionUID = 20053285L;

    @Override
    protected void setChoices() {
        super.setChoices();
    }
    public void setLimit(int limit){
        this.limit=limit;
    }

    public int getLimit(){
        return this.limit;
    }

    protected void setResponse(String response){
        this.userAnswer.setResponse(response);
    }

    public ResponseCorrectAnswer getResponse(){
        return this.userAnswer;
    }

    public void take(){
        Output.display(getPrompt());
        if(multipleAns) {
            Output.display("You must answer the prompt, " + numAns + "Times.");
            for(int i =0; i<numAns; i++){
                Output.display("Enter a response");
                String resp = Input.getStringLim(limit);
                setResponse(resp);
            }
        }
        else{
            Output.display("Answer the prompt");
            String input = Input.getStringLim(limit);
            setResponse(input);
        }
    }

    public void modify(){
        super.modify();
    }

    public void display(){
        super.display();
    }
}
