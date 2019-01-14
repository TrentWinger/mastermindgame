package gamePackage;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import javafx.scene.paint.Color;

public class Pieces extends StackPane {

    private Color color;

    public Color getColor(){
        return color;
    }
    public Pieces(Color color, int x, int y){
        this.color = color;

        relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);
        Circle crc = new Circle(Main.TILE_SIZE * .45);
        crc.setFill(color);

        //this formula centers the circle inside the tile
        crc.setTranslateX((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) /2 );
        crc.setTranslateY((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) /2 );

        getChildren().add(crc);

    }

}
