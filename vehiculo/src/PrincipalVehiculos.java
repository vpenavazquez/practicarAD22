public class PrincipalVehiculos {
    public static void main(String[] args) {
        // 🏢 Creamos la empresa de alquiler
        EmpresaAlquiler empresa = new EmpresaAlquiler();

        // 🚚 Creamos una furgoneta y la añadimos a la flota
        Furgoneta furgo = new Furgoneta(1200, "1234-ABC");
        empresa.añadirVehiculo(furgo);

        // 🔄 Primer intento: El vehículo está disponible (true)
        try {
            System.out.println("Primer intento de alquiler...");
            empresa.alquilarVehiculo("1234-ABC");
        } catch (VehiculoNoDisponibleException e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }

        // 🔄 Segundo intento: El vehículo ya se alquiló (false), saltará al catch
        try {
            System.out.println("\nSegundo intento de alquiler del mismo vehículo...");
            empresa.alquilarVehiculo("1234-ABC");
        } catch (VehiculoNoDisponibleException e) {
            // 🎯 Capturamos la excepción y mostramos el mensaje sin romper el programa
            System.out.println("⚠️ Error capturado en el examen: " + e.getMessage());
        }

        System.out.println("\nEl programa termina de forma segura.");
    }
}