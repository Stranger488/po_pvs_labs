package pvs.labs.model;

public abstract class GraphNode {
	protected int value;
	protected boolean visited;

	public boolean isVisited() {
		return visited;
	}

	public void setVisited() {
		this.visited = true;
	}

	public GraphNode(int val) {
		this.value = val;
		this.visited = false;
	}

	public int getValue() {
		return this.value;
	}

}
