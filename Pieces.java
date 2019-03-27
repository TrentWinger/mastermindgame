package gamePackage;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        String pieceColor;
        String temp = getColor().toString();

        switch (temp) {
            case "0x008000ff":
                pieceColor = "green";
                break;
            case "0xff0000ff":
                pieceColor = "red";
                break;
            case "0x0000ffff":
                pieceColor = "blue";
                break;
            case "0xffff00ff":
                pieceColor = "yellow";
                break;
            case "0xffa500ff":
                pieceColor = "orange";
                break;
            case "0x000000ff":
                pieceColor = "black";
                break;
            case "0xffc0cbff":
                pieceColor = "pink";
                break;
            case "0x800080ff":
                pieceColor = "purple";
                break;
            default: pieceColor = "";
        }

        return pieceColor;
    }

    /**
     * function that takes a color in and outputs the file path for the image
     * of the matching color.
     * @param pieceColor Color of the piece that is being used
     * @return returns a file path for an image
     */
    private String colorToImage(final Color pieceColor) {

        String img;
        String temp = pieceColor.toString();

        switch (temp) {
            case "0x008000ff":
                img = "Piece_Green.png";
                break;
            case "0xff0000ff":
                img = "Piece_red.png";
                break;
            case "0x0000ffff":
                img = "Piece_blue.png";
                break;
            case "0xffff00ff":
                img = "Piece_Yellow.png";
                break;
            case "0xffa500ff":
                img = "Piece_Orange.png";
                break;
            case "0x000000ff":
                img = "Piece_Black.png";
                break;
            case "0xffc0cbff":
                img = "Piece_Pink.png";
                break;
            case "0x800080ff":
                img = "Piece_Purple.png";
                break;
            default: img = "";
        }

        return img;
    }

    /**
     *
     * @param pieceColor takes in the color of the piece.
     * @param x x location of the piece.
     * @param y y location of the piece.
     * @param moveable tells us if the piece can be moved.
     */
    Pieces(final Color pieceColor, final int x, final int y, final boolean moveable) {
        this.color = pieceColor;

        move(x, y);
        //relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);
        Circle crc = new Circle(Main.TILE_SIZE * .45);
        Image img = new Image("File:images/" + colorToImage(color));


        ImageView imageView = new ImageView();
        imageView.setImage(img);
        imageView.setFitHeight(Main.TILE_SIZE * .9);
        imageView.setFitWidth(Main.TILE_SIZE * .9);
        imageView.setTranslateX((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) / 2);
        imageView.setTranslateY((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) / 2);



        crc.setFill(pieceColor);

        //this formula centers the circle inside the tile
        crc.setTranslateX((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) / 2);
        crc.setTranslateY((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) / 2);

        getChildren().add(crc);
        getChildren().add(imageView);

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
    void move(final int x, final int y) {
        oldX = x * Main.TILE_SIZE;
        oldY = y * Main.TILE_SIZE;
        relocate(oldX, oldY);
    }

}
