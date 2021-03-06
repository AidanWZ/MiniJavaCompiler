package miniJava.SyntacticAnalyzer;

import miniJava.SyntacticAnalyzer.SourceFile;
import miniJava.SyntacticAnalyzer.Token;

public final class Scanner {

	private SourceFile sourceFile;
	private char currentChar;
	private StringBuffer currentSpelling;
	private boolean currentlyScanningToken;
	private int lineNumber;
	private int charNumber;

	private boolean isLetter(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}
	private boolean isDigit(char c) {
	    return (c >= '0' && c <= '9');
	}

	public Scanner(SourceFile source) {
		sourceFile = source;
		currentChar = sourceFile.getSource();
		lineNumber = 0;
		charNumber = 0;
	}

	private void takeIt() {
	    if (currentlyScanningToken) {
			currentSpelling.append(currentChar);
			if (currentChar == '\n') {
				lineNumber++;
				charNumber = 0;
			}
	    }
		currentChar = sourceFile.getSource();
		charNumber++;
	  }

	private int scanSeparator() {
		switch (currentChar) {
		case '/': {
			takeIt();
			switch(currentChar) {
				case '/': {
					do takeIt();
					while(currentChar != '\n' && currentChar != '\u0000');
				}
				return 2;
				case '*': {
					takeIt();
					while (true){
						if(currentChar == '\u0000') {
							System.out.println("Debugging: Error message 4");
							System.exit(4);
							return 0;
						}
						if(currentChar != '*'){
							takeIt();
							continue;
						}
						takeIt();
						if(currentChar == '/'){
							takeIt();
							break;
						}		
					}
				}
				return 2;
				default:
					return -1;
			}
		}
		case '\n': case ' ': case '\t': case '\r': {
			do{
				takeIt();
			}
			while(currentChar == '\n' || currentChar == ' ' || currentChar == '\t' || currentChar == '\r'); {
				return 3;
			}	
		}
		default: 
			return 0;
		}
	}

	public Token scan() {
		Token tok;
		SourcePosition pos;
		int kind;
		currentlyScanningToken = false;
		int scanSeparatorStatus = 1;
		
		while (currentChar == '\n' || currentChar == ' ' || currentChar == '\t' || currentChar == '\r' || currentChar == '/') {
			scanSeparatorStatus = scanSeparator(); 
			if(scanSeparatorStatus == -1) {
				break;
			}
		}
		currentlyScanningToken = true;
	    currentSpelling = new StringBuffer("");
	    pos = new SourcePosition();
	    pos.start = sourceFile.getCurrentLine();

		if(scanSeparatorStatus == -1) {
			kind = Token.OPERATOR;
			currentSpelling.append('/');
		}
		else {
			kind = scanToken();
		}
		pos.finish = sourceFile.getCurrentLine();
		//tok = new Token(kind, currentSpelling.toString(), lineNumber, charNumber);	
		tok = new Token(kind, currentSpelling.toString(), pos);		
		return tok;
	}

	private int scanToken() {
		if(isLetter(currentChar) || currentChar == '_'){
			takeIt();
			while (isLetter(currentChar) || isDigit(currentChar) || currentChar == '_') {
				takeIt();
			} 
			return Token.IDENTIFIER;
		}
		if(isDigit(currentChar)){
			takeIt();
			while (isDigit(currentChar)) {
				takeIt();
			}
			return Token.NUM;
		}
		if(currentChar == '\"') {			
			takeIt();
			while (currentChar != '\"') {
				takeIt();
			}
			takeIt();
			return Token.STRING;
		}
		
		switch (currentChar) {
			case ';':
				takeIt();
				return Token.SEMICOLON;
			case ',':
				takeIt();
				return Token.COMMA;
			case '.':
				takeIt();
				return Token.PERIOD;	
			case '>':  case '<':  case '!':
				takeIt();
				if(currentChar == '=') {
					takeIt();
				}
				return Token.OPERATOR;	
			case '=': 
				takeIt();
				if(currentChar == '=') {
					takeIt();
					return Token.OPERATOR;
				}
				else {
					return Token.EQUALS;
				}		
			case '+':  case '-':  case '*':  case '/':
				takeIt();
				return Token.OPERATOR;	
			case '|':
				takeIt();
				if(currentChar == '|'){
					takeIt();
					return Token.OPERATOR;
				}
				break;
			case '&':
				takeIt();
				if(currentChar == '&'){
					takeIt();
					return Token.OPERATOR;
				}
				break;	
			case ':':
				takeIt();
				return Token.COLON;
			case ')':
				takeIt();
				return Token.RPAREN;
			case '}':
				takeIt();
				return Token.RCURLY;
			case '{':
				takeIt();
				return Token.LCURLY;	
			case '(':
				takeIt();
				return Token.LPAREN;
			case ']':
				takeIt();
				return Token.RBRACKET;
			case '[':
				takeIt();
				return Token.LBRACKET;
			case SourceFile.eot:
				return Token.EOT;
			default:
				takeIt();
				return Token.ERROR;
		}
		return Token.ERROR;
	}

	public int scanTokenInput(char c) {
		if(isLetter(currentChar)){
			return Token.IDENTIFIER;
		}
		if(isDigit(currentChar)){
			return Token.NUM;
		}
		
		switch (currentChar) {
			case ';':
				return Token.SEMICOLON;
			case ',':
				return Token.COMMA;
			case '.':
				return Token.PERIOD;	
			case '>':  case '<':  case '!':
				return Token.OPERATOR;		
			case '+':  case '-':  case '*':  case '/':
				return Token.OPERATOR;	
			case '|':
				return Token.OPERATOR;
			case '&':
				return Token.OPERATOR;
			case ':':
				return Token.COLON;
			case ')':
				return Token.RPAREN;
			case '}':
				return Token.RCURLY;
			case '{':
				return Token.LCURLY;	
			case '(':
				return Token.LPAREN;
			case ']':
				return Token.RBRACKET;
			case '[':
				return Token.LBRACKET;
			case SourceFile.eot:
				return Token.EOT;
			default:
				return Token.ERROR;
		}
	}
}