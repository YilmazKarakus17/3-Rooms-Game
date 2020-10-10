import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import java.io.*;
import java.util.ArrayList;
/**
 * Panel class that extends JPanel and is used as IO for when player creates a new game
 *
 * @author (YK)
 * @version (21/03/2020)
 */
public class NewGamePanel extends JPanel implements ActionListener
{
    /********* Instance Variables *********/
    private HomePage Window;
    private Image BackgroundImg = (new ImageIcon("NewGame.png").getImage());
    //file directory
    private File directory = new File("GameSaves");
    //Panel Components
    private JLabel FileNameLabel = new JLabel("Enter A FileName: ");
    private JLabel OutputMessage = new JLabel("");
    private JButton CreateFileButton = new JButton("Create");
    private JButton BackButton = new JButton("Back");
    private JTextField FileNameTextField = new JTextField("Example: myGame",5);
    //String variable containing whats written in the text field
    private String FileName;
    /********* Constructor *********/
    public NewGamePanel(HomePage window) //remember to add HomePage as Argument
    {
       this.Window = window;
       //creating game save folder if it doesn't exist
       createGameSave();
       //setting panel size
       Dimension ImageSize = new Dimension(BackgroundImg.getWidth(null), BackgroundImg.getHeight(null));
       setSize(ImageSize);
       setPreferredSize(ImageSize);
       setMaximumSize(ImageSize);
       setMinimumSize(ImageSize);
       //setting Layout
       setLayout(null);
       //------------ Adding the components ------------/
       //Positioning and manipulating the look and size of the label that reads Enter A FileName:
       FileNameLabel.setBounds(180, 200, 250, 35);
       FileNameLabel.setFont(new java.awt.Font("ITALIC", Font.BOLD,18)); 
       FileNameLabel.setForeground(Color.WHITE);
       
       //Positioning and manipulating the look and size of text field
       FileNameTextField.setBounds(350, 200, 250, 35);
       FileNameTextField.setBorder(null);
       FileNameTextField.setEditable(true);
       //Positioning and manipulating the look and size of the submit Button
       CreateFileButton.addActionListener(this);
       CreateFileButton.setBounds(350, 280, 110, 30);
       CreateFileButton.setContentAreaFilled(false);
       CreateFileButton.setForeground(Color.WHITE);
       CreateFileButton.setFont(new java.awt.Font("ITALIC",Font.BOLD, 25));
       CreateFileButton.setBorder(null);
       //Positioning and manipulating the look and size of the Message Label
       OutputMessage.setBounds(375, 245, 110, 30);
       OutputMessage.setForeground(Color.RED);
       OutputMessage.setFont(new java.awt.Font("ITALIC", Font.BOLD, 20));
       //Positioning and manipulating the look and size of the submit Button
       BackButton.addActionListener(this);
       BackButton.setBounds(20, 480, 110, 30);
       BackButton.setForeground(Color.WHITE);
       BackButton.setBackground(Color.ORANGE);
       BackButton.setFont(new java.awt.Font("ITALIC",Font.BOLD, 25));
       BackButton.setBorder(null);
       //Adding the Components
       add(FileNameLabel);
       add(FileNameTextField);
       add(CreateFileButton);
       add(OutputMessage);
       add(BackButton);
    }    
    /*********************************** Instance methods ******************************************************/
    //creates a game save folder if it doesnt exist
    public void createGameSave()
    {
        if(directory.exists() == false)
        {
            directory.mkdir();
        }
    }
    //creates file
    public void CreateFile()
    {
        try
        {
            String validatedNewFileName = getValidatedFileName();
            File newFile = new File("GameSaves/" + validatedNewFileName + ".txt");
            if(CheckIfFileExists(newFile) == true){ 
                OutputMessage.setForeground(Color.RED);
                OutputMessage.setBounds(350, 245, 110, 30);
                OutputMessage.setSize(300,50);
                OutputMessage.setText("File Already Exists!");                
            }
            else
            {
                checkFileDirectoryExists();
                PrintWriter outputStream = new PrintWriter(new FileWriter(newFile));
                outputStream.close();
                PickCharacter("GameSaves/" + validatedNewFileName + ".txt");
            }
        }
        catch(IOException e)
        {
            
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
    //disposes current window after creating HeroInitializerIO object
    public void PickCharacter(String filename)
    {
        HeroInitializerIO pickCharacter = new HeroInitializerIO(filename);
        Window.Dispose();
    }
    //method to take out unwanted characters such as: spaces or . , or white space characters or ' ; # @ { }
    public String getValidatedFileName()
    {
        String[] splitFileName = FileName.split("[.,\\s';#@{}]");
        String validatedFileName = "";
        for(int i=0; i<splitFileName.length; i++)
        {
            validatedFileName += splitFileName[i];
        }
        return validatedFileName;
    }
    //method which returns false if doesn't exist and changes the Message displayed to doesn't exist
    public boolean CheckIfFileExists(File newFile)
    {
        if(newFile.exists())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /********** Overriding the inherited paintComponent method *********/
    //Overriding paintCompenent Method to draw the background image of the new game  panel
    public void paintComponent(Graphics g)
    {
        g.drawImage(BackgroundImg,0,0,null);
    }
    /********** Implementing the method in events interface *********/
    public void actionPerformed(ActionEvent event)
    {
        String Command = event.getActionCommand();
        if(Command.equals("Create"))
        {
            FileName = FileNameTextField.getText();
            CreateFile();
        }
        else if(Command.equals("Back"))
        {
            Window.backToHomeScreen();
        }
    }
}
