package parser.expr;

import token.*;
import exceptions.*;

public class ExprThriple {
    private MyBoolean condition;
    private Token one;
    private Token two;

    public ExprThriple(Token _condition, Token _one, Token _two) throws ExpressionException{
        if (!_condition.getType().equals("boolean"))
            throw new TypeMismatchedException();
        condition = new MyBoolean(_condition);
        
        if (_one.getType().equals("decimal"))
            one = new Decimal(_one);
        else if (_one.getType().equals("boolean"))
            one = new MyBoolean(_one);
        else
            throw new MissingOperandException();
        
        if (_two.getType().equals("decimal"))
            two = new Decimal(_two);
        else if (_two.getType().equals("boolean"))
            two = new MyBoolean(_two);
        else
            throw new MissingOperandException();
    }

    public Token expr() throws ExpressionException {
        boolean choose = condition.getBoolean();

        if (choose) {
            if (one.getType().equals("decimal"))
                return new Decimal(one, false);
            else
                return new MyBoolean(one, false);
        }

        if (two.getType().equals("decimal"))
            return new Decimal(two, false);
        else
            return new MyBoolean(two, false);
    }
}
