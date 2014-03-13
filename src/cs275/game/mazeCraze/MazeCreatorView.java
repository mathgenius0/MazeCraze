package cs275.game.mazeCraze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.cloudmine.api.rest.callbacks.ObjectModificationResponseCallback;
import com.cloudmine.api.rest.response.ObjectModificationResponse;

public class MazeCreatorView extends View {

	private Paint paint = new Paint();
	Bitmap b;
	Canvas c;
	Grid g;
	private int gridx;
	private int gridy;
	private float size;
	private float topx;
	private float topy;
	int prevx = -1;
	int prevy = -1;
	private Context _context;

	public MazeCreatorView(Context context) {
		super( context );
		_context = context;
		g = new MazeGenerator().KruskalGenerate( 11, 11 );
		g.save( new ObjectModificationResponseCallback() {
			public void onCompletion(ObjectModificationResponse response) {
				if ( response.wasSuccess() )
					Toast.makeText( _context, "Grid Saved", Toast.LENGTH_SHORT ).show();
			}

			public void onFailure(Throwable e, String msg) {
				Log.v( "cloudmine", "Failed to save grid", e );
			}
		} );
		gridx = g.getGridSizeX();
		gridy = g.getGridSizeY();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int mousex = (int) event.getX();
		int mousey = (int) event.getY();
		int selectx = Math.round( ( mousex - topx ) / size );
		int selecty = Math.round( ( mousey - topy ) / size );
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
		float x = canvas.getWidth();
		float y = canvas.getHeight();
		size = Math.min( x / gridx, y / gridy );
		if ( b == null ) {
			b = Bitmap.createBitmap( Math.round( size * gridx ), Math.round( size * gridy ), Bitmap.Config.ARGB_8888 );
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
