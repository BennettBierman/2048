import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

//Bennett Bierman
//Program description:
//May 16, 2017
public class Block
{
   private int x;
   private int y;
   private int l;
   private int blockValue;

   public Block(int x, int y, int l, int blockValue)
   {
      this.x = x;
      this.y = y;
      this.l = l;
      this.blockValue = blockValue;
   }


   public void draw(Graphics2D g2, Color[] color)
   {
      if(blockValue!=0)
      {
      g2.setColor(color[((int) (Math.log10(blockValue) / Math.log10(2)) - 1) % color.length]);
      g2.fill(getBounds());
      g2.setColor(Color.BLACK);
      g2.draw(getBounds());
      g2.drawString("" + getBlockValue(), (int) (getBounds().getX() + getBounds().getWidth() / 4),
            (int) (getBounds().getY() + getBounds().getHeight() / 2));
      }
      else
      {
         g2.setColor(Color.LIGHT_GRAY);
         g2.fill(getBounds());
         g2.setColor(Color.BLACK);
         g2.draw(getBounds());
      }
   }


   public Rectangle getBounds()
   {
      return new Rectangle(x, y, l, l);
   }

   public int getBlockValue()
   {
      return blockValue;
   }

   public void setBlockValue(int numHeld)
   {
      this.blockValue = numHeld;
   }

   public int getX()
   {
      return x;
   }

   public void setX(int x)
   {
      this.x = x;
   }

   public int getY()
   {
      return y;
   }

   public void setY(int y)
   {
      this.y = y;
   }

  
}
