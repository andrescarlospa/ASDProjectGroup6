package edu.mum.asd.framework;

import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.canvas.GraphicsContext;

public class TablePile extends CardPile{
	final static int ydist = 25;
	int localy;
	public TablePile(List<Card> cards, int x, int y) {
		super(x, y);
		this.cards = cards;
		localy = y;
	}

	public static int numberPiles = 7;
	
	public void display(GraphicsContext gc) {
		stackDisplay(gc);
	}
	
	private void stackDisplay(GraphicsContext g) {
		cards.stream().collect(Collectors.toCollection(ArrayDeque::new)) // or LinkedList
	      .descendingIterator()
	      .forEachRemaining(c->{
	    	   		c.draw(g, x, localy);
				localy += ydist;
	      });
		
		localy = y;
	}

	@Override
	public boolean canTake(Card card) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void select(double x, double y) {
		// TODO Auto-generated method stub
		
	}
	
}
