package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private final char[][] gameField;
    private char symbol;

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }

    private boolean isFinished;
    private boolean isDraw;
    private Player player1;
    private Player player2;
    private final GameRenderer renderer;
    private final Scanner scanner = new Scanner(System.in);

    public Game() {
        gameField = new char[3][3];
        initGameField();
        symbol = 'X';
        isFinished = false;
        isDraw = false;
        renderer = new GameRenderer();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void initGameField() {
        for (char[] chars : gameField) {
            Arrays.fill(chars, ' ');
        }
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String[] getGameParams() {
        System.out.print("Input command: ");
        String inputCommand = scanner.nextLine().trim();
        if (inputCommand.equals("exit")) {
            return new String[]{"exit"};
        }
        String[] params = inputCommand.split(" ");

        if (params.length != 3) {
            System.out.println("Bad parameters!");
            return getGameParams();
        }
        if (!(params[0].equals("start") || !params[0].equals("exit"))) {
            System.out.println("Bad parameters!");
            return getGameParams();
        }
        if (!(params[1].equals("user") || params[1].equals("easy") || params[1].equals("medium") || params[1].equals("hard"))) {
            System.out.println("Bad parameters!");
            return getGameParams();
        }
        if (!(params[2].equals("user") || params[2].equals("easy") || params[2].equals("medium") || params[2].equals("hard"))) {
            System.out.println("Bad parameters!");
            return getGameParams();
        }
        return params;

    }


    public void runGame() {
        String[] params = getGameParams();
        String choice = params[0].toLowerCase();
        switch (choice) {
            case "exit":
                return;
            case "start":
                switch (params[1]) {
                    case "user" -> player1 = new HumanPlayer();
                    case "easy" -> player1 = new EasyAIPlayer();
                    case "medium" -> player1 = new MediumAIPlayer();
                    case "hard" -> player1 = new HardAIPlayer();
                }
                switch (params[2]) {
                    case "user" -> player2 = new HumanPlayer();
                    case "easy" -> player2 = new EasyAIPlayer();
                    case "medium" -> player2 = new MediumAIPlayer();
                    case "hard" -> player2 = new HardAIPlayer();
                }
                playGame(player1, player2);
        }
    }

    public void playGame(Player player1, Player player2) {
        renderer.render(this);
        while (!isFinished) {
            if (symbol == 'X') {
                player1.makeMove(this);
            } else {
                player2.makeMove(this);
            }
            renderer.render(this);
            checkGameStatus();
        }
        if (isDraw) {
            System.out.println("Draw");
        } else {
            System.out.println(symbol + " wins");
        }
    }

    public char checkGameStatus() {
        if (isWin()) {
            isFinished = true;
        } else if (isDraw()) {
            isFinished = true;
            isDraw = true;
        } else {
            setSymbol(symbol == 'X' ? 'O' : 'X');
        }
        return symbol;
    }

    private boolean isDraw() {
        for (char[] chars : gameField) {
            for (char aChar : chars) {
                if (aChar == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWin() {
        return isWinByRow() || isWinByColumn() || isWinByDiagonal();
    }

    private boolean isWinByRow() {
        for (int i = 0; i < 3; i++) {
            if (gameField[i][0] != ' ' && gameField[i][0] == gameField[i][1] && gameField[i][0] == gameField[i][2]) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinByColumn() {
        for (int i = 0; i < 3; i++) {
            if (gameField[0][i] != ' ' && gameField[0][i] == gameField[1][i] && gameField[0][i] == gameField[2][i]) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinByDiagonal() {
        if (gameField[0][0] != ' ' && gameField[0][0] == gameField[1][1] && gameField[0][0] == gameField[2][2]) {
            return true;
        }
        return gameField[0][2] != ' ' && gameField[0][2] == gameField[1][1] && gameField[0][2] == gameField[2][0];
    }

    public char[][] getGameField() {
        return gameField;
    }

    public void setGameFieldValue(int x, int y, char symbol) {
        gameField[x][y] = symbol;
    }

    public int[] checkBestMove(char symbol) {
        // Check rows for winning move
        for (int row = 0; row < 3; row++) {
            if (gameField[row][0] == symbol && gameField[row][1] == symbol && gameField[row][2] == ' ') {
                return new int[]{row, 2};
            }
            if (gameField[row][0] == symbol && gameField[row][2] == symbol && gameField[row][1] == ' ') {
                return new int[]{row, 1};
            }
            if (gameField[row][1] == symbol && gameField[row][2] == symbol && gameField[row][0] == ' ') {
                return new int[]{row, 0};
            }
        }

        // Check columns for winning move
        for (int col = 0; col < 3; col++) {
            if (gameField[0][col] == symbol && gameField[1][col] == symbol && gameField[2][col] == ' ') {
                return new int[]{2, col};
            }
            if (gameField[0][col] == symbol && gameField[2][col] == symbol && gameField[1][col] == ' ') {
                return new int[]{1, col};
            }
            if (gameField[1][col] == symbol && gameField[2][col] == symbol && gameField[0][col] == ' ') {
                return new int[]{0, col};
            }
        }

        // Check diagonals for winning move
        if (gameField[0][0] == symbol && gameField[1][1] == symbol && gameField[2][2] == ' ') {
            return new int[]{2, 2};
        }
        if (gameField[0][0] == symbol && gameField[2][2] == symbol && gameField[1][1] == ' ') {
            return new int[]{1, 1};
        }
        if (gameField[1][1] == symbol && gameField[2][2] == symbol && gameField[0][0] == ' ') {
            return new int[]{0, 0};
        }
        if (gameField[0][2] == symbol && gameField[1][1] == symbol && gameField[2][0] == ' ') {
            return new int[]{2, 0};
        }
        if (gameField[0][2] == symbol && gameField[2][0] == symbol && gameField[1][1] == ' ') {
            return new int[]{1, 1};
        }
        if (gameField[1][1] == symbol && gameField[2][0] == symbol && gameField[0][2] == ' ') {
            return new int[]{0, 2};
        }
        return new int[]{-1, -1};
    }

}

