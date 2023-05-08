package tictactoe;

public class Main {

    //the input command is "start/exit user/easy/medium/hard user/easy/medium/hard" for example "start user hard"
    //means that the first player is a human and the second player is a hard AI
    //or start easy easy means that both players are easy AI
    public static void main(String[] args) {
        Game game = new Game();
        game.runGame();
    }

}
