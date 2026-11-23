package Ejercicio5.Entidades;



import jakarta.persistence.*;


import java.io.Serializable;



@Entity

@Table(name="videojuego")


public class Videojuego implements Serializable {


    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String titulo;

    private int lanzamiento;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    @JoinColumn(name = "id_estudio")

    private Estudio estudio;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    @JoinColumn(name = "id_personaje")

    private Personaje personaje;

    public Videojuego() {
    }

    public Videojuego(int id, String titulo, int lanzamiento, Estudio estudio, Personaje personaje) {
        this.id = id;
        this.titulo = titulo;
        this.lanzamiento = lanzamiento;
        this.estudio = estudio;
        this.personaje = personaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(int lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }


    @Override
    public String toString() {
        return "Videojuego{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", lanzamiento=" + lanzamiento +
                ", personaje=" + personaje +
                '}';
    }
}
