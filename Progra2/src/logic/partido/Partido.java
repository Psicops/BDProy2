package logic.partido;

import java.sql.Date;
import java.sql.Time;
import logic.Equipo;

public class Partido{
    public static final String FASE_GRUPOS = "Fase de Grupos";
    public static final String OCTAVOS_FINAL= "Octavos de Final";
    public static final String CUARTOS_FINAL = "Cuartos de Final";
    public static final String SEMIFINALES = "Semifinales";
    public static final String TERCER_LUGAR = "Tercer Lugar";
    public static final String FINAL = "Final";
    public static final String EXTRA_SI = "SI";
    public static final String EXTRA_NO = "NO";

    private final int numeroPartido;
    private final Sede sede;
    private final boolean extras;
    private final int cantidadAficionados;
    private final String etapa;
    private final Date fecha;
    private final Time hora;
    private final Time reposicion1Tiempo;
    private final Time reposicion2Tiempo;
    private final Equipo Equipo1;
    private final Equipo Equipo2;
    
    public Partido(int numeroPartido, Sede sede, boolean extras, int cantidadAficionados,
            String etapa, Date fecha, Time hora, Time reposicion1Tiempo, Time reposicion2Tiempo,
             Equipo Equipo1, Equipo Equipo2){
        this.numeroPartido = numeroPartido;
        this.sede = sede;
        this.extras = extras;
        this.cantidadAficionados = cantidadAficionados;
        this.etapa = etapa;
        this.fecha = fecha;
        this.hora = hora;
        this.reposicion1Tiempo = reposicion1Tiempo;
        this.reposicion2Tiempo = reposicion2Tiempo;
        this.Equipo1 = Equipo1;
        this.Equipo2 = Equipo2;
    }

    public int getNumeroPartido() {
        return numeroPartido;
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
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public Time getReposicion1Tiempo() {
        return reposicion1Tiempo;
    }

    public Time getReposicion2Tiempo() {
        return reposicion2Tiempo;
    }

    public Equipo getEquipo1() {
        return Equipo1;
    }

    public Equipo getEquipo2() {
        return Equipo2;
    }


    
    
    public int getMarcador(Equipo equipo){
        int marcador = 0;
        /*for(Accion accion : acciones){
            if(accion.getTipoAccion().equals(Accion.GOL) &&
               accion.getEquipo() == equipo){
                    marcador++;
            }    
        }*/
        return marcador;
    }
    /*
    public String getMarcadores(){
        int marcadorEquipo1 = getMarcador(alineacionEquipo1.getEquipo());
        int marcadorEquipo2 = getMarcador(alineacionEquipo2.getEquipo());
        return Integer.toString(marcadorEquipo1) + " - " 
             + Integer.toString(marcadorEquipo2);
        
    }*/
}