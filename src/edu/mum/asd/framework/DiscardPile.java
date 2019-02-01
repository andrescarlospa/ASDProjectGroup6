package edu.mum.asd.framework;

import edu.mum.asd.framework.iterator.LinkedList;

public class DiscardPile extends CardPile{
	public DiscardPile(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean canTake(Card card) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void select(double x, double y) {
		// TODO Auto-generated method stub
		
	}
	
	public void clear() {
		this.cards = new LinkedList();
	}
}
