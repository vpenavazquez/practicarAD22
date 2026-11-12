package Ejercicio1;

import java.io.File;


public class GestorArchivos {

    public void prepararDirectorio(String ruta){

        File directorio = new File( ruta);



        if (!directorio.exists()){
            directorio.mkdir();
            System.out.println("directorio creado correctamente");
        }else{
            System.out.println("Directorio ya estaba creado");
        }



    }



}

