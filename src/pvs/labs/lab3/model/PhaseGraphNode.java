package pvs.labs.lab3.model;

import pvs.labs.model.GraphNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PhaseGraphNode extends GraphNode implements Runnable {

    private final int D;

    static class Holder {
        static volatile boolean isFinish = false;
    }

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

    public PhaseGraphNode(int size, int D) {
        super(size);
        this.D = D;
    }

    @Override
    public void run() {
        runWorker();
    }

    public void runMain() {
        sendMsgToSuccessors();
    }

    private void sendMsgToSuccessors() {
        successors.forEach(node -> {
            lst.get(node.getValue()).offer(new Message(this, node));
            sentCount++;

            System.out.println("#" + this.getValue() + "; send from " + this.getValue() + " to "
                    + node.getValue() + "; sentCount = " + sentCount);
        });
    }

    private void runWorker() {
        while (recCount.values().stream().min(Comparator.comparingInt(Integer::intValue)).orElse(0) < D && !Holder.isFinish) {
            Message poll = lst.get(this.getValue()).poll();
            if (poll != null) {
                int value = poll.getFrom().getValue();
                recCount.put(value, recCount.get(value) + 1);
                System.out.println("#" + this.getValue() + "; accept from " + value + "; recCount "
                        + recCount.get(value) + "; sentCount = " + sentCount);

                if (recCount.values().stream().min(Comparator.comparingInt(Integer::intValue)).orElse(0) >= sentCount && sentCount < D) {
                    sendMsgToSuccessors();
                }
            }
        }
        Holder.isFinish = true;
    }

}
