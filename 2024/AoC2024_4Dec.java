import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AoC2024_4Dec {
    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");
        int product = 0;
        char [] [] grid = new char[140][140];
        
        try {
            File myObj = new File("./Aoc_4Dec2024.txt");
            //File myObj = new File("./testData.txt");
            Scanner myReader = new Scanner(myObj);
            int row = 0;

            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                grid[row] = str.toCharArray();
                row++;
                //System.out.println(inputArr.length);
                
            }
            solve (grid);
            solve2(grid);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //System.out.println(product);

    }

    public static void solve (char [] [] grid){
        System.out.println(getCount(grid));
    }

    public static void solve2(char [] [] grid){
        System.out.println(checkDiag(grid));
    }

    public static int checkDiag (char [] [] grid){
        int cnt = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'A') {
                    cnt += checkDiagonal(grid, i, j);
                }
            }
        }
        return cnt;
    }
    private static int checkDiagonal(char[][] grid, int i, int j) {
        try {
            var diag1 = "" + grid[i + 1][j - 1] + grid[i - 1][j + 1];
            var diag2 = "" + grid[i - 1][j - 1] + grid[i + 1][j + 1];
            return (diag1.equals("MS") || diag1.equals("SM")) && (diag2.equals("MS") || diag2.equals("SM")) ? 1 : 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    public static int getCount (char [] [] grid){
        int cnt = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'X') {
                    cnt += checkDirectionXmas(grid, i, j, 0, 1);   // Horizontal right
                    cnt += checkDirectionXmas(grid, i, j, 0, -1);  // Horizontal left
                    cnt += checkDirectionXmas(grid, i, j, 1, 0);   // Vertical down
                    cnt += checkDirectionXmas(grid, i, j, -1, 0);  // Vertical up
                    cnt += checkDirectionXmas(grid, i, j, 1, 1);   // Diagonal down-right
                    cnt += checkDirectionXmas(grid, i, j, -1, -1); // Diagonal up-left
                    cnt += checkDirectionXmas(grid, i, j, 1, -1);  // Diagonal down-left
                    cnt += checkDirectionXmas(grid, i, j, -1, 1);  // Diagonal up-right
                }
            }
        }
        return cnt;
    }

    private static int checkDirectionXmas(char[][] grid, int i, int j, int di, int dj) {
        try {
            return (grid[i + di][j + dj] == 'M' && grid[i + 2 * di][j + 2 * dj] == 'A' && grid[i + 3 * di][j + 3 * dj] == 'S') ? 1 : 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
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
