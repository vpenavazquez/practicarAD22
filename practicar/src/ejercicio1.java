import java.util.Scanner;

public class ejercicio1 {
public static void main(String args[]){

    try (Scanner scanner = new Scanner(System.in)){

        System.out.println("dime un nombre o ruta del archivo que quieres crear");

        String input = scanner.nextLine().trim();
        String editor = "notepad";

       ProcessBuilder processBuilder = new ProcessBuilder(editor,input);
       processBuilder.start();

    }catch (Exception e){
        System.out.println(e.getMessage());
    }


}

}
