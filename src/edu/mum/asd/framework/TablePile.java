package edu.mum.asd.framework;

import edu.mum.asd.framework.bridge.StackDrawing;
import edu.mum.asd.framework.iterator.LinkedList;
import javafx.scene.canvas.GraphicsContext;

public class TablePile extends CardPile {
	final static int ydist = 25;

	public TablePile(LinkedList cards, int x, int y) {
		super(x, y);
		this.cards = cards;
		drawing = new StackDrawing();
	}

	public static int numberPiles = 7;

	@Override
	public void display(GraphicsContext gc) {
		drawing.display(gc, cards, x, y);
	}

	@Override
	public boolean canTake(Card aCard) {
		if (cards.empty())
			return aCard.isKing();

		Card topCard = top();

		// if our topmost card is face down
		// we can't accept another card
		if (!topCard.faceUp())
			return false;

		return (aCard.color() != topCard.color()) && (aCard.rank() == topCard.rank() - 1);
	}

	@Override
	public boolean includes(double tx, double ty) {
		if (cards.empty())
			return false;

		// don't test bottom of card
		return x <= tx && tx <= x + Card.width && y <= ty;
	}

}
