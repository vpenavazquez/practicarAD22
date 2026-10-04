import java.util.Locale;
import java.io.File;

public class Ejercicio2 {
    public static void main(String args[]){

        String os = System.getProperty("os.name");
        String comando;

        if (os.toLowerCase().contains("windows")){
            comando = "dir";

        }else{
            comando = "ls";

        }

        System.out.println("Sistema detectado " + os + " Usaremos el comando " + comando);

        ProcessBuilder processBuilder = new ProcessBuilder(comando);

        String directorio = System.getProperty("user.dir");
        System.out.println(directorio +  " | " + processBuilder.directory());

        String home = System.getProperty("user.home");
        System.setProperty("user.dir",home);

        System.out.println("Propiedad cambiada " + System.getProperty("user.dir") + " | " + processBuilder.directory() );

        processBuilder.directory(new File("C:/Windows/Temp"));

        System.out.println("Propiedad cambiada " + System.getProperty("user.dir") + " | " + processBuilder.directory() );

        try {
            processBuilder.start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }






    }

}
