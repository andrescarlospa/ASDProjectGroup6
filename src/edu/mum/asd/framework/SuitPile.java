package edu.mum.asd.framework;

public class SuitPile extends CardPile{
	
	private int suit;

	
	
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
}
