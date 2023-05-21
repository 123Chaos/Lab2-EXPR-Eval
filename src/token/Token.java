package token;

import java.util.HashMap;

/**
 * Token.
 */
public abstract class Token {

    protected int priorityId;
    protected String type;
    protected boolean terminal;
    protected String value;
    protected static HashMap <String, Integer> priorityIdMap;

    public String getValue() {
        return value;
    }
    public int getPriority() {
        return priorityId;
    }
    public String getType() {
        return type;
    }
    public boolean isTermial() {
        return terminal;
    }
    public void setNonTerminal() {
        terminal = false;
    }
    public abstract double getDecimal();
    public abstract boolean getBoolean();
    public abstract String getInformation();
    /**
     * build the priority hash map.
     */
    static {
        priorityIdMap = new HashMap <String, Integer>();
        priorityIdMap.put("(", 2);
        priorityIdMap.put(")", 3);
        priorityIdMap.put("max", 4);
        priorityIdMap.put("min", 4);
        priorityIdMap.put("sin", 4);
        priorityIdMap.put("cos", 4);
        priorityIdMap.put("--", 5);
        priorityIdMap.put("^", 6);
        priorityIdMap.put("*", 7);
        priorityIdMap.put("/", 7);
        priorityIdMap.put("+", 8);
        priorityIdMap.put("-", 8);
        priorityIdMap.put("<", 9);
        priorityIdMap.put("<=", 9);
        priorityIdMap.put(">",9);
        priorityIdMap.put(">=", 9);
        priorityIdMap.put("=", 9);
        priorityIdMap.put("<>", 9);
        priorityIdMap.put("!", 10);
        priorityIdMap.put("&", 11);
        priorityIdMap.put("|", 12);
        priorityIdMap.put("?", 13);
        priorityIdMap.put(":", 14);
        priorityIdMap.put(",", 15);
        priorityIdMap.put("$", 16);
    }
}
