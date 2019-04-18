package viewpackage;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;

/**
 * Pieces contains the game pieces which are eventually moved to the guessbox
 * class to be guessed against the key.
 */
class Pieces extends StackPane {

    /**
     * Image variabled used for different themes.
     */
    private String img;

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
     * Gets the image of the piece.
     * @return a string containing the image.
     */
    public String getImg() {
        return img;
    }

    /**
     * Sets the image to the parameter.
     * @param img input of which string to set the image to.
     */
    public void setImg(final String img) {
        this.img = img;
    }

    /**
     * Gets the color of the current piece.
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
    private String colorToDefault(final Color pieceColor) {

        String img;
        String temp = pieceColor.toString();

        switch (temp) {
            case "0x008000ff":
                img = "Default/Piece_Green.png";
                break;
            case "0xff0000ff":
                img = "Default/Piece_red.png";
                break;
            case "0x0000ffff":
                img = "Default/Piece_blue.png";
                break;
            case "0xffff00ff":
                img = "Default/Piece_Yellow.png";
                break;
            case "0xffa500ff":
                img = "Default/Piece_Orange.png";
                break;
            case "0x000000ff":
                img = "Default/Piece_Black.png";
                break;
            case "0xffc0cbff":
                img = "Default/Piece_Pink.png";
                break;
            case "0x800080ff":
                img = "Default/Piece_Purple.png";
                break;
            case "0x808080ff":
                img = "Default/Piece_Grey.png";
                break;
            default: img = "";
        }

        this.img = img;
        return img;
    }

    /**
     * function that takes a color in and outputs the file path for the image
     * of the matching color for this theme.
     * @param pieceColor Color of the piece that is being used
     * @return returns a file path for an image
     */
    private String colorToCookie(final Color pieceColor) {
        String img;
        String temp = pieceColor.toString();

        switch (temp) {
            case "0x008000ff":
                img = "Cookie/Piece_Cookie_Green.png";
                break;
            case "0xff0000ff":
                img = "Cookie/Piece_Cookie_red.png";
                break;
            case "0x0000ffff":
                img = "Cookie/Piece_Cookie_blue.png";
                break;
            case "0xffff00ff":
                img = "Cookie/Piece_Cookie_Yellow.png";
                break;
            case "0xffa500ff":
                img = "Cookie/Piece_Cookie_Orange.png";
                break;
            case "0x000000ff":
                img = "Cookie/Piece_Cookie_Black.png";
                break;
            case "0xffc0cbff":
                img = "Cookie/Piece_Cookie_Pink.png";
                break;
            case "0x800080ff":
                img = "Cookie/Piece_Cookie_Purple.png";
                break;
            case "0x808080ff":
                img = "Cookie/Piece_Cookie_Grey.png";
                break;
            default: img = "";
        }

        this.img = img;
        return img;
    }

    /**
     * function that takes a color in and outputs the file path for the image
     * of the matching color for this theme.
     * @param pieceColor Color of the piece that is being used
     * @return returns a file path for an image
     */
    private String colorToCyber(final Color pieceColor) {

        String img;
        String temp = pieceColor.toString();

        switch (temp) {
            case "0x008000ff":
                img = "Cyber/Piece_Cyber_Green.png";
                break;
            case "0xff0000ff":
                img = "Cyber/Piece_Cyber_red.png";
                break;
            case "0x0000ffff":
                img = "Cyber/Piece_Cyber_blue.png";
                break;
            case "0xffff00ff":
                img = "Cyber/Piece_Cyber_Yellow.png";
                break;
            case "0xffa500ff":
                img = "Cyber/Piece_Cyber_Orange.png";
                break;
            case "0x000000ff":
                img = "Cyber/Piece_Cyber_Black.png";
                break;
            case "0xffc0cbff":
                img = "Cyber/Piece_Cyber_Pink.png";
                break;
            case "0x800080ff":
                img = "Cyber/Piece_Cyber_Purple.png";
                break;
            case "0x808080ff":
                img = "Cyber/Piece_Cyber_Grey.png";
                break;
            default: img = "";
        }

        this.img = img;
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
        //Circle crc = new Circle(Main.TILE_SIZE * .45);
        Image theme;
        if (Main.theme == 1) {
            theme = new Image("File:images/" + colorToCyber(color));
        } else if (Main.theme == 2) {
            theme = new Image("File:images/" + colorToCookie(color));

        } else {
            theme = new Image("File:images/" + colorToDefault(color));
        }



        ImageView imageView = new ImageView();
        imageView.setImage(theme);
        imageView.setFitHeight(Main.TILE_SIZE * .9);
        imageView.setFitWidth(Main.TILE_SIZE * .9);
        imageView.setTranslateX((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) / 2);
        imageView.setTranslateY((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) / 2);



        //crc.setFill(pieceColor);

        //this formula centers the circle inside the tile
        //crc.setTranslateX((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) / 2);
        //crc.setTranslateY((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) / 2);

        //getChildren().add(crc);
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
