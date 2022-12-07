import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC2022_7Dec {

    static long sizeOfLessThan100KDirs = 0;
    static PriorityQueue<Long> minHeap = new PriorityQueue<Long>();
    public static void calcMinDirToInstalUpdate (MyDirectory workingDir, PriorityQueue<Long> mnHp){
        long rootSize = workingDir.size;
        long totalSize = 70000000l;
        long neededSize = 30000000l;

        while(!minHeap.isEmpty()) {

            if(totalSize-rootSize+ minHeap.peek() >= neededSize) System.out.println(minHeap.poll() + " yeah!");
            else minHeap.poll();

        }

    }
    public static void printDirStructure (MyDirectory workingDir) {
        printDirStructure (workingDir, 0);
    }
    public static void printDirStructure (MyDirectory workingDir, int level){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) sb.append(".");
        System.out.println (sb.toString() + workingDir.dirName + " Size: " + workingDir.size);
        for (int i=0; i < workingDir.subDirectories.size();i++) {
            System.out.println (sb.toString()+"." + workingDir.subDirectories.get(i).dirName + " Size: " + workingDir.subDirectories.get(i).size);
        }
        for (int i=0; i < workingDir.subDirectories.size();i++) if (!workingDir.subDirectories.get(i).isFile) printDirStructure(workingDir.subDirectories.get(i), level+1);
    }
    /*
    public static void updateDirs (MyDirectory workingDir) {
        //System.out.println (workingDir.subDirectories.size());
        if (workingDir.subDirectories == null || workingDir.subDirectories.isEmpty()) {
            //System.out.println(workingDir.size + " " + workingDir.dirName);
            workingDir.parentDir.size += workingDir.size;
            if (workingDir.size < 100000 && workingDir.isFile == false) {
                //System.out.println("some dir with less than 100K " + workingDir.dirName + " " + workingDir.size);
                sizeOfLessThan100KDirs += workingDir.size;
            }
            return;
        }
        for (int i=0; i < workingDir.subDirectories.size(); i++){
            updateDirs(workingDir.subDirectories.get(i));
        }
        if (workingDir.size < 100000 && workingDir.isFile == false) {
            //System.out.println("some dir with less than 100K " + workingDir.dirName + " " + workingDir.size);

            sizeOfLessThan100KDirs += workingDir.size;
        }
        workingDir.parentDir.size += workingDir.size;
    }
    */

    public static void updateDirs (MyDirectory workingDir) {
        //System.out.println (workingDir.subDirectories.size());
        if (workingDir.isFile) {
            workingDir.parentDir.size += workingDir.size;
            return;
        }

        for (int i=0; i < workingDir.subDirectories.size(); i++){
            updateDirs(workingDir.subDirectories.get(i));
        }
        if (workingDir.size < 100000 && workingDir.isFile == false) {
            //System.out.println("some dir with less than 100K " + workingDir.dirName + " " + workingDir.size);

            sizeOfLessThan100KDirs += workingDir.size;
        }
        if (!workingDir.dirName.equals("/")) workingDir.parentDir.size += workingDir.size;
        minHeap.offer(workingDir.size);
    }


    public static void main(String[] args) {
        String newline = System. getProperty("line.separator");

        MyDirectory root = new MyDirectory();
        root.dirName = "/";
        root.isFile = false;
        root.size = 0;
        root.parentDir = null;
        root.subDirectories = new ArrayList<MyDirectory>();

        try {
            File myObj = new File("/Users/vpanchal/IdeaProjects/AoC2022/src/AoC2022_7Dec.txt");
            Scanner myReader = new Scanner(myObj);
            MyDirectory currDir = root;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.charAt(0) == '$') {
                    //command prompt
                    String [] cmds = data.split(" ");
                    if (cmds[1].equals("cd")){
                        //change directory to cmds[2]
                        //assumes sub-directories of this directory have already been set and we do not expect file not found scenarios :P

                        if (cmds[2].equals("/")) {
                            //System.out.println(data);
                            //System.out.println("Changed current directory from " + currDir.dirName +" to " + root.dirName);

                            currDir = root;
                        } else if (cmds[2].equals("..")) {
                            //System.out.println(data);
                            //System.out.println("Changed current directory from " + currDir.dirName +" to " + currDir.parentDir.dirName);

                            currDir = currDir.parentDir;
                        } else {

                            // when changing in to a named sub directory
                            for (MyDirectory runningDir : currDir.subDirectories) {
                                if (runningDir.dirName.equals(cmds[2]) && runningDir.isFile==false) {
                                    //System.out.println(data);
                                    //System.out.println("Changed current directory from " + currDir.dirName +" to " + runningDir.dirName);
                                    currDir = runningDir;

                                    break;
                                }
                            }
                        }

                    } else if (cmds[1].equals("ls")) {
                        //do nothing, at least in part 1
                    }

                } else {
                    //create directory structure, calculate file sizes
                    String [] fileOrDirDetails = data.split(" ");
                    boolean dirExists = false;
                    if (fileOrDirDetails[0].equals("dir")){
                        //add sub-directory to currDir

                        for (MyDirectory runningDir : currDir.subDirectories) {
                            if (runningDir.dirName.equals(fileOrDirDetails[1]) && runningDir.isFile==false) {
                                dirExists = true;
                                break;
                            }
                        }
                        if (!dirExists) {
                            MyDirectory newDir = new MyDirectory();
                            newDir.isFile = false;
                            newDir.dirName = fileOrDirDetails[1];
                            newDir.parentDir = currDir;
                            newDir.size = 0;
                            newDir.subDirectories = new ArrayList<MyDirectory>();
                            currDir.subDirectories.add(newDir);

                        } else {
                            //do nothing if directory already exists
                            System.out.println("Very surprising if this gets printed");
                        }

                    } else {
                        //add file  to sub-directory
                        MyDirectory newDir = new MyDirectory();
                        newDir.isFile = true;
                        newDir.dirName = fileOrDirDetails[1];
                        newDir.parentDir = currDir;
                        newDir.size = Integer.parseInt(fileOrDirDetails[0]);
                        newDir.subDirectories = null;
                        currDir.subDirectories.add(newDir);
                        //currDir.size = currDir.size + Integer.parseInt(fileOrDirDetails[0]);
                        //System.out.println("Filesize: " + Integer.parseInt(fileOrDirDetails[0]) + " added to dir: "+currDir.dirName + " with resultant dir size: " + currDir.size);
                    }
                }

            }

            //walk through the directory structure to identify all directories with size less than 100K
            //sizeOfLessThan100KDirs
            updateDirs (root);
            //printDirStructure (root);
            calcMinDirToInstalUpdate (root, minHeap);

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println (root.size + " " + sizeOfLessThan100KDirs) ;
        //System.out.println (max + secondMax + thirdMax);
    }
}

class MyDirectory {
    String dirName;
    long size;
    MyDirectory parentDir;
    List <MyDirectory> subDirectories;
    boolean isFile;

    void updateParentDirSize (MyDirectory presentDir) {
        if (presentDir.dirName.equals("/")) return;
        presentDir.parentDir.size += presentDir.size;
        updateParentDirSize (presentDir.parentDir);
    }
}

