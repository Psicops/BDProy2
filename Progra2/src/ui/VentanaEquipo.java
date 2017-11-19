package ui;

import java.awt.Frame;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import logic.Equipo;
import logic.Logic;
import logic.persona.Asistente;
import logic.persona.Federativo;
import logic.persona.Jugador;

public class VentanaEquipo extends javax.swing.JDialog {

    private Equipo equipo;
    private MainWindow parent;
    
    public VentanaEquipo(Equipo equipo, MainWindow parent) {
        super(parent, true);
        this.parent = parent;
        initComponents();
        grupoComboBox.addItem(Logic.GRUPO_A);
        grupoComboBox.addItem(Logic.GRUPO_B);
        grupoComboBox.addItem(Logic.GRUPO_C);
        grupoComboBox.addItem(Logic.GRUPO_D);
        grupoComboBox.addItem(Logic.GRUPO_E);
        grupoComboBox.addItem(Logic.GRUPO_F);
        grupoComboBox.addItem(Logic.GRUPO_G);
        grupoComboBox.addItem(Logic.GRUPO_H);
        if(equipo != null){
            this.equipo = equipo;
            this.setTitle("Edición de Equipo");
            llenarCamposEdicion();
        }
        else{
            this.setTitle("Creación de Equipo");
            llenarCamposCreacion();
        }  
    }
    
    private void llenarCamposCreacion(){
        try {
            for(Asistente entrenador : Logic.getInstance().getEntrenadores()){
                entrenadorComboBox.addItem(entrenador);
            }
            DefaultListModel arbitrosTodosModel = new DefaultListModel();
            for(Asistente asistente : Logic.getInstance().getAsistentesLibres())
                arbitrosTodosModel.addElement(asistente);
            asistentesTodosList.setModel(arbitrosTodosModel);
            
            DefaultListModel modelFederativos = new DefaultListModel();
            for(Federativo federativo : Logic.getInstance().getFederativosLibres())
                modelFederativos.addElement(federativo);
            federativosTodosList.setModel(modelFederativos);
            
            DefaultListModel modelJugadores = new DefaultListModel();
            for(Jugador asistente : Logic.getInstance().getJugadoresLibres())
                modelJugadores.addElement(asistente);
            jugadoresTodosList.setModel(modelJugadores);
        } catch (SQLException ex) {
            UI.getInstance().displayError("Error al cargar los campos de creación:\n"+ex.getMessage(), this);
        }
    }
    
    private void llenarCamposEdicion(){
        try {
            this.nombreTextField.setText(equipo.getNombrePais());
            this.codigoTextField.setText(equipo.getCodigoPais());
            this.grupoComboBox.setSelectedItem(equipo.getGrupo());
            
            for(Asistente entrenador : Logic.getInstance().getEntrenadoresLibres()){
                entrenadorComboBox.addItem(entrenador);
            }
            
            entrenadorComboBox.setSelectedItem(equipo.getEntrenador());
            
            DefaultListModel asistentesTodosModel = new DefaultListModel();
            for(Asistente asistente : Logic.getInstance().getAsistentesLibres())
                asistentesTodosModel.addElement(asistente);
            asistentesTodosList.setModel(asistentesTodosModel);
            
            DefaultListModel asistentesEquipoModel = new DefaultListModel();
            for(Asistente asistente : equipo.getAsistentes())
                asistentesEquipoModel.addElement(asistente);
            asistentesEquipoList.setModel(asistentesEquipoModel);
            
            DefaultListModel federativosTodosModel = new DefaultListModel();
            for(Federativo federativo : Logic.getInstance().getFederativosLibres())
                federativosTodosModel.addElement(federativo);
            federativosTodosList.setModel(federativosTodosModel);
            
            DefaultListModel federativosEquipoModel = new DefaultListModel();
            for(Federativo federativo : equipo.getFederativos())
                federativosEquipoModel.addElement(federativo);
            federativosEquipoList.setModel(federativosEquipoModel);
            
            DefaultListModel jugadoresTodosModel = new DefaultListModel();
            for(Jugador jugador : Logic.getInstance().getJugadoresLibres())
                jugadoresTodosModel.addElement(jugador);
            jugadoresTodosList.setModel(jugadoresTodosModel);
            
            DefaultListModel jugadoresEquipoModel = new DefaultListModel();
            for(Jugador jugador : equipo.getJugadores())
                jugadoresEquipoModel.addElement(jugador);
            jugadoresTodosList.setModel(jugadoresEquipoModel);
        } catch (Exception ex) {
            UI.getInstance().displayError("Error al cargar los campos de edición:\n"+ex.getMessage(), this);
        }
    }

