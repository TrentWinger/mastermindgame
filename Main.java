package gamePackage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;



public class Main extends Application {

    public static final int TILE_SIZE = 100;
    public static final int HEIGHT = 8;
    public static final int WIDTH = 4;

    private Group guessGroup = new Group();
    private Group pieceGroup = new Group();

    private Guessbox[][] board = new Guessbox[WIDTH][HEIGHT];

    Stage window;
    Scene titleScreen, gameScreen;


    public void start(Stage primaryStage) throws Exception{
        //sets primary state = to window just for readability
        window = primaryStage;

        window.setTitle("MasterMind");

        Pane menu = new Pane();

        gameScreen = new Scene((createContent()));
        titleScreen = new Scene(menu, TILE_SIZE * WIDTH * 2,TILE_SIZE * HEIGHT);

        //Start button code
        Button start = new Button("Start");
        start.setOnAction(e -> window.setScene(gameScreen));
        start.relocate(350, 300);
        start.setPrefSize(100,50);

        //Title code
        Text gameTitle = new Text(200,100,"MasterMind");
        gameTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        gameTitle.relocate(250, 50);

        //makes the background the image in the specified path
        Image background = new Image("File:images/Wood-Background.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);
        iv.setFitHeight(HEIGHT * TILE_SIZE);
        iv.setFitWidth(WIDTH * TILE_SIZE * 2);


        //menu.setStyle("-fx-background-color: #8B4513;");
        menu.getChildren().addAll(iv,start, gameTitle);

        window.setResizable(false);
        window.setScene(gameScreen);
        window.show();


    }
    //this is the content for the window just so its not all in the start method
    private Parent createContent(){
        Pane root = new Pane();

        //sets background as the image in that path
        Image background = new Image("File:images/Light-Wood-Background.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);
        iv.setFitHeight(HEIGHT * TILE_SIZE);
        iv.setFitWidth(WIDTH * TILE_SIZE * 2);

        //Makes the window 2 times longer than the area of the guessing for room for pegs and other colors to pick from.
        //May need to make it wider or smaller depending on the items in game
        root.setPrefSize((WIDTH * TILE_SIZE) * 2, (HEIGHT * TILE_SIZE));

        //root.setStyle("-fx-background-color: #8B4513;");
        root.getChildren().addAll(iv,guessGroup,pieceGroup);

        //Creates the Guessing locations on to the board
        for(int x = 0; x < WIDTH; x++){
            for(int y = 1; y < HEIGHT + 1; y++){
               Guessbox guess = new Guessbox(Color.LIGHTGRAY,x,y);
               guessGroup.getChildren().addAll(guess);
            }
        }
        //creates the locations for the colors to go just to the right of the board
        for(int x = 6; x < 8; x++){
            for(int y = 4; y < 8 + 1; y++){
                Guessbox colorOptions = new Guessbox(Color.WHITE,x, y);
                guessGroup.getChildren().addAll(colorOptions);
            }
        }

        //Create the color blocks
        Pieces redPiece = makePiece(Color.RED, 6, 5, true);
        Pieces greenPiece = makePiece(Color.GREEN, 6, 6,true);
        Pieces bluePiece = makePiece(Color.BLUE, 6, 7,true);
        Pieces orangePiece = makePiece(Color.ORANGE, 7, 5,true);
        Pieces blackPiece = makePiece(Color.BLACK, 7, 6,true);
        Pieces yellowPiece = makePiece(Color.YELLOW, 7, 7,true);


        //Create the key pieces that are grayed out
        Pieces keyPiece1 = new Pieces(Color.GRAY, 0, 0,false);
        Pieces keyPiece2 = new Pieces(Color.GRAY, 1, 0,false);
        Pieces keyPiece3 = new Pieces(Color.GRAY, 2, 0,false);
        Pieces keyPiece4 = new Pieces(Color.GRAY, 3, 0,false);


        pieceGroup.getChildren().addAll(redPiece, greenPiece, bluePiece, orangePiece, blackPiece, yellowPiece, keyPiece1, keyPiece2, keyPiece3, keyPiece4);



        return root;

    }

    //this function makes it so it can convert the pixel location to the X and Y Coordinate
    private int toBoard(double pixel){
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    private Pieces makePiece(Color color, int x, int y,boolean moveable){
        Pieces piece = new Pieces(color, x, y,moveable);


        //when the mouse is released, the OldX value, OldY value, newX value and newY value are updated
        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY= toBoard(piece.getLayoutY());

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            //this is for testing purposes when we implement the logic
            System.out.println("Old X : " + x0 + " Old Y : " + y0);
            System.out.println("New X : " + newX + " New Y : " + newY);

            if(!(newX > 3 || newY == 0)){
                piece.move(newX, newY);
            }
            else{
                piece.move(x0, y0);
                System.out.println("Out of Bounds");
            }
            //board[newX][newY].setPiece(piece);

        });

        return piece;
    }


    public static void main(String[] args) {

        launch(args);
    }
}
