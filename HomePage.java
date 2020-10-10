import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * HomePage class used to at the start to determine whether the user will start new game or continue with existing one
 *
 * @author (Yk)
 * @version (25/03/2020)
 */
public class HomePage extends JFrame
{
    /********* Constructor *********/
    public HomePage()
    {
        super("Home Screen");
        HomeScreenPanel Container = new HomeScreenPanel();
        add(Container);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    /********************************* Instance methods *****************************/
    //procedure which switches current panel back to the homescreen panel
    public void backToHomeScreen()
    {
        HomeScreenPanel Container = new HomeScreenPanel();
        setContentPane(Container);
    }
    //procedure which switches current panel to be of NewGamePanel 
    public void selectNewGame()
    {
        NewGamePanel GamePanel = new NewGamePanel(this);
        setContentPane(GamePanel);
    }
    //procedure which switches current panel to be of LoadGamePanel 
    public void selectLoadGame()
    {
        LoadGamePanel LoadGamePanel = new LoadGamePanel(this);
        setContentPane(LoadGamePanel);
    }
    //Disposes frame
    public void Dispose()
    {
        this.dispose();
    }
    /********************************* Inner Class for Panel *****************************/
    public class HomeScreenPanel extends JPanel implements ActionListener{
        /********* Instance Variables *********/
        private Image img = (new ImageIcon("HomePage.png").getImage());
        /********* Constructor *********/
        public HomeScreenPanel(){   
           //sets the panel size to fit the image size
           Dimension PanelDimension = new Dimension(img.getWidth(null), img.getHeight(null));
           setPreferredSize(PanelDimension);
           setMinimumSize(PanelDimension);
           setMaximumSize(PanelDimension);
           setSize(PanelDimension);
           //Laying out the buttons
           setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
           
           //New Game Button 
           HomeScreenButton NewGameButton = new HomeScreenButton("NewGame");
           NewGameButton.addActionListener(this);
           NewGameButton.setAlignmentX(NewGameButton.CENTER_ALIGNMENT);
           
           //Load Game Button 
           HomeScreenButton LoadGameButton = new HomeScreenButton("LoadGame"); 
           LoadGameButton.addActionListener(this);
           LoadGameButton.setAlignmentX(LoadGameButton.CENTER_ALIGNMENT);
           
           //How To Player Button
           HomeScreenButton GuideButton = new HomeScreenButton("Guide");
           GuideButton.addActionListener(this);
           GuideButton.setAlignmentX(GuideButton.CENTER_ALIGNMENT);
           
           //Exit Button
           HomeScreenButton ExitButton = new HomeScreenButton("Exit");
           ExitButton.addActionListener(this);
           ExitButton.setAlignmentX(ExitButton.CENTER_ALIGNMENT);
           
           //------------------------------------------------
           this.add(Box.createVerticalGlue());
              
           this.add(NewGameButton);
           this.add(LoadGameButton);
           this.add(GuideButton);
           this.add(ExitButton);
           //add verticalglue at the end also to center the items in the center 
           this.add(Box.createVerticalGlue());
        }
        
        //Overriding paintCompenent Method to draw the background image of the homescreen panel
        public void paintComponent (Graphics g){
            //draws image staring from top right
            g.drawImage(img, 0, 0, null);
        }
        
        /********** Implementing the method in events interface *********/
        public void actionPerformed(ActionEvent evt)
        {
            String Command = evt.getActionCommand();
            if(Command.equals("NewGame"))
            {
                selectNewGame();
            }
            else if(Command.equals("LoadGame"))
            {
                selectLoadGame();
            }
            else if(Command.equals("Guide"))
            {
                HowToPlayWindow GuideGUI = new HowToPlayWindow();
            }
            else if(Command.equals("Exit"))
            {
                System.exit(0);
            }
        } 
    }  
    /********************************* Inner Class for Home Screen Buttons *****************************/
    /* This class is used as a template on standardizing the look of 
     * the buttons in the HomeScreen.
     */
    public class HomeScreenButton extends JButton
    {
        public HomeScreenButton(String ButtonName)
        {
            super(ButtonName);
            //Font styling - color, font
            setFont(new java.awt.Font("ITALIC", Font.BOLD, 38));
            setForeground(Color.WHITE);
            
            //Makes the buttons background transparent
            setContentAreaFilled(false);
            setBorder(null);
        }

    }
    
   
    /********************************************************************** MAIN **********************************************************************************/
    public static void main()
    {
        HomePage homePage = new HomePage();
    }
}

