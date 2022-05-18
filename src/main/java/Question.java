public abstract class Question implements java.io.Serializable {
    private static final long serialVersionUID = 23053285L;
    public Response userAnswer = new Response();
    protected String questionPrompt;

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
        this.userAnswer.setAnswer(response);
    }

    public Response getResponse(){
        return this.userAnswer;
    }

    public void take(){

    }

    public void modify(){

    }

    public void display(){

    }
}
