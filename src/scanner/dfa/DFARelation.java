package scanner.dfa;

import token.*;
import java.util.*;

public class DFARelation extends DFAPoint {
    DFARelation() {
        finish = true;
        type = new String("relation");
        edges = new HashMap <Character, Integer> ();
    }
    DFARelation(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    public Relation getToken(String name) {
        return new Relation(name);
    }
}
