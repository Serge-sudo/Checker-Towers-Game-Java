package ru.sargsyan;

import java.util.ArrayList;

/**
 * Tower class
 */
public class Tower {
    /**
     * draughts in tower
     */
    private final ArrayList<Draught> draughts;
    /**
     * killed draughts
     */
    private final ArrayList<Position> killedPos;

    {
        draughts = new ArrayList<>();
        killedPos = new ArrayList<>();
    }

    /**
     * constructor
     */
    public Tower() {
    }

    /**
     * getter
     *
     * @return draughts
     */
    public ArrayList<Draught> getDraughts() {
        return draughts;
    }

    /**
     * getter
     * @return killedPos
     */
    public ArrayList<Position> getKilledPos() {
        return killedPos;
    }

    /**
     * gets draught position
     * @return position of draught
     */
    public Position getPosition() {
        return draughts.get(0).getPosition();
    }

    /**
     * position setter
     *
     * @param p new position
     */
    public void setPosition(Position p) {
        draughts.get(0).setPosition(p);
    }

    /**
     * gets color of the draught
     *
     * @return color of the draught
     */
    public DraughtColor getColor() {
        return draughts.get(0).getColor();
    }

    /**
     * moves figure on the board
     *
     * @param np new position
     * @param m  move or eat
     * @param bh tool to work with board
     * @return position of enemy to be killed, or null if it was just a move
     */
    public Position move(Position np, Mood m, BoardHandler bh) {
        Position enemyPos = draughts.get(0).move(np, m, bh);
        bh.setFigure(np, this);
        bh.setFigure(getPosition(), null);
        setPosition(np);
        return enemyPos;
    }

    /**
     * check if draught has eating move
     *
     * @param bh tool to work with board
     * @return if current draught can eat opponents figure
     */
    public boolean hasMove(BoardHandler bh) {
        return draughts.get(0).hasMove(bh, killedPos) != null;
    }

    /**
     * @return makes string
     */
    @Override
    public String toString() {
        String str = "";
        for (Draught d : draughts) {
            str = str.concat(d.toString());
        }
        return str;

    }

    /**
     * gets type of the draught
     *
     * @return type of the draught
     */
    public DraughtType getType() {
        return draughts.get(0).getType();
    }

}
