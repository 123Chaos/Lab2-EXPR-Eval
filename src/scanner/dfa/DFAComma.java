package scanner.dfa;

import token.*;
import java.util.*;

public class DFAComma extends DFAPoint {
    DFAComma() {
        finish = true;
        type = new String("comma");
        edges = new HashMap <Character, Integer> ();
    }
    DFAComma(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    public Symbol getToken(String name) {
        return new Symbol(name);
    }
}
