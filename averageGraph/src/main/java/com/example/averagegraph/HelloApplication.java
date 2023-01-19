package com.example.averagegraph;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.ini4j.Ini;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class HelloApplication extends Application {
    private Ini aRead;
    private Ini aWrite;
    private double width;
    private double height;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        aRead = new Ini();
        aRead.load(new FileReader("D:/UM/WIX1002 Fundamentals of Programming/trygraph.txt"));

        width = aRead.get("scene","width",double.class);
        height = aRead.get("scene","height",double.class);

        stage.setWidth(width);
        stage.setHeight(height);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                       aWrite = new Ini();
                       aWrite.put("scene","width",stage.getWidth());
                       aWrite.put("scene","height",stage.getHeight());

                       try{
                           aWrite.store(new FileOutputStream("D:/UM/WIX1002 Fundamentals of Programming/trygraph.txt"));
                       }catch(IOException e){
                           e.getMessage();
                       }
                    }
                });
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}