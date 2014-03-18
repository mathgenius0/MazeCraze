package cs275.game.mazeCraze.Generator;

/**
 * TODO
 * 
 */
class Edge {
	// An edge is a collection of a 2 vectors representing the path between two vertices in our graph //
	private Vec _from, _to;

	public Edge(Vec from, Vec to) {
		setFrom( from );
		setTo( to );
	}

	@Override
	public boolean equals(Object other) {
		Edge o = (Edge) other;
		// Edges are equal if they are the same in either direction //
		return ( o.getFrom().equals( getFrom() ) && o.getTo().equals( getTo() ) || ( o.getFrom().equals( getTo() ) && o
				.getTo().equals( getFrom() ) ) );
	}

	public String toString() {
		return getFrom() + "->" + getTo();
	}

	public Vec getTo() {
		return _to;
	}

	public void setTo(Vec _to) {
		this._to = _to;
	}

	public Vec getFrom() {
		return _from;
	}

	public void setFrom(Vec _from) {
		this._from = _from;
	}
}