package cs275.game.mazeCraze.Generator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import cs275.game.mazeCraze.Grid;
import cs275.game.mazeCraze.Graphics.Graphic;

public enum MazeGenerator {
	DFS("Depth First Search") {
		@Override
		public Grid generate(int sizeX, int sizeY, Graphic wallstyle, Graphic floorstyle) {
			ArrayList<Edge> path = new ArrayList<Edge>();
			ArrayDeque<Edge> stack = new ArrayDeque<Edge>();
			TreeSet<Vec> visited = new TreeSet<Vec>();
			// start with a dummy edge //
			stack.push( new Edge( null, new Vec( 0, 0 ) ) );
			while ( !stack.isEmpty() ) {
				// keep removing edges until we have one that goes to a vertex we haven't covered yet //
				Edge current = stack.removeLast();
				if ( !visited.contains( current.getTo() ) ) {
					// mark it as covered //
					visited.add( current.getTo() );
					// and add this edge to our maze //
					path.add( current );
					// get all the possible edges leading away from here //
					ArrayList<Edge> possible = current.getTo().getPossible( sizeX, sizeY );
					// randomize them and add them to the stack //
					Collections.shuffle( possible );
					stack.addAll( possible );
				}
			}
			// remove the dummy edge from the beginning //
			path.remove( 0 );
			return convert( path, sizeX, sizeY, wallstyle, floorstyle );
		}
	},

	PRIM("Prim's Algorithm") {
		@Override
		public Grid generate(int sizeX, int sizeY, Graphic wallstyle, Graphic floorstyle) {
			ArrayList<Edge> path = new ArrayList<Edge>();
			ArrayList<Edge> edges = new ArrayList<Edge>();
			ArrayList<ArrayList<MergeFind<Vec>>> verts = new ArrayList<ArrayList<MergeFind<Vec>>>();
			// Create a forest of all of the vertices and a list of all of the possible edges //
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
			// Randomize all of the edges //
			Collections.shuffle( edges );
			for ( Edge curr : edges ) {
				MergeFind<Vec> from = verts.get( curr.getFrom().getY() ).get( curr.getFrom().getX() );
				MergeFind<Vec> to = verts.get( curr.getTo().getY() ).get( curr.getTo().getX() );
				// for each edge, if it connects two different sets of vertices //
				if ( !from.find().equals( to.find() ) ) {
					// merge together the two sets of vertices //
					from.merge( to );
					// add the edge into our maze //
					path.add( curr );
				}
			}
			return convert( path, sizeX, sizeY, wallstyle, floorstyle );
		}
	},

	KRUSKAL("Kruskal's Algorithm") {
		@Override
		public Grid generate(int sizeX, int sizeY, Graphic wallstyle, Graphic floorstyle) {
			ArrayList<Edge> path = new ArrayList<Edge>();
			ArrayList<Edge> stack = new ArrayList<Edge>();
			TreeSet<Vec> visited = new TreeSet<Vec>();
			// start with a dummy edge //
			stack.add( new Edge( null, new Vec( 0, 0 ) ) );
			while ( !stack.isEmpty() ) {
				// keep removing edges until we have one that goes to a vertex we haven't covered yet //
				Edge current = stack.remove( 0 );
				if ( !visited.contains( current.getTo() ) ) {
					// mark it as covered //
					visited.add( current.getTo() );
					// and add this edge to our maze //
					path.add( current );
					// get all the possible edges leading away from here and add them to the stack //
					ArrayList<Edge> possible = current.getTo().getPossible( sizeY, sizeY );
					stack.addAll( possible );
					// randomize the entire stack //
					Collections.shuffle( stack );
				}
			}
			// remove the dummy edge //
			path.remove( 0 );
			return convert( path, sizeX, sizeY, wallstyle, floorstyle );
		}
	};

	private String _algorithm;

	MazeGenerator(String algorithmName) {
		_algorithm = algorithmName;
	}

	public String toString() {
		return _algorithm;
	}

	public abstract Grid generate(int sizeX, int sizeY, Graphic wallstyle, Graphic floorstyle);

	protected Grid convert(ArrayList<Edge> path, int sizeX, int sizeY, Graphic wallstyle, Graphic floorstyle) {
		// This converts an array of edges, which is the output of the generation algorithms
		// into a grid, which is format we need them in to be able to render 
		Grid grid = new Grid( sizeX, sizeY, wallstyle, floorstyle );
		for ( Edge curr : path )
			grid.toggleBlock( curr.getFrom().getX() + curr.getTo().getX(), curr.getFrom().getY() + curr.getTo().getY() );
		for ( int y = 0; y < grid.getGridSizeY(); y += 2 )
			for ( int x = 0; x < grid.getGridSizeX(); x += 2 ) {
				grid.toggleBlock( x, y );
			}

		return grid;
	}
}
