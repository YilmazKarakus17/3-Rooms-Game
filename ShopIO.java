import java.awt.*;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
//---------- For my Timer class --------------
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;
/**
 * Write a description of class ShopIO here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ShopIO extends Frame implements ActionListener
{
    //Images used 
    private ImageIcon ChestClosed = new ImageIcon("ClosedChest.png");
    private ImageIcon ChestOpen = new ImageIcon("OpenChest.png");
    private ImageIcon OutputImage;
    //Labels
    private JLabel ImageShown;
    private Label OutputMessage;
    private Label PlayerGoldAmount;    
    //Games Own Type Objects
    private Shop shop;
    private Hero Player;
    //Dimesion of the window
    private Dimension myDimension = new Dimension(350, 500);
    //Panels
    private Panel ItemEnhanceButtonsPanel = new Panel(new GridLayout(1,3));
    private Panel ButtonPanel;
    //--- Timer Objects used to create delays ---//
    private Timer timer = new Timer();
    private ChangeImageToClosed SchedulerTask = new ChangeImageToClosed();
    /************* Constructor *****************/
    public ShopIO(Shop shop, Hero Player)
    {
      super("Shop");
      this.shop = shop;
      this.Player = Player;
      setMaximumSize(myDimension);
      setMinimumSize(myDimension);
      setSize(myDimension);
      setLayout(new GridLayout(5,1));
      //----------1st row------------
      PlayerGoldAmount = new Label("Coins: " + Player.getCoins()); 
      add(PlayerGoldAmount);
      //----------2nd row (image row)------------
      OutputImage = new ImageIcon("ClosedChest.png");
      ImageShown = new JLabel(OutputImage);
      add(ImageShown);
      //----------3rd row (Button Row)------------
      ButtonPanel = new Panel(new GridLayout(1,2));
      //buy chest button 
      Button BuyChestButton = new Button("BuyChest");
      BuyChestButton.addActionListener(this);
      //return button 
      Button ExitButton = new Button("Exit");
      ExitButton.addActionListener(this);
      ButtonPanel.add(BuyChestButton); ButtonPanel.add(ExitButton);
      add(ButtonPanel);
      //---------- 4th row (Buttons row) -------
      Button BuySharpness = new Button("SharpenAllKnives");
      BuySharpness.addActionListener(this);
      ItemEnhanceButtonsPanel.add(BuySharpness);
      
      Button BuyTraining = new Button("Training");
      BuyTraining.addActionListener(this);
      ItemEnhanceButtonsPanel.add(BuyTraining);
      
      Button BuyShieldPerk = new Button("StrongerShield");
      BuyShieldPerk.addActionListener(this);
      ItemEnhanceButtonsPanel.add(BuyShieldPerk);
      
      add(ItemEnhanceButtonsPanel);
      //----------5th row (Button Row)------------
      OutputMessage = new Label("");
      add(OutputMessage);
      //-------------------------------
      pack();
      show();
    }
    public void chosenBuy()
    {
        try{
             shop.buyChest(Player, this);
             ImageShown.setIcon(ChestOpen);
             PlayerGoldAmount.setText("Coins: " + Player.getCoins());
             timer.schedule(SchedulerTask, 1500);
        }
        catch(InsufficientFundsException e)
        {
            OutputMessage.setText(e.getMessage());
        }
    }
    
    //for buying training
    public void chosenTraining()
    {
        try{
            shop.BuyTraining(Player);
            OutputMessage.setText("Training Course Complete!");
            PlayerGoldAmount.setText("Coins: " + Player.getCoins());
        }
        catch(InsufficientFundsException e)
        {
            OutputMessage.setText(e.getMessage());
        }
        catch(ItemsException e)
        {
            OutputMessage.setText(e.getMessage());
        }    
    }
    
    //for getting all knives in inventory sharpened
    public void chosenSharpness()
    {
        try{
            shop.BuySharpness(Player);
            OutputMessage.setText("All Knives in inventory Sharpened!");
            PlayerGoldAmount.setText("Coins: " + Player.getCoins());
        }
        catch(InsufficientFundsException e)
        {
            OutputMessage.setText(e.getMessage());
        }
        catch(ItemsException e)
        {
            OutputMessage.setText(e.getMessage());
        }  
    }

    //for making first shield stronger
    public void chosenStrongerShield()
    {
        try{
            shop.BuyShieldPerk(Player);
            OutputMessage.setText("The first shield in inventory is stronger!");
            PlayerGoldAmount.setText("Coins: " + Player.getCoins());
        }
        catch(InsufficientFundsException e)
        {
            OutputMessage.setText(e.getMessage());
        }
        catch(ItemsException e)
        {
            OutputMessage.setText(e.getMessage());
        }  
    }
    
    //setter for chest output label
    public void setMessage(String Message)
    {
        OutputMessage.setText(Message);
    }
    /********** ActionListener implemented method **************/
    public void actionPerformed(ActionEvent evt)
    {
        String Command = evt.getActionCommand();
        if(Command.equals("BuyChest"))
        {
            chosenBuy();
        }
        else if(Command.equals("Training"))
        {
            chosenTraining();
        }
        else if(Command.equals("SharpenAllKnives"))
        {
            chosenSharpness();
        }
        else if(Command.equals("StrongerShield"))
        {
            chosenStrongerShield();
        }  
        else if(Command.equals("Exit"))
        {
            dispose();
        }
    }
    /************** Inner Class *****************/
    public class ChangeImageToClosed extends TimerTask
    {
        public void run(){
            ImageShown.setIcon(ChestClosed);
            if(Player.getCoins() == 0)
            {
                dispose();
            }
        }
    }                 
}
