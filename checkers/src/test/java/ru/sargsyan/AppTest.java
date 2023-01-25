package ru.sargsyan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * App tests
 */
public class AppTest {

    @Test
    void simpleTest() {
        String white_pos = "a1 a3 b2 c1 c3 d2 e1 e3 f2 g1 g3 h2";
        String black_pos = "a7 b6 b8 c7 d6 d8 e7 f6 f8 g7 h6 h8";
        String[] moves = {
                "g3-f4 f6-e5", "c3-d4 e5:c3", "b2:d4 d6-c5",
                "d2-c3 g7-f6", "h2-g3 h8-g7", "c1-b2 f6-g5",
                "g3-h4 g7-f6", "f4-e5 f8-g7"
        };

        String answer = "a1 a3 b2 c3 d4 e1 e3 e5 f2 g1 h4\n"
                + "a7 b6 b8 c5 c7 d8 e7 f6 g5 g7 h6";

        Game game_ = new Game(white_pos, black_pos);
        for (String step : moves) {
            String w = step.split(" ")[0];
            String b = step.split(" ")[1];
            game_.makeMove(DraughtColor.White, w);
            game_.makeMove(DraughtColor.Black, b);
        }
        Assertions.assertThat(game_.getDraughtPositionsStr()).isEqualTo(answer);
    }

    @Test
    void simpleTest2() {
        String white_pos = "c3";
        String black_pos = "b2 D4 f6";
        String[] moves = {
                "c3:a1 D4-E5"
        };

        String answer = "a1\n"
                + "E5 f6";

        Game game_ = new Game(white_pos, black_pos);
        for (String step : moves) {
            String w = step.split(" ")[0];
            String b = step.split(" ")[1];
            game_.makeMove(DraughtColor.White, w);
            game_.makeMove(DraughtColor.Black, b);
        }
        Assertions.assertThat(game_.getDraughtPositionsStr()).isEqualTo(answer);
    }

    @Test
    void simpleTest3() {
        String white_pos = "c3";
        String black_pos = "b2 D4 f6";
        String[] moves = {
                "c3:e5:G7 b2-c1"
        };
        String answer = "g7\n"
                + "C1";

        Game game_ = new Game(white_pos, black_pos);
        for (String step : moves) {
            String w = step.split(" ")[0];
            String b = step.split(" ")[1];
            game_.makeMove(DraughtColor.White, w);
            game_.makeMove(DraughtColor.Black, b);
        }
        Assertions.assertThat(game_.getDraughtPositionsStr()).isEqualTo(answer);
    }

    @Test
    void simpleTest4() {
        String white_pos = "C5 c3 a1 e1";
        String black_pos = "b4 e5 g1 g3";
        String[] moves = {
                "C5:A3 e5-f6"
        };

        String answer = "A3 a1 c3 e1\n"
                + "f6 g1 g3";

        Game game_ = new Game(white_pos, black_pos);
        for (String step : moves) {
            String w = step.split(" ")[0];
            String b = step.split(" ")[1];
            game_.makeMove(DraughtColor.White, w);
            game_.makeMove(DraughtColor.Black, b);
        }
        Assertions.assertThat(game_.getDraughtPositionsStr()).isEqualTo(answer);
    }

    @Test
    void simpleTest5() {
        String white_pos = "a1 a3 b2 c1 c3 d2 e1 e3 f2 g1 g3 h2";
        String black_pos = "a7 b6 b8 c7 d6 d8 e7 f6 f8 g7 h6 h8";
        String[] moves = {
                "c3-b4 f6-g5",
                "g3-f4 g7-f6",
                "b2-c3 b6-c5",
                "b4-a5 g5-h4",
                "c3-d4 h8-g7",
                "d4:b6 a7:c5",
                "a1-b2 b8-a7",
                "f4-g5 h6:f4",
                "e3:g5 d6-e5",
                "g5-h6 c7-b6",
                "a5:c7 d8:b6",
                "b2-c3 e5-d4",
                "c3:e5 f6:d4",
                "c1-b2 e7-d6",
                "d2-c3 d4-e3",
                "f2:d4 c5:e3",
                "c3-b4 e3-d2",
                "e1:c3 b6-a5",
                "g1-f2 g7-f6",
                "f2-e3 h4-g3",
                "h2:f4 f6-g5"
        };

        String answer = "a3 b2 b4 c3 e3 f4 h6\n"
                + "a5 a7 d6 f8 g5";

        Game game_ = new Game(white_pos, black_pos);
        for (String step : moves) {
            String w = step.split(" ")[0];
            String b = step.split(" ")[1];
            game_.makeMove(DraughtColor.White, w);
            game_.makeMove(DraughtColor.Black, b);
        }
        Assertions.assertThat(game_.getDraughtPositionsStr()).isEqualTo(answer);
    }

