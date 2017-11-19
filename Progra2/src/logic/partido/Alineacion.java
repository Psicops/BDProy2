package logic.partido;

import logic.persona.Jugador;
import java.util.ArrayList;
import logic.Equipo;
import logic.persona.Asistente;

public class Alineacion{
    private final ArrayList<Jugador> titulares;
    private final ArrayList<Jugador> suplentes;
    private final ArrayList<Asistente> tecnicos;
    private final ArrayList<Cambio> cambios;
    private final Jugador capitan;
    private final Equipo equipo;
    public static final String ENTRENADOR = "Entrenador";
    public static final String ASIST_TECNICO = "Asistente Técnico";
    public static final String MEDICO = "Médico";
    public static final String DELEGADO = "Delegado";

    public Alineacion(Equipo equipo, Jugador capitan) {
        this.equipo = equipo;
        this.capitan = capitan;
        this.titulares = new ArrayList();
        this.suplentes = new ArrayList();
        this.tecnicos = new ArrayList();
        this.cambios = new ArrayList();
    }

    public ArrayList<Jugador> getTitulares() {
        return titulares;
    }

    public ArrayList<Jugador> getSuplentes() {
        return suplentes;
    }

    public ArrayList<Asistente> getTecnicos() {
        return tecnicos;
    }
    
    public ArrayList<Cambio> getCambios(){
        return cambios;
    }

    public Equipo getEquipo() {
        return equipo;
    }
}