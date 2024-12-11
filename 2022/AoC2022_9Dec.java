import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC2022_9Dec {

    public static void main(String[] args) {
        //String newline = System. getProperty("line.separator");

        int hMax = 0;
        int hMin = 0;
        int vMax = 0;
        int vMin = 0;
        int h = 0;
        int v = 0;


        //get number of rows and columns. one pass of the input file
        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_9Dec.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] inputDataLine = data.split(" ");
                if (inputDataLine[0].equals("R")) {
                    h += Integer.parseInt(inputDataLine[1]);
                    hMax = Math.max (hMax,h);
                }
                if (inputDataLine[0].equals("L")) {
                    h -= Integer.parseInt(inputDataLine[1]);
                    hMin = Math.min (hMin,h);
                }
                if (inputDataLine[0].equals("U")) {
                    v += Integer.parseInt(inputDataLine[1]);
                    vMax = Math.max (vMax,v);
                }
                if (inputDataLine[0].equals("D")) {
                    v -= Integer.parseInt(inputDataLine[1]);
                    vMin = Math.min (vMin,v);
                }


            }

            System.out.println ("hMax: " + hMax + " hMin: " + hMin + " vMax: " + vMax + " vMin: "+ vMin);

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



        //initialize 2D array and take input
        boolean [] [] visited = new boolean [(hMax - hMin)*2][(vMax-vMin)*2];
        boolean [] [] visitedByKnotTen = new boolean [(hMax - hMin)*2][(vMax-vMin)*2];
        int startH = 0 - hMin + 1;
        int startV = 0 - vMin + 1;
        visited [startH][startV] = true;

        int [] xPosOfKnots = {startH, startH, startH, startH, startH, startH, startH, startH, startH, startH};
        int [] yPosOfKnots = {startV, startV, startV, startV, startV, startV, startV, startV, startV, startV};

        visitedByKnotTen[xPosOfKnots[9]][yPosOfKnots[9]] = true;

        int headX = startH;
        int tailX = startH;
        int headY = startV;
        int tailY = startV;

        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_9Dec.txt");
            Scanner myReader = new Scanner(myObj);
            //int r = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] inputDataLine = data.split(" ");
                //System.out.println("read new line of Data:" + data);

                for (int i= 0; i < Integer.parseInt(inputDataLine[1]);i++){
                    if (inputDataLine[0].equals("R")){
                        //System.out.println("in R");
                        headX = headX + 1;
                        int [] tailPos = moveTailIfNeeded(headX,headY,tailX,tailY);
                        tailX += tailPos[0];
                        tailY += tailPos[1];

                        //part 2
                        xPosOfKnots[0] = headX;
                        updatePosOfAllSubsequentKnots (xPosOfKnots, yPosOfKnots);

                    }
                    if (inputDataLine[0].equals("L")){
                        headX = headX - 1;
                        int [] tailPos = moveTailIfNeeded(headX,headY,tailX,tailY);
                        tailX += tailPos[0];
                        tailY += tailPos[1];

                        xPosOfKnots[0] = headX;
                        updatePosOfAllSubsequentKnots (xPosOfKnots, yPosOfKnots);

                    }
                    if (inputDataLine[0].equals("U")){
                        headY = headY + 1;
                        int [] tailPos = moveTailIfNeeded(headX,headY,tailX,tailY);
                        tailX += tailPos[0];
                        tailY += tailPos[1];

                        yPosOfKnots[0] = headY;
                        updatePosOfAllSubsequentKnots (xPosOfKnots, yPosOfKnots);
                    }
                    if (inputDataLine[0].equals("D")){
                        headY = headY - 1;
                        int [] tailPos = moveTailIfNeeded(headX,headY,tailX,tailY);
                        tailX += tailPos[0];
                        tailY += tailPos[1];

                        yPosOfKnots[0] = headY;
                        updatePosOfAllSubsequentKnots (xPosOfKnots, yPosOfKnots);
                    }
                    //headVisited[headX][headY] = true;
                    visited[tailX][tailY] = true;
                    visitedByKnotTen[xPosOfKnots[9]][yPosOfKnots[9]] = true;
                }

            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int res = 0;
        int resKnotTen = 0;
        //int headRes = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++){
                if(visited[i][j]) res++;
                if (visitedByKnotTen[i][j]) resKnotTen++;
                //if (headVisited[i][j]) headRes++;
            }
            //System.out.println("");
        }
        System.out.println(res + " " + resKnotTen);

    }

    private static void updatePosOfAllSubsequentKnots(int[] xPosOfKnots, int[] yPosOfKnots) {
        for (int i = 1; i < xPosOfKnots.length;i++){
            int [] updatedXY = moveKnotIfNeeded(xPosOfKnots[i-1], yPosOfKnots[i-1], xPosOfKnots[i], yPosOfKnots[i]);
            xPosOfKnots[i] += updatedXY[0];
            yPosOfKnots[i] += updatedXY[1];
        }
    }

    private static int[] moveKnotIfNeeded(int headX, int headY, int tailX, int tailY) {
        return moveTailIfNeeded( headX,  headY,  tailX,  tailY);
    }

    private static int[] moveTailIfNeeded(int headX, int headY, int tailX, int tailY) {
        // if both head and tail are in the same poistion
        if (headX == tailX && headY == tailY) return new int[] {0,0};

        //if both head and tail are on the same horizontal line
        if (headY == tailY) {
            if (Math.abs(headX-tailX) == 1) return new int[] {0,0}; // only 1 unit distance, no need to move
            if (headX > tailX)  return new int[] {1,0}; // head to the right, move tail to right
            if (headX < tailX)  return new int[] {-1,0}; // head to the left, move tail to left
        }

        //if both head and tail are on the same vertical line
        if (headX == tailX) {
            if (Math.abs(headY-tailY) == 1) return new int[] {0,0}; // only 1 unit distance, no need to move
            if (headY > tailY)  return new int[] {0,1}; // head to the up, move tail to up
            if (headY < tailY)  return new int[] {0,-1}; // head to the down, move tail to down
        }

        //if not nice things hit the fan!
        if (Math.abs(headY-tailY) == 1 && Math.abs(headX-tailX) == 1 ) return new int[] {0,0}; // only 1 unit distance diagonally, no need to move
        if (headX>tailX && headY>tailY) return new int[] {1,1};
        if (headX>tailX && headY<tailY) return new int[] {1,-1};
        if (headX<tailX && headY>tailY) return new int[] {-1,1};
        if (headX<tailX && headY<tailY) return new int[] {-1,-1};

        return new int [] {0,0};

    }

}

