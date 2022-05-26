import java.io.File;
import java.util.*;

public class Survey implements java.io.Serializable {
    ArrayList<Question> questions=null;
    String name;
    private static final long serialVersionUID = 329458345938L;
    public String basePath = "Surveys" + File.separator;
    Survey(){
        questions = new ArrayList<Question>();
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
        MultipleChoice multQuestion = new MultipleChoice();
        setPrompt(multQuestion);
        multQuestion.setChoices();
        questions.add(multQuestion);
    }

    protected void addTF(){
        TrueFalse tFQuestion = new TrueFalse();
        setPrompt(tFQuestion);
        questions.add(tFQuestion);
    }

    protected void addEssay(){
        Essay essayQuestion = new Essay();
        setPrompt(essayQuestion);
        essayQuestion.setChoices();
        questions.add(essayQuestion);
    }

    protected void addShortAns(){
        ShortAnswer sAnsQuestion = new ShortAnswer();
        setPrompt(sAnsQuestion);
        Output.display("Please enter the character limit for this question");
        sAnsQuestion.setLimit(Input.getIntRespFloor(0));
        sAnsQuestion.setChoices();
        questions.add(sAnsQuestion);
    }

    protected void addMatching(){
        Matching matchQuestion = new Matching();
        setPrompt(matchQuestion);
        matchQuestion.setChoices();
        questions.add(matchQuestion);
    }

    protected void addValidDate(){
        ValidDate validDateQuestion = new ValidDate();
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
        ArrayList<ResponseCorrectAnswer> answers = new ArrayList<>();
        int i=0;
        for(Question question : questions){
            question.userAnswer.response=new ArrayList<>();
            question.take();
            if(i!=answers.size()) {
                answers.set(i, question.userAnswer);
            }
            else{
                answers.add(question.userAnswer);
            }
            i+=1;
        }
        storeResponses(answers);

    }

    public void tabulate(){
        try {
            ArrayList<ArrayList<ResponseCorrectAnswer>> allResponses = loadResponses();
            ArrayList<ArrayList<ResponseCorrectAnswer>> correctedResponses = new ArrayList<>();
            //Correct the 2D array so that each question has its own array of answers.
            //go through all of the questions in the response
            /*while(true) {
                int i;
                for (i = 0; i < allResponses.get(i).size(); i++) {
                    //go through all of the responses
                    ArrayList<ResponseCorrectAnswer> theList = new ArrayList<>();
                    for (int j = 0; j < allResponses.size(); j++) {
                        theList.add(allResponses.get(j).get(i));
                    }
                    correctedResponses.add(theList);
                    if(i+1>allResponses.size()-1){
                        break;
                    }
                }
                if(i+1>allResponses.size()-1){
                    break;
                }
            }

             */
            for(int i=0; i<allResponses.get(0).size(); i++) {
                ArrayList<ResponseCorrectAnswer> theList = new ArrayList<>();
                for (ArrayList<ResponseCorrectAnswer> arrResponse : allResponses) {
                    theList.add(arrResponse.get(i));
                }
                correctedResponses.add(theList);
            }

            for (int i = 0; i < correctedResponses.size(); i++) {
                Output.display("Question:");
                Hashtable<String, Integer> nameAndCount = new Hashtable<String, Integer>();
                Hashtable<List<String>, Integer> nameAndCount1 = new Hashtable<List<String>, Integer>();
                Question q = questions.get(i);
                Output.display(q.getPrompt());
                ArrayList<ResponseCorrectAnswer> currentResponses = correctedResponses.get(i);
                ArrayList<String> allQResponses = new ArrayList<>();

                if (!(q instanceof Essay) && !(q instanceof Matching)) {
                    for (int j = 0; j < currentResponses.size(); j++) {
                        for (String response : currentResponses.get(j).getResponse()) {
                            allQResponses.add(response);
                        }
                    }
                    // build hash table with count
                    for (String answer : allQResponses) {
                        Integer count = nameAndCount.get(answer);
                        if (count == null) {
                            nameAndCount.put(answer, 1);
                        } else {
                            nameAndCount.put(answer, ++count);
                        }
                    }
                    // Print duplicate elements from array in Java
                    Set<Map.Entry<String, Integer>> entrySet = nameAndCount.entrySet();
                    for (Map.Entry<String, Integer> entry : entrySet) {
                        Output.display(entry.getKey() + ": " + entry.getValue());

                    }
                } else if (q instanceof Matching) {
                    // build hash table with count
                    for (ResponseCorrectAnswer answer : currentResponses) {
                        List<String> theAnswer = answer.getResponse();
                        Integer count = nameAndCount1.get(theAnswer);
                        if (count == null) {
                            nameAndCount1.put(theAnswer, 1);
                        } else {
                            nameAndCount1.put(theAnswer, ++count);
                        }
                    }
                    // Print duplicate elements from array in Java
                    Set<Map.Entry<List<String>, Integer>> entrySet = nameAndCount1.entrySet();
                    for (Map.Entry<List<String>, Integer> entry : entrySet) {
                        Output.display(String.valueOf(entry.getValue()));
                        Output.display(entry.getKey(), q);
                    }
                } else if (q instanceof Essay) {

                    for (int j = 0; j < currentResponses.size(); j++) {
                        for (String response : currentResponses.get(j).getResponse()) {
                            allQResponses.add(response);
                        }
                    }
                    for (String answer : allQResponses) {
                        Output.display(answer);
                    }
                }
            }

        }catch(Exception e){
            Output.display("There was no responses to load");
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

    public void storeResponses(ArrayList<ResponseCorrectAnswer> answers){
        int numResponses= FileUtils.getNumberOfResponses(basePath, getName());
        String connectedString= getName() + "-Response" + (numResponses+1);
        SerializationHelper.serialize(ArrayList.class, answers,basePath,connectedString);

    }
    public ArrayList<ArrayList<ResponseCorrectAnswer>> loadResponses(){
        ArrayList<ArrayList<ResponseCorrectAnswer>> responses = new ArrayList<>();
        ArrayList<String> responseFiles=FileUtils.getResponses(basePath,name);
        for(String f : responseFiles){
            responses.add(SerializationHelper.deserialize(ArrayList.class,f));
        }
        return responses;
    }
    public ArrayList<ResponseCorrectAnswer> loadResponse(){
        ArrayList<ResponseCorrectAnswer> userResponses=null;
        String response=FileUtils.listAndPickResponseFromDir(basePath,name);
        try{
            userResponses=SerializationHelper.deserialize(ArrayList.class,response);
            Output.display("File loaded Successfully");
        }
        catch(Exception e){
            Output.display("Couldn't find file");
        }
        return userResponses;
    }




}
