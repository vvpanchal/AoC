import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

public class AoC2024_18Dec {

    static int noOfSteps = 999999;
    
    
    public static void main(String[] args) {
        
        char [] [] grid = new char[71][71];
        Point [] points = new Point[4000];
    
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

            
            /* int z = 0;
            while (myReader.hasNextLine() ) {
                String str = myReader.nextLine();
                String [] charArray = str.split(",");
                points[z] = new Point (Integer.parseInt(charArray[0]), Integer.parseInt(charArray[1]) );
                z++;
            } */
            
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        /* List path = new LinkedList<>();
        System.out.println(path.size()-1);
        int n = 0;

        for (int i = 0; i < points.length; i++){
            
            if (path.contains(points[i])) break;
            n++;
        } */

        //System.out.println(points[n].getX() +"," + points[n].getY());
        //System.out.println(res);
        //print (grid);
        //solve (grid);
        
        
        int [] [] visited = new int [71][71];
        walk(grid, visited, 0, 0, 0);
        System.out.println(noOfSteps);
        System.out.println(findShortestPath(grid).size());

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
        //visited[x][y] = 0; //reset current pos to not visited after walks are initiated

    }

    private static List<Point> findShortestPath(char[][] memory) {
        Point start = new Point(0, 0);
        Point end = new Point(70, 70);
        boolean[][] visited = new boolean[71][71];
        Map<Point, Point> parentMap = new HashMap<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.getY()][start.getX()] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.equals(end)) {
                List<Point> path = new LinkedList<>();
                for (Point at = end; at != null; at = parentMap.get(at)) {
                    path.add(at);
                }
                return path;
            }
            
            

            if (current.getY() - 1 >= 0){
                Point newPointUp = new Point(current.getX(), current.getY()-1);
                if (!visited[newPointUp.getY()][newPointUp.getX()] && memory[newPointUp.getY()][newPointUp.getX()] != '#') {
                    queue.add(newPointUp);
                    visited[newPointUp.getY()][newPointUp.getX()] = true;
                    parentMap.put(newPointUp, current);
                }
            }

            if (current.getY() + 1 <= 70){
                Point newPointDown = new Point(current.getX(), current.getY()+1);
                if (!visited[newPointDown.getY()][newPointDown.getX()] && memory[newPointDown.getY()][newPointDown.getX()] != '#') {
                    queue.add(newPointDown);
                    visited[newPointDown.getY()][newPointDown.getX()] = true;
                    parentMap.put(newPointDown, current);
                }
            }

            if (current.getX() - 1 >= 0){
                Point newPointLeft = new Point(current.getX()-1, current.getY());
                if (!visited[newPointLeft.getY()][newPointLeft.getX()] && memory[newPointLeft.getY()][newPointLeft.getX()] != '#') {
                    queue.add(newPointLeft);
                    visited[newPointLeft.getY()][newPointLeft.getX()] = true;
                    parentMap.put(newPointLeft, current);
                }
            }

            if (current.getX() + 1 <= 70){
                Point newPointRight = new Point(current.getX()+1, current.getY());
                if (!visited[newPointRight.getY()][newPointRight.getX()] && memory[newPointRight.getY()][newPointRight.getX()] != '#') {
                    queue.add(newPointRight);
                    visited[newPointRight.getY()][newPointRight.getX()] = true;
                    parentMap.put(newPointRight, current);
                }
            }
        
        }

        return Collections.emptyList(); // Return an empty list if there is no path
    }

}