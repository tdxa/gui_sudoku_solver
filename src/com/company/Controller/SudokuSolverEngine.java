package com.company.Controller;

import com.company.Strategies.BacktrackStrategy;
import com.company.Strategies.ISudokuStrategy;
import com.company.Strategies.MarkUpStrategy;
import com.company.Strategies.NakedPairStrategy;

import java.util.LinkedList;
import java.util.List;

public class SudokuSolverEngine {
    private SudokuBoardStateManager sudokuBoardStateManager;
    private SudokuMapper sudokuMapper;

    public SudokuSolverEngine(SudokuBoardStateManager sudokuBoardStateManager, SudokuMapper sudokuMapper){
        this.sudokuBoardStateManager = sudokuBoardStateManager;
        this.sudokuMapper = sudokuMapper;
    }

    public boolean solve(int[][] sudokuBoard){
        List<ISudokuStrategy> strategies = new LinkedList<>();
        strategies.add(new MarkUpStrategy(sudokuMapper));
        strategies.add(new NakedPairStrategy(sudokuMapper));
        //strategies.add(new BacktrackStrategyNPS(sudokuMapper));

        String currentState = sudokuBoardStateManager.generateState(sudokuBoard);
        String nextState = sudokuBoardStateManager.generateState(strategies.get(0).solve(sudokuBoard));

        //use other strategies to tighten decision tree for backtracking or solve puzzle
        while(!sudokuBoardStateManager.isSolved(sudokuBoard) && !currentState.equals(nextState)){
            currentState = nextState;
            for(var strategy : strategies){
                nextState = sudokuBoardStateManager.generateState(strategy.solve(sudokuBoard));
            }
        }

        //only use backtracking after eliminating possible cell values
        if(!sudokuBoardStateManager.isSolved(sudokuBoard)){
            BacktrackStrategy backtrackStrategy = new BacktrackStrategy(sudokuMapper);
            backtrackStrategy.solve(sudokuBoard);
        }

        return sudokuBoardStateManager.isSolved(sudokuBoard);
    }
}