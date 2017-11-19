package logic.partido;

public class Sede{
    int capacidad;
    String nombreEstadio;
    String nombreSede;

    public Sede(int capacidad, String nombreEstadio, String nombreSede) {
        this.capacidad = capacidad;
        this.nombreEstadio = nombreEstadio;
        this.nombreSede = nombreSede;
    }
    
    public int getCapacidad() {
        return capacidad;
    }

    public String getNombreEstadio() {
        return nombreEstadio;
    }

    public String getNombreSede() {
        return nombreSede;
    }
}