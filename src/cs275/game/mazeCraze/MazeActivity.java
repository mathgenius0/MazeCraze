package cs275.game.mazeCraze;

import android.app.Activity;
import android.os.Bundle;

import com.cloudmine.api.CMApiCredentials;
import com.cloudmine.api.persistance.ClassNameRegistry;

public class MazeActivity extends Activity {
	// Find this in your developer console
	private static final String APP_ID = "849d9c0416c54f8b9b9569717b67ead3";
	// Find this in your developer console
	private static final String API_KEY = "4d0454120b944a63a14eb9d645088460";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate( savedInstanceState );
		//setContentView( new MazeNavigatorView( this ) );
		//setContentView( new MazeCreatorView( this ) );

		CMApiCredentials.initialize( APP_ID, API_KEY, getApplicationContext() );
		setContentView( R.layout.creator );
	}

	static {
		ClassNameRegistry.register( Grid.CLASS_NAME, Grid.class );
	}
}
