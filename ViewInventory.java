import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Write a description of class ViewInventory here.
 *
 * @author (k)
 * @version (27/03/2020)
 */
public class ViewInventory extends JFrame
{
    public ViewInventory(ArrayList<Items> inventory)
    {
        ViewInventoryPanel panel = new ViewInventoryPanel(inventory);
        add(panel);
        pack();
        setVisible(true);
        setResizable(false);
    }
    
    public class ViewInventoryPanel extends JPanel
    {
        /******* Instance Variable ********/
        private Image bgImg = (new ImageIcon("itemsBackground.png").getImage());
        private JTextArea tf = new JTextArea();
        /******* Constructor ********/
        public ViewInventoryPanel(ArrayList<Items> inventory)
        {
            //setLayout
            setLayout(null);
            //setting dimensions
            Dimension imgSize = new Dimension(bgImg.getWidth(null),bgImg.getHeight(null));
            setSize(imgSize);
            setPreferredSize(imgSize);
            setMaximumSize(imgSize);
            setMinimumSize(imgSize);
            //adding FieldArea
            tf.setEditable(false);
            tf.append("YOUR INVENTORY:" + "\n");
            for(Items i: inventory)
            {
                tf.append(i.getInstance() + "\n");
            }
            JScrollPane scrollbar = new JScrollPane(tf);
            scrollbar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollbar.setBounds(50, 175, 510,150);
            this.add(scrollbar);
        }
        
        //paints the background
        public void paintComponent(Graphics g)
        {
            g.drawImage(bgImg,0,0,null);
        }
        
        public void actionPerformed(ActionEvent evt)
        {
            if(evt.getActionCommand().equals("Exit"))
            {
                System.exit(0);
            }
        }      
    }
}
