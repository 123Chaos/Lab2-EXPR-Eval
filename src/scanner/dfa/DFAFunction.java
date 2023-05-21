package scanner.dfa;

import token.*;
import java.util.*;

public class DFAFunction extends DFAPoint {
    DFAFunction() {
        finish = true;
        type = new String("function");
        edges = new HashMap <Character, Integer> ();
    }
    DFAFunction(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }
    public Function getToken(String name) {
        return new Function(name);
    }
}
