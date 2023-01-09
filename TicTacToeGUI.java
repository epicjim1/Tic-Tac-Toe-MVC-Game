/**  TicTacToeGUI Class
*    Creates a layout for a TicTacToe game
*    Last Modified: 20/01/2023
*    @author Abhishek Luthra
*/ 

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.*;

public class TicTacToeGUI extends JPanel
{
     //Instance Variables
     private TicTacToeModel game;                                                               //The game Model
     
     //MENU PANEL
     private JPanel menuPanel = new JPanel();                                                   //The main panel for the user to start a game, go to file view, and toggle audio
     private TicTacToeImageComponent menuImg = new TicTacToeImageComponent("menuImage.jpg");    //A splash image to greet the user to the program
     private JButton playBtn = new JButton("Play");                                             //Takes the user to the settings panel
     private JButton pastResultsBtn = new JButton("Past Results");                              //Takes the user to the file view panel
     private JButton audioControlBtn = new JButton();                                           //Button to toggle the background music on and off
     private Clip clip;                                                                         //A clip of audio for background music throughout the program
     private JButton menuExitBtn = new JButton("Exit");                                         //Prompts the user to exit the program
     
     //FILEVIEW PANEL
     private JPanel fileViewPanel = new JPanel();                                               //Panel to open and view past game results
     private JTextArea fileView = new JTextArea();                                              //The text area that displays the contents of the choosen file
     private JButton openFileBtn = new JButton("Choose File");                                  //Button that allows the user to choose a file to open
     private JButton backBtn = new JButton("Back");                                             //Takes the user back to the menu panel
     
     //SETTINGS PANEL
     private JPanel settingsPanel = new JPanel();                                               //Panel to choose number of rounds and difficuly for a new game
     private JTextField roundsChooser = new JTextField(15);                                     //TextField for user to enter the number of rounds to play
     private JButton easyBtn = new JButton("Easy");                                             //Allows the user to select an easy difficulty
     private JButton hardBtn = new JButton("Hard");                                             //Allows the user to select an hard difficulty
     private JButton impossibleBtn = new JButton("Impossible");                                 //Allows the user to select an impossible difficulty
     private JTextArea modeDescription = new JTextArea();                                       //
     private JButton startBtn = new JButton("Start Game");                                      //Button to take all the settings and take the user to the game panel to start the first round
     
     //GAME PANEL
     private JPanel gamePanel = new JPanel();                                                   //Panel used to play TicTacToe
     private JLabel roundStatus = new JLabel("");                                               //Label used to display whoose turn it is and the winner of a round
     private JLabel currentRound = new JLabel("Round: ");                                       //The current round the user is on
     private JButton[][] cells = new JButton[3][3];                                             //The playable grid of buttons of the game
     private JButton nextRoundBtn = new JButton("Next Round");                                  //Takes the user to the next round of a game, only visible when a round is finished
     private JButton resultsBtn = new JButton("View Results");                                  //Takes the user to the end panel, only visible once the last round has finished
     private JButton exitBtn = new JButton("Exit");                                             //Prompts the user to exit the program
     
     //END PANEL
     private JPanel gameEndPanel = new JPanel();                                                 //Panel to display the results of a game
     private JLabel gameNumber = new JLabel("Game: ");                                           //The number of the game that was just played
     private JTextArea gameEndStats = new JTextArea(8, 10);                                      //The TextArea that holds all the results of the game
     private JPanel gameEndCenterPanel = new JPanel();                                           //Panel to hold the results text area and image
     private TicTacToeImageComponent gameEndImg = new TicTacToeImageComponent("tie.png");        //Image displayed about the games status, will be removed and readded in update()
     private JButton newGameBtn = new JButton("New Game");                                       //Starts a new game, takes user to settings panel
     private JButton menuBtn = new JButton("Menu");                                              //Takes the user to the menu panel
     private JButton endExitBtn = new JButton("Exit");                                           //Prompts the user to exit the program
     
     /** Default constructor for the GUI.  Assigns Model and View, identifies controllers
       * and draws the layout
       * @param newGame        The Model for the game
       */ 
     public TicTacToeGUI(TicTacToeModel newGame)
     {
          super();
          this.game = newGame;
          this.game.setGUI(this);
          this.layoutView();
          this.registerControllers();
          this.update();
     }
     
