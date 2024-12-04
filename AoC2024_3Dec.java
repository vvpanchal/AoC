import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AoC2024_3Dec {
    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");
        int product = 0;
        
        try {
            File myObj = new File("./Aoc_3Dec2024.txt");
            //File myObj = new File("./testData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                //char [] inputArr = str.toCharArray();
                //System.out.println(inputArr.length);
                Pattern pattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)");
                Matcher matcher = pattern.matcher(str);
                //boolean matchFound = matcher.find();
                while (matcher.find()) {
                    String formula = matcher.group(0);
                    product = product + getProduct(formula);
                }
                
                
                
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(product);

    }

    public static int getProduct (String evalThis) {
        int first = 0;
        int second = 0;
        
        String bracket = evalThis.substring(evalThis.indexOf("(")+1, evalThis.indexOf(")"));
        String [] nums = bracket.split(",");
        first = Integer.parseInt(nums[0]);
        second = Integer.parseInt(nums[1]);
        return first * second;
    
    }

    public static boolean isSafe (Object [] arr) {
        int dir = 0;
        for (int i = 0; i < arr.length - 1; i++){
            int one = Integer.parseInt((String)arr[i]);
            int two = Integer.parseInt((String)arr[i + 1]);
            if (Math.abs(two - one) >3 || Math.abs (two - one) < 1) break;
            if (i == 0) {
                if (two > one) dir = 1;
                else dir = -1;
                continue;
            }
            if ( (two - one)*dir < 0) break;
            if (i == arr.length - 2) return true;

        }
        return false;
    }

    public static boolean isSafeBarOneLevel (String [] arr) {
        if (isSafe(arr)) return true;
        for (int i = 0; i < arr.length - 1; i++){
            ArrayList <String> workingAL = makeAL(arr);
            workingAL.remove(i);
            if (isSafe(workingAL.toArray())) return true;
        }
        return false;
    }

    public static ArrayList <String> makeAL (String [] hurray){
        ArrayList <String> res = new ArrayList<String>();
        for (int i = 0; i < hurray.length; i++) res.add(hurray[i]);
        return res;
    }

    public static int weHaveANumber(String num) {
        
        return -1;
    }


}