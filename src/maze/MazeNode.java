package maze;

import java.util.LinkedList;
import java.util.List;

public class MazeNode {

  private List<MazeNode> neighbors;
  private int row;
  private int column;
  private char displayChar;

  public static final char EMPTY = '-';
  public static final char PATH = 'o';
  public static final char START = 'S';
  public static final char GOAL = 'G';

  public MazeNode(int row, int column) {
    this.row = row;
    this.column = column;
    neighbors = new LinkedList<MazeNode>();
    displayChar = EMPTY;
  }

  public List<MazeNode> getNeighbors() {
    return neighbors;
  }

  public void addNeighbor(MazeNode neighbor) {
    neighbors.add(neighbor);
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public char getDisplayChar() {
    return displayChar;
  }

  public void setDisplayChar(char displayChar) {
    this.displayChar = displayChar;
  }


}
