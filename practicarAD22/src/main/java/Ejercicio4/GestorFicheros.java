package Ejercicio4;

import Ejercicio4.Entidades.Autor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheros {

    public static List <String> readFicheiro(String rutaFicheiro){
        List<String> lista = new ArrayList<>();

        File file = new File(rutaFicheiro);

        if (file.exists()){

            try(BufferedReader br = new BufferedReader(new FileReader(file))){

                String linea;

                while ((linea = br.readLine()) != null){
                    lista.add(linea.trim());
                }

            }catch (IOException e){
                System.out.println(e.getMessage());
            }


        }else{
            System.out.println("El fichero no existe");
        }

        return lista;



    }

    public static void serializarAutor(Autor autor, String rutaFicheiro){

        try(FileOutputStream fos = new FileOutputStream(rutaFicheiro);
           ObjectOutputStream oos = new ObjectOutputStream(fos) ){

            oos.writeObject(autor);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }





}
