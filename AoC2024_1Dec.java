import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AoC2024_1Dec {
    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");
        ArrayList <Integer> arLst1 = new ArrayList<>();
        ArrayList <Integer> arLst2 = new ArrayList<>();

        try {
            File myObj = new File("./Aoc_1Dec2024.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                String[] myArray = str.split("   ");
                arLst1.add(Integer.parseInt(myArray[0]));
                arLst2.add(Integer.parseInt(myArray[1]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        arLst1.sort(null);
        arLst2.sort(null);

        int sum = 0; //part 1 
        //System.out.println(arLst2.get(23));
        for (int i=0; i < arLst1.size(); i++){
            sum += Math.abs(arLst2.get(i) - arLst1.get(i));
        }
        System.out.println("Sum: "+sum);

        //part 2
        int score = 0;
        for (int i=0; i < arLst1.size(); i++){
            if (arLst2.indexOf(arLst1.get(i)) == -1) continue;
            if (arLst2.indexOf(arLst1.get(i)) == arLst2.lastIndexOf(arLst1.get(i))) {
                score = score + arLst1.get(i);
                continue;
            }
            score = score + arLst1.get(i) *(arLst2.lastIndexOf(arLst1.get(i)) - arLst2.indexOf(arLst1.get(i)) +1);
        }
        System.out.println("score: " + score);


    }

    public static int weHaveANumber(String num) {
        
        int sum = 0;

        
        return -1;
    }


}