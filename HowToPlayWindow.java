import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
/**
 * This is a class that extends JFrame and includes many different tips on how the game works
 * Page 1 - Final Boss
 * Page 2 - How to Move Around the Rooms
 * @author (YK)
 * @version (20/03/2020)
 */
public class HowToPlayWindow extends JFrame
{
    /********* Instance Variables *********/
    private int CurrentPage;

    private CardLayout PagesLayout;
    HowToPlayPanel1 page1 = new HowToPlayPanel1();
    HowToPlayPanel2 page2 = new HowToPlayPanel2();
    /********* Constructor *********/
    public HowToPlayWindow()
    {
        super("How To Play!");
        //starts at page 1
        setContentPane(page1); 
        

        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(this);
    }
    //removes panel displaying page 2 and changes it
    public void ChangeToPage1()
    {
        setContentPane(page1);
    }
    //removes panel displaying page 1 and changes it 
    public void ChangeToPage2()
    {
        setContentPane(page2);
    }    
    /********************************* Inner Class for How To Play Panel *****************************/
    public class HowToPlayPanel1 extends JPanel implements ActionListener
    {
        /********* Instance Variables *********/
        private int CurrentPage;
        private Image page1Image = (new ImageIcon("page1.png").getImage());
        /********* Constructor *********/       
        public HowToPlayPanel1()
        {
           //setting the size of the panel to be same as the size of the image
           Dimension ImageSize = new Dimension(page1Image.getWidth(null),page1Image.getHeight(null));
           setPreferredSize(ImageSize);
           setMinimumSize(ImageSize);
           setMaximumSize(ImageSize);
           setSize(ImageSize);
           //setting the layout of the panel
           setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
           
           HowToPlayPanelButtons NextButton = new HowToPlayPanelButtons("Next");
           NextButton.addActionListener(this);
           NextButton.setAlignmentX(NextButton.RIGHT_ALIGNMENT);
           this.add(Box.createVerticalGlue());
           add(NextButton);
        }
        
        //paints the background by overriding the paintComponent method
        public void paintComponent(Graphics g)
        {
            g.drawImage(page1Image,0,0,null);
        }
        /********** Implementing the method in events interface *********/
        public void actionPerformed(ActionEvent evt)
        {
            String Command = evt.getActionCommand();
            if(Command.equals("Next"))
            {
                ChangeToPage2();
            }
        }
    }
    /********************************* Inner Class for How To Play Panel *****************************/
    public class HowToPlayPanel2 extends JPanel implements ActionListener
    {
        /********* Instance Variables *********/
        private Image page2Image = (new ImageIcon("page2.png").getImage());
        /********* Constructor *********/       
        public HowToPlayPanel2()
        {
           //setting the size of the panel to be same as the size of the image
           Dimension ImageSize = new Dimension(page2Image.getWidth(null),page2Image.getHeight(null));
           setPreferredSize(ImageSize);
           setMinimumSize(ImageSize);
           setMaximumSize(ImageSize);
           setSize(ImageSize);
           //setting the layout of the panel
           setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
           
           HowToPlayPanelButtons BackButton = new HowToPlayPanelButtons("Back");
           BackButton.addActionListener(this);
           BackButton.setAlignmentX(BackButton.LEFT_ALIGNMENT);
           this.add(Box.createVerticalGlue());
           add(BackButton);
        }
        //paints the background by overriding the paintComponent method
        public void paintComponent(Graphics g)
        {
            g.drawImage(page2Image,0,0,null);
        }
        /********** Implementing the method in events interface *********/
        public void actionPerformed(ActionEvent evt)
        {
            String Command = evt.getActionCommand();
            if(Command.equals("Back"))
            {
                ChangeToPage1();
            }
        }
    }
    /********************************* Inner Class for Buttons *****************************/
    public class HowToPlayPanelButtons extends JButton
    {
        public HowToPlayPanelButtons(String ButtonName)
        {
            super(ButtonName);
            //setting the size
            setSize(100,35);
            setPreferredSize(getSize());
            setMinimumSize(getSize());
            setMaximumSize(getSize());
            //setting the font
            setFont(new java.awt.Font("ITALIC", Font.BOLD, 10));
            setBackground(Color.ORANGE);
            setForeground(Color.WHITE);
            //setting the border 
            Border ButtonBorder = new LineBorder(Color.BLACK,1);
            setBorder(ButtonBorder); 
        }
    }
    
}
