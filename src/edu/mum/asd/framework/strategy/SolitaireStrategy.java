package edu.mum.asd.framework.strategy;

import edu.mum.asd.framework.Card;
import edu.mum.asd.framework.CardPile;
import edu.mum.asd.framework.DeckPile;
import edu.mum.asd.framework.DiscardPile;
import edu.mum.asd.framework.GameBoard;
import edu.mum.asd.framework.SuitPile;
import edu.mum.asd.framework.TablePile;
import edu.mum.asd.framework.iterator.ListIterator;

public class SolitaireStrategy implements IGameStrategy{
	
	private GameBoard gameBoard;
	
	public SolitaireStrategy() {
	}
	
	public void playCard(GameBoard gameBoard, double x, double y) {
		this.gameBoard = gameBoard;
		for (CardPile pile : gameBoard.getPiles())
			if (pile.includes(x, y)) {
				if(pile.getCards().empty() && pile instanceof DeckPile) {
					
					ListIterator iterator = gameBoard.getDiscard().getCards().iterator();

					// build reversed order list
					while (!iterator.atEnd()) {
						Card card = (Card) iterator.current();
						card.flip();
						pile.addCard(card);
						iterator.next();
					}
					((DiscardPile)gameBoard.getDiscard()).clear();
				}else if(pile instanceof DeckPile){
					Card card = (Card)pile.getCards().pop();
					card.flip();
					gameBoard.getDiscard().addCard(card);
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
			if (gameBoard.getSuits().get(i).canTake(topCard)) {
				gameBoard.getSuits().get(i).addCard((Card)pile.getCards().pop());
				return;
			}

		// then check the TablePile's
		
		for (int i = 0; i < TablePile.numberPiles; i++)
			if (gameBoard.getTablePiles().get(i).canTake(topCard)) {
				gameBoard.getTablePiles().get(i).addCard((Card)pile.getCards().pop());

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
				if (gameBoard.getSuits().get(i).canTake(topCard)) {
					gameBoard.getSuits().get(i).addCard((Card)pile.getCards().pop());
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
					if ( gameBoard.getTablePiles().get(i).canTake(topCard)) {
						gameBoard.getTablePiles().get(i).addCard((Card) pile.getCards().pop());
						return;
					}
			} else // we got ourselves a build to play
			{
				topCard = build.top();

				// see if any other table pile can take this build
				for (int i = 0; i < TablePile.numberPiles; i++)
					if (gameBoard.getTablePiles().get(i).canTake(topCard)) {
						while (!build.getCards().empty())
							gameBoard.getTablePiles().get(i).addCard((Card)build.getCards().pop());

						return;
					}

				// can't play the build?
				// then we must restore our pile
				while (!build.getCards().empty())
					pile.getCards().add((Card)build.getCards().pop());
			}
			
			
		}
	}
}
