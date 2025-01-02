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

public class AoC2024_20Dec {

    static int noOfSteps = 999999;
    static int size = 141; // 15 for test input or 141 puzzle input
    
    public static void main(String[] args) {
        
        char [] [] grid = new char[size][size];
        //Point [] points = new Point[4000];
    
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size ; j++){
                grid[i][j] = '.';
            }
        }
        
        try {
            File myObj = new File("./Aoc_20Dec2024.txt");
            //File myObj = new File("./testData.txt");
            Scanner myReader = new Scanner(myObj);
            int num = 0;

            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                char [] charArray = str.toCharArray();
                for (int i = 0; i < charArray.length; i++){
                    grid [num][i] = charArray[i];
                }
                num++;
            }
            
            myReader.close();

        } catch (FileNotFoundException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

        }
        //print(grid);
        Point start = new Point (0,0);
        Point end = start;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (grid[i][j] == 'S'){
                    start = new Point (j, i);
                }
                if (grid[i][j] == 'E'){
                    end = new Point(j, i);
                }
            }
        }

        ArrayList path = findShortestPath(grid, start, end);
        //System.out.println(path.toString());
        System.out.println(path.size()-1);
        path.reversed();

        int res = 0;
        int saving = 102;

        for (int i = 0; i < path.size(); i++){

            Point left = new Point(((Point)path.get(i)).getX() - 2, ((Point)path.get(i)).getY());
            Point right = new Point( ((Point)path.get(i)).getX() + 2, ((Point)path.get(i)).getY());
            Point up = new Point(  ((Point)path.get(i)).getX(), ((Point)path.get(i)).getY()-2 );
            Point down = new Point( ((Point)path.get(i)).getX(), ((Point)path.get(i)).getY() + 2);

            if (path.contains(left) && (path.indexOf(left) - i >= saving)) res++;
            if (path.contains(right) && (path.indexOf(right) - i >= saving)) res++;
            if (path.contains(up) && (path.indexOf(up) - i >= saving)) res++;
            if (path.contains(down) && (path.indexOf(down) - i >= saving)) res++;

        }

        System.out.println (res);

    }

    public static void print (char [] [] grid){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length ; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
    }

    public static void print (int [] [] grid){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length ; j++){
                System.out.print(grid[i][j] + ",");
            }
            System.out.println("");
        }
    }

    private static ArrayList<Point> findShortestPath(char[][] memory, Point start, Point end) {

        boolean[][] visited = new boolean[size][size];
        Map<Point, Point> parentMap = new HashMap<Point, Point>();
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(start);
        visited[start.getY()][start.getX()] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.equals(end)) {
                ArrayList<Point> path = new ArrayList<Point>();
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

            if (current.getY() + 1 < size){
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

            if (current.getX() + 1 < size){
                Point newPointRight = new Point(current.getX()+1, current.getY());
                if (!visited[newPointRight.getY()][newPointRight.getX()] && memory[newPointRight.getY()][newPointRight.getX()] != '#') {
                    queue.add(newPointRight);
                    visited[newPointRight.getY()][newPointRight.getX()] = true;
                    parentMap.put(newPointRight, current);
                }
            }
        
        }

        return new ArrayList<Point>(); // Return an empty list if there is no path
    }

}