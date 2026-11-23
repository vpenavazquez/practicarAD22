package Ejercicio4.Servicios;



import Ejercicio4.Entidades.Protagonista;

import org.hibernate.Session;

import org.hibernate.SessionFactory;

import org.hibernate.Transaction;


public class ServicioProtagonista {

    private SessionFactory factory;


    public ServicioProtagonista(SessionFactory factory) {

        this.factory = factory;

    }


    public void actualizarNombre(int id, String nuevoNombre){


        Session session = factory.openSession();

        Transaction tx = null;


        try{

            tx = session.beginTransaction();

            Protagonista protagonista= session.get(Protagonista.class, id);


            if(protagonista != null){



                protagonista.setNome(nuevoNombre);


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

            session.createQuery("DELETE FROM Protagonista").executeUpdate();

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