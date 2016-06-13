import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        fileParser fp = new fileParser(args);

        try {
            fp.parse();
            System.out.println("The shortest path to the goal is: ");
            System.out.println("");
            Strategy strat = new Strategy(fp.getInitial(), fp.getGoal(), fp.getGrid());
            strat.search();
            strat.printGrid();
        }
        catch(IOException ex) {
            System.err.println(ex);
        }
    }
}
