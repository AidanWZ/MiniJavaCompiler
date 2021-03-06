/**
 * miniJava Abstract Syntax Tree classes
 * @author prins
 * @version COMP 520 (v2.2)
 */
package miniJava.ContextualAnalyzer;

import miniJava.ErrorReporter;
import miniJava.AbstractSyntaxTrees.AST;
import miniJava.AbstractSyntaxTrees.ArrayType;
import miniJava.AbstractSyntaxTrees.AssignStmt;
import miniJava.AbstractSyntaxTrees.BaseType;
import miniJava.AbstractSyntaxTrees.BinaryExpr;
import miniJava.AbstractSyntaxTrees.BlockStmt;
import miniJava.AbstractSyntaxTrees.BooleanLiteral;
import miniJava.AbstractSyntaxTrees.CallExpr;
import miniJava.AbstractSyntaxTrees.CallStmt;
import miniJava.AbstractSyntaxTrees.ClassDecl;
import miniJava.AbstractSyntaxTrees.ClassDeclList;
import miniJava.AbstractSyntaxTrees.ClassType;
import miniJava.AbstractSyntaxTrees.Declaration;
import miniJava.AbstractSyntaxTrees.ExprList;
import miniJava.AbstractSyntaxTrees.Expression;
import miniJava.AbstractSyntaxTrees.FieldDecl;
import miniJava.AbstractSyntaxTrees.FieldDeclList;
import miniJava.AbstractSyntaxTrees.IdRef;
import miniJava.AbstractSyntaxTrees.Identifier;
import miniJava.AbstractSyntaxTrees.IfStmt;
import miniJava.AbstractSyntaxTrees.IntLiteral;
import miniJava.AbstractSyntaxTrees.IxAssignStmt;
import miniJava.AbstractSyntaxTrees.IxExpr;
import miniJava.AbstractSyntaxTrees.LiteralExpr;
import miniJava.AbstractSyntaxTrees.MemberDecl;
import miniJava.AbstractSyntaxTrees.MethodDecl;
import miniJava.AbstractSyntaxTrees.MethodDeclList;
import miniJava.AbstractSyntaxTrees.NewArrayExpr;
import miniJava.AbstractSyntaxTrees.NewObjectExpr;
import miniJava.AbstractSyntaxTrees.NewStringExpr;
import miniJava.AbstractSyntaxTrees.NullExpr;
import miniJava.AbstractSyntaxTrees.NullLiteral;
import miniJava.AbstractSyntaxTrees.Operator;
import miniJava.AbstractSyntaxTrees.Package;
import miniJava.AbstractSyntaxTrees.ParameterDecl;
import miniJava.AbstractSyntaxTrees.ParameterDeclList;
import miniJava.AbstractSyntaxTrees.QualRef;
import miniJava.AbstractSyntaxTrees.RefExpr;
import miniJava.AbstractSyntaxTrees.Reference;
import miniJava.AbstractSyntaxTrees.ReturnStmt;
import miniJava.AbstractSyntaxTrees.Statement;
import miniJava.AbstractSyntaxTrees.StatementList;
import miniJava.AbstractSyntaxTrees.StringLiteral;
import miniJava.AbstractSyntaxTrees.ThisRef;
import miniJava.ContextualAnalyzer.Traveller;
import miniJava.AbstractSyntaxTrees.TypeDenoter;
import miniJava.AbstractSyntaxTrees.TypeKind;
import miniJava.AbstractSyntaxTrees.UnaryExpr;
import miniJava.AbstractSyntaxTrees.VarDecl;
import miniJava.AbstractSyntaxTrees.VarDeclStmt;
import miniJava.AbstractSyntaxTrees.WhileStmt;
import miniJava.SyntacticAnalyzer.SourcePosition;
import miniJava.SyntacticAnalyzer.Token;

/**
 * An implementation of the Visitor interface provides a method visitX
 * for each non-abstract AST class X.  
 */
public interface Visitor<ArgType,ResultType> {

  // Package
    public ResultType visitPackage(Package prog, ArgType arg);

  // Declarations
    public ResultType visitClassDecl(ClassDecl cd, ArgType arg);
    public ResultType visitFieldDecl(FieldDecl fd, ArgType arg);
    public ResultType visitMethodDecl(MethodDecl md, ArgType arg);
    public ResultType visitParameterDecl(ParameterDecl pd, ArgType arg);
    public ResultType visitVarDecl(VarDecl decl, ArgType arg);
 
  // Types
    public ResultType visitBaseType(BaseType type, ArgType arg);
    public ResultType visitClassType(ClassType type, ArgType arg);
    public ResultType visitArrayType(ArrayType type, ArgType arg);
    
  // Statements
    public ResultType visitBlockStmt(BlockStmt stmt, ArgType arg);
    public ResultType visitVardeclStmt(VarDeclStmt stmt, ArgType arg);
    public ResultType visitAssignStmt(AssignStmt stmt, ArgType arg);
    public ResultType visitIxAssignStmt(IxAssignStmt stmt, ArgType arg);
    public ResultType visitCallStmt(CallStmt stmt, ArgType arg);
    public ResultType visitReturnStmt(ReturnStmt stmt, ArgType arg);
    public ResultType visitIfStmt(IfStmt stmt, ArgType arg);
    public ResultType visitWhileStmt(WhileStmt stmt, ArgType arg);
    
  // Expressions
    public ResultType visitUnaryExpr(UnaryExpr expr, ArgType arg);
    public ResultType visitBinaryExpr(BinaryExpr expr, ArgType arg);
    public ResultType visitRefExpr(RefExpr expr, ArgType arg);
    public ResultType visitIxExpr(IxExpr expr, ArgType arg);
    public ResultType visitCallExpr(CallExpr expr, ArgType arg);
    public ResultType visitLiteralExpr(LiteralExpr expr, ArgType arg);
    public ResultType visitNewObjectExpr(NewObjectExpr expr, ArgType arg);
    public ResultType visitNewStringExpr(NewStringExpr expr, ArgType arg);
    public ResultType visitNewArrayExpr(NewArrayExpr expr, ArgType arg);
    public ResultType visitNullExpr(NullExpr expr, ArgType arg);
    
  // References
    public ResultType visitThisRef(ThisRef ref, ArgType arg);
    public ResultType visitIdRef(IdRef ref, ArgType arg);
    public ResultType visitQRef(QualRef ref, ArgType arg);

  // Terminals
    public ResultType visitIdentifier(Identifier id, ArgType arg);
    public ResultType visitOperator(Operator op, ArgType arg);
    public ResultType visitIntLiteral(IntLiteral num, ArgType arg);
    public ResultType visitBooleanLiteral(BooleanLiteral bool, ArgType arg);

	public ResultType visitNullLiteral(NullLiteral nullLiteral, ArgType o);

	public ResultType visitStringLiteral(StringLiteral stringLiteral, ArgType o);
}
