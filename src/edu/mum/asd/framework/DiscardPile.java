package edu.mum.asd.framework;

import edu.mum.asd.framework.bridge.BasicDrawing;
import edu.mum.asd.framework.iterator.LinkedList;

public class DiscardPile extends CardPile{
	public DiscardPile(int x, int y) {
		super(x, y);
		drawing = new BasicDrawing();
	}

	@Override
	public boolean canTake(Card card) {
		return false;
	}

	@Override
	public void select(double x, double y) {
	}
	
	public void clear() {
		this.cards = new LinkedList();
	}
}
