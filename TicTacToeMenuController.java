/** TicTacToeMenuController class
  * The controller for the buttons in the menu, also allows user to view txt files
  * Last Modified: 20/01/2023
  * @author  Abhishek Luthra
  */ 

import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class TicTacToeMenuController implements ActionListener
{
     //Variable Declarations
     private TicTacToeModel game;           //The Model used to describe the game
     private JButton myPlayBtn;             //Button for going to settings and starting a new game
     private JButton myPastResultsBtn;      //The component being interacted with
     private JButton myExitBtn;             //Button for exiting the game
     private JButton[][] myCells;           //The playable grid of buttons of the game
     private JButton myFileOpener;          //Button for opening a file
     private JButton myBackBtn;             //Button for going back to menu
     private JTextArea myFileView;          //TextArea for displaying file contents
     private JButton myAudioBtn;            //Button for toggling audio
     private Clip myAudioClip;              //Clip of audio thats playing in GUI
     
     private boolean hasPlayedOnce = false;
     private int currGameNum;
     
     /** Default constructor
       * Links the component to the Model
       * @param aGame           The Model describing game behaviour
       * @param playGame        Button for going to settings and starting a new game
       * @param pastResults     The component being interacted with
       * @param exitBtn         Button for exiting the game
       * @param cells           The playable grid of buttons of the game
       * @param fileOpener      Button for opening a file
       * @param backBtn         Button for going back to menu
       * @param fileView        TextArea for displaying file contents
       * @param audioBtn        Button for toggling audio
       * @param audioClip       Clip of audio thats playing in GUI
       */ 
     public TicTacToeMenuController(TicTacToeModel aGame, JButton playGame, JButton pastResults, JButton exitBtn, JButton[][] cells, JButton fileOpener, JButton backBtn, JTextArea fileView, JButton audioBtn, Clip audioClip)
     {
          this.game = aGame;
          this.myPlayBtn = playGame;
          this.myPastResultsBtn = pastResults;
          this.myExitBtn = exitBtn;
          this.myCells = cells;
          this.myFileOpener = fileOpener;
          this.myBackBtn = backBtn;
          this.myFileView = fileView;
          this.myAudioBtn = audioBtn;
          this.myAudioClip = audioClip;
     }
     
     /** Allows the different buttons to do their task
       * @param e      The event sent from the button that was clicked
       */ 
     public void actionPerformed(ActionEvent e)
     {
          //If playBtn was pressed - switchs to settings
          if (e.getSource() == myPlayBtn)
          {
               currGameNum = game.getCurrentGameNum(); //Stores the current game number
               
               //Checks if playBtn has been pressed once and user went back to menu from last game
               if (hasPlayedOnce == true && game.getCurrentGameNum() == currGameNum)
               {
                    game.resetGame(myCells);
               }
               
               game.setVisiblePanel(3);
               hasPlayedOnce = true;
          }
          //If pastResultsBtn was pressed - switchs to file view panel
          else if (e.getSource() == myPastResultsBtn)
          {
               game.setVisiblePanel(2);
          }
          //If audioBtn was pressed - toggle audio and image
          else if (e.getSource() == myAudioBtn)
          {
               //If audio is playing - switch image and turn audio off
               if (myAudioClip.isActive())
               {
                    myAudioBtn.setIcon(new ImageIcon("audioIconOn.png"));
                    myAudioClip.stop();
               }
               //If audio is not playing - switch image and turn audio on where it left off
               else
               {
                    myAudioBtn.setIcon(new ImageIcon("audioIconOff.png"));
                    myAudioClip.loop(Clip.LOOP_CONTINUOUSLY);
               }
          }
          //If exitBtn was pressed - exits the program
          else if (e.getSource() == myExitBtn)
          {
               game.exit();
          }
          //If backBtn was pressed - switchs from fileView to Menu
          else if (e.getSource() == myBackBtn)
          {
               game.setVisiblePanel(1);
          }
          //If fileOpener was pressed - opens choosen file and prints it to fileView
          else if (e.getSource() == myFileOpener)
          {
               game.openAndViewFile(myFileView);
          }
     }//end of actionPreformed
}