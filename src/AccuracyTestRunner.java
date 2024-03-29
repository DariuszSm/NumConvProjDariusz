public class AccuracyTestRunner {
    public static void main(String[] args) {
        int rounds = 1000;
        int passed = 0;
        int failed = 0;
        int roundsDone = 0;
        for (int i = 0; roundsDone < rounds; i++) {
            System.out.println("Tests Completed (%/100): " + (double)roundsDone/rounds);
            // bigger num produces longer test times for base 1 cases
            int num = (int)(Math.random()*10000);
            int base = (int)(Math.random()*35) + 2;
            NumberConverter nc = new NumberConverter(String.valueOf(num), base);

            int compare = 0;
            try {
                compare = Integer.parseInt(String.valueOf(num), base);
            } catch (Exception BaseTooLow) {
                // if the code reaches here, it's likely that whatever number was produced
                // hit the integer limit, and as a result, would cause the test to crash.
                // Rounds that encounter this possibility are ignored in the result.
                continue;
            }

            // direct integer value comparison
            int ncInt = nc.getInt();
            if (ncInt != compare) {
                failed++;

                System.out.println("Round " + (i+1) + " failed at direct integer value comparison:");
                System.out.println("Num: " + num);
                System.out.println("Base: " + base);
                System.out.println("Expected: " + compare);
                System.out.println("Got: " + ncInt);
                System.out.println();
            } else {
                passed++;
            }

            // lossless conversion between bases
            for (int f = 1; f < base; f++) {
                // edge case for base 1 numbers due to incompatibility with parseInt
                if (f == 1) {
                    if (nc.convertToBase(1).length == nc.getInt() || nc.convertToBase(1)[0]==nc.getInt()) {
                        passed++;
                    } else {
                        failed++;

                        System.out.println("Round " + (i+1) + " failed at lossless conversion between bases:");
                        System.out.println("Num: " + num);
                        System.out.println("Base: " + f);
                        System.out.println();
                    }
                    roundsDone++;
                    continue;
                }
                String newBaseString = String.valueOf(NumberConverter.converterNumberAsString(nc.convertToBase(f)));
                NumberConverter incBaseNum = new NumberConverter(newBaseString, f);
                int newBaseInt = incBaseNum.getInt();
                if (newBaseInt != compare) {
                    failed++;

                    System.out.println("Round " + (i+1) + " failed at lossless conversion between bases:");
                    System.out.println("Num: " + num);
                    System.out.println("Base: " + f);
                    System.out.println("Expected: " + compare);
                    System.out.println("Got: " + newBaseInt);
                    System.out.println();
                } else {
                    passed++;
                }
                roundsDone++;
            }

            roundsDone++;

        }
        System.out.println("Number Conversion Test:");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Percent completed: " + (double)Math.round(((double)passed/roundsDone)*100)/100);
    }
}
