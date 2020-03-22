package com.company.Controller;

import java.util.*;

public class SudokuBoardStateManager {
    public String generateState(int[][] sudokuBoard){
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard[i].length; j++) {
                key.append(sudokuBoard[i][j]);
            }
        }
        return key.toString();
    }

    public boolean isSolved(int[][] sudokuBoard){
        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard[i].length; j++) {
                if (sudokuBoard[i][j] == 0 || String.valueOf(sudokuBoard[i][j]).length() > 1)
                    return false;
            }
        }
        return true;
    }

    public boolean checkSolution(int[][] sudokuBoard){
        HashSet<String> seenValues = new HashSet<>();

        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard[i].length; j++) {
                int cellValue = sudokuBoard[i][j];

                if(cellValue != 0){
                    seenValues.add(cellValue +" found in row " + i);
                    seenValues.add(cellValue + " found in col " + j);
                    seenValues.add(cellValue + " found in block " + (i/3) + " - " + (j/3));
                }
            }
        }

        return seenValues.size() == 243;
    }

    public int[][] generateSudokuBoard(){
        int[][] sudokuBoard = new int[9][9];
        int x, y, counter = 0;

        List<Integer> seed = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9));
        Collections.shuffle(seed);

        Random rand = new Random();

        for(int i = 0; i < 9; i++){
            sudokuBoard[0][i] = seed.get(i);
        }

        for(int row = 1; row < 9; row++){
            for(int col = 0; col < 9; col++){
                if(row % 3 == 0){
                    sudokuBoard[row][col] = sudokuBoard[row-1][(col+1)%9];
                }
                else{
                    sudokuBoard[row][col] = sudokuBoard[row-1][(col+3)%9];
                }
            }
        }

        while(counter < 60){
            x = rand.nextInt(9);
            y = rand.nextInt(9);

            if(sudokuBoard[x][y] != 0){
                sudokuBoard[x][y] = 0;
                counter++;
            }
        }

        return sudokuBoard;
    }
}
