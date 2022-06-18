package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class Purchase {
    String name;
    double price;
    int category;

    Purchase(String name, double price, int category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    @Override
    public String toString() {
        String output = String.format("%s $%.2f",name,price);
        return output;
    }
}

public class Main {

    static double balance = 0;
    static List<Purchase> purchaseList = new LinkedList();

    public static void showMenu() {
        System.out.print("Choose your action:\n1) Add income \n2) Add purchase \n" +
                "3) Show list of purchases \n4) Balance \n5) Save\n6) Load\n7) Analyze (Sort)\n0) Exit \n");
    }

    public static void save() {
        File file = new File("purchases.txt");
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(balance);
            for (Purchase p : purchaseList) {
                printWriter.println(p.name);
                printWriter.println(p.price);
                printWriter.println(p.category);
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    public static void load() {
        File file = new File("purchases.txt");
        try (Scanner scanner = new Scanner(file)) {
            balance=Double.parseDouble(scanner.nextLine());
            while (scanner.hasNext()) {
                String name = scanner.nextLine();
                double price= Double.parseDouble(scanner.nextLine());
                int category = Integer.parseInt(scanner.nextLine());
                Purchase Temp = new Purchase(name, price, category);
                purchaseList.add(Temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + file);
        }
    }


    public static void addIncome() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter income:");
        double income = scan.nextDouble();
        balance += income;
        System.out.println("Income was added!\n");
    }

    public static void addPurchase() {
        Scanner scan = new Scanner(System.in);
        int category=0;
       while (true) {
           System.out.println("Choose the type of purchase\n1) Food\n2) Clothes\n3) Entertainment\n4) Other\n5) Back\n");
        category = Integer.parseInt(scan.nextLine());
        if (category == 5) return;
        System.out.println("Enter purchase name:");
        String name = scan.nextLine();
        System.out.println("Enter its price:");
        double price = scan.nextDouble();
        balance -= price;
        Purchase Temp = new Purchase(name, price, category);
        purchaseList.add(Temp);
        System.out.println("Purchase was added!\n");
        String trash=scan.nextLine();
       }
    }

    public static void showListOfPurchase() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            double sumOfAllPurchases = 0;
            System.out.println("Choose the type of purchases\n1) Food\n2) Clothes\n3) Entertainment\n4) Other\n5) All\n6) Back\n");
            int category = Integer.parseInt(scan.nextLine());
            switch (category) {
                case 6:
                    return;
                case 5:
                    for (Purchase p : purchaseList) {
                            System.out.println(p.toString());
                            sumOfAllPurchases += p.getPrice();
                    }
                    if (sumOfAllPurchases == 0) {
                        System.out.println("Purchase list is empty\n");
                    } else {
                        System.out.println("Total sum: $" + sumOfAllPurchases + "\n");
                    }
                    break;
                default:
                    for (Purchase p : purchaseList) {
                        if (p.category == category) {
                            System.out.println(p.toString());
                            sumOfAllPurchases += p.getPrice();
                        }
                    }
                    if (sumOfAllPurchases == 0) {
                        System.out.println("Purchase list is empty\n");
                    } else {
                        System.out.println("Total sum: $" + sumOfAllPurchases + "\n");
                    }
                    break;

            }
        }
    }

    public static void analyze() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            double sumOfAllPurchases = 0;
            List<Purchase> forAnalize = new LinkedList<>(purchaseList);
            System.out.println("");
            System.out.println("How do you want to sort\n" +
                    "1) Sort all purchases\n2) Sort by type\n3) Sort certain type\n4) Back\n");
            int typeOfSort = Integer.parseInt(scan.nextLine());
            switch (typeOfSort){
                case 4:
                    return;
                case 1:
                    forAnalize = new LinkedList<>(purchaseList);
                    Collections.sort(forAnalize, new Comparator<Purchase>() {
                        @Override
                        public int compare(Purchase o1, Purchase o2) {
                            return Double.compare(o2.price, o1.price);
                        }
                    });
                    for (Purchase p : forAnalize) {
                        System.out.println(p.toString());
                        sumOfAllPurchases += p.getPrice();
                    }
                    if (sumOfAllPurchases == 0) {
                        System.out.println("");
                        System.out.println("Purchase list is empty\n");
                    } else {
                        System.out.println("Total sum: $" + sumOfAllPurchases + "\n");
                    }

                    break;
                case 2:
                    double food=0,enter=0,clothes=0,other=0;
                    for (Purchase p : purchaseList) {
                        if (p.category == 1) {
                            food += p.getPrice();
                            sumOfAllPurchases += p.getPrice();
                        }
                        if (p.category == 2) {
                            clothes += p.getPrice();
                            sumOfAllPurchases += p.getPrice();
                        }
                        if (p.category == 3) {
                            enter += p.getPrice();
                            sumOfAllPurchases += p.getPrice();
                        }
                        if (p.category == 4) {
                            other += p.getPrice();
                            sumOfAllPurchases += p.getPrice();
                        }
                    }
                       if (sumOfAllPurchases == 0) {
                            System.out.println("Food - $0.00\nEntertainment - $0,00\nClothes - $0,00\nOther - $0,00\n");
                           System.out.println("");}
                            else {
                           Map<Double, String> sortByType = new TreeMap<Double, String>(new Comparator<Double>() {
                               @Override
                               public int compare(Double o1, Double o2) {
                                   return o2.compareTo(o1);
                               }
                           });
                           sortByType.put(food, "Food");
                           sortByType.put(enter, "Entertainment");
                           sortByType.put(clothes, "Clothes");
                           sortByType.put(other, "Other");
                           for (Map.Entry<Double, String> mapData : sortByType.entrySet()) {
                               System.out.println(mapData.getValue() + " - $" + String.format("%.2f", mapData.getKey()));
                           }
                       }
                    break;
                case 3:
                    System.out.println("Choose the type of purchases\n1) Food\n2) Clothes\n3) Entertainment\n4) Other\n");
                    int category = Integer.parseInt(scan.nextLine());
                    forAnalize = new LinkedList<>(purchaseList);
                    Collections.sort(forAnalize, new Comparator<Purchase>() {
                        @Override
                        public int compare(Purchase o1, Purchase o2) {
                            return Double.compare(o2.price, o1.price);
                        }
                    });
                    for (Purchase p : forAnalize) {
                        if (p.category == category) {
                            System.out.println(p.toString());
                            sumOfAllPurchases += p.getPrice();
                        }
                    }
                    if (sumOfAllPurchases == 0) {
                        System.out.println("Purchase list is empty\n");
                    } else {
                        System.out.println("Total sum: $" + sumOfAllPurchases + "\n");
                    }
                    break;
                default:
                    System.out.println("Oops. Something wrong");
                    break;
            }
        }
    }

    public static void main(String[] args) {


        while (true) {
            showMenu();
            Scanner scan = new Scanner(System.in);
            String command = scan.next();
            switch (command) {
                case "1":
                    System.out.println("");
                    addIncome();
                    break;
                case "2":
                    System.out.println("");
                    addPurchase();
                    break;
                case "3":
                    System.out.println("");
                    showListOfPurchase();
                    break;
                case "4":
                    System.out.println("");
                    System.out.println("Balance: $" + balance + "\n");
                    break;
                case "5":
                    save();
                    System.out.println("");
                    System.out.println("Purchases were saved!\n");
                    break;
                case "6":
                    load();
                    System.out.println("");
                    System.out.println("Purchases were loaded!\n");
                    break;
                case "7":
                    System.out.println("");
                    analyze();
                    break;
                case "0":
                    System.out.println("");
                    System.out.println("Bye!\n");
                    return;
                default:
                    System.out.println("");
                    System.out.println("Unknown command\n");
                    break;
            }
        }
    }
}
