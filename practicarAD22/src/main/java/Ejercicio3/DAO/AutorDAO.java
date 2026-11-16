package Ejercicio3.DAO;

import Ejercicio1.Entidades.Estudio;
import Ejercicio3.Entidades.Autor;
import Ejercicio3.Entidades.Libro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    private SessionFactory factory;

    public AutorDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void insertar(Autor autor) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(autor);
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

    public List<Autor> listarTodos() {
        Session session = factory.openSession();
        List<Autor> lista = null;

        try {
            lista = session.createQuery("FROM Autor", Autor.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return lista;
    }

    public void toXML(String nombreFichero) {
        List<Autor> lista = listarTodos();

        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay autores para exportar a XML.");
            return;
        }

        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        try {
            FileWriter fw = new FileWriter(nombreFichero);
            XMLStreamWriter xsw = xof.createXMLStreamWriter(fw);

            xsw.writeStartDocument("UTF-8", "1.0");
            xsw.writeCharacters("\n");

            xsw.writeStartElement("biblioteca");
            xsw.writeCharacters("\n");

            for (Autor a : lista) {
                xsw.writeCharacters("  ");
                xsw.writeStartElement("autor");
                xsw.writeCharacters("\n");

                xsw.writeCharacters("    ");
                xsw.writeStartElement("id");
                xsw.writeCharacters(String.valueOf(a.getId()));
                xsw.writeEndElement();
                xsw.writeCharacters("\n");

                xsw.writeCharacters("    ");
                xsw.writeStartElement("nombre");
                xsw.writeCharacters(a.getNombre());
                xsw.writeEndElement();
                xsw.writeCharacters("\n");

                xsw.writeCharacters("    ");
                xsw.writeStartElement("nacionalidad");
                xsw.writeCharacters(a.getNacionalidad());
                xsw.writeEndElement();
                xsw.writeCharacters("\n");

                xsw.writeCharacters("    ");
                xsw.writeStartElement("libros");
                xsw.writeCharacters("\n");

                for (Libro l : a.getLibros()) {
                    xsw.writeCharacters("      ");
                    xsw.writeStartElement("libro");
                    xsw.writeCharacters("\n");

                    xsw.writeCharacters("        ");
                    xsw.writeStartElement("id");
                    xsw.writeCharacters(String.valueOf(l.getId()));
                    xsw.writeEndElement();
                    xsw.writeCharacters("\n");

                    xsw.writeCharacters("        ");
                    xsw.writeStartElement("titulo");
                    xsw.writeCharacters(l.getTitulo());
                    xsw.writeEndElement();
                    xsw.writeCharacters("\n");

                    xsw.writeCharacters("        ");
                    xsw.writeStartElement("precio");
                    xsw.writeCharacters(String.valueOf(l.getPrecio()));
                    xsw.writeEndElement();
                    xsw.writeCharacters("\n");

                    xsw.writeCharacters("      ");
                    xsw.writeEndElement();
                    xsw.writeCharacters("\n");
                }

                xsw.writeCharacters("    ");
                xsw.writeEndElement();
                xsw.writeCharacters("\n");

                xsw.writeCharacters("  ");
                xsw.writeEndElement();
                xsw.writeCharacters("\n");
            }

            xsw.writeEndElement();
            xsw.writeCharacters("\n");
            xsw.writeEndDocument();

            xsw.flush();
            xsw.close();
            fw.close();

            System.out.println("Fichero XML generado con éxito: " + nombreFichero);

        } catch (Exception e) {
            System.err.println("Error al escribir el fichero XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}