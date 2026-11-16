package Ejercicio3.Entidades;


import jakarta.persistence.*;

@Entity
@Table(name="libro")





public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private double precio;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;


    public Libro() {
    }

    public Libro(int id, String titulo, double precio, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.autor = autor;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "precio=" + precio +
                ", titulo='" + titulo + '\'' +
                ", id=" + id +
                '}';
    }
}
