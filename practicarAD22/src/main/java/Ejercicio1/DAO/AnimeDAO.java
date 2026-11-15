package Ejercicio1.DAO;

import Ejercicio1.Entidades.Anime;
import Ejercicio1.Entidades.Estudio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AnimeDAO {

    private SessionFactory factory;


    public AnimeDAO(SessionFactory factory) {
        this.factory = factory;
    }


    public void insertar(Anime anime){

        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.persist(anime);
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

    public List<Anime> listartodos(){
        Session session = factory.openSession();
        List<Anime> lista = null;

        try{

            lista = session.createQuery("FROM Anime", Anime.class).getResultList();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return lista;

    }

    public void actualizarPorObjeto(int id, String nuevoTitulo, int nuevaPuntuacion, Estudio nuevoEstudio){
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Anime anime = session.get(Anime.class, id);

            if (anime !=null){
                anime.setTitulo(nuevoTitulo);
                anime.setPuntuacion(nuevaPuntuacion);
                anime.setEstudio(nuevoEstudio);
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
            session.createQuery("DELETE FROM Anime").executeUpdate();
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
