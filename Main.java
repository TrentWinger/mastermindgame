package gamePackage;
import gameLogic.GameInstance;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
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

    //Creates constant for tile size, height and width which are easily accessed throughout project
    public static final int TILE_SIZE = 60;
    public static int HEIGHT = 12;
    public static int WIDTH = 4;


    //these are all of the groups that different items get added to which get added to the root pane in createcontent()
    private Group guessGroup = new Group();
    private Group pieceGroup = new Group();
    private Group pegGroup = new Group();
    private Group keyGroup = new Group();
    private Group pegColors = new Group();


    //arr is the array that is populated with colors once the 'guess' button is pressed. it is compared to the answer inside of Game Instance
    //pegArr is the array of peg colors
    String[] arr = new String[4];
    Color[] pegArr = new Color[4];

    //starts new game instance
    public GameInstance game = new GameInstance();

    //window is the window that contains the scene and stage
    //Scene is which scene is showing (title screen, game screen etc...)
    Stage window;
    Scene titleScreen, gameScreen, diffScreen;


    public void start(Stage primaryStage) throws Exception{
        //sets primary stage = to window just for readability
        window = primaryStage;

        window.setTitle("MasterMind");

        Pane menu = new Pane();
        Pane difficulty = new Pane();

        //sets the scenes to new scenes which sets width and height.
        //game screen just calls create content because thats where most of the action is
        gameScreen = new Scene((createContent()));
        titleScreen = new Scene(menu, TILE_SIZE * WIDTH * 2,TILE_SIZE * HEIGHT);
        diffScreen = new Scene(difficulty,TILE_SIZE * WIDTH * 2,TILE_SIZE * HEIGHT);

        //check boxes for the difficulty
        CheckBox easy = new CheckBox();
        CheckBox medium = new CheckBox();
        CheckBox hard = new CheckBox();


        //Start button code, setOnAction contains code for when the button is pressed
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

        //creates the button titles options
        Button options = new Button("Options");
        options.relocate(200,400);
        options.setPrefSize(100,50);

        //this is if we just want some regular background color instead of an image
        //menu.setStyle("-fx-background-color: #8B4513;");

        //menu is the main menu panel, we add all the buttons and background to the panel
        menu.getChildren().addAll(iv,start, gameTitle,options);

        //makes the window not able to be resized, sets default scene to the title screen and then shows the window
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
        Button newGame = new Button("New Game");
        newGame.relocate(6*TILE_SIZE, 0);
        newGame.setOnAction(e->{
            pieceGroup.getChildren().clear();
            pegColors.getChildren().clear();
            for(int i = 0; i < arr.length; i++)
                arr[i] = null;
            game = new GameInstance();

        });

        //image for the trash can below the colors
        Image trashCan = new Image("File:images/Trash-Can.jpg");
        ImageView tc = new ImageView();
        tc.setImage(trashCan);
        tc.setFitWidth(TILE_SIZE);
        tc.setFitHeight(TILE_SIZE);
        tc.relocate(7*TILE_SIZE, 9*TILE_SIZE);

        //Guess button, setOnMouseClicked is making a guess
        Button guessButton = new Button("Guess");
        guessButton.relocate(7*TILE_SIZE,12*TILE_SIZE);
        guessButton.setOnMouseClicked(e-> {
            //for testing purposes
            System.out.println(" " + arr[0] + arr[1] + arr[2] + arr[3]);

            //check if all of the tiles in the row have a piece on them
            if(!(arr[0] == null || arr[1] == null || arr[2] == null || arr[3] == null)) {
                //calls guess and then creates a new instance of the Pegs class and gets peg colors based on if the array matches
                game.guess(arr[0], arr[1], arr[2], arr[3]);
                Pegs peg = getPegColors();

                //adds the peg color array to the pegColors group
                pegColors.getChildren().addAll(peg);

                //if you get all black pegs (win), show the key up top and set turn count to 14 so no more pieces can be placed
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

                //if you run out of turns (lose), show the key
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

        //here is where everything is added to the root which is indirectly added to the gameScreen scene
        root.getChildren().addAll(iv,tc,guessGroup,pegGroup,pieceGroup,keyGroup,pegColors,guessButton,newGame);

        //Creates the 4x12 Guessing locations on to the board
        for(int x = 0; x < WIDTH; x++){
            for(int y = 1; y < HEIGHT + 1; y++){
               Guessbox guess = new Guessbox(Color.LIGHTGRAY,x,y);
               guessGroup.getChildren().addAll(guess);
            }
        }
        //creates the 2x4 group of box's for the colors to go just to the right of the board
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

        //Creates the shell for the pegs that will soon be filled with colors
        for(int y = 1; y < HEIGHT + 1; y++) {
            Pegs defaultPegs = new Pegs(Color.TRANSPARENT,Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, WIDTH, y);
            pegGroup.getChildren().add(defaultPegs);
        }

        //we have a seperate group for the keys because when we start a new game we just need to clear the pieceGroup and leave the keys alone.
        //this code adds all of the key pieces listed above to the keyGroup which is indirectly added to the gameScreen
        keyGroup.getChildren().addAll(redPiece, greenPiece, bluePiece, orangePiece, blackPiece, yellowPiece, keyPiece1, keyPiece2, keyPiece3, keyPiece4);


        //returns the root pane which gets passed to the new Scene() in the start method
        return root;

    }

    //this function makes it so it can convert the pixel location to the X and Y Coordinate
    private int toBoard(double pixel){
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    /**
     *
     * @param color passes the color of the piece
     * @param x location at where the piece will be placed
     * @param y location at where the piece will be placed
     * @param moveable if its not moveable then you cannot move it
     * @return returns type Pieces (a new piece)
     */
    private Pieces makePiece(Color color, int x, int y,boolean moveable){
        //creates new piece from Pieces class
        Pieces piece = new Pieces(color, x, y,moveable);


        //when the mouse is released, the OldX value, OldY value, newX value and newY value are updated
        piece.setOnMouseReleased(e -> {

            //these locations are where you are trying to place the piece on the board
            int newX = toBoard(piece.getLayoutX());
            int newY= toBoard(piece.getLayoutY());

            //these locations are where you just picked up a piece from
            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            //this is for testing purposes
            System.out.println("Old X : " + x0 + " Old Y : " + y0);
            System.out.println("New X : " + newX + " New Y : " + newY);


            if(!(newX > 3 || newY == 0 || newY != turnCount )){

                //Checks if you are trying to move a piece that you already placed on the board
                //if not, move piece to newX and newY, make a new piece at the previous space which is where the key is
                //update the array with the piece color and add the piece to the pieceGroup group
                if(!(pieceGroup.getChildren().contains(piece))) {
                    piece.move(newX, newY);
                    Pieces newPiece = makePiece(color, x0, y0, moveable);
                    keyGroup.getChildren().addAll(newPiece);
                    arr[newX] = piece.hexToString();
                    pieceGroup.getChildren().add(piece);
                }
                else
                    //move back to previous location
                    piece.move(x0,y0);




                //System.out.println("Array X: " + arr[newX]);

            }
            //checks if piece is dropped on the garbage can, if so it moves the piece to the spot where the key is for that color
            else if(newX == 7 && newY == 9){

                switch(piece.hexToString()){
                    case "green": piece.move(6,6);
                        break;
                    case "red": piece.move(6,5);
                        break;
                    case "blue": piece.move(6,7);
                        break;
                    case "orange": piece.move(7,5);
                        break;
                    case "yellow": piece.move(7,7);
                        break;
                    case "black": piece.move(7,6);
                        break;
                }

            }
            else{
                //move back to original location
                piece.move(x0, y0);
                System.out.println("Out of Bounds");
            }



        });

        return piece;
    }

    /**
     * this function looks at the GameInstance class and sees how many whitepegs there are and black pegs there are
     * @return returns instance of Pegs which has the colors and location
     */
    public Pegs getPegColors(){

        System.out.println("blackPegs: " + blackpegs);
        System.out.println("whitePegs: " + whitepegs);

        //checks if you have any black pegs, if so then update the pegArr to the color black
        for(int i =0; i < blackpegs; i++)
            pegArr[i] = Color.BLACK;
        //checks if you have any white pegs, if so then update the pegArr to the color White
        for(int i = blackpegs; i < whitepegs + blackpegs; i++)
            pegArr[i] = Color.WHITE;
        //if no white pegs or black pegs, make the color transparent (default)
        for(int i = blackpegs + whitepegs;i < pegArr.length; i++)
            pegArr[i] = Color.TRANSPARENT;

        return new Pegs(pegArr[0], pegArr[1], pegArr[2], pegArr[3], WIDTH, turnCount);

    }

    //converts the string of a color to an actual javafx.scene.Color
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
