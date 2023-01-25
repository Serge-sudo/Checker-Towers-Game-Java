package ru.sargsyan;

import java.util.ArrayList;

/**
 * Class that deals with operations on board
 */
public class BoardHandler {
    /**
     * board on which operations should be performed
     */
    private final Board myBoard;

    /**
     * @param b board
     */
    BoardHandler(Board b) {
        myBoard = b;
    }


    /**
     * gets Draught handler of draught from the board
     *
     * @param p position
     * @return DraughtHandler of draught at position p
     */
    public TowerHandler getFigureHandler(Position p) {
        return new TowerHandler(myBoard.getFigure(p));
    }

    /**
     * puts draught at desired position on board
     *
     * @param p position
     * @param d draught
     */
    public void setFigure(Position p, Tower d) {
        myBoard.setFigure(p, d);
    }

    /**
     * puts figure on board
     *
     * @param c color of draught
     * @return if any draught with color c has eating move
     */
    public boolean hasEatingMoves(DraughtColor c) {
        ArrayList<Tower> drs = myBoard.getDraughts(c);
        boolean hasMove = false;
        for (Tower d : drs) {
            hasMove |= d.hasMove(this);
        }
        return hasMove;
    }

    /**
     * gets free cells
     *
     * @param p    position
     * @param dist distance
     * @return all free cells in distance dist from position p on diagonal
     */
    public ArrayList<Position> freeCellsInDistance(Position p, int dist) {
        ArrayList<Position> free = new ArrayList<>();
        MoveDirection[] all = {MoveDirection.UpRight, MoveDirection.UpLeft,
                MoveDirection.DownLeft, MoveDirection.DownRight};
        for (MoveDirection md : all) {
            Position end = Position.shift(p, md, dist);
            if (Board.isInside(end) && getFigureColor(end) == DraughtColor.Void) {
                free.add(end);
            }
        }
        return free;
    }

    /**
     * gets figure color
     *
     * @param p position
     * @return return figure color of draught at position p
     */
    public DraughtColor getFigureColor(Position p) {
        TowerHandler fh = getFigureHandler(p);
        return fh.getColor();
    }

    /**
     * Removes draught on position p from the board
     *
     * @param p position
     */
    public void killFigure(Position p) {
        Tower t = myBoard.getFigure(p);
        Draught d = t.getDraughts().remove(0);
        myBoard.getDraughts(d.getColor()).remove(t);
        if (t.getDraughts().size() == 0) {
            setFigure(p, null);
        } else {
            t.setPosition(p);
            myBoard.getDraughts(t.getColor()).add(t);
        }

    }

    /**
     * makes draught at position p a queen
     *
     * @param p position
     * @return new queen draught
     */
    public TowerHandler makeQueen(Position p) {
        Tower t = myBoard.getFigure(p);
        Draught d = t.getDraughts().remove(0);
        t.getDraughts().add(0, new QueenDraught(p, d.getColor()));
        return new TowerHandler(t);
    }

}
