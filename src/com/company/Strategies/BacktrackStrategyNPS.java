package com.company.Strategies;

import com.company.Controller.SudokuMapper;
import com.company.SudokuMap;

//NPS version - not using previous board state

public class BacktrackStrategyNPS implements ISudokuStrategy {
    SudokuMapper sudokuMapper;
    public BacktrackStrategyNPS(SudokuMapper sudokuMapper){
        this.sudokuMapper = sudokuMapper;
    }

    @Override
    public int[][] solve(int[][] sudokuBoard) {
        solveHelper(sudokuBoard,0,0);
        return sudokuBoard;
    }

    private boolean solveHelper(int[][] sudokuBoard, int row, int col){
        //base case to fill board
        if(col == 9){
            col = 0;
            row++;

            if(row == 9)
                return true;
        }

        //skip valid values
        if(isValidSingle(sudokuBoard[row][col])){
            return solveHelper(sudokuBoard, row, col+1);
        }

        //search in possible values
        for(int i = 1; i <= 9; i++){
            if(isValidSingle(sudokuBoard[row][col])){
                solveHelper(sudokuBoard, row, col+1);
            }

            if(isValidChoice(sudokuBoard, row, col, i)){
                //choose
                sudokuBoard[row][col] = i;

                //explore
                if(solveHelper(sudokuBoard, row, col+1))
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
