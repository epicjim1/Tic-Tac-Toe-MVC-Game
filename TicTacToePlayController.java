/** TicTacToePlayController class
  * The controller for each playable TicTacToe button for the players turn.
  * Last Modified: 20/01/2023
  * @author  Abhishek Luthra
  */ 

import javax.swing.*;
import java.awt.event.*;

public class TicTacToePlayController implements ActionListener
{
     //Variable Declarations
     private TicTacToeModel game;        //The Model used to describe the game
     private JButton myBtn;              //A button in the main grid of TicTacToe buttons
     
     /** Default constructor
       * Links the component to the Model
       * @param aGame            The Model describing game behaviour
       * @param thisButton       A button in the main grid of TicTacToe buttons
       */ 
     public TicTacToePlayController(TicTacToeModel aGame, JButton thisButton)
     {
          this.game = aGame;
          this.myBtn = thisButton;
     }
     
     /** Places the players symbol on the button pressed
       * @param e      The event sent from the button that was clicked
       */ 
     public void actionPerformed(ActionEvent e)
     {
          game.playerMove(myBtn);
     }
}