package edu.mum.asd.framework.templatemethod;

import edu.mum.asd.framework.Card;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FrontSide extends AGraph {

	public void draw(GraphicsContext gc, Card card, double x, double y) {
		// clear rectangle, draw border
		gc.clearRect(x, y, width, height);
		gc.setFill(Color.WHITE);
		gc.fillRect(x, y, width, height);

		// draw body of card
		if (card.color() == red)
			gc.setFill(Color.RED);
		else
			gc.setFill(Color.BLACK);
		gc.fillText(names[card.rank()], x + 3, y + 15);
		gc.fillText(names[card.rank()], x + 35, y + 65);

		if (card.suit() == heart) {
			gc.drawImage(hearts, x + 10, y + 22, 30, 30);
		} else if (card.suit() == spade) {
			gc.drawImage(spades, x + 10, y + 22, 30, 30);
		} else if (card.suit() == diamond) {
			gc.drawImage(diamonds, x + 10, y + 22, 30, 30);
		} else if (card.suit() == club) {
			gc.drawImage(clubs, x + 10, y + 22, 30, 30);
		}

	}

}
