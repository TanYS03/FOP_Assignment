import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class PieChart3D extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String FILE = "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\NumErrorsEachMonths.txt";

    public PieChart3D(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        // This will create the dataset
        PieDataset dataset = createDataset();
        // based on the dataset we create the chart
        JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, dataset, true, true, false);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);

        // Get the plot of the chart
        PiePlot plot = (PiePlot) chart.getPlot();
        // Set the label generator to display the number of errors
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
    }

   
    private PieDataset createDataset() {
        
        DefaultPieDataset result = new DefaultPieDataset();
        HashMap<String, String> months= new HashMap<String, String>();
        months.put("01", "January");
        months.put("02", "February");
        months.put("03", "March");
        months.put("04", "April");
        months.put("05", "May");
        months.put("06", "June");
        months.put("07", "July");
        months.put("08", "August");
        months.put("09", "September");
        months.put("10", "October");
        months.put("11", "November");
        months.put("12", "December");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE));
            String line;
            
                while((line = reader.readLine()) != null) {
                    String[] info = line.split(" ");
                    result.setValue(months.get(info[0]), Integer.parseInt(info[1]));
                }
            reader.close();
        } catch (FileNotFoundException e) {System.out.println("File not found.");} 
          catch (IOException ex) {}

        return result;
    }

    public static void main(String[] args) {
        PieChart3D demo = new PieChart3D("Comparison", "Number of Errors In Particular Months");
        demo.pack();
        demo.setVisible(true);
    }
}
