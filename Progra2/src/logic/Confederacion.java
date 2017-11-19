package logic;

import java.util.ArrayList;

public class Confederacion{
    private final String nombre;
    private final String codigo;
    private ArrayList<Equipo> equipos;
    public static final String CONCACAF = "Concacaf";
    public static final String UEFA = "UEFA";
    public static final String CONMEBOL = "Conmebol";
    public static final String CAF = "CAF";
    public static final String AFC = "AFC";
    public static final String OFC = "OFC";

    public Confederacion(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.equipos = new ArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }
    
    public void setEquipo(ArrayList<Equipo> equipos){
        this.equipos = equipos;
    }
    
    @Override
    public String toString(){
        return codigo;
    } 
}