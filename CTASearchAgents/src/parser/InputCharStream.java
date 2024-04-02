package parser;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Joseph Phillips
 *  PURPOSE:  To implement an interface that manages the character source.
 */
public class InputCharStream {
	// I. Member vars:
	// PURPOSE: To hold the input source.
	private BufferedReader reader_;

	// PURPOSE: To hold the filepath.
	private String filepath_;

	// PURPOSE: To keep track of the line number.
	private int lineNum_ = 0;

	// PURPOSE: To hold the input.
	private String input_;

	// PURPOSE: To hold where the cursor is.
	private int index_;

	// II. Disallowed auto-generated methods:

	// III. Protected methods:

	// IV. Constructor(s), assignment op(s), factory(s) and destructor:
	// PURPOSE: To initialize 'this' to read from file 'filepath'.
	// No return value.
	public InputCharStream(String filepath) throws IOException {
		filepath_ = filepath;
		reader_ = new BufferedReader(new FileReader(filepath_));

		if ((input_ = reader_.readLine()) == null) {
			throw new IOException("Cannot read from " + filepath_);
		}

		lineNum_++;
		index_ = 0;
	}

	// V. Accessors:

	// VI. Mutators:

	// VII. Methods that do main and misc work of class:
	// PURPOSE: To return the current char, or '\0' if there are no more.
	// No parameters.
	public char peek() throws IOException {
		if (input_ == null) {
			return ('\0');
		}

		if (index_ >= input_.length()) {
			if ((input_ = reader_.readLine()) == null) {
				return ('\0');
			}

			lineNum_++;
			index_ = 0;
			return (peek());
		}

		return (input_.charAt(index_));
	}

	// PURPOSE: To return 'true' if at eof-of-input, or 'false' otherwise.
	public boolean isAtEnd() throws IOException {
		return (peek() == '\0');
	}

	// PURPOSE: To advance to the next char (if not already at end). No
	// parameters. No return value.
	public void advance() throws IOException {
		if (!isAtEnd()) {
			index_++;
		}
	}

	// PURPOSE: To advance to the beginning of the next line (if not already at
	// end). No parameters. No return value.
	public void advanceLine() throws IOException {
		if (!isAtEnd()) {
			input_ = reader_.readLine();

			if (input_ != null) {
				lineNum_++;
			}

			index_ = 0;
		}
	}

	// PURPOSE: To return a String telling the current location. No parameters.
	public String getLocation() {
		return ("line " + lineNum_ + " of " + filepath_);
	}
};
