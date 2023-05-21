package scanner.dfa;

import java.util.*;

import token.*;

public abstract class DFAPoint {
    protected boolean finish;
    protected String type;
    protected HashMap <Character, Integer> edges;

    DFAPoint() {
        finish = false;
        type = new String();
        edges = new HashMap <Character, Integer> ();
    }

    DFAPoint(String _type) {
        finish = true;
        type = new String(_type);
        edges = new HashMap <Character, Integer> ();
    }

    DFAPoint(DFAPoint _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    public abstract Token getToken(String name);

    public boolean isFinish() {
        return finish;
    }

    public String getType() {
        return type;
    }

    public HashMap <Character, Integer> getEdges() {
        return edges;
    }

    public void addEdge(char weight, int to) {
        edges.put(weight, to);
    }
}
