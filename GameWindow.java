/**
   GAME WINDOW CLASS
   
   This class creates the board for the player "x".
   
*/



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;



public class GameWindow extends JFrame
{
   private CentralPanel centralpanel;
   private LeftPanel leftpanel;
   private RightPanel rightpanel;
   private UpPanel uppanel;
   private static ArrayList<PokerPlayer> players;
   private final int N_PLAYERS = 4;
   private PokerPlayer player;
   //for the SOUTHERN PANNEL
   private JButton raise;
   private JButton call;
   private JButton check;
   private JButton pass;
   private JPanel downpanel;
   /**
      Constructor for the GameWindow Class
      @param pokerplayer The player that corresponds to this board
      @param pokerplayers All the players that are playing
   */
   public GameWindow(PokerPlayer pokerplayer, ArrayList<PokerPlayer> pokerplayers)
   {
      //tittle
      setTitle("Poker Game. " + pokerplayer.getName());
      //hold the arguments
      player = pokerplayer;
      players = pokerplayers;
      //close op
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //creating instances of the different parts of the board
      centralpanel = new CentralPanel();
      leftpanel = new LeftPanel(player);
      rightpanel = new RightPanel(players);     
      uppanel = new UpPanel();
      //calling a method that creates the panel with the buttons
      buildDownPanel();
      //layout      
      setLayout(new BorderLayout());
      add(centralpanel, BorderLayout.CENTER);
      add(leftpanel, BorderLayout.WEST);
      add(rightpanel, BorderLayout.EAST);
      add(uppanel, BorderLayout.NORTH);
      add(downpanel, BorderLayout.SOUTH);
      
      pack();
      setVisible(true);
   }
   /**
      Method to create the panel with the buttons
   */
   private void buildDownPanel()
   {
      downpanel = new JPanel();
   
      downpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      
      downpanel.setBackground(Color.BLACK);
      
      raise = new JButton("Raise");
      call = new JButton("Call");
      check = new JButton("Check");
      pass = new JButton("Pass");
      
      raise.addActionListener(new RaiseListener());
      call.addActionListener(new CallListener());
      check.addActionListener(new CheckListener());
      pass.addActionListener(new PassListener());
      
      downpanel.add(raise);
      downpanel.add(call);
      downpanel.add(check);
      downpanel.add(pass);
   }
   /**
      Private inner class that handles the event when the user press the raise button
   */
   private class RaiseListener implements ActionListener
   {
      /**
         Method that handles the event
         @param e The actionevent
      */
      public void actionPerformed(ActionEvent e)
      {
         player.setRaise(leftpanel.getRaise());
         player.setMovement(PokerPlayer.MOV_RAISE);
      }
   }
   /**
      Private inner class that handles the event when the user press the call button
   */
   private class CallListener implements ActionListener
   {
      /**
         Method that handles the event
         @param e The actionevent
      */
      public void actionPerformed(ActionEvent e)
      {
         player.setMovement(PokerPlayer.MOV_CALL);
      }
   }
   /**
      Private inner class that handles the event when the user press the check button
   */
   private class CheckListener implements ActionListener
   {
      /**
         Method that handles the event
         @param e The actionevent
      */
      public void actionPerformed(ActionEvent e)
      {
         player.setMovement(PokerPlayer.MOV_CHECK);
      }
   }
   /**
      Private inner class that handles the event when the user press the pass button
   */
   private class PassListener implements ActionListener
   {
      /**
         Method that handles the event
         @param e The actionevent
      */
      public void actionPerformed(ActionEvent e)
      {
         player.setMovement(PokerPlayer.MOV_PASS);
      }
   }
   /**
      Method that posts the cards of the player in the board
   */
   public void setHand()
   {
      leftpanel.setCard1(player.getCard(1).getImage());
      leftpanel.setCard2(player.getCard(2).getImage());
   }
   /**
      Method that posts the flop in the board
      @param imagecard1 A string with the direction of the first card
      @param imagecard2 A string with the direction of the second card
      @param imagecard3 A string with the direction of the third card
   */
   public void setFlop(String imagecard1, String imagecard2, String imagecard3)
   {
      centralpanel.setBoardCard1(imagecard1);
      centralpanel.setBoardCard2(imagecard2);
      centralpanel.setBoardCard3(imagecard3);
   }
   /**
      Method that posts the turn in the board
      @param imagecard4 A string with the direction of the fourth card
   */
   public void setTurn(String imagecard4)
   {
      centralpanel.setBoardCard4(imagecard4);
   }
   /**
      Method that posts the river in the board
      @param imagecard5 A string with the direction of the fifth card
   */
   public void setRiver(String imagecard5)
   {
      centralpanel.setBoardCard5(imagecard5);
   }
   /**
      Method to actualize the board
      @param pot The total pot
      @param minBet The minimum Bet
   */
   public void actualize(int pot, int minBet)
   {
      //actualize left panel
      leftpanel.actualizeLeft(minBet);

      //actualize right panel
      rightpanel.actualizeRight(pot);
   }
   /**
      Method to reset the Cards in the table (puts a "back" image)
   */
   public void resetBoardCards()
   {
      centralpanel.resetCenter();
      leftpanel.resetCards();
   }
   /**
      Method to change the color of the down panel to know who is playing.
      @param color the color to change
   */
   public void actingStatusColor(Color color)
   {
      downpanel.setBackground(color);
   }
}