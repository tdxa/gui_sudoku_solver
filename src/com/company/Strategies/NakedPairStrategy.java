package com.company.Strategies;

import com.company.Controller.SudokuMapper;
import com.company.SudokuMap;

import java.util.Arrays;

public class NakedPairStrategy implements ISudokuStrategy {
    private SudokuMapper sudokuMapper;
    public NakedPairStrategy(SudokuMapper sudokuMapper){
        this.sudokuMapper=sudokuMapper;
    }

    //uses state from mark up strategy
    @Override
    public int[][] solve(int[][] sudokuBoard) {
        for(int row = 0; row<9; row++){
            for(int col = 0; col<9; col++){
                eliminateNakedPairInRow(sudokuBoard, row, col);
                eliminateNakedPairInCol(sudokuBoard, row, col);
                eliminateNakedPairInBlock(sudokuBoard, row, col);
            }
        }

        return sudokuBoard;
    }

    //eliminate pair in row for given value
    private void eliminateNakedPairInRow(int[][] sudokuBoard, int givenRow, int givenCol){
        if(!hasNakedPairInRow(sudokuBoard, givenRow, givenCol))
            return;

        for(int col = 0; col < 9; col++){
            if(sudokuBoard[givenRow][col] != sudokuBoard[givenRow][givenCol] && String.valueOf(sudokuBoard[givenRow][col]).length() > 1)
                eliminateNakedPair(sudokuBoard, sudokuBoard[givenRow][givenCol], givenRow, col);
        }
    }

    //check if given value has pair in its row
    private boolean hasNakedPairInRow(int[][] sudokuBoard, int givenRow, int givenCol){
        for(int col=0; col < 9; col++){
            if(givenCol != col && isNakedPair(sudokuBoard[givenRow][col], sudokuBoard[givenRow][givenCol]))
                return true;
        }
        return false;
    }

    //eliminate pair in col for given value
    private void eliminateNakedPairInCol(int[][] sudokuBoard, int givenRow, int givenCol){
        if(!hasNakedPairInCol(sudokuBoard, givenRow, givenCol))
            return;

        for(int row = 0; row < 9; row++){
            if(sudokuBoard[row][givenCol] != sudokuBoard[givenRow][givenCol] && String.valueOf(sudokuBoard[row][givenCol]).length() > 1)
                eliminateNakedPair(sudokuBoard, sudokuBoard[givenRow][givenCol], row, givenCol);
        }
    }

    //check if given value has pair in its col
    private boolean hasNakedPairInCol(int[][] sudokuBoard, int givenRow, int givenCol){
        for(int col=0; col < 9; col++){
            if(givenCol != col && isNakedPair(sudokuBoard[givenRow][col], sudokuBoard[givenRow][givenCol]))
                return true;
        }
        return false;
    }

    //eliminate pair in block for given value
    private void eliminateNakedPairInBlock(int[][] sudokuBoard, int givenRow, int givenCol){
        if(!hasNakedPairInBlock(sudokuBoard, givenRow, givenCol))
            return;

        SudokuMap sudokuMap = sudokuMapper.find(givenRow, givenCol);

        for(int row = sudokuMap.getStartRow(); row<sudokuMap.getStartRow()+2; row++){
            for(int col = sudokuMap.getStartCol(); col<sudokuMap.getStartCol()+2; col++){
                if(String.valueOf(sudokuBoard[row][col]).length() > 1 && sudokuBoard[row][col] != sudokuBoard[givenRow][givenCol])
                    eliminateNakedPair(sudokuBoard, sudokuBoard[givenRow][givenCol], row, col);
            }
        }
    }

    //check if given value has pair in its block
    private boolean hasNakedPairInBlock(int[][] sudokuBoard, int givenRow, int givenCol){
        for(int row = 0; row<9; row++){
            for(int col = 0; col<9; col++){
                boolean valuePair = givenRow == row && givenCol == col;
                boolean valuePairInBlock = sudokuMapper.find(givenRow, givenCol).getStartRow() == sudokuMapper.find(row, col).getStartRow()
                        && sudokuMapper.find(givenRow, givenCol).getStartCol() == sudokuMapper.find(row, col).getStartCol();

                if(!valuePair && valuePairInBlock && isNakedPair(sudokuBoard[givenRow][givenCol], sudokuBoard[row][col]))
                    return true;
            }
        }
        return false;
    }

    private void eliminateNakedPair(int[][] sudokuBoard, int valueToEliminate, int givenRow, int givenCol){
        String sudokuBoardValues = String.valueOf(sudokuBoard[givenRow][givenCol]);
        String valuesToEliminate = String.valueOf(valueToEliminate);

        int[] sudokuBoardValuesArray = new int[sudokuBoardValues.length()];
        int[] valuesToEliminateArray = new int[valuesToEliminate.length()];

        for(int i=0; i<valuesToEliminate.length(); i++){
            sudokuBoardValuesArray[i] = Character.getNumericValue(sudokuBoardValues.charAt(i));
        }
        for(int i=0; i<valuesToEliminate.length(); i++){
            valuesToEliminateArray[i] = Character.getNumericValue(valuesToEliminate.charAt(i));
        }

        int[] newValues = Arrays.stream(sudokuBoardValuesArray).filter(x -> Arrays.stream(valuesToEliminateArray).anyMatch(y -> y!=x)).toArray();
        sudokuBoard[givenRow][givenCol] = Integer.parseInt(Arrays.stream(newValues).mapToObj(String::valueOf).reduce((a,b) -> a.concat(b)).get());
    }

    private boolean isNakedPair(int firstPair, int secondPair){
        return String.valueOf(firstPair).length() == 2 && firstPair == secondPair;
    }
}
