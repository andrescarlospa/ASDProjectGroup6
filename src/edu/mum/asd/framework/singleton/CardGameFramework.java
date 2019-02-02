package edu.mum.asd.framework.singleton;

import java.util.LinkedList;
import java.util.List;

import edu.mum.asd.framework.Card;
import edu.mum.asd.framework.CardPile;
import edu.mum.asd.framework.DeckPile;
import edu.mum.asd.framework.DiscardPile;
import edu.mum.asd.framework.GameBoard;
import edu.mum.asd.framework.SuitPile;
import edu.mum.asd.framework.TablePile;
import edu.mum.asd.framework.facade.CardGameFacade;
import edu.mum.asd.framework.iterator.ListIterator;
import edu.mum.asd.framework.memento.Memento;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CardGameFramework implements CardGameFacade{

	private GameBoard gameBoard;
	
	private List<Memento> savedGames;

	private CardGameFramework() {
		gameBoard = new GameBoard();
		gameBoard.initialization();
		savedGames = new LinkedList<>();
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
	
	public void moveCard(double x, double y) {
		for (CardPile pile : getExternalizedState().getPiles())
			if (pile.includes(x, y)) {
				if(pile.getCards().empty() && pile instanceof DeckPile) {
					
					ListIterator iterator = getExternalizedState().getDiscard().getCards().iterator();

					// build reversed order list
					while (!iterator.atEnd()) {
						Card card = (Card) iterator.current();
						card.flip();
						pile.addCard(card);
						iterator.next();
					}
					((DiscardPile)getExternalizedState().getDiscard()).clear();
				}else if(pile instanceof DeckPile){
					Card card = (Card)pile.getCards().pop();
					card.flip();
					getExternalizedState().getDiscard().addCard(card);
				}else if(pile instanceof TablePile) {
					// if face down, then flip
					tablePileLogic(pile);
					
				}else if(pile instanceof DiscardPile) {
					dicardPileLogic(pile);
				}
				
				break;
			}
	}
	
	public void dicardPileLogic(CardPile pile) {
		if (pile.getCards().empty())
			return;

		Card topCard = (Card)pile.getCards().front();

		// check the SuitPile's first
		for (int i = 0; i < SuitPile.numberSuits; i++)
			if (getExternalizedState().getSuits().get(i).canTake(topCard)) {
				getExternalizedState().getSuits().get(i).addCard((Card)pile.getCards().pop());
				return;
			}

		// then check the TablePile's
		
		for (int i = 0; i < TablePile.numberPiles; i++)
			if (getExternalizedState().getTablePiles().get(i).canTake(topCard)) {
				getExternalizedState().getTablePiles().get(i).addCard((Card)pile.getCards().pop());

				return;
			}
	}
	
	public void tablePileLogic(CardPile pile) {
		if (pile.getCards().empty())
			return;
		
		Card topCard = (Card)pile.getCards().front();
		if (!topCard.faceUp()) {
			topCard.flip();
		}else {
			
			
			for (int i = 0; i < SuitPile.numberSuits; i++)
				if (getExternalizedState().getSuits().get(i).canTake(topCard)) {
					getExternalizedState().getSuits().get(i).addCard((Card)pile.getCards().pop());
					return;
				}

			// try to create a build
			CardPile build = new SuitPile(0, 0);

			// get the cards for the build from the suit pile
			while (!pile.getCards().empty()) {
				// stop if we reached a card that is face down
				if (!((Card)pile.getCards().front()).faceUp())
					break;
				build.addCard(((Card)pile.getCards().pop()));
			}

			// We don't allow the user to play a King card
			// that is at the bottom of a table pile
			// to another table pile
			if (build.top().isKing() && pile.getCards().empty()) {
				while (!build.getCards().empty())
					pile.addCard((Card)build.getCards().pop());
				return;
			}

			// if we have to play only one card
			if (build.top() == topCard) {
				// put it back into the table pile
				pile.getCards().add(build.getCards().pop());

				// we have already tried the suit piles
				// see if any other table pile can take card
				for (int i = 0; i < TablePile.numberPiles; i++)
					if ( getExternalizedState().getTablePiles().get(i).canTake(topCard)) {
						getExternalizedState().getTablePiles().get(i).addCard((Card) pile.getCards().pop());
						return;
					}
			} else // we got ourselves a build to play
			{
				topCard = build.top();

				// see if any other table pile can take this build
				for (int i = 0; i < TablePile.numberPiles; i++)
					if (getExternalizedState().getTablePiles().get(i).canTake(topCard)) {
						while (!build.getCards().empty())
							getExternalizedState().getTablePiles().get(i).addCard((Card)build.getCards().pop());

						return;
					}

				// can't play the build?
				// then we must restore our pile
				while (!build.getCards().empty())
					pile.getCards().add((Card)build.getCards().pop());
			}
			
			
		}
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
