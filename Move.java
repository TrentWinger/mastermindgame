package modelpackage;

/**
 * class for getting the move that is guessed or the answer.
 */
class Move {

    /**
     * Array which holds the answer to the key.
     */
    String[] orderArray = new String[4];

    /**
     * inputs a string for each of the guesses which is the color of the piece.
     * @param color1 first color.
     * @param color2 second color.
     * @param color3 third color.
     * @param color4 fourth color.
     */
    Move(String color1, String color2, String color3, String color4) {
        this.orderArray[0] = color1;
        this.orderArray[1] = color2;
        this.orderArray[2] = color3;
        this.orderArray[3] = color4;
    }
}