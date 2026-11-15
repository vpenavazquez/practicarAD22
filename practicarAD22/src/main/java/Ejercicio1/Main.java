package Ejercicio1;

import Ejercicio1.DAO.AnimeDAO;
import Ejercicio1.DAO.EstudioDAO;
import Ejercicio1.Entidades.Anime;
import Ejercicio1.Entidades.Estudio;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.getSessionFactory();

        EstudioDAO estudioDAO = new EstudioDAO(sf);
        AnimeDAO animeDAO = new AnimeDAO(sf);
        GestorArchivos gestorArchivos = new GestorArchivos();

        gestorArchivos.prepararDirectorio("archivos");

        Estudio est = new Estudio();
        est.setNome("atack on titan");
        est.setFundacion(LocalDate.of(1987, 2, 12));

        Estudio est1 = new Estudio();
        est1.setNome("jojolands");
        est1.setFundacion(LocalDate.of(2021, 8, 12));

        estudioDAO.insertar(est);
        estudioDAO.insertar(est1);

        List<Estudio> listaEstudios = estudioDAO.listartodos();

        for (Estudio e : listaEstudios) {
            System.out.println(e);
        }

        Anime ani1 = new Anime("JOJOS", 100, est);
        Anime ani2 = new Anime("SBR", 100, est1);

        animeDAO.insertar(ani1);
        animeDAO.insertar(ani2);

        List<Anime> listarAnimes = animeDAO.listartodos();

        for (Anime a : listarAnimes) {
            System.out.println(a);
        }

        List<Estudio> buscarAnterioresA = estudioDAO.buscarAnterioresA(LocalDate.of(2000, 1, 1));

        for (Estudio e : buscarAnterioresA) {
            System.out.println(e);
        }

        gestorArchivos.guardarEstudioBinario(listaEstudios, "archivos/estudios.dat");
        List<Estudio> estudiosBin = gestorArchivos.leerEstudiosBinarios("archivos/estudios.dat");
        for (Estudio e : estudiosBin) {
            System.out.println(e);
        }

        gestorArchivos.exportarEstudiosXML(listaEstudios, "archivos/estudios.xml");
        List<Estudio> estudiosXml = gestorArchivos.leerEstudiosXML("archivos/estudios.xml");
        for (Estudio e : estudiosXml) {
            System.out.println(e);
        }

        gestorArchivos.guardarAnimesDatoS(listarAnimes, "archivos/animes.dat");
        List<Anime> animesDatos = gestorArchivos.leerAnimesDatos("archivos/animes.dat");
        for (Anime a : animesDatos) {
            System.out.println(a);
        }

        HibernateUtil.shutdown();
    }
}