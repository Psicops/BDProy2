package ui;

import java.awt.Component;
import javax.swing.JOptionPane;

public class UI {
    private static UI instance;
    
    private final MainWindow mainWindow;
    
    private UI(){
        mainWindow = new MainWindow();
    }
    
    public static UI getInstance(){
        if(instance == null)
            instance = new UI();
        return instance;
    }
    
    public void displayUI(){
        mainWindow.setVisible(true);
    }
    
    public MainWindow getMainWindow(){
        return mainWindow;
    }
    
    public void displayError(String message, Component parent){
        JOptionPane.showMessageDialog(parent, message, "ERROR",
                                      JOptionPane.ERROR_MESSAGE);
    }
    public void displayInfo(String message, Component parent){
        JOptionPane.showMessageDialog(null, message, "Información",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    public boolean displayConfirm(String message, Component parent){
        int reply =  JOptionPane.showConfirmDialog(null, message, "Pregunta", 
                                      JOptionPane.YES_NO_OPTION);
        return reply == JOptionPane.YES_OPTION;
    }
}