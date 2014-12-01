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
   private final int ACE_POKERVALUE = 14;
   
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
      Method that returns the value of the cards in the holdem texas poker game
      @return The value of the card in the game
   */
   public int getPokerValue()
   {  
      int pokerValue;
      switch (rank)
      {
         case ACE:
            pokerValue = ACE_POKERVALUE;
            break;
         case 2:
            pokerValue = 2;
            break;
         case 3:
            pokerValue = 3;
            break;
         case 4:
            pokerValue = 4;
            break;
         case 5:
            pokerValue = 5;
            break;
         case 6:
            pokerValue = 6;
            break;
         case 7:
            pokerValue = 7;
            break;
         case 8:
            pokerValue = 8;
            break;
         case 9:
            pokerValue = 9;
            break;
         case 10:
            pokerValue = 10;
            break;
         case JACK:
            pokerValue = JACK;
            break;
         case QUEEN:
            pokerValue = QUEEN;
            break;
         case KING:
            pokerValue = KING;
            break;
         default:
            pokerValue = 0;
            break;
      }
      return pokerValue;
   }
   /**
      Method that returns the name of the image file of the card
      @return A string with the name of the image file
   */
   public String getImage()
   {
      String rankCard;
      switch (rank)
      {
         case ACE:
            rankCard = "ace";
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
            rankCard = "jack";
            break;
         case QUEEN:
            rankCard = "queen";
            break;
         case KING:
            rankCard = "king";
            break;
         default:
            rankCard = "NO RANK";
            break;
      }
      String suitCard;
      switch (suit)
      {
         case SPADES:
            suitCard = "s";
            break;
         case CLUBS:
            suitCard = "c";
            break;
         case HEARTS:
            suitCard = "h";
            break;
         case DIAMONDS:
            suitCard = "d";
            break;
         default:
            suitCard = "NO SUIT";
            break;
      }
      
      String image;
      image = (rankCard + suitCard + ".jpg");
      
      return image;
   }
   /**
      Method that checks if the 2 cards are the same
      @param c The card to compare with
      @return True if its the same card, false if it is not.
   */
   public boolean isSameCard(Card c)
   {
      boolean status;
      if((this.rank == c.rank) && (this.suit == c.suit))
      {
         status = true;
      }
      else
      {
         status = false;
      }
      return status;
   }
   /**
      Method that checks if two cards are equal or not
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
   /**
      Method that compares two cards
      @param card An object of the Card class (Second card to compare)
      @return -1 if the first is lower, 1 if its greater, 0 if they are equal.
   */
   public int compareTo(Card card) 
   {
      if (this.getPokerValue() < card.getPokerValue()) 
      {
         return -1;
      } 
      else if (this.getPokerValue() > card.getPokerValue()) 
      {
         return 1;
      } 
      else 
      {
         return 0;
      }
   }
}