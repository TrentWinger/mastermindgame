package gamePackage;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import javafx.scene.paint.Color;

public class Pieces extends StackPane {

    private double oldX, oldY;
    private double mouseX, mouseY;
    private Color color;

    public Color getColor() {
        return color;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    /**
     * @return returns a string that was converted from the Color.scene.paint.Color 'hex' value.
     */
    public String hexToString() {
        String color = "";
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
        }

        return color;
    }

    public Pieces(Color color, int x, int y, boolean moveable){
        this.color = color;

        move(x, y);
        //relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);
        Circle crc = new Circle(Main.TILE_SIZE * .45);
        crc.setFill(color);

        //this formula centers the circle inside the tile
        crc.setTranslateX((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) /2);
        crc.setTranslateY((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) /2);

        getChildren().add(crc);

        if (moveable) {
            setOnMousePressed(e -> {
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();

            });

            setOnMouseDragged(e -> {
                relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);

            });

        }
    }


    public void move(int x, int y) {
        oldX = x * Main.TILE_SIZE;
        oldY = y * Main.TILE_SIZE;
        relocate(oldX, oldY);
    }

}
