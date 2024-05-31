package com;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ui.ApplicationFrame;

import java.awt.*;

public class ChartHelper {
    public static void displayChart(XYSeries series, String chartTitle) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle,
                "Generation", // x-axis Label
                "Fitness", // y-axis Label
                dataset,
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // URLs
        );

        styleChart(chart);

        ApplicationFrame frame = new ApplicationFrame(chartTitle);
        frame.setContentPane(new ChartPanel(chart));
        frame.setSize(800, 600);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void styleChart(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();

        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint(Color.gray);
        chart.setBackgroundPaint(Color.white);

        Font font = new Font("Arial", Font.PLAIN, 12);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 16));
        plot.getDomainAxis().setLabelFont(font);
        plot.getRangeAxis().setLabelFont(font);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f)); 
        plot.setRenderer(renderer);

        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    }
}
