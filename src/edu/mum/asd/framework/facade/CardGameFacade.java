package edu.mum.asd.framework.facade;

import edu.mum.asd.framework.strategy.IGameStrategy;
import javafx.scene.canvas.Canvas;

public interface CardGameFacade {
	public void setGameStrategy(IGameStrategy gameStrategy);
	public void saveToMemento() throws CloneNotSupportedException;
	public void restoreFromMemento();
	public void playCard(double x, double y);
	public void paintPiles(Canvas canvas);
	public void repaint(Canvas canvas);
}
