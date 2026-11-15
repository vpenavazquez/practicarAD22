package Ejercicio1;

import Ejercicio1.Entidades.Anime;
import Ejercicio1.Entidades.Estudio;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {

    public void prepararDirectorio(String ruta) {

        File directorio = new File(ruta);

        if (!directorio.exists()) {
            directorio.mkdir();
            System.out.println("directorio creado correctamente");
        } else {
            System.out.println("Directorio ya estaba creado");
        }

    }

    public void guardarEstudioBinario(List<Estudio> lista, String rutaArchivo) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {

            oos.writeObject(lista);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Estudio> leerEstudiosBinarios(String rutaArchivo) {

        List<Estudio> lista = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            lista = (List<Estudio>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    public void exportarEstudiosXML(List<Estudio> lista, String rutaArchivo) {
        XMLStreamWriter writer = null;

        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            writer = factory.createXMLStreamWriter(new FileWriter(rutaArchivo));

            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("estudios");

            for (Estudio est : lista) {
                writer.writeStartElement("estudio");
                writer.writeAttribute("id", String.valueOf(est.getId()));
                writer.writeStartElement("nome");
                writer.writeCharacters(est.getNome());
                writer.writeEndElement();
                writer.writeStartElement("fundacion");
                writer.writeCharacters(est.getFundacion().toString());
                writer.writeEndElement();
                writer.writeEndElement();

            }

            writer.writeEndElement();
            writer.writeEndDocument();

        } catch (XMLStreamException | IOException e) {

            System.out.println(e.getMessage());

        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Estudio> leerEstudiosXML(String rutaArchivo) {
        List<Estudio> lista = new ArrayList<>();
        Estudio estActual = null;
        XMLStreamReader reader = null;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            reader = factory.createXMLStreamReader(new FileReader(rutaArchivo));

            while (reader.hasNext()) {
                int evento = reader.next();

                if (evento == XMLStreamConstants.START_ELEMENT) {
                    String etiqueta = reader.getLocalName();

                    if (etiqueta.equals("estudio")) {
                        estActual = new Estudio();
                        estActual.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
                    } else if (etiqueta.equals("nome")) {
                        if (estActual != null) {
                            estActual.setNome(reader.getElementText());
                        }
                    } else if (etiqueta.equals("fundacion")) {
                        if (estActual != null) {
                            estActual.setFundacion(LocalDate.parse(reader.getElementText()));
                        }
                    }

                } else if (evento == XMLStreamConstants.END_ELEMENT) {
                    if (reader.getLocalName().equals("estudio") && estActual != null) {
                        lista.add(estActual);
                    }
                }
            }

        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }

        return lista;
    }

    public void guardarAnimesDatoS(List <Anime> lista, String rutaArchivo){
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(rutaArchivo))){
            for(Anime ani : lista){
                dos.writeInt(ani.getId());
                dos.writeUTF(ani.getTitulo());
                dos.writeInt(ani.getPuntuacion());
            }
        }catch (IOException e){

            System.out.println(e.getMessage());

        }
    }

    public List<Anime> leerAnimesDatos(String rutaArchivo){
        List<Anime> lista = new ArrayList<>();

        try(DataInputStream dis = new DataInputStream(new FileInputStream(rutaArchivo))){


            while(true){
                Anime ani = new Anime();
                ani.setId(dis.readInt());
                ani.setTitulo(dis.readUTF());
                ani.setPuntuacion(dis.readInt());
                lista.add( ani);
            }

        }catch (EOFException e){
            System.out.println(e.getMessage());

        }catch (IOException b){
            System.out.println(b.getMessage());
        }

        return lista;
    }
}