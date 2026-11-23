package Ejercicio5.Entidades;
import jakarta.persistence.*;


import java.io.Serializable;



@Entity

@Table(name="personaje")






public class Personaje implements Serializable {


    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String nome;

    private String habilidad;

    public Personaje() {
    }

    public Personaje(int id, String nome, String habilidad) {
        this.id = id;
        this.nome = nome;
        this.habilidad = habilidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", habilidad='" + habilidad + '\'' +
                '}';
    }
}