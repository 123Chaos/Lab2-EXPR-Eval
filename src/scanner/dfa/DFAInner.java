package scanner.dfa;

import token.*;
import java.util.*;

public class DFAInner extends DFAPoint {
    DFAInner() {
        finish = false;
        type = new String("inner");
        edges = new HashMap <Character, Integer> ();
    }
    DFAInner(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }
    public Token getToken(String name) {
        return new Decimal(name);
    }
}
