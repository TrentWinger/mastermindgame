package gameLogic;


import gamePackage.Main;

import java.util.Random;

/**
 * Logic for the game.
 */
public class GameInstance {

    /**
     * tells the game class if the game is over or not.
     */
    private boolean ongoing;

    /**
     * tells what turn the player is on.
     */
    public static int turnCount;

    /**
     * Answer to the key that the user is trying to guess.
     */
    private Move answer;

    /**
     * number of black pegs on any given guess.
     */
    public static int blackpegs = 0;

    /**
     * number of white pegs on any given guess.
     */
    public static int whitepegs = 0;

    /**
     * Array for picking a random color when enable duplicates is off.
     */
    private String[] colorz = new String[Main.diffColors];

    /**
     * Constructor for GameInstance, set ongoing to true, populates colors,
     * makes an answer, sets the pegs and turn count to starting position.
     */
    public GameInstance() {
        this.ongoing = true;


        if (Main.diffColors >= 4) {
            colorz[0] = "green";
            colorz[1] = "orange";
            colorz[2] = "black";
            colorz[3] = "red";
        }
        if (Main.diffColors >= 6) {
            colorz[4] = "blue";
            colorz[5] = "yellow";
        }
        if (Main.diffColors == 8) {
            colorz[6] = "purple";
            colorz[7] = "pink";

        }

        this.answer = new Move(randomColor(), randomColor(), randomColor(), randomColor());

        turnCount = 12;
        blackpegs = 0;
        whitepegs = 0;
        System.out.println(" " + this.answer.orderArray[0] + this.answer.orderArray[1] + this.answer.orderArray[2] + this.answer.orderArray[3]);


    }

    /**
     * @return returns an array of strings which is the answer for the game
     */
    public String[] getAnswer() {
        String[] temp = new String[4];
        temp[0] = this.answer.orderArray[0];
        temp[1] = this.answer.orderArray[1];
        temp[2] = this.answer.orderArray[2];
        temp[3] = this.answer.orderArray[3];

        return temp;
    }


    /**
     * Generates a random color and if enable duplicate colors is off,
     * generate 4 random different colors.
     * @return returns a color as a string.
     */
    private String randomColor() {

        String s;

        Random rand = new Random();
        int random = rand.nextInt(Main.diffColors);

        s = colorz[random];


        if (!Main.duplicateColors) {
            colorz[random] = null;

            if (s != null) {
                return s;
            } else {
                return randomColor();
            }
        } else {
            return s;
        }
    }

    /**
     * Guesses a color which then populates the peg arrays based on if their
     * answer was right or wrong.
     * @param color1 first color guessed.
     * @param color2 second color guessed.
     * @param color3 third color guessed.
     * @param color4 fourth color guessed.
     */
    public void guess(String color1, String color2, String color3, String color4) {
        if (ongoing) {


            Move move = new Move(color1, color2, color3, color4);

            String[] ans = {this.answer.orderArray[0], this.answer.orderArray[1], this.answer.orderArray[2], this.answer.orderArray[3]};

            String[] guessArr = {color1, color2, color3, color4};

            if (move.orderArray[0].equals(this.answer.orderArray[0])) {
                blackpegs++;
                ans[0] = null;
                guessArr[0] = "guessed";
            }

            if (move.orderArray[1].equals(this.answer.orderArray[1])) {
                blackpegs++;
                ans[1] = null;
                guessArr[1] = "guessed";

            }

            if (move.orderArray[2].equals(this.answer.orderArray[2])) {
                blackpegs++;
                ans[2] = null;
                guessArr[2] = "guessed";

            }

            if (move.orderArray[3].equals(this.answer.orderArray[3])) {
                blackpegs++;
                ans[3] = null;
                guessArr[3] = "guessed";

            }

            for (int i = 0; i < this.answer.orderArray.length; i++) {
                if (guessArr[0].equals(ans[i]) && i != 0) {
                    whitepegs++;
                    ans[i] = null;
                    break;
                }
            }

            for (int i = 0; i < this.answer.orderArray.length; i++) {
                if (guessArr[1].equals(ans[i]) && i != 1) {
                    whitepegs++;
                    ans[i] = null;
                    break;
                }
            }

            for (int i = 0; i < this.answer.orderArray.length; i++) {
                if (guessArr[2].equals(ans[i]) && i != 2) {
                    whitepegs++;
                    ans[i] = null;
                    break;
                }
            }

            for (int i = 0; i < this.answer.orderArray.length; i++) {
                if (guessArr[3].equals(ans[i]) && i != 3) {
                    whitepegs++;
                    ans[i] = null;
                    break;
                }
            }


        } else {
            System.out.print("The game has ended");

        }

    }
}
