package com.jminx42.app;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int turnNum = 1;
        char[][] array = new char[3][3];
        for (int i = 0; i < array.length; i++) {
            Arrays.fill(array[i], ' ');
        }
        printBoard(array);

        while (!gameOver(array)) {
            String input = getCoordinates(array);
            array = setCoordinates(input, array, turn(turnNum));
            turnNum++;
            printBoard(array);
        }
    }

    public static void printBoard(char[][] twoDimArray) {

        System.out.println("---------");


        for (int i = 0; i < twoDimArray.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < twoDimArray[i].length; j++) {
                System.out.print(twoDimArray[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }

        System.out.println("---------");

    }

    public static String getCoordinates(char[][] twoDimArray) {
        Scanner scanner = new Scanner(System.in);

        String UserInput = "";

        do {
            System.out.print("Enter the coordinates: ");
            UserInput = scanner.nextLine();
        } while (!coordinatesCorrect(UserInput, twoDimArray));

        return UserInput;
    }

    public static boolean coordinatesCorrect(String input, char[][] twoDimArray) {
        boolean correct = true;

        //check if input = numerical values
        char[] userInputAr = input.toCharArray();
        //check if 2 numerical values are entered
        if (userInputAr.length != 3) {
            System.out.println("You should enter numbers!");
            correct = false;
        } else if (userInputAr[0] > '3' || userInputAr[2] > '3') {
            System.out.println("You should enter numbers!");
            correct = false;
        } else {

            int uIA1 = Integer.parseInt(userInputAr[0] + "");
            int uIA2 = Integer.parseInt(userInputAr[2] + "");

            //check if both numerical values are either 1, 2 or 3
            if (uIA1 == 0 || uIA1 > 3 || uIA2 == 0 || uIA2 > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                correct = false;
            } else if (twoDimArray[uIA1-1][uIA2-1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                correct = false;
            }

        }


        return correct;
    }

    public static char[][] setCoordinates(String input, char[][] twoDimArray, char turn) {

        char[] userInputAr = input.toCharArray();
        int uIA1 = Integer.parseInt(userInputAr[0] + "");
        int uIA2 = Integer.parseInt(userInputAr[2] + "");

        twoDimArray[uIA1-1][uIA2-1] = turn;

        return twoDimArray;
    }

    public static char turn(int turnNum) {
        char turn;

        if (turnNum % 2 == 0) {
            turn = 'O';
        } else {
            turn = 'X';
        }

        return turn;
    }

    public static boolean gameOver(char[][] input) {
        boolean endGame = false;

        int Xs = 0;
        int Os = 0;
        int blank = 0;
        String verdict = "";

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == 'X') {
                    Xs++;
                } else if (input[i][j] == 'O') {
                    Os++;
                } else if (input[i][j] == ' ') {
                    blank++;
                }
            }
        }

        //ROWS

        if (input[0][0] == input[0][1] && input[0][1] == input[0][2] && input[0][0] != ' ') {
            verdict = input[0][0] + " wins";
            endGame = true;

        }

        if (input[1][0] == input[1][1] && input[1][1] == input[1][2] && input[1][0] != ' ') {
            verdict = input[1][0] + " wins";
            endGame = true;
        }

        if (input[2][0] == input[2][1] && input[2][1] == input[2][2] && input[2][0] != ' ') {
            verdict = input[2][0] + " wins";
            endGame = true;
        }

        // COLUMNS

        if (input[0][0] == input[1][0] && input[1][0] == input[2][0] && input[0][0] != ' ') {
            verdict = input[0][0] + " wins";
            endGame = true;
        }

        if (input[0][1] == input[1][1] && input[1][1] == input[2][1] && input[0][1] != ' ') {
            verdict = input[0][1] + " wins";
            endGame = true;
        }

        if (input[0][2] == input[1][2] && input[1][2] == input[2][2] && input[0][2] != ' ') {
            verdict = input[0][2] + " wins";
            endGame = true;
        }

        // DIAGONAL

        if (input[0][0] == input[1][1] && input[1][1] == input[2][2] && input[0][0] != ' ') {
            verdict = input[0][0] + " wins";
            endGame = true;
        }

        if (input[0][2] == input[1][1] && input[1][1] == input[2][0] && input[0][2] != ' ') {
            verdict = input[0][2] + " wins";
            endGame = true;
        }

        //checking if draw
        if (blank == 0 && (Xs + Os == 9) && verdict.isEmpty()) {
            verdict = "Draw";
            endGame = true;
        }

        if (endGame) {
            System.out.println(verdict);
        }

        return endGame;
    }
}
