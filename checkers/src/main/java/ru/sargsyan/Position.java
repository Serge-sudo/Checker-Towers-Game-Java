package ru.sargsyan;

/**
 * Movement direction of draught
 */
enum MoveDirection {
    UpLeft,
    UpRight,
    DownLeft,
    DownRight;

    /**
     * used in order to figure direction of movement to get from one cell into another
     *
     * @param x number of steps along Alphabet axis
     * @param y number of steps along Number axis
     * @return direction of movement
     */
    public static MoveDirection getDirection(int x, int y) {
        if (x > 0 && y > 0) {
            return UpRight;
        } else if (x > 0 && y < 0) {
            return DownRight;
        } else if (x < 0 && y > 0) {
            return UpLeft;
        } else {
            return DownLeft;
        }
    }
}

/**
 * Class for working with draught position related things
 */
public class Position {

    /**
     * position of draught along Alphabet axis
     */
    private final int xPos;


    /**
     * position of draught along Number axis
     */
    private final int yPos;

    /**
     * @param x position of draught along Alphabet axis
     * @param y position of draught along Number axis
     */
    Position(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    /**
     * Position constructor
     *
     * @param pos user-notation of cells position {ex A1, B4}
     */
    public Position(String pos) {
        pos = pos.toUpperCase();
        this.yPos = pos.charAt(1) - '1';
        this.xPos = pos.charAt(0) - 'A';
    }

    /**
     * make shift in position
     *
     * @param p  position
     * @param md movement direction
     * @param n  number of jumps
     * @return position of object after making n steps along movement direction
     */
    public static Position shift(Position p, MoveDirection md, int n) {
        Position newPos = null;
        switch (md) {
            case UpLeft:
                newPos = new Position(p.xPos - n, p.yPos + n);
                break;

            case UpRight:
                newPos = new Position(p.xPos + n, p.yPos + n);
                break;

            case DownLeft:
                newPos = new Position(p.xPos - n, p.yPos - n);
                break;

            case DownRight:
                newPos = new Position(p.xPos + n, p.yPos - n);
                break;
        }
        return newPos;
    }

    /**
     * gets string representation of position
     *
     * @param p position
     * @param t type of draught
     * @return user-notation of current draught position {ex A1, b1}
     */
    public static String posAsString(Position p, DraughtType t) {
        if (t == DraughtType.Simple) {
            return "" + (char) (p.xPos + 'a') + (char) (p.yPos + '1');
        } else {
            return "" + (char) (p.xPos + 'A') + (char) (p.yPos + '1');
        }
    }

    /**
     * getter
     * @return y pos
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * getter
     * @return x pos
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * @return hash function
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + xPos;
        result = prime * result + yPos;
        return result;
    }

    /**
     * check if two positions are equal
     *
     * @param other other position
     * @return true if positions are the same, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Position)) {
            return false;
        }

        return this.xPos == ((Position) other).xPos && this.yPos == ((Position) other).yPos;
    }

}
