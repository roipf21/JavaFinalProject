/**
   LEFT PANEL CLASS
*/

import java.awt.*;
import javax.swing.*;
public class LeftPanel extends JPanel
{
   private JLabel card1;
   private JLabel card2;
   private ImageIcon cardImage;
   private JLabel points;
   private JLabel currBet;
   private JLabel minBet;
   private JTextField raise;
   private JPanel panel1;
   private PokerPlayer player;
   private final int STARTING_POINTS = 2500;
   /**
      Constructor for the left panel of the board
      @param pokerplayer The player that owns th board
   */
   public LeftPanel(PokerPlayer pokerplayer)
   {
      player = pokerplayer;
   
      setLayout(new GridLayout(3,1));//MODIFICAR EL LAYOUT
      
      setBackground(Color.BLACK);
      
      cardImage = new ImageIcon("back.jpg");
      
      card1 = new JLabel(cardImage);
      card2 = new JLabel(cardImage);
      
      card1.setBorder(BorderFactory.createLineBorder(new Color(0,100,0),5));
      card2.setBorder(BorderFactory.createLineBorder(new Color(0,100,0),5));
      
      card1.setForeground(Color.WHITE);
      card2.setForeground(Color.WHITE);
      
      add(card1);
      add(card2);
      
      points = new JLabel();
      points.setBorder(BorderFactory.createTitledBorder("Remaining Points"));
      points.setText(String.valueOf(player.getPRemain()));
      
      currBet = new JLabel();
      currBet.setBorder(BorderFactory.createTitledBorder("Current Bet"));
      currBet.setText("");
      
      minBet = new JLabel();
      minBet.setBorder(BorderFactory.createTitledBorder("Minimum Bet"));
      minBet.setText("");
      
      raise = new JTextField(10);
      raise.setEditable(true);
      raise.setBorder(BorderFactory.createTitledBorder("Raise"));
      raise.setText("");
      
      panel1 = new JPanel();
      panel1.setLayout(new GridLayout(4,1));
      panel1.add(points);
      panel1.add(currBet);
      panel1.add(minBet);
      panel1.add(raise);
      
      add(panel1);
   }
   /**
      Method to set the first card
      @param imageDirectory A String with the directory of the image
   */
   public void setCard1(String imageDirectory)
   {
      cardImage = new ImageIcon(imageDirectory);
      card1.setIcon(cardImage);
   }
   /**
      Method to set the second card
      @param imageDirectory A String with the directory of the image
   */
   public void setCard2(String imageDirectory)
   {
      cardImage = new ImageIcon(imageDirectory);
      card2.setIcon(cardImage);
   }
   /**
      Method that returns the input "raise" from the player
      @return The amount raised
   */
   public int getRaise()
   {
      String input;
      input = raise.getText();
      int raise;
      raise = Integer.parseInt(input);
      return raise;
   }
   /**
      Method to get the remaining points
      @return The remaining points
   */
   public int getPoints()
   {
      String input;
      input = points.getText();
      int points;
      points = Integer.parseInt(input);
      return points;
   }
   /**
      Method to get the min Bet
      @return The min Bet
   */
   public int getMinBet()
   {
      String input;
      input = minBet.getText();
      int minBet;
      minBet = Integer.parseInt(input);
      return minBet;
   }
   /**
      Method to get the curr Bet
      @return The curr Bet
   */
   public int getCurrBet()
   {
      String input;
      input = currBet.getText();
      int currBet;
      currBet = Integer.parseInt(input);
      return currBet;
   }
   /**   
      Method to set the remaining points
   */
   public void setPoints()
   {
      int pts = player.getPRemain();
      points.setText(String.valueOf(pts));
   }
   /**
      Method to set the min Bet
      @param minimumBet The min Bet
   */
   public void setMinBet(int minimumBet)
   { 
      minBet.setText(String.valueOf(minimumBet));
   }
   /**
      Method to set the curr Bet
   */
   // public void setCurrBet()
//    {
//       int currentBet = player.getCurrBet();
//       currBet.setText(String.valueOf(currentBet));
//    }
   /**
      Method to set the raise
   */
   public void clearRaise()
   {
      raise.setText("0");
   }
   /**
   */
   public void actualizeLeft(int minimumBet) 
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() 
            {
               //SetCurrentBet
               int currentBet = player.getCurrBet();
               currBet.setText(String.valueOf(currentBet));
               //SetMinBet
               minBet.setText(String.valueOf(minimumBet));
               //SetPoints
               int pts = player.getPRemain();
               points.setText(String.valueOf(pts));
               //clearRaise
               raise.setText("0");
            }
         }
      );
   }
}