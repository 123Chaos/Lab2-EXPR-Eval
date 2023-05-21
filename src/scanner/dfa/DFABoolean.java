package scanner.dfa;

import token.*;
import java.util.*;

public class DFABoolean extends DFAPoint {
    DFABoolean() {
        finish = true;
        type = new String("boolean");
        edges = new HashMap <Character, Integer> ();
    }

    DFABoolean(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    public MyBoolean getToken(String name) {
        return new MyBoolean(name);
    }
}
