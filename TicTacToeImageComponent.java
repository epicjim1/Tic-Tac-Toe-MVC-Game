/**  TicTacToeImageComponent Class
*    Draws an image
*    Last Modified: 20/01/2023
*    @author Abhishek Luthra
*/ 

import javax.swing.*;
import java.awt.*;

public class TicTacToeImageComponent extends JComponent
{
     ImageIcon image;   //The image that gets drawn to the component
     
     /** Default constructor
       * Links the image icon to the name parameter
       * @param imgName - The name of the image file that the suer wants drawn*/
     public TicTacToeImageComponent(String imgName)
     {
          super();
          image = new ImageIcon(imgName);
          this.setPreferredSize(new Dimension(image.getImage().getWidth(null), image.getImage().getHeight(null)));
     }
     
     
     /** Overrides the paintComponent method from the JComponent class to display an image
       * @param g - The Graphics object used to draw the image*/
     public void paintComponent (Graphics g)
     {
          super.paintComponent(g);
          Image pic = image.getImage();
          g.drawImage(pic, 0, 0, null);
     }
}