package token;


public class Operator extends Token {

    public Operator(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        type = new String("operator");
        terminal = true;
        value = new String(_value);
    }

    public Operator(Token _copy) {
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
        return value;
    }
}
