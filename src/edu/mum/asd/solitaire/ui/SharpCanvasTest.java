package edu.mum.asd.solitaire.ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SharpCanvasTest extends Application {

    @Override
    public void start(Stage primaryStage) {
        Canvas sharpCanvas = createCanvasGrid(600, 300, true);
        Canvas blurryCanvas = createCanvasGrid(600, 300, false);
        VBox root = new VBox(5, sharpCanvas, blurryCanvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Canvas createCanvasGrid(int width, int height, boolean sharp) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D() ;
        gc.setLineWidth(1.0);
        
        int x = 10;
        int y = 10;
        
        gc.moveTo(x + 25, y + 30);
        gc.lineTo(x + 35, y + 20);
        
        gc.stroke();
        
        /*
        g.drawLine(x + 25, y + 30, x + 35, y + 20);
		g.drawLine(x + 35, y + 20, x + 45, y + 30);
		g.drawLine(x + 45, y + 30, x + 25, y + 60);
		g.drawLine(x + 25, y + 60, x + 5, y + 30);
		g.drawLine(x + 5, y + 30, x + 15, y + 20);
		g.drawLine(x + 15, y + 20, x + 25, y + 30);
        
        /*
        
        for (int x = 0; x < width; x+=10) {
            double x1 ;
            if (sharp) {
                x1 = x + 0.5 ;
            } else {
                x1 = x ;
            }
            gc.moveTo(x1, 0);
            gc.lineTo(x1, height);
            gc.stroke();
        }

        for (int y = 0; y < height; y+=10) {
            double y1 ;
            if (sharp) {
                y1 = y + 0.5 ;
            } else {
                y1 = y ;
            }
            gc.moveTo(0, y1);
            gc.lineTo(width, y1);
            gc.stroke();
        }*/
        return canvas ;
    }

    public static void main(String[] args) {
        launch(args);
    }
}