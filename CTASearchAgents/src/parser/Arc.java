package parser;

import java.util.ArrayList;
import java.util.HashMap;

public class Arc {
	private HashMap<String, ArrayList<String>> connected;

	Arc() {
		connected = new HashMap<>();
	}

	public void arc(String node, String neighbor) {
		if (!connected.containsKey(node)) {
			connected.put(node, new ArrayList<>());
		}
		connected.get(node).add(neighbor);
	}
	
	public ArrayList<String> getNeighbors(String node){
		return connected.get(node);
	}
}
