/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author anouer
 */
public final class Journal extends javax.swing.JInternalFrame {

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
    static String test2;
    static String test3;
    static String article;
    

    /**
     * Creates new form Examen
     *
     * @throws java.sql.SQLException
     */
    public Journal() throws SQLException {

        initComponents();
        remove_title_bar();
        
        if(LoginGUI.role.equals("USER")){
            jLabel4.setVisible(false);
            NbInvt.setVisible(false);
            btnInventaire.setVisible(false);
            btnSupprInventaire.setVisible(false);
        }
        
        AffichageArticle();
        AffichageJournal();
        remplirComboCategorie();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbachground2.setIcon(img);
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbackground3.setIcon(img2);
        txtrechercher1Article.setText("Taper Nom Article");
        
        
                
        //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        //dateFormat.format(date1.getDate());
        date1.setDate(date3);
        date2.setDate(date3);
        
        
        /*PriceVolumeDemo1 pricevolumedemo1 = new PriceVolumeDemo1("Price Volume Chart Demo");
        pricevolumedemo1.pack();*/
        this.chartPanel.removeAll();
        this.chartPanel.repaint();
        
        /*PriceVolumeDemo1 pricevolumedemo1 = new PriceVolumeDemo1("test");
        pricevolumedemo1.pack();
        RefineryUtilities.centerFrameOnScreen(pricevolumedemo1);
        pricevolumedemo1.setVisible(true);*/
        
        
        JFreeChart jfreechart = createChart();
        ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, false, true);
        chartpanel.setPreferredSize(new Dimension(300, 180));
        
        chartPanel.setLayout(new BorderLayout());
        chartPanel.add(chartpanel, BorderLayout.CENTER);
        chartPanel.show();
        /*
        JFreeChart jfreechart = createChart();
        ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, false, true);
        chartpanel.setPreferredSize(new Dimension(390, 210));
        
        chartPanel.add(pricevolumedemo1);
        chartPanel.show();
        chartPanel.setVisible(true);
        
        /*PriceVolumeDemo1 pricevolumedemo1 = new PriceVolumeDemo1("Price Volume Chart Demo");
        JFreeChart jfreechart = createChart();
        ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, false, true);
        chartpanel.setPreferredSize(new Dimension(500, 270));
        pricevolumedemo1.pack();
        RefineryUtilities.centerFrameOnScreen(pricevolumedemo1);
        pricevolumedemo1.setVisible(true);*/
        
        btnInventaire.setEnabled(false);
        btnSupprInventaire.setEnabled(false);
        
