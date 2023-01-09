/** TicTacToeGameEndController class
  * The controller for the buttons at the end of a game
  * Last Modified: 20/01/2023
  * @author  Abhishek Luthra
  */ 

import javax.swing.*;
import java.awt.event.*;

public class TicTacToeGameEndController implements ActionListener
{
     //Variable Declarations
     private TicTacToeModel game;      //The Model used to describe the game
     private JButton myNewGame;        //Button for creating a new game
     private JButton myExitBtn;        //Button for exiting the game
     private JButton[][] myCells;      //The playable grid of buttons of the game
     private JButton myMenuBtn;        //Button for going back to the menu
     
     /** Default constructor
       * Links the component to the Model
       * @param aGame          The Model describing game behaviour
       * @param newGame        Button for creating a new game
       * @param exitBtn        Button for exiting the game
       * @param cells          The playable grid of buttons of the game
       * @param menuBtn        Button for going back to the menu
       */ 
     public TicTacToeGameEndController(TicTacToeModel aGame, JButton newGame, JButton exitBtn, JButton[][] cells, JButton menuBtn)
     {
          this.game = aGame;
          this.myNewGame = newGame;
          this.myExitBtn = exitBtn;
          this.myCells = cells;
          this.myMenuBtn = menuBtn;
     }
     
     /** Allows the different buttons to do their task
       * @param e      The event sent from the button that was clicked
       */ 
     public void actionPerformed(ActionEvent e)
     {
          //If newGame was pressed - a new game starts
          if (e.getSource() == myNewGame)
          {
               game.resetGame(myCells);
          }
          //If menuBtn was pressed - switchs to menu panel
          else if (e.getSource() == myMenuBtn)
          {
               game.setVisiblePanel(1);
          }
          //If exitBtn was pressed - exits the porgram
          else if (e.getSource() == myExitBtn)
          {
               game.exit();
          }
     }
}