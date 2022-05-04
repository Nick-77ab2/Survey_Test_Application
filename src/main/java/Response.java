import java.util.ArrayList;
import java.util.List;

public class Response implements java.io.Serializable {
    private List<String> response = new ArrayList<String>();

    public void setResponse(String response){
        this.response.add(response);
    }

    public List<String> getResponse(){
        return this.response;
    }

    public void display(){
        for(int i=0; i<response.size(); i++){
            //FILL LATER
        }
    }
}
