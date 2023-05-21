package token;


public class Function extends Token {

    public Function(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        type = new String("function");
        terminal = true;
        value = new String(_value);
    }


    public Function(Token _copy) {
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
            case "sin":
                return "unary";
            case "cos":
                return "unary";
            default:
                return "variabl";
        }
    }
}
