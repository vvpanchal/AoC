import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class AoC2022_5Dec {
    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");

        ArrayList <Stack<String>> stackList = new ArrayList<Stack<String>>();
        //manually setting up the stacks and their initial condition
        String [] firstStackEntries = {"Z", "T", "F", "R", "W", "J", "G"};
        Stack<String> stack1 = new Stack<String>();
        for (int i = 0; i < firstStackEntries.length; i++) stack1.push(firstStackEntries[i]);
        stackList.add(stack1);

        String [] secondStackEntries = {"G", "W", "M"};
        Stack<String> stack2 = new Stack<String>();
        for (int i = 0; i < secondStackEntries.length; i++) stack2.push(secondStackEntries[i]);
        stackList.add(stack2);

        String [] thirdStackEntries = {"J", "N", "H", "G"};
        Stack<String> stack3 = new Stack<String>();
        for (int i = 0; i < thirdStackEntries.length; i++) stack3.push(thirdStackEntries[i]);
        stackList.add(stack3);

        String [] fourthStackEntries = {"J", "R", "C", "N", "W"};
        Stack<String> stack4 = new Stack<String>();
        for (int i = 0; i < fourthStackEntries.length; i++) stack4.push(fourthStackEntries[i]);
        stackList.add(stack4);

        String [] fifthStackEntries = {"W", "F", "S", "B", "G", "Q", "V", "M"};
        Stack<String> stack5 = new Stack<String>();
        for (int i = 0; i < fifthStackEntries.length; i++) stack5.push(fifthStackEntries[i]);
        stackList.add(stack5);

        String [] sixthStackEntries = {"S", "R", "T", "D", "V", "W", "C"};
        Stack<String> stack6 = new Stack<String>();
        for (int i = 0; i < sixthStackEntries.length; i++) stack6.push(sixthStackEntries[i]);
        stackList.add(stack6);

        String [] seventhStackEntries = {"H", "B", "N", "C", "D", "Z", "G", "V"};
        Stack<String> stack7 = new Stack<String>();
        for (int i = 0; i < seventhStackEntries.length; i++) stack7.push(seventhStackEntries[i]);
        stackList.add(stack7);

        String [] eighthStackEntries = {"S", "J", "N", "M", "G", "C"};
        Stack<String> stack8 = new Stack<String>();
        for (int i = 0; i < eighthStackEntries.length; i++) stack8.push(eighthStackEntries[i]);
        stackList.add(stack8);

        String [] ninthStackEntries = {"G", "P", "N", "W", "C", "J", "D", "L"};
        Stack<String> stack9 = new Stack<String>();
        for (int i = 0; i < ninthStackEntries.length; i++) stack9.push(ninthStackEntries[i]);
        stackList.add(stack9);

        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_5Dec.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //String workingStr = data.replace('-',',');
                String [] range = data.split(" ");
                System.out.println (range[1] + " " + range [3] + " " + range [5]);
                int fromStack = Integer.parseInt(range[3]);
                int toStack = Integer.parseInt(range[5]);
                int count = Integer.parseInt(range[1]);
/*
//part 1
                for (int j =0; j <count; j++){
                    stackList.get(toStack-1).push(stackList.get(fromStack-1).pop());
                }
*/
                Stack <String> holdingStack = new Stack<String>();
                for (int j =0; j <count; j++){
                    holdingStack.push(stackList.get(fromStack-1).pop());
                }
                for (int j =0; j <count; j++){
                    stackList.get(toStack-1).push(holdingStack.pop());
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stackList.size(); i++) sb.append(stackList.get(i).pop());
        System.out.println (sb.toString());
        //System.out.println (max + secondMax + thirdMax);
    }
}

