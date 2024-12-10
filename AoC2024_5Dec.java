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

public class AoC2024_5Dec {
    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");
        int product = 0;
        char [] [] grid = new char[140][140];
        
        try {
            File myObj = new File("./Aoc_5Dec2024.txt");
            //File myObj = new File("./testData.txt");
            Scanner myReader = new Scanner(myObj);
            Map<String, List<String>> rules = new HashMap<>();
            int result = 0;
            int result2 = 0;

            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                
                
                
                if (str.isBlank()) continue;
                if (str.contains("|")) {
                    String[] arr = str.split("\\|");
                    if (!rules.containsKey(arr[0])) {
                        rules.put(arr[0], new ArrayList<>());
                    }
                    rules.get(arr[0]).add(arr[1]);
                }
                if (str.contains(",")) {
                    Map<String, Integer> lineToCheck = new HashMap<>();
                    String[] splitArr = str.split(",");
                    //part 2
                    List<String> before = Arrays.asList(splitArr);
                    List<String> after = new ArrayList<>();
                    after.addAll(Arrays.asList(splitArr));

                    after.sort((a, b) -> {
                        if (!rules.containsKey(a)) return 0;
                        List<String> right = rules.get(a);
                        if (right.contains(b)) return -1;
                        return 0;
                    });

                    if (!before.toString().equals(after.toString())) {
                        result2 += Integer.parseInt(after.get(after.size() / 2));
                    }

                    // end part 2

                    for (int i = 0; i < splitArr.length; i++) {
                        lineToCheck.put(splitArr[i], i);
                    }

                    boolean correct = true;
                    for (Map.Entry<String, List<String>> entry : rules.entrySet()) {
                        if (lineToCheck.containsKey(entry.getKey())) {
                            int start = lineToCheck.get(entry.getKey());
                            for (String s : entry.getValue()) {
                                if (lineToCheck.containsKey(s) && start > lineToCheck.get(s)) {
                                    correct = false;
                                    break;
                                }
                            }
                        }
                    }
                    if (correct) {
                        result += Integer.parseInt(splitArr[splitArr.length / 2]);
                    }
                }
            

            // 6236 - high

            
                
            }
            System.out.println(result);
            System.out.println(result2);
            
            
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