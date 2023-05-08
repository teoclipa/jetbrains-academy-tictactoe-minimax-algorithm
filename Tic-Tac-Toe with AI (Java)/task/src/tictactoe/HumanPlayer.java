package tictactoe;

import java.util.Scanner;

public class HumanPlayer implements Player {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void makeMove(Game game) {
        try {
            System.out.print("Enter the coordinates: ");
            int xCoordinate;
            if (scanner.hasNextInt()) {
                xCoordinate = scanner.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                makeMove(game);
                return;
            }
            int yCoordinate = scanner.nextInt();
            if (xCoordinate > 3 || yCoordinate > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                makeMove(game);
                return;
            }
            if (game.getGameField()[xCoordinate - 1][yCoordinate - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                makeMove(game);
                return;
            }
            game.setGameFieldValue(xCoordinate - 1, yCoordinate - 1, game.getSymbol());

        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            makeMove(game);
        }
    }

}
