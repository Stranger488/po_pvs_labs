package pvs.labs.model;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GraphNode graphNode = (GraphNode) o;
		return value == graphNode.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
