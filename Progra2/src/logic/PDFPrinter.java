package logic;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;
import logic.partido.Partido;
import logic.persona.Jugador;

public class PDFPrinter{
    
    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
    private static final Font PARAGRAPH_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
    
    public PDFPrinter(){}
    
    public void crearReporteConfederaciones() throws DocumentException, FileNotFoundException, SQLException, Exception{
        ArrayList<Confederacion> confederaciones = Logic.getInstance().getConfederaciones();
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("Reporte Confederaciones.pdf"));
        doc.open();
        for(Confederacion confederacion : confederaciones){
            doc.add(new Paragraph(confederacion.getNombre(), TITLE_FONT));
            for(Equipo equipo : confederacion.getEquipos()){
                doc.add(new Paragraph("    "+equipo.getNombrePais()+" ("+equipo.getCodigoPais()+")", PARAGRAPH_FONT));
            }
        }
        doc.close();
    }
    
    public void crearReporteConfederacion(Confederacion confederacion) throws FileNotFoundException, DocumentException{
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("Reporte Confederación "+confederacion.getNombre()+".pdf"));
        doc.open();
        doc.add(new Paragraph(confederacion.getNombre(), TITLE_FONT));
        for(Equipo equipo : confederacion.getEquipos()){
            doc.add(new Paragraph("    "+equipo.getNombrePais()+" ("+equipo.getCodigoPais()+")", PARAGRAPH_FONT));
        }
        doc.close();
    }
    
    public void crearReportePartidos(){
    }
    
    public void crearReportePartido(Partido partido){
    }
    
    public void crearReporteGrupos(){
        
    }
    
    public void crearReportePosiciones(){
    }
    
    public void crearReporteGoleadores() throws SQLException{
        Pair<ArrayList<Jugador>, ArrayList<Integer>> goledores = Logic.getInstance().getGoleadores();
        
    }
}