    @Test
    void simpleTest6() {
        String white_pos = "a1 a3 b2 c1 c3 d2 e1 e3 f2 g1 g3 h2";
        String black_pos = "a7 b6 b8 c7 d6 d8 e7 f6 f8 g7 h6 h8";
        String[] moves = {
                "g3-f4 f6-e5",
                "h2-g3 e5-d4",
                "c3:e5 h6-g5",
                "f4:h6 d6:f4:h2",
                "a3-b4 g7-f6",
                "b4-a5 f6-e5",
                "b2-c3 h8-g7",
                "c3-b4 g7-f6",
                "a1-b2 e5-f4",
                "e3:g5 f6:h4",
                "d2-e3 b6-c5",
                "b4:d6 c7:e5",
                "b2-c3 e5-f4",
                "e3:g5 h4:f6",
                "c3-d4 a7-b6",
                "a5:c7 d8:b6"
        };

        String answer = "c1 d4 e1 f2 g1 h6\n"
                + "b6 b8 e7 f6 f8 h2";

        Game game_ = new Game(white_pos, black_pos);
        for (String step : moves) {
            String w = step.split(" ")[0];
            String b = step.split(" ")[1];
            game_.makeMove(DraughtColor.White, w);
            game_.makeMove(DraughtColor.Black, b);
        }
        Assertions.assertThat(game_.getDraughtPositionsStr()).isEqualTo(answer);
    }

    @Test
    void simpleTest7() {
        String white_pos = "b6";
        String black_pos = "c7 e7 e5 c5";
        String[] moves = {
                "b6:d8:f6:d4:a7 \n"
        };

        String answer = "A7\n";
        Game game_ = new Game(white_pos, black_pos);
        for (String step : moves) {
            String w = step.split(" ")[0];
            game_.makeMove(DraughtColor.White, w);
        }
        Assertions.assertThat(game_.getDraughtPositionsStr()).isEqualTo(answer);
    }

