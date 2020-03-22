package com.company.Strategies;

import com.company.Controller.SudokuMapper;
import com.company.SudokuMap;

import java.util.Arrays;

public class MarkUpStrategy implements ISudokuStrategy {
    private SudokuMapper sudokuMapper;
    public MarkUpStrategy(SudokuMapper sudokuMapper){
        this.sudokuMapper = sudokuMapper;
    }

    @Override
    public int[][] solve(int[][] sudokuBoard) {
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(sudokuBoard[i][j] == 0 || String.valueOf(sudokuBoard[i][j]).length() > 1){
                    int rowColValues = getRowColValues(sudokuBoard, i, j);
                    int blockValues = getBlockValues(sudokuBoard, i, j);
                    sudokuBoard[i][j] = getInteresection(rowColValues, blockValues);
                }
            }
        }

        return sudokuBoard;
    }

    //choose values that match in row, column, block
    private int getInteresection(int rowColValues, int blockValues){
        String rowCol = String.valueOf(rowColValues);
        String block = String.valueOf(blockValues);

        int[] possibleRowColValues = new int[rowCol.length()];
        int[] possibleBlockValues = new int[block.length()];

        for(int i=0; i<rowCol.length(); i++){
            possibleRowColValues[i] = Character.getNumericValue(rowCol.charAt(i));
        }
        for(int i=0; i<block.length(); i++){
            possibleBlockValues[i] = Character.getNumericValue(block.charAt(i));
        }

        int[] possibleValues = Arrays.stream(possibleRowColValues).distinct().filter(x -> Arrays.stream(possibleBlockValues).anyMatch(y -> y==x)).toArray();
        return Integer.parseInt(Arrays.stream(possibleValues).mapToObj(String::valueOf).reduce((a,b) -> a.concat(b)).get());
    }

    private int getRowColValues(int sudokuBoard[][], int givenRow, int givenCol){
        int[] possible = {1,2,3,4,5,6,7,8,9};

        //eliminate possible values in given row and column
        for(int col=0; col<9; col++){
            if(isValidSingle(sudokuBoard[givenRow][col]))
                possible[sudokuBoard[givenRow][col]-1]=0;
        }
        for(int row=0; row<9; row++){
            if(isValidSingle(sudokuBoard[row][givenCol]))
                possible[sudokuBoard[row][givenCol]-1]=0;
        }

        //return possible values
        int[] possibleValues = Arrays.stream(possible).filter(p -> p!=0).toArray();
        return Integer.parseInt(Arrays.stream(possibleValues).mapToObj(String::valueOf).reduce((a,b) -> a.concat(b)).get());
    }

    private int getBlockValues(int sudokuBoard[][], int givenRow, int givenCol){
        int[] possible = {1,2,3,4,5,6,7,8,9};
        SudokuMap sudokuMap = sudokuMapper.find(givenRow, givenCol);

        for(int row=sudokuMap.getStartRow(); row<=sudokuMap.getStartRow()+2; row++){
            for(int col=sudokuMap.getStartCol(); col<=sudokuMap.getStartCol()+2; col++){
                if(isValidSingle(sudokuBoard[row][col]))
                    //eliminate possible values in block
                    possible[sudokuBoard[row][col]-1]=0;
            }
        }

        int[] possibleValues = Arrays.stream(possible).filter(p -> p!=0).toArray();
        return Integer.parseInt(Arrays.stream(possibleValues).mapToObj(String::valueOf).reduce((a,b) -> a.concat(b)).get());
    }

    private boolean isValidSingle(int value){
        return value !=0 && String.valueOf(value).length() == 1;
    }
}
