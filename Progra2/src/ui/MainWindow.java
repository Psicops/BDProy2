package ui;

import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JTable;
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
        //this.update();
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
            fila[0] = partido.getEquipo1().getCodigoPais();
            fila[1] = partido.getEquipo2().getCodigoPais();
            //fila[2] = partido.getMarcadores();
            fila[3] = partido.getFecha();
            fila[4] = partido.getSede().getNombreEstadio();
            model.addRow(fila);
        }
    }
    
    private void updateTablaGrupo(String idGrupo, JTable tabla) throws SQLException{
        clearTable(tabla);
        DefaultTableModel model = (DefaultTableModel)tabla.getModel();
        Object[] fila = new String[10];
        int posicion = 1;
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
    
    public void updateTablasGrupos() throws SQLException{
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
        Object[] fila = new String[2];
        HashMap<Jugador, Integer> goleadores = Logic.getInstance().getGoleadores();
        for(Jugador jugador : goleadores.keySet()){
            fila[0] = jugador.getNombre();
            fila[1] = goleadores.get(jugador);
            model.addRow(fila);
        }
    }
    
    public void updateEquipos() throws SQLException{
        clearTable(tableEquipos);
        DefaultTableModel model = (DefaultTableModel)tablePosiciones.getModel();
        Object[] fila = new String[2];
        for(Confederacion confederacion : Logic.getInstance().getConfederaciones()){
            for(Equipo equipo : confederacion.getEquipos()){
                fila[0] = equipo.getNombrePais();
                fila[1] = confederacion.getNombre();
                model.addRow(fila);
            }
        }
    }
    
    public void update(){
        try {
            updatePartidos();
            updateTablasGrupos();
            updatePosiciones();
            updateGoleadores();
            updateEquipos();
        } catch (SQLException ex) {
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
        popupMenuPosiciones.add(popupMenuPosicionesReporte);

        menuItemPartidosCrear.setText("Crear Partido");
        popupMenuPartidos.add(menuItemPartidosCrear);

        menuItemPartidosBorrar.setText("Borrar Partido Seleccionado");
        popupMenuPartidos.add(menuItemPartidosBorrar);

        menuItemPartidosModificar.setText("Modificar Partido Selecionado");
        menuItemPartidosModificar.setActionCommand("");
        popupMenuPartidos.add(menuItemPartidosModificar);

        menuItemPartidosReporte.setText("Crear Reporte del Partido Seleccionado");
        popupMenuPartidos.add(menuItemPartidosReporte);

        menuItemPartidosReporteTodos.setText("Crear Reporte de TODOS los Partidos");
        popupMenuPartidos.add(menuItemPartidosReporteTodos);

        menuItemGoleadoresReporte.setText("Crear Reporte");
        menuItemGoleadoresReporte.setActionCommand("");
        popupMenuGoleadores.add(menuItemGoleadoresReporte);

        menuItemEquiposReporte.setText("Crear Reporte De La Confederación Seleccionada");
        menuItemEquiposReporte.setToolTipText("");
        menuItemEquiposReporte.setActionCommand("");
        popupMenuEquipos.add(menuItemEquiposReporte);

        menuItemEquiposReporteTodos.setText("jMenuItem1");
        menuItemEquiposReporteTodos.setActionCommand("Crear Reporte De la Condefederación Seleccionada");
        popupMenuEquipos.add(menuItemEquiposReporteTodos);

        menuItemEquiposCrear.setText("Crear Equipo");
        menuItemEquiposCrear.setActionCommand("");
        popupMenuEquipos.add(menuItemEquiposCrear);

        menuItemEquiposModificar.setText("Modificar Equipo Seleccionado");
        menuItemEquiposModificar.setActionCommand("");
        popupMenuEquipos.add(menuItemEquiposModificar);

        menuItemEquiposBorrar.setText("Borrar Equipo Seleccionado");
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
                "Equipo 1", "Equipo 2", "Resultado", "Fecha", "Lugar"
            }
        ));
        tablePartidos.setInheritsPopupMenu(true);
        tablePartidos.getTableHeader().setReorderingAllowed(false);
        scrollpanePartidos.setViewportView(tablePartidos);
        tablePartidos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablePartidos.getColumnModel().getColumnCount() > 0) {
            tablePartidos.getColumnModel().getColumn(0).setMinWidth(20);
            tablePartidos.getColumnModel().getColumn(2).setHeaderValue("Resultado");
            tablePartidos.getColumnModel().getColumn(3).setHeaderValue("Fecha");
            tablePartidos.getColumnModel().getColumn(4).setHeaderValue("Lugar");
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
                    .addComponent(scrollpaneGoleadores, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(scrollpaneEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemGruposReporteActionPerformed

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