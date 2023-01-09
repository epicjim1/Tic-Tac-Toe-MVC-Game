/**  TicTacToeModel Class
*    The model for TicTacToe, does various calculations for a TicTacToe game
*    Last Modified: 20/01/2023
*    @author Abhishek Luthra
*/ 

import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

public class TicTacToeModel extends Object
{
     //Variable Declarations
     private TicTacToeGUI gameView;                //The view for TicTacToe
     
     private int gameMode;                         //The game mode for a current game, either easy or hard
     private String gameModeName;                  //The game mode selected for a current game, used for output
     
     private int numOfRounds;                      //The number of rounds the user will play in the current game
     private int currentRound = 1;                 //The current round the user is on, starts from 1
     private int currentGameNum = 1;               //The current game the user is on, starts from 1
     
     private String player;                        //The players symbol for the game
     private String computer;                      //The computers symbol for the game
     private boolean isPlayerTurn;                 //If it is currently the players turn
     
     private int playerWins = 0;                   //The total amount of wins the player has in the current game
     private int computerWins = 0;                 //The total amount of wins the computer has in the current game
     private int roundTies = 0;                    //The total amount of ties have happened in the current game
     
     private int playerMovesMade = 0;              //The amount of moves the player made in a game
     private int computerMovesMade = 0;            //The amount of moves the computer made in a game
     private int totalMovesMade = 0;               //The amount of moves both the player and computer made in a game
     
     private int cheatChance;                      //The percentage chance of the computer cheating only in impossible mode, between 0 and 100
     private int computerCheatCount = 0;           //The amount of time rhe computer has cheated
     
     private String gameWinner = "";               //The winner of the current game
     private String roundWinner;                   //The winner of the current round
     
     private String endImgName = "tie.jpg";        //The name of file that is displayed at the end of every game, default to tie image
     private String fileName;                      //The name of the file that the game results are saved to
     private String gameStats = "";                //The data that will be inside the results file
     
     private int visiblePanel = 1;                 //The panel that is visible to the user, 1-Menu 2-FileView 3-Settings 4-Round 5-GameEnd
     
     /** Creates a default TicTacToe game with X being the player and O being the computer,
       * Defaults the results file name and chance of computer cheating
       */
     public TicTacToeModel()
     {
          super();
          player = "X";
          computer = "O";
          fileName = "GameResults " + java.time.LocalDate.now() + ".txt";
          cheatChance = 0;
     }
     
     /** Sets the view for the game
       * @param currentGUI        The View used to display the game
       */ 
     public void setGUI(TicTacToeGUI currentGUI)
     {
          this.gameView = currentGUI;
     }
     
     //--------------------ACCESSOR METHODS GET--------------------//
     /** Returns the current game mode, as a String
       * @return gameMode*/
     public String getGameModeName()
     {
          return this.gameModeName;
     }
     /** Returns the total number of rounds for the current game, as an int
       * @return numOfRounds*/
     public int getNumOfRounds()
     {
          return this.numOfRounds;
     }
     /** Returns the current round in the current game, as an int
       * @return currentRound*/
     public int getCurrentRound()
     {
          return this.currentRound;
     }
     /** Returns the current game the user is on, as an int
       * @return currentGameNum*/
     public int getCurrentGameNum()
     {
          return this.currentGameNum;
     }
     /** Returns the file name for an image to be displayed in the end game panel, as a String
       * @return endImgName*/
     public String getEndImgName()
     {
          return this.endImgName;
     }
     /** Returns if it is currently the players turn to move, as a boolean
       * @return isPlayerTurn*/
     public boolean getIsPlayerTurn()
     {
          return this.isPlayerTurn;
     }
     /** Returns the total amount of wins for the player in a game, as an int
       * @return playerWins*/
     public int getPlayerWins()
     {
          return this.playerWins;
     }
     /** Returns the total amount of wins for the computer in a game, as an int
       * @return computerWins*/
     public int getComputerWins()
     {
          return this.computerWins;
     }
     /** Returns the total amount of ties in a game, as an int
       * @return roundTies*/
     public int getRoundTies()
     {
          return this.roundTies;
     }
     /** Returns the amount of moves the player made in a game, as an int
       * @return playerMovesMade*/
     public int getPlayerMovesMade()
     {
          return this.playerMovesMade;
     }
     /** Returns the amount of moves the computer made in a game, as an int
       * @return computerMovesMade*/
     public int getComputerMovesMade()
     {
          return this.computerMovesMade;
     }
     /** Returns the amount of moves both the player and computer made in a game, as an int
       * @return totalMovesMade*/
     public int getTotalMovesMade()
     {
          return this.totalMovesMade;
     }
     /** Returns the amount of time rhe computer has cheated, as an int
       * @return computerCheatCount*/
     public int getComputerCheatCount()
     {
          return this.computerCheatCount;
     }
     /** Returns the winner of the current game, as a String
       * @return gameWinner*/
     public String getGameWinner()
     {
          return this.gameWinner;
     }
     /** Returns the winner of the current round, as a String
       * @return gameEndStatus*/
     public String getRoundWinner()
     {
          return this.roundWinner;
     }
     /** Returns the panel that should be vivible, as an int
       * @return visiblePanel*/
     public int getVisiblePanel()
     {
          return this.visiblePanel;
     }
     
