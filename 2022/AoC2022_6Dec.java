import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC2022_6Dec {
    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");
        int count = 0;

        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_6Dec.txt");
            Scanner myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }

            char[] str = data.toCharArray();
            int n = str.length;
            int l = 0;

            boolean[] flag = new boolean[256];

            for (int r = 0; r < n; r++) {
                char rc = str[r];

                while (flag[rc]) {
                    char lc = str[l++];
                    flag[lc] = false;
                }

                flag[rc] = true;

                if (r-l+1 == 14) {
                    count = r+1;
                    break;
                }

            }


            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println (count);
        //System.out.println (max + secondMax + thirdMax);
    }
}

