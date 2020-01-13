/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import static com.GPB.frame.Entree.test;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author anouer
 */
public class Journal extends javax.swing.JInternalFrame {

    Connection conn = null;
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
    public Journal() throws SQLException {

        initComponents();
        remove_title_bar();
        conn = ConexionBD.Conexion();
        AffichageArticle();
        AffichageJournal();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground2.setIcon(img);
        txtrechercherArticle.setText("Taper Numero Article");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground3.setIcon(img2);
        txtrechercher1Article.setText("Taper Nom Article");

    }

    private void remove_title_bar() {
        putClientProperty("Fournisseur.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }
    
    public void CloseConnexion() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connexion fermée");
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void CloseRsPs1() {
        if (rs != null) {
            try {
                rs.close();
                System.out.println("RS1 fermée");
            } catch (SQLException e) {
                /* ignored */
            }
        }
        if (ps != null) {
            try {
                ps.close();
                System.out.println("PS1 fermée");
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void CloseRsPs2() {
        if (rs2 != null) {
            try {
                rs2.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
        if (ps2 != null) {
            try {
                ps2.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void CloseRsPs3() {
        if (rs3 != null) {
            try {
                rs3.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
        if (ps3 != null) {
            try {
                ps3.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void CloseRsPs4() {
        if (rs4 != null) {
            try {
                rs4.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
        if (ps4 != null) {
            try {
                ps4.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void CloseRsPs5() {
        if (rs5 != null) {
            try {
                rs5.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
        if (ps5 != null) {
            try {
                ps5.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    private void AffichageArticle() {
        conn = ConexionBD.Conexion();
        try {
            numeroArticle.setText("");
            String requete = "select * from Article";
            ps = conn.prepareStatement(requete);
            rs = ps.executeQuery();
            TableArticle.setModel(DbUtils.resultSetToTableModel(rs));
            ajusterTableArticle();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            try {
                ps.close();
                rs.close();
                CloseConnexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }

    }
    
    private void AffichageJournal() {
        conn = ConexionBD.Conexion();
        try {
            String requete = "select Date as 'Date' ,TypeMouvement as 'Type' ,Action as 'Action' ,NumMouvement as 'Ref' ,article.NomArticle as 'Article' ,AncienQte ,NouveauQte, AncienPU, NouveauPU, AncienMontant, NouveauMontant, Journal.QteStock, Journal.MontantStock from Journal, article WHERE\n"
                    + "article.NumArticle=Journal.NumArticle";
            ps = conn.prepareStatement(requete);
            rs = ps.executeQuery();
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            try {
                ps.close();
                rs.close();
                CloseConnexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }

    }

    public void Deplace() {
        try {

            int row = TableJournal.getSelectedRow();
            Journal.test = (TableJournal.getModel().getValueAt(row, 0).toString());
            String requet = " select * from  Fournisseur where NumFournisseur = '" + test + "' ";
            ps = conn.prepareStatement(requet);
            rs = ps.executeQuery();

            if (rs.next()) {
                String t1 = rs.getString("NomFournisseur");
                String t2 = rs.getString("NumFournisseur");
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    private void AffichageJournal(String numArticle) {
        try {
            String requete = "select Date as 'Date' ,TypeMouvement as 'Type' ,Action as 'Action' ,NumMouvement as 'Ref' ,article.NomArticle as 'Article' ,AncienQte ,NouveauQte, AncienPU, NouveauPU, AncienMontant, NouveauMontant, Journal.QteStock, Journal.MontantStock from Journal, article WHERE\n"
                    + "article.NumArticle=Journal.NumArticle and Journal.NumArticle like '" + numArticle + "'";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs5));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        //ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbackgroundarticle.setIcon(img);
        //txtrechercherarticle.setText("Tapez Numero ou Nom Article");

    }
    
    private void ajusterTableArticle() {                                         
        int col = 0, droiteMax = 0, larg = 0, largTotal = 0,
                                    row = 0, tableX = 0, width = 0;
        JTableHeader header = TableArticle.getTableHeader();
        Enumeration columns = TableArticle.getColumnModel().getColumns();
 
        TableArticle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        while(columns.hasMoreElements()){                            // longueur maximum du texte ou du titre d'une colonne
            TableColumn column = (TableColumn)columns.nextElement();
            col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            width = (int)TableArticle.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(TableArticle, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for(row = 0; row<TableArticle.getRowCount(); row++){
                int preferedWidth =
                        (int)TableArticle.getCellRenderer(row, col).getTableCellRendererComponent(TableArticle,
                        TableArticle.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);                       // this line is very important
            larg = width+TableArticle.getIntercellSpacing().width;
         //   larg = (larg*13)/10;                            // largeur de la colonne plus un peu pour desserrer
            larg = larg+20;           // mais c'est mieux un ajout fixe, pas en %, 
                                         // par ex. un blanc devant et derrière chaque donnée avant de l'écrire
            largTotal += larg;                                  // largeur totale de la table si utile 
            column.setWidth(larg);
        } 
    }
    
    private void ajusterTableJournal() {                                         
        int col = 0, droiteMax = 0, larg = 0, largTotal = 0,
                                    row = 0, tableX = 0, width = 0;
        JTableHeader header = TableJournal.getTableHeader();
        Enumeration columns = TableJournal.getColumnModel().getColumns();
 
        TableJournal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        while(columns.hasMoreElements()){                            // longueur maximum du texte ou du titre d'une colonne
            TableColumn column = (TableColumn)columns.nextElement();
            col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            width = (int)TableJournal.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(TableJournal, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for(row = 0; row<TableJournal.getRowCount(); row++){
                int preferedWidth =
                        (int)TableJournal.getCellRenderer(row, col).getTableCellRendererComponent(TableJournal,
                        TableJournal.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);                       // this line is very important
            larg = width+TableJournal.getIntercellSpacing().width;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TableJournal = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableArticle = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        txtrechercherArticle = new javax.swing.JTextField();
        txtbachground2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtrechercher1Article = new javax.swing.JTextField();
        txtbackground3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numeroArticle = new javax.swing.JLabel();
        date1 = new com.toedter.calendar.JDateChooser();
        date2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBorder(null);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Journal des Mouvements :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TableJournal.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        TableJournal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableJournal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableJournal.setRowHeight(20);
        TableJournal.setSelectionBackground(new java.awt.Color(0, 153, 153));
        TableJournal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableJournalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableJournalMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableJournalMouseReleased(evt);
            }
        });
        TableJournal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableJournalKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TableJournal);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 350, 1200, 290);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setBackground(new java.awt.Color(0, 102, 255));
        jLabel10.setFont(new java.awt.Font("Advent Pro", 0, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("HISTORIQUE DES MOUVEMENTS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 0, 1200, 50);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Listes des Articles :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

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
        jScrollPane2.setViewportView(TableArticle);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(0, 120, 380, 220);

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
        txtrechercherArticle.setBounds(20, 80, 200, 10);

        txtbachground2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        txtbachground2.setAlignmentY(0.0F);
        getContentPane().add(txtbachground2);
        txtbachground2.setBounds(10, 70, 220, 30);

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
        jButton3.setBounds(240, 60, 70, 40);

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
        txtrechercher1Article.setBounds(330, 80, 200, 10);

        txtbackground3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        txtbackground3.setAlignmentY(0.0F);
        getContentPane().add(txtbackground3);
        txtbackground3.setBounds(320, 70, 220, 30);

        jLabel2.setText("Numero : ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(580, 80, 60, 14);

        numeroArticle.setText("                  ");
        getContentPane().add(numeroArticle);
        numeroArticle.setBounds(640, 80, 70, 14);

        date1.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(date1);
        date1.setBounds(710, 80, 180, 20);

        date2.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(date2);
        date2.setBounds(920, 80, 180, 20);

        jLabel1.setText("Recherche entre :");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(710, 60, 180, 14);

        jLabel3.setText("et");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(900, 80, 20, 14);

        jButton1.setText("Rechercher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1110, 80, 90, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableJournalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableJournalMouseClicked

    }//GEN-LAST:event_TableJournalMouseClicked

    private void TableJournalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableJournalMouseEntered

    }//GEN-LAST:event_TableJournalMouseEntered

    private void TableJournalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableJournalMouseReleased

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        Deplace();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground2.setIcon(img);
        txtrechercherArticle.setText("Taper Numero Fournisseur");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground3.setIcon(img2);
        txtrechercher1Article.setText("Taper Nom Fournisseur");
    }//GEN-LAST:event_TableJournalMouseReleased

    private void TableJournalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableJournalKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
            Deplace();
        }
    }//GEN-LAST:event_TableJournalKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        clear();
    }//GEN-LAST:event_formMouseClicked

    private void TableArticleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleMouseClicked

    private void TableArticleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleMouseEntered

    private void TableArticleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArticleMouseReleased
        conn = ConexionBD.Conexion();
        int row = TableArticle.getSelectedRow();
        Journal.test = (TableArticle.getModel().getValueAt(row, 0).toString());
        numeroArticle.setText(test);
        AffichageJournal(test);
        CloseRsPs1();
        CloseRsPs2();
        CloseConnexion();
    }//GEN-LAST:event_TableArticleMouseReleased

    private void TableArticleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableArticleKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleKeyReleased

    private void txtrechercherArticleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherArticleMouseClicked
        AffichageArticle();
        numeroArticle.setText("");

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
        conn = ConexionBD.Conexion();
        try {
            String requete = "select NumArticle as 'Numero' ,NomArticle as 'Nom Article' ,pu as 'Prix unitaire' ,QteStock as 'Quatité en Stock' ,categorie.NomCategorie as 'Categorie' from article, categorie WHERE\n"
            + "(categorie.NumCategorie=article.categorie) and NumArticle LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercherArticle.getText() + "%");
            rs = ps.executeQuery();
            TableArticle.setModel(DbUtils.resultSetToTableModel(rs));
            ajusterTableArticle();
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
                CloseConnexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
    }//GEN-LAST:event_txtrechercherArticleKeyReleased

    private void txtrechercherArticleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherArticleKeyTyped
        numeroArticle.setText("");
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercherArticleKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        AffichageArticle();
        AffichageJournal();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground2.setIcon(img);
        txtrechercherArticle.setText("Taper Numero Article");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground3.setIcon(img2);
        txtrechercher1Article.setText("Taper Nom Article");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtrechercher1ArticleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1ArticleMouseClicked
        AffichageArticle();
        numeroArticle.setText("");

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
        conn = ConexionBD.Conexion();
        try {
            String requete = "select NumArticle as 'Numero' ,NomArticle as 'Nom Article' ,pu as 'Prix unitaire' ,QteStock as 'Quatité en Stock' ,categorie.NomCategorie as 'Categorie' from article, categorie WHERE\n"
            + "(categorie.NumCategorie=article.categorie) and NomArticle LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercher1Article.getText() + "%");
            rs = ps.executeQuery();
            TableArticle.setModel(DbUtils.resultSetToTableModel(rs));
            ajusterTableArticle();
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
                CloseConnexion();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
    }//GEN-LAST:event_txtrechercher1ArticleKeyReleased

    private void txtrechercher1ArticleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1ArticleKeyTyped
        numeroArticle.setText("");
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercher1ArticleKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableArticle;
    private javax.swing.JTable TableJournal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel numeroArticle;
    private javax.swing.JLabel txtbachground2;
    private javax.swing.JLabel txtbackground3;
    private javax.swing.JTextField txtrechercher1Article;
    private javax.swing.JTextField txtrechercherArticle;
    // End of variables declaration//GEN-END:variables
}
