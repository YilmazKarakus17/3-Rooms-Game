import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer.*;
import java.io.*;
/**
 * when the game comes to an end this method is called
 *
 * @author (YK)
 * @version (20/03/2020)
 */
public class WinGame extends JPanel implements ActionListener
{
    /******* Instance Variable ********/
    private Image bgImg = (new ImageIcon("youWinBackground.png").getImage());
    /******* Constructor ********/
    public WinGame()
    {
        //setLayout
        setLayout(null);
        //setting dimensions
        Dimension imgSize = new Dimension(bgImg.getWidth(null),bgImg.getHeight(null));
        setSize(imgSize);
        setPreferredSize(imgSize);
        setMaximumSize(imgSize);
        setMinimumSize(imgSize);
        //adding exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new java.awt.Font("ITALIC", Font.BOLD, 50));
        exitButton.setForeground(Color.DARK_GRAY);
        exitButton.setBackground(Color.WHITE);
        exitButton.setBounds(200,500, 375,200);
        exitButton.setSize(375, 150);
        exitButton.setBorder(BorderFactory.createRaisedBevelBorder());
        exitButton.addActionListener(this);
        add(exitButton);
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
