import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);

        while (true){

            System.out.print("Introduce un comando (o 'salir'): ");
            String entrada = scanner.nextLine().trim();

            if(entrada.equalsIgnoreCase("salir")){
                System.out.println("Saliendo del programa...");
                break;
            }

            System.out.println("Has introducido: " + entrada);


            String partes[] = entrada.split("\\s+");




            ProcessBuilder processBuilder = new ProcessBuilder(partes);

           try{
               Process proceso = processBuilder.start();
               int codigoSalida = proceso.waitFor();
               System.out.println("Programa: " + partes[0] + " | Código de salida: " + codigoSalida);



           }catch (Exception e){
               System.out.println(e.getMessage());
           }



        }

    }
}
