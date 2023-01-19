package org.example;

import java.util.Arrays;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class graph extends Application {

    public static void main(String[] args) {
        launch(args);
    }
//    public void call(){
//        start(stage);
//    }

    @Override
    public void start(Stage stage){

        // Create X-axis and Y-axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("June", "July", "August", "September", "October", "November", "December")));
        NumberAxis yAxis = new NumberAxis();

        // Create chart
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
//        StackedBarChart<String, Number> chart = new StackedBarChart<>(xAxis, yAxis);
        chart.setTitle("Total of Created Job & Pending Job");
        Assignment a = new Assignment();

        // Create data arrays
        String[] data1 = a.getMonth();
        int[] data2 = a.getAmount();

//        String[] categories = {"June", "July", "August", "September", "October", "November", "December"};


        // Create series data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("CreatedJob");
        for (int i = 0; i < data1.length; i++) {
            series1.getData().add(new XYChart.Data<>(data1[i], data2[i]));
        }

//        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
//        series2.setName("PendingJob");
//        for (int i = 0; i < data2.length; i++) {
//            series2.getData().add(new XYChart.Data<>(categories[i], data2[i]));
//        }

        // Add series data to chart
        chart.getData().addAll(series1);

        // Create scene and add chart to it
        Scene scene = new Scene(chart, 800, 600);
        stage.setScene(scene);

        // Show the chart
        stage.show();
    }

//    public void ExceedAndInvalidAcc(Stage stage){
//        // Create X-axis and Y-axis
//        CategoryAxis xAxis = new CategoryAxis();
//        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("June", "July", "August", "September", "October", "November", "December")));
//        NumberAxis yAxis = new NumberAxis();
//
//        // Create chart
//        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
//        chart.setTitle("Total number of jobs created successfully(Exceeded & Invalid)");
//        org.example.Assignment a = new org.example.Assignment();
//
//        // Create data arrays
//
//        int[] data1 = a.QOSLimit();
//        int[] data2 = a.MemLimit();
//        int[] data3 = a.CpuLimit();
//        int[] data4 = a.InvalidAcc();
//
//        String[] categories = {"June", "July", "August", "September", "October", "November", "December"};
//
//
//        // Create series data
//        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
//        series1.setName("QosMaxWallDuarationPerJobLimit");
//        for (int i = 0; i < data1.length; i++) {
//            series1.getData().add(new XYChart.Data<>(categories[i], data1[i]));
//        }
//
//        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
//        series2.setName("AssocGrpCpuLimit");
//        for (int i = 0; i < data2.length; i++) {
//            series2.getData().add(new XYChart.Data<>(categories[i], data2[i]));
//        }
//
//        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
//        series3.setName("AssocGrpMemLimit");
//        for (int i = 0; i < data3.length; i++) {
//            series3.getData().add(new XYChart.Data<>(categories[i], data3[i]));
//        }
//
//        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
//        series4.setName("InvalidJob");
//        for (int i = 0; i < data4.length; i++) {
//            series4.getData().add(new XYChart.Data<>(categories[i], data4[i]));
//        }
//
//
//        // Add series data to chart
//        chart.getData().addAll(series1, series2, series3, series4);
//
//        // Create scene and add chart to it
//        Scene scene = new Scene(chart, 800, 600);
//        stage.setScene(scene);
//
//        // Show the chart
//        stage.show();
//    }
//[0:16 am, 18/01/2023] Yanho DS: public void PartitionOfInvalidAcc(Stage stage){
//        // Create X-axis and Y-axis
//        CategoryAxis xAxis = new CategoryAxis();
//        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("June", "July", "August", "September", "October", "November", "December")));
//        NumberAxis yAxis = new NumberAxis();
//
//        // Create chart
//        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
//        chart.setTitle("Total number of jobs created successfully(Exceeded & Invalid)");
//        org.example.Assignment a = new org.example.Assignment();
//
//        // Create data arrays
//
//        int[] data1 = a.CpuOpteron();
//        int[] data2 = a.k10();
//        int[] data3 = a.k40c();
//
//        String[] categories = {"June", "July", "August", "September", "October", "November", "December"};
//
//
//        // Create series data
//        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
//        series1.setName("Gpu-k10");
//        for (int i = 0; i < data1.length; i++) {
//            series1.getData().add(new XYChart.Data<>(categories[i], data1[i]));
//        }
//
//        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
//        series2.setName("Gpu-k40c");
//        for (int i = 0; i < data2.length; i++) {
//            series2.getData().add(new XYChart.Data<>(categories[i], data2[i]));
//        }
//
//        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
//        series3.setName("Cpu-opteron");
//        for (int i = 0; i < data3.length; i++) {
//            series3.getData().add(new XYChart.Data<>(categories[i], data3[i]));
//        }
//
//
//        // Add series data to chart
//        chart.getData().addAll(series1, series2, series3);
//
//        // Create scene and add chart to it
//
//        Scene scene = new Scene(chart, 800, 600);
////        chart.getCategoryGap(50);
//        stage.setScene(scene);
//
//        // Show the chart
//        stage.show();
//    }
}