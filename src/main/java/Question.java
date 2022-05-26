public abstract class Question implements java.io.Serializable {
    private static final long serialVersionUID = 23053285L;
    public ResponseCorrectAnswer userAnswer = new ResponseCorrectAnswer();
    protected String questionPrompt;
    protected int numAns=1;
    protected boolean multipleAns;
    protected String[] choices;
    protected int limit;

    protected abstract void setChoices();

    protected void setLimit(int limit){

    }
    protected void setPrompt(String question){
        this.questionPrompt=question;
    }

    protected String getPrompt(){
        return questionPrompt;
    }

    protected void setResponse(String response){
        this.userAnswer.setResponse(response);
    }

    public ResponseCorrectAnswer getResponse(){
        return this.userAnswer;
    }

    public void take(){
    }

    public void modify(){

    }

    public void display(){

    }
    public int getLimit(){
        return limit;
    }
}
