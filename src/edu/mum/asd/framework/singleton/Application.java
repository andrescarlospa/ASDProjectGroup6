package edu.mum.asd.framework.singleton;

import java.util.List;

import edu.mum.asd.framework.GameBoard;
import edu.mum.asd.framework.memento.Memento;

public class Application {

	private GameBoard gameBoard;
	private List<Memento> savedGames;

	private Application() {
	}

	private static class Singleton {
		private static final Application INSTANCE = new Application();
	}

	public static Application getGameInstance() {
		return Singleton.INSTANCE;
	}

	GameBoard getExternalizedState() {
		return gameBoard;
	}

	public Memento saveToMemento() {
		return new Memento(gameBoard);
	}

	public void restoreFromMemento(Memento memento) {
		gameBoard = memento.getGameBoard();
	}

	
}
