package com.company.Strategies;

import com.company.Controller.SudokuMapper;
import com.company.SudokuMap;

public class BacktrackStrategy implements ISudokuStrategy {
    SudokuMapper sudokuMapper;
    public BacktrackStrategy(SudokuMapper sudokuMapper){
        this.sudokuMapper = sudokuMapper;
    }

    //uses state from previous strategies
    @Override
    public int[][] solve(int[][] sudokuBoard) {
        int[][] possibleCellValues = new int[9][9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                possibleCellValues[i][j] = sudokuBoard[i][j];
            }
        }

        solveHelper(sudokuBoard, possibleCellValues,0,0);
        return sudokuBoard;
    }

    private boolean solveHelper(int[][] sudokuBoard, int[][] possibleCellValues, int row, int col){
        //base case to fill board
        if(col == 9){
            col = 0;
            row++;

            if(row == 9)
                return true;
        }

        //skip valid values
        if(isValidSingle(sudokuBoard[row][col])){
            return solveHelper(sudokuBoard, possibleCellValues, row, col+1);
        }


        //search in possible values
        for(int i = 0; i < String.valueOf(possibleCellValues[row][col]).length(); i++){
            int chosenValue = Character.getNumericValue(String.valueOf(possibleCellValues[row][col]).charAt(i));

            if(isValidChoice(sudokuBoard, row, col, chosenValue)){
                //choose
                sudokuBoard[row][col] = chosenValue;

                //explore
                if(solveHelper(sudokuBoard, possibleCellValues, row, col+1))
                    return true;

                //unchoose
                sudokuBoard[row][col] = 0;
            }
        }

        return false;
    }

    //decision tree constraints
    private boolean isValidChoice(int[][] sudokuBoard, int giverRow, int givenCol, int cellValue){
        //check row for matching values
        for(int col = 0; col < 9; col++){
            if(sudokuBoard[giverRow][col] == cellValue)
                return false;
        }
        //check column for matching values
        for(int row = 0; row < 9; row++){
            if(sudokuBoard[row][givenCol] == cellValue)
                return false;
        }
        //check block for matching values
        SudokuMap sudokuMap = sudokuMapper.find(giverRow, givenCol);
        for(int row=sudokuMap.getStartRow(); row<=sudokuMap.getStartRow()+2; row++){
            for(int col=sudokuMap.getStartCol(); col<=sudokuMap.getStartCol()+2; col++){
                if(sudokuBoard[row][col] == cellValue)
                    return false;
            }
        }

        return true;
    }

    private boolean isValidSingle(int value){
        return value !=0 && String.valueOf(value).length() == 1;
    }
}
