package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;
import logic.partido.Partido;
import logic.partido.Sede;
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
    
    public ArrayList<Confederacion> getConfederaciones() throws SQLException, Exception{
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
    
    public String getConfederacionCodigo(Equipo equipo) throws SQLException{
        ResultSet rs = Conexion.getRS("SELECT CODIGOCONFEDERACION FROM EQUIPO WHERE CODIGOPAIS = '" + equipo.getCodigoPais() + "'");
        while(rs.next()){
            return rs.getString("CODIGOCONFEDERACION");
        }
        throw new SQLException("No se encontraron confederaciones para el país: "+equipo.toString());
    }
    
    public ArrayList<String> getConfederacionesCodigos() throws SQLException{
        ArrayList<String> codigos = new ArrayList();
        ResultSet rs = Conexion.getRS("SELECT * FROM CONFEDERACION");
        while(rs.next()){
            codigos.add(rs.getString("CODIGO"));
        }
        return codigos;
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
    
    //Retorna la Confederacion de un Equipo
    private String getConfedTeam(String codPais) throws SQLException{
        String confed = "";
        ResultSet rs = Conexion.getRS("SELECT getConfedTeam('" + codPais + "') FROM DUAL");
        while(rs.next()){
            confed = rs.getString(1);
        }
        return confed;
    }
    
    //Retorna el Grupo de un Equipo
    private String getGrupoTeam(String codPais) throws SQLException{
        String grupo = "";
        ResultSet rs = Conexion.getRS("SELECT getGrupoTeam('" + codPais + "') FROM DUAL");
        while(rs.next()){
            grupo = rs.getString(1);
        }
        return grupo;
    }
    
    //retorna los equipos de un grupo ordenado por puntaje
    public ArrayList<Equipo> getGrupo(String idGrupo) throws SQLException, Exception{
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
    
    public Equipo getEquipo(String codigo) throws SQLException, Exception{
        Equipo team = null;
        ArrayList<Jugador> jugadores = new ArrayList();
        ArrayList<Asistente> tecnicos = new ArrayList();
        ArrayList<Federativo> federativos = new ArrayList();
        ResultSet rs = Conexion.getRS("select * from equipo where codigopais = '" + codigo + "'");
        while(rs.next()){
            team = new Equipo(rs.getString("CODIGOPAIS"),rs.getString("NOMBREPAIS"),rs.getString("GRUPO"),
                                jugadores, tecnicos, federativos);
            team.setEntrenador(getEntrenador(team));
        } 
        team.getFederativos().addAll(getFederativos(team));
        team.getAsistentes().addAll(getAsistentes(team));
        team.getJugadores().addAll(getJugadores(team));
        return team;
    }

    public ArrayList<Equipo> getEquipos() throws SQLException, Exception{
        ArrayList<Equipo> teams = new ArrayList();
        ResultSet rs = Conexion.getRS("select codigoPais from equipo");
        while(rs.next()){
            teams.add(getEquipo(rs.getString("codigoPais")));
        }
        return teams;
    }
    
    public ArrayList<Equipo> getEquipos(String codigoConf) throws SQLException, Exception{
        ArrayList<Equipo> teams = new ArrayList();
        ResultSet rs = Conexion.getRS("select codigoPais from equipo where codigoConfederacion = '" + codigoConf + "'");
        while(rs.next()){
            teams.add(getEquipo(rs.getString("codigoPais")));
        }
        return teams;
    }
    
    public void crearEquipo(Equipo equipo, String codigoConfederacion) throws SQLException{
        Conexion.setQuery("insert into equipo(codigoconfederacion, nombrepais, grupo, codigopais)\n"+
                          "values ('"+codigoConfederacion+"', '"+equipo.getNombrePais()+"', '"+equipo.getGrupo()+"', '"+equipo.getCodigoPais()+"')");
        for(Jugador jugador : equipo.getJugadores())
            Conexion.setQuery("update jugadores set equipo = '"+equipo.getCodigoPais()+
                              "' where numeropasaporte = '"+jugador.getNumeroPasaporte()+"'");
        for(Asistente asistente : equipo.getAsistentes())
            Conexion.setQuery("update asistente set equipo = '"+equipo.getCodigoPais()+
                              "' where numeropasaporte = '"+asistente.getNumeroPasaporte()+"'");
        for(Federativo federativo : equipo.getFederativos())
            Conexion.setQuery("update federativo set equipo = '"+equipo.getCodigoPais()+
                              "' where numeropasaporte = '"+federativo.getNumeroPasaporte()+"'");
    }
    
    public void borrarEquipo(Equipo equipo) throws SQLException{
        Conexion.setQuery("update jugadores set equipo = null where equipo = '"+equipo.getCodigoPais()+"'");
        Conexion.setQuery("update asistente set equipo = null where equipo = '"+equipo.getCodigoPais()+"'");
        Conexion.setQuery("update federativo set equipo = null where equipo = '"+equipo.getCodigoPais()+"'");
        Conexion.setQuery("delete from equipo where codigopais = '"+equipo.getCodigoPais()+"'");
    }
    
    public void actualizarEquipo(Equipo viejo, Equipo nuevo, String codigoConfederacion) throws SQLException{
        borrarEquipo(viejo);
        crearEquipo(nuevo, codigoConfederacion);
    }
    
    public ArrayList<Jugador> getJugadores(Equipo equipo) throws SQLException{
        ArrayList<Jugador> jugadores = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Jugadores.numeroPasaporte,"
                + " fechaNacimiento, posicion, numeroCamisa from Personas join Jugadores"
                    + " on Jugadores.numeroPasaporte = Personas.numeroPasaporte"
                        + " where equipo = '" + equipo.getCodigoPais() + "'");
        while(rs.next()){
            jugadores.add(new Jugador(rs.getString("NOMBRE"),rs.getString("APELLIDO1"), rs.getString("APELLIDO2"), 
                rs.getString("NUMEROPASAPORTE"), rs.getDate("FECHANACIMIENTO"), rs.getString("POSICION"),
                    rs.getInt("NUMEROCAMISA")));
        }
        return jugadores;
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
    
    public ArrayList<Jugador> getJugadoresLibres() throws SQLException{
        ArrayList<Jugador> jugadores = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Jugadores.numeroPasaporte,"
                        + " fechaNacimiento, posicion, numeroCamisa from Personas join Jugadores"
                            + " on Jugadores.numeroPasaporte = Personas.numeroPasaporte where Equipo is null");
        while(rs.next()){
            jugadores.add(new Jugador(rs.getString("NOMBRE"),rs.getString("APELLIDO1"), rs.getString("APELLIDO2"), 
                rs.getString("NUMEROPASAPORTE"), rs.getDate("FECHANACIMIENTO"), rs.getString("POSICION"),
                    rs.getInt("NUMEROCAMISA")));
        }
        return jugadores;
    }
    
    public void setJugadoresLibres(ArrayList<Jugador> player) throws SQLException{
        for(int x = 0; x < player.size(); x++){
            String numPas = player.get(x).getNumeroPasaporte();
            Conexion.setQuery("update jugadores set equipo = null where numeropasaporte = '" + numPas + "'");
        }
    }

    // hacer metodo que recibe arraylist de jugadores/entrenadores/asistentes para colocarlos en un equipo
    public ArrayList<Asistente> getAsistentesLibres() throws SQLException{
        ArrayList<Asistente> Asist = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Asistente.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, tipoasistente, iniciopuesto from Personas join Asistente"
                            + " on Asistente.numeroPasaporte = Personas.numeroPasaporte where tipoasistente != 'Entrenador'"
                                + " and equipo is null");
        while(rs.next()){
            Asist.add(new Asistente(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("tipoasistente"), rs.getDate("iniciopuesto")));
        }
        return Asist;
    }
    
    public ArrayList<Asistente> getAsistentes(Equipo equipo) throws SQLException{
        ArrayList<Asistente> Asist = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Asistente.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, tipoasistente, iniciopuesto from Personas join Asistente"
                            + " on Asistente.numeroPasaporte = Personas.numeroPasaporte where tipoasistente != 'Entrenador'"
                                + " and equipo = '"+equipo.getCodigoPais()+"'");
        while(rs.next()){
            Asist.add(new Asistente(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("tipoasistente"), rs.getDate("iniciopuesto")));
        }
        return Asist;
    }
    
    public void setAsistentesLibres(ArrayList<Asistente> asist) throws SQLException{
        for(int x = 0; x < asist.size(); x++){
            String numPas = asist.get(x).getNumeroPasaporte();
            Conexion.setQuery("update asistente set equipo = null where numeropasaporte = '" + numPas + "'");
        }
    }
    
    public Asistente getEntrenador(Equipo equipo) throws SQLException{
        ArrayList<Asistente> entrenadores = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Asistente.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, tipoasistente, iniciopuesto from Personas join Asistente"
                            + " on Asistente.numeroPasaporte = Personas.numeroPasaporte where tipoasistente = 'Entrenador'"
                                +" and equipo = '"+equipo.getCodigoPais()+"'");
        while(rs.next()){
            entrenadores.add(new Asistente(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("tipoasistente"), rs.getDate("iniciopuesto")));
        }
        return entrenadores.get(0);
    }
    
    public ArrayList<Asistente> getEntrenadores() throws SQLException{
        ArrayList<Asistente> entrenadores = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Asistente.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, tipoasistente, iniciopuesto from Personas join Asistente"
                            + " on Asistente.numeroPasaporte = Personas.numeroPasaporte where tipoasistente = 'Entrenador'");
        while(rs.next()){
            entrenadores.add(new Asistente(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("tipoasistente"), rs.getDate("iniciopuesto")));
        }
        return entrenadores;
    }
    
    public ArrayList<Asistente> getEntrenadoresLibres() throws SQLException{
        ArrayList<Asistente> entrenadores = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Asistente.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, tipoasistente, iniciopuesto from Personas join Asistente"
                            + " on Asistente.numeroPasaporte = Personas.numeroPasaporte where tipoasistente = 'Entrenador'"
                                + "and equipo is null");
        while(rs.next()){
            entrenadores.add(new Asistente(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("tipoasistente"), rs.getDate("iniciopuesto")));
        }
        return entrenadores;
    }
    
    public ArrayList<Federativo> getFederativos(Equipo equipo) throws SQLException{
        ArrayList<Federativo> fede = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Personas.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, puesto, iniciopuesto from Personas join Federativo"
                            + " on Federativo.numeroPasaporte = Personas.numeroPasaporte where equipo = '"+equipo.getCodigoPais()+"'");
        while(rs.next()){
            fede.add(new Federativo(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("puesto"), rs.getDate("iniciopuesto")));
        }
        return fede;
    }
    
    public ArrayList<Federativo> getFederativos() throws SQLException{
        ArrayList<Federativo> fede = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Personas.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, puesto, iniciopuesto from Personas join Federativo"
                            + " on Federativo.numeroPasaporte = Personas.numeroPasaporte");
        while(rs.next()){
            fede.add(new Federativo(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("puesto"), rs.getDate("iniciopuesto")));
        }
        return fede;
    }
    
    public ArrayList<Federativo> getFederativosLibres() throws SQLException{
        ArrayList<Federativo> fede = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, Personas.numeroPasaporte,"
                        + " fechaNacimiento, nacionalidad, puesto, iniciopuesto from Personas join Federativo"
                            + " on Federativo.numeroPasaporte = Personas.numeroPasaporte where Equipo is null");
        while(rs.next()){
            fede.add(new Federativo(rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"), 
                rs.getString("numeroPasaporte"), rs.getDate("fechaNacimiento"), rs.getString("nacionalidad"),
                    rs.getString("puesto"), rs.getDate("iniciopuesto")));
        }
        return fede;
    }
    
    public void setFederativosLibres(ArrayList<Federativo> fede) throws SQLException{
        for(int x = 0; x < fede.size(); x++){
            String numPas = fede.get(x).getNumeroPasaporte();
            Conexion.setQuery("update federativo set equipo = null where numeropasaporte = '" + numPas + "'");
        }
    }
    
    public ArrayList<Arbitro> getArbitros() throws SQLException{
        ArrayList<Arbitro> arbitros = new ArrayList();
        ResultSet rs = Conexion.getRS("select nombre, apellido1, apellido2, personas.numeropasaporte,"
                        + " personas.fechanacimiento, nacionalidad, iniciopuesto"
                            + " from personas join arbitro on personas.numeropasaporte = arbitro.numeropasaporte");
        while(rs.next()){
            arbitros.add(new Arbitro(rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"),
                    rs.getString("numeropasaporte"), rs.getDate("fechanacimiento"),
                        rs.getString("nacionalidad"), rs.getDate("iniciopuesto")));
        }
        return arbitros;
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
    
    public void agregarPartido(Partido partido) throws SQLException{
        Conexion.setQuery("insert into partidos values ("+partido.getNumeroPartido()+",'"+partido.getEtapa()
                +"','"+partido.getFecha()+"',"+partido.getCantidadAficionados()+",'"+partido.getReposicion1Tiempo()
                +"','"+partido.getReposicion2Tiempo()+"','"+partido.getAlineacion1().getEquipo().getCodigoPais()
                +"','"+partido.getAlineacion2().getEquipo().getCodigoPais()+"','"+partido.getSede().getNombreEstadio()
                +"','"+partido.getExtras()+"')");
        for(int i = 0; i<partido.getAcciones().size(); i++){
            Conexion.setQuery("insert into accion values ('"+partido.getAcciones().get(i).getJugador().getNumeroPasaporte()
                +"','"+partido.getAcciones().get(i).getTipoAccion()+"','"+partido.getAcciones().get(i).getTiempo()
                +"',"+partido.getNumeroPartido()+",'"+partido.getAcciones().get(i).getEquipo().getCodigoPais()+"')");
        }
        for(int i=0; i<partido.getAlineacion1().getCambios().size();i++){
            Conexion.setQuery("insert into cambios values ('"+partido.getAlineacion1().getCambios().get(i).getEntrante().getNumeroPasaporte()
                +"','"+partido.getAlineacion1().getCambios().get(i).getSaliente().getNumeroPasaporte()+"','"
                +partido.getAlineacion1().getCambios().get(i).getTiempo()+"',"+partido.getNumeroPartido());
        }
        for(int i=0; i<partido.getAlineacion2().getCambios().size();i++){
            Conexion.setQuery("insert into cambios values ('"+partido.getAlineacion2().getCambios().get(i).getEntrante().getNumeroPasaporte()
                +"','"+partido.getAlineacion2().getCambios().get(i).getSaliente().getNumeroPasaporte()+"','"
                +partido.getAlineacion2().getCambios().get(i).getTiempo()+"',"+partido.getNumeroPartido()+")");
        }
        for(int i=0;i<partido.getArbitros().size();i++){
            Conexion.setQuery("insert into cuerpoarbitral values ('"+partido.getArbitros().get(i).getNumeroPasaporte()
                +"','Guardalíneas',"+partido.getNumeroPartido()+")");
        }
        for(int i=0;i<partido.getAlineacion1().getTecnicos().size();i++){
            Conexion.setQuery("insert into equipotecnico values ("+partido.getAlineacion1().getTecnicos().get(i).getNumeroPasaporte()
                +",'"+partido.getAlineacion1().getEquipo().getCodigoPais()+"','"+partido.getAlineacion1().getTecnicos().get(i).getPuesto()
                +"',"+partido.getNumeroPartido()+")");
        }
        for(int i=0;i<partido.getAlineacion2().getTecnicos().size();i++){
            Conexion.setQuery("insert into equipotecnico values ("+partido.getAlineacion2().getTecnicos().get(i).getNumeroPasaporte()
                +",'"+partido.getAlineacion2().getEquipo().getCodigoPais()+"','"+partido.getAlineacion2().getTecnicos().get(i).getPuesto()
                +"',"+partido.getNumeroPartido()+")");
        }
        for(int i=0;i<partido.getPenales().size();i++){
            Conexion.setQuery("insert into penales values ('"+partido.getPenales().get(i).getNumero()+"','"
                +partido.getPenales().get(i).isAnotado()+"','"+partido.getPenales().get(i).getJugador().getNumeroPasaporte()
                +"',"+partido.getNumeroPartido()+")");
        }
        for(int i=0;i<partido.getAlineacion1().getSuplentes().size();i++){
            Conexion.setQuery("insert into suplentes values ('"+partido.getAlineacion1().getSuplentes().get(i).getNumeroPasaporte()
                +"','"+partido.getAlineacion1().getEquipo().getCodigoPais()+"',"+partido.getNumeroPartido()+")");
        }
        for(int i=0;i<partido.getAlineacion2().getSuplentes().size();i++){
            Conexion.setQuery("insert into suplentes values ('"+partido.getAlineacion2().getSuplentes().get(i).getNumeroPasaporte()
                +"','"+partido.getAlineacion2().getEquipo().getCodigoPais()+"',"+partido.getNumeroPartido()+")");
        }
        for(int i=0;i<partido.getAlineacion1().getTitulares().size();i++){
            String cap = "NO";
            if(partido.getAlineacion1().getCapitan().getNumeroPasaporte().equals(partido.getAlineacion1().getTitulares().get(i).getNumeroPasaporte())){
                cap = "SI";
            }
            Conexion.setQuery("insert into titulares values ('"+partido.getAlineacion1().getTitulares().get(i).getNumeroPasaporte()
                +"','"+cap+"','"+partido.getAlineacion1().getEquipo().getCodigoPais()+"',"+partido.getNumeroPartido()+")");
        }
        for(int i=0;i<partido.getAlineacion2().getTitulares().size();i++){
            String cap = "NO";
            if(partido.getAlineacion2().getCapitan().getNumeroPasaporte().equals(partido.getAlineacion2().getTitulares().get(i).getNumeroPasaporte())){
                cap = "SI";
            }
            Conexion.setQuery("insert into titulares values ('"+partido.getAlineacion2().getTitulares().get(i).getNumeroPasaporte()
                +"','"+cap+"','"+partido.getAlineacion2().getEquipo().getCodigoPais()+"',"+partido.getNumeroPartido()+")");
        }
    }

    public int partidosGanadosFaseGrupos(String equipo) throws SQLException{
        int win = 0;
        ResultSet rs = Conexion.getRS("select from");
        while(rs.next()){
            win = rs.getInt("GANADOS");
        }
        return win;
    }
    
    public int getCantidadPartidos() throws SQLException{
        int cant = 0;
        ResultSet rs = Conexion.getRS("select count(numeropartido) as cantidadPartidos from partidos");
        while(rs.next()){
            cant = rs.getInt("cantidadPartidos");
        }
        return cant;
    }
    
    public void borrarPartido(Partido partido) throws SQLException{
        Conexion.setQuery("");
    }
    
    //retorna todos los partidos
    public ArrayList<Partido> getPartidos() throws SQLException{
        ArrayList<Partido> partidos = new ArrayList();
        ResultSet rs = Conexion.getRS("SELECT * FROM PARTIDOS");
        while(rs.next()){
            if(rs.getString("EXTRAS").equals(Partido.EXTRA_SI))
                EX = true;
            else
                EX = false;
            Sede estadio = getSede(rs.getString("NOMBREESTADIO"));
            partidos.add(new Partido(rs.getInt("NUMEROPARTIDO"),estadio,EX,
                rs.getInt("CANTIDADAFICIONADOS"),rs.getString("ETAPA"),rs.getDate("FECHAHORA"),
                    rs.getString("FECHAHORA"),rs.getString("ETAPA")));
        }
        return partidos;
    }
    
    public ArrayList<Sede> getSedes() throws SQLException{
        ArrayList<Sede> estadios = new ArrayList();
        ResultSet rs = Conexion.getRS("SELECT * FROM SEDE");
        while(rs.next()){
            estadios.add(new Sede(rs.getInt("CAPACIDAD"), rs.getString("NOMBREESTADIO"), rs.getString("NOMBRESEDE")));
        }
        return estadios;
    }
    
    public Sede getSede(String nombre) throws SQLException{
        Sede estadio = null;
        ResultSet rs = Conexion.getRS("SELECT * FROM SEDE WHERE NOMBREESTADIO = '"+nombre+"'");
        while(rs.next()){
            estadio = new Sede(rs.getInt("CAPACIDAD"), rs.getString("NOMBREESTADIO"), rs.getString("NOMBRESEDE"));
        }
        return estadio;
    }
    
    //retorna un solo partido
    public Partido getPartido(int numeroPartido) throws SQLException{
        Partido partido = null;
        ResultSet rs = Conexion.getRS("SELECT * FROM PARTIDOS WHERE NUMEROPARTIDO = '"+numeroPartido+"'");
        while(rs.next()){
            if(rs.getString("EXTRAS").equals(Partido.EXTRA_SI))
                EX = true;
            else
                EX = false;
            Sede estadio = getSede(rs.getString("NOMBREESTADIO"));
            partido = new Partido(rs.getInt("NUMEROPARTIDO"),estadio,EX,
                rs.getInt("CANTIDADAFICIONADOS"),rs.getString("ETAPA"),rs.getDate("FECHAHORA"),
                    rs.getString("FECHAHORA"),rs.getString("ETAPA"));
        }
        return partido;
    }
    
    //retorna un string con el diccionario de datos de UNA tabla
    private String getDiccionarioDatosTabla(String nombreTabla) throws SQLException{
        String resultSet = "Tabla: "+ nombreTabla + "\n"
                + "Nombre_Columna |\t PK |\t Tipo_Dato |\t Nulos |\t Valor_Default |\t Descripcion\n";
        ResultSet rs = Conexion.getRS("SELECT ATC.COLUMN_NAME, DECODE((SELECT COUNT(*) FROM ALL_CONS_COLUMNS ACC "
                + "WHERE ACC.COLUMN_NAME = ATC.COLUMN_NAME AND ACC.TABLE_NAME = ATC.TABLE_NAME AND ACC.POSITION = 1 ),"
                + "0,'N','S') AS PK, CONCAT(ATC.DATA_TYPE,DECODE(ATC.DATA_PRECISION,NULL,'',"
                + "CONCAT(' / ',ATC.DATA_PRECISION))) AS TIPO_DATO, DECODE(ATC.NULLABLE,'Y','Si','N','No') AS Permite_Nulos,"
                + " ATC.DATA_DEFAULT AS Valor_Default, 'N/A' AS Rango_Valores_Permitidos, "
                + "NVL((SELECT ACC2.COMMENTS FROM ALL_COL_COMMENTS ACC2 "
                + "WHERE ACC2.TABLE_NAME = ATC.TABLE_NAME AND ACC2.COLUMN_NAME = ATC.COLUMN_NAME AND rownum = 1),"
                + "'ATRIBUTO') AS Descripcion_Columna FROM ALL_TAB_COLUMNS ATC WHERE ATC.TABLE_NAME = '"+nombreTabla+"' "
                + "ORDER BY PK DESC , COLUMN_NAME ASC");
        while(rs.next()){
            resultSet += rs.getString("COLUMN_NAME") +"\t|";
            resultSet += rs.getString("PK") +"\t|";
            resultSet += rs.getString("TIPO_DATO") +"\t|";
            resultSet += rs.getString("Permite_Nulos") +"\t|";
            resultSet += rs.getString("Valor_Default") +"\t|";
            resultSet += rs.getString("Descripcion_Columna") +"\t|";
            resultSet += "\n\n";
        }
        return resultSet;
    }
    
    //retorna un string con el diccionario de datos de TODAS las tablas
    public String getDiccionarioDatosTablas() throws SQLException{
        ArrayList<String> tablas = new ArrayList();
        tablas.add("ACCION");tablas.add("ARBITRO");tablas.add("ASISTENTE");tablas.add("CAMBIOS");tablas.add("CONFEDERACION");
        tablas.add("CUERPOARBITRAL");tablas.add("EQUIPO");tablas.add("EQUIPOTECNICO");tablas.add("FEDERATIVO");
        tablas.add("JUGADORES");tablas.add("PARTIDOS");tablas.add("PENALES");tablas.add("PERSONAS");tablas.add("SEDE");
        tablas.add("SUPLENTES");tablas.add("TITULARES");
        String resultSet = "";
        for(String nombreTabla : tablas){
            resultSet += getDiccionarioDatosTabla(nombreTabla)+"\n\n";
        }
        return resultSet;
    }
}

