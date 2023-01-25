package ru.sargsyan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Apptests
 */
public class AppTest {

    @Test
    void simpleTest() {
        String white_pos = "a7_wbb b2_ww c1_w e1_w f2_w g1_w";
        String black_pos = "b4_bwww b8_b c3_b c7_b e5_bww e7_b f8_b g5_b g7_b h8_b";
        String[] moves = {"b2_ww:d4_wwb:f6_wwbb:d8_wwbbb:b6_wwbbbb b4_bwww-a3_bwww"};

        String answer = "a7_wbb b6_Wwbbbb c1_w e1_w e5_ww f2_w g1_w\n"
                + "a3_bwww b8_b f8_b g5_b g7_b h8_b";

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
    void simpleTest1() {
        String white_pos = "a1_w a3_w b2_w c1_w c3_w d2_w e1_w e3_w f2_w g1_w g3_w h2_w";
        String black_pos = "a7_b b6_b b8_b c7_b d6_b d8_b e7_b f6_b f8_b g7_b h6_b h8_b";
        String[] moves = {
                "g3_w-f4_w f6_b-e5_b", "c3_w-d4_w e5_b:c3_bw", "b2_w:d4_wb d6_b-c5_b",

        };

        String answer = "a1_w a3_w c1_w c3_w d2_w d4_wb e1_w e3_w f2_w f4_w g1_w h2_w\n"
                + "a7_b b6_b b8_b c5_b c7_b d8_b e7_b f8_b g7_b h6_b h8_b";

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
        String white_pos = "d4_w";
        String black_pos = "c3_b E5_B g7_b";
        String[] moves = {
                "d4_w:b2_wb e5_b:a1_bw"
        };

        String answer = "\n"
                + "a1_Bw b2_b g7_b";

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
        String white_pos = "d4_w";
        String black_pos = "c3_b E5_B g7_b";
        String[] moves = {
                "d4_w:f6_wb:h8_wbb c3_b-d2_b", "h8_wbb-b2_wbb d2_b-e1_b"
        };
        String answer = "b2_WBb\n"
                + "e1_B";

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
        String white_pos = "D6_W d4_w b2_w f2_w";
        String black_pos = "b4_b f6_b h2_b h4_b";
        String[] moves = {
                "d6_w:a3_wb f6_b-g7_b"
        };

        String answer = "a3_Wb b2_w d4_w f2_w\n"
                + "g7_b h2_b h4_b";

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
    void doubleHit() {
        String white_pos = "A1_W";
        String black_pos = "b2_b";
        String[] moves = {
                "a1_w:c3_wb:a1_wbb"
        };

        Game game_ = new Game(white_pos, black_pos);
        try {
            for (String step : moves) {
                String w = step.split(" ")[0];
                game_.makeMove(DraughtColor.White, w);
            }
            Assertions.assertThat(true).isEqualTo(false);
        } catch (GeneralErrorException bce) {
            Assertions.assertThat(bce.getMessage()).isEqualTo("Can't hit same tower twice");
        }catch (Exception ex){
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void frindKiller() {
        String white_pos = "A1_W b2_w";
        String black_pos = "d4_b";
        String[] moves = {
                "a1_w:c3_Ww "
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
        }catch (Exception ex){
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void comeBackToSamePos() {
        String white_pos = "b6_w";
        String black_pos = "c7_b f6_b f4_b c5_b";
        String[] moves = {
                "b6_w:d8_wb:g5_wbb:e3_wbbb:b6_wbbbb "
        };

        Game game_ = new Game(white_pos, black_pos);
        for (String step : moves) {
            String w = step.split(" ")[0];
            game_.makeMove(DraughtColor.White, w);
        }
        Assertions.assertThat(game_.getDraughtPositionsStr()).isEqualTo("b6_Wbbbb\n");
    }

    @Test
    void busyCellTest1() {
        String white_pos = "A3_W b4_w f2_w g7_w H8_W";
        String black_pos = "b6_b c3_b d6_b d8_b E1_B";
        String[] moves = {
                "b4_w-a3_w d6_b-e7_b"
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
    void whiteCellTest1() {
        String white_pos = "b2_w c3_w E5_W g5_w";
        String black_pos = "E3_B f2_b f4_b";
        String[] moves = {
                "e5_w:g3_wb:e1_wbb e3_b-f3_b"
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
        } catch (WhiteCellException wce) {
        }catch (Exception ex){
            Assertions.assertThat(true).isEqualTo(false);
        }
    }

    @Test
    void invalidMoveTest() {
        String white_pos = "a1_w c1_w e1_w f2_ww h2_w g5_wbb";
        String black_pos = "a3_b e3_b a5_bww c5_bwww e7_b g7_b b8_b d8_b f8_b h8_b";
        String[] moves = {
                "f2_ww:d4_wwb:b6_wwbb g7_b-f6_b",
                "h2_w-g3_w f6_b:h4_bw:f2_bww",
                "e1_w:g3_wb g5_bb-h4_bb"
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
    void invalidMoveTest1() {
        String white_pos = "D8_W c5_w f6_w g1_w";
        String black_pos = "a5_b f4_b h6_b h8_b";
        String[] moves = {
                "c5_w-d6_w f4_b-e3_b", "d6_w-c7_w h8_b-g7_b",
                "c7_w-b8_w g7_b:e5_bw", "B8_W:F4_Wb:D2Wbb h6_b-g5_b"
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
    void errorTest1() {
        String white_pos = "d2_w d6_w f2_w H2_W";
        String black_pos = "A1_B e3_b E5_b g5_b";
        String[] moves = {
                "f2_w:d4_wb:f6_wbB:h4_wbBb A1_B-A3_B\n"
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
    void errorTest2() {
        String white_pos = "B4_W d2_w e2_w";
        String black_pos = "c3_b d4_b f4_B";
        String[] moves = {
                "B4_W:A3_Wb f4_b:g5_bw\n"
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

