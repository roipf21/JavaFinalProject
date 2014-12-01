/**
   POKERPLAYER CLASS
*/

public class PokerPlayer
{
   private String name;
   private Card card1;
   private Card card2;
   private Hand hand;
   private int numbCards;
   private boolean isPlaying;
   private int pointsRemain;
   private int currBet;
   public static final int MOV_RAISE = 1;
   public static final int MOV_CALL = 2;
   public static final int MOV_CHECK = 3;
   public static final int MOV_PASS = 4;
   private int movement;
   private boolean movDone;
   private int raise;
   
   
   /**
      Constructor for the PokerPlayer class
      @param playerName The players name
      @param startPoints The starting points
   */
   public PokerPlayer(String playerName, int startPoints)
   {
      pointsRemain = startPoints;
      name = playerName;
      hand = new Hand();
      numbCards = 0;
      movement = 0;
      movDone = false;
      raise = 0;
   }
   /**
      Method to check which was the last button that the player pressed
      @return A integer that specifies the movement
   */
   public int getMovement()
   {
      return movement;
   }
   /**
      Method to set the movement done and that the movement was done
      @param mov The int that corresponds to the movement done
   */
   public void setMovement(int mov)
   {
      switch(mov)
      {
         case MOV_RAISE:
            movement = MOV_RAISE;
            movDone = true;
            break;
         case MOV_CALL:
            movement = MOV_CALL;
            movDone = true;
            break;
         case MOV_CHECK:
            movement = MOV_CHECK;
            movDone = true;
            break;
         case MOV_PASS:
            movement = MOV_PASS;
            movDone = true;
            break;
         default:
            movement = 0;
            movDone = false;
            break;
      }   
   }
   /**
      Method that checks if a movement has been done
      @return True if a movement has been done
   */
   public boolean isMovDone()
   {
      return movDone;
   }
   /**
      Method to reset the boolean movDone to false and the movement to 0
   */
   public void resetMov()
   {
      movement = 0;
      movDone = false;
   }
   /**
      Method to set the raise
      @param amount The amount raised
   */
   public void setRaise(int amount)
   {
      raise = amount;
   }
   /**
      Method to get the raise
      @return The raise
   */
   public int getRaise()
   {
      return raise;
   }
   /**
      Method to add a card to hand
      @param card The card to add
   */
   public void addCardtoHand(Card card)
   {
      hand.addCard(card);
      numbCards++;
   }
   /**
      Method to get the current hand
      @return The current hand
   */
   public Hand getHand()
   {
      return hand;
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
      Method to clear the players hand
   */
   public void clearHand()
   {
      hand.clearHand();
   }
   /**
      Method that returns the desired card
      @param index The number of the desired card
      @return The desired card
   */
   public Card getCard(int index)
   {
      Card[] cards = hand.getHand();
      return cards[index-1];
   }
   /**
      Method that accepts a number of points to bet as argument, substracts the amount from the 
      total and returns the current bet.
      @param points The points to bet
   */
   public void bet(int points)
   {
      pointsRemain = pointsRemain - points;
      currBet = currBet + points;          
   }
   /**
      Small blind method.
      @param small The small blind
   */
   public void smallBlind(int small) 
   {
      pointsRemain = pointsRemain - small;
      currBet = currBet + small;
   }
   /**
      Big blind method
      @param big The big blind
   */
   public void bigBlind(int big) 
   {
      pointsRemain = pointsRemain - big;
      currBet = currBet + big;
   }
   /**
      Method to get the current bet
      @return The current bet
   */
   public int getCurrBet()
   {
      return currBet;
   }
   /**
      Method to add points to the total if player wons the hand
      @param pGained The amount of points gained
   */
   public void roundWon(int pGained)
   {
      pointsRemain = pointsRemain + pGained;
   }
   
   /**
      Method to get the amount of points remaining
      @return The amount of points remaining
   */
   public int getPRemain()
   {
      return pointsRemain;
   }
   
   /**
      Method that sets if a player is playing or not
      @param is True if is playing, False if it is not
   */
   public void setIsPlaying(boolean is)
   {
      isPlaying = is;
   }
   /**
      Method to get if a player is playing
      @return True if it is playing, False if it is not
   */
   public boolean getIsPlaying()
   {
      return isPlaying;
   }
   /**
      Method to return the name of the player
      @return The name of the player
   */
   public String getName()
   {
      return name;
   }
   /**
      Method to reset the bets of the player
   */
   public void resetBets()
   {
      currBet = 0;
      raise = 0;
   }
}