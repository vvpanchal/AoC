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

public class AoC2024_18Dec {

    static int noOfSteps = 999999;
    
    
    public static void main(String[] args) {
        
        char [] [] grid = new char[71][71];
    
        for (int i = 0; i < 71; i++){
            for (int j = 0; j < 71 ; j++){
                grid[i][j] = '.';
            }
        }
        
        //boolean [] [] visited = new boolean[130][130];

        //long res = 0l;
        
        try {
            File myObj = new File("./Aoc_18Dec2024.txt");
            //File myObj = new File("./testData.txt");
            Scanner myReader = new Scanner(myObj);
            int num = 0;

            while (myReader.hasNextLine() && num < 1024) {
                String str = myReader.nextLine();
                String [] charArray = str.split(",");
                grid [Integer.parseInt(charArray[0])][Integer.parseInt(charArray[1])] = '#';
                num++;
            }
            
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //System.out.println(res);
        //print (grid);
        solve (grid);
        
        
        //int [] [] visited = new int [71][71];
        //walk(grid, visited, 0, 0, 0);
        //System.out.println(noOfSteps);

    }

    public static void print (char [] [] grid){
        for (int i = 0; i < 71; i++){
            for (int j = 0; j < 71 ; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
    }

    public static void print (int [] [] grid){
        for (int i = 0; i < 71; i++){
            for (int j = 0; j < 71 ; j++){
                System.out.print(grid[i][j] + ",");
            }
            System.out.println("");
        }
    }

    

    public static void solve (char[][]grid){
        //int steps = 0;
        int [] [] stepCounts = new int [71][71];
        for (int i = 0; i < 71; i++){
            for (int j = 0; j < 71 ; j++){
                stepCounts[i][j] = 999999;
            }
        }

        for (int i = 0; i < 71; i++){
            for (int j = 0; j < 71; j++){
                if (i ==0 && j==0) {
                    stepCounts[i][j] = 0;
                    continue;
                }

                if (grid [i][j] == '#'){
                    stepCounts[i][j] = 999999;
                    continue;
                }

                int left = (i-1 < 0)? 999999: stepCounts[i-1][j];
                int top = (j-1 < 0)? 999999: stepCounts[i][j-1];
                int bottom = (i+1 > 70)? 999999: stepCounts[i+1][j];
                int right = (j+1 > 70)? 999999: stepCounts[i][j+1];

                int min = Math.min (left, top);
                min = Math.min (min, bottom);
                min = Math.min (min, right);

                stepCounts[i][j] = min + 1;

            }

        }
        //print(stepCounts);
        //System.out.println(stepCounts[70][70]);
    }

    public static void walk (char [] [] grid, int [] [] visited, int x, int y, int steps) {
        //update minimum number of steps and return
        if (x == 70 && y == 70) {
            noOfSteps = Math.min(noOfSteps, steps);
            return;
        }

        //if out of grid, cant do anything
        if (x < 0 || x > 70 || y < 0 || y > 70) return;

        //do nothing and return if pos is visited or corrupted
        if (visited[x][y] == 1) return; //
        if (grid[x][y] == '#') return;

        //update current pos and visited and walk in four directions
        visited[x][y] = 1;
        walk (grid, visited, x+1, y, steps+1);
        walk (grid, visited, x-1, y, steps+1);
        walk (grid, visited, x, y+1, steps+1);
        walk (grid, visited, x, y-1, steps+1);
        visited[x][y] = 0; //reset current pos to not visited after walks are initiated

    }

    

}