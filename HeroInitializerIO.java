import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Simple frame that only shows stats of the hero
 *
 * @author (YK)
 * @version (14/3/20)
 */
public class HeroInitializerIO extends JFrame
{
    /******* Instance Variable *************/
    private String FileName;
    /********* Constructor **************/
    public HeroInitializerIO(String FileName)
    {
        super("HeroInitializer");
        this.FileName = FileName;
        pickHeroPanel Container = new pickHeroPanel(this);
        add(Container);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);       
    }
    //changes the panel to a specialized panel that presents user with textfield to choose their name in game
    public void changeToChooseName(Hero chosenHero)
    {
        createNamePanel panel = new createNamePanel(this, chosenHero);
        setContentPane(panel);
    }
    //changes the panel to a specialized panel that presents user with two hero options
    public void changeToChooseHero()
    {
        pickHeroPanel panel = new pickHeroPanel(this);
        setContentPane(panel);
    }
    //to dispose frame
    public void Dispose()
    {
        this.dispose();
    }
    //returns the file name 
    public String getFileName()
    {
        return FileName;
    }
    /***************************** Inner Class Panel for choosing Hero ********************************/
    public class pickHeroPanel extends JPanel implements ActionListener
    {
        /******* Instance Variables *******/
        private HeroInitializerIO Window;
        private Image img = (new ImageIcon("pickHeroBackground.png").getImage());
        /********** Constructor ************/
        public pickHeroPanel(HeroInitializerIO window)
        {
            this.Window = window;
            Dimension ImageSize = new Dimension(img.getWidth(null), img.getHeight(null));
            setSize(ImageSize);
            setMaximumSize(ImageSize);
            setMinimumSize(ImageSize);
            setPreferredSize(ImageSize);
            
            setLayout(null);           
            //----- BerserkerStats and Button ------------       
            Panel BerserkerStats = new Panel(new GridLayout(9,1));
            Hero berserker = new Berserker();
            BerserkerStats.add(new Label("Hp: " + berserker.getHp()));
            BerserkerStats.add(new Label("Damage: " + berserker.getDamage()));
            BerserkerStats.add(new Label("Armour: " + berserker.getArmour()));
            BerserkerStats.add(new Label("Speed: " + berserker.getSpeed()));        
            BerserkerStats.add(new Label("Stamina: " + berserker.getStamina()));
            BerserkerStats.add(new Label("Stamina Regen: " + berserker.getStaminaRegenAmount()));
            BerserkerStats.add(new Label("Special: Berserker starts of with 15 coins, as well as bonus Odenson Damage"));;
            BerserkerStats.setBounds(45,200, 250,300);
            BerserkerStats.setSize(450,200);
            BerserkerStats.setPreferredSize(getSize());
            //Choose Berserker Button
            pickHeroButton chooseBerserker = new pickHeroButton("Berserker");
            chooseBerserker.setBounds(45,415, 450, 200);
            chooseBerserker.addActionListener(this);
            add(chooseBerserker);
            //----- SpeedsterStats pannel ------------  
            Panel SpeedsterStats = new Panel(new GridLayout(9,1));
            Hero speedster = new Speedster();
            SpeedsterStats.add(new Label("Hp: " + speedster.getHp()));
            SpeedsterStats.add(new Label("Damage: " + speedster.getDamage()));
            SpeedsterStats.add(new Label("Armour: " + speedster.getArmour()));
            SpeedsterStats.add(new Label("Speed: " + speedster.getSpeed()));        
            SpeedsterStats.add(new Label("Stamina: " + speedster.getStamina()));
            SpeedsterStats.add(new Label("Special: Speedster has 15% chance of not getting hit by enemy attack, by going back in time"));;
            SpeedsterStats.setBounds(540,200, 650,300);
            SpeedsterStats.setSize(500,200);
            SpeedsterStats.setPreferredSize(getSize());
            //Choose Speedster Button
            pickHeroButton chooseSpeedster = new pickHeroButton("Speedster");
            chooseSpeedster.setBounds(540,415, 500, 200);
            chooseSpeedster.addActionListener(this);
            add(chooseSpeedster);
            

            add(BerserkerStats);
            add(SpeedsterStats);   
        }
        //overriding paintComponent to set the background
        public void paintComponent(Graphics g)
        {
            g.drawImage(img, 0, 0, null);
        }
        /************ implementing method for ActionListener iinterface ***************/
        public void actionPerformed(ActionEvent evt)
        {
            String Command = evt.getActionCommand();
            if(Command.equals("Berserker"))
            {
                changeToChooseName(new Berserker());
            }
            else if(Command.equals("Speedster"))
            {
                changeToChooseName(new Speedster());
            }           
        }
    }
    /***************************** Inner Class Panel for choosing Name for hero ********************************/
    public class createNamePanel extends JPanel implements ActionListener
    {
        /******* Instance Variables *******/
        private HeroInitializerIO Window;
        private Image img = (new ImageIcon("createNameBackground.png").getImage());
        private JTextField nameTextField = new JTextField();
        private JLabel createNameLabel = new JLabel("Pick a Name: ");
        private JLabel outputMessage = new JLabel("");
        private JButton createHeroButton = new JButton("Start");
        private JButton BackButton = new JButton("Back");
        private String Name = "";
        private Hero HeroType;
        private Hero CreatedHero;
        /********** Constructor ************/
        public createNamePanel(HeroInitializerIO window, Hero HeroType)
        {
            this.Window = window;
            this.HeroType = HeroType;
            //setting the size          
            Dimension ImageSize = new Dimension(img.getWidth(null), img.getHeight(null));
            setSize(ImageSize);
            setMaximumSize(ImageSize);
            setMinimumSize(ImageSize);
            setPreferredSize(ImageSize);
            //setting layout
            setLayout(null);
            //Position Labels
            createLabels();
            //creating text field
            createTextField();
            //creating Buttons
            createButtons();
        }
        //positions and styles the labels
        public void createLabels()
        {
            //createNameLabel
            createNameLabel.setBounds(350, 240, 20, 300);
            createNameLabel.setForeground(Color.ORANGE);
            createNameLabel.setFont(new java.awt.Font("ITALIC", Font.BOLD, 25));
            createNameLabel.setSize(300,50);
            this.add(createNameLabel);
            //outputMessage Label
            outputMessage.setBounds(400, 280, 20, 300);
            outputMessage.setForeground(Color.RED);
            outputMessage.setFont(new java.awt.Font("ITALIC", Font.BOLD, 25));
            outputMessage.setSize(500,50);
            this.add(outputMessage);
        }
        //positions and styles the textfield
        public void createTextField()
        {
            nameTextField.setBounds(525, 250, 20, 300);
            nameTextField.setFont(new java.awt.Font("ITALIC", Font.BOLD, 20));
            nameTextField.setSize(300,30);
            nameTextField.setForeground(Color.ORANGE);
            nameTextField.setBorder(null);
            this.add(nameTextField);
        }
        //postions and styles the JButtons start and back
        public void createButtons()
        {
            //back Button
            BackButton.addActionListener(this);
            BackButton.setBounds(20, 580, 10, 300);
            BackButton.setBackground(Color.ORANGE);
            BackButton.setForeground(Color.WHITE);
            BackButton.setFont(new java.awt.Font("ITALIC",Font.BOLD, 25));
            BackButton.setSize(100,30);
            this.add(BackButton);
            //adding start button
            createHeroButton.addActionListener(this);
            createHeroButton.setBounds(350, 350, 10, 300);
            createHeroButton.setForeground(Color.ORANGE);
            createHeroButton.setFont(new java.awt.Font("ITALIC",Font.BOLD, 45));
            createHeroButton.setSize(300,50);
            //making background of button trasparent
            createHeroButton.setContentAreaFilled(false);
            createHeroButton.setBorder(null);
            this.add(createHeroButton);
        }
        //starts the actual game by making a game object and passing hero object as argument
        public void StartGame()
        {
            if(HeroType instanceof Berserker)
            {
                CreatedHero = new Berserker(Name);
                GameIO game = new GameIO(CreatedHero,Window.getFileName());               
                Window.Dispose();
            }
            else if(HeroType instanceof Speedster)
            {
                CreatedHero = new Speedster(Name);
                GameIO game = new GameIO(CreatedHero,Window.getFileName());
                Window.Dispose();
            }
            else{
                //Do Nothing
            } 
        }
        //overriding paintComponent to set the background
        public void paintComponent(Graphics g)
        {
            g.drawImage(img, 0, 0, null);
        }
        /************ implementing method for ActionListener iinterface ***************/
        public void actionPerformed(ActionEvent evt)
        {
            String Command = evt.getActionCommand();
            if(Command.equals("Start"))
            {
                Name = nameTextField.getText();
                if(Name.length() < 5)
                {
                    outputMessage.setForeground(Color.RED);
                    outputMessage.setText("Name has to be at least 5 characters");
                }
                else
                {
                    StartGame();
                }
            }
            else if(Command.equals("Back"))
            {
                Window.changeToChooseHero();
            }
        }
    }
    /********************************************* Inner Class for Button Styling **********************************************/
    public class pickHeroButton extends JButton{
        public pickHeroButton(String buttonName)
        {
            super(buttonName);
            setFont(new java.awt.Font("ITALIC", Font.BOLD, 40));
            setBackground(Color.ORANGE);
            setForeground(Color.BLACK);
            setSize(200,400);
        }
    }
}
