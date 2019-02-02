package edu.mum.asd.framework.facade;

import javafx.scene.canvas.Canvas;

public interface CardGameFacade {
	public void saveToMemento() throws CloneNotSupportedException;
	public void restoreFromMemento();
	public void moveCard(double x, double y);
	public void paintPiles(Canvas canvas);
	public void repaint(Canvas canvas);
}
