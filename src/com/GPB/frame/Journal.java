/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

//import com.mysql.jdbc.ResultSetMetaData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.jfree.data.general.DefaultPieDataset;
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
    ResultSet rs6 = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    PreparedStatement ps3 = null;
    PreparedStatement ps4 = null;
    PreparedStatement ps5 = null;
    PreparedStatement ps6 = null;
    
    static String test;
    static String test2;
    static String test3;
    static String article;
    static String section;
    static int print;
    private ChartPanel chartPanel;
    

    /**
     * Creates new form Examen
     *
     * @throws java.sql.SQLException
     */
    public Journal() throws SQLException {

        initComponents();
        remove_title_bar();
        affichagePie();
        if(LoginGUI.role.equals("USER")){
            jLabel4.setVisible(false);
            NbInvt.setVisible(false);
            btnInventaire.setVisible(false);
            btnSupprInventaire.setVisible(false);
        }
        labelSection.setVisible(false);
        labelFournisseur.setVisible(false);
        ComboSection.setVisible(false);
        ComboFournisseur.setVisible(false);
        
        AffichageArticle();
        AffichageJournal();
        remplirComboCategorie();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbachground2.setIcon(img);
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        //txtbackground3.setIcon(img2);
        txtrechercher1Article.setText("Taper Nom Article");
        
        
                
        //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //Long millis = System.currentTimeMillis();
        //Date date3 = new Date(millis);
        //dateFormat.format(date1.getDate());
        //date1.setDate(date3);
        //date2.setDate(date3);
        
        date1.setDate(null);
        date2.setDate(null);
        
        
        /*PriceVolumeDemo1 pricevolumedemo1 = new PriceVolumeDemo1("Price Volume Chart Demo");
        pricevolumedemo1.pack();*/
        //this.chartPanel.removeAll();
        //this.chartPanel.repaint();
        
        /*PriceVolumeDemo1 pricevolumedemo1 = new PriceVolumeDemo1("test");
        pricevolumedemo1.pack();
        RefineryUtilities.centerFrameOnScreen(pricevolumedemo1);
        pricevolumedemo1.setVisible(true);*/
        
        
        //JFreeChart jfreechart = createChart();
        //ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, false, true);
        //chartpanel.setPreferredSize(new Dimension(300, 180));
        
        //chartPanel.setLayout(new BorderLayout());
        //chartPanel.add(chartpanel, BorderLayout.CENTER);
        //chartPanel.show();
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
        /*if (conn != null) {
            try {
                conn.close();
                System.out.println("Connexion fermée");
            } catch (SQLException e) {
            }
        }*/
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
    
    private void affichagePie() {
        conn = ConexionBD.Conexion();
        this.graph.removeAll();
        this.graph.repaint();
        //this.jPanel2.removeAll();
        //this.jPanel2.repaint();
        try {
            String requete3 = "select NomArticle as 'Article',QteStock as 'Quantité',MontantStock as 'Montant'  from  article";
            ps3 = conn.prepareStatement(requete3);
            rs3 = ps3.executeQuery();
            //Table3.setModel(DbUtils.resultSetToTableModel(rs3));

            ResultSetMetaData a = (ResultSetMetaData) rs3.getMetaData();
            int nbColonnes = a.getColumnCount();

            DefaultPieDataset data = new DefaultPieDataset();
            //DefaultPieDataset data2 = new DefaultPieDataset();

            while (rs3.next()) {

                for (int i = 1; i <= nbColonnes; i++) {
                    String laValeur = rs3.getString(i);
                    System.out.println(laValeur + " " + a.getColumnName(i));
                }

                String NomBanque = rs3.getString(1);
                System.out.println("Article = " + NomBanque);
                String EffectifClient = rs3.getString(2);
                System.out.println("Quantité = " + EffectifClient);
                String TotalPret = rs3.getString(3);
                System.out.println("Montant = " + TotalPret);

                data.setValue(NomBanque, Long.parseLong(EffectifClient));
                //data2.setValue(NomBanque, Long.parseLong(TotalPret));
            }

            boolean URLs = false;
            boolean legend = false;
            boolean tooltips = false;
            JFreeChart chart = ChartFactory.createPieChart("Effectif Article", data, true, true, false);
            //JFreeChart chart2 = ChartFactory.createPieChart("Effectif Montant par Banque", data2, true, true, false);
            chartPanel = new ChartPanel(chart);
            //chartPanel2 = new ChartPanel(chart2);
            graph.setLayout(new BorderLayout());
            graph.add(chartPanel, BorderLayout.CENTER);
            graph.show();
            //jPanel2.setLayout(new BorderLayout());
            //jPanel2.add(chartPanel2, BorderLayout.CENTER);
            //jPanel2.show();

        } catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        } finally {

            try {
                ps3.close();
                rs3.close();
                CloseConnexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }
        
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
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle ORDER BY DateJournal asc";
                
                /*requete = "select distinct Journal.DateJournal as 'Date Journal' ,Journal.DateMouvement as 'Date Mouvement' ,Journal.TypeMouvement as 'Type' ,Journal.Action as 'Action' ,Journal.NumMouvement as 'Ref', Fournisseur.NomFournisseur as 'Fournisseur', section.NomSection as 'Section' , article.NomArticle as 'Article' ,Journal.QteMouvement , Journal.PUMouvement, Journal.MontantMouvement, Journal.QteStock, Journal.MontantStock,Journal.QteInventaire,Journal.Difference from Journal, article, Fournisseur, section WHERE\n"
                    + "(article.NumArticle=Journal.NumArticle and Fournisseur.NumFournisseur=Journal.Fournisseur and section.idSection=Journal.Section) ORDER BY DateJournal asc";*/
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle ORDER BY DateMouvement asc";
            }
            
            System.out.println(requete);
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
        print=1;
    }
    
    private void AffichageJournal2date(String date1, String date2) {
        conn = ConexionBD.Conexion();
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
                
                /*requete = "select distinct Journal.DateJournal as 'Date Journal' ,Journal.DateMouvement as 'Date Mouvement' ,Journal.TypeMouvement as 'Type' ,Journal.Action as 'Action' ,Journal.NumMouvement as 'Ref', Fournisseur.NomFournisseur as 'Fournisseur', section.NomSection as 'Section' , article.NomArticle as 'Article' ,Journal.QteMouvement , Journal.PUMouvement, Journal.MontantMouvement, Journal.QteStock, Journal.MontantStock,Journal.QteInventaire,Journal.Difference from Journal, article, Fournisseur, section WHERE\n"
                    + "(article.NumArticle=Journal.NumArticle and Fournisseur.NumFournisseur=Journal.Fournisseur and section.idSection=Journal.Section) ORDER BY DateJournal asc";*/
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
            
            System.out.println(requete);
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
        print=2;
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
    
    
    private void AffichageJournalArticle(String nomArticle) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where A.NomArticle like '" + nomArticle + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where A.NomArticle like '" + nomArticle + "' ORDER BY DateMouvement asc";
            }
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

        print=3;
    }
    
    private void AffichageJournalArticle2date(String nomArticle, String date1, String date2) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where A.NomArticle like '" + nomArticle + "' and DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where A.NomArticle like '" + nomArticle + "' and DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
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
        print=4;
    }
   
    
    private void AffichageJournalInventaire2date(String date1, String date2) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'INVENTAIRE' and J.DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'INVENTAIRE' and J.DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
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
        print=5;
    }
    
    private void AffichageJournalEntree2date(String date1, String date2) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and J.DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and J.DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
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
        print=6;
    }
    
    private void AffichageJournalSortie2date(String date1, String date2) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' and J.DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' and J.DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
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
        print=7;
    }
    
    private void AffichageJournalTypeArticle2date(String type, String nomArticle, String date1, String date2) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like '" + type + "' and A.NomArticle like '" + nomArticle + "' and J.DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like '" + type + "' and A.NomArticle like '" + nomArticle + "' and J.DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
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
        print=8;
    }
    
    private void AffichageJournalEntreeFournisseur2date(String acteur, String date1, String date2) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and F.NumFournisseur like '" + acteur + "' and J.DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and F.NumFournisseur like '" + acteur + "' and J.DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
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
        print=9;
    }
    
    private void AffichageJournalSortieSection2date(String acteur, String date1, String date2) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' and S.idSection like '" + acteur + "' and J.DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' and S.idSection like '" + acteur + "' and J.DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
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
        print=10;
    }
    
    private void AffichageJournalEntreeFournisseurArticle2date(String acteur, String nomArticle, String date1, String date2) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and A.NomArticle like '" + nomArticle + "' and F.NumFournisseur like '" + acteur + "' and J.DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and A.NumArticle like '" + nomArticle + "' and F.NumFournisseur like '" + acteur + "' and DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
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
        print=11;
    }
    
    private void AffichageJournalSortieSectionArticle2date(String acteur, String nomArticle, String date1, String date2) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' and S.idSection like '" + acteur + "' and A.NomArticle like '" + nomArticle + "' and J.DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' and S.idSection like '" + acteur + "' and A.NomArticle like '" + nomArticle + "' and J.DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
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
        print=12;
    }
    
    private void AffichageJournalInventaireArticle2date(String nomArticle, String date1, String date2) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Article A on A.NumArticle=J.NumArticle where A.NomArticle like '" + nomArticle + "' and J.TypeMouvement like 'INVENTAIRE' and J.DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Article A on A.NumArticle=J.NumArticle where A.NomArticle like '" + nomArticle + "' and J.TypeMouvement like 'INVENTAIRE' and J.DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
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
        print=13;
    }
    
    private void AffichageJournalEntreeArticle2date(String nomArticle, String date1, String date2) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where A.NomArticle like '" + nomArticle + "' and J.TypeMouvement like 'ENTREE' and J.DateJournal between '" + date1 + "' and '" + date2 + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where A.NomArticle like '" + nomArticle + "' and J.TypeMouvement like 'ENTREE' and J.DateMouvement between '" + date1 + "' and '" + date2 + "' ORDER BY DateMouvement asc";
            }
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
        print=14;
    }
    
    private void AffichageJournalFiltre(String type) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like '" + type + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like '" + type + "' ORDER BY DateMouvement asc";
            }
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
        print=15;
    }
    
    private void AffichageJournalEntreeFournisseur(String acteur) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and F.NumFournisseur like '" + acteur + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and F.NumFournisseur like '" + acteur + "' ORDER BY DateMouvement asc";
            }
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
        print=16;
    }
    
    private void AffichageJournalSortieSection(String acteur) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' and S.idSection like '" + acteur + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where   J.TypeMouvement like 'SORTIE' and S.idSection like '" + acteur + "' ORDER BY DateMouvement asc";
            }
            ps2 = conn.prepareStatement(requete);
            rs2 = ps2.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs2));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println("AffichageJournalSortieSection: "+e);
        } finally {
            CloseRsPs2();
        }
        tabelJournal();
        print=17;
    }
    
    private void AffichageJournalSortie() {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' ORDER BY DateMouvement asc";
            }
            ps2 = conn.prepareStatement(requete);
            rs2 = ps2.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs2));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println("AffichageJournalSortieSection: "+e);
        } finally {
            CloseRsPs2();
        }
        tabelJournal();
        print=18;
    }
    
    private void AffichageJournalEntree() {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' ORDER BY DateMouvement asc";
            }
            ps2 = conn.prepareStatement(requete);
            rs2 = ps2.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs2));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println("AffichageJournalSortieSection: "+e);
        } finally {
            CloseRsPs2();
        }
        tabelJournal();
        print=19;
    }
    
    private void AffichageJournalFiltreArticle(String type, String article) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like '" + type + "' and A.NomArticle like '" + article + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock,"
                        + "J.QteInventaire,"
                        + "J.Difference "
                        + "from Journal J "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like '" + type + "' and A.NomArticle like '" + article + "' ORDER BY DateMouvement asc";
            }
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
        print=20;
    }
    
    private void AffichageJournalSortieSectionArticle(String acteur, String article) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' and S.idSection like '" + acteur + "' and A.NomArticle like '" + article + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' and S.idSection like '" + acteur + "' and A.NomArticle like '" + article + "' ORDER BY DateMouvement asc";
            }
            ps2 = conn.prepareStatement(requete);
            rs2 = ps2.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs2));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs2();
        }
        tabelJournal();
        print=21;
    }
    
    private void AffichageJournalEntreeFournisseurArticle(String acteur, String article) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and F.NumFournisseur like '" + acteur + "' and A.NomArticle like '" + article + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and F.NumFournisseur like '" + acteur + "' and A.NomArticle like '" + article + "' ORDER BY DateMouvement asc";
            }
            ps2 = conn.prepareStatement(requete);
            rs2 = ps2.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs2));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs2();
        }
        tabelJournal();
        print=22;
    }
    
    private void AffichageJournalEntreeArticle(String article) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and A.NomArticle like '" + article + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "F.NomFournisseur as 'Fournisseur', "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join Fournisseur F on F.NumFournisseur=J.Fournisseur "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'ENTREE' and A.NomArticle like '" + article + "' ORDER BY DateMouvement asc";
            }
            ps2 = conn.prepareStatement(requete);
            rs2 = ps2.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs2));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs2();
        }
        tabelJournal();
        print=23;
    }
    
    private void AffichageJournalSortieArticle(String article) {
        try {
            String requete = null;
            if(dateCombo.getSelectedItem().toString().equals("DateJournal")){
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' and A.NomArticle like '" + article + "' ORDER BY DateJournal asc";
            }else{
                requete = "select distinct J.DateJournal as 'Date Journal' ,"
                        + "J.DateMouvement as 'Date Mouvement' ,"
                        + "J.TypeMouvement as 'Type' ,"
                        + "J.Action as 'Action' ,"
                        + "J.NumMouvement as 'Ref', "
                        + "S.NomSection as 'Section' , "
                        + "A.NomArticle as 'Article' ,"
                        + "J.QteMouvement , "
                        + "J.PUMouvement, "
                        + "J.MontantMouvement, "
                        + "J.QteStock, "
                        + "J.MontantStock "
                        + "from Journal J "
                        + "left join section S on S.idSection=J.Section "
                        + "left join Article A on A.NumArticle=J.NumArticle where J.TypeMouvement like 'SORTIE' and A.NomArticle like '" + article + "' ORDER BY DateMouvement asc";
            }
            ps2 = conn.prepareStatement(requete);
            rs2 = ps2.executeQuery();
            System.out.println(requete);
            TableJournal.setModel(DbUtils.resultSetToTableModel(rs2));
            ajusterTableJournal();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            CloseRsPs2();
        }
        tabelJournal();
        print=24;
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
        jLabel7 = new javax.swing.JLabel();
        dateCombo = new javax.swing.JComboBox<>();
        labelSection = new javax.swing.JLabel();
        ComboSection = new javax.swing.JComboBox<>();
        labelFournisseur = new javax.swing.JLabel();
        ComboFournisseur = new javax.swing.JComboBox<>();
        graph = new javax.swing.JPanel();

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
        jScrollPane2.setBounds(10, 120, 520, 220);

        jLabel2.setText("NomArticle : ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(540, 110, 80, 14);

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
        nomArticle.setBounds(610, 110, 190, 14);

        btnInventaire.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnInventaire.setText("Inventaire");
        btnInventaire.setBorder(null);
        btnInventaire.setBorderPainted(false);
        btnInventaire.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        btnInventaire.setBounds(540, 200, 118, 30);

        btnSupprInventaire.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSupprInventaire.setText("Supprimer");
        btnSupprInventaire.setBorder(null);
        btnSupprInventaire.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        btnSupprInventaire.setBounds(680, 200, 118, 30);

        jLabel4.setText("Nombre inventé :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(540, 140, 120, 14);

        NbInvt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NbInvtActionPerformed(evt);
            }
        });
        getContentPane().add(NbInvt);
        NbInvt.setBounds(540, 160, 120, 20);

        printbtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        printbtn.setText("Imprimer");
        printbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        printbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        printbtn.setBounds(680, 150, 120, 30);

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
        ComboCategorie.setBounds(320, 90, 200, 20);

        jLabel5.setText("Catégorie :");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(320, 70, 200, 14);

        typeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "ENTREE", "SORTIE", "INVENTAIRE" }));
        typeCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                typeComboItemStateChanged(evt);
            }
        });
        getContentPane().add(typeCombo);
        typeCombo.setBounds(540, 270, 260, 20);

        jLabel6.setText("Filtre par Type :");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(540, 250, 110, 14);

        jLabel7.setText("Filtre par Date :");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(440, 60, 110, 14);

        dateCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DateJournal", "DateMouvement" }));
        dateCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dateComboItemStateChanged(evt);
            }
        });
        dateCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboActionPerformed(evt);
            }
        });
        getContentPane().add(dateCombo);
        dateCombo.setBounds(520, 60, 120, 20);

        labelSection.setText("Section : ");
        getContentPane().add(labelSection);
        labelSection.setBounds(540, 300, 120, 14);

        ComboSection.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboSectionItemStateChanged(evt);
            }
        });
        getContentPane().add(ComboSection);
        ComboSection.setBounds(540, 320, 120, 20);

        labelFournisseur.setText("Fournissseur : ");
        getContentPane().add(labelFournisseur);
        labelFournisseur.setBounds(680, 300, 120, 14);

        ComboFournisseur.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboFournisseurItemStateChanged(evt);
            }
        });
        getContentPane().add(ComboFournisseur);
        ComboFournisseur.setBounds(680, 320, 120, 20);

        javax.swing.GroupLayout graphLayout = new javax.swing.GroupLayout(graph);
        graph.setLayout(graphLayout);
        graphLayout.setHorizontalGroup(
            graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );
        graphLayout.setVerticalGroup(
            graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        getContentPane().add(graph);
        graph.setBounds(820, 110, 370, 230);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableJournalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableJournalMouseClicked

    }//GEN-LAST:event_TableJournalMouseClicked

    private void TableJournalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableJournalMouseEntered

    }//GEN-LAST:event_TableJournalMouseEntered

    private void TableJournalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableJournalMouseReleased
        int row = TableJournal.getSelectedRow();
        Journal.test = (TableJournal.getModel().getValueAt(row, 2).toString());
        
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
        AffichageJournalArticle(test);
        CloseRsPs1();
        CloseRsPs2();
        CloseConnexion();
        
        NbInvt.setText("");
        typeCombo.setSelectedItem(" ");
        date1.setDate(null);
        date2.setDate(null);
        
        btnInventaire.setEnabled(true);
        btnSupprInventaire.setEnabled(false);
    }//GEN-LAST:event_TableArticleMouseReleased

    private void TableArticleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableArticleKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableArticleKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        conn = ConexionBD.Conexion();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String Date1 = dateFormat.format(date1.getDate());
        String Date2 = dateFormat.format(date2.getDate());
        String num = nomArticle.getText();
        if(Date1.hashCode()>Date2.hashCode()){
            JOptionPane.showMessageDialog(null, "Date1 inférieur à Date2");
        }else{
            if(num.equals("")){
                if(typeCombo.getSelectedItem().toString().equals(" ")){
                   AffichageJournal2date(Date1,Date2); 
                } else if(!(typeCombo.getSelectedItem().toString().equals(" "))){
                    if(typeCombo.getSelectedItem().toString().equals("INVENTAIRE")){
                        AffichageJournalInventaire2date(Date1,Date2);
                    }else if(typeCombo.getSelectedItem().toString().equals("ENTREE")){
                        if(ComboFournisseur.getSelectedItem().toString().equals(" ")){
                            AffichageJournalEntree2date(Date1,Date2);
                        }else{
                            try {
                                String requete = "select * from Fournisseur where NomFournisseur LIKE ?";
                                ps6 = conn.prepareStatement(requete);
                                System.out.println(ComboFournisseur.getSelectedItem().toString());
                                ps6.setString(1, "%" + ComboFournisseur.getSelectedItem().toString() + "%");
                                rs6 = ps6.executeQuery();
                                section = rs6.getString("NumFournisseur");
                            } catch (SQLException e) {
                                System.out.println("ComboFournisseurItemStateChanged: "+e);
                            } finally {
                                try {
                                    ps6.close();
                                    rs6.close();
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(null, "erreur BD");
                                }
                            }
                            AffichageJournalEntreeFournisseur2date(section,Date1,Date2);
                        }
                    }else{
                        if(ComboSection.getSelectedItem().toString().equals(" ")){
                            AffichageJournalSortie2date(Date1,Date2);
                        }else{
                            try {
                                String requete = "select * from section where NomSection LIKE ?";
                                ps6 = conn.prepareStatement(requete);
                                System.out.println(ComboSection.getSelectedItem().toString());
                                ps6.setString(1, "%" + ComboSection.getSelectedItem().toString() + "%");
                                rs6 = ps6.executeQuery();
                                section = rs6.getString("idSection");
                            } catch (SQLException e) {
                                System.out.println("ComboSectionItemStateChanged: "+e);
                            } finally {
                                try {
                                    ps6.close();
                                    rs6.close();
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(null, "erreur BD");
                                }
                            }
                            AffichageJournalSortieSection2date(section,Date1,Date2);
                        }
                    } 
                } 
            }else{
                if(typeCombo.getSelectedItem().toString().equals(" ")){
                   AffichageJournalArticle2date(num,Date1,Date2); 
                } else if(!(typeCombo.getSelectedItem().toString().equals(" "))){
                    if(typeCombo.getSelectedItem().toString().equals("INVENTAIRE")){
                        AffichageJournalInventaireArticle2date(num,Date1,Date2);
                    }else if(typeCombo.getSelectedItem().toString().equals("ENTREE")){
                        if(ComboFournisseur.getSelectedItem().toString().equals(" ")){
                            AffichageJournalEntreeArticle2date(num,Date1,Date2);
                        }else{
                            try {
                                String requete = "select * from Fournisseur where NomFournisseur LIKE ?";
                                ps6 = conn.prepareStatement(requete);
                                System.out.println(ComboFournisseur.getSelectedItem().toString());
                                ps6.setString(1, "%" + ComboFournisseur.getSelectedItem().toString() + "%");
                                rs6 = ps6.executeQuery();
                                section = rs6.getString("NumFournisseur");
                            } catch (SQLException e) {
                                System.out.println("ComboFournisseurItemStateChanged: "+e);
                            } finally {
                                try {
                                    ps6.close();
                                    rs6.close();
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(null, "erreur BD");
                                }
                            }
                            AffichageJournalEntreeFournisseurArticle2date(section,num,Date1,Date2);
                        }
                    }else{
                        if(ComboSection.getSelectedItem().toString().equals(" ")){
                            AffichageJournalTypeArticle2date(typeCombo.getSelectedItem().toString(),num,Date1,Date2);
                        }else{
                            try {
                                String requete = "select * from section where NomSection LIKE ?";
                                ps6 = conn.prepareStatement(requete);
                                System.out.println(ComboSection.getSelectedItem().toString());
                                ps6.setString(1, "%" + ComboSection.getSelectedItem().toString() + "%");
                                rs6 = ps6.executeQuery();
                                section = rs6.getString("idSection");
                            } catch (SQLException e) {
                                System.out.println("ComboSectionItemStateChanged: "+e);
                            } finally {
                                try {
                                    ps6.close();
                                    rs6.close();
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(null, "erreur BD");
                                }
                            }
                            AffichageJournalSortieSectionArticle2date(ComboSection.getSelectedItem().toString(),num,Date1,Date2);
                        }
                    } 
                }
            }
        }
        
        
        /*
        
        if(num.equals("") && typeCombo.getSelectedItem().toString().equals(" ")){
            AffichageJournal2date(Date1,Date2);
        }else if(num.equals("") && !(typeCombo.getSelectedItem().toString().equals(" "))){
            AffichageJournal2dateType(typeCombo.getSelectedItem().toString(),Date1,Date2);
        }else if(!(num.equals("")) && typeCombo.getSelectedItem().toString().equals(" ")){
            AffichageJournal2dateArticle(nomArticle.getText(),Date1,Date2);
        }else {
            AffichageJournal2dateArticleType(typeCombo.getSelectedItem().toString(),nomArticle.getText(),Date1,Date2);
        }*/
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

        if(print==1){
            ImpressionJournal();
        }else if(print==2){
            ImpressionJournal2date();
        }else if(print==3){
            ImpressionJournalArticle();
        }else if(print==4){
            ImpressionJournalArticle2date();
        }else if(print==5){
            ImpressionJournalType2date();
        }else if(print==6){
            ImpressionJournalType2date();
        }else if(print==7){
            ImpressionJournalType2date();
        }else if(print==8){
            ImpressionJournalArticleType2date();
        }else if(print==9){
            ImpressionJournalTypeFournisseur2date();
        }else if(print==10){
            ImpressionJournalTypeSection2date();
        }else if(print==11){
            ImpressionJournalArticleTypeFournisseur2date();
        }else if(print==12){
            ImpressionJournalArticleTypeSection2date();
        }else if(print==13){
            ImpressionJournalArticleType2date();
        }else if(print==14){
            ImpressionJournalArticleType2date();
        }else if(print==15){
            ImpressionJournalType();
        }else if(print==16){
            ImpressionJournalTypeFournisseur();
        }else if(print==17){
            ImpressionJournalTypeFournisseur();
        }else if(print==18){
            ImpressionJournalType();
        }else if(print==19){
            ImpressionJournalType();
        }else if(print==20){
            ImpressionJournalArticleType();
        }else if(print==21){
            ImpressionJournalArticleTypeSection();
        }else if(print==22){
            ImpressionJournalArticleTypeFournisseur();
        }else if(print==23){
            ImpressionJournalArticleType();
        }else if(print==24){
            ImpressionJournalArticleType();
        }
        
        /*conn = ConexionBD.Conexion();
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);*/
        /*
        if(print==1){
            MessageFormat header = new MessageFormat("Historique de Mouvement le "+dateFormat1.format(date3));
            MessageFormat footer = new MessageFormat("Page{0,number,integer}");
            AffichageJournalImpression();
            try {
                TableJournal.print(JTable.PrintMode.FIT_WIDTH, header, footer);

            } catch (java.awt.print.PrinterException e) {
                System.err.format("Erreur d'impression ", e.getMessage());
            }
            AffichageJournalArticle();
        }else if(print==2){
            MessageFormat header = new MessageFormat("Historique de Mouvement le "+dateFormat1.format(date3));
            MessageFormat footer = new MessageFormat("Page{0,number,integer}");
            AffichageJournalImpression();
            try {
                TableJournal.print(JTable.PrintMode.FIT_WIDTH, header, footer);

            } catch (java.awt.print.PrinterException e) {
                System.err.format("Erreur d'impression ", e.getMessage());
            }
            AffichageJournalArticle();
        }else
        */
        /*
        if(nomArticle.getText().equals("")){
            MessageFormat header = new MessageFormat("Historique des articles ,le "+dateFormat1.format(date3));
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
            MessageFormat header = new MessageFormat("Historique de "+article+" du "+dateFormat1.format(date3));
            MessageFormat footer = new MessageFormat("Page{0,number,integer}");
            try {
                TableJournal.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            } catch (java.awt.print.PrinterException e) {
                System.err.format("Erreur d'impression ", e.getMessage());
            }
        }*/

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
    
    public void ImpressionJournaltemp(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[7];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Article : "+nomArticle.getText());
        header[5] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());
        header[4] = new MessageFormat("                  Fournisseur : "+ComboFournisseur.getSelectedItem());
        header[4] = new MessageFormat("                  Section : "+ComboSection.getSelectedItem());
        header[6] = new MessageFormat("                  Date du "+date1.getDateFormatString()+" au "+date2.getDateFormatString());

        MessageFormat[] footer = new MessageFormat[2];
        footer[0] = new MessageFormat("");
        footer[1] = new MessageFormat("");
        job.setCopies(2);
        job.setJobName("BondEntree");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournal(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[3];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");

        MessageFormat[] footer = new MessageFormat[2];
        footer[0] = new MessageFormat("");
        footer[1] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalArticle(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[4];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Article : "+nomArticle.getText());

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalType(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[4];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalTypeFournisseur(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[5];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());
        header[4] = new MessageFormat("                  Fournisseur : "+ComboFournisseur.getSelectedItem());

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalTypeSection(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[5];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());
        header[4] = new MessageFormat("                  Section : "+ComboSection.getSelectedItem());

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournal2date(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[4];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Date du "+dateFormat1.format(date1.getDate())+" au "+dateFormat1.format(date2.getDate()));

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalArticleType(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[5];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Article : "+nomArticle.getText());
        header[4] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalArticleTypeFournisseur(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[6];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Article : "+nomArticle.getText());
        header[4] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());
        header[5] = new MessageFormat("                  Fournisseur : "+ComboFournisseur.getSelectedItem());

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalArticleTypeSection(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[6];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Article : "+nomArticle.getText());
        header[4] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());
        header[5] = new MessageFormat("                  Section : "+ComboSection.getSelectedItem());

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalArticle2date(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[5];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Article : "+nomArticle.getText());
        header[4] = new MessageFormat("                  Date du "+dateFormat1.format(date1.getDate())+" au "+dateFormat1.format(date2.getDate()));

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalArticleType2date(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[6];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Article : "+nomArticle.getText());
        header[4] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());
        header[5] = new MessageFormat("                  Date du "+dateFormat1.format(date1.getDate())+" au "+dateFormat1.format(date2.getDate()));

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalArticleTypeFournisseur2date(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[7];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Article : "+nomArticle.getText());
        header[4] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());
        header[5] = new MessageFormat("                  Fournisseur : "+ComboFournisseur.getSelectedItem());
        header[6] = new MessageFormat("                  Date du "+dateFormat1.format(date1.getDate())+" au "+dateFormat1.format(date2.getDate()));

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalArticleTypeSection2date(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[7];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Article : "+nomArticle.getText());
        header[4] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());
        header[5] = new MessageFormat("                  Section : "+ComboSection.getSelectedItem());
        header[6] = new MessageFormat("                  Date du "+dateFormat1.format(date1.getDate())+" au "+dateFormat1.format(date2.getDate()));

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalType2date(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[5];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());
        header[4] = new MessageFormat("                  Date du "+dateFormat1.format(date1.getDate())+" au "+dateFormat1.format(date2.getDate()));

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalTypeFournisseur2date(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[6];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());
        header[4] = new MessageFormat("                  Fournisseur : "+ComboFournisseur.getSelectedItem());
        header[5] = new MessageFormat("                  Date du "+dateFormat1.format(date1.getDate())+" au "+dateFormat1.format(date2.getDate()));

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void ImpressionJournalTypeSection2date(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd MMMM yyyy");
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        PrinterJob job = PrinterJob.getPrinterJob();
        MessageFormat[] header = new MessageFormat[5];
        header[0] = new MessageFormat("");
        header[1] = new MessageFormat("");
        header[2] = new MessageFormat("                                                HISTORIQUE du "+dateFormat1.format(date3)+"                                          ");
        header[3] = new MessageFormat("                  Type : "+typeCombo.getSelectedItem());
        header[4] = new MessageFormat("                  Section : "+ComboSection.getSelectedItem());
        header[5] = new MessageFormat("                  Date du "+dateFormat1.format(date1.getDate())+" au "+dateFormat1.format(date2.getDate()));

        MessageFormat[] footer = new MessageFormat[1];
        footer[0] = new MessageFormat("");
        job.setJobName("Historique");
        job.setPrintable(new MyTablePrintable(TableJournal, JTable.PrintMode.FIT_WIDTH, header, footer));

        if (job.printDialog())
        try {
            System.out.println("Calling PrintJob.print()");
            job.print();
            System.out.println("End PrintJob.print()");
        }
        catch (PrinterException pe) {
            System.out.println("Error printing: " + pe);
        }
    }
    
    public void remplirComboCategorie() {
        
        ComboCategorie.removeAllItems();
        ComboCategorie.addItem("");
        String requet = "select * from categorie";
        
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
    
    public void remplirComboSection(){
        conn = ConexionBD.Conexion();
        ComboSection.addItem(" ");
        String requet = " select * from section";
        try {
            ps4 = conn.prepareStatement(requet);
            rs4 = ps4.executeQuery();
            ComboSection.removeAllItems();
            while (rs4.next()){
                String nom = rs4.getString("NomSection").toString();
                ComboSection.addItem(nom);
                ComboSection.setSelectedItem(" ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseRsPs4();
            CloseConnexion();
        }
    }
    
    public void remplirComboFournisseur() {
        
        conn = ConexionBD.Conexion();
        ComboFournisseur.removeAllItems();
        ComboFournisseur.addItem(" ");
        String requet = " select * from  Fournisseur";
        
        try {
            ps4 = conn.prepareStatement(requet);
            rs4 = ps4.executeQuery();

            while (rs4.next()) {
                String nom = rs4.getString("NomFournisseur").toString();
                ComboFournisseur.addItem(nom);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseRsPs4();
            CloseConnexion();
        }
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
        
        date1.setDate(null);
        date2.setDate(null);
        
        NbInvt.setText("");
        nomArticle.setText("");
        
        btnInventaire.setEnabled(false);
        btnSupprInventaire.setEnabled(false);
        
        labelSection.setVisible(false);
        ComboSection.setVisible(false);
        labelFournisseur.setVisible(false);
        ComboFournisseur.setVisible(false);
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
            tabelArticle();
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
                String requete5 = "insert into Journal (DateJournal,DateMouvement,TypeMouvement,Action,NumArticle,QteStock,QteInventaire,Difference) values (?,?,?,?,?,?,?,?)";
                ps5 = conn.prepareStatement(requete5);
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Long millis = System.currentTimeMillis();
                Date date3 = new Date(millis);
                ps5.setString(1, dateFormat.format(date3));
                ps5.setString(2, dateFormat.format(date3));
                ps5.setString(3, "INVENTAIRE");
                ps5.setString(4, "OK");
                ps5.setString(5, num);
                ps5.setString(6, ancienQte);
                ps5.setString(7, NbInvt.getText());
                ps5.setString(8, "0");
                ps5.execute();
                
                int row = TableArticle.getSelectedRow();
                Journal.test = (TableArticle.getModel().getValueAt(row, 0).toString());
                nomArticle.setText(test);
                AffichageJournalArticle(test);
                CloseRsPs5();
            }else{
                String requete5 = "insert into Journal (DateJournal,DateMouvement,TypeMouvement,Action,NumArticle,QteStock,QteInventaire,Difference) values (?,?,?,?,?,?,?,?)";
                ps5 = conn.prepareStatement(requete5);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Long millis = System.currentTimeMillis();
                Date date3 = new Date(millis);
                ps5.setString(1, dateFormat.format(date3));
                ps5.setString(2, dateFormat.format(date3));
                ps5.setString(3, "INVENTAIRE");
                ps5.setString(4, "NON OK");
                ps5.setString(5, num);
                ps5.setString(6, ancienQte);
                ps5.setString(7, NbInvt.getText());
                int resultat = Integer.parseInt(NbInvt.getText())-Integer.parseInt(ancienQte);
                ps5.setString(8, String.valueOf(resultat));
                ps5.execute();

                int row = TableArticle.getSelectedRow();
                Journal.test = (TableArticle.getModel().getValueAt(row, 0).toString());
                nomArticle.setText(test);
                AffichageJournalArticle(test);
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
            Journal.test3 = (TableJournal.getModel().getValueAt(row, 6).toString());
            
            String requete = "select * from  article where NomArticle = '" + test3 + "'";
            ps2 = conn.prepareStatement(requete);
            rs2 = ps2.executeQuery();
            String num = rs2.getString("NumArticle");
            
            String type = "INVENTAIRE";
            String requet = "delete from Journal where DateJournal = '" + test2 + "' and NumArticle = '" + num + "' and TypeMouvement = '" + type + "'";
            ps = conn.prepareStatement(requet);
            ps.execute();
            
            if(nomArticle.getText().equals("")){
                AffichageJournal();
            }else{
                AffichageJournalArticle(test3);
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
      
        String num = nomArticle.getText();
        if(typeCombo.getSelectedItem().toString().equals(" ")){
            labelFournisseur.setVisible(false);
            ComboFournisseur.setVisible(false);
            labelSection.setVisible(false);
            ComboSection.setVisible(false);
            if(num.equals("")){
                AffichageJournal();
            }else{
                AffichageJournalArticle(num);
            }
        }else{
            if(typeCombo.getSelectedItem().toString().equals("ENTREE")){
                labelFournisseur.setVisible(true);
                ComboFournisseur.setVisible(true);
                labelSection.setVisible(false);
                ComboSection.setVisible(false);
                remplirComboFournisseur();
                ComboFournisseur.addItem(" ");
                ComboFournisseur.setSelectedItem(" ");
                /* if(num.equals("")){
                    AffichageJournalEntree();
                }else{
                    AffichageJournalEntreeArticle(num);
                }*/
            }else if(typeCombo.getSelectedItem().toString().equals("SORTIE")){
                labelSection.setVisible(true);
                ComboSection.setVisible(true);
                labelFournisseur.setVisible(false);
                ComboFournisseur.setVisible(false);
                remplirComboSection();
                ComboSection.addItem(" ");
                ComboSection.setSelectedItem(" ");
                
                /*if(num.equals("")){
                    AffichageJournalEntree();
                }else{
                    AffichageJournalEntreeArticle(num);
                }*/
            }else if(typeCombo.getSelectedItem().toString().equals("INVENTAIRE")){
                labelSection.setVisible(false);
                ComboSection.setVisible(false);
                labelFournisseur.setVisible(false);
                ComboFournisseur.setVisible(false);
                
                if(num.equals("")){
                    AffichageJournalFiltre(typeCombo.getSelectedItem().toString());
                }else{
                    AffichageJournalFiltreArticle(typeCombo.getSelectedItem().toString(),nomArticle.getText());
                }
            }
        }
        //dateCombo.setSelectedItem("DateJournal");
        btnInventaire.setEnabled(false);
        btnSupprInventaire.setEnabled(false);
        
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        date1.setDate(date3);
        date2.setDate(date3);
        date1.setDate(null);
        date2.setDate(null);
    }//GEN-LAST:event_typeComboItemStateChanged

    private void dateComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dateComboItemStateChanged
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
        
        date1.setDate(null);
        date2.setDate(null);
        
        NbInvt.setText("");
        nomArticle.setText("");
        
        btnInventaire.setEnabled(false);
        btnSupprInventaire.setEnabled(false);
    }//GEN-LAST:event_dateComboItemStateChanged

    private void dateComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboActionPerformed

    private void ComboSectionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboSectionItemStateChanged
        conn = ConexionBD.Conexion();
        //String num1 = numArticle.getText();
        if(ComboSection.getItemCount() == 0){
            
        }else{
            
            if(ComboSection.getSelectedItem().toString().equals(" ")){
                if(nomArticle.getText().equals("")){
                    AffichageJournalSortie();
                }else{
                    AffichageJournalSortieArticle(nomArticle.getText());
                }
            }else{
                try {
                String requete = "select * from section where NomSection LIKE ?";
                ps6 = conn.prepareStatement(requete);
                System.out.println(ComboSection.getSelectedItem().toString());
                ps6.setString(1, "%" + ComboSection.getSelectedItem().toString() + "%");
                rs6 = ps6.executeQuery();
                section = rs6.getString("idSection");
                } catch (SQLException e) {
                    System.out.println("ComboSectionItemStateChanged: "+e);
                } finally {
                    try {
                        ps6.close();
                        rs6.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "erreur BD");
                    }
                }
                if(nomArticle.getText().equals("")){
                    AffichageJournalSortieSection(section);
                }else{
                    AffichageJournalSortieSectionArticle(section,nomArticle.getText());
                }
            }
        }
        
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        date1.setDate(date3);
        date2.setDate(date3);
        date1.setDate(null);
        date2.setDate(null);
    }//GEN-LAST:event_ComboSectionItemStateChanged

    private void ComboFournisseurItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboFournisseurItemStateChanged
        conn = ConexionBD.Conexion();
        //String num1 = numArticle.getText();
        if(ComboFournisseur.getItemCount() == 0){
            
        }else{
            
            if(ComboFournisseur.getSelectedItem().toString().equals(" ")){
                if(nomArticle.getText().equals("")){
                    AffichageJournalEntree();
                }else{
                    AffichageJournalEntreeArticle(nomArticle.getText());
                }
            }else{
                try {
                String requete = "select * from Fournisseur where NomFournisseur LIKE ?";
                ps6 = conn.prepareStatement(requete);
                System.out.println(ComboFournisseur.getSelectedItem().toString());
                ps6.setString(1, "%" + ComboFournisseur.getSelectedItem().toString() + "%");
                rs6 = ps6.executeQuery();
                section = rs6.getString("NumFournisseur");
                } catch (SQLException e) {
                    System.out.println("ComboFournisseurItemStateChanged: "+e);
                } finally {
                    try {
                        ps6.close();
                        rs6.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "erreur BD");
                    }
                }
                if(nomArticle.getText().equals("")){
                    AffichageJournalEntreeFournisseur(section);
                }else{
                    AffichageJournalEntreeFournisseurArticle(section,nomArticle.getText());
                }
            }
        }
        
        Long millis = System.currentTimeMillis();
        Date date3 = new Date(millis);
        date1.setDate(date3);
        date2.setDate(date3);
        date1.setDate(null);
        date2.setDate(null);
    }//GEN-LAST:event_ComboFournisseurItemStateChanged

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
    private javax.swing.JComboBox<String> ComboFournisseur;
    private javax.swing.JComboBox<String> ComboSection;
    private javax.swing.JTextField NbInvt;
    private javax.swing.JTable TableArticle;
    private javax.swing.JTable TableJournal;
    private javax.swing.JButton btnInventaire;
    private javax.swing.JButton btnSupprInventaire;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JComboBox<String> dateCombo;
    private javax.swing.JPanel graph;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelFournisseur;
    private javax.swing.JLabel labelSection;
    private javax.swing.JLabel nomArticle;
    private javax.swing.JButton printbtn;
    private javax.swing.JLabel txtbackground3;
    private javax.swing.JTextField txtrechercher1Article;
    private javax.swing.JComboBox<String> typeCombo;
    // End of variables declaration//GEN-END:variables
}
