/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
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
    ResultSet rs8 = null;
    ResultSet rs9 = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    PreparedStatement ps3 = null;
    PreparedStatement ps4 = null;
    PreparedStatement ps5 = null;
    PreparedStatement ps6 = null;
    PreparedStatement ps7 = null;
    PreparedStatement ps8 = null;
    PreparedStatement ps9 = null;
    static String test;
    static String VarDateEntree;

    /**
     * Creates new form Examen
     * @throws java.sql.SQLException
     */
    public Entree() throws SQLException {
        
        initComponents();
        remove_title_bar();
        
        if(LoginGUI.role.equals("USER")){
            btnsupprimerEntree.setVisible(false);
            btnmodifierEntree.setVisible(false);
        }
        
        clearEntree();
        
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
        //btnmodifierLE.setEnabled(false);
        btnenregistrerLE.setEnabled(false);
        btnnvEntree.setEnabled(true);
        btnsupprimerEntree.setEnabled(false);
        btnmodifierEntree.setEnabled(false);
        btnenregistrerEntree.setEnabled(false);
    }

    private void remove_title_bar() {
        putClientProperty("Entree.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    private void masquerLigneEntree() {
        JPanelLigneEntree.setVisible(false);
        impression.setVisible(false);
    }

    private void afficherLigneEntree() {
        JPanelLigneEntree.setVisible(true);
        impression.setVisible(true);
    }

    private void masquerArticle() {
        jButtonRefreshArticle.setVisible(false);
        txtrechercherarticle.setVisible(false);
        txtbackgroundarticle.setVisible(false);
        jScrollPane4.setVisible(false);
        TableArticleEntree.setVisible(false);
        impression.setVisible(false);
    }

    private void afficherArticle() {
        jButtonRefreshArticle.setVisible(true);
        txtrechercherarticle.setVisible(true);
        txtbackgroundarticle.setVisible(true);
        jScrollPane4.setVisible(true);
        TableArticleEntree.setVisible(true);
        impression.setVisible(true);
    }
    
    public void tabelArticle() {
        
        TableArticleEntree.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TableArticleEntree.getTableHeader().setOpaque(false);
        TableArticleEntree.getTableHeader().setBackground(new Color(3, 91, 155));
        TableArticleEntree.getTableHeader().setForeground(new Color(255,255,255));
        //TableArticle.setRowHeight(25);
    }
    
    public void tabelEntree() {
        
        TableEntree.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TableEntree.getTableHeader().setOpaque(false);
        TableEntree.getTableHeader().setBackground(new Color(3, 91, 155));
        TableEntree.getTableHeader().setForeground(new Color(255,255,255));
        //TableArticle.setRowHeight(25);
    }
    
    public void tabelLE() {
        
        TableLigneEntree.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TableLigneEntree.getTableHeader().setOpaque(false);
        TableLigneEntree.getTableHeader().setBackground(new Color(3, 91, 155));
        TableLigneEntree.getTableHeader().setForeground(new Color(255,255,255));
        //TableArticle.setRowHeight(25);
    }

    private void AffichageArticle() {
        try {
            String requete = "select NomArticle as 'Nom Article' ,pu as 'Prix unitaire' ,QteStock as 'Quatité en Stock' ,categorie.NomCategorie as 'Categorie' ,MontantStock as 'Montant en Stock' from article, categorie WHERE\n"
                    + "(categorie.NumCategorie=article.categorie)";
            String requete2 = "select * from article";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
 TableArticleEntree.setModel(DbUtils.resultSetToTableModel(rs5));
 ajusterTableArticleEntree();
 tabelArticle();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
                CloseRsPs5();
        }
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackgroundarticle.setIcon(img);
        txtrechercherarticle.setText("Tapez Nom Article");

    }

    private void AffichageEntree() {
        try {
            String requete = "select RefEntree as 'Reference' ,Fournisseur.NomFournisseur as 'Fournisseur' ,DateEntree as 'Date' ,MontantTotalEntree as 'MontantTotal' ,ObservationEntree as 'Observation' from Entree, Fournisseur WHERE\n"
                    + "(Fournisseur.NumFournisseur=Entree.NumFournisseur)";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            TableEntree.setModel(DbUtils.resultSetToTableModel(rs5));
            ajusterTableEntree();
            tabelEntree();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        //ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbackgroundarticle.setIcon(img);
        //txtrechercherarticle.setText("Tapez Nom Article");

    }

    private void AffichageLigneEntree(String numEntree) {
        try {
            String requete = "select LigneEntree.NumLigneEntree as 'N°' ,article.NomArticle as 'Article' ,NbrEntree as 'Nombre' ,puFournisseur as 'Prix Unitaire Fournisseur' ,MontantEntree as 'Montant' from LigneEntree, article WHERE\n"
                    + "article.NumArticle=LigneEntree.NumArticle and LigneEntree.NumEntree like '" + numEntree + "'";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            TableLigneEntree.setModel(DbUtils.resultSetToTableModel(rs5));
            ajusterTableLigneEntree();
            tabelLE();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        //ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbackgroundarticle.setIcon(img);
        //txtrechercherarticle.setText("Tapez Numero ou Nom Article");

    }
    
    private void ajusterTableArticleEntree() {                                         
        int col = 0, droiteMax = 0, larg = 0, largTotal = 0,
                                    row = 0, tableX = 0, width = 0;
        JTableHeader header = TableArticleEntree.getTableHeader();
        Enumeration columns = TableArticleEntree.getColumnModel().getColumns();
 
        TableArticleEntree.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        while(columns.hasMoreElements()){                            // longueur maximum du texte ou du titre d'une colonne
            TableColumn column = (TableColumn)columns.nextElement();
            col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            width = (int)TableArticleEntree.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(TableArticleEntree, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for(row = 0; row<TableArticleEntree.getRowCount(); row++){
                int preferedWidth =
                        (int)TableArticleEntree.getCellRenderer(row, col).getTableCellRendererComponent(TableArticleEntree,
                        TableArticleEntree.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);                       // this line is very important
            larg = width+TableArticleEntree.getIntercellSpacing().width;
         //   larg = (larg*13)/10;                            // largeur de la colonne plus un peu pour desserrer
            larg = larg+20;           // mais c'est mieux un ajout fixe, pas en %, 
                                         // par ex. un blanc devant et derrière chaque donnée avant de l'écrire
            largTotal += larg;                                  // largeur totale de la table si utile 
            column.setWidth(larg);
        } 
    }
    
    private void ajusterTableEntree() {                                         
        int col = 0, droiteMax = 0, larg = 0, largTotal = 0,
                                    row = 0, tableX = 0, width = 0;
        JTableHeader header = TableEntree.getTableHeader();
        Enumeration columns = TableEntree.getColumnModel().getColumns();
 
        TableEntree.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        while(columns.hasMoreElements()){                            // longueur maximum du texte ou du titre d'une colonne
            TableColumn column = (TableColumn)columns.nextElement();
            col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            width = (int)TableEntree.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(TableEntree, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for(row = 0; row<TableEntree.getRowCount(); row++){
                int preferedWidth =
                        (int)TableEntree.getCellRenderer(row, col).getTableCellRendererComponent(TableEntree,
                        TableEntree.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);                       // this line is very important
            larg = width+TableEntree.getIntercellSpacing().width;
         //   larg = (larg*13)/10;                            // largeur de la colonne plus un peu pour desserrer
            larg = larg+20;           // mais c'est mieux un ajout fixe, pas en %, 
                                         // par ex. un blanc devant et derrière chaque donnée avant de l'écrire
            largTotal += larg;                                  // largeur totale de la table si utile 
            column.setWidth(larg);
        } 
    }
    
    private void ajusterTableLigneEntree() {                                         
        int col = 0, droiteMax = 0, larg = 0, largTotal = 0,
                                    row = 0, tableX = 0, width = 0;
        JTableHeader header = TableLigneEntree.getTableHeader();
        Enumeration columns = TableLigneEntree.getColumnModel().getColumns();
 
        TableLigneEntree.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        while(columns.hasMoreElements()){                            // longueur maximum du texte ou du titre d'une colonne
            TableColumn column = (TableColumn)columns.nextElement();
            col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            width = (int)TableLigneEntree.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(TableLigneEntree, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for(row = 0; row<TableLigneEntree.getRowCount(); row++){
                int preferedWidth =
                        (int)TableLigneEntree.getCellRenderer(row, col).getTableCellRendererComponent(TableLigneEntree,
                        TableLigneEntree.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);                       // this line is very important
            larg = width+TableLigneEntree.getIntercellSpacing().width;
         //   larg = (larg*13)/10;                            // largeur de la colonne plus un peu pour desserrer
            larg = larg+20;           // mais c'est mieux un ajout fixe, pas en %, 
                                         // par ex. un blanc devant et derrière chaque donnée avant de l'écrire
            largTotal += larg;                                  // largeur totale de la table si utile 
            column.setWidth(larg);
        } 
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
    
    public void CloseRsPs8() {
        if (rs8 != null) {
            try {
                rs8.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (ps8 != null) {
            try {
                ps8.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
    
    public void CloseRsPs9() {
        if (rs9 != null) {
            try {
                rs9.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (ps9 != null) {
            try {
                ps9.close();
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
            String requet = " select * from article where NomArticle = '" + test + "'";
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

    public void DeplaceEntree() throws ParseException {
        conn = ConexionBD.Conexion();
        try {

            int row = TableEntree.getSelectedRow();
            Entree.test = (TableEntree.getModel().getValueAt(row, 0).toString());
            String requet = " select * from Entree where RefEntree = '" + test + "'";
            ps = conn.prepareStatement(requet);
            rs = ps.executeQuery();

                String t1 = rs.getString("NumEntree");
                String t2 = rs.getString("RefEntree");
                String t3 = rs.getString("NumFournisseur");
                String t4 = rs.getString("DateEntree");
                
                VarDateEntree = t4;
                String t5 = rs.getString("MontantTotalEntree");
                String t6 = rs.getString("ObservationEntree");
                String fournisseur = " select * from Fournisseur where NumFournisseur = '" + t3 + "'";
                ps2 = conn.prepareStatement(fournisseur);
                rs2 = ps2.executeQuery();
                numeroEntree.setText(t1);
                //DesignationArticle.setText(t2);
                ref.setText(t2);
                
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date2 = dateFormat.parse(t4);

                date.setDate(date2);
                
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
        ComboFournisseur.removeAllItems();
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
            
            Long millis = System.currentTimeMillis();
            Date date3 = new Date(millis);
            
            //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            date.setDate(date3);
            MontantTotal.setText("0");
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ComboFournisseur = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnnvEntree = new javax.swing.JButton();
        btnenregistrerEntree = new javax.swing.JButton();
        btnmodifierEntree = new javax.swing.JButton();
        btnsupprimerEntree = new javax.swing.JButton();
        MontantTotal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        obs = new javax.swing.JTextArea();
        date = new com.toedter.calendar.JDateChooser();
        impression = new javax.swing.JPanel();
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
        btnsupprimerLE = new javax.swing.JButton();
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

        jLabel6.setText("Date : ");

        jLabel7.setText("Fournisseur :");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Action :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 3, 12))); // NOI18N

        btnnvEntree.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnnvEntree.setText("Nouveau");
        btnnvEntree.setToolTipText("");
        btnnvEntree.setAutoscrolls(true);
        btnnvEntree.setBorder(null);
        btnnvEntree.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnnvEntree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnnvEntreeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnnvEntreeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnnvEntreeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnnvEntreeMouseReleased(evt);
            }
        });
        btnnvEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnvEntreeActionPerformed(evt);
            }
        });

        btnenregistrerEntree.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnenregistrerEntree.setText("Enregistrer");
        btnenregistrerEntree.setBorder(null);
        btnenregistrerEntree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnenregistrerEntreeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnenregistrerEntreeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnenregistrerEntreeMousePressed(evt);
            }
        });
        btnenregistrerEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenregistrerEntreeActionPerformed(evt);
            }
        });

        btnmodifierEntree.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnmodifierEntree.setText("Modifier");
        btnmodifierEntree.setBorder(null);
        btnmodifierEntree.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnmodifierEntree.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnmodifierEntreeMouseMoved(evt);
            }
        });
        btnmodifierEntree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnmodifierEntreeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmodifierEntreeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnmodifierEntreeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnmodifierEntreeMouseReleased(evt);
            }
        });
        btnmodifierEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifierEntreeActionPerformed(evt);
            }
        });

        btnsupprimerEntree.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnsupprimerEntree.setText("Supprimer");
        btnsupprimerEntree.setBorder(null);
        btnsupprimerEntree.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnsupprimerEntree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsupprimerEntreeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsupprimerEntreeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnsupprimerEntreeMousePressed(evt);
            }
        });
        btnsupprimerEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupprimerEntreeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerEntree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnvEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnenregistrerEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmodifierEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnvEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenregistrerEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsupprimerEntree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodifierEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        MontantTotal.setText("0");

        jLabel9.setText("Observation : ");

        obs.setColumns(20);
        obs.setRows(5);
        jScrollPane3.setViewportView(obs);

        date.setDateFormatString("dd-MM-yyyy");

        impression.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Impréssion :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        printbtn1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        printbtn1.setText("Imprimer");
        printbtn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        printbtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        javax.swing.GroupLayout impressionLayout = new javax.swing.GroupLayout(impression);
        impression.setLayout(impressionLayout);
        impressionLayout.setHorizontalGroup(
            impressionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, impressionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(printbtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
        );
        impressionLayout.setVerticalGroup(
            impressionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(ComboFournisseur, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(MontantTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38)
                        .addComponent(numeroEntree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(impression, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MontantTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(impression, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 120, 440, 550);

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
        TableEntree.setSelectionBackground(new java.awt.Color(3, 91, 155));
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
        jScrollPane1.setBounds(440, 110, 490, 240);

        jPanel5.setBackground(new java.awt.Color(3, 91, 155));

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
        TableLigneEntree.setSelectionBackground(new java.awt.Color(3, 91, 155));
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
        btnnvLE.setText("Nouveau");
        btnnvLE.setToolTipText("");
        btnnvLE.setAutoscrolls(true);
        btnnvLE.setBorder(null);
        btnnvLE.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        btnenregistrerLE.setText("Ajouter");
        btnenregistrerLE.setBorder(null);
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

        btnsupprimerLE.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnsupprimerLE.setText("Supprimer");
        btnsupprimerLE.setBorder(null);
        btnsupprimerLE.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
                .addComponent(btnenregistrerLE, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelActionLigneEntreeLayout.setVerticalGroup(
            jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelActionLigneEntreeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelActionLigneEntreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnvLE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenregistrerLE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsupprimerLE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jPanelFormLigneEntree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelActionLigneEntree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPaneLigneEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        getContentPane().add(JPanelLigneEntree);
        JPanelLigneEntree.setBounds(440, 360, 720, 320);

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
        txtrechercher1Entree.setForeground(new java.awt.Color(3, 91, 155));
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
        txtrechercher1Entree.setBounds(100, 80, 200, 10);

        txtbackground1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        txtbackground1.setAlignmentY(0.0F);
        getContentPane().add(txtbackground1);
        txtbackground1.setBounds(90, 70, 220, 30);

        txtrechercherarticle.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercherarticle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercherarticle.setForeground(new java.awt.Color(3, 91, 155));
        txtrechercherarticle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercherarticle.setAlignmentX(0.0F);
        txtrechercherarticle.setAlignmentY(0.0F);
        txtrechercherarticle.setBorder(null);
        txtrechercherarticle.setSelectionColor(new java.awt.Color(3, 91, 155));
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
        TableArticleEntree.setSelectionBackground(new java.awt.Color(3, 91, 155));
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
        jScrollPane4.setBounds(940, 140, 220, 210);

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
        jButton2.setBounds(10, 60, 70, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableEntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEntreeMouseClicked

    }//GEN-LAST:event_TableEntreeMouseClicked

    private void TableEntreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEntreeMouseEntered

    }//GEN-LAST:event_TableEntreeMouseEntered

    private void TableEntreeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEntreeMouseReleased

        
        
        afficherLigneEntree();
        if(LoginGUI.role.equals("USER")){
            btnsupprimerLE.setVisible(false);
        }
        afficherArticle();
        AffichageArticle();
        clearLE();
        try {
            DeplaceEntree();
        } catch (ParseException ex) {
            Logger.getLogger(Entree.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("Tapez Reference Entree");
        txtbackground1.setIcon(img2);
        
        btnnvEntree.setEnabled(true);
        btnsupprimerEntree.setEnabled(true);
        btnmodifierEntree.setEnabled(true);
        btnenregistrerEntree.setEnabled(false);
        
        btnnvLE.setEnabled(true);
        btnsupprimerLE.setEnabled(false);
        btnenregistrerLE.setEnabled(false);
        
        date.setEnabled(false);

    }//GEN-LAST:event_TableEntreeMouseReleased

    private void SommeMontant() throws SQLException {
        String requete = "select SUM(MontantEntree) as somme from LigneEntree where NumEntree = '" + numeroEntree.getText() + "'";
        ps5 = conn.prepareStatement(requete);
        rs5 = ps5.executeQuery();
        if(rs5.next()){
            MontantTotal.setText(rs5.getString("somme"));
        }else{
            MontantTotal.setText("0");
        }
        
        CloseRsPs5();
    }
    
    public double arrondir(double nombre,double nbApVirg)
    {
        return(double)((int)(nombre * Math.pow(10,nbApVirg) + .5)) / Math.pow(10,nbApVirg);
    }

    private void btnenregistrerLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenregistrerLEActionPerformed
        conn = ConexionBD.Conexion();
        try {
            if(article.getText().equals("")||nbr.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Completez l'information"); 
            }else{
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
            
            String requete7 = "select * from LigneEntree where  NumEntree ='" + numeroEntree.getText() + "' and NumArticle ='" + num + "'";
            ps7 = conn.prepareStatement(requete7);
            rs7 = ps7.executeQuery();
            
            if(rs7.next()){
                JOptionPane.showMessageDialog(null, "Cet article est deja utilisé dans une ligne, veuillez modifier cette ligne");
            }else{
                ps.setString(1, numeroEntree.getText());
                ps.setString(2, num);
                ps.setString(3, nbr.getText());
                ps.setString(4, puFournisseur.getText());
                double mtn = Double.parseDouble(nbr.getText()) * Double.parseDouble(puFournisseur.getText());
                ps.setString(5, String.valueOf(mtn));
                ps.execute();

                String requete3 = "update article set QteStock =? ,pu =? ,MontantStock =? where  NumArticle ='" + num + "'";
                ps3 = conn.prepareStatement(requete3);

                double qte = Double.parseDouble(nbr.getText()) + Double.parseDouble(ancienQte);
                ps3.setString(1, String.valueOf(qte));
                double Mtn = mtn + Double.parseDouble(ancienMtn);
                
                if (qte >= 0) {
                        double NewPU = Mtn / qte;
                        ps3.setString(2, String.valueOf(NewPU));
                    } else {
                        ps3.setString(2, "0");
                    }
                
                if (Mtn > 0) {
                        ps3.setString(3, String.valueOf(Mtn));
                    } else {
                        ps3.setString(3, "0");
                }
                ps3.execute();

                JOptionPane.showMessageDialog(null, "Enregistrement succes");

                AffichageLigneEntree(numeroEntree.getText());
                try {
                    SommeMontant();
                } catch (SQLException ex) {
                    Logger.getLogger(Entree.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String requete5 = "insert into Journal (DateJournal,DateMouvement,TypeMouvement,Action,NumMouvement,Fournisseur,NumArticle,QteMouvement,PUMouvement,MontantMouvement,QteStock,MontantStock) values (?,?,?,?,?,?,?,?,?,?,?,?)";
                ps5 = conn.prepareStatement(requete5);
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Long millis = System.currentTimeMillis();
                Date date3 = new Date(millis);
                ps5.setString(1, dateFormat.format(date3));
                ps5.setString(2, dateFormat.format(date.getDate()));
                ps5.setString(3, "ENTREE");
                ps5.setString(4, "AJOUT");
                ps5.setString(5, ref.getText());
                
                String fournisseur = null;
                try {
                String requete6 = "select * from Fournisseur where NomFournisseur LIKE ?";
                ps6 = conn.prepareStatement(requete6);
                ps6.setString(1, "%" + ComboFournisseur.getSelectedItem().toString() + "%");
                rs6 = ps6.executeQuery();
                fournisseur = rs6.getString("NumFournisseur");
                } catch (SQLException e) {
                    System.out.println(e);
                } finally {
                    try {
                        ps6.close();
                        rs6.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "erreur BD");
                    }
                }
                
                ps5.setString(6, fournisseur);
                ps5.setString(7, num);
                ps5.setString(8, nbr.getText());
                ps5.setString(9, puFournisseur.getText());
                ps5.setString(10, String.valueOf(mtn));
                ps5.setString(11, String.valueOf(qte));
                ps5.setString(12, String.valueOf(Mtn));
                ps5.execute();
                clearLE();
            }
        }
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
        
        RectifierMontantTotalEntree();
        
        btnnvEntree.setEnabled(true);
        btnsupprimerEntree.setEnabled(true);
        btnmodifierEntree.setEnabled(true);
        btnenregistrerEntree.setEnabled(false);
        
        btnnvLE.setEnabled(true);
        btnsupprimerLE.setEnabled(false);
//        btnmodifierLE.setEnabled(false);
        btnenregistrerLE.setEnabled(false);
        
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
//        btnmodifierLE.setEnabled(false);
        clearLE();
        conn = ConexionBD.Conexion(); 
        AffichageLigneEntree(numeroEntree.getText());
        CloseConnexion();
    }//GEN-LAST:event_btnnvLEMouseReleased

    private void btnenregistrerLEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerLEMouseExited
        btnenregistrerLE.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnenregistrerLEMouseExited

    private void btnenregistrerLEMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerLEMousePressed
        btnenregistrerLE.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnenregistrerLEMousePressed

    private void btnenregistrerLEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerLEMouseEntered
        
    }//GEN-LAST:event_btnenregistrerLEMouseEntered

    private void btnsupprimerLEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerLEMouseEntered
        
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
                String PUEntree = rs5.getString("puFournisseur");
                String MontantEntree = rs5.getString("MontantEntree");

                String requete7 = "select * from  article where NumArticle = '" + NumArticle + "'";
                ps7 = conn.prepareStatement(requete7);
                rs7 = ps7.executeQuery();
                String AncienQte = rs7.getString("QteStock");
                String AncienMtn = rs7.getString("MontantStock");
                String NomArticle = rs7.getString("NomArticle");

                
                double qte = Double.parseDouble(AncienQte) - Double.parseDouble(NbrEntree);
                double MtnRet = Double.parseDouble(AncienMtn) - Double.parseDouble(MontantEntree);
                
                if (qte < 0 || MtnRet < 0) {
                    JOptionPane.showMessageDialog(null, "Erreur de suppression de: "+ NomArticle +", car le stock est insuffisant!");
                } else {
                    String requete6 = "update article set QteStock =? ,pu =? ,MontantStock =? where  NumArticle ='" + NumArticle + "'";
                    ps6 = conn.prepareStatement(requete6);

                    ps6.setString(1, String.valueOf(qte));
                    if (qte > 0) {
                        double pu = MtnRet / qte;
                        ps6.setString(2, String.valueOf(pu));
                    } else {
                        ps6.setString(2, "0");
                    }
                    ps6.setString(3, String.valueOf(MtnRet));
                    ps6.execute();

                    String requete = "delete from LigneEntree where NumLigneEntree = '" + numeroLigneEntree.getText() + "'";
                    ps = conn.prepareStatement(requete);

                    ps.execute();
                    
                    String requete10 = "insert into Journal (DateJournal,DateMouvement,TypeMouvement,Action,NumMouvement,Fournisseur,NumArticle,QteMouvement,PUMouvement,MontantMouvement,QteStock,MontantStock) values (?,?,?,?,?,?,?,?,?,?,?,?)";
                    ps9 = conn.prepareStatement(requete10);

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Long millis = System.currentTimeMillis();
                    Date date3 = new Date(millis);
                    ps9.setString(1, dateFormat.format(date3));
                    ps9.setString(2, dateFormat.format(date.getDate()));
                    ps9.setString(3, "ENTREE");
                    ps9.setString(4, "SUPPR");
                    ps9.setString(5, ref.getText());
                    

                    String fournisseur = null;
                    try {
                    String requete15 = "select * from Fournisseur where NomFournisseur LIKE ?";
                    ps6 = conn.prepareStatement(requete15);
                    ps6.setString(1, "%" + ComboFournisseur.getSelectedItem().toString() + "%");
                    rs6 = ps6.executeQuery();
                    fournisseur = rs6.getString("NumFournisseur");
                    } catch (SQLException e) {
                        System.out.println(e);
                    } finally {
                        try {
                            ps6.close();
                            rs6.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "erreur BD");
                        }
                    }

                    
                    ps9.setString(6, fournisseur);
                    ps9.setString(7, NumArticle);
                    ps9.setString(8, NbrEntree);
                    ps9.setString(9, PUEntree);
                    ps9.setString(10, MontantEntree);
                    ps9.setString(11, String.valueOf(qte));
                    ps9.setString(12, String.valueOf(MtnRet));
                    ps9.execute();
                    CloseRsPs9();
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
            CloseRsPs9();
        }
        
        AffichageLigneEntree(numeroEntree.getText());
        clearLE();
        btnnvLE.setEnabled(true);
        btnsupprimerLE.setEnabled(false);
//        btnmodifierLE.setEnabled(false);
        btnenregistrerLE.setEnabled(false);
        try {
            SommeMontant();
        } catch (SQLException ex) {
            Logger.getLogger(Entree.class.getName()).log(Level.SEVERE, null, ex);
        }
        CloseConnexion();
        RectifierMontantTotalEntree();
    }//GEN-LAST:event_btnsupprimerLEActionPerformed

    private void TableLigneEntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableLigneEntreeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableLigneEntreeMouseClicked

    private void TableLigneEntreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableLigneEntreeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TableLigneEntreeMouseEntered

    private void TableLigneEntreeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableLigneEntreeMouseReleased
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        DeplaceLigneEntree();
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("Taper Nom Entree");
        
        btnnvEntree.setEnabled(true);
        btnsupprimerEntree.setEnabled(true);
        btnmodifierEntree.setEnabled(true);
        btnenregistrerEntree.setEnabled(false);
        
        btnnvLE.setEnabled(true);
        btnsupprimerLE.setEnabled(true);
//        btnmodifierLE.setEnabled(true);
        btnenregistrerLE.setEnabled(false);
    }//GEN-LAST:event_TableLigneEntreeMouseReleased

    private void TableLigneEntreeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableLigneEntreeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableLigneEntreeKeyReleased

    private void btnnvEntreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvEntreeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvEntreeMouseEntered

    private void btnnvEntreeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvEntreeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvEntreeMouseExited

    private void btnnvEntreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvEntreeMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvEntreeMousePressed

    private void btnnvEntreeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnvEntreeMouseReleased
        btnenregistrerEntree.setEnabled(true);
        btnsupprimerEntree.setEnabled(false);
        btnmodifierEntree.setEnabled(false);
        clearEntree();
        masquerArticle();
        masquerLigneEntree();
        conn = ConexionBD.Conexion(); 
        AffichageEntree();
        CloseConnexion();
    }//GEN-LAST:event_btnnvEntreeMouseReleased

    private void btnnvEntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnvEntreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnvEntreeActionPerformed

    private void btnenregistrerEntreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerEntreeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnenregistrerEntreeMouseEntered

    private void btnenregistrerEntreeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerEntreeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnenregistrerEntreeMouseExited

    private void btnenregistrerEntreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenregistrerEntreeMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnenregistrerEntreeMousePressed

    private void btnenregistrerEntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenregistrerEntreeActionPerformed
        conn = ConexionBD.Conexion();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        try {
            
            if(ref.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Completez l'information"); 
            }else if(ref.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Completez l'information"); 
            }else{
                
            String requete = "insert into Entree (refEntree,NumFournisseur,DateEntree,MontantTotalEntree,ObservationEntree) values (?,?,?,?,?)";
            ps = conn.prepareStatement(requete);

            String requete2 = "select * from  Fournisseur where NomFournisseur = '" + ComboFournisseur.getSelectedItem() + "'";

            ps2 = conn.prepareStatement(requete2);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("NumFournisseur");
            ps.setString(1, ref.getText());
            ps.setString(2, num);
            
            String dateEntree = dateFormat.format(date.getDate());
            
            ps.setString(3, dateEntree);
            ps.setString(4, MontantTotal.getText());
            ps.setString(5, obs.getText());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Enregistrement succes");}
        } catch (HeadlessException | SQLException e) {
            System.out.println("--> SQLException : " + e);
            JOptionPane.showMessageDialog(null, "Erreur formulaire ou Identifiant doublé");
        } finally {
            CloseRsPs1();
            CloseRsPs2();
        }
        AffichageEntree();
        clearEntree();
        CloseConnexion();
    }//GEN-LAST:event_btnenregistrerEntreeActionPerformed

    private void btnmodifierEntreeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierEntreeMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierEntreeMouseMoved

    private void btnmodifierEntreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierEntreeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierEntreeMouseEntered

    private void btnmodifierEntreeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierEntreeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierEntreeMouseExited

    private void btnmodifierEntreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierEntreeMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierEntreeMousePressed

    private void btnmodifierEntreeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodifierEntreeMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodifierEntreeMouseReleased

    private void btnmodifierEntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifierEntreeActionPerformed
        conn = ConexionBD.Conexion();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            if(ref.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Completez l'information"); 
            }else{
                
                String requete5 = "select * from Entree where  NumEntree ='" + numeroEntree.getText() + "'";
            ps2 = conn.prepareStatement(requete5);
            rs2 = ps2.executeQuery();
            String reference = rs2.getString("refEntree");
                
            String requete = "update Entree set refEntree =?,NumFournisseur=?,DateEntree=?,MontantTotalEntree=?,ObservationEntree=? where  NumEntree ='" + numeroEntree.getText() + "'";
            ps = conn.prepareStatement(requete);

            String requete2 = "select * from  Fournisseur where NomFournisseur = '" + ComboFournisseur.getSelectedItem() + "'";

            ps3 = conn.prepareStatement(requete2);
            rs3 = ps3.executeQuery();
            String num = rs3.getString("NumFournisseur");
            ps.setString(1, ref.getText());
            ps.setString(2, num);
            String dateEntree = dateFormat1.format(date.getDate());
            
            ps.setString(3, dateEntree);
            ps.setString(4, MontantTotal.getText());
            ps.setString(5, obs.getText());
            ps.execute();
            
            String requete6 = "select * from Journal where  NumMouvement ='" + reference + "'";
            ps6 = conn.prepareStatement(requete6);
            rs6 = ps6.executeQuery();
            String action = rs6.getString("Action");
            
            
            String requete3 = "update Journal set DateMouvement =?,NumMouvement=?,Action=?,Fournisseur=? where  NumMouvement ='" + reference + "'";
            ps4 = conn.prepareStatement(requete3);
            ps4.setString(1, dateFormat.format(date.getDate()));
            ps4.setString(2, ref.getText());
            ps4.setString(3, action+"/MODIF");
            ps4.setString(4, num);
            ps4.execute();
            
            
            
            JOptionPane.showMessageDialog(null, "Modification succes");}
            
            btnnvEntree.setEnabled(true);
            btnsupprimerEntree.setEnabled(false);
            btnmodifierEntree.setEnabled(false);
            btnenregistrerEntree.setEnabled(false);
            clearEntree();
        } catch (HeadlessException | SQLException e) {
            System.out.println("--> SQLException : " + e);
            JOptionPane.showMessageDialog(null, "Erreur formulaire ou Identifiant doublé");
        } finally {
            CloseRsPs1();
            CloseRsPs2();
        }
        AffichageEntree();
        clearEntree();
        masquerArticle();
        masquerLigneEntree();
        CloseConnexion();
    }//GEN-LAST:event_btnmodifierEntreeActionPerformed

    private void btnsupprimerEntreeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerEntreeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsupprimerEntreeMouseEntered

    private void btnsupprimerEntreeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerEntreeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsupprimerEntreeMouseExited

    private void btnsupprimerEntreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerEntreeMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsupprimerEntreeMousePressed

    private void btnsupprimerEntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerEntreeActionPerformed
        conn = ConexionBD.Conexion();
        try {
            if (JOptionPane.showConfirmDialog(null, "attention vous devez suprimer une Entree, Toute les Lignes Entree de ce Entree seront tous supprimées ,est ce que vous êtes sûre?",
                    "Supprimer Entree", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                String requete2 = "select * from LigneEntree where NumEntree = '" + numeroEntree.getText() + "'";
                ps7 = conn.prepareStatement(requete2);
                rs7 = ps7.executeQuery();
                
                String Nom = null;
                String ok = "ok";

                while (rs7.next()) {
                    try {
                            String NumArticle = rs7.getString("NumArticle");
                            String NbrEntree = rs7.getString("NbrEntree");
                            String MontantEntree = rs7.getString("MontantEntree");

                            String requete7 = "select * from  article where NumArticle = '" + NumArticle + "'";
                            ps4 = conn.prepareStatement(requete7);
                            rs4 = ps4.executeQuery();
                            String AncienQte = rs4.getString("QteStock");
                            String AncienMtn = rs4.getString("MontantStock");
                            String NomArticle = rs4.getString("NomArticle");


                            double qte = Double.parseDouble(AncienQte) - Double.parseDouble(NbrEntree);
                            double MtnRet = Double.parseDouble(AncienMtn) - Double.parseDouble(MontantEntree);

                            if (qte < 0 || MtnRet < 0) {
                                ok = "non";
                                Nom = NomArticle;
                            } 
                    } catch (HeadlessException | SQLException e) {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "erreur de suppression" + e.getMessage() + "\n Peut être que le stock est vide!");
                    } finally {
                        CloseRsPs4();
                    }
                }
                
                if(ok.equalsIgnoreCase("ok")){
                    String requete5 = "select * from LigneEntree where NumEntree = '" + numeroEntree.getText() + "'";
                    ps5 = conn.prepareStatement(requete5);
                    rs5 = ps5.executeQuery();
                    
                    while (rs5.next()) {
                           try {
                                    String NumeroLigneEntree1 = rs5.getString("NumLigneEntree");
                                    String NumArticle1 = rs5.getString("NumArticle");
                                    String NbrEntree1 = rs5.getString("NbrEntree");
                                    String PUEntree1 = rs5.getString("puFournisseur");
                                    String MontantEntree1 = rs5.getString("MontantEntree");

                                    String requete10 = "select * from  article where NumArticle = '" + NumArticle1 + "'";
                                    ps3 = conn.prepareStatement(requete10);
                                    rs3 = ps3.executeQuery();
                                    String AncienQte1 = rs3.getString("QteStock");
                                    String AncienMtn1 = rs3.getString("MontantStock");
                                    String NomArticle1 = rs3.getString("NomArticle");


                                    double qte = Double.parseDouble(AncienQte1) - Double.parseDouble(NbrEntree1);
                                    double MtnRet = Double.parseDouble(AncienMtn1) - Double.parseDouble(MontantEntree1);

                                    if (qte < 0 || MtnRet < 0) {
                                        JOptionPane.showMessageDialog(null, "Erreur de suppression de: "+ NomArticle1 +", car le stock est insuffisant!");
                                    } else {
                                        String requete6 = "update article set QteStock =? ,pu =? ,MontantStock =? where  NumArticle ='" + NumArticle1 + "'";
                                        ps2 = conn.prepareStatement(requete6);

                                        ps2.setString(1, String.valueOf(qte));
                                        if (qte != 0) {
                                            double pu = MtnRet / qte;
                                            ps2.setString(2, String.valueOf(pu));
                                        } else {
                                            ps2.setString(2, String.valueOf(0));
                                        }
                                        ps2.setString(3, String.valueOf(MtnRet));
                                        ps2.execute();

                                        String requete = "delete from LigneEntree where NumLigneEntree = '" + NumeroLigneEntree1 + "'";
                                        ps = conn.prepareStatement(requete);
                                        ps.execute();
                                        
                                        String requete101 = "insert into Journal (DateJournal,DateMouvement,TypeMouvement,Action,NumMouvement,Acteur,NumArticle,QteMouvement,PUMouvement,MontantMouvement,QteStock,MontantStock) values (?,?,?,?,?,?,?,?,?,?,?,?)";
                                        ps9 = conn.prepareStatement(requete101);

                                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        Long millis = System.currentTimeMillis();
                                        Date date3 = new Date(millis);
                                        ps9.setString(1, dateFormat.format(date3));
                                        ps9.setString(2, dateFormat.format(date.getDate()));
                                        ps9.setString(3, "ENTREE");
                                        ps9.setString(4, "SUPPR");
                                        ps9.setString(5, ref.getText());
                                        
                                        String fournisseur = null;
                                        try {
                                        String requete15 = "select * from Fournisseur where NomFournisseur LIKE ?";
                                        ps6 = conn.prepareStatement(requete15);
                                        ps6.setString(1, "%" + ComboFournisseur.getSelectedItem().toString() + "%");
                                        rs6 = ps6.executeQuery();
                                        fournisseur = rs6.getString("NumFournisseur");
                                        } catch (SQLException e) {
                                            System.out.println(e);
                                        } finally {
                                            try {
                                                ps6.close();
                                                rs6.close();
                                            } catch (SQLException e) {
                                                JOptionPane.showMessageDialog(null, "erreur BD");
                                            }
                                        }


                                        ps9.setString(6, fournisseur);
                                        
                                        ps9.setString(7, NumArticle1);
                                        ps9.setString(8, NbrEntree1);
                                        ps9.setString(9, PUEntree1);
                                        ps9.setString(10, MontantEntree1);
                                        ps9.setString(11, String.valueOf(qte));
                                        ps9.setString(12, String.valueOf(MtnRet));
                                        ps9.execute();
                                        CloseRsPs9();
                                    }
                            } catch (HeadlessException | SQLException e) {
                                System.out.println(e);
                                JOptionPane.showMessageDialog(null, "erreur de suppression" + e.getMessage() + "\n Peut être que le stock est vide!");
                            } finally {
                                CloseRsPs1();
                                CloseRsPs2();
                                CloseRsPs2();
                            }
                        }

                    String requete = "delete from Entree where NumEntree = '" + numeroEntree.getText() + "'";
                    ps6 = conn.prepareStatement(requete);
                    ps6.execute();

                    JOptionPane.showMessageDialog(null, "Suppression succes");
                }else{
                    JOptionPane.showMessageDialog(null, "Erreur de suppression de: "+ Nom +", car le stock est insuffisant!");
                }
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "erreur de suppression" + e.getMessage());
        } finally {
            CloseRsPs5();
            CloseRsPs6();
            CloseRsPs7();
        }
        AffichageArticle();
        AffichageEntree();
        CloseConnexion();
        masquerArticle();
        masquerLigneEntree();
        clearEntree();
    }//GEN-LAST:event_btnsupprimerEntreeActionPerformed

    private void refMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_refMouseEntered

    private void refMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_refMouseExited

    private void jButtonRefreshArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshArticleActionPerformed
        conn = ConexionBD.Conexion();
        AffichageArticle();
        clearLE();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackgroundarticle.setIcon(img);
        txtrechercherarticle.setText("Taper Nom Article");
        CloseConnexion();
        
        btnnvLE.setEnabled(true);
        btnsupprimerLE.setEnabled(false);
        btnenregistrerLE.setEnabled(false);
    }//GEN-LAST:event_jButtonRefreshArticleActionPerformed

    private void txtrechercher1EntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1EntreeMouseClicked
        conn = ConexionBD.Conexion();
        AffichageEntree();
        clearEntree();

        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
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
            String requete = "select RefEntree as 'Reference' ,Fournisseur.NomFournisseur as 'Fournisseur' ,DateEntree as 'Date' ,MontantTotalEntree as 'MontantTotal' ,ObservationEntree as 'Observation' from Entree, Fournisseur WHERE\n"
                    + "(Fournisseur.NumFournisseur=Entree.NumFournisseur) and RefEntree LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercher1Entree.getText() + "%");
            rs = ps.executeQuery();
            TableEntree.setModel(DbUtils.resultSetToTableModel(rs));
            ajusterTableEntree();
            tabelEntree();
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
            String requete = "select NomArticle as 'Nom Article' ,pu as 'Prix unitaire' ,QteStock as 'Quatité en Stock' ,categorie.NomCategorie as 'Categorie' from article, categorie WHERE\n"
                    + "(categorie.NumCategorie=article.categorie) and NomArticle LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercherarticle.getText() + "%");
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
        txtrechercherarticle.setText("Tapez Nom Article");
    }//GEN-LAST:event_TableArticleEntreeMouseReleased

    private void TableArticleEntreeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableArticleEntreeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleEntreeKeyReleased

    private void puFournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puFournisseurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_puFournisseurActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1Entree.setText("Tapez Reference Entree");
        
        conn = ConexionBD.Conexion();
        AffichageEntree();
        masquerArticle();
        masquerLigneEntree();
        clearEntree();
        CloseConnexion();
        
        btnnvEntree.setEnabled(true);
        btnsupprimerEntree.setEnabled(false);
        btnmodifierEntree.setEnabled(false);
        btnenregistrerEntree.setEnabled(false);
        
        date.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[7];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                      BON D'ENTREE                                            ");
        header[3] = new MessageFormat("                  N° : "+numeroEntree.getText());
        header[4] = new MessageFormat("                  Fournisseur : "+ComboFournisseur.getSelectedItem());
        header[5] = new MessageFormat("                  REF : "+ref.getText());
        header[6] = new MessageFormat("                  Date : "+VarDateEntree);

        MessageFormat[] footer = new MessageFormat[2];
        footer[0] = new MessageFormat("           Montant Total : "+MontantTotal.getText()+" Ariary");
        footer[1] = new MessageFormat("");
        job.setCopies(2);
        job.setJobName("BondEntree");
        job.setPrintable(new MyTablePrintable(TableLigneEntree, PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }//GEN-LAST:event_printbtn1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboFournisseur;
    private javax.swing.JPanel JPanelLigneEntree;
    private javax.swing.JLabel MontantTotal;
    private javax.swing.JTable TableArticleEntree;
    private javax.swing.JTable TableEntree;
    private javax.swing.JTable TableLigneEntree;
    private javax.swing.JLabel article;
    private javax.swing.JButton btnenregistrerEntree;
    private javax.swing.JButton btnenregistrerLE;
    public javax.swing.JButton btnmodifierEntree;
    private javax.swing.JButton btnnvEntree;
    private javax.swing.JButton btnnvLE;
    private javax.swing.JButton btnsupprimerEntree;
    private javax.swing.JButton btnsupprimerLE;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JPanel impression;
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
    private javax.swing.JPanel jPanel5;
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
    private javax.swing.JButton printbtn1;
    private javax.swing.JTextField puFournisseur;
    private javax.swing.JTextField ref;
    private javax.swing.JLabel txtbackground1;
    private javax.swing.JLabel txtbackgroundarticle;
    private javax.swing.JTextField txtrechercher1Entree;
    private javax.swing.JTextField txtrechercherarticle;
    // End of variables declaration//GEN-END:variables
}
