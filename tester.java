import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
public class tester
{   
      
   public static void main(String[] args)
   {
      //CREATE THE PLAYERS
      ArrayList<PokerPlayer> players = new ArrayList<PokerPlayer>();
      String name;
      for (int i = 0; i < 4 ; i++)
      {
         name = JOptionPane.showInputDialog("Name");
         players.add(new PokerPlayer(name, 5000));
      }
      
      //CREATE THE WINDOWS
      ArrayList<GameWindow> windows;
      
      windows = new ArrayList<GameWindow>();
      
      for (int i = 0; i < players.size(); i++)
      {
         windows.add(new GameWindow(players.get(i),players));
      }
      
       
      //DEAL
      
      Deck deck = new Deck();
      deck.shuffle();
      //DEAL STRUCTURE
      Card[] card1 = new Card[players.size()];
      Card[] card2 = new Card[players.size()];
      for (int j = 0; j < players.size(); j++)
      {
         card1[j] = deck.dealCard();
      }
      for (int j = 0; j < players.size(); j++)
      {
         card2[j] = deck.dealCard();
      }
      for (int i = 0; i < players.size(); i++)
      {  
         players.get(i).addCardtoHand(card1[i]);
         players.get(i).addCardtoHand(card2[i]);
         windows.get(i).setHand();
      }
                  //wait
      String hola;
      hola = JOptionPane.showInputDialog("ESPERA AVER QUE PASA");
      
      //BOARD-----FLOP
      
      Card[] flop = new Card[3];
      for (int i = 0; i < 3; i++)
      {
         flop[i] = deck.dealCard();
         //board.add(flop[i]); //adds the flop to the board arraylist
      }
      //sets the images in the windows
      for (int i = 0; i < windows.size(); i++)
      {
         windows.get(i).setFlop(flop[0].getImage(),flop[1].getImage(),flop[2].getImage());     //mirar bien a QUIEN le añado QUE cartas
      }
      
      
                     //wait      
      String cola;
      cola = JOptionPane.showInputDialog("ESPERA AVER QUE PASA");
      
      
      
      //BOARD-----TURN
      
      //Burn a card
      deck.dealCard();
      //Holds the Turn
      Card turn = deck.dealCard();
      //board.add(turn); //adds the turn to the board arraylist
      //sets the images in the windows
      for (int i = 0; i < windows.size(); i++)
      {
         windows.get(i).setTurn(turn.getImage());
      }
      
      
                  //wait      
      String mola;
      mola = JOptionPane.showInputDialog("ESPERA AVER QUE PASA");
      
      
      //BOARD-----RIVER
      
      //Burn a card
      deck.dealCard();
      //Holds the Turn
      Card river = deck.dealCard();
      //board.add(river); //adds the river to the board arraylist
      //sets the images in the windows
      for (int i = 0; i < windows.size(); i++)
      {
         windows.get(i).setRiver(river.getImage());
      }
   }
}