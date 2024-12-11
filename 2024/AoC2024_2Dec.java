import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class AoC2024_2Dec {
    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");
        int countOfSafe = 0;
        
        try {
            File myObj = new File("./Aoc_2Dec2024.txt");
            //File myObj = new File("./testData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                String[] myArray = str.split(" ");
                /* 
                for (int i = 0; i < myArray.length - 1; i++){
                    int one = Integer.parseInt(myArray[i]);
                    int two = Integer.parseInt(myArray[i + 1]);
                    if (Math.abs(two - one) >3 || Math.abs (two - one) < 1) break;
                    if (i == 0) {
                        if (two > one) dir = 1;
                        else dir = -1;
                        continue;
                    }
                    if ( (two - one)*dir < 0) break;
                    if (i == myArray.length - 2) countOfSafe++;

                }
                */
                //if (isSafe(myArray)) countOfSafe++; //part 1
                if (isSafeBarOneLevel(myArray)) countOfSafe++; //part 2    
                
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(countOfSafe);

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
        
        for (int i = 0; i < arr.length; i++){
            //List <String> workingAL = Arrays.asList(arr);
            ArrayList <String> workingAL = makeAL (arr);
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
