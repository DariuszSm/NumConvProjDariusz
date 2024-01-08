public class NumberConverter {
    int[] digits;
    int base;

    public NumberConverter(int number, int base) {
        String numberAsString = Integer.toString(number);
        digits = new int[numberAsString.length()];
        for (int i = 0; i < numberAsString.length(); i++) {
            String single = numberAsString.substring(i,i+1);
            int d = Integer.parseInt(single);
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

    public int[] convertToDecimal() {
        int decNum = 0;
        int[] decArray;
        int numsUsed = 0;

        for (int i = 0; i < digits.length; i++) {
            decNum += digits[digits.length - i - 1] * Math.pow(base, i);
        }

        String decStr = "";

        for (int i = 0; i < String.valueOf(decNum).length(); i++) {
            decStr += String.valueOf(decNum).substring(i, i+1) + "#";
            numsUsed++;
        }
        System.out.println(decStr);

        String[] decStrArray = decStr.split("#");
        decArray = new int[decStrArray.length];

        for (int i = 0; i < decStrArray.length; i++) {
            decArray[i] = Integer.parseInt(decStrArray[i]);
        }

        return decArray;
    }

    public int[] convertToBinary() {
        int[] decArray = convertToDecimal();
        String decString = "";
        return null;
    }

    public int[] convertToOctal() {
        return null;
    }
}