     //--------------------ACCESSOR METHODS SET--------------------//
     /** Allows user to sets the game mode
       * @param modeNum - the users mode selection
       * 0 - easy
       * 1 - hard
       * 2 - impossible*/
     public void setGameMode(int modeNum)
     {
          this.gameMode = modeNum;
          
          //Sets the mode name
          if (gameMode == 0)
          {
               gameModeName = "Easy";
          }
          else if (gameMode == 1)
          {
               gameModeName = "Hard";
          }
          else
          {
               gameModeName = "Impossible";
          }
     }
     /** Allows user to set the number of rounds
       * @param num - the users number of rounds selection*/
     public void setNumOfRounds(int num)
     {
          this.numOfRounds = num;
     }
     /** Sets with panel in the view is visible
       * @param num - the users selection of visible panel*/
     public void setVisiblePanel(int num)
     {
          this.visiblePanel = num;
          this.updateView();
     }
     
     //--------------------FUNCTION METHODS--------------------//
     /** Determines who starts a round */
     public void startingTurn()
     {
          int randomNum = (int)(Math.random() * 2);
          
          //If number is 0 its computers turn
          if (randomNum == 0)
          {
               isPlayerTurn = true;
          }
          //If number is 1 its players turn
          else
          {
               isPlayerTurn = false;
          }
     }
     
     /** Makes the player move on the button pressed, if in impossible mode the computer will move on the button pressed
       * @param thisButton - the button that the user pressed*/
     public void playerMove(JButton thisButton)
     {
          //Checks if it currently the players turn
          if (getIsPlayerTurn())
          {
               //If the game is in impossible mode
               if (gameMode == 2)
               {
                    int randomNum = (int)(Math.random() * 101);
                    
                    //If number is 0 computer takes over players move
                    if (randomNum <= cheatChance)
                    {
                         thisButton.setText(computer);
                         thisButton.setEnabled(false);
                         
                         computerCheatCount += 1;
                         computerMovesMade += 1;
                         System.out.println("CHEATED"); //Debug
                    }
                    //If number is 1 computer doesnt cheat
                    else
                    {
                         thisButton.setText(player);
                         thisButton.setEnabled(false);
                         
                         playerMovesMade += 1;
                    }
               }
               //Game is not in impossible mode
               else
               {
                    thisButton.setText(player);
                    thisButton.setEnabled(false);
                    
                    playerMovesMade += 1;
               }
               
               totalMovesMade += 1;
               isPlayerTurn = false;
               
               this.updateView();
          }
     }
     
