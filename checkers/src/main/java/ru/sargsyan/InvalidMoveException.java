package ru.sargsyan;

/**
 * Error type if draught wants to just move when its has eating opportunity
 */
public class InvalidMoveException extends RuntimeException {
    /**
     * InvalidMoveException constructor
     */
    InvalidMoveException() {
        super("invalid move");
    }
}
