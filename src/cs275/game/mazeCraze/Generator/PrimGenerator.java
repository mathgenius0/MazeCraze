package cs275.game.mazeCraze.Generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import cs275.game.mazeCraze.Block.Grid;
import cs275.game.mazeCraze.Graphics.Graphic;

public class PrimGenerator extends MazeGenerator {

	@Override
	public Grid generate(int sizeX, int sizeY, Graphic wallstyle, Graphic floorstyle) {
		ArrayList<Edge> path = new ArrayList<Edge>();
		ArrayList<Edge> stack = new ArrayList<Edge>();
		TreeSet<Vec> visited = new TreeSet<Vec>();
		stack.add( new Edge( null, new Vec( 0, 0 ) ) );
		while ( !stack.isEmpty() ) {
			Edge current = stack.remove( 0 );
			if ( !visited.contains( current.getTo() ) ) {
				visited.add( current.getTo() );
				path.add( current );
				//				Log.v("genz",current.toString());
				ArrayList<Edge> possible = current.getTo().getPossible( sizeY, sizeY );
				stack.addAll( possible );
				Collections.shuffle( stack );
			}
		}
		path.remove( 0 );
		return convert( path, sizeX, sizeY, wallstyle, floorstyle );
	}

}
