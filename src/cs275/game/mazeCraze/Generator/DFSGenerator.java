package cs275.game.mazeCraze.Generator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import cs275.game.mazeCraze.Block.Grid;
import cs275.game.mazeCraze.Graphics.Graphic;

public class DFSGenerator extends MazeGenerator {

	@Override
	public Grid generate(int sizeX, int sizeY, Graphic wallstyle, Graphic floorstyle) {
		ArrayList<Edge> path = new ArrayList<Edge>();
		ArrayDeque<Edge> stack = new ArrayDeque<Edge>();
		TreeSet<Vec> visited = new TreeSet<Vec>();
		stack.push( new Edge( null, new Vec( 0, 0 ) ) );
		while ( !stack.isEmpty() ) {
			Edge current = stack.removeLast();
			if ( !visited.contains( current.getTo() ) ) {
				visited.add( current.getTo() );
				path.add( current );
				//				Log.v("genz",current.toString());
				ArrayList<Edge> possible = current.getTo().getPossible(sizeX,sizeY);
				Collections.shuffle( possible );
				stack.addAll( possible );
			}
		}
		path.remove( 0 );
		return convert( path, sizeX, sizeY, wallstyle, floorstyle );
	}

}