    @Test
    void eatingMoveAtTheEnd() {
        String white_pos = "a1";
        String black_pos = "b2 d4";
        String[] moves = {
                "a1:c3 "
        };


        Game game_ = new Game(white_pos, black_pos);
        try {
            for (String step : moves) {
                String w = step.split(" ")[0];
                game_.makeMove(DraughtColor.White, w);
            }
            Assertions.assertThat(true).isEqualTo(false);
        } catch (InvalidMoveException ime) {
        } catch (Exception ex) {
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void doubleHit() {
        String white_pos = "a1";
        String black_pos = "b2";
        String[] moves = {
                "a1:c3:a1 "
        };


        Game game_ = new Game(white_pos, black_pos);
        try {
            for (String step : moves) {
                String w = step.split(" ")[0];
                game_.makeMove(DraughtColor.White, w);
            }
            Assertions.assertThat(true).isEqualTo(false);
        } catch (GeneralErrorException gee) {
            Assertions.assertThat(gee.getMessage()).isEqualTo("Can't hit same draught twice");
        } catch (Exception ex) {
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void outOfBoard() {
        String white_pos = "h8";
        String black_pos = "b2";
        String[] moves = {
                "h8-h7 b2-c3"
        };


        Game game_ = new Game(white_pos, black_pos);
        try {
            for (String step : moves) {
                String w = step.split(" ")[0];
                game_.makeMove(DraughtColor.White, w);
            }
            Assertions.assertThat(true).isEqualTo(false);
        } catch (WhiteCellException wce) {
        } catch (Exception ex) {
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void notingToEat() {
        String white_pos = "a1";
        String black_pos = "d4";
        String[] moves = {
                "a1:c3 "
        };


        Game game_ = new Game(white_pos, black_pos);
        try {
            for (String step : moves) {
                String w = step.split(" ")[0];
                game_.makeMove(DraughtColor.White, w);
            }
            Assertions.assertThat(true).isEqualTo(false);
        } catch (GeneralErrorException bce) {
            Assertions.assertThat(bce.getMessage()).isEqualTo("There is nothing edible on the way!");
        } catch (Exception ex) {
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void friendKiller() {
        String white_pos = "A1 b2";
        String black_pos = "d4";
        String[] moves = {
                "a1:c3 "
        };


        Game game_ = new Game(white_pos, black_pos);
        try {
            for (String step : moves) {
                String w = step.split(" ")[0];
                game_.makeMove(DraughtColor.White, w);
            }
            Assertions.assertThat(true).isEqualTo(false);
        } catch (GeneralErrorException bce) {
            Assertions.assertThat(bce.getMessage()).isEqualTo("Can't jump");
        } catch (Exception ex) {
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void busyCellTest() {
        String white_pos = "A1 b2 g5 h6";
        String black_pos = "b4 c1 d4 d6";
        String[] moves = {
                "b2-a1 d4-e5"
        };

        Game game_ = new Game(white_pos, black_pos);
        try {
            for (String step : moves) {
                String w = step.split(" ")[0];
                String b = step.split(" ")[1];
                game_.makeMove(DraughtColor.White, w);
                game_.makeMove(DraughtColor.Black, b);
            }
            Assertions.assertThat(true).isEqualTo(false);
        } catch (BusyCellException bce) {
        }catch (Exception ex){
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void WCellTest() {
        String white_pos = "b4 c5 E7 g7";
        String black_pos = "E5 f4 f6";
        String[] moves = {
                "E7:G5:E3 E5-F5"
        };

        Game game_ = new Game(white_pos, black_pos);
        try {
            for (String step : moves) {
                String w = step.split(" ")[0];
                String b = step.split(" ")[1];
                game_.makeMove(DraughtColor.White, w);
                game_.makeMove(DraughtColor.Black, b);
            }
            Assertions.assertThat(true).isEqualTo(false);
        } catch (WhiteCellException bce) {
        }catch (Exception ex){
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void invMoveTest() {
        String white_pos = "D8 c5 f6 g3";
        String black_pos = "f4 h6 f8";
        String[] moves = {
                "c5-d6 f4-e3", "d6-e7 h8-g7",
                "c7-d8 g7:e5", "B8:F4:D2 h6-g5"
        };

        Game game_ = new Game(white_pos, black_pos);
        try {
            for (String step : moves) {
                String w = step.split(" ")[0];
                String b = step.split(" ")[1];
                game_.makeMove(DraughtColor.White, w);
                game_.makeMove(DraughtColor.Black, b);
            }
            Assertions.assertThat(true).isEqualTo(false);
        } catch (InvalidMoveException ive) {
        }catch (Exception ex){
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void genErrorTest1() {
        String white_pos = "d2 d6 f2 H2";
        String black_pos = "A3 e3 E5 g5";
        String[] moves = {
                "f2:d4:f6:h4 A3-B6\n"
        };

        Game game_ = new Game(white_pos, black_pos);
        try {
            for (String step : moves) {
                String w = step.split(" ")[0];
                String b = step.split(" ")[1];
                game_.makeMove(DraughtColor.White, w);
                game_.makeMove(DraughtColor.Black, b);
            }
            Assertions.assertThat(true).isEqualTo(false);
        } catch (GeneralErrorException ge) {
        }catch (Exception ex){
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void genErrorTest2() {
        String white_pos = "B6 d2 e3";
        String black_pos = "c3 d4 f4";
        String[] moves = {
                "B6:A5 f4:g5\n"
        };

        Game game_ = new Game(white_pos, black_pos);
        try {
            for (String step : moves) {
                String w = step.split(" ")[0];
                String b = step.split(" ")[1];
                game_.makeMove(DraughtColor.White, w);
                game_.makeMove(DraughtColor.Black, b);
            }
            Assertions.assertThat(true).isEqualTo(false);
        } catch (GeneralErrorException ge) {
        }catch (Exception ex){
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

}

