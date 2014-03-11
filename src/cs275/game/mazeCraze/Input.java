package cs275.game.mazeCraze;

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
	public boolean onTouch(View v, MotionEvent mouseEvent) {
		//TODO how should the navigation interface with the camera? Via the grid? Or what?
		//TODO how to interface with the menu? something about switching v shut up smart guy it's fucking late
		if( _type == Type.ONTOUCH ) {
			GLcanvas navigator = (GLcanvas) v;
			if (mouseEvent.getAction() == MotionEvent.ACTION_DOWN) {
				float x = mouseEvent.getX();
				if (x < navigator.getWidth() / 3.0) {
					navigator.left();
				} else if (x < 2.0 * navigator.getWidth() / 3.0) {
					navigator.forward();
				} else {
					navigator.right();
				}
			}	
		}
		return true;
	}
	
	public String getType() { return _type.name(); }
	public void setType(String type) { _type = Type.valueOf(type); }
}
