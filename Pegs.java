package gamePackage;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Peg class which creates the transparent shell for the pegs then later,
 * as colors are being guessed, updates the colors to match results.
 */
class Pegs extends StackPane {


    /**
     * Creates a peg with colors specified by parameters. Places pegs
     * in location relative to the x and y input.
     * @param tlColor Top left color in the peg square.
     * @param trColor Top right color in the peg square.
     * @param blColor Bottom left color in the peg square.
     * @param brColor Bottom right color in the peg square.
     * @param x x location for the peg.
     * @param y y location for the peg.
     */
    Pegs(final Color tlColor, final Color trColor, final Color blColor, final Color brColor, final int x, final int y) {

        Circle topLeftCircle = new Circle(Main.TILE_SIZE * .15);
        Circle topRightCircle = new Circle(Main.TILE_SIZE * .15);
        Circle bottomLeftCircle = new Circle(Main.TILE_SIZE * .15);
        Circle bottomRightCircle = new Circle(Main.TILE_SIZE * .15);



        topLeftCircle.setFill(tlColor);
        topRightCircle.setFill(trColor);
        bottomLeftCircle.setFill(blColor);
        bottomRightCircle.setFill(brColor);

        topLeftCircle.setStroke(Color.BLACK);
        topRightCircle.setStroke(Color.BLACK);
        bottomLeftCircle.setStroke(Color.BLACK);
        bottomRightCircle.setStroke(Color.BLACK);

        topRightCircle.setTranslateX((Main.TILE_SIZE - Main.TILE_SIZE * .15 * 2) / 2);

        bottomLeftCircle.setTranslateY((Main.TILE_SIZE - Main.TILE_SIZE * .15 * 2) / 2);

        bottomRightCircle.setTranslateX((Main.TILE_SIZE - Main.TILE_SIZE * .15 * 2) / 2);
        bottomRightCircle.setTranslateY((Main.TILE_SIZE - Main.TILE_SIZE * .15 * 2) / 2);

        relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);

        getChildren().addAll(topLeftCircle, topRightCircle, bottomLeftCircle, bottomRightCircle);


    }
}
