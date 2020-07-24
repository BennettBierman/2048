import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


//Bennett Bierman
//Program description:
//May 22, 2017

public class TwentyFortyEight extends JPanel implements KeyListener
{
   private static final long serialVersionUID = 1L;
   private static final int PREF_W = 800, PREF_H = 800, BOARD = 600;
   private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   private Rectangle background;
   private String message;
   private int length,location,score;
   private Board2048 board2048 = new Board2048();;
   private Block[][] block = new Block[4][4];
   private Color[] color = {Color.WHITE, new Color(210, 180,140),Color.ORANGE, new Color(255,140,0 ), new Color(205,92,92), Color.RED, Color.PINK};
                           //    white 2       tan 4               orange 8         dark orange 16     indian red 32        red 64        gold yellow 128
   public TwentyFortyEight()
   {
      super();
      this.setBackground(Color.BLACK );

      this.addKeyListener(this);
      this.setFocusable(true);
      
      score = 0;
      length = 130;
      location = 110;
      
      background = new Rectangle(100,100,BOARD,BOARD);
      
      message = "Score: "+score;
      
      board2048.addNum(2);
      
      for(int r=0;r<block.length;r++)
         for(int c=0;c<block[r].length;c++)
            block[r][c] = new Block(location+150*r,location+150*c,length,board2048.getInt(r, c));
      
      
   }
   
   
   public void paintComponent(Graphics g)
   {
     
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHints(hints);
      g2.setFont(new Font("Cooper Black", Font.PLAIN, 90));
      g2.setFont(new Font("Cooper Black", Font.PLAIN, 30));
      g2.setColor(Color.GRAY);
      g2.fill(background.getBounds());
      g2.setColor(Color.WHITE);
      
      for(int r=0;r<block.length;r++)
         for(int c=0;c<block[r].length;c++)
            block[r][c].draw(g2, color);
    
      g2.setColor(Color.RED);
      g2.drawString(message, PREF_W/2-60, 60);
   }
   
   public void update()
   {
      for(int r=0;r<block.length;r++)
         for(int c=0;c<block[r].length;c++)
            block[r][c] = new Block(location+150*r,location+150*c,length,board2048.getInt(r, c));
     
      if(board2048.getCombine())
      {
         score++;
         System.out.println("Combine");
         System.out.println(score);
      }
      
      if(board2048.gameOver())
         message = "GAME OVER";
      
      board2048.setCombine(false);
      message = "Score: "+score;
      repaint();
     
   }
   
   public Dimension getPreferredSize()
   {
      return new Dimension(PREF_W, PREF_H);
   }
   
   private static void createAndShowGUI()
   {
      TwentyFortyEight gamePanel = new TwentyFortyEight();
      
      JFrame frame = new JFrame("2048");
      frame.getContentPane().add(gamePanel);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
   
   
  
   
   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(new Runnable(){
         public void run(){
            createAndShowGUI();
         }
      });
   }

  
   
   public void fullGameReset()
   {
      
      
   }
   
   @Override
   public void keyPressed(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      
      if(keyCode == KeyEvent.VK_LEFT)
      {
         System.out.println("Left");
         board2048.moveUp();
         update();
         repaint();
         board2048.addNum(1);
         update();
         repaint();
      }

        

      if(keyCode == KeyEvent.VK_RIGHT)
      {
         System.out.println("Right");
         board2048.moveDown();
         update();
    //     repaint();
         board2048.addNum(1);
         update();
         repaint();
      }
      if(keyCode == KeyEvent.VK_UP)
      {
         System.out.println("Up");
         board2048.moveLeft();
         update();
    //     repaint();
         board2048.addNum(1);
         update();
         repaint();
      }
      
      if(keyCode == KeyEvent.VK_DOWN)
      {
         System.out.println("Down");
         board2048.moveRight();
         update();
      //   repaint();
         board2048.addNum(1);
         update();
         repaint();
      }
     
      if(keyCode==KeyEvent.VK_SPACE)
      {
         board2048.addNum(1);
         update();
         System.out.println("space");
        // repaint();
         board2048.addNum(1);
         update();
         repaint();
      }
      
      if(keyCode==KeyEvent.VK_W)
      {
         block[0][0].setBlockValue(block[0][0].getBlockValue()*2);
         repaint();
      }
 
   }


  @Override
   public void keyReleased(KeyEvent e)
   {
   }




@Override
public void keyTyped(KeyEvent e)
{
}
   
    
}