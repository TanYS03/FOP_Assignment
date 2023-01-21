
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.regex.*;
import java.io.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;

public class BarChartExample extends JFrame {

    public BarChartExample() {

        initUI();
    }

    private void initUI() {

        CategoryDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.black);
        add(chartPanel);

        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errorByParticularUser.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(",");
                dataset.setValue(Integer.parseInt(info[1]), "Errors", info[0]);
            }

        } catch (Exception ex) {
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Errors Caused By Particular Users",
                "Usernames",
                "Number of errors",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            BarChartExample ex = new BarChartExample();
            ex.setVisible(true);
        });
    }
}
