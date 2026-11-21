


import Ejercicio4.Entidades.Protagonista;
import jakarta.persistence.*;

import javax.persistence.JoinColumn;

@Entity
@Table(name="protagonista")





public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String poder;

    public Comic() {
    }


    public Comic(int id, String nome, String poder) {
        this.id = id;
        this.nome = nome;
        this.poder = poder;
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
        return "Comic{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", poder='" + poder + '\'' +
                '}';
    }
}