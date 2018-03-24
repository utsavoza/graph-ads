package maze;

import java.util.List;

public class Main {
  public static void main(String args[]) {
    String mazeFile = "data/mazes/maze1.maze";
    Maze maze = new Maze(mazeFile);
    System.out.println("Maze:");
    maze.printMaze();

    List<MazeNode> path = maze.dfs(3, 3, 2, 0);
    System.out.println("\n\nDFS Path:");
    maze.setPath(path);
    maze.printMaze();

    maze.clearPath();

    System.out.println("\n\nBFS Path:");
    maze.setPath(maze.bfs(3, 3, 2, 0));
    maze.printMaze();
  }
}
