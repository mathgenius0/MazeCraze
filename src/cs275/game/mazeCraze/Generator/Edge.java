package cs275.game.mazeCraze.Generator;


/**
 * TODO
 * 
 */
class Edge {
	@Override
	public boolean equals(Object other) {
		Edge o = (Edge) other;
		return ( o.getFrom().equals( getFrom() ) && o.getTo().equals( getTo() ) || ( o.getFrom().equals( getTo() ) && o.getTo().equals( getFrom() ) ) );
	}

	private Vec _from, _to;

	public Edge(Vec from, Vec to) {
		setFrom( from );
		setTo( to );
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