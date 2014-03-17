package cs275.game.mazeCraze.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import cs275.game.mazeCraze.R;
import cs275.game.mazeCraze.Generator.MazeGenerator;
import cs275.game.mazeCraze.Graphics.Graphic;

public class RandomActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.random_menu );
		
		Button returnButton = (Button) findViewById( R.id.btnreturn );
		returnButton.setOnClickListener( this );
		
		Button generateButton = (Button) findViewById( R.id.btngenerate );
		generateButton.setOnClickListener( this );
		
		Spinner walls = (Spinner) findViewById( R.id.spinwall );
		Spinner floors = (Spinner) findViewById( R.id.spinfloor );
		ArrayAdapter<Graphic> wallGraphicAdapter = new ArrayAdapter<Graphic>( this, android.R.layout.simple_spinner_item,
				Graphic.getWallValues() );
		ArrayAdapter<Graphic> floorGraphicAdapter = new ArrayAdapter<Graphic>( this, android.R.layout.simple_spinner_item,
				Graphic.getFloorValues() );
		
		wallGraphicAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		floorGraphicAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		
		walls.setAdapter( wallGraphicAdapter );
		floors.setAdapter( floorGraphicAdapter );
		
		Spinner generator = (Spinner) findViewById( R.id.spinalgorithm );//TODO edit R.id for this
		ArrayAdapter<MazeGenerator> generatorAdaptor = new ArrayAdapter<MazeGenerator>( this,
				android.R.layout.simple_spinner_item, MazeGenerator.values() );
		
		generatorAdaptor.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		generator.setAdapter( generatorAdaptor );
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId() ) {
		case R.id.btnreturn:
			NavUtils.navigateUpFromSameTask( this );
			break;
		case R.id.btngenerate:
			Intent intent = new Intent( this, NavigatorActivity.class );
			
			int sizeX = Integer.parseInt( ( (TextView) findViewById( R.id.txtgridx ) ).getText().toString() );
			intent.putExtra( "gridx", sizeX );
			int sizeY = Integer.parseInt( ( (TextView) findViewById( R.id.txtgridy ) ).getText().toString() );
			intent.putExtra( "gridy", sizeY );
			
			Graphic walls = (Graphic) ( (Spinner) findViewById( R.id.spinwall ) ).getSelectedItem();
			intent.putExtra( "walls", walls.name() );
			Graphic floors = (Graphic) ( (Spinner) findViewById( R.id.spinfloor ) ).getSelectedItem();
			intent.putExtra( "floors", floors.name() );
			
			MazeGenerator generator = (MazeGenerator) ( (Spinner) findViewById( R.id.spinalgorithm ) )
					.getSelectedItem();
			intent.putExtra( "algorithm", generator.name() );
			
			startActivity( intent );
			break;
		}
	}
}
