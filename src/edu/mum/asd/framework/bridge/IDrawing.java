package edu.mum.asd.framework.bridge;

import edu.mum.asd.framework.iterator.LinkedList;
import javafx.scene.canvas.GraphicsContext;

public interface IDrawing {
	
	public void display(GraphicsContext gc, LinkedList cards, double x, double y);

}
