/**
   RIGHT PANEL CLASS 
*/
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
public class RightPanel extends JPanel
{
   private JTextField message;
   private JLabel total;
   private ArrayList<JLabel> playersBet;
   private static ArrayList<PokerPlayer> players;
   private int numbplayers;
   private ArrayList<JPanel> panels;
   /**
      Constructor for the right panel class
      @param pokerplayers An arraylist with the pokerplayers
   */      
   public RightPanel(ArrayList<PokerPlayer> pokerplayers)
   {
      players = pokerplayers;
      
      numbplayers = players.size();
   
      setLayout(new GridLayout((numbplayers+1),1));
      
      setBackground(Color.BLACK);
      
      panels = new ArrayList<JPanel>();
      for (int i = 0 ; i < (numbplayers+1) ; i++) // we want 1 panel more than the number of players to hold the pot
      {
         panels.add(new JPanel());
      }
           
      total = new JLabel();
      total.setBorder(BorderFactory.createTitledBorder("Pot"));
      total.setText(""); 
      
      message = new JTextField(10);
      message.setEditable(false);
      message.setText("PLAYERS");     
      
      playersBet = new ArrayList<JLabel>();
      for (int i = 0 ; i < numbplayers ; i++)
      {
         playersBet.add(new JLabel());
      }
      for (int i = 0 ; i < playersBet.size() ; i++)
      {
         playersBet.get(i).setBorder(BorderFactory.createTitledBorder(players.get(i).getName()));
         playersBet.get(i).setText(""); 
      }
      
      panels.get(0).setLayout(new GridLayout(2,1));
      panels.get(0).add(total);
      panels.get(0).add(message);
      
      for (int i = 1; i < (numbplayers+1); i++)
      {  
         panels.get(i).setLayout(new GridLayout(1,1));
         panels.get(i).add(playersBet.get((i-1)));
      }
      for (int i = 0; i < panels.size() ; i++)
      {
         add(panels.get(i));
      }  
   }
   /**
      Method to set the "index" player current bet
      @param index The index of the player in the players list
      @param bet The player[index] current bet
   */
   public void setPlayerBet(int index, int bet)
   {
      playersBet.get(index-1).setText(String.valueOf(bet));
   }
   /**
      Method to set the current pot
      @param totalPot The current total pot
   */
   public void setPot(int totalPot)
   {
      total.setText(String.valueOf(totalPot));
   }
   /**
      Method to set the players curr bet   
   */
   public void setBets()
   {
      for (int i = 0 ; i < players.size() ; i++)
      {
         playersBet.get(i).setText(String.valueOf(players.get(i).getCurrBet()));
      }
   }
   /**
   */
   public void actualizeRight(int pot) 
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() 
            {
               //SetBets
               for (int i = 0 ; i < players.size() ; i++)
               {
                  playersBet.get(i).setText(String.valueOf(players.get(i).getCurrBet()));
               }
               //SetPot
               total.setText(String.valueOf(pot));
            }
         }
      );
   }
}