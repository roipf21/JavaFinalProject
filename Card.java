/* 
Name: Rodrigo Penide
Subject: CS110 Assignment #5
CARD CLASS
*/

/**
   CARD CLASS
*/
public class Card
{
   //PUBLIC CONSTANTS AND PRIVATE VARIABLES
   public static final int ACE = 1;
   public static final int JACK = 11;
   public static final int QUEEN = 12;
   public static final int KING = 13;
   public static final int SPADES = 1;
   public static final int CLUBS = 2;
   public static final int HEARTS = 3;
   public static final int DIAMONDS = 4;
   private int suit;
   private int rank;
   
   /**
      Constructor for Card class
      @param suit The suit(integer) of the card (spades(1), clubs(2), hearts(3), diamonds(4))
      @param rank The rank(integer) of the card (ACE(1), 2, 3...)
   */
   public Card(int suit, int rank) 
   {
      this.suit = suit;
      this.rank = rank;
   }
   
   /**
      Method to get the suit of the card
      @return An integer that indicates the suit of the card
   */
   public int getSuit()
   {
      return suit;
   }
   
   /**
      Method to get the rank of the card
      @return An integer that indicates the suit of the card
   */
   public int getRank()
   {
      return rank;
   }
   
   /**
      Merhod to get the description of the card
      @return Description of the card
   */
   public String toString()
   {
      String rankCard;
      switch (rank)
      {
         case ACE:
            rankCard = "ACE";
            break;
         case 2:
            rankCard = "2";
            break;
         case 3:
            rankCard = "3";
            break;
         case 4:
            rankCard = "4";
            break;
         case 5:
            rankCard = "5";
            break;
         case 6:
            rankCard = "6";
            break;
         case 7:
            rankCard = "7";
            break;
         case 8:
            rankCard = "8";
            break;
         case 9:
            rankCard = "9";
            break;
         case 10:
            rankCard = "10";
            break;
         case JACK:
            rankCard = "JACK";
            break;
         case QUEEN:
            rankCard = "QUEEN";
            break;
         case KING:
            rankCard = "KING";
            break;
         default:
            rankCard = "NO RANK";
            break;
      }
      String suitCard;
      switch (suit)
      {
         case SPADES:
            suitCard = "SPADES";
            break;
         case CLUBS:
            suitCard = "CLUBS";
            break;
         case HEARTS:
            suitCard = "HEARTS";
            break;
         case DIAMONDS:
            suitCard = "DIAMONDS";
            break;
         default:
            suitCard = "NO SUIT";
            break;
      }
      
      String cardDesc;
      cardDesc = (rankCard + " of " + suitCard);
      
      return cardDesc;
   }
   
   /**
      Method that compares to cards
      @param c An object of the Card class (Second card to compare)
      @return True if its the same card. False if its not the same card.
   */
   public boolean equals(Card c)
   {
      boolean status;
      if(rank == c.rank)
      {
         status = true;
      }
      else
      {
         status = false;
      }
      return status;
   }
}