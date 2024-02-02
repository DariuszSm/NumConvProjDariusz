import java.util.Arrays;

public class NumberConverter {
    String origNumber;
    int[] digits;
    int base;

    /**
     * Joins together all values as digits together into one String.
     * @param intArr Array holding the values digits in the NumberConverter class format.
     * @return A String that contains the digits inside the array in order.
     */
    public static String converterNumberAsString(int[] intArr) {
        if (intArr == null) {
            return "Invalid Number!";
        }
        String assemble = "";
        for (int i = 0; i < intArr.length; i++) {
            String digit = valueToDigit(intArr[i]);
            assemble += digit;
        }
        return assemble;
    }

    /**
     * Provides the value associated with a digit, returns 0 if the digit is invalid.
     * @param digit A digit that corresponds to a number.
     * @return The associated value for the digit passed.
     */
    public static int digitToValue(String digit) {
        char digitChar = digit.charAt(0);
        int digitCharInt = (int)digitChar;

        if (digitCharInt >= 48 && digitCharInt <= 57) {
            // if digit is a number value
            return digitCharInt-48;
        } else if (digitCharInt >= 65 && digitCharInt <= 90) {
            // if digit is an uppercase letter
            return digitCharInt-55;
        } else if (digitCharInt >= 97 && digitCharInt <= 122) {
            // if digit is a lowercase letter
            return digitCharInt-61;
        } else if (digitCharInt == 43) {
            // if digit is "+"
            return 62;
        } else if (digitCharInt == 47) {
            // if digit is "/"
            return 63;
        }

        // invalid characters are 0
        return -1;
    }
    /**
     * Provides the digit associated with a value, returns 0 if the value is invalid.
     * @param value A value that corresponds to a number.
     * @return The associated digit for the value passed.
     */
    public static String valueToDigit(int value) {
        if (value < 10) {
            return String.valueOf(value);
        } else if (value < 36) {
            return String.valueOf((char)(value+55));
        } else if (value < 62) {
            return String.valueOf((char)(value+61));
        } else if (value == 62) {
            return "+";
        } else if (value == 63) {
            return "/";
        }

        // invalid characters are `
        return "`";
    }

    /**
     * Inspects a String to see if the String is capable of representing a number of the given base. Great for
     * checking for number validity prior to constructing a number.
     * @param number The number in String form.
     * @param base
     * @return
     */
    public static boolean isStringValidNumber(String number, int base) {
        for (int i = 0; i < number.length(); i++) {
            if (digitToValue(number.substring(i, i+1)) >= base) {
                return false;
            }
        }
        return true;
    }

    /**
     * Evaluates if the stored digits form a valid number.
     * @return Returns validity of the stored digits as a number. Returns false if the digits array is null.
     */
    public boolean isValidNumber() {
        if (digits == null) {return false;}
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] >= base) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the values for each digit within the object's held number.
     * @return An array of values in order of most to least significant digit.
     */
    public int[] getDigits() {
        return digits;
    }

    /**
     * Makes an object that holds the base and the array that holds the number's individual digit values.
     * @param number Represents the given number as a String.
     * @param base The base of the given number.
     */
    public NumberConverter(String number, int base) {
        this.origNumber = number;
        this.base = base;

        // validity edge case
        if (!isStringValidNumber(number, base)) {
            digits = null;
            return;
        }
        // empty edge case
        if (number.isEmpty()) {
            digits = new int[1];
            return;
        }
        // check for 0s at initial area of number
        int numStart = 0;
        while (numStart < number.length() && number.substring(numStart, numStart+1).equals("0")){
            numStart++;
        }
        // if all numbers in the string are 0 (including just 1 0), just make digits a single number 0
        if (numStart == number.length()) {
            digits = new int[1];
            return;
        }
        number = number.substring(numStart);

        // primary constructor instructions
        if (base == 1) {
            digits = new int[Integer.parseInt(number)];
            for (int i = 0; i < digits.length; i++) {
                digits[i] = 1;
            }
        } else {
            digits = new int[number.length()];
            for (int i = 0; i < number.length(); i++) {
                int d = digitToValue(number.substring(i, i + 1));
                digits[i] = d;
            }
        }
    }

    /**
     * Returns the original number given to the object upon instantiation, in its raw String form.
     * @return The number passed to the object in its original base.
     */
    public String displayOriginalNumber() {
        return origNumber;
    }

    /**
     * Converts the number corresponding to this object to a different base, non-destructive.
     * @param base The base to convert the object's number to.
     * @return The int array holding the values of all the digits of the converted number. Returns null if the given array is null.
     */
    public int[] convertToBase(int base) {
        // edge cases
        if (digits == null) {
            return null;
        }

        if (digits[0] == 0) {
            return new int[1];
        }

        // convert to base 10 for simple storage as an integer variable
        int decNum = 0;
        int[] decArray;
        if (this.base == 1) {
            decNum = digits.length;
        }
        for (int i = 0; i < digits.length; i++) {
            decNum += digits[digits.length - i - 1] * Math.pow(this.base, i);
        }

        // calculate individual digits, put digits within a separated string
        int mutDecNum = decNum;
        String mutDecStr = "";

        if (base == 1) {
            for (int i = 0; i < decNum; i++) {
                mutDecStr += "1#";
            }
        } else {
            for (int i = 0; mutDecNum / base != 0 || mutDecNum % base != 0; i++) {
                int quotient = mutDecNum / base;
                int remainder = mutDecNum % base;
                mutDecStr = valueToDigit(remainder) + "#" + mutDecStr;
                mutDecNum = quotient;
            }
        }

        // split the array and assemble an array holding the values of the converted number
        String[] decStrArray = mutDecStr.split("#");
//        System.out.println(mutDecStr);
//        System.out.println(Arrays.toString(decStrArray));
        decArray = new int[decStrArray.length];
        for (int i = 0; i < decStrArray.length; i++) {
            decArray[i] = digitToValue(decStrArray[i]);
        }

        return decArray;
    }

    /**
     * Calculates the held number into an integer value.
     * @return An integer that represents the held number as a base 10 number.
     */
    public int getInt() {
        // Calls for an array of the number in base 10, and returns the digits added together according to each digit's power.
        int[] decArray = convertToBase(10);
        int decInt = 0;
        for (int i = 0; i < decArray.length; i++) {
            decInt += decArray[i] * Math.pow(10, decArray.length-i-1);
        }
        return decInt;
    }

    /**
     * @return The held object's number in base 10.
     */
    public int[] convertToDecimal() {
        return convertToBase(10);
    }

    /**
     * @return The held object's number in base 8.
     */
    public int[] convertToOctal() {
        return convertToBase(8);
    }
}

