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
    public DraughtHandler getFigureHandler(Position p) {
        return new DraughtHandler(myBoard.getFigure(p));
    }

    /**
     * puts draught at desired position on board
     *
     * @param p position
     * @param d draught
     */
    public void setFigure(Position p, Draught d) {
        myBoard.setFigure(p, d);
    }

    /**
     * puts figure on board
     *
     * @param c color of draught
     * @return if any draught with color c has eating move
     */
    public boolean hasEatingMoves(DraughtColor c) {
        ArrayList<Draught> drs = myBoard.getDraughts(c);
        boolean hasMove = false;
        for (Draught d : drs) {
            hasMove |= d.hasMove(this) != null;
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
        DraughtHandler fh = getFigureHandler(p);
        return fh.getColor();
    }

    /**
     * Removes draught on position p from the board
     *
     * @param p position
     */
    public void killFigure(Position p) {
        Draught d = myBoard.getFigure(p);
        setFigure(p, null);
        myBoard.getDraughts(d.getColor()).remove(d);
    }

    /**
     * makes draught at position p a queen
     *
     * @param p position
     * @return new queen draught
     */
    public DraughtHandler makeQueen(Position p) {
        Draught d = myBoard.getFigure(p);
        this.killFigure(p);
        myBoard.setFigure(p, new QueenDraught(p, d.getColor()));
        myBoard.getFigure(p).setKilledPos(d.getKilledPos());
        myBoard.getDraughts(d.getColor()).add(myBoard.getFigure(p));
        return new DraughtHandler(myBoard.getFigure(p));
    }

}
