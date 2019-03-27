package gamePackage;

import gameLogic.GameInstance;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;


import static gameLogic.GameInstance.turnCount;
import static gameLogic.GameInstance.blackpegs;
import static gameLogic.GameInstance.whitepegs;


/**
 * Main method for GUI.
 */

public class Main extends Application {

    //Creates constant for tile size, height and width
    //which are easily accessed throughout project

    /**
     * integer that holds the size of each tile.
     */
    static final int TILE_SIZE = 60;

    /**
     * The height of the tiles in the guessing area.
     */
    private static final int HEIGHT = 12;

    /**
     * The width of the tiles in guessing area.
     */
    private static final int WIDTH = 4;

    /**
     * How many colors there are in the game.
     */
    public static int diffColors = 6;

    /**
     * Changes with each difficulty and is the number of boxes
     * that holds the color.
     */
    private static int numBox = 8;

    /**
     * Enables or disables duplicate colors to be in the key.
     */
    public static boolean duplicateColors = true;

    /**
     * Group for the square boxes on screen.
     */
    private Group guessGroup = new Group();

    /**
     * Group for the pieces that are placed on the board.
     */
    private Group pieceGroup = new Group();

    /**
     * Group for the peg shells that stay the same all game.
     */
    private Group pegGroup = new Group();

    /**
     * Group for the pieces that we are guessing from and the answer.
     */
    private Group keyGroup = new Group();

    /**
     * Group for the colors of the pegs to be placed into the peg shells.
     */
    private Group pegColors = new Group();

    /**
     * Array that is compared to with the answer every turn.
     */
    private String[] arr = new String[4];

    /**
     * Array that holds the colors for the pegs.
     */
    private Color[] pegArr = new Color[4];

    //player 2 stuff here

    /**
     * Array that is compared to with the answer every turn.
     */
    private String[] p2arr = new String[4];

    /**
     * Array that holds the colors for the pegs.
     */
    private Color[] p2pegArr = new Color[4];

    /**
     * Group for the pieces that we are guessing from and the answer.
     */
    private Group p2keyGroup = new Group();

    /**
     * Group for the square boxes on screen.
     */
    private Group p2guessGroup = new Group();

    /**
     * Group for the pieces that are placed on the board.
     */
    private Group p2pieceGroup = new Group();

    /**
     * Group for the colors of the pegs to be placed into the peg shells.
     */
    private Group p2pegColors = new Group();

    /**
     * Group for the peg shells that stay the same all game.
     */
    private Group p2pegGroup = new Group();

    /**
     * New game instance which contains the logic for the game.
     */
    private GameInstance game, p2game;

    /**
     * Used for telling which game mode is being played.
     */
    private int gm = 0;

    /**
     * Tells which player is actively playing.
     */
    private boolean player1Turn = true;

    /**
     * Tells us if we are playing a two player game.
     */
    private boolean isTwoPlayer = false;

    /**
     * Stage which displays all a scene for the game.
     */
    private Stage window;

    /**
     * All of the scenes which are placed into the window.
     */
    private Scene titleScreen, gameScreen, diffScreen, instructScreen, gameModeScreen, player2screen;

