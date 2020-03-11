/**
 * miniJava Abstract Syntax Tree classes
 * @author prins
 * @version COMP 520 (v2.2)
 */
package miniJava.AbstractSyntaxTrees;

import miniJava.SyntacticAnalyzer.SourcePosition;
import miniJava.SyntacticAnalyzer.TypeError;

public class BinaryExpr extends Expression
{
    public BinaryExpr(Operator o, Expression e1, Expression e2, SourcePosition posn){
        super(posn);
        operator = o;
        left = e1;
        right = e2;
    }
        
    public <A,R> R visit(Visitor<A,R> v, A o) {
        return v.visitBinaryExpr(this, o);
    }

    public <R> void visit(Traveller<R> v) throws TypeError {
        v.visitBinaryExpr(this);
    }
    
    public Operator operator;
    public Expression left;
    public Expression right;
}