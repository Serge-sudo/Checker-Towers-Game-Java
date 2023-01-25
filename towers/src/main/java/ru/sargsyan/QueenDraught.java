package ru.sargsyan;

import java.util.ArrayList;

/**
 * Queen draught's class
 */
public class QueenDraught extends Draught {
    /**
     * QueenDraught constructor
     *
     * @param p position
     * @param c color
     */
    public QueenDraught(Position p, DraughtColor c) {
        this.setPosition(p);
        this.setColor(c);
    }

    /**
     * @param bh tool to work with board
     * @return true if current draught can eat a draught of the opponent, false otherwise
     */
    public Position hasMove(BoardHandler bh, ArrayList<Position> killedPos) {
        for (int i = 2; i < Board.BOARD_SIZE; ++i) {
            ArrayList<Position> freeCells = bh.freeCellsInDistance(this.getPosition(), i);
            for (Position free : freeCells) {
                ArrayList<Position> between = Board.getCellsInBetween(this.getPosition(), free);
                Position enemyPos = null;
                for (Position dest : between) {
                    if (bh.getFigureColor(dest) == DraughtColor.rev(this.getColor())) {
                        if (enemyPos != null) {
                            enemyPos = null;
                            break;
                        }
                        enemyPos = dest;
                    } else if (bh.getFigureColor(dest) == this.getColor()) {
                        enemyPos = null;
                        break;
                    }
                }
                if (enemyPos != null) {
                    if (!killedPos.isEmpty() && killedPos.contains(enemyPos)) {
                        continue;
                    }
                    return enemyPos;
                }
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
        int deltaX = p.getXPos() - this.getPosition().getXPos();
        int deltaY = p.getYPos() - this.getPosition().getYPos();
        if (Math.abs(deltaX) != Math.abs(deltaY)) throw new GeneralErrorException("Only diagonal move");
        if (m == Mood.Eat) {
            if (Math.abs(deltaX) == 1) throw new GeneralErrorException("Invalid QueenDraught eating move");
            ArrayList<Position> between = Board.getCellsInBetween(this.getPosition(), p);
            for (Position dest : between) {
                if (bh.getFigureColor(dest) == DraughtColor.rev(this.getColor())) {
                    if (enemyPos != null) throw new GeneralErrorException("Can't eat more than one at once");
                    enemyPos = dest;
                } else if (bh.getFigureColor(dest) == this.getColor()) {
                    throw new GeneralErrorException("Can't jump");
                }
            }
            if (enemyPos == null) throw new GeneralErrorException("No enemy to eat");
        } else {
            if (bh.hasEatingMoves(this.getColor())) throw new InvalidMoveException();
            ArrayList<Position> between = Board.getCellsInBetween(this.getPosition(), p);
            for (Position dest : between) {
                if (bh.getFigureColor(dest) != DraughtColor.Void) {
                    throw new GeneralErrorException("Can't jump");
                }
            }
        }
        return enemyPos;
    }

    /**
     * @return string representation of draught, in this case uppercase value of color (W or B)
     */
    @Override
    public String toString() {
        return this.getColor().toString().toUpperCase();
    }
}

