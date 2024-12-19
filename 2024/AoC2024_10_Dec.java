import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
//import Point;

public class AoC2024_10_Dec {
    static int result = 0;
    static int  n = 0;
    public static void main(String[] args) {
        //String newline = System. getProperty("line.separator");
        int width = 57;
        int height = 57;
        int[] map = new int[width * height];

        int i = 0;
        
        try {
            File myObj = new File("./Aoc_10Dec2024.txt");
            //File myObj = new File("./testData.txt");
            Scanner myReader = new Scanner(myObj);
            int row = 0;

            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                for (String character : str.trim().split("")) {
                    map[i] = Integer.parseInt(character);
                    i++;
                }
                
            }

            long count = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Set<Point> set = countTrails(map, x, y, width, height,0);
                    count += set.size();
                }
            }
            System.out.println(count);
                
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //System.out.println(product);

    }

    private static Set<Point> countTrails(int[] map, int x, int y, int width, int height, int val) {
        if (x >= width) return new HashSet<>();
        if (y >= height) return new HashSet<>();
        if (x < 0) return new HashSet<>();
        if (y < 0) return new HashSet<>();

        if (map[y * width + x] != val) return new HashSet<>();
        if (val == 9) {
            Set<Point> newSet = new HashSet();
            newSet.add(new Point(9, x, y));
            return newSet;
        }

        Set<Point> newSet = new HashSet();
        newSet.addAll(countTrails(map, x + 1, y, width, height, val + 1));
        newSet.addAll(countTrails(map, x - 1, y, width, height, val + 1));
        newSet.addAll(countTrails(map, x, y + 1, width, height, val + 1));
        newSet.addAll(countTrails(map, x, y - 1, width, height, val + 1));

        return newSet;
    }

}