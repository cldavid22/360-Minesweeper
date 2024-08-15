/*
 * TCSS 360 Summer 2023
 * Group Assignment 1
 */
import java.io.PrintWriter;
import java.util.Scanner;
/**
 *
 * Creates the logic of a minesweeper game and makes a field according to the input file.
 *
 * @author Christian David
 * @version 30 June 2023
 */
public class MineSweeper {

    /**
     * Creates a main method to call the run() method to start the program execution.
     * @param theArgs command line arguments.
     */
    public static void main(String[] theArgs) {
        run();
    }

    /**
     * Runs the minesweeper game logic.
     * Reads the input file through console
     * and generates the field with hints with outputs the results.
     */
    static void run() {
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        int fieldCount = 0;

        while (true) {
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            if (rows == 0 && columns == 0) {
                break;
            }
            fieldCount++;
            char[][] field = new char[rows][columns];

            for (int i = 0; i < rows; i++) {
                String row = scanner.next();
                field[i] = row.toCharArray();
            }
            writer.println("Field #" + fieldCount + ":");
            char[][] result = findHints(field);
            generateField(result, writer);
            writer.println();
        }
        scanner.close();
        writer.close();
    }

    /**
     * Generates the hints for each cell in the field.
     * The hints represent the number of adjacent cells that contain mines.
     *
     * @param theField the original field with mines represented by '*' and empty cells represented by '.'
     * @return the field with hints represented by numbers indicating the adjacent mines count
     */
     static char[][] findHints(char[][] theField) {
        int rows = theField.length;
        int columns = theField[0].length;
        char[][] hints = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (theField[i][j] == '*') {
                    hints[i][j] = '*';
                } else {
                    int count = 0;

                    for (int a = -1; a <= 1; a++) {
                        for (int b = -1; b <= 1; b++) {
                            int newRow = i + a;
                            int newColumn = j + b;

                            if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && theField[newRow][newColumn] == '*') {
                                count++;
                            }
                        }
                    }
                    hints[i][j] = (char) (count + '0');
                }
            }
        }
        return hints;
    }

    /**
     * Outputs the generated field with hints to the specified PrintWriter.
     *
     * @param theField the field with hints
     * @param theWriter the PrintWriter used for output
     */
    private static void generateField(char[][] theField, PrintWriter theWriter) {
        for (char[] row : theField) {
            theWriter.println(row);
        }
    }
}