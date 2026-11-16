package Ejercicio2;

import Ejercicio2.Entidades.Empleado;
import Ejercicio2.Entidades.TarjetaAcceso;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheros {

    public List<Empleado> leerEmpleadosDesdeTexto(String rutaArchivo){

        List<Empleado> lista = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))){
            String linea;
            while ((linea = br.readLine()) != null){
                String[] datos = linea.split(";");
                TarjetaAcceso tarjeta = new TarjetaAcceso();
                tarjeta.setCodigo(datos[2]);
                Empleado empleado = new Empleado();
                empleado.setNombre(datos[0]);
                empleado.setDepartamento(datos[1]);
                empleado.setTarjeta(tarjeta);
                tarjeta.setEmpleado(empleado);
                lista.add(empleado);


            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public void generarResumenAccesos(List<Empleado> lista, String rutaArchivo){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))){

            for(Empleado emp : lista){
               bw.write("Empleado: " + emp.getNombre() + " | Dept: " + emp.getDepartamento() + " | Tarjeta: " + emp.getTarjeta().getCodigo());
               bw.newLine();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

}
