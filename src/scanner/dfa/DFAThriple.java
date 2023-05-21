package scanner.dfa;

import token.*;
import java.util.*;

public class DFAThriple extends DFAPoint {
    DFAThriple() {
        finish = true;
        type = new String("thriple");
        edges = new HashMap <Character, Integer> ();
    }

    DFAThriple(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    public Thriple getToken(String name) {
        return new Thriple(name);
    }
}
