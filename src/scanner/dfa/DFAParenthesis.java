package scanner.dfa;

import token.*;
import java.util.*;

public class DFAParenthesis extends DFAPoint {
    DFAParenthesis() {
        finish = true;
        type = new String("parenthesis");
        edges = new HashMap <Character, Integer> ();
    }
    DFAParenthesis(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }
    public Parenthesis getToken(String name) {
        return new Parenthesis(name);
    }
}
