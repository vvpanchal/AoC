import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AoC2022_3Dec {
    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");
        int sum = 0;
        int counter = 0;

        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_3Dec.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                int [] charMap = new int [256];
                int [] charMap2 = new int [256];
                int [] charMap3 = new int [256];
                for (int j = 0; j < 3; j++){
                    String data = myReader.nextLine();
                    for (int i = 0; i < data.length(); i++){
                        if (j==0) {
                            if (data.charAt(i) >= 97 && data.charAt(i) <= 122 || data.charAt(i) >= 65 && data.charAt(i) <= 90) charMap[data.charAt(i)]=1;
                        }
                        if (j==1){
                            if ((data.charAt(i) >= 97 && data.charAt(i) <= 122 || data.charAt(i) >= 65 && data.charAt(i) <= 90) && charMap[data.charAt(i)] > 0) charMap2[data.charAt(i)]=1;
                        }
                        if (j==2){
                            if ((data.charAt(i) >= 97 && data.charAt(i) <= 122 || data.charAt(i) >= 65 && data.charAt(i) <= 90) && charMap2[data.charAt(i)] > 0) {
                                charMap3[data.charAt(i)]=1;
                                if (data.charAt(i) > 96) {
                                    sum += data.charAt(i) - 96;
                                } else {
                                    sum += data.charAt(i) - 38;
                                }
                                break;
                            }
                        }
                    }
                }

                /*
                //part 1 solution
                String data = myReader.nextLine();
                int [] charMap = new int [256];
                for (int i = 0; i < data.length()/2; i++){
                    if (data.charAt(i) >= 97 && data.charAt(i) <= 122 || data.charAt(i) >= 65 && data.charAt(i) <= 90) charMap[data.charAt(i)]++;
                }
                for (int i = data.length()/2; i < data.length(); i++){
                    if (charMap[data.charAt(i)] > 0){
                        if (data.charAt(i) > 96) {
                            sum += data.charAt(i) - 96;
                        } else {
                            sum += data.charAt(i) - 38;
                        }
                        break;
                    }
                }
                */


            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println (sum);
        //System.out.println (max + secondMax + thirdMax);
    }
}

