import java.util.Scanner;

/**
 * This class runs the MovieSearchApp program.
 */
public class runProgram {

    /**
     * This method runs the MovieSearchApp program.
     * @param args if any
     */
    public static void main(String[] args) {
        BackendInterface backend5 = new Backend();
        Scanner input5 = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input5, backend5);
        movieSearchApp.runCommandLoop();
    }
}
