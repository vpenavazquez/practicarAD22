package Ejercicio1.Entidades;



import jakarta.persistence.*;



@Entity

@Table(name = "anime")



public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;

    private int puntuacion;

    @ManyToOne
    @JoinColumn(name = "estudio_id")
    private Estudio estudio;

    public Anime(){

    }

    public Anime(String titulo, int puntuacion, Estudio estudio) {
        this.titulo = titulo;
        this.puntuacion = puntuacion;
        this.estudio = estudio;
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

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", puntuacion=" + puntuacion +
                ", estudio=" + estudio +
                '}';
    }
}