     /** Draws the initial layout for the game board
       */ 
     private void layoutView()
     {
          //--------------------MUSIC--------------------//
          try
          {
               // Load the music file into a stream
               AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("BGMusic.wav"));
               
               // Get a clip to play the music
               clip = AudioSystem.getClip();
               clip.open(audioInputStream);
               
               // Set the volume and balance of the clip
               FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
               volumeControl.setValue(1.0f); // Set the volume to maximum
               FloatControl balanceControl = (FloatControl) clip.getControl(FloatControl.Type.BALANCE);
               balanceControl.setValue(0.0f); // Set the balance to center
               
               // Start playing the music
               clip.start();
               
               // Loop clip
               clip.loop(Clip.LOOP_CONTINUOUSLY);
          }
          catch (Exception e)
          {
               System.out.println(e);
          }
          
          //--------------------MENU PANEL--------------------//
          Icon icon =  new ImageIcon("audioIconOff.png"); //defaults icon to switch off audio
          audioControlBtn.setIcon(icon);
          
          playBtn.setPreferredSize(new Dimension(70, 40));
          pastResultsBtn.setPreferredSize(new Dimension(120, 40));
          audioControlBtn.setPreferredSize(new Dimension(60, 40));
          menuExitBtn.setPreferredSize(new Dimension(70, 40));
          
          JPanel menuBtnsPanel = new JPanel();
          menuBtnsPanel.add(playBtn);
          menuBtnsPanel.add(pastResultsBtn);
          menuBtnsPanel.add(audioControlBtn);
          menuBtnsPanel.add(menuExitBtn);
          
          menuPanel.setLayout(new BorderLayout());
          menuPanel.add(menuImg, BorderLayout.NORTH);
          menuPanel.add(menuBtnsPanel, BorderLayout.SOUTH);
          
          //--------------------FILEVIEW PANEL--------------------//
          fileView.setEditable(false);
          fileView.setFont(new Font("Arial", Font.PLAIN, 16));
          
          JScrollPane fileScrollPane = new JScrollPane(fileView);
          fileScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          fileScrollPane.setPreferredSize(new Dimension(350, 350));
          
          JPanel fileViewBtns = new JPanel();
          fileViewBtns.add(backBtn);
          fileViewBtns.add(openFileBtn);
          
          backBtn.setPreferredSize(new Dimension(80, 40));
          openFileBtn.setPreferredSize(new Dimension(120, 40));
          
          fileViewPanel.setLayout(new BorderLayout());
          fileViewPanel.add(fileScrollPane, BorderLayout.NORTH);
          fileViewPanel.add(fileViewBtns, BorderLayout.SOUTH);
          
          //--------------------SETTINGS PANEL--------------------//
          JLabel roundsLabel = new JLabel("Number of rounds(max 10):");
          JPanel roundsPanel = new JPanel();
          roundsPanel.add(roundsLabel);
          roundsPanel.add(roundsChooser);
          
          JPanel gameModeBtnsPanel = new JPanel();
          gameModeBtnsPanel.add(easyBtn);
          gameModeBtnsPanel.add(hardBtn);
          gameModeBtnsPanel.add(impossibleBtn);
          
          easyBtn.setPreferredSize(new Dimension(70, 30));
          hardBtn.setPreferredSize(new Dimension(70, 30));
          impossibleBtn.setPreferredSize(new Dimension(100, 30));
          
          JPanel description = new JPanel();
          description.add(modeDescription);
          modeDescription.setEditable(false);
          modeDescription.setPreferredSize(new Dimension(350, 100));
          modeDescription.setFont(new Font("Arial", Font.PLAIN, 14));
          modeDescription.setLineWrap(true);
          modeDescription.setWrapStyleWord(true);
          modeDescription.setText("EASY - Computer will randomly move\n\n" +
                                  "HARD - Computer knows how win and block you\n\n" +
                                  "IMPOSSIBLE - Hard mode but the computer can hack your turn");
          
          JPanel gameModePanel = new JPanel();
          gameModePanel.setLayout(new BorderLayout());
          gameModePanel.add(gameModeBtnsPanel, BorderLayout.CENTER);
          gameModePanel.add(description, BorderLayout.SOUTH);
          gameModePanel.setBorder(BorderFactory.createTitledBorder("Choose a Game Mode:"));
          
