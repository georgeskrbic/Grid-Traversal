import java.io.*;

public class fileParser {

    File inFile = null;
    int gridSize;
    int lineCount;
    Node[][] grid;
    Node initial;
    Node goal;

    public fileParser(String[] input) {
        if (0 < input.length) {
            inFile = new File(input[0]);
        }
        else {
            System.exit(1);
        }
    }

    public void parse() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(inFile));
        String line;
        gridSize = Integer.parseInt(br.readLine());
        grid = new Node[gridSize][gridSize];

        /*Create integer matrix for the given input. Nodes are given integer values corresponding to types*/
        while ((line = br.readLine()) != null) {
            for(int i = 0; i < line.length(); i++) {
                if(line.charAt(i) == '.') {
                    grid[lineCount][i] = new Node(lineCount, i, 0);//open
                }
                else if(line.charAt(i) == '+') {
                    grid[lineCount][i] = new Node(lineCount, i, 3);//wall
                }
                else if(line.charAt(i) == 'i') {
                    Node temp = new Node(lineCount, i, 1);//initial
                    grid[lineCount][i] = temp;
                    initial = temp;
                }
                else if(line.charAt(i) == 'g') {
                    Node temp = new Node(lineCount, i, 2);//goal
                    grid[lineCount][i] = temp;
                    goal = temp;
                }
            }
            lineCount++;
        }
        br.close();

        for(int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                buildNeighbors(grid[i][j], i, j);
            }
        }
    }

    /*For each node that is not a wall represented as a 3, the corresponding up, down, left, and right neighbors will be
    added to a list*/
    public void buildNeighbors(Node n, int row, int col) {
        if(n.getType() != 3) {
            if(row == 0) {//Check for edge cases where neighbor amount will vary
                if(col == 0) {
                    n.addNeighbor(grid[row + 1][col]);
                    n.addNeighbor(grid[row][col + 1]);
                }
                else if(col == gridSize - 1){
                    n.addNeighbor(grid[row + 1][col]);
                    n.addNeighbor(grid[row][col - 1]);
                }
                else{
                    n.addNeighbor(grid[row + 1][col]);
                    n.addNeighbor(grid[row][col + 1]);
                    n.addNeighbor(grid[row][col - 1]);
                }
            }
            else if(row == gridSize - 1) {
                if(col == gridSize - 1){
                    n.addNeighbor(grid[row - 1][col]);
                    n.addNeighbor(grid[row][col - 1]);
                }
                else if(col == 0){
                    n.addNeighbor(grid[row - 1][col]);
                    n.addNeighbor(grid[row][col + 1]);
                }
                else{
                    n.addNeighbor(grid[row - 1][col]);
                    n.addNeighbor(grid[row][col - 1]);
                    n.addNeighbor(grid[row][col + 1]);
                }
            }
            else if(col == 0) {
                n.addNeighbor(grid[row + 1][col]);
                n.addNeighbor(grid[row - 1][col]);
                n.addNeighbor(grid[row][col + 1]);
            }
            else if(col == gridSize - 1) {
                n.addNeighbor(grid[row + 1][col]);
                n.addNeighbor(grid[row - 1][col]);
                n.addNeighbor(grid[row][col - 1]);
            }
            else{
                n.addNeighbor(grid[row + 1][col]);
                n.addNeighbor(grid[row - 1][col]);
                n.addNeighbor(grid[row][col - 1]);
                n.addNeighbor(grid[row][col + 1]);
            }
        }
    }

    public Node getInitial() { return initial; }

    public Node getGoal(){ return goal; }

    public Node[][] getGrid(){ return grid; }
}
