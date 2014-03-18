package cs275.game.mazeCraze.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import cs275.game.mazeCraze.MazeNavigatorView;
import cs275.game.mazeCraze.Generator.MazeGenerator;
import cs275.game.mazeCraze.Graphics.Graphic;

public class NavigatorActivity extends Activity {
	static NavigatorActivity _context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		_context = this;
		// Pull out information from the intent that was sent by the Random Activity //
		Intent intent = getIntent();
		int sizeX = intent.getIntExtra( "gridx", 10 );
		int sizeY = intent.getIntExtra( "gridy", 10 );
		Graphic walls = Graphic.valueOf( intent.getStringExtra( "walls" ) );
		Graphic floors = Graphic.valueOf( intent.getStringExtra( "floors" ) );
		MazeGenerator generator = MazeGenerator.valueOf( intent.getStringExtra( "algorithm" ) );
		// Instantiate a Maze Navigator View based upon this information //
		setContentView( new MazeNavigatorView( this, generator, sizeX, sizeY, walls, floors ) );
	}

	public static void showWin() {
		// Create and display a dialog saying that the user has won //
		AlertDialog.Builder alertDialog = new AlertDialog.Builder( _context );
		alertDialog.setTitle( "Congratulations!" );
		alertDialog.setMessage( "You beat the maze!\nClick OK to return back to the menu." );
		alertDialog.setNeutralButton( "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Send them back to the main menu //
				Intent intent = new Intent( _context, MainActivity.class );
				NavUtils.navigateUpTo( _context, intent );
			}
		} );
		alertDialog.create().show();
	}
}
