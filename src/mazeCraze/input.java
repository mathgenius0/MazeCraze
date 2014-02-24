package mazeCraze;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Input implements OnTouchListener {
	
	private enum Type { ONTOUCH, ACCELEROMETER; }
	private Type _type;
	
	public Input(String type) {
		setType(type);
	}
	
	//TODO if( _type == Type.ACCELEROMETER)
	
	@Override
	public boolean onTouch(View v, MotionEvent mouseevent) {
		//TODO how should the navigation interface with the camera? Via the grid? Or what?
		//TODO how to interface with the menu? something about switching v shut up smart guy it's fucking late
		if( _type == Type.ONTOUCH ) {
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
		}
		return true;
	}
	
	public String getType() { return _type.name(); }
	public void setType(String type) { _type = Type.valueOf( type.toUpperCase() ); }
}
