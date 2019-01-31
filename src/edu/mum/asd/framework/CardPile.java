package edu.mum.asd.framework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class CardPile implements Cloneable{

	protected static String names[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	
	protected List<Card> cards = new LinkedList<>();
	
	public static int numberSuits = 4;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		List<Card> clonedCards = new ArrayList<>();
		for(Card card : cards) {
			clonedCards.add((Card) card.clone());
		}
		CardPile clone = (CardPile) super.clone();
		clone.cards = clonedCards;
		return clone;
	}

	public List<Card> getCards() {
		return cards;
	}
	
}
