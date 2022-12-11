import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class AoC2022_11Dec {

    public static void main(String[] args) {

        ArrayList <Monkey> troopOfMonkeys = new ArrayList<Monkey>();

        //iterate over input file and create initial condition
        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_11Dec.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //read the next 5 lines after the key word Monkey appears in the start of input line
                if(data.startsWith("Monkey")) {
                    Monkey currMonkey = new Monkey();
                    currMonkey.label = data.charAt(7);
                    currMonkey.items = new ArrayList<BigInteger>();
                    for (int i = 0; i < 5; i++) {
                        String monkeyAttributes = myReader.nextLine();
                        if (monkeyAttributes.startsWith("  Starting items:")){
                            //System.out.println(monkeyAttributes.substring(18));
                            String [] currLine = monkeyAttributes.substring(18).split(",");
                            for (int j = 0; j < currLine.length; j++) {
                                currMonkey.items.add(BigInteger.valueOf(Long.parseLong(currLine[j].trim())));
                            }
                        } else if (monkeyAttributes.startsWith("  Operation: new = ")){
                            //System.out.println(monkeyAttributes.substring(19));
                            currMonkey.operation = monkeyAttributes.substring(19);
                        } else if (monkeyAttributes.startsWith("  Test: divisible by ")){
                            String[] currLine = monkeyAttributes.split(" ");
                            currMonkey.divisorForDivisibilityTest = Integer.parseInt(currLine[currLine.length-1]);
                        } else if (monkeyAttributes.startsWith("    If true: throw to monkey ")){
                            String[] currLine = monkeyAttributes.split(" ");
                            currMonkey.pointerToMonkeyPositionIfTestPasses = Integer.parseInt(currLine[currLine.length-1]);
                        }else if (monkeyAttributes.startsWith("    If false: throw to monkey ")){
                            String[] currLine = monkeyAttributes.split(" ");
                            currMonkey.pointerToMonkeyPositionIfTestFails = Integer.parseInt(currLine[currLine.length-1]);
                        }

                        if (!myReader.hasNextLine()) break;

                    }
                    troopOfMonkeys.add(currMonkey);
                }
                //System.out.println("______*********________");

            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //print(troopOfMonkeys);
        solvePart2 (troopOfMonkeys);

    }

    private static void print(ArrayList<Monkey> troopOfMonkeys) {
        for (int j = 0; j < troopOfMonkeys.size(); j++){
            Monkey currMonkey = troopOfMonkeys.get(j);
            System.out.print(currMonkey.label);
            System.out.println("");
            System.out.println("Items: " + currMonkey.items.toString());
            System.out.println("Op: " + currMonkey.operation);
            System.out.println("divisor: " + currMonkey.divisorForDivisibilityTest);
            System.out.println("If test passes, throws to : " + currMonkey.pointerToMonkeyPositionIfTestPasses + " which is monkey: " + troopOfMonkeys.get(currMonkey.pointerToMonkeyPositionIfTestPasses).label);
            System.out.println("If test passes, throws to : " + currMonkey.pointerToMonkeyPositionIfTestFails + " which is monkey: " + troopOfMonkeys.get(currMonkey.pointerToMonkeyPositionIfTestFails).label);
            System.out.println("Items Processed: " + currMonkey.itemsProcessed);
        }
    }

    private static void solvePart1(ArrayList<Monkey> troopOfMonkeys) {
        int maxActive = 0;
        int secondMaxActive = 0;

        //do 20 rounds of monkey business
        //print(troopOfMonkeys);
        for(int i =0; i < 20; i++) {
            for (int j = 0; j < troopOfMonkeys.size(); j++){
                Monkey currMonkey = troopOfMonkeys.get(j);
                //System.out.println(currMonkey.label);
                for (int k = 0; k < currMonkey.items.size(); k++){
                    BigInteger value = currMonkey.items.get(k);
                    currMonkey.itemsProcessed++;
                    String [] ops = currMonkey.operation.split(" ");

                    if (ops[2].equals("old")){
                        value = value.multiply( value);
                    } else {
                        int num = Integer.parseInt(ops[2]);
                        if (ops[1].equals("+")) {
                            value = value.add(BigInteger.valueOf(num));
                        } else if (ops[1].equals("*")){
                            value = value.multiply(BigInteger.valueOf(num));
                        } else System.out.println("no operation happened");
                    }

                    value = value.divide(BigInteger.valueOf(3));

                    Monkey monkeyWhoseListNeedsToBeAddedTo;
                    //if (value % currMonkey.divisorForDivisibilityTest == 0) monkeyWhoseListNeedsToBeAddedTo = troopOfMonkeys.get(currMonkey.pointerToMonkeyPositionIfTestPasses);
                    //else monkeyWhoseListNeedsToBeAddedTo = troopOfMonkeys.get(currMonkey.pointerToMonkeyPositionIfTestFails);

                    BigInteger divisor = BigInteger.valueOf((long)currMonkey.divisorForDivisibilityTest);
                    BigInteger remainder = value.remainder(divisor);

                    if (remainder.compareTo(BigInteger.ZERO) == 0) monkeyWhoseListNeedsToBeAddedTo = troopOfMonkeys.get(currMonkey.pointerToMonkeyPositionIfTestPasses);
                    else monkeyWhoseListNeedsToBeAddedTo = troopOfMonkeys.get(currMonkey.pointerToMonkeyPositionIfTestFails);

                    monkeyWhoseListNeedsToBeAddedTo.items.add(value);
                }
                currMonkey.items.clear();

            }
            //print(troopOfMonkeys);
        }

        for (int i = 0; i < troopOfMonkeys.size(); i++) {
            System.out.println(troopOfMonkeys.get(i).itemsProcessed);
            /*
            if (maxActive < troopOfMonkeys.get(i).itemsProcessed) {
                maxActive = troopOfMonkeys.get(i).itemsProcessed;
            } else {
                if (secondMaxActive < troopOfMonkeys.get(i).itemsProcessed) secondMaxActive = troopOfMonkeys.get(i).itemsProcessed;
            }

             */
        }

        //turns out I cannot find out the top two integers from a list of 7 B-)

        System.out.println(maxActive + " " + secondMaxActive);
        System.out.println(maxActive * secondMaxActive);
        System.out.println(242*239);

    }

    private static void solvePart2(ArrayList<Monkey> troopOfMonkeys) {

        long start = System.currentTimeMillis();
        int maxActive = 0;
        int secondMaxActive = 0;

        //print(troopOfMonkeys);
        for(int i =0; i < 10000; i++) {
            for (int j = 0; j < troopOfMonkeys.size(); j++){
                Monkey currMonkey = troopOfMonkeys.get(j);
                //System.out.println(currMonkey.label);
                for (int k = 0; k < currMonkey.items.size(); k++){
                    BigInteger value = currMonkey.items.get(k);
                    currMonkey.itemsProcessed++;
                    String [] ops = currMonkey.operation.split(" ");

                    if (ops[2].equals("old")){
                        value = value.multiply( value);
                    } else {
                        int num = Integer.parseInt(ops[2]);
                        if (ops[1].equals("+")) {
                            value = value.add(BigInteger.valueOf(num));
                        } else if (ops[1].equals("*")){
                            value = value.multiply(BigInteger.valueOf(num));
                        } else System.out.println("no operation happened");
                    }

                    Monkey monkeyWhoseListNeedsToBeAddedTo;
                    BigInteger divisor = BigInteger.valueOf((long)currMonkey.divisorForDivisibilityTest);
                    BigInteger remainder = value.remainder(divisor);

                    if (remainder.compareTo(BigInteger.ZERO) == 0) monkeyWhoseListNeedsToBeAddedTo = troopOfMonkeys.get(currMonkey.pointerToMonkeyPositionIfTestPasses);
                    else monkeyWhoseListNeedsToBeAddedTo = troopOfMonkeys.get(currMonkey.pointerToMonkeyPositionIfTestFails);

                    monkeyWhoseListNeedsToBeAddedTo.items.add(value);

                }
                currMonkey.items.clear();

            }
            //print(troopOfMonkeys);
        }

        System.out.println (System.currentTimeMillis() - start);

        for (int i = 0; i < troopOfMonkeys.size(); i++) {
            System.out.println(troopOfMonkeys.get(i).itemsProcessed );
        }

        //turns out I cannot find out the top two integers from a list of 7 B-)

        //System.out.println(maxActive + " " + secondMaxActive);
        //System.out.println(maxActive * secondMaxActive);
        //System.out.println(116301*115962);

    }

}

class Monkey {
    char label;
    ArrayList<BigInteger> items;
    String operation;
    int divisorForDivisibilityTest;
    int pointerToMonkeyPositionIfTestPasses;
    int pointerToMonkeyPositionIfTestFails;
    long itemsProcessed;
}

