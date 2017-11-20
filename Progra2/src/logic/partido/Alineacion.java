package logic.partido;

import logic.persona.Jugador;
import java.util.ArrayList;
import logic.Equipo;
import logic.persona.Asistente;

public class Alineacion{
    private ArrayList<Jugador> titulares;
    private ArrayList<Jugador> suplentes;
    private ArrayList<Asistente> tecnicos;
    private ArrayList<Cambio> cambios;
    private final Jugador capitan;
    private final Equipo equipo;
    
    public Alineacion(Equipo equipo, Jugador capitan) {
        this.equipo = equipo;
        this.capitan = capitan;
        this.titulares = new ArrayList();
        this.suplentes = new ArrayList();
        this.tecnicos = new ArrayList();
        this.cambios = new ArrayList();
    }
    
    public Equipo getEquipo() {
        return equipo;
    }
    
    public Jugador getCapitan(){
        return capitan;
    }

    public ArrayList<Jugador> getTitulares() {
        return titulares;
    }
    
    public void setTitulares(ArrayList<Jugador> titulares){
        this.titulares = titulares;
    }

    public ArrayList<Jugador> getSuplentes() {
        return suplentes;
    }
    
    public void setSuplentes(ArrayList<Jugador> suplentes){
        this.suplentes = suplentes;
    }

    public ArrayList<Asistente> getTecnicos() {
        return tecnicos;
    }
    
    public void setTecnicos(ArrayList<Asistente> tecnicos){
        this.tecnicos = tecnicos;
    }
    
    public ArrayList<Cambio> getCambios(){
        return cambios;
    }
    
    public void setCambios(ArrayList<Cambio> cambios){
        this.cambios = cambios;
    }
}