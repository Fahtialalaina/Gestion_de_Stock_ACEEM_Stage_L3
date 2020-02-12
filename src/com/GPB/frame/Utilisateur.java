/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import static com.GPB.frame.Password.hashPassword;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import org.bouncycastle.crypto.generators.BCrypt;

/**
 *
 * @author anouer
 */
public class Utilisateur extends javax.swing.JInternalFrame {

    Connection conn4 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    static String test;

    /**
     * Creates new form Examen
     *
     * @throws java.sql.SQLException
     */
    public Utilisateur() throws SQLException {

        initComponents();
        remove_title_bar();
        conn4 = ConexionBD.Conexion();
        Affichage();
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("Taper Login");
        btnnv.setEnabled(true);
        btnsupprimer.setEnabled(false);
        btnmodifier.setEnabled(false);
        btnenregistrer.setEnabled(false);
        clear();
        /*String requete4 = "delete from effectif";
            ps = conn4.prepareStatement(requete4);
            ps.execute();*/
    }

    private void remove_title_bar() {
        putClientProperty("Section.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }
    
    public void tabel() {
        
        Table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        Table.getTableHeader().setOpaque(false);
        Table.getTableHeader().setBackground(new Color(3, 91, 155));
        Table.getTableHeader().setForeground(new Color(255,255,255));
        //TableArticle.setRowHeight(25);
    }

    private void Affichage() {
        try {
            String requete = "select login as 'Login', password as 'Mot de passe', role as 'Rôle' from login_table ";
            ps = conn4.prepareStatement(requete);
            rs = ps.executeQuery();
            Table.setModel(DbUtils.resultSetToTableModel(rs));
            ajusterTable();
            tabel();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }

    }

    public void Deplace() {
        try {

            int row = Table.getSelectedRow();
            Utilisateur.test = (Table.getModel().getValueAt(row, 0).toString());
            String requet = " select * from  login_table where login = '" + test + "' ";
            ps = conn4.prepareStatement(requet);
            rs = ps.executeQuery();

            if (rs.next()) {
                String t1 = rs.getString("login");
                String t2 = rs.getString("idLogin");
                String t3 = rs.getString("password");
                String t4 = rs.getString("role");
                login.setText(t1);
                numero.setText(t2);
                comboRole.setSelectedItem(t4);
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {

            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
    }

    public void clear() {
        try {
            numero.setText("");
            login.setText("");
            mdp.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void ajusterTable() {                                         
        int col = 0, droiteMax = 0, larg = 0, largTotal = 0,
                                    row = 0, tableX = 0, width = 0;
        JTableHeader header = Table.getTableHeader();
        Enumeration columns = Table.getColumnModel().getColumns();
 
        Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        while(columns.hasMoreElements()){                            // longueur maximum du texte ou du titre d'une colonne
            TableColumn column = (TableColumn)columns.nextElement();
            col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            width = (int)Table.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(Table, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for(row = 0; row<Table.getRowCount(); row++){
                int preferedWidth =
                        (int)Table.getCellRenderer(row, col).getTableCellRendererComponent(Table,
                        Table.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);                       // this line is very important
            larg = width+Table.getIntercellSpacing().width;
         //   larg = (larg*13)/10;                            // largeur de la colonne plus un peu pour desserrer
            larg = larg+20;           // mais c'est mieux un ajout fixe, pas en %, 
                                         // par ex. un blanc devant et derrière chaque donnée avant de l'écrire
            largTotal += larg;                                  // largeur totale de la table si utile 
            column.setWidth(larg);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        txtrechercher1 = new javax.swing.JTextField();
        txtbackground1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        login = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        numero = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mdp = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        comboRole = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        jPanel2 = new javax.swing.JPanel();
        btnnv = new javax.swing.JButton();
        btnenregistrer = new javax.swing.JButton();
        btnmodifier = new javax.swing.JButton();
        btnsupprimer = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setBorder(null);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        txtrechercher1.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercher1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercher1.setForeground(new java.awt.Color(3, 91, 155));
        txtrechercher1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercher1.setBorder(null);
        txtrechercher1.setDoubleBuffered(true);
        txtrechercher1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrechercher1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtrechercher1MouseEntered(evt);
            }
        });
        txtrechercher1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercher1ActionPerformed(evt);
            }
        });
        txtrechercher1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercher1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercher1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercher1KeyTyped(evt);
            }
        });
        getContentPane().add(txtrechercher1);
        txtrechercher1.setBounds(90, 90, 200, 10);

        txtbackground1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        getContentPane().add(txtbackground1);
        txtbackground1.setBounds(80, 80, 220, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/interface.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(0, 70, 70, 40);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Formulaire Utilisateur :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.setFocusTraversalPolicyProvider(true);
        jPanel1.setName(""); // NOI18N

        jLabel1.setText("Login   :            ");

        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginMouseExited(evt);
            }
        });

