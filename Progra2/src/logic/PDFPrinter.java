package logic;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import logic.partido.Partido;

public class PDFPrinter{
    
    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
    private static final Font PARAGRAPH_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
    
    public PDFPrinter(){}
    
    public void crearReporteConfederaciones(ArrayList<Confederacion> confederaciones) throws DocumentException, FileNotFoundException{
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
    
    public void crearReportePartidos(ArrayList<Partido> partidos){
    }
    
    public void crearReporteGrupos(String grupo){
    }
    
    public void crearReportePosiciones(){
    }
    
    public void crearReporteGoleadores(){
    }
}