import java.io.File;
import java.util.ArrayList;

public class Test extends Survey implements java.io.Serializable{
    private static final long serialVersionUID = 49584392039483L;
    ArrayList<ResponseCorrectAnswer> correctAnswers;
    ArrayList<ResponseCorrectAnswer> currUserAnswers;
    double grade;
    double pointsEarned;


    Test(){
        correctAnswers = new ArrayList<ResponseCorrectAnswer>();
        currUserAnswers = new ArrayList<ResponseCorrectAnswer>();
        basePath = "Tests" + File.separator;
    }
    public void deleteQuestion(int question){
        correctAnswers.remove(questions.indexOf(question));
        questions.remove(question);
    }

    public void grade(){
        pointsEarned=0.0;
        int numEssays=0;
        double points = 100.0/questions.size();
        Question question;
        for(int i=0; i<questions.size(); i++){
            question=questions.get(i);
            if(!(question instanceof Essay) || (question instanceof ShortAnswer)){
                ResponseCorrectAnswer correctAns=correctAnswers.get(i);
                ResponseCorrectAnswer userAns=currUserAnswers.get(i);
                if(correctAns.compare(correctAns,userAns)){
                    pointsEarned+=points;
                }
            }
            else
                numEssays+=1;
        }
        grade=pointsEarned;
        if(numEssays>0){
            Output.display("You received an " + grade + " on the test. The test was worth 100 points, but only " + (100-(points*numEssays)) + " of those points could be auto graded because there was " + numEssays + " essay question(s)");
        }
        else{
            Output.display("You received an " + grade + " on the test. The test was worth 100 points.");
        }


    }
    protected void addMult(){
        MultipleChoice multQuestion = new MultipleChoice();
        setPrompt(multQuestion);
        multQuestion.setChoices();

        Output.display("This question has " + multQuestion.numAns + " Answers.");
        ResponseCorrectAnswer answers = new ResponseCorrectAnswer();
        for(int i=0; i<multQuestion.numAns; i++) {
            Output.display("What is the(an) answer to this question?");
            String resp = Input.compareInputToChoices(multQuestion.choices);
            answers.setResponse(resp);
        }
        addCorrectAnswer(answers);
        questions.add(multQuestion);
    }

    protected void addTF(){
        TrueFalse tFQuestion = new TrueFalse();
        setPrompt(tFQuestion);

        ResponseCorrectAnswer answers = new ResponseCorrectAnswer();
        Output.display("What is the answer to this question?");
        String resp = Input.compareInputToChoices(tFQuestion.choices);
        resp=resp.toLowerCase();
        answers.setResponse(resp);
        addCorrectAnswer(answers);

        questions.add(tFQuestion);
    }

    protected void addEssay(){
        Essay essayQuestion = new Essay();
        setPrompt(essayQuestion);
        essayQuestion.setChoices();

        Output.display("This question has " + essayQuestion.numAns + " Answers.");
        ResponseCorrectAnswer answers = new ResponseCorrectAnswer();
        for(int i=0; i<essayQuestion.numAns; i++) {
            Output.display("What is the(an) answer to this question?");
            String resp = Input.getStringResp();
            answers.setResponse(resp);
        }
        addCorrectAnswer(answers);

        questions.add(essayQuestion);
    }

    protected void addShortAns(){
        ShortAnswer sAnsQuestion = new ShortAnswer();
        setPrompt(sAnsQuestion);
        Output.display("Please enter the character limit for this question");
        sAnsQuestion.setLimit(Input.getIntRespFloor(0));
        sAnsQuestion.setChoices();

        Output.display("This question has " + sAnsQuestion.numAns + " Answers.");
        ResponseCorrectAnswer answers = new ResponseCorrectAnswer();
        for(int i=0; i<sAnsQuestion.numAns; i++) {
            Output.display("What is the(an) answer to this question?");
            String resp = Input.getStringLim(sAnsQuestion.getLimit());
            answers.setResponse(resp);
        }
        addCorrectAnswer(answers);
        questions.add(sAnsQuestion);
    }

    protected void addMatching(){
        Matching matchQuestion = new Matching();
        setPrompt(matchQuestion);
        matchQuestion.setChoices();
        Output.display("This question has " + matchQuestion.numAns + " Answers.");
        matchQuestion.display();
        ResponseCorrectAnswer answers = new ResponseCorrectAnswer();
        for(int i=0; i<matchQuestion.numAns; i++) {
            Output.display("What is the answer to " + ((Matching) matchQuestion).colOne[i] + "?" );
            String resp = Input.compareInputToChoices(((Matching) matchQuestion).colTwo);
            answers.setResponse(resp);
        }
        addCorrectAnswer(answers);
        questions.add(matchQuestion);
    }

    protected void addValidDate(){
        ValidDate validDateQuestion = new ValidDate();
        setPrompt(validDateQuestion);
        validDateQuestion.setChoices();

        Output.display("This question has " + validDateQuestion.numAns + " Answers.");
        ResponseCorrectAnswer answers = new ResponseCorrectAnswer();
        for(int i=0; i<validDateQuestion.numAns; i++) {
            Output.display("What is the(an) answer to this question? use format yyyy-mm-dd");
            String resp = Input.getValidDateFormat();
            answers.setResponse(resp);
        }
        addCorrectAnswer(answers);

        questions.add(validDateQuestion);
    }

    public ResponseCorrectAnswer getCorrectAnswer(Question question){
        int pos=questions.indexOf(question);
        return correctAnswers.get(pos);
    }

    public void addCorrectAnswer(ResponseCorrectAnswer response){
        correctAnswers.add(response);
    }
    public void display(boolean withAnswers){
        //The Problem is that part C specifically states that Survey, question cannot store correctAnswers, so it's better for me to deal with answers outside of questions, leading to a less personalized display.
        for (Question question : questions) {
            question.display();
            if(withAnswers){
                Output.display("Correct Responses: ");
                ResponseCorrectAnswer correctAns=getCorrectAnswer(question);
                    if (!(question instanceof Matching)) {
                        for (String response : correctAns.response) {
                            if (correctAns.response.size() > 1) {
                                Output.display("A correct response is: " + response);
                            } else
                                Output.display("The correct response is: " + response);
                        }
                    }
                    else{
                       for(int i=0; i< ((Matching) question).colOne.length; i++){
                           Output.display((i + ") " + ((Matching) question).colOne[i]) + correctAns.response.get(i));
                       }
                    }
            }
        }
    }
    public void take(){
        super.take();
    }

    public void modify(){
        //Yes this modify is quite complex. No I don't regret making it this way.
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
                    //While loop to edit the answer(s) to the question
                    while(true) {
                        Output.display("Would you like to edit the answer(s) to this question?");
                        String resp1 = Input.getYesNo();

                        if (resp1.equals("yes")) {
                            ResponseCorrectAnswer currResponse = correctAnswers.get(qNum - 1);
                            Output.display("Select the number of the answer you'd like to edit");
                            //Display the answers
                            for (int i = 0; i < currResponse.getResponse().size(); i++) {
                                Output.display((i + 1) + ") " + currResponse.response.get(i));
                            }

                            int respNum = Input.getIntRespCeiling(currResponse.getResponse().size() + 1);
                            Output.display("Write the new answer");
                            String newAns = Input.getStringResp();
                            currResponse.response.set((respNum - 1), newAns);

                        }
                        else{
                            break;
                        }
                    }
                }
            }
        }

    }
}
