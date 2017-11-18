package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import static logic.Equipo.getEquipo;
import logic.partido.Partido;
import logic.persona.Arbitro;
import logic.persona.Asistente;
import logic.persona.Federativo;
import logic.persona.Jugador;

public class Logic {

    public static String GRUPO_A = "A";
    public static String GRUPO_B = "B";
    public static String GRUPO_C = "C";
    public static String GRUPO_D = "D";
    public static String GRUPO_E = "E";
    public static String GRUPO_F = "F";
    public static String GRUPO_G = "G";
    public static String GRUPO_H = "H";
    public static boolean EX;

    private static Logic instance;

    private final PDFPrinter pdfPrinter;

    private Logic() throws SQLException{
        this.pdfPrinter = new PDFPrinter();
    }

    public static Logic getInstance() throws SQLException{
        if(instance == null)
            instance = new Logic();
        return instance;
    }
    
    public PDFPrinter getPDFPrinter(){
        return pdfPrinter;
    }

    public ArrayList<Equipo> getEquipos(String codigoConf) throws SQLException{
        ArrayList<Equipo> teams = new ArrayList();
        ResultSet rs = Conexion.getRS("select codigoPais from equipo where codigoConfederacion = '" + codigoConf + "'");
        while(rs.next()){
            teams.add(getEquipo(rs.getString("codigoPais")));
        }
        return teams;
    }
    
    public ArrayList<Confederacion> getConfederaciones() throws SQLException{
        ArrayList<Confederacion> confed = new ArrayList();
        ResultSet rs = Conexion.getRS("SELECT * FROM CONFEDERACION");
        while(rs.next()){
            confed.add(new Confederacion(rs.getString("NOMBRE"),rs.getString("CODIGO")));
        }
        for(Confederacion e : confed){
            e.setEquipo(getEquipos(e.getCodigo()));
        }
        return confed;
    }
    
    //Retorna la información de una Confederacion
    public Confederacion getConfederacion(String conf) throws SQLException{
        Confederacion confed = null;
        ResultSet rs = Conexion.getRS("SELECT * FROM CONFEDERACION WHERE CODIGO = '" + conf + "'");
        while(rs.next()){
            confed = new Confederacion(rs.getString("NOMBRE"),rs.getString("CODIGO"));
        }
        return confed;
    }
    
    
    //retorna los equipos de un grupo ordenado por puntaje
    public ArrayList<Equipo> getGrupo(String idGrupo) throws SQLException{
        ArrayList<Equipo> grupo = new ArrayList();
        for(Confederacion confederacion: getConfederaciones()){
            for(Equipo equipo : confederacion.getEquipos()){
                if(equipo.getGrupo().equals(idGrupo))
                    grupo.add(equipo);
            }
        }
        return grupo;
    }
    
    //retorna todos los equipos ordenados por puntaje
    //y posición eliminatoria
    public ArrayList<Equipo> getEquiposPosiciones(){
        return null;
    }

    //retorna todos los partidos-----
    public ArrayList<Partido> getPartidos() throws SQLException{
        ArrayList<Partido> partidos = new ArrayList();
        ResultSet rs = Conexion.getRS("SELECT * FROM PARTIDOS");
        while(rs.next()){
            if(rs.getString("EXTRAS").equals(Partido.EXTRA_SI))
                EX = true;
            else
                EX = false;
//                partidos.add(new Partido(rs.getInt("NUMEROPARTIDO"),rs.getString("NOMBREESTADIO"),EX,
//                    rs.getInt("CANTIDADAFICIONADOS"),rs.getString("ETAPA"),rs.getString("FECHAHORA"),
//                        rs.getString("FECHAHORA"),rs.getString("ETAPA"),rs.getString("ETAPA")));
        }
        return partidos;
    }
    
