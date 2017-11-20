package ui;

import javax.swing.DefaultListModel;
import logic.Equipo;
import logic.Logic;
import logic.partido.Partido;
import logic.partido.Sede;
import logic.persona.Arbitro;
import logic.persona.Asistente;
import logic.persona.Jugador;

public class VentanaPartido extends javax.swing.JDialog {

    public VentanaPartido(Partido partido, java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        accionesList.setModel(new DefaultListModel());
        arbitrosList.setModel(new DefaultListModel());
        asistentes1List.setModel(new DefaultListModel());
        asistentes2List.setModel(new DefaultListModel());
        cambios1List.setModel(new DefaultListModel());
        cambios2List.setModel(new DefaultListModel());
        jugadores1List.setModel(new DefaultListModel());
        jugadores2List.setModel(new DefaultListModel());
        penalesList.setModel(new DefaultListModel());
        
        if(partido != null){
            this.setTitle("Edición de Partido");
            cargarImmutables();
            this.equipo1ComboBox.setSelectedItem(partido.getAlineacion1().getEquipo());
            this.equipo2ComboBox.setSelectedItem(partido.getAlineacion2().getEquipo());
        }
        else{
            this.setTitle("Creación de Partido");
            cargarImmutables();
        }
    }
    
    private void cargarImmutables(){
        try {
            for(Equipo equipo : Logic.getInstance().getEquipos()){
                equipo1ComboBox.addItem(equipo);
                equipo2ComboBox.addItem(equipo);
            }

            for(Sede sede : Logic.getInstance().getSedes())
                this.sedeComboBox.addItem(sede);
            
            DefaultListModel modelArbitros = (DefaultListModel)arbitrosList.getModel();
            for(Arbitro arbitro : Logic.getInstance().getArbitros())
                modelArbitros.addElement(arbitro);
            etapaComboBox.addItem(Partido.FASE_GRUPOS);
            etapaComboBox.addItem(Partido.OCTAVOS_FINAL);
            etapaComboBox.addItem(Partido.CUARTOS_FINAL);
            etapaComboBox.addItem(Partido.SEMIFINALES);
            etapaComboBox.addItem(Partido.FINAL);
            etapaComboBox.addItem(Partido.TERCER_LUGAR);
        } catch (Exception ex) {
            UI.getInstance().displayError("Error al conseguir los equipos de la base de datos:\n"+ex.getMessage(), this);
        }
    }
    
    private void reset(){
        ((DefaultListModel)cambios1List.getModel()).clear();
        ((DefaultListModel)cambios2List.getModel()).clear();
        ((DefaultListModel)asistentes1List.getModel()).clear();
        ((DefaultListModel)asistentes2List.getModel()).clear();
        ((DefaultListModel)jugadores1List.getModel()).clear();
        ((DefaultListModel)jugadores2List.getModel()).clear();
        ((DefaultListModel)accionesList.getModel()).clear();
        ((DefaultListModel)penalesList.getModel()).clear();
        
        Equipo equipo1 = (Equipo)equipo1ComboBox.getSelectedItem();
        Equipo equipo2 = (Equipo)equipo2ComboBox.getSelectedItem();
        
        entrenador1Label.setText(equipo1.getEntrenador().toString());
        entrenador2Label.setText(equipo2.getEntrenador().toString());
        for(Jugador jugador : equipo1.getJugadores()){
            ((DefaultListModel)jugadores1List.getModel()).addElement(jugador);
        }
        for(Jugador jugador : equipo2.getJugadores()){
            ((DefaultListModel)jugadores2List.getModel()).addElement(jugador);
        }
        for(Asistente asistente : equipo1.getAsistentes()){
            ((DefaultListModel)asistentes1List.getModel()).addElement(asistente);
        }
        for(Asistente asistente : equipo2.getAsistentes()){
            ((DefaultListModel)asistentes2List.getModel()).addElement(asistente);
        }
    }
    
