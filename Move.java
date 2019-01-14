package gameLogic;
import java.util.*;

public class Move {

    String orderArray[] = new String[4];

    public Move(String color1, String color2, String color3, String color4){
        this.orderArray[0] = color1;
        this.orderArray[1] = color2;
        this.orderArray[2] = color3;
        this.orderArray[3] = color4;
    }
}

