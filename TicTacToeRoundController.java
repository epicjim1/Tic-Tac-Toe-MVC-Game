/** TicTacToeRoundController class
  * The controller for the buttons every round of the game.
  * Last Modified: 20/01/2023
  * @author  Abhishek Luthra
  */ 

import javax.swing.*;
import java.awt.event.*;

public class TicTacToeRoundController implements ActionListener
{
     //Variable Declarations
     private TicTacToeModel game;            //The Model used to describe the game
     private JButton myNextRoundBtn;         //Button to start the next round in the current game
     private JButton myExitBtn;              //Button for exiting the game
     private JButton myResultsBtn;           //Button in the last round to see the current game results
     private JButton[][] myCells;            //The playable grid of buttons of the game
     
     /** Default constructor
       * Links the component to the Model
       * @param aGame            The Model describing game behaviour
       * @param nextRoundBtn     Button to start the next round in the current game
       * @param exitBtn          Button for exiting the game
       * @param resultsBtn       Button in the last round to see the current game results
       * @param cells            The playable grid of buttons of the game
       */  
     public TicTacToeRoundController(TicTacToeModel aGame, JButton nextRoundBtn, JButton exitBtn, JButton resultsBtn, JButton[][] cells)
     {
          this.game = aGame;
          this.myNextRoundBtn = nextRoundBtn;
          this.myExitBtn = exitBtn;
          this.myResultsBtn = resultsBtn;
          this.myCells = cells;
     }
     
     /** Allows the different buttons to do their task
       * @param e      The event sent from the button that was clicked
       */ 
     public void actionPerformed(ActionEvent e)
     {
          //If nextRoundBtn was pressed - reset the round
          if (e.getSource() == myNextRoundBtn)
          {
               game.resetRound(myCells);
          }
          //If resultsBtn was pressed - switchs to game end panel
          else if (e.getSource() == myResultsBtn)
          {
               game.setVisiblePanel(5);
          }
          //If exitBtn was pressed - exits the program
          else if (e.getSource() == myExitBtn)
          {
               game.exit();
          }
     }
}