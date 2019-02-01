package edu.mum.asd.framework;

public class SuitPile extends CardPile{
	
	private int suit;

	public SuitPile(int x, int y) {
		super(x, y);
	}
	
	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
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
}