    private VentanaPartido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuAcciones = new javax.swing.JPopupMenu();
        menuItemAccionesAgregar = new javax.swing.JMenuItem();
        menuItemAccionesBorrar = new javax.swing.JMenuItem();
        menuCambios1 = new javax.swing.JPopupMenu();
        menuItemCambios1Agregar = new javax.swing.JMenuItem();
        menuItemCambios1Borrar = new javax.swing.JMenuItem();
        menuCambios2 = new javax.swing.JPopupMenu();
        menuItemCambios2Agregar = new javax.swing.JMenuItem();
        menuItemCambios2Borrar = new javax.swing.JMenuItem();
        menuPenales = new javax.swing.JPopupMenu();
        menuItemPenalesAgregar = new javax.swing.JMenuItem();
        menuItemPenalesBorrar = new javax.swing.JMenuItem();
        entrenador1Label = new javax.swing.JLabel();
        entrenador2Label = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        cambios2List = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        asistentes2List = new javax.swing.JList();
        jScrollPane7 = new javax.swing.JScrollPane();
        accionesList = new javax.swing.JList();
        jScrollPane8 = new javax.swing.JScrollPane();
        arbitrosList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jugadores2List = new javax.swing.JList();
        jScrollPane9 = new javax.swing.JScrollPane();
        penalesList = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jugadores1List = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        cambios1List = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        asistentes1List = new javax.swing.JList();
        equipo2ComboBox = new javax.swing.JComboBox();
        equipo1ComboBox = new javax.swing.JComboBox();
        sedeComboBox = new javax.swing.JComboBox();
        etapaComboBox = new javax.swing.JComboBox();
        extrasCheckbox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        agregarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();

        menuItemAccionesAgregar.setText("Agregar Acción");
        menuItemAccionesAgregar.setActionCommand("");
        menuAcciones.add(menuItemAccionesAgregar);

        menuItemAccionesBorrar.setText("Borrar Acción");
        menuAcciones.add(menuItemAccionesBorrar);

        menuItemCambios1Agregar.setText("Agregar Cambio");
        menuCambios1.add(menuItemCambios1Agregar);

        menuItemCambios1Borrar.setText("Borrar Cambio");
        menuCambios1.add(menuItemCambios1Borrar);

        menuItemCambios2Agregar.setText("Agregar Cambio");
        menuCambios2.add(menuItemCambios2Agregar);

        menuItemCambios2Borrar.setText("Borrar Cambio");
        menuCambios2.add(menuItemCambios2Borrar);

        menuItemPenalesAgregar.setText("Agregar Penal");
        menuPenales.add(menuItemPenalesAgregar);

