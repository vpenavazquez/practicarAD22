package Ejercicio5.Servicios;


import Ejercicio5.Entidades.Estudio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ServicioEstudio {
    private SessionFactory factory;

    public ServicioEstudio(SessionFactory factory) {
        this.factory = factory;
    }


    public void actualizarNombre(int id, String nuevoNombre){


        Session session = factory.openSession();

        Transaction tx = null;


        try{

            tx = session.beginTransaction();

            Estudio estudio = session.get(Estudio.class, id);


            if(estudio != null){

                estudio.setNombre(nuevoNombre);

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

            session.createQuery("DELETE FROM Estudio").executeUpdate();

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