    private void createp2game() {
        p2pieceGroup.getChildren().clear();
        p2keyGroup.getChildren().clear();
        p2guessGroup.getChildren().clear();

        if (diffColors >= 4) {
            Pieces redPiece = makePiece(Color.RED, 6, 5, true, false);
            Pieces greenPiece = makePiece(Color.GREEN, 6, 6, true, false);
            Pieces orangePiece = makePiece(Color.ORANGE, 7, 5, true, false);
            Pieces blackPiece = makePiece(Color.BLACK, 7, 6, true, false);

            p2keyGroup.getChildren().addAll(redPiece, greenPiece, blackPiece, orangePiece);

        }
        if (diffColors >= 6) {
            Pieces yellowPiece = makePiece(Color.YELLOW, 7, 7, true, false);
            Pieces bluePiece = makePiece(Color.BLUE, 6, 7, true, false);

            p2keyGroup.getChildren().addAll(bluePiece, yellowPiece);

        }
        if (diffColors == 8) {
            Pieces purplePiece = makePiece(Color.PURPLE, 6, 8, true, false);
            Pieces pinkPiece = makePiece(Color.PINK, 7, 8, true, false);

            p2keyGroup.getChildren().addAll(purplePiece, pinkPiece);

        }

        //creates the box's for the keys based on the difficulty
        for (int x = 6; x < 8; x++) {
            for (int y = 5; y < numBox; y++) {
                Guessbox colorOptions = new Guessbox(Color.WHITE, x, y);
                p2guessGroup.getChildren().addAll(colorOptions);
            }
        }

        //Creates the 4x12 Guessing locations on to the board
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 1; y < HEIGHT + 1; y++) {
                Guessbox guess = new Guessbox(Color.LIGHTGRAY, x, y);
                p2guessGroup.getChildren().addAll(guess);

            }
        }

        //Create the key pieces that are grayed out
        Pieces keyPiece1 = makePiece(Color.GRAY, 0, 0, false, false);
        Pieces keyPiece2 = makePiece(Color.GRAY, 1, 0, false, false);
        Pieces keyPiece3 = makePiece(Color.GRAY, 2, 0, false, false);
        Pieces keyPiece4 = makePiece(Color.GRAY, 3, 0, false, false);


        p2keyGroup.getChildren().addAll(keyPiece1, keyPiece2, keyPiece3, keyPiece4);



        player2screen = new Scene(createp2Content());
        p2game = new GameInstance();

        window.setScene(gameScreen);
    }

    private void createGame() {


        game = new GameInstance();
        pieceGroup.getChildren().clear();
        keyGroup.getChildren().clear();
        guessGroup.getChildren().clear();


        if (diffColors >= 4) {
            Pieces redPiece = makePiece(Color.RED, 6, 5, true, true);
            Pieces greenPiece = makePiece(Color.GREEN, 6, 6, true, true);
            Pieces orangePiece = makePiece(Color.ORANGE, 7, 5, true, true);
            Pieces blackPiece = makePiece(Color.BLACK, 7, 6, true, true);

            keyGroup.getChildren().addAll(redPiece, greenPiece, blackPiece, orangePiece);

        }
        if (diffColors >= 6) {
            Pieces yellowPiece = makePiece(Color.YELLOW, 7, 7, true, true);
            Pieces bluePiece = makePiece(Color.BLUE, 6, 7, true, true);

            keyGroup.getChildren().addAll(bluePiece, yellowPiece);

        }
        if (diffColors == 8) {
            Pieces purplePiece = makePiece(Color.PURPLE, 6, 8, true, true);
            Pieces pinkPiece = makePiece(Color.PINK, 7, 8, true, true);

            keyGroup.getChildren().addAll(purplePiece, pinkPiece);

        }

        //creates the box's for the keys based on the difficulty
        for (int x = 6; x < 8; x++) {
            for (int y = 5; y < numBox; y++) {
                Guessbox colorOptions = new Guessbox(Color.WHITE, x, y);
                guessGroup.getChildren().addAll(colorOptions);
            }
        }

        //Creates the 4x12 Guessing locations on to the board
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 1; y < HEIGHT + 1; y++) {
                Guessbox guess = new Guessbox(Color.LIGHTGRAY, x, y);
                guessGroup.getChildren().addAll(guess);

            }
        }

        //Create the key pieces that are grayed out
        Pieces keyPiece1 = makePiece(Color.GRAY, 0, 0, false, true);
        Pieces keyPiece2 = makePiece(Color.GRAY, 1, 0, false, true);
        Pieces keyPiece3 = makePiece(Color.GRAY, 2, 0, false, true);
        Pieces keyPiece4 = makePiece(Color.GRAY, 3, 0, false, true);


        keyGroup.getChildren().addAll(keyPiece1, keyPiece2, keyPiece3, keyPiece4);

        gameScreen = new Scene((createp1Content()));

        window.setScene(gameScreen);
    }

    private Parent createp2Content() {
        Pane root = new Pane();



        //sets background as the image in that path
        Image background = new Image("File:images/Light-Wood-Background.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);
        iv.setFitHeight(HEIGHT * TILE_SIZE + TILE_SIZE);
        iv.setFitWidth(WIDTH * TILE_SIZE * 2);

        Text playerLabel = new Text("Player 2");
        playerLabel.setFill(Color.BLACK);
        playerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        playerLabel.relocate(4 * TILE_SIZE + (TILE_SIZE / 2), 0);

        //new game button
        Button newGame = new Button("New Game");
        newGame.relocate(6 * TILE_SIZE + (TILE_SIZE / 2), 0);
        newGame.setOnAction(e -> {
            p2pieceGroup.getChildren().clear();
            p2pegColors.getChildren().clear();
            for (int i = 0; i < p2arr.length; i++) {
                p2arr[i] = null;
            }
            p2game = new GameInstance();


        });

        Button menu = new Button("Menu");
        menu.relocate(7 * TILE_SIZE, TILE_SIZE);
        menu.setOnAction(e -> window.setScene(titleScreen));

        //image for the trash can below the colors
        Image trashCan = new Image("File:images/Trash-Can.png");
        ImageView tc = new ImageView();
        tc.setImage(trashCan);
        tc.setFitWidth(TILE_SIZE - 10);
        tc.setFitHeight(TILE_SIZE);
        tc.relocate(7 * TILE_SIZE, 9 * TILE_SIZE);




        //Guess button, setOnMouseClicked is making a guess
        Button guessButton = new Button("Guess");
        guessButton.relocate(7 * TILE_SIZE, 12 * TILE_SIZE);
        guessButton.setOnMouseClicked(e -> {
            //for testing purposes
            //System.out.println(" " + arr[0] + arr[1] + arr[2] + arr[3]);

            //check if all of the tiles in the row have a piece on them
            if (!(p2arr[0] == null || p2arr[1] == null || p2arr[2] == null || p2arr[3] == null)) {
                //calls guess, creates pegs, gets peg colors.
                p2game.guess(p2arr[0], p2arr[1], p2arr[2], p2arr[3]);
                Pegs peg = getPegColors();

                //adds the peg color array to the pegColors group
                p2pegColors.getChildren().addAll(peg);

                //if you get all black pegs (win), show the key up top and
                //set turn count to 14 so no more pieces can be placed
                if (blackpegs == 4) {
                    //System.out.println("You win");
                    Pieces answer1 = makePiece(stringToColor(p2game.getAnswer()[0]), 0, 0, false, false);
                    Pieces answer2 = makePiece(stringToColor(p2game.getAnswer()[1]), 1, 0, false, false);
                    Pieces answer3 = makePiece(stringToColor(p2game.getAnswer()[2]), 2, 0, false, false);
                    Pieces answer4 = makePiece(stringToColor(p2game.getAnswer()[3]), 3, 0, false, false);
                    p2pieceGroup.getChildren().addAll(answer1, answer2, answer3, answer4);
                    turnCount = 14;
                }

                turnCount--;

                //if you run out of turns (lose), show the key
                if (turnCount == 0) {
                    //System.out.println("you lose");
                    Pieces answer1 = makePiece(stringToColor(p2game.getAnswer()[0]), 0, 0, false, false);
                    Pieces answer2 = makePiece(stringToColor(p2game.getAnswer()[1]), 1, 0, false, false);
                    Pieces answer3 = makePiece(stringToColor(p2game.getAnswer()[2]), 2, 0, false, false);
                    Pieces answer4 = makePiece(stringToColor(p2game.getAnswer()[3]), 3, 0, false, false);
                    p2pieceGroup.getChildren().addAll(answer1, answer2, answer3, answer4);
                }
                //System.out.println("turns left: " + turnCount);
                blackpegs = 0;
                whitepegs = 0;
                //clears the array that we are comparing to
                for (int i = 0; i < p2arr.length; i++) {
                    p2arr[i] = null;
                }
                guessButton.setDisable(!player1Turn);
            } else {
                System.out.println("Please Place All Pieces");
            }


            player1Turn = true;



        });

        Button flip = new Button("flip");
        flip.relocate(6 * TILE_SIZE, 12 * TILE_SIZE);
        flip.setOnMouseClicked(e -> {
            window.setScene(gameScreen);
            guessButton.setDisable(false);

        });

        //Makes the window 2 times longer than the area of the guessing
        //for room for pegs and other colors to pick from.
        //May need to make it wider or smaller depending on the items in game
        root.setPrefSize((WIDTH * TILE_SIZE) * 2, (HEIGHT * TILE_SIZE) + TILE_SIZE);

        //here is where everything is added to the root
        //which is indirectly added to the gameScreen scene


        //Creates the shell for the pegs that will soon be filled with colors
        for (int y = 1; y < HEIGHT + 1; y++) {
            Pegs defaultPegs = new Pegs(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, WIDTH, y);
            p2pegGroup.getChildren().add(defaultPegs);
        }

        //we have a seperate group for the keys because when we start a new game
        //we just need to clear the pieceGroup and leave the keys alone.
        //this code adds all of the key pieces listed above to the keyGroup
        //which is indirectly added to the gameScreen.

        root.getChildren().addAll(iv, tc, p2guessGroup, p2pegGroup, p2keyGroup, p2pieceGroup, p2pegColors, guessButton, newGame, menu, flip, playerLabel);


        //returns the root pane which gets passed to the new Scene()
        //in the start method
        return root;

    }

    /**
     * @return root which is a pane that the content will be displayed on.
     */
    private Parent createp1Content() {
        Pane root = new Pane();


        //sets background as the image in that path
        Image background = new Image("File:images/Light-Wood-Background.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);
        iv.setFitHeight(HEIGHT * TILE_SIZE + TILE_SIZE);
        iv.setFitWidth(WIDTH * TILE_SIZE * 2);

        Text playerLabel = new Text("Player 1");
        playerLabel.setFill(Color.BLACK);
        playerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        playerLabel.relocate(4 * TILE_SIZE + (TILE_SIZE / 2), 0);

        //new game button
        Button newGame = new Button("New Game");
        newGame.relocate(6 * TILE_SIZE + (TILE_SIZE / 2), 0);
        newGame.setOnAction(e -> {
            pieceGroup.getChildren().clear();
            pegColors.getChildren().clear();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = null;
            }
            game = new GameInstance();


        });

        Button menu = new Button("Menu");
        menu.relocate(7 * TILE_SIZE, TILE_SIZE);
        menu.setOnAction(e -> window.setScene(titleScreen));

        //image for the trash can below the colors
        Image trashCan = new Image("File:images/Trash-Can.png");
        ImageView tc = new ImageView();
        tc.setImage(trashCan);
        tc.setFitWidth(TILE_SIZE - 10);
        tc.setFitHeight(TILE_SIZE);
        tc.relocate(7 * TILE_SIZE, 9 * TILE_SIZE);


        //Guess button, setOnMouseClicked is making a guess
        Button guessButton = new Button("Guess");
        guessButton.relocate(7 * TILE_SIZE, 12 * TILE_SIZE);
        guessButton.setOnMouseClicked(e -> {
            //for testing purposes
            System.out.println(" " + arr[0] + arr[1] + arr[2] + arr[3]);

            //check if all of the tiles in the row have a piece on them
            if (!(arr[0] == null || arr[1] == null || arr[2] == null || arr[3] == null)) {
                //calls guess, creates pegs, gets peg colors.
                game.guess(arr[0], arr[1], arr[2], arr[3]);
                Pegs peg = getPegColors();

                //adds the peg color array to the pegColors group
                pegColors.getChildren().addAll(peg);

                //if you get all black pegs (win), show the key up top and
                //set turn count to 14 so no more pieces can be placed
                if (blackpegs == 4) {
                    //System.out.println("You win");
                    Pieces answer1 = makePiece(stringToColor(game.getAnswer()[0]), 0, 0, false, true);
                    Pieces answer2 = makePiece(stringToColor(game.getAnswer()[1]), 1, 0, false, true);
                    Pieces answer3 = makePiece(stringToColor(game.getAnswer()[2]), 2, 0, false, true);
                    Pieces answer4 = makePiece(stringToColor(game.getAnswer()[3]), 3, 0, false, true);
                    pieceGroup.getChildren().addAll(answer1, answer2, answer3, answer4);
                    turnCount = 14;
                }

                if (!isTwoPlayer) {
                    turnCount--;
                }

                //if you run out of turns (lose), show the key
                if (turnCount == 0) {
                    //System.out.println("you lose");
                    Pieces answer1 = makePiece(stringToColor(game.getAnswer()[0]), 0, 0, false, true);
                    Pieces answer2 = makePiece(stringToColor(game.getAnswer()[1]), 1, 0, false, true);
                    Pieces answer3 = makePiece(stringToColor(game.getAnswer()[2]), 2, 0, false, true);
                    Pieces answer4 = makePiece(stringToColor(game.getAnswer()[3]), 3, 0, false, true);
                    pieceGroup.getChildren().addAll(answer1, answer2, answer3, answer4);
                }
                //System.out.println("turns left: " + turnCount);
                blackpegs = 0;
                whitepegs = 0;
                //clears the array that we are comparing to
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = null;
                }
                if (isTwoPlayer) {
                    guessButton.setDisable(player1Turn);
                }

            } else {
                System.out.println("Please Place All Pieces");
            }

            player1Turn = false;

        });


        Button flip = new Button("flip");
        if (isTwoPlayer) {
            flip.relocate(6 * TILE_SIZE, 12 * TILE_SIZE);
            flip.setOnMouseClicked(e -> {
                window.setScene(player2screen);
                guessButton.setDisable(false);

            });
        } else {
            flip.setOpacity(0);
        }

        //Makes the window 2 times longer than the area of the guessing
        //for room for pegs and other colors to pick from.
        //May need to make it wider or smaller depending on the items in game
        root.setPrefSize((WIDTH * TILE_SIZE) * 2, (HEIGHT * TILE_SIZE) + TILE_SIZE);

        //here is where everything is added to the root
        //which is indirectly added to the gameScreen scene


        //Creates the shell for the pegs that will soon be filled with colors
        for (int y = 1; y < HEIGHT + 1; y++) {
            Pegs defaultPegs = new Pegs(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, WIDTH, y);
            pegGroup.getChildren().add(defaultPegs);
        }

        //we have a seperate group for the keys because when we start a new game
        //we just need to clear the pieceGroup and leave the keys alone.
        //this code adds all of the key pieces listed above to the keyGroup
        //which is indirectly added to the gameScreen.

        root.getChildren().addAll(iv, tc, guessGroup, pegGroup, keyGroup, pieceGroup, pegColors, guessButton, newGame, flip, menu, playerLabel);

        //returns the root pane which gets passed to the new Scene()
        //in the start method
        return root;

    }


    /**
     * @param primaryStage input for a stage which is the thing
     *                     that will be displayed to the user.
     */

    public void start(final Stage primaryStage) {
        //sets primary stage = to window just for readability
        window = primaryStage;


        window.setTitle("MasterMind");

        Pane menu = new Pane();
        Pane difficulty = new Pane();


        //creates a group for the radio buttons
        final ToggleGroup group = new ToggleGroup();

        //creates the easy, medium, hard radio buttons
        RadioButton easy = new RadioButton("Easy");
        easy.setToggleGroup(group);
        easy.setTextFill(Color.WHITE);

        RadioButton medium = new RadioButton("Medium");
        medium.setToggleGroup(group);
        medium.setSelected(true);
        medium.setTextFill(Color.WHITE);

        RadioButton hard = new RadioButton("Hard");
        hard.setToggleGroup(group);
        hard.setTextFill(Color.WHITE);


        CheckBox dup = new CheckBox("Enable Duplicates");
        dup.setTextFill(Color.WHITE);


        //relocates the buttons to where they are
        easy.relocate(100, 400);
        medium.relocate(200, 400);
        hard.relocate(300, 400);

        dup.relocate(200, 500);


        //checks to see which box is selected and update the variable
        //diffColors to the value which is associated with a difficulty
        group.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            if (hard.isSelected()) {
                diffColors = 8;
                numBox = 9;
            }
            if (medium.isSelected()) {
                diffColors = 6;
                numBox = 8;
            }

            if (easy.isSelected()) {
                diffColors = 4;
                numBox = 7;
            }

        });

        //this button goes into the actual game
        Button go = new Button("Go");
        go.setOnAction(e -> {

            if (gm != 0) {
                createp2game();
                isTwoPlayer = true;
            }

            createGame();



            Main.duplicateColors = dup.isSelected();


        });

        go.relocate(200, 300);
        go.setPrefSize(100, 50);


        //Start button code, setOnAction contains code for
        //when the button is pressed
        Button start = new Button("Start");
        start.setOnAction(e -> window.setScene(diffScreen));
        start.relocate(200, 300);
        start.setPrefSize(100, 50);

        //Title code
        Text gameTitle = new Text(200, 100, "MasterMind");
        gameTitle.setFill(Color.WHITE);
        gameTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        gameTitle.relocate(75, 50);

        //makes the background the image in the specified path
        Image background = new Image("File:images/Wood-Background.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);
        iv.setFitHeight(HEIGHT * TILE_SIZE);
        iv.setFitWidth(WIDTH * TILE_SIZE * 2);

        ImageView diffBg = new ImageView();
        diffBg.setImage(background);
        diffBg.setFitHeight(HEIGHT * TILE_SIZE);
        diffBg.setFitWidth(WIDTH * TILE_SIZE * 2);

        //creates the button titles options
        Button options = new Button("Options");
        options.relocate(200, 400);
        options.setPrefSize(100, 50);

        Button htp = new Button("How To Play");
        htp.relocate(200, 500);
        htp.setPrefSize(100, 50);
        htp.setOnAction(e -> window.setScene(instructScreen));

        Button gameMode = new Button("Game Mode");
        gameMode.relocate(200, 600);
        gameMode.setPrefSize(100, 50);
        gameMode.setOnAction(e -> window.setScene(gameModeScreen));

        //this is if we just want some regular
        //background color instead of an image
        //menu.setStyle("-fx-background-color: #8B4513;");

        //sets the scenes to new scenes which sets width and height.
        //game screen just calls create content because thats where
        //most of the action is

        gameModeScreen = new Scene(createGameMode());
        titleScreen = new Scene(menu, TILE_SIZE * WIDTH * 2, TILE_SIZE * HEIGHT);
        diffScreen = new Scene(difficulty, TILE_SIZE * WIDTH * 2, TILE_SIZE * HEIGHT);
        instructScreen = new Scene(createInstructions());

        //menu is the main menu panel,
        //we add all the buttons and background to the panel
        menu.getChildren().addAll(diffBg, start, gameTitle, options, htp, gameMode);
        difficulty.getChildren().addAll(iv, go, easy, medium, hard, dup);

        //makes the window not able to be resized, sets default scene
        //to the title screen and then shows the window
        window.setResizable(false);
        window.setScene(titleScreen);
        window.show();


    }




    /**
     * @param pixel takes a pixel on the screen.
     * @return returns an int which is the location in terms of X or Y.
     */
    private int toBoard(final double pixel) {
        return (int) (pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    /**
     * @param color    passes the color of the piece
     * @param x        location at where the piece will be placed
     * @param y        location at where the piece will be placed
     * @param moveable if its not moveable then you cannot move it.
     * @param player1 Tells us if it is player 1's turn or player 2.
     * @return returns type Pieces (a new piece)
     */
    private Pieces makePiece(final Color color, final int x, final int y, final boolean moveable, final boolean player1) {
        //creates new piece from Pieces class
        Pieces piece = new Pieces(color, x, y, moveable);


        //when the mouse is released, the OldX, OldY, newX and newY are updated
        piece.setOnMouseReleased(e -> {

            //where you are trying to place the piece on the board
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            //these locations are where you just picked up a piece from
            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            //this is for testing purposes
            System.out.println("Old X : " + x0 + " Old Y : " + y0);
            System.out.println("New X : " + newX + " New Y : " + newY);


            if (player1) {
                if (!(newX > 3 || newY == 0 || newY != turnCount || !player1Turn)) {

                    System.out.println("p1t " + player1Turn);
                    //Checks if you are trying to move a piece that you placed
                    //if not, move piece to newX and newY,
                    //make a new piece at the previous space
                    //update array with piece color and add the piece pieceGroup
                    //else if checks if the piece if coming from a key spot and if so then override the first if statement

                    if (!(pieceGroup.getChildren().contains(piece))) {
                        piece.move(newX, newY);
                        Pieces newPiece = makePiece(color, x0, y0, moveable, true);
                        keyGroup.getChildren().addAll(newPiece);
                        arr[newX] = piece.hexToString();
                        pieceGroup.getChildren().add(piece);
                    } else if (keySpace(x0, y0)) {
                        piece.move(newX, newY);
                        Pieces newPiece = makePiece(color, x0, y0, moveable, true);
                        arr[newX] = piece.hexToString();
                        keyGroup.getChildren().addAll(newPiece);

                        arr[newX] = piece.hexToString();
                    } else {
                        //move back to previous location
                        piece.move(x0, y0);
                    }


                    //System.out.println("Array X: " + arr[newX]);

                } else if (newX == 7 && newY == 9 && y0 == turnCount) {
                    //checks if piece is dropped on the garbage can,
                    //if so it moves the piece to the spot where the key is for that color

                    //System.out.println("trash");

                    switch (piece.hexToString()) {
                        case "green":
                            piece.move(6, 6);
                            break;
                        case "red":
                            piece.move(6, 5);
                            break;
                        case "blue":
                            piece.move(6, 7);
                            break;
                        case "orange":
                            piece.move(7, 5);
                            break;
                        case "yellow":
                            piece.move(7, 7);
                            break;
                        case "black":
                            piece.move(7, 6);
                            break;
                        case "purple":
                            piece.move(6, 8);
                            break;
                        case "pink":
                            piece.move(7, 8);
                            break;
                        default:
                            piece.move(0, 0);
                    }
                    arr[x0] = null;


                } else {
                    //move back to original location
                    piece.move(x0, y0);
                    System.out.println("Out of Bounds");
                }
            } else {
                if (!(newX > 3 || newY == 0 || newY != turnCount || player1Turn)) {


                    //Checks if you are trying to move a piece that you placed
                    //if not, move piece to newX and newY,
                    //make a new piece at the previous space
                    //update array with piece color and add the piece pieceGroup
                    //else if checks if the piece if coming from a key spot and if so then override the first if statement

                    if (!(p2pieceGroup.getChildren().contains(piece))) {
                        piece.move(newX, newY);
                        Pieces newPiece = makePiece(color, x0, y0, moveable, false);
                        p2keyGroup.getChildren().addAll(newPiece);
                        p2arr[newX] = piece.hexToString();
                        p2pieceGroup.getChildren().add(piece);
                    } else if (keySpace(x0, y0)) {
                        piece.move(newX, newY);
                        Pieces newPiece = makePiece(color, x0, y0, moveable, false);
                        p2arr[newX] = piece.hexToString();
                        p2keyGroup.getChildren().addAll(newPiece);

                        p2arr[newX] = piece.hexToString();
                    } else {
                        //move back to previous location
                        piece.move(x0, y0);
                    }


                    //System.out.println("Array X: " + arr[newX]);

                } else if (newX == 7 && newY == 9 && y0 == turnCount) {
                    //checks if piece is dropped on the garbage can,
                    //if so it moves the piece to the spot where the key is for that color

                    //System.out.println("trash");

                    switch (piece.hexToString()) {
                        case "green":
                            piece.move(6, 6);
                            break;
                        case "red":
                            piece.move(6, 5);
                            break;
                        case "blue":
                            piece.move(6, 7);
                            break;
                        case "orange":
                            piece.move(7, 5);
                            break;
                        case "yellow":
                            piece.move(7, 7);
                            break;
                        case "black":
                            piece.move(7, 6);
                            break;
                        case "purple":
                            piece.move(6, 8);
                            break;
                        case "pink":
                            piece.move(7, 8);
                            break;
                        default:
                            piece.move(0, 0);
                    }
                    p2arr[x0] = null;


                } else {
                    //move back to original location
                    piece.move(x0, y0);
                    System.out.println("Out of Bounds");
                }
            }


        });

        return piece;
    }

    /**
     * this function looks at the GameInstance class and
     * sees how many whitepegs there are and black pegs there are.
     *
     * @return returns instance of Pegs which has the colors and location.
     */
    public Pegs getPegColors() {

        //System.out.println("blackPegs: " + blackpegs);
        //System.out.println("whitePegs: " + whitepegs);

        //checks if you have any black pegs, if so update pegArr to black
        for (int i = 0; i < blackpegs; i++) {
            pegArr[i] = Color.BLACK;
        }
        //checks if you have white pegs, if so update the pegArr to White
        for (int i = blackpegs; i < whitepegs + blackpegs; i++) {
            pegArr[i] = Color.WHITE;
        }
        //if no white pegs or black pegs, make the color transparent (default)
        for (int i = blackpegs + whitepegs; i < pegArr.length; i++) {
            pegArr[i] = Color.TRANSPARENT;
        }

        return new Pegs(pegArr[0], pegArr[1], pegArr[2], pegArr[3], WIDTH, turnCount);

    }

    //converts the string of a color to an actual javafx.scene.Color

    /**
     * @param str string to convert to a javafx Color
     * @return returns a javafx Color
     */
    private Color stringToColor(final String str) {
        Color color;

        switch (str) {

            case "green":
                color = Color.GREEN;
                break;
            case "red":
                color = Color.RED;
                break;
            case "blue":
                color = Color.BLUE;
                break;
            case "orange":
                color = Color.ORANGE;
                break;
            case "yellow":
                color = Color.YELLOW;
                break;
            case "black":
                color = Color.BLACK;
                break;
            case "purple":
                color = Color.PURPLE;
                break;
            case "pink":
                color = Color.PINK;
                break;
            default:
                color = Color.TRANSPARENT;
        }


        return color;


    }

    /**
     * @param x takes the x location of a piece.
     * @param y takes the y location of a piece.
     * @return returns a string based off of where the piece is located.
     */
    private String keyLocation(int x, int y) {

        if (x == 6 && y == 6) {
            return "green";
        } else if (x == 6 && y == 5) {
            return "red";
        } else if (x == 6 && y == 7) {
            return "blue";
        } else if (x == 7 && y == 5) {
            return "orange";
        } else if (x == 7 && y == 7) {
            return "yellow";
        } else if (x == 7 && y == 6) {
            return "black";
        } else if (x == 6 && y == 8) {
            return "purple";
        } else if (x == 7 && y == 8) {
            return "pink";
        }

        return "";

    }

    /**
     * @param x takes the x location of a piece.
     * @param y takes the y location of a piece.
     * @return returns true or false if string from keyLocation matches a color.
     */
    private boolean keySpace(int x, int y) {
        String temp = keyLocation(x, y);
        boolean result;
        switch (temp) {
            case "green":
                result = true;
                break;
            case "red":
                result = true;
                break;
            case "blue":
                result = true;
                break;
            case "orange":
                result = true;
                break;
            case "yellow":
                result = true;
                break;
            case "black":
                result = true;
                break;
            case "purple":
                result = true;
                break;
            case "pink":
                result = true;
                break;
            default:
                result = false;
        }

        return result;
    }

    /**
     * Create the contents of the scene for the how to play category.
     *
     * @return returns root which is a pane used by the window.
     */
    private Parent createInstructions() {
        Pane root = new Pane();


        root.setPrefSize((WIDTH * TILE_SIZE) * 2, (HEIGHT * TILE_SIZE) + TILE_SIZE);

        Text title = new Text("How to Play");
        title.setFill(Color.WHITE);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        title.relocate(75, 50);


        Image background = new Image("File:images/Wood-Background.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);
        iv.setFitHeight((HEIGHT * TILE_SIZE) + TILE_SIZE);
        iv.setFitWidth(WIDTH * TILE_SIZE * 2);

        Text instructions = new Text("Mastermind is a guessing game where there are two players, a code master and a guesser. In our case, the code master is an AI that creates a key consisting of 4 colors and are greyed out on the top of the board. The objective for the guesser is to guess what is in the key created by the code master by guessing random colors within 12 turns. Once a guess of 4 colors is submitted the player receives feedback through pegs on how right their colors were. There are 3 levels of pegs, black pegs, white pegs and transparent. If the player receives a black peg that means that they guessed a color that is in the right spot. If the peg is white that means you have the right color but in the wrong spot. If the peg is transparent that means that a piece you guessed has isnt on the board at all. The pegs are generated after every turn and the user will not know which pieces are right based off of the pegs as the pegs do not correlate with the exact location of the peg (ie. the top left peg doesn’t correlate with the left-most guess). The player has 12 turns to guess what the code master has in the key. There are levels of difficulty which adds to the amount of colors being used.");
        instructions.relocate(75, 150);
        instructions.setWrappingWidth(TILE_SIZE * 6);
        instructions.setFill(Color.WHITE);
        instructions.setFont(Font.font("Verdana", 17));

        Button menu = new Button("Menu");
        menu.relocate(7 * TILE_SIZE, TILE_SIZE);
        menu.setOnAction(e -> window.setScene(titleScreen));


        root.getChildren().addAll(iv, title, instructions, menu);
        return root;
    }

    /**
     * Creates what is on the screen to choose which game mode you want.
     *
     * @return returns a parent which is a pane.
     */
    private Parent createGameMode() {
        Pane root = new Pane();
        root.setPrefSize((WIDTH * TILE_SIZE) * 2, (HEIGHT * TILE_SIZE) + TILE_SIZE);

        Image background = new Image("File:images/Wood-Background.jpg");
        ImageView iv = new ImageView();
        iv.setImage(background);
        iv.setFitHeight((HEIGHT * TILE_SIZE) + TILE_SIZE);
        iv.setFitWidth(WIDTH * TILE_SIZE * 2);

        Button menu = new Button("Menu");
        menu.relocate(7 * TILE_SIZE, TILE_SIZE);
        menu.setOnAction(e -> window.setScene(titleScreen));

        //Calculation for these buttons is this:
        //Height * TILE_SIZE - (width of button / 2)

        Button op = new Button("1 Player");
        op.relocate(190, 300);
        op.setPrefSize(100, 50);
        op.setOnAction(e -> {
            window.setScene(diffScreen);
            gm = 0;
        });

        Button tp = new Button("2 Player");
        tp.relocate(90, 400);
        tp.setPrefSize(100, 50);
        tp.setOnAction(e -> {
            window.setScene(diffScreen);
            gm = 1;
        });

        Button tpva = new Button("2 Player vs. AI");
        tpva.relocate(290, 400);
        tpva.setPrefSize(100, 50);
        tpva.setWrapText(true);
        tpva.setTextAlignment(TextAlignment.CENTER);
        tpva.setOnAction(e -> {
            window.setScene(diffScreen);
            gm = 2;
        });


        root.getChildren().addAll(iv, menu, op, tp, tpva);


        return root;
    }

    /**
     * Main method.
     *
     * @param args args.
     */
    public static void main(String[] args) {

        launch(args);
    }
}
