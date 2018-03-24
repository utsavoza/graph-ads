package basicgraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Graph {

  private int numVertices;
  private int numEdges;
  private Map<Integer, String> vertexLabels;

  public Graph() {
    numVertices = 0;
    numEdges = 0;
    vertexLabels = null;
  }

  public int getNumVertices() {
    return numVertices;
  }

  public int getNumEdges() {
    return numEdges;
  }

  public int addVertex() {
    implementAddVertex();
    numVertices++;
    return (numVertices - 1);
  }

  public abstract void implementAddVertex();

  public void addEdge(int v, int w) {
    numEdges++;
    if (v < numVertices && w < numVertices) {
      implementAddEdge(v, w);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  public abstract void implementAddEdge(int v, int w);

  public abstract List<Integer> getNeighbors(int v);

  public abstract List<Integer> getInNeighbors(int v);

  /**
   * The degree sequence of a graph is sorted list of the degrees of the vertices in the graph
   *
   * @return The degree sequence of the graph
   */
  public List<Integer> degreeSequence() {
    List<Integer> degSequence = new ArrayList<>();
    for (int i = 0; i < getNumVertices(); i++) {
      degSequence.add(getNeighbors(i).size() + getInNeighbors(i).size());
    }
    degSequence.sort(Comparator.reverseOrder());
    return degSequence;
  }

  /**
   * Get the list of vertices that are 2 steps away from the given vertex.
   *
   * @param v The starting vertex
   * @return A list if vertices that can be reached in exactly two steps (by following two edges) from vertex v.
   */
  public abstract List<Integer> getTwoHopDistance(int v);

  public String toString() {
    String s = "\nGraph with " + numVertices + " vertices and " + numEdges + " edges.\n";
    s += "Degree sequence: " + degreeSequence() + " vertices and " + numEdges + " edges.\n";
    if (numVertices <= 20) {
      s += adjacencyString();
    }
    return s;
  }

  public abstract String adjacencyString();

  public void initializeLabels() {
    vertexLabels = new HashMap<>();
  }

  public boolean hasVertex(int v) {
    return v < numVertices;
  }

  public boolean hasVertex(String s) {
    return vertexLabels.containsValue(s);
  }

  public void addLabel(int v, String s) {
    if (v < getNumVertices() && !vertexLabels.containsKey(v)) {
      vertexLabels.put(v, s);
    } else {
      System.out.println("ERROR: tried to label a vertex that is out of range or already labeled");
    }
  }

  public String getLabel(int v) {
    return vertexLabels.getOrDefault(v, null);
  }

  public int getIndex(String s) {
    for (Map.Entry<Integer, String> entry : vertexLabels.entrySet()) {
      if (entry.getValue().equals(s)) {
        return entry.getKey();
      }
    }
    System.out.println("ERROR: No vertex with this label");
    return -1;
  }
}
