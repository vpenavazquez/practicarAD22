package Ejercicio3;

import Ejercicio1.Entidades.Estudio;
import Ejercicio1.HibernateUtil;
import Ejercicio3.DAO.AutorDAO;
import Ejercicio3.Entidades.Autor;
import Ejercicio3.Entidades.Libro;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {


    public static void main(String[] args){

        SessionFactory sf = HibernateUtil.getSessionFactory();
        AutorDAO autorDAO = new AutorDAO(sf);

        Autor a1 =  new Autor();

        a1.setNombre("Victor");
        a1.setNacionalidad("España");
        Libro l1 =  new Libro();
        Libro l2 = new  Libro();
        l1.setTitulo("jojos");
        l1.setPrecio(12.34);

        l2.setTitulo("aot");
        l2.setPrecio(30.45);
        l1.setAutor(a1);
        l2.setAutor(a1);

        a1.getLibros().add(l1);
        a1.getLibros().add(l2);
        autorDAO.insertar(a1);

        List<Autor> listaAutores = autorDAO.listarTodos();

        for (Autor e : listaAutores) {
            System.out.println(e);
        }

        autorDAO.toXML("biblioteca.xml");

        HibernateUtil.shutdown();


    }



}
