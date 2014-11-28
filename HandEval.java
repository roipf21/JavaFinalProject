/*
   HANDEVAL CLASS
*/

import java.util.ArrayList;//necessary to use ArrayLists

public class HandEval
{
   //Here we are going to store our hand
   private Card[] cards;
   //constants needed for the methods
   private final int N_CARDS = 7;
   private final int MAX_PAIRS = 2;
   private final int N_SUITS = 4;
   private final int N_RANKS = 14;
   private final int RANKINGS = 6;
   //variable that holds the description of our hand
   private String description;
   //values for the different rankings of possible hands
   private final int ROYAL_FLUSH = 9;
   private final int STRAIGHT_FLUSH = 8;
   private final int FOUR_KIND = 7;
   private final int FULL_HOUSE = 6;
   private final int FLUSH = 5;
   private final int STRAIGHT = 4;
   private final int THREE_KIND = 3;
   private final int DOUBLE_PAIR = 2;
   private final int ONE_PAIR = 1;
   private final int HIGH_CARD = 0;
   //conditions and variables of our hand
   private boolean hasQuad;
   private int quadRank;
   private boolean hasTriple;
   private int tripleRank;
   private boolean hasPair;
   private ArrayList<Integer> pairsList; //List of pairs
   private boolean hasDoublePair;
   private boolean hasFull;
   private boolean hasFlush;
   private int maxFlushValue;
   private int flushType;
   private int[] suitDistribution; //the distribution of suits in our hand
   private int[] rankDistribution; //the distribution of ranks in our hand
   private int[] flushRanksDist;   //the distribution of ranks in our hand for the cards with same suit when we have a flush
   private boolean hasStraight;
   private int straightValue;
   private boolean hasFlushStraight;
   private int flushStraightValue;
   private ArrayList<Integer> highCardsList;  //List of high cards
   //These arrays will be used to calculate the total value of the hand so we can compare them
   private int totalValue;
   private int[] rankingValue = new int[RANKINGS];
   private final int[] FACTORS = {371293, 28561, 2197, 169, 13, 1};
   
   
   /**
      Constructor for the Hand evaluator Class
      @param hand The hand to evaluate
   */
   public HandEval(Hand hand) 
   {
      cards = hand.getSortedHand(); 
      //look for distributions and special conditions of our hand
      distributions();
      findRepeated();
      findFull();
      findFlush();
      findStraight();
      findFlushStraight();
      //We get our hand description and the rankingValue vector covered for this hand
      if (isStraightFlush()){description = "Straight Flush";}
      else if (isFourKind()){description = "Four of a kind";}
      else if (isFullHouse()){description = "Full House";}
      else if (isFlush()){description = "Flush";}
      else if (isStraight()){description = "Straight";}
      else if (isThreeKind()){description = "Three of a kind";}
      else if (isDoublePair()){description = "Double Pair";}
      else if (isOnePair()){description = "One Pair";}
      else
      {
         highCards();
         description = "High Card";
      }
      //Once we have the rankingValue vector covered we multiply each value times a factor
      //The sum of them gives us the value of the hand so we can compare them
      for (int i = 0; i < RANKINGS; i++) 
      {
         totalValue += rankingValue[i] * FACTORS[i];
      }   
   }
   /**
      Method to find if there are pairs, three of a kind, four of a kind and their ranks also takes the high cards
   */
   public void findRepeated()
   {
      int temp;
      highCardsList = new ArrayList<Integer>();
      pairsList = new ArrayList<Integer>();
      for (int i = 1; i <= N_RANKS; i++)
      {
         if( rankDistribution[i] == 4 )
         {
            hasQuad = true;
            quadRank = i+1;
         }
         else if (rankDistribution[i] == 3)
         {
            if ( !hasTriple )
            {   
               hasTriple = true;
               tripleRank = i+1;
            }
            else
            {
               pairsList.add(tripleRank);
               hasPair = true;
               tripleRank = i+1;
            }   
         }
         else if (rankDistribution[i] == 2)
         {
            if (!hasPair)
            {
               hasPair = true;
               pairsList.add(i+1);
            }
            else if ( (hasPair)&&(pairsList.size()<2) )
            {
               pairsList.add(i+1);
               hasDoublePair = true;
            }
            else
            {
               temp = pairsList.get(0);
               if ((!highCardsList.isEmpty())&&(temp<highCardsList.get(0)))
               {
                  highCardsList.add(0,temp);
               }
               else
               {
                  highCardsList.add(temp);
               }
               pairsList.remove(0);
               pairsList.add(i+1);
            }
         }
         else
         {
            highCardsList.add(i+1);
         }
      }
   }
   
