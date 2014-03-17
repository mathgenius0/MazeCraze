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
import cs275.game.mazeCraze.R;
import cs275.game.mazeCraze.Graphics.Graphic;

public class LoadActivity extends Activity implements OnClickListener {

	@Override //TODO
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.random_menu );
		Button ret = (Button) findViewById( R.id.btnreturn );
		ret.setOnClickListener( this );
		
		Button gen = (Button) findViewById( R.id.btngenerate );
		gen.setOnClickListener( this );
		
		Spinner walls = (Spinner) findViewById(R.id.spinwall);
		Spinner floors = (Spinner) findViewById(R.id.spinfloor);
		
		ArrayAdapter<Graphic> adapter = new ArrayAdapter<Graphic>(this,
				android.R.layout.simple_spinner_item, Graphic.values());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		walls.setAdapter(adapter);
		floors.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId() ) {
		case R.id.btnreturn:
			NavUtils.navigateUpFromSameTask(this);
			break;
		case R.id.btngenerate:
			startActivity( new Intent( this, LoadActivity.class ) );
			break;
		}
	}

}
