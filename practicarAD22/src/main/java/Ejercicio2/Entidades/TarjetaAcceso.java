package Ejercicio2.Entidades;

import jakarta.persistence.*;


@Entity
@Table
public class TarjetaAcceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String codigo;

    @OneToOne(mappedBy = "tarjeta")
    private Empleado empleado;

    public TarjetaAcceso() {
    }

    public TarjetaAcceso(int id, String codigo, Empleado empleado) {
        this.id = id;
        this.codigo = codigo;
        this.empleado = empleado;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }


    @Override
    public String toString() {
        return "TarjetaAcceso{" +
                "id=" + id +
                ", codigo='" + codigo + '}';
    }
}
