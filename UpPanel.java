/**
   UP PANEL CLASS   
*/
import java.awt.*;
import javax.swing.*;
public class UpPanel extends JPanel
{
   private JTextField note;
   /**
      Constructor for the UpPanel Class
   */
   public UpPanel()
   {
      setBackground(Color.BLACK);
      
      setLayout(new FlowLayout(FlowLayout.CENTER));
      
      note = new JTextField(21);
      note.setEditable(false);
      note.setBorder(BorderFactory.createTitledBorder("Note"));
      note.setText("Non valid movements will result in FOLD");
      
      add(note);
   }
   
}
