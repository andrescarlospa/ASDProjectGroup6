package edu.mum.asd.framework.factory;

import java.util.List;

import edu.mum.asd.framework.CardPile;

public interface IPileFactory {
	
	public CardPile createPile(String type);

	public List<CardPile> createPileList(String string, CardPile deck);

}
