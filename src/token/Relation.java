package token;


public class Relation extends Token {
    public Relation(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        type = new String("relation");
        terminal = true;
        value = new String(_value);
    }

    public Relation(Token _copy) {
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
            case "<":
            case "<=":
            case ">":
            case ">=":
            case "=":
            case "<>":
                return "decimal";
            default:
                return "boolean";
        }
    }
}
