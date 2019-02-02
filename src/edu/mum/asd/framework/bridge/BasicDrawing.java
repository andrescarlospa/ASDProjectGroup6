package edu.mum.asd.framework.bridge;

import edu.mum.asd.framework.Card;
import edu.mum.asd.framework.iterator.LinkedList;
import edu.mum.asd.framework.templatemethod.AGraph;
import edu.mum.asd.framework.templatemethod.BackSide;
import edu.mum.asd.framework.templatemethod.EmptySide;
import edu.mum.asd.framework.templatemethod.FrontSide;
import javafx.scene.canvas.GraphicsContext;

public class BasicDrawing implements IDrawing{
	
	public void display(GraphicsContext gc, LinkedList cards, double x, double y) {
		
		AGraph graph=null;
		Card card = null;
		if (cards.empty()) {
			graph = new EmptySide();
		} else
		{
			card = ((Card)cards.front());
			if(card.faceUp()) {
				graph = new FrontSide();
			}else {
				graph = new BackSide();
			}
			
		}
		graph.draw(gc, card, x, y);
	}

}
