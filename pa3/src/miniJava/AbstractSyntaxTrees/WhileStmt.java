/**
 * miniJava Abstract Syntax Tree classes
 * @author prins
 * @version COMP 520 (v2.2)
 */
package miniJava.AbstractSyntaxTrees;

import miniJava.SyntacticAnalyzer.SourcePosition;
import miniJava.SyntacticAnalyzer.TypeError;

public class WhileStmt extends Statement
{
    public WhileStmt(Expression e, Statement s, SourcePosition posn){
        super(posn);
        cond = e;
        body = s;
    }
        
    public <A,R> R visit(Visitor<A,R> v, A o) {
        return v.visitWhileStmt(this, o);
    }

    public <R> void visit(Traveller<R> v) throws TypeError {
        v.visitWhileStmt(this);
      }

    public Expression cond;
    public Statement body;
}
