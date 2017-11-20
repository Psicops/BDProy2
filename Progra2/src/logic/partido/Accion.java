package logic.partido;

import logic.persona.Jugador;
import logic.Equipo;

public class Accion{
    public static final String TARJETA_ROJA = "Tarjeta Roja";
    public static final String TARJETA_AMARILLA = "Tarjeta Amarilla";
    public static final String GOL = "Gol";
    
    private final Equipo equipo;
    private final String tipoAccion;
    private final String tiempo;
    private final Jugador jugador;

    public Accion(Equipo equipo, String tipoAccion, String tiempo, Jugador jugador) {
        this.equipo = equipo;
        this.tipoAccion = tipoAccion;
        this.tiempo = tiempo;
        this.jugador = jugador;
    }
    
    public Equipo getEquipo() {
        return equipo;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public String getTiempo() {
        return tiempo;
    }

    public Jugador getJugador() {
        return jugador;
    }
    
    @Override
    public String toString(){
        return tipoAccion + ": " + jugador.getNombre() + " " +
               jugador.getApellido1() + " ("+tiempo+")";
    }
}