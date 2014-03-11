package cs275.game.mazeCraze;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import android.util.Log;

public class MazeGenerator {
	private int _sizeX, _sizeY;
	private Random _rand = new Random( System.currentTimeMillis() );

	public MazeGenerator() {
	}

	public Grid DFSGenerate(int sizeX, int sizeY) {
		_sizeX = sizeX;
		_sizeY = sizeY;

		ArrayList<Edge> path = new ArrayList<Edge>();
		TreeSet<Vec> visited = new TreeSet<Vec>();

		Edge current = new Edge( null, new Vec( 0, 0 ) );
		while ( visited.size() < ( _sizeX + 1 ) / 2 * ( _sizeY + 1 ) / 2 ) {
			ArrayList<Edge> possible = current.getTo().getPossible();
			while ( !possible.isEmpty() ) {
				Edge temp = possible.remove( _rand.nextInt( possible.size() ) );
				if ( !visited.contains( temp.getTo() ) ) {
					current = temp;
					visited.add( current.getTo() );
					path.add( current );
					Log.v( "gen", current.toString() );
					possible = current.getTo().getPossible();
				}
			}
			current = path.get( path.lastIndexOf( current.getFrom() ) );
		}

		return convert( path );
	}

	private Grid convert(ArrayList<Edge> path) {
		Grid grid = new Grid( _sizeX, _sizeY );

		for ( int y = 0; y < grid.getGridSizeY(); y++ )
			for ( int x = 0; x < grid.getGridSizeX(); x++ ) {
				if ( y % 2 == 0 && x % 2 == 0 )
					grid.toggleBlock( x, y );
				else if ( y % 2 == 1 && x % 2 == 1 )
					;
				//					maze.add(false);  TODO
				else if ( path.contains( new Edge( new Vec( x / 2, y / 2 ), new Vec( ( x + 1 ) / 2, ( y + 1 ) / 2 ) ) ) )
					grid.toggleBlock( x, y );
			}

		return grid;
	}

	//	TODO get rid of this if not needed
	//	public GridFactory(int sizex, int sizey) {
	//		_sizex = sizex;
	//		_sizey = sizey;
	//		for (int y = 0; y < _sizey; y++)
	//			for (int x = 0; x < _sizex; x++)
	//					maze.add(true);
	//	}
	//
	//	public Grid getMaze() {
	//		Grid grid = new Grid(_sizex,_sizey);
	//		for(int i = 0; i < maze.size(); i++) {
	//			if(!maze.get(i))
	//				grid.toggleBlock(i);
	//		}
	//		return grid;
	//	}

	/**
	 * TODO
	 * 
	 */
	private class Edge {
		@Override
		public boolean equals(Object o) {
			if ( o instanceof Edge ) {
				Edge other = (Edge) o;
				return ( other._from.equals( _from ) && other._to.equals( _to ) )
						|| ( other._from.equals( _to ) && other._to.equals( _from ) );
			} else if ( o instanceof Vec ) {
				Vec other = (Vec) o;
				return ( _to.equals( other ) );
			} else
				return false;
		}

		private Vec _from, _to;

		public Edge(Vec from, Vec to) {
			_from = from;
			_to = to;
		}

		public Vec getFrom() {
			return _from;
		}

		public Vec getTo() {
			return _to;
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
		public boolean equals(Object o) {
			if ( o instanceof Edge ) {
				Edge other = (Edge) o;
				return equals( other._to );
			} else if ( o instanceof Vec ) {
				Vec other = (Vec) o;
				return ( other._x == _x && other._y == _y );
			} else
				return false;
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
}
