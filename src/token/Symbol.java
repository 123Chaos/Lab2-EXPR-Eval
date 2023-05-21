package token;


public class Symbol extends Token {

    public Symbol(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        switch (_value) {
            case "$":
                type = new String("dollar");
                break;
            case ",":
                type = new String("comma");
                break;
            default:
                type = new String("unknown");
        }
        terminal = true;
        value = new String(_value);
    }

    public Symbol(Token _copy) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _copy.terminal;
        value = _copy.value;
    }

    public double getDecimal() {return 0;}
    public boolean getBoolean() {return false;}
    public String getInformation() {return value;}
}
