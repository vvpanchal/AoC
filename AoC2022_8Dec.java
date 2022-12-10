import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC2022_8Dec {

    public static void main(String[] args) {
        //String newline = System. getProperty("line.separator");

        int count = 0;
        int rows = 0;
        int columns = 0;

        //part 2 variable
        int scenicScore = 1;

        //get number of rows and columns. one pass of the input file
        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_8Dec.txt");
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
        int [] [] treeHeights = new int [rows][columns];

        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_8Dec.txt");
            Scanner myReader = new Scanner(myObj);
            int r = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                for (int i=0; i < data.length(); i++) {
                    treeHeights[r][i] = (Integer.parseInt(String.valueOf(data.charAt(i))));
                }
                r++;

            }
            myReader.close();
            count = goFigure (treeHeights);
            scenicScore = goFigurePart2IcantBeBotheredToBeEfficient (treeHeights);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println (count) ;
        System.out.println (scenicScore) ;



    }

    private static int goFigurePart2(int[][] treeHeights) {
        int ss = 1;
        int [] [] scenicScoreLeft = new int [treeHeights.length][treeHeights[0].length];
        int [] [] scenicScoreRight = new int [treeHeights.length][treeHeights[0].length];
        int [] [] scenicScoreTop = new int [treeHeights.length][treeHeights[0].length];
        int [] [] scenicScoreDown = new int [treeHeights.length][treeHeights[0].length];

        for (int i =0; i < treeHeights.length; i++){
            PriorityQueue<int []> lookingLeft = new PriorityQueue<int[]>( 100, new java.util.Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[1] - b[1];
                }
            });
            for(int j = 1; j < treeHeights[i].length; j++){
                if (lookingLeft.isEmpty()) {
                    scenicScoreLeft[i][j] = 1;
                    lookingLeft.offer(new int[]{1, treeHeights[i][j]});
                    continue;
                }
                int index = 0;
                //int value = 0;

                while ( !lookingLeft.isEmpty() &&  lookingLeft.peek()[1] < treeHeights[i][j]) {
                    index = lookingLeft.peek()[0];
                    //value = lookingLeft.peek()[1];
                    lookingLeft.poll();
                }

                if (!lookingLeft.isEmpty() && lookingLeft.peek()[1] == treeHeights[i][j]){
                    index = lookingLeft.peek()[0];
                    //value = lookingLeft.peek()[1];
                    lookingLeft.poll();
                }
                scenicScoreLeft[i][j] = j - index;
                lookingLeft.offer(new int[]{1, treeHeights[i][j]});

            }
        }
        for (int i =1; i < treeHeights.length-1; i++){

            PriorityQueue<int []> lookingRight = new PriorityQueue<int[]>( 100, new java.util.Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[1] - b[1];
                }
            });

            for(int j =  treeHeights[i].length-2; j >=1 ; j--){
                if (lookingRight.isEmpty()) {
                    scenicScoreRight[i][j] = 1;
                    lookingRight.offer(new int[]{1, treeHeights[i][j]});
                    continue;
                }
                int index = 0;
                //int value = 0;

                while ( !lookingRight.isEmpty() &&  lookingRight.peek()[1] < treeHeights[i][j]) {
                    index = lookingRight.peek()[0];
                    //value = lookingRight.peek()[1];
                    lookingRight.poll();
                }

                if (!lookingRight.isEmpty() && lookingRight.peek()[1] == treeHeights[i][j]){
                    index = lookingRight.peek()[0];
                    //value = lookingRight.peek()[1];
                    lookingRight.poll();
                }
                scenicScoreRight[i][j] = j - index;
                lookingRight.offer(new int[]{1, treeHeights[i][j]});

            }
        }

        for (int i = 1; i < treeHeights.length-1;i++) {
            PriorityQueue<int []> lookingUp = new PriorityQueue<int[]>( 100, new java.util.Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[1] - b[1];
                }
            });
            for (int j =1; j < treeHeights[i].length-1; j++){


                if (lookingUp.isEmpty()) {
                    scenicScoreTop[j][i] = 1;
                    lookingUp.offer(new int[]{1, treeHeights[j][i]});
                    continue;
                }
                int index = 0;
                //int value = 0;

                while ( !lookingUp.isEmpty() &&  lookingUp.peek()[1] < treeHeights[j][i]) {
                    index = lookingUp.peek()[0];
                    //value = lookingUp.peek()[1];
                    lookingUp.poll();
                }

                if (!lookingUp.isEmpty() && lookingUp.peek()[1] == treeHeights[j][i]){
                    index = lookingUp.peek()[0];
                    //value = lookingUp.peek()[1];
                    lookingUp.poll();
                }
                scenicScoreTop[j][i] = i - index;
                lookingUp.offer(new int[]{1, treeHeights[j][i]});
            }
        }
        for (int i = 1; i < treeHeights.length-1; i++) {

            PriorityQueue<int []> lookingDown = new PriorityQueue<int[]>( 100, new java.util.Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[1] - b[1];
                }
            });
            for (int j = treeHeights[0].length - 2; j>=1; j--){
                if (lookingDown.isEmpty()) {
                    scenicScoreDown[j][i] = 1;
                    lookingDown.offer(new int[]{1, treeHeights[j][i]});
                    continue;
                }
                int index = 0;
                //int value = 0;

                while ( !lookingDown.isEmpty() &&  lookingDown.peek()[1] < treeHeights[j][i]) {
                    index = lookingDown.peek()[0];
                    //value = lookingDown.peek()[1];
                    lookingDown.poll();
                }

                if (!lookingDown.isEmpty() && lookingDown.peek()[1] == treeHeights[j][i]){
                    index = lookingDown.peek()[0];
                    //value = lookingDown.peek()[1];
                    lookingDown.poll();
                }
                scenicScoreDown[j][i] = i - index;
                lookingDown.offer(new int[]{1, treeHeights[j][i]});
            }
        }


        //print2DArray(scenicScoreLeft);
        //System.out.println("");

        for (int i = 0; i < treeHeights.length; i++){
            for(int j = 0; j < treeHeights[0].length; j++){
                ss = Math.max(ss, scenicScoreLeft[i][j] * scenicScoreRight[i][j] * scenicScoreTop[i][j] * scenicScoreDown[i][j]);
            }
        }

        return ss;
    }
    private static int goFigurePart2IcantBeBotheredToBeEfficient(int[][] treeHeights) {
        int ss = 1;

        for (int i=1; i < treeHeights.length-1; i++){
            for (int j = 1; j < treeHeights.length-1; j++){
                int left = 0;
                int l = j-1;
                while (l>=1 && treeHeights[i][j] > treeHeights[i][l--]) left++;
                // left++;
                left++;

                int right = 0;
                int r = j+1;
                while (r< treeHeights.length-1 && treeHeights[i][j] > treeHeights[i][r++]) right++;
                //if (treeHeights[i][j] == treeHeights[i][r]) right++;
                right++;

                int up = 0;
                int u = i-1;
                while (u>=1 && treeHeights[i][j] > treeHeights[u--][j]) up++;
                //if (treeHeights[i][j] == treeHeights[u][j]) up++;
                up++;

                int down = 0;
                int d = i+1;
                while (d< treeHeights.length-1 && treeHeights[i][j] > treeHeights[d++][j]) down++;
                //if (treeHeights[i][j] == treeHeights[d][j]) down++;
                down++;

                ss = Math.max (ss, left * right * up * down );

            }
        }

        return ss;
    }
    private static void print2DArray (int [] [] arrToBePrinted) {
        for(int i = 0; i < arrToBePrinted.length; i++){
            System.out.println("");
            for (int j = 0; j < arrToBePrinted[0].length; j++) System.out.print(arrToBePrinted[i][j] + " ");
        }
    }

    private static int goFigure(int [] [] treeHeights) {
        if (treeHeights == null) return 0; //just in case I return to form and *&^% up!
        int rows = treeHeights.length;
        int columns = treeHeights[0].length;
        int [] [] rowLevelHeights = new int [rows] [columns];
        int [] [] colLevelHeights = new int [rows] [columns];

        //update highest value from the left at every index
        for (int i = 0; i < treeHeights.length;i++) {
            int runningVar = 0;
            for (int j =0; j < treeHeights[i].length; j++){
                if (treeHeights[i][j]>= runningVar) runningVar = treeHeights[i][j];
                rowLevelHeights[i][j] = runningVar*10;
            }
        }
        //print2DArray (rowLevelHeights);
        for (int i = 0; i < treeHeights.length; i++) {
            int runningVar = 0;
            for (int j = treeHeights[0].length - 1; j>=0; j--){
                if (treeHeights[i][j]>= runningVar) runningVar = treeHeights[i][j];
                //System.out.println (rowLevelHeights[i][j] + " " + runningVar);
                rowLevelHeights[i][j] += runningVar;
            }
        }
        //print2DArray (rowLevelHeights);

        //update highest value from the left at every index in 0th index of array and from right in the 1st index
        for (int i = 0; i < treeHeights.length;i++) {
            int runningVar = 0;
            for (int j =0; j < treeHeights[i].length; j++){
                if (treeHeights[j][i]>= runningVar) runningVar = treeHeights[j][i];
                colLevelHeights[j][i] = runningVar*10;
            }
        }
        for (int i = 0; i < treeHeights.length; i++) {
            int runningVar = 0;
            for (int j = treeHeights[0].length - 1; j>=0; j--){
                if (treeHeights[j][i]>= runningVar) runningVar = treeHeights[j][i];
                colLevelHeights[j][i] += runningVar;
            }
        }
        //print2DArray(treeHeights);
        //System.out.println("");
        //print2DArray (rowLevelHeights);
        //System.out.println("");
        //print2DArray (colLevelHeights);
        //print2DArray
        int ans = 0;
        for (int i = 1; i < treeHeights.length-1;i++) {

            for (int j =1; j < treeHeights[0].length-1; j++){
                if (treeHeights[i][j] > rowLevelHeights [i][j-1]/10 ||  treeHeights[i][j] > rowLevelHeights [i][j+1]%10 || treeHeights[i][j] > colLevelHeights [i-1][j]/10 ||  treeHeights[i][j] > colLevelHeights [i+1][j]%10){
                    ans++;
                }
            }
        }
        ans = ans + rows*2 + columns*2 - 4;
        return ans;

    }
}

