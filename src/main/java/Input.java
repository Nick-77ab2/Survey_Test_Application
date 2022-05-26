import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Input {
    public static String getStringResp(){
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        return input;
    }

    public static int getIntRespCeiling(int limit) {
        int inputInteger=0;
        boolean isNum=  false;
        Scanner reader = new Scanner(System.in);

        while(!isNum) {
            try {
                inputInteger = reader.nextInt();
                if(inputInteger < limit && inputInteger > 0){
                    isNum=true;
                }
                else{
                    Output.display("Please enter a number in range");
                    reader.nextLine();
                }
            } catch (InputMismatchException e) {
                Output.display("Please enter an int");
                reader.nextLine();

            }
        }
        reader.nextLine();
        return inputInteger;
    }
    public static int getIntRespFloor(int floor) {
        int inputInteger=0;
        boolean isNum=  false;
        Scanner reader = new Scanner(System.in);

        while(!isNum) {
            try {
                inputInteger = reader.nextInt();
                if(inputInteger > floor){
                    isNum=true;
                }
                else{
                    Output.display("Please enter a number greater than 0");
                    reader.nextLine();
                }
            } catch (InputMismatchException e) {
                Output.display("Please enter an int");
                reader.nextLine();

            }
        }
        reader.nextLine();
        return inputInteger;
    }
    public static String getYesNo() {
        String input = null;
        Scanner reader = new Scanner(System.in);
        while (input == null) {
            input = reader.nextLine();
            input = input.toLowerCase();
            if(!(input.equals("yes") || input.equals("no"))){
                input=null;
                Output.display("Please only type yes or no.");
            }
        }
        return input;
    }

    public static String getStringLim(int limit) {
        boolean notLimit = true;
        String input = "";
        Scanner scan = new Scanner(System.in);
        while (notLimit) {

            input = scan.nextLine();

            if (input.length() > limit) {

                Output.display("Your answer was " + input.length() + " characters long, please enter an answer that's less than or equal to " + limit + " characters.");

            } else {
                notLimit = false;
            }
        }

        return input;
    }

    /**
     * THE BELOW IS BORROWED FOR USE WITH FILEUTILS CREDIT GOES TO SEAN GRIMES
     */
    public static int readIntInRange(int start, int end){
        String failSpeech = "Please enter a valid number between " + start + " - " + end;
        // BufferedReader is better than Scanner. Prove me wrong.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "z";  // Some non-integer value to fail parsing if checks are missed
        try{
            line = br.readLine();
            while(
                    line == null
                            || line.length() <= 0
                            || !Validation.isIntBetweenInclusive(start, end, line)
            ){
                System.out.println(failSpeech);
                line = br.readLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        //noinspection ConstantConditions
        return Integer.parseInt(line);
    }
    public static String compareInputToChoices(String[] choices){
        String input = null;
        Scanner reader = new Scanner(System.in);
        while (input == null) {
            input = reader.nextLine();
            String input1 = input.toLowerCase();
            for(int i=0; i<choices.length; i++) {
                String choice = choices[i].toLowerCase();
                if (input1.equals(choice)) {
                    break;
                }
                if (i == choices.length - 1 && !input1.equals(choice)) {
                    input = null;
                    Output.display("Please only type an answer that is there.");
                }
            }
        }
        return input;
    }

    public static String getValidDateFormat(){
        String input = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        sdf.setLenient(false);
        Scanner reader = new Scanner(System.in);
        while (input == null) {
            input = reader.nextLine();
            try{
                sdf.parse(input);
            } catch (ParseException e) {
                input=null;
                Output.display("Please input a date in the format yyyy-mm-dd");
            }
        }
        return input;
    }
}

