package parser.expr;

import token.*;
import exceptions.*;

import java.util.*;

public class ExprFunction {
    private Token func;
    private ArrayList <Token> args;
    private int length;

    public ExprFunction() {
        args = new ArrayList <Token>();
        length = 0;
    }

    public ExprFunction(Token _func, ArrayList <Token> _args) {
        func = new Function(_func);
        args = new ArrayList <Token>(_args);
        length = args.size();
    }

    private void checkArgs() throws ExpressionException {
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                Token iValue = args.get(i);
                
                switch (iValue.getType()) {
                    case "boolean":
                        throw new TypeMismatchedException();
                    case "comma":
                        throw new MissingOperandException();
                    case "decimal":
                        break;
                    default:
                        throw new FunctionCallException();
                }

                double nowValue = iValue.getDecimal();
            } else {
                if (!args.get(i).getType().equals("comma"))
                    throw new FunctionCallException();
            }
        }
        if (length % 2 == 0)
            throw new MissingOperandException();
    }
    private Token exprSinCos() throws ExpressionException {
        if (length == 0)
            throw new MissingOperandException();
        if (length != 1)
            throw new FunctionCallException();
        
        Token value = args.get(0);

        switch (func.getValue()) {
            case "sin":
                return new Decimal(Math.sin(value.getDecimal()), false);
            case "cos":
                return new Decimal(Math.cos(value.getDecimal()), false);
        }
        throw new FunctionCallException();
    }

    private Token exprMaxMin() throws ExpressionException {
        
        if (length == 0)
            throw new MissingOperandException();

        Token firstValue = args.get(0);
        double maxValue = firstValue.getDecimal();
        double minValue = maxValue;
        for (int i = 1; i < length; i++) {
            if (i % 2 == 0) {
                Token iValue = args.get(i);

                double nowValue = iValue.getDecimal();
                maxValue = Math.max(nowValue, maxValue);
                minValue = Math.min(nowValue, minValue);
            } else {
                if (!args.get(i).getType().equals("comma"))
                    throw new FunctionCallException();
            }
        }

        if ((length + 1) / 2 <= 1)
            throw new MissingOperandException();

        switch (func.getValue()) {
            case "max":
                return new Decimal(maxValue, false);
            case "min":
                return new Decimal(minValue, false);
        }
        throw new FunctionCallException();
    }
    public Token expr() throws ExpressionException {

        checkArgs();

        if (func.getInformation().equals("unary"))
            return exprSinCos();
        else
            return exprMaxMin();
    }
}
