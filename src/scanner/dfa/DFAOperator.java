package scanner.dfa;

import token.*;
import java.util.*;


public class DFAOperator extends DFAPoint {
    DFAOperator() {
        finish = true;
        type = new String("osperator");
        edges = new HashMap <Character, Integer> ();
    }
    DFAOperator(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }
    public Operator getToken(String name) {
        return new Operator(name);
    }
}