    private VentanaEquipo(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jugadoresEquipoSrollPane = new javax.swing.JScrollPane();
        jugadoresEquipoList = new javax.swing.JList();
        jugadoresTodosScrollPane = new javax.swing.JScrollPane();
        jugadoresTodosList = new javax.swing.JList();
        cancelarButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        federativosEquipoScrollPane = new javax.swing.JScrollPane();
        federativosEquipoList = new javax.swing.JList();
        asistentesTodosScrollPane = new javax.swing.JScrollPane();
        asistentesTodosList = new javax.swing.JList();
        federativosTodosScrollPane = new javax.swing.JScrollPane();
        federativosTodosList = new javax.swing.JList();
        guardarButton = new javax.swing.JButton();
        asistentesEquipoScrollPane = new javax.swing.JScrollPane();
        asistentesEquipoList = new javax.swing.JList();
        entrenadorComboBox = new javax.swing.JComboBox();
        grupoComboBox = new javax.swing.JComboBox();
        codigoTextField = new javax.swing.JTextField();
        nombreTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jugadoresEquipoList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jugadoresEquipoList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jugadoresEquipoListMouseClicked(evt);
            }
        });
        jugadoresEquipoSrollPane.setViewportView(jugadoresEquipoList);

        jugadoresTodosList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jugadoresTodosList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jugadoresTodosListMouseClicked(evt);
            }
        });
        jugadoresTodosScrollPane.setViewportView(jugadoresTodosList);

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("(Haga doble click para agregar o quitar personas del equipo)");

        federativosEquipoList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        federativosEquipoList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                federativosEquipoListMouseClicked(evt);
            }
        });
        federativosEquipoScrollPane.setViewportView(federativosEquipoList);

        asistentesTodosList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        asistentesTodosList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                asistentesTodosListMouseClicked(evt);
            }
        });
        asistentesTodosScrollPane.setViewportView(asistentesTodosList);

        federativosTodosList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        federativosTodosList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                federativosTodosListMouseClicked(evt);
            }
        });
        federativosTodosScrollPane.setViewportView(federativosTodosList);

        guardarButton.setText("Guardar Cambios");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        asistentesEquipoList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        asistentesEquipoList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                asistentesEquipoListMouseClicked(evt);
            }
        });
        asistentesEquipoScrollPane.setViewportView(asistentesEquipoList);

        jLabel1.setText("Nombre");

        jLabel2.setText("Código");

        jLabel4.setText("Entrenador");

        jLabel3.setText("Federativos");

        jLabel5.setText("Equipo Técnico");

        jLabel6.setText("Jugadores");

        jLabel8.setText("Grupo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(277, 277, 277)
                        .addComponent(grupoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(guardarButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(federativosTodosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(federativosEquipoScrollPane))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(273, 273, 273)
                            .addComponent(codigoTextField))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(252, 252, 252)
                                    .addComponent(entrenadorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(asistentesTodosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(asistentesEquipoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jugadoresTodosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jugadoresEquipoSrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(codigoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(grupoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(entrenadorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(federativosTodosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(federativosEquipoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(asistentesTodosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(asistentesEquipoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jugadoresTodosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jugadoresEquipoSrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void federativosTodosListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_federativosTodosListMouseClicked
        if (evt.getClickCount() == 2) {
            moverPersona(federativosTodosList, federativosEquipoList);
        }
    }//GEN-LAST:event_federativosTodosListMouseClicked

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        try {
            //se guardan todas las personas que no fueron asignadas al equipo como libres.
            ArrayList<Asistente> asistentesTodos = new ArrayList(); 
            ArrayList<Federativo> federativosTodos = new ArrayList(); 
            ArrayList<Jugador> jugadoresTodos = new ArrayList();

            for(int i = 0; i< entrenadorComboBox.getItemCount(); i++){
                if(!((Asistente)entrenadorComboBox.getItemAt(i)).equals(entrenadorComboBox.getSelectedItem()))
                    asistentesTodos.add((Asistente)entrenadorComboBox.getItemAt(i));
            }
            for(int i = 0; i < asistentesTodosList.getModel().getSize(); i++)
                asistentesTodos.add((Asistente)asistentesTodosList.getModel().getElementAt(i));
            for(int i = 0; i < federativosTodosList.getModel().getSize(); i++)
                federativosTodos.add((Federativo)federativosTodosList.getModel().getElementAt(i));
            for(int i = 0; i < jugadoresTodosList.getModel().getSize(); i++)
                jugadoresTodos.add((Jugador)jugadoresTodosList.getModel().getElementAt(i));

            Logic.getInstance().setAsistentesLibres(asistentesTodos);
            Logic.getInstance().setFederativosLibres(federativosTodos);
            Logic.getInstance().setJugadoresLibres(jugadoresTodos);

            //se guardan todas las personas y datos del equipo.
            ArrayList<Asistente> asistentesEquipo = new ArrayList(); 
            ArrayList<Federativo> federativosEquipo = new ArrayList(); 
            ArrayList<Jugador> jugadoresEquipo = new ArrayList(); 

            asistentesEquipo.add((Asistente)entrenadorComboBox.getSelectedItem());

            for(int i = 0; i < asistentesEquipoList.getModel().getSize(); i++)
                asistentesEquipo.add((Asistente)asistentesEquipoList.getModel().getElementAt(i));
            for(int i = 0; i < federativosEquipoList.getModel().getSize(); i++)
                federativosEquipo.add((Federativo)federativosEquipoList.getModel().getElementAt(i));
            for(int i = 0; i < jugadoresEquipoList.getModel().getSize(); i++)
                jugadoresEquipo.add((Jugador)jugadoresEquipoList.getModel().getElementAt(i));

            Equipo equipoNuevo = new Equipo(codigoTextField.getText(), nombreTextField.getText(),
                                            (String)grupoComboBox.getSelectedItem(), jugadoresEquipo,
                                            asistentesEquipo, federativosEquipo);
            if(equipo != null)
                Logic.getInstance().updateTeam(equipo, equipoNuevo);
            else
                Logic.getInstance().createTeam(equipoNuevo);
            parent.update();
            UI.getInstance().displayInfo("Equipo agregado exitosamente.", this);
            this.dispose();
        } catch (SQLException ex) {
            UI.getInstance().displayError("Error al crear el equipo:\n"+ex.getMessage(), this);
        }
    }//GEN-LAST:event_guardarButtonActionPerformed

    private void federativosEquipoListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_federativosEquipoListMouseClicked
        if (evt.getClickCount() == 2) {
            moverPersona(federativosEquipoList, federativosTodosList);
        }
    }//GEN-LAST:event_federativosEquipoListMouseClicked

    private void asistentesTodosListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_asistentesTodosListMouseClicked
        if (evt.getClickCount() == 2) {
            moverPersona(asistentesTodosList, asistentesEquipoList);
        }
    }//GEN-LAST:event_asistentesTodosListMouseClicked

    private void asistentesEquipoListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_asistentesEquipoListMouseClicked
        if (evt.getClickCount() == 2) {
            moverPersona(asistentesEquipoList, asistentesTodosList);
        }
    }//GEN-LAST:event_asistentesEquipoListMouseClicked

    private void jugadoresTodosListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jugadoresTodosListMouseClicked
        if (evt.getClickCount() == 2) {
            moverPersona(jugadoresTodosList, jugadoresEquipoList);
        }
    }//GEN-LAST:event_jugadoresTodosListMouseClicked

    private void jugadoresEquipoListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jugadoresEquipoListMouseClicked
        if (evt.getClickCount() == 2) {
            moverPersona(jugadoresEquipoList, jugadoresTodosList);
        }
    }//GEN-LAST:event_jugadoresEquipoListMouseClicked
    
    private void moverPersona(JList desde, JList hacia){
        if(desde.getSelectedValue() != null){
            DefaultListModel haciaModel = (DefaultListModel)hacia.getModel();
            DefaultListModel desdeModel = (DefaultListModel)desde.getModel();
            haciaModel.addElement(desde.getSelectedValue());
            desdeModel.removeElement(desde.getSelectedValue());
        }else
            UI.getInstance().displayError("No ha seleccionado ninguna persona en la lista "+desde.getName(), this);
    }
    
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
            java.util.logging.Logger.getLogger(VentanaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaEquipo dialog = new VentanaEquipo(new javax.swing.JFrame(), true);
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
    private javax.swing.JList asistentesEquipoList;
    private javax.swing.JScrollPane asistentesEquipoScrollPane;
    private javax.swing.JList asistentesTodosList;
    private javax.swing.JScrollPane asistentesTodosScrollPane;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JTextField codigoTextField;
    private javax.swing.JComboBox entrenadorComboBox;
    private javax.swing.JList federativosEquipoList;
    private javax.swing.JScrollPane federativosEquipoScrollPane;
    private javax.swing.JList federativosTodosList;
    private javax.swing.JScrollPane federativosTodosScrollPane;
    private javax.swing.JComboBox grupoComboBox;
    private javax.swing.JButton guardarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jugadoresEquipoList;
    private javax.swing.JScrollPane jugadoresEquipoSrollPane;
    private javax.swing.JList jugadoresTodosList;
    private javax.swing.JScrollPane jugadoresTodosScrollPane;
    private javax.swing.JTextField nombreTextField;
    // End of variables declaration//GEN-END:variables
}
