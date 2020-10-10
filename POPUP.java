import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
/**
 * Simple subclass to Jframe used to display a message
 *
 * @author (YK)
 * @version (20/03/2020)
 */
public class POPUP extends JFrame implements ActionListener
{
    public POPUP(String Message)
    {
        super("MESSAGE!");
        setLayout(new GridLayout(2,1));        
        add(new JLabel(Message));
        Button exitButton = new Button("Exit");
        exitButton.addActionListener(this);
        setSize(600, 250);
        setLocationRelativeTo(null);
        setVisible(true);        
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        String Command = evt.getActionCommand();
        if(Command.equals("Exit"))
        {
            this.dispose();
        }
    }
}
