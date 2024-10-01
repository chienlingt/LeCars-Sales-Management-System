/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author Seanseann
 */
import javax.swing.*;
import java.awt.*;

public class LineChart extends JFrame {
    private String[] month=Assignment.month;
    private int[] cases=Assignment.cases;

    public LineChart(String title) {
        super(title);
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LabeledLineChartPanel chartPanel = new LabeledLineChartPanel();
        add(chartPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class LabeledLineChartPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw axes
            g.drawLine(50, getHeight()-50, getWidth()-50, getHeight()-50);
            g.drawLine(50, getHeight()-50, 50, 50);

            // Draw x-axis labels
            int xGap=(getWidth()-100) / (month.length-1);
            for (int i=0; i<month.length; i++) {
                int x=50+i*xGap;
                g.drawString(month[i], x-20, getHeight()-30);
            }

            // Draw y-axis labels with an increment of 5
            for (int i=0; i<=100; i+=5) {
                int y = getHeight()-50 - (i*(getHeight()-100)/100);
                g.drawString(Integer.toString(i), 30, y + 5);
            }

            // Draw data points and lines
            for (int i=0; i<cases.length; i++) {
                int x=50+i*xGap;
                int y=getHeight()-50-(cases[i]*(getHeight()-100)/100);

                // Draw data points
                g.setColor(Color.BLUE);
                g.fillOval(x-5, y-5, 10, 10);

                // Draw lines connecting data points
                if (i>0) {
                    int prevX=50+(i-1)*xGap;
                    int prevY=getHeight()-50-(cases[i-1]*(getHeight()-100)/100);
                    g.setColor(Color.RED);
                    g.drawLine(prevX, prevY, x, y);
                }
            }
        }
    }
}

