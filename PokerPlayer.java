

public class PokerPlayer
{
   private Card card1;
   private Card card2;
   private boolean isPlaying;
   private int pointsRemain;
   private int currBet;
   
   
   /**
   */
   public PokerPlayer(int startPoints)
   {
      pointsRemain = startPoints;
   }
   
   /**
      Method to draw the first card. When we call the method it draws the card and references it 
      with the field card1
      @param deck Deck from where we are going to draw the card
   */
   public void draw1(Deck deck)
   {
      card1 = deck.dealCard();
   }
   /**
      Method to draw the second card. When we call the method it draws the card and references it 
      with the field card2
      @param deck Deck from where we are going to draw the card
   */
   public void draw2(Deck deck)
   {
      card2 = deck.dealCard();
   }
   /**
      Method to set the first card.
      @param firstCard The first card 
   */
   public void setCard1(Card firstCard)
   {
      card1 = firstCard;
   }
   /**
      Method to set the first card.
      @param secondCard The second card 
   */
   public void setCard2(Card secondCard)
   {
      card2 = secondCard;
   }
   
   
   
   /**
      Method that returns the first card
      @return The first card
   */
   public Card getCard1()
   {
      return card1;
   }
   /**
      Method that returns the second card
      @return The second card
   */
   public Card getCard2()
   {
      return card2;
   }
   
   /**
      Method that accepts a number of points to bet as argument, substracts the amount from the 
      total and returns the current bet.
      @param points The points to bet
   */
   public int bet(int points)
   {
      pointsRemain = pointsRemain - points;
      currBet = currBet + points;          //where to initialize the currbet???
      return currBet;
   }
   /**
   */
   public int getCurrBet()
   {
      return currBet;
   }
   
   /**
   */
   public int allIn()
   {
      return pointsRemain;
   }
   
   /**
   */
   public void roundWon(int pGained)
   {
      pointsRemain = pointsRemain + pGained;
   }
   
   /**
   */
   public int getPRemain()
   {
      return pointsRemain;
   }
   
   /**
   */
   public void setIsPlaying(boolean is)
   {
      isPlaying = is;
   }
   /**
   */
   public boolean getIsPlaying()
   {
      return isPlaying;
   }
   /**
   */
   
}