package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;

import java.util.Collection;

public class Main extends Application {

        Stage window;
        Scene titleScreen, gameScreen;


    public void start(Stage primaryStage) throws Exception{
        // this is a generic way for a one scene application
        window = primaryStage;

        window.setTitle("MasterMind");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5,5,5,5));
        grid.setVgap(30);
        grid.setHgap(2);

        Label title = new Label("MasterMind Game");
        GridPane.setConstraints(title,4,0);

        Circle circle = new Circle(25, Color.RED);
        GridPane.setConstraints(circle,0,1);
        Circle circle2 = new Circle(25, Color.ORANGE);
        GridPane.setConstraints(circle2,1,1);
        Circle circle3 = new Circle(25, Color.BLUE);
        GridPane.setConstraints(circle3,2,1);
        Circle circle4 = new Circle(25, Color.GREEN);
        GridPane.setConstraints(circle4,3,1);

        for(int i = 0; i < 4; i++) {
            for (int j = 3; j < 12; j++) {
                Circle emptyCircle = new Circle(10, Color.BLACK);
                GridPane.setConstraints(emptyCircle, i, j);
                grid.getChildren().add(emptyCircle);
            }
        }

        Circle startCircle = new Circle(25, Color.GREY);
        GridPane.setConstraints(startCircle,0,12);


        grid.setStyle("-fx-background-color: #8B4513;");

        grid.getChildren().addAll(circle,circle2,circle3,circle4,title,startCircle);

        gameScreen = new Scene(grid, 500,700);


        VBox menu = new VBox();
        BorderPane border = new BorderPane();
        border.setRight(menu);
        titleScreen = new Scene(border, 500,700);

        Button start = new Button("Start");
        start.setOnAction(e -> window.setScene(gameScreen));
        menu.getChildren().add(start);

        window.setScene(titleScreen);
        window.show();














        //Here is a way to do it with multiple scenes.\

//        window = primaryStage;
//
//        Button button1 = new Button("Button to go to scene 2");
//        button1.setOnAction(e -> window.setScene(scene2));
//        StackPane layout = new StackPane();
//        layout.getChildren().addAll(button1);
//        scene1 = new Scene(layout, 900,900);
//
//        Button button2 = new Button("Button to go to scene 1");
//        button2.setOnAction(e -> window.setScene(scene1));
//        StackPane layout2 = new StackPane();
//        layout2.getChildren().addAll(button2);
//        scene2 = new Scene(layout2,500,500);
//
//        window.setScene(scene1);
//        window.setTitle("Title Here");
//        window.show();

    }


    public static void main(String[] args) {

        launch(args);
    }
}
