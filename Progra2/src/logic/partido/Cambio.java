package logic.partido;

import logic.persona.Jugador;
import logic.Equipo;

public class Cambio{
    private final Equipo equipo;
    private final Jugador entrante;
    private final Jugador saliente;
    private final String tiempo;

    public Cambio(Equipo equipo, Jugador entrante, Jugador saliente, String tiempo) {
        this.equipo = equipo;
        this.entrante = entrante;
        this.saliente = saliente;
        this.tiempo = tiempo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Jugador getEntrante() {
        return entrante;
    }

    public Jugador getSaliente() {
        return saliente;
    }

    public String getTiempo() {
        return tiempo;
    }
    
    @Override
    public String toString(){
        return saliente.getApellido1() + " -> " 
               + entrante.getApellido1() + " (" +tiempo+")";
    }
}