          JPanel start = new JPanel();
          start.add(startBtn);
          startBtn.setPreferredSize(new Dimension(140, 40));
          
          settingsPanel.setLayout(new BorderLayout());
          settingsPanel.add(roundsPanel, BorderLayout.NORTH);
          settingsPanel.add(gameModePanel, BorderLayout.CENTER);
          settingsPanel.add(start, BorderLayout.SOUTH);
          
          //--------------------GAME PANEL--------------------//
          JPanel roundInfoPanel = new JPanel();
          roundInfoPanel.setLayout(new BorderLayout());
          roundInfoPanel.add(roundStatus, BorderLayout.WEST);
          roundInfoPanel.add(currentRound, BorderLayout.EAST);
          
          JPanel gridPanel = new JPanel(new GridLayout(3, 3));
          
          //Add buttons
          for (int i = 0; i < 3; i++)
          {
               for (int j = 0; j < 3; j++)
               {
                    cells[i][j] = new JButton("");
                    gridPanel.add(cells[i][j]);
                    cells[i][j].setPreferredSize(new Dimension(120, 120));
                    cells[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
               }
          }
          
          nextRoundBtn.setPreferredSize(new Dimension(120, 40));
          resultsBtn.setPreferredSize(new Dimension(120, 40));
          menuBtn.setPreferredSize(new Dimension(80, 40));
          exitBtn.setPreferredSize(new Dimension(80, 40));
          
          JPanel roundsBtnPanel = new JPanel();
          roundsBtnPanel.add(nextRoundBtn);
          roundsBtnPanel.add(resultsBtn);
          roundsBtnPanel.add(exitBtn);
          
          gamePanel.setLayout(new BorderLayout());
          gamePanel.add(roundInfoPanel, BorderLayout.NORTH);
          gamePanel.add(gridPanel, BorderLayout.CENTER);
          gamePanel.add(roundsBtnPanel, BorderLayout.SOUTH);
          
          //--------------------END PANEL--------------------//
          //gameNumber.setFont(new Font("Arial", Font.PLAIN, 20));
          
          gameEndCenterPanel.setLayout(new BorderLayout());
          gameEndStats.setEditable(false);
          gameEndCenterPanel.add(gameEndStats, BorderLayout.NORTH);
          gameEndCenterPanel.add(gameEndImg, BorderLayout.SOUTH);
          
          JPanel gameEndButtonsPanel = new JPanel();
          newGameBtn.setPreferredSize(new Dimension(120, 40));
          endExitBtn.setPreferredSize(new Dimension(80, 40));
          gameEndButtonsPanel.add(newGameBtn);
          gameEndButtonsPanel.add(menuBtn);
          gameEndButtonsPanel.add(endExitBtn);
          
          gameEndPanel.setLayout(new BorderLayout());
          gameEndPanel.add(gameNumber, BorderLayout.NORTH);
          gameEndPanel.add(gameEndCenterPanel, BorderLayout.CENTER);
          gameEndPanel.add(gameEndButtonsPanel, BorderLayout.SOUTH);
          
          //--------------------ADD ALL PANELS--------------------//
          this.add(menuPanel);
          this.add(fileViewPanel);
          this.add(settingsPanel);
          this.add(gamePanel);
          this.add(gameEndPanel);
     }
     
