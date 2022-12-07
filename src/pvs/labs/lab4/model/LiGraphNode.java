package pvs.labs.lab4.model;

import pvs.labs.model.Graph;
import pvs.labs.model.GraphNode;
import pvs.labs.model.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LiGraphNode extends GraphNode implements Runnable {

    private final int D;

    private static volatile boolean isFinish = false;
    private static volatile boolean isInitiated = false;

    private final Graph graph;

    private List<GraphNode> predecessors = new ArrayList<>();

    private List<GraphNode> successors = new ArrayList<>();

    public void setPredecessors(List<GraphNode> predecessors) {
        this.predecessors = predecessors;
    }

    public void setSuccessors(List<GraphNode> successors) {
        this.successors = successors;
    }

    private int sentCount = 0;

    private final ConcurrentHashMap<Integer, Integer> recCount = new ConcurrentHashMap<>();

    private ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Message>> lst = new ConcurrentHashMap<>();

    public void setLst(ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Message>> lst) {
        this.lst = lst;
    }

    public ConcurrentHashMap<Integer, Integer> getRecCount() {
        return recCount;
    }

    public LiGraphNode(Graph graph, int size, int D) {
        super(size);
        this.D = D;
        this.graph = graph;
    }

    @Override
    public void run() {
        if (((LiGraph) graph).getInitiator() == this.getValue() && !isInitiated) {
            runMain();
            isInitiated = true;
        }
        runWorker();
    }

    public void runMain() {
        sendMsgToSuccessors();
    }

    private void sendMsgToSuccessors() {
        successors.forEach(node -> {
            lst.get(node.getValue()).offer(new Message(this, node));

            System.out.println("#" + this.getValue() + "; send from " + this.getValue() + " to "
                    + node.getValue() + "; sentCount = " + sentCount);
        });
        sentCount++;
    }

    private void runWorker() {
        while (getRecCountMin() < D && !isFinish) {
            Message poll = lst.get(this.getValue()).poll();
            if (poll != null) {
                int value = poll.getFrom().getValue();
                recCount.put(value, recCount.get(value) + 1);
                System.out.println("#" + this.getValue() + "; accept from " + value
                        + "; recCount " + recCount.get(value)
                        + "; minRecCount " + getRecCountMin()
                        + "; sentCount = " + sentCount);

                if (getRecCountMin() >= sentCount && sentCount < D) {
                    sendMsgToSuccessors();
                }
            }
        }
        isFinish = true;
        System.out.println("#" + this.getValue() + " decide!");
    }

    private Integer getRecCountMin() {
        return recCount.values().stream().min(Comparator.comparingInt(Integer::intValue)).orElse(0);
    }

}
