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

public class AoC2024_6Dec {
    static int [] [] newCoords =  {{-1,0},{0, 1}, {1,0}, {0,-1} };
    static int cnt = 0;
    public static void main(String[] args) {
        //String newline = System. getProperty("line.separator");
        //int product = 0;
        char [] [] grid = new char[130][130];
        
        //boolean [] [] visited = new boolean[130][130];
        
        try {
            File myObj = new File("./Aoc_6Dec2024.txt");
            //File myObj = new File("./testData.txt");
            Scanner myReader = new Scanner(myObj);
            int row = 0;

            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                grid[row] = str.toCharArray();
                row++;
            }
            
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        solve (grid);

    }

    public static void solve (char [] [] grid){
        int x = -1;
        int y = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '^') {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        System.out.println("Starting to solve from "+ x +" " + y);
        System.out.println(getCount(grid, x, y, 0));
    }

    public static int getCount (char [] [] grid, int x, int y, int mod){
        
        try {

            int n = 0;
            int newX = x ;
            int newY = y ;

            while (grid [newX][newY]!= '#'){
                if (grid [newX][newY] == '.' ){
                    cnt++;
                    grid[newX][newY] = 'X';
                }
                n++;
                newX = x + newCoords[mod%4][0]*n;
                newY = y + newCoords[mod%4][1]*n;
            }
            System.out.println ("turning right at " + newX + " " + newY + "as found: " + grid[newX][newY]);
            System.out.println("Going back one step to: " + (newX-newCoords[mod%4][0]) + ","+ (newY - newCoords[mod%4][1]) + "and turning right");
            getCount (grid, newX - newCoords[mod%4][0], newY-newCoords[mod%4][1], mod+1);
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println ("going off grid");
            return cnt;
        }

        return cnt;
    }

    
}
