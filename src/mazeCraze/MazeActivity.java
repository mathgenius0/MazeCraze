package mazeCraze;

import android.app.Activity;
import android.os.Bundle;

public class MazeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GLcanvas d = new GLcanvas(this);
		setContentView(d);
	}
}
