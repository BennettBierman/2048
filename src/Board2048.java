import java.util.Scanner;

//Bennett Bierman
//Program description:
//Jun 2, 2017

public class Board2048
{
   private int[][] board;
   private boolean combine;

   public Board2048()
   {
      board = new int[4][4];
      combine = false;
      
   }

   public void drawBoard()
   {
      for (int r = 0; r < board.length; r++)
      {
         for (int c = 0; c < board[0].length; c++)
            System.out.printf("%6s",board[r][c] + " ");
         System.out.println();
      }
   }

   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      Board2048 game = new Board2048();
      game.addNum(2);
      game.drawBoard();
      
      do
      {
         System.out.println("Enter a direction");
         String move = input.nextLine();
         
         if (move.equalsIgnoreCase("w")) game.moveUp();
         else if (move.equalsIgnoreCase("s")) game.moveDown();
         else if (move.equalsIgnoreCase("d")) game.moveRight();
         else if (move.equalsIgnoreCase("a")) game.moveLeft();
         game.addNum(1);
         game.drawBoard();
      } while (!game.gameOver());
      
      System.out.println("You got 2048 good job");
   }

   public void moveUp()
   {
      int toRow;
      for (int col = 0; col < board[0].length; col++)
         for (int row = 1; row < board.length; row++)
         {
            toRow = row;
            boolean flag = false;
            while (toRow > 0 && board[row][col] != 0&& (board[toRow - 1][col] == 0 || board[toRow - 1][col] == board[row][col]))
            {
               if (board[toRow- 1][col] == 0)
               {
                  toRow--;
                  flag = true;
               } 
               else
               {
                  board[toRow - 1][col] *= 2;
                  board[row][col] = 0;
                  combine = true;
               }
            }
            if (flag)
            {
               board[toRow][col] = board[row][col];
               board[row][col] = 0;
            }
        
         }
   }
   
   public void moveDown()
   {
      int toRow;
      for (int col = 0; col < board[0].length; col++)
         for (int row = board.length; row >= 0; row--)
         {
            toRow = row;
            boolean flag = false;
            while (toRow <3  && board[row][col] != 0&& (board[toRow + 1][col] == 0 || board[toRow + 1][col] == board[row][col]))
            {
               if (board[toRow+1][col] == 0)
               {
                  toRow++;
                  flag = true;
               } 
               else
               {
                  board[toRow + 1][col] *= 2;
                  board[row][col] = 0;
                  combine =true;
               }
            }
            if (flag)
            {
               board[toRow][col] = board[row][col];
               board[row][col] = 0;
            }
         }
   }

   public void moveRight()
   {
      int toCol;
      for (int row = 0; row < board[0].length; row++)
         for (int col = board.length; col >= 0; col--)
         {
            toCol = col;
            boolean flag = false;
            while (toCol <3  && board[row][col] != 0&& (board[row][toCol+1] == 0 || board[row][toCol+1] == board[row][col]))
            {
               if (board[row][toCol+1] == 0)
               {
                  toCol++;
                  flag = true;
               } 
               else
               {
                  board[row][toCol+1] *= 2;
                  board[row][col] = 0;
                  combine = true;
               }
            }
            if (flag)
            {
               board[row][toCol] = board[row][col];
               board[row][col] = 0;
            }
         }
      
      
   }

   public void moveLeft()
   {
      int toCol;
      for (int row = 0; row < board.length; row++)
         for (int col = 1; col < board[0].length; col++)
         {
            toCol = col;
            boolean flag = false;
            while (toCol > 0 && board[row][col] != 0&& (board[row][toCol-1] == 0 || board[row][toCol-1] == board[row][col]))
            {
               if (board[row][toCol-1] == 0)
               {
                  toCol--;
                  flag = true;
               } 
               else
               {
                  board[row][toCol-1] *= 2;
                  board[row][col] = 0;
                  combine =true;
               }
            }
            if (flag)
            {
               board[row][toCol] = board[row][col];
               board[row][col] = 0;
            }
         }
      
   }
   public boolean openMove()
   { 
      if(!boardOpen())
      {
      for(int r=0; r<board.length;r++)
         for(int c=0;c<board[0].length;c++)
            if(board[r][c]==board[r][c+1]||board[r][c]==board[r][c-1]||board[r][c]==board[r+1][c]||board[r][c]==board[r-1][c])
               return true;
      return false;
      }
      return true;
           
   }
   
   public boolean getCombine()
   {
      if(combine)
         return true;
      return false;
   }
   
   public void setCombine(boolean choose)
   {
      combine = choose;
   }
   
   public boolean boardOpen()
   {
      for(int r=0; r<board.length;r++)
         for(int c=0; c<board[0].length;c++)
            if(board[r][c]==0)
               return true;
      return false;
   }
   

   
   
   public void addNum(int num)
   {
      int counter = 0;
      
      do
      {
      int row;
      int col;
 
      do
      {
         row=(int)(Math.random()*4);
         col=(int)(Math.random()*4);
         
      }while(board[row][col]!=0&&boardOpen());
    
     counter++;
     
     if(boardOpen())
     {
     if((int)(Math.random()*10)>8)
        board[row][col]=4;
     else
        board[row][col]=2;
     }
     
      }while(num!=counter&&boardOpen());
      
       
   }
   
   public boolean gameOver()
   {
      for(int r=0;r<board.length;r++)
         for(int c=0;c<board[0].length;c++)
            if(board[r][c]==2048)
               return true;
      if(!openMove())
         return true;
      return false;
   }
   
   public int getInt(int r, int c)
   {
      return board[r][c];
   }
  
   
   
   
}
