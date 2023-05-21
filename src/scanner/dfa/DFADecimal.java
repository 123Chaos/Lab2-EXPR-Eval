package scanner.dfa;

import token.*;
import java.util.*;

public class DFADecimal extends DFAPoint {
    DFADecimal() {
        finish = true;
        type = new String("decimal");
        edges = new HashMap <Character, Integer> ();
    }

    DFADecimal(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    public Decimal getToken(String name) {
        return new Decimal(name);
    }
}
