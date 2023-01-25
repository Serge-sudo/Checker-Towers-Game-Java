package ru.sargsyan;

/**
 * Error Type if desired cell is white.
 */
public class WhiteCellException extends RuntimeException {
    /**
     * WhiteCellException constructor
     */
    WhiteCellException() {
        super("white cell");
    }
}
