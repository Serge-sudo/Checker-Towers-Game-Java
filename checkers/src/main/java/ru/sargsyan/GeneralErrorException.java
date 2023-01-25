package ru.sargsyan;

/**
 * Any other error
 */
public class GeneralErrorException extends RuntimeException {
    /**
     * @param s Error details
     */
    GeneralErrorException(String s) {
        super(s);
    }
}
