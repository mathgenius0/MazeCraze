package mazeCraze;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class input implements OnTouchListener {
	@Override
	public boolean onTouch(View v, MotionEvent mouseevent) {
		GLcanvas gl = (GLcanvas) v;
		if (mouseevent.getAction() == MotionEvent.ACTION_DOWN) {
			float x = mouseevent.getX();
			if (x < gl.getWidth() / 3.0) {
				gl.left();
			} else if (x < 2.0 * gl.getWidth() / 3.0) {
				gl.forward();
			} else {
				gl.right();
			}
		}
		return true;
	}
}
