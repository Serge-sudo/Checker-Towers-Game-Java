package ru.sargsyan;

import java.util.ArrayList;

/**
 * class of ordinary draught
 */
public class SimpleDraught extends Draught {
    /**
     * SimpleDraught constructor
     *
     * @param p position
     * @param c color
     */
    public SimpleDraught(Position p, DraughtColor c) {
        setKilledPos(new ArrayList<>());
        this.setPos(p);
        this.setColor(c);
    }

    /**
     * @param bh   tool to work with board
     * @return true if current draught can eat a draught of opponent, false otherwise
     */
    public Position hasMove(BoardHandler bh) {
        ArrayList<Position> freeCells = bh.freeCellsInDistance(this.getPos(), 2);
        for (Position free : freeCells) {
            Position mid = Board.getCellsInBetween(this.getPos(), free).get(0);
            if (bh.getFigureColor(mid) == DraughtColor.rev(this.getColor())) {
                if (!getKilledPos().isEmpty() && getKilledPos().contains(mid)) {
                    continue;
                }
                return mid;
            }
        }
        return null;
    }

    /**
     * makes move (eating move or just simple position change)
     *
     * @param p  new position
     * @param m  move or eat
     * @param bh tool to work with board
     */
    @Override
    public Position move(Position p, Mood m, BoardHandler bh) {
        Position enemyPos = null;
        int deltaX = p.getXPos() - this.getPos().getXPos();
        int deltaY = p.getYPos() - this.getPos().getYPos();
        if (Math.abs(deltaX) != Math.abs(deltaY)) throw new GeneralErrorException("Only diagonal move");
        if (m == Mood.Eat) {
            if (Math.abs(deltaX) != 2) throw new GeneralErrorException("Invalid Simple Draught eating move");
            enemyPos = Board.getCellsInBetween(this.getPos(), p).get(0);
            if (bh.getFigureColor(enemyPos) != DraughtColor.rev(this.getColor())) {
                throw new GeneralErrorException("There is nothing edible on the way!");
            }
        } else {
            if (Math.abs(deltaX) != 1) throw new GeneralErrorException("Invalid Simple Draught move");
            if (bh.hasEatingMoves(this.getColor())) throw new InvalidMoveException();
        }
        bh.setFigure(p, this);
        bh.setFigure(this.getPos(), null);
        this.setPosition(p);
        return enemyPos;
    }

    /**
     * @return string representation of draught, in this case lowercase value of color (w or b)
     */
    @Override
    public String toString() {
        return this.getColor().toString();
    }

}
