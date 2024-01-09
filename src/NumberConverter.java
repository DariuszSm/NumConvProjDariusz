public class NumberConverter {
    int[] digits;
    int base;

    public NumberConverter(String number, int base) {
        digits = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            int d = digitToValue(number.substring(i,i+1));
            digits[i] = d;
        }
        this.base = base;
    }

    public String displayOriginalNumber() {
        String o = "";
        for (int i = 0; i < digits.length; i++) {
            o = o + digits[i];
        }
        o = o + "\n";
        return o;
    }

    public int[] getDigits() {
        return digits;
    }

    public int digitToValue(String digit) {
        char digitChar = digit.charAt(0);
        int digitCharInt = (int)digitChar;

        if (digitCharInt >= 48 && digitCharInt <= 57) {
            // if digit is a number value
            return digitCharInt-48;
        } else if (digitCharInt >= 65 && digitCharInt <= 90) {
            // if digit is an uppercase letter
            return digitCharInt-55;
        } else if (digitCharInt >= 97 && digitCharInt <= 122) {
            // if digit is an lowercase letter
            return digitCharInt-61;
        } else if (digitCharInt == 43) {
            return 62;
        } else if (digitCharInt == 47) {
            return 63;
        }

        // invalid characters are 0
        return 0;
    }

    public String valueToDigit(int value) {
        if (value < 10) {
            return String.valueOf(value);
        } else if (value < 36) {
            return String.valueOf((char)value+55);
        } else if (value < 62) {
            return String.valueOf((char)value+61);
        } else if (value == 62) {
            return "+";
        } else if (value == 63) {
            return "/";
        }

        // invalid characters are 0
        return "0";
    }

    public int[] convertToBase(int base) {
        int decNum = 0;
        int[] decArray;

        // convert to base 10
        for (int i = 0; i < digits.length; i++) {
            decNum += digits[digits.length - i - 1] * Math.pow(this.base, i);
            System.out.println(decNum);
        }

        int mutDecNum = decNum;
        String mutDecStr = "";

        for (int i = 0; i < String.valueOf(decNum).length(); i++) {
            int quotient = mutDecNum/base;
            mutDecStr += (mutDecNum % base) + "#";
        }

        String decStr = "";

        for (int i = 0; i < String.valueOf(decNum).length(); i++) {
            decStr += String.valueOf(decNum).substring(i, i+1) + "#";
        }
        System.out.println(decStr);

        String[] decStrArray = decStr.split("#");
        decArray = new int[decStrArray.length];

        for (int i = 0; i < decStrArray.length; i++) {
            decArray[i] = Integer.parseInt(decStrArray[i]);
        }

        return decArray;
    }

    public int getInt() {
        int[] decArray = convertToBase(10);
        int decInt = 0;

        for (int i = 0; i < decArray.length; i++) {
            System.out.println(decArray[i] + ":" + (decArray.length-i));
            decInt += decArray[i] * Math.pow(10, decArray.length-i-1);
        }

        return decInt;
    }

    public int[] convertToBase() {
        int num = getInt();

        return null;
    }

    public int[] convertToOctal() {
        return null;
    }
}

