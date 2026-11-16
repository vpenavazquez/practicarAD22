package Ejercicio2.DAO;

import Ejercicio1.Entidades.Estudio;
import Ejercicio2.Entidades.Empleado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EmpleadoDAO {

    private SessionFactory factory;

    public EmpleadoDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void insertar(Empleado empleado){

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(empleado);
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

    public List<Empleado> listarTodos(){

        Session session = factory.openSession();
        List<Empleado> lista = null;

        try {
            lista = session.createQuery("FROM Empleado", Empleado.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return lista;
    }

}

