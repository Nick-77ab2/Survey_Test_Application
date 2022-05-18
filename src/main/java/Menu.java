import java.io.File;
import java.io.IOException;

public class Menu {
    String[]mainMenu={"Create Survey", "Modify Survey", "Take Survey", "Display Survey", "Load Survey", "Store Survey", "Quit"};
    String[]addMenu={"add a Multiple Choice", "add a True False", "add a Essay", "add a Short Answer", "add a Matching", "add a Valid Date", "Quit"};
    String basePath = "Surveys" + File.separator;

    public void loadMenu() throws IOException {
        Survey survey = new Survey();
        int choice;
        boolean mainLoop=true;

        while (mainLoop) {
            Output.showMenu(mainMenu);
            choice=Input.getIntRespCeiling(mainMenu.length+1)-1;
            switch (choice) {
                case 0:
                    survey = questionMenu(survey);
                    break;
                case 1:
                    if(survey.getQuestions()==null){
                        Output.display("You must have a survey loaded, or have questions in it in order to modify it.");
                    }
                    else{
                        survey.modify();
                    }
                    break;
                case 2:
                    survey = loadSurvey();
                    survey.take();
                    break;
                case 3:
                    if(survey.getQuestions()==null){
                        Output.display("You must have a survey loaded, or have questions in it in order to display it.");
                    }
                    else{
                        survey.display();
                    }
                    break;
                case 4:
                    survey = loadSurvey();
                    break;
                case 5:
                    saveSurvey(survey);
                    break;
                case 6:
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
                case 6: return survey;

                default: return survey;

            }
        }
        return survey;
    }

    public void saveSurvey(Survey survey){
        String name;
        String resp;
        String userData;
        if(survey.getName()==null) {
            Output.display("Enter a name for the survey:");
            name = Input.getStringResp();
            survey.setName(name);
        }
        else{
            name = survey.getName();
        }
        Output.display("Was this a response to an existing survey? (yes/no)");
        resp = Input.getYesNo();
        if(resp.equals("yes")){
            Output.display("Please Give your name and response number i.e. Shawn1");
            userData = Input.getStringResp();
            name+="_" + userData;
        }
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
}
