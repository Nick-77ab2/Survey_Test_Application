import java.util.ArrayList;
import java.util.List;

public class Response implements java.io.Serializable {
    protected List<String> response;

    public Response() {
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
}
