package gamePackage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Guessbox extends Rectangle {


    Pieces piece;

    public boolean hasPiece() {
        return piece != null;
    }

    public Pieces getPiece() {
        return piece;
    }

    public void setPiece(Pieces piece) {
        this.piece = piece;
    }


    public Guessbox(Color color, int x, int y) {
        //Sets the width and height of a tile
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);

        //places tile on the board in the correct position
        relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);

        //Fill - sets the color (has option to make checkered to change in the future
        //Stroke - deals with the border
        setFill(color);
        setStrokeType(StrokeType.INSIDE);
        setStroke(Color.BLACK);
    }
}
