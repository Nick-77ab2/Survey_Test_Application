public class TrueFalse extends MultipleChoice implements java.io.Serializable{
    private static final long serialVersionUID = 13053285L;
    protected String[] choices={"t","f"};

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
        Output.display(choices);
        Output.display("Enter choice #");
        int input = Input.getIntRespCeiling(choices.length + 1);
        setResponse(choices[input-1]);
    }

    public void modify(){
        Output.display("Please enter the new Prompt");
        String prompt = Input.getStringResp();
        setPrompt(prompt);
    }

    public void display(){
        Output.display(getPrompt());
        Output.display(choices);
    }
}