    //retorna los goleadores en orden de cantidad de goles.
    public HashMap<Jugador, Integer> getGoleadores() throws SQLException{
        HashMap<Jugador, Integer> goleadores = new HashMap();
        ResultSet rs = Conexion.getRS("SELECT NUMEROPASAPORTE, COUNT(TIPOACCION) AS CANTGOLES FROM ACCION"
                + " WHERE TIPOACCION = 'Gol' GROUP BY NUMEROPASAPORTE");
        while(rs.next()){
            Jugador goleador = getJugador(rs.getString("NUMEROPASAPORTE"));
            goleadores.put(goleador, rs.getInt("CANTGOLES"));
//                System.out.println("Jugador: " + rs.getString("NUMEROPASAPORTE"));
//                System.out.println("Goles: " + rs.getInt("CANTGOLES"));
        }
        return goleadores;
    }

    public Jugador getJugador(String jugador) throws SQLException{
        Jugador player = null;
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Jugadores.numeroPasaporte,"
                + " fechaNacimiento, posicion, numeroCamisa from Personas join Jugadores"
                    + " on Jugadores.numeroPasaporte = Personas.numeroPasaporte"
                        + " where Jugadores.numeroPasaporte = '" + jugador + "'");
        while(rs.next()){
            player = new Jugador(rs.getString("NOMBRE"),rs.getString("APELLIDO1"), rs.getString("APELLIDO2"), 
                rs.getString("NUMEROPASAPORTE"), rs.getDate("FECHANACIMIENTO"), rs.getString("POSICION"),
                    rs.getInt("NUMEROCAMISA"));
        }
        return player;
    }
    
    public ArrayList<Arbitro> getArbitros() throws SQLException{
        ArrayList<Arbitro> arbitros = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, personas.numeropasaporte,"
                        + " personas.fechanacimiento, nacionalidad, iniciopuesto"
                            + " from personas join arbitro on"
                                + " personas.numeropasaporte = arbitro.numeropasaporte");
        while(rs.next()){
            arbitros.add(new Arbitro(rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"),
                    rs.getString("numeropasaporte"), rs.getDate("fechanacimiento"),
                        rs.getString("nacionalidad"), rs.getDate("iniciopuesto")));
        }
        return arbitros;
    }
    
    public ArrayList<Jugador> getJugadores() throws SQLException{
        ArrayList<Jugador> jugadores = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Jugadores.numeroPasaporte,"
                        + " fechaNacimiento, posicion, numeroCamisa from Personas join Jugadores"
                            + " on Jugadores.numeroPasaporte = Personas.numeroPasaporte");
        while(rs.next()){
            jugadores.add(new Jugador(rs.getString("NOMBRE"),rs.getString("APELLIDO1"), rs.getString("APELLIDO2"), 
                rs.getString("NUMEROPASAPORTE"), rs.getDate("FECHANACIMIENTO"), rs.getString("POSICION"),
                    rs.getInt("NUMEROCAMISA")));
        }
        return jugadores;
    }

    public ArrayList<Asistente> getEntrenadores() throws SQLException{
        ArrayList<Asistente> entrenadores = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Jugadores.numeroPasaporte,"
                        + " fechaNacimiento, posicion, numeroCamisa from Personas join Jugadores"
                            + " on Jugadores.numeroPasaporte = Personas.numeroPasaporte");
        while(rs.next()){
            entrenadores.add(new Asistente(rs.getString("NOMBRE"),rs.getString("APELLIDO1"), rs.getString("APELLIDO2"), 
                rs.getString("NUMEROPASAPORTE"), rs.getDate("FECHANACIMIENTO"), rs.getString("POSICION"),
                    rs.getInt("NUMEROCAMISA")));
        }
        return entrenadores;
    }
    
    public ArrayList<Asistente> getAsistentes() throws SQLException{
        return null;
    }
    
    public ArrayList<Federativo> getFederativos() throws SQLException{
        return null;
    }
    
    public void updateTeam(Equipo viejo, Equipo nuevo) throws SQLException{
        
    }
    
    //retorna un string con el diccionario de datos
    //podemos usar el del proyecto 1.
    public String getDiccionarioDatos(){
        return null;
    }
}

