public class Matching extends Question implements java.io.Serializable{
    private static final long serialVersionUID = 45053285L;
    public int colMax;
    public String[] colOne;
    public String[] colTwo;
    protected String[] storedAnswer;

    protected void setChoices(){
        Output.display("Enter the number of choices for the question");
        colMax = Input.getIntRespFloor(0);
        colOne= new String[colMax];
        colTwo= new String[colMax];
        storedAnswer = new String[colMax];
        numAns=colMax;

        Output.display("Column 1");
        for(int i=0; i<colMax; i++){
            Output.display("Enter choice number " + (i+1));
            colOne[i]=Input.getStringResp();
        }
        Output.display("Column 2");
        for(int i=0; i<colMax; i++){
            Output.display("Enter choice number " + (i+1));
            colTwo[i]=Input.getStringResp();
        }
    }

    public void getChoices(){
        String matching;
        for(int i=0; i<colMax; i++){
            matching = (i+1) + ") " + colOne[i] + "\t" + (i+1) + ") " + colTwo[i];
            Output.display(matching);
        }
    }

    protected void setResponse(String response){
        this.userAnswer.setResponse(response);

    }

    public ResponseCorrectAnswer getResponse(){
        return super.getResponse();
    }

    public void take(){
        Output.display(getPrompt());
        getChoices();
        Output.display("Following column one in order, choose the number of the answer in column two to match it");
        for(int i=0; i<colMax; i++){
            Output.display("Choose the correct answer for number " + (i+1));
            storedAnswer[i]=colTwo[Input.getIntRespCeiling(colMax+1)-1];
            setResponse(storedAnswer[i]);
        }

    }

    public void modify(){
        while(true) {
            Output.display("Would you like to edit column 1 or column 2 (1/2)? type 0 to exit");
            int input = Input.readIntInRange(0, 2);
            if (input == 1) {
                Output.display("Which number would you like to edit?");
                int editNum = Input.readIntInRange(1, colMax);
                Output.display("Type your change below");
                String change = Input.getStringResp();
                colOne[editNum] = change;
            } else if(input==2) {
                Output.display("Which number would you like to edit?");
                int editNum = Input.readIntInRange(1, colMax);
                Output.display("Type your change below");
                String change = Input.getStringResp();
                colTwo[editNum] = change;
            }
            else{
                break;
            }
        }

    }

    public void display(){
        Output.display(getPrompt());
        getChoices();
    }
}
