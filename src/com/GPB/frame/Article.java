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
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author anouer
 */
public class Article extends javax.swing.JInternalFrame {

    Connection conn = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    ResultSet rs4 = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    PreparedStatement ps3 = null;
    PreparedStatement ps4 = null;
    static String test;

    /**
     * Creates new form Examen
     *
     * @throws java.sql.SQLException
     */
    public Article() throws SQLException {
        initComponents();
        remove_title_bar();

        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercherCategorie.setText("Taper Numero Categorie");
        txtbachground2.setIcon(img);
        txtrechercherArticle.setText("Taper Numero Article");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Categorie.setText("Taper Nom Categorie");
        txtbackground3.setIcon(img2);
        txtrechercher1Article.setText("Taper Nom Article");

        conn = ConexionBD.Conexion();
        conn = ConexionBD.Conexion();
        AffichageCategorie();
        AffichageArticle();
        remplirComboCategorie();

        btnsupprimerCategorie.setEnabled(false);
        btnmodifierCategorie.setEnabled(false);
        btnenregistrerCategorie.setEnabled(false);
        btnsupprimerArticle.setEnabled(false);
        btnmodifierArticle.setEnabled(false);
        btnenregistrerArticle.setEnabled(false);

        /*String requete4 = "delete from effectif";
            ps = conn.prepareStatement(requete4);
            ps.execute();*/
    }

