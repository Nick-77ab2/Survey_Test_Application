public class Matching extends Question implements java.io.Serializable{
    private static final long serialVersionUID = 45053285L;
    public int colMax;
    public String[] colOne;
    public String[] colTwo;
    protected String[] storedAnswer;

    protected void setChoices(){

    }

    public void getChoices(){

    }

    protected void setResponse(String response){
        this.userAnswer.setResponse(response);

    }

    public Response getResponse(){
        return super.getResponse();
    }

    public void take(){

    }

    public void modify(){

    }

    public void display(){

    }
}
