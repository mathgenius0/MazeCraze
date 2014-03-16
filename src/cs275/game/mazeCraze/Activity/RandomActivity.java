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
		Button ret = (Button) findViewById( R.id.btnreturn );
		ret.setOnClickListener( this );
		Button gen = (Button) findViewById( R.id.btngenerate );
		gen.setOnClickListener( this );
		Spinner walls = (Spinner) findViewById( R.id.spinwall );
		Spinner floors = (Spinner) findViewById( R.id.spinfloor );
		ArrayAdapter<Graphic> graphicadapter = new ArrayAdapter<Graphic>( this, android.R.layout.simple_spinner_item,
				Graphic.values() );
		graphicadapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		walls.setAdapter( graphicadapter );
		floors.setAdapter( graphicadapter );
		Spinner algorithm = (Spinner) findViewById( R.id.spinalgorithm );
		ArrayAdapter<MazeGenerator.Algorithms> algorithmadapter = new ArrayAdapter<MazeGenerator.Algorithms>( this,
				android.R.layout.simple_spinner_item, MazeGenerator.Algorithms.values() );
		graphicadapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		algorithm.setAdapter( algorithmadapter );
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
			MazeGenerator.Algorithms algorithm = (MazeGenerator.Algorithms) ( (Spinner) findViewById( R.id.spinalgorithm ) )
					.getSelectedItem();
			intent.putExtra( "algorithm", algorithm.name() );
			startActivity( intent );
			break;
		}
	}
}
