package gamePackage;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Pegs extends StackPane {

    public Pegs(Color color, int x, int y){
        Rectangle box = new Rectangle(Main.TILE_SIZE * .25, Main.TILE_SIZE * .25);
        Circle crc = new Circle(Main.TILE_SIZE * .05);


        relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);


    }
}
