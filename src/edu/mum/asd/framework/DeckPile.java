package edu.mum.asd.framework;

import java.util.LinkedList;
import java.util.List;

public class DeckPile extends CardPile{
	
	public DeckPile() {
		this.create();
		this.shuffle();
	}
	
	public void create() {
		for (int i = 0; i < numberSuits; i++)
			for (int j = 0; j <= names.length; j++) {
				cards.add(new Card(i, j));
			}
	}
	
	public void shuffle() {
		int count = cards.size();
		List<Card> sorted = cards;
		cards = new LinkedList<>();
		List<Card> temp = new LinkedList<>();
		for (; count > 0; count--) {
			int limit = ((int) (Math.random() * 1000)) % count;

			// move down to a random location in pileOne
			// while poping the cards into pileTwo
			for (int i = 0; i < limit; i++)
				temp.add(sorted.remove(0));

			// then add the card found there
			// to our LinkedList object: cardList
			cards.add(sorted.remove(0));

			// now put back into pileOne the cards
			// that we poped into pileTwo
			while (!temp.isEmpty())
				sorted.add(temp.remove(0));
		}
		System.out.println("Test");
	}

}
