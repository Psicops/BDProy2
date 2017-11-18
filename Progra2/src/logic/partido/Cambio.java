package logic.partido;

import logic.persona.Jugador;
import java.sql.Time;
import logic.Equipo;

public class Cambio{
    private final Equipo equipo;
    private final Jugador entrante;
    private final Jugador saliente;
    private final Time tiempo;

    public Cambio(Equipo equipo, Jugador entrante, Jugador saliente, Time tiempo) {
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

    public Time getTiempo() {
        return tiempo;
    }
}