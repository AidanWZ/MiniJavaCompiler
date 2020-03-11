package miniJava;

import miniJava.SyntacticAnalyzer.SourcePosition;

public class ErrorReporter {

	int numErrors;

	ErrorReporter() {
		this.numErrors = 0;
	}

	public void reportError(String message, String tokenName, SourcePosition pos) {
		//System.out.println("ERROR: " + message);
		this.numErrors++;
	}

	public void reportError(int lineNumber) {
		System.out.println("*** line " + lineNumber + "Type Error - incompatible types assignment");
	}

	public void reportRestriction(String message) {
		System.out.println("RESTRICTION: " + message);
	}
}