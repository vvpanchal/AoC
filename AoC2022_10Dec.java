import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC2022_10Dec {

    public static void main(String[] args) {

        int cycle = 0;
        int x = 1;
        int res = 0;
        int [] valueOfXAtNthCycle = new int [250];


        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_10Dec.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                valueOfXAtNthCycle[cycle] = x;
                if (data.equals("noop")){
                    cycle = cycle + 1;
                } else {

                    String [] inputDataLine = data.split(" ");
                    int increment = Integer.parseInt(inputDataLine[1]);
                    cycle = cycle + 1;
                    valueOfXAtNthCycle[cycle] = x;
                    cycle = cycle + 1;
                    valueOfXAtNthCycle[cycle] = x;
                    x = x + increment;

                }

            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        res = valueOfXAtNthCycle[20]*20 + valueOfXAtNthCycle[60]*60 + valueOfXAtNthCycle[100]*100 + valueOfXAtNthCycle[140]*140 + valueOfXAtNthCycle[180]*180 + valueOfXAtNthCycle[220]*220;
        System.out.println(res);

        //part 2

        char [] [] displayGrid = new char [6] [40];
        int [] [] xValueGrid = new int [6][40];
        int cycleCounter = 0;
        for (int i = 0; i < displayGrid.length; i++){
            for (int j = 0; j < displayGrid[i].length;j++){
                //xValueGrid[i][j] = valueOfXAtNthCycle[cycleCounter];
                xValueGrid[i][j] = cycleCounter;
                if (Math.abs(valueOfXAtNthCycle[cycleCounter]-cycleCounter%40) < 2) displayGrid[i][j] = '#';
                else displayGrid[i][j] = '.';
                cycleCounter++;
            }
        }
        for (int i = 0; i < xValueGrid.length; i++){
            for (int j = 0; j < xValueGrid[i].length;j++){
                System.out.print(  xValueGrid[i][j] + " " );
            }
            System.out.println("");
        }

        for (int i = 0; i < displayGrid.length; i++){
            for (int j = 0; j < displayGrid[i].length;j++){
                System.out.print(" " + displayGrid[i][j] + " " );
            }
            System.out.println("");
        }

    }

}

