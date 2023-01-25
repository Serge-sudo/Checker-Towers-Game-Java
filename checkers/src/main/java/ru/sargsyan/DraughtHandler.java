package ru.sargsyan;

/**
 * class for performing operations on draughts
 */
public class DraughtHandler {
    /**
     * draught on which operations will be performed
     */
    private final Draught myDraught;

    /**
     * @param d draught
     */
    DraughtHandler(Draught d) {
        this.myDraught = d;
    }

    /**
     * makes move
     *
     * @param p  new position
     * @param m  eat or move
     * @param bh tool for working with board
     */
    public void moveDraught(Position p, Mood m, BoardHandler bh) {
        Position ep = myDraught.move(p, m, bh);
        if (ep != null) {
            if (myDraught.getKilledPos().contains(ep)) throw new GeneralErrorException("Can't hit same draught twice");
            myDraught.getKilledPos().add(ep);
        }
    }

    /**
     * checks if there are any eating moves left
     * @param bh board handler
     * @return has current draught eating move
     */
    public boolean hasEatingMovesYet(BoardHandler bh) {
        return myDraught.hasMove(bh) != null;
    }


    /**
     * removes all killed figures from the board
     *
     * @param bh tool for working with board
     */
    public void finalizeKilling(BoardHandler bh) {
        for (Position p : myDraught.getKilledPos()) {
            bh.killFigure(p);
        }
        myDraught.getKilledPos().clear();
    }

    /**
     * gets color of the draught
     *
     * @return get color of draught, if there is no draught to work with return void
     */
    public DraughtColor getColor() {
        if (myDraught == null) return DraughtColor.Void;
        return myDraught.getColor();
    }

    /**
     * gets type of the draught
     *
     * @return type of draught
     */
    public DraughtType getType() {
        return myDraught.getType();
    }

    /**
     * gets position of the draught
     *
     * @return draught's position
     */
    public Position getPosition() {
        return myDraught.getPosition();
    }

    /**
     * gets string rep of position
     *
     * @return draught's position in Chess board notation
     */
    public String getStrPosition() {
        return Position.posAsString(myDraught.getPosition(), myDraught.getType());
    }

    /**
     * @return string representation  of draught
     */
    @Override
    public String toString() {
        return getColor().toString();
    }
}
