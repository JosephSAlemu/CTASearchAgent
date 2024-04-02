package parser;

import java.util.ArrayList;

public class Node {
	private String name;
	private ArrayList<String> subNodes;

	public Node(String name) {
		this.name = name;
		subNodes = new ArrayList<>();
	}

	public String getName(){ 
		return name;
	}

	public void addSubnode(String subNode){
		subNodes.add(subNode);
	}

	public ArrayList<String> getSubnodes(){
		return subNodes;
	}
}
