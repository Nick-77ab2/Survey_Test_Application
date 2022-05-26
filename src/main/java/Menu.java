import java.io.File;

public class Menu {
    String[] loadMenu={"Survey", "Test"};
    String[]surveyMenu={"Create Survey", "Modify Survey", "Take Survey", "Display Survey", "Load Survey", "Store Survey", "Tabulate Survey", "Quit"};
    String[] testMenu={"Create Test", "Display Existing Test Without Answers", "Display Existing Test With Answers", "Load Existing Test", "Save Current Test", "Take Current Test", "Modify Current Test","Tabulate Current Test", "Grade Current Test", "Quit"};
    String[]addMenu={"add a Multiple Choice", "add a True False", "add a Essay", "add a Short Answer", "add a Matching", "add a Valid Date", "Quit"};
    String basePath = "Surveys" + File.separator;
    String baseTestPath = "Tests" + File.separator;

    public void loadMenu(){
        int choice;
        Output.showMenu(loadMenu);

        choice=Input.getIntRespCeiling(loadMenu.length+1)-1;

        switch(choice){
            case 0: surveyMenu();
                break;
            case 1: testMenu();
                break;
            default: break;
        }
    }

    public void testMenu() {
        Test test = null;
        int choice;
        boolean surveyLoop=true;

        while (surveyLoop) {
            Output.showMenu(testMenu);
            choice=Input.getIntRespCeiling(testMenu.length+1)-1;
            switch (choice) {
                case 0:
                    test = new Test();
                    test = questionMenu(test);
                    break;
                case 1:
                    try{
                        test.display(false);
                    }
                    catch(Exception e){
                        Output.display("You must have a test loaded, or have questions in it in order to display it.");
                    }
                    break;
                case 2:
                    try{
                        test.display(true);
                    }
                    catch(Exception e){
                        Output.display("You must have a survey loaded, or have questions in it in order to display it.");
                    }
                    break;
                case 3:
                    test = loadTest();
                    break;
                case 4:
                    try{
                        saveTest(test);
                    }
                    catch(Exception e){
                        Output.display("You must have a test loaded in order to save it.");
                    }
                    break;
                case 5:
                    try{
                        test.take();
                    }
                    catch(Exception e){
                        Output.display("You must have a test loaded in order to take it. Exception: " + e);
                    }
                    break;
                case 6:
                    try{
                        test.modify();
                    }
                    catch(Exception e){
                        Output.display("There is no test to modify. Exception: " + e);
                    }
                    break;
                case 7:
                    try{
                        test.tabulate();
                    }
                    catch(Exception e) {
                        Output.display("You must have a test loaded in order to tabulate it.");
                    }
                    break;
                case 8:
                    if(test.getQuestions()==null){
                        Output.display("You must have a test loaded in order to grade it.");
                    }
                    else {
                        test=loadTest();
                        test.currUserAnswers=test.loadResponse();
                        test.grade();
                    }
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    break;

            }
        }
    }

