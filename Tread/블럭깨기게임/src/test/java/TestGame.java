import org.nhnacademy.tip.BlockBreakGame;

public class TestGame {
    public static void main(String[] args) {
        BlockBreakGame blockBreakGame = new BlockBreakGame(700, 1000);

        blockBreakGame.setVisible(true);
        blockBreakGame.run(10000);
    }
}