     /**Assigns the controllers to the remove tokens textboxes.
       */ 
     private void registerControllers()
     {
          //Menu Controllers
          TicTacToeMenuController playController = new TicTacToeMenuController(this.game, this.playBtn, this.pastResultsBtn, this.menuExitBtn,
                                                                               this.cells, this.openFileBtn, this.backBtn, this.fileView,
                                                                               this.audioControlBtn, this.clip);
          playBtn.addActionListener(playController);
          
          TicTacToeMenuController resultsController = new TicTacToeMenuController(this.game, this.playBtn, this.pastResultsBtn, this.menuExitBtn,
                                                                                  this.cells, this.openFileBtn, this.backBtn, this.fileView,
                                                                                  this.audioControlBtn, this.clip);
          pastResultsBtn.addActionListener(resultsController);
          
          TicTacToeMenuController audioBtnController = new TicTacToeMenuController(this.game, this.playBtn, this.pastResultsBtn, this.menuExitBtn,
                                                                                   this.cells, this.openFileBtn, this.backBtn, this.fileView,
                                                                                   this.audioControlBtn, this.clip);
          audioControlBtn.addActionListener(audioBtnController);
          
          TicTacToeMenuController menuExitController = new TicTacToeMenuController(this.game, this.playBtn, this.pastResultsBtn, this.menuExitBtn,
                                                                                   this.cells, this.openFileBtn, this.backBtn, this.fileView,
                                                                                   this.audioControlBtn, this.clip);
          menuExitBtn.addActionListener(menuExitController);
          
          //Fileview section
          TicTacToeMenuController fileViewController = new TicTacToeMenuController(this.game, this.playBtn, this.pastResultsBtn, this.menuExitBtn,
                                                                                   this.cells, this.openFileBtn, this.backBtn, this.fileView,
                                                                                   this.audioControlBtn, this.clip);
          openFileBtn.addActionListener(fileViewController);
          
          TicTacToeMenuController backBtnController = new TicTacToeMenuController(this.game, this.playBtn, this.pastResultsBtn, this.menuExitBtn,
                                                                                  this.cells, this.openFileBtn, this.backBtn, this.fileView,
                                                                                  this.audioControlBtn, this.clip);
          backBtn.addActionListener(backBtnController);
          
          //Starting Controllers
          TicTacToeStartController startController = new TicTacToeStartController(this.game, this.startBtn, this.easyBtn, this.hardBtn, this.roundsChooser, this.impossibleBtn);
          startBtn.addActionListener(startController);
          
          TicTacToeStartController easyController = new TicTacToeStartController(this.game, this.startBtn, this.easyBtn, this.hardBtn, this.roundsChooser, this.impossibleBtn);
          easyBtn.addActionListener(easyController);
          
          TicTacToeStartController hardController = new TicTacToeStartController(this.game, this.startBtn, this.easyBtn, this.hardBtn, this.roundsChooser, this.impossibleBtn);
          hardBtn.addActionListener(hardController);
          
          TicTacToeStartController impossibleController = new TicTacToeStartController(this.game, this.startBtn, this.easyBtn, this.hardBtn, this.roundsChooser, this.impossibleBtn);
          impossibleBtn.addActionListener(impossibleController);
          
          //TicTacToe game buttons
          for (int i = 0; i < 3; i++)
          {
               for (int j = 0; j < 3; j++)
               {
                    TicTacToePlayController playButtonController = new TicTacToePlayController(this.game, this.cells[i][j]);
                    cells[i][j].addActionListener(playButtonController);
               }
          }
          
          //Round Controllers
          TicTacToeRoundController nextRoundController = new TicTacToeRoundController(this.game, this.nextRoundBtn, this.exitBtn, this.resultsBtn, this.cells);
          nextRoundBtn.addActionListener(nextRoundController);
          
          TicTacToeRoundController viewResultsController = new TicTacToeRoundController(this.game, this.nextRoundBtn, this.exitBtn, this.resultsBtn, this.cells);
          resultsBtn.addActionListener(viewResultsController);
          
          TicTacToeRoundController exitController = new TicTacToeRoundController(this.game, this.nextRoundBtn, this.exitBtn, this.resultsBtn, this.cells);
          exitBtn.addActionListener(exitController);
          
          //End Game Controllers
          TicTacToeGameEndController newGameController = new TicTacToeGameEndController(this.game, this.newGameBtn, this.endExitBtn, this.cells, this.menuBtn);
          newGameBtn.addActionListener(newGameController);
          
          TicTacToeGameEndController menuBtnController = new TicTacToeGameEndController(this.game, this.newGameBtn, this.endExitBtn, this.cells, this.menuBtn);
          menuBtn.addActionListener(menuBtnController);
          
          TicTacToeGameEndController endExitController = new TicTacToeGameEndController(this.game, this.newGameBtn, this.endExitBtn, this.cells, this.menuBtn);
          endExitBtn.addActionListener(endExitController);
     }
     
