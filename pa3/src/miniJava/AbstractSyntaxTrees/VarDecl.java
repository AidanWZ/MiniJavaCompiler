/**
 * miniJava Abstract Syntax Tree classes
 * @author prins
 * @version COMP 520 (v2.2)
 */
package miniJava.AbstractSyntaxTrees;

import miniJava.ContextualAnalyzer.IdentificationError;
import miniJava.ContextualAnalyzer.Traveller;
import miniJava.ContextualAnalyzer.TypeError;
import miniJava.ContextualAnalyzer.Visitor;
import miniJava.SyntacticAnalyzer.SourcePosition;

public class VarDecl extends LocalDecl {
	
	public VarDecl(TypeDenoter t, String name, SourcePosition posn) {
		super(name, t, posn);
	}
	
	public <A,R> R visit(Visitor<A,R> v, A o) {
		return v.visitVarDecl(this, o);
	}

	public <R> void visit(Traveller<R> v) throws TypeError, IdentificationError{
		v.visitVarDecl(this);
	  }
}
