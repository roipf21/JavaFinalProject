/**
   DECK CLASS
*/

import java.util.Random;
public class Deck 
{
   // Number of cards in a deck
   public final static int CARDS_IN_DECK = 52;

   //The deck of Cards
   private Card [] deck;
   //Current number of Cards
   private int ct;
   
   /**
      Constructor for a regular 52-card deck in order.
   */
   public Deck()
   {
      freshDeck();
   }
   /**
      Method to create a new collection of 52 cards, in order.
   */
   public void freshDeck()
   {
      deck = new Card[CARDS_IN_DECK];
      for (int r = Card.ACE; r<=Card.KING;r++)
      {
         for (int s=Card.SPADES;s<=Card.DIAMONDS;s++)
         {
            deck[ct]=new Card(s,r);
            ct = ct + 1;
         }
      }
   }
   /** 
      Method that simulates the deal of a card. Removes and return the top Card.
      @return The Card that was top on the Deck
   */
   public Card dealCard()
   {
      ct--;
      return deck[ct];
   }
   /** 
      Method to return the current number of Cards in the Deck
      @return The number of Cards in the Deck
   */
   public int cardsRemaining()
   {  
      return ct;
   }
   /** 
      Randomize the order of the Cards in the Deck
   */
   public void shuffle()
   {
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < ct; i++)
      {
         randNum = r.nextInt(ct);
         temp = deck[i];
         deck[i]=deck[randNum];
         deck[randNum]=temp;
      }
   }
   /** 
      Method to determine if the Deck is empty
      @return True if there are no more cards.
   */
   public boolean isEmpty()
   {
      return (cardsRemaining() == 0);
   }

   public static void main(String [] args) 
   {
      Deck deck = new Deck();
      deck.shuffle();
      int i = 0;
      while (!(deck.isEmpty()))
         System.out.println(i++ + " : " + deck.dealCard().toString());
      deck.freshDeck();
      System.out.println("************");
      i = 0;
      while (!(deck.isEmpty()))
         System.out.println(i++ + " : " + deck.dealCard().toString());

   }
}