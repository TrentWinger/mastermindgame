package gameLogic;


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

    }


    public String[] getAnswer(){
        String[] temp = new String[4];
        temp[0] = this.answer.orderArray[0];
        temp[1] = this.answer.orderArray[1];
        temp[2] = this.answer.orderArray[2];
        temp[3] = this.answer.orderArray[3];

        return temp;
    }

    private String randomColor(){
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

    public void guess(String color1, String color2, String color3, String color4){
        if(ongoing){


            Move move = new Move(color1, color2, color3, color4);

            String[] temp = {this.answer.orderArray[0], this.answer.orderArray[1], this.answer.orderArray[2], this.answer.orderArray[3]};
            // add another temp array for the one we are comparing to to and then when one is black, make it equal to like "used"
            String[] guessArr = {color1, color2, color3, color4};

            if(move.orderArray[0].equals(this.answer.orderArray[0])){
                blackpegs++;
                temp[0] = null;
                guessArr[0] = "guessed";
            }

            if(move.orderArray[1].equals(this.answer.orderArray[1])){
                blackpegs++;
                temp[1] = null;
                guessArr[1] = "guessed";

            }

            if(move.orderArray[2].equals(this.answer.orderArray[2])){
                blackpegs++;
                temp[2] = null;
                guessArr[2] = "guessed";

            }

            if(move.orderArray[3].equals(this.answer.orderArray[3])){
                blackpegs++;
                temp[3] = null;
                guessArr[3] = "guessed";

            }

            for(int i=0; i<this.answer.orderArray.length; i++){
                if(guessArr[0].equals(temp[i]) && i != 0){
                    whitepegs++;
                    break;
                }
            }

            for(int i=0; i<this.answer.orderArray.length; i++){
                if(guessArr[1].equals(temp[i]) && i != 1){
                    whitepegs++;
                    break;
                }
            }

            for(int i=0; i<this.answer.orderArray.length; i++){
                if(guessArr[2].equals(temp[i]) && i != 2){
                    whitepegs++;
                    break;
                }
            }

            for(int i=0; i<this.answer.orderArray.length; i++){
                if(guessArr[3].equals(temp[i]) && i != 3){
                    whitepegs++;
                    break;
                }
            }




        }

        else{
            System.out.print("The game has ended");

        }

    }
}