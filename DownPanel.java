/**import java.awt.*;
import javax.swing.*;
public class DownPanel extends JPanel
{
   private JButton raise;
   private JButton call;
   private JButton check;
   private JButton pass;
   private JPanel panel1;
   private boolean isRaise;
   private boolean isCall;
   private boolean isCheck;
   private boolean isPass;
   private PokerPlayer player;
   
   
   
   public DownPanel(PokerPlayer pokerplayer)
   {
      player = pokerplayer;
   
      setLayout(new FlowLayout(FlowLayout.CENTER));
      
      setBackground(Color.BLACK);
      
      raise = new JButton("Raise");
      call = new JButton("Call");
      check = new JButton("Check");
      pass = new JButton("Pass");
      
      raise.addActionListener(new RaiseListener());
      call.addActionListener(new CallListener());
      check.addActionListener(new CheckListener());
      pass.addActionListener(new PassListener());
      
      add(raise);
      add(call);
      add(check);
      add(pass);
   }
}
