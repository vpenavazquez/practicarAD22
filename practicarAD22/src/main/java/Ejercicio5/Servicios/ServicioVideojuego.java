package Ejercicio5.Servicios;


import Ejercicio4.Entidades.Comic;
import Ejercicio5.Entidades.Videojuego;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ServicioVideojuego {

    private SessionFactory factory;

    public ServicioVideojuego(SessionFactory factory) {
        this.factory = factory;
    }

    public void insertar(Videojuego videojuego){


        Session session = factory.openSession();

        Transaction tx = null;


        try {

            tx = session.beginTransaction();

            session.persist(videojuego);

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

    public List<Videojuego> listarTodos() {

        Session session = factory.openSession();

        List<Videojuego> lista = null;


        try {

            lista = session.createQuery("FROM Videojuego", Videojuego.class).getResultList();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {

                session.close();

            }

        }


        return lista;

    }

    public void eliminarTodos(){


        Session session = factory.openSession();

        Transaction tx = null;


        try{

            tx = session.beginTransaction();

            session.createQuery("DELETE FROM Videojuego").executeUpdate();

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
