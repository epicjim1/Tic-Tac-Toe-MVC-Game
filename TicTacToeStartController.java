/** TicTacToeStartController class
  * The controller for configuring a games settings in the start panel.
  * Last Modified: 20/01/2023
  * @author  Abhishek Luthra
  */ 

import javax.swing.*;
import java.awt.event.*;

public class TicTacToeStartController implements ActionListener
{
     //Variable Declarations
     private TicTacToeModel game;              //The Model used to describe the game
     private JButton myStartBtn;               //Button for using the settings and starting the round
     private JButton myEasyBtn;                //Button for switching the game to easy mode
     private JButton myHardBtn;                //Button for switching the game to hard mode
     private JButton myImpossibleBtn;          //
     private JTextField myRoundChooser;        //TextField for choosing the number of rounds the game will be
     
     private int maxRoundNum = 10;             //The maximum number of rounds a user can select
     
     /** Default constructor
       * Links the component to the Model
       * @param aGame             The Model describing game behaviour
       * @param startBtn          Button for using the settings and starting the round
       * @param easyBtn           Button for switching the game to easy mode
       * @param hardBtn           Button for switching the game to hard mode
       * @param roundChooser      TextField for choosing the number of rounds the game will be
       */  
     public TicTacToeStartController(TicTacToeModel aGame, JButton startBtn, JButton easyBtn, JButton hardBtn, JTextField roundChooser, JButton impossibleBtn)
     {
          this.game = aGame;
          this.myStartBtn = startBtn;
          this.myEasyBtn = easyBtn;
          this.myHardBtn = hardBtn;
          this.myRoundChooser = roundChooser;
          this.myImpossibleBtn = impossibleBtn;
     }
     
     /** Allows the different buttons to do their task
       * @param e      The event sent from the button that was clicked
       */ 
     public void actionPerformed(ActionEvent e)
     {
          //If startBtn was pressed - determine number of rounds, mode and then start round
          if (e.getSource() == myStartBtn)
          {
               //Check if textfield has an int between 1 and 10
               try
               {
                    int roundNum = Integer.parseInt(this.myRoundChooser.getText()); //get int from text field
                    
                    //Checks if roundNum is in the range of desired rounds
                    if (roundNum > 0 && roundNum <= maxRoundNum)
                    {
                         //If either easy, hard button, or impossible button is clicked
                         if ((!myEasyBtn.isEnabled() && myHardBtn.isEnabled() && myImpossibleBtn.isEnabled()) ||
                             (myEasyBtn.isEnabled() && !myHardBtn.isEnabled() && myImpossibleBtn.isEnabled()) ||
                             (myEasyBtn.isEnabled() && myHardBtn.isEnabled() && !myImpossibleBtn.isEnabled()))
                         {
                              //Check if a easy button has been clicked
                              if (!myEasyBtn.isEnabled() && myHardBtn.isEnabled()&& myImpossibleBtn.isEnabled())
                              {
                                   game.setGameMode(0);
                              }
                              //Check if a hard button has been clicked
                              else if (myEasyBtn.isEnabled() && !myHardBtn.isEnabled()&& myImpossibleBtn.isEnabled())
                              {
                                   game.setGameMode(1);
                              }
                              //Check if a impossible button has been clicked
                              else if (myEasyBtn.isEnabled() && myHardBtn.isEnabled()&& !myImpossibleBtn.isEnabled())
                              {
                                   game.setGameMode(2);
                              }
                              
                              game.setNumOfRounds(roundNum); //Sets number of rounds
                              game.setVisiblePanel(4);
                         }
                         else
                         {
                              this.myRoundChooser.setText("Select a game mode"); //a game mode button wasnt clicked
                         }
                    }
                    else
                    {
                         this.myRoundChooser.setText("Enter a number, 1-10"); //roundNum wasnt in range
                    }
               }
               //textfield didnt have an int
               catch (NumberFormatException ex)
               {
                    this.myRoundChooser.setText("Enter a number, 1-10");
               }
          }
          //If easyBtn was pressed - disable the easy button and enable the hard button
          else if (e.getSource() == myEasyBtn)
          {
               myEasyBtn.setEnabled(false);
               
               //If hardBtn is disabled, enable it
               if (!myHardBtn.isEnabled())
               {
                    myHardBtn.setEnabled(true);
               }
               
               //If impossibleBtn is disabled, enable it
               if (!myImpossibleBtn.isEnabled())
               {
                    myImpossibleBtn.setEnabled(true);
               }
          }
          //If hardBtn was pressed - disable the hard button and enable the easy button
          else if (e.getSource() == myHardBtn)
          {
               myHardBtn.setEnabled(false);
               
               //If easyBtn is disabled, enable it
               if (!myEasyBtn.isEnabled())
               {
                    myEasyBtn.setEnabled(true);
               }
               
               //If impossibleBtn is disabled, enable it
               if (!myImpossibleBtn.isEnabled())
               {
                    myImpossibleBtn.setEnabled(true);
               }
          }
          //If impossibleBtn was pressed - disable the hard button and enable the easy button
          else if (e.getSource() == myImpossibleBtn)
          {
               myImpossibleBtn.setEnabled(false);
               
               //If easyBtn is disabled, enable it
               if (!myEasyBtn.isEnabled())
               {
                    myEasyBtn.setEnabled(true);
               }
               
               //If hardBtn is disabled, enable it
               if (!myHardBtn.isEnabled())
               {
                    myHardBtn.setEnabled(true);
               }
          }
     }
}