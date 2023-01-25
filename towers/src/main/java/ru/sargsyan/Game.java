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
    public void validateInput(Triple<CellView, Mood, ArrayList<CellView>> mv, DraughtColor c) {
        if (Board.isWhite(mv.getFirst().getPos())) throw new WhiteCellException();
        if (!Board.isInside(mv.getFirst().getPos())) throw new GeneralErrorException("Cell is out of Border");
        TowerHandler dh = this.bh.getFigureHandler(mv.getFirst().getPos());
        if (dh.getColor() == DraughtColor.Void) throw new GeneralErrorException("There is no Draught to move");
        if (dh.getColor() != c) throw new GeneralErrorException("Not your turn");
        for (int i = 0; i < mv.getThird().size(); ++i) {
            if (Board.isWhite(mv.getThird().get(i).getPos())) {
                throw new WhiteCellException();
            }
            if (!Board.isInside(mv.getThird().get(i).getPos())) {
                throw new GeneralErrorException("Cell is out of Border");
            }
            TowerHandler dest = this.bh.getFigureHandler(mv.getThird().get(i).getPos());
            if (dest.getColor() != DraughtColor.Void && !(i != 0
                    && mv.getFirst().getPos().equals(mv.getThird().get(i).getPos()))) {
                throw new BusyCellException();
            }
        }
    }

    /**
     * @param m moves of draughts in user-notation {ex A1-B2 or A1: C3}
     * @param c color of draught
     * @return {Start of movement,Intention,List of next positions} more convenient representation
     */
    private Triple<CellView, Mood, ArrayList<CellView>> moveInterpreter(String m, DraughtColor c) {
        if (!m.matches("([a-hA-H][1-8]_[wWbB]+-[a-hA-H][1-8]_[wWbB]+)|"
                + "(([a-hA-H][1-8]_[wWbB]+:)+[a-hA-H][1-8]_[wWbB]+)")) {
            throw new GeneralErrorException("Syntax error");
        }
        ArrayList<CellView> moves = new ArrayList<>();
        CellView start;
        Mood md;
        if (m.indexOf('-') != -1) {
            md = Mood.Move;
            String[] steps = m.split("-");

            start = new CellView(new Position(steps[0].split("_")[0]), steps[0].split("_")[1]);
            moves.add(new CellView(new Position(steps[1].split("_")[0]), steps[1].split("_")[1]));
        } else {
            md = Mood.Eat;
            String[] steps = m.split(":");
            start = new CellView(new Position(steps[0].split("_")[0]), steps[0].split("_")[1]);
            for (int i = 1; i < steps.length; ++i) {
                moves.add(new CellView(new Position(steps[i].split("_")[0]), steps[i].split("_")[1]));
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
        TowerHandler dh = this.bh.getFigureHandler(stepList.getFirst().getPos());
        for (int i = 0; i < stepList.getThird().size(); ++i) {
            dh.moveTower(stepList.getThird().get(i).getPos(), stepList.getSecond(), bh);
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
     * gets positions of draughts in user-notation
     *
     * @return positions of white and black draughts
     */
    public String getDraughtPositionsStr() {
        ArrayList<String> whiteDraught = new ArrayList<>();
        ArrayList<String> blackDraught = new ArrayList<>();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                TowerHandler dh = bh.getFigureHandler(new Position(i, j));
                if (dh.getColor() == DraughtColor.Void) continue;
                if (dh.getColor() == DraughtColor.Black) blackDraught.add(dh.toString());
                if (dh.getColor() == DraughtColor.White) whiteDraught.add(dh.toString());
            }
        }
        return String.join(" ", whiteDraught) + "\n" + String.join(" ", blackDraught);
    }

}

