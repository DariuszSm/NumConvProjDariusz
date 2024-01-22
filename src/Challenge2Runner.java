import java.util.Scanner;
import java.util.Arrays;

class Challenge2Runner {
    public static void main(String[] args) {
        System.out.println("WELCOME TO THE NUMBER CONVERTER!");
        System.out.println("--------------------------------");
        System.out.print("ENTER ANY BASE 10 NUMBER!!!!!!!!!!!!!!!: ");

        Scanner s = new Scanner(System.in);
        String number = s.nextLine();

        System.out.print("GREAT CHOICE!!!!!! ENTER A FANCY NEW BASE HIGHER THAN 1 AND UP TO BASE 64!!!!!!: ");
        String choice = s.nextLine();
        int base = Integer.parseInt(choice);

        s.close();

        NumberConverter nc = new NumberConverter(number, 10);
        int[] digits = nc.getDigits();
        System.out.println("YOUR FANCY BRAND SPANKIN' NEW NUMBER!!!!: " + NumberConverter.converterNumberAsString(nc.convertToBase(base)));
    }
}

