package gameLogic;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Random;

public class GameInstance {
    private boolean ongoing;
    public static int turnCount = 12;
    private Move answer;

    public static int blackpegs = 0;
    public static int whitepegs = 0;

    public GameInstance(){
        this.ongoing = true;
        this.answer = new Move(randomColor(),randomColor(),randomColor(),randomColor()); //Make a list of available colors
        blackpegs = 0;
        whitepegs = 0;
        System.out.println("Answer: " + this.answer.orderArray[0] + this.answer.orderArray[1] + this.answer.orderArray[2] + this.answer.orderArray[3]);

    }


    public String[] getAnswer(){
        String[] temp = new String[4];
        temp[0] = this.answer.orderArray[0];
        temp[1] = this.answer.orderArray[1];
        temp[2] = this.answer.orderArray[2];
        temp[3] = this.answer.orderArray[3];

        return temp;
    }

    public String randomColor(){
        String[] color = new String[6];
        color[0] = "green";
        color[1] = "orange";
        color[2] = "black";
        color[3] = "yellow";
        color[4] = "blue";
        color[5] = "red";

        Random rand = new Random();
        int random = rand.nextInt(6);


        return color[random];
    }

    public String guess(String color1, String color2, String color3, String color4){
        if(ongoing){


            Move move = new Move(color1, color2, color3, color4);

            if(move.orderArray[0].equals(this.answer.orderArray[0])){
                blackpegs++;
            }

            if(move.orderArray[1].equals(this.answer.orderArray[1])){
                blackpegs++;
            }

            if(move.orderArray[2].equals(this.answer.orderArray[2])){
                blackpegs++;
            }

            if(move.orderArray[3].equals(this.answer.orderArray[3])){
                blackpegs++;
            }

            for(int i=0; i<this.answer.orderArray.length; i++){
                if(move.orderArray[0].equals(this.answer.orderArray[i]) && i != 0){
                    whitepegs++;
                    break;
                }
            }

            for(int i=0; i<this.answer.orderArray.length; i++){
                if(move.orderArray[1].equals(this.answer.orderArray[i]) && i != 1){
                    whitepegs++;
                    break;
                }
            }

            for(int i=0; i<this.answer.orderArray.length; i++){
                if(move.orderArray[2].equals(this.answer.orderArray[i]) && i != 2){
                    whitepegs++;
                    break;
                }
            }

            for(int i=0; i<this.answer.orderArray.length; i++){
                if(move.orderArray[3].equals(this.answer.orderArray[i]) && i != 3){
                    whitepegs++;
                    break;
                }
            }

//            guessArray[turnCount][0] = move.orderArray[0];
//            guessArray[turnCount][1] = move.orderArray[1];
//            guessArray[turnCount][2] = move.orderArray[2];
//            guessArray[turnCount][3] = move.orderArray[3];



//            if(Arrays.equals(this.answer.orderArray, move.orderArray)){
//                return("victory");
//            }
//            if{
                if(turnCount == 1){
                    this.ongoing = false;
                    return("loss");
                }
//            }


            return("whitepegs:"+whitepegs+
                    "|blackpegs:"+blackpegs+
                    "|hole1:"+move.orderArray[0]+
                    "|hole2:"+move.orderArray[1]+
                    "|hole3:"+move.orderArray[2]+
                    "|hole4:"+move.orderArray[3]
            );
        }

        else{
            System.out.print("The game has ended");
            return(null);
        }

    }
}