package com.company.gui;

import com.company.Controller.SudokuBoardStateManager;

public class GameBoard {
    private int[][] starting;
    private SudokuBoardStateManager boardStateManager;

    public int[][] getStarting() {
        return starting;
    }
    public SudokuBoardStateManager getBoardStateManager() {
        return boardStateManager;
    }


    public GameBoard(){
        this.boardStateManager = new SudokuBoardStateManager();
        this.starting = boardStateManager.generateSudokuBoard();
    }
}