    private void remove_title_bar() {
        putClientProperty("Article.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    private void AffichageCategorie() {
        try {
            String requete = "select NumCategorie as 'Numero' ,NomCategorie as 'Nom de la Categorie' from categorie ";
            ps = conn.prepareStatement(requete);
            rs = ps.executeQuery();
            TableCategorie.setModel(DbUtils.resultSetToTableModel(rs));
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

    private void AffichageArticle() {
        try {
            String requete = "select NumArticle as 'Numero' ,NomArticle as 'Nom Article' ,pu as 'Prix unitaire' ,QteStock as 'Quatité en Stock' ,categorie.NomCategorie as 'Categorie' ,MontantStock as 'Montant en Stock' from article, categorie WHERE\n"
                    + "(categorie.NumCategorie=article.categorie)";

            String requete2 = "select * from article";

            ps = conn.prepareStatement(requete);
            rs = ps.executeQuery();
            TableArticle.setModel(DbUtils.resultSetToTableModel(rs));
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

    public void DeplaceCategorie() {
        try {

            int row = TableCategorie.getSelectedRow();
            Article.test = (TableCategorie.getModel().getValueAt(row, 0).toString());
            String requet = " select * from  categorie where NumCategorie = '" + test + "' ";
            ps = conn.prepareStatement(requet);
            rs = ps.executeQuery();

            if (rs.next()) {
                String t1 = rs.getString("NomCategorie");
                String t2 = rs.getString("NumCategorie");
                txtNomCategorie.setText(t1);
                numeroCategorie.setText(t2);
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

            int row = TableArticle.getSelectedRow();
            Article.test = (TableArticle.getModel().getValueAt(row, 0).toString());
            String requet = " select * from article where NumArticle = '" + test + "'";
            ps = conn.prepareStatement(requet);
            rs = ps.executeQuery();

            if (rs.next()) {
                String t1 = rs.getString("NumArticle");
                String t2 = rs.getString("NomArticle");
                String t3 = rs.getString("pu");
                String t4 = rs.getString("QteStock");
                String t5 = rs.getString("categorie");
                String cat = " select * from categorie where NumCategorie = '" + t5 + "'";
                ps2 = conn.prepareStatement(cat);
                rs2 = ps2.executeQuery();
                numeroArticle.setText(t1);
                DesignationArticle.setText(t2);
                puArticle.setText(t3);
                qteStockArticle.setText(t4);
                ComboCategorie.setSelectedItem(rs2.getString("NomCategorie"));
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

    public void remplirComboCategorie() {
        String requet = " select * from  categorie";

        try {
            ps = conn.prepareStatement(requet);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nom = rs.getString("NomCategorie").toString();
                ComboCategorie.addItem(nom);
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

    public void clearCategorie() {
        try {
            numeroCategorie.setText("");
            txtNomCategorie.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clearArticle() {
        try {
            numeroArticle.setText("");
            DesignationArticle.setText("");
            puArticle.setText("");
            qteStockArticle.setText("");
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
        txtrechercher1Categorie = new javax.swing.JTextField();
        txtbackground1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DesignationArticle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        numeroArticle = new javax.swing.JLabel();
        puArticle = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        qteStockArticle = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ComboCategorie = new javax.swing.JComboBox<>();
        txtrechercherCategorie = new javax.swing.JTextField();
        txtbachground = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableArticle = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        jPanel2 = new javax.swing.JPanel();
        btnnvCategorie = new javax.swing.JButton();
        btnenregistrerCategorie = new javax.swing.JButton();
        btnmodifierCategorie = new javax.swing.JButton();
        btnsupprimerCategorie = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        printbtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableCategorie = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        jPanel3 = new javax.swing.JPanel();
        btnnvArticle = new javax.swing.JButton();
        btnenregistrerArticle = new javax.swing.JButton();
        btnmodifierArticle = new javax.swing.JButton();
        btnsupprimerArticle = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        printbtn1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNomCategorie = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        numeroCategorie = new javax.swing.JLabel();
        txtrechercher1Article = new javax.swing.JTextField();
        txtbackground3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtrechercherArticle = new javax.swing.JTextField();
        txtbachground2 = new javax.swing.JLabel();

        setBorder(null);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        txtrechercher1Categorie.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercher1Categorie.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercher1Categorie.setForeground(new java.awt.Color(0, 153, 153));
        txtrechercher1Categorie.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercher1Categorie.setAlignmentX(0.0F);
        txtrechercher1Categorie.setAlignmentY(0.0F);
        txtrechercher1Categorie.setBorder(null);
        txtrechercher1Categorie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrechercher1CategorieMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtrechercher1CategorieMouseEntered(evt);
            }
        });
        txtrechercher1Categorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercher1CategorieActionPerformed(evt);
            }
        });
        txtrechercher1Categorie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercher1CategorieKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercher1CategorieKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercher1CategorieKeyTyped(evt);
            }
        });
        getContentPane().add(txtrechercher1Categorie);
        txtrechercher1Categorie.setBounds(320, 80, 200, 10);

        txtbackground1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        txtbackground1.setAlignmentY(0.0F);
        getContentPane().add(txtbackground1);
        txtbackground1.setBounds(310, 70, 220, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/interface.png"))); // NOI18N
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(230, 60, 70, 40);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Formulaire Article :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.setFocusTraversalPolicyProvider(true);
        jPanel1.setName(""); // NOI18N

        jLabel1.setText("Designation   : ");

        DesignationArticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DesignationArticleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DesignationArticleMouseExited(evt);
            }
        });

        jLabel2.setText("Numero : ");

        numeroArticle.setText("                  ");

        puArticle.setEditable(false);
        puArticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                puArticleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                puArticleMouseExited(evt);
            }
        });

        jLabel5.setText("Prix unitaire   : ");

        qteStockArticle.setEditable(false);
        qteStockArticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                qteStockArticleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                qteStockArticleMouseExited(evt);
            }
        });

        jLabel6.setText("Qantité en stock   : ");

        jLabel7.setText("Catégorie   : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(numeroArticle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DesignationArticle)
                            .addComponent(puArticle)
                            .addComponent(qteStockArticle, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(ComboCategorie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numeroArticle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DesignationArticle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(puArticle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qteStockArticle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(590, 410, 280, 220);

        txtrechercherCategorie.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercherCategorie.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercherCategorie.setForeground(new java.awt.Color(0, 153, 153));
        txtrechercherCategorie.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercherCategorie.setAlignmentX(0.0F);
        txtrechercherCategorie.setAlignmentY(0.0F);
        txtrechercherCategorie.setBorder(null);
        txtrechercherCategorie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrechercherCategorieMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtrechercherCategorieMouseEntered(evt);
            }
        });
        txtrechercherCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercherCategorieActionPerformed(evt);
            }
        });
        txtrechercherCategorie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercherCategorieKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercherCategorieKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercherCategorieKeyTyped(evt);
            }
        });
        getContentPane().add(txtrechercherCategorie);
        txtrechercherCategorie.setBounds(10, 80, 200, 10);

        txtbachground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        txtbachground.setAlignmentY(0.0F);
        getContentPane().add(txtbachground);
        txtbachground.setBounds(0, 70, 220, 30);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Listes des Articles :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableArticle.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        TableArticle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableArticle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableArticle.setRowHeight(20);
        TableArticle.setSelectionBackground(new java.awt.Color(0, 153, 153));
        TableArticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableArticleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableArticleMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableArticleMouseReleased(evt);
            }
        });
        TableArticle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableArticleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TableArticle);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 410, 570, 110);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Action :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 3, 12))); // NOI18N

        btnnvCategorie.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnnvCategorie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file.png"))); // NOI18N
        btnnvCategorie.setText("Nouveau");
        btnnvCategorie.setToolTipText("");
        btnnvCategorie.setAutoscrolls(true);
        btnnvCategorie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnnvCategorie.setContentAreaFilled(false);
        btnnvCategorie.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnnvCategorie.setOpaque(true);
        btnnvCategorie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnnvCategorieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnnvCategorieMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnnvCategorieMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnnvCategorieMouseReleased(evt);
            }
        });
        btnnvCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnvCategorieActionPerformed(evt);
            }
        });

        btnenregistrerCategorie.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnenregistrerCategorie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/save.png"))); // NOI18N
        btnenregistrerCategorie.setText("Enregistrer");
        btnenregistrerCategorie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnenregistrerCategorie.setContentAreaFilled(false);
        btnenregistrerCategorie.setOpaque(true);
        btnenregistrerCategorie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnenregistrerCategorieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnenregistrerCategorieMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnenregistrerCategorieMousePressed(evt);
            }
        });
        btnenregistrerCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenregistrerCategorieActionPerformed(evt);
            }
        });

        btnmodifierCategorie.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnmodifierCategorie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (2).png"))); // NOI18N
        btnmodifierCategorie.setText("Modifier");
        btnmodifierCategorie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnmodifierCategorie.setContentAreaFilled(false);
        btnmodifierCategorie.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnmodifierCategorie.setOpaque(true);
        btnmodifierCategorie.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnmodifierCategorieMouseMoved(evt);
            }
        });
        btnmodifierCategorie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnmodifierCategorieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmodifierCategorieMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnmodifierCategorieMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnmodifierCategorieMouseReleased(evt);
            }
        });
        btnmodifierCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifierCategorieActionPerformed(evt);
            }
        });

        btnsupprimerCategorie.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnsupprimerCategorie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (1).png"))); // NOI18N
        btnsupprimerCategorie.setText("Supprimer");
        btnsupprimerCategorie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnsupprimerCategorie.setContentAreaFilled(false);
        btnsupprimerCategorie.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnsupprimerCategorie.setOpaque(true);
        btnsupprimerCategorie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsupprimerCategorieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsupprimerCategorieMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnsupprimerCategorieMousePressed(evt);
            }
        });
        btnsupprimerCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupprimerCategorieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerCategorie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnvCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnenregistrerCategorie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnvCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenregistrerCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerCategorie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(430, 230, 390, 110);

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
                .addComponent(printbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(printbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(430, 530, 140, 100);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setBackground(new java.awt.Color(0, 102, 255));
        jLabel10.setFont(new java.awt.Font("Advent Pro", 0, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("GESTION DES ARTICLES");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(652, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 0, 1160, 50);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Listes des Categories :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableCategorie.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        TableCategorie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableCategorie.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableCategorie.setRowHeight(20);
        TableCategorie.setSelectionBackground(new java.awt.Color(0, 153, 153));
        TableCategorie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCategorieMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableCategorieMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableCategorieMouseReleased(evt);
            }
        });
        TableCategorie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableCategorieKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(TableCategorie);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(0, 120, 420, 220);

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
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerArticle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnvArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnenregistrerArticle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnvArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenregistrerArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerArticle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 520, 390, 110);

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
                .addComponent(printbtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(printbtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel6);
        jPanel6.setBounds(730, 130, 140, 100);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Formulaire Categorie :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jPanel7.setForeground(new java.awt.Color(0, 0, 204));
        jPanel7.setFocusTraversalPolicyProvider(true);
        jPanel7.setName(""); // NOI18N

        jLabel3.setText("Nom Categorie   : ");

        txtNomCategorie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtNomCategorieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtNomCategorieMouseExited(evt);
            }
        });

        jLabel4.setText("Numero : ");

        numeroCategorie.setText("                  ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomCategorie))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(numeroCategorie)
                        .addGap(0, 140, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(numeroCategorie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        getContentPane().add(jPanel7);
        jPanel7.setBounds(430, 120, 290, 110);

        txtrechercher1Article.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercher1Article.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercher1Article.setForeground(new java.awt.Color(0, 153, 153));
        txtrechercher1Article.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercher1Article.setAlignmentX(0.0F);
        txtrechercher1Article.setAlignmentY(0.0F);
        txtrechercher1Article.setBorder(null);
        txtrechercher1Article.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrechercher1ArticleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtrechercher1ArticleMouseEntered(evt);
            }
        });
        txtrechercher1Article.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercher1ArticleActionPerformed(evt);
            }
        });
        txtrechercher1Article.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercher1ArticleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercher1ArticleKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercher1ArticleKeyTyped(evt);
            }
        });
        getContentPane().add(txtrechercher1Article);
        txtrechercher1Article.setBounds(320, 370, 200, 10);

        txtbackground3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        txtbackground3.setAlignmentY(0.0F);
        getContentPane().add(txtbackground3);
        txtbackground3.setBounds(310, 360, 220, 30);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/interface.png"))); // NOI18N
        jButton3.setAlignmentY(0.0F);
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(230, 350, 70, 40);

        txtrechercherArticle.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercherArticle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercherArticle.setForeground(new java.awt.Color(0, 153, 153));
        txtrechercherArticle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercherArticle.setAlignmentX(0.0F);
        txtrechercherArticle.setAlignmentY(0.0F);
        txtrechercherArticle.setBorder(null);
        txtrechercherArticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrechercherArticleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtrechercherArticleMouseEntered(evt);
            }
        });
        txtrechercherArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercherArticleActionPerformed(evt);
            }
        });
        txtrechercherArticle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercherArticleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercherArticleKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercherArticleKeyTyped(evt);
            }
        });
        getContentPane().add(txtrechercherArticle);
        txtrechercherArticle.setBounds(10, 370, 200, 10);

        txtbachground2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        txtbachground2.setAlignmentY(0.0F);
        getContentPane().add(txtbachground2);
        txtbachground2.setBounds(0, 360, 220, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AffichageCategorie();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercherCategorie.setText("Taper Numero Categorie");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Categorie.setText("Taper Nom Categorie");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtrechercher1CategorieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1CategorieMouseClicked
        AffichageCategorie();
        clearCategorie();

        ImageIcon img2 = new ImageIcon(getClass().getResource("txt1.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Categorie.setText("");
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercherCategorie.setText("Taper Numero Categorie");

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));

    }//GEN-LAST:event_txtrechercher1CategorieMouseClicked

    private void txtrechercher1CategorieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1CategorieMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1CategorieMouseEntered

    private void txtrechercher1CategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercher1CategorieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1CategorieActionPerformed

    private void txtrechercher1CategorieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1CategorieKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1CategorieKeyPressed

    private void txtrechercher1CategorieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1CategorieKeyReleased
        try {
            String requete = "select NumCategorie as 'Numero' ,NomCategorie as 'Nom Categorie' from categorie  where NomCategorie LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercher1Categorie.getText() + "%");
            rs = ps.executeQuery();
            TableCategorie.setModel(DbUtils.resultSetToTableModel(rs));
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

    }//GEN-LAST:event_txtrechercher1CategorieKeyReleased

    private void txtrechercher1CategorieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1CategorieKeyTyped
        //clear();
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercher1CategorieKeyTyped

    private void txtrechercherCategorieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherCategorieMouseClicked
        AffichageCategorie();
        clearCategorie();

        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbachground.setIcon(img);
        txtrechercherCategorie.setText("");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Categorie.setText("Taper Nom Categorie");

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherCategorieMouseClicked

    private void txtrechercherCategorieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherCategorieMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherCategorieMouseEntered

    private void txtrechercherCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercherCategorieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherCategorieActionPerformed

    private void txtrechercherCategorieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherCategorieKeyPressed

    }//GEN-LAST:event_txtrechercherCategorieKeyPressed

    private void txtrechercherCategorieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherCategorieKeyReleased

        try {
            String requete = "select NumCategorie as 'Numero' ,NomCategorie as 'Nom Categorie' from categorie  where NumCategorie LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercherCategorie.getText() + "%");
            rs = ps.executeQuery();
            TableCategorie.setModel(DbUtils.resultSetToTableModel(rs));
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
    }//GEN-LAST:event_txtrechercherCategorieKeyReleased

    private void txtrechercherCategorieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherCategorieKeyTyped
        clearCategorie();
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));

    }//GEN-LAST:event_txtrechercherCategorieKeyTyped

    private void TableArticleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleMouseClicked

    }//GEN-LAST:event_TableArticleMouseClicked

    private void TableArticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleMouseEntered

    }//GEN-LAST:event_TableArticleMouseEntered

    private void TableArticleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleMouseReleased

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        DeplaceArticle();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercherArticle.setText("Taper Numero Article");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Article.setText("Taper Nom Article");
        btnsupprimerArticle.setEnabled(true);
        btnmodifierArticle.setEnabled(true);
        btnenregistrerArticle.setEnabled(false);
    }//GEN-LAST:event_TableArticleMouseReleased

    private void btnenregistrerCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenregistrerCategorieActionPerformed
        try {
            String requete = "insert into  categorie (NomCategorie) values (?)";
            ps = conn.prepareStatement(requete);
            ps.setString(1, txtNomCategorie.getText());
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
        AffichageCategorie();
        clearCategorie();
        remplirComboCategorie();

    }//GEN-LAST:event_btnenregistrerCategorieActionPerformed

    private void TableArticleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableArticleKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
            DeplaceCategorie();
        }
    }//GEN-LAST:event_TableArticleKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        //clear();
    }//GEN-LAST:event_formMouseClicked

    private void DesignationArticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DesignationArticleMouseEntered

    }//GEN-LAST:event_DesignationArticleMouseEntered

    private void DesignationArticleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DesignationArticleMouseExited

    }//GEN-LAST:event_DesignationArticleMouseExited

    private void btnnvCategorieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvCategorieMouseEntered
        btnnvCategorie.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnnvCategorieMouseEntered

    private void btnnvCategorieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvCategorieMouseExited
        btnnvCategorie.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnnvCategorieMouseExited

    private void btnnvCategorieMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvCategorieMousePressed
        btnnvCategorie.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnnvCategorieMousePressed

    private void btnnvCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnvCategorieActionPerformed

    }//GEN-LAST:event_btnnvCategorieActionPerformed

    private void btnnvCategorieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvCategorieMouseReleased
        btnenregistrerCategorie.setEnabled(true);
        btnsupprimerCategorie.setEnabled(false);
        btnmodifierCategorie.setEnabled(false);
        clearCategorie();
    }//GEN-LAST:event_btnnvCategorieMouseReleased

    private void btnenregistrerCategorieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerCategorieMouseExited
        btnenregistrerCategorie.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnenregistrerCategorieMouseExited

    private void btnenregistrerCategorieMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerCategorieMousePressed
        btnenregistrerCategorie.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnenregistrerCategorieMousePressed

    private void btnenregistrerCategorieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerCategorieMouseEntered
        btnenregistrerCategorie.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnenregistrerCategorieMouseEntered

    private void btnmodifierCategorieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierCategorieMouseEntered
        btnmodifierCategorie.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnmodifierCategorieMouseEntered

    private void btnmodifierCategorieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierCategorieMouseExited
        btnmodifierCategorie.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnmodifierCategorieMouseExited

    private void btnmodifierCategorieMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierCategorieMousePressed
        btnmodifierCategorie.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnmodifierCategorieMousePressed

    private void btnmodifierCategorieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierCategorieMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierCategorieMouseReleased

    private void btnmodifierCategorieMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierCategorieMouseMoved

    }//GEN-LAST:event_btnmodifierCategorieMouseMoved

    private void btnmodifierCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifierCategorieActionPerformed
        try {

            String requete = "update categorie set NomCategorie =? where  NumCategorie ='" + numeroCategorie.getText() + "'";

            ps = conn.prepareStatement(requete);
            ps.setString(1, txtNomCategorie.getText());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Modification avec succès");
        } catch (SQLException ex) {
            System.out.println(ex);
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

        AffichageCategorie();
        clearCategorie();
        remplirComboCategorie();
    }//GEN-LAST:event_btnmodifierCategorieActionPerformed

    private void btnsupprimerCategorieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerCategorieMouseEntered
        btnsupprimerCategorie.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnsupprimerCategorieMouseEntered

    private void btnsupprimerCategorieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerCategorieMouseExited
        btnsupprimerCategorie.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnsupprimerCategorieMouseExited

    private void btnsupprimerCategorieMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerCategorieMousePressed
        btnsupprimerCategorie.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnsupprimerCategorieMousePressed

    private void btnsupprimerCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerCategorieActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(null, "attention vous devez suprimer une Categorie, est ce que vous êtes sur?",
                    "Supprimer Categorie", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                String requete = "delete from categorie where NumCategorie = '" + numeroCategorie.getText() + "'";
                String requete2 = "select * from article where categorie = '" + numeroCategorie.getText() + "'";
                ps2 = conn.prepareStatement(requete2);
                rs2 = ps2.executeQuery();
                if(rs2.next()){
                    JOptionPane.showMessageDialog(null, "Erreur de suppression car il existe des articles pour cette categorie");
                } else{
                ps = conn.prepareStatement(requete);
                ps.execute();
                }
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
        AffichageCategorie();
        clearCategorie();
        remplirComboCategorie();
    }//GEN-LAST:event_btnsupprimerCategorieActionPerformed

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
            TableArticle.print(JTable.PrintMode.NORMAL, header, footer);

        } catch (java.awt.print.PrinterException e) {
            System.err.format("Erreur d'impression ", e.getMessage());
        }

    }//GEN-LAST:event_printbtnActionPerformed

    private void TableCategorieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCategorieMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableCategorieMouseClicked

    private void TableCategorieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCategorieMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TableCategorieMouseEntered

    private void TableCategorieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCategorieMouseReleased
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        DeplaceCategorie();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercherCategorie.setText("Taper Numero Categorie");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Categorie.setText("Taper Nom Categorie");
        btnsupprimerCategorie.setEnabled(true);
        btnmodifierCategorie.setEnabled(true);
        btnenregistrerCategorie.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_TableCategorieMouseReleased

    private void TableCategorieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableCategorieKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableCategorieKeyReleased

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
        clearArticle();
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
        try {
            String requete = "insert into  article (NomArticle, Categorie) values (?,?)";

            String combo = (String) ComboCategorie.getSelectedItem();
            String requete2 = " select * from  categorie where NomCategorie = '" + combo + "'";

            ps2 = conn.prepareStatement(requete2);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("NumCategorie");

            ps = conn.prepareStatement(requete);
            ps.setString(1, DesignationArticle.getText());
            ps.setString(2, num);
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
        AffichageArticle();
        clearArticle();
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
        try {

            String requete = "update article set NomArticle =? ,categorie =? where  NumArticle ='" + numeroArticle.getText() + "'";

            String combo = (String) ComboCategorie.getSelectedItem();
            String requete2 = " select * from  categorie where NomCategorie = '" + combo + "'";

            ps2 = conn.prepareStatement(requete2);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("NumCategorie");

            ps = conn.prepareStatement(requete);
            ps.setString(1, DesignationArticle.getText());
            ps.setString(2, num);
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
        AffichageArticle();
        clearArticle();
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
        try {
            if (JOptionPane.showConfirmDialog(null, "attention vous devez suprimer un Article, est ce que tu es sur?",
                    "Supprimer Article", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                String requete = "delete from article where NumArticle = '" + numeroArticle.getText() + "'";
                
                String requete2 = "select * from LigneEntree where NumArticle = '" + numeroArticle.getText() + "'";
                ps2 = conn.prepareStatement(requete2);
                rs2 = ps2.executeQuery();
                if(rs2.next()){
                    JOptionPane.showMessageDialog(null, "Erreur de suppression car il existe des Entrée pour cette article");
                } else{
                ps = conn.prepareStatement(requete);
                ps.execute();
                }
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "erreur de supprimer car peut Ãªtre que cette article est en cours de mouvement /n erreur de suppression" + e.getMessage());
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
        AffichageArticle();
        clearArticle();
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

    private void txtNomCategorieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomCategorieMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomCategorieMouseEntered

    private void txtNomCategorieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomCategorieMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomCategorieMouseExited

    private void txtrechercher1ArticleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1ArticleMouseClicked
        AffichageArticle();
        clearArticle();

        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbackground3.setIcon(img);
        txtrechercher1Article.setText("");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground2.setIcon(img2);
        txtrechercherArticle.setText("Taper Numero Article");

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercher1ArticleMouseClicked

    private void txtrechercher1ArticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1ArticleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1ArticleMouseEntered

    private void txtrechercher1ArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercher1ArticleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1ArticleActionPerformed

    private void txtrechercher1ArticleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1ArticleKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1ArticleKeyPressed

    private void txtrechercher1ArticleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1ArticleKeyReleased
        try {
            String requete = "select NumArticle as 'Numero' ,NomArticle as 'Nom Article' ,pu as 'Prix unitaire' ,QteStock as 'Quatité en Stock' ,categorie.NomCategorie as 'Categorie' from article, categorie WHERE\n"
                    + "(categorie.NumCategorie=article.categorie) and NomArticle LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercher1Article.getText() + "%");
            rs = ps.executeQuery();
            TableArticle.setModel(DbUtils.resultSetToTableModel(rs));
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
                };

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
    }//GEN-LAST:event_txtrechercher1ArticleKeyReleased

    private void txtrechercher1ArticleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1ArticleKeyTyped
        clearArticle();
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercher1ArticleKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        AffichageArticle();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground2.setIcon(img);
        txtrechercherArticle.setText("Taper Numero Article");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground3.setIcon(img2);
        txtrechercher1Article.setText("Taper Nom Article");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtrechercherArticleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherArticleMouseClicked
        AffichageArticle();
        clearArticle();

        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbachground2.setIcon(img);
        txtrechercherArticle.setText("");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground3.setIcon(img2);
        txtrechercher1Article.setText("Taper Nom Article");
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercherArticleMouseClicked

    private void txtrechercherArticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherArticleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherArticleMouseEntered

    private void txtrechercherArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercherArticleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherArticleActionPerformed

    private void txtrechercherArticleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherArticleKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherArticleKeyPressed

    private void txtrechercherArticleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherArticleKeyReleased
        try {
            String requete = "select NumArticle as 'Numero' ,NomArticle as 'Nom Article' ,pu as 'Prix unitaire' ,QteStock as 'Quatité en Stock' ,categorie.NomCategorie as 'Categorie' from article, categorie WHERE\n"
                    + "(categorie.NumCategorie=article.categorie) and NumArticle LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercherArticle.getText() + "%");
            rs = ps.executeQuery();
            TableArticle.setModel(DbUtils.resultSetToTableModel(rs));
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
    }//GEN-LAST:event_txtrechercherArticleKeyReleased

    private void txtrechercherArticleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherArticleKeyTyped
        clearArticle();
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercherArticleKeyTyped

    private void puArticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_puArticleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_puArticleMouseEntered

    private void puArticleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_puArticleMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_puArticleMouseExited

    private void qteStockArticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qteStockArticleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_qteStockArticleMouseEntered

    private void qteStockArticleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qteStockArticleMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_qteStockArticleMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboCategorie;
    private javax.swing.JTextField DesignationArticle;
    private javax.swing.JTable TableArticle;
    private javax.swing.JTable TableCategorie;
    private javax.swing.JButton btnenregistrerArticle;
    private javax.swing.JButton btnenregistrerCategorie;
    public javax.swing.JButton btnmodifierArticle;
    public javax.swing.JButton btnmodifierCategorie;
    private javax.swing.JButton btnnvArticle;
    private javax.swing.JButton btnnvCategorie;
    private javax.swing.JButton btnsupprimerArticle;
    private javax.swing.JButton btnsupprimerCategorie;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel numeroArticle;
    private javax.swing.JLabel numeroCategorie;
    private javax.swing.JButton printbtn;
    private javax.swing.JButton printbtn1;
    private javax.swing.JTextField puArticle;
    private javax.swing.JTextField qteStockArticle;
    private javax.swing.JTextField txtNomCategorie;
    private javax.swing.JLabel txtbachground;
    private javax.swing.JLabel txtbachground2;
    private javax.swing.JLabel txtbackground1;
    private javax.swing.JLabel txtbackground3;
    private javax.swing.JTextField txtrechercher1Article;
    private javax.swing.JTextField txtrechercher1Categorie;
    private javax.swing.JTextField txtrechercherArticle;
    private javax.swing.JTextField txtrechercherCategorie;
    // End of variables declaration//GEN-END:variables
}
