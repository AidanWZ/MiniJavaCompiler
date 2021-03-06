/**
 * miniJava Abstract Syntax Tree classes
 * @author prins
 * @version COMP 520 (v2.2)
 */
package miniJava.AbstractSyntaxTrees;

import miniJava.CodeGenerator.Generator;
import miniJava.ContextualAnalyzer.IdentificationError;
import miniJava.ContextualAnalyzer.Traveller;
import miniJava.ContextualAnalyzer.Visitor;
import miniJava.ContextualAnalyzer.TypeError;
import miniJava.SyntacticAnalyzer.SourcePosition;

public class NewArrayExpr extends NewExpr
{
    public NewArrayExpr(TypeDenoter et, Expression e, SourcePosition posn){
        super(posn);
        eltType = et;
        sizeExpr = e;
    }
    
    public <A,R> R visit(Visitor<A,R> v, A o) {
        return v.visitNewArrayExpr(this, o);
    }

    public <R> void visit(Traveller<R> v) throws TypeError, IdentificationError {
        v.visitNewArrayExpr(this);
    }

    public <R> Object generate(Generator<R> generator) {
        return generator.visitNewArrayExpr(this);
    }

    public TypeDenoter eltType;
    public Expression sizeExpr;
}