package edu.mum.asd.framework;

import java.util.ArrayList;
import java.util.List;

import edu.mum.asd.framework.factory.IPileFactory;
import edu.mum.asd.framework.factory.PileFactory;

public class GameBoard {
	
	protected int totalCardPiles = 13;
	protected int totalSuitPiles = 4;
	protected int totalTablePiles = 7;
	
	private List<CardPile> piles;
	
	CardPile deck;
	CardPile discard;
	List<CardPile> suits;
	List<CardPile> tablePiles;
	
	public void initialization() {
		
		IPileFactory factory = new PileFactory();
		piles = new ArrayList<>();
		
		deck = factory.createPile("DECK");
		discard = factory.createPile("DISCARD");
		suits = factory.createPileList("SUIT", null);
		tablePiles = factory.createPileList("TABLE", deck);
		
		piles.add(deck);
		piles.add(discard);
		piles.addAll(suits);
		piles.addAll(tablePiles);
		
	}
	
	public static void main(String[] args) {
		GameBoard gb = new GameBoard();
		gb.initialization();
	}

	public int getTotalCardPiles() {
		return totalCardPiles;
	}

	public int getTotalSuitPiles() {
		return totalSuitPiles;
	}

	public int getTotalTablePiles() {
		return totalTablePiles;
	}

	public List<CardPile> getPiles() {
		return piles;
	}

	public CardPile getDeck() {
		return deck;
	}

	public CardPile getDiscard() {
		return discard;
	}

	public List<CardPile> getSuits() {
		return suits;
	}

	public List<CardPile> getTablePiles() {
		return tablePiles;
	}
	
	

}
