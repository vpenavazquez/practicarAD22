package Ejercicio4;

import Ejercicio1.HibernateUtil; // Nota: en el examen será config.HibernateConfig
import Ejercicio4.Entidades.Autor;
import Ejercicio4.Entidades.Comic;
import Ejercicio4.Entidades.Protagonista;
import Ejercicio4.Servicios.ServicioAutor;
import Ejercicio4.Servicios.ServicioComic;
import Ejercicio4.Servicios.ServicioProtagonista;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        // =========================================================================
        // 1. INICIALIZACIÓN DE HIBERNATE Y SERVICIOS
        // =========================================================================
        SessionFactory sf = HibernateUtil.getSessionFactory();
        ServicioComic servicioComic = new ServicioComic(sf);
        ServicioAutor servicioAutor = new ServicioAutor(sf);
        ServicioProtagonista servicioProtagonista = new ServicioProtagonista(sf);

        // =========================================================================
        // 2. CREACIÓN DE OBJETOS E INSERCIÓN EN BD
        // =========================================================================
        // 4 Autores con constructor vacío
        Autor a1 = new Autor();
        a1.setNombre("Stan");
        a1.setApellido("Lee");
        a1.setNacionalidad("Estadounidense");

        Autor a2 = new Autor();
        a2.setNombre("Alan");
        a2.setApellido("Moore");
        a2.setNacionalidad("Británico");

        Autor a3 = new Autor();
        a3.setNombre("Frank");
        a3.setApellido("Miller");
        a3.setNacionalidad("Estadounidense");

        Autor a4 = new Autor();
        a4.setNombre("Hergé");
        a4.setApellido("Remi");
        a4.setNacionalidad("Belga");

        // 6 Protagonistas con constructor vacío
        Protagonista p1 = new Protagonista();
        p1.setNome("Spider-Man");
        p1.setPoder("Sentido arácnido y trepamuros");

        Protagonista p2 = new Protagonista();
        p2.setNome("Batman");
        p2.setPoder("Inteligencia y artes marciales");

        Protagonista p3 = new Protagonista();
        p3.setNome("Superman");
        p3.setPoder("Vuelo y superfuerza");

        Protagonista p4 = new Protagonista();
        p4.setNome("Wonder Woman");
        p4.setPoder("Superfuerza y lazo de la verdad");

        Protagonista p5 = new Protagonista();
        p5.setNome("Tintín");
        p5.setPoder("Periodismo e investigación");

        Protagonista p6 = new Protagonista();
        p6.setNome("Daredevil");
        p6.setPoder("Sentidos superdesarrollados");

        // 8 Cómics enlazando ambos lados de la relación
        Comic c1 = new Comic();
        c1.setTitulo("The Amazing Spider-Man");
        c1.setAno(1962);
        c1.setAutor(a1);
        c1.setProtagonista(p1);
        a1.getComic().add(c1);

        Comic c2 = new Comic();
        c2.setTitulo("Daredevil: El Hombre sin Miedo");
        c2.setAno(1964);
        c2.setAutor(a1);
        c2.setProtagonista(p6);
        a1.getComic().add(c2);

        Comic c3 = new Comic();
        c3.setTitulo("Batman: La Broma Asesina");
        c3.setAno(1988);
        c3.setAutor(a2);
        c3.setProtagonista(p2);
        a2.getComic().add(c3);

        Comic c4 = new Comic();
        c4.setTitulo("Superman: ¿Qué fue del Hombre del Mañana?");
        c4.setAno(1986);
        c4.setAutor(a2);
        c4.setProtagonista(p3);
        a2.getComic().add(c4);

        Comic c5 = new Comic();
        c5.setTitulo("Batman: El Regreso del Caballero Oscuro");
        c5.setAno(1986);
        c5.setAutor(a3);
        c5.setProtagonista(p2);
        a3.getComic().add(c5);

        Comic c6 = new Comic();
        c6.setTitulo("Daredevil: Born Again");
        c6.setAno(1986);
        c6.setAutor(a3);
        c6.setProtagonista(p6);
        a3.getComic().add(c6);

        Comic c7 = new Comic();
        c7.setTitulo("Las aventuras de Tintín: El Loto Azul");
        c7.setAno(1936);
        c7.setAutor(a4);
        c7.setProtagonista(p5);
        a4.getComic().add(c7);

        Comic c8 = new Comic();
        c8.setTitulo("Wonder Woman: Espíritu de la Verdad");
        c8.setAno(2001);
        c8.setAutor(a2);
        c8.setProtagonista(p4);
        a2.getComic().add(c8);

        // Guardar los 8 cómics en BD
        servicioComic.insertar(c1);
        servicioComic.insertar(c2);
        servicioComic.insertar(c3);
        servicioComic.insertar(c4);
        servicioComic.insertar(c5);
        servicioComic.insertar(c6);
        servicioComic.insertar(c7);
        servicioComic.insertar(c8);

        // =========================================================================
        // 3. PRIMER LISTADO CON EL FORMATO DEL EXAMEN
        // =========================================================================
        System.out.println("=== PRIMER LISTADO ===");
        List<Comic> listaComics = servicioComic.listarTodos();
        imprimirComicsPorAutor(listaComics);

        // =========================================================================
        // 4. LECTURA DEL FICHERO "datos", ACTUALIZACIÓN Y SERIALIZACIÓN
        // =========================================================================
        List<String> datosFichero = GestorFicheros.readFicheiro("datos");

        if (datosFichero.size() >= 2) {
            String nombreAutorBuscar = datosFichero.get(0); // Primera línea: "Stan Lee"
            String nombreProtaBuscar = datosFichero.get(1); // Segunda línea: "Spider-man"

            int idAutorActualizar = -1;
            int idProtaActualizar = -1;

            for (Comic c : listaComics) {
                String nombreCompletoAutor = c.getAutor().getNombre() + " " + c.getAutor().getApellido();
                if (nombreCompletoAutor.equalsIgnoreCase(nombreAutorBuscar)) {
                    idAutorActualizar = c.getAutor().getId();
                }

                if (c.getProtagonista().getNome().equalsIgnoreCase(nombreProtaBuscar)) {
                    idProtaActualizar = c.getProtagonista().getId();
                }
            }

            if (idAutorActualizar != -1) {
                servicioAutor.actualizarNombre(idAutorActualizar, "Satan", "Leee");

                // Como actualizarNombre es void en ServicioAutor, recuperamos el Autor actualizado
                Autor autorActualizado = null;
                for (Comic c : servicioComic.listarTodos()) {
                    if (c.getAutor().getId() == idAutorActualizar) {
                        autorActualizado = c.getAutor();
                        break;
                    }
                }

                if (autorActualizado != null) {
                    GestorFicheros.serializarAutor(autorActualizado, "autor.dat");
                }
            }

            if (idProtaActualizar != -1) {
                servicioProtagonista.actualizarNombre(idProtaActualizar, "Spooder-Mon");
            }
        }

        // =========================================================================
        // 5. SEGUNDO LISTADO, BORRADO DE TABLAS Y COMPROBACIÓN FINAL
        // =========================================================================
        System.out.println("\n=== SEGUNDO LISTADO (TRAS ACTUALIZAR) ===");
        listaComics = servicioComic.listarTodos();
        imprimirComicsPorAutor(listaComics);

        // Borrar el contenido de TODAS las tablas respetando el orden de las claves foráneas
        servicioComic.eliminarTodos();
        servicioAutor.eliminarTodos();
        servicioProtagonista.eliminarTodos();

        // Comprobación final
        System.out.println("\n=== COMPROBACIÓN FINAL ===");
        listaComics = servicioComic.listarTodos();
        if (listaComics == null || listaComics.isEmpty()) {
            System.out.println("La lista de cómics está vacía. Tablas borradas correctamente.");
        } else {
            System.out.println("Quedan " + listaComics.size() + " cómics en la base de datos.");
        }

        HibernateUtil.shutdown();
    }

    private static void imprimirComicsPorAutor(List<Comic> listaComics) {
        if (listaComics == null) return;

        Set<Integer> autoresImpresos = new HashSet<>();

        for (Comic c : listaComics) {
            Autor autor = c.getAutor();
            if (autor != null && autoresImpresos.add(autor.getId())) {
                System.out.println("Autor:");
                System.out.println(autor.getNombre() + " " + autor.getApellido());
                System.out.println("Comics:");
                for (Comic comicAutor : autor.getComic()) {
                    System.out.println(comicAutor.getTitulo() + " (" + comicAutor.getAno() + ")");
                    System.out.println("Protagonista: " + comicAutor.getProtagonista().getNome()
                            + " poder: " + comicAutor.getProtagonista().getPoder());
                }
                System.out.println();
            }
        }
    }
}