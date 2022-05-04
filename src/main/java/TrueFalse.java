public class TrueFalse extends MultipleChoice implements java.io.Serializable{
    private static final long serialVersionUID = 13053285L;

    protected String[] choices={"T","F"};

    public String[] getChoices(){
        return this.choices;
    }

    protected void setResponse(String response){
    }

    public String getResponse(){
        return this.userAnswer;
    }
}
