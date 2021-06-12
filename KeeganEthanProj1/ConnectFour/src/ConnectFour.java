/*
 * Author: Ethan Keegan
 * Class: EECS 1510
 * Date: 2/19/2021
 * 
 * This class that I have created is the game Connect Four. In the class, there are a variety of methods
 * that work together to make the game smooth and replayable. My methods consist of: main, runGame, displayBoard, 
 * dropDisk, placeADisk, isWinner. These are are all broken down into detail throughout the program.
 */

import java.util.Scanner;

public class ConnectFour
{
		//variables given from Dr. Hobbs' demos
		public static Scanner input = new Scanner(System.in);
		static final int ROWS = 6, COLS = 7;
		static char[][] board = new char[ROWS][COLS]; 			// 6-x-7 array to simulate the board
		
		public static void main(String[] args)
		{		
			displayBoard(board);				//Setting up the board
			runGame(board);						//Starting Game
			
			//Restarting Game (can restart up to 100 times before code needs to be run again)
			for(int i = 0; i < 100; i++)
			{
				int playAgain = input.nextInt();
				if(playAgain == 1)				//if the user inputs the correct value, it restarts the game
				{
					clearBoard(board);
					displayBoard(board);
					runGame(board);
				}
				else
					return;
			}
		}

		
		//this is what will be running the game output
		private static void runGame(char[][] board)
		{
			for(int i = 0; i < 21; i++)						//each player gets 21 turns; 42 in total.
			{
				//yellow's turn (yellow goes first)
				dropDisk('Y', board);
				displayBoard(board);
				
				//checking to see if yellow player won
				if(isWinner(board) == true)
				{
					System.out.println("Y has WON the game!");
					System.out.println("DO YOU WANT TO PLAY A NEW GAME? (type 1 for yes)");
					return;
				}
	
				
				//red's turn
				dropDisk('R', board);				
				displayBoard(board);
				
				//checking to see if red player won
				if(isWinner(board) == true)
				{
					System.out.println("R has WON the game!");
					System.out.println("DO YOU WANT TO PLAY A NEW GAME? (type 1 for yes)");
					return;
				}
			}
			
			
			if(isWinner(board) == false)					//if there are no winners, the board is full
			{
				System.out.println("I declare a draw");
				System.out.println("DO YOU WANT TO PLAY A NEW GAME? (type 1 for yes)");
				return;
			}
			
		}
				
		
		//code is given in the directions
		//this generates the game board
		private static void displayBoard(char[][] board)
		{
			// This method displays the current board, so the user can see what's
			// currently where, and they can plan their next move accordingly
			
			for(int i = board.length - 1; i >= 0; i--)
			{
				System.out.print("| ");
				for(int j = 0; j < board[i].length; j++)
				{
					System.out.print(board[i][j] + " | ");
				}
				System.out.println(); //newline
			}
		}//displayBoard
		
		
		//this method is called when the game resets
		//this clears the game board
		private static void clearBoard(char [][] board)
		{
			for(int r = 0; r < ROWS; r++)
				for (int c = 0; c < COLS; c++)
				{
					if(board[r][c] == 'Y')			//clearing Y disks
						board[r][c] = '\u0000';
					if(board[r][c] == 'R')			//clearing R disks
						board[r][c] = '\u0000';
				}
		}
		
		
		//method logic is given from Dr. Hobbs' demos
		//this method runs the placeADisk on repeat until the user makes a valid input
		public static void dropDisk(char player, char[][] board)
		{
			int column = input.nextInt();
			
			if(placeADisk(board, column, player) == true)	//if there is a space, the disk has been placed by the PlaceADisk method
			{
				return;
			}
			else											//if the disk has not been placed yet, the method calls itself to run again
			{
				System.out.println("YOU ENTERED AN INVALID INTEGER OR THE COLUMN IS FULL.");
				dropDisk(player, board);
			}
		}
		
		
		//method logic is given from Dr. Hobbs' demos
		//this method is initiated from the dropDisk method. It checks if the player made a valid input and places a disk if it is.
		static boolean placeADisk(char [][] board, int column, char player)
		{
			if(column < 0 || column > 6)							//checking if input is within column boundaries
				return false;
			else													
				{
					for(int i = 0; i < board.length; i++)
					{
						if(board[i][column] == '\u0000')			//checking if the space is open
						{
							board[i][column] = player;
							return true;                  			//location was available so disc was dropped
						}
					}
					return false;						  			//column is full
				}
		}

		
		//method logic inspired from Dr. Hobbs' demos
		//this method checks for disks 4-in-a-row in all directions
		public static boolean isWinner(char[][] board)
		{
			//checking for winners horizontally
			for(int r = 0; r < ROWS; r++)						// for EVERY row
				for (int c = 0; c <= 3; c++)					// for columns 0-3
					if (board[r][c] != '\u0000' &&				// if it's not empty, AND
					    board[r][c] == board[r][c+1] && 		// it matches neighbor 1 AND
					    board[r][c] == board[r][c+2] && 		// it matches TWO over AND
					    board[r][c] == board[r][c+3]) 			// it matches THREE over
						return true;							// then it's a WINNER!
			
			
			//checking for winners vertically
			for(int c = 0; c < COLS; c++)						// for EVERY column
				for(int r = 0; r <= 2; r++)						// for rows 0-2
					if (board[r][c] != '\u0000' &&				// if it's not empty, AND
					    board[r][c] == board[r+1][c] && 		// it matches neighbor 1 AND
					    board[r][c] == board[r+2][c] && 		// it matches TWO over AND
					    board[r][c] == board[r+3][c]) 			// it matches THREE over
						return true;							// then it's a WINNER!
			
			
			//checking for winners inclined diagonally
			for(int c = 0; c < 4; c++)							// for the FIRST 4 columns
				for(int r = 0; r < 3; r++)						// for the FIRST 3 rows
					if (board[r][c] != '\u0000' &&				// if it's not empty, AND
						board[r][c] == board[r+1][c+1] &&		// it matches its inclined diagonally neighbor 1 AND
						board[r][c] == board[r+2][c+2] &&		// it matches the SECOND one over AND
						board[r][c] == board[r+3][c+3])			// it matches the THIRD one over AND
						return true;							// then it's a WINNER!
			
			
			//checking for winners declined diagonally
			for(int r = 0; r < 3; r++)							// for the FIRST 3 rows
				for(int c = 6; c >= 3; c--)						// for the LAST 4 columns
					if (board[r][c] != '\u0000' &&				// if it's not empty, AND
						board[r][c] == board[r+1][c-1] &&		// it matches its declined diagonally neighbor 1 AND
						board[r][c] == board[r+2][c-2] &&		// it matches the SECOND one over AND
						board[r][c] == board[r+3][c-3])			// it matches the THIRD one over AND
						return true;							// then it's a WINNER!
			
			
			return false; 										// If we haven't hit any of the above cases, then we don't have a winner
		}
		
}
