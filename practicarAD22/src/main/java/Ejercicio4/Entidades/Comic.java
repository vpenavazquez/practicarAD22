package Ejercicio4.Entidades;



import Ejercicio4.Entidades.Protagonista;

import jakarta.persistence.*;


import java.io.Serializable;



@Entity

@Table(name="comic")


public class Comic implements Serializable {


    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String titulo;

    private int ano;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    @JoinColumn(name = "id_autor")

    private Autor autor;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    @JoinColumn(name = "id_protagonista")

    private Protagonista protagonista;





    public Comic() {

    }


    public Comic(int id, String titulo, int ano, Autor autor, Protagonista protagonista) {

        this.id = id;

        this.titulo = titulo;

        this.ano = ano;

        this.autor = autor;

        this.protagonista = protagonista;

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


    public int getAno() {

        return ano;

    }


    public void setAno(int ano) {

        this.ano = ano;

    }


    public Autor getAutor() {

        return autor;

    }


    public void setAutor(Autor autor) {

        this.autor = autor;

    }


    public Protagonista getProtagonista() {

        return protagonista;

    }


    public void setProtagonista(Protagonista protagonista) {

        this.protagonista = protagonista;

    }


    @Override

    public String toString() {

        return "Comic{" +

                "id=" + id +

                ", titulo='" + titulo + '\'' +

                ", ano=" + ano +

                ", protagonista=" + protagonista +

                '}';

    }

} 