   /**
      Method to find which ranks and suits are repeated and how many times
   */
   public void distributions()
   {
      //SUIT DISTRIBUTION
      suitDistribution = new int[N_SUITS];
      for (Card card : cards)
      {
         if (card.getSuit()==Card.SPADES)
         {
            suitDistribution[Card.SPADES-1]++;
         }
         else if (card.getSuit()==Card.CLUBS)
         {
            suitDistribution[Card.CLUBS-1]++;
         }
         else if (card.getSuit()==Card.HEARTS)
         {
            suitDistribution[Card.HEARTS-1]++;
         }
         else
         {
            suitDistribution[Card.DIAMONDS-1]++;
         }
      }
      //RANK DISTRIBUTION
      rankDistribution = new int[N_RANKS];
      for (Card card : cards)
      {
         //each rank stored in its corresponding position (example "2 of hearts" in rankDistribution[1] that is the 2nd position)
         rankDistribution[card.getPokerValue()-1]++;
         //Aces are going to be in 2 positions to cover the 2 possible Straights
         if (card.getRank()==Card.ACE)
         {
            rankDistribution[Card.ACE-1]++;
         }
      }
   }
   /**
      Method to find a possible full
   */
   public void findFull()
   {
      if (hasTriple && hasPair)
      {
         hasFull = true;
      }
   }
   /**
      Method to find a possible flush
   */
   public void findFlush()           
   {
      maxFlushValue = 0;
      for (int i = 0 ; i < N_SUITS ; i++)
      {
         if ( suitDistribution[i] >= 5 )
         {
            hasFlush = true;
            //flushValue   
            for (Card card : cards)
            {
               if ((card.getSuit() == (i+1)) && (card.getPokerValue()>maxFlushValue))
               {
                  maxFlushValue = card.getPokerValue();
               }
            }
            //flushType
            if (i == Card.SPADES-1)
            {
               flushType = Card.SPADES;
            }
            else if (i == Card.CLUBS-1)
            {
               flushType = Card.CLUBS;
            }
            else if (i == Card.HEARTS-1)
            {
               flushType = Card.HEARTS;
            }
            else
            {
               flushType = Card.DIAMONDS;
            }
         }
      }
   }
   /**
      Method to find a possible Straight
   */
   public void findStraight()
   {
      int cardsInStraight = 0;
      for (int i = 0; i < N_RANKS; i++)
      {
         if (rankDistribution[i] == 0)
         {
            cardsInStraight = 0;
         }
         else
         {
            cardsInStraight++;
         }
         if (cardsInStraight >= 5)
         {
            hasStraight = true;
            straightValue = i+1;
         }
      }
   }
   /**
      Method to find a possible Straight Flush
   */
   public void findFlushStraight()
   {
      if (hasFlush && hasStraight)
      {
         flushRanksDist = new int[N_RANKS];
         for (Card card : cards)
         {
            if ( card.getSuit() == flushType )
            {
               //each rank stored in its corresponding position (example "2 of hearts" in rankDistribution[1] that is the 2nd position)
               flushRanksDist[card.getPokerValue()-1]++;
               //Aces are going to be in 2 positions to cover the 2 possible Straights
               if (card.getRank()==Card.ACE)
               {
                  flushRanksDist[Card.ACE-1]++;
               }
            }
         }
         int cardsInStraight = 0;
         for (int i = 0; i < N_RANKS; i++)
         {
            if (flushRanksDist[i] == 0)
            {
               cardsInStraight = 0;
            }
            else
            {
               cardsInStraight++;
            }
            if (cardsInStraight >= 5)
            {
               hasFlushStraight = true;
               flushStraightValue = i+1;
            }
         }
      }
   }
   /**
      Method that calculates the ranking values for a "One Pair"
      @return True if it is a One Pair Hand.
   */
   public boolean isOnePair()
   {
      if ((hasPair) && (!hasDoublePair))
      {
         rankingValue[0] = ONE_PAIR;
         rankingValue[1] = pairsList.get(0);
         rankingValue[2] = highCardsList.get(highCardsList.size()-1);
         rankingValue[3] = highCardsList.get(highCardsList.size()-2);
         rankingValue[4] = highCardsList.get(highCardsList.size()-3);
         return true;  
      }
      else 
      { 
         return false;
      }
   }
   /**
      Method that calculates the ranking values for a "Double Pair"
      @return True if it is a Double Pair Hand.
   */
   public boolean isDoublePair()
   {
      if (hasDoublePair)
      {
         rankingValue[0] = DOUBLE_PAIR;
         rankingValue[1] = pairsList.get(1);
         rankingValue[2] = pairsList.get(0);
         rankingValue[3] = highCardsList.get(highCardsList.size()-1);
         rankingValue[4] = highCardsList.get(highCardsList.size()-2);
         return true;
      }
      else 
      { 
         return false;
      }
   }
   /**
      Method that calculates the ranking values for a "Three of a kind"
      @return True if it is a Three of a kind Hand.
   */
   public boolean isThreeKind()
   {
      if (hasTriple && (!hasPair))
      {
         rankingValue[0] = THREE_KIND;
         rankingValue[1] = tripleRank;
         rankingValue[2] = highCardsList.get(highCardsList.size()-1);
         rankingValue[3] = highCardsList.get(highCardsList.size()-2);
         return true;
      }
      else 
      { 
         return false;
      }
   }
   /**
      Method that calculates the ranking values for a "Straight"
      @return True if it is a Straight Hand.
   */
   public boolean isStraight()
   {
      if (hasStraight)
      {
         rankingValue[0] = STRAIGHT;
         rankingValue[1] = straightValue;
         return true;
      }
      else 
      { 
         return false;
      }
   }
   /**
      Method that calculates the ranking values for a "Flush"
      @return True if it is a Flush Hand.
   */
   public boolean isFlush()
   {
      if (hasFlush)
      {
         rankingValue[0] = FLUSH;
         rankingValue[1] = maxFlushValue;
         return true;
      }
      else 
      { 
         return false;
      }
   }
   /**
      Method that calculates the ranking values for a "Full House"
      @return True if it is a Full House Hand.
   */
   public boolean isFullHouse()
   {
      if (hasFull)
      {
         rankingValue[0] = FULL_HOUSE;
         rankingValue[1] = tripleRank;
         rankingValue[2] = pairsList.get(pairsList.size()-1);
         return true;
      }
      else 
      { 
         return false;
      }
   }
   /**
      Method that calculates the ranking values for a "Four of a kind"
      @return True if it is a Four of a kind Hand.
   */
   public boolean isFourKind()
   {
      if (hasQuad)
      {
         rankingValue[0] = FOUR_KIND;
         rankingValue[1] = quadRank;
         if (hasTriple)
         {
            rankingValue[2] = tripleRank;
         }
         else if (hasDoublePair)
         {
            rankingValue[2] = pairsList.get(pairsList.size()-1);
         }
         else if (hasPair && (!hasDoublePair))
         {
            if (pairsList.get(pairsList.size()-1) > highCardsList.get(highCardsList.size()-1))
            {
               rankingValue[2] = pairsList.get(pairsList.size()-1);
            }
            else
            {
               rankingValue[2] = highCardsList.get(highCardsList.size()-1);
            }
         }
         else
         {
            rankingValue[2] = highCardsList.get(highCardsList.size()-1);
         }
         return true; 
      }
      else 
      { 
         return false;
      }
   }
   /**
      Method that calculates the ranking values for a "Straight Flush"
      @return True if it is a Straight Flush Hand.
   */
   public boolean isStraightFlush()
   {
      if (hasFlushStraight && (flushStraightValue==14))
      {
         rankingValue[0] = ROYAL_FLUSH;
         return true;
      }
      else if (hasFlushStraight && (flushStraightValue!=14))
      {  
         rankingValue[0] = STRAIGHT_FLUSH;
         rankingValue[1] = flushStraightValue;
         return true;
      }
      else 
      { 
         return false;
      } 
   }
   /**
      Method that calculates the ranking values for a "High Card"
   */
   public void highCards()
   {
      rankingValue[0] = HIGH_CARD;
      rankingValue[1] = highCardsList.get(highCardsList.size()-1);
      rankingValue[2] = highCardsList.get(highCardsList.size()-2);
      rankingValue[3] = highCardsList.get(highCardsList.size()-3);
      rankingValue[4] = highCardsList.get(highCardsList.size()-4);
      rankingValue[5] = highCardsList.get(highCardsList.size()-5);
   }
   /**
      Method that returns the total value of the hand
      @return The total value of the hand
   */
   public int getTotalValue()
   {
      return totalValue;
   }
   /**
      Method that compares two Hands
      @return -1 if lower, 0 if equal, 1 if higher
   */
   public int compareTo(HandEval otherHandEval)
   {
      if ((this.getTotalValue()) < (otherHandEval.getTotalValue()))
      {
         return -1;
      }
      else if ((this.getTotalValue()) == (otherHandEval.getTotalValue()))
      {
         return 0;
      }
      else
      {
         return 1;
      }
   }
}