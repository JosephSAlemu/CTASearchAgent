package parser;

/**
 * @author Joseph Phillips
 */

//  PURPOSE:  To represent a parsed symbol, and any associated data.
class Symbol {
	// PURPOSE: To represent the different lexemes in the language.
	public enum symbol_ty {
		END_OF_INPUT_SYM,

		NODE_KEYWORD_SYM, ARC_KEYWORD_SYM, INCLUDES_KEYWORD_SYM,

		PERIOD_SYM, NUMBER_SYM, IDENTIFIER_SYM, STRING_SYM, BEGIN_PAREN_SYM, END_PAREN_SYM, COMMA_SYM
	}

	// PURPOSE: To give names to the various lexemes.
	public static String getName(symbol_ty symbol) {
		switch (symbol) {
		case END_OF_INPUT_SYM:
			return ("<eof>");
		case NODE_KEYWORD_SYM:
			return ("node");
		case ARC_KEYWORD_SYM:
			return ("arc");
		case INCLUDES_KEYWORD_SYM:
			return ("includes");
		case PERIOD_SYM:
			return (".");
		case NUMBER_SYM:
			return ("<number>");
		case IDENTIFIER_SYM:
			return ("<identifier>");
		case STRING_SYM:
			return ("<string>");
		case BEGIN_PAREN_SYM:
			return ("(");
		case END_PAREN_SYM:
			return (")");
		case COMMA_SYM:
			return (".");
		}
		return ("(unknown)");
	}

	// I. Member vars:
	// PURPOSE: To tell the type of symbol that '*this' represents.
	private symbol_ty symbol_;

	// PURPOSE: To hold the address of a string associated with '*this' symbol
	// (if there is one).
	private String string_;

	// PURPOSE: To hold the number associated with '*this' symbol (if there
	// is one).
	private double number_;

	// II. Disallowed auto-generated methods:

	// III. Protected methods:

	// IV. Constructor(s), assignment op(s), factory(s) and destructor:
	// PURPOSE: To initialize '*this' to hold symbol 'newSymbol'. No return
	// value.
	public Symbol(symbol_ty newSymbol) {
		symbol_ = newSymbol;
		string_ = null;
		number_ = 0.0;
	}

	// PURPOSE: To initialize '*this' to hold string 'newString'. No return
	// value.
	public Symbol(String newString) {
		symbol_ = symbol_ty.STRING_SYM;
		string_ = newString;
		number_ = 0.0;
	}

	// PURPOSE: To initialize '*this' to hold number 'newNumber'. No return
	// value.
	public Symbol(double newNumber) {
		symbol_ = symbol_ty.NUMBER_SYM;
		string_ = null;
		number_ = newNumber;
	}

	// V. Accessors:
	// PURPOSE: To return the type associated with '*this' Symbol. No
	// parameters.
	public symbol_ty getType() {
		return (symbol_);
	}

	// PURPOSE: To return a reference to the string stored at '*this' Symbol,
	// or a reference to the empty string if '*this' is not a string.
	public String getString() {
		return ((string_ == null) ? "" : string_);
	}

	// PURPOSE: To return the number stored at '*this' Symbol, or '0' if
	// '*this' does not represent a number.
	public double getNumber() {
		return (number_);
	}

};
