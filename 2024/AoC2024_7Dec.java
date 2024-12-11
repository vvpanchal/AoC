import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class AoC2024_7Dec {
    
    
    public static void main(String[] args) {
        //String newline = System. getProperty("line.separator");
        //int product = 0;
        //char [] [] grid = new char[130][130];
        
        //boolean [] [] visited = new boolean[130][130];

        long res = 0l;
        
        try {
            File myObj = new File("./Aoc_7Dec2024.txt");
            //File myObj = new File("./testData.txt");
            Scanner myReader = new Scanner(myObj);
            

            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                String [] arr = str.split(":");
                long prod = Long.parseLong(arr[0].trim());
                //if (res == 0l) System.out.println(arr[1]);
                if (equates (prod, arr[1].trim().split(" "), 0, -1l)) res = res + prod;
            }
            
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(res);
        //solve (grid);

    }

    public static boolean equates (long prod, String [] nums, int idx, long inter){

        if (idx == nums.length) return (prod == inter);
        return (equates (prod, nums, idx + 1, ((inter==-1)? 1 : inter)*Integer.parseInt(nums[idx])) || equates(prod, nums, idx + 1, ((inter==-1)?0:inter)+Integer.parseInt(nums[idx])));
        
    }

}