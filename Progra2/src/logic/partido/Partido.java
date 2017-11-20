package logic.partido;

import java.sql.Date;
import java.util.ArrayList;
import logic.Equipo;
import logic.persona.Arbitro;

public class Partido{
    public static final String FASE_GRUPOS = "Fase de Grupos";
    public static final String OCTAVOS_FINAL= "Octavos de Final";
    public static final String CUARTOS_FINAL = "Cuartos de Final";
    public static final String SEMIFINALES = "Semifinales";
    public static final String TERCER_LUGAR = "Tercer Lugar";
    public static final String FINAL = "Final";
    public static final String EXTRA_SI = "SI";
    public static final String EXTRA_NO = "NO";

    private int numeroPartido;
    private Sede sede;
    private boolean extras;
    private int cantidadAficionados;
    private String etapa;
    private Date fecha_hora;
    private String reposicion1Tiempo;
    private String reposicion2Tiempo;
    private Alineacion alineacion1;
    private Alineacion alineacion2;
    private ArrayList<Accion> acciones;
    private ArrayList<Arbitro> arbitros;
    private ArrayList<Penal> penales;
    
    public Partido(int numeroPartido, Sede sede, boolean extras, int cantidadAficionados,
            String etapa, Date fecha, String reposicion1Tiempo, String reposicion2Tiempo){
        this.numeroPartido = numeroPartido;
        this.sede = sede;
        this.extras = extras;
        this.cantidadAficionados = cantidadAficionados;
        this.etapa = etapa;
        this.fecha_hora = fecha;
        this.reposicion1Tiempo = reposicion1Tiempo;
        this.reposicion2Tiempo = reposicion2Tiempo;
        this.alineacion1 = null;
        this.alineacion2 = null;
        this.acciones = null;
        this.arbitros = null;
        this.penales = null;
    }

    public int getNumeroPartido() {
        return numeroPartido;
    }

    public String getExtras(){
        String extra;
        if(extras)
            extra = EXTRA_SI;
        else
            extra =EXTRA_NO;
        return extra;
    }
    public Sede getSede() {
        return sede;
    }

    public boolean isExtras() {
        return extras;
    }

    public int getCantidadAficionados() {
        return cantidadAficionados;
    }

    public String getEtapa() {
        return etapa;
    }

    public Date getFecha() {
        return fecha_hora;
    }

    public String getReposicion1Tiempo() {
        return reposicion1Tiempo;
    }

    public String getReposicion2Tiempo() {
        return reposicion2Tiempo;
    }

    public void setAlineacion1(Alineacion alineacion1) {
        this.alineacion1 = alineacion1;
    }
    
    public Alineacion getAlineacion1() {
        return alineacion1;
    }

    public void setAlineacion2(Alineacion alineacion2) {
        this.alineacion2 = alineacion2;
    }
    
    public Alineacion getAlineacion2() {
        return alineacion2;
    }
    
    public ArrayList<Arbitro> getArbitros(){
        return arbitros;
    }
    
    public void setArbitros(ArrayList<Arbitro> arbitros){
        this.arbitros = arbitros;
    }
    
    public ArrayList<Accion> getAcciones() {
        return acciones;
    }
    
    public void setAcciones(ArrayList<Accion> acciones){
        this.acciones = acciones;
    }
    
    public ArrayList<Penal> getPenales() {
        return penales;
    }
    
    public void setPenales(ArrayList<Penal> penales){
        this.penales = penales;
    }
    
    public int getMarcador(Equipo equipo){
        int marcador = 0;
        for(Accion accion : acciones){
            if(accion.getTipoAccion().equals(Accion.GOL) &&
               accion.getEquipo() == equipo){
                    marcador++;
            }    
        }
        return marcador;
    }
    
    public String getMarcadores(){
        int marcadorEquipo1 = getMarcador(alineacion1.getEquipo());
        int marcadorEquipo2 = getMarcador(alineacion2.getEquipo());
        return Integer.toString(marcadorEquipo1) + " - " 
             + Integer.toString(marcadorEquipo2);
        
    }
    
    @Override
    public String toString(){
        return Integer.toString(numeroPartido);
    }
}