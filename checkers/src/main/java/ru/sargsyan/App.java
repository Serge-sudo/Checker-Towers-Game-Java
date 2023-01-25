package ru.sargsyan;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * App
 */
final class App {
    /**
     * Private constructor
     */
    private App() {

    }


    /**
     * entry point of program
     *
     * @param args arguments
     */
    public static void main(String[] args) throws java.io.IOException {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String wp = in.readLine();
            String bp = in.readLine();
            Game game = new Game(wp, bp);
            String moves;
            while ((moves = in.readLine()) != null) {
                String[] moveList = moves.split("\\s+");
                game.makeMove(DraughtColor.White, moveList[0]);
                if (moveList.length > 1) {
                    game.makeMove(DraughtColor.Black, moveList[1]);
                }
            }
            System.out.println(game.getDraughtPositionsStr());
        } catch (InvalidMoveException ex) {
            System.out.println("invalid move");
        } catch (BusyCellException ex) {
            System.out.println("busy cell");
        } catch (WhiteCellException ex) {
            System.out.println("white cell");
        } catch (GeneralErrorException ex) {
            System.out.println("error");
        }
    }
}

