package ru.sargsyan;

import java.util.ArrayList;

/**
 * intention of draught move
 */
enum Mood {
    Eat,
    Move
}

/**
 * Main Class with which Player is working
 */
public class Game {
    /**
     * tool for working with board
     */
    private final BoardHandler bh;

    /**
     * @param wp white draught position
     * @param bp black draught position
     */
    Game(String wp, String bp) {
        bh = new BoardHandler(new Board(wp, bp));
    }

    /**
     * checks if the user input is valid
     *
     * @param mv moves {Start of movement,Intention,List of next positions}
     * @param c  color of draught
     */
    public void validateInput(Triple<Position, Mood, ArrayList<Position>> mv, DraughtColor c) {
        if (Board.isWhite(mv.getFirst())) throw new WhiteCellException();
        if (!Board.isInside(mv.getFirst())) throw new GeneralErrorException("Cell is out of Border");
        DraughtHandler dh = this.bh.getFigureHandler(mv.getFirst());
        if (dh.getColor() == DraughtColor.Void) throw new GeneralErrorException("There is no Draught to move");
//        if (dh.getColor() != c) throw new GeneralErrorException("Not your turn");
        for (int i = 0; i < mv.getThird().size(); ++i) {
            if (Board.isWhite(mv.getThird().get(i))) throw new WhiteCellException();
            if (!Board.isInside(mv.getThird().get(i))) throw new GeneralErrorException("Cell is out of Border");
            DraughtHandler dest = this.bh.getFigureHandler(mv.getThird().get(i));
            if (dest.getColor() != DraughtColor.Void && !(i != 0 && mv.getFirst().equals(mv.getThird().get(i)))) {
                throw new BusyCellException();
            }
        }
    }

    /**
     * @param m moves of draughts in user-notation {ex A1-B2 or A1: C3}
     * @param c color of draught
     * @return {Start of movement,Intention,List of next positions} more convenient representation
     */
    private Triple<Position, Mood, ArrayList<Position>> moveInterpreter(String m, DraughtColor c) {
        if (!m.matches("([a-hA-H][1-8]-[a-hA-H][1-8])|(([a-hA-H][1-8]:)+[a-hA-H][1-8])")) {
            throw new GeneralErrorException("Syntax error");
        }
        ArrayList<Position> moves = new ArrayList<>();
        Position start;
        Mood md;
        if (m.indexOf('-') != -1) {
            md = Mood.Move;
            String[] steps = m.split("-");
            start = new Position(steps[0]);
            moves.add(new Position(steps[1]));
        } else {
            md = Mood.Eat;
            String[] steps = m.split(":");
            start = new Position(steps[0]);
            for (int i = 1; i < steps.length; ++i) {
                moves.add(new Position(steps[i]));
            }
        }
        var result = new Triple<>(start, md, moves);
        validateInput(result, c);
        return result;
    }

    /**
     * checks if input is valid, converts it into convenient notion and then makes moves
     *
     * @param c draught color whose turn is to play
     * @param m user-notation of moves
     */
    public void makeMove(DraughtColor c, String m) {
        if (m.isEmpty()) {
            return;
        }
        var stepList = moveInterpreter(m, c);
        DraughtHandler dh = this.bh.getFigureHandler(stepList.getFirst());
        for (int i = 0; i < stepList.getThird().size(); ++i) {
            dh.moveDraught(stepList.getThird().get(i), stepList.getSecond(), bh);
            if (dh.getType() != DraughtType.Queen && Board.isQueenieSpot(dh.getColor(), dh.getPosition())) {
                dh = bh.makeQueen(dh.getPosition());
            }
        }
        if (stepList.getSecond() == Mood.Eat) {
            if (dh.hasEatingMovesYet(bh)) {
                throw new InvalidMoveException();
            }
        }
        dh.finalizeKilling(bh);
    }

    /**
     * prints board --for debug only
     */
    public void printBoard() {
        for (int i = Board.BOARD_SIZE - 1; i >= 0; --i) {
            for (int j = 0; j < Board.BOARD_SIZE; ++j) {
                System.out.print(bh.getFigureColor(new Position(j, i)));
            }
            System.out.println();
        }
    }

    /**
     * gets positions of draughts in user-notation
     *
     * @return positions of white and black draughts
     */
    public String getDraughtPositionsStr() {
        ArrayList<String> whiteDraught = new ArrayList<>();
        ArrayList<String> blackDraught = new ArrayList<>();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                DraughtHandler dh = bh.getFigureHandler(new Position(i, j));
                if (dh.getColor() == DraughtColor.Void) continue;
                if (dh.getType() == DraughtType.Queen) {
                    if (dh.getColor() == DraughtColor.Black) blackDraught.add(dh.getStrPosition());
                    if (dh.getColor() == DraughtColor.White) whiteDraught.add(dh.getStrPosition());
                }
            }
        }
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                DraughtHandler dh = bh.getFigureHandler(new Position(i, j));
                if (dh.getColor() == DraughtColor.Void) continue;
                if (dh.getType() == DraughtType.Simple) {
                    if (dh.getColor() == DraughtColor.Black) blackDraught.add(dh.getStrPosition());
                    if (dh.getColor() == DraughtColor.White) whiteDraught.add(dh.getStrPosition());
                }
            }
        }
        return String.join(" ", whiteDraught) + "\n" + String.join(" ", blackDraught);
    }

}
