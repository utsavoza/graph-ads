package maze;

import java.util.LinkedList;
import java.util.List;

public class MazeNode {

  private int row;
  private int column;
  private char displayChar;
  private List<MazeNode> neighbors;

  public static final char EMPTY = '-';
  public static final char PATH = 'o';
  public static final char START = 'S';
  public static final char GOAL = 'G';

  public MazeNode(int row, int column) {
    this.row = row;
    this.column = column;
    neighbors = new LinkedList<>();
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

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof MazeNode)) {
      return false;
    }
    MazeNode anotherNode = (MazeNode) obj;
    return this.row == anotherNode.row
        && this.column == anotherNode.column
        && this.displayChar == anotherNode.displayChar
        && this.neighbors.equals(anotherNode.neighbors);
  }

  @Override public int hashCode() {
    int hash = 17;
    hash += 31 * this.row + hash;
    hash += 31 * this.column + hash;
    hash += 31 * this.displayChar + hash;
    return hash;
  }
}
