package ru.sargsyan;

import java.util.ArrayList;

/**
 * Class of Checker's Board
 */
public class Board {
    /**
     * Board's size
     */
    static final int BOARD_SIZE = 8;
    /**
     * Array of Draughts on Board
     */
    private final Tower[][] board;
    /**
     * List of white draughts on Board
     */
    private final ArrayList<Tower> whites;
    /**
     * List of black draughts on Board
     */
    private final ArrayList<Tower> blacks;


    /**
     * board constructor
     *
     * @param wp positions of white figures
     * @param bp positions of black figures
     */
    public Board(String wp, String bp) {
        board = new Tower[BOARD_SIZE][BOARD_SIZE];
        whites = new ArrayList<>();
        blacks = new ArrayList<>();
        String[] whitePos = wp.split("\\s+");
        String[] blackPos = bp.split("\\s+");
        if (!wp.isEmpty()) {
            for (String p : whitePos) {
                addFigure(p);
            }
        }
        if (!bp.isEmpty()) {
            for (String p : blackPos) {
                addFigure(p);
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

    private void addFigure(String p) {
        CellView cv = new CellView(new Position(p.split("_")[0]), p.split("_")[1]);
        Tower tower = new Tower();
        for (char c : cv.getContent()) {
            switch (c) {
                case 'w':
                    tower.getDraughts().add(new SimpleDraught(cv.getPos(), DraughtColor.White));
                    break;
                case 'W':
                    tower.getDraughts().add(new QueenDraught(cv.getPos(), DraughtColor.White));
                    break;
                case 'b':
                    tower.getDraughts().add(new SimpleDraught(cv.getPos(), DraughtColor.Black));
                    break;
                case 'B':
                    tower.getDraughts().add(new QueenDraught(cv.getPos(), DraughtColor.Black));
                    break;
                default:
                    throw new GeneralErrorException("Wrong letter");
            }
        }

        board[cv.getPos().getYPos()][cv.getPos().getXPos()] = tower;
        getDraughts(tower.getColor()).add(tower);
    }

    /**
     * get list of draughts
     *
     * @param c Color of the draughts
     * @return list of draughts with desired color
     */
    public ArrayList<Tower> getDraughts(DraughtColor c) {
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
    public void setFigure(Position p, Tower d) {
        board[p.getYPos()][p.getXPos()] = d;
    }

    /**
     * gets figure from the board
     *
     * @param p position
     * @return draught at position p on board
     */
    public Tower getFigure(Position p) {
        return board[p.getYPos()][p.getXPos()];
    }

}
