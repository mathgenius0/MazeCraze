package cs275.game.mazeCraze.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cloudmine.api.CMApiCredentials;
import com.cloudmine.api.persistance.ClassNameRegistry;
import com.cloudmine.api.rest.callbacks.ObjectModificationResponseCallback;
import com.cloudmine.api.rest.response.ObjectModificationResponse;

import cs275.game.mazeCraze.R;
import cs275.game.mazeCraze.Graphics.Graphic;
import cs275.game.mazeCraze.View.MazeCreatorView;

/**
 * 
 *
 */
public class CreatorActivity extends Activity implements OnClickListener {

	private static final String APP_ID = "849d9c0416c54f8b9b9569717b67ead3";
	private static final String API_KEY = "4d0454120b944a63a14eb9d645088460";
	private MazeCreatorView creator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		
		// Import the intent, i.e. the input from the previous menu //
		Intent intent = getIntent();
		String name = intent.getStringExtra( "name" );
		int sizeX = intent.getIntExtra( "gridx", 10 );
		int sizeY = intent.getIntExtra( "gridy", 10 );
		Graphic walls = Graphic.valueOf( intent.getStringExtra( "walls" ) );
		Graphic floors = Graphic.valueOf( intent.getStringExtra( "floors" ) );
		
		CMApiCredentials.initialize( APP_ID, API_KEY, getApplicationContext() );
		setContentView( R.layout.creator_menu );
		LinearLayout layout = (LinearLayout) findViewById( R.id.laycreator );
		
		// Make the creator view for display, which needs the grid //
		creator = new MazeCreatorView(this, sizeX, sizeY, walls, floors, name);
		layout.addView( creator );
		
		// Create the buttons //
		Button returnButton = (Button) findViewById( R.id.btnreturn );
		returnButton.setOnClickListener( this );
		Button generatorButton = (Button) findViewById( R.id.btngenerate );
		generatorButton.setOnClickListener( this );
		Button saveButton = (Button) findViewById( R.id.btnsave );
		saveButton.setOnClickListener( this );
	}

	@Override
	public void onClick(View v) {
		ObjectModificationResponseCallback responseCallback = new ObjectModificationResponseCallback() {
			public void onCompletion(ObjectModificationResponse response) {
				if ( response.wasSuccess() )
					Toast.makeText( CreatorActivity.this, "Grid successfully saved", Toast.LENGTH_SHORT ).show();
			}

			public void onFailure(Throwable e, String msg) {
				Log.v( "cloudmine", "failed to save grid", e );
				Toast.makeText( CreatorActivity.this, "Grid failed to save", Toast.LENGTH_SHORT ).show();
			}
		}; 
		
		switch ( v.getId() ) {
		case R.id.btnreturn:
			NavUtils.navigateUpFromSameTask( this );
			break;
		case R.id.btngenerate:
			//TODO
			break;
		case R.id.btnsave:
			creator.getGrid().save();
			break;
		}
	}

//	static {
//		ClassNameRegistry.register( Grid.CLASS_NAME, Grid.class );
//	}
}
