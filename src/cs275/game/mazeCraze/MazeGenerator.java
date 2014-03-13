package cs275.game.mazeCraze;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class MazeGenerator {
	private int _sizeX, _sizeY;

	//	private Random _rand = new Random( System.currentTimeMillis() );

	public Grid DFSGenerate(int sizeX, int sizeY) {
		_sizeX = sizeX;
		_sizeY = sizeY;

		ArrayList<Edge> path = new ArrayList<Edge>();
		ArrayDeque<Edge> stack = new ArrayDeque<Edge>();
		TreeSet<Vec> visited = new TreeSet<Vec>();
		stack.push( new Edge( null, new Vec( 0, 0 ) ) );
		while ( !stack.isEmpty() ) {
			Edge current = stack.removeLast();
			if ( !visited.contains( current._to ) ) {
				visited.add( current._to );
				path.add( current );
				//				Log.v("genz",current.toString());
				ArrayList<Edge> possible = current._to.getPossible();
				Collections.shuffle( possible );
				stack.addAll( possible );
			}
		}
		path.remove( 0 );
		return convert( path );
	}

	public Grid PrimGenerate(int sizeX, int sizeY) {
		_sizeX = sizeX;
		_sizeY = sizeY;

		ArrayList<Edge> path = new ArrayList<Edge>();
		ArrayList<Edge> stack = new ArrayList<Edge>();
		TreeSet<Vec> visited = new TreeSet<Vec>();
		stack.add( new Edge( null, new Vec( 0, 0 ) ) );
		while ( !stack.isEmpty() ) {
			Edge current = stack.remove( 0 );
			if ( !visited.contains( current._to ) ) {
				visited.add( current._to );
				path.add( current );
				//				Log.v("genz",current.toString());
				ArrayList<Edge> possible = current._to.getPossible();
				stack.addAll( possible );
				Collections.shuffle( stack );
			}
		}
		path.remove( 0 );
		return convert( path );
	}

	public Grid KruskalGenerate(int sizeX, int sizeY) {
		_sizeX = sizeX;
		_sizeY = sizeY;
		ArrayList<Edge> path = new ArrayList<Edge>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		ArrayList<ArrayList<MergeFind<Vec>>> verts = new ArrayList<ArrayList<MergeFind<Vec>>>();
		for ( int y = 0; y < ( sizeY + 1 ) / 2; y++ ) {
			ArrayList<MergeFind<Vec>> row = new ArrayList<MergeFind<Vec>>();
			verts.add( row );
			for ( int x = 0; x < ( sizeX + 1 ) / 2; x++ ) {
				row.add( new MergeFind<Vec>( new Vec( x, y ) ) );
				if ( x + 1 < ( sizeX + 1 ) / 2 )
					edges.add( new Edge( new Vec( x, y ), new Vec( x + 1, y ) ) );
				if ( y + 1 < ( sizeY + 1 ) / 2 )
					edges.add( new Edge( new Vec( x, y ), new Vec( x, y + 1 ) ) );
			}
		}
		Collections.shuffle( edges );
		for ( Edge curr : edges ) {
			MergeFind<Vec> from = verts.get( curr._from._y ).get( curr._from._x );
			MergeFind<Vec> to = verts.get( curr._to._y ).get( curr._to._x );
			if ( !from.find().equals( to.find() ) ) {
				from.merge( to );
				path.add( curr );
			}
		}
		return convert( path );
	}

	private Grid convert(ArrayList<Edge> path) {
		Grid grid = new Grid( _sizeX, _sizeY );
		for ( Edge curr : path )
			grid.toggleBlock( curr._from._x + curr._to._x, curr._from._y + curr._to._y );
		for ( int y = 0; y < grid.getGridSizeY(); y += 2 )
			for ( int x = 0; x < grid.getGridSizeX(); x += 2 ) {
				grid.toggleBlock( x, y );
			}
		return grid;
	}

	/**
	 * TODO
	 * 
	 */
	private class Edge {
		@Override
		public boolean equals(Object other) {
			Edge o = (Edge) other;
			return ( o._from.equals( _from ) && o._to.equals( _to ) || ( o._from.equals( _to ) && o._to.equals( _from ) ) );
		}

		private Vec _from, _to;

		public Edge(Vec from, Vec to) {
			_from = from;
			_to = to;
		}

		public String toString() {
			return _from + "->" + _to;
		}
	}

	/**
	 * TODO
	 * 
	 */
	private class Vec implements Comparable<Vec> {
		@Override
		public boolean equals(Object other) {
			Vec o = (Vec) other;
			return ( o._x == _x && o._y == _y );
		}

		public ArrayList<Edge> getPossible() {
			ArrayList<Edge> possible = new ArrayList<Edge>();
			if ( _x + 1 < ( _sizeX + 1 ) / 2 )
				possible.add( new Edge( this, new Vec( _x + 1, _y ) ) );
			if ( _y + 1 < ( _sizeY + 1 ) / 2 )
				possible.add( new Edge( this, new Vec( _x, _y + 1 ) ) );
			if ( ( _x - 1 ) >= 0 )
				possible.add( new Edge( this, new Vec( _x - 1, _y ) ) );
			if ( ( _y - 1 ) >= 0 )
				possible.add( new Edge( this, new Vec( _x, _y - 1 ) ) );
			return possible;
		}

		private int _x, _y;

		public Vec(int x, int y) {
			_x = x;
			_y = y;
		}

		public String toString() {
			return "(" + _x + "," + _y + ")";
		}

		@Override
		public int compareTo(Vec o) {
			if ( o._x > _x )
				return 1;
			else if ( o._x < _x )
				return -1;
			else {
				if ( o._y > _y )
					return 1;
				else if ( o._y < _y )
					return -1;
				else
					return 0;
			}
		}
	}

	private class MergeFind<E> {
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object o) {
			MergeFind<E> other = (MergeFind<E>) o;
			return data.equals( other.data );
		}

		private E data;
		private MergeFind<E> parent;
		private int rank;

		public MergeFind(E x) {
			data = x;
			parent = this;
			rank = 0;
		}

		public void merge(MergeFind<E> other) {
			MergeFind<E> thisroot = find();
			MergeFind<E> otherroot = other.find();
			if ( thisroot.equals( otherroot ) )
				return;
			if ( thisroot.rank < otherroot.rank )
				thisroot.parent = otherroot;
			else if ( thisroot.rank > otherroot.rank )
				otherroot.parent = thisroot;
			else {
				otherroot.parent = thisroot;
				thisroot.rank++;
			}
		}

		public MergeFind<E> find() {
			if ( parent != this )
				parent = parent.find();
			return parent;
		}
	}
}
