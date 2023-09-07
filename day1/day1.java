import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class day1 {

    public static void main(String args[]) {
        Path filename = Path.of("input.txt");
        try {
            String instructions = Files.readString(filename);
            String[] instructionsSplit = instructions.split(", ");
            findDistanceFromHQ(instructionsSplit);
        } catch(IOException e) {
            System.err.println(e);
        }
    }

    public static void findDistanceFromHQ(String[] instructions) {
        int distanceNS = 0, distanceEW = 0;
        Direction currentDirection = Direction.North;
        ArrayList<int[]> coordinatesVisited = new ArrayList<int[]>();
        boolean part2HQFound = false;
        for(String instruction : instructions) {
            Turn turn = instruction.charAt(0) == 'R' ? Turn.Right : Turn.Left;
            int distance = Integer.parseInt(instruction.substring(1));
            switch(currentDirection) {
                case North:
                    currentDirection = turn == Turn.Right ? Direction.East : Direction.West;
                    break;
                case South:
                    currentDirection = turn == Turn.Right ? Direction.West : Direction.East;
                    break;
                case East:
                    currentDirection = turn == Turn.Right ? Direction.South : Direction.North;
                    break;
                case West:
                    currentDirection = turn == Turn.Right ? Direction.North : Direction.South;
                    break;
            }
            while(distance > 0) {
                switch(currentDirection) {
                    case North:
                        distanceNS++;
                        break;
                    case South:
                        distanceNS--;
                        break;
                    case East:
                        distanceEW++;
                        break;
                    case West:
                        distanceEW--;
                        break;
                }
                distance--;
                if(!part2HQFound) {
                    int[] coordinates = {distanceEW, distanceNS};
                    if(coordinatesPreviouslyVisited(coordinates,coordinatesVisited)) {
                        System.out.println("HQ Distance Part 2: " + (Math.abs(distanceEW) + Math.abs(distanceNS)));
                        part2HQFound = true;
                    } else {
                        coordinatesVisited.add(coordinates);
                    }
                }
            }
        }
        System.out.println("HQ Distance Part 1: " + (Math.abs(distanceNS) + Math.abs(distanceEW)));
    }

    public static boolean coordinatesPreviouslyVisited(int[] coordinates, ArrayList<int[]> coordinatesVisited) {
        for(int[] visitedCoordinates : coordinatesVisited) {
            if (Arrays.equals(visitedCoordinates, coordinates)) {
                return true;
            }
        }
        return false;
    }

    enum Direction {
        North,
        South,
        East,
        West
    }

    enum Turn {
        Right,
        Left
    }
}
