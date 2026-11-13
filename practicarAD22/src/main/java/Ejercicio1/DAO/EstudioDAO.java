package Ejercicio1.DAO;

import Ejercicio1.Entidades.Estudio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstudioDAO {

    private SessionFactory factory;

    public EstudioDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void insertar(Estudio estudio) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(estudio);
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

    public List<Estudio> listartodos() {
        Session session = factory.openSession();
        List<Estudio> lista = null;

        try {
            lista = session.createQuery("FROM Estudio", Estudio.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return lista;
    }

    public void actualizarPorObjeto(int id, String nuevoNome, LocalDate nuevaFundacion) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Estudio estudio = session.get(Estudio.class, id);

            if (estudio != null) {
                estudio.setNome(nuevoNome);
                estudio.setFundacion(nuevaFundacion);
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

    public void actualizarPorQuery(int id, String nuevoNome, LocalDate nuevaFundacion) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.createQuery("UPDATE Estudio SET nome = :nome, fundacion = :fundacion WHERE id = :id")
                    .setParameter("id", id)
                    .setParameter("nome", nuevoNome)
                    .setParameter("fundacion", nuevaFundacion)
                    .executeUpdate();
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

    public void eliminarTodos() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
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

    public List<Estudio> buscarAnterioresA(LocalDate fechaLimite) {
        List<Estudio> lista = new ArrayList<>();
        String url = "jdbc:postgresql://10.0.9.226:5432/probas";
        String user = "postgres";
        String password = "admin";

        String sql = "SELECT * FROM estudio WHERE fundacion < ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setDate(1, Date.valueOf(fechaLimite));

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Estudio est = new Estudio();
                    est.setId(rs.getInt("id"));
                    est.setNome(rs.getString("nome"));
                    est.setFundacion(rs.getDate("fundacion").toLocalDate());
                    lista.add(est);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }
}