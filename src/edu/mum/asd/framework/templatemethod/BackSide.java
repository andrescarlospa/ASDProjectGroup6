package edu.mum.asd.framework.templatemethod;

import edu.mum.asd.framework.Card;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BackSide extends AGraph {

	public void draw(GraphicsContext gc, Card card, double x, double y) {
		gc.clearRect(x, y, width, height);
		gc.setFill(Color.WHITE);
		gc.fillRect(x, y, width, height);
		gc.setFill(Color.YELLOW);
		gc.drawImage(back, x, y, 50, 70);
	}
}
