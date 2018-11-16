package com.github.pkrysztofiak.javafx.clippane;

import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class DemoApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(0., 0.);
        gridPane.setPrefSize(600, 600);
        gridPane.setMaxSize(10e8, 10e8);
        gridPane.setVgap(2);
        gridPane.setHgap(2);

        IntStream.range(0, 3).forEach(i -> {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.SOMETIMES);
            columnConstraints.setMinWidth(10);
            columnConstraints.setPrefWidth(100);
            gridPane.getColumnConstraints().add(columnConstraints);
        });

        IntStream.range(0, 3).forEach(i -> {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            rowConstraints.setMinHeight(10);
            rowConstraints.setPrefHeight(100);
            gridPane.getRowConstraints().add(rowConstraints);
        });
        
        IntStream.range(0, 3).forEach(column -> IntStream.range(0, 3).forEach(row -> gridPane.add(new ClipPane(), column, row)));
        
        stage.setScene(new Scene(gridPane));
        stage.show();
    }
}