     /** Makes the computer move based on the difficult choosen
       * Easy Mode - Computer will randomly move
       * Hard/Impossible Mode - Computer will look for winning scenarios and scenarios to block player
       * @param cells - The playable grid of buttons of the game*/
     public void computerMove(JButton[][] cells)
     {
          //Game is in Easy Mode
          if (gameMode == 0)
          {
               boolean compHasMoved = false; //flag if computer moved
               
               //Keep randomizing until empty spot found
               while(!compHasMoved)
               {
                    int randomRow = (int)(Math.random() * 3);
                    int randomCol = (int)(Math.random() * 3);
                    
                    //If empty spot found
                    if(cells[randomRow][randomCol].getText().equals(""))
                    {
                         cells[randomRow][randomCol].setText(computer);
                         cells[randomRow][randomCol].setEnabled(false);
                         compHasMoved = true;
                    }
               }
          }
          //Game is in Hard Mode or Impossible Mode
          else if (gameMode == 1 || gameMode == 2)
          {
               int[] bestMove = computerHardMove(cells); //Finds the best move to currently make
               
               //DEBUG - If method gave an empty spot, it should
               if(cells[bestMove[0]][bestMove[1]].getText().equals(""))
               {
                    cells[bestMove[0]][bestMove[1]].setText(computer);
                    cells[bestMove[0]][bestMove[1]].setEnabled(false);
               }
               //Method did not give empty spot, this should never trigger
               else
               {
                    System.out.println("METHOD DIDNT WORK, computer skipped turn");
               }
          }
          
          System.out.println("Computer has moved");
          computerMovesMade += 1;
          totalMovesMade += 1;
          isPlayerTurn = true;
          
          this.updateView();
     }
     
