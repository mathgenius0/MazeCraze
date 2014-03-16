package cs275.game.mazeCraze.Generator;

import java.util.ArrayList;
import java.util.Collections;

import cs275.game.mazeCraze.Block.Grid;
import cs275.game.mazeCraze.Graphics.Graphic;

public class KruskalGenerator extends MazeGenerator {

	@Override
	public Grid generate(int sizeX, int sizeY, Graphic wallstyle, Graphic floorstyle) {
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
			MergeFind<Vec> from = verts.get( curr.getFrom().getY() ).get( curr.getFrom().getX() );
			MergeFind<Vec> to = verts.get( curr.getTo().getY() ).get( curr.getTo().getX() );
			if ( !from.find().equals( to.find() ) ) {
				from.merge( to );
				path.add( curr );
			}
		}
		return convert( path, sizeX, sizeY, wallstyle, floorstyle );
	}

}
