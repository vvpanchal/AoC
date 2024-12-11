import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AoC2022_4Dec {
    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");
        int count = 0;
        //int counter = 0;

        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_4Dec.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String workingStr = data.replace('-',',');
                String [] range = workingStr.split(",");
                //for (int i = 0; i < range.length; i++) System.out.print (Integer.parseInt(range[i]) + " ");
                //System.out.println("");
                /*
                part 1
                boolean firstRangeContainsTheSecond = (Integer.parseInt(range[0]) <= Integer.parseInt(range[2]) && Integer.parseInt(range[1])>= Integer.parseInt(range[3]));
                boolean secondRangeContainsTheFirst = (Integer.parseInt(range[2]) <= Integer.parseInt(range[0]) && Integer.parseInt(range[3])>= Integer.parseInt(range[1]));
                */
                boolean firstRangeToLeftOfTheSecond = (Integer.parseInt(range[1]) < Integer.parseInt(range[2]) );
                boolean secondRangeToLeftOfTheFirst = (Integer.parseInt(range[0]) > Integer.parseInt(range[3]) );

                if (!(firstRangeToLeftOfTheSecond || secondRangeToLeftOfTheFirst) ) count++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println (count);
        //System.out.println (max + secondMax + thirdMax);
    }
}

