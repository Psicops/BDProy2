package logic;

import logic.persona.Asistente;
import logic.persona.Jugador;
import java.util.ArrayList;
import logic.persona.Federativo;

public class Equipo{
    private final String codigoPais;
    private final String nombrePais;
    private final String Grupo;
    private Asistente entrenador;
    private final ArrayList<Jugador> jugadores;
    private final ArrayList<Asistente> asistentes;
    private final ArrayList<Federativo> federativos;
    
    private final int puntos;
    private final int partidosJugados;
    private final int partidosGanados;
    private final int partidosEmpatados;
    private final int partidosPerdidos;
    private final int golesFavor;
    private final int golesContra;
    private final int golesDiferencia;

    public Equipo(String codigoPais, String nombrePais, String Grupo, ArrayList<Jugador> jugadores,
                  ArrayList<Asistente> asistentes, ArrayList<Federativo> federativos){
        this.codigoPais = codigoPais;
        this.nombrePais = nombrePais;
        this.Grupo = Grupo;
        this.entrenador = null;
        this.jugadores = jugadores;
        this.asistentes = asistentes;
        this.federativos = federativos;
        this.puntos = 0;
        this.partidosJugados = 0;
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
        this.golesDiferencia = 0;
    }
    
    public String getCodigoPais() {
        return codigoPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public String getGrupo() {
        return Grupo;
    }
    
    public void setEntrenador(Asistente entrenador) throws Exception{
        if(entrenador.getPuesto().equals(Asistente.ENTRENADOR))
            this.entrenador = entrenador;
        else
            throw new Exception("El entrenador designado para el equipo "+
                                codigoPais+" es un "+entrenador.getPuesto()+".");
    }
    
    public Asistente getEntrenador(){
        return entrenador;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
    public ArrayList<Federativo> getFederativos(){
        return federativos;
    }

    public ArrayList<Asistente> getAsistentes() {
        return asistentes;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public int getGolesDiferencia() {
        return golesDiferencia;
    }
    
    @Override
    public String toString(){
        return nombrePais+" ("+codigoPais+")";
    }
}