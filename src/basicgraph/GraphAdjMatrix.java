package basicgraph;

import java.util.ArrayList;
import java.util.List;

public class GraphAdjMatrix extends Graph {

  private final int defaultNumVertices = 5;
  private int[][] adjMatrix;

  public GraphAdjMatrix() {
    adjMatrix = new int[defaultNumVertices][defaultNumVertices];
  }

  @Override
  public void implementAddVertex() {
    int v = getNumVertices();
    if (v >= adjMatrix.length) {
      int[][] newAdjMatrix = new int[v * 2][v * 2];
      for (int i = 0; i < adjMatrix.length; i++) {
        for (int j = 0; j < adjMatrix.length; j++) {
          newAdjMatrix[i][j] = adjMatrix[i][j];
        }
      }
      adjMatrix = newAdjMatrix;
    }
  }

  @Override
  public void implementAddEdge(int v, int w) {
    adjMatrix[v][w] += 1;
  }

  @Override
  public List<Integer> getNeighbors(int v) {
    List<Integer> neighbors = new ArrayList<Integer>();
    for (int i = 0; i < getNumVertices(); i++) {
      for (int j = 0; j < adjMatrix[v][i]; j++) {
        neighbors.add(i);
      }
    }
    return neighbors;
  }

  @Override
  public List<Integer> getInNeighbors(int v) {
    List<Integer> inNeighbors = new ArrayList<Integer>();
    for (int i = 0; i < getNumVertices(); i++) {
      for (int j = 0; j < adjMatrix[i][v]; j++) {
        inNeighbors.add(i);
      }
    }
    return inNeighbors;
  }

  @Override
  public List<Integer> getDistance2(int v) {
    List<Integer> twoHop = new ArrayList<Integer>();
    for (int u : getNeighbors(v)) {
      twoHop.addAll(getNeighbors(u));
    }
    return twoHop;
  }

  @Override
  public String adjacencyString() {
    int dim = adjMatrix.length;
    String s = "Adjacency Matrix";
    s += " (size " + dim + "x" + dim + "= " + dim * dim + " integers):";
    for (int i = 0; i < dim; i++) {
      s += "\n\t" + i + ": ";
      for (int j = 0; j < adjMatrix[i].length; j++) {
        s += adjMatrix[i][j] + ", ";
      }
    }
    return s;
  }
}
