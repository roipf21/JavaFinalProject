/**
   Central Panel Class. The central panel of the board
*/

import java.awt.*;
import javax.swing.*;
public class CentralPanel extends JPanel
{
   private JLabel card1;
   private JLabel card2;
   private JLabel card3;
   private JLabel card4;
   private JLabel card5;
   private ImageIcon cardImage;
   private final int MAX_CARDS_BOARD = 5;
   /**
      Constructor for the central panel of the board
   */
   public CentralPanel()
   {
      setLayout(new FlowLayout(FlowLayout.CENTER));
      
      setBackground(new Color(0,100,0));
      
      cardImage = new ImageIcon("back.jpg");
      
      card1 = new JLabel(cardImage);
      card2 = new JLabel(cardImage);
      card3 = new JLabel(cardImage);
      card4 = new JLabel(cardImage);
      card5 = new JLabel(cardImage);
      
      card1.setBorder(BorderFactory.createTitledBorder("BoardCard1"));
      card2.setBorder(BorderFactory.createTitledBorder("BoardCard2"));
      card3.setBorder(BorderFactory.createTitledBorder("BoardCard3"));
      card4.setBorder(BorderFactory.createTitledBorder("BoardCard4"));
      card5.setBorder(BorderFactory.createTitledBorder("BoardCard5"));
      
      add(card1);
      add(card2);
      add(card3);
      add(card4);
      add(card5);
      
   }
   /**
      Method to set the first card of the board
      @param imageDirectory A string witht the direction of the card
   */
   public void setBoardCard1(String imageDirectory)
   {
      cardImage = new ImageIcon(imageDirectory);
      card1.setIcon(cardImage);
   }
   /**
      Method to set the second card of the board
      @param imageDirectory A string witht the direction of the card
   */
   public void setBoardCard2(String imageDirectory)
   {
      cardImage = new ImageIcon(imageDirectory);
      card2.setIcon(cardImage);
   }
   /**
      Method to set the third card of the board
      @param imageDirectory A string witht the direction of the card
   */
   public void setBoardCard3(String imageDirectory)
   {
      cardImage = new ImageIcon(imageDirectory);
      card3.setIcon(cardImage);
   }
   /**
      Method to set the fourth card of the board
      @param imageDirectory A string witht the direction of the card
   */
   public void setBoardCard4(String imageDirectory)
   {
      cardImage = new ImageIcon(imageDirectory);
      card4.setIcon(cardImage);
   }
   /**
      Method to set the fifth card of the board
      @param imageDirectory A string witht the direction of the card
   */
   public void setBoardCard5(String imageDirectory)
   {
      cardImage = new ImageIcon(imageDirectory);
      card5.setIcon(cardImage);
   }
   /**
      Method to reset the cards in the board
   */
   public void resetCenter() 
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() 
            {
               cardImage = new ImageIcon("back.jpg");
               card1.setIcon(cardImage);
               card2.setIcon(cardImage);
               card3.setIcon(cardImage);
               card4.setIcon(cardImage);
               card5.setIcon(cardImage);
            }
         }
      );
   }
}
