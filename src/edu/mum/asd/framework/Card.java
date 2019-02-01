package edu.mum.asd.framework;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

////////////////////////
//Defines a Card class
//
//used by CardPile
////////////////////////
public class Card implements Cloneable{
	// data fields for colors and suits
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

	private static String names[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

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

	Image spades = new Image("file:resources/images/spades.png");
    Image diamonds = new Image("file:resources/images/diamonds.png");
    Image clubs = new Image("file:resources/images/clubs.png");
    Image hearts = new Image("file:resources/images/hearts.png");
    Image back = new Image("file:resources/images/back.jpg");
	// draw the card
	public void draw(GraphicsContext gc, int x, int y) {
		// clear rectangle, draw border
		gc.clearRect(x, y, width, height);
		gc.setFill(Color.WHITE);
		gc.fillRect(x, y, width, height);

		// draw body of card
		if (faceUp()) {
			if (color() == red)
				gc.setFill(Color.RED);
			else
				gc.setFill(Color.BLACK);
			gc.fillText(names[rank()], x + 3, y + 15);
			gc.fillText(names[rank()], x + 35, y + 65);
			


			if (suit() == heart) {
				gc.drawImage(hearts, x+10, y+22, 30, 30);
			} else if (suit() == spade) {
				gc.drawImage(spades, x+10, y+22, 30, 30);
			} else if (suit() == diamond) {
				gc.drawImage(diamonds, x+10, y+22, 30, 30);
			} else if (suit() == club) {
				gc.drawImage(clubs, x+10, y	+22, 30, 30);
			}
		} else // face down
		{
			gc.setFill(Color.YELLOW);
			gc.drawImage(back, x, y, 50, 70);
			//gc.drawLine(x + 15, y + 5, x + 15, y + 65);
//			gc.drawLine(x + 35, y + 5, x + 35, y + 65);
//			gc.drawLine(x + 5, y + 20, x + 45, y + 20);
//			gc.drawLine(x + 5, y + 35, x + 45, y + 35);
//			gc.drawLine(x + 5, y + 50, x + 45, y + 50);
		}
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}