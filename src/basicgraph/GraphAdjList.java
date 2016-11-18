package basicgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphAdjList extends Graph {

	private Map<Integer, ArrayList<Integer>> adjListMap;
	
	public GraphAdjList() {
		adjListMap = new HashMap<Integer, ArrayList<Integer>>();
	}
	
	@Override
	public void implementAddVertex() {
		int v = getNumVertices();
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		adjListMap.put(v, neighbors);
	}

	@Override
	public void implementAddEdge(int v, int w) {
		(adjListMap.get(v)).add(w);
	}

	@Override
	public List<Integer> getNeighbors(int v) {
		return new ArrayList<Integer>(adjListMap.get(v));
	}

	@Override
	public List<Integer> getInNeighbors(int v) {
		List<Integer> inNeighbors = new ArrayList<Integer>();
		for(int u: adjListMap.keySet()) {
			for(int w : adjListMap.get(u)) {
				if(v == w) {
					inNeighbors.add(u);
				}
			}
		}
		return inNeighbors;
	}

	@Override
	public List<Integer> getDistance2(int v) {
		// TODO: Implementation 4
		return null;
	}

	@Override
	public String adjacencyString() {
		String s = "Adjacency List";
		s += " (size " + getNumVertices() + "+" + getNumEdges() + " integers)";
		
		for(int v: adjListMap.keySet()) {
			for(int w: adjListMap.get(v)) {
				s += w+ ", ";
			}
		}
		return s;
	}
}