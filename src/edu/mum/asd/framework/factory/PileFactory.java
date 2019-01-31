package edu.mum.asd.framework.factory;

import java.util.ArrayList;
import java.util.List;

import edu.mum.asd.framework.Card;
import edu.mum.asd.framework.CardPile;
import edu.mum.asd.framework.DeckPile;
import edu.mum.asd.framework.DiscardPile;
import edu.mum.asd.framework.SuitPile;
import edu.mum.asd.framework.TablePile;
import edu.mum.asd.framework.game.Solitaire;

public class PileFactory implements IPileFactory{

	@Override
	public CardPile createPile(String type) {
		
		if(type.equals("DECK")) {
			return new DeckPile();
		}else if(type.equals("DISCARD")) {
			return new DiscardPile();
		}else if(type.equals("SUIT")) {
			return new SuitPile();
		}
		return null;
	}

	@Override
	public List<CardPile> createPileList(String type, CardPile deck) {
		if(type.equals("SUIT")) {
			List<CardPile> suits = new ArrayList<>();
			for(int i = 0 ;i < CardPile.numberSuits;i++) {
				SuitPile sp = (SuitPile) this.createPile("SUIT");
				sp.setSuit(i);
				suits.add(sp);
			}
			return suits;
		}else if(type.equals("TABLE")) {
			List<CardPile> tablePiles = new ArrayList<>();
			for(int c = 1;c<=TablePile.numberSuits;c++)
			{
				List<Card> cards = new ArrayList<>();
				for (int i = 0; i < c; i++) {
					cards.add(deck.getCards().remove(0));
				}
				
				TablePile tp = new TablePile(cards);
				Card top = tp.getCards().get(0);
				// flip topmost card face up
				top.flip();
				tablePiles.add(tp);
			}
			return tablePiles;
		}
		return null;
	}

}
