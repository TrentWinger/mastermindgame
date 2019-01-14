package gameLogic;

import java.util.Arrays;

public class GameInstance {
    private boolean ongoing;
    private int turnCount = 0;
    private Move answer;

    public GameInstance(){
        this.ongoing = true;
        this.answer = new Move("RANDOM","RANDOM","RANDOM","RANDOM"); //Make a list of available colors
    }

    public String guess(String color1, String color2, String color3, String color4){
        if(ongoing){

            int blackpegs = 0;
            int whitepegs = 0;

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

            turnCount ++;

            if(Arrays.equals(this.answer.orderArray, move.orderArray)){
                return("victory");
            }
            else{
                if(this.turnCount >= 12){
                    this.ongoing = false;
                    return("loss");
                }
            }


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
