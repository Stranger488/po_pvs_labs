package pvs.labs.lab3.model;

import pvs.labs.model.GraphNode;

public class Message {

    private final GraphNode from;

    private final GraphNode to;

    public GraphNode getFrom() {
        return from;
    }

    public GraphNode getTo() {
        return to;
    }

    public Message(GraphNode from, GraphNode to) {
        this.from = from;
        this.to = to;
    }

}
