public class Vehiculo {

   private final String matricula;
   private boolean disponible;

    public Vehiculo(String matricula){
        this.matricula = matricula;

        this.disponible = true;
    }

    public  String getMatricula(){
        return matricula;
    }

    public void alquilar() throws VehiculoNoDisponibleException {

        if (!this.disponible) {

            throw new VehiculoNoDisponibleException("El vehículo con matrícula " + matricula + " ya está alquilado.");
        }


        this.disponible = false;
    }
}

