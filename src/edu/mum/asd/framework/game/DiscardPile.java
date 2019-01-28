package edu.mum.asd.framework.game;

///////////////////////////////
//Defines a DiscardPile class
///////////////////////////////
class DiscardPile extends CardPile {
	DiscardPile(int x, int y) {
		super(x, y);
	}

	public void addCard(Card aCard) {
		if (!aCard.faceUp())
			aCard.flip();

		super.addCard(aCard);
	}

	public void select(int tx, int ty) {
		if (empty())
			return;

		Card topCard = top();

		// check the SuitPile's first
		for (int i = 0; i < Solitaire.no_suit_piles; i++)
			if (Solitaire.suitPile[i].canTake(topCard)) {
				Solitaire.suitPile[i].addCard(pop());
				return;
			}

		// then check the TablePile's
		for (int i = 0; i < Solitaire.no_table_piles; i++)
			if (Solitaire.tableau[i].canTake(topCard)) {
				Solitaire.tableau[i].addCard(pop());
				return;
			}
	}
}