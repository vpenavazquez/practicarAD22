package Ejercicio5;

import Ejercicio1.HibernateUtil; // En el examen será config.HibernateConfig
import Ejercicio5.Entidades.Estudio;
import Ejercicio5.Entidades.Personaje;
import Ejercicio5.Entidades.Videojuego;
import Ejercicio5.Servicios.ServicioEstudio;
import Ejercicio5.Servicios.ServicioPersonaje;
import Ejercicio5.Servicios.ServicioVideojuego;
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
        ServicioVideojuego servicioVideojuego = new ServicioVideojuego(sf);
        ServicioEstudio servicioEstudio = new ServicioEstudio(sf);
        ServicioPersonaje servicioPersonaje = new ServicioPersonaje(sf);


        // =========================================================================
        // 2. CREACIÓN DE OBJETOS E INSERCIÓN EN BD
        // =========================================================================
        // 3 Estudios
        Estudio nintendo = new Estudio();
        nintendo.setNombre("Nintendo");
        nintendo.setPais("Japón");
        nintendo.setFundacion(1889);

        Estudio naughtyDog = new Estudio();
        naughtyDog.setNombre("Naughty Dog");
        naughtyDog.setPais("Estados Unidos");
        naughtyDog.setFundacion(1984);

        Estudio cdProjekt = new Estudio();
        cdProjekt.setNombre("CD Projekt Red");
        cdProjekt.setPais("Polonia");
        cdProjekt.setFundacion(1994);

        // 4 Personajes
        Personaje link = new Personaje();
        link.setNome("Link");
        link.setHabilidad("Espada Maestra y arco");

        Personaje mario = new Personaje();
        mario.setNome("Mario");
        mario.setHabilidad("Salto y potenciadores");

        Personaje ellie = new Personaje();
        ellie.setNome("Ellie");
        ellie.setHabilidad("Supervivencia y sigilo");

        Personaje geralt = new Personaje();
        geralt.setNome("Geralt de Rivia");
        geralt.setHabilidad("Alquimia y señales mágicas");

        // 5 Videojuegos enlazando ambos lados
        Videojuego zelda = new Videojuego();
        zelda.setTitulo("The Legend of Zelda: Breath of the Wild");
        zelda.setLanzamiento(2017);
        zelda.setEstudio(nintendo);
        zelda.setPersonaje(link);
        nintendo.getVideojuego().add(zelda);

        Videojuego marioOdyssey = new Videojuego();
        marioOdyssey.setTitulo("Super Mario Odyssey");
        marioOdyssey.setLanzamiento(2017);
        marioOdyssey.setEstudio(nintendo);
        marioOdyssey.setPersonaje(mario);
        nintendo.getVideojuego().add(marioOdyssey);

        Videojuego tlou1 = new Videojuego();
        tlou1.setTitulo("The Last of Us");
        tlou1.setLanzamiento(2013);
        tlou1.setEstudio(naughtyDog);
        tlou1.setPersonaje(ellie);
        naughtyDog.getVideojuego().add(tlou1);

        Videojuego tlou2 = new Videojuego();
        tlou2.setTitulo("The Last of Us Part II");
        tlou2.setLanzamiento(2020);
        tlou2.setEstudio(naughtyDog);
        tlou2.setPersonaje(ellie);
        naughtyDog.getVideojuego().add(tlou2);

        Videojuego witcher3 = new Videojuego();
        witcher3.setTitulo("The Witcher 3: Wild Hunt");
        witcher3.setLanzamiento(2015);
        witcher3.setEstudio(cdProjekt);
        witcher3.setPersonaje(geralt);
        cdProjekt.getVideojuego().add(witcher3);

        // Inserción de los 5 videojuegos en BD
        servicioVideojuego.insertar(zelda);
        servicioVideojuego.insertar(marioOdyssey);
        servicioVideojuego.insertar(tlou1);
        servicioVideojuego.insertar(tlou2);
        servicioVideojuego.insertar(witcher3);


        // =========================================================================
        // 3. PRIMER LISTADO AGRUPADO POR ESTUDIO
        // =========================================================================
        System.out.println("=== PRIMER LISTADO ===");
        List<Videojuego> listaJuegos = servicioVideojuego.listarTodos();
        imprimirVideojuegosPorEstudio(listaJuegos);


        // =========================================================================
        // 4. LECTURA DE "config_juegos.txt", ACTUALIZACIÓN Y SERIALIZACIÓN
        // =========================================================================
        List<String> config = GestorFicheros.readFicheiro("config_juegos.txt");

        if (config.size() >= 2) {
            String estudioBuscar = config.get(0);   // Primera línea: "Nintendo"
            String personajeBuscar = config.get(1); // Segunda línea: "Ellie"

            int idEstudioActualizar = -1;
            int idPersonajeActualizar = -1;

            for (Videojuego v : listaJuegos) {
                if (v.getEstudio() != null && v.getEstudio().getNombre().equalsIgnoreCase(estudioBuscar)) {
                    idEstudioActualizar = v.getEstudio().getId();
                }
                if (v.getPersonaje() != null && v.getPersonaje().getNome().equalsIgnoreCase(personajeBuscar)) {
                    idPersonajeActualizar = v.getPersonaje().getId();
                }
            }

            if (idEstudioActualizar != -1) {
                servicioEstudio.actualizarNombre(idEstudioActualizar, "Nintendo EPD");

                List<Videojuego> listaActualizada = servicioVideojuego.listarTodos();
                for (Videojuego v : listaActualizada) {
                    if (v.getEstudio() != null && v.getEstudio().getId() == idEstudioActualizar) {
                        GestorFicheros.serializarEstudio(v.getEstudio(), "estudio_actualizado.dat");
                        break;
                    }
                }
            }

            if (idPersonajeActualizar != -1) {
                servicioPersonaje.actualizarNombre(idPersonajeActualizar, "Ellie Williams");
            }
        }


        // =========================================================================
        // 5. SEGUNDO LISTADO, BORRADO DE TABLAS Y COMPROBACIÓN FINAL
        // =========================================================================
        System.out.println("\n=== SEGUNDO LISTADO (TRAS ACTUALIZAR) ===");
        listaJuegos = servicioVideojuego.listarTodos();
        imprimirVideojuegosPorEstudio(listaJuegos);

        servicioVideojuego.eliminarTodos();
        servicioEstudio.eliminarTodos();
        servicioPersonaje.eliminarTodos();

        System.out.println("\n=== COMPROBACIÓN FINAL ===");
        List<Videojuego> listaFinal = servicioVideojuego.listarTodos();
        if (listaFinal == null || listaFinal.isEmpty()) {
            System.out.println("La tabla de videojuegos está vacía. Borrado completado correctamente.");
        } else {
            System.out.println("Quedan " + listaFinal.size() + " videojuegos en la base de datos.");
        }

        HibernateUtil.shutdown();
    }

    private static void imprimirVideojuegosPorEstudio(List<Videojuego> listaJuegos) {
        if (listaJuegos == null) return;

        Set<Integer> estudiosImpresos = new HashSet<>();

        for (Videojuego v : listaJuegos) {
            Estudio estudio = v.getEstudio();
            if (estudio != null && estudiosImpresos.add(estudio.getId())) {
                System.out.println("Estudio: " + estudio.getNombre() + " (" + estudio.getPais() + ") - Fundado en: " + estudio.getFundacion());
                System.out.println("Videojuegos:");

                for (Videojuego juego : estudio.getVideojuego()) {
                    Personaje p = juego.getPersonaje();
                    String nombrePersonaje = (p != null) ? p.getNome() : "Sin personaje";
                    String habilidadPersonaje = (p != null) ? p.getHabilidad() : "N/A";
                    System.out.println("\t" + juego.getTitulo() + " (" + juego.getLanzamiento() + ") -> Personaje: " + nombrePersonaje + " | Habilidad: " + habilidadPersonaje);
                }
                System.out.println();
            }
        }
    }
}