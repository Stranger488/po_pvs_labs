package pvs.labs.lab2.model;

import pvs.labs.model.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class FinnGraphNode extends GraphNode {
    private List<Integer> nodesFrom = new ArrayList<>(); // от кого получаем в данный момент сигнал

    public FinnGraphNode(int val) {
        super(val);
    }

    public List<Integer> getNodesFrom() {
        return nodesFrom;
    }
}
