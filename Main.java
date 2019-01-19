package gamePackage;
import gameLogic.GameInstance;

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

import java.util.Arrays;

import static gameLogic.GameInstance.turnCount;
import static gameLogic.GameInstance.blackpegs;
import static gameLogic.GameInstance.whitepegs;



public class Main extends Application {

    public static final int TILE_SIZE = 60;
    public static final int HEIGHT = 12;
    public static final int WIDTH = 4;

    private Group guessGroup = new Group();
    private Group pieceGroup = new Group();
    private Group pegGroup = new Group();

    String[] arr = new String[4];
    Color[] pegArr = new Color[4];


    public GameInstance game = new GameInstance();

    Stage window;
    Scene titleScreen, gameScreen;


    public void start(Stage primaryStage) throws Exception{
        //sets primary stage = to window just for readability
        window = primaryStage;

        window.setTitle("MasterMind");

        Pane menu = new Pane();

        gameScreen = new Scene((createContent()));
        titleScreen = new Scene(menu, TILE_SIZE * WIDTH * 2,TILE_SIZE * HEIGHT);


        //Start button code
        Button start = new Button("Start");
        start.setOnAction(e -> window.setScene(gameScreen));
        start.relocate(200,300);
        start.setPrefSize(100,50);

        //Title code
        Text gameTitle = new Text(200,100,"MasterMind");
        gameTitle.setFill(Color.WHITE);
        gameTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        gameTitle.relocate(75, 50);

        //makes the background the image in the specified path
        Image background = new Image("File:images/Wood-Background.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);
        iv.setFitHeight(HEIGHT * TILE_SIZE);
        iv.setFitWidth(WIDTH * TILE_SIZE * 2);


        Button options = new Button("Options");
        options.relocate(200,400);
        options.setPrefSize(100,50);


        //menu.setStyle("-fx-background-color: #8B4513;");
        menu.getChildren().addAll(iv,start, gameTitle,options);

        window.setResizable(false);
        window.setScene(titleScreen);
        window.show();


    }
    //this is the content for the window just so its not all in the start method
    private Parent createContent(){
        Pane root = new Pane();

        //sets background as the image in that path
        Image background = new Image("File:images/Light-Wood-Background.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);
        iv.setFitHeight(HEIGHT * TILE_SIZE + TILE_SIZE);
        iv.setFitWidth(WIDTH * TILE_SIZE * 2);

        //new game button
        Button mainMenu = new Button("Menu");
        mainMenu.relocate(7*TILE_SIZE, 0);
        mainMenu.setOnAction(e->{
            window.setScene(titleScreen);
        });

        //Guess button
        Button guessButton = new Button("Guess");
        guessButton.relocate(7*TILE_SIZE,12*TILE_SIZE);
        guessButton.setOnMouseClicked(e-> {
            System.out.println(" " + arr[0] + arr[1] + arr[2] + arr[3]);

            //check if all of the tiles in the row have a piece on them
            if(!(arr[0] == null || arr[1] == null || arr[2] == null || arr[3] == null)) {
                game.guess(arr[0], arr[1], arr[2], arr[3]);
                Pegs peg = getPegColors();

                pegGroup.getChildren().addAll(peg);
                if(blackpegs == 4) {
                    System.out.println("You win");
                    Pieces answer1 = makePiece(stringToColor(game.getAnswer()[0]),0,0,false);
                    Pieces answer2 = makePiece(stringToColor(game.getAnswer()[1]),1,0,false);
                    Pieces answer3 = makePiece(stringToColor(game.getAnswer()[2]),2,0,false);
                    Pieces answer4 = makePiece(stringToColor(game.getAnswer()[3]),3,0,false);
                    pieceGroup.getChildren().addAll(answer1,answer2,answer3,answer4);
                    turnCount = 14;
                }

                turnCount--;
                if(turnCount == 0) {
                    System.out.println("you lose");
                    Pieces answer1 = makePiece(stringToColor(game.getAnswer()[0]),0,0,false);
                    Pieces answer2 = makePiece(stringToColor(game.getAnswer()[1]),1,0,false);
                    Pieces answer3 = makePiece(stringToColor(game.getAnswer()[2]),2,0,false);
                    Pieces answer4 = makePiece(stringToColor(game.getAnswer()[3]),3,0,false);
                    pieceGroup.getChildren().addAll(answer1,answer2,answer3,answer4);
                }
                System.out.println("turns left: " + turnCount);
                blackpegs = 0;
                whitepegs = 0;
                //clears the array that we are comparing to
                for(int i = 0; i < arr.length; i++)
                    arr[i] = null;
            }
            else
                System.out.println("Please Place All Pieces");

        });


        //Makes the window 2 times longer than the area of the guessing for room for pegs and other colors to pick from.
        //May need to make it wider or smaller depending on the items in game
        root.setPrefSize((WIDTH * TILE_SIZE) * 2, (HEIGHT * TILE_SIZE) + TILE_SIZE);

        //root.setStyle("-fx-background-color: #8B4513;");
        root.getChildren().addAll(iv,guessGroup,pegGroup,pieceGroup,guessButton,mainMenu);

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

        for(int y = 1; y < HEIGHT + 1; y++) {
            Pegs defaultPegs = new Pegs(Color.TRANSPARENT,Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, WIDTH, y);
            pegGroup.getChildren().add(defaultPegs);
        }
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
            //System.out.println("Old X : " + x0 + " Old Y : " + y0);
            //System.out.println("New X : " + newX + " New Y : " + newY);
            //System.out.println("Array: " + board[newX][newY]);

            if(!(newX > 3 || newY == 0 || newY != turnCount)){
                //this commented function will get the 8 digit text that you can convert in the Pieces class
                //piece.getColor().toString();
                piece.move(newX, newY);
                Pieces newPiece = makePiece(color,x0,y0,moveable);
                pieceGroup.getChildren().addAll(newPiece);




                arr[newX] = piece.hexToString();
                //System.out.println("Array X: " + arr[newX]);

            }
            else{
                piece.move(x0, y0);
                System.out.println("Out of Bounds");
            }
            //board[newX][newY].setPiece(piece);


            //System.out.println(x);

        });

        return piece;
    }


    public Pegs getPegColors(){

        System.out.println("blackPegs: " + blackpegs);
        System.out.println("whitePegs: " + whitepegs);

        for(int i =0; i < blackpegs; i++)
            pegArr[i] = Color.BLACK;
        for(int i = blackpegs; i < whitepegs + blackpegs; i++)
            pegArr[i] = Color.WHITE;
        for(int i = blackpegs + whitepegs;i < pegArr.length; i++)
            pegArr[i] = Color.TRANSPARENT;

        return new Pegs(pegArr[0], pegArr[1], pegArr[2], pegArr[3], WIDTH, turnCount);

    }

    //just in case we need it
    public Color stringToColor(String str){
        Color color = Color.TRANSPARENT;

        switch(str){
            case "green": color = Color.GREEN;
                break;
            case "red": color = Color.RED;
                break;
            case "blue": color = Color.BLUE;
                break;
            case "orange": color = Color.ORANGE;
                break;
            case "yellow": color = Color.YELLOW;
                break;
            case "black": color = Color.BLACK;
                break;
        }

        return color;


    }
    public static void main(String[] args) {

        launch(args);
    }
}
