package cs275.game.mazeCraze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MazeCreatorView extends View {

	private Paint paint = new Paint();
	Bitmap b;
	Canvas c;
	Grid g;
	private int gridx;
	private int gridy;
	private int size;
	private int topx;
	private int topy;
	int prevx = -1;
	int prevy = -1;

	public MazeCreatorView(Context context) {
		super( context );
		g = new MazeGenerator().KruskalGenerate( 101, 101 );
		gridx = g.getGridSizeX();
		gridy = g.getGridSizeY();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int mousex = (int) event.getX();
		int mousey = (int) event.getY();
		int selectx = ( mousex - topx ) / size;
		int selecty = ( mousey - topy ) / size;
		if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
			try {
				g.toggleBlock( selectx, selecty );
			} catch ( Exception e ) {
			}
			updateBitmap();
			invalidate();
		}
		if ( event.getAction() == MotionEvent.ACTION_MOVE ) {
			if ( prevx != selectx || prevy != selecty ) {
				try {
					g.toggleBlock( selectx, selecty );
				} catch ( Exception e ) {
				}
				updateBitmap();
				invalidate();
			}
		}
		prevx = selectx;
		prevy = selecty;
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw( canvas );
		int x = canvas.getWidth();
		int y = canvas.getHeight();
		size = Math.min( x / gridx, y / gridy );
		if ( b == null ) {
			b = Bitmap.createBitmap( size * gridx, size * gridy, Bitmap.Config.ARGB_8888 );
			c = new Canvas( b );
			updateBitmap();
		}
		topx = ( Math.max( size * gridx, x ) - Math.min( size * gridx, x ) ) / 2;
		topy = ( Math.max( size * gridy, y ) - Math.min( size * gridy, y ) ) / 2;
		canvas.drawBitmap( b, topx, topy, paint );
	}

	public void updateBitmap() {
		for ( int y = 0; y < gridy; y++ ) {
			for ( int x = 0; x < gridx; x++ ) {
				if ( g.isTraversible( x, y ) )
					paint.setColor( Color.GREEN );
				else
					paint.setColor( Color.RED );
				c.drawRect( x * size, y * size, ( x + 1 ) * size, ( y + 1 ) * size, paint );
			}
		}
	}

}
