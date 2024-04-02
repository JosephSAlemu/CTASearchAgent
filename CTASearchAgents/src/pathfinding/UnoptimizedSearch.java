package pathfinding;

import java.io.File;
import java.util.Scanner;

import aima.IterativeDeepeningSearch;
import aima.Problem;
import parser.GraphParser;
import parser.InputCharStream;
import parser.NodeFunctionGenerator;
import parser.TokenStream;

public class UnoptimizedSearch {
	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);
	    System.out.println("Enter your file path: ");
	    String filePath = obj.nextLine();
	    
	    
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
		System.out.println("\nEnter your First stop: ");
		String start = obj.nextLine();
		System.out.println("\nEnter your Last stop: ");
		String end = obj.nextLine();
		NodeFunctionGenerator cta = new NodeFunctionGenerator(end, graph.relations);
		Problem problem = new Problem(start, cta.getActionfunction(), cta.getResultFunction(), cta.getGoalFunction());
		IterativeDeepeningSearch searchAgent = new IterativeDeepeningSearch();
		System.out.println("\n" + searchAgent.findNode(problem).toString().substring(9));
		System.out.println("Recursive Calls: " + searchAgent.dlsCount);
	}
}

