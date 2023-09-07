


import java.io.*;

public class day2 {

    static String bathroomCodePart1 = "";
    static String[][] keypadPart1 = {
            {"1","2","3"},
            {"4","5","6"},
            {"7","8","9"}
    };
    static int[] previousButtonPart1 = { 1,1 };  // indexes for the previous button, starts on 5

    static String bathroomCodePart2 = "";
    static String[][] keypadPart2 = {
            {"-","-","1","-","-"},
            {"-","2","3","4","-"},
            {"5","6","7","8","9"},
            {"-","A","B","C","-"},
            {"-","-","D","-","-"}
    };
    static int[] previousButtonPart2 = { 2,0 };

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                findNextButton(line);
            }
            fr.close();
            System.out.println("Bathroom Code Part 1: " + bathroomCodePart1);
            System.out.println("Bathroom Code Part 2: " + bathroomCodePart2);
        } catch(IOException e) {
            System.err.println(e);
        }
    }

    public static void findNextButton(String instructions) {
        for(int i = 0; i < instructions.length(); i++) {
            switch(instructions.charAt(i)) {
                case 'U':
                    if(previousButtonPart1[0] > 0) {
                        previousButtonPart1[0]--;
                    }
                    if(previousButtonPart2[0] > 0 &&
                            keypadPart2[previousButtonPart2[0]-1][previousButtonPart2[1]] != "-") {
                        previousButtonPart2[0]--;
                    }
                    break;
                case 'D':
                    if(previousButtonPart1[0] < 2) {
                        previousButtonPart1[0]++;
                    }
                    if(previousButtonPart2[0] < 4 &&
                            keypadPart2[previousButtonPart2[0]+1][previousButtonPart2[1]] != "-") {
                        previousButtonPart2[0]++;
                    }
                    break;
                case 'L':
                    if(previousButtonPart1[1] > 0) {
                        previousButtonPart1[1]--;
                    }
                    if(previousButtonPart2[1] > 0 &&
                            keypadPart2[previousButtonPart2[0]][previousButtonPart2[1]-1] != "-") {
                        previousButtonPart2[1]--;
                    }
                    break;
                case 'R':
                    if(previousButtonPart1[1] < 2) {
                        previousButtonPart1[1]++;
                    }
                    if(previousButtonPart2[1] < 4 &&
                            keypadPart2[previousButtonPart2[0]][previousButtonPart2[1]+1] != "-") {
                        previousButtonPart2[1]++;
                    }
                    break;
            }
        }
        bathroomCodePart1 +=
                keypadPart1[previousButtonPart1[0]][previousButtonPart1[1]];
        bathroomCodePart2 +=
                keypadPart2[previousButtonPart2[0]][previousButtonPart2[1]];
    }
}