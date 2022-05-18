import java.util.ArrayList;

public class Survey implements java.io.Serializable {
    ArrayList<Question> questions;
    //ArrayList<Response> answers;
    String name;
    private static final long serialVersionUID = 1L;
    public String basePath = "surveys";
    Survey(){
        questions = new ArrayList<>();
    }
    public ArrayList<Question> getQuestions(){
        return questions;
    }
    public void deleteQuestion(int question){
        questions.remove(question);
    }
    protected void setName(String name){
        this.name=name;
    }
    protected String getName(){
        return this.name;
    }

    protected void addMult(){
        Question multQuestion = new MultipleChoice();
        setPrompt(multQuestion);
        multQuestion.setChoices();
        questions.add(multQuestion);
    }

    protected void addTF(){
        Question tFQuestion = new TrueFalse();
        setPrompt(tFQuestion);
        questions.add(tFQuestion);
    }

    protected void addEssay(){
        Question essayQuestion = new Essay();
        setPrompt(essayQuestion);
        essayQuestion.setChoices();
        questions.add(essayQuestion);
    }

    protected void addShortAns(){
        Question sAnsQuestion = new ShortAnswer();
        setPrompt(sAnsQuestion);
        Output.display("Please enter the character limit for this question");
        sAnsQuestion.setLimit(Input.getIntRespFloor(0));
        sAnsQuestion.setChoices();
        questions.add(sAnsQuestion);
    }

    protected void addMatching(){
        Question matchQuestion = new Matching();
        setPrompt(matchQuestion);
        matchQuestion.setChoices();
        questions.add(matchQuestion);
    }

    protected void addValidDate(){
        Question validDateQuestion = new ValidDate();
        setPrompt(validDateQuestion);
        validDateQuestion.setChoices();
        questions.add(validDateQuestion);
    }
    protected void setPrompt(Question question){
        Output.display("Please enter the prompt for the question");
        String response = Input.getStringResp();
        question.setPrompt(response);
    }

    public void take(){
        for (Question question : questions) {
            question.take();
        }

    }
    public void modify(){
        int qNum;
        //create bool to force this to continue because we're modifying the survey, not just a question
        boolean isModifying=true;
        while(isModifying){
            Output.display("There are " + questions.size() + " questions. Please enter the number of the question you would like to modify (starting from 1) or 0 to exit");
            qNum = Input.readIntInRange(0,questions.size());
            if(qNum == 0){
                isModifying=false;
            }
            else{
                Output.display("Would you like to delete the question (yes/no)?");
                String resp = Input.getYesNo();
                if(resp.equals("yes")){
                    deleteQuestion((qNum-1));
                }
                else{
                    questions.get(qNum-1).modify();
                }
            }
        }

    }
    public void display(){
        for (Question question : questions) {
            question.display();
        }
    }
    /* THE BELOW BECAME OBSOLETE WHEN I REALIZED THAT I NEED TO LOAD A SURVEY IN ORDER TO TAKE ONE
    public void storeAnswers(ArrayList answers){
        String personData;
        String connectedString;
        if(getName()==null) {
            Output.display("Enter a name for the survey:");
            setName(Input.getStringResp());
        }
        Output.display("Enter your name and response number:");
        personData = Input.getStringResp();
        connectedString= getName() + "_" + personData;
        SerializationHelper.serialize(Response.class, answers,basePath,connectedString);

    }
    public void loadAnswers(){
        //This is synonymous with loading a survey. The only real difference is what's stored.
    }
     */


}