        menuItemPenalesBorrar.setText("Borrar Penal");
        menuPenales.add(menuItemPenalesBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        entrenador1Label.setText("DT1");

        entrenador2Label.setText("DT2");

        jScrollPane6.setComponentPopupMenu(menuCambios2);

        cambios2List.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        cambios2List.setToolTipText(UI.POPUP_MENU_MESSAGE);
        cambios2List.setInheritsPopupMenu(true);
        jScrollPane6.setViewportView(cambios2List);

        asistentes2List.setToolTipText("Los asistentes que marque con azul serán protagonistas en el partido.");
        jScrollPane4.setViewportView(asistentes2List);

        jScrollPane7.setComponentPopupMenu(menuAcciones);

        accionesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        accionesList.setToolTipText(UI.POPUP_MENU_MESSAGE);
        accionesList.setInheritsPopupMenu(true);
        jScrollPane7.setViewportView(accionesList);

        arbitrosList.setToolTipText("Los árbitros que marque con azul serán los protagonistas del partido.");
        jScrollPane8.setViewportView(arbitrosList);

        jugadores2List.setToolTipText("Los jugadores que marque con azul serán titulares, los demás suplentes.");
        jScrollPane2.setViewportView(jugadores2List);

        jScrollPane9.setComponentPopupMenu(menuPenales);

        penalesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        penalesList.setToolTipText(UI.POPUP_MENU_MESSAGE);
        penalesList.setInheritsPopupMenu(true);
        jScrollPane9.setViewportView(penalesList);

        jugadores1List.setToolTipText("Los jugadores que marque con azul serán titulares, los demás suplentes.");
        jScrollPane1.setViewportView(jugadores1List);

        jScrollPane5.setComponentPopupMenu(menuCambios1);

        cambios1List.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        cambios1List.setToolTipText(UI.POPUP_MENU_MESSAGE);
        cambios1List.setInheritsPopupMenu(true);
        jScrollPane5.setViewportView(cambios1List);

        asistentes1List.setToolTipText("Los asistentes que marque con azul serán protagonistas en el partido.");
        jScrollPane3.setViewportView(asistentes1List);

        equipo2ComboBox.setToolTipText("Seleccione el segundo equipo.");
        equipo2ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipo2ComboBoxActionPerformed(evt);
            }
        });

        equipo1ComboBox.setToolTipText("Seleccione el equipo 1.");
        equipo1ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipo1ComboBoxActionPerformed(evt);
            }
        });

        sedeComboBox.setToolTipText("Seleccione una sede.");

        etapaComboBox.setToolTipText("Seleccione una etapa durante la cual se jugó este partido.");

        extrasCheckbox.setText("Extras?");
        extrasCheckbox.setToolTipText("El partido tuvo tiempos extras?");
        extrasCheckbox.setActionCommand("");

        jLabel2.setText("Árbitros");

        jLabel4.setText("Acciones");

        jLabel8.setText("Penales");

        jLabel7.setText("Cambios");

        jLabel9.setText("Sede");

        jLabel10.setText("Etapa");

        agregarButton.setText("Agregar");

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(237, 237, 237)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(extrasCheckbox))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(equipo1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(equipo2ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(entrenador1Label))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(entrenador2Label))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sedeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etapaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agregarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(equipo1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equipo2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(entrenador1Label)
                                    .addComponent(entrenador2Label)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(extrasCheckbox)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel9))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(sedeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(etapaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(agregarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void equipo1ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipo1ComboBoxActionPerformed
        if(equipo1ComboBox.getSelectedItem() != null && equipo2ComboBox.getSelectedItem() != null)
            reset();
    }//GEN-LAST:event_equipo1ComboBoxActionPerformed

    private void equipo2ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipo2ComboBoxActionPerformed
        if(equipo1ComboBox.getSelectedItem() != null && equipo2ComboBox.getSelectedItem() != null)
            reset();
    }//GEN-LAST:event_equipo2ComboBoxActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaPartido dialog = new VentanaPartido(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList accionesList;
    private javax.swing.JButton agregarButton;
    private javax.swing.JList arbitrosList;
    private javax.swing.JList asistentes1List;
    private javax.swing.JList asistentes2List;
    private javax.swing.JList cambios1List;
    private javax.swing.JList cambios2List;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JLabel entrenador1Label;
    private javax.swing.JLabel entrenador2Label;
    private javax.swing.JComboBox equipo1ComboBox;
    private javax.swing.JComboBox equipo2ComboBox;
    private javax.swing.JComboBox etapaComboBox;
    private javax.swing.JCheckBox extrasCheckbox;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JList jugadores1List;
    private javax.swing.JList jugadores2List;
    private javax.swing.JPopupMenu menuAcciones;
    private javax.swing.JPopupMenu menuCambios1;
    private javax.swing.JPopupMenu menuCambios2;
    private javax.swing.JMenuItem menuItemAccionesAgregar;
    private javax.swing.JMenuItem menuItemAccionesBorrar;
    private javax.swing.JMenuItem menuItemCambios1Agregar;
    private javax.swing.JMenuItem menuItemCambios1Borrar;
    private javax.swing.JMenuItem menuItemCambios2Agregar;
    private javax.swing.JMenuItem menuItemCambios2Borrar;
    private javax.swing.JMenuItem menuItemPenalesAgregar;
    private javax.swing.JMenuItem menuItemPenalesBorrar;
    private javax.swing.JPopupMenu menuPenales;
    private javax.swing.JList penalesList;
    private javax.swing.JComboBox sedeComboBox;
    // End of variables declaration//GEN-END:variables
}
