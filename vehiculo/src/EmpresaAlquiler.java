import java.util.ArrayList;

public class EmpresaAlquiler  {

    private ArrayList<Vehiculo>listVehiculos;

    public EmpresaAlquiler(){


        this.listVehiculos = new ArrayList<>();

    }

    public void alquilarVehiculo(String matriculaBusqueda) throws VehiculoNoDisponibleException {


        for (Vehiculo v : listVehiculos) {


            if (v.getMatricula().equals(matriculaBusqueda)) {
                v.alquilar();
                System.out.println("¡Vehículo alquilado con éxito!");
                return;
            }
        }
        System.out.println("❌ No se encontró ningún vehículo con esa matrícula.");
    }



}
