package parser;

import token.*;
import scanner.*;
import exceptions.*;
import java.util.*;

/**
 * Parser
 * 语法解析部分，通过输入的expr buffer进行parse
 */
public class Parser {
    private ArrayList <Token> buffer;
    private ArrayList <Token> stack;
    private static int [][]table;

    Parser(String expression) throws ExpressionException {
        buffer = new ArrayList <Token>(new MyScanner(expression).scan());
        stack = new ArrayList <Token>();
        buffer.add(new Symbol("$"));
    }

    /**
     * 取token栈顶元素
     * @return token栈顶元素
     */
    private Token getStackTop() {
        int stackLength = stack.size();
        int i = stackLength - 1;
        for (; i >= 0; i--) {
            if (stack.get(i).isTermial())
                break;
            }
        return stack.get(i);
    }

    private Token addInStack(Token lookahead) throws IllegalSymbolException {
        switch (lookahead.getType()) {
            case "decimal":
                return new Decimal(lookahead);
            case "boolean":
                return new MyBoolean(lookahead);
            case "function":
                return new Function(lookahead);
            case "operator":
                return new Operator(lookahead);
            case "parenthesis":
                return new Parenthesis(lookahead);
            case "relation":
                return new Relation(lookahead);
            case "thriple":
                return new Thriple(lookahead);
            case "unary":
                return new Unary(lookahead);
            case "comma":
            case "dollar":
                return new Symbol(lookahead);
            default:
                throw new IllegalSymbolException();
        }
    }

    /**
     * 返回expr的结果.
     * @return token.
     * @throws TypeMismatchedException 结果必须为decimal
     */
    private double getAnswer() throws TypeMismatchedException {
        if (stack.size() == 2) {
            Token top = stack.get(stack.size() - 1);
            if (top.getType().equals("decimal"))
                return top.getDecimal();
            else
                throw new TypeMismatchedException();
        }
        return 0;
    }

    /**
     * Reduce操作
     * @throws ExpressionException
     */
    private void reduce() throws ExpressionException {
        Token stackTop = getStackTop();
        Token lookahead = buffer.get(0);
        int action = table[stackTop.getPriority()][lookahead.getPriority()];
        while (action == 1) {
            stack = new Reducer(stack).calculate(stackTop.getType());
            stackTop = getStackTop();
            action = table[stackTop.getPriority()][lookahead.getPriority()];
        }
    }

    /**
     * Shift操作
     * @param lookahead->将要shift的token.
     * @throws IllegalSymbolException
     */
    private void shift(Token lookahead) throws IllegalSymbolException {
        stack.add(addInStack(lookahead));
        buffer.remove(0);
    }

    /**
     * OPP核心控制部分
     * @return 栈顶元素或抛出异常
     * @throws ExpressionException
     */
    public double opp() throws ExpressionException {
        stack.add(new Symbol("$"));
        while (true) {
            Token stackTop = getStackTop();
            Token lookahead = buffer.get(0);
            int action = table[stackTop.getPriority()][lookahead.getPriority()];

            switch (action) {
                case 0:
                    shift(lookahead);
                    break;
                case 1:
                    reduce();
                    break;
                case 2:
                    double answer = getAnswer();
                    return answer;
                case -1:
                    throw new MissingOperatorException();
                case -2:
                    throw new MissingOperandException();
                case -3:
                    throw new MissingLeftParenthesisException();
                case -4:
                    throw new MissingRightParenthesisException();
                case -5:
                    throw new TrinaryOperationException();
                case -6:
                    throw new FunctionCallException();
            }
        }
    }

    /**
     * OPP表
     */
    static {
        table = new int[][]{
            /*  b   d   (   )   f   -   ^   *   +   r   !   &   |   ?   :   ,   &  */
            /* b */ { -1, -1, -1, 1 , -1, 1 , 1 , 1 , 1 , 1 , -1, 1 , 1 , 1 , 1 , 1 , 1 },
            /* d */ { -1, -1, -1, 1 , -1, 1 , 1 , 1 , 1 , 1 , -1, 1 , 1 , 1 , 1 , 1 , 1 },
            /* ( */ { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , -5, 0 , -4},
            /* ) */ { -1, -1, -1, 1 , -1, 1 , 1 , 1 , 1 , 1 , -1, 1 , 1 , 1 , 1 , 1 , 1 },
            /* f */ { -6, -6, 0 , -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3},
            /* - */ { 0 , 0 , 0 , 1 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* ^ */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* * */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* + */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* r */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* ! */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* & */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 },
            /* | */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 },
            /* ? */ { 0 , 0 , 0 , -2, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , -5},
            /* : */ { 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 },
            /* , */ { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 },
            /* $ */ { 0 , 0 , 0 , -3, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , -5, 0 , 2 }
        };
    }
}
