import java.io.IOException;

public class Output {
    String input;

    public static void showMenu(String[] menu){
        System.out.println("Choose the number for the option you want");
        for(int i=0; i< menu.length; i++) {
            System.out.println((i+1) + ".) " + menu[i]);
        }
    }
    public static void display(String text){
        System.out.println(text);
    }
    public static void display(String[] text){
        for(int i =0;i<text.length;i++){
            display((i+1) + ") " + text[i]);
        }
    }
}
