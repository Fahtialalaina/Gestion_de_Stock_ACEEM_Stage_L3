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
public class Sortie extends javax.swing.JInternalFrame {

    Connection connSortie = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    ResultSet rs4 = null;
    ResultSet rs5 = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    PreparedStatement ps3 = null;
    PreparedStatement ps4 = null;
    PreparedStatement ps5 = null;
    static String test;

    /**
     * Creates new form Examen
     *
     * @throws java.sql.SQLException
     */
    public Sortie() throws SQLException {
        
        initComponents();
        remove_title_bar();

        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercherEntree.setText("Taper Numero Sortie");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("Taper Reference Sortie");

        connSortie = ConexionBD.Conexion();
        AffichageSortie();
        AffichageArticle();
        remplirComboSection();
        masquerLigneSortie();
        masquerArticle();

        btnsupprimerLS.setEnabled(false);
        btnmodifierLS.setEnabled(false);
        btnenregistrerLS.setEnabled(false);
        btnsupprimerSortie.setEnabled(false);
        btnmodifierSortie.setEnabled(false);
        btnenregistrerSortie.setEnabled(false);
    }

    private void remove_title_bar() {
        putClientProperty("Sortie.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    private void masquerLigneSortie() {
        JPanelLigneEntree.setVisible(false);
    }

    private void afficherLigneSortie() {
        JPanelLigneEntree.setVisible(true);
    }

    private void masquerArticle() {
        jButtonRefreshArticle.setVisible(false);
        txtrechercherarticle.setVisible(false);
        txtbackgroundarticle.setVisible(false);
        jScrollPane4.setVisible(false);
        TableArticleSortie.setVisible(false);
    }

    private void afficherArticle() {
        jButtonRefreshArticle.setVisible(true);
        txtrechercherarticle.setVisible(true);
        txtbackgroundarticle.setVisible(true);
        jScrollPane4.setVisible(true);
        TableArticleSortie.setVisible(true);
    }

    private void AffichageArticle() {
        try {
            String requete2 = "select * from article";
            ps = connSortie.prepareStatement(requete2);
            rs = ps.executeQuery();
            TableArticleSortie.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackgroundarticle.setIcon(img);
        txtrechercherarticle.setText("Tapez Numero ou Nom Article");

    }

    private void AffichageSortie() {
        try {
            String requete = "select NumSortie as 'Numero' ,RefSortie as 'Reference' ,Section.idSection as 'Section' ,DateSortie as 'Date' ,MontantTotalSortie as 'MontantTotal' ,ObservationSortie as 'Observation' from Sortie, Section WHERE\n"
                    + "(Section.idSection=Sortie.NumSection)";
            ps = connSortie.prepareStatement(requete);
            rs = ps.executeQuery();
            TableSortie.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
        //ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbackgroundarticle.setIcon(img);
        //txtrechercherarticle.setText("Tapez Numero ou Nom Article");

    }

    private void AffichageLigneSortie(String numSortie) {
        try {
            String requete = "select NumLigneSortie as 'Numero' ,article.NomArticle as 'Article' ,NbrSortie as 'Nombre' ,puSortie as 'Prix Unitaire' ,MontantSortie as 'Montant' from LigneSortie, article WHERE\n"
                    + "article.NumArticle=LigneSortie.NumArticle and LigneSortie.NumSortie like '" + numSortie + "'";
            ps = connSortie.prepareStatement(requete);
            rs = ps.executeQuery();
            TableLigneSortie.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
        //ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbackgroundarticle.setIcon(img);
        //txtrechercherarticle.setText("Tapez Numero ou Nom Article");

    }

    public void DeplaceLigneSortie() {
        try {

            int row = TableLigneSortie.getSelectedRow();
            Sortie.test = (TableLigneSortie.getModel().getValueAt(row, 0).toString());
            String requet = " select * from  LigneSortie where NumLigneSortie = '" + test + "' ";
            ps = connSortie.prepareStatement(requet);
            rs = ps.executeQuery();

            if (rs.next()) {
                String t1 = rs.getString("NumLigneSortie");
                String t2 = rs.getString("NumArticle");
                String t3 = rs.getString("NbrSortie");
                String t4 = rs.getString("puSortie");
                numeroLigneSortie.setText(t1);
                nbr.setText(t3);
                puSortie.setText(t4);

                String article1 = " select * from article where NumArticle = '" + t2 + "'";
                ps2 = connSortie.prepareStatement(article1);
                rs2 = ps2.executeQuery();
                article.setText(rs2.getString("NomArticle"));
                try {
                    if(rs2 != null){
                       rs2.close();
                    }
                    if(ps2 != null){
                       ps2.close();
                    }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
    }

    public void DeplaceArticle() {
        try {

            int row = TableArticleSortie.getSelectedRow();
            Sortie.test = (TableArticleSortie.getModel().getValueAt(row, 0).toString());
            String requet = " select * from article where NumArticle = '" + test + "'";
            ps = connSortie.prepareStatement(requet);
            rs = ps.executeQuery();

            if (rs.next()) {
                String t1 = rs.getString("NomArticle");
                String t2 = rs.getString("pu");
                article.setText(t1);
                puSortie.setText(t2);
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
    }

    public void DeplaceSortie() {
        try {

            int row = TableSortie.getSelectedRow();
            Sortie.test = (TableSortie.getModel().getValueAt(row, 0).toString());
            String requet = " select * from Sortie where NumSortie = '" + test + "'";
            ps = connSortie.prepareStatement(requet);
            rs = ps.executeQuery();

            if (rs.next()) {
                try {
                String t1 = rs.getString("NumSortie");
                String t2 = rs.getString("RefSortie");
                String t3 = rs.getString("NumSection");
                String t4 = rs.getString("DateSortie");
                String t5 = rs.getString("MontantTotalSortie");
                String t6 = rs.getString("ObservationSortie");
                String section = " select * from Section where idSection = '" + t3 + "'";
                ps2 = connSortie.prepareStatement(section);
                rs2 = ps2.executeQuery();
                numeroSortie.setText(t1);
                ref.setText(t2);
                date.setText(t4);
                MontantTotal.setText(t5);
                obs.setText(t6);
                ComboSection.setSelectedItem(rs2.getString("NomSection"));
                String num = numeroSortie.getText();
                AffichageLigneSortie(num);
                } catch (Exception e) {
                     System.out.println(e);
                }finally{
                try {
                    if(rs2 != null){
                       rs2.close();
                    }
                    if(ps2 != null){
                       ps2.close();
                    }
                } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
                }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
    }

    public void remplirComboSection() {
        String requet = " select * from  Section";

        try {
            ps = connSortie.prepareStatement(requet);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nom = rs.getString("NomSection").toString();
                ComboSection.addItem(nom);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }

    }

    public void clearSortie() {
        try {
            numeroSortie.setText("");
            ref.setText("");
            date.setText("");
            MontantTotal.setText("");
            obs.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clearLS() {
        try {
            numeroLigneSortie.setText("");
            article.setText("");
            nbr.setText("");
            puSortie.setText("");
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
        numeroSortie = new javax.swing.JLabel();
        ref = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ComboSection = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnnvSortie = new javax.swing.JButton();
        btnenregistrerSortie = new javax.swing.JButton();
        btnmodifierSortie = new javax.swing.JButton();
        btnsupprimerSortie = new javax.swing.JButton();
        MontantTotal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        obs = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        printbtn1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableSortie = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        JPanelLigneEntree = new javax.swing.JPanel();
        jScrollPaneLigneEntree = new javax.swing.JScrollPane();
        TableLigneSortie = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        jPanelFormLigneEntree = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numeroLigneSortie = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nbr = new javax.swing.JTextField();
        article = new javax.swing.JLabel();
        puSortie = new javax.swing.JTextField();
        jPanelActionLigneEntree = new javax.swing.JPanel();
        btnnvLS = new javax.swing.JButton();
        btnenregistrerLS = new javax.swing.JButton();
        btnmodifierLS = new javax.swing.JButton();
        btnsupprimerLS = new javax.swing.JButton();
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
        TableArticleSortie = new javax.swing.JTable(){
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Formulaire Sortie :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.setFocusTraversalPolicyProvider(true);
        jPanel1.setName(""); // NOI18N

        jLabel1.setText("Montant Total : ");

        jLabel2.setText("Numero : ");

        numeroSortie.setText("                  ");

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

        jLabel7.setText("Section :");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Action :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 3, 12))); // NOI18N

        btnnvSortie.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnnvSortie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file.png"))); // NOI18N
        btnnvSortie.setText("Nouveau");
        btnnvSortie.setToolTipText("");
        btnnvSortie.setAutoscrolls(true);
        btnnvSortie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnnvSortie.setContentAreaFilled(false);
        btnnvSortie.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnnvSortie.setOpaque(true);
        btnnvSortie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnnvSortieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnnvSortieMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnnvSortieMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnnvSortieMouseReleased(evt);
            }
        });
        btnnvSortie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnvSortieActionPerformed(evt);
            }
        });

        btnenregistrerSortie.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnenregistrerSortie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/save.png"))); // NOI18N
        btnenregistrerSortie.setText("Enregistrer");
        btnenregistrerSortie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnenregistrerSortie.setContentAreaFilled(false);
        btnenregistrerSortie.setOpaque(true);
        btnenregistrerSortie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnenregistrerSortieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnenregistrerSortieMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnenregistrerSortieMousePressed(evt);
            }
        });
        btnenregistrerSortie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenregistrerSortieActionPerformed(evt);
            }
        });

        btnmodifierSortie.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnmodifierSortie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (2).png"))); // NOI18N
        btnmodifierSortie.setText("Modifier");
        btnmodifierSortie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnmodifierSortie.setContentAreaFilled(false);
        btnmodifierSortie.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnmodifierSortie.setOpaque(true);
        btnmodifierSortie.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnmodifierSortieMouseMoved(evt);
            }
        });
        btnmodifierSortie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnmodifierSortieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmodifierSortieMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnmodifierSortieMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnmodifierSortieMouseReleased(evt);
            }
        });
        btnmodifierSortie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifierSortieActionPerformed(evt);
            }
        });

        btnsupprimerSortie.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnsupprimerSortie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (1).png"))); // NOI18N
        btnsupprimerSortie.setText("Supprimer");
        btnsupprimerSortie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnsupprimerSortie.setContentAreaFilled(false);
        btnsupprimerSortie.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnsupprimerSortie.setOpaque(true);
        btnsupprimerSortie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsupprimerSortieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsupprimerSortieMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnsupprimerSortieMousePressed(evt);
            }
        });
        btnsupprimerSortie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupprimerSortieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerSortie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnvSortie, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnenregistrerSortie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierSortie, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnvSortie, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenregistrerSortie, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerSortie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierSortie, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(ComboSection, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(MontantTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38)
                        .addComponent(numeroSortie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addComponent(numeroSortie))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Listes des Sorties :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableSortie.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        TableSortie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableSortie.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableSortie.setRowHeight(20);
        TableSortie.setSelectionBackground(new java.awt.Color(0, 153, 153));
        TableSortie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableSortieMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableSortieMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableSortieMouseReleased(evt);
            }
        });
        TableSortie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableSortieKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TableSortie);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(440, 110, 490, 200);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setBackground(new java.awt.Color(0, 102, 255));
        jLabel10.setFont(new java.awt.Font("Advent Pro", 0, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("GESTION DES SORTIES");

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

        JPanelLigneEntree.setBorder(javax.swing.BorderFactory.createTitledBorder("Ligne Sortie"));

        jScrollPaneLigneEntree.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Listes des LigneSorties :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPaneLigneEntree.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPaneLigneEntree.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableLigneSortie.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        TableLigneSortie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableLigneSortie.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableLigneSortie.setRowHeight(20);
        TableLigneSortie.setSelectionBackground(new java.awt.Color(0, 153, 153));
        TableLigneSortie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableLigneSortieMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableLigneSortieMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableLigneSortieMouseReleased(evt);
            }
        });
        TableLigneSortie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableLigneSortieKeyReleased(evt);
            }
        });
        jScrollPaneLigneEntree.setViewportView(TableLigneSortie);

        jPanelFormLigneEntree.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Formulaire Ligne Sortie :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jPanelFormLigneEntree.setForeground(new java.awt.Color(0, 0, 204));
        jPanelFormLigneEntree.setFocusTraversalPolicyProvider(true);
        jPanelFormLigneEntree.setName(""); // NOI18N

        jLabel3.setText("Article : ");

        jLabel4.setText("Numero : ");

        numeroLigneSortie.setText("                  ");

        jLabel8.setText("Nombre : ");

        jLabel11.setText("Prix unitaire : ");

        nbr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbrActionPerformed(evt);
            }
        });

        article.setText("                  ");

        puSortie.setEditable(false);
        puSortie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puSortieActionPerformed(evt);
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
                            .addComponent(numeroLigneSortie, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(article, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelFormLigneEntreeLayout.createSequentialGroup()
                        .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nbr)
                            .addComponent(puSortie, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanelFormLigneEntreeLayout.setVerticalGroup(
            jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLigneEntreeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(numeroLigneSortie))
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
                    .addComponent(puSortie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanelActionLigneEntree.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Action :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 3, 12))); // NOI18N

        btnnvLS.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnnvLS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file.png"))); // NOI18N
        btnnvLS.setText("Nouveau");
        btnnvLS.setToolTipText("");
        btnnvLS.setAutoscrolls(true);
        btnnvLS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnnvLS.setContentAreaFilled(false);
        btnnvLS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnnvLS.setOpaque(true);
        btnnvLS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnnvLSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnnvLSMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnnvLSMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnnvLSMouseReleased(evt);
            }
        });
        btnnvLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnvLSActionPerformed(evt);
            }
        });

        btnenregistrerLS.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnenregistrerLS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/save.png"))); // NOI18N
        btnenregistrerLS.setText("Ajouter");
        btnenregistrerLS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnenregistrerLS.setContentAreaFilled(false);
        btnenregistrerLS.setOpaque(true);
        btnenregistrerLS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnenregistrerLSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnenregistrerLSMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnenregistrerLSMousePressed(evt);
            }
        });
        btnenregistrerLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenregistrerLSActionPerformed(evt);
            }
        });

        btnmodifierLS.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnmodifierLS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (2).png"))); // NOI18N
        btnmodifierLS.setText("Modifier");
        btnmodifierLS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnmodifierLS.setContentAreaFilled(false);
        btnmodifierLS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnmodifierLS.setOpaque(true);
        btnmodifierLS.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnmodifierLSMouseMoved(evt);
            }
        });
        btnmodifierLS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnmodifierLSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmodifierLSMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnmodifierLSMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnmodifierLSMouseReleased(evt);
            }
        });
        btnmodifierLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifierLSActionPerformed(evt);
            }
        });

        btnsupprimerLS.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnsupprimerLS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (1).png"))); // NOI18N
        btnsupprimerLS.setText("Supprimer");
        btnsupprimerLS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnsupprimerLS.setContentAreaFilled(false);
        btnsupprimerLS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnsupprimerLS.setOpaque(true);
        btnsupprimerLS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsupprimerLSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsupprimerLSMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnsupprimerLSMousePressed(evt);
            }
        });
        btnsupprimerLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupprimerLSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelActionLigneEntreeLayout = new javax.swing.GroupLayout(jPanelActionLigneEntree);
        jPanelActionLigneEntree.setLayout(jPanelActionLigneEntreeLayout);
        jPanelActionLigneEntreeLayout.setHorizontalGroup(
            jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActionLigneEntreeLayout.createSequentialGroup()
                .addGroup(jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerLS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnvLS, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnenregistrerLS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierLS, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanelActionLigneEntreeLayout.setVerticalGroup(
            jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelActionLigneEntreeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnvLS, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenregistrerLS, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerLS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierLS, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        TableArticleSortie.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        TableArticleSortie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableArticleSortie.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableArticleSortie.setRowHeight(20);
        TableArticleSortie.setSelectionBackground(new java.awt.Color(0, 153, 153));
        TableArticleSortie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableArticleSortieMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableArticleSortieMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableArticleSortieMouseReleased(evt);
            }
        });
        TableArticleSortie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableArticleSortieKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(TableArticleSortie);

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

    private void TableSortieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableSortieMouseClicked

    }//GEN-LAST:event_TableSortieMouseClicked

    private void TableSortieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableSortieMouseEntered

    }//GEN-LAST:event_TableSortieMouseEntered

    private void TableSortieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableSortieMouseReleased

        afficherLigneSortie();
        afficherArticle();
        AffichageArticle();
        clearLS();
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        DeplaceSortie();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        //txtrechercherArticle.setText("Taper Numero Article");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        //txtrechercher1Article.setText("Taper Nom Article");
        btnsupprimerSortie.setEnabled(true);
        btnmodifierSortie.setEnabled(true);
        btnenregistrerSortie.setEnabled(false);

    }//GEN-LAST:event_TableSortieMouseReleased

    private void SommeMontant() throws SQLException {
        String requete = "select SUM(MontantSortie) as somme from LigneSortie where NumSortie = '" + numeroSortie.getText() + "'";
        ps = connSortie.prepareStatement(requete);
        rs = ps.executeQuery();
        MontantTotal.setText(rs.getString("somme"));
        try {
            if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur BD");
        }
    }

    private void btnenregistrerLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenregistrerLSActionPerformed
        try {
            //String requete5 = "select * from  LigneEntree where NumArticle = '" + article.getText() + "' and NumEntree = '" + numeroEntree.getText() + "'";
            //ps5 = connSortie.prepareStatement(requete5);
            
            String requete = "insert into LigneSortie (NumSortie,NumArticle,NbrSortie,puSortie,MontantSortie) values (?,?,?,?,?)";
            ps = connSortie.prepareStatement(requete);

            String requete2 = "select * from  article where NomArticle = '" + article.getText() + "'";
            

            ps2 = connSortie.prepareStatement(requete2);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("NumArticle");
            
            String requete4 = "select * from article where  NumArticle ='" + num + "'";
            ps4 = connSortie.prepareStatement(requete4);
            rs4 = ps4.executeQuery();
            String ancienQte = rs4.getString("QteStock");
            String ancienMtn = rs4.getString("MontantStock");
            
            ps.setString(1, numeroSortie.getText());
            ps.setString(2, num);
            ps.setString(3, nbr.getText());
            ps.setString(4, puSortie.getText());
            int mtn = Integer.parseInt(nbr.getText()) * Integer.parseInt(puSortie.getText());
            ps.setString(5, String.valueOf(mtn));
            //String avant = MontantTotal.getText();
            //Long apres = mtn + Integer.parseInt(avant);
            //MontantTotal.setText(String.valueOf(apres));
            ps.execute();

            String requete3 = "update article set QteStock =? ,MontantStock =? where  NumArticle ='" + num + "'";
            ps3 = connSortie.prepareStatement(requete3);
            
            int qte = Integer.parseInt(ancienQte) - Integer.parseInt(nbr.getText());
            ps3.setString(1, String.valueOf(qte));
            ps3.setString(2, String.valueOf(Integer.parseInt(ancienMtn)-mtn));
            ps3.execute();

            JOptionPane.showMessageDialog(null, "Enregistrement succes");

            AffichageLigneSortie(numeroSortie.getText());
            clearLS();
            /*try {
                SommeMontant();
            } catch (SQLException ex) {
                Logger.getLogger(Sortie.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } catch (HeadlessException | SQLException e) {
            System.out.println("--> SQLException : " + e);
            JOptionPane.showMessageDialog(null, "Tout est Obligatoire");
        } finally {
            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "deja inserre" + ex);
            }
        }

    }//GEN-LAST:event_btnenregistrerLSActionPerformed

    private void TableSortieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableSortieKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
            DeplaceLigneSortie();
        }
    }//GEN-LAST:event_TableSortieKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        //clear();
    }//GEN-LAST:event_formMouseClicked

    private void btnnvLSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvLSMouseEntered
        btnnvLS.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnnvLSMouseEntered

    private void btnnvLSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvLSMouseExited
        btnnvLS.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnnvLSMouseExited

    private void btnnvLSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvLSMousePressed
        btnnvLS.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnnvLSMousePressed

    private void btnnvLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnvLSActionPerformed

    }//GEN-LAST:event_btnnvLSActionPerformed

    private void btnnvLSMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvLSMouseReleased
        btnenregistrerLS.setEnabled(true);
        btnsupprimerLS.setEnabled(false);
        btnmodifierLS.setEnabled(false);
        clearLS();
    }//GEN-LAST:event_btnnvLSMouseReleased

    private void btnenregistrerLSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerLSMouseExited
        btnenregistrerLS.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnenregistrerLSMouseExited

    private void btnenregistrerLSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerLSMousePressed
        btnenregistrerLS.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnenregistrerLSMousePressed

    private void btnenregistrerLSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerLSMouseEntered
        btnenregistrerLS.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnenregistrerLSMouseEntered

    private void btnmodifierLSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierLSMouseEntered
        btnmodifierLS.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnmodifierLSMouseEntered

    private void btnmodifierLSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierLSMouseExited
        btnmodifierLS.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnmodifierLSMouseExited

    private void btnmodifierLSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierLSMousePressed
        btnmodifierLS.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnmodifierLSMousePressed

    private void btnmodifierLSMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierLSMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierLSMouseReleased

    private void btnmodifierLSMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierLSMouseMoved

    }//GEN-LAST:event_btnmodifierLSMouseMoved

    private void btnmodifierLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifierLSActionPerformed
        try {

            //String requete1String = "update categorie set NomCategorie =? where  NumCategorie ='" + numeroLigneEntree.getText() + "'";

            String requete = "update LigneSortie set NumSortie =?,NumArticle=?,NbrSortie=?,puSortie=?,MontantSortie=? where  NumLigneSortie ='" + numeroLigneSortie.getText() + "'";
            ps = connSortie.prepareStatement(requete);

            String requete2 = "select * from  article where NomArticle = '" + article.getText() + "'";

            ps2 = connSortie.prepareStatement(requete2);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("NumArticle");
            ps.setString(1, numeroSortie.getText());
            ps.setString(2, num);
            ps.setString(3, nbr.getText());
            ps.setString(4, puSortie.getText());
            long mtn = Integer.parseInt(nbr.getText()) * Integer.parseInt(puSortie.getText());
            ps.setString(5, String.valueOf(mtn));
            ps.execute();
            JOptionPane.showMessageDialog(null, "Modification succes");
        } catch (HeadlessException | SQLException e) {
            System.out.println("--> SQLException : " + e);
            JOptionPane.showMessageDialog(null, "Tout est Obligatoire");
        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
        AffichageLigneSortie(numeroSortie.getText());
        clearLS();
        try {
            SommeMontant();
        } catch (SQLException ex) {
            Logger.getLogger(Sortie.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnmodifierLSActionPerformed

    private void btnsupprimerLSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerLSMouseEntered
        btnsupprimerLS.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnsupprimerLSMouseEntered

    private void btnsupprimerLSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerLSMouseExited
        btnsupprimerLS.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnsupprimerLSMouseExited

    private void btnsupprimerLSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerLSMousePressed
        btnsupprimerLS.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnsupprimerLSMousePressed

    private void btnsupprimerLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerLSActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(null, "attention vous devez suprimer une Ligne Sortie, est ce que tu es sur?",
                "Supprimer Ligne Sortie", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                String requete2 = "select * from  article where NomArticle = '" + article.getText() + "'";
                ps2 = connSortie.prepareStatement(requete2);
                rs2 = ps2.executeQuery();
                String num = rs2.getString("NumArticle");
                
                String requete4 = "select * from article where  NumArticle ='" + num + "'";
                ps4 = connSortie.prepareStatement(requete4);
                rs4 = ps4.executeQuery();
                String ancienQte = rs4.getString("QteStock");
                String ancienMtn = rs4.getString("MontantStock");
                
                String requete3 = "update article set QteStock =? ,MontantStock =? where  NumArticle ='" + num + "'";
                ps3 = connSortie.prepareStatement(requete3);
                int qte = Integer.parseInt(ancienQte) + Integer.parseInt(nbr.getText());
                ps3.setString(1, String.valueOf(qte));
                int mtn = Integer.parseInt(nbr.getText()) * Integer.parseInt(puSortie.getText());
                ps3.setString(2, String.valueOf(Integer.parseInt(ancienMtn)+mtn));
                ps3.execute();
                
                String requete = "delete from LigneSortie where NumLigneSortie = '" + numeroLigneSortie.getText() + "'";
                ps = connSortie.prepareStatement(requete);
                ps.execute();
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "erreur de suppression" + e.getMessage());
        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
        AffichageLigneSortie(numeroSortie.getText());
        clearLS();
        try {
            SommeMontant();
        } catch (SQLException ex) {
            Logger.getLogger(Sortie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsupprimerLSActionPerformed

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
            TableSortie.print(JTable.PrintMode.NORMAL, header, footer);

        } catch (java.awt.print.PrinterException e) {
            System.err.format("Erreur d'impression ", e.getMessage());
        }

    }//GEN-LAST:event_printbtnActionPerformed

    private void TableLigneSortieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableLigneSortieMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableLigneSortieMouseClicked

    private void TableLigneSortieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableLigneSortieMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TableLigneSortieMouseEntered

    private void TableLigneSortieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableLigneSortieMouseReleased
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        DeplaceLigneSortie();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercherEntree.setText("Taper Numero Categorie");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("Taper Nom Categorie");
        btnsupprimerLS.setEnabled(true);
        btnmodifierLS.setEnabled(true);
        btnenregistrerLS.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_TableLigneSortieMouseReleased

    private void TableLigneSortieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableLigneSortieKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableLigneSortieKeyReleased

    private void btnnvSortieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvSortieMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvSortieMouseEntered

    private void btnnvSortieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvSortieMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvSortieMouseExited

    private void btnnvSortieMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvSortieMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvSortieMousePressed

    private void btnnvSortieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvSortieMouseReleased
        btnenregistrerSortie.setEnabled(true);
        btnsupprimerSortie.setEnabled(false);
        btnmodifierSortie.setEnabled(false);
        clearSortie();
    }//GEN-LAST:event_btnnvSortieMouseReleased

    private void btnnvSortieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnvSortieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvSortieActionPerformed

    private void btnenregistrerSortieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerSortieMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnenregistrerSortieMouseEntered

    private void btnenregistrerSortieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerSortieMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnenregistrerSortieMouseExited

    private void btnenregistrerSortieMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerSortieMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnenregistrerSortieMousePressed

    private void btnenregistrerSortieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenregistrerSortieActionPerformed
        try {
            String requete = "insert into Sortie (refSortie,NumSection,DateSortie,MontantTotalSortie,ObservationSortie) values (?,?,?,?,?)";
            ps = connSortie.prepareStatement(requete);

            String requete2 = "select * from  Section where NomSection = '" + ComboSection.getSelectedItem() + "'";

            ps2 = connSortie.prepareStatement(requete2);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("idSection");
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

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "deja inserre" + ex);
            }
        }
        AffichageSortie();
        clearSortie();

    }//GEN-LAST:event_btnenregistrerSortieActionPerformed

    private void btnmodifierSortieMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierSortieMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierSortieMouseMoved

    private void btnmodifierSortieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierSortieMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierSortieMouseEntered

    private void btnmodifierSortieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierSortieMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierSortieMouseExited

    private void btnmodifierSortieMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierSortieMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierSortieMousePressed

    private void btnmodifierSortieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierSortieMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierSortieMouseReleased

    private void btnmodifierSortieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifierSortieActionPerformed
        try {
            String requete = "update Sortie set refSortie =?,NumSection=?,DateSortie=?,MontantTotalSortie=?,ObservationSortie=? where  NumSortie ='" + numeroSortie.getText() + "'";
            ps = connSortie.prepareStatement(requete);

            String requete2 = "select * from  Section where idSection = '" + ComboSection.getSelectedItem() + "'";

            ps2 = connSortie.prepareStatement(requete2);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("idSection");
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

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "deja inserre" + ex);
            }
        }
        AffichageSortie();
        clearSortie();
        masquerArticle();
        masquerLigneSortie();
    }//GEN-LAST:event_btnmodifierSortieActionPerformed

    private void btnsupprimerSortieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerSortieMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsupprimerSortieMouseEntered

    private void btnsupprimerSortieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerSortieMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsupprimerSortieMouseExited

    private void btnsupprimerSortieMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerSortieMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsupprimerSortieMousePressed

    private void btnsupprimerSortieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerSortieActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(null, "attention vous devez suprimer une Sortie, Toute les Lignes Sortie de ce Sortie seront tous supprimées ,est ce que tu es sur?",
                    "Supprimer Article", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                String requete2 = "select * from LigneSortie where NumSortie = '" + numeroSortie.getText() + "'";
                ps2 = connSortie.prepareStatement(requete2);
                rs2 = ps2.executeQuery();
                
                while (rs2.next()) {  
                    try {
                    String num = rs2.getString("NumArticle");
                    String nbr = rs2.getString("NbrSortie");
                    String pu = rs2.getString("puSortie");
                    String mtn = rs2.getString("MontantSortie");
                    
                    String requete4 = "select * from article where  NumArticle ='" + num + "'";
                    ps4 = connSortie.prepareStatement(requete4);
                    rs4 = ps4.executeQuery();
                    String ancienQte = rs4.getString("QteStock");
                    String ancienMtn = rs4.getString("MontantStock");
                
                    String requete3 = "update article set QteStock =? ,MontantStock =? where  NumArticle ='" + num + "'";
                    ps3 = connSortie.prepareStatement(requete3);
                    int qte = Integer.parseInt(ancienQte) + Integer.parseInt(nbr);
                    ps3.setString(1, String.valueOf(qte));
                    ps3.setString(2, String.valueOf(Integer.parseInt(ancienMtn)+mtn));
                    ps3.execute();
                    
                    
                    } catch (HeadlessException | SQLException e) {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "erreur de suppression" + e.getMessage());
                    }
                }
                String requete6 = "delete from LigneSortie where NumSortie = '" + numeroSortie.getText() + "'";
                ps5 = connSortie.prepareStatement(requete6);
                ps5.execute();
                
                String requete = "delete from Sortie where NumSortie = '" + numeroSortie.getText() + "'";
                ps = connSortie.prepareStatement(requete);
                ps.execute();
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "erreur de suppression" + e.getMessage());
        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }
                if(rs5 != null){
                   rs5.close();
                }
                if(ps5 != null){
                   ps5.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
        AffichageArticle();
    }//GEN-LAST:event_btnsupprimerSortieActionPerformed

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
        AffichageSortie();
        clearSortie();

        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbachground.setIcon(img);
        txtrechercherEntree.setText("");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("Taper Reference Sortie");

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        // TODO add your handling code here:
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

        try {
            String requete = "select NumSortie as 'Numero' ,RefSortie as 'Reference' ,Section.idSection as 'Section' ,DateSortie as 'Date' ,MontantTotalSortie as 'MontantTotal' ,ObservationSortie as 'Observation' from Sortie, Section WHERE\n"
                    + "(Section.idSection=Sortie.NumSection) and NumSortie LIKE ?";
            ps = connSortie.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercherEntree.getText() + "%");
            rs = ps.executeQuery();
            TableSortie.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }

    }//GEN-LAST:event_txtrechercherEntreeKeyReleased

    private void txtrechercherEntreeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherEntreeKeyTyped
        clearSortie();
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercherEntreeKeyTyped

    private void jButtonRefreshArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshArticleActionPerformed
        AffichageArticle();
        clearLS();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackgroundarticle.setIcon(img);
        txtrechercherarticle.setText("Taper Numero ou Nom Sortie");
    }//GEN-LAST:event_jButtonRefreshArticleActionPerformed

    private void txtrechercher1EntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1EntreeMouseClicked
        AffichageSortie();
        clearSortie();

        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbachground.setIcon(img);
        txtrechercherEntree.setText("Taper Numero Sortie");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("");

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
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

        try {
            String requete = "select NumSortie as 'Numero' ,RefSortie as 'Reference' ,Section.idSection as 'Section' ,DateSortie as 'Date' ,MontantTotalSortie as 'MontantTotal' ,ObservationSortie as 'Observation' from Sortie, Section WHERE\n"
                    + "(Section.idSection=Sortie.NumSection) and RefSortie LIKE ?";
            ps = connSortie.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercher1Entree.getText() + "%");
            rs = ps.executeQuery();
            TableSortie.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
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
        try {
            String requete = "select * from article  where NumArticle LIKE ? or NomArticle LIKE ?";
            ps = connSortie.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercherarticle.getText() + "%");
            ps.setString(2, "%" + txtrechercherarticle.getText() + "%");
            rs = ps.executeQuery();
            TableArticleSortie.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            try {
                if(rs != null){
                   rs.close();
                }
                if(ps != null){
                   ps.close();
                }
                if(rs2 != null){
                   rs2.close();
                }
                if(ps2 != null){
                   ps2.close();
                }
                if(rs3 != null){
                   rs3.close();
                }
                if(ps3 != null){
                   ps3.close();
                }
                if(rs4 != null){
                   rs4.close();
                }
                if(ps4 != null){
                   ps4.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
    }//GEN-LAST:event_txtrechercherarticleKeyReleased

    private void txtrechercherarticleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherarticleKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherarticleKeyTyped

    private void TableArticleSortieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleSortieMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleSortieMouseClicked

    private void TableArticleSortieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleSortieMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleSortieMouseEntered

    private void TableArticleSortieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleSortieMouseReleased
        DeplaceArticle();
        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbackgroundarticle.setIcon(img);
        txtrechercherarticle.setText("Tapez Numero ou Nom Article");
    }//GEN-LAST:event_TableArticleSortieMouseReleased

    private void TableArticleSortieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableArticleSortieKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleSortieKeyReleased

    private void puSortieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puSortieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_puSortieActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboSection;
    private javax.swing.JPanel JPanelLigneEntree;
    private javax.swing.JLabel MontantTotal;
    private javax.swing.JTable TableArticleSortie;
    private javax.swing.JTable TableLigneSortie;
    private javax.swing.JTable TableSortie;
    private javax.swing.JLabel article;
    private javax.swing.JButton btnenregistrerLS;
    private javax.swing.JButton btnenregistrerSortie;
    public javax.swing.JButton btnmodifierLS;
    public javax.swing.JButton btnmodifierSortie;
    private javax.swing.JButton btnnvLS;
    private javax.swing.JButton btnnvSortie;
    private javax.swing.JButton btnsupprimerLS;
    private javax.swing.JButton btnsupprimerSortie;
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
    private javax.swing.JLabel numeroLigneSortie;
    private javax.swing.JLabel numeroSortie;
    private javax.swing.JTextArea obs;
    private javax.swing.JButton printbtn;
    private javax.swing.JButton printbtn1;
    private javax.swing.JTextField puSortie;
    private javax.swing.JTextField ref;
    private javax.swing.JLabel txtbachground;
    private javax.swing.JLabel txtbackground1;
    private javax.swing.JLabel txtbackgroundarticle;
    private javax.swing.JTextField txtrechercher1Entree;
    private javax.swing.JTextField txtrechercherEntree;
    private javax.swing.JTextField txtrechercherarticle;
    // End of variables declaration//GEN-END:variables
}
