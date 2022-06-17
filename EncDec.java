package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

interface Algorithm {

    public String enc(char[] input, int key);

    public String dec(char[] input, int key);

}

class shiftAlgorithm implements Algorithm {

    @Override
    public  String enc(char[] input, int key) {

        for (int i = 0; i < input.length; i++) {
            if ((int) input[i] <= 90 && (int) input [i]>=65) {
                if ((int) input[i] + key <= 90) {
                    input[i] = (char) ((int) input[i] + key);
                } else {
                    input[i] = (char) ((int) input[i] + key - 26);
                }
            } else  if ((int) input[i] <= 122 && (int) input [i]>=97){
                if ((int) input[i] + key <= 122) {
                    input[i] = (char) ((int) input[i] + key);
                } else {
                    input[i] = (char) ((int) input[i] + key - 26);
                }
            }
        }
        return new String(input);
    }


    @Override
    public  String dec(char[] input, int key) {
        for (int i = 0; i < input.length; i++) {
            if ((int) input[i] >=97&&(int) input[i] <= 122) {
                if ((int) input[i] - key >= 97) {
                    input[i] = (char) ((int) input[i] - key);
                } else {
                    input[i] = (char) ((int) input[i] - key + 26);
                }
            } else if ((int) input[i] <= 90 && (int) input [i]>=65)
            {
                if ((int) input[i] - key >= 65) {
                    input[i] = (char) ((int) input[i] - key);
                } else {
                    input[i] = (char) ((int) input[i] - key + 26);
                }
            }
        }
        return new String(input);
    }
}

class unicodeAlgorithm implements Algorithm {

    @Override
    public  String enc(char[] input, int key) {

        for (int i = 0; i < input.length; i++) {
            if ((int) input[i] + key <= 126) {
                input[i] = (char) ((int) input[i] + key);
            } else {
                input[i] = (char) (((int) input[i] + key) - 94);
            }
        }
        return new String(input);
    }

    @Override
    public  String dec(char[] input, int key) {
        for (int i = 0; i < input.length; i++) {
            if ((int) input[i] - key >= 32) {
                input[i] = (char) ((int) input[i] - key);
            } else {
                input[i] = (char) (94 + (int) input[i] - key);
            }
        }
        return new String(input);
    }
}


public class Main {


    public static void main(String[] args) {
        int key = 0;
        String operation = "enc";
        String data = "";
        String alg= "shift";
        boolean exportFlag = false;
        String path = "";
        Algorithm algorithm = new shiftAlgorithm();

        List<String> arguments = Arrays.asList(args);

        if (arguments.contains("-alg")) {
            if (arguments.indexOf("-alg") == arguments.size() - 1) {
                System.out.println("Error. -alg should be shift or unicode");
                return;
            } else {
                if (arguments.get(arguments.indexOf("-alg") + 1) == "shift") {
                    alg= "shift";
                } else {
                    if (arguments.get(arguments.indexOf("-alg") + 1) == "unicode") {
                        alg= "unicode";
                    } else {
                        System.out.println("Error. -alg should be shift or unicode");
                        return;
                    }
                }
            }
        }

        if (arguments.contains("-mode")) {
            if (arguments.indexOf("-mode") == arguments.size() - 1) {
                System.out.println("Error. -mode should be enc or dec");
                return;
            } else {
                if (arguments.get(arguments.indexOf("-mode") + 1) == "enc") {
                    operation = "enc";
                } else {
                    if (arguments.get(arguments.indexOf("-mode") + 1) == "dec") {
                        operation = "dec";
                    } else {
                        System.out.println("Error. -mode should be enc or dec");
                        return;
                    }
                }
            }
        }

        if (arguments.contains("-key")) {
            if (arguments.indexOf("-key") == arguments.size() - 1) {
                System.out.println("Error. -key should be int");
                return;
            } else {
                try {
                    key = Integer.parseInt(arguments.get(arguments.indexOf("-key") + 1));
                } catch (NumberFormatException e) {
                    System.out.println("Error. -key should be int");
                    return;
                }
            }
        }

        if (arguments.contains("-data")) {
            if (arguments.indexOf("-data") == arguments.size() - 1) {
                data = "";
            } else {
                data = arguments.get(arguments.indexOf("-data") + 1);
            }
        }

        if (arguments.contains("-in") && !arguments.contains("-data")) {
            if (arguments.indexOf("-in") == arguments.size() - 1) {
                System.out.println("Error. -in should have filename");
                return;
            } else {
                File file = new File(arguments.get(arguments.indexOf("-in") + 1));
                try (Scanner scanner = new Scanner(file)) {
                    data = scanner.nextLine();
                } catch (FileNotFoundException e) {
                    System.out.println("Error. File not found");
                    return;
                }
            }
        }

        if (alg == "shift") {
            algorithm = new shiftAlgorithm();
        } else {
            algorithm = new unicodeAlgorithm();
        }


        char[] input = data.toCharArray();
        switch (operation) {
            case "enc":
                if (arguments.contains("-out")) {
                    if (arguments.indexOf("-out") == arguments.size() - 1) {
                        System.out.println("Error. -out should have filename");
                        return;
                    } else {
                        File outfile = new File(arguments.get(arguments.indexOf("-out") + 1));
                        try (PrintWriter printWriter = new PrintWriter(outfile)) {
                            printWriter.println(algorithm.enc(input, key));
                        } catch (FileNotFoundException e) {
                            System.out.println("Error. File not found");
                        }
                    }
                } else {
                    System.out.println(algorithm.enc(input, key));
                }
                break;
            case "dec":
                if (arguments.contains("-out")) {
                    if (arguments.indexOf("-out") == arguments.size() - 1) {
                        System.out.println("Error. -out should have filename");
                        return;
                    } else {
                        File outfile = new File(arguments.get(arguments.indexOf("-out") + 1));
                        try (PrintWriter printWriter = new PrintWriter(outfile)) {
                            printWriter.println(algorithm.dec(input, key));
                        } catch (FileNotFoundException e) {
                            System.out.println("Error. File not found");
                        }
                    }
                } else {
                    System.out.println(algorithm.dec(input, key));
                }
                break;
            default:
                break;
        }
    }
}
