package edu.mum.asd.framework.templatemethod;

import edu.mum.asd.framework.Card;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AGraph {
	
	public final static int width = 50;
	public final static int height = 70;

	Image spades = new Image("file:resources/images/spades.png");
    Image diamonds = new Image("file:resources/images/diamonds.png");
    Image clubs = new Image("file:resources/images/clubs.png");
    Image hearts = new Image("file:resources/images/hearts.png");
    Image back = new Image("file:resources/images/back.jpg");
    
    final static int red = 0;
	final static int black = 1;
	
	final static int heart = 0;
	final static int spade = 1;
	final static int diamond = 2;
	final static int club = 3;
	
	protected static String names[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	public abstract void draw(GraphicsContext gc, Card card, double x, double y);
}
