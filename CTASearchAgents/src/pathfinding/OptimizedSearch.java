package pathfinding;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import aima.Action;
import aima.IterativeDeepeningSearch;
import aima.Problem;
import parser.GraphParser;
import parser.InputCharStream;
import parser.NodeFunctionGenerator;
import parser.PathAndLength;
import parser.TokenStream;

public class OptimizedSearch {
	public static void main(String[] args) {
		Long total = 0L;
		Scanner obj = new Scanner(System.in);
	    System.out.println("Enter your CTA Train abstract file path:");
	    String filePath = obj.nextLine();
	    System.out.println("\nEnter your CTA Train file path:");
	    String otherFilePath = obj.nextLine();
	    
		File myObj = new File(filePath);
		String input = myObj.toString();
		InputCharStream charStream = null;
		TokenStream tokenizer = null;
		GraphParser graph = null;
		String fullPath = "";

		try {
			charStream = new InputCharStream(input);
			tokenizer = new TokenStream(charStream);
			graph = new GraphParser(tokenizer);
		} catch (Exception error) {
			if (tokenizer != null) {
				System.err.print("At " + tokenizer.getLocation() + ": ");
			}

			System.err.println(error.toString());
		}

		System.out.println("\nEnter your First stop: ");
		String st = obj.nextLine();
		System.out.println("\nEnter your Last stop: ");
		String en = obj.nextLine();
		
		String start = graph.getSuperNodeName(st);
		String end = graph.getSuperNodeName(en);
		
		NodeFunctionGenerator cta = new NodeFunctionGenerator(end, graph.relations);
		Problem problem = new Problem(start, cta.getActionfunction(), cta.getResultFunction(), cta.getGoalFunction());
		IterativeDeepeningSearch searchAgent = new IterativeDeepeningSearch();
		String path = searchAgent.findNode(problem).toString();
		total+=searchAgent.dlsCount;
		System.out.println("\n" + path.substring(9) + "\n");

		ArrayList<String> superNodes = new ArrayList<>();
		superNodes.add(start);
		for(Action a: searchAgent.findActions(problem)) {
			String j = a.toString();
			j = j.substring(1,j.length()-1);
			superNodes.add(j);
		}
		
		String[] superNodess = superNodes.toArray(new String[0]);
		OptimizedSearch test = new OptimizedSearch();
		String[] transitStops = test.getAllTransitionStops(st, en, superNodess, graph);
		PathAndLength recurseBuild = new PathAndLength();
		for(int i = 0; i < transitStops.length-1; i++) {
			if(i != transitStops.length-2) {
				recurseBuild = test.iDDFS(transitStops[i], transitStops[i+1], otherFilePath);
				total+=recurseBuild.recursions;
				fullPath += recurseBuild.path + " - > ";
			}
			else {
				recurseBuild = test.iDDFS(transitStops[i], transitStops[i+1], otherFilePath);
				total+=recurseBuild.recursions;
				fullPath += recurseBuild.path;
			}
		}
		fullPath = st + " - >" + fullPath;
		System.out.println(fullPath + "\n \n" + "Total Recursive Calls: " + total);
		
	}
	
	public PathAndLength iDDFS(String start, String end, String filePath) {
		PathAndLength recurseBuild = new PathAndLength();
		File myObj = new File(filePath);
		String input = myObj.toString();
		InputCharStream charStream = null;
		TokenStream tokenizer = null;
		GraphParser graph = null;
		try {
			charStream = new InputCharStream(input);
			tokenizer = new TokenStream(charStream);
			graph = new GraphParser(tokenizer);
		} catch (Exception error) {
			if (tokenizer != null) {
				System.err.print("At " + tokenizer.getLocation() + ": ");
			}

			System.err.println(error.toString());
		}
		NodeFunctionGenerator cta = new NodeFunctionGenerator(end, graph.relations);
		Problem problem = new Problem(start, cta.getActionfunction(), cta.getResultFunction(), cta.getGoalFunction());
		IterativeDeepeningSearch searchAgent = new IterativeDeepeningSearch();
		recurseBuild.path = searchAgent.findNode(problem).toString().substring(9);
		int temp = recurseBuild.path.indexOf(">");
		recurseBuild.path = recurseBuild.path.substring(temp+1);
		recurseBuild.recursions = searchAgent.dlsCount;
		
		return recurseBuild;
	}
	
	
	
	public String[] getAllTransitionStops(String start, String end, String[] j, GraphParser graph) {
		String[] transitStops = new String[j.length+1];
		transitStops[0] = start;
		transitStops[transitStops.length-1] = end;
		for(int i = 0; i < j.length-1; i++) {
			String a = j[i];
			String b = j[i+1];
			for(String c: graph.getSuperNode(a).getSubnodes()) {
				if(graph.getSuperNode(b).getSubnodes().contains(c)) {
					transitStops[i+1] = c;
				}
			}
		}
		return transitStops;
	}
	
}
