package com.company.Controller;
import com.company.SudokuMap;

public class SudokuMapper {
    public SudokuMap find(int givenRow, int givenCol)
    {
        SudokuMap sudokuMap = new SudokuMap();

        if ((givenRow >= 0 && givenRow <= 2) && (givenCol >= 0 && givenCol <= 2)) {
            sudokuMap.setStartRow(0);
            sudokuMap.setStartCol(0);
        }
        else if ((givenRow >= 0 && givenRow <= 2) && (givenCol >= 3 && givenCol <= 5)) {
            sudokuMap.setStartRow(0);
            sudokuMap.setStartCol(3);
        }
        else if ((givenRow >= 0 && givenRow <= 2) && (givenCol >= 6 && givenCol <= 8)) {
            sudokuMap.setStartRow(0);
            sudokuMap.setStartCol(6);
        }
        else if ((givenRow >= 3 && givenRow <= 5) && (givenCol >= 0 && givenCol <= 2)) {
            sudokuMap.setStartRow(3);
            sudokuMap.setStartCol(0);
        }
        else if ((givenRow >= 3 && givenRow <= 5) && (givenCol >= 3 && givenCol <= 5)) {
            sudokuMap.setStartRow(3);
            sudokuMap.setStartCol(3);
        }
        else if ((givenRow >= 3 && givenRow <= 5) && (givenCol >= 6 && givenCol <= 8)) {
            sudokuMap.setStartRow(3);
            sudokuMap.setStartCol(6);
        }
        else if ((givenRow >= 6 && givenRow <= 8) && (givenCol >= 0 && givenCol <= 2)) {
            sudokuMap.setStartRow(6);
            sudokuMap.setStartCol(0);
        }
        else if ((givenRow >= 6 && givenRow <= 8) && (givenCol >= 3 && givenCol <= 5)) {
            sudokuMap.setStartRow(6);
            sudokuMap.setStartCol(3);
        }
        else if ((givenRow >= 6 && givenRow <= 8) && (givenCol >= 6 && givenCol <= 8)) {
            sudokuMap.setStartRow(6);
            sudokuMap.setStartCol(6);
        }

        return sudokuMap;
    }

}
