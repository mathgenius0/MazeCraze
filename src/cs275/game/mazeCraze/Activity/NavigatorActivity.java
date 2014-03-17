package cs275.game.mazeCraze.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cs275.game.mazeCraze.Generator.MazeGenerator;
import cs275.game.mazeCraze.Graphics.Graphic;
import cs275.game.mazeCraze.View.MazeNavigatorView;

public class NavigatorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		Intent intent = getIntent();
		int sizeX = intent.getIntExtra( "gridx", 10 );
		int sizeY = intent.getIntExtra( "gridy", 10 );
		Graphic walls = Graphic.valueOf( intent.getStringExtra( "walls" ) );
		Graphic floors = Graphic.valueOf( intent.getStringExtra( "floors" ) );
		MazeGenerator generator = MazeGenerator.valueOf( intent.getStringExtra( "algorithm" ) );

		setContentView( new MazeNavigatorView( this, generator, sizeX, sizeY, walls, floors ) );
	}
}
