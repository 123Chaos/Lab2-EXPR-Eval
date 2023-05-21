package token;


public class Unary extends Token {

    public Unary(String _value) {
        type = new String("unary");
        terminal = true;
        if (_value.equals("-"))
            value = new String("--");
        else
            value = new String("!");
        priorityId = Token.priorityIdMap.get(value);
    }

    public Unary(Token _copy) {
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
            case "!":
                return "boolean";
            default:
                return "decimal";
        }
    }
}
