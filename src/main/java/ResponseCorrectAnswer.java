import java.util.ArrayList;
import java.util.List;

public class ResponseCorrectAnswer implements java.io.Serializable {
    protected List<String> response;

    public ResponseCorrectAnswer() {
        response=new ArrayList<>();
    }

    public void setResponse(String resp){
        response.add(resp);
    }

    public List<String> getResponse(){
        return response;
    }

    public void display() {
        if (this.response.size() > 0) {
            for (String s : response) {
                Output.display(s);
            }
        }
    }
    public boolean compare(ResponseCorrectAnswer a, ResponseCorrectAnswer b){
        return a.response.equals(b.response);
    }
}
