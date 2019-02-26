package gamePackage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * Boxes for the color pieces to be placed in and start at.
 */
class Guessbox extends Rectangle {


    /**
     * Creates a box with a background color and location.
     * @param color color of the background.
     * @param x x location of the box.
     * @param y y location of the box.
     */
    Guessbox(Color color, int x, int y) {
        //Sets the width and height of a tile
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);

        setArcHeight(20);
        setArcWidth(20);

        //places tile on the board in the correct position
        relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);

        //Fill - sets the color (has option to make checkered to change in the future
        //Stroke - deals with the border
        setFill(color);
        setStrokeType(StrokeType.INSIDE);
        setStroke(Color.BLACK);
    }
}
