package tictactoe;

public class GameRenderer {
    public void render(Game game) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(game.getGameField()[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
