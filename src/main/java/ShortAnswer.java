public class ShortAnswer extends Essay implements java.io.Serializable{
    private static final long serialVersionUID = 20053285L;
    private int limit;

    @Override
    protected void setChoices() {
        Output.display("Does this question have multiple answers (Yes/no)?");
        String resp = Input.getYesNo();
        multipleAns= resp.equals("yes");
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

    public Response getResponse(){
        return this.userAnswer;
    }

    public void take(){
        Output.display(getPrompt());
        if(multipleAns) {
            Output.display("Answer with ShortAnswer when asked or 0 when done");
            while(true) {
                Output.display("Answer the prompt");
                String input = Input.getStringLim(limit);
                if(input.equals("0")){
                    break;
                }
                setResponse(input);
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
