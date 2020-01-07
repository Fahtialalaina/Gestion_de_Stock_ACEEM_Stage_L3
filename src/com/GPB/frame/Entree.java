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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author anouer
 */
public final class Entree extends javax.swing.JInternalFrame {

    Connection conn = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    ResultSet rs4 = null;
    ResultSet rs5 = null;
    ResultSet rs6 = null;
    ResultSet rs7 = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    PreparedStatement ps3 = null;
    PreparedStatement ps4 = null;
    PreparedStatement ps5 = null;
    PreparedStatement ps6 = null;
    PreparedStatement ps7 = null;
    static String test;

    /**
     * Creates new form Examen
     * @throws java.sql.SQLException
     */
    public Entree() throws SQLException {
        
        initComponents();
        remove_title_bar();

        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercherEntree.setText("Tapez Numero Entree");
        
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("Tapez Reference Entree");
        CloseConnexion();
        conn = ConexionBD.Conexion();
        AffichageEntree();
        AffichageArticle();
        CloseConnexion();
        remplirComboFournisseur();
        masquerLigneEntree();
        masquerArticle();
        
        btnsupprimerLE.setEnabled(false);
        btnmodifierLE.setEnabled(false);
        btnenregistrerLE.setEnabled(false);
        btnsupprimerArticle.setEnabled(false);
        btnmodifierArticle.setEnabled(false);
        btnenregistrerArticle.setEnabled(false);
    }

    private void remove_title_bar() {
        putClientProperty("Entree.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    private void masquerLigneEntree() {
        JPanelLigneEntree.setVisible(false);
    }

    private void afficherLigneEntree() {
        JPanelLigneEntree.setVisible(true);
    }

    private void masquerArticle() {
        jButtonRefreshArticle.setVisible(false);
        txtrechercherarticle.setVisible(false);
        txtbackgroundarticle.setVisible(false);
        jScrollPane4.setVisible(false);
        TableArticleEntree.setVisible(false);
    }

    private void afficherArticle() {
        jButtonRefreshArticle.setVisible(true);
        txtrechercherarticle.setVisible(true);
        txtbackgroundarticle.setVisible(true);
        jScrollPane4.setVisible(true);
        TableArticleEntree.setVisible(true);
    }

    private void AffichageArticle() {
        try {
            String requete2 = "select * from article";
            ps5 = conn.prepareStatement(requete2);
            rs5 = ps5.executeQuery();
            TableArticleEntree.setModel(DbUtils.resultSetToTableModel(rs5));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
                CloseRsPs5();
        }
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackgroundarticle.setIcon(img);
        txtrechercherarticle.setText("Tapez Numero ou Nom Article");

    }

    private void AffichageEntree() {
        try {
            String requete = "select NumEntree as 'Numero' ,RefEntree as 'Reference' ,Fournisseur.NomFournisseur as 'Fournisseur' ,DateEntree as 'Date' ,MontantTotalEntree as 'MontantTotal' ,ObservationEntree as 'Observation' from Entree, Fournisseur WHERE\n"
                    + "(Fournisseur.NumFournisseur=Entree.NumFournisseur)";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            TableEntree.setModel(DbUtils.resultSetToTableModel(rs5));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        //ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbackgroundarticle.setIcon(img);
        //txtrechercherarticle.setText("Tapez Numero ou Nom Article");

    }

    private void AffichageLigneEntree(String numEntree) {
        try {
            String requete = "select NumLigneEntree as 'Numero' ,article.NomArticle as 'Article' ,NbrEntree as 'Nombre' ,puFournisseur as 'Prix Unitaire Fournisseur' ,MontantEntree as 'Montant' from LigneEntree, article WHERE\n"
                    + "article.NumArticle=LigneEntree.NumArticle and LigneEntree.NumEntree like '" + numEntree + "'";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            TableLigneEntree.setModel(DbUtils.resultSetToTableModel(rs5));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        //ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbackgroundarticle.setIcon(img);
        //txtrechercherarticle.setText("Tapez Numero ou Nom Article");

    }
    
    public void CloseConnexion() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connexion fermée");
            } catch (SQLException e) { /* ignored */}
        }
    }
    
    public void CloseRsPs1() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
    
