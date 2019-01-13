package gamePackage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;


public class Main extends Application {

    public static final int TILE_SIZE = 100;
    public static final int HEIGHT = 8;
    public static final int WIDTH = 4;

    private Group guessGroup = new Group();
    private Group pieceGroup = new Group();

    Stage window;
        Scene titleScreen, gameScreen;


    public void start(Stage primaryStage) throws Exception{
        // this is a generic way for a one scene application
        window = primaryStage;

        window.setTitle("MasterMind");

        Pane menu = new Pane();

        gameScreen = new Scene((createContent()));
        titleScreen = new Scene(menu, TILE_SIZE * WIDTH * 2,TILE_SIZE * HEIGHT);

        Button start = new Button("Start");
        start.setOnAction(e -> window.setScene(gameScreen));
        start.relocate(350, 300);
        start.setPrefSize(100,50);

        Text gameTitle = new Text(200,100,"MasterMind");
        gameTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        gameTitle.relocate(250, 50);



        menu.setStyle("-fx-background-color: #8B4513;");
        menu.getChildren().addAll(start, gameTitle);

        window.setScene(titleScreen);
        window.show();


    }
    //this is the content for the window just so its not all in the start method
    private Parent createContent(){
        Pane root = new Pane();
        //Makes the window 1.5 times longer than the area of the guessing for room for pegs and other colors to pick from.
        //May need to make it wider or smaller depending on the items in game
        root.setPrefSize((WIDTH * TILE_SIZE) * 2, (HEIGHT * TILE_SIZE));
        root.setStyle("-fx-background-color: #8B4513;");
        root.getChildren().addAll(guessGroup,pieceGroup);
        //Creates the Guessing locations on to the board
        for(int i = 0; i < WIDTH; i++){
            for(int j = 1; j < HEIGHT + 1; j++){
               Guessbox guess = new Guessbox(Color.LIGHTGRAY,i,j);
               Guessbox colorOptions = new Guessbox(Color.WHITE,i+6, j+4);
               guessGroup.getChildren().addAll(guess,colorOptions);
            }
        }

        //Create the color blocks
        Pieces redPiece = new Pieces(Color.RED, 6, 5);
        Pieces greenPiece = new Pieces(Color.GREEN, 6, 6);
        Pieces bluePiece = new Pieces(Color.BLUE, 6, 7);
        Pieces orangePiece = new Pieces(Color.ORANGE, 7, 5);
        Pieces blackPiece = new Pieces(Color.BLACK, 7, 6);
        Pieces yellowPiece = new Pieces(Color.YELLOW, 7, 7);

        //Create the key pieces that are grayed out
        Pieces keyPiece1 = new Pieces(Color.GRAY, 0, 0);
        Pieces keyPiece2 = new Pieces(Color.GRAY, 1, 0);
        Pieces keyPiece3 = new Pieces(Color.GRAY, 2, 0);
        Pieces keyPiece4 = new Pieces(Color.GRAY, 3, 0);


        pieceGroup.getChildren().addAll(redPiece, greenPiece, bluePiece, orangePiece, blackPiece, yellowPiece, keyPiece1, keyPiece2, keyPiece3, keyPiece4);


        return root;

    }


    public static void main(String[] args) {

        launch(args);
    }
}