     /** Redraws the game board according to the current game situation.  Requires data
       * from the Model.
       */ 
     public void update()
     {
          switchPanels();  //Switchs to the desired panel
          
          //If user is in menu
          if (game.getVisiblePanel() == 1)
          {
               
          }
          //If user is in file viewer
          else if (game.getVisiblePanel() == 2)
          {
               
          }
          //If user is in game start settings
          else if (game.getVisiblePanel() == 3)
          {
               //Reset options
               easyBtn.setEnabled(true);
               hardBtn.setEnabled(true);
               impossibleBtn.setEnabled(true);
               roundsChooser.setText("");
               
               game.startingTurn();
          }
          //If user is in a round
          else if (game.getVisiblePanel() == 4)
          {
               this.currentRound.setText("Round: " + game.getCurrentRound()); //Display round number
               
               //If round ended
               if (game.isRoundOver(cells))
               {
                    game.disableAllCells(cells);
                    
                    this.roundStatus.setText("Round Status: " + game.getRoundWinner());
                    
                    //If the last round ended
                    if (game.getCurrentRound() == game.getNumOfRounds())
                    {
                         resultsBtn.setVisible(true);
                    }
                    //If a round before last round ended
                    else
                    {
                         resultsBtn.setVisible(false);
                         nextRoundBtn.setVisible(true); //if last round player cant go to next round
                    }
               }
               //If round is still going
               else
               {
                    nextRoundBtn.setVisible(false);
                    resultsBtn.setVisible(false);
                    
                    //If its the players turn
                    if (game.getIsPlayerTurn())
                    {
                         this.roundStatus.setText("Your turn to move");
                    }
                    //If its the computers turn
                    else
                    {
                         //Creates a new thread to pause time, this will affect the main thread but not freeze the UI
                         Thread delayThread = new Thread(() -> {
                              try
                              {
                                   this.roundStatus.setText("Computers turn to move");
                                   Thread.sleep(500);
                                   this.roundStatus.setText("Computers turn to move .");
                                   Thread.sleep(500);
                                   this.roundStatus.setText("Computers turn to move ..");
                                   Thread.sleep(500);
                                   //this.roundStatus.setText("Computers turn to move ...");
                                   //Thread.sleep(500);
                                   
                                   game.computerMove(cells);
                              }
                              catch (InterruptedException e)
                              {
                                   System.out.println("Error: " + e);
                              }
                         });
                         
                         delayThread.start();
                         //delayThread.interrupt(); //Stops thread
                         
                         /*this.turnStatus.setText("Computers turn to move"); 
                         game.wait(3000);   //DOES NOT WORK, Freezes UI
                         game.computerMove(cells);*/
                    }
               }
          }
          //If user is done a game
          else if (game.getVisiblePanel() == 5)
          {
               gameEndCenterPanel.remove(gameEndImg);                          //Removes previously attached image component
               gameEndImg = new TicTacToeImageComponent(game.getEndImgName()); //Creates new image component with models img name
               gameEndCenterPanel.add(gameEndImg);                             //Adds new component to panel
               
               gameNumber.setText("Game: " + game.getCurrentGameNum());
               gameEndStats.setText("Game Status: " + game.getGameWinner() + "\nGame Mode: " + game.getGameModeName() + "\n\nPlayer wins: " +
                                    game.getPlayerWins() + "\nComputer wins: " + game.getComputerWins() + "\nRounds Tied: " + game.getRoundTies() +
                                    "\n\nPlayer Moves Made: " + game.getPlayerMovesMade() + "\nComputer Moves Made: " + game.getComputerMovesMade() +
                                    "\nTotal Moves Made: " + game.getTotalMovesMade() + "\n\nTimes Computer Cheated: " + game.getComputerCheatCount() +
                                    "\n\nRounds Played: " + game.getNumOfRounds()); //More INFO
          }
          
     }//end of update
     
     /** Switchs to the desired panel*/
     private void switchPanels()
     {
          menuPanel.setVisible(false);
          fileViewPanel.setVisible(false);
          settingsPanel.setVisible(false);
          gamePanel.setVisible(false);
          gameEndPanel.setVisible(false);
          
          //Sees which panel should be visible from the model
          switch(game.getVisiblePanel())
          {
               case 1:
                    menuPanel.setVisible(true);
                    break;
               case 2:
                    fileViewPanel.setVisible(true);
                    break;
               case 3:
                    settingsPanel.setVisible(true);
                    break;
               case 4:
                    gamePanel.setVisible(true);
                    break;
               case 5:
                    gameEndPanel.setVisible(true);
                    break;
               default:
                    menuPanel.setVisible(true);
                    break;
          }
     }
}//end of class