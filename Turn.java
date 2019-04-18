package modelpackage;

/**
 * class for storing the a turn's guessed move, as well as earned black pegs or white pegs
 */
public class Turn {

    /**
     * Creates instance of the Move class.
     */
    private Move move;

    /**
     * Keeps track of black pegs on board.
     */
    private int blackpegs;

    /**
     * Keeps track of white pegs on board.
     */
    private int whitepegs;

    /**
     * creates a turn instance which stores information from a guessed turn.
     * @param move guessed move.
     * @param blackpegs number of black pegs this turn.
     * @param whitepegs number of white pegs this turn.
     */

    public Turn(Move move, int blackpegs, int whitepegs) {
        this.move = move;
        this.blackpegs = blackpegs;
        this.whitepegs = whitepegs;
    }

    /**
     * @return returns the number of black pegs.
     */
    public int getBlackpegs() {
        return this.blackpegs;
    }
    /**
     * @return returns the number of white pegs.
     */
    public int getWhitepegs() {
        return this.whitepegs;
    }
    /**
     * @return returns the move.
     */
    public Move getMove() {
        return this.move;
    }

}