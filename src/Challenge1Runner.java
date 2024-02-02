import java.util.Scanner;
import java.util.Arrays;

class Challenge1Runner {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Number Converter!");
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------------------");
            System.out.print("Enter the base of your number: (q to quit)");

            String choice = s.nextLine();

            if (choice.equalsIgnoreCase("q")) {s.close(); break;}
            int base = Integer.parseInt(choice);

            System.out.print("Enter your number: ");
            String number = s.nextLine();

            if (NumberConverter.isStringValidNumber(number, base)) {
                System.out.println("Uhh... that number doesn't really work out. Try a less... invalid number.");
                continue;
            }

            NumberConverter nc = new NumberConverter(number, base);
            int[] digits = nc.getDigits();
            System.out.println("\n\nDigit array: " + Arrays.toString(digits));
            System.out.println("Number: " + nc.displayOriginalNumber());
            System.out.println("Decimal: " + NumberConverter.converterNumberAsString(nc.convertToDecimal()));
            System.out.println("Octal: " + NumberConverter.converterNumberAsString(nc.convertToOctal()));
            System.out.println("Hexadecimal: " + NumberConverter.converterNumberAsString(nc.convertToBase(16)));
        }
    }
}

