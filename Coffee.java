package com.company;

import java.util.Scanner;

public class Main {

    // initial amount of resources
    private static int WATER = 400;
    private static int MILK = 540;
    private static int COFFEE_BEANS = 120;
    private static int CUPS = 9;
    private static int MONEY = 550;

    // For the espresso, the coffee machine needs
    // 250 ml of water and 16 g of coffee beans.
    // It costs $4.
    private static final int ESPRESSO_WATER = 250;
    private static final int ESPRESSO_COFFEE_BEANS = 16;
    private static final int ESPRESSO_COST = 4;

    // For the latte, the coffee machine needs
    // 350 ml of water, 75 ml of milk, and 20 g of coffee beans.
    // It costs $7.
    private static final int LATTE_WATER = 350;
    private static final int LATTE_MILK = 75;
    private static final int LATTE_COFFEE_BEANS = 20;
    private static final int LATTE_COST = 7;

    // And for the cappuccino, the coffee machine needs
    // 200 ml of water, 100 ml of milk, and 12 g of coffee.
    // It costs $6.
    private static final int CAPPUCCINO_WATER = 200;
    private static final int CAPPUCCINO_MILK = 100;
    private static final int CAPPUCCINO_COFFEE_BEANS = 12;
    private static final int CAPPUCCINO_COST = 6;

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu();
    }

    private static void machineStatus() {
        System.out.println("The coffee machine has:\n" +
                WATER + " of water\n" +
                MILK + " of milk\n" +
                COFFEE_BEANS + " of coffee beans\n" +
                CUPS + " of disposable cups\n" +
                MONEY + " of money\n");
    }

    private static void showMenu() {
        boolean switchOff = false;

        while (!switchOff) {
            System.out.print("Write action (buy, fill, take, remaining, exit): ");
            String option = sc.next(); // add io checking
            switch (option) {
                case "buy":
                    buyCoffee();
//                    machineStatus();
                    break;
                case "fill":
                    fillMachine();
//                    machineStatus();
                    break;
                case "take":
                    takeMoney();
//                    machineStatus();
                    break;
                case "remaining":
                    machineStatus();
                    break;
                case "exit":
                    switchOff = true;
                    break;
                default:
                    System.out.println("Error: invalid option entered");
                    break;
            }
        }
    }

    private static void buyCoffee() {
        System.out.print("What do you want to buy? " +
                "1 - espresso, " +
                "2 - latte, " +
                "3 - cappuccino, " +
                "back - to main menu: ");
        String userChoice = sc.next();
        switch (userChoice) {
            case "1":
                makeEspresso();
                break;
            case "2":
                makeLatte();
                break;
            case "3":
                makeCappuccino();
                break;
            case "back":
                break;
            default:
                System.out.println("Sorry we don't have that kind of coffee");
                break;
        }
    }

    private static void makeEspresso() {
        if (WATER < ESPRESSO_WATER) {
            System.out.println("Sorry, not enough water!\n");
            return;
        }
        if (COFFEE_BEANS < ESPRESSO_COFFEE_BEANS) {
            System.out.println("Sorry, not enough coffee beans!\n");
            return;
        }
        if (CUPS < 1) {
            System.out.println("Sorry, not enough cups!\n");
            return;
        }

        System.out.println("I have enough resources, making you a coffee!\n");
        WATER -= ESPRESSO_WATER;
        COFFEE_BEANS -= ESPRESSO_COFFEE_BEANS;
        CUPS--;
        MONEY += ESPRESSO_COST;
    }

    private static void makeLatte() {
        if (WATER < LATTE_WATER) {
            System.out.println("Sorry, not enough water!\n");
            return;
        }
        if (MILK < LATTE_MILK) {
            System.out.println("Sorry, not enough milk!\n");
            return;
        }
        if (COFFEE_BEANS < LATTE_COFFEE_BEANS) {
            System.out.println("Sorry, not enough coffee beans!\n");
            return;
        }
        if (CUPS < 1) {
            System.out.println("Sorry, not enough cups!\n");
            return;
        }

        System.out.println("I have enough resources, making you a coffee!\n");
        WATER -= LATTE_WATER;
        MILK -= LATTE_MILK;
        COFFEE_BEANS -= LATTE_COFFEE_BEANS;
        CUPS--;
        MONEY += LATTE_COST;
    }

    private static void makeCappuccino() {
        if (WATER < CAPPUCCINO_WATER) {
            System.out.println("Sorry, not enough water!\n");
            return;
        }
        if (MILK < CAPPUCCINO_MILK) {
            System.out.println("Sorry, not enough milk!\n");
            return;
        }
        if (COFFEE_BEANS < CAPPUCCINO_COFFEE_BEANS) {
            System.out.println("Sorry, not enough coffee beans!\n");
            return;
        }
        if (CUPS < 1) {
            System.out.println("Sorry, not enough cups!\n");
            return;
        }

        System.out.println("I have enough resources, making you a coffee!\n");
        WATER -= CAPPUCCINO_WATER;
        MILK -= CAPPUCCINO_MILK;
        COFFEE_BEANS -= CAPPUCCINO_COFFEE_BEANS;
        CUPS--;
        MONEY += CAPPUCCINO_COST;
    }

    private static void fillMachine() {
        System.out.print("Write how many ml of water do you want to add: ");
        int waterRefill = sc.nextInt();
        WATER += waterRefill < 1 ? 0 : waterRefill;

        System.out.print("Write how many ml of milk do you want to add: ");
        int milkRefill = sc.nextInt();
        MILK += milkRefill < 1 ? 0 : milkRefill;

        System.out.print("Write how many grams of coffee beans do you want to add: ");
        int beansRefill = sc.nextInt();
        COFFEE_BEANS += beansRefill < 1 ? 0 : beansRefill;

        System.out.print("Write how many disposable cups of coffee do you want to add: ");
        int cupsRefill = sc.nextInt();
        CUPS += cupsRefill < 1 ? 0 : cupsRefill;
    }

    private static void takeMoney() {
        System.out.println("I gave you " + MONEY);
        MONEY = 0;
    }

}
