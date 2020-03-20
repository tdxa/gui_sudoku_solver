package gui;
public class GameBoard {

    private int[][] solution;
    private int[][] starting;

    public int[][] getSolution() {
        return solution;
    }

    public int[][] getStarting() {
        return starting;
    }


    public GameBoard(){
        solution = new int[][]{
                {5,3,8,4,6,1,7,9,2},
                {6,9,7,3,2,5,8,1,4},
                {2,1,4,7,8,9,5,6,3},
                {9,4,1,2,7,8,6,3,5},
                {7,6,2,1,5,3,9,4,8},
                {8,5,3,9,4,6,1,2,7},
                {3,8,9,5,1,2,4,7,6},
                {4,2,6,8,9,7,3,5,1},
                {1,7,5,6,3,4,2,8,9}
        };

        // '0' for empty space

        starting = new int[][]{
                {0,0,0,4,0,0,0,9,0},
                {6,0,7,0,0,0,8,0,4},
                {0,1,0,7,0,9,0,0,3},
                {9,0,1,0,7,0,0,3,0},
                {0,0,2,0,0,0,9,0,0},
                {0,5,0,0,4,0,1,0,7},
                {3,0,0,5,0,2,0,7,0},
                {4,0,6,0,0,0,3,0,1},
                {0,7,0,0,0,4,0,0,0}
        };
    }







}