     /** Computer will look for all winning scenarios and all scenarios to block player
       * @param cells - The playable grid of buttons of the game
       * @return bestMove - Returns a pair of numbers for the best move to make, as an int array*/
     private int[] computerHardMove(JButton[][] cells)
     {
          int[] bestMove = {1, 1};
          
          //Checking for all scenarios to block player, this is before win checking because its less of a priority
          for (int i = 0; i < 3; i++)
          {
               //COLLUM/VERTICAL BLOCKS
               //scenario 1 - player needs top
               if ((cells[0][i].getText().equals("") && cells[1][i].getText().equals(player) && cells[2][i].getText().equals(player)))
               {
                    bestMove[0] = 0;
                    bestMove[1] = i;
               }
               //scenario 2 - player need mid
               else if ((cells[0][i].getText().equals(player) && cells[1][i].getText().equals("") && cells[2][i].getText().equals(player)))
               {
                    bestMove[0] = 1;
                    bestMove[1] = i;
               }
               //scenario 3 - player needs bot
               else if ((cells[0][i].getText().equals(player) && cells[1][i].getText().equals(player) && cells[2][i].getText().equals("")))
               {
                    bestMove[0] = 2;
                    bestMove[1] = i;
               }
               //ROW/HORIZONTAL BLOCKS
               //scenrio 1 - player needs left
               else if ((cells[i][0].getText().equals("") && cells[i][1].getText().equals(player) && cells[i][2].getText().equals(player)))
               {
                    bestMove[0] = i;
                    bestMove[1] = 0;
               }
               //scenrio 2 - player needs center
               else if ((cells[i][0].getText().equals(player) && cells[i][1].getText().equals("") && cells[i][2].getText().equals(player)))
               {
                    bestMove[0] = i;
                    bestMove[1] = 1;
               }
               //scenrio 3 - player needs right
               else if ((cells[i][0].getText().equals(player) && cells[i][1].getText().equals(player) && cells[i][2].getText().equals("")))
               {
                    bestMove[0] = i;
                    bestMove[1] = 2;
               }
          }
          
          //DIAGONAL BLOCK - Top to down
          //scenrio 1 - player needs topleft
          if ((cells[0][0].getText().equals("") && cells[1][1].getText().equals(player) && cells[2][2].getText().equals(player)))
          {
               bestMove[0] = 0;
               bestMove[1] = 0;
          }
          //scenrio 2 - player needs center
          else if ((cells[0][0].getText().equals(player) && cells[1][1].getText().equals("") && cells[2][2].getText().equals(player)))
          {
               bestMove[0] = 1;
               bestMove[1] = 1;
          }
          //scenrio 3 - player needs bottomright
          else if ((cells[0][0].getText().equals(player) && cells[1][1].getText().equals(player) && cells[2][2].getText().equals("")))
          {
               bestMove[0] = 2;
               bestMove[1] = 2;
          }
          //DIAGONAL BLOCK - Down to top
          //scenrio 1 - player needs topright
          else if ((cells[2][0].getText().equals("") && cells[1][1].getText().equals(player) && cells[0][2].getText().equals(player)))
          {
               bestMove[0] = 2;
               bestMove[1] = 0;
          }
          //scenrio 2 - player needs center
          else if ((cells[2][0].getText().equals(player) && cells[1][1].getText().equals("") && cells[0][2].getText().equals(player)))
          {
               bestMove[0] = 1;
               bestMove[1] = 1;
          }
          //scenrio 3 - player needs bottomleft
          else if ((cells[2][0].getText().equals(player) && cells[1][1].getText().equals(player) && cells[0][2].getText().equals("")))
          {
               bestMove[0] = 0;
               bestMove[1] = 2;
          }
          
          
          
          //Checking for all scenarios to win, this is after block checking because its more of a priority
          for (int i = 0; i < 3; i++)
          {
               //COLLUM/VERTICAL BLOCKS
               //scenario 1 - computer needs top
               if ((cells[0][i].getText().equals("") && cells[1][i].getText().equals(computer) && cells[2][i].getText().equals(computer)))
               {
                    bestMove[0] = 0;
                    bestMove[1] = i;
               }
               //scenario 2 - computer need mid
               else if ((cells[0][i].getText().equals(computer) && cells[1][i].getText().equals("") && cells[2][i].getText().equals(computer)))
               {
                    bestMove[0] = 1;
                    bestMove[1] = i;
               }
               //scenario 3 - computer needs bot
               else if ((cells[0][i].getText().equals(computer) && cells[1][i].getText().equals(computer) && cells[2][i].getText().equals("")))
               {
                    bestMove[0] = 2;
                    bestMove[1] = i;
               }
               //ROW/HORIZONTAL BLOCKS
               //scenrio 1 - computer needs left
               else if ((cells[i][0].getText().equals("") && cells[i][1].getText().equals(computer) && cells[i][2].getText().equals(computer)))
               {
                    bestMove[0] = i;
                    bestMove[1] = 0;
               }
               //scenrio 2 - computer needs center
               else if ((cells[i][0].getText().equals(computer) && cells[i][1].getText().equals("") && cells[i][2].getText().equals(computer)))
               {
                    bestMove[0] = i;
                    bestMove[1] = 1;
               }
               //scenrio 3 - computer needs right
               else if ((cells[i][0].getText().equals(computer) && cells[i][1].getText().equals(computer) && cells[i][2].getText().equals("")))
               {
                    bestMove[0] = i;
                    bestMove[1] = 2;
               }
          }
          
          //DIAGONAL WIN - Top to down
          //scenrio 1 - computer needs topleft
          if ((cells[0][0].getText().equals("") && cells[1][1].getText().equals(computer) && cells[2][2].getText().equals(computer)))
          {
               bestMove[0] = 0;
               bestMove[1] = 0;
          }
          //scenrio 2 - computer needs center
          else if ((cells[0][0].getText().equals(computer) && cells[1][1].getText().equals("") && cells[2][2].getText().equals(computer)))
          {
               bestMove[0] = 1;
               bestMove[1] = 1;
          }
          //scenrio 3 - computer needs bottomright
          else if ((cells[0][0].getText().equals(computer) && cells[1][1].getText().equals(computer) && cells[2][2].getText().equals("")))
          {
               bestMove[0] = 2;
               bestMove[1] = 2;
          }
          //DIAGONAL WIN - Down to top
          //scenrio 1 - computer needs topright
          else if ((cells[2][0].getText().equals("") && cells[1][1].getText().equals(computer) && cells[0][2].getText().equals(computer)))
          {
               bestMove[0] = 2;
               bestMove[1] = 0;
          }
          //scenrio 2 - computer needs center
          else if ((cells[2][0].getText().equals(computer) && cells[1][1].getText().equals("") && cells[0][2].getText().equals(computer)))
          {
               bestMove[0] = 1;
               bestMove[1] = 1;
          }
          //scenrio 3 - computer needs bottomleft
          else if ((cells[2][0].getText().equals(computer) && cells[1][1].getText().equals(computer) && cells[0][2].getText().equals("")))
          {
               bestMove[0] = 0;
               bestMove[1] = 2;
          }
          
          //Randomly decide best move if there is no best move 
          while (cells[bestMove[0]][bestMove[1]].getText().equals("") == false)
          {
               bestMove[0] = (int)(Math.random() * 3);
               bestMove[1] = (int)(Math.random() * 3);
          }
          
          return bestMove;
     }
     
