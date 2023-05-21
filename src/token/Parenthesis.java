package token;


public class Parenthesis extends Token {

    public Parenthesis(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        type = new String("parenthesis");
        terminal = true;
        value = new String(_value);
    }

    public Parenthesis(Token _copy) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _copy.terminal;
        value = _copy.value;
    }

    public double getDecimal() {
        return 0;
    }
    public boolean getBoolean() {
        return false;
    }

    public String getInformation() {
        switch (value) {
            case "(":
                return "left";
            default:
                return "right";
        }
    }
}
