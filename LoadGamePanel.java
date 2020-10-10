import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import java.io.*;
import java.util.ArrayList;
/**
 * Panel class that extends JPanel and is used as IO for when player loads a game
 *
 * @author (YK)
 * @version (21/03/2020)
 */
public class LoadGamePanel extends JPanel implements ActionListener
{
    /********* Instance Variables *********/
    private HomePage Window;
    private Image BackgroundImg = (new ImageIcon("LoadGame.png").getImage());
    //Variables concerning File processing
    private String[] fileNames;
    private File directory = new File("GameSaves");
    //------ Panel Components --------
    private JTextArea listOfFilesNames;
    private JTextField FileNameTextField = new JTextField();
    private JScrollPane scrollbar;
    //labels
    private JLabel fileNameLabel = new JLabel("Enter FileName: ");
    private JLabel outputMessage = new JLabel("");
    //buttons
    private JButton LoadGameButton = new JButton("Load");
    private JButton BackButton = new JButton("Back");
    //standardized font
    private Font LoadGameFont = new java.awt.Font("ITALIC",Font.BOLD, 20);
    //String variable containing whats written in the text field
    private String FileName;
    //------ File Variables --------------
    private ArrayList<String> gameSaveInfo;
    /********* Constructor *********/
    public LoadGamePanel(HomePage Window)
    {
       this.Window = Window;
       //setting panel size
       Dimension ImageSize = new Dimension(BackgroundImg.getWidth(null), BackgroundImg.getHeight(null));
       setSize(ImageSize);
       setPreferredSize(ImageSize);
       setMinimumSize(ImageSize);
       setMaximumSize(ImageSize);
       //setting layout
       setLayout(null);
       //setting Up Labels
       setUpTextArea();
       //Creating Button
       createLoadButton();
       //creating Labels and TextField
       createInputField();
       //creating the Back Button
       createBackButton();
    }
    //Positioning and manipulating the look and size of the submit Button
    public void createBackButton()
    {
       BackButton.addActionListener(this);
       BackButton.setBounds(20, 480, 110, 30);
       BackButton.setForeground(Color.WHITE);
       BackButton.setBackground(Color.ORANGE);
       BackButton.setFont(new java.awt.Font("ITALIC",Font.BOLD, 25));
       BackButton.setBorder(null);
       add(BackButton); 
    }
    //creates and populates a text area listing all filenames
    public void setUpTextArea()
    {
        try{
            checkFileDirectoryExists();
            fileNames = directory.list();
            listOfFilesNames = new JTextArea();
            //loops to add all the file names to the textField
            for(int i=0; i<fileNames.length; i++)
            {
                listOfFilesNames.append(fileNames[i] +"\n");
            }
            listOfFilesNames.setEditable(false);
            //adding scrollbar - wrapping it around textArea and setting it Vertical
            scrollbar = new JScrollPane(listOfFilesNames);
            scrollbar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollbar.setBounds(20, 50, 250,400);
            this.add(scrollbar);
        }
        catch(FileException e)
        {
            POPUP errorPopUp = new POPUP(e.getMessage());
        }
    }
    //throws checked exception if directory doesn't exist
    public void checkFileDirectoryExists() throws FileException
    {
        if(directory.isDirectory() == false){throw new FileException("Error Directory Doesn't Exist! To Fix create new folder called GameSaves");}
    }
    //styles and positions the item on the panel
    public void createLoadButton()
    {
        LoadGameButton.addActionListener(this);
        //making button transparent
        LoadGameButton.setContentAreaFilled(false);
        LoadGameButton.setBorder(null);
        //Styling Button Font
        LoadGameButton.setFont(new java.awt.Font("ITALIC", Font.BOLD, 40));
        LoadGameButton.setForeground(Color.WHITE);
        //Positioning the button
        LoadGameButton.setBounds(480, 300, 13, 15);
        LoadGameButton.setSize(100,30);
        this.add(LoadGameButton);
    }
    /*Style and postions both the label that reads Enter FileName: and the outputMessage Label
     * and the textField
     */
    public void createInputField()
    {  
        //fileNameLabel
        fileNameLabel.setBounds(365, 180, 53, 55);
        fileNameLabel.setFont(LoadGameFont);
        fileNameLabel.setForeground(Color.WHITE);
        fileNameLabel.setSize(200,20);
        
        //outputMessage
        outputMessage.setBounds(420, 240, 53, 55);
        outputMessage.setFont(LoadGameFont);
        outputMessage.setForeground(Color.WHITE);
        outputMessage.setSize(200,20);
        
        //FileNameTextField
        FileNameTextField.setBounds(540, 178, 200, 30);
        FileNameTextField.setEditable(true);
        FileNameTextField.setBorder(null);
        FileNameTextField.setBackground(Color.WHITE);
        //styling TextField text font
        FileNameTextField.setForeground(Color.ORANGE);
        FileNameTextField.setFont(LoadGameFont);
        
        this.add(fileNameLabel);
        this.add(outputMessage);
        this.add(FileNameTextField);
    }
    //If the filename entered exists it loads the actual game using the data from it
    public void LoadGame()
    {
        if(CheckFileName())
        {
            try{
                LoadFileInfo();
                checkFileNotCorrupted();
                outputSuccessMessage("FileLoaded");
                StartGame();
            }
            catch(FileException e)
            {
                outputErrorMessage(e.getMessage());
            }
            catch(IOException e)
            {
                outputErrorMessage(e.getMessage());
            }
        }
        else
        {
            outputErrorMessage("Incorrect FileName!");
        }
    }
    //checks if file name exists
    public boolean CheckFileName()
    {
        File checkFile = new File("GameSaves/" + FileName);
        if(checkFile.exists())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //changes the position, text and font of the output message to notify an action performed was successful to the user
    public void outputSuccessMessage(String Message)
    {
        outputMessage.setText(Message);
        outputMessage.setForeground(Color.GREEN);
        outputMessage.setBounds(380,240,53,55);
        outputMessage.setSize(300,20);
    }
    //changes the position, text and font of the output message to notify an action performed was unsuccessful to the user
    public void outputErrorMessage(String Message)
    {
        outputMessage.setText(Message);
        outputMessage.setForeground(Color.RED);
        outputMessage.setBounds(380,240,700,55);
        outputMessage.setSize(850,20);
    }    
    //Loads the information on the file to the gameSaveInfo arrayList
    public void LoadFileInfo() throws IOException
    {
        gameSaveInfo = new ArrayList<String>();
        String FileLine = "Line1";
        BufferedReader inputStream = new BufferedReader(new FileReader("GameSaves/" + FileName));
        while(FileLine != null)
        {
            FileLine = inputStream.readLine();
            if(FileLine != null)
            {
                gameSaveInfo.add(FileLine);
            }
            else{}
        }
    }
    //checks if file has at least 9 lines
    public void checkFileNotCorrupted() throws FileException
    {
        if(gameSaveInfo.size() < 9)
        {
            throw new FileException("Corrupted File: Cannot Load!");
        }
    }
    //------------------------- Starts Game -------------------------//
    //creates a game object and passes the character info to the constructor then calls on the dispose method
    public void StartGame()
    {
        String filename = "GameSaves/" + FileName;
        //info
        String type = gameSaveInfo.get(0);
        String name = gameSaveInfo.get(1);
        int hp = Integer.parseInt(gameSaveInfo.get(2));
        double armour = Double.parseDouble(gameSaveInfo.get(3));
        int damage = Integer.parseInt(gameSaveInfo.get(4));
        int speed = Integer.parseInt(gameSaveInfo.get(5));
        int stamina = Integer.parseInt(gameSaveInfo.get(6));
        int coins = Integer.parseInt(gameSaveInfo.get(7));
        int staminaRegen = Integer.parseInt(gameSaveInfo.get(8));

        //finally disposes the frame this panel is added to
        ArrayList<Items> inventory = new ArrayList<Items>();
        for(int i=9; i<gameSaveInfo.size();i++)
        {
            try{
                inventory.add(getCorrectItem(gameSaveInfo.get(i)));
            }
            catch(ItemsException e){}
        }
        
        if(type.equalsIgnoreCase("berserker"))
        {
            Hero Player = new Berserker(name,hp,armour,damage,speed,stamina,coins,staminaRegen,inventory);
            GameIO startGame = new GameIO(Player, filename);
        }
        else if(type.equalsIgnoreCase("speedster"))
        {
            Hero Player = new Speedster(name,hp,armour,damage,speed,stamina,coins,staminaRegen,inventory);
            GameIO startGame = new GameIO(Player, filename);
        }
        else{
            POPUP error = new POPUP("Correct Type Doesn't Exist");
            Window.Dispose();
        }     
        Window.Dispose();
    }
    //returns the correct item
    public Items getCorrectItem(String itemName) throws ItemsException
    {
        if(itemName.equals("goldensword"))
        {
            return new GoldenSword();
        }
        else if (itemName.equals("knife"))
        {
            return new Knife();
        }
        else if (itemName.equals("shield"))
        {
            return new Shield();
        }
        else{
            throw new ItemsException("Item Name Wrong! ");
        }
    }
    /********** Overriding the inherited paintComponent method *********/
    //Overriding paintCompenent Method to draw the background image of the new game  panel
    public void paintComponent(Graphics g)
    {
        g.drawImage(BackgroundImg,0,0,null);
    }
    /********** Implementing the method in events interface *********/
    public void actionPerformed(ActionEvent evt)
    {
        String Command = evt.getActionCommand();
        if(Command.equals("Load"))
        {
            FileName = FileNameTextField.getText();
            LoadGame();
        }
        else if(Command.equals("Back"))
        {
            Window.backToHomeScreen();
        }
    }   
}