     /** Determines if the current round is over or not
       * @param cells - The playable grid of buttons of the game
       * @return Returns true if the round is over, false if it is not*/
     public boolean isRoundOver(JButton[][] cells)
     {
          int gameOverStatus = 0; //Used to who won the round. 0 - Default, 1 - Player wins, 2 - Computer wins
          
          // Check all rows
          for (int i = 0; i < 3; i++)
          {
               //Check for players symbol - X
               if (cells[i][0].getText().equals(player) && cells[i][1].getText().equals(player) && cells[i][2].getText().equals(player))
               {
                    gameOverStatus = 1;
               }
               //Check for computers symbol - O
               else if (cells[i][0].getText().equals(computer) && cells[i][1].getText().equals(computer) && cells[i][2].getText().equals(computer))
               {
                    gameOverStatus = 2;
               }
          }
          
          // Check all columns
          for (int j = 0; j < 3; j++)
          {
               //Check for players symbol - X
               if (cells[0][j].getText().equals(player) && cells[1][j].getText().equals(player) && cells[2][j].getText().equals(player))
               {
                    gameOverStatus = 1;
               }
               //Check for computers symbol - O
               else if (cells[0][j].getText().equals(computer) && cells[1][j].getText().equals(computer) && cells[2][j].getText().equals(computer))
               {
                    gameOverStatus = 2;
               }
          }
          
          // Check all diagonals
          //Check for players symbol - X
          if (cells[0][0].getText().equals(player) && cells[1][1].getText().equals(player) && cells[2][2].getText().equals(player))
          {
               gameOverStatus = 1;
          }
          //Check for computers symbol - O
          else if (cells[0][0].getText().equals(computer) && cells[1][1].getText().equals(computer) && cells[2][2].getText().equals(computer))
          {
               gameOverStatus = 2;
          }
          
          //Check for players symbol - X
          if (cells[0][2].getText().equals(player) && cells[1][1].getText().equals(player) && cells[2][0].getText().equals(player))
          {
               gameOverStatus = 1;
          }
          //Check for computers symbol - O
          else if (cells[0][2].getText().equals(computer) && cells[1][1].getText().equals(computer) && cells[2][0].getText().equals(computer))
          {
               gameOverStatus = 2;
          }
          
          //If round is over in any way return true
          if (gameOverStatus == 1 || gameOverStatus == 2 || isDraw(cells))
          {
               //If player won round
               if (gameOverStatus == 1)
               {
                    roundWinner = "Player has won";
                    playerWins += 1;
               }
               //If computer won round
               else if (gameOverStatus == 2)
               {
                    roundWinner = "Computer has won";
                    computerWins += 1;
               }
               //If round is a draw
               else if (isDraw(cells))
               {
                    roundWinner = "Game has been tied";
                    roundTies += 1;
               }
               
               //If this was the last round in a game, determine winner
               if (currentRound == numOfRounds)
               {
                    determineGameWinner();
               }
               //determineGameWinner(); //If results file was to keep track of each round
               
               return true;
          }
          
          return false;
     }
     
