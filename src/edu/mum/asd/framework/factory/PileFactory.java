package edu.mum.asd.framework.factory;

import java.util.ArrayList;
import java.util.List;

import edu.mum.asd.framework.Card;
import edu.mum.asd.framework.CardPile;
import edu.mum.asd.framework.DeckPile;
import edu.mum.asd.framework.DiscardPile;
import edu.mum.asd.framework.SuitPile;
import edu.mum.asd.framework.TablePile;
import edu.mum.asd.framework.game.LinkedList;

public class PileFactory implements IPileFactory{
	
	final static int topMargin = 40;
	final static int leftMargin = 5;
	final static int distTable = 5;
	final static int distSuit = 10;


	@Override
	public CardPile createPile(String type) {
		int xDeck = leftMargin + (TablePile.numberPiles - 1) * (Card.width + distTable);
		if(type.equals("DECK")) {
			return new DeckPile(xDeck, topMargin);
		}else if(type.equals("DISCARD")) {
			return new DiscardPile(xDeck - Card.width - distSuit, topMargin);
		}
		return null;
	}

	@Override
	public List<CardPile> createPileList(String type, CardPile deck) {
		if(type.equals("SUIT")) {
			List<CardPile> suits = new ArrayList<>();
			for(int i = 0 ;i < CardPile.numberSuits;i++) {
				SuitPile sp = new SuitPile(leftMargin + (Card.width + distSuit) * i, topMargin);
				sp.setSuit(i);
				suits.add(sp);
			}
			return suits;
		}else if(type.equals("TABLE")) {
			List<CardPile> tablePiles = new ArrayList<>();
			for(int c = 1;c<=TablePile.numberPiles;c++)
			{
				LinkedList cards = new LinkedList();
				for (int i = 0; i < c; i++) {
					cards.add(deck.getCards().pop());
				}
				
				TablePile tp = new TablePile(cards, leftMargin + (Card.width + distTable) * c,
						Card.height + distTable + topMargin);
				Card top = (Card) tp.getCards().front();
				// flip topmost card face up
				top.flip();
				tablePiles.add(tp);
			}
			return tablePiles;
		}
		return null;
	}

}
