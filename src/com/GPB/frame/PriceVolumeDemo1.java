package com.GPB.frame;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

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
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PriceVolumeDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public PriceVolumeDemo1(String s) {
        super(s);
        JFreeChart jfreechart = createChart();
        ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, false, true);
        chartpanel.setPreferredSize(new Dimension(300, 180));
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

    public static void main(String args[]) {
        PriceVolumeDemo1 pricevolumedemo1 = new PriceVolumeDemo1("Price Volume Chart Demo");
        pricevolumedemo1.pack();
        RefineryUtilities.centerFrameOnScreen(pricevolumedemo1);
        pricevolumedemo1.setVisible(true);
    }

}