        jLabel2.setText("Numero : ");

        numero.setText("                  ");

        jLabel3.setText("Mot de passe   :            ");

        jLabel4.setText("Rôle   :            ");

        comboRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "USER" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addComponent(numero)
                                .addGap(65, 65, 65))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(login))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mdp))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(538, 115, 270, 140);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Liste des Utilisateurs :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Table.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Table.setRowHeight(20);
        Table.setSelectionBackground(new java.awt.Color(3, 91, 155));
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableMouseReleased(evt);
            }
        });
        Table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 120, 530, 220);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Action :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 3, 12))); // NOI18N

        btnnv.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnnv.setText("Nouveau");
        btnnv.setToolTipText("");
        btnnv.setAutoscrolls(true);
        btnnv.setBorder(null);
        btnnv.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnnvMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnnvMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnnvMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnnvMouseReleased(evt);
            }
        });
        btnnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnvActionPerformed(evt);
            }
        });

        btnenregistrer.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnenregistrer.setText("Enregistrer");
        btnenregistrer.setBorder(null);
        btnenregistrer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnenregistrerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnenregistrerMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnenregistrerMousePressed(evt);
            }
        });
        btnenregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenregistrerActionPerformed(evt);
            }
        });

        btnmodifier.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnmodifier.setText("Modifier");
        btnmodifier.setBorder(null);
        btnmodifier.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnmodifier.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnmodifierMouseMoved(evt);
            }
        });
        btnmodifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnmodifierMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmodifierMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnmodifierMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnmodifierMouseReleased(evt);
            }
        });
        btnmodifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifierActionPerformed(evt);
            }
        });

        btnsupprimer.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnsupprimer.setText("Supprimer");
        btnsupprimer.setBorder(null);
        btnsupprimer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnsupprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsupprimerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsupprimerMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnsupprimerMousePressed(evt);
            }
        });
        btnsupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupprimerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnv, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnenregistrer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifier, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnv, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifier, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 340, 390, 130);

        jPanel5.setBackground(new java.awt.Color(3, 91, 155));

        jLabel10.setBackground(new java.awt.Color(0, 102, 255));
        jLabel10.setFont(new java.awt.Font("Advent Pro", 0, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("GESTION DES UTILISATEURS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(462, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 0, 1160, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Affichage();
        clear();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("Taper Login");

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        btnnv.setEnabled(true);
        btnsupprimer.setEnabled(false);
        btnmodifier.setEnabled(false);
        btnenregistrer.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtrechercher1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1MouseClicked
        Affichage();

        ImageIcon img2 = new ImageIcon(getClass().getResource("txt1.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("");
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));

    }//GEN-LAST:event_txtrechercher1MouseClicked

    private void txtrechercher1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1MouseEntered

    private void txtrechercher1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercher1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1ActionPerformed

    private void txtrechercher1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1KeyPressed

    private void txtrechercher1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1KeyReleased
        try {
            String requete = "select login as 'Login', password as 'Mot de passe', role as 'Rôle' from login_table  where login LIKE ?";
            ps = conn4.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercher1.getText() + "%");
            rs = ps.executeQuery();
            Table.setModel(DbUtils.resultSetToTableModel(rs));
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            try {
                ps.close();
                rs.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }

    }//GEN-LAST:event_txtrechercher1KeyReleased

    private void txtrechercher1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1KeyTyped
        clear();
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercher1KeyTyped

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked

    }//GEN-LAST:event_TableMouseClicked

    private void TableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseEntered

    }//GEN-LAST:event_TableMouseEntered

    private void TableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseReleased
        clear();
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        Deplace();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("Taper Nom Section");
        btnsupprimer.setEnabled(true);
        btnmodifier.setEnabled(true);
        btnenregistrer.setEnabled(false);
    }//GEN-LAST:event_TableMouseReleased

    private void btnenregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenregistrerActionPerformed
        try {
            
            if(login.getText().equals("")||mdp.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Completez l'information"); 
            }else{
                String requete = "insert into  login_table (login,password,role) values (?,?,?)";
                ps = conn4.prepareStatement(requete);
                ps.setString(1, login.getText());
                ps.setString(2, hashPassword(mdp.getText()));
                ps.setString(3, comboRole.getSelectedItem().toString());
                ps.execute();
                JOptionPane.showMessageDialog(null, "Enregistrement succes"); 
            }
            
        } catch (HeadlessException | SQLException e) {
            System.out.println("--> SQLException : " + e);
            JOptionPane.showMessageDialog(null, "Tout est Obligatoire");
        } finally {

            try {
                ps.close();
                rs.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "deja inserre" + ex);
            }
        }
        Affichage();

    }//GEN-LAST:event_btnenregistrerActionPerformed

    private void TableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
            Deplace();
        }
    }//GEN-LAST:event_TableKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        clear();
    }//GEN-LAST:event_formMouseClicked

    private void loginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseEntered

    }//GEN-LAST:event_loginMouseEntered

    private void loginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseExited

    }//GEN-LAST:event_loginMouseExited

    private void btnnvMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvMouseEntered
       
    }//GEN-LAST:event_btnnvMouseEntered

    private void btnnvMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvMouseExited
        btnnv.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnnvMouseExited

    private void btnnvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvMousePressed
        btnnv.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnnvMousePressed

    private void btnnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnvActionPerformed

    }//GEN-LAST:event_btnnvActionPerformed

    private void btnnvMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvMouseReleased
        btnenregistrer.setEnabled(true);
        btnsupprimer.setEnabled(false);
        btnmodifier.setEnabled(false);
        clear();
    }//GEN-LAST:event_btnnvMouseReleased

    private void btnenregistrerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerMouseExited
        btnenregistrer.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnenregistrerMouseExited

    private void btnenregistrerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerMousePressed
        btnenregistrer.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnenregistrerMousePressed

    private void btnenregistrerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerMouseEntered
      
    }//GEN-LAST:event_btnenregistrerMouseEntered

    private void btnmodifierMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierMouseEntered
        
    }//GEN-LAST:event_btnmodifierMouseEntered

    private void btnmodifierMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierMouseExited
        btnmodifier.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnmodifierMouseExited

    private void btnmodifierMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierMousePressed
        btnmodifier.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnmodifierMousePressed

    private void btnmodifierMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierMouseReleased

    private void btnmodifierMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierMouseMoved

    }//GEN-LAST:event_btnmodifierMouseMoved

    private void btnmodifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifierActionPerformed
        try {
            if(login.getText().equals("")||mdp.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Completez l'information"); 
            }else{
                String requete = "update login_table set login =?, password =?, role =? where  idLogin ='" + numero.getText() + "'";

                ps = conn4.prepareStatement(requete);
                ps.setString(1, login.getText());
                ps.setString(2, hashPassword(mdp.getText()));
                ps.setString(3, comboRole.getSelectedItem().toString());
                ps.execute();
                JOptionPane.showMessageDialog(null, "Modification avec succès");
                ps.close();
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {

            try {
                ps.close();
                rs.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }

        Affichage();

    }//GEN-LAST:event_btnmodifierActionPerformed

    private void btnsupprimerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerMouseEntered
       
    }//GEN-LAST:event_btnsupprimerMouseEntered

    private void btnsupprimerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerMouseExited
        btnsupprimer.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnsupprimerMouseExited

    private void btnsupprimerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerMousePressed
        btnsupprimer.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnsupprimerMousePressed

    private void btnsupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(null, "attention vous devez suprimer un Utilisateur, est ce que tu es sur?",
                    "Supprimer Section", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                String requete = "delete from login_table where idLogin = '" + numero.getText() + "'";
                ps = conn4.prepareStatement(requete);
//                String requete2 = "select * from Sortie where NumSection = '" + numero.getText() + "'";
//                ps2 = conn4.prepareStatement(requete2);
//                rs2 = ps2.executeQuery();
//                if(rs2.next()){
//                    JOptionPane.showMessageDialog(null, "Erreur de suppression car il existe encore des Mouvements pour cette section");
//                } else{
//                ps.execute();
//                ps.close();
//                rs.close();
//                }
                ps.execute();
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        } finally {

            try {
                ps.close();
                rs.close();
                ps2.close();
                rs2.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
        Affichage();
        clear();
    }//GEN-LAST:event_btnsupprimerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table;
    private javax.swing.JButton btnenregistrer;
    public javax.swing.JButton btnmodifier;
    private javax.swing.JButton btnnv;
    private javax.swing.JButton btnsupprimer;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> comboRole;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField login;
    private javax.swing.JPasswordField mdp;
    private javax.swing.JLabel numero;
    private javax.swing.JLabel txtbackground1;
    private javax.swing.JTextField txtrechercher1;
    // End of variables declaration//GEN-END:variables
}
