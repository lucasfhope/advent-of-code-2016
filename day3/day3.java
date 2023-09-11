import java.io.*;

public class day3 {

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            int numberOfPossibleTrianglesHorizontal = 0;
            int numberOfPossibleTrianglesVerticle = 0;
            String[] sideLengthsVerticleLeft = new String[4];
            String[] sideLengthsVerticleMiddle = new String[4];
            String[] sideLengthsVerticleRight = new String[4];
            String line; int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                String[] sideLengthsHorizontal = line.split("\\s+");                        // split for blank space leaves an empty string at index 0
                sideLengthsVerticleLeft[lineNumber%3+1] = sideLengthsHorizontal[1];         // this is accounted for in each array
                sideLengthsVerticleMiddle[lineNumber%3+1] = sideLengthsHorizontal[2];       // as well as the function isPossibleTriangle()
                sideLengthsVerticleRight[lineNumber%3+1] = sideLengthsHorizontal[3];
                lineNumber++;
                if(lineNumber % 3 == 0) {
                    if(isPossibleTriangle(sideLengthsVerticleLeft)) numberOfPossibleTrianglesVerticle++;
                    if(isPossibleTriangle(sideLengthsVerticleMiddle)) numberOfPossibleTrianglesVerticle++;
                    if(isPossibleTriangle(sideLengthsVerticleRight)) numberOfPossibleTrianglesVerticle++;
                }
                if(isPossibleTriangle(sideLengthsHorizontal)) numberOfPossibleTrianglesHorizontal++;
            }
            fr.close();
            System.out.println("Part 1: " + numberOfPossibleTrianglesHorizontal + " triangles");
            System.out.println("Part 2: " + numberOfPossibleTrianglesVerticle + " triangles");
        } catch(IOException e) {
            System.err.println(e);
        }
    }

    public static boolean isPossibleTriangle(String[] sideLengths) {
        if(Integer.parseInt(sideLengths[1])+Integer.parseInt(sideLengths[2]) > Integer.parseInt(sideLengths[3])
            && Integer.parseInt(sideLengths[3])+Integer.parseInt(sideLengths[1]) > Integer.parseInt(sideLengths[2])
                && Integer.parseInt(sideLengths[2])+Integer.parseInt(sideLengths[3]) > Integer.parseInt(sideLengths[1]))
            return true;
        return false;
    }
}
