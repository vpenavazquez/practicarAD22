package Ejercicio3;

import Ejercicio3.Entidades.Autor;
import Ejercicio3.Entidades.Libro;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheros {

    public List<Autor> leerBibliotecaXML(String rutaArchivo) {
        List<Autor> listaAutores = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(rutaArchivo));

            NodeList nodosAutores = doc.getElementsByTagName("autor");

            for (int i = 0; i < nodosAutores.getLength(); i++) {
                Node nodoAutor = nodosAutores.item(i);

                if (nodoAutor.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoAutor = (Element) nodoAutor;

                    Autor autor = new Autor();
                    autor.setNombre(elementoAutor.getAttribute("nombre"));
                    autor.setNacionalidad(elementoAutor.getAttribute("nacionalidad"));

                    NodeList nodosLibros = elementoAutor.getElementsByTagName("libro");

                    for (int j = 0; j < nodosLibros.getLength(); j++) {
                        Node nodoLibro = nodosLibros.item(j);

                        if (nodoLibro.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementoLibro = (Element) nodoLibro;

                            Libro libro = new Libro();
                            libro.setTitulo(elementoLibro.getAttribute("titulo"));
                            libro.setPrecio(Double.parseDouble(elementoLibro.getAttribute("precio")));

                            libro.setAutor(autor);
                            autor.getLibros().add(libro);
                        }
                    }

                    listaAutores.add(autor);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return listaAutores;
    }

    public void generarInformeBiblioteca(List<Autor> lista, String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {

            for (Autor autor : lista) {
                bw.write("Autor: " + autor.getNombre() + " (" + autor.getNacionalidad() + ")");
                bw.newLine();

                for (Libro libro : autor.getLibros()) {
                    bw.write("\t- " + libro.getTitulo() + " - " + libro.getPrecio() + " €");
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}