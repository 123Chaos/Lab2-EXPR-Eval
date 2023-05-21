package parser.expr;

import token.*;
import exceptions.*;

import java.util.*;

import javax.swing.DebugGraphics;

public class ExprUnary {
    private Token value;

    public ExprUnary(Token _value) throws TypeMismatchedException {
        if (_value.getType().equals("decimal"))
            value = new Decimal(-_value.getDecimal(), false);
        else if (_value.getType().equals("boolean"))
            value = new MyBoolean(_value.getBoolean(), false);
        else
            throw new TypeMismatchedException();
    }

    public Token expr() {
        return value;
    }
}
