package gameLogic;


import gamePackage.Main;

import java.util.ArrayList;
import java.util.Random;

public class GameInstance {
    private boolean ongoing;
    public static int turnCount;
    private Move answer;

    public static int blackpegs = 0;
    public static int whitepegs = 0;


    public GameInstance() {
        this.ongoing = true;
        this.answer = new Move(randomColor(), randomColor(), randomColor(), randomColor());
        turnCount = 12;
        blackpegs = 0;
        whitepegs = 0;
        System.out.println(" "+this.answer.orderArray[0] + this.answer.orderArray[1] + this.answer.orderArray[2] + this.answer.orderArray[3]);


    }

    /**
     *
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

    private String randomColor() {

        String[] color = new String[Main.diffColors];

        if (Main.diffColors >= 4) {
            color[0] = "green";
            color[1] = "orange";
            color[2] = "black";
            color[3] = "red";
        }
        if (Main.diffColors >= 6) {
            color[4] = "blue";
            color[5] = "yellow";
        }
        if (Main.diffColors == 8) {
            color[6] = "purple";
            color[7] = "pink";

        }

        Random rand = new Random();
        int random = rand.nextInt(Main.diffColors);

        return color[random];
    }

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

            for  (int i = 0; i < this.answer.orderArray.length; i++) {
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
