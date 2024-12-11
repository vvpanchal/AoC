import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC2022_12Dec {

    public static void main(String[] args) {
        //String newline = System. getProperty("line.separator");
        int count = 0;
        int rows = 0;
        int columns = 0;

        //part 2 variable
        int part2Res = 0;

        //get number of rows and columns. one pass of the input file
        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC_12Dec2022.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                rows++;
                columns = data.length();

            }
            //System.out.println(rows + " " + columns);
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //initialize 2D array and take input
        int [] [] heightMap = new int [rows][columns];
        boolean [] [] visited = new boolean [rows][columns];


        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC_12Dec2022.txt");
            Scanner myReader = new Scanner(myObj);
            int r = 0;
            int startX = 0;
            int startY = 0;

            int targetX = 0;
            int targetY = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                for (int i=0; i < data.length(); i++) {
                    int val = 0;
                    if (data.charAt(i) > 'Z') val = data.charAt(i) - 'a';
                    else if (data.charAt(i) == 'S') {
                        val = 0;
                        startX = r;
                        startY = i;
                    } else if (data.charAt(i) == 'E'){
                        val = 25;
                        targetX = r;
                        targetY = i;
                    }
                    heightMap[r][i] = val;
                }
                r++;

            }
            myReader.close();
            //print2DArray(heightMap);
            System.out.println(" " );
            System.out.println("start X, startY: "+ startX +"," +startY);
            System.out.println("Target X, targetY: "+ targetX +"," +targetY);
            count = goFigure (heightMap, startX, startY, targetX, targetY, 0);

            part2Res = bruteMode (heightMap, targetX, targetY);
            //scenicScore = goFigurePart2IcantBeBotheredToBeEfficient (heightMap);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println (count) ;
        System.out.println (part2Res) ;
        //System.out.println (scenicScore) ;

    }

    private static int bruteMode(int[][] heightMap, int targetX, int targetY) {
        int min = 1000;
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[0].length; j++) {
                if (heightMap[i][j] ==0) {
                    int x = i;
                    int y = j;

                    min = Math.min (min, goFigure (heightMap, x, y, targetX, targetY, 0));
                    //System.out.println ("Evaluated for i,j: "+ i + ", "+j + " with min steps so far: "+ min);
                }
            }
        }
        return min;
    }


    private static void print2DArray (int [] [] arrToBePrinted) {
        for(int i = 0; i < arrToBePrinted.length; i++){
            System.out.println("");
            for (int j = 0; j < arrToBePrinted[0].length; j++) System.out.print(arrToBePrinted[i][j] + " ");
        }
    }
    private static void print2DArray (boolean [] [] arrToBePrinted) {
        for(int i = 0; i < arrToBePrinted.length; i++){
            System.out.println("");
            for (int j = 0; j < arrToBePrinted[0].length; j++) System.out.print(arrToBePrinted[i][j] + " ");
        }
    }

    private static int noFigure(int [] [] heightMap, boolean [] [] visited, int stX, int stY, int tX, int tY, int stepsSoFar) {
        if (heightMap == null) return 0; //just in case I return to form and *&^% up!
        //System.out.println(stX +"," + stY + "    " + tX+","+tY + " steps: " + stepsSoFar);
        if (stX == tX && stY == tY) {
            System.out.println("reached!");
            //print2DArray(visited);
            return stepsSoFar;
        }
        visited[stX][stY] = true;
        int goingRight = Integer.MAX_VALUE;
        int goingLeft = Integer.MAX_VALUE;
        int goingUp = Integer.MAX_VALUE;
        int goingDown = Integer.MAX_VALUE;

        if ( (stX+1 < heightMap.length)&& !visited[stX+1][stY] && heightMap[stX+1][stY] - heightMap[stX][stY] < 2 ) {
            //check if going right gets you there
            //System.out.println("Going right");
            goingRight = noFigure (heightMap, visited,stX + 1, stY, tX, tY, stepsSoFar + 1);
        }
        if ( (stX-1 > -1)&& !visited[stX-1][stY] && heightMap[stX-1][stY] - heightMap[stX][stY] < 2 ) {
            //check if going left gets you there
            //System.out.println("Going left");
            goingLeft = noFigure (heightMap, visited,stX - 1, stY, tX, tY, stepsSoFar + 1);
        }
        if ( (stY+1 < heightMap[0].length)&& !visited[stX][stY+1] && heightMap[stX][stY+1] - heightMap[stX][stY] < 2 ) {
            //check if going down gets you there
            //System.out.println("Going down");
            goingDown = noFigure (heightMap, visited, stX, stY + 1, tX, tY, stepsSoFar + 1);
        }
        if ( (stY-1 > -1) && !visited[stX][stY-1] && heightMap[stX][stY-1] - heightMap[stX][stY] < 2 ) {
            //check if going up gets you there
            //System.out.println("Going up");
            goingUp = noFigure (heightMap, visited, stX, stY-1, tX, tY, stepsSoFar + 1);
        }

        visited[stX][stY] = false;

        return Math.min (goingUp, Math.min (goingDown, Math.min (goingLeft, goingRight)));

    }

    private static int goFigure(int [] [] heightMap, int stX, int stY, int tX, int tY, int stepsSoFar) {

        boolean [] [] beenThere = new boolean[heightMap.length][heightMap[0].length];
        //Queue<int [] > queue = new ArrayDeque<int [] >();
        Queue <Integer> queue = new ArrayDeque<Integer>();
        //queue.add(new int [] {stX,stY});
        queue.add (10000*stX + stY);

        while (!queue.isEmpty() ) {
            int num = queue.poll();
            int level = num / 1000000;
            num = num - level*1000000;
            int currX = num / 10000;
            int currY = num % 10000;
            if (currX == tX && currY == tY) return level;
            beenThere[currX][currY] = true;
            if ((currX + 1 < heightMap.length) &&
                    !beenThere[currX + 1][currY] &&
                    heightMap[currX + 1][currY] - heightMap[currX][currY] < 2) {
                queue.add(10000 * (currX + 1) + currY + 1000000*(level+1));
                beenThere[currX + 1][currY] = true;
            }


            if ((currX - 1 > -1) &&
                    !beenThere[currX - 1][currY] &&
                    heightMap[currX - 1][currY] - heightMap[currX][currY] < 2) {
                queue.add(10000 * (currX - 1) + currY+ 1000000*(level+1));
                beenThere[currX - 1][currY] = true;
            }


            if ((currY + 1 < heightMap[0].length) &&
                    !beenThere[currX][currY + 1] &&
                    heightMap[currX][currY + 1] - heightMap[currX][currY] < 2) {
                beenThere[currX][currY + 1] = true;
                queue.add(10000 * currX + currY + 1+ 1000000*(level+1));
            }

            if ( (currY-1 > -1)&&
                    !beenThere[currX][currY-1] &&
                    heightMap[currX][currY-1] - heightMap[currX][currY] < 2 ){
                queue.add ( 10000*currX + currY-1+ 1000000*(level+1));
                beenThere[currX][currY - 1] = true;
            }


            stepsSoFar++;
            //if (stepsSoFar %1000000 == 0) System.out.println (queue.size());

        }

        return Integer.MAX_VALUE;

    }

    private static int goFigure2(int [] [] heightMap, int stX, int stY, int stepsSoFar) {

        boolean [] [] beenThere = new boolean[heightMap.length][heightMap[0].length];
        //Queue<int [] > queue = new ArrayDeque<int [] >();
        Queue <Integer> queue = new ArrayDeque<Integer>();
        //queue.add(new int [] {stX,stY});
        queue.add (10000*stX + stY);

        while (!queue.isEmpty() ) {
            int num = queue.poll();
            int level = num / 1000000;
            num = num - level*1000000;
            int currX = num / 10000;
            int currY = num % 10000;
            if (heightMap[stX][stY] == 0) return level;
            beenThere[currX][currY] = true;
            if ((currX + 1 < heightMap.length) &&
                    !beenThere[currX + 1][currY] &&
                    heightMap[currX][currY] - heightMap[currX+1][currY] < 2) {
                queue.add(10000 * (currX + 1) + currY + 1000000*(level+1));
                beenThere[currX + 1][currY] = true;
            }


            if ((currX - 1 > -1) &&
                    !beenThere[currX - 1][currY] &&
                    heightMap[currX][currY] - heightMap[currX-1][currY] < 2) {
                queue.add(10000 * (currX - 1) + currY+ 1000000*(level+1));
                beenThere[currX - 1][currY] = true;
            }


            if ((currY + 1 < heightMap[0].length) &&
                    !beenThere[currX][currY + 1] &&
                    heightMap[currX][currY] - heightMap[currX][currY+1] < 2) {
                beenThere[currX][currY + 1] = true;
                queue.add(10000 * currX + currY + 1+ 1000000*(level+1));
            }

            if ( (currY-1 > -1)&&
                    !beenThere[currX][currY-1] &&
                    heightMap[currX][currY] - heightMap[currX][currY-1] < 2 ){
                queue.add ( 10000*currX + currY-1+ 1000000*(level+1));
                beenThere[currX][currY - 1] = true;
            }


            stepsSoFar= level;
            //if (stepsSoFar %1000000 == 0) System.out.println (queue.size());

        }

        return stepsSoFar;

    }

}

