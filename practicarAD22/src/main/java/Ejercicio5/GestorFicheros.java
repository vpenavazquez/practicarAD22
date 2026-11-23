package Ejercicio5;

import Ejercicio5.Entidades.Estudio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheros {

    public static List<String> readFicheiro(String rutaFicheiro){
        List<String> lista = new ArrayList<>();
        File file = new File(rutaFicheiro);
        if (file.exists()){

            try(BufferedReader br = new BufferedReader(new FileReader(file))){

                String linea;

                while ((linea = br.readLine()) != null){
                    lista.add(linea.trim());
                }


            } catch (IOException e) {
                System.out.println(e.getMessage());
            }



        }
        return lista;

    }

    public static void serializarEstudio(Estudio estudio, String rutaFicheiro){

        try(FileOutputStream fos = new FileOutputStream(rutaFicheiro);
            ObjectOutputStream oos = new ObjectOutputStream(fos) ){

            oos.writeObject(estudio);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

















}
