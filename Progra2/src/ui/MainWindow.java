package ui;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import logic.Confederacion;
import logic.Equipo;
import logic.partido.Partido;
import logic.Logic;
import logic.persona.Jugador;

public class MainWindow extends javax.swing.JFrame {

    public MainWindow() {
        initComponents();
        this.setTitle("Base de Datos: Copa del Mundo");
        this.setLocationRelativeTo(null);
        tableGrupoA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGrupoB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGrupoC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGrupoD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGrupoE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGrupoF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGrupoG.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGrupoH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGoleadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePartidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePosiciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void clearTable(JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--)
            model.removeRow(i);
    }
    
    public void updatePartidos() throws SQLException {
        clearTable(tablePartidos);
        DefaultTableModel model = (DefaultTableModel)tablePartidos.getModel();
        Object[] fila = new String[5];
        for(Partido partido : Logic.getInstance().getPartidos()){
            fila[0] = partido.getAlineacion1().getEquipo().getCodigoPais();
            fila[1] = partido.getAlineacion2().getEquipo().getCodigoPais();
            fila[2] = partido.getMarcadores();
            fila[3] = partido.getFecha();
            fila[4] = partido.getSede().getNombreEstadio();
            model.addRow(fila);
        }
    }
    
    private void updateTablaGrupo(String idGrupo, JTable tabla) throws SQLException, Exception{
        clearTable(tabla);
        DefaultTableModel model = (DefaultTableModel)tabla.getModel();
        Object[] fila = new Object[10];
        Integer posicion = 1;
        for(Equipo equipo : Logic.getInstance().getGrupo(idGrupo)){
            fila[0] = posicion;
            fila[1] = equipo.getNombrePais();
            fila[2] = equipo.getPuntos();
            fila[3] = equipo.getPartidosJugados();
            fila[4] = equipo.getPartidosGanados();
            fila[5] = equipo.getPartidosEmpatados();
            fila[6] = equipo.getPartidosPerdidos();
            fila[7] = equipo.getGolesFavor();
            fila[8] = equipo.getGolesContra();
            fila[9] = equipo.getGolesDiferencia();
            model.addRow(fila);
            posicion++;
        }
    }
    
    public void updateTablasGrupos() throws SQLException, Exception{
        HashMap<String, JTable> tablas = new HashMap();
        tablas.put(Logic.GRUPO_A, tableGrupoA);
        tablas.put(Logic.GRUPO_B, tableGrupoB);
        tablas.put(Logic.GRUPO_C, tableGrupoC);
        tablas.put(Logic.GRUPO_D, tableGrupoD);
        tablas.put(Logic.GRUPO_E, tableGrupoE);
        tablas.put(Logic.GRUPO_F, tableGrupoF);
        tablas.put(Logic.GRUPO_G, tableGrupoG);
        tablas.put(Logic.GRUPO_H, tableGrupoH);
        
        for(String grupo : tablas.keySet()){
            updateTablaGrupo(grupo, tablas.get(grupo)); 
        }
    }
    
    public void updatePosiciones() throws SQLException{
        clearTable(tablePosiciones);
        DefaultTableModel model = (DefaultTableModel)tablePosiciones.getModel();
        Object[] fila = new String[10];
        int posicion = 1;
        for(Equipo equipo : Logic.getInstance().getEquiposPosiciones()){
            fila[0] = posicion;
            fila[1] = equipo.getNombrePais();
            fila[2] = equipo.getPuntos();
            fila[3] = equipo.getPartidosJugados();
            fila[4] = equipo.getPartidosGanados();
            fila[5] = equipo.getPartidosEmpatados();
            fila[6] = equipo.getPartidosPerdidos();
            fila[7] = equipo.getGolesFavor();
            fila[8] = equipo.getGolesContra();
            fila[9] = equipo.getGolesDiferencia();
            model.addRow(fila);
            
            posicion++;
        }
    }
    
    public void updateGoleadores() throws SQLException{
        clearTable(tableGoleadores);
        DefaultTableModel model = (DefaultTableModel)tablePosiciones.getModel();
        Object[] fila = new Object[2];
        Pair<ArrayList<Jugador>, ArrayList<Integer>> goleadores = Logic.getInstance().getGoleadores();
        int i = 0;
        for(Jugador jugador : goleadores.getKey()){
            fila[0] = jugador;
            fila[1] = goleadores.getValue().get(i++);
            model.addRow(fila);
        }
    }
    
