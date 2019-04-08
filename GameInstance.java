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
     * Array for keeping track of turns
     */

    public Turn[] turns = new Turn[12];

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
     * Sets the answer for the GameInstance.
     * Used for debugging.
     * @param color1 first color passed.
     * @param color2 second color passed.
     * @param color3 third color passed.
     * @param color4 fourth color passed.
     */
    public void setAnswer(final String color1, final String color2, final String color3, final String color4){
        this.answer.orderArray[0] = color1;
        this.answer.orderArray[1] = color2;
        this.answer.orderArray[2] = color3;
        this.answer.orderArray[3] = color4;
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
    public void guess(final String color1, final String color2, final String color3, final String color4) {
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

            for(int i=0; i < this.turns.length; i++){
                if (turns[i] == null){
                    turns[i] = new Turn(move, blackpegs, whitepegs);
                    break;
                }
            }

            System.out.println("TURN COUNT:"+turnCount);



        } else {
            System.out.print("The game has ended");

        }

    }
    public String[] guessAI(){
        String[] guess = new String[4];

        guess[0] = randomColor();
        guess[1] = randomColor();
        guess[2] = randomColor();
        guess[3] = randomColor();

            if(!(turnCount == 12) && turns[12-turnCount-1] != null && turns[12-turnCount-1].getWhitepegs() >0){
                for(int j = turns[12-turnCount-1].getWhitepegs()-1; j>=0;j++){
                    if(j==4){
                        guess[0] = turns[12-turnCount-1].getMove().orderArray[4];
                    }
                    else{
                        guess[j+1] = turns[12-turnCount-1].getMove().orderArray[j];
                    }
                }
            }

            if(!(turnCount == 12) && turns[12-turnCount-1] != null && turns[12-turnCount-1].getBlackpegs() >0){
                for(int j = turns[12-turnCount-1].getBlackpegs()-1; j>=0;j++){
                    guess[j] = turns[12-turnCount-1].getMove().orderArray[j];
                }
            }

        for(int i=0;i<=12;i++){
            if(!(turnCount == 12) && turns[i].getBlackpegs() > 0){
                for(int j = 0; j <= 12; j++){
                    if(turns[j].getBlackpegs()>0){
                        if(i!=j){
                            if(turns[i].getMove().orderArray[0].equals(turns[j].getMove().orderArray[0])){
                                guess[0]=turns[i].getMove().orderArray[0];
                            }

                        }
                    }
                }

            }
            if(!(turnCount == 12) && turns[i].getBlackpegs() > 0){
                for(int j = 0; j <= 12; j++){
                    if(turns[j].getBlackpegs()>0){
                        if(i!=j){
                            if(turns[i].getMove().orderArray[1].equals(turns[j].getMove().orderArray[1])){
                                guess[1]=turns[i].getMove().orderArray[1];
                            }

                        }
                    }
                }

            }
            if(!(turnCount == 12) && turns[i].getBlackpegs() > 0){
                for(int j = 0; j <= 12; j++){
                    if(turns[j].getBlackpegs()>0){
                        if(i!=j){
                            if(turns[i].getMove().orderArray[2].equals(turns[j].getMove().orderArray[2])){
                                guess[2]=turns[i].getMove().orderArray[2];
                            }

                        }
                    }
                }

            }
            if(!(turnCount == 12) && turns[i].getBlackpegs() > 0){
                for(int j = 0; j <= 12; j++){
                    if(turns[j].getBlackpegs()>0){
                        if(i!=j){
                            if(turns[i].getMove().orderArray[3].equals(turns[j].getMove().orderArray[3])){
                                guess[3]=turns[i].getMove().orderArray[3];
                            }

                        }
                    }
                }
            }

        }
        System.out.println("GUESS: "+guess[0]+" "+guess[1]+""+guess[2]+""+guess[3]);
        return guess;



    }

}
//The following code adds the current guess, and respective peg counts to the turns array.


