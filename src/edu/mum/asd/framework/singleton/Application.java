package edu.mum.asd.framework.singleton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.mum.asd.framework.GameBoard;
import edu.mum.asd.framework.memento.Memento;

public class Application {

	private GameBoard gameBoard;
	
	private List<Memento> savedGames;

	private Application() {
		gameBoard = new GameBoard();
		gameBoard.initialization();
		savedGames = new LinkedList<>();
	}

	private static class Singleton {
		private static final Application INSTANCE = new Application();
	}

	public static Application getGameInstance() {
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
	
}
