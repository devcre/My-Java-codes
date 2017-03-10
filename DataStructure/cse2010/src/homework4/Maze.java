package homework4;
/*
 * CSE2010 Homework #4: Maze.java
 * 
 * Fill your code here!
 * 
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Maze {
	private int numRows;
	private int numCols;

	private int[][] myMaze = null;
	private boolean[][] visited = null;

	private int startI = 0; private int startJ = 0; // Entry point
	private int finalI;	private int finalJ = 0;		// Exit point
	
	// Create Stack of Location with a capacity of 100 
	private ArrayStack<Location> stack = new ArrayStack<>(100);
	
	/*
	 * DO NOT MODIFY THIS CONSTRUCTOR CODE!
	 */	
	public Maze(String filename) {
		try {
			Scanner sc = new Scanner(new File(filename));
			
			numRows = sc.nextInt();
			numCols = sc.nextInt();
			
			finalI = numRows - 1;
			finalJ = numCols - 1;
			
			// Create Maze
			myMaze = new int[numRows][numCols];
			visited = new boolean[numRows][numCols];
			
			for (int i = 0; i < numRows; i++)
				for (int j = 0; j < numCols; j++) {
					myMaze[i][j] = sc.nextInt();
					visited[i][j] = false;
			}
			
			sc.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	/*
	 * DO NOT MODIFY THIS METHOD!
	 */	
	public void printMaze() {
		
		System.out.println("Maze[" + numRows + "][" + numCols + "]");
		System.out.println("Entry index = (" + startI + ", " + startJ + ")" );
		System.out.println("Exit index = (" + finalI + ", " + finalJ + ")"  + "\n");
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++)
				System.out.print(myMaze[i][j] + " ");
			System.out.print("\n");
		}
		System.out.println();
	}
	
	/*
	 * DO NOT MODIFY THIS METHOD!
	 */	
	public boolean findPath() {
		
		return moveTo(startI,startJ);
	}
	
	/*
     * Complete the following recursive method.
     */	
	private boolean moveTo(int row, int col) {
		Location nLocation;
		Location bLocation;
		
		if(row-1 < 5 && row-1 > 0 && visited[row-1][col] != true && myMaze[row-1][col] == 0){
			nLocation = new Location(row,col);
			stack.push(nLocation);
			visited[row][col] = true;
			return moveTo(row-1, col);
		}
		else if(col+1 < 5 && col+1 > 0 && visited[row][col+1] != true &&myMaze[row][col+1] == 0){
			nLocation = new Location(row,col);
			stack.push(nLocation);
			visited[row][col] = true;
			return moveTo(row, col+1);
		}
		else if(row+1 < 5 && row+1 > 0 && visited[row+1][col] != true && myMaze[row+1][col] == 0){
			nLocation = new Location(row,col);
			stack.push(nLocation);
			visited[row][col] = true;
			return moveTo(row+1, col);
		}
		else if(col-1 < 5 && col-1 > 0 && visited[row][col-1] != true && myMaze[row][col-1] == 0){
			nLocation = new Location(row,col);
			stack.push(nLocation);
			visited[row][col] = true;
			return moveTo(row, col-1);
		}
		else if(row == 4 && col == 4){
			return true;
		}
		else{
			bLocation = stack.pop();
			visited[row][col] = true;
			return moveTo(bLocation.x_coord,bLocation.y_coord);
		}
	}
	
	/*
	 * DO NOT MODIFY THIS METHOD!
	 */	
	public void showPath() {

		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " <-- ");
		}

		System.out.println("Start");
	}
	
}