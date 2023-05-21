package scanner.dfa;

import token.*;
import java.util.*;

public class DFAUnary extends DFAPoint {
    DFAUnary() {
        finish = true;
        type = new String("unary");
        edges = new HashMap <Character, Integer> ();
    }

    DFAUnary(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    public Unary getToken(String name) {
        return new Unary(name);
    }
}
