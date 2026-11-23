package Ejercicio5.Servicios;

import Ejercicio5.Entidades.Estudio;
import Ejercicio5.Entidades.Personaje;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ServicioPersonaje {

    private SessionFactory factory;


    public ServicioPersonaje(SessionFactory factory) {
        this.factory = factory;
    }


    public void actualizarNombre(int id, String nuevoNombre){


        Session session = factory.openSession();

        Transaction tx = null;


        try{

            tx = session.beginTransaction();

            Personaje personaje = session.get(Personaje.class, id);


            if(personaje != null){

                personaje.setNome(nuevoNombre);

            }

            tx.commit();

        } catch (Exception e) {

            if (tx != null) {

                tx.rollback();

            }

            e.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {

                session.close();

            }

        }


    }

    public void eliminarTodos(){


        Session session = factory.openSession();

        Transaction tx = null;


        try{

            tx = session.beginTransaction();

            session.createQuery("DELETE FROM Personaje").executeUpdate();

            tx.commit();

        } catch (Exception e) {

            if (tx != null) {

                tx.rollback();

            }

            e.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {

                session.close();

            }

        }

    }




}
