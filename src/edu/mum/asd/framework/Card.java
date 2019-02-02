package edu.mum.asd.framework;

public class Card implements Cloneable{

	public final static int width = 50;
	public final static int height = 70;

	final static int red = 0;
	final static int black = 1;

	final static int heart = 0;
	final static int spade = 1;
	final static int diamond = 2;
	final static int club = 3;

	final static int ace = 0;
	final static int king = 12;

	// data fields
	private boolean faceup;
	private int rank;
	private int suit;

	// constructor
	public Card(int s, int r) {
		suit = s;
		rank = r;
		faceup = false;
	}

	// get rank of card as an int in the interval [0, 12]
	public int rank() {
		return rank;
	}

	// get suit of card as an int in the interval [0, 3]
	public int suit() {
		return suit;
	}

	// true if card is face up, false otherwise
	public boolean faceUp() {
		return faceup;
	}

	// change value of faceup
	public void flip() {
		faceup = !faceup;
	}

	// true if card is ace, false otherwise
	public boolean isAce() {
		return rank == ace;
	}

	// true if card is king, false otherwise
	public boolean isKing() {
		return rank == king;
	}

	// return color of card as an int in the range [0,1]
	public int color() {
		if (suit() == heart || suit() == diamond)
			return red;

		return black;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}