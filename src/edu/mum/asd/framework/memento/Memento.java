package edu.mum.asd.framework.memento;

import edu.mum.asd.framework.GameBoard;

public class Memento {
	
	GameBoard gameBoard;
	
	public Memento(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}
}
