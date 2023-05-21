package token;

public class Thriple extends Token {

    public Thriple(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        type = new String("thriple");
        terminal = true;
        value = new String(_value);
    }

    public Thriple(Token _copy) {
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
