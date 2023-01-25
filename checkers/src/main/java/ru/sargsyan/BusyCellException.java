package ru.sargsyan;

/**
 * Error Type if desired cell is occupied
 */
public class BusyCellException extends RuntimeException {
    /**
     * BusyCellException constructor
     */
    BusyCellException() {
        super("busy cell");
    }
}
