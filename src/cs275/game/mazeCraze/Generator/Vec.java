package cs275.game.mazeCraze.Generator;

import java.util.ArrayList;

/**
 * TODO
 * 
 */
class Vec implements Comparable<Vec> {
	// Vec is a collection of two coordinates representing a vertex in our graph
	private int _x, _y;

	public Vec(int x, int y) {
		setX( x );
		setY( y );
	}

	@Override
	public boolean equals(Object other) {
		Vec o = (Vec) other;
		return ( o.getX() == getX() && o.getY() == getY() );
	}

	@Override
	public int compareTo(Vec o) {
		// this is creating an unique ordering of vertices so that we can use them in a TreeSet //
		if ( o.getX() > getX() )
			return 1;
		else if ( o.getX() < getX() )
			return -1;
		else {
			if ( o.getY() > getY() )
				return 1;
			else if ( o.getY() < getY() )
				return -1;
			else
				return 0;
		}
	}

	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}

	public ArrayList<Edge> getPossible(int sizeX, int sizeY) {
		// This finds all the edges that originate from this vertex which are still in the grid //
		ArrayList<Edge> possible = new ArrayList<Edge>();
		if ( getX() + 1 < ( sizeX + 1 ) / 2 )
			possible.add( new Edge( this, new Vec( getX() + 1, getY() ) ) );
		if ( getY() + 1 < ( sizeY + 1 ) / 2 )
			possible.add( new Edge( this, new Vec( getX(), getY() + 1 ) ) );
		if ( ( getX() - 1 ) >= 0 )
			possible.add( new Edge( this, new Vec( getX() - 1, getY() ) ) );
		if ( ( getY() - 1 ) >= 0 )
			possible.add( new Edge( this, new Vec( getX(), getY() - 1 ) ) );
		return possible;
	}

	public int getY() {
		return _y;
	}

	public void setY(int _y) {
		this._y = _y;
	}

	public int getX() {
		return _x;
	}

	public void setX(int _x) {
		this._x = _x;
	}
}