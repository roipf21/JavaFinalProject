/**
   MAIN DRIVER CLASS
*/
import java.util.ArrayList;

public class MainDriver
{
   public static void main(String[] args)
   {
      final int BIGBLIND = 50;
      PokerGame pokergame = new PokerGame(BIGBLIND);
      pokergame.game();
   }

}