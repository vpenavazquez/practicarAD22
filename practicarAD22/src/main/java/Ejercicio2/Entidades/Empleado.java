package Ejercicio2.Entidades;
import jakarta.persistence.*;

@Entity
@Table
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String departamento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tarjeta_id")
    private TarjetaAcceso tarjeta;


    public Empleado() {

    }

    public Empleado(int id, String nombre, String departamento, TarjetaAcceso tarjeta) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.tarjeta = tarjeta;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public TarjetaAcceso getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaAcceso tarjeta) {
        this.tarjeta = tarjeta;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", departamento='" + departamento + '\'' +
                ", tarjeta=" + tarjeta +
                '}';
    }
}
