package ru.sargsyan;

import java.util.ArrayList;

/**
 * Draught types on board
 */
enum DraughtType {
    /**
     * Simple Draught
     */
    Simple,
    /**
     * Queen Draught
     */
    Queen
}

/**
 * Draught color on board
 */
enum DraughtColor {
    /**
     * White draught
     */
    White,
    /**
     * Black draught
     */
    Black,
    /**
     * Empty Color
     */
    Void;

    /**
     * @param c color
     * @return return 'antonym' of the color c
     */
    static DraughtColor rev(DraughtColor c) {
        return (c == White) ? Black : White;
    }

    /**
     * @return return string representation of Color
     */
    @Override
    public String toString() {
        if (this == White) {
            return "w";
        } else if (this == Black) {
            return "b";
        } else {
            return "x";
        }
    }
}

/**
 * Abstract class of Draught objects
 */
public abstract class Draught {
    /**
     * position of the draught
     */
    private Position pos;
    /**
     * color of the draught
     */
    private DraughtColor color;
    /**
     * list of all killed items during movements
     */
    private ArrayList<Position> killedPos;

    /**
     * getter
     * @return value of pos
     */
    protected Position getPos() {
        return pos;
    }

    /**
     * setter
     * @param pos new position
     */
    protected void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     * killed list
     * @return killedPos list
     */
    public ArrayList<Position> getKilledPos() {
        return killedPos;
    }

    /**
     * killedPos setter
     *
     * @param kp list of killed figures
     */
    public void setKilledPos(ArrayList<Position> kp) {
        this.killedPos = kp;
    }

    /**
     * gets position of the draught
     *
     * @return position of the draught
     */
    public Position getPosition() {
        return this.pos;
    }

    /**
     * position-setter
     *
     * @param p new position
     */
    public void setPosition(Position p) {
        pos = p;
    }

    /**
     * gets color of the draught
     *
     * @return color of the draught
     */
    public DraughtColor getColor() {
        return color;
    }

    /**
     * setter
     * @param color new color
     */
    public void setColor(DraughtColor color) {
        this.color = color;
    }

    /**
     * moves figure on the board
     *
     * @param np new position
     * @param m  move or eat
     * @param bh tool to work with board
     * @return killed items position, or null if it was just move
     */
    public abstract Position move(Position np, Mood m, BoardHandler bh);

    /**
     * check if draught has eating move
     *
     * @param bh   tool to work with board
     * @return if current draught can eat opponents figure
     */
    public abstract Position hasMove(BoardHandler bh);

    /**
     * gets type of the draught
     *
     * @return type of the draught
     */
    public DraughtType getType() {
        return (this instanceof SimpleDraught) ? DraughtType.Simple : DraughtType.Queen;
    }


}
