package ru.sargsyan;

/**
 * Tool for working with user input
 */
public class CellView {
    /**
     * Position
     */
    private final Position pos;
    /**
     * Tower content
     */
    private final char[] content;

    /**
     * constructor
     * @param pos position
     * @param c content
     */
    public CellView(Position pos, String c) {
        this.pos = pos;
        this.content = c.toCharArray();
    }

    /**
     * getter
     * @return Position
     */
    public Position getPos() {
        return pos;
    }

    /**
     * getter
     * @return content
     */
    public char[] getContent() {
        return content;
    }
}
