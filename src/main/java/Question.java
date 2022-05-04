public abstract class Question implements java.io.Serializable {
    private static final long serialVersionUID = 23053285L;
    protected Response userAnswer;
    protected String questionPrompt;

    protected void setPrompt(String question){
        this.questionPrompt=question;
    }

    protected String getPrompt(){
        return questionPrompt;
    }

    protected void setResponse(Response response){

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
