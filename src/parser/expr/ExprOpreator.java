package parser.expr;

import token.*;
import exceptions.*;

import java.util.*;

public class ExprOpreator {
    private Token opertor;
    private Decimal left;
    private Decimal right;
    public ExprOpreator(Token _opertor, Token _left, Token _right) throws TypeMismatchedException {
        if (!_left.getType().equals("decimal"))
            throw new TypeMismatchedException();
        if (!_right.getType().equals("decimal"))
            throw new TypeMismatchedException();
        opertor = new Operator(_opertor);
        left = new Decimal(_left);
        right = new Decimal(_right);
    }

    public Token expr() throws ExpressionException {
        double leftDecimal = left.getDecimal();
        double rightDecimal = right.getDecimal();
        switch (opertor.getValue()) {
            case "+":
                return new Decimal(leftDecimal + rightDecimal, false);
            case "-":
                return new Decimal(leftDecimal - rightDecimal, false);
            case "*":
                return new Decimal(leftDecimal * rightDecimal, false);
            case "/":
                if (rightDecimal == 0)
                    throw new DividedByZeroException();
                return new Decimal(leftDecimal / rightDecimal, false);
            case "^":
                return new Decimal(Math.pow(leftDecimal, rightDecimal), false);
        }
        throw new MissingOperatorException();
    }
}
