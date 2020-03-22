package com.company.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.company.Controller.SudokuMapper;
import com.company.Controller.SudokuSolverEngine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Controller implements Initializable {

    GameBoard gameBoard;

    @FXML
    Button btn_solve;
    @FXML
    Button btn_reset;
    @FXML
    Canvas canvas;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        gameBoard = new GameBoard();
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
    }

    private void drawOnCanvas(GraphicsContext context) {

        //drawing empty board
        context.clearRect(0, 0, 450, 450);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                int position_y = row * 50 + 2;
                int position_x = col * 50 + 2;
                int width = 46;

                if ((row >= 0 && row <= 2) && (col >= 0 && col <= 2)) {
                    context.setFill(Color.LIGHTSTEELBLUE);
                    context.fillRoundRect(position_x, position_y, width, width, 10, 10);
                } else if ((row >= 0 && row <= 2) && (col >= 6 && col <= 8)) {
                    context.setFill(Color.LIGHTSTEELBLUE);
                    context.fillRoundRect(position_x, position_y, width, width, 10, 10);
                } else if ((row >= 3 && row <= 5) && (col >= 3 && col <= 5)) {
                    context.setFill(Color.LIGHTSTEELBLUE);
                    context.fillRoundRect(position_x, position_y, width, width, 10, 10);
                } else if ((row >= 6 && row <= 8) && (col >= 6 && col <= 8)) {
                    context.setFill(Color.LIGHTSTEELBLUE);
                    context.fillRoundRect(position_x, position_y, width, width, 10, 10);
                } else if ((row >= 6 && row <= 8) && (col >= 0 && col <= 2)) {
                    context.setFill(Color.LIGHTSTEELBLUE);
                    context.fillRoundRect(position_x, position_y, width, width, 10, 10);
                } else {
                    context.setFill(Color.LIGHTSKYBLUE);
                    context.fillRoundRect(position_x, position_y, width, width, 10, 10);
                }

            }
        }

        //drawing the initial numbers from GameBoard class

        int[][] initial = gameBoard.getStarting();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                int position_y = row * 50 + 35;
                int position_x = col * 50 + 20;

                context.setFill(Color.BLACK);
                //default font is System
                context.setFont(new Font(20));

                if (initial[row][col] != 0) {
                    context.fillText(initial[row][col] + "", position_x, position_y);
                }

            }
        }
    }

    public void buttonResetPressed() {
        this.gameBoard = new GameBoard();
        GraphicsContext context_reset = canvas.getGraphicsContext2D();
        drawOnCanvas(context_reset);
    }

    public void buttonSolvePressed(){
        SudokuMapper sudokuMapper = new SudokuMapper();
        SudokuSolverEngine sudokuSolverEngine = new SudokuSolverEngine(gameBoard.getBoardStateManager(),sudokuMapper);
        sudokuSolverEngine.solve(gameBoard.getStarting());
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
    }
}
