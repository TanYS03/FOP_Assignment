package com.example.executegraph;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController extends Assignment implements Initializable{
    @FXML
    private LineChart<?, ?> line;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @Override
    public  void initialize (URL url , ResourceBundle rb){
        HelloController Graph = new HelloController();
        Graph.graph();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("time in second");
        series1.getData().add(new XYChart.Data("June",21741.745));
        series1.getData().add(new XYChart.Data("July",32025.247));
        series1.getData().add(new XYChart.Data("August",28200.423));
        series1.getData().add(new XYChart.Data("September",36553.099));
        series1.getData().add(new XYChart.Data("October",24107.917));
        series1.getData().add(new XYChart.Data("November",47850.660));
        series1.getData().add(new XYChart.Data("December",33436.879));
        line.getData().addAll(series1);

    }
    @Override
    public void graph(){
        Assignment graph = new
    }
}