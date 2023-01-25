package ru.sargsyan;

/**
 * class for performing operations on draughts
 */
public class TowerHandler {
    /**
     * draught on which operations will be performed
     */
    private final Tower myTower;

    /**
     * @param t draught
     */
    TowerHandler(Tower t) {
        this.myTower = t;
    }

    /**
     * makes move
     *
     * @param p  new position
     * @param m  eat or move
     * @param bh tool for working with board
     */
    public void moveTower(Position p, Mood m, BoardHandler bh) {
        Position enemyPos = myTower.move(p, m, bh);
        if (enemyPos != null) {
            if (myTower.getKilledPos().contains(enemyPos)) {
                throw new GeneralErrorException("Can't hit same tower twice");
            }
            myTower.getKilledPos().add(enemyPos);
            TowerHandler th = bh.getFigureHandler(enemyPos);
            myTower.getDraughts().add(th.myTower.getDraughts().get(0));
        }
    }

    /**
     * gets color of the draught
     *
     * @return get color of draught, if there is no draught to work with return void
     */
    public DraughtColor getColor() {
        if (myTower == null) return DraughtColor.Void;
        return myTower.getColor();
    }

    /**
     * gets type of the draught
     *
     * @return type of draught
     */
    public DraughtType getType() {
        return myTower.getType();
    }

    /**
     * check if there is eating move
     * @param bh board handler
     * @return has current draught eating move
     */
    public boolean hasEatingMovesYet(BoardHandler bh) {
        return myTower.hasMove(bh);
    }

    /**
     * gets position of the draught
     * @return draught's position
     */
    public Position getPosition() {
        return myTower.getPosition();
    }

    /**
     * kills draughts
     * @param bh board handler
     * clears killing list
     */
    public void finalizeKilling(BoardHandler bh) {
        for (Position p : myTower.getKilledPos()) {
            bh.killFigure(p);
        }
        myTower.getKilledPos().clear();
    }

    /**
     * gets string rep of position
     *
     * @return draught's position in Chess board notation
     */
    public String getStrPosition() {
        return Position.posAsString(myTower.getPosition(), myTower.getType());
    }

    /**
     * @return string representation  of draught
     */
    @Override
    public String toString() {
        return this.getStrPosition().toLowerCase() + "_" + myTower.toString();
    }
}
