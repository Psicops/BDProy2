package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;
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
        System.out.println("ayy lmao");
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
        ResultSet rs = Conexion.getRS("SELECT * FROM EQUIPO WHERE GRUPO = '" + idGrupo + "'");
        while(rs.next()){
            grupo.add(getEquipo(rs.getString("CODIGOPAIS")));
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
//            partidos.add(new Partido(rs.getInt("NUMEROPARTIDO"),rs.getString("NOMBREESTADIO"),EX,
//                rs.getInt("CANTIDADAFICIONADOS"),rs.getString("ETAPA"),rs.getString("FECHAHORA"),
//                    rs.getString("FECHAHORA"),rs.getString("ETAPA"),rs.getString("ETAPA")));
        }
        return partidos;
    }
    
    public Partido getPartido(int numeroPartido){
        return null;
    }
    
    //retorna los goleadores en orden de cantidad de goles.
    public Pair<ArrayList<Jugador>, ArrayList<Integer>> getGoleadores() throws SQLException{
        ArrayList<Jugador> goleadores = new ArrayList();
        ArrayList<Integer> goles = new ArrayList();
        ResultSet rs = Conexion.getRS("SELECT NUMEROPASAPORTE, COUNT(TIPOACCION) AS CANTGOLES FROM ACCION"
                + " WHERE TIPOACCION = 'Gol' GROUP BY NUMEROPASAPORTE");
        while(rs.next()){
            Jugador goleador = getJugador(rs.getString("NUMEROPASAPORTE"));
            goleadores.add(goleador);
            goles.add(rs.getInt("CANTGOLES"));
        }
        Pair<ArrayList<Jugador>, ArrayList<Integer>> goleadoresEntry = new Pair(goleadores, goles);
        return goleadoresEntry;
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
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Asistente.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, tipoasistente, iniciopuesto from Personas join Asistente"
                            + " on Asistente.numeroPasaporte = Personas.numeroPasaporte where puesto = 'Entrenador'");
        while(rs.next()){
            entrenadores.add(new Asistente(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("tipoasistente"), rs.getDate("iniciopuesto")));
        }
        return entrenadores;
    }
    // hacer metodo que recibe arraylist de jugadores/entrenadores/asistentes para colocarlos en un equipo
    public ArrayList<Asistente> getAsistentesLibres() throws SQLException{
        ArrayList<Asistente> Asist = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Asistente.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, tipoasistente, iniciopuesto from Personas join Asistente"
                            + " on Asistente.numeroPasaporte = Personas.numeroPasaporte where puesto != 'Entrenador'"
                                + " and equipo = 'NUL'");
        while(rs.next()){
            Asist.add(new Asistente(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("tipoasistente"), rs.getDate("iniciopuesto")));
        }
        return Asist;
    }
    
    public ArrayList<Asistente> getEntrenadoresLibres() throws SQLException{
        ArrayList<Asistente> entrenadores = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Asistente.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, tipoasistente, iniciopuesto from Personas join Asistente"
                            + " on Asistente.numeroPasaporte = Personas.numeroPasaporte where puesto = 'Entrenador'"
                                + "and Equipo = 'NUL'");
        while(rs.next()){
            entrenadores.add(new Asistente(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("tipoasistente"), rs.getDate("iniciopuesto")));
        }
        return entrenadores;
    }
    
    public ArrayList<Federativo> getFederativosLibres() throws SQLException{
        ArrayList<Federativo> fede = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Personas.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, puesto, iniciopuesto from Personas join Federativo"
                            + " on Federativo.numeroPasaporte = Personas.numeroPasaporte where puesto = 'Entrenador'"
                                + "and Equipo = 'NUL'");
        while(rs.next()){
            fede.add(new Federativo(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("puesto"), rs.getDate("iniciopuesto")));
        }
        return fede;
    }
    
    public ArrayList<Jugador> getJugadoresLibres() throws SQLException{
        ArrayList<Jugador> jugadores = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Jugadores.numeroPasaporte,"
                        + " fechaNacimiento, posicion, numeroCamisa from Personas join Jugadores"
                            + " on Jugadores.numeroPasaporte = Personas.numeroPasaporte where Equipo = 'NUL'");
        while(rs.next()){
            jugadores.add(new Jugador(rs.getString("NOMBRE"),rs.getString("APELLIDO1"), rs.getString("APELLIDO2"), 
                rs.getString("NUMEROPASAPORTE"), rs.getDate("FECHANACIMIENTO"), rs.getString("POSICION"),
                    rs.getInt("NUMEROCAMISA")));
        }
        return jugadores;
    }
    
    public ArrayList<Federativo> getFederativos() throws SQLException{
        ArrayList<Federativo> fede = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Personas.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, puesto, iniciopuesto from Personas join Federativo"
                            + " on Federativo.numeroPasaporte = Personas.numeroPasaporte where puesto = 'Entrenador'");
        while(rs.next()){
            fede.add(new Federativo(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("puesto"), rs.getDate("iniciopuesto")));
        }
        return fede;
    }
    
    public void setAsistentesLibres(ArrayList<Asistente> asist) throws SQLException{
        for(int x = 0; x < asist.size(); x++){
            String numPas = asist.get(x).getNumeroPasaporte();
            Conexion.setQuery("update asistente set equipo = 'NUL' where numeropasaporte = '" + numPas + "'");
        }
    }
    
    public void setFederativosLibres(ArrayList<Federativo> fede) throws SQLException{
        for(int x = 0; x < fede.size(); x++){
            String numPas = fede.get(x).getNumeroPasaporte();
            Conexion.setQuery("update federativo set equipo = 'NUL' where numeropasaporte = '" + numPas + "'");
        }
    }
    
    public void setJugadoresLibres(ArrayList<Jugador> player) throws SQLException{
        for(int x = 0; x < player.size(); x++){
            String numPas = player.get(x).getNumeroPasaporte();
            Conexion.setQuery("update jugadores set equipo = 'NUL' where numeropasaporte = '" + numPas + "'");
        }
    }
    
    public void createTeam(Equipo team) throws SQLException{
        
    }
    
    public void updateTeam(Equipo viejo, Equipo nuevo) throws SQLException{
        
    }
    
    public void deleteTeam(Equipo equipo) throws SQLException{
    }
    
    //retorna un string con el diccionario de datos
    //podemos usar el del proyecto 1.
    public String getDiccionarioDatos(){
        return null;
    }
}

