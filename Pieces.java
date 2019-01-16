package gamePackage;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import javafx.scene.paint.Color;

public class Pieces extends StackPane {

    private double oldX, oldY;
    private double mouseX, mouseY;
    private Color color;

    public Color getColor(){
        return color;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public Pieces(Color color, int x, int y, boolean moveable){
        this.color = color;

        move(x,y);
        //relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);
        Circle crc = new Circle(Main.TILE_SIZE * .45);
        crc.setFill(color);

        //this formula centers the circle inside the tile
        crc.setTranslateX((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) /2 );
        crc.setTranslateY((Main.TILE_SIZE - Main.TILE_SIZE * .45 * 2) /2 );

        getChildren().add(crc);

        if(moveable) {
            setOnMousePressed(e -> {
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();

            });

            setOnMouseDragged(e -> {
                //relocate(e.getSceneX() - mouseX + (x * Main.TILE_SIZE), e.getSceneY() - mouseY + (y * Main.TILE_SIZE));
                relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);

            });
        }
    }


    public void move(int x, int y){
        oldX = x * Main.TILE_SIZE;
        oldY = y * Main.TILE_SIZE;
        relocate(oldX, oldY);
    }

}
