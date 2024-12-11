import java.io.File;
        import java.io.FileNotFoundException;
        import java.util.Scanner;

public class AoC2022_2Dec {
    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");
        int score = 0;

        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_2Dec.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println("");
                char [] gameMoves = data.toCharArray();
                //System.out.println(gameMoves.length);
                /*
                //Solution for part 1
                if (gameMoves[2] == 'X'){
                    score = score + 1;
                    if (gameMoves[0] == 'A') score = score + 3;
                    if (gameMoves[0] == 'B') score = score + 0;
                    if (gameMoves[0] == 'C') score = score + 6;
                    continue;
                }
                if (gameMoves[2] == 'Y'){
                    score = score + 2;
                    if (gameMoves[0] == 'A') score = score + 6;
                    if (gameMoves[0] == 'B') score = score + 3;
                    if (gameMoves[0] == 'C') score = score + 0;
                    continue;
                }
                if (gameMoves[2] == 'Z'){
                    score = score + 3;
                    if (gameMoves[0] == 'A') score = score + 0;
                    if (gameMoves[0] == 'B') score = score + 6;
                    if (gameMoves[0] == 'C') score = score + 3;
                    continue;
                }
                */

                if (gameMoves[2] == 'X'){
                    score = score + 0;
                    if (gameMoves[0] == 'A') score = score + 3;
                    if (gameMoves[0] == 'B') score = score + 1;
                    if (gameMoves[0] == 'C') score = score + 2;
                    continue;
                }
                if (gameMoves[2] == 'Y'){
                    score = score + 3;
                    if (gameMoves[0] == 'A') score = score + 1;
                    if (gameMoves[0] == 'B') score = score + 2;
                    if (gameMoves[0] == 'C') score = score + 3;
                    continue;
                }
                if (gameMoves[2] == 'Z'){
                    score = score + 6;
                    if (gameMoves[0] == 'A') score = score + 2;
                    if (gameMoves[0] == 'B') score = score + 3;
                    if (gameMoves[0] == 'C') score = score + 1;
                    continue;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println (score);

    }
}

