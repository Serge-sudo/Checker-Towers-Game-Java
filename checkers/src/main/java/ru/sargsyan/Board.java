package ru.sargsyan;

import java.util.ArrayList;

/**
 * Class of Checker's Board
 */
public class Board {
    /**
     * Board's size
     */
    public static final int BOARD_SIZE = 8;
    /**
     * Array of Draughts on Board
     */
    private final Draught[][] board;
    /**
     * List of white draughts on Board
     */
    private final ArrayList<Draught> whites;
    /**
     * List of black draughts on Board
     */
    private final ArrayList<Draught> blacks;

    /**
     * board constructor
     *
     * @param wp positions of white figures
     * @param bp positions of black figures
     */
    public Board(String wp, String bp) {
        board = new Draught[BOARD_SIZE][BOARD_SIZE];
        whites = new ArrayList<>();
        blacks = new ArrayList<>();
        String[] whitePos = wp.split(" ");
        String[] blackPos = bp.split(" ");
        if (!wp.isEmpty()) {
            for (String p : whitePos) {
                Position pos = new Position(p);
                if (!Board.isInside(pos)) throw new GeneralErrorException("Cell is out of Border");
                if (Board.isWhite(pos)) throw new WhiteCellException();

                if (Character.isUpperCase(p.charAt(0))) {
                    board[pos.getYPos()][pos.getXPos()] = new QueenDraught(pos, DraughtColor.White);
                } else {
                    board[pos.getYPos()][pos.getXPos()] = new SimpleDraught(pos, DraughtColor.White);
                }
                whites.add(board[pos.getYPos()][pos.getXPos()]);
            }
        }
        if (!bp.isEmpty()) {
            for (String p : blackPos) {
                Position pos = new Position(p);
                if (!Board.isInside(pos)) throw new GeneralErrorException("Cell is out of Border");
                if (Board.isWhite(pos)) throw new WhiteCellException();

                if (Character.isUpperCase(p.charAt(0))) {
                    board[pos.getYPos()][pos.getXPos()] = new QueenDraught(pos, DraughtColor.Black);
                } else {
                    board[pos.getYPos()][pos.getXPos()] = new SimpleDraught(pos, DraughtColor.Black);
                }
                blacks.add(board[pos.getYPos()][pos.getXPos()]);
            }
        }
    }

    /**
     * checks if position is inside the board
     *
     * @param p position
     * @return true if position is inside the board, false if not
     */
    public static boolean isInside(Position p) {
        return p.getXPos() < BOARD_SIZE && p.getXPos() >= 0 && p.getYPos() < BOARD_SIZE && p.getYPos() >= 0;
    }

    /**
     * checks if position is white cell
     *
     * @param p position
     * @return true if position is white cell, false otherwise
     */
    public static boolean isWhite(Position p) {
        return (p.getXPos() + p.getYPos()) % 2 != 0;
    }

    /**
     * checks if draught currently should become a queen
     *
     * @param c Color of figure
     * @param p Current Position
     * @return true if figure is on the opposite border of board, false otherwise
     */
    public static boolean isQueenieSpot(DraughtColor c, Position p) {
        if (c == DraughtColor.White) {
            return p.getYPos() == BOARD_SIZE - 1;
        } else {
            return p.getYPos() == 0;
        }
    }

    /**
     * Returns positions of cells in between
     *
     * @param start position of the start
     * @param end   position of the end
     * @return list of positions in between by diagonal
     */
    public static ArrayList<Position> getCellsInBetween(Position start, Position end) {
        ArrayList<Position> between = new ArrayList<>();
        MoveDirection md = MoveDirection.getDirection(end.getXPos() - start.getXPos(), end.getYPos() - start.getYPos());
        for (int i = 1; i < Math.abs(end.getXPos() - start.getXPos()); ++i) {
            between.add(Position.shift(start, md, i));
        }
        return between;
    }

    /**
     * get list of draughts
     *
     * @param c Color of the draughts
     * @return list of draughts with desired color
     */
    public ArrayList<Draught> getDraughts(DraughtColor c) {
        if (c == DraughtColor.White) {
            return whites;
        } else {
            return blacks;
        }
    }

    /**
     * puts draught at desired position on board
     *
     * @param p position
     * @param d draught
     */
    public void setFigure(Position p, Draught d) {
        board[p.getYPos()][p.getXPos()] = d;
    }

    /**
     * gets figure from the board
     *
     * @param p position
     * @return draught at position p on board
     */
    public Draught getFigure(Position p) {
        return board[p.getYPos()][p.getXPos()];
    }

}
