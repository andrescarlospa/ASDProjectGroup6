package edu.mum.asd.framework;

import edu.mum.asd.framework.game.LinkedList;
import edu.mum.asd.framework.game.ListIterator;
import edu.mum.asd.framework.game.Solitaire;
import javafx.scene.canvas.GraphicsContext;

public class TablePile extends CardPile{
	final static int ydist = 25;

	public TablePile(LinkedList cards, int x, int y) {
		super(x, y);
		this.cards = cards;
	}

	public static int numberPiles = 7;
	
	public void display(GraphicsContext gc) {
		stackDisplay(gc);
	}
	
	private void stackDisplay(GraphicsContext g) {
//		cards.stream().collect(Collectors.toCollection(ArrayDeque::new)) // or LinkedList
//	      .descendingIterator()
//	      .forEachRemaining(c->{
//	    	   		c.draw(g, x, localy);
//				localy += ydist;
//	      });
//		
//		localy = y;
		
		// holds y-coordinate of cards in pile
		int localy = y;

		LinkedList reverseCardList = new LinkedList();

		// get iterator for our list
		ListIterator iterator = cards.iterator();

		// build reversed order list
		while (!iterator.atEnd()) {
			reverseCardList.add(iterator.current());
			iterator.next();
		}

		// get iterator for reversed order list
		iterator = reverseCardList.iterator();

		// Go through the reversed order list
		// and draw each card in the list
		while (!iterator.atEnd()) {
			((Card) iterator.current()).draw(g, x, localy);
			localy += ydist;
			iterator.next();
		}
	}

	@Override
	public boolean canTake(Card aCard) {
		if (cards.empty())
			return aCard.isKing();

		Card topCard = top();

		// if our topmost card is face down
		// we can't accept another card
		if (!topCard.faceUp())
			return false;

		return (aCard.color() != topCard.color()) && (aCard.rank() == topCard.rank() - 1);
	}

	@Override
	public boolean includes(double tx, double ty) {
		if (cards.empty())
			return false;

		// don't test bottom of card
		return x <= tx && tx <= x + Card.width && y <= ty;
	}

	public void select(double tx, double ty) {
		
	}
	
}
