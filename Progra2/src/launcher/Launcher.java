package launcher;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import logic.*;
import ui.*;

public class Launcher {

    public static void main(String[] args) {
        try {
            //poniendo el look and feel de windows
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) { 
            JOptionPane.showMessageDialog(null, "You're not using Windows, so the interface might look a little clunky :(", "ERROR",
            JOptionPane.ERROR_MESSAGE);
        }

        try{
            //Conexión a la base de datos e inicializacion de singletons
            Logic.getInstance();
            UI.getInstance().displayUI();
            Logic.getInstance().getGoleadores();
        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con SQL ORACLE.", "ERROR",
            JOptionPane.ERROR_MESSAGE);
        }
    }
}
