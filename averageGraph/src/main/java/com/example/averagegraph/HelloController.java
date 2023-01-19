package com.example.averagegraph;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private LineChart<?, ?> line;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
//    private String[] month;
//    private int[] time;





//    void read(){
//        try{
//            Scanner inputstream = new Scanner(new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/average time.txt"));
//            int num=0;
//            while(inputstream.hasNextLine()){
//                String[] line = inputstream.nextLine().split(" ");
//                month[num]=line[0];
//                time[num]=Integer.parseInt(line[1]);
//            }
//            inputstream.close();
//        }catch(IOException e){
//            e.getMessage();
//        }
//    }

@Override
    public void initialize (URL url , ResourceBundle rb){

//        try{
//            Scanner inputstream = new Scanner(new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/average time.txt"));
//            int num=0;
//            while(inputstream.hasNextLine()){
//                String[] line = inputstream.nextLine().split(" ");
//                month[num]=line[0];
//                time[num]=Integer.parseInt(line[1]);
//            }
//            inputstream.close();
//        }catch(IOException e){
//            e.getMessage();
//        }


        XYChart.Series series1 = new XYChart.Series();
            series1.setName("time in second");
//            for (int i = 0; i < month.length; i++) {
//            series1.getData().add(new XYChart.Data(month[i],time[i]));
            series1.getData().add(new XYChart.Data("June",21741.745));
            series1.getData().add(new XYChart.Data("July",32025.247));
            series1.getData().add(new XYChart.Data("August",28200.423));
            series1.getData().add(new XYChart.Data("September",36553.099));
            series1.getData().add(new XYChart.Data("October",24107.917));
            series1.getData().add(new XYChart.Data("November",47850.660));
            series1.getData().add(new XYChart.Data("December",33436.879));
//            }
            line.getData().addAll(series1);

    }
}
