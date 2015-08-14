package edu.buffalo.cse.blue.maybe.ast;

import polyglot.ast.*;
import polyglot.lex.*;
import polyglot.util.*;

import java.util.*;

/**
 * NodeFactory for maybe extension.
 */
public class MaybeNodeFactory_c extends NodeFactory_c implements MaybeNodeFactory {
    public MaybeNodeFactory_c(MaybeLang lang, MaybeExtFactory extFactory) {
        super(lang, extFactory);
    }

    @Override
    public MaybeExtFactory extFactory() {
        return (MaybeExtFactory) super.extFactory();
    }

    // TODO:  Implement factory methods for new AST nodes.
    // TODO:  Override factory methods for overridden AST nodes.
    // TODO:  Override factory methods for AST nodes with new extension nodes.
    @Override
    public Maybe Maybe(Position pos, Expr cond, List<Block> alternatives) {
        Maybe n = new Maybe_c(pos, cond, alternatives);
        n = ext(n, extFactory().extIf());
        n = del(n, delFactory().delIf());
        return n;
    }

    @Override
    public MaybeAssign MaybeAssign(Position pos, Expr left, Assign.Operator op, Expr right) {
        if (left instanceof Local) {
            return MaybeLocalAssign(pos, (Local) left, op, right);
        }
        else if (left instanceof Field) {
            return MaybeFieldAssign(pos, (Field) left, op, right);
        }
        else if (left instanceof ArrayAccess) {
            return MaybeArrayAccessAssign(pos, (ArrayAccess) left, op, right);
        }
        return MaybeAmbAssign(pos, left, op, right);

        // MaybeAssign n = new MaybeAssign_c(pos, left, op, right);
        // n = ext(n, extFactory().extIf());
        // n = del(n, delFactory().delIf());
        // return n;
    }

    @Override
    public MaybeLocalAssign MaybeLocalAssign(Position pos, Local left,
            Assign.Operator op, Expr right) {
        MaybeLocalAssign n = new MaybeLocalAssign_c(pos, left, op, right);
        n = ext(n, extFactory().extLocalAssign());
        n = del(n, delFactory().delLocalAssign());
        return n;
    }

    @Override
    public MaybeFieldAssign MaybeFieldAssign(Position pos, Field left,
            Assign.Operator op, Expr right) {
        MaybeFieldAssign n = new MaybeFieldAssign_c(pos, left, op, right);
        n = ext(n, extFactory().extFieldAssign());
        n = del(n, delFactory().delFieldAssign());
        return n;
    }

    @Override
    public MaybeArrayAccessAssign MaybeArrayAccessAssign(Position pos, ArrayAccess left,
            Assign.Operator op, Expr right) {
        MaybeArrayAccessAssign n = new MaybeArrayAccessAssign_c(pos, left, op, right);
        n = ext(n, extFactory().extArrayAccessAssign());
        n = del(n, delFactory().delArrayAccessAssign());
        return n;
    }

    @Override
    public MaybeAmbAssign MaybeAmbAssign(Position pos, Expr left, Assign.Operator op,
            Expr right) {
        MaybeAmbAssign n = new MaybeAmbAssign_c(pos, left, op, right);
        n = ext(n, extFactory().extAmbAssign());
        n = del(n, delFactory().delAmbAssign());
        return n;
    }
}
