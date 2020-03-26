/**
 * miniJava Abstract Syntax Tree classes
 * @author prins
 * @version COMP 520 (v2.2)
 */
package miniJava.AbstractSyntaxTrees;

import miniJava.SyntacticAnalyzer.IdentificationError;
import miniJava.SyntacticAnalyzer.SourcePosition;
import miniJava.SyntacticAnalyzer.TypeError;

public class BlockStmt extends Statement
{
    public BlockStmt(StatementList sl, SourcePosition posn){
        super(posn);
        this.sl = sl;
    }
        
    public <A,R> R visit(Visitor<A,R> v, A o) {
        return v.visitBlockStmt(this, o);
    }

    public <R> void visit(Traveller<R> v) throws TypeError, IdentificationError {
        v.visitBlockStmt(this);
    }
   
    public StatementList sl;
}