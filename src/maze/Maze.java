package maze;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class Maze {
	
	private MazeNode[][] cells;
	private int width;
	private int height;
	
	private final int DEFAULT_SIZE = 10;
	
	public Maze() {
		cells = new MazeNode[DEFAULT_SIZE][DEFAULT_SIZE];
		this.width = DEFAULT_SIZE;
		this.height = DEFAULT_SIZE;
	}
	
	public Maze(int width, int height) {
		cells = new MazeNode[width][height];
		this.width = width;
		this.height = height;
	}
	
	public void initialise(int width, int height) {
		cells = new MazeNode[width][height];
		this.width = width;
		this.height = height;
	}
	
	public void addNode(int row, int column) {
		cells[row][column] = new MazeNode(row, column);
	}
	
	public void linkEdges() {
		int numRows = cells.length;
		for(int r = 0; r < numRows; r++) {
			int numCols = cells[r].length;
			for(int c = 0; c < numCols; c++) {
				if(cells[r][c] != null) {
					if(r > 0 && cells[r - 1][c] != null) {
						cells[r][c].addNeighbor(cells[r-1][c]);
					}
					if(c > 0 && cells[r][c - 1] != null) {
						cells[r][c].addNeighbor(cells[r][c - 1]);
					}
					if(r < numRows - 1 && cells[r + 1][c] != null) {
						cells[r][c].addNeighbor(cells[r + 1][c]);
					}
					if(c < numCols - 1 && cells[r][c + 1] != null) {
						cells[r][c].addNeighbor(cells[r][c + 1]);
					}
				}
			}
		}
	}
	
	public void printMaze() {
		for(int r = 0; r < height; r++) {
			for(int c = 0; c < width; c++) {
				if(cells[r][c] == null) {
					System.out.print("*");
				}
				else {
					System.out.print(cells[r][c].getDisplayChar());
				}
			}
			System.out.println();
		}
	}
	
	public void setPath(List<MazeNode> path) {
		int index = 0;
		for(MazeNode n: path) {
			if(index == 0) {
				n.setDisplayChar(MazeNode.START);
			}
			else if(index == path.size() - 1) {
				n.setDisplayChar(MazeNode.GOAL);
			}
			else {
				n.setDisplayChar(MazeNode.PATH);
			}
			index++;
		}
	}
	
	public void clearPath() {
		for(int r=0;r<cells.length;r++) {
			for(int c=0;c<cells[r].length;c++) {
				MazeNode n = cells[r][c];
				if(n != null) {
					n.setDisplayChar(MazeNode.EMPTY);
				}
			}
		}
	}
	
	public List<MazeNode> dfs(int startRow, int startCol, int endRow, int endCol) {
		MazeNode start = cells[startRow][startCol];
		MazeNode goal = cells[endRow][endCol];
		
		if(start == null || goal == null) {
			System.out.println("Start or end node is null!. No path exists");
			return new LinkedList<MazeNode>();
		}
		
		HashMap<MazeNode, MazeNode> parentMap = new HashMap<MazeNode, MazeNode>();
		boolean found = dfsSearch(start, goal, parentMap);
		
		if(!found) {
			System.out.println("No path exists");
			return new LinkedList<MazeNode>();
		}
		
		return constructPath(start, goal, parentMap);
	}
	
	private boolean dfsSearch(MazeNode start, MazeNode goal, HashMap<MazeNode, MazeNode> parentMap) {
		HashSet<MazeNode> visited = new HashSet<MazeNode>();
		Stack<MazeNode> toExplore = new Stack<MazeNode>();
		toExplore.push(start);
		boolean found = false;
		
		while(!toExplore.isEmpty()) {
			MazeNode current = toExplore.pop();
			if(current == goal) {
				found = true;
				break;
			}
			List<MazeNode> neighbors = current.getNeighbors();
			ListIterator<MazeNode> it = neighbors.listIterator(neighbors.size());
			while(it.hasPrevious()) {
				MazeNode next = it.previous();
				if(!visited.contains(next)) {
					visited.add(next);
					parentMap.put(next, current);
					toExplore.push(next);
				}
			}
		}
		return found;
	}
	
	private List<MazeNode> constructPath(MazeNode start, MazeNode goal, HashMap<MazeNode, MazeNode> parentMap) {
		LinkedList<MazeNode> path = new LinkedList<MazeNode>();
		MazeNode current = goal;
		while(current != start) {
			path.addFirst(current);
			current = parentMap.get(current);
		}
		path.addFirst(start);
		return path;
	}
	
	public List<MazeNode> bfs(int startRow, int startCol, int endRow, int endCol) {
		
		MazeNode start = cells[startRow][startCol];
		MazeNode goal = cells[endRow][endCol];
		
		if(start == null || goal == null) {
			System.out.println("Start or Goal node is null! No path exists.");
			return new LinkedList<MazeNode>();
		}
		
		HashMap<MazeNode, MazeNode> parentMap = new HashMap<MazeNode, MazeNode>();
		HashSet<MazeNode> visited = new HashSet<MazeNode>();
		Queue<MazeNode> toExplore = new LinkedList<MazeNode>();
		toExplore.add(start);
		boolean found = false;
		
		while(!toExplore.isEmpty()) {
			MazeNode current = toExplore.remove();
			if(current == goal) {
				found = true;
				break;
			}
			List<MazeNode> neighbors = current.getNeighbors();
			ListIterator<MazeNode> it = neighbors.listIterator(neighbors.size());
			while(it.hasPrevious()) {
				MazeNode next = it.previous();
				if(!visited.contains(next)) {
					visited.add(next);
					parentMap.put(next, current);
					toExplore.add(next);
				}
			}
		}
		
		if(!found) {
			System.out.println("No path found!");
			return new LinkedList<MazeNode>();
		}
		
		LinkedList<MazeNode> path = new LinkedList<MazeNode>();
		MazeNode current = goal;
		while(current != start) {
			path.addFirst(current);
			current = parentMap.get(current);
		}
		path.addFirst(start);
		return path;
	}
	
	public static void main(String args[]) {
		String mazeFile = "data/mazes/maze1.maze";
		Maze maze = new Maze();
		MazeLoader.loadMaze(mazeFile, maze);
		maze.printMaze();
		List<MazeNode> path = maze.dfs(3, 3, 2, 0);
		maze.setPath(path);
		System.out.println();
		maze.printMaze();
		maze.clearPath();
		maze.setPath(maze.bfs(3, 3, 2, 0));
		System.out.println();
		maze.printMaze();
	}
}
