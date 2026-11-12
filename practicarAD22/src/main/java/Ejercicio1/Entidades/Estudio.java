package Ejercicio1.Entidades;


import java.io.Serializable;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity

@Table(name = "estudio")



public class Estudio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private LocalDate fundacion;

    @Transient
    private transient int tempContador;

    public Estudio(){

    }

    public Estudio(String nome, LocalDate fundacion, int tempContador) {
        this.nome = nome;
        this.fundacion = fundacion;
        this.tempContador = tempContador;
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

    public LocalDate getFundacion() {
        return fundacion;
    }

    public void setFundacion(LocalDate fundacion) {
        this.fundacion = fundacion;
    }

    public int getTempContador() {
        return tempContador;
    }

    public void setTempContador(int tempContador) {
        this.tempContador = tempContador;
    }

    @Override
    public String toString() {
        return "Estudio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", fundacion=" + fundacion +
                ", tempContador=" + tempContador +
                '}';
    }
}