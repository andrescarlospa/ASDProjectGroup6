package edu.mum.asd.framework.game;


////////////////////////////
//Defines a SuitPile class
////////////////////////////
class SuitPile extends CardPile {
	SuitPile(int x, int y) {
		super(x, y);
	}

	public boolean canTake(Card aCard) {
		if (empty())
			return aCard.isAce();

		Card topCard = top();
		return (aCard.suit() == topCard.suit()) && (aCard.rank() == 1 + topCard.rank());
	}

	public void select(int tx, int ty) {
		if (empty())
			return;

		Card topCard = top();

		// check the TablePile's
		for (int i = 0; i < Solitaire.no_table_piles; i++)
			if (Solitaire.tableau[i].canTake(topCard)) {
				Solitaire.tableau[i].addCard(pop());
				return;
			}
	}
}