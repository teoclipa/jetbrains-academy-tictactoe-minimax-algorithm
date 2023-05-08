package tictactoe;

public class MediumAIPlayer implements Player {
    @Override
    public void makeMove(Game game) {
        int[] winningMove = game.checkBestMove(game.getSymbol());
        if (winningMove[0] != -1) {
            game.setGameFieldValue(winningMove[0], winningMove[1], game.getSymbol());
            System.out.println("Making move level \"medium\"");
            return;
        }

        char opponentSymbol = game.getSymbol() == 'X' ? 'O' : 'X';
        int[] blockMove = game.checkBestMove(opponentSymbol);

        if (blockMove[0] != -1) {
            game.setGameFieldValue(blockMove[0], blockMove[1], game.getSymbol());
            System.out.println("Making move level \"medium\"");
            return;
        }

        while (true) {
            int xCoordinate = (int) (Math.random() * 3);
            int yCoordinate = (int) (Math.random() * 3);
            if (game.getGameField()[xCoordinate][yCoordinate] != ' ') {
                continue;
            }
            System.out.println("Making move level \"medium\"");
            game.setGameFieldValue(xCoordinate, yCoordinate, game.getSymbol());
            break;
        }
    }
}
