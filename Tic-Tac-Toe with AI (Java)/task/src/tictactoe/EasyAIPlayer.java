package tictactoe;

public class EasyAIPlayer implements Player {
    @Override
    public void makeMove(Game game) {
        while (true) {
            int xCoordinate = (int) (Math.random() * 3);
            int yCoordinate = (int) (Math.random() * 3);
            if (game.getGameField()[xCoordinate][yCoordinate] != ' ') {
                continue;
            }
            System.out.println("Making move level \"easy\"");
            game.setGameFieldValue(xCoordinate, yCoordinate, game.getSymbol());
            break;
        }

    }
}
