package edu.mum.asd.framework;

import edu.mum.asd.framework.bridge.BasicDrawing;
import edu.mum.asd.framework.iterator.LinkedList;
import javafx.scene.canvas.GraphicsContext;

public class DiscardPile extends CardPile{
	public DiscardPile(int x, int y) {
		super(x, y);
		drawing = new BasicDrawing();
	}
	@Override
	public void display(GraphicsContext gc) {
		drawing.display(gc, cards, x, y);
	}
	@Override
	public boolean canTake(Card card) {
		return false;
	}

	public void clear() {
		this.cards = new LinkedList();
	}
}
