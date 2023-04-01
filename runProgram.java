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
        BackendInterface backend = new Backend();
        Scanner input = new Scanner(System.in);
        MovieSearchAppInterface movieSearchApp = new MovieSearchApp(input, backend);
        movieSearchApp.runCommandLoop();
    }
}
