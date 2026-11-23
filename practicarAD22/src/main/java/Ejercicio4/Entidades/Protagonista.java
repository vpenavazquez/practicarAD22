package Ejercicio4.Entidades;





import jakarta.persistence.*;


import java.io.Serializable;



@Entity

@Table(name="protagonista")






public class Protagonista implements Serializable {


    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String nome;

    private String poder;


    public Protagonista() {

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


    public String getPoder() {

        return poder;

    }


    public void setPoder(String poder) {

        this.poder = poder;

    }


    @Override

    public String toString() {

        return "Protagonista{" +

                "id=" + id +

                ", nome='" + nome + '\'' +

                ", poder='" + poder + '\'' +

                '}';

    }

} 