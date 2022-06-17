package com.company;

import java.util.Scanner;

public class Main {

    public static void printField (char [][] field){
        System.out.println("---------");
        for (int i=0; i<3; i++) {
            System.out.print("|"+" ");
            for (int j=0; j<3; j++) {
                System.out.print(field[i] [j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void main(String[] args) {
        Scanner readMachine = new Scanner(System.in);
        char [] [] field = new char[3][3];
        int x=0,y=0;
        int rowx=0, rowzero=0, cross=0,zero=0, space=0;

        // fill array

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                field [i] [j] = '_';
            }
        }

        //print field

        printField(field);

        for (int it=1;it<=9;it++) {

            //Retrieve move
            System.out.println("Enter the coordinates:");
            String trash;
            while (x == 0 || y == 0) {
                if (readMachine.hasNextInt()) {
                    x = readMachine.nextInt();
                } else {
                    System.out.println("You should enter numbers!");
                    System.out.println("Enter the coordinates:");
                    x = 0;
                    y = 0;
                    trash = readMachine.nextLine();
                }
                if (readMachine.hasNextInt()) {
                    y = readMachine.nextInt();
                } else {
                    System.out.println("You should enter numbers!");
                    System.out.println("Enter the coordinates:");
                    x = 0;
                    y = 0;
                    trash = readMachine.nextLine();
                }
                if (x != 0 || y != 0) {
                    if (x < 1 || x > 3 || y < 1 || y > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        System.out.println("Enter the coordinates:");
                        x = 0;
                        y = 0;
                        trash = readMachine.nextLine();
                    } else {
                        if (field[x-1][y-1] != '_') {
                            System.out.println("This cell is occupied! Choose another one!");
                            System.out.println("Enter the coordinates:");
                            x = 0;
                            y = 0;
                            trash = readMachine.nextLine();
                        } else {
                            if (it%2==1) {
                                field[x-1][y-1] = 'X';
                                x = 0;
                                y = 0;
                                break;
                            }
                            if (it%2==0) {
                                field[x-1][y - 1] = 'O';
                                x = 0;
                                y = 0;
                                break;
                            }
                        }
                    }
                }
            }

            printField(field);
            rowx=0;
            rowzero=0;
            cross=0;
            zero=0;
            space=0;

            //analyze field

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] == 'X') {
                        cross++;
                        if (i == 0 && field[i + 1][j] == 'X' && field[i + 2][j] == 'X') {
                            rowx++;
                        }
                        if (j == 0 && field[i][j + 1] == 'X' && field[i][j + 2] == 'X') {
                            rowx++;
                        }
                        if ((i == 0 && j == 0 && field[i + 1][j + 1] == 'X' && field[i + 2][j + 2] == 'X') ||
                                (i == 0 && j == 2 && field[i + 1][j - 1] == 'X' && field[i + 2][j - 2] == 'X')) {
                            rowx++;
                        }
                    }

                    if (field[i][j] == 'O') {
                        zero++;
                        if (i == 0 && field[i + 1][j] == 'O' && field[i + 2][j] == 'O') {
                            rowzero++;
                        }
                        if (j == 0 && field[i][j + 1] == 'O' && field[i][j + 2] == 'O') {
                            rowzero++;
                        }
                        if ((i == 0 && j == 0 && field[i + 1][j + 1] == 'O' && field[i + 2][j + 2] == 'O') ||
                                (i == 0 && j == 2 && field[i + 1][j - 1] == 'O' && field[i + 2][j - 2] == 'O')) {
                            rowzero++;
                        }
                    }
                    if (field[i][j] == '_') {
                        space++;
                    }
                }
            }
            if (rowx > 0 && rowzero > 0 || cross - zero > 1 || zero - cross > 1) {
                System.out.println("Impossible");
                return;
            }
            if (rowx > 0) {
                System.out.println("X wins");
                return;
            }
            if (rowzero > 0) {
                System.out.println("O wins");
                return;
            }
            if (space == 0) {
                System.out.println("Draw");
                return;
            }
            System.out.println("Game not finished");
        }

    }
}
