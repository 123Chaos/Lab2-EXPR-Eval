package scanner;

import token.*;
import exceptions.*;
import scanner.dfa.*;
import java.util.*;

public class MyScanner {
    private String expression;
    private ArrayList <Token> tokens;
    private static DFA dfa = new DFA();

    public MyScanner() {
        expression = new String("");
        tokens = new ArrayList<Token>();
    }

    public MyScanner(String _expression) {
        expression = new String(_expression.toLowerCase());
        tokens = new ArrayList<Token>();
    }

    /**
     * 判断是否取反
     * @param 当前token.
     * @return true or false.
     */
    private boolean checkUnary(String now) {
        if (now.equals("!"))
            return true;
        if (now.equals("-")) {
            int tokenCount = tokens.size();
            if (tokenCount > 0) {
                Token last = tokens.get(tokenCount - 1);
                if (last.getType().equals("decimal") 
                    || last.getValue().equals(")")
                    || last.getType().equals("boolean"))
                    return false;
                else
                    return true;
            } else
                return true;
        }
        return false;
    }

    /**
     * 扫描当前表达式并继续DFA流程
     * @return token list.
     * @throws ExpressionException 如果格式有误：IllegalIdentifier 如果输入为空：EmptyExpression.
     */
    public ArrayList <Token> scan() throws ExpressionException {
        int expressionLength = expression.length();
        int index = 0;

        String nowToken = new String();
        dfa.reset();
        boolean startWithLetter = false;
        boolean startWithDigit = false;
        while (index < expressionLength) {
            char now = expression.charAt(index);
            char lookahead = (index + 1 < expressionLength) ? expression.charAt(index + 1) : '$';
            if (now == ' ') {
                index++;
                continue;
            }

            if (dfa.isStart()) {
                if (Character.isLetter(now))
                    startWithLetter = true;
                else if (Character.isDigit(now) || now == '.')
                    startWithDigit = true;
            }
            
            nowToken += now;
            String tokenType = dfa.nextState(now, lookahead);
            if (tokenType.equals("notYet")) {
                index++;
                continue;
            }
            else if (tokenType.equals("error")) {

                if (startWithLetter)
                    throw new IllegalIdentifierException();
                else if (startWithDigit)
                    throw new IllegalDecimalException();
                else
                    throw new IllegalSymbolException();

            } else {

                tokens.add(dfa.getToken(nowToken, checkUnary(nowToken)));
                dfa.reset();
                nowToken = "";
                startWithLetter = false;
                startWithDigit = false;

            }
            index++;
        }

        if (tokens.isEmpty())
            throw new EmptyExpressionException();
        return tokens;
    }
}
