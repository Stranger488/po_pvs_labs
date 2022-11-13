package pvs.labs.lab1.model;

import pvs.labs.model.GraphNode;

public class EchoGraphNode extends GraphNode {
	private int father; // -2 - not set
						// -1 - root
	private int totalNeigh, receivedTokens;
	private boolean tokenSent;

	public EchoGraphNode(int val) {
		super(val);
		this.father = -2;
		this.totalNeigh = 0;
		this.receivedTokens = 0;
		this.tokenSent = false;
	}

	/**
	 * Check if node is eligible to perform echo.
	 * If yes, sent token to its father.
	 *
	 */
	public boolean echo() {
		if (this.tokenSent) return false;

		if (this.receivedTokens == this.totalNeigh - 1) {
			this.sentToken();
			return true;
		}
		return false;
	}

	public void receiveToken() {
		receivedTokens++;
		System.out.println("[GRAPH_STRUCTURE]: Node (" +this.value + ") received a token. "
				+ "New total of " + this.receivedTokens + "/" + this.totalNeigh);
	}

	public void sentToken(EchoGraphNode val) {
		System.out.println("[GRAPH_STRUCTURE]: "+ this.value + " has sent token to neighbor " + val.getValue());
		val.receiveToken();
	}

	public void sentToken() {
		System.out.println("[GRAPH_STRUCTURE]: "+this.value + " has sent token to his father " + this.father);
		this.tokenSent = true;
	}

	void incrementNeighbors() {
		totalNeigh++;
		System.out.println("[GRAPH_STRUCTURE]: Added neighbor on node(" + this.value
				+ "). New total of " + totalNeigh + " neighbors");
	}

	public void setFather(int val) {
		if (val == -1) {
			//System.out.println("[GRAPH_STRUCTURE]: Initiator's ("+ this.value
			//		+ ") father has been set to " + val);
		} else
			System.out.println("[GRAPH_STRUCTURE]: "+ this.value
					+ " father is " + val);
		this.father = val;
	}

	public int getFather() {
		return this.father;
	}

	public boolean isVisited() {
		return this.visited;
	}
	public boolean hasFather() {
		return this.father >= -1;
	}

	@Override
	public String toString() {
		return "Node with value(" +this.value+")"+" has a total of "+ this.totalNeigh
				+" neighbors and has received "+ this.receivedTokens+ " tokens" ;
	}

	public boolean canDecide() {
		return this.receivedTokens == this.totalNeigh;
	}
}