     /** Determines if the current round has ended in a draw
       * @param cells - The playable grid of buttons of the game
       * @return Returms true if there is draw, false if there isnt*/
     public boolean isDraw(JButton[][] cells)
     {
          //Checks all playble buttons
          for (int i = 0; i < 3; i++)
          {
               for (int j = 0; j < 3; j++)
               {
                    //If any button is empty, not a draw
                    if (cells[i][j].getText().equals(""))
                    {
                         return false;
                    }
               }
          }
          return true; //no empty buttons, it is a draw
     }
          
     /** Determines the winner of the current game */
     private void determineGameWinner()
     {
          //If the player has more round wins
          if (playerWins > computerWins)
          {
               gameWinner = "Player Won";
               endImgName = "win.png";
          }
          //If the computer has more round wins
          else if (playerWins < computerWins)
          {
               gameWinner = "Computer Won";
               endImgName = "lose.png";
          }
          //If the they both have the same round wins
          else
          {
               gameWinner = "Tied";
               endImgName = "tie.png";
          }
          
          //Only updates gameStats and prints file in last round
          if (currentRound == numOfRounds)
          {
               /*gameStats = gameStats.concat("Game: " + getCurrentGameNum() + "\nGame Status: " + getGameWinner() + "\nGame Mode: "+ getGameMode() +
                    "\n\nPlayer wins: " + getPlayerWins() + "\nComputer wins: " + getComputerWins() + "\nRounds Tied: " + getRoundTies() +
                    "\n\nPlayer Moves Made: " + getPlayerMovesMade() + "\nComputer Moves Made: " + getComputerMovesMade() + "\nTotal Moves Made: "
                    + getTotalMovesMade() + "\n\nTimes Computer Cheated: " + getComputerCheatCount() + "\n\nRounds Played: " + getNumOfRounds() + "\n\n\n");*/ //keeps track of entire sessions
               
               gameStats = "Game: " + getCurrentGameNum() + "\nGame Status: " + getGameWinner() + "\nGame Mode: "+ getGameModeName() +
                    "\n\nPlayer wins: " + getPlayerWins() + "\nComputer wins: " + getComputerWins() + "\nRounds Tied: " + getRoundTies() +
                    "\n\nPlayer Moves Made: " + getPlayerMovesMade() + "\nComputer Moves Made: " + getComputerMovesMade() + "\nTotal Moves Made: "
                    + getTotalMovesMade() + "\n\nTimes Computer Cheated: " + getComputerCheatCount() + "\n\nRounds Played: " + getNumOfRounds() + "\n\n\n";    //Keeps track of one game at a time
               
               printGameStatsToFile(); //Print to file
               //saveFile();           //If user wants to name and choose where to save file
          }
     }
     
     /** Resets the round for the next round
       * @param cells - The playable grid of buttons of the game*/
     public void resetRound(JButton[][] cells)
     {
          //Reset all buttons
          for (int i = 0; i < 3; i++)
          {
               for (int j = 0; j < 3; j++)
               {
                    cells[i][j].setEnabled(true);
                    cells[i][j].setText("");
               }
          }
          currentRound += 1;
          startingTurn();
          this.updateView();
     }
     
     /** Resets the game for a new game
       * @param cells - The playable grid of buttons of the game*/
     public void resetGame(JButton[][] cells)
     {
          //Reset all buttons
          for (int i = 0; i < 3; i++)
          {
               for (int j = 0; j < 3; j++)
               {
                    cells[i][j].setEnabled(true);
                    cells[i][j].setText("");
               }
          }
          
          playerWins = 0;
          computerWins = 0;
          
          playerMovesMade = 0;
          computerMovesMade = 0;
          totalMovesMade = 0;
          computerCheatCount = 0;
          
          currentRound = 1;
          currentGameNum += 1;
          
          setVisiblePanel(3); //goes to game options, updates view
     }
     
