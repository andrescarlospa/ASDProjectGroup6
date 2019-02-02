package edu.mum.asd.framework.bridge;

import edu.mum.asd.framework.Card;
import edu.mum.asd.framework.iterator.LinkedList;
import edu.mum.asd.framework.iterator.ListIterator;
import edu.mum.asd.framework.templatemethod.AGraph;
import edu.mum.asd.framework.templatemethod.BackSide;
import edu.mum.asd.framework.templatemethod.FrontSide;
import javafx.scene.canvas.GraphicsContext;

public class StackDrawing implements IDrawing{

	final static int ydist = 25;
	
	@Override
	public void display(GraphicsContext gc, LinkedList cards, double x, double y) {
			
		// holds y-coordinate of cards in pile
		double localy = y;

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
			AGraph graph=null;
			Card card = ((Card) iterator.current());
			if(card.faceUp()) {
				graph = new FrontSide();
			}else {
				graph = new BackSide();
			}
			graph.draw(gc, card, x, localy);
			localy += ydist;
			iterator.next();
		}
	}

}
