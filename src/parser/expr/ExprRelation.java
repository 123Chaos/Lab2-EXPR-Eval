package parser.expr;

import token.*;
import exceptions.*;

import java.util.*;

public class ExprRelation {
    private Token opertor;
    private Token left;
    private Token right;

    public ExprRelation(Token _opertor, Token _left, Token _right) throws TypeMismatchedException {
        if (_left.getType().equals("decimal") &&
            _right.getType().equals("decimal") &&
            _opertor.getInformation().equals("decimal")) {
            left = new Decimal(_left);
            right = new Decimal(_right);
        } else if (_left.getType().equals("boolean") &&
                   _right.getType().equals("boolean") &&
                   _opertor.getInformation().equals("boolean")) {
            left = new MyBoolean(_left);
            right = new MyBoolean(_right);
        } else
            throw new TypeMismatchedException();

        opertor = new Relation(_opertor);
    }
    private Token exprBoolean() throws ExpressionException {
        switch (opertor.getValue()) {
            case "&":
                return new MyBoolean(left.getBoolean() && right.getBoolean(), false);
            case "|":
                return new MyBoolean(left.getBoolean() || right.getBoolean(), false);
        }
        throw new MissingOperatorException();
    }
    private Token exprDecimal() throws ExpressionException {
        double leftDecimal = left.getDecimal();
        double rightDecimal = right.getDecimal();
        switch (opertor.getValue()) {
            case "<":
                return new MyBoolean(leftDecimal < rightDecimal, false);
            case "<=":
                return new MyBoolean(leftDecimal <= rightDecimal, false);
            case ">":
                return new MyBoolean(leftDecimal > rightDecimal, false);
            case ">=":
                return new MyBoolean(leftDecimal >= rightDecimal, false);
            case "=":
                return new MyBoolean(leftDecimal == rightDecimal, false);
            case "<>":
                return new MyBoolean(leftDecimal != rightDecimal, false);
        }
        throw new MissingOperatorException();
    }
    public Token expr() throws ExpressionException {
        if (opertor.getInformation().equals("boolean"))
            return exprBoolean();
        return exprDecimal();
    }
}