    public void surveyMenu() {
        Survey survey = null;
        int choice;
        boolean surveyLoop=true;

        while (surveyLoop) {
            Output.showMenu(surveyMenu);
            choice=Input.getIntRespCeiling(surveyMenu.length+1)-1;
            switch (choice) {
                case 0:
                    survey = new Survey();
                    survey = questionMenu(survey);
                    break;
                case 1:
                    try{
                        survey.modify();
                    }
                    catch(Exception e){
                        Output.display("There is no survey to modify. Exception: " + e);
                    }
                    break;
                case 2:
                    try{
                        survey.take();
                    }
                    catch(Exception e){
                        Output.display("You must have a survey loaded in order to take it. Exception: " + e);
                    }
                    break;
                case 3:
                    try{
                        survey.display();
                    }
                    catch(Exception e){
                        Output.display("You must have a survey loaded, or have questions in it in order to display it.");
                    }
                    break;
                case 4:
                    survey = loadSurvey();
                    break;
                case 5:
                    try{
                        saveSurvey(survey);
                    }
                    catch(Exception e){
                        Output.display("You must have a survey loaded in order to save it.");
                    }
                    break;
                case 6:
                    if(survey.getQuestions()==null){
                        Output.display("You must have a survey loaded in order to tabulate it.");
                    }
                    else {
                        survey.tabulate();
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    break;

            }
        }

    }
    public Survey questionMenu(Survey survey){
        int choice;
        boolean questionLoop=true;

        while (questionLoop) {
            Output.showMenu(addMenu);
            choice=Input.getIntRespCeiling(addMenu.length+1)-1;
            switch (choice) {
                case 0:	survey.addMult();
                    break;
                case 1: survey.addTF();
                    break;
                case 2: survey.addEssay();
                    break;
                case 3: survey.addShortAns();
                    break;
                case 4: survey.addMatching();
                    break;
                case 5: survey.addValidDate();
                    break;
                case 6:
                    String name;
                    if(survey.getName()==null) {
                        Output.display("Enter a name for the survey:");
                        name = Input.getStringResp();
                        survey.setName(name);
                    }
                    Output.display("The Survey will automatically be stored.");
                    Output.display("Storing survey");
                    try {
                        saveSurvey(survey);
                    }catch(Exception e){
                        Output.display("Failed to save survey. " + e);
                    }

                    return survey;


                default: return survey;

            }
        }
        return survey;
    }
    public Test questionMenu(Test test){
        int choice;
        boolean questionLoop=true;

        while (questionLoop) {
            Output.showMenu(addMenu);
            choice=Input.getIntRespCeiling(addMenu.length+1)-1;
            switch (choice) {
                case 0:	test.addMult();
                    break;
                case 1: test.addTF();
                    break;
                case 2: test.addEssay();
                    break;
                case 3: test.addShortAns();
                    break;
                case 4: test.addMatching();
                    break;
                case 5: test.addValidDate();
                    break;
                case 6:
                    String name;
                    if(test.getName()==null) {
                        Output.display("Enter a name for the test:");
                        name = Input.getStringResp();
                        test.setName(name);
                    }
                    Output.display("The Survey will automatically be stored.");
                    Output.display("Storing survey");
                    try {
                        saveTest(test);
                    }catch(Exception e){
                        Output.display("Failed to save test. " + e);
                    }

                    return test;


                default: return test;

            }
        }
        return test;
    }

    public void saveSurvey(Survey survey){
        String name;
        name = survey.getName();
        /*
        Output.display("Was this a response to an existing survey? (yes/no)");
        resp = Input.getYesNo();
        if(resp.equals("yes")){
            Output.display("Please Give your name and response number i.e. Shawn1");
            userData = Input.getStringResp();
            name+="_" + userData;
        }

         */
        SerializationHelper.serialize(Survey.class, survey,basePath,name);
        Output.display("Survey Stored Successfully");

    }

    public Survey loadSurvey(){
        Survey theSurvey=null;
        Output.display("Choose from the list the Survey to load");
        String selectedSurvey = FileUtils.listAndPickFileFromDir(basePath);
        try{
            theSurvey=SerializationHelper.deserialize(Survey.class,selectedSurvey);
            Output.display("File loaded Successfully");
        }
        catch(Exception e){
            Output.display("Couldn't find file");
        }
        return theSurvey;
    }

    public void saveTest(Test test){
        String name;
        name = test.getName();
        SerializationHelper.serialize(Test.class, test,baseTestPath,name);
        Output.display("Test Stored Successfully");

    }

    public Test loadTest(){
        Test theTest=null;
        Output.display("Choose from the list the Test to load");
        String selectedSurvey = FileUtils.listAndPickFileFromDir(baseTestPath);
        try{
            theTest=SerializationHelper.deserialize(Test.class,selectedSurvey);
            Output.display("File loaded Successfully");
        }
        catch(Exception e){
            Output.display("Couldn't find file");
        }
        return theTest;
    }
}
