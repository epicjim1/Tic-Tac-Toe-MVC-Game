/**  A Tic Tac Toe Game
*    Player and computer alternate turns trying to align 3 of their symbolsan X or O.  whomever wins the most rounds wins the game
*    Last Modified: 20/01/2023
*    @author Abhishek Luthra
*/ 

import javax.swing.*;

public class TicTacToe
{
     public static void main (String [] args)
     {
          TicTacToeModel game = new TicTacToeModel();          //The game model
          TicTacToeGUI mainScreen = new TicTacToeGUI(game);    //The game view
          
          //Initialize the Frame
          JFrame f = new JFrame("TicTacToe Game");
          f.setSize(400,470);
          f.setResizable(false);
          //f.setMinimumSize(new Dimension(400, 480));
          f.setLocation(300,200);
          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          f.setContentPane(mainScreen);
          f.setVisible(true);
     }
}