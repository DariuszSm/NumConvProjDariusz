import java.util.Scanner;
import java.util.Arrays;

class Challenge1Runner {
    public static void main(String[] args) {
        System.out.println("Welcome to the Number Converter!");
        System.out.println("--------------------------------");
        System.out.print("Enter the base of your number (2, 8 or 10): ");

        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        int base = Integer.parseInt(choice);

        System.out.print("Enter your number: ");
        String number = s.nextLine();

        s.close();

        NumberConverter nc = new NumberConverter(number, base);
        int[] digits = nc.getDigits();
        System.out.println("\n\nDigit array: " + Arrays.toString(digits));
        System.out.println("Number: " + nc.displayOriginalNumber());
        System.out.println("Decimal: " + NumberConverter.converterNumberAsString(nc.convertToDecimal()));
        System.out.println("Octal: " + NumberConverter.converterNumberAsString(nc.convertToOctal()));
        System.out.println("Hexadecimal: " + NumberConverter.converterNumberAsString(nc.convertToBase(16)));
    }
}