     /** Opens a text file the user desires and then prints its contents to a text area
       * @param fileView - The text are that the file contents are printed to*/
     public void openAndViewFile(JTextArea fileView)
     {
          fileView.setText(""); //Reset text to blank
          
          JFileChooser fileChooser = new JFileChooser();
          
          //Makes it so the user can only choose to open txt files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
          fileChooser.addChoosableFileFilter(filter);
          fileChooser.setFileFilter(filter);
          
          int returnVal = fileChooser.showOpenDialog(null);
          if (returnVal == JFileChooser.APPROVE_OPTION)  //If file was opened
          {
               File myFile = fileChooser.getSelectedFile();
               Scanner inFile = null;
               
               try
               {
                    inFile = new Scanner(myFile);
                    
                    //While there is text in file print to text area
                    while (inFile.hasNextLine())
                    {
                         String curLine = inFile.nextLine();
                         fileView.append(String.format("%s\n", curLine));
                    }
               }
               catch (IOException ex)
               {
                    JOptionPane.showMessageDialog(null, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
               }
          }
     }
     
     /** Allows user to name and choose where to save the game results file*/
     public void saveFile()
     {
          //Keep trying to save file until user has saved the file
          while(true)
          {
               JFileChooser fileChooser = new JFileChooser();
               
               //Defaults Dialog title and file type
               fileChooser.setDialogTitle("Save File");
               FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES (.txt)", "txt");
               fileChooser.addChoosableFileFilter(filter);
               fileChooser.setFileFilter(filter);
               
               int userSelection = fileChooser.showSaveDialog(null);
          
               //If user clicked save button
               if (userSelection == JFileChooser.APPROVE_OPTION)
               {
                    try
                    {
                         FileWriter fileToSave = new FileWriter(fileChooser.getSelectedFile() + ".txt");
                         fileToSave.write(gameStats);
                         fileToSave.close();
                         break;
                    }
                    catch (Exception ex)
                    {
                         System.out.println(ex);
                    }
               }
               //If user cancelled process
               else if (userSelection == JFileChooser.CANCEL_OPTION)
               {
                    System.out.println("canceled");
                    //System.exit(0);
               }
          }
     }
     
     /** Prints a games results to a file */
     public void printGameStatsToFile()
     {
          PrintWriter out = getPrintWriter(fileName);
          out.print(gameStats);
          System.out.println("File has been saved\n");  //Update the user
          out.close();
     }
     
     /** Configures the file name and checks for errors
       * @param fileName - The name of the file that the game results are saved to
       * @return PrintWriter - Returns a PrintWriter after configuring fileName and checking errors*/
     private PrintWriter getPrintWriter(String fileName)
     {
          //Checks if file name is vaild
          try
          {
               int fileCount = 1;
               File f = new File(fileName);
               
               //If file name already exists increase its count, eg - (1)
               while (f.exists())
               {
                    fileName = ("GameResults " + java.time.LocalDate.now() + " (" + fileCount + ").txt"); //Includes current time in name, keep format same as initialized name
                    //System.out.println("GameResults " + fileCount + ".txt");                            //Less info version
                    fileCount += 1;
                    f = new File(fileName);
               }
               
               return (new PrintWriter(new File(fileName)));
          }
          catch (FileNotFoundException ex)
          {
               System.out.println(ex.getMessage());
               System.exit(1);
          }
          return null;
     }
     
     /** Disables all the cells in playable grid
       * @param cells - The playable grid of buttons of the game*/
     public void disableAllCells(JButton[][] cells)
     {
          //Loops through all buttons
          for (int i = 0; i < 3; i++)
          {
               for (int j = 0; j < 3; j++)
               {
                    cells[i][j].setEnabled(false);
               }
          }
     }
     
     /** Stops the main thread for a certain amount of time, SHOULDNT be used because it freezes the UI
       * @param ms - The amount of time to stop the thread in milliseconds*/
     public void wait(int ms)
     {
          try
          {
               Thread.sleep(ms);
          }
          catch(InterruptedException ex)
          {
               Thread.currentThread().interrupt();
          }
     }
     
     /** Exits the program based on users decision*/
     public void exit()
     {
          //System.exit(0); //Exits without prompting user
          
          int result = JOptionPane.showConfirmDialog(gameView, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
          
          if (result == JOptionPane.YES_OPTION) {
               System.exit(0);
          }
     }
     
     /**  Updates the view in the GUI*/
     public void updateView()
     {
          gameView.update();
     }
}//end of class