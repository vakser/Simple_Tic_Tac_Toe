package tictactoe;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private StringBuilder gameState = new StringBuilder("         ");
    private String input;
    private final char[][] gameStateArray = new char[3][3];
    private int sumDiagonal1, sumDiagonal2;
    private int sumRow1, sumRow2, sumRow3;
    private int sumCol1, sumCol2, sumCol3;
    private int oNum, xNum;
    private int movesCounter = 0;
    private boolean draw = false;
    private boolean oWin = false;
    private boolean xWin = false;

    public void printGrid() {
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 || i == 4) {
                    System.out.print("-");
                } else if (j == 0 || j == 8) {
                    System.out.print("|");
                } else if (j % 2 == 1) {
                    System.out.print(" ");
                } else {
                    System.out.print(gameState.charAt(k));
                    k++;
                }
            }
            System.out.println();
        }
    }

    public void enterNumbers() {
        System.out.println("You should enter numbers!");
        System.out.print("Enter the coordinates: ");
        input = scanner.nextLine();
    }

    private void enterWithinRange() {
        System.out.println("Coordinates should be from 1 to 3!");
        System.out.print("Enter the coordinates: ");
        input = scanner.nextLine();
    }

    private void chooseFreeCell() {
        System.out.println("This cell is occupied! Choose another one!");
        System.out.print("Enter the coordinates: ");
        input = scanner.nextLine();
    }

    public void buildGameStateArray() {
        int l = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameStateArray[i][j] = gameState.charAt(l);
                l++;
            }
        }
    }

    public void checkCoordinatesInputForValidity() {
        while (input.length() != 3 || !Character.isDigit(input.charAt(0)) || !Character.isDigit(input.charAt(2)) || input.charAt(1) != ' ' || Integer.parseInt(String.valueOf(input.charAt(0))) > 3 || Integer.parseInt(String.valueOf(input.charAt(2))) > 3 || gameStateArray[Integer.parseInt(String.valueOf(input.charAt(0))) - 1][Integer.parseInt(String.valueOf(input.charAt(2))) - 1] != ' ') {
            if (input.length() != 3 || !Character.isDigit(input.charAt(0)) || !Character.isDigit(input.charAt(2)) || input.charAt(1) != ' ') {
                enterNumbers();
            } else if (Integer.parseInt(String.valueOf(input.charAt(0))) > 3 || Integer.parseInt(String.valueOf(input.charAt(2))) > 3) {
                enterWithinRange();
            } else {
                chooseFreeCell();
            }
        }
    }

    public void makeMove() {
        if (movesCounter % 2 == 0) {
            gameStateArray[Integer.parseInt(String.valueOf(input.charAt(0))) - 1][Integer.parseInt(String.valueOf(input.charAt(2))) - 1] = 'X';
            xNum++;
        } else {
            gameStateArray[Integer.parseInt(String.valueOf(input.charAt(0))) - 1][Integer.parseInt(String.valueOf(input.charAt(2))) - 1] = 'O';
            oNum++;
        }
        movesCounter++;
    }

    public void updateGameState() {
        gameState = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameState.append(gameStateArray[i][j]);
            }
        }
    }

    public void printResult() {
        if (oWin) {
            System.out.println("O wins");
        }
        if (xWin) {
            System.out.println("X wins");
        }
        if (draw) {
            System.out.println("Draw");
        }
    }

    public void playGame() {
        while (!oWin && !xWin && !draw) {
            System.out.print("Enter the coordinates: ");
            input = scanner.nextLine();
            checkCoordinatesInputForValidity();
            makeMove();
            updateGameState();
            printGrid();
            calculateWinConditions();
            checkEndCondition();
        }
        printResult();
    }

    public void calculateWinConditions() {
        sumDiagonal1 = gameState.charAt(0) + gameState.charAt(4) + gameState.charAt(8);
        sumDiagonal2 = gameState.charAt(2) + gameState.charAt(4) + gameState.charAt(6);
        sumRow1 = gameState.charAt(0) + gameState.charAt(1) + gameState.charAt(2);
        sumRow2 = gameState.charAt(3) + gameState.charAt(4) + gameState.charAt(5);
        sumRow3 = gameState.charAt(6) + gameState.charAt(7) + gameState.charAt(8);
        sumCol1 = gameState.charAt(0) + gameState.charAt(3) + gameState.charAt(6);
        sumCol2 = gameState.charAt(1) + gameState.charAt(4) + gameState.charAt(7);
        sumCol3 = gameState.charAt(2) + gameState.charAt(5) + gameState.charAt(8);
    }

    public void checkEndCondition() {
        if (sumDiagonal1 == 237 || sumDiagonal2 == 237 || sumRow1 == 237 || sumRow2 == 237 || sumRow3 == 237 || sumCol1 == 237 || sumCol2 == 237 || sumCol3 == 237) {
            oWin = true;
        }
        if (sumDiagonal1 == 264 || sumDiagonal2 == 264 || sumRow1 == 264 || sumRow2 == 264 || sumRow3 == 264 || sumCol1 == 264 || sumCol2 == 264 || sumCol3 == 264) {
            xWin = true;
        }
        if (oNum + xNum == 9 && !oWin && !xWin) {
            draw = true;
        }
    }
}
