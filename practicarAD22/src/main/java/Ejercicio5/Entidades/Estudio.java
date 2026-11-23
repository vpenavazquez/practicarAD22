package Ejercicio5.Entidades;



import jakarta.persistence.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "estudio")

public class Estudio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String nombre;
    private String pais;
    private int fundacion;

    @OneToMany(mappedBy = "estudio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Videojuego> videojuego = new ArrayList<>();

    public Estudio() {
    }

    public Estudio(int id, String nombre, String pais, int fundacion, List<Videojuego> videojuego) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fundacion = fundacion;
        this.videojuego = videojuego;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Videojuego> getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(List<Videojuego> videojuego) {
        this.videojuego = videojuego;
    }

    @Override
    public String toString() {
        return "Estudio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", fundacion=" + fundacion +
                ", videojuego=" + videojuego +
                '}';
    }
}