        TableColorCellRenderer renderer = new TableColorCellRenderer();
        TableJournal.setDefaultRenderer(Object.class, renderer);
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
            nomArticle.setText("");
            String requete = "select NomArticle as 'Nom Article' ,pu as 'Prix unitaire' ,QteStock as 'Quatité en Stock' ,MontantStock as 'Montant en Stock' ,categorie.NomCategorie as 'Categorie' from article, categorie WHERE\n"
                    + "(categorie.NumCategorie=article.categorie)";
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
        tabelArticle();
    }
    
    private void AffichageArticleParCategorie(String categorie) {
        conn = ConexionBD.Conexion();
        try {
            nomArticle.setText("");
            String requete = "select NomArticle as 'Nom Article' ,pu as 'Prix unitaire' ,QteStock as 'Quatité en Stock' ,MontantStock as 'Montant en Stock' ,categorie.NomCategorie as 'Categorie' from article, categorie WHERE\n"
                    + "(categorie.NumCategorie=article.categorie) and categorie.NomCategorie like '" + categorie + "'";
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
        tabelArticle();
    }
    
    private void AffichageJournal() {
        conn = ConexionBD.Conexion();
        try {
            String requete = "select Date as 'Date' ,TypeMouvement as 'Type' ,Action as 'Action' ,NumMouvement as 'Ref' ,article.NomArticle as 'Article' ,AncienQte ,NouveauQte, AncienPU, NouveauPU, AncienMontant, NouveauMontant, Journal.QteStock, Journal.MontantStock,QteInventaire,Difference from Journal, article WHERE\n"
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
        tabelJournal();
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
    
    
    private void AffichageJournal(String nomArticle) {
        try {
            String requete = "select Date as 'Date' ,TypeMouvement as 'Type' ,Action as 'Action' ,NumMouvement as 'Ref' ,article.NomArticle as 'Article' ,AncienQte ,NouveauQte, AncienPU, NouveauPU, AncienMontant, NouveauMontant, Journal.QteStock, Journal.MontantStock,QteInventaire,Difference from Journal, article WHERE\n"
                    + "article.NumArticle=Journal.NumArticle and article.NomArticle like '" + nomArticle + "'";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs5));
            ajusterTableJournal();
            /*TableColorCellRenderer renderer = new TableColorCellRenderer();
            TableJournal.setDefaultRenderer(Object.class, renderer);
            TableJournal.setSelectionForeground(Color.GREEN);*/
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        tabelJournal();
        //ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbackgroundarticle.setIcon(img);
        //txtrechercherarticle.setText("Tapez Numero ou Nom Article");

    }
    
    private void AffichageJournal2dateArticle(String nomArticle, String date1, String date2) {
        try {
            String requete = "select Date as 'Date' ,TypeMouvement as 'Type' ,Action as 'Action' ,NumMouvement as 'Ref' ,article.NomArticle as 'Article' ,AncienQte ,NouveauQte, AncienPU, NouveauPU, AncienMontant, NouveauMontant, Journal.QteStock, Journal.MontantStock,QteInventaire,Difference from Journal, article WHERE\n"
                    + "article.NumArticle=Journal.NumArticle and article.NomArticle like '" + nomArticle + "' and Date between '" + date1 + "' and '" + date2 + "' order by Date desc";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs5));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        tabelJournal();
    }
    private void AffichageJournal2dateType(String type, String date1, String date2) {
        try {
            String requete = "select Date as 'Date' ,TypeMouvement as 'Type' ,Action as 'Action' ,NumMouvement as 'Ref' ,article.NomArticle as 'Article' ,AncienQte ,NouveauQte, AncienPU, NouveauPU, AncienMontant, NouveauMontant, Journal.QteStock, Journal.MontantStock,QteInventaire,Difference from Journal, article WHERE\n"
                    + "article.NumArticle=Journal.NumArticle and TypeMouvement like '" + type + "' and Date between '" + date1 + "' and '" + date2 + "' order by Date desc";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs5));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        tabelJournal();
    }
    
    private void AffichageJournal2dateArticleType(String type, String nomArticle, String date1, String date2) {
        try {
            String requete = "select Date as 'Date' ,TypeMouvement as 'Type' ,Action as 'Action' ,NumMouvement as 'Ref' ,article.NomArticle as 'Article' ,AncienQte ,NouveauQte, AncienPU, NouveauPU, AncienMontant, NouveauMontant, Journal.QteStock, Journal.MontantStock,QteInventaire,Difference from Journal, article WHERE\n"
                    + "article.NumArticle=Journal.NumArticle and article.NomArticle like '" + nomArticle + "' and TypeMouvement like '" + type + "' and Date between '" + date1 + "' and '" + date2 + "' order by Date desc";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs5));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        tabelJournal();
    }
    
    private void AffichageJournal2date(String date1, String date2) {
        try {
            String requete = "select Date as 'Date' ,TypeMouvement as 'Type' ,Action as 'Action' ,NumMouvement as 'Ref' ,article.NomArticle as 'Article' ,AncienQte ,NouveauQte, AncienPU, NouveauPU, AncienMontant, NouveauMontant, Journal.QteStock, Journal.MontantStock,QteInventaire,Difference from Journal, article WHERE\n"
                    + "article.NumArticle=Journal.NumArticle and Date between '" + date1 + "' and '" + date2 + "' order by Date desc";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs5));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        tabelJournal();
    }
    
    private void AffichageJournalFiltre(String type) {
        try {
            String requete = "select Date as 'Date' ,TypeMouvement as 'Type' ,Action as 'Action' ,NumMouvement as 'Ref' ,article.NomArticle as 'Article' ,AncienQte ,NouveauQte, AncienPU, NouveauPU, AncienMontant, NouveauMontant, Journal.QteStock, Journal.MontantStock,QteInventaire,Difference from Journal, article WHERE\n"
                    + "article.NumArticle=Journal.NumArticle and TypeMouvement like '" + type + "'";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs5));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        tabelJournal();
    }
    
    private void AffichageJournalFiltreArticle(String type, String article) {
        try {
            String requete = "select Date as 'Date' ,TypeMouvement as 'Type' ,Action as 'Action' ,NumMouvement as 'Ref' ,article.NomArticle as 'Article' ,AncienQte ,NouveauQte, AncienPU, NouveauPU, AncienMontant, NouveauMontant, Journal.QteStock, Journal.MontantStock,QteInventaire,Difference from Journal, article WHERE\n"
                    + "article.NumArticle=Journal.NumArticle and TypeMouvement like '" + type + "' and article.NomArticle like '" + article + "'";
            ps5 = conn.prepareStatement(requete);
            rs5 = ps5.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs5));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs5();
        }
        tabelJournal();
    }
    
    
    public void tabelArticle() {
        
        TableArticle.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TableArticle.getTableHeader().setOpaque(false);
        TableArticle.getTableHeader().setBackground(new Color(3, 91, 155));
        TableArticle.getTableHeader().setForeground(new Color(255,255,255));
        //TableArticle.setRowHeight(25);
    }
    
    public void tabelJournal() {
        
        TableJournal.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TableJournal.getTableHeader().setOpaque(false);
        TableJournal.getTableHeader().setBackground(new Color(3, 91, 155));
        TableJournal.getTableHeader().setForeground(new Color(255,255,255));
        //TableJournal.setRowHeight(25);
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
        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
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
        jLabel2 = new javax.swing.JLabel();
        date1 = new com.toedter.calendar.JDateChooser();
        date2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        chartPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        txtrechercher1Article = new javax.swing.JTextField();
        txtbackground3 = new javax.swing.JLabel();
        nomArticle = new javax.swing.JLabel();
        btnInventaire = new javax.swing.JButton();
        btnSupprInventaire = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        NbInvt = new javax.swing.JTextField();
        printbtn = new javax.swing.JButton();
        ComboCategorie = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        typeCombo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setBorder(null);
        setVisible(true);
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
        TableJournal.setSelectionBackground(new java.awt.Color(3, 91, 155));
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
        jScrollPane1.setBounds(0, 350, 1200, 330);

        jPanel5.setBackground(new java.awt.Color(3, 91, 155));

        jLabel10.setBackground(new java.awt.Color(0, 102, 255));
        jLabel10.setFont(new java.awt.Font("Advent Pro", 0, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("HISTORIQUE DES MOUVEMENTS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(315, Short.MAX_VALUE))
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
        TableArticle.setSelectionBackground(new java.awt.Color(3, 91, 155));
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
        jScrollPane2.setBounds(0, 120, 530, 220);

        jLabel2.setText("Article : ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(540, 100, 60, 14);

        date1.setDateFormatString("dd-MM-yyyy");
        getContentPane().add(date1);
        date1.setBounds(690, 80, 180, 20);

        date2.setDateFormatString("dd-MM-yyyy");
        getContentPane().add(date2);
        date2.setBounds(900, 80, 180, 20);

        jLabel1.setText("Recherche entre :");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(690, 60, 180, 14);

        jLabel3.setText("et");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(880, 80, 20, 14);

        jButton1.setText("Rechercher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1090, 80, 90, 23);

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        getContentPane().add(chartPanel);
        chartPanel.setBounds(670, 110, 510, 230);

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

        txtrechercher1Article.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercher1Article.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercher1Article.setForeground(new java.awt.Color(3, 91, 155));
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
        txtrechercher1Article.setBounds(100, 80, 200, 10);

        txtbackground3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        txtbackground3.setAlignmentY(0.0F);
        getContentPane().add(txtbackground3);
        txtbackground3.setBounds(90, 70, 220, 30);

        nomArticle.setText(" ");
        getContentPane().add(nomArticle);
        nomArticle.setBounds(590, 100, 70, 14);

        btnInventaire.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnInventaire.setText("Inventaire");
        btnInventaire.setBorder(null);
        btnInventaire.setBorderPainted(false);
        btnInventaire.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInventaire.setOpaque(true);
        btnInventaire.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInventaireMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInventaireMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnInventaireMousePressed(evt);
            }
        });
        btnInventaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventaireActionPerformed(evt);
            }
        });
        getContentPane().add(btnInventaire);
        btnInventaire.setBounds(540, 180, 118, 30);

        btnSupprInventaire.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSupprInventaire.setText("Supprimer");
        btnSupprInventaire.setBorder(null);
        btnSupprInventaire.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSupprInventaire.setOpaque(true);
        btnSupprInventaire.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSupprInventaireMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSupprInventaireMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSupprInventaireMousePressed(evt);
            }
        });
        btnSupprInventaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprInventaireActionPerformed(evt);
            }
        });
        getContentPane().add(btnSupprInventaire);
        btnSupprInventaire.setBounds(540, 220, 118, 30);

        jLabel4.setText("Nombre inventé :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(540, 130, 120, 14);

        NbInvt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NbInvtActionPerformed(evt);
            }
        });
        getContentPane().add(NbInvt);
        NbInvt.setBounds(540, 150, 120, 20);

        printbtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        printbtn.setText("Imprimer");
        printbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        getContentPane().add(printbtn);
        printbtn.setBounds(540, 260, 120, 30);

        ComboCategorie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboCategorieItemStateChanged(evt);
            }
        });
        ComboCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboCategorieActionPerformed(evt);
            }
        });
        getContentPane().add(ComboCategorie);
        ComboCategorie.setBounds(330, 90, 190, 20);

        jLabel5.setText("Catégorie :");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(330, 70, 90, 14);

        typeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "ENTREE", "SORTIE", "INVENTAIRE" }));
        typeCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                typeComboItemStateChanged(evt);
            }
        });
        getContentPane().add(typeCombo);
        typeCombo.setBounds(540, 320, 120, 20);

        jLabel6.setText("Filtre par Type :");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(540, 300, 110, 14);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableJournalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableJournalMouseClicked

    }//GEN-LAST:event_TableJournalMouseClicked

    private void TableJournalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableJournalMouseEntered

    }//GEN-LAST:event_TableJournalMouseEntered

    private void TableJournalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableJournalMouseReleased
        int row = TableJournal.getSelectedRow();
        Journal.test = (TableJournal.getModel().getValueAt(row, 1).toString());
        
        if(test.equals("INVENTAIRE")){
            btnInventaire.setEnabled(false);
            btnSupprInventaire.setEnabled(true);
        }else{
            btnInventaire.setEnabled(false);
            btnSupprInventaire.setEnabled(false);
        }
    }//GEN-LAST:event_TableJournalMouseReleased

    private void TableJournalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableJournalKeyReleased
        
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
        nomArticle.setText(test);
        AffichageJournal(test);
        CloseRsPs1();
        CloseRsPs2();
        CloseConnexion();
        
        NbInvt.setText("");
        
        btnInventaire.setEnabled(true);
        btnSupprInventaire.setEnabled(false);
    }//GEN-LAST:event_TableArticleMouseReleased

    private void TableArticleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableArticleKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        conn = ConexionBD.Conexion();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String Date1 = dateFormat.format(date1.getDate());
        String Date2 = dateFormat.format(date2.getDate());
        String num = nomArticle.getText();
        if(num.equals("") && typeCombo.getSelectedItem().toString().equals(" ")){
            AffichageJournal2date(Date1,Date2);
        }else if(num.equals("") && !(typeCombo.getSelectedItem().toString().equals(" "))){
            AffichageJournal2dateType(typeCombo.getSelectedItem().toString(),Date1,Date2);
        }else if(!(num.equals("")) && typeCombo.getSelectedItem().toString().equals(" ")){
            AffichageJournal2dateArticle(nomArticle.getText(),Date1,Date2);
        }else{
            AffichageJournal2dateArticleType(typeCombo.getSelectedItem().toString(),nomArticle.getText(),Date1,Date2);
        }
        btnInventaire.setEnabled(false);
        btnSupprInventaire.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void printbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMouseEntered
   
    }//GEN-LAST:event_printbtnMouseEntered

    private void printbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMouseExited
        printbtn.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_printbtnMouseExited

    private void printbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMousePressed
        printbtn.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_printbtnMousePressed

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed

        
        conn = ConexionBD.Conexion();
        if(nomArticle.getText().equals("")){
            DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
            Long millis = System.currentTimeMillis();
            Date date3 = new Date(millis);

            MessageFormat header = new MessageFormat("Historique des articles du "+dateFormat1.format(date3));

            MessageFormat footer = new MessageFormat("Page{0,number,integer}");
            try {
                TableJournal.print(JTable.PrintMode.FIT_WIDTH, header, footer);

            } catch (java.awt.print.PrinterException e) {
                System.err.format("Erreur d'impression ", e.getMessage());
            }
        }else{
            try {
            String requete = "select * from Article where NomArticle LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + nomArticle.getText() + "%");
            rs = ps.executeQuery();
            
            article = rs.getString("NomArticle");
            
            
            
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



            DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            Long millis = System.currentTimeMillis();
            Date date3 = new Date(millis);

            MessageFormat header = new MessageFormat("Historique de "+article+" du "+dateFormat.format(date3));

            MessageFormat footer = new MessageFormat("Page{0,number,integer}");
            try {
                TableJournal.print(JTable.PrintMode.FIT_WIDTH, header, footer);

            } catch (java.awt.print.PrinterException e) {
                System.err.format("Erreur d'impression ", e.getMessage());
            }
        }
        

        /*
        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            String imgFileName = "testLogo.jpg";
            PDImageXObject pdImage = PDImageXObject.createFromFile(imgFileName, doc);

            int iw = 100;
            int ih = 100;

            float offset = 20f;

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

                cont.drawImage(pdImage, offset, offset, iw, ih);
            }

            doc.save("test.pdf");
        } catch (IOException ex) {
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }//GEN-LAST:event_printbtnActionPerformed
    
    
    public void remplirComboCategorie() {
        
        ComboCategorie.removeAllItems();
        ComboCategorie.addItem("");
        String requet = " select * from  categorie";
        
        try {
            conn = ConexionBD.Conexion();
            ps5 = conn.prepareStatement(requet);
            rs5 = ps5.executeQuery();

            while (rs5.next()) {
                String nom = rs5.getString("NomCategorie").toString();
                ComboCategorie.addItem(nom);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseRsPs5();
            CloseConnexion();
        }
        ComboCategorie.setSelectedItem("");
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AffichageArticle();
        AffichageJournal();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbachground2.setIcon(img);
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbackground3.setIcon(img2);
        txtrechercher1Article.setText("Taper Nom Article");
        typeCombo.setSelectedItem(" ");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        date1.setDate(date3);
        date2.setDate(date3);
        tabelJournal();
        
        NbInvt.setText("");
        nomArticle.setText("");
        
        btnInventaire.setEnabled(false);
        btnSupprInventaire.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtrechercher1ArticleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1ArticleMouseClicked
       AffichageArticle();
        nomArticle.setText("");
        btnInventaire.setEnabled(false);
        btnSupprInventaire.setEnabled(false);

        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        //txtbackground3.setIcon(img);
        txtrechercher1Article.setText("");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbachground2.setIcon(img2);

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
        tabelJournal();
        NbInvt.setText("");
        nomArticle.setText("");
    }//GEN-LAST:event_txtrechercher1ArticleKeyReleased

    private void txtrechercher1ArticleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1ArticleKeyTyped
        nomArticle.setText("");
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
    }//GEN-LAST:event_txtrechercher1ArticleKeyTyped

    private void btnInventaireMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventaireMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInventaireMouseEntered

    private void btnInventaireMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventaireMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInventaireMouseExited

    private void btnInventaireMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventaireMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInventaireMousePressed

    private void btnInventaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventaireActionPerformed
        try {
            conn = ConexionBD.Conexion();
            
            String requete = "select * from  article where NomArticle = '" + nomArticle.getText() + "'";
            ps = conn.prepareStatement(requete);
            rs = ps.executeQuery();
            String ancienQte = rs.getString("QteStock");
            String num = rs.getString("NumArticle");
            
            if(NbInvt.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Veillez entrer l'inventaire!");
            }else if(ancienQte.equals(NbInvt.getText())){
                String requete5 = "insert into Journal (Date,TypeMouvement,Action,NumArticle,QteStock,QteInventaire,Difference) values (?,?,?,?,?,?,?)";
                ps5 = conn.prepareStatement(requete5);
                
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Long millis = System.currentTimeMillis();
                Date date3 = new Date(millis);
                ps5.setString(1, dateFormat.format(date3));
                ps5.setString(2, "INVENTAIRE");
                ps5.setString(3, "OK");
                ps5.setString(4, num);
                ps5.setString(5, ancienQte);
                ps5.setString(6, NbInvt.getText());
                ps5.setString(7, "0");
                ps5.execute();
                
                int row = TableArticle.getSelectedRow();
                Journal.test = (TableArticle.getModel().getValueAt(row, 0).toString());
                nomArticle.setText(test);
                AffichageJournal(test);
                CloseRsPs5();
            }else{
                String requete5 = "insert into Journal (Date,TypeMouvement,Action,NumArticle,QteStock,QteInventaire,Difference) values (?,?,?,?,?,?,?)";
                ps5 = conn.prepareStatement(requete5);

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Long millis = System.currentTimeMillis();
                Date date3 = new Date(millis);
                ps5.setString(1, dateFormat.format(date3));
                ps5.setString(2, "INVENTAIRE");
                ps5.setString(3, "NON OK");
                ps5.setString(4, num);
                ps5.setString(5, ancienQte);
                ps5.setString(6, NbInvt.getText());
                int resultat = Integer.parseInt(NbInvt.getText())-Integer.parseInt(ancienQte);
                ps5.setString(7, String.valueOf(resultat));
                ps5.execute();

                int row = TableArticle.getSelectedRow();
                Journal.test = (TableArticle.getModel().getValueAt(row, 0).toString());
                nomArticle.setText(test);
                AffichageJournal(test);
                CloseRsPs5();
            }
            CloseConnexion();
            
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Exception : "+e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInventaireActionPerformed

    private void btnSupprInventaireMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupprInventaireMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupprInventaireMouseEntered

    private void btnSupprInventaireMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupprInventaireMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupprInventaireMouseExited

    private void btnSupprInventaireMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupprInventaireMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupprInventaireMousePressed

    private void btnSupprInventaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprInventaireActionPerformed
        conn = ConexionBD.Conexion();
        try {
            int row = TableJournal.getSelectedRow();
            Journal.test2 = (TableJournal.getModel().getValueAt(row, 0).toString());
            Journal.test3 = (TableJournal.getModel().getValueAt(row, 4).toString());
            
            String requete = "select * from  article where NomArticle = '" + test3 + "'";
            ps2 = conn.prepareStatement(requete);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("NumArticle");
            
            String type = "INVENTAIRE";
            String requet = "delete from Journal where Date = '" + test2 + "' and NumArticle = '" + num + "' and TypeMouvement = '" + type + "'";
            ps = conn.prepareStatement(requet);
            ps.execute();
            
            if(nomArticle.getText().equals("")){
                AffichageJournal();
            }else{
                AffichageJournal(test3);
            }
            AffichageArticle();
            ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
            ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
            txtrechercher1Article.setText("Taper Nom Article");
            Long millis = System.currentTimeMillis();
            Date date3 = new Date(millis);
            date1.setDate(date3);
            date2.setDate(date3);
            tabelJournal();
            
            btnInventaire.setEnabled(false);
            btnSupprInventaire.setEnabled(false);

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            CloseRsPs1();
            CloseRsPs2();
            CloseConnexion();
        }
    }//GEN-LAST:event_btnSupprInventaireActionPerformed

    private void NbInvtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NbInvtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NbInvtActionPerformed

    private void ComboCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboCategorieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboCategorieActionPerformed

    private void ComboCategorieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboCategorieItemStateChanged
        String item = ComboCategorie.getSelectedItem().toString();
        if(item.equals("")){
            AffichageArticle();
        }else{
            AffichageArticleParCategorie(item);
        }
    }//GEN-LAST:event_ComboCategorieItemStateChanged

    private void typeComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typeComboItemStateChanged
        conn = ConexionBD.Conexion();
        String num = nomArticle.getText();
        if(typeCombo.getSelectedItem().toString().equals(" ")){
            if(num.equals("")){
                AffichageJournal();
            }else{
                AffichageJournal(num);
            }
        }else{
            if(num.equals("")){
                AffichageJournalFiltre(typeCombo.getSelectedItem().toString());
            }else{
                AffichageJournalFiltreArticle(typeCombo.getSelectedItem().toString(),nomArticle.getText());
            }
        }
        btnInventaire.setEnabled(false);
        btnSupprInventaire.setEnabled(false);
    }//GEN-LAST:event_typeComboItemStateChanged

    private static final long serialVersionUID = 1L;

    public void AfficherPriceVolumeChart() {
        JFreeChart jfreechart = createChart();
        ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, false, true);
        chartpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartpanel);
    }

    private static JFreeChart createChart() {
        XYDataset xydataset = createPriceDataset();
        String s = "Eurodollar Futures Contract (MAR03)";
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(s, "Date", "Price", xydataset, true, true,
                false);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setLowerMargin(0.40000000000000002D);
        DecimalFormat decimalformat = new DecimalFormat("00.00");
        numberaxis.setNumberFormatOverride(decimalformat);
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        xyitemrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})",
                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
        NumberAxis numberaxis1 = new NumberAxis("Volume");
        numberaxis1.setUpperMargin(1.0D);
        xyplot.setRangeAxis(1, numberaxis1);
        xyplot.setDataset(1, createVolumeDataset());
        xyplot.setRangeAxis(1, numberaxis1);
        xyplot.mapDatasetToRangeAxis(1, 1);
        XYBarRenderer xybarrenderer = new XYBarRenderer(0.20000000000000001D);
        xybarrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})",
                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
        xyplot.setRenderer(1, xybarrenderer);
        return jfreechart;
    }

    private static XYDataset createPriceDataset() {
        TimeSeries timeseries = new TimeSeries("Price");
        timeseries.add(new Day(2, 1, 2002), 95.564999999999998D);
        timeseries.add(new Day(3, 1, 2002), 95.640000000000001D);
        timeseries.add(new Day(4, 1, 2002), 95.709999999999994D);
        timeseries.add(new Day(7, 1, 2002), 95.930000000000007D);
        timeseries.add(new Day(8, 1, 2002), 95.930000000000007D);
        timeseries.add(new Day(9, 1, 2002), 95.959999999999994D);
        timeseries.add(new Day(10, 1, 2002), 96.055000000000007D);
        timeseries.add(new Day(11, 1, 2002), 96.334999999999994D);
        timeseries.add(new Day(14, 1, 2002), 96.290000000000006D);
        timeseries.add(new Day(15, 1, 2002), 96.275000000000006D);
        timeseries.add(new Day(16, 1, 2002), 96.239999999999995D);
        timeseries.add(new Day(17, 1, 2002), 96.079999999999998D);
        timeseries.add(new Day(18, 1, 2002), 96.144999999999996D);
        timeseries.add(new Day(22, 1, 2002), 96.120000000000005D);
        timeseries.add(new Day(23, 1, 2002), 96.015000000000001D);
        timeseries.add(new Day(24, 1, 2002), 95.890000000000001D);
        timeseries.add(new Day(25, 1, 2002), 95.864999999999995D);
        timeseries.add(new Day(28, 1, 2002), 95.879999999999995D);
        timeseries.add(new Day(29, 1, 2002), 96.049999999999997D);
        timeseries.add(new Day(30, 1, 2002), 96.064999999999998D);
        timeseries.add(new Day(31, 1, 2002), 95.909999999999997D);
        timeseries.add(new Day(1, 2, 2002), 96.015000000000001D);
        timeseries.add(new Day(4, 2, 2002), 96.140000000000001D);
        timeseries.add(new Day(5, 2, 2002), 96.194999999999993D);
        timeseries.add(new Day(6, 2, 2002), 96.245000000000005D);
        timeseries.add(new Day(7, 2, 2002), 96.219999999999999D);
        timeseries.add(new Day(8, 2, 2002), 96.280000000000001D);
        timeseries.add(new Day(11, 2, 2002), 96.265000000000001D);
        timeseries.add(new Day(12, 2, 2002), 96.159999999999997D);
        timeseries.add(new Day(13, 2, 2002), 96.120000000000005D);
        timeseries.add(new Day(14, 2, 2002), 96.125D);
        timeseries.add(new Day(15, 2, 2002), 96.265000000000001D);
        timeseries.add(new Day(19, 2, 2002), 96.290000000000006D);
        timeseries.add(new Day(20, 2, 2002), 96.275000000000006D);
        timeseries.add(new Day(21, 2, 2002), 96.280000000000001D);
        timeseries.add(new Day(22, 2, 2002), 96.305000000000007D);
        timeseries.add(new Day(25, 2, 2002), 96.265000000000001D);
        timeseries.add(new Day(26, 2, 2002), 96.185000000000002D);
        timeseries.add(new Day(27, 2, 2002), 96.305000000000007D);
        timeseries.add(new Day(28, 2, 2002), 96.215000000000003D);
        timeseries.add(new Day(1, 3, 2002), 96.015000000000001D);
        timeseries.add(new Day(4, 3, 2002), 95.969999999999999D);
        timeseries.add(new Day(5, 3, 2002), 95.935000000000002D);
        timeseries.add(new Day(6, 3, 2002), 95.935000000000002D);
        timeseries.add(new Day(7, 3, 2002), 95.704999999999998D);
        timeseries.add(new Day(8, 3, 2002), 95.484999999999999D);
        timeseries.add(new Day(11, 3, 2002), 95.504999999999995D);
        timeseries.add(new Day(12, 3, 2002), 95.540000000000006D);
        timeseries.add(new Day(13, 3, 2002), 95.674999999999997D);
        timeseries.add(new Day(14, 3, 2002), 95.510000000000005D);
        timeseries.add(new Day(15, 3, 2002), 95.5D);
        timeseries.add(new Day(18, 3, 2002), 95.5D);
        timeseries.add(new Day(19, 3, 2002), 95.534999999999997D);
        timeseries.add(new Day(20, 3, 2002), 95.420000000000002D);
        timeseries.add(new Day(21, 3, 2002), 95.400000000000006D);
        timeseries.add(new Day(22, 3, 2002), 95.375D);
        timeseries.add(new Day(25, 3, 2002), 95.349999999999994D);
        timeseries.add(new Day(26, 3, 2002), 95.504999999999995D);
        timeseries.add(new Day(27, 3, 2002), 95.549999999999997D);
        timeseries.add(new Day(28, 3, 2002), 95.484999999999999D);
        timeseries.add(new Day(1, 4, 2002), 95.484999999999999D);
        timeseries.add(new Day(2, 4, 2002), 95.629999999999995D);
        timeseries.add(new Day(3, 4, 2002), 95.734999999999999D);
        timeseries.add(new Day(4, 4, 2002), 95.694999999999993D);
        timeseries.add(new Day(5, 4, 2002), 95.810000000000002D);
        timeseries.add(new Day(8, 4, 2002), 95.810000000000002D);
        timeseries.add(new Day(9, 4, 2002), 95.864999999999995D);
        timeseries.add(new Day(10, 4, 2002), 95.885000000000005D);
        timeseries.add(new Day(11, 4, 2002), 95.900000000000006D);
        timeseries.add(new Day(12, 4, 2002), 95.980000000000004D);
        timeseries.add(new Day(15, 4, 2002), 96.034999999999997D);
        timeseries.add(new Day(16, 4, 2002), 96D);
        timeseries.add(new Day(17, 4, 2002), 96.034999999999997D);
        timeseries.add(new Day(18, 4, 2002), 96.084999999999994D);
        timeseries.add(new Day(19, 4, 2002), 96.075000000000003D);
        timeseries.add(new Day(22, 4, 2002), 96.105000000000004D);
        timeseries.add(new Day(23, 4, 2002), 96.075000000000003D);
        timeseries.add(new Day(24, 4, 2002), 96.209999999999994D);
        timeseries.add(new Day(25, 4, 2002), 96.254999999999995D);
        timeseries.add(new Day(26, 4, 2002), 96.310000000000002D);
        timeseries.add(new Day(29, 4, 2002), 96.310000000000002D);
        timeseries.add(new Day(30, 4, 2002), 96.325000000000003D);
        timeseries.add(new Day(1, 5, 2002), 96.344999999999999D);
        timeseries.add(new Day(2, 5, 2002), 96.284999999999997D);
        timeseries.add(new Day(3, 5, 2002), 96.385000000000005D);
        timeseries.add(new Day(6, 5, 2002), 96.379999999999995D);
        timeseries.add(new Day(7, 5, 2002), 96.484999999999999D);
        timeseries.add(new Day(8, 5, 2002), 96.230000000000004D);
        timeseries.add(new Day(9, 5, 2002), 96.310000000000002D);
        timeseries.add(new Day(10, 5, 2002), 96.444999999999993D);
        timeseries.add(new Day(13, 5, 2002), 96.355000000000004D);
        timeseries.add(new Day(14, 5, 2002), 96.180000000000007D);
        timeseries.add(new Day(15, 5, 2002), 96.239999999999995D);
        timeseries.add(new Day(16, 5, 2002), 96.325000000000003D);
        timeseries.add(new Day(17, 5, 2002), 96.200000000000003D);
        timeseries.add(new Day(20, 5, 2002), 96.305000000000007D);
        timeseries.add(new Day(21, 5, 2002), 96.385000000000005D);
        timeseries.add(new Day(22, 5, 2002), 96.444999999999993D);
        timeseries.add(new Day(23, 5, 2002), 96.385000000000005D);
        timeseries.add(new Day(24, 5, 2002), 96.390000000000001D);
        timeseries.add(new Day(28, 5, 2002), 96.390000000000001D);
        timeseries.add(new Day(29, 5, 2002), 96.474999999999994D);
        timeseries.add(new Day(30, 5, 2002), 96.555000000000007D);
        timeseries.add(new Day(31, 5, 2002), 96.5D);
        timeseries.add(new Day(3, 6, 2002), 96.540000000000006D);
        timeseries.add(new Day(4, 6, 2002), 96.605000000000004D);
        timeseries.add(new Day(5, 6, 2002), 96.579999999999998D);
        timeseries.add(new Day(6, 6, 2002), 96.609999999999999D);
        timeseries.add(new Day(7, 6, 2002), 96.599999999999994D);
        timeseries.add(new Day(10, 6, 2002), 96.614999999999995D);
        timeseries.add(new Day(11, 6, 2002), 96.704999999999998D);
        timeseries.add(new Day(12, 6, 2002), 96.75D);
        timeseries.add(new Day(13, 6, 2002), 96.829999999999998D);
        timeseries.add(new Day(14, 6, 2002), 96.965000000000003D);
        timeseries.add(new Day(17, 6, 2002), 96.944999999999993D);
        timeseries.add(new Day(18, 6, 2002), 96.989999999999995D);
        timeseries.add(new Day(19, 6, 2002), 97.165000000000006D);
        timeseries.add(new Day(20, 6, 2002), 97.030000000000001D);
        timeseries.add(new Day(21, 6, 2002), 97.144999999999996D);
        timeseries.add(new Day(24, 6, 2002), 97.120000000000005D);
        timeseries.add(new Day(25, 6, 2002), 97.174999999999997D);
        timeseries.add(new Day(26, 6, 2002), 97.364999999999995D);
        timeseries.add(new Day(27, 6, 2002), 97.245000000000005D);
        timeseries.add(new Day(28, 6, 2002), 97.245000000000005D);
        timeseries.add(new Day(1, 7, 2002), 97.290000000000006D);
        timeseries.add(new Day(2, 7, 2002), 97.379999999999995D);
        timeseries.add(new Day(3, 7, 2002), 97.379999999999995D);
        timeseries.add(new Day(5, 7, 2002), 97.219999999999999D);
        timeseries.add(new Day(8, 7, 2002), 97.325000000000003D);
        timeseries.add(new Day(9, 7, 2002), 97.454999999999998D);
        timeseries.add(new Day(10, 7, 2002), 97.579999999999998D);
        timeseries.add(new Day(11, 7, 2002), 97.605000000000004D);
        timeseries.add(new Day(12, 7, 2002), 97.689999999999998D);
        timeseries.add(new Day(15, 7, 2002), 97.730000000000004D);
        timeseries.add(new Day(16, 7, 2002), 97.579999999999998D);
        timeseries.add(new Day(17, 7, 2002), 97.640000000000001D);
        timeseries.add(new Day(18, 7, 2002), 97.680000000000007D);
        timeseries.add(new Day(19, 7, 2002), 97.715000000000003D);
        timeseries.add(new Day(22, 7, 2002), 97.814999999999998D);
        timeseries.add(new Day(23, 7, 2002), 97.875D);
        timeseries.add(new Day(24, 7, 2002), 97.834999999999994D);
        timeseries.add(new Day(25, 7, 2002), 97.924999999999997D);
        timeseries.add(new Day(26, 7, 2002), 97.959999999999994D);
        timeseries.add(new Day(29, 7, 2002), 97.745000000000005D);
        timeseries.add(new Day(30, 7, 2002), 97.709999999999994D);
        timeseries.add(new Day(31, 7, 2002), 97.930000000000007D);
        timeseries.add(new Day(1, 8, 2002), 98D);
        timeseries.add(new Day(2, 8, 2002), 98.170000000000002D);
        timeseries.add(new Day(5, 8, 2002), 98.224999999999994D);
        timeseries.add(new Day(6, 8, 2002), 98.114999999999995D);
        timeseries.add(new Day(7, 8, 2002), 98.265000000000001D);
        timeseries.add(new Day(8, 8, 2002), 98.180000000000007D);
        timeseries.add(new Day(9, 8, 2002), 98.185000000000002D);
        timeseries.add(new Day(12, 8, 2002), 98.150000000000006D);
        timeseries.add(new Day(13, 8, 2002), 98.290000000000006D);
        timeseries.add(new Day(14, 8, 2002), 98.155000000000001D);
        timeseries.add(new Day(15, 8, 2002), 98.075000000000003D);
        timeseries.add(new Day(16, 8, 2002), 98D);
        timeseries.add(new Day(19, 8, 2002), 98.040000000000006D);
        timeseries.add(new Day(20, 8, 2002), 98.135000000000005D);
        timeseries.add(new Day(21, 8, 2002), 98.109999999999999D);
        timeseries.add(new Day(22, 8, 2002), 98.004999999999995D);
        timeseries.add(new Day(23, 8, 2002), 98.055000000000007D);
        timeseries.add(new Day(26, 8, 2002), 98.064999999999998D);
        timeseries.add(new Day(27, 8, 2002), 97.980000000000004D);
        timeseries.add(new Day(28, 8, 2002), 98.034999999999997D);
        timeseries.add(new Day(29, 8, 2002), 98.094999999999999D);
        timeseries.add(new Day(30, 8, 2002), 98.060000000000002D);
        timeseries.add(new Day(3, 9, 2002), 98.25D);
        timeseries.add(new Day(4, 9, 2002), 98.245000000000005D);
        timeseries.add(new Day(5, 9, 2002), 98.314999999999998D);
        timeseries.add(new Day(6, 9, 2002), 98.170000000000002D);
        timeseries.add(new Day(9, 9, 2002), 98.079999999999998D);
        timeseries.add(new Day(10, 9, 2002), 98.090000000000003D);
        timeseries.add(new Day(11, 9, 2002), 98.030000000000001D);
        timeseries.add(new Day(12, 9, 2002), 98.105000000000004D);
        timeseries.add(new Day(13, 9, 2002), 98.135000000000005D);
        timeseries.add(new Day(16, 9, 2002), 98.114999999999995D);
        timeseries.add(new Day(17, 9, 2002), 98.125D);
        timeseries.add(new Day(18, 9, 2002), 98.129999999999995D);
        timeseries.add(new Day(19, 9, 2002), 98.254999999999995D);
        timeseries.add(new Day(20, 9, 2002), 98.254999999999995D);
        timeseries.add(new Day(23, 9, 2002), 98.280000000000001D);
        timeseries.add(new Day(24, 9, 2002), 98.310000000000002D);
        timeseries.add(new Day(25, 9, 2002), 98.25D);
        timeseries.add(new Day(26, 9, 2002), 98.299999999999997D);
        timeseries.add(new Day(27, 9, 2002), 98.409999999999997D);
        timeseries.add(new Day(30, 9, 2002), 98.495000000000005D);
        timeseries.add(new Day(1, 10, 2002), 98.439999999999998D);
        timeseries.add(new Day(2, 10, 2002), 98.439999999999998D);
        timeseries.add(new Day(3, 10, 2002), 98.439999999999998D);
        timeseries.add(new Day(4, 10, 2002), 98.379999999999995D);
        timeseries.add(new Day(7, 10, 2002), 98.385000000000005D);
        timeseries.add(new Day(8, 10, 2002), 98.340000000000003D);
        timeseries.add(new Day(9, 10, 2002), 98.420000000000002D);
        timeseries.add(new Day(10, 10, 2002), 98.375D);
        timeseries.add(new Day(11, 10, 2002), 98.275000000000006D);
        timeseries.add(new Day(14, 10, 2002), 98.275000000000006D);
        timeseries.add(new Day(15, 10, 2002), 98.135000000000005D);
        timeseries.add(new Day(16, 10, 2002), 98.165000000000006D);
        timeseries.add(new Day(17, 10, 2002), 98.170000000000002D);
        timeseries.add(new Day(18, 10, 2002), 98.165000000000006D);
        timeseries.add(new Day(21, 10, 2002), 98.105000000000004D);
        timeseries.add(new Day(22, 10, 2002), 98.125D);
        timeseries.add(new Day(23, 10, 2002), 98.185000000000002D);
        timeseries.add(new Day(24, 10, 2002), 98.245000000000005D);
        timeseries.add(new Day(25, 10, 2002), 98.319999999999993D);
        timeseries.add(new Day(28, 10, 2002), 98.420000000000002D);
        timeseries.add(new Day(29, 10, 2002), 98.540000000000006D);
        timeseries.add(new Day(30, 10, 2002), 98.545000000000002D);
        timeseries.add(new Day(31, 10, 2002), 98.560000000000002D);
        return new TimeSeriesCollection(timeseries);
    }

    private static IntervalXYDataset createVolumeDataset() {
        TimeSeries timeseries = new TimeSeries("Volume");
        timeseries.add(new Day(2, 1, 2002), 41020D);
        timeseries.add(new Day(3, 1, 2002), 45586D);
        timeseries.add(new Day(4, 1, 2002), 81672D);
        timeseries.add(new Day(7, 1, 2002), 81975D);
        timeseries.add(new Day(8, 1, 2002), 79692D);
        timeseries.add(new Day(9, 1, 2002), 53187D);
        timeseries.add(new Day(10, 1, 2002), 87929D);
        timeseries.add(new Day(11, 1, 2002), 107047D);
        timeseries.add(new Day(14, 1, 2002), 86276D);
        timeseries.add(new Day(15, 1, 2002), 79005D);
        timeseries.add(new Day(16, 1, 2002), 80632D);
        timeseries.add(new Day(17, 1, 2002), 88797D);
        timeseries.add(new Day(18, 1, 2002), 57179D);
        timeseries.add(new Day(22, 1, 2002), 36611D);
        timeseries.add(new Day(23, 1, 2002), 57063D);
        timeseries.add(new Day(24, 1, 2002), 101938D);
        timeseries.add(new Day(25, 1, 2002), 87177D);
        timeseries.add(new Day(28, 1, 2002), 39831D);
        timeseries.add(new Day(29, 1, 2002), 67654D);
        timeseries.add(new Day(30, 1, 2002), 81162D);
        timeseries.add(new Day(31, 1, 2002), 64923D);
        timeseries.add(new Day(1, 2, 2002), 73481D);
        timeseries.add(new Day(4, 2, 2002), 54723D);
        timeseries.add(new Day(5, 2, 2002), 76708D);
        timeseries.add(new Day(6, 2, 2002), 81281D);
        timeseries.add(new Day(7, 2, 2002), 66553D);
        timeseries.add(new Day(8, 2, 2002), 53592D);
        timeseries.add(new Day(11, 2, 2002), 29410D);
        timeseries.add(new Day(12, 2, 2002), 60345D);
        timeseries.add(new Day(13, 2, 2002), 67339D);
        timeseries.add(new Day(14, 2, 2002), 40057D);
        timeseries.add(new Day(15, 2, 2002), 67865D);
        timeseries.add(new Day(19, 2, 2002), 58628D);
        timeseries.add(new Day(20, 2, 2002), 52109D);
        timeseries.add(new Day(21, 2, 2002), 50195D);
        timeseries.add(new Day(22, 2, 2002), 47806D);
        timeseries.add(new Day(25, 2, 2002), 31711D);
        timeseries.add(new Day(26, 2, 2002), 88328D);
        timeseries.add(new Day(27, 2, 2002), 95805D);
        timeseries.add(new Day(28, 2, 2002), 84035D);
        timeseries.add(new Day(1, 3, 2002), 113584D);
        timeseries.add(new Day(4, 3, 2002), 71872D);
        timeseries.add(new Day(5, 3, 2002), 83016D);
        timeseries.add(new Day(6, 3, 2002), 62273D);
        timeseries.add(new Day(7, 3, 2002), 138508D);
        timeseries.add(new Day(8, 3, 2002), 139428D);
        timeseries.add(new Day(11, 3, 2002), 80232D);
        timeseries.add(new Day(12, 3, 2002), 75693D);
        timeseries.add(new Day(13, 3, 2002), 104068D);
        timeseries.add(new Day(14, 3, 2002), 72171D);
        timeseries.add(new Day(15, 3, 2002), 117262D);
        timeseries.add(new Day(18, 3, 2002), 66048D);
        timeseries.add(new Day(19, 3, 2002), 87079D);
        timeseries.add(new Day(20, 3, 2002), 116084D);
        timeseries.add(new Day(21, 3, 2002), 113206D);
        timeseries.add(new Day(22, 3, 2002), 68326D);
        timeseries.add(new Day(25, 3, 2002), 34340D);
        timeseries.add(new Day(26, 3, 2002), 104413D);
        timeseries.add(new Day(27, 3, 2002), 57277D);
        timeseries.add(new Day(28, 3, 2002), 69936D);
        timeseries.add(new Day(1, 4, 2002), 57282D);
        timeseries.add(new Day(2, 4, 2002), 74686D);
        timeseries.add(new Day(3, 4, 2002), 108601D);
        timeseries.add(new Day(4, 4, 2002), 123381D);
        timeseries.add(new Day(5, 4, 2002), 106691D);
        timeseries.add(new Day(8, 4, 2002), 118535D);
        timeseries.add(new Day(9, 4, 2002), 85577D);
        timeseries.add(new Day(10, 4, 2002), 75441D);
        timeseries.add(new Day(11, 4, 2002), 88845D);
        timeseries.add(new Day(12, 4, 2002), 137141D);
        timeseries.add(new Day(15, 4, 2002), 72518D);
        timeseries.add(new Day(16, 4, 2002), 122100D);
        timeseries.add(new Day(17, 4, 2002), 136419D);
        timeseries.add(new Day(18, 4, 2002), 141338D);
        timeseries.add(new Day(19, 4, 2002), 80274D);
        timeseries.add(new Day(22, 4, 2002), 40449D);
        timeseries.add(new Day(23, 4, 2002), 72292D);
        timeseries.add(new Day(24, 4, 2002), 110644D);
        timeseries.add(new Day(25, 4, 2002), 145142D);
        timeseries.add(new Day(26, 4, 2002), 139573D);
        timeseries.add(new Day(29, 4, 2002), 51509D);
        timeseries.add(new Day(30, 4, 2002), 105782D);
        timeseries.add(new Day(1, 5, 2002), 170680D);
        timeseries.add(new Day(2, 5, 2002), 140800D);
        timeseries.add(new Day(3, 5, 2002), 170411D);
        timeseries.add(new Day(6, 5, 2002), 46172D);
        timeseries.add(new Day(7, 5, 2002), 137251D);
        timeseries.add(new Day(8, 5, 2002), 220626D);
        timeseries.add(new Day(9, 5, 2002), 175902D);
        timeseries.add(new Day(10, 5, 2002), 128807D);
        timeseries.add(new Day(13, 5, 2002), 78208D);
        timeseries.add(new Day(14, 5, 2002), 212048D);
        timeseries.add(new Day(15, 5, 2002), 145643D);
        timeseries.add(new Day(16, 5, 2002), 121520D);
        timeseries.add(new Day(17, 5, 2002), 147820D);
        timeseries.add(new Day(20, 5, 2002), 75969D);
        timeseries.add(new Day(21, 5, 2002), 118970D);
        timeseries.add(new Day(22, 5, 2002), 131013D);
        timeseries.add(new Day(23, 5, 2002), 141100D);
        timeseries.add(new Day(24, 5, 2002), 63606D);
        timeseries.add(new Day(28, 5, 2002), 78687D);
        timeseries.add(new Day(29, 5, 2002), 86743D);
        timeseries.add(new Day(30, 5, 2002), 164376D);
        timeseries.add(new Day(31, 5, 2002), 150108D);
        timeseries.add(new Day(3, 6, 2002), 132363D);
        timeseries.add(new Day(4, 6, 2002), 144902D);
        timeseries.add(new Day(5, 6, 2002), 123834D);
        timeseries.add(new Day(6, 6, 2002), 125004D);
        timeseries.add(new Day(7, 6, 2002), 165049D);
        timeseries.add(new Day(10, 6, 2002), 88069D);
        timeseries.add(new Day(11, 6, 2002), 114146D);
        timeseries.add(new Day(12, 6, 2002), 149992D);
        timeseries.add(new Day(13, 6, 2002), 191261D);
        timeseries.add(new Day(14, 6, 2002), 207444D);
        timeseries.add(new Day(17, 6, 2002), 117081D);
        timeseries.add(new Day(18, 6, 2002), 135924D);
        timeseries.add(new Day(19, 6, 2002), 179654D);
        timeseries.add(new Day(20, 6, 2002), 260936D);
        timeseries.add(new Day(21, 6, 2002), 140283D);
        timeseries.add(new Day(24, 6, 2002), 199052D);
        timeseries.add(new Day(25, 6, 2002), 191804D);
        timeseries.add(new Day(26, 6, 2002), 384936D);
        timeseries.add(new Day(27, 6, 2002), 313065D);
        timeseries.add(new Day(28, 6, 2002), 169963D);
        timeseries.add(new Day(1, 7, 2002), 109906D);
        timeseries.add(new Day(2, 7, 2002), 140644D);
        timeseries.add(new Day(3, 7, 2002), 150898D);
        timeseries.add(new Day(5, 7, 2002), 181355D);
        timeseries.add(new Day(8, 7, 2002), 155042D);
        timeseries.add(new Day(9, 7, 2002), 204305D);
        timeseries.add(new Day(10, 7, 2002), 300113D);
        timeseries.add(new Day(11, 7, 2002), 338948D);
        timeseries.add(new Day(12, 7, 2002), 281325D);
        timeseries.add(new Day(15, 7, 2002), 256101D);
        timeseries.add(new Day(16, 7, 2002), 348164D);
        timeseries.add(new Day(17, 7, 2002), 242995D);
        timeseries.add(new Day(18, 7, 2002), 200744D);
        timeseries.add(new Day(19, 7, 2002), 181071D);
        timeseries.add(new Day(22, 7, 2002), 163266D);
        timeseries.add(new Day(23, 7, 2002), 188508D);
        timeseries.add(new Day(24, 7, 2002), 308070D);
        timeseries.add(new Day(25, 7, 2002), 230901D);
        timeseries.add(new Day(26, 7, 2002), 162577D);
        timeseries.add(new Day(29, 7, 2002), 216318D);
        timeseries.add(new Day(30, 7, 2002), 280677D);
        timeseries.add(new Day(31, 7, 2002), 260236D);
        timeseries.add(new Day(1, 8, 2002), 242803D);
        timeseries.add(new Day(2, 8, 2002), 298490D);
        timeseries.add(new Day(5, 8, 2002), 182890D);
        timeseries.add(new Day(6, 8, 2002), 232273D);
        timeseries.add(new Day(7, 8, 2002), 253552D);
        timeseries.add(new Day(8, 8, 2002), 165365D);
        timeseries.add(new Day(9, 8, 2002), 160382D);
        timeseries.add(new Day(12, 8, 2002), 118030D);
        timeseries.add(new Day(13, 8, 2002), 208807D);
        timeseries.add(new Day(14, 8, 2002), 231599D);
        timeseries.add(new Day(15, 8, 2002), 343482D);
        timeseries.add(new Day(16, 8, 2002), 186116D);
        timeseries.add(new Day(19, 8, 2002), 96437D);
        timeseries.add(new Day(20, 8, 2002), 151735D);
        timeseries.add(new Day(21, 8, 2002), 167390D);
        timeseries.add(new Day(22, 8, 2002), 127184D);
        timeseries.add(new Day(23, 8, 2002), 80205D);
        timeseries.add(new Day(26, 8, 2002), 79893D);
        timeseries.add(new Day(27, 8, 2002), 201723D);
        timeseries.add(new Day(28, 8, 2002), 114001D);
        timeseries.add(new Day(29, 8, 2002), 188389D);
        timeseries.add(new Day(30, 8, 2002), 162801D);
        timeseries.add(new Day(3, 9, 2002), 200951D);
        timeseries.add(new Day(4, 9, 2002), 129229D);
        timeseries.add(new Day(5, 9, 2002), 183348D);
        timeseries.add(new Day(6, 9, 2002), 216722D);
        timeseries.add(new Day(9, 9, 2002), 128575D);
        timeseries.add(new Day(10, 9, 2002), 224714D);
        timeseries.add(new Day(11, 9, 2002), 144224D);
        timeseries.add(new Day(12, 9, 2002), 195721D);
        timeseries.add(new Day(13, 9, 2002), 160724D);
        timeseries.add(new Day(16, 9, 2002), 65473D);
        timeseries.add(new Day(17, 9, 2002), 141274D);
        timeseries.add(new Day(18, 9, 2002), 115084D);
        timeseries.add(new Day(19, 9, 2002), 242106D);
        timeseries.add(new Day(20, 9, 2002), 130034D);
        timeseries.add(new Day(23, 9, 2002), 95215D);
        timeseries.add(new Day(24, 9, 2002), 229288D);
        timeseries.add(new Day(25, 9, 2002), 163672D);
        timeseries.add(new Day(26, 9, 2002), 193573D);
        timeseries.add(new Day(27, 9, 2002), 170741D);
        timeseries.add(new Day(30, 9, 2002), 199615D);
        timeseries.add(new Day(1, 10, 2002), 170771D);
        timeseries.add(new Day(2, 10, 2002), 138498D);
        timeseries.add(new Day(3, 10, 2002), 154774D);
        timeseries.add(new Day(4, 10, 2002), 287154D);
        timeseries.add(new Day(7, 10, 2002), 111762D);
        timeseries.add(new Day(8, 10, 2002), 172535D);
        timeseries.add(new Day(9, 10, 2002), 148339D);
        timeseries.add(new Day(10, 10, 2002), 178796D);
        timeseries.add(new Day(11, 10, 2002), 153499D);
        timeseries.add(new Day(14, 10, 2002), 4589D);
        timeseries.add(new Day(15, 10, 2002), 172088D);
        timeseries.add(new Day(16, 10, 2002), 151267D);
        timeseries.add(new Day(17, 10, 2002), 222680D);
        timeseries.add(new Day(18, 10, 2002), 127019D);
        timeseries.add(new Day(21, 10, 2002), 118226D);
        timeseries.add(new Day(22, 10, 2002), 183031D);
        timeseries.add(new Day(23, 10, 2002), 221005D);
        timeseries.add(new Day(24, 10, 2002), 121333D);
        timeseries.add(new Day(25, 10, 2002), 138179D);
        timeseries.add(new Day(28, 10, 2002), 162012D);
        timeseries.add(new Day(29, 10, 2002), 237355D);
        timeseries.add(new Day(30, 10, 2002), 161650D);
        timeseries.add(new Day(31, 10, 2002), 207569D);
        return new TimeSeriesCollection(timeseries);
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart();
        return new ChartPanel(jfreechart);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboCategorie;
    private javax.swing.JTextField NbInvt;
    private javax.swing.JTable TableArticle;
    private javax.swing.JTable TableJournal;
    private javax.swing.JButton btnInventaire;
    private javax.swing.JButton btnSupprInventaire;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel chartPanel;
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nomArticle;
    private javax.swing.JButton printbtn;
    private javax.swing.JLabel txtbackground3;
    private javax.swing.JTextField txtrechercher1Article;
    private javax.swing.JComboBox<String> typeCombo;
    // End of variables declaration//GEN-END:variables
}
