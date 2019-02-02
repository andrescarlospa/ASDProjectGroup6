package edu.mum.asd.framework.singleton;

import java.util.LinkedList;
import java.util.List;

import edu.mum.asd.framework.CardPile;
import edu.mum.asd.framework.GameBoard;
import edu.mum.asd.framework.facade.CardGameFacade;
import edu.mum.asd.framework.memento.Memento;
import edu.mum.asd.framework.strategy.IGameStrategy;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CardGameFramework implements CardGameFacade{

	private GameBoard gameBoard;
	
	private List<Memento> savedGames;
	
	private IGameStrategy gameStrategy;

	private CardGameFramework() {
		this.gameBoard = new GameBoard();
		this.gameBoard.initialization();
		this.savedGames = new LinkedList<>();
	}

	public void setGameStrategy(IGameStrategy gameStrategy) {
		this.gameStrategy = gameStrategy;
	}

	private static class Singleton {
		private static final CardGameFramework INSTANCE = new CardGameFramework();
	}

	public static CardGameFramework getGameInstance() {
		return Singleton.INSTANCE;
	}

	public GameBoard getExternalizedState() {
		return gameBoard;
	}

	public void saveToMemento() throws CloneNotSupportedException {
		addMemento(new Memento((GameBoard) gameBoard.clone()));
	}

	public void restoreFromMemento() {
		if(savedGames.size()>0)
			gameBoard = savedGames.remove(savedGames.size()-1).getGameBoard();
	}

	public void addMemento(Memento memento) {
		this.savedGames.add(memento);
	}
	
	public void playCard(double x, double y) {
		gameStrategy.playCard(this.gameBoard, x, y);
	}
	
	public void repaint(Canvas canvas){
		paintPiles(canvas);
	}
	
	public void paintPiles(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, 800, 600);
		gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
		for (CardPile pile : getExternalizedState().getPiles())
		{
			pile.display(gc);
		}
	}
	
}
