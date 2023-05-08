package tictactoe;

import java.util.Random;

public class HardAIPlayer implements Player {

    @Override
    public void makeMove(Game game) {
        System.out.println("Making move level \"hard\"");
        char[][] gameField = game.getGameField();
        char symbol = game.getSymbol();
        char opponentSymbol = symbol == 'X' ? 'O' : 'X';
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j] == ' ') {
                    gameField[i][j] = symbol;
                    int score = minimax(gameField, 0, false, symbol, opponentSymbol);
                    gameField[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        game.setGameFieldValue(bestMove[0], bestMove[1], symbol);
    }

    public int minimax(char[][] gameField, int depth, boolean isMaximizing, char symbol, char opponentSymbol) {
        int score = evaluate(gameField, symbol, opponentSymbol);
        if (score == 10) {
            return score - depth;
        }
        if (score == -10) {
            return score + depth;
        }
        if (isBoardFull(gameField)) {
            return 0;
        }
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < gameField.length; i++) {
                for (int j = 0; j < gameField[i].length; j++) {
                    if (gameField[i][j] == ' ') {
                        gameField[i][j] = symbol;
                        int score1 = minimax(gameField, depth + 1, false, symbol, opponentSymbol);
                        gameField[i][j] = ' ';
                        bestScore = Math.max(score1, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < gameField.length; i++) {
                for (int j = 0; j < gameField[i].length; j++) {
                    if (gameField[i][j] == ' ') {
                        gameField[i][j] = opponentSymbol;
                        int score1 = minimax(gameField, depth + 1, true, symbol, opponentSymbol);
                        gameField[i][j] = ' ';
                        bestScore = Math.min(score1, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    public int evaluate(char[][] gameField, char symbol, char opponentSymbol) {
        int score = 0;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j] == symbol) {
                    score++;
                } else if (gameField[i][j] == opponentSymbol) {
                    score--;
                }
            }
        }
        if (gameField[0][0] == symbol && gameField[1][1] == symbol && gameField[2][2] == symbol) {
            score = 10;
        } else if (gameField[0][0] == opponentSymbol && gameField[1][1] == opponentSymbol && gameField[2][2] == opponentSymbol) {
            score = -10;
        } else if (gameField[0][2] == symbol && gameField[1][1] == symbol && gameField[2][0] == symbol) {
            score = 10;
        } else if (gameField[0][2] == opponentSymbol && gameField[1][1] == opponentSymbol && gameField[2][0] == opponentSymbol) {
            score = -10;
        } else if (gameField[0][0] == symbol && gameField[0][1] == symbol && gameField[0][2] == symbol) {
            score = 10;
        } else if (gameField[0][0] == opponentSymbol && gameField[0][1] == opponentSymbol && gameField[0][2] == opponentSymbol) {
            score = -10;
        } else if (gameField[1][0] == symbol && gameField[1][1] == symbol && gameField[1][2] == symbol) {
            score = 10;
        } else if (gameField[1][0] == opponentSymbol && gameField[1][1] == opponentSymbol && gameField[1][2] == opponentSymbol) {
            score = -10;
        } else if (gameField[2][0] == symbol && gameField[2][1] == symbol && gameField[2][2] == symbol) {
            score = 10;
        } else if (gameField[2][0] == opponentSymbol && gameField[2][1] == opponentSymbol && gameField[2][2] == opponentSymbol) {
            score = -10;
        } else if (gameField[0][0] == symbol && gameField[1][0] == symbol && gameField[2][0] == symbol) {
            score = 10;
        } else if (gameField[0][0] == opponentSymbol && gameField[1][0] == opponentSymbol && gameField[2][0] == opponentSymbol) {
            score = -10;
        } else if (gameField[0][1] == symbol && gameField[1][1] == symbol && gameField[2][1] == symbol) {
            score = 10;
        } else if (gameField[0][1] == opponentSymbol && gameField[1][1] == opponentSymbol && gameField[2][1] == opponentSymbol) {
            score = -10;
        } else if (gameField[0][2] == symbol && gameField[1][2] == symbol && gameField[2][2] == symbol) {
            score = 10;
        } else if (gameField[0][2] == opponentSymbol && gameField[1][2] == opponentSymbol && gameField[2][2] == opponentSymbol) {
            score = -10;
        }
        return score;
    }

    public boolean isBoardFull(char[][] gameField) {
        boolean isFull = true;
        for (char[] chars : gameField) {
            for (char aChar : chars) {
                if (aChar == ' ') {
                    isFull = false;
                    break;
                }
            }
        }
        return isFull;
    }
}
