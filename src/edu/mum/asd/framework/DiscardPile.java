package edu.mum.asd.framework;

import java.util.ArrayList;

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
		this.cards = new ArrayList<>();
	}
}
