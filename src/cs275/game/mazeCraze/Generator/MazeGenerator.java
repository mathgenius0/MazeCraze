package cs275.game.mazeCraze.Generator;

import java.util.ArrayList;

import cs275.game.mazeCraze.Block.Grid;
import cs275.game.mazeCraze.Graphics.Graphic;

public abstract class MazeGenerator {
	public enum Algorithms {
		DFS(new DFSGenerator(), "Depth First Search"), PRIM(new PrimGenerator(), "Prim's Algorithm"), KRUSKAL(
				new KruskalGenerator(), "Kruskal's Algorithm");
		MazeGenerator _generator;
		String _name;

		Algorithms(MazeGenerator generator, String name) {
			_generator = generator;
			_name = name;
		}

		@Override
		public String toString() {
			return _name;
		}

		public Grid generate(int x, int y, Graphic wallstyle, Graphic floorstyle) {
			return _generator.generate(x, y, wallstyle, floorstyle);
		}
	};

	public abstract Grid generate(int sizeX, int sizeY, Graphic wallstyle, Graphic floorstyle);

	protected Grid convert(ArrayList<Edge> path, int sizeX, int sizeY, Graphic wallstyle, Graphic floorstyle) {
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
