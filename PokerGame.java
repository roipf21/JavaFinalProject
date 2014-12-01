import java.awt.Color;
import javax.swing.JOptionPane;     //necessary for the use of JOptionPane
import java.util.ArrayList;         //necessary for the use of ArrayLists
/**
   POKER GAME CLASS, RULES THE GAME AND BEHAVIOUR OF PARTS
*/
public class PokerGame
{
   private static final int MAX_RAISES = 3;
   private static final int FLOP_CARDS = 3;
   private static final int N_PLAYERS = 4;
   private static final int START_POINTS = 5000;
   private int bigBlind;
   private ArrayList<PokerPlayer> players;
   private ArrayList<PokerPlayer> activePlayers;
   private Deck deck;
   private ArrayList<Card> board;
   private int dealerPosition;
   private PokerPlayer dealer;
   private int actorPosition;
   private PokerPlayer actor;
   private int minBet;
   private int bet;
   private PokerPlayer lastBettor;
   private int raises;
   private ArrayList<GameWindow> windows;
   private int totalPot;
   //to handle allin
   private boolean playerinallin;
   private int allinpot;
   /**
      Constructor for the PokerGame class
      @param bigBlind The Big Blind
   */
   public PokerGame(int bigBlind) 
   {
       this.bigBlind = bigBlind;
       players = new ArrayList<PokerPlayer>();
       activePlayers = new ArrayList<PokerPlayer>();
       deck = new Deck();
       board = new ArrayList<Card>();
       windows = new ArrayList<GameWindow>();
       totalPot = 0;
   }
   /**
      Method for the Game core Loop
   */
   public void game()
   {  
      //CREATE THE PLAYERS
      String name;
      for (int i = 0; i < N_PLAYERS ; i++)
      {
         name = JOptionPane.showInputDialog("Name");
         players.add(new PokerPlayer(name, START_POINTS));
      }
      //CREATE THE WINDOWS
      for (int i = 0; i < players.size(); i++)
      {
         windows.add(new GameWindow(players.get(i),players));
      }
      //WAIT FOR LOCATE WINDOWS
      JOptionPane.showMessageDialog(null,"READY?");
      //GAME MAIN LOOP
      while (true) 
      {
         //NEXT ROUND
         JOptionPane.showMessageDialog(null,"NEXT ROUND");
         //starts next round
         int numbActivePlayers = 0;
         for (PokerPlayer pokerplayer : players) 
         {
            if (pokerplayer.getPRemain() >= bigBlind) 
            {
               numbActivePlayers++;
            }
         }
         if (numbActivePlayers > 1)           
         {
            playHand();
            for(int i = 0 ; i < windows.size() ; i++)
            {
               windows.get(i).resetBoardCards();
            }
         } 
         else 
         {
            break;
         }
      }
   }
   /**
      Method to process the play of one Hand
   */
   private void playHand() 
   {
      resetHand();
      // Small blind.
      if (activePlayers.size() > 2) 
      {
         rotateActor();
      }
      postSmallBlind();
        
      // Big blind.
      rotateActor();
      postBigBlind();
        
      // Pre-Flop.
      dealCards();
      doBettingRound();
        
      // Flop.
      if (activePlayers.size() > 1) 
      {
         bet = 0;
         dealFlop();
         if (playerinallin)
         {}
         else
         {
            doBettingRound();
         }
         // Turn.
         if (activePlayers.size() > 1) 
         {
            bet = 0;
            dealTurn();
            if (playerinallin)
            {}
            else
            {
               minBet = 2 * bigBlind;
               doBettingRound();
            }

            // River.
            if (activePlayers.size() > 1) 
            {
               bet = 0;
               dealRiver();
               if (playerinallin)
               {}
               else
               {
                  doBettingRound();
               }
               //DECIDE WHO WON THE GAME
               if (activePlayers.size() > 1) 
               {
                  bet = 0;
                  determineWinner();
               }
            }
         }
      }
   }
   /**
      Resets the game for a new hand.
   */
   private void resetHand() 
   {
      // Clear the board.
      board.clear();
      totalPot = 0;
      playerinallin = false;
      allinpot = 0;        
        
      // Determine the active players.
      activePlayers.clear();
      for (PokerPlayer pokerplayer : players) 
      {
         pokerplayer.clearHand();
         // Player must be able to afford the big blind.
         if (pokerplayer.getPRemain() >= bigBlind) 
         {
            activePlayers.add(pokerplayer);
            pokerplayer.setIsPlaying(true);
         }
      }
      /* 
         Dealer rotation
         Use of %: Modulus - Divides left hand operand by right hand operand and returns remainder
         with this I am able to return to the first member of the list after working with the last
         in the same loop. Do not need special loops these way.
      */
      dealerPosition = (dealerPosition + 1) % activePlayers.size();
      dealer = activePlayers.get(dealerPosition);

      // Shuffle the deck.
      deck.shuffle();

      // Determine the first player to act.
      actorPosition = dealerPosition;
      actor = activePlayers.get(actorPosition);
        
      // Set the initial bet to the big blind.
      minBet = bigBlind;
      bet = minBet;
   }
   /**
      Method to rotate the player that has to play   
   */
   private void rotateActor() 
   {
      int index = players.indexOf(actor);
      windows.get(index).actingStatusColor(Color.BLACK);
      /* 
         Actor rotation
         Use of %: Modulus - Divides left hand operand by right hand operand and returns remainder
         with this I am able to return to the first member of the list after working with the last
         in the same loop. Do not need special loops these way.
      */
      actorPosition = (actorPosition + 1) % activePlayers.size();
      actor = activePlayers.get(actorPosition);
      index = players.indexOf(actor);
      windows.get(index).actingStatusColor(Color.RED);
   }
   /**
      Method to make the player who is the small blind to post the small blind
   */
   private void postSmallBlind() 
   {
      int smallB = bigBlind / 2;
      actor.smallBlind(smallB);
      totalPot = totalPot + smallB;
      for(int i = 0 ; i < windows.size() ; i++)
      {
         windows.get(i).actualize(totalPot, minBet);
      }
   }
   /**
      Method to make the player who is the big blind to post the big blind
   */
   private void postBigBlind() 
   {
      actor.bigBlind(bigBlind);
      totalPot = totalPot + bigBlind;
      for(int i = 0 ; i < windows.size() ; i++)
      {
         windows.get(i).actualize(totalPot, minBet);
      }
   }
   /**
      Method to Deal Cards to the players
   */
   private void dealCards()
   {
      Card[] card1 = new Card[activePlayers.size()];
      Card[] card2 = new Card[activePlayers.size()];
      for (int j = 0; j < activePlayers.size(); j++)
      {
         card1[j] = deck.dealCard();
      }
      for (int j = 0; j < activePlayers.size(); j++)
      {
         card2[j] = deck.dealCard();
      }
      for (int i = 0; i < windows.size(); i++)
      {  
         activePlayers.get(i).addCardtoHand(card1[i]);
         activePlayers.get(i).addCardtoHand(card2[i]);
         windows.get(i).setHand();
      }
   }
   /**
      Method to Deal the Flop
   */
   private void dealFlop()
   {
      //Burn a card
      deck.dealCard();
      //Holds the flop
      Card[] flop = new Card[FLOP_CARDS];
      for (int i = 0; i < FLOP_CARDS; i++)
      {
         flop[i] = deck.dealCard();
         board.add(flop[i]); //adds the flop to the board arraylist
         for(int j = 0; j < activePlayers.size(); j++)
         {
            activePlayers.get(j).addCardtoHand(flop[i]);
         }
      }
      //sets the images in the windows
      for (int i = 0; i < windows.size(); i++)
      {
         windows.get(i).setFlop(flop[0].getImage(),flop[1].getImage(),flop[2].getImage());
      }
   }
   /**
      Method to deal the Turn
   */
   private void dealTurn()
   {
      //Burn a card
      deck.dealCard();
      //Holds the Turn
      Card turn = deck.dealCard();
      board.add(turn); //adds the turn to the board arraylist
      for(int j = 0; j < activePlayers.size(); j++)
      {
         activePlayers.get(j).addCardtoHand(turn);
      }
      //sets the images in the windows
      for (int i = 0; i < windows.size(); i++)
      {
         windows.get(i).setTurn(turn.getImage());
      }
   }
   /**
      Method to deal the River
   */
   private void dealRiver()
   {
      //Burn a card
      deck.dealCard();
      //Holds the Turn
      Card river = deck.dealCard();
      board.add(river); //adds the river to the board arraylist
      for(int j = 0; j < activePlayers.size(); j++)
      {
         activePlayers.get(j).addCardtoHand(river);
      }
      //sets the images in the windows
      for (int i = 0; i < windows.size(); i++)
      {
         windows.get(i).setRiver(river.getImage());
      }
   }
   /**
      A Betting round
   */
   private void doBettingRound() 
   {
      // Determine the number of active players.
      int playersToAct = activePlayers.size();
      // Determine the initial player and bet size.
      if (board.size() == 0) 
      {
         //Pre-Flop
         bet = bigBlind;
      } 
      else 
      {
         //Any other betting round
         actorPosition = dealerPosition;
         bet = 0;
      }
        
      if (playersToAct == 2) 
      {
         // Player who is not the dealer starts.
         actorPosition = dealerPosition;
      }
      
      lastBettor = null;
      raises = 0;
        
      while (playersToAct > 0) 
      {
         rotateActor();
         
         if(playerinallin) //when in all-in only call/fold(pass) is allowed.
         {
            while (!actor.isMovDone()){}
            playersToAct--;
            if (actor.getMovement() == PokerPlayer.MOV_CHECK)
            {
               actor.clearHand();
               activePlayers.remove(actor);
               actor.setIsPlaying(false);
               actorPosition--;
               if (activePlayers.size() == 1)
               {
                  //the player has won
                  PokerPlayer winner = activePlayers.get(0);
                  winner.roundWon(totalPot);
                  playersToAct = 0;
                  JOptionPane.showMessageDialog(null,"Player: " + winner.getName() + " won");
               }
            }
            else if (actor.getMovement() == PokerPlayer.MOV_CALL)
            {
               if(actor.getPRemain() < allinpot - actor.getCurrBet())
               {
                  actor.clearHand();
                  activePlayers.remove(actor);
                  actor.setIsPlaying(false);
                  actorPosition--;
                  if (activePlayers.size() == 1)
                  {
                     //the player has won
                     PokerPlayer winner = activePlayers.get(0);
                     winner.roundWon(totalPot);
                     playersToAct = 0;
                     JOptionPane.showMessageDialog(null,"Player: " + winner.getName() + " won");
                  }   
               }
               else
               {
                  int difference = allinpot - actor.getCurrBet();
                  totalPot = totalPot + difference;
                  actor.bet(allinpot - actor.getCurrBet());
               }               
                           
            }
            else if (actor.getMovement() == PokerPlayer.MOV_RAISE)
            {
               actor.clearHand();
               activePlayers.remove(actor);
               actor.setIsPlaying(false);
               actorPosition--;
               if (activePlayers.size() == 1)
               {
                  //the player has won
                  PokerPlayer winner = activePlayers.get(0);
                  winner.roundWon(totalPot);
                  playersToAct = 0;
                  JOptionPane.showMessageDialog(null,"Player: " + winner.getName() + " won");
               }
            }
            else if (actor.getMovement() == PokerPlayer.MOV_PASS)
            {
               actor.clearHand();
               activePlayers.remove(actor);
               actor.setIsPlaying(false);
               actorPosition--;
               if (activePlayers.size() == 1)
               {
                  //the player has won
                  PokerPlayer winner = activePlayers.get(0);
                  winner.roundWon(totalPot);
                  playersToAct = 0;
                  JOptionPane.showMessageDialog(null,"Player: " + winner.getName() + " won");
               }
            }
            actor.resetMov();
            for(int i = 0 ; i < windows.size() ; i++)
            {
               windows.get(i).actualize(totalPot, minBet);
            }
         }
         else
         {
            while (!actor.isMovDone()){}
            playersToAct--;
            if (actor.getMovement() == PokerPlayer.MOV_CHECK)
            {
               if(bet > actor.getCurrBet())
               {
                  actor.clearHand();
                  activePlayers.remove(actor);
                  actor.setIsPlaying(false);
                  actorPosition--;
                  if (activePlayers.size() == 1)
                  {
                     //the player has won
                     PokerPlayer winner = activePlayers.get(0);
                     winner.roundWon(totalPot);
                     playersToAct = 0;
                     JOptionPane.showMessageDialog(null,"Player: " + winner.getName() + " won");
                  }
               }
               else
               {}
            }
            else if (actor.getMovement() == PokerPlayer.MOV_CALL)
            {
               int difference = bet - actor.getCurrBet();
               if (difference >= actor.getPRemain())
               {
                  difference = actor.getPRemain();
                  playerinallin = true;
                  actor.bet(difference);
                  allinpot = actor.getCurrBet();
                  totalPot = totalPot + difference;
                  playersToAct = activePlayers.size() - 1;
               }
               else //with this we ensure that if the user press CALL and his current bet is the same that the highest is like a CHECK
               {
                  actor.bet(difference);
                  totalPot = totalPot + difference;
               }
            }
            else if (actor.getMovement() == PokerPlayer.MOV_RAISE)
            {
               int raise = actor.getRaise();
               if (raise + (bet-actor.getCurrBet()) > actor.getPRemain())
               {
                  raise = actor.getPRemain();
                  playerinallin = true;
                  actor.bet(raise);
                  allinpot = actor.getCurrBet();
                  playersToAct = activePlayers.size() - 1;
                  totalPot = totalPot + raise;
               }
               else
               {
                  int difference = (bet-actor.getCurrBet());
                  actor.bet(raise + difference);
                  bet = bet + raise;
                  totalPot = totalPot + raise + difference;
                  minBet = raise;
               }
               lastBettor = actor;
               if (raises < MAX_RAISES || activePlayers.size() == 2) 
               { 
                  // All players get another turn.
                  playersToAct = activePlayers.size();
               } 
               else 
               {
                  // Max. number of raises reached; other players get one more turn.
                  playersToAct = activePlayers.size() - 1;
               }
            }
            else if (actor.getMovement() == PokerPlayer.MOV_PASS)
            {
               actor.clearHand();
               activePlayers.remove(actor);
               actor.setIsPlaying(false);
               actorPosition--;
               if (activePlayers.size() == 1)
               {
                  //the player has won
                  PokerPlayer winner = activePlayers.get(0);
                  winner.roundWon(totalPot);
                  playersToAct = 0;
                  JOptionPane.showMessageDialog(null,"Player: " + winner.getName() + " won");
               }
            }
            else 
            {}
            actor.resetMov();
            for(int i = 0 ; i < windows.size() ; i++)
            {
               windows.get(i).actualize(totalPot, minBet);
            }
         }   
      }
      // Reset player's bets.
      for (PokerPlayer player : activePlayers) 
      {
         player.resetBets();
         
         for(int i = 0 ; i < windows.size() ; i++)
         {
            windows.get(i).actualize(totalPot, minBet);
         }         
      } 
   }                   
   /**
      Method that determines who player won and gives the points to them
   */
   private void determineWinner()
   {
      ArrayList<PokerPlayer> checkifwon = new ArrayList<PokerPlayer>();
      for(int i = 0; i < activePlayers.size(); i++)
      {
         if(activePlayers.get(i).getIsPlaying())
         {
            checkifwon.add(activePlayers.get(i));
         }
      }
      ArrayList<HandEval> handevals = new ArrayList<HandEval>();
      for(int i = 0; i < checkifwon.size(); i++)
      {
         handevals.add(new HandEval(checkifwon.get(i).getHand()));
      }
      HandEval highest = handevals.get(0);
      int highestIndex = 0;
      for(int i = 1; i < handevals.size(); i++)
      {
         if(handevals.get(i).getTotalValue() > highest.getTotalValue())
         {
            highest = handevals.get(i);
            highestIndex = i;
         }
      }
      ArrayList<HandEval> otherWinners = new ArrayList<HandEval>();
      ArrayList<Integer> otherWinnersIndexes = new ArrayList<Integer>();
      for(int i = 1; i < handevals.size(); i++)
      {
         if((handevals.get(i).getTotalValue() == highest.getTotalValue()) && (handevals.get(i).equals(highest)))
         {
            otherWinners.add(handevals.get(i));
            otherWinnersIndexes.add(i);
         }
      }
      if(otherWinners.size()>=1)
      {  
         checkifwon.get(highestIndex).roundWon(totalPot/(otherWinners.size()+1));
         for(int i = 0; i < otherWinners.size(); i++)
         {
            checkifwon.get(otherWinnersIndexes.get(i)).roundWon(totalPot/(otherWinners.size()+1));
            JOptionPane.showMessageDialog(null,"Player: " + checkifwon.get(otherWinnersIndexes.get(i)).getName() + " won.");
         }
      }
      else
      {
         checkifwon.get(highestIndex).roundWon(totalPot);
         JOptionPane.showMessageDialog(null,"Player: " + checkifwon.get(highestIndex).getName() + " won");
      }
   }
   /**
      Method to get the minBet
      @return The min bet
   */
   public int getMinBet()
   {
      return minBet;
   }
   /**
      Method to get the minBet
      @return The min bet
   */
   public int getTotalPot()
   {
      return totalPot;
   }
}
