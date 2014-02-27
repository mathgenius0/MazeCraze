package cs275.game.mazeCraze;

import android.app.Activity;
import android.os.Bundle;

public class MazeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GLcanvas d = new GLcanvas(this);//TODO should this class even exist like this? where grid go?
		setContentView(d);
	}
}
