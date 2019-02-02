package edu.mum.asd.framework.templatemethod;

import edu.mum.asd.framework.Card;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EmptySide extends AGraph {

	public void draw(GraphicsContext gc, Card card, double x, double y) {
		gc.setFill(Color.GRAY);
		gc.fillRect(x, y, Card.width, Card.height);
	}
}
