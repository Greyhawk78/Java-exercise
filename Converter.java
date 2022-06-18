package converter;

import java.util.Scanner;


public class Main {

    public static String convertInteger (String wholePart, int radix,int newradix) {
        long temp;
        StringBuilder result = new StringBuilder();
        if (radix == 1) {
            temp = wholePart.length();
        } else {
            temp = Long.parseLong(wholePart, radix);
        }
        if (newradix == 1) {
            for (int i = 0; i < Long.parseLong(wholePart); i++) {
                result.append("1");
            }
        } else {
            result.append(Long.toString(temp, newradix));
        }
        return result.toString();
    }

    public static String convertFractal (String fractalPart, int radix,int newradix) {
        if (fractalPart.equals("")) return "";
        double temp=0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <fractalPart.length(); i++) {
            temp+=(Long.parseLong(String.valueOf(fractalPart.charAt(i)),radix))/Math.pow(radix,i+1);
        }
        while (temp>1) {
            temp=temp/10;
        }

        for (int i=0; i<5; i++) {
            temp=temp*newradix;
            double tempFractionalPart = temp % 1;
            double tempIntegralPart = temp - tempFractionalPart;
            sb.append(Long.toString((long) tempIntegralPart, newradix));
            temp=temp-tempIntegralPart;
        }
        return sb.toString() ;

    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int radix = 0,newradix=0;
        String number;
        if (scan.hasNextInt()) radix = Integer.parseInt(scan.nextLine());
        number = scan.nextLine();
        if (scan.hasNextInt())newradix = Integer.parseInt(scan.nextLine());

        if (radix>36|| radix<1 ||newradix >36 || newradix <1) {
            System.out.println("Error. Radix should be between 1 and 36");
            return;
        }

        String [] fullNumber = number.split("\\.");
        String wholePart=fullNumber[0];
        String fractalPart="";

        if (fullNumber.length>1) {
            fractalPart=fullNumber[1];
        }
        try {
            wholePart = convertInteger(wholePart, radix, newradix);
            fractalPart = convertFractal(fractalPart, radix, newradix);
        } catch (Exception e) {
            System.out.println("Error");
            return;
        }
        System.out.println(fractalPart.equals("")?wholePart:wholePart+"."+fractalPart);
    }
}
