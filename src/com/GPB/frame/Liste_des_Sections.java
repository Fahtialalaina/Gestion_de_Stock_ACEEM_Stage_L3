/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anouer
 */
public final class Liste_des_Sections extends javax.swing.JInternalFrame {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    static String test;
    static String te;
    public static String nbrh;
    public static String nbrh2;
    public static String datfin;
    public static String mont;
    public static String catp;
    public static String datfin2;
    public static String mont2;
    public static String catp2;
    public ImageIcon Format = null;

    /**
     * @throws java.sql.SQLException
     */
    public Liste_des_Sections() throws SQLException {
        initComponents();
        conn = ConexionBD.Conexion();
        Affichage();
        remove_title_bar();
        txtrechercher.setText("Taper Numero Section");
        txtrechercher1.setText("Taper Nom Section");
        nncontart.setVisible(false);
        ccode.setVisible(false);
        cconduite.setVisible(false);

        //String requete4 = "delete from effectif";
        //    ps = conn.prepareStatement(requete4);
        //    ps.execute();
    }

    public String re() {
        return te;
    }

    void remove_title_bar() {
        putClientProperty("Liste_des_Sections.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);

    }

    public void recuperutlisateur() throws SQLException {
        AcceuilGui los = new AcceuilGui();

        try {
            String recp = los.utilisateurs();
            System.out.println(recp);
            llogin.setText(recp);
            if (recp.equals("fahtia")) {
                Panelaction.setVisible(false);
            } else {
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void clear() {
        try {
            txtnom.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //display table
    public void Affichage() {
        try {
            String requete = "select idSection as 'Numero Section' ,NomSection as 'Nom du Section' from section ";
            System.out.println(requete);
            ps = conn.prepareStatement(requete);
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

    }

    public void Deplace() {
        try {

            int row = Table.getSelectedRow();
            Liste_des_Sections.test = (Table.getModel().getValueAt(row, 0).toString());
            String requet = " select * from  section where id = '" + test + "' ";
            ps = conn.prepareStatement(requet);
            rs = ps.executeQuery();

            if (rs.next()) {
                String t2 = rs.getString("NomSection");
                txtnom.setText(t2);
                /*String t3 = rs.getString("prenomc");
                txtprenom.setText(t3);
                String t4 = rs.getString("date_naissance");
                txtns.setText(t4);
                String t5 = rs.getString("age");
                txtage.setText(t5);
                String t = rs.getString("sexe");
                txtesex.setText(t);
                String t7 = rs.getString("gsm");
                txtesexe.setText(t7);
                String t8 = rs.getString("adresse");
                txtmail.setText(t8);
                String t9 = rs.getString("date_inscription");
                txtinscri.setText(t9);
                String t10 = rs.getString("image");
                if (t10.equals("")) {
                    ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
                    image.setIcon(img202);
                } else {
                    image.setIcon(new ImageIcon(t10));
                }*/
            }
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
    }

    public String gettableresult() {
        return test;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtnom = new javax.swing.JLabel();
        nncontart = new javax.swing.JLabel();
        ccode = new javax.swing.JLabel();
        cconduite = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        txtrechercher = new javax.swing.JTextField();
        btnrefresh = new javax.swing.JButton();
        Panelaction = new javax.swing.JPanel();
        btnsupprimer = new javax.swing.JButton();
        btnaj = new javax.swing.JButton();
        modifierbtn = new javax.swing.JButton();
        txtbachground = new javax.swing.JLabel();
        txtrechercher1 = new javax.swing.JTextField();
        txtbackground1 = new javax.swing.JLabel();
        llogin = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        printbtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setBorder(null);
        setTitle("Listes");
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Information Client :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 204));

        jLabel2.setText("Nom  :");

        txtnom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtnom.setForeground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnom, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(550, 110, 240, 60);

        nncontart.setToolTipText("Pas du Contrat");
        nncontart.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nncontart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nncontartMouseClicked(evt);
            }
        });
        getContentPane().add(nncontart);
        nncontart.setBounds(820, 6, 40, 40);

        ccode.setToolTipText("Contrat du code de la route");
        ccode.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ccode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ccodeMouseClicked(evt);
            }
        });
        getContentPane().add(ccode);
        ccode.setBounds(880, 6, 40, 40);

        cconduite.setToolTipText("Contrat du Conduite");
        cconduite.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cconduite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cconduiteMouseClicked(evt);
            }
        });
        getContentPane().add(cconduite);
        cconduite.setBounds(940, 6, 40, 40);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Listes des Clients :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Table.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Table.setAutoscrolls(false);
        Table.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        Table.setRowHeight(20);
        Table.setSelectionBackground(new java.awt.Color(0, 153, 153));
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
        jScrollPane1.setBounds(0, 120, 540, 350);

        txtrechercher.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercher.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercher.setForeground(new java.awt.Color(0, 153, 153));
        txtrechercher.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercher.setBorder(null);
        txtrechercher.setDoubleBuffered(true);
        txtrechercher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrechercherMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtrechercherMouseEntered(evt);
            }
        });
        txtrechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercherActionPerformed(evt);
            }
        });
        txtrechercher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercherKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercherKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercherKeyTyped(evt);
            }
        });
        getContentPane().add(txtrechercher);
        txtrechercher.setBounds(4, 89, 213, 14);

        btnrefresh.setBackground(new java.awt.Color(153, 0, 0));
        btnrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/interface.png"))); // NOI18N
        btnrefresh.setBorder(null);
        btnrefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });
        getContentPane().add(btnrefresh);
        btnrefresh.setBounds(230, 70, 70, 40);

        Panelaction.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Action :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        btnsupprimer.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnsupprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (1).png"))); // NOI18N
        btnsupprimer.setText("Supprimer");
        btnsupprimer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnsupprimer.setContentAreaFilled(false);
        btnsupprimer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnsupprimer.setOpaque(true);
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

        btnaj.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnaj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file.png"))); // NOI18N
        btnaj.setText("Nouveau");
        btnaj.setToolTipText("");
        btnaj.setAutoscrolls(true);
        btnaj.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnaj.setContentAreaFilled(false);
        btnaj.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnaj.setOpaque(true);
        btnaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnajMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnajMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnajMousePressed(evt);
            }
        });
        btnaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnajActionPerformed(evt);
            }
        });

        modifierbtn.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        modifierbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (2).png"))); // NOI18N
        modifierbtn.setText("Modifier");
        modifierbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        modifierbtn.setContentAreaFilled(false);
        modifierbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        modifierbtn.setOpaque(true);
        modifierbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                modifierbtnMouseMoved(evt);
            }
        });
        modifierbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                modifierbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                modifierbtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                modifierbtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                modifierbtnMouseReleased(evt);
            }
        });
        modifierbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelactionLayout = new javax.swing.GroupLayout(Panelaction);
        Panelaction.setLayout(PanelactionLayout);
        PanelactionLayout.setHorizontalGroup(
            PanelactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelactionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modifierbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(btnsupprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelactionLayout.setVerticalGroup(
            PanelactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelactionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnaj, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifierbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        getContentPane().add(Panelaction);
        Panelaction.setBounds(550, 180, 220, 190);

        txtbachground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        getContentPane().add(txtbachground);
        txtbachground.setBounds(0, 80, 220, 30);

        txtrechercher1.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercher1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercher1.setForeground(new java.awt.Color(0, 153, 153));
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
        txtrechercher1.setBounds(312, 89, 213, 14);

        txtbackground1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        getContentPane().add(txtbackground1);
        txtbackground1.setBounds(310, 80, 220, 30);
        getContentPane().add(llogin);
        llogin.setBounds(340, 60, 160, 0);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Impréssion :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        printbtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        printbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/printer.png"))); // NOI18N
        printbtn.setText("Imprimer");
        printbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        printbtn.setContentAreaFilled(false);
        printbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        printbtn.setOpaque(true);
        printbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                printbtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                printbtnMousePressed(evt);
            }
        });
        printbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(printbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(printbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(550, 370, 220, 90);

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setFont(new java.awt.Font("Advent Pro", 0, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("GESTION DES CLIENTS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(834, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 1160, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnajActionPerformed
        Liste_des_Sections.te = "a";

        String requete = "insert into (NomSection) values (?)";

        try {
            ps = conn.prepareStatement(requete);
            rs = ps.executeQuery();
            Table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
            }
        }

        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        Affichage();
        clear();
    }//GEN-LAST:event_btnajActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        Deplace();

    }//GEN-LAST:event_TableMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void TableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseEntered
        //Affichage();
    }//GEN-LAST:event_TableMouseEntered

    private void btnsupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerActionPerformed
        /*  try {
            if (JOptionPane.showConfirmDialog(null, "attention vous allez supprimer une Section, est ce que vous ête sûr?",
                    "Supprimer Client", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                if (txtcin.getText().length() != 0) {

                    String requete = "delete from section where id = ?";
                    ps = conn.prepareStatement(requete);
                    ps.setString(1, txtcin.getText());
                    ps.execute();
                    System.out.println("deleted");
                    clear();
                } else {
                    JOptionPane.showMessageDialog(null, "veuillez choisir  une Section !");
                }
            }
            ps.close();
            rs.close();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "erreur de suppression, peut être qu'il existe des prets en cours pour cette client\n" + e.getMessage());
        } finally {

            try {
                ps.close();
                rs.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        Affichage();
        clear();

         */
    }//GEN-LAST:event_btnsupprimerActionPerformed

    private void TableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseReleased
        btnsupprimer.setEnabled(true);
        modifierbtn.setEnabled(true);
        //Deplace();
    }//GEN-LAST:event_TableMouseReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseMoved

    private void modifierbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierbtnActionPerformed
        Liste_des_Sections.te = "ah";
        AjoutSection act = null;
        try {
            act = new AjoutSection();
        } catch (SQLException ex) {
            Logger.getLogger(Liste_des_Sections.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            act.Recuper();
        } catch (SQLException ex) {
            Logger.getLogger(Liste_des_Sections.class.getName()).log(Level.SEVERE, null, ex);
        }
        act.setVisible(true);
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        Affichage();
        clear();

    }//GEN-LAST:event_modifierbtnActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void modifierbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifierbtnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_modifierbtnMouseReleased

    private void modifierbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifierbtnMouseMoved

    }//GEN-LAST:event_modifierbtnMouseMoved

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed

        MessageFormat header = new MessageFormat("Liste des Sections:");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            Table.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        } catch (java.awt.print.PrinterException e) {
            System.err.format("Erreur d'impression ", e.getMessage());
        }

    }//GEN-LAST:event_printbtnActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        Affichage();
        clear();
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        txtrechercher.setText("");
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercher.setText("Taper Cin Client");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("Taper Nom Client");

    }//GEN-LAST:event_formMouseClicked

    private void txtrechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        Affichage();

        clear();
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercher.setText("Taper Cin Client");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("Taper Nom Client");

    }//GEN-LAST:event_btnrefreshActionPerformed
    public void search() {
        try {
            String requete = "select idSection as 'Numero Section' ,NomSection as 'Nom du Section' from  section where NomSection LIKE  ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercher.getText() + "%");
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
//        
    }
    private void txtrechercherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherKeyReleased

        try {
            String requete = "select idSection as 'Numero Section' ,NomSection as 'Nom du Section' from  section where idSection LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercher.getText() + "%");
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
    }//GEN-LAST:event_txtrechercherKeyReleased

    private void txtrechercherKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherKeyTyped
        clear();
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);

    }//GEN-LAST:event_txtrechercherKeyTyped

    private void txtrechercherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherKeyPressed

    }//GEN-LAST:event_txtrechercherKeyPressed

    private void txtrechercherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherMouseClicked
        Affichage();
        clear();

        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbachground.setIcon(img);
        txtrechercher.setText("");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("Taper Nom Client");

        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherMouseClicked

    private void txtrechercherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherMouseEntered

    private void txtrechercher1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1MouseClicked
        Affichage();
        clear();
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt1.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("");
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercher.setText("Taper Cin Client");

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
            String requete = "select idSection as 'Numero Section' ,NomSection as 'Nom du Section' from  section where NomSection LIKE  ?";
            ps = conn.prepareStatement(requete);
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
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
    }//GEN-LAST:event_txtrechercher1KeyTyped

    private void ccodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ccodeMouseClicked
        /* String info = "<html><head></head><body><h3 color ='#2C4CCC'><center>Contrat du Code de la route</center></h3><p>Cin :<b><i>" + test + "</i></b></p>"
                + "<br><p>Nom & Prénom :<b><i>" + txtnom.getText() + "</i> " + txtprenom.getText() + "</b></p>"
                + "<br><p >Nombre D'heures du contrat :<b><i>" + nbrh2 + "</i></b></p>"
                + "<br><p >Montant du Contrat  :<b><i>" + mont2 + "Dt</i></b></p>"
                + "<br><p >Catégorie du Permis :<b><i>" + catp2 + "</i></b></p>"
                + "<br><p >Date Fin du Contrat :<b><i>" + datfin2 + "</i></b></p><br></body></html>";
        JOptionPane p = new JOptionPane();
        p.setMessage(info);
        p.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog d = p.createDialog(null, "Information sur le contrat ");
        d.setVisible(true);*/
    }//GEN-LAST:event_ccodeMouseClicked

    private void cconduiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cconduiteMouseClicked
        /* String info = "<html><head></head><body><h3 color='#2C4CCC'><center>Contrat du Conduite</center></h3> <p >Cin :<b><i>" + test + "</i></b></p>"
                + "<br><p>Nom & Prénom :<b><i>" + txtnom.getText() + "</i>  " + txtprenom.getText() + "</b></p>"
                + "<br><p >Nombre D'heures du contrat :<b><i>" + nbrh + "</i></b></p>"
                + "<br><p >Montant du Contrat  :<b><i>" + mont + "Dt</i></b></p>"
                + "<br><p >Catégorie du Permis :<b><i>" + catp + "</i></b></p>"
                + "<br><p >Date Fin du Contrat :<b><i>" + datfin + "</i></b></p><br></body></html>";
        JOptionPane p = new JOptionPane();
        p.setMessage(info);
        p.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog d = p.createDialog(null, "Informations sur le contrat");
        d.setVisible(true);*/
    }//GEN-LAST:event_cconduiteMouseClicked

    private void nncontartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nncontartMouseClicked

    }//GEN-LAST:event_nncontartMouseClicked

    private void TableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableKeyReleased

    }//GEN-LAST:event_TableKeyReleased

    private void modifierbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifierbtnMouseEntered
        modifierbtn.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_modifierbtnMouseEntered

    private void modifierbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifierbtnMouseExited
        modifierbtn.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_modifierbtnMouseExited

    private void btnajMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnajMouseEntered
        btnaj.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnajMouseEntered

    private void btnajMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnajMouseExited
        btnaj.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnajMouseExited

    private void btnajMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnajMousePressed
        btnaj.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnajMousePressed

    private void btnsupprimerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerMouseExited
        btnsupprimer.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnsupprimerMouseExited

    private void btnsupprimerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerMousePressed
        btnsupprimer.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnsupprimerMousePressed

    private void btnsupprimerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerMouseEntered
        btnsupprimer.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnsupprimerMouseEntered

    private void modifierbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifierbtnMousePressed
        modifierbtn.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_modifierbtnMousePressed

    private void printbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMouseExited
        printbtn.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_printbtnMouseExited

    private void printbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMousePressed
        printbtn.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_printbtnMousePressed

    private void printbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMouseEntered
        printbtn.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_printbtnMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panelaction;
    private javax.swing.JTable Table;
    private javax.swing.JButton btnaj;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsupprimer;
    private javax.swing.JLabel ccode;
    private javax.swing.JLabel cconduite;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel llogin;
    public javax.swing.JButton modifierbtn;
    private javax.swing.JLabel nncontart;
    private javax.swing.JButton printbtn;
    private javax.swing.JLabel txtbachground;
    private javax.swing.JLabel txtbackground1;
    private javax.swing.JLabel txtnom;
    private javax.swing.JTextField txtrechercher;
    private javax.swing.JTextField txtrechercher1;
    // End of variables declaration//GEN-END:variables

    //To change body of generated methods, choose Tools | Templates.
}
