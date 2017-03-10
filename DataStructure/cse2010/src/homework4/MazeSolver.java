package homework4;


/*
 * CSE2010 Homework #4: MazeSolver.java
 * 
 * DO NOT MODIFY THIS FILE!
 * 
 */

public class MazeSolver {

	public static void main(String[] args) {
		
		Maze maze = new Maze(args[0]);
		
		maze.printMaze();

		if (maze.findPath()) maze.showPath();
		else
			System.out.println("No path found");
	}
}
