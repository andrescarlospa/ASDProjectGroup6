package edu.mum.asd.framework;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class CardPile implements Cloneable {

	protected static String names[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	protected List<Card> cards;

	public static int numberSuits = 4;

	protected int x;
	protected int y;

	CardPile(int x, int y) {
		this.x = x;
		this.y = y;
		cards = new LinkedList<>();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		List<Card> clonedCards = new LinkedList<>();
		for (Card card : cards) {
			clonedCards.add((Card) card.clone());
		}
		CardPile clone = (CardPile) super.clone();
		clone.cards = clonedCards;
		return clone;
	}

	public List<Card> getCards() {
		return cards;
	}

	public boolean includes(double tx, double ty) {
		return x <= tx && tx <= x + Card.width && y <= ty && ty <= y + Card.height;
	}

	public void display(GraphicsContext gc) {
		if (cards.isEmpty()) {
			gc.setFill(Color.GRAY);
			gc.fillRect(x, y, Card.width, Card.height);
		} else
			cards.get(0).draw(gc, x, y);
		// top().draw(g, x, y);

	}

	public abstract boolean canTake(Card card);

	// to be overridden by descendants
	public abstract void select(double x, double y);

	// add a card to pile
	public void addCard(Card card) {
		cards.add(card);
	}

	// get number of cards in pile
	public int getNoCards() {
		return cards.size();
	}

}
