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
import cs275.game.mazeCraze.Graphics.Graphic;

public class CreateActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.create_menu );
		Button ret = (Button) findViewById( R.id.btnreturn );
		ret.setOnClickListener( this );
		Button gen = (Button) findViewById( R.id.btnstartcreate );
		gen.setOnClickListener( this );
		Spinner walls = (Spinner) findViewById( R.id.spinwall );
		Spinner floors = (Spinner) findViewById( R.id.spinfloor );
		ArrayAdapter<Graphic> graphicadapter = new ArrayAdapter<Graphic>( this, android.R.layout.simple_spinner_item,
				Graphic.values() );
		graphicadapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		walls.setAdapter( graphicadapter );
		floors.setAdapter( graphicadapter );
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId() ) {
		case R.id.btnreturn:
			NavUtils.navigateUpFromSameTask( this );
			break;
		case R.id.btnstartcreate:
			Intent intent = new Intent( this, CreatorActivity.class );
			String name = ( (TextView) findViewById( R.id.txtname ) ).getText().toString();
			intent.putExtra( "name", name );
			int sizeX = Integer.parseInt( ( (TextView) findViewById( R.id.txtgridx ) ).getText().toString() );
			intent.putExtra( "gridx", sizeX );
			int sizeY = Integer.parseInt( ( (TextView) findViewById( R.id.txtgridy ) ).getText().toString() );
			intent.putExtra( "gridy", sizeY );
			Graphic walls = (Graphic) ( (Spinner) findViewById( R.id.spinwall ) ).getSelectedItem();
			intent.putExtra( "walls", walls.name() );
			Graphic floors = (Graphic) ( (Spinner) findViewById( R.id.spinfloor ) ).getSelectedItem();
			intent.putExtra( "floors", floors.name() );
			startActivity( intent );
			break;
		}
	}

}
