package basicgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphAdjList extends Graph {

  private Map<Integer, ArrayList<Integer>> adjListMap;

  public GraphAdjList() {
    adjListMap = new HashMap<>();
  }

  @Override public void implementAddVertex() {
    int v = getNumVertices();
    ArrayList<Integer> neighbors = new ArrayList<>();
    adjListMap.put(v, neighbors);
  }

  @Override public void implementAddEdge(int v, int w) {
    (adjListMap.get(v)).add(w);
  }

  @Override public List<Integer> getNeighbors(int v) {
    return new ArrayList<>(adjListMap.get(v));
  }

  @Override public List<Integer> getInNeighbors(int v) {
    List<Integer> inNeighbors = new ArrayList<>();
    for (int u : adjListMap.keySet()) {
      for (int w : adjListMap.get(u)) {
        if (v == w) {
          inNeighbors.add(u);
        }
      }
    }
    return inNeighbors;
  }

  @Override public List<Integer> getTwoHopDistance(int v) {
    List<Integer> twoHop = new ArrayList<>();
    for (int u : getNeighbors(v)) {
      twoHop.addAll(getNeighbors(u));
    }
    return twoHop;
  }

  @Override public String adjacencyString() {
    StringBuilder s = new StringBuilder("Adjacency List");

    s.append(" (size ")
        .append(getNumVertices())
        .append("+")
        .append(getNumEdges())
        .append(" integers)");

    for (int v : adjListMap.keySet()) {
      for (int w : adjListMap.get(v)) {
        s.append(w).append(", ");
      }
    }
    return s.toString();
  }
}
