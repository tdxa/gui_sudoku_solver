package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


public class Controller implements Initializable {

    @FXML Button btn_solve;
    @FXML Button btn_reset;
    @FXML Canvas canvas;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
    }

    public void drawOnCanvas(GraphicsContext context){

        context.clearRect(0, 0, 450, 450);
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {

                int position_y = row * 50 + 2;
                int position_x = col * 50 + 2;
                int width = 46;

                context.setFill(Color.WHITE);
                context.fillRoundRect(position_x, position_y, width, width, 10, 10);
            }
        }

    }
}
