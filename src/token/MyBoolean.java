package token;

public class MyBoolean extends Token {

    private boolean booleanValue;

    public MyBoolean(String _value) {
        priorityId = 0;
        type = new String("boolean");
        terminal = true;
        value = new String(_value);

        if (_value.equals("true"))
            booleanValue = true;
        else
            booleanValue = false;
    }

    public MyBoolean(boolean _value, boolean _terminal) {
        priorityId = 0;
        type = new String("boolean");
        terminal = _terminal;
        value = new String(_value ? "true" : "false");

        booleanValue = _value;
    }

    public MyBoolean(Token _copy) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _copy.terminal;
        value = new String(_copy.value);

        if (value.equals("true"))
            booleanValue = true;
        else
            booleanValue = false;
    }

    public MyBoolean(Token _copy, boolean _terminal) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _terminal;
        value = new String(_copy.value);

        if (value.equals("true"))
            booleanValue = true;
        else
            booleanValue = false;
    }

    public String getInformation() {
        return value;
    }
    public double getDecimal() {
        return 0;
    }

    public boolean getBoolean() {return booleanValue;}
}
