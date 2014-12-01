/*
   HAND CLASS
*/

public class Hand
{
   /*
      Fields of the Hand class
   */
   private static final int MAX_CARDS = 7;
   private Card[] cards;
   private int cardsInHand = 0;
   /**
      No-arg Constructor for the Hand class
   */
   public Hand()
   {
      cards = new Card[MAX_CARDS];
   }
   /**
      Method that returns the number of cards in the hand. The Size.
      @return The number of cards in the hand.
   */
   public int size() 
   {
      return cardsInHand;
   }
   /**
      Method that adds a card to the hand
      @param card The card to add to the hand
   */
   public void addCard(Card card) 
   {
      cards[cardsInHand] = card;
      cardsInHand++;
   }
   /**
      Method to clear the hand
   */
   public void clearHand()
   {
      for(int i=0; i<cardsInHand;i++)
      {
         cards[i] = null;
      }
      cardsInHand = 0;
      
   }
   /**
      Method to sort the hand
   */
   public void handSort()
   {
      int startScan, index, minIndex;
      Card minCard;
      
      for (startScan = 0; startScan < (cardsInHand-1); startScan++)
      {
         minIndex = startScan;
         minCard = cards[startScan];
         for (index = startScan + 1; index < cardsInHand; index++)
         {
            if (cards[index].compareTo(minCard) < 0)
            {
               minCard = cards[index];
               minIndex = index;
            }
         }
         cards[minIndex] = cards[startScan];
         cards[startScan] = minCard;
      }
   }
   /**
      Method to get the cards in the hand
      @return An array of cards with the cards in hand
   */
   public Card[] getHand()
   {
      return cards;
   }
   /**
      Method to get the cards in the hand already sorted in ascent order
      @return The array of cards in hand already sorted.
   */
   public Card[] getSortedHand()
   {
      handSort();
      return cards;
   }
   /**
      Method that returns a String with the cards in the hand.
      @return The String with the cards in the hand.
   */
   public String toString()
   {
      String handString = null;
      for (int i = 0; i < cardsInHand; i++) 
      {
         if(handString == null)
         {
            handString = cards[i].toString();
         }
         else
         {
            handString = handString + "\n" + cards[i].toString();   
         }
      }
      return handString;
   }


}