    public void CloseRsPs2() {
        if (rs2 != null) {
            try {
                rs2.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (ps2 != null) {
            try {
                ps2.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
    
    public void CloseRsPs3() {
        if (rs3 != null) {
            try {
                rs3.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (ps3 != null) {
            try {
                ps3.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
    
    public void CloseRsPs4() {
        if (rs4 != null) {
            try {
                rs4.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (ps4 != null) {
            try {
                ps4.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
    
    public void CloseRsPs5() {
        if (rs5 != null) {
            try {
                rs5.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (ps5 != null) {
            try {
                ps5.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
    
    public void CloseRsPs6() {
        if (rs6 != null) {
            try {
                rs6.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (ps6 != null) {
            try {
                ps6.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
    
    public void CloseRsPs7() {
        if (rs7 != null) {
            try {
                rs7.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (ps7 != null) {
            try {
                ps7.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
    
    

    public void DeplaceLigneEntree() {
        conn = ConexionBD.Conexion();
        try {

            int row = TableLigneEntree.getSelectedRow();
            Entree.test = (TableLigneEntree.getModel().getValueAt(row, 0).toString());
            String requet = " select * from  LigneEntree where NumLigneEntree = '" + test + "' ";
            ps = conn.prepareStatement(requet);
            rs = ps.executeQuery();

            if (rs.next()) {
                String t1 = rs.getString("NumLigneEntree");
                String t2 = rs.getString("NumArticle");
                String t3 = rs.getString("NbrEntree");
                String t4 = rs.getString("puFournisseur");
                numeroLigneEntree.setText(t1);
                nbr.setText(t3);
                puFournisseur.setText(t4);

                String article1 = " select * from article where NumArticle = '" + t2 + "'";
                ps2 = conn.prepareStatement(article1);
                rs2 = ps2.executeQuery();
                article.setText(rs2.getString("NomArticle"));
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            CloseRsPs1();
            CloseRsPs2();
            CloseConnexion();
        }
    }

    public void DeplaceArticle() {
        conn = ConexionBD.Conexion();
        try {

            int row = TableArticleEntree.getSelectedRow();
            Entree.test = (TableArticleEntree.getModel().getValueAt(row, 0).toString());
            String requet = " select * from article where NumArticle = '" + test + "'";
            ps = conn.prepareStatement(requet);
            rs = ps.executeQuery();

            if (rs.next()) {
                String t1 = rs.getString("NomArticle");
                String t2 = rs.getString("pu");
                article.setText(t1);
                puFournisseur.setText(t2);
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            CloseRsPs1();
            CloseConnexion();
        }
    }

    public void DeplaceEntree() {
        conn = ConexionBD.Conexion();
        try {

            int row = TableEntree.getSelectedRow();
            Entree.test = (TableEntree.getModel().getValueAt(row, 0).toString());
            String requet = " select * from Entree where NumEntree = '" + test + "'";
            ps = conn.prepareStatement(requet);
            rs = ps.executeQuery();

                String t1 = rs.getString("NumEntree");
                String t2 = rs.getString("RefEntree");
                String t3 = rs.getString("NumFournisseur");
                String t4 = rs.getString("DateEntree");
                String t5 = rs.getString("MontantTotalEntree");
                String t6 = rs.getString("ObservationEntree");
                String fournisseur = " select * from Fournisseur where NumFournisseur = '" + t3 + "'";
                ps2 = conn.prepareStatement(fournisseur);
                rs2 = ps2.executeQuery();
                numeroEntree.setText(t1);
                //DesignationArticle.setText(t2);
                ref.setText(t2);
                date.setText(t4);
                MontantTotal.setText(t5);
                obs.setText(t6);
                ComboFournisseur.setSelectedItem(rs2.getString("NomFournisseur"));
                String num = numeroEntree.getText();
                AffichageLigneEntree(num);

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            CloseRsPs1();
            CloseRsPs2();
            CloseConnexion();
        }
    }

    public void remplirComboFournisseur() {
        conn = ConexionBD.Conexion();
        String requet = " select * from  Fournisseur";

        try {
            ps = conn.prepareStatement(requet);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nom = rs.getString("NomFournisseur");
                ComboFournisseur.addItem(nom);
            }

        } catch (SQLException e) {
        } finally {
            CloseRsPs1();
            CloseConnexion();
        }

    }

    public void clearEntree() {
        try {
            numeroEntree.setText("");
            ref.setText("");
            date.setText("");
            MontantTotal.setText("");
            obs.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clearLE() {
        try {
            numeroLigneEntree.setText("");
            article.setText("");
            nbr.setText("");
            puFournisseur.setText("");
        } catch (Exception e) {
            System.out.println(e);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numeroEntree = new javax.swing.JLabel();
        ref = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ComboFournisseur = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnnvArticle = new javax.swing.JButton();
        btnenregistrerArticle = new javax.swing.JButton();
        btnmodifierArticle = new javax.swing.JButton();
        btnsupprimerArticle = new javax.swing.JButton();
        MontantTotal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        obs = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        printbtn1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableEntree = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        JPanelLigneEntree = new javax.swing.JPanel();
        jScrollPaneLigneEntree = new javax.swing.JScrollPane();
        TableLigneEntree = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        jPanelFormLigneEntree = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numeroLigneEntree = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nbr = new javax.swing.JTextField();
        article = new javax.swing.JLabel();
        puFournisseur = new javax.swing.JTextField();
        jPanelActionLigneEntree = new javax.swing.JPanel();
        btnnvLE = new javax.swing.JButton();
        btnenregistrerLE = new javax.swing.JButton();
        btnmodifierLE = new javax.swing.JButton();
        btnsupprimerLE = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        printbtn = new javax.swing.JButton();
        txtrechercherEntree = new javax.swing.JTextField();
        txtbachground = new javax.swing.JLabel();
        jButtonRefreshArticle = new javax.swing.JButton();
        txtrechercher1Entree = new javax.swing.JTextField();
        txtbackground1 = new javax.swing.JLabel();
        txtrechercherarticle = new javax.swing.JTextField();
        txtbackgroundarticle = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableArticleEntree = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        jButton2 = new javax.swing.JButton();

        setBorder(null);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Formulaire Entree :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.setFocusTraversalPolicyProvider(true);
        jPanel1.setName(""); // NOI18N

        jLabel1.setText("Montant Total : ");

        jLabel2.setText("Numero : ");

        numeroEntree.setText("                  ");

        ref.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refMouseExited(evt);
            }
        });

        jLabel5.setText("Reference : ");

        date.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dateMouseExited(evt);
            }
        });

        jLabel6.setText("Date : ");

        jLabel7.setText("Fournisseur :");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Action :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 3, 12))); // NOI18N

        btnnvArticle.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnnvArticle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file.png"))); // NOI18N
        btnnvArticle.setText("Nouveau");
        btnnvArticle.setToolTipText("");
        btnnvArticle.setAutoscrolls(true);
        btnnvArticle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnnvArticle.setContentAreaFilled(false);
        btnnvArticle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnnvArticle.setOpaque(true);
        btnnvArticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnnvArticleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnnvArticleMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnnvArticleMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnnvArticleMouseReleased(evt);
            }
        });
        btnnvArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnvArticleActionPerformed(evt);
            }
        });

        btnenregistrerArticle.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnenregistrerArticle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/save.png"))); // NOI18N
        btnenregistrerArticle.setText("Enregistrer");
        btnenregistrerArticle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnenregistrerArticle.setContentAreaFilled(false);
        btnenregistrerArticle.setOpaque(true);
        btnenregistrerArticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnenregistrerArticleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnenregistrerArticleMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnenregistrerArticleMousePressed(evt);
            }
        });
        btnenregistrerArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenregistrerArticleActionPerformed(evt);
            }
        });

        btnmodifierArticle.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnmodifierArticle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (2).png"))); // NOI18N
        btnmodifierArticle.setText("Modifier");
        btnmodifierArticle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnmodifierArticle.setContentAreaFilled(false);
        btnmodifierArticle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnmodifierArticle.setOpaque(true);
        btnmodifierArticle.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnmodifierArticleMouseMoved(evt);
            }
        });
        btnmodifierArticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnmodifierArticleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmodifierArticleMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnmodifierArticleMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnmodifierArticleMouseReleased(evt);
            }
        });
        btnmodifierArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifierArticleActionPerformed(evt);
            }
        });

        btnsupprimerArticle.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnsupprimerArticle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (1).png"))); // NOI18N
        btnsupprimerArticle.setText("Supprimer");
        btnsupprimerArticle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnsupprimerArticle.setContentAreaFilled(false);
        btnsupprimerArticle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnsupprimerArticle.setOpaque(true);
        btnsupprimerArticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsupprimerArticleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsupprimerArticleMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnsupprimerArticleMousePressed(evt);
            }
        });
        btnsupprimerArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupprimerArticleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerArticle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnvArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnenregistrerArticle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnvArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenregistrerArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerArticle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        MontantTotal.setText("0");

        jLabel9.setText("Observation : ");

        obs.setColumns(20);
        obs.setRows(5);
        jScrollPane3.setViewportView(obs);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Impréssion :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        printbtn1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        printbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/printer.png"))); // NOI18N
        printbtn1.setText("Imprimer");
        printbtn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        printbtn1.setContentAreaFilled(false);
        printbtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        printbtn1.setOpaque(true);
        printbtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printbtn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                printbtn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                printbtn1MousePressed(evt);
            }
        });
        printbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(printbtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(printbtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ref)
                            .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addComponent(ComboFournisseur, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(MontantTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38)
                        .addComponent(numeroEntree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numeroEntree))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MontantTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 100, 440, 530);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Listes des Entrees :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableEntree.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        TableEntree.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableEntree.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableEntree.setRowHeight(20);
        TableEntree.setSelectionBackground(new java.awt.Color(0, 153, 153));
        TableEntree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableEntreeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableEntreeMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableEntreeMouseReleased(evt);
            }
        });
        TableEntree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableEntreeKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TableEntree);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(440, 110, 490, 200);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setBackground(new java.awt.Color(0, 102, 255));
        jLabel10.setFont(new java.awt.Font("Advent Pro", 0, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("GESTION DES ENTREES");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(692, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 0, 1200, 50);

        JPanelLigneEntree.setBorder(javax.swing.BorderFactory.createTitledBorder("Ligne Entree"));

        jScrollPaneLigneEntree.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Listes des LigneEntrees :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPaneLigneEntree.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPaneLigneEntree.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableLigneEntree.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        TableLigneEntree.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableLigneEntree.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableLigneEntree.setRowHeight(20);
        TableLigneEntree.setSelectionBackground(new java.awt.Color(0, 153, 153));
        TableLigneEntree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableLigneEntreeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableLigneEntreeMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableLigneEntreeMouseReleased(evt);
            }
        });
        TableLigneEntree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableLigneEntreeKeyReleased(evt);
            }
        });
        jScrollPaneLigneEntree.setViewportView(TableLigneEntree);

        jPanelFormLigneEntree.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Formulaire Ligne Entree :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jPanelFormLigneEntree.setForeground(new java.awt.Color(0, 0, 204));
        jPanelFormLigneEntree.setFocusTraversalPolicyProvider(true);
        jPanelFormLigneEntree.setName(""); // NOI18N

        jLabel3.setText("Article : ");

        jLabel4.setText("Numero : ");

        numeroLigneEntree.setText("                  ");

        jLabel8.setText("Nombre : ");

        jLabel11.setText("Prix unitaire : ");

        nbr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbrActionPerformed(evt);
            }
        });

        article.setText("                  ");

        puFournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puFournisseurActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormLigneEntreeLayout = new javax.swing.GroupLayout(jPanelFormLigneEntree);
        jPanelFormLigneEntree.setLayout(jPanelFormLigneEntreeLayout);
        jPanelFormLigneEntreeLayout.setHorizontalGroup(
            jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLigneEntreeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormLigneEntreeLayout.createSequentialGroup()
                        .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numeroLigneEntree, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(article, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelFormLigneEntreeLayout.createSequentialGroup()
                        .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nbr)
                            .addComponent(puFournisseur, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanelFormLigneEntreeLayout.setVerticalGroup(
            jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLigneEntreeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(numeroLigneEntree))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(article))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(puFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanelActionLigneEntree.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Action :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 3, 12))); // NOI18N

        btnnvLE.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnnvLE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file.png"))); // NOI18N
        btnnvLE.setText("Nouveau");
        btnnvLE.setToolTipText("");
        btnnvLE.setAutoscrolls(true);
        btnnvLE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnnvLE.setContentAreaFilled(false);
        btnnvLE.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnnvLE.setOpaque(true);
        btnnvLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnnvLEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnnvLEMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnnvLEMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnnvLEMouseReleased(evt);
            }
        });
        btnnvLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnvLEActionPerformed(evt);
            }
        });

        btnenregistrerLE.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnenregistrerLE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/save.png"))); // NOI18N
        btnenregistrerLE.setText("Ajouter");
        btnenregistrerLE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnenregistrerLE.setContentAreaFilled(false);
        btnenregistrerLE.setOpaque(true);
        btnenregistrerLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnenregistrerLEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnenregistrerLEMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnenregistrerLEMousePressed(evt);
            }
        });
        btnenregistrerLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenregistrerLEActionPerformed(evt);
            }
        });

        btnmodifierLE.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnmodifierLE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (2).png"))); // NOI18N
        btnmodifierLE.setText("Modifier");
        btnmodifierLE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnmodifierLE.setContentAreaFilled(false);
        btnmodifierLE.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnmodifierLE.setOpaque(true);
        btnmodifierLE.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnmodifierLEMouseMoved(evt);
            }
        });
        btnmodifierLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnmodifierLEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmodifierLEMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnmodifierLEMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnmodifierLEMouseReleased(evt);
            }
        });
        btnmodifierLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifierLEActionPerformed(evt);
            }
        });

        btnsupprimerLE.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnsupprimerLE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (1).png"))); // NOI18N
        btnsupprimerLE.setText("Supprimer");
        btnsupprimerLE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnsupprimerLE.setContentAreaFilled(false);
        btnsupprimerLE.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnsupprimerLE.setOpaque(true);
        btnsupprimerLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsupprimerLEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsupprimerLEMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnsupprimerLEMousePressed(evt);
            }
        });
        btnsupprimerLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupprimerLEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelActionLigneEntreeLayout = new javax.swing.GroupLayout(jPanelActionLigneEntree);
        jPanelActionLigneEntree.setLayout(jPanelActionLigneEntreeLayout);
        jPanelActionLigneEntreeLayout.setHorizontalGroup(
            jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActionLigneEntreeLayout.createSequentialGroup()
                .addGroup(jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerLE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnvLE, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnenregistrerLE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierLE, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanelActionLigneEntreeLayout.setVerticalGroup(
            jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelActionLigneEntreeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnvLE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenregistrerLE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerLE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierLE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout JPanelLigneEntreeLayout = new javax.swing.GroupLayout(JPanelLigneEntree);
        JPanelLigneEntree.setLayout(JPanelLigneEntreeLayout);
        JPanelLigneEntreeLayout.setHorizontalGroup(
            JPanelLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelLigneEntreeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneLigneEntree, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanelLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelFormLigneEntree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelActionLigneEntree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        JPanelLigneEntreeLayout.setVerticalGroup(
            JPanelLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelLigneEntreeLayout.createSequentialGroup()
                .addGroup(JPanelLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelLigneEntreeLayout.createSequentialGroup()
                        .addComponent(jPanelFormLigneEntree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelActionLigneEntree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(jScrollPaneLigneEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(JPanelLigneEntree);
        JPanelLigneEntree.setBounds(440, 320, 720, 310);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Impréssion :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 12))); // NOI18N

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(printbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(printbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(1030, 60, 125, 50);

        txtrechercherEntree.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercherEntree.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercherEntree.setForeground(new java.awt.Color(0, 153, 153));
        txtrechercherEntree.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercherEntree.setAlignmentX(0.0F);
        txtrechercherEntree.setAlignmentY(0.0F);
        txtrechercherEntree.setBorder(null);
        txtrechercherEntree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrechercherEntreeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtrechercherEntreeMouseEntered(evt);
            }
        });
        txtrechercherEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercherEntreeActionPerformed(evt);
            }
        });
        txtrechercherEntree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercherEntreeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercherEntreeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercherEntreeKeyTyped(evt);
            }
        });
        getContentPane().add(txtrechercherEntree);
        txtrechercherEntree.setBounds(10, 80, 200, 10);

        txtbachground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        txtbachground.setAlignmentY(0.0F);
        getContentPane().add(txtbachground);
        txtbachground.setBounds(0, 70, 220, 30);

        jButtonRefreshArticle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/interface.png"))); // NOI18N
        jButtonRefreshArticle.setAlignmentY(0.0F);
        jButtonRefreshArticle.setBorder(null);
        jButtonRefreshArticle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonRefreshArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshArticleActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRefreshArticle);
        jButtonRefreshArticle.setBounds(940, 80, 40, 30);

        txtrechercher1Entree.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercher1Entree.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercher1Entree.setForeground(new java.awt.Color(0, 153, 153));
        txtrechercher1Entree.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercher1Entree.setAlignmentX(0.0F);
        txtrechercher1Entree.setAlignmentY(0.0F);
        txtrechercher1Entree.setBorder(null);
        txtrechercher1Entree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrechercher1EntreeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtrechercher1EntreeMouseEntered(evt);
            }
        });
        txtrechercher1Entree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercher1EntreeActionPerformed(evt);
            }
        });
        txtrechercher1Entree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercher1EntreeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercher1EntreeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercher1EntreeKeyTyped(evt);
            }
        });
        getContentPane().add(txtrechercher1Entree);
        txtrechercher1Entree.setBounds(320, 80, 200, 10);

        txtbackground1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        txtbackground1.setAlignmentY(0.0F);
        getContentPane().add(txtbackground1);
        txtbackground1.setBounds(310, 70, 220, 30);

        txtrechercherarticle.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercherarticle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercherarticle.setForeground(new java.awt.Color(0, 153, 153));
        txtrechercherarticle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercherarticle.setAlignmentX(0.0F);
        txtrechercherarticle.setAlignmentY(0.0F);
        txtrechercherarticle.setBorder(null);
        txtrechercherarticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrechercherarticleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtrechercherarticleMouseEntered(evt);
            }
        });
        txtrechercherarticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercherarticleActionPerformed(evt);
            }
        });
        txtrechercherarticle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercherarticleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercherarticleKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercherarticleKeyTyped(evt);
            }
        });
        getContentPane().add(txtrechercherarticle);
        txtrechercherarticle.setBounds(950, 120, 200, 10);

        txtbackgroundarticle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        txtbackgroundarticle.setAlignmentY(0.0F);
        getContentPane().add(txtbackgroundarticle);
        txtbackgroundarticle.setBounds(940, 110, 220, 30);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Articles :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableArticleEntree.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        TableArticleEntree.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableArticleEntree.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableArticleEntree.setRowHeight(20);
        TableArticleEntree.setSelectionBackground(new java.awt.Color(0, 153, 153));
        TableArticleEntree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableArticleEntreeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableArticleEntreeMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableArticleEntreeMouseReleased(evt);
            }
        });
        TableArticleEntree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableArticleEntreeKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(TableArticleEntree);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(940, 140, 220, 170);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/interface.png"))); // NOI18N
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(230, 60, 70, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableEntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEntreeMouseClicked

    }//GEN-LAST:event_TableEntreeMouseClicked

    private void TableEntreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEntreeMouseEntered

    }//GEN-LAST:event_TableEntreeMouseEntered

    private void TableEntreeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEntreeMouseReleased

        afficherLigneEntree();
        afficherArticle();
        AffichageArticle();
        clearLE();
        DeplaceEntree();
        
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercherEntree.setText("Tapez Numero Entree");
        
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("Tapez Reference Entree");
        txtbackground1.setIcon(img2);
        btnsupprimerArticle.setEnabled(true);
        btnmodifierArticle.setEnabled(true);
        btnenregistrerArticle.setEnabled(false);
        

    }//GEN-LAST:event_TableEntreeMouseReleased

    private void SommeMontant() throws SQLException {
        String requete = "select SUM(MontantEntree) as somme from LigneEntree where NumEntree = '" + numeroEntree.getText() + "'";
        ps5 = conn.prepareStatement(requete);
        rs5 = ps5.executeQuery();
        MontantTotal.setText(rs5.getString("somme"));
        
        CloseRsPs5();
    }

    private void btnenregistrerLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenregistrerLEActionPerformed
        conn = ConexionBD.Conexion();
        try {
            //String requete5 = "select * from  LigneEntree where NumArticle = '" + article.getText() + "' and NumEntree = '" + numeroEntree.getText() + "'";
            //ps5 = conn.prepareStatement(requete5);
            
            String requete = "insert into LigneEntree (NumEntree,NumArticle,NbrEntree,puFournisseur,MontantEntree) values (?,?,?,?,?)";
            ps = conn.prepareStatement(requete);

            String requete2 = "select * from  article where NomArticle = '" + article.getText() + "'";
            

            ps2 = conn.prepareStatement(requete2);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("NumArticle");
            
            String requete4 = "select * from article where  NumArticle ='" + num + "'";
            ps4 = conn.prepareStatement(requete4);
            rs4 = ps4.executeQuery();
            String ancienQte = rs4.getString("QteStock");
            String ancienMtn = rs4.getString("MontantStock");
            
            ps.setString(1, numeroEntree.getText());
            ps.setString(2, num);
            ps.setString(3, nbr.getText());
            ps.setString(4, puFournisseur.getText());
            int mtn = Integer.parseInt(nbr.getText()) * Integer.parseInt(puFournisseur.getText());
            ps.setString(5, String.valueOf(mtn));
            
            
            //String avant = MontantTotal.getText();
            //Long apres = mtn + Integer.parseInt(avant);
            //MontantTotal.setText(String.valueOf(apres));
            ps.execute();
            
            

            String requete3 = "update article set QteStock =? ,pu =? ,MontantStock =? where  NumArticle ='" + num + "'";
            ps3 = conn.prepareStatement(requete3);
            
            int qte = Integer.parseInt(nbr.getText()) + Integer.parseInt(ancienQte);
            ps3.setString(1, String.valueOf(qte));
            int pu = (mtn + Integer.parseInt(ancienMtn)) / qte;
            //int NvMtn = mtn + Integer.parseInt(ancienMtn);
            ps3.setString(2, String.valueOf(pu));
            ps3.setString(3, String.valueOf(pu * qte));
            ps3.execute();

            JOptionPane.showMessageDialog(null, "Enregistrement succes");
            
            AffichageLigneEntree(numeroEntree.getText());
            clearLE();
            try {
                SommeMontant();
            } catch (SQLException ex) {
                Logger.getLogger(Entree.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println("--> SQLException : " + e);
            JOptionPane.showMessageDialog(null, "Tout est Obligatoire");
        } finally {
            CloseRsPs1();
            CloseRsPs2();
            CloseRsPs3();
            CloseRsPs4();
            CloseConnexion();
        }
        
        RectifierMontantTotalEntree();
        
    }//GEN-LAST:event_btnenregistrerLEActionPerformed

    private void RectifierMontantTotalEntree(){
        conn = ConexionBD.Conexion();
        try {
            String requete = "update Entree set MontantTotalEntree=? where  NumEntree ='" + numeroEntree.getText() + "'";
            ps = conn.prepareStatement(requete);

            ps.setString(1, MontantTotal.getText());
            ps.execute();
        } catch (HeadlessException | SQLException e) {
            System.out.println("--> SQLException : " + e);
        } finally {
            CloseRsPs1();
            CloseRsPs2();
        }
        AffichageEntree();
        CloseConnexion();
    }
    
    private void TableEntreeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableEntreeKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
            DeplaceLigneEntree();
        }
    }//GEN-LAST:event_TableEntreeKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        //clear();
    }//GEN-LAST:event_formMouseClicked

    private void btnnvLEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvLEMouseEntered
        btnnvLE.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnnvLEMouseEntered

    private void btnnvLEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvLEMouseExited
        btnnvLE.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnnvLEMouseExited

    private void btnnvLEMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvLEMousePressed
        btnnvLE.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnnvLEMousePressed

    private void btnnvLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnvLEActionPerformed

    }//GEN-LAST:event_btnnvLEActionPerformed

    private void btnnvLEMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvLEMouseReleased
        btnenregistrerLE.setEnabled(true);
        btnsupprimerLE.setEnabled(false);
        btnmodifierLE.setEnabled(false);
        clearLE();
    }//GEN-LAST:event_btnnvLEMouseReleased

    private void btnenregistrerLEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerLEMouseExited
        btnenregistrerLE.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnenregistrerLEMouseExited

    private void btnenregistrerLEMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerLEMousePressed
        btnenregistrerLE.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnenregistrerLEMousePressed

    private void btnenregistrerLEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerLEMouseEntered
        btnenregistrerLE.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnenregistrerLEMouseEntered

    private void btnmodifierLEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierLEMouseEntered
        btnmodifierLE.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnmodifierLEMouseEntered

    private void btnmodifierLEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierLEMouseExited
        btnmodifierLE.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnmodifierLEMouseExited

    private void btnmodifierLEMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierLEMousePressed
        btnmodifierLE.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnmodifierLEMousePressed

    private void btnmodifierLEMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierLEMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierLEMouseReleased

    private void btnmodifierLEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierLEMouseMoved

    }//GEN-LAST:event_btnmodifierLEMouseMoved

    private void btnmodifierLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifierLEActionPerformed
        conn = ConexionBD.Conexion();
        try {
            
            String requete1 = "select * from LigneEntree where  NumLigneEntree ='" + numeroLigneEntree.getText() + "'";
            ps5 = conn.prepareStatement(requete1);
            rs5 = ps5.executeQuery();
            String NumArticle = rs5.getString("NumArticle");
            String NbrEntree = rs5.getString("NbrEntree");
            String MontantEntree = rs5.getString("MontantEntree");
            
            String requete7 = "select * from  article where NumArticle = '" + NumArticle + "'";
            ps7 = conn.prepareStatement(requete7);
            rs7 = ps7.executeQuery();
            String AncienQte = rs7.getString("QteStock");
            String AncienMtn = rs7.getString("MontantStock");
            
                String requete6 = "update article set QteStock =? ,pu =? ,MontantStock =? where  NumArticle ='" + NumArticle + "'";
            ps6 = conn.prepareStatement(requete6);
            
            int qte = Integer.parseInt(AncienQte) - Integer.parseInt(NbrEntree);
            ps6.setString(1, String.valueOf(qte));
            int MtnRet = Integer.parseInt(AncienMtn) - Integer.parseInt(MontantEntree);
            if (qte != 0) {
                int pu = MtnRet / qte;
                ps6.setString(2, String.valueOf(pu));
            } else {
                ps6.setString(2, String.valueOf(0));
            }
            ps6.setString(3, String.valueOf(MtnRet));
            ps6.execute();

            String requete = "update LigneEntree set NumEntree =?,NumArticle=?,NbrEntree=?,puFournisseur=?,MontantEntree=? where  NumLigneEntree ='" + numeroLigneEntree.getText() + "'";
            ps = conn.prepareStatement(requete);

            String requete2 = "select * from  article where NomArticle = '" + article.getText() + "'";
            ps2 = conn.prepareStatement(requete2);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("NumArticle");
            
            ps.setString(1, numeroEntree.getText());
            ps.setString(2, num);
            ps.setString(3, nbr.getText());
            ps.setString(4, puFournisseur.getText());
            int mtn = Integer.parseInt(nbr.getText()) * Integer.parseInt(puFournisseur.getText());
            ps.setString(5, String.valueOf(mtn));
            ps.execute();
            
            
            String requete4 = "select * from article where  NumArticle ='" + num + "'";
            ps4 = conn.prepareStatement(requete4);
            rs4 = ps4.executeQuery();
            String ancienQte = rs4.getString("QteStock");
            String ancienMtn = rs4.getString("MontantStock");
            
             String requete3 = "update article set QteStock =? ,pu =? ,MontantStock =? where  NumArticle ='" + num + "'";
            ps3 = conn.prepareStatement(requete3);
            
            int qte2 = Integer.parseInt(nbr.getText()) + Integer.parseInt(ancienQte);
            ps3.setString(1, String.valueOf(qte2));
            int pu2 = (mtn + Integer.parseInt(ancienMtn)) / qte2;
            ps3.setString(2, String.valueOf(pu2));
            ps3.setString(3, String.valueOf(pu2 * qte2));
            ps3.execute();
            
            
            JOptionPane.showMessageDialog(null, "Modification succes");
            
            
        } catch (HeadlessException | SQLException e) {
            System.out.println("--> SQLException : " + e);
            JOptionPane.showMessageDialog(null, "Tout est Obligatoire");
        } finally {
            CloseRsPs1();
            CloseRsPs2();
            CloseRsPs3();
            CloseRsPs4();
            CloseRsPs5();
            CloseRsPs6();
            CloseRsPs7();
            CloseConnexion();
        }
        conn = ConexionBD.Conexion();
        AffichageLigneEntree(numeroEntree.getText());
        clearLE();
        try {
            SommeMontant();
        } catch (SQLException ex) {
            Logger.getLogger(Entree.class.getName()).log(Level.SEVERE, null, ex);
        }
        CloseConnexion();
        RectifierMontantTotalEntree();
    }//GEN-LAST:event_btnmodifierLEActionPerformed

    private void btnsupprimerLEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerLEMouseEntered
        btnsupprimerLE.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnsupprimerLEMouseEntered

    private void btnsupprimerLEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerLEMouseExited
        btnsupprimerLE.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnsupprimerLEMouseExited

    private void btnsupprimerLEMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerLEMousePressed
        btnsupprimerLE.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnsupprimerLEMousePressed

    private void btnsupprimerLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerLEActionPerformed
        conn = ConexionBD.Conexion();
        try {
            if (JOptionPane.showConfirmDialog(null, "attention vous devez suprimer une Ligne Entree, est ce que tu es sur?",
                    "Supprimer Ligne Entree", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                String requete1 = "select * from LigneEntree where  NumLigneEntree ='" + numeroLigneEntree.getText() + "'";
                ps5 = conn.prepareStatement(requete1);
                rs5 = ps5.executeQuery();
                String NumArticle = rs5.getString("NumArticle");
                String NbrEntree = rs5.getString("NbrEntree");
                String MontantEntree = rs5.getString("MontantEntree");

                String requete7 = "select * from  article where NumArticle = '" + NumArticle + "'";
                ps7 = conn.prepareStatement(requete7);
                rs7 = ps7.executeQuery();
                String AncienQte = rs7.getString("QteStock");
                String AncienMtn = rs7.getString("MontantStock");

                
                int qte = Integer.parseInt(AncienQte) - Integer.parseInt(NbrEntree);
                int MtnRet = Integer.parseInt(AncienMtn) - Integer.parseInt(MontantEntree);
                
                if (qte < 0 || MtnRet < 0) {
                    JOptionPane.showMessageDialog(null, "Erreur de supression car le stock est insuffisant!");
                } else {
                    String requete6 = "update article set QteStock =? ,pu =? ,MontantStock =? where  NumArticle ='" + NumArticle + "'";
                    ps6 = conn.prepareStatement(requete6);

                    ps6.setString(1, String.valueOf(qte));
                    if (qte != 0) {
                        int pu = MtnRet / qte;
                        ps6.setString(2, String.valueOf(pu));
                    } else {
                        ps6.setString(2, String.valueOf(0));
                    }
                    ps6.setString(3, String.valueOf(MtnRet));
                    ps6.execute();

                    String requete = "delete from LigneEntree where NumLigneEntree = '" + numeroLigneEntree.getText() + "'";
                    ps = conn.prepareStatement(requete);

                    ps.execute();
                }
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "erreur de suppression" + e.getMessage() + "\n Peut être que le stock est vide!");
        } finally {
            CloseRsPs1();
            CloseRsPs5();
            CloseRsPs6();
            CloseRsPs7();
        }
        
        AffichageLigneEntree(numeroEntree.getText());
        clearLE();
        try {
            SommeMontant();
        } catch (SQLException ex) {
            Logger.getLogger(Entree.class.getName()).log(Level.SEVERE, null, ex);
        }
        CloseConnexion();
        RectifierMontantTotalEntree();
    }//GEN-LAST:event_btnsupprimerLEActionPerformed

    private void printbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMouseEntered
        printbtn.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_printbtnMouseEntered

    private void printbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMouseExited
        printbtn.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_printbtnMouseExited

    private void printbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMousePressed
        printbtn.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_printbtnMousePressed

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed

        MessageFormat header = new MessageFormat("Liste des Sections:");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            TableEntree.print(JTable.PrintMode.NORMAL, header, footer);

        } catch (java.awt.print.PrinterException e) {
            System.err.format("Erreur d'impression ", e.getMessage());
        }

    }//GEN-LAST:event_printbtnActionPerformed

    private void TableLigneEntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableLigneEntreeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableLigneEntreeMouseClicked

    private void TableLigneEntreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableLigneEntreeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TableLigneEntreeMouseEntered

    private void TableLigneEntreeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableLigneEntreeMouseReleased
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        DeplaceLigneEntree();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercherEntree.setText("Taper Numero Entree");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("Taper Nom Entree");
        btnsupprimerLE.setEnabled(true);
        btnmodifierLE.setEnabled(true);
        btnenregistrerLE.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_TableLigneEntreeMouseReleased

    private void TableLigneEntreeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableLigneEntreeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableLigneEntreeKeyReleased

    private void btnnvArticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvArticleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvArticleMouseEntered

    private void btnnvArticleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvArticleMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvArticleMouseExited

    private void btnnvArticleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvArticleMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvArticleMousePressed

    private void btnnvArticleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvArticleMouseReleased
        btnenregistrerArticle.setEnabled(true);
        btnsupprimerArticle.setEnabled(false);
        btnmodifierArticle.setEnabled(false);
        clearEntree();
        masquerArticle();
        masquerLigneEntree();
    }//GEN-LAST:event_btnnvArticleMouseReleased

    private void btnnvArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnvArticleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvArticleActionPerformed

    private void btnenregistrerArticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerArticleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnenregistrerArticleMouseEntered

    private void btnenregistrerArticleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerArticleMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnenregistrerArticleMouseExited

    private void btnenregistrerArticleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerArticleMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnenregistrerArticleMousePressed

    private void btnenregistrerArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenregistrerArticleActionPerformed
        conn = ConexionBD.Conexion();
        try {
            String requete = "insert into Entree (refEntree,NumFournisseur,DateEntree,MontantTotalEntree,ObservationEntree) values (?,?,?,?,?)";
            ps = conn.prepareStatement(requete);

            String requete2 = "select * from  Fournisseur where NomFournisseur = '" + ComboFournisseur.getSelectedItem() + "'";

            ps2 = conn.prepareStatement(requete2);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("NumFournisseur");
            ps.setString(1, ref.getText());
            ps.setString(2, num);
            ps.setString(3, date.getText());
            ps.setString(4, MontantTotal.getText());
            ps.setString(5, obs.getText());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Enregistrement succes");
        } catch (HeadlessException | SQLException e) {
            System.out.println("--> SQLException : " + e);
            JOptionPane.showMessageDialog(null, "Tout est Obligatoire");
        } finally {
            CloseRsPs1();
            CloseRsPs2();
        }
        AffichageEntree();
        clearEntree();
        CloseConnexion();
    }//GEN-LAST:event_btnenregistrerArticleActionPerformed

    private void btnmodifierArticleMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierArticleMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierArticleMouseMoved

    private void btnmodifierArticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierArticleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierArticleMouseEntered

    private void btnmodifierArticleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierArticleMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierArticleMouseExited

    private void btnmodifierArticleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierArticleMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierArticleMousePressed

    private void btnmodifierArticleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierArticleMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierArticleMouseReleased

    private void btnmodifierArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifierArticleActionPerformed
        conn = ConexionBD.Conexion();
        try {
            String requete = "update Entree set refEntree =?,NumFournisseur=?,DateEntree=?,MontantTotalEntree=?,ObservationEntree=? where  NumEntree ='" + numeroEntree.getText() + "'";
            ps = conn.prepareStatement(requete);

            String requete2 = "select * from  Fournisseur where NomFournisseur = '" + ComboFournisseur.getSelectedItem() + "'";

            ps2 = conn.prepareStatement(requete2);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("NumFournisseur");
            ps.setString(1, ref.getText());
            ps.setString(2, num);
            ps.setString(3, date.getText());
            ps.setString(4, MontantTotal.getText());
            ps.setString(5, obs.getText());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Modification succes");
        } catch (HeadlessException | SQLException e) {
            System.out.println("--> SQLException : " + e);
            JOptionPane.showMessageDialog(null, "Tout est Obligatoire");
        } finally {
            CloseRsPs1();
            CloseRsPs2();
        }
        AffichageEntree();
        clearEntree();
        masquerArticle();
        masquerLigneEntree();
        CloseConnexion();
    }//GEN-LAST:event_btnmodifierArticleActionPerformed

    private void btnsupprimerArticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerArticleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsupprimerArticleMouseEntered

    private void btnsupprimerArticleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerArticleMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsupprimerArticleMouseExited

    private void btnsupprimerArticleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerArticleMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsupprimerArticleMousePressed

    private void btnsupprimerArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerArticleActionPerformed
        conn = ConexionBD.Conexion();
        try {
            if (JOptionPane.showConfirmDialog(null, "attention vous devez suprimer un Article, est ce que tu es sur?",
                    "Supprimer Article", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                String requete = "delete from article where NumArticle = '" + numeroEntree.getText() + "'";
                ps = conn.prepareStatement(requete);

                ps.execute();
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "erreur de suppression" + e.getMessage());
        } finally {
            CloseRsPs1();
        }
        AffichageArticle();
        CloseConnexion();
    }//GEN-LAST:event_btnsupprimerArticleActionPerformed

    private void printbtn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtn1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_printbtn1MouseEntered

    private void printbtn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtn1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_printbtn1MouseExited

    private void printbtn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtn1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_printbtn1MousePressed

    private void printbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printbtn1ActionPerformed

    private void refMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_refMouseEntered

    private void refMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_refMouseExited

    private void dateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_dateMouseExited

    private void dateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_dateMouseEntered

    private void txtrechercherEntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherEntreeMouseClicked
        conn = ConexionBD.Conexion();
        AffichageEntree();
        clearEntree();

        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbachground.setIcon(img);
        txtrechercherEntree.setText("");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("Taper Reference Entree");

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        CloseConnexion();
    }//GEN-LAST:event_txtrechercherEntreeMouseClicked

    private void txtrechercherEntreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherEntreeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherEntreeMouseEntered

    private void txtrechercherEntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercherEntreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherEntreeActionPerformed

    private void txtrechercherEntreeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherEntreeKeyPressed

    }//GEN-LAST:event_txtrechercherEntreeKeyPressed

    private void txtrechercherEntreeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherEntreeKeyReleased
        conn = ConexionBD.Conexion();
        try {
            String requete = "select NumEntree as 'Numero' ,RefEntree as 'Reference' ,Fournisseur.NomFournisseur as 'Fournisseur' ,DateEntree as 'Date' ,MontantTotalEntree as 'MontantTotal' ,ObservationEntree as 'Observation' from Entree, Fournisseur WHERE\n"
                    + "(Fournisseur.NumFournisseur=Entree.NumFournisseur) and NumEntree LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercherEntree.getText() + "%");
            rs = ps.executeQuery();
            TableEntree.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs1();
            CloseConnexion();
        }

    }//GEN-LAST:event_txtrechercherEntreeKeyReleased

    private void txtrechercherEntreeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherEntreeKeyTyped
        clearEntree();
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercherEntreeKeyTyped

    private void jButtonRefreshArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshArticleActionPerformed
        conn = ConexionBD.Conexion();
        AffichageArticle();
        clearLE();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackgroundarticle.setIcon(img);
        txtrechercherarticle.setText("Taper Numero ou Nom Entree");
        CloseConnexion();
    }//GEN-LAST:event_jButtonRefreshArticleActionPerformed

    private void txtrechercher1EntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1EntreeMouseClicked
        conn = ConexionBD.Conexion();
        AffichageEntree();
        clearEntree();

        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbachground.setIcon(img);
        txtrechercherEntree.setText("Taper Numero Entree");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("");

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        CloseConnexion();
    }//GEN-LAST:event_txtrechercher1EntreeMouseClicked

    private void txtrechercher1EntreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1EntreeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1EntreeMouseEntered

    private void txtrechercher1EntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercher1EntreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1EntreeActionPerformed

    private void txtrechercher1EntreeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1EntreeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1EntreeKeyPressed

    private void txtrechercher1EntreeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1EntreeKeyReleased
        conn = ConexionBD.Conexion();
        try {
            String requete = "select NumEntree as 'Numero' ,RefEntree as 'Reference' ,Fournisseur.NomFournisseur as 'Fournisseur' ,DateEntree as 'Date' ,MontantTotalEntree as 'MontantTotal' ,ObservationEntree as 'Observation' from Entree, Fournisseur WHERE\n"
                    + "(Fournisseur.NumFournisseur=Entree.NumFournisseur) and RefEntree LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercher1Entree.getText() + "%");
            rs = ps.executeQuery();
            TableEntree.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs1();
            CloseConnexion();
        }
    }//GEN-LAST:event_txtrechercher1EntreeKeyReleased

    private void txtrechercher1EntreeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1EntreeKeyTyped
        //clear();
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercher1EntreeKeyTyped

    private void nbrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nbrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nbrActionPerformed

    private void txtrechercherarticleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherarticleMouseClicked

        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbackgroundarticle.setIcon(img);
        txtrechercherarticle.setText("");

    }//GEN-LAST:event_txtrechercherarticleMouseClicked

    private void txtrechercherarticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherarticleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherarticleMouseEntered

    private void txtrechercherarticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercherarticleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherarticleActionPerformed

    private void txtrechercherarticleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherarticleKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherarticleKeyPressed

    private void txtrechercherarticleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherarticleKeyReleased
        conn = ConexionBD.Conexion();
        try {
            String requete = "select * from article  where NumArticle LIKE ? or NomArticle LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercherarticle.getText() + "%");
            ps.setString(2, "%" + txtrechercherarticle.getText() + "%");
            rs = ps.executeQuery();
            TableArticleEntree.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs1();
            CloseConnexion();
        }
    }//GEN-LAST:event_txtrechercherarticleKeyReleased

    private void txtrechercherarticleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherarticleKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherarticleKeyTyped

    private void TableArticleEntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleEntreeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleEntreeMouseClicked

    private void TableArticleEntreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleEntreeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleEntreeMouseEntered

    private void TableArticleEntreeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleEntreeMouseReleased
        DeplaceArticle();
        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbackgroundarticle.setIcon(img);
        txtrechercherarticle.setText("Tapez Numero ou Nom Article");
    }//GEN-LAST:event_TableArticleEntreeMouseReleased

    private void TableArticleEntreeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableArticleEntreeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleEntreeKeyReleased

    private void puFournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puFournisseurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_puFournisseurActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercherEntree.setText("Tapez Numero Entree");
        
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("Tapez Reference Entree");
        
        conn = ConexionBD.Conexion();
        AffichageEntree();
        masquerArticle();
        masquerLigneEntree();
        clearEntree();
        CloseConnexion();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboFournisseur;
    private javax.swing.JPanel JPanelLigneEntree;
    private javax.swing.JLabel MontantTotal;
    private javax.swing.JTable TableArticleEntree;
    private javax.swing.JTable TableEntree;
    private javax.swing.JTable TableLigneEntree;
    private javax.swing.JLabel article;
    private javax.swing.JButton btnenregistrerArticle;
    private javax.swing.JButton btnenregistrerLE;
    public javax.swing.JButton btnmodifierArticle;
    public javax.swing.JButton btnmodifierLE;
    private javax.swing.JButton btnnvArticle;
    private javax.swing.JButton btnnvLE;
    private javax.swing.JButton btnsupprimerArticle;
    private javax.swing.JButton btnsupprimerLE;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField date;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonRefreshArticle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelActionLigneEntree;
    private javax.swing.JPanel jPanelFormLigneEntree;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPaneLigneEntree;
    private javax.swing.JTextField nbr;
    private javax.swing.JLabel numeroEntree;
    private javax.swing.JLabel numeroLigneEntree;
    private javax.swing.JTextArea obs;
    private javax.swing.JButton printbtn;
    private javax.swing.JButton printbtn1;
    private javax.swing.JTextField puFournisseur;
    private javax.swing.JTextField ref;
    private javax.swing.JLabel txtbachground;
    private javax.swing.JLabel txtbackground1;
    private javax.swing.JLabel txtbackgroundarticle;
    private javax.swing.JTextField txtrechercher1Entree;
    private javax.swing.JTextField txtrechercherEntree;
    private javax.swing.JTextField txtrechercherarticle;
    // End of variables declaration//GEN-END:variables
}
