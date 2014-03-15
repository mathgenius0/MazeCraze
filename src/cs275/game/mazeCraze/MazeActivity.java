package cs275.game.mazeCraze;

import android.app.Activity;
import android.os.Bundle;

public class MazeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		//setContentView( new MazeNavigatorView( this ) );
		//setContentView( new MazeCreatorView( this ) );
		setContentView(R.layout.creator);
	}
}
