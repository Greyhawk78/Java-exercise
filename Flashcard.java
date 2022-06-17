package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    final static Scanner sc = new Scanner(System.in);
    public static ArrayList<String> log = new ArrayList<>();

    public static void loggedPrint(String output){
        System.out.println(output);
        log.add(output);
    }

    public static void loggedPrintShort(String output){
        System.out.print(output);
        log.add(output);
    }

    public static String loggedInput() {
        String input = sc.nextLine();
        log.add(input);
        return input;
    }

    public static void main(String[] args) {
        Map<String, String> flashcard = new LinkedHashMap<>();
        Map <String, Integer> cardWithErrors = new LinkedHashMap<>();
        int exportFlag=0;

        switch (args.length) {
            case 2:
                if (args[0]=="-import") {
                    File file = new File(args[1]);
                    int cardCount=0;
                    try (Scanner scanner = new Scanner(file)) {
                        while (scanner.hasNext()) {
                            String term=scanner.nextLine();
                            String definit=scanner.nextLine();
                            Integer errors= Integer.parseInt(scanner.nextLine());
                            flashcard.put(term, definit);
                            cardWithErrors.put(term,errors);
                            cardCount++;
                        }
                        loggedPrint(cardCount + " cards have been loaded.");
                    } catch (FileNotFoundException e) {
                        loggedPrint("File not found");
                    }
                }
                if (args[0]=="-export") {
                    exportFlag=1;
                }
                break;
            case 4:
                if (args[0]=="-import") {
                    File file = new File(args[1]);
                    int cardCount=0;
                    try (Scanner scanner = new Scanner(file)) {
                        while (scanner.hasNext()) {
                            String term=scanner.nextLine();
                            String definit=scanner.nextLine();
                            Integer errors= Integer.parseInt(scanner.nextLine());
                            flashcard.put(term, definit);
                            cardWithErrors.put(term,errors);
                            cardCount++;
                        }
                        loggedPrint(cardCount + " cards have been loaded.");
                    } catch (FileNotFoundException e) {
                        loggedPrint("File not found");
                    }
                }
                if (args[2]=="-import") {
                    File file = new File(args[3]);
                    int cardCount=0;
                    try (Scanner scanner = new Scanner(file)) {
                        while (scanner.hasNext()) {
                            String term=scanner.nextLine();
                            String definit=scanner.nextLine();
                            Integer errors= Integer.parseInt(scanner.nextLine());
                            flashcard.put(term, definit);
                            cardWithErrors.put(term,errors);
                            cardCount++;
                        }
                        loggedPrint(cardCount + " cards have been loaded.");
                    } catch (FileNotFoundException e) {
                        loggedPrint("File not found");
                    }
                }
                if (args[0]=="-export") {
                    exportFlag=1;
                }
                if (args[2]=="-export") {
                    exportFlag=3;
                }
                break;
            default:
                break;
        }

        while (true) {
            loggedPrint ("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            String command = loggedInput();
            switch (command) {
                case ("add") :
                    addCard(flashcard, cardWithErrors);
                    break;
                case ("remove") :
                    removeCard(flashcard, cardWithErrors);
                    break;
                case ("import") :
                    importCard(flashcard, cardWithErrors);
                    break;
                case ("export") :
                    exportCard(flashcard, cardWithErrors);
                    break;
                case ("ask") :
                    play(flashcard,cardWithErrors);
                    break;
                case ("reset stats") :
                    resetStats(cardWithErrors);
                    break;
                case ("hardest card") :
                    hardestCard(cardWithErrors);
                    break;
                case ("log") :
                    logCards (log);
                    break;
                case ("exit") :
                    loggedPrint("Bye bye!");
                    if (exportFlag==1) {
                        File file = new File(args[1]);
                        int cardCount=0;
                        try (PrintWriter printWriter = new PrintWriter(file)) {
                            for (Map.Entry<String, String> entry : flashcard.entrySet()) {
                                String term=entry.getKey();
                                printWriter.println(term);
                                printWriter.println(entry.getValue());
                                printWriter.println(cardWithErrors.get(term));
                                cardCount++;
                            }
                            loggedPrint(cardCount + " cards have been saved.");
                        } catch (FileNotFoundException e) {
                            loggedPrint("Unknown file error.");
                        }
                    }
                    if (exportFlag==3) {
                        File file = new File(args[3]);
                        int cardCount=0;
                        try (PrintWriter printWriter = new PrintWriter(file)) {
                            for (Map.Entry<String, String> entry : flashcard.entrySet()) {
                                String term=entry.getKey();
                                printWriter.println(term);
                                printWriter.println(entry.getValue());
                                printWriter.println(cardWithErrors.get(term));
                                cardCount++;
                            }
                            loggedPrint(cardCount + " cards have been saved.");
                        } catch (FileNotFoundException e) {
                            loggedPrint("Unknown file error.");
                        }
                    }
                    return;
                default:
                    loggedPrint("Wrong command. Try again");
                    break;
            }
        }
    }

    private static void  addCard(Map<String, String> flashcard, Map <String, Integer> cardWithErrors) {
        loggedPrint("The card :");
        String term = loggedInput();
        while (true) {
            if (flashcard.containsKey(term)) {
                loggedPrint("The card " + "\""  + term + "\"" + " already exists.");
                term = loggedInput();
            } else {
                flashcard.put(term, " ");
                cardWithErrors.put(term, 0);
                break;
            }
        }
        loggedPrint("The definition of the card:");
        String definition = loggedInput();
        while (true) {
            if (flashcard.containsValue(definition)) {
                loggedPrint("The definition " + "\"" + definition + "\"" +" already exists.");
                definition = loggedInput();
            } else {
                flashcard.put(term, definition);
                loggedPrint("The pair (" + "\"" + term + "\"" +":" + "\"" + definition + "\"" + ") has been added.");
                break;
            }
        }
    }

    private static void  removeCard(Map<String, String> flashcard, Map <String, Integer> cardWithErrors) {
        loggedPrint("The card :");
        String term = loggedInput();
        while (true) {
            if (!flashcard.containsKey(term)) {
                loggedPrint("Can't remove " + "\""  + term + "\"" + ": there is no such card");
                term = loggedInput();
            } else {
                flashcard.remove(term);
                cardWithErrors.remove(term);
                loggedPrint("The card has been removed.");
                break;
            }
        }
    }

    private static void  importCard(Map<String, String> flashcard,  Map <String, Integer> cardWithErrors) {
        loggedPrint("File name: ");
        String filename = loggedInput();
        File file = new File(filename);
        int cardCount=0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String term=scanner.nextLine();
                String definit=scanner.nextLine();
                Integer errors= Integer.parseInt(scanner.nextLine());
                flashcard.put(term, definit);
                cardWithErrors.put(term,errors);
                cardCount++;
            }
            loggedPrint(cardCount + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            loggedPrint("File not found");
        }
    }

    private static void  exportCard(Map<String, String> flashcard,  Map <String, Integer> cardWithErrors) {
        loggedPrint("File name: ");
        String filename = loggedInput();
        File file = new File(filename);
        int cardCount=0;
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (Map.Entry<String, String> entry : flashcard.entrySet()) {
                String term=entry.getKey();
                printWriter.println(term);
                printWriter.println(entry.getValue());
                printWriter.println(cardWithErrors.get(term));
                cardCount++;
            }
            loggedPrint(cardCount + " cards have been saved.");
        } catch (FileNotFoundException e) {
            loggedPrint("Unknown file error.");
        }
    }

    private static void play(Map<String, String> flashcard, Map <String, Integer> cardWithErrors) {
        loggedPrint("How many times to ask?");
        int numberOfQuestions=sc.nextInt();
        String right=loggedInput();
        Random randomizer = new Random();
        List<String> keysAsArray = new ArrayList<>(flashcard.keySet());
        for (int i=0; i<numberOfQuestions; i++) {
            String term=keysAsArray.get(randomizer.nextInt(keysAsArray.size()));
            String definition = flashcard.get(term);
            loggedPrint("Print the definition of  " + "\"" + term + "\"" + ": ");
            right=null;
            String answer = loggedInput();
            if (definition.equals(answer)) {
                loggedPrint("Correct answer.");
            } else {
                int errors=cardWithErrors.get(term);
                errors++;
                cardWithErrors.put(term,errors);
                if (flashcard.containsValue(answer)) {
                    for (Map.Entry<String,String> entry1 : flashcard.entrySet()) {
                        if (entry1.getValue().equals(answer)) {
                            right=entry1.getKey();
                        }
                    }
                    loggedPrint("Wrong answer. The correct one is " + "\"" + definition + "\"" + " you've just written the" +
                            " definition of " + "\"" + right + "\"" + ".");
                } else {
                    loggedPrint("Wrong answer. The correct one is " + "\"" + definition + "\"");
                }
            }
        }
    }

    private static void resetStats(Map<String, Integer> cardWithErrors) {
        for (Map.Entry<String, Integer> entry : cardWithErrors.entrySet()) {
            String term = entry.getKey();
            cardWithErrors.put(term, 0);
        }
        loggedPrint("Card statistics has been reset.");
    }

    private static void hardestCard(Map<String, Integer> cardWithErrors) {
        ArrayList <String> hardest = new ArrayList<>();
        if (!cardWithErrors.isEmpty()) {
            int maxValueInMap = (Collections.max(cardWithErrors.values()));
            if (maxValueInMap == 0) {
                loggedPrint("There are no cards with errors.");

            } else {
                for (Map.Entry<String, Integer> entry : cardWithErrors.entrySet()) {
                    if (entry.getValue() == maxValueInMap) {
                        hardest.add(entry.getKey());
                    }
                }
                if (hardest.size() < 2) {
                    loggedPrint("The hardest card is " + "\"" + hardest.get(0) + "\"" +
                            ". You have " + cardWithErrors.get(hardest.get(0)) + " errors answering it.");
                } else {
                    loggedPrintShort("The hardest cards are ");
                    for (int it = 0; it < hardest.size() - 1; it++) {
                        loggedPrintShort("\"" + hardest.get(it) + "\"" + ", ");
                    }
                    loggedPrintShort("\"" + hardest.get(hardest.size() - 1) + "\"" + ".");
                    loggedPrint(" You have " + cardWithErrors.get(hardest.get(0)) + " errors answering them.");
                }
            }
        } else {
            loggedPrint("There are no cards with errors.");
        }
    }

    public static void logCards (ArrayList log) {
        loggedPrint("File name: ");
        String filename = loggedInput();
        File file = new File(filename);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            loggedPrint("The log has been saved.");
            for (int i = 0; i < log.size(); i++) {
                printWriter.println(log.get(i));
            }
        } catch (FileNotFoundException e) {
            loggedPrint("Unknown file error.");
        }
    }
}
