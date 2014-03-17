package cs275.game.mazeCraze;

import java.util.ArrayList;

import cs275.game.mazeCraze.Graphics.Graphic;

import android.graphics.Color;

public enum Block {
	FLOOR {
		@Override
		public String toString() { return "."; }

		@Override
		protected ArrayList<Float> genVertexCoords(int x, int y) {
			ArrayList<Float> coords = new ArrayList<Float>();
			// @formatter:off
			coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
			coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
			coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
			coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
			// @formatter:on
			return coords;
		}

		@Override
		protected ArrayList<Float> genTextureCoords() {
			ArrayList<Float> coords = new ArrayList<Float>();
			// @formatter:off
			coords.add( 0.0f ); coords.add( 1.0f );
			coords.add( 0.0f ); coords.add( 0.0f );
			coords.add( 1.0f ); coords.add( 1.0f );
			coords.add( 1.0f ); coords.add( 0.0f );
			// @formatter:on
			return coords;
		}

		@Override
		protected ArrayList<Integer> genDrawOrder(int i) {
			ArrayList<Integer> order = new ArrayList<Integer>();
			for (int a = 0; a < 4; a++)
				order.add(i + a);
			order.add(i + 3);
			order.add(i + 4);
			return order;
		}
		
		@Override
		public int getColor() { return Color.GREEN; }
	}, 
	
	WALL {
		@Override
		public String toString() { return "X"; }

		@Override
		protected ArrayList<Float> genVertexCoords(int x, int y) {
			ArrayList<Float> coords = new ArrayList<Float>();
			// @formatter:off
			coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
			coords.add( x + 0.0f ); coords.add( 0.5f ); coords.add( y + 0.0f );
			coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
			coords.add( x + 1.0f ); coords.add( 0.5f ); coords.add( y + 0.0f );
			coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
			coords.add( x + 1.0f ); coords.add( 0.5f ); coords.add( y + 1.0f );
			coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
			coords.add( x + 0.0f ); coords.add( 0.5f ); coords.add( y + 1.0f );
			coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
			coords.add( x + 0.0f ); coords.add( 0.5f ); coords.add( y + 0.0f );
			// @formatter:on
			return coords;
		}

		@Override
		protected ArrayList<Float> genTextureCoords() {
			ArrayList<Float> coords = new ArrayList<Float>();
			// @formatter:off
			coords.add( 0.0f ); coords.add( 1.0f );
			coords.add( 0.0f ); coords.add( 0.0f );
			coords.add( 1.0f ); coords.add( 1.0f );
			coords.add( 1.0f ); coords.add( 0.0f );
			coords.add( 2.0f ); coords.add( 1.0f );
			coords.add( 2.0f ); coords.add( 0.0f );
			coords.add( 3.0f ); coords.add( 1.0f );
			coords.add( 3.0f ); coords.add( 0.0f );
			coords.add( 4.0f ); coords.add( 1.0f );
			coords.add( 4.0f ); coords.add( 0.0f );
			// @formatter:on
			return coords;
		}

		@Override
		protected ArrayList<Integer> genDrawOrder(int i) {
			ArrayList<Integer> order = new ArrayList<Integer>();
			for (int a = 0; a < 10; a++)
				order.add(i + a);
			order.add(i + 9);
			order.add(i + 10);
			return order;
		}
		
		@Override
		public int getColor() { return Color.RED; }
	},
	
	ENTRANCE {//TODO is this math supposed to be different?
		@Override
		public String toString() { return "<"; }
		
		 @Override
		 protected ArrayList<Float> genVertexCoords(int x, int y) {
			 ArrayList<Float> coords = new ArrayList<Float>();
			 // @formatter:off
			 coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
			 coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
			 coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
			 coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
			 // @formatter:on
			 return coords;
		 }
		
		 @Override
		 protected ArrayList<Float> genTextureCoords() {
			 ArrayList<Float> coords = new ArrayList<Float>();
			 // @formatter:off
			 coords.add( 0.0f ); coords.add( 1.0f );
			 coords.add( 0.0f ); coords.add( 0.0f );
			 coords.add( 1.0f ); coords.add( 1.0f );
			 coords.add( 1.0f ); coords.add( 0.0f );
			 // @formatter:on
			 return coords;
		 }
		
		 @Override
		 protected ArrayList<Integer> genDrawOrder(int i) {
		 ArrayList<Integer> order = new ArrayList<Integer>();
		 	for (int a = 0; a < 4; a++)
		 		order.add(i + a);
		 	order.add(i + 3);
		 	order.add(i + 4);
		 	return order;
		 }
		
		@Override
		public int getColor() { return Color.BLUE; }
	},
		
	EXIT {//TODO is this math supposed to be different?
		 @Override
		 public String toString() { return ">"; }
		
		 @Override
		 protected ArrayList<Float> genVertexCoords(int x, int y) {
			 ArrayList<Float> coords = new ArrayList<Float>();
			// @formatter:off
			coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
			coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
			coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
			coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
			// @formatter:on
			return coords;
		 }
		
		 @Override
		 protected ArrayList<Float> genTextureCoords() {
			 ArrayList<Float> coords = new ArrayList<Float>();
			// @formatter:off
			coords.add( 0.0f ); coords.add( 1.0f );
			coords.add( 0.0f ); coords.add( 0.0f );
			coords.add( 1.0f ); coords.add( 1.0f );
			coords.add( 1.0f ); coords.add( 0.0f );
			// @formatter:on
			return coords;
		 }
		
		 @Override
		 protected ArrayList<Integer> genDrawOrder(int i) {
			 ArrayList<Integer> order = new ArrayList<Integer>();
			 for (int a = 0; a < 4; a++)
				 order.add(i + a);
			 order.add(i + 3);
			 order.add(i + 4);
			 return order;
		 }
		
		
		 @Override
		 public int getColor() { return Color.BLUE; }
	};
	
	public abstract String toString();

	public void generateBuffers(Graphic graphic, int x, int y) {
		int count = graphic.getVertexCount();
		graphic.appendArrays( genVertexCoords(x, y), genTextureCoords(), genDrawOrder(count) );
	}

	protected abstract ArrayList<Float> genVertexCoords(int x, int y);

	protected abstract ArrayList<Float> genTextureCoords();

	protected abstract ArrayList<Integer> genDrawOrder(int i);
	
	protected abstract int getColor();
}
