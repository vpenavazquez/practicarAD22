package Ejercicio2;

import Ejercicio1.GestorArchivos;
import Ejercicio1.HibernateUtil;
import Ejercicio2.DAO.EmpleadoDAO;
import Ejercicio2.Entidades.Empleado;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {


    public static void main(String[] args){


        SessionFactory sf = HibernateUtil.getSessionFactory();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO(sf);
        GestorFicheros gestorFicheros = new GestorFicheros();



        List<Empleado> listaEmpleado =  gestorFicheros.leerEmpleadosDesdeTexto("empleados.txt");

        for(Empleado e : listaEmpleado){

            empleadoDAO.insertar(e);

        }

        List<Empleado> listarTodosEmpleados =   empleadoDAO.listarTodos();
        gestorFicheros.generarResumenAccesos(listarTodosEmpleados, "resumen_accesos.txt");
        for (Empleado e : listarTodosEmpleados){
            System.out.println(e);
        }

        HibernateUtil.shutdown();

    }
}