    public void updateEquipos() throws SQLException, Exception{
        clearTable(tableEquipos);
        DefaultTableModel model = (DefaultTableModel)tableEquipos.getModel();
        Object[] fila = new Object[2];
        for(Confederacion confederacion : Logic.getInstance().getConfederaciones()){
            for(Equipo equipo : confederacion.getEquipos()){
                fila[0] = equipo;
                fila[1] = confederacion;
                model.addRow(fila);
            }
        }
    }
    
    public void update(){
        try {
            //updatePartidos();
            updateTablasGrupos();
            //updatePosiciones();
            //updateGoleadores();
            updateEquipos();
        } catch (Exception ex) {
            UI.getInstance().displayError("Al cargar información de la base de datos:\n"+ex.getMessage(), this);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuGrupos = new javax.swing.JPopupMenu();
        menuItemGruposReporte = new javax.swing.JMenuItem();
        popupMenuPosiciones = new javax.swing.JPopupMenu();
        popupMenuPosicionesReporte = new javax.swing.JMenuItem();
        popupMenuPartidos = new javax.swing.JPopupMenu();
        menuItemPartidosCrear = new javax.swing.JMenuItem();
        menuItemPartidosBorrar = new javax.swing.JMenuItem();
        menuItemPartidosModificar = new javax.swing.JMenuItem();
        menuItemPartidosReporte = new javax.swing.JMenuItem();
        menuItemPartidosReporteTodos = new javax.swing.JMenuItem();
        popupMenuGoleadores = new javax.swing.JPopupMenu();
        menuItemGoleadoresReporte = new javax.swing.JMenuItem();
        popupMenuEquipos = new javax.swing.JPopupMenu();
        menuItemEquiposReporte = new javax.swing.JMenuItem();
        menuItemEquiposReporteTodos = new javax.swing.JMenuItem();
        menuItemEquiposCrear = new javax.swing.JMenuItem();
        menuItemEquiposModificar = new javax.swing.JMenuItem();
        menuItemEquiposBorrar = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        tabbedPaneGrupos = new javax.swing.JTabbedPane();
        scrollpaneGrupoA = new javax.swing.JScrollPane();
        tableGrupoA = new javax.swing.JTable();
        scrollpaneGrupoB = new javax.swing.JScrollPane();
        tableGrupoB = new javax.swing.JTable();
        scrollpaneGrupoC = new javax.swing.JScrollPane();
        tableGrupoC = new javax.swing.JTable();
        scrollpaneGrupoD = new javax.swing.JScrollPane();
        tableGrupoD = new javax.swing.JTable();
        scrollpaneGrupoE = new javax.swing.JScrollPane();
        tableGrupoE = new javax.swing.JTable();
        scrollpaneGrupoF = new javax.swing.JScrollPane();
        tableGrupoF = new javax.swing.JTable();
        scrollpaneGrupoG = new javax.swing.JScrollPane();
        tableGrupoG = new javax.swing.JTable();
        scrollpaneGrupoH = new javax.swing.JScrollPane();
        tableGrupoH = new javax.swing.JTable();
        scrollpanePosiciones = new javax.swing.JScrollPane();
        tablePosiciones = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        scrollpaneGoleadores = new javax.swing.JScrollPane();
        tableGoleadores = new javax.swing.JTable();
        scrollpanePartidos = new javax.swing.JScrollPane();
        tablePartidos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        scrollpaneEquipos = new javax.swing.JScrollPane();
        tableEquipos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        menubar = new javax.swing.JMenuBar();
        menuOpciones = new javax.swing.JMenu();
        menuDiccionarioDatos = new javax.swing.JMenuItem();
        menuVerBracket = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenuItem();

        menuItemGruposReporte.setText("Crear Reporte");
        menuItemGruposReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGruposReporteActionPerformed(evt);
            }
        });
        popupMenuGrupos.add(menuItemGruposReporte);

        popupMenuPosicionesReporte.setText("Crear Reporte");
        popupMenuPosicionesReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupMenuPosicionesReporteActionPerformed(evt);
            }
        });
        popupMenuPosiciones.add(popupMenuPosicionesReporte);

        menuItemPartidosCrear.setText("Crear Partido");
        menuItemPartidosCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPartidosCrearActionPerformed(evt);
            }
        });
        popupMenuPartidos.add(menuItemPartidosCrear);

        menuItemPartidosBorrar.setText("Borrar Partido Seleccionado");
        popupMenuPartidos.add(menuItemPartidosBorrar);

        menuItemPartidosModificar.setText("Modificar Partido Selecionado");
        menuItemPartidosModificar.setActionCommand("");
        popupMenuPartidos.add(menuItemPartidosModificar);

        menuItemPartidosReporte.setText("Crear Reporte del Partido Seleccionado");
        menuItemPartidosReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPartidosReporteActionPerformed(evt);
            }
        });
        popupMenuPartidos.add(menuItemPartidosReporte);

        menuItemPartidosReporteTodos.setText("Crear Reporte de TODOS los Partidos");
        menuItemPartidosReporteTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPartidosReporteTodosActionPerformed(evt);
            }
        });
        popupMenuPartidos.add(menuItemPartidosReporteTodos);

        menuItemGoleadoresReporte.setText("Crear Reporte");
        menuItemGoleadoresReporte.setActionCommand("");
        menuItemGoleadoresReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGoleadoresReporteActionPerformed(evt);
            }
        });
        popupMenuGoleadores.add(menuItemGoleadoresReporte);

        menuItemEquiposReporte.setText("Crear Reporte De La Confederación Seleccionada");
        menuItemEquiposReporte.setToolTipText("");
        menuItemEquiposReporte.setActionCommand("");
        menuItemEquiposReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEquiposReporteActionPerformed(evt);
            }
        });
        popupMenuEquipos.add(menuItemEquiposReporte);

        menuItemEquiposReporteTodos.setText("Crear Reporte De Todas las Confederacciones");
        menuItemEquiposReporteTodos.setActionCommand("");
        menuItemEquiposReporteTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEquiposReporteTodosActionPerformed(evt);
            }
        });
        popupMenuEquipos.add(menuItemEquiposReporteTodos);

        menuItemEquiposCrear.setText("Crear Equipo");
        menuItemEquiposCrear.setActionCommand("");
        menuItemEquiposCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEquiposCrearActionPerformed(evt);
            }
        });
        popupMenuEquipos.add(menuItemEquiposCrear);

        menuItemEquiposModificar.setText("Modificar Equipo Seleccionado");
        menuItemEquiposModificar.setActionCommand("");
        menuItemEquiposModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEquiposModificarActionPerformed(evt);
            }
        });
        popupMenuEquipos.add(menuItemEquiposModificar);

        menuItemEquiposBorrar.setText("Borrar Equipo Seleccionado");
        menuItemEquiposBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEquiposBorrarActionPerformed(evt);
            }
        });
        popupMenuEquipos.add(menuItemEquiposBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Posiciones Generales");

        tabbedPaneGrupos.setComponentPopupMenu(popupMenuGrupos);

        scrollpaneGrupoA.setInheritsPopupMenu(true);

        tableGrupoA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posición", "Equipo", "Puntos", "PJ", "PG", "PE", "PP", "GF", "GC", "Dif"
            }
        ));
        tableGrupoA.setInheritsPopupMenu(true);
        tableGrupoA.getTableHeader().setReorderingAllowed(false);
        scrollpaneGrupoA.setViewportView(tableGrupoA);
        if (tableGrupoA.getColumnModel().getColumnCount() > 0) {
            tableGrupoA.getColumnModel().getColumn(0).setHeaderValue("Posición");
            tableGrupoA.getColumnModel().getColumn(1).setMinWidth(20);
            tableGrupoA.getColumnModel().getColumn(2).setHeaderValue("Puntos");
            tableGrupoA.getColumnModel().getColumn(3).setHeaderValue("PJ");
            tableGrupoA.getColumnModel().getColumn(4).setHeaderValue("PG");
            tableGrupoA.getColumnModel().getColumn(5).setHeaderValue("PE");
            tableGrupoA.getColumnModel().getColumn(6).setHeaderValue("PP");
            tableGrupoA.getColumnModel().getColumn(7).setHeaderValue("GF");
            tableGrupoA.getColumnModel().getColumn(8).setHeaderValue("GC");
            tableGrupoA.getColumnModel().getColumn(9).setHeaderValue("Dif");
        }

        tabbedPaneGrupos.addTab("        A        ", scrollpaneGrupoA);

        scrollpaneGrupoB.setInheritsPopupMenu(true);

        tableGrupoB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posición", "Equipo", "Puntos", "PJ", "PG", "PE", "PP", "GF", "GC", "Dif"
            }
        ));
        tableGrupoB.setInheritsPopupMenu(true);
        tableGrupoB.getTableHeader().setReorderingAllowed(false);
        scrollpaneGrupoB.setViewportView(tableGrupoB);
        if (tableGrupoB.getColumnModel().getColumnCount() > 0) {
            tableGrupoB.getColumnModel().getColumn(0).setHeaderValue("Posición");
            tableGrupoB.getColumnModel().getColumn(1).setMinWidth(20);
            tableGrupoB.getColumnModel().getColumn(2).setHeaderValue("Puntos");
            tableGrupoB.getColumnModel().getColumn(3).setHeaderValue("PJ");
            tableGrupoB.getColumnModel().getColumn(4).setHeaderValue("PG");
            tableGrupoB.getColumnModel().getColumn(5).setHeaderValue("PE");
            tableGrupoB.getColumnModel().getColumn(6).setHeaderValue("PP");
            tableGrupoB.getColumnModel().getColumn(7).setHeaderValue("GF");
            tableGrupoB.getColumnModel().getColumn(8).setHeaderValue("GC");
            tableGrupoB.getColumnModel().getColumn(9).setHeaderValue("Dif");
        }

        tabbedPaneGrupos.addTab("        B        ", scrollpaneGrupoB);

        scrollpaneGrupoC.setInheritsPopupMenu(true);

        tableGrupoC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posición", "Equipo", "Puntos", "PJ", "PG", "PE", "PP", "GF", "GC", "Dif"
            }
        ));
        tableGrupoC.setInheritsPopupMenu(true);
        tableGrupoC.getTableHeader().setReorderingAllowed(false);
        scrollpaneGrupoC.setViewportView(tableGrupoC);
        if (tableGrupoC.getColumnModel().getColumnCount() > 0) {
            tableGrupoC.getColumnModel().getColumn(0).setHeaderValue("Posición");
            tableGrupoC.getColumnModel().getColumn(1).setMinWidth(20);
            tableGrupoC.getColumnModel().getColumn(2).setHeaderValue("Puntos");
            tableGrupoC.getColumnModel().getColumn(3).setHeaderValue("PJ");
            tableGrupoC.getColumnModel().getColumn(4).setHeaderValue("PG");
            tableGrupoC.getColumnModel().getColumn(5).setHeaderValue("PE");
            tableGrupoC.getColumnModel().getColumn(6).setHeaderValue("PP");
            tableGrupoC.getColumnModel().getColumn(7).setHeaderValue("GF");
            tableGrupoC.getColumnModel().getColumn(8).setHeaderValue("GC");
            tableGrupoC.getColumnModel().getColumn(9).setHeaderValue("Dif");
        }

        tabbedPaneGrupos.addTab("        C       ", scrollpaneGrupoC);

        scrollpaneGrupoD.setInheritsPopupMenu(true);

        tableGrupoD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posición", "Equipo", "Puntos", "PJ", "PG", "PE", "PP", "GF", "GC", "Dif"
            }
        ));
        tableGrupoD.setInheritsPopupMenu(true);
        tableGrupoD.getTableHeader().setReorderingAllowed(false);
        scrollpaneGrupoD.setViewportView(tableGrupoD);
        if (tableGrupoD.getColumnModel().getColumnCount() > 0) {
            tableGrupoD.getColumnModel().getColumn(0).setHeaderValue("Posición");
            tableGrupoD.getColumnModel().getColumn(1).setMinWidth(20);
            tableGrupoD.getColumnModel().getColumn(2).setHeaderValue("Puntos");
            tableGrupoD.getColumnModel().getColumn(3).setHeaderValue("PJ");
            tableGrupoD.getColumnModel().getColumn(4).setHeaderValue("PG");
            tableGrupoD.getColumnModel().getColumn(5).setHeaderValue("PE");
            tableGrupoD.getColumnModel().getColumn(6).setHeaderValue("PP");
            tableGrupoD.getColumnModel().getColumn(7).setHeaderValue("GF");
            tableGrupoD.getColumnModel().getColumn(8).setHeaderValue("GC");
            tableGrupoD.getColumnModel().getColumn(9).setHeaderValue("Dif");
        }

        tabbedPaneGrupos.addTab("        D        ", scrollpaneGrupoD);

        scrollpaneGrupoE.setInheritsPopupMenu(true);

        tableGrupoE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posición", "Equipo", "Puntos", "PJ", "PG", "PE", "PP", "GF", "GC", "Dif"
            }
        ));
        tableGrupoE.setInheritsPopupMenu(true);
        tableGrupoE.getTableHeader().setReorderingAllowed(false);
        scrollpaneGrupoE.setViewportView(tableGrupoE);
        if (tableGrupoE.getColumnModel().getColumnCount() > 0) {
            tableGrupoE.getColumnModel().getColumn(0).setHeaderValue("Posición");
            tableGrupoE.getColumnModel().getColumn(1).setMinWidth(20);
            tableGrupoE.getColumnModel().getColumn(2).setHeaderValue("Puntos");
            tableGrupoE.getColumnModel().getColumn(3).setHeaderValue("PJ");
            tableGrupoE.getColumnModel().getColumn(4).setHeaderValue("PG");
            tableGrupoE.getColumnModel().getColumn(5).setHeaderValue("PE");
            tableGrupoE.getColumnModel().getColumn(6).setHeaderValue("PP");
            tableGrupoE.getColumnModel().getColumn(7).setHeaderValue("GF");
            tableGrupoE.getColumnModel().getColumn(8).setHeaderValue("GC");
            tableGrupoE.getColumnModel().getColumn(9).setHeaderValue("Dif");
        }

        tabbedPaneGrupos.addTab("        E        ", scrollpaneGrupoE);

        scrollpaneGrupoF.setInheritsPopupMenu(true);

        tableGrupoF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posición", "Equipo", "Puntos", "PJ", "PG", "PE", "PP", "GF", "GC", "Dif"
            }
        ));
        tableGrupoF.setInheritsPopupMenu(true);
        tableGrupoF.getTableHeader().setReorderingAllowed(false);
        scrollpaneGrupoF.setViewportView(tableGrupoF);
        if (tableGrupoF.getColumnModel().getColumnCount() > 0) {
            tableGrupoF.getColumnModel().getColumn(0).setHeaderValue("Posición");
            tableGrupoF.getColumnModel().getColumn(1).setMinWidth(20);
            tableGrupoF.getColumnModel().getColumn(2).setHeaderValue("Puntos");
            tableGrupoF.getColumnModel().getColumn(3).setHeaderValue("PJ");
            tableGrupoF.getColumnModel().getColumn(4).setHeaderValue("PG");
            tableGrupoF.getColumnModel().getColumn(5).setHeaderValue("PE");
            tableGrupoF.getColumnModel().getColumn(6).setHeaderValue("PP");
            tableGrupoF.getColumnModel().getColumn(7).setHeaderValue("GF");
            tableGrupoF.getColumnModel().getColumn(8).setHeaderValue("GC");
            tableGrupoF.getColumnModel().getColumn(9).setHeaderValue("Dif");
        }

        tabbedPaneGrupos.addTab("        F        ", scrollpaneGrupoF);

        scrollpaneGrupoG.setInheritsPopupMenu(true);

        tableGrupoG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posición", "Equipo", "Puntos", "PJ", "PG", "PE", "PP", "GF", "GC", "Dif"
            }
        ));
        tableGrupoG.setInheritsPopupMenu(true);
        tableGrupoG.getTableHeader().setReorderingAllowed(false);
        scrollpaneGrupoG.setViewportView(tableGrupoG);
        if (tableGrupoG.getColumnModel().getColumnCount() > 0) {
            tableGrupoG.getColumnModel().getColumn(0).setHeaderValue("Posición");
            tableGrupoG.getColumnModel().getColumn(1).setMinWidth(20);
            tableGrupoG.getColumnModel().getColumn(2).setHeaderValue("Puntos");
            tableGrupoG.getColumnModel().getColumn(3).setHeaderValue("PJ");
            tableGrupoG.getColumnModel().getColumn(4).setHeaderValue("PG");
            tableGrupoG.getColumnModel().getColumn(5).setHeaderValue("PE");
            tableGrupoG.getColumnModel().getColumn(6).setHeaderValue("PP");
            tableGrupoG.getColumnModel().getColumn(7).setHeaderValue("GF");
            tableGrupoG.getColumnModel().getColumn(8).setHeaderValue("GC");
            tableGrupoG.getColumnModel().getColumn(9).setHeaderValue("Dif");
        }

        tabbedPaneGrupos.addTab("        G        ", scrollpaneGrupoG);

        scrollpaneGrupoH.setInheritsPopupMenu(true);

        tableGrupoH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posición", "Equipo", "Puntos", "PJ", "PG", "PE", "PP", "GF", "GC", "Dif"
            }
        ));
        tableGrupoH.setInheritsPopupMenu(true);
        tableGrupoH.getTableHeader().setReorderingAllowed(false);
        scrollpaneGrupoH.setViewportView(tableGrupoH);
        if (tableGrupoH.getColumnModel().getColumnCount() > 0) {
            tableGrupoH.getColumnModel().getColumn(0).setHeaderValue("Posición");
            tableGrupoH.getColumnModel().getColumn(1).setMinWidth(20);
            tableGrupoH.getColumnModel().getColumn(2).setHeaderValue("Puntos");
            tableGrupoH.getColumnModel().getColumn(3).setHeaderValue("PJ");
            tableGrupoH.getColumnModel().getColumn(4).setHeaderValue("PG");
            tableGrupoH.getColumnModel().getColumn(5).setHeaderValue("PE");
            tableGrupoH.getColumnModel().getColumn(6).setHeaderValue("PP");
            tableGrupoH.getColumnModel().getColumn(7).setHeaderValue("GF");
            tableGrupoH.getColumnModel().getColumn(8).setHeaderValue("GC");
            tableGrupoH.getColumnModel().getColumn(9).setHeaderValue("Dif");
        }

        tabbedPaneGrupos.addTab("        H        ", scrollpaneGrupoH);

        scrollpanePosiciones.setComponentPopupMenu(popupMenuPosiciones);

        tablePosiciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posición", "Equipo", "Puntos", "PJ", "PG", "PE", "PP", "GF", "GC", "Dif"
            }
        ));
        tablePosiciones.setInheritsPopupMenu(true);
        tablePosiciones.getTableHeader().setReorderingAllowed(false);
        scrollpanePosiciones.setViewportView(tablePosiciones);
        if (tablePosiciones.getColumnModel().getColumnCount() > 0) {
            tablePosiciones.getColumnModel().getColumn(0).setHeaderValue("Posición");
            tablePosiciones.getColumnModel().getColumn(1).setMinWidth(20);
            tablePosiciones.getColumnModel().getColumn(2).setHeaderValue("Puntos");
            tablePosiciones.getColumnModel().getColumn(3).setHeaderValue("PJ");
            tablePosiciones.getColumnModel().getColumn(4).setHeaderValue("PG");
            tablePosiciones.getColumnModel().getColumn(5).setHeaderValue("PE");
            tablePosiciones.getColumnModel().getColumn(6).setHeaderValue("PP");
            tablePosiciones.getColumnModel().getColumn(7).setHeaderValue("GF");
            tablePosiciones.getColumnModel().getColumn(8).setHeaderValue("GC");
            tablePosiciones.getColumnModel().getColumn(9).setHeaderValue("Dif");
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Grupos");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Partidos");

        scrollpaneGoleadores.setComponentPopupMenu(popupMenuGoleadores);

        tableGoleadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Jugador", "Goles"
            }
        ));
        tableGoleadores.setColumnSelectionAllowed(true);
        tableGoleadores.setInheritsPopupMenu(true);
        tableGoleadores.getTableHeader().setReorderingAllowed(false);
        scrollpaneGoleadores.setViewportView(tableGoleadores);
        tableGoleadores.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tableGoleadores.getColumnModel().getColumnCount() > 0) {
            tableGoleadores.getColumnModel().getColumn(0).setMinWidth(20);
        }

        scrollpanePartidos.setComponentPopupMenu(popupMenuPartidos);

        tablePartidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Equipo 1", "Equipo 2", "Resultado", "Fecha", "Lugar", "ID Partido"
            }
        ));
        tablePartidos.setColumnSelectionAllowed(true);
        tablePartidos.setInheritsPopupMenu(true);
        tablePartidos.getTableHeader().setReorderingAllowed(false);
        scrollpanePartidos.setViewportView(tablePartidos);
        tablePartidos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablePartidos.getColumnModel().getColumnCount() > 0) {
            tablePartidos.getColumnModel().getColumn(0).setMinWidth(20);
        }

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Equipos");

        scrollpaneEquipos.setComponentPopupMenu(popupMenuEquipos);

        tableEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Equipo", "Confederación"
            }
        ));
        tableEquipos.setInheritsPopupMenu(true);
        tableEquipos.getTableHeader().setReorderingAllowed(false);
        scrollpaneEquipos.setViewportView(tableEquipos);
        tableEquipos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tableEquipos.getColumnModel().getColumnCount() > 0) {
            tableEquipos.getColumnModel().getColumn(0).setMinWidth(20);
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Goleadores");

        menuOpciones.setText("Opciones");

        menuDiccionarioDatos.setText("Ver Diccionario de Datos");
        menuDiccionarioDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDiccionarioDatosActionPerformed(evt);
            }
        });
        menuOpciones.add(menuDiccionarioDatos);

        menuVerBracket.setText("Ver Bracket de Eliminación");
        menuVerBracket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVerBracketActionPerformed(evt);
            }
        });
        menuOpciones.add(menuVerBracket);

        menuAyuda.setText("Ayuda");
        menuAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAyudaActionPerformed(evt);
            }
        });
        menuOpciones.add(menuAyuda);

        menubar.add(menuOpciones);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollpanePosiciones, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabbedPaneGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollpanePartidos, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scrollpaneGoleadores, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(scrollpaneEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollpanePartidos, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(tabbedPaneGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollpanePosiciones)
                    .addComponent(scrollpaneGoleadores)
                    .addComponent(scrollpaneEquipos))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAyudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuAyudaActionPerformed

    private void menuVerBracketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVerBracketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuVerBracketActionPerformed

    private void menuItemGruposReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGruposReporteActionPerformed
        try {
            Logic.getInstance().getPDFPrinter().crearReporteGrupos();
        } catch (SQLException ex) {
            UI.getInstance().displayError("Error al generar el PDF de grupos:\n"+ex.getMessage(), this);
        }
    }//GEN-LAST:event_menuItemGruposReporteActionPerformed

    private void menuItemGoleadoresReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGoleadoresReporteActionPerformed
        try {
            Logic.getInstance().getPDFPrinter().crearReporteGoleadores();
        } catch (SQLException ex) {
            UI.getInstance().displayError("Error al generar el PDF de goleadores:\n"+ex.getMessage(), this);
        }
    }//GEN-LAST:event_menuItemGoleadoresReporteActionPerformed

    private void popupMenuPosicionesReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupMenuPosicionesReporteActionPerformed
        try {
            Logic.getInstance().getPDFPrinter().crearReportePosiciones();
        } catch (SQLException ex) {
            UI.getInstance().displayError("Error al generar el PDF de posiciones generales:\n"+ex.getMessage(), this);
        }
    }//GEN-LAST:event_popupMenuPosicionesReporteActionPerformed

    private void menuItemPartidosReporteTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPartidosReporteTodosActionPerformed
        try {
            Logic.getInstance().getPDFPrinter().crearReportePartidos();
        } catch (SQLException ex) {
            UI.getInstance().displayError("Error al generar el PDF de todos los partidos:\n"+ex.getMessage(), this);
        }
    }//GEN-LAST:event_menuItemPartidosReporteTodosActionPerformed

    private void menuItemPartidosReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPartidosReporteActionPerformed
        try {
            int selectedRow = tablePartidos.getSelectedRow();
            if(selectedRow != -1){
                Partido partido = (Partido)(tablePartidos.getModel().getValueAt(selectedRow, 5));
                Logic.getInstance().getPDFPrinter().crearReportePartido(partido);
            }
            else
                UI.getInstance().displayError("No ha seleccionado ningún partido.", this);
        } catch (SQLException ex) {
            UI.getInstance().displayError("Error al generar el PDF de Posiciones Generales:\n"+ex.getMessage(), this);
        }
    }//GEN-LAST:event_menuItemPartidosReporteActionPerformed

    private void menuItemEquiposReporteTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEquiposReporteTodosActionPerformed
        try {
            Logic.getInstance().getPDFPrinter().crearReporteConfederaciones();
            UI.getInstance().displayInfo("PDF generado exitosamente.", this);
        } catch (SQLException ex) {
            UI.getInstance().displayError("Error SQL al generar el PDF de las Confederaciones:\n"+ex.getMessage(), this);
        } catch (FileNotFoundException | DocumentException ex) {
            UI.getInstance().displayError("Error durante la creación del archivo PDF de las Confederaciones:\n"+ex.getMessage(), this);
        } catch (Exception ex){
            UI.getInstance().displayError("Error al intentar conseguir la informacion de equipos de la base de datos:\n"+ex.getMessage(), this);
        }
    }//GEN-LAST:event_menuItemEquiposReporteTodosActionPerformed

    private void menuItemEquiposCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEquiposCrearActionPerformed
            new VentanaEquipo(null, this).setVisible(true);
    }//GEN-LAST:event_menuItemEquiposCrearActionPerformed

    private void menuItemEquiposModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEquiposModificarActionPerformed
        int selectedRow = tableEquipos.getSelectedRow();
        if(selectedRow != -1){
            Equipo equipo = (Equipo)(tableEquipos.getModel().getValueAt(selectedRow, 0));
            new VentanaEquipo(equipo, this).setVisible(true);
        }else
            UI.getInstance().displayError("Debe seleccionar un equipo para modificar.", this);
    }//GEN-LAST:event_menuItemEquiposModificarActionPerformed

    private void menuItemEquiposBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEquiposBorrarActionPerformed
        int selectedRow = tableEquipos.getSelectedRow();
        if(selectedRow != -1){
            try {
                Equipo equipo = (Equipo)(tableEquipos.getModel().getValueAt(selectedRow, 0));
                Logic.getInstance().borrarEquipo(equipo);
                UI.getInstance().displayInfo("Equipo eliminado satisfactoriamente.", this);
                this.update();
            } catch (SQLException ex) {
                UI.getInstance().displayError("Error al borrar equipo:\n"+ex.getMessage(), this);
            }
        }else
            UI.getInstance().displayError("Debe seleccionar un equipo para borrar.", this);
    }//GEN-LAST:event_menuItemEquiposBorrarActionPerformed

    private void menuItemEquiposReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEquiposReporteActionPerformed
        int selectedRow = tableEquipos.getSelectedRow();
        if(selectedRow != -1){
            Confederacion confederacion = (Confederacion)(tableEquipos.getModel().getValueAt(selectedRow, 1));
            try {
                Logic.getInstance().getPDFPrinter().crearReporteConfederacion(confederacion);
                UI.getInstance().displayInfo("PDF generado exitosamente.", this);
            } catch (SQLException ex) {
                UI.getInstance().displayError("Error de SQL al generar PDF de "+confederacion.getCodigo()+":\n"+ex.getMessage(), this);
            } catch (FileNotFoundException | DocumentException ex) {
                UI.getInstance().displayError("Error al generar PDF de "+confederacion.getCodigo()+":\n"+ex.getMessage(), this);
            }
        }else
            UI.getInstance().displayError("Debe seleccionar una confederación para generar un reporte.", this);
    }//GEN-LAST:event_menuItemEquiposReporteActionPerformed

    private void menuDiccionarioDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDiccionarioDatosActionPerformed
        try {
            new VentanaDiccionarioDatos(this).setVisible(true);
        } catch (SQLException ex) {
            UI.getInstance().displayError("Error al intentar conseguir eel diccionario de datos:\n"+ex.getMessage(), this);
        }
    }//GEN-LAST:event_menuDiccionarioDatosActionPerformed

    private void menuItemPartidosCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPartidosCrearActionPerformed
        new VentanaPartido(null, this).setVisible(true);
    }//GEN-LAST:event_menuItemPartidosCrearActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem menuAyuda;
    private javax.swing.JMenuItem menuDiccionarioDatos;
    private javax.swing.JMenuItem menuItemEquiposBorrar;
    private javax.swing.JMenuItem menuItemEquiposCrear;
    private javax.swing.JMenuItem menuItemEquiposModificar;
    private javax.swing.JMenuItem menuItemEquiposReporte;
    private javax.swing.JMenuItem menuItemEquiposReporteTodos;
    private javax.swing.JMenuItem menuItemGoleadoresReporte;
    private javax.swing.JMenuItem menuItemGruposReporte;
    private javax.swing.JMenuItem menuItemPartidosBorrar;
    private javax.swing.JMenuItem menuItemPartidosCrear;
    private javax.swing.JMenuItem menuItemPartidosModificar;
    private javax.swing.JMenuItem menuItemPartidosReporte;
    private javax.swing.JMenuItem menuItemPartidosReporteTodos;
    private javax.swing.JMenu menuOpciones;
    private javax.swing.JMenuItem menuVerBracket;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JPopupMenu popupMenuEquipos;
    private javax.swing.JPopupMenu popupMenuGoleadores;
    private javax.swing.JPopupMenu popupMenuGrupos;
    private javax.swing.JPopupMenu popupMenuPartidos;
    private javax.swing.JPopupMenu popupMenuPosiciones;
    private javax.swing.JMenuItem popupMenuPosicionesReporte;
    private javax.swing.JScrollPane scrollpaneEquipos;
    private javax.swing.JScrollPane scrollpaneGoleadores;
    private javax.swing.JScrollPane scrollpaneGrupoA;
    private javax.swing.JScrollPane scrollpaneGrupoB;
    private javax.swing.JScrollPane scrollpaneGrupoC;
    private javax.swing.JScrollPane scrollpaneGrupoD;
    private javax.swing.JScrollPane scrollpaneGrupoE;
    private javax.swing.JScrollPane scrollpaneGrupoF;
    private javax.swing.JScrollPane scrollpaneGrupoG;
    private javax.swing.JScrollPane scrollpaneGrupoH;
    private javax.swing.JScrollPane scrollpanePartidos;
    private javax.swing.JScrollPane scrollpanePosiciones;
    private javax.swing.JTabbedPane tabbedPaneGrupos;
    private javax.swing.JTable tableEquipos;
    private javax.swing.JTable tableGoleadores;
    private javax.swing.JTable tableGrupoA;
    private javax.swing.JTable tableGrupoB;
    private javax.swing.JTable tableGrupoC;
    private javax.swing.JTable tableGrupoD;
    private javax.swing.JTable tableGrupoE;
    private javax.swing.JTable tableGrupoF;
    private javax.swing.JTable tableGrupoG;
    private javax.swing.JTable tableGrupoH;
    private javax.swing.JTable tablePartidos;
    private javax.swing.JTable tablePosiciones;
    // End of variables declaration//GEN-END:variables
}
