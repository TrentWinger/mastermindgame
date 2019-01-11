package gamePackage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Main extends Application {

    public static final int TILE_SIZE = 75;
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

        gameScreen = new Scene((createContent()));

        VBox menu = new VBox();
        BorderPane border = new BorderPane();
        border.setRight(menu);
        titleScreen = new Scene(border, 500,700);


        Button start = new Button("Start");
        start.setOnAction(e -> window.setScene(gameScreen));
        menu.getChildren().add(start);

        window.setScene(titleScreen);
        window.show();


    }
    //this is the content for the window just so its not all in the start method
    private Parent createContent(){
        Pane root = new Pane();
        //Makes the window 1.5 times longer than the area of the guessing for room for pegs and other colors to pick from.
        //May need to make it wider or smaller depending on the items in game
        root.setPrefSize((WIDTH * TILE_SIZE) * 1.5, (HEIGHT * TILE_SIZE));

        root.getChildren().addAll(guessGroup,pieceGroup);
        //Creates the Guessing locations on to the board
        for(int i = 0; i < WIDTH; i++){
            for(int j = 1; j < HEIGHT + 1; j++){
               Guessbox guess = new Guessbox((i+j) % 2 == 0, i,j);

               guessGroup.getChildren().add(guess);
            }
        }




        return root;

    }


    public static void main(String[] args) {

        launch(args);
    }
}
