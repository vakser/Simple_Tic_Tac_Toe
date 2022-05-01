package tictactoe;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Game game = new Game();
        game.printGrid();
        game.buildGameStateArray();
        game.playGame();
    }
}
