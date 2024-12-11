import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AoC2022_1Dec {
    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");
        int max = 0;
        int secondMax = 0;
        int thirdMax = 0;
        int current = 0;
        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_1Dec_1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data==""){
                    if (current > max) {
                        thirdMax = secondMax;
                        secondMax = max;
                        max = current;
                    } else if (current > secondMax){
                        thirdMax = secondMax;
                        secondMax = current;
                    } else if (current > thirdMax) thirdMax = current;
                    current = 0;
                    System. out. println("Enter has been pressed");
                } else {
                    current = current + Integer.parseInt(data);
                    System.out.println(data);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println (max);
        System.out.println (max + secondMax + thirdMax);
    }
}

