package gamePackage;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import javafx.scene.paint.Color;

/**
 * Pieces contains the game pieces which are eventually moved to the guessbox
 * class to be guessed against the key.
 */
class Pieces extends StackPane {

    /**
     * Old position that the mouse was on for X and Y value.
     */
    private double oldX, oldY;

    /**
     * The current location of the mouse for X and Y values.
     */
    private double mouseX, mouseY;

    /**
     * Color of the piece that we are working with.
     */
    private Color color;

    /**
     * gets the color of the current piece.
     * @return returns a color.
     */
    private Color getColor() {
        return color;
    }

    /**
     * gets the value of OldX.
     * @return returns value of OldX
     */
    double getOldX() {
        return oldX;
    }

    /**
     * gets the value of OldY.
     * @return returns value of OldY
     */
    double getOldY() {
        return oldY;
    }

    /**
     * @return returns a string that was converted
     * from the Color.scene.paint.Color 'hex' value.
     */
    String hexToString() {
        String color;
        String temp = getColor().toString();

        switch (temp) {
            case "0x008000ff":
                color = "green";
                break;
            case "0xff0000ff":
                color = "red";
                break;
            case "0x0000ffff":
                color = "blue";
                break;
            case "0xffff00ff":
                color = "yellow";
                break;
            case "0xffa500ff":
                color = "orange";
                break;
            case "0x000000ff":
                color = "black";
                break;
            case "0xffc0cbff":
                color = "pink";
                break;
            case "0x800080ff":
                color = "purple";
                break;
            default: color = "";
        }

        return color;
    }

    /**
     *
     * @param color takes in the color of the piece.
     * @param x x location of the piece.
     * @param y y location of the piece.
     * @param moveable tells us if the piece can be moved.
     */
    Pieces(Color color, int x, int y, boolean moveable, boolean player1) {
        this.color = color;

        move(x, y);
        //relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);
        Circle crc = new Circle(Main.TILE_SIZE * .45);
        crc.setFill(color);

        //this formula centers the circle inside the tile
        crc.setTranslateX((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) / 2);
        crc.setTranslateY((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) / 2);

        getChildren().add(crc);

        if (moveable) {
            setOnMousePressed(e -> {
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();

            });

            setOnMouseDragged(e -> relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY));

        }
    }


    /**
     * moves a piece.
     * @param x x location to be moved to.
     * @param y y location to be moved to.
     */
    void move(int x, int y) {
        oldX = x * Main.TILE_SIZE;
        oldY = y * Main.TILE_SIZE;
        relocate(oldX, oldY);
    }

}
