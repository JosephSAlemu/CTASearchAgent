package parser;

import java.util.ArrayList;

class SuperNode {
	private String name;
	private ArrayList<String> subNodes;
	private String first;

	public SuperNode(String name) {
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
