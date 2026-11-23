package Ejercicio4.Servicios;



import Ejercicio4.Entidades.Comic;

import org.hibernate.Session;

import org.hibernate.SessionFactory;

import org.hibernate.Transaction;


import java.util.List;


public class ServicioComic {


    private SessionFactory factory;


    public ServicioComic(SessionFactory factory) {

        this.factory = factory;

    }


    public void insertar(Comic comic){


        Session session = factory.openSession();

        Transaction tx = null;


        try {

            tx = session.beginTransaction();

            session.persist(comic);

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


    public List<Comic> listarTodos() {

        Session session = factory.openSession();

        List<Comic> lista = null;


        try {

            lista = session.createQuery("FROM Comic", Comic.class).getResultList();

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

            session.createQuery("DELETE FROM Comic").executeUpdate();

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