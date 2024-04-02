package parser;

import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * @author Joseph Phillips
 */
public class GraphParser {
	public ArrayList<Node> stopList = new ArrayList<>();
	public Arc relations = new Arc();
	
	// PURPOSE: To parse non-terminal 'Fail' from 'tokenizer'.
	protected void parse(TokenStream tokenizer) throws Exception {
		// I. Application validity check:

		// II. Attempt to parse finish:
		Symbol.symbol_ty peek = tokenizer.peek();
		String name0;
		String name1;

		if (peek == Symbol.symbol_ty.NODE_KEYWORD_SYM) {
			// II.A. Parse "S -> node ( <string> ) S"
			tokenizer.advance();

			if (tokenizer.peek() != Symbol.symbol_ty.BEGIN_PAREN_SYM) {
				throw new Exception("Expected \"(\"");
			}

			tokenizer.advance();

			if (tokenizer.peek() != Symbol.symbol_ty.STRING_SYM) {
				throw new Exception("Expected node name");
			}

			name0 = tokenizer.advance().getString();

			if (tokenizer.peek() != Symbol.symbol_ty.END_PAREN_SYM) {
				throw new Exception("Expected \")\"");
			}

			tokenizer.advance();
			addNode(name0);
			parse(tokenizer);
		} else if (peek == Symbol.symbol_ty.ARC_KEYWORD_SYM) {
			// II.B. Parse "S -> arc ( <string> , <string> ) S"
			tokenizer.advance();

			if (tokenizer.peek() != Symbol.symbol_ty.BEGIN_PAREN_SYM) {
				throw new Exception("Expected \"(\"");
			}

			tokenizer.advance();

			if (tokenizer.peek() != Symbol.symbol_ty.STRING_SYM) {
				throw new Exception("Expected node name");
			}

			name0 = tokenizer.advance().getString();

			if (tokenizer.peek() != Symbol.symbol_ty.COMMA_SYM) {
				throw new Exception("Expected \",\"");
			}

			tokenizer.advance();

			if (tokenizer.peek() != Symbol.symbol_ty.STRING_SYM) {
				throw new Exception("Expected node name");
			}

			name1 = tokenizer.advance().getString();

			if (tokenizer.peek() != Symbol.symbol_ty.END_PAREN_SYM) {
				throw new Exception("Expected \")\"");
			}

			tokenizer.advance();
			addArc(name0, name1);
			parse(tokenizer);
		} else if (peek == Symbol.symbol_ty.INCLUDES_KEYWORD_SYM) {
			// II.C. Parse "S -> includes ( <string> , <string> ) S"
			Node node;

			tokenizer.advance();

			if (tokenizer.peek() != Symbol.symbol_ty.BEGIN_PAREN_SYM) {
				throw new Exception("Expected \"(\"");
			}

			tokenizer.advance();

			if (tokenizer.peek() != Symbol.symbol_ty.STRING_SYM) {
				throw new Exception("Expected node name");
			}

			name0 = tokenizer.advance().getString();

			if (tokenizer.peek() != Symbol.symbol_ty.COMMA_SYM) {
				throw new Exception("Expected \",\"");
			}

			tokenizer.advance();

			if (tokenizer.peek() != Symbol.symbol_ty.STRING_SYM) {
				throw new Exception("Expected node name");
			}

			name1 = tokenizer.advance().getString();

			if (tokenizer.peek() != Symbol.symbol_ty.END_PAREN_SYM) {
				throw new Exception("Expected \")\"");
			}

			tokenizer.advance();
			node = getNode(name0);

			if (node == null) {
				throw new Exception("What is " + name0 + "?  Unknown node.");
			}

			node.addSubnode(name1);
			parse(tokenizer);
		} else if (peek == Symbol.symbol_ty.END_OF_INPUT_SYM) {
			// Literally do nothing, this is the empty production
		} else {
			throw new Exception("Expected \"node\", \"arc\" or \"includes\" instead of " + Symbol.getName(peek));
		}

		// III. Finished:
	}

	// PURPOSE: To return a string with the expression to compute, either from
	// the command line or the keyboard. 'argc' tells how many arguments
	// were on the command line and 'argv[]' points to those arguments.
	protected static String getInput(String args[]) {
		// I. Application validity check:

		// II. Get input:
		// II.A. Handle command line input:
		if (args.length > 0)
			return (args[0]);

		// II.B. Handle keyboard input:
		Scanner input = new Scanner(System.in);

		System.out.print("Definition of graph: ");

		// III. Finished:
		return (input.nextLine());
	}

	// PURPOSE: To initialize 'this' Graph from the definition read from
	// 'tokenizer'. No return value.
	public GraphParser(TokenStream tokenizer) throws Exception {
		parse(tokenizer);
	}

	// PURPOSE: To return a Node named 'name', or 'null' if no such node
	// exists.
	public Node getNode(String name) {
		// !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!!
		//
		// THIS IS A DUMMY-ASS METHOD! REPLACE IT WITH A *REAL* METHOD!
		//
		// !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!!
		for(Node a: stopList) {
			if(a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}
	
	public String getSuperNodeName(String name) {
		for(Node a: stopList) {
			if(a.getSubnodes().contains(name)) {
				return a.getName();
			}
		}
		return null;
	}
	
	public Node getSuperNode(String name) {
		for(Node a: stopList) {
			if(a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}
	// PURPOSE: To attempt to add a node named 'name'. No return value.
	public void addNode(String name) {
		// !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!!
		//
		// THIS IS A DUMMY-ASS METHOD! REPLACE IT WITH A *REAL* METHOD!
		//
		// !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!!
		//Fire technique!
		stopList.add(new Node(name));
	}

	// PURPOSE: To attempt to add an arc from a node named 'fromName' to a node
	// named 'toName'. No return value.
	public void addArc(String fromName, String toName) {
		// !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!!
		//
		// THIS IS A DUMMY-ASS METHOD! REPLACE IT WITH A *REAL* METHOD!
		//
		// !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!! !!!!

		relations.arc(fromName, toName);
		relations.arc(toName, fromName);
	}

	// PURPOSE: To get, and attempt to compute, the expression. The expression
	// may either come from the command line or the keyboard. 'args[]' has
	// the command line arguments. No return value.
	public static void main(String args[]) {
		//String input = getInput(args);
		File myObj = new File("C:\\Users\\jojoa\\Downloads\\AI HW\\JosephAI\\src\\parser\\ctaTrain.txt");
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
	}
}
