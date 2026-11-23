package Ejercicio4.Servicios;



import Ejercicio4.Entidades.Autor;

import org.hibernate.Session;

import org.hibernate.SessionFactory;

import org.hibernate.Transaction;


public class ServicioAutor {


    private SessionFactory factory;


    public ServicioAutor(SessionFactory factory) {

        this.factory = factory;

    }


    public void actualizarNombre(int id, String nuevoNombre, String nuevoApellido){


        Session session = factory.openSession();

        Transaction tx = null;


        try{

            tx = session.beginTransaction();

            Autor autor = session.get(Autor.class, id);


            if(autor != null){



                autor.setNombre(nuevoNombre);

                autor.setApellido(nuevoApellido);

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

            session.createQuery("DELETE FROM Autor").executeUpdate();

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