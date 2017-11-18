package cargaInicialExcel;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class CargaInicialExcel {
    
    public static final String FORMATO_FECHA = "dd-MM-yyyy";

    public static void main(String[] args) {
        try {
            HashMap<Jugador, String> jugadores = getJugadoresExcel("actores.xls");
            HashMap<Asistente, String> asistentes = getAsistentesExcel("actores.xls");
            HashMap<Federativo, String> federativos = getFederativosExcel("actores.xls");
            
            //carga inicial acá
            
        } catch (IOException | BiffException | ParseException ex) {
            Logger.getLogger(CargaInicialExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static HashMap<Jugador, String> getJugadoresExcel(String ruta) throws IOException, BiffException, ParseException{
        Workbook workbook = Workbook.getWorkbook(new File(ruta));
        Sheet sheet = workbook.getSheet("jugadores");
        HashMap<Jugador, String> jugadores = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat(FORMATO_FECHA);
        Jugador jugador;
        for(int i = 0; i < sheet.getRows(); i++){
            String numeroPasaporte = sheet.getCell(0, i).getContents();
            String nombre = sheet.getCell(1, i).getContents();
            String apellido1 = sheet.getCell(2, i).getContents();
            String apellido2 = sheet.getCell(3, i).getContents();
            Date fechaNacimiento = new Date(format.parse(sheet.getCell(4, i).getContents()).getTime());
            String codigoPais = sheet.getCell(5, i).getContents();
            String puesto = sheet.getCell(6, i).getContents();
            int numeroCamisa = Integer.parseInt(sheet.getCell(7, i).getContents());
            
            jugador = new Jugador(nombre, apellido1, apellido2, numeroPasaporte, fechaNacimiento, puesto, numeroCamisa);
            jugadores.put(jugador, codigoPais);
        }
        return jugadores;
    }
    
    public static HashMap<Asistente, String> getAsistentesExcel(String ruta) throws IOException, BiffException, ParseException{
        Workbook workbook = Workbook.getWorkbook(new File(ruta));
        Sheet sheet = workbook.getSheet("asistentes");
        HashMap<Asistente, String> asistentes = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat(FORMATO_FECHA);
        Asistente asistente;
        for(int i = 0; i < sheet.getRows(); i++){
            String numeroPasaporte = sheet.getCell(0, i).getContents();
            String nombre = sheet.getCell(1, i).getContents();
            String apellido1 = sheet.getCell(2, i).getContents();
            String apellido2 = sheet.getCell(3, i).getContents();
            Date fechaNacimiento = new Date(format.parse(sheet.getCell(4, i).getContents()).getTime());
            String codigoPais = sheet.getCell(5, i).getContents();
            String puesto = sheet.getCell(6, i).getContents();
            Date inicioPuesto = new Date(format.parse(sheet.getCell(7, i).getContents()).getTime());
            String nacionalidad = sheet.getCell(8, i).getContents();
            
            asistente = new Asistente(nombre, apellido1, apellido2, numeroPasaporte, fechaNacimiento, nacionalidad, puesto, inicioPuesto);
            asistentes.put(asistente, codigoPais);
        }
        return asistentes;
    }
    
    public static HashMap<Federativo, String> getFederativosExcel(String ruta) throws IOException, BiffException, ParseException{
        Workbook workbook = Workbook.getWorkbook(new File(ruta));
        Sheet sheet = workbook.getSheet("federativos");
        HashMap<Federativo, String> federativos = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat(FORMATO_FECHA);
        Federativo federativo;
        for(int i = 0; i < sheet.getRows(); i++){
            String numeroPasaporte = sheet.getCell(0, i).getContents();
            String nombre = sheet.getCell(1, i).getContents();
            String apellido1 = sheet.getCell(2, i).getContents();
            String apellido2 = sheet.getCell(3, i).getContents();
            Date fechaNacimiento = new Date(format.parse(sheet.getCell(4, i).getContents()).getTime());
            String codigoPais = sheet.getCell(5, i).getContents();
            String puesto = sheet.getCell(6, i).getContents();
            Date inicioPuesto = new Date(format.parse(sheet.getCell(7, i).getContents()).getTime());
            String nacionalidad = sheet.getCell(8, i).getContents();
            
            federativo = new Federativo(nombre, apellido1, apellido2, numeroPasaporte, fechaNacimiento, nacionalidad, puesto, inicioPuesto);
            federativos.put(federativo, codigoPais);
        }
        return federativos;
    }
    
    public static void generarExcel(){
        try {
            String[] nombres = {"Keylor", "Johnny", "Giancarlo", "Michael", "Celso", "Oscar", "Christian", "Dave", "Joel", "Bryan", "Waylon", "Esteban", "Randall", "Junior", "Yeltsin", "Patrick", "Roy", "Diego", "Marcos", "Jose", "Daniel", "Tim", "DeAndre", "Omar", "Matt", "John", "DeMarcus", "Clint", "Aron", "Mikkel", "Alejandro", "Brad", "Jermaine", "Kyle", "Julian", "Jozy", "Chris"};
            String[] apellidos = {"Navas", "Acosta", "Gonzales", "Umana", "Borges", "Duarte", "Bolanos", "Myrie", "Campbell", "Ruiz", "Barrantes", "Francis", "Brenes", "Diaz", "Gamboa", "Tejeda", "Pemberton", "Miller", "Calvo", "Urena", "Cubero", "Cambronero", "Howard", "Yeldin", "Gonzales", "Bradley", "Besler", "Brooks", "Beasly", "Dempsey", "Johansson", "Diskerud", "Bedoya", "Guzman"};
            String[] codigosPaises = {"AF", "AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW", "AU", "AT", "AZ", "BS", "BH", "BD", "BB", "BY", "BE", "BZ", "BJ", "BM", "BT", "BO", "BA", "BW", "BV", "BR", "IO", "BN"};
            String[] puestosJugadores = {"Portero", "Defensa", "Mediocampista", "Delantero"};
            String[] puestosAsistentes = {"Entrenador", "Técnico", "Técnico", "Preparador Físico", "Médico", "Psicólogo", "Nutricionista", "Administrativo", "Delegado de Equipo"};
            String[] puestosFederativos = {"Director", "Vicepresidente", "Secretario", "Tesorero", "Fiscal", "Vocal"};
            String[] nacionalidades = {"Costarricense", "Nicaraguense", "Estadounidense", "Chino", "Coreano", "Espanol", "Argentino", "Mexicano"};
            int numeroPasaporte = 0;
            Random random = new Random();
            
            WritableWorkbook book = Workbook.createWorkbook(new File("actores.xls"));
            WritableSheet sheetJugadores = book.createSheet("jugadores", 0);
            WritableSheet sheetAsistentes = book.createSheet("asistentes", 1);
            WritableSheet sheetFederativos = book.createSheet("federativos", 2);
            
            int cantJugadores = 0;
            int cantFederativos = 0;
            int cantAsistentes = 0;
            
            for(int equipos = 0; equipos < codigosPaises.length; equipos++){
                for(int numeroCamisa = 1; numeroCamisa <=23; numeroCamisa++){
                    sheetJugadores.addCell(new Label(0, cantJugadores, Integer.toString(numeroPasaporte)));
                    sheetJugadores.addCell(new Label(1, cantJugadores, nombres[random.nextInt(nombres.length)]));
                    sheetJugadores.addCell(new Label(2, cantJugadores, apellidos[random.nextInt(apellidos.length)]));
                    sheetJugadores.addCell(new Label(3, cantJugadores, apellidos[random.nextInt(apellidos.length)]));
                    sheetJugadores.addCell(new Label(4, cantJugadores, getRandomDate()));
                    sheetJugadores.addCell(new Label(5, cantJugadores, codigosPaises[equipos]));
                    
                    sheetJugadores.addCell(new Label(6, cantJugadores, puestosJugadores[random.nextInt(puestosJugadores.length)]));
                    sheetJugadores.addCell(new Label(7, cantJugadores, Integer.toString(numeroCamisa)));
                    
                    cantJugadores++;
                    numeroPasaporte++;
                }
                for(int i = 0; i < puestosAsistentes.length; i++){
                    sheetAsistentes.addCell(new Label(0, cantAsistentes, Integer.toString(numeroPasaporte)));
                    sheetAsistentes.addCell(new Label(1, cantAsistentes, nombres[random.nextInt(nombres.length)]));
                    sheetAsistentes.addCell(new Label(2, cantAsistentes, apellidos[random.nextInt(apellidos.length)]));
                    sheetAsistentes.addCell(new Label(3, cantAsistentes, apellidos[random.nextInt(apellidos.length)]));
                    sheetAsistentes.addCell(new Label(4, cantAsistentes, getRandomDate()));
                    sheetAsistentes.addCell(new Label(5, cantAsistentes, codigosPaises[equipos]));
                    
                    sheetAsistentes.addCell(new Label(6, cantAsistentes, puestosAsistentes[i]));
                    sheetAsistentes.addCell(new Label(7, cantAsistentes, getRandomDate()));
                    sheetAsistentes.addCell(new Label(8, cantAsistentes, nacionalidades[random.nextInt(nacionalidades.length)]));
             
                    numeroPasaporte++;
                    cantAsistentes++;
                }
                for(int i = 0; i < puestosFederativos.length; i++){
                    sheetFederativos.addCell(new Label(0, cantFederativos, Integer.toString(numeroPasaporte)));
                    sheetFederativos.addCell(new Label(1, cantFederativos, nombres[random.nextInt(nombres.length)]));
                    sheetFederativos.addCell(new Label(2, cantFederativos, apellidos[random.nextInt(apellidos.length)]));
                    sheetFederativos.addCell(new Label(3, cantFederativos, apellidos[random.nextInt(apellidos.length)]));
                    sheetFederativos.addCell(new Label(4, cantFederativos, getRandomDate()));
                    sheetFederativos.addCell(new Label(5, cantFederativos, codigosPaises[equipos]));
                    
                    sheetFederativos.addCell(new Label(6, cantFederativos, puestosFederativos[i]));
                    sheetFederativos.addCell(new Label(7, cantFederativos, getRandomDate()));
                    sheetFederativos.addCell(new Label(8, cantFederativos, nacionalidades[random.nextInt(nacionalidades.length)]));
                    
                    numeroPasaporte++;
                    cantFederativos++;
                } 
            }
            book.write();
            book.close();
        } catch (IOException | WriteException ex) {
            Logger.getLogger(CargaInicialExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getRandomDate(){
        Random random = new Random();
        long fecha = random.nextLong() % System.currentTimeMillis();
        Date date = new Date(fecha);
        SimpleDateFormat format = new SimpleDateFormat(FORMATO_FECHA);
        return format.format(date);
